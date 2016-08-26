package com.stevengiblin.spring.taleemdb.controllers;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stevengiblin.spring.taleemdb.dto.ForgotPasswordForm;
import com.stevengiblin.spring.taleemdb.dto.ResetPasswordForm;
import com.stevengiblin.spring.taleemdb.dto.SignupForm;
import com.stevengiblin.spring.taleemdb.mail.MailSender;
import com.stevengiblin.spring.taleemdb.services.UsrService;
import com.stevengiblin.spring.taleemdb.util.MyUtil;
import com.stevengiblin.spring.taleemdb.validators.ForgotPasswordFormValidator;
import com.stevengiblin.spring.taleemdb.validators.ResetPasswordFormValidator;
import com.stevengiblin.spring.taleemdb.validators.SignupFormValidator;

@Controller
public class RootController {
		
	private static final Logger logger = LoggerFactory.getLogger(RootController.class);
	
	private MailSender mailSender;
	private UsrService userService;
	private SignupFormValidator signupFormValidator;
	private ForgotPasswordFormValidator forgotPasswordFormValidator;
	private ResetPasswordFormValidator resetPasswordFormValidator;
	
	@Autowired
	public RootController(
			MailSender mailSender, 
			UsrService userService, 
			SignupFormValidator signupFormValidator,
			ForgotPasswordFormValidator forgotPasswordFormValidator,
			ResetPasswordFormValidator resetPasswordFormValidator) {
		this.mailSender = mailSender;
		this.userService = userService;
		this.signupFormValidator = signupFormValidator;
		this.forgotPasswordFormValidator = forgotPasswordFormValidator;
		this.resetPasswordFormValidator = resetPasswordFormValidator;
	}
	
	@InitBinder("signupForm")
	protected void initSignupBinder(WebDataBinder binder) {
		binder.setValidator(signupFormValidator);
	}
	
	@InitBinder("forgotPasswordForm")
	protected void initForgotPasswordBinder(WebDataBinder binder) {
		binder.setValidator(forgotPasswordFormValidator);
	}
	
	@InitBinder("resetPasswordForm")
	protected void initResetPasswordBinder(WebDataBinder binder) {
		binder.setValidator(resetPasswordFormValidator);
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home() {
		return "redirect:/pieces";
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public String signup(Model model) throws MessagingException {
		model.addAttribute(new SignupForm());
		return "signup";
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("signupForm") @Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) return "signup";
		userService.signup(signupForm);
		MyUtil.flash(redirectAttributes, "success", "signupSuccess");
		return "redirect:/pieces";
	}
	
	@RequestMapping(value="/forgot-password", method = RequestMethod.GET)
	public String forgotPassword(Model model) {
		model.addAttribute(new ForgotPasswordForm());
		return "forgot-password";
	}

	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgotPassword(
			@ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm forgotPasswordForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) return "forgot-password";
		userService.forgotPassword(forgotPasswordForm);
		MyUtil.flash(redirectAttributes, "info", "checkMailResetPassword");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/reset-password/{forgotPasswordCode}", method = RequestMethod.GET)
	public String resetPassword(@PathVariable("forgotPasswordCode") String forgotPasswordCode, Model model) {
		
		model.addAttribute(new ResetPasswordForm());
		return "reset-password";
	}
	
	@RequestMapping(value = "/reset-password/{forgotPasswordCode}", method = RequestMethod.POST) 
	public String resetPassword(@PathVariable("forgotPasswordCode") String forgotPasswordCode,
			@ModelAttribute("resetPasswordForm") @Valid ResetPasswordForm resetPasswordForm,
			BindingResult result, RedirectAttributes redirectAttributes) throws ServletException {
		
		userService.resetPassword(forgotPasswordCode, resetPasswordForm, result);
		if (result.hasErrors()) return "reset-password";
		MyUtil.flash(redirectAttributes,  "success", "passwordChanged");
		return "redirect:/login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String signup(Model model, HttpServletRequest request) throws MessagingException, ServletException {
		request.logout();
		return "redirect:/"; 
	}
	
}
