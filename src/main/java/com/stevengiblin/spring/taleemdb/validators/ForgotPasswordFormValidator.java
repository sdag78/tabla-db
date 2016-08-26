package com.stevengiblin.spring.taleemdb.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.stevengiblin.spring.taleemdb.dto.ForgotPasswordForm;
import com.stevengiblin.spring.taleemdb.entities.Usr;
import com.stevengiblin.spring.taleemdb.repositories.UsrRepository;

@Component
public class ForgotPasswordFormValidator extends LocalValidatorFactoryBean {

	private UsrRepository userRepository;
	
	@Autowired
	public ForgotPasswordFormValidator(UsrRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(ForgotPasswordForm.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors, final Object... validationHints) {
		super.validate(obj,  errors, validationHints);
		
		if (!errors.hasErrors()) {
			ForgotPasswordForm forgotPasswordForm = (ForgotPasswordForm) obj;
			Usr user = userRepository.findByEmail(forgotPasswordForm.getEmail());
			if (user == null) {
				errors.rejectValue("email", "notFound");
			}
		}
	}
}
