package com.stevengiblin.spring.taleemdb.services;

import javax.mail.MessagingException;

import org.springframework.validation.BindingResult;

import com.stevengiblin.spring.taleemdb.dto.ForgotPasswordForm;
import com.stevengiblin.spring.taleemdb.dto.ResetPasswordForm;
import com.stevengiblin.spring.taleemdb.dto.SignupForm;
import com.stevengiblin.spring.taleemdb.dto.UserEditForm;
import com.stevengiblin.spring.taleemdb.entities.Usr;

public interface UsrService {
	public abstract void signup(SignupForm signupForm);
	public abstract void verify(String verificationCode);
	public abstract void sendVerificationEmail() throws MessagingException;
	public abstract void forgotPassword(ForgotPasswordForm forgotPasswordForm);
	public abstract void resetPassword(String forgotPasswordCode, ResetPasswordForm resetPasswordForm,
			BindingResult result);
	public abstract Usr findOne(long userId);
	public abstract void update(long userId, UserEditForm userEditForm);
}
