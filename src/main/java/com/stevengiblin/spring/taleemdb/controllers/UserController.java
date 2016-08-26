package com.stevengiblin.spring.taleemdb.controllers;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stevengiblin.spring.taleemdb.dto.UserEditForm;
import com.stevengiblin.spring.taleemdb.entities.Usr;
import com.stevengiblin.spring.taleemdb.services.UsrService;
import com.stevengiblin.spring.taleemdb.util.MyUtil;

@Controller
@RequestMapping("/users")
public class UserController {

	private UsrService userService;
	
	@Autowired
	public void setUserService(UsrService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/{verificationCode}/verify")
	public String verify(@PathVariable("verificationCode") String verificationCode, 
			RedirectAttributes redirectAttributes,
			HttpServletRequest request
			) throws ServletException {
		
		userService.verify(verificationCode);
		MyUtil.flash(redirectAttributes, "success", "verificationSuccess");
		request.logout();
		return "redirect:/";
	}
	
	@RequestMapping("/resend-verification-mail")
	public String resendVerificationMail() throws MessagingException {
		userService.sendVerificationEmail();
		return "redirect:/";
	}

	@RequestMapping("/{userId}")
	public String verify(@PathVariable("userId") long userId, Model model) {
		model.addAttribute(userService.findOne(userId));
		return "user";
	}
	
	@RequestMapping(value = "/{userId}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("userId") long userId, Model model) {
		
		Usr user = userService.findOne(userId);
		UserEditForm form = new UserEditForm();
		form.setName(user.getName());
		form.setRoles(user.getRoles());
		model.addAttribute(form);
		return "user-edit";
	}
	
	@RequestMapping(value = "/{userId}/edit", method = RequestMethod.POST)
	public String edit(
			@PathVariable("userId") long userId,
			@ModelAttribute("userEditForm") @Valid UserEditForm userEditForm,
			BindingResult result, 
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws ServletException {
		
		if (result.hasErrors()) return "user-edit"; 
		userService.update(userId, userEditForm);
		MyUtil.flash(redirectAttributes,  "success",  "editSuccessful");
		request.logout();
		return "redirect:/";
	}
	
}
