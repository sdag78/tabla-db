package com.stevengiblin.spring.taleemdb.services;

import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.validation.BindingResult;

import com.stevengiblin.spring.taleemdb.dto.ForgotPasswordForm;
import com.stevengiblin.spring.taleemdb.dto.ResetPasswordForm;
import com.stevengiblin.spring.taleemdb.dto.SignupForm;
import com.stevengiblin.spring.taleemdb.dto.UserDetailsImpl;
import com.stevengiblin.spring.taleemdb.dto.UserEditForm;
import com.stevengiblin.spring.taleemdb.entities.Usr;
import com.stevengiblin.spring.taleemdb.entities.Usr.Role;
import com.stevengiblin.spring.taleemdb.mail.MailSender;
import com.stevengiblin.spring.taleemdb.repositories.UsrRepository;
import com.stevengiblin.spring.taleemdb.util.MyUtil;
import com.stevengiblin.spring.taleemdb.util.UserRepositoryUtil;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UsrServiceImpl implements UsrService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UsrServiceImpl.class);
	
	private UsrRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private MailSender mailSender;
	
	@Autowired
	public UsrServiceImpl(UsrRepository userRepository, PasswordEncoder passwordEncoder, MailSender mailSender) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.mailSender = mailSender;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void signup(SignupForm signupForm) {
		final Usr user = new Usr();
		user.setEmail(signupForm.getEmail());
		user.setName(signupForm.getName());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		user.getRoles().add(Role.UNVERIFIED);
		user.setVerificationCode(RandomStringUtils.randomAlphanumeric(16)); //21, 03:36
		userRepository.save(user);
		
		TransactionSynchronizationManager.registerSynchronization(
				new TransactionSynchronizationAdapter() {
					@Override
					public void afterCommit() {
						try {
							sendVerificationEmail(user);
						} catch (MessagingException e) {
							logger.error(ExceptionUtils.getStackTrace(e));
						}
					}

				});
		
	}

	public void sendVerificationEmail() throws MessagingException {
		Usr sessionUser = MyUtil.getSessionUser();
		sendVerificationEmail(sessionUser);
	}
	public void sendVerificationEmail(Usr user) throws MessagingException {
		String verifyLink = MyUtil.hostUrl() + "/users/" + user.getVerificationCode() + "/verify";
		mailSender.send(user.getEmail(), MyUtil.getMessage("verifySubject"), MyUtil.getMessage("verifyEmail", verifyLink));
		logger.info("Verification mail to " + user.getEmail() + " queued.");
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usr user = userRepository.findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		
		return new UserDetailsImpl(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void verify(String verificationCode) {
		long loggedInUserId = MyUtil.getSessionUser().getId();
		Usr user = userRepository.findOne(loggedInUserId);
		
		MyUtil.validate(user.getRoles().contains(Role.UNVERIFIED), "alreadyVerified");
		MyUtil.validate(user.getVerificationCode().equals(verificationCode), "incorrect", "verification code");
		
		user.getRoles().remove(Role.UNVERIFIED);
		user.setVerificationCode(null);
		userRepository.save(user);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void forgotPassword(ForgotPasswordForm form) {
		final Usr user = userRepository.findByEmail(form.getEmail());
		final String forgotPasswordCode = UserRepositoryUtil.generateUniqueForgotPasswordCode(userRepository);
		
		user.setForgotPasswordCode(forgotPasswordCode);
		final Usr savedUser = userRepository.save(user);
		
		TransactionSynchronizationManager.registerSynchronization( 
				
				new TransactionSynchronizationAdapter(){
					@Override
					public void afterCommit() {
						try {
							mailForgotPasswordLink(savedUser);
						} catch (MessagingException e) {
							logger.error(ExceptionUtils.getStackTrace(e));
						}
					}

				});
	}
	
	private void mailForgotPasswordLink(Usr user) throws MessagingException {
			
		String forgotPasswordLink = 
				MyUtil.hostUrl() + 
				"/reset-password/" + 
				user.getForgotPasswordCode();
		
		mailSender.send(
				user.getEmail(), 
				MyUtil.getMessage("forgotPasswordSubject"), 
				MyUtil.getMessage("forgotPasswordEmail", forgotPasswordLink));
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void resetPassword(String forgotPasswordCode, ResetPasswordForm resetPasswordForm, BindingResult result) {
		
		Usr user = userRepository.findByForgotPasswordCode(forgotPasswordCode);
		if (user == null) 
			result.reject("invalidForgotPassword");
		
		if (result.hasErrors()) 
			return;
		
		user.setForgotPasswordCode(null);
		user.setPassword(passwordEncoder.encode(resetPasswordForm.getPassword().trim()));
		userRepository.save(user);
		
	}

	@Override
	public Usr findOne(long userId) {
		
		Usr loggedIn = MyUtil.getSessionUser();
		Usr user = userRepository.findOne(userId);
		
		if (loggedIn == null || 
			loggedIn.getId() != user.getId() && !loggedIn.isAdmin())
			
			user.setEmail("Confidential");
			
		return user;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void update(long userId, UserEditForm userEditForm) {
		Usr loggedIn = MyUtil.getSessionUser();
		MyUtil.validate(loggedIn.isAdmin() || loggedIn.getId() == userId, "noPermission");
		Usr user = userRepository.findOne(userId);
		user.setName(userEditForm.getName());
		if (loggedIn.isAdmin())
			user.setRoles(userEditForm.getRoles());
		userRepository.save(user);
	}
	
}
