package com.stevengiblin.spring.taleemdb.validators;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.stevengiblin.spring.taleemdb.dto.SignupForm;
import com.stevengiblin.spring.taleemdb.entities.Usr;
import com.stevengiblin.spring.taleemdb.repositories.UsrRepository;

@Component
public class SignupFormValidator extends LocalValidatorFactoryBean {

	private UsrRepository userRepository;
	
	@Resource
	public void setUserRepository(UsrRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(SignupForm.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors, final Object... validationHints) {
		super.validate(obj,  errors, validationHints);
		
		if (!errors.hasErrors()) {
			SignupForm signupForm = (SignupForm) obj;
			Usr user = userRepository.findByEmail(signupForm.getEmail());
			if (user != null) {
				errors.rejectValue("email", "emailNotUnique");
			}
		}
	}
}
