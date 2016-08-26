package com.stevengiblin.spring.taleemdb.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.stevengiblin.spring.taleemdb.dto.CreateCompositionForm;

@Component
public class CreateCompositionFormValidator extends LocalValidatorFactoryBean {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(CreateCompositionForm.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors, final Object... validationHints) {
		super.validate(obj,  errors, validationHints);
		
		if (!errors.hasErrors()) {
			CreateCompositionForm createCompositionFor = (CreateCompositionForm) obj;
			
			if (!MyRegexUtil.isValidCharacterInput(createCompositionFor.getBols())) {
				errors.rejectValue("Bols", "invalidCharacters");
			}
			
			if (!MyRegexUtil.isValidCharacterInput(createCompositionFor.getName())) {
				errors.rejectValue("Name", "invalidCharacters");
			}
			
			if (!MyRegexUtil.isValidCharacterInput(createCompositionFor.getComments())) {
				errors.rejectValue("Comments", "invalidCharacters");
			}
		}
	}
}
