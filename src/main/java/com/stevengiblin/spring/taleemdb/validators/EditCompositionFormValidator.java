package com.stevengiblin.spring.taleemdb.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.stevengiblin.spring.taleemdb.dto.EditCompositionForm;

/*
 * validations:
 * 
 * 	anything can be blank, however if characters present must be alphanumeric, or 1)comma, 2)round brackets, 3)single hyphen, 4)square brackets
 * 	pieceName not blank
 * 	numberOfBeats > 0
 */
@Component
public class EditCompositionFormValidator extends LocalValidatorFactoryBean {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(EditCompositionForm.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors, final Object... validationHints) {
		super.validate(obj,  errors, validationHints);
		
		if (!errors.hasErrors()) {
			EditCompositionForm editCompositionForm = (EditCompositionForm) obj;
			
			doCompositionFormValidation(editCompositionForm, errors);
		}
	}

	/*
	 * validations:
	 * 
	 * 	anything can be blank, however if characters present must be alphanumeric, or 1)comma, 2)round brackets, 3)single hyphen, 4)square brackets
	 * 	pieceName not blank
	 * 	numberOfBeats > 0
	 */
	private void doCompositionFormValidation(EditCompositionForm editCompositionForm, Errors errors) {
		
		if (!errors.hasErrors()) {
				
			if (!MyRegexUtil.isValidCharacterInput(editCompositionForm.getBols())) {
				errors.rejectValue("Bols", "invalidCharacters");
			}
			
			if (!MyRegexUtil.isValidCharacterInput(editCompositionForm.getName())) {
				errors.rejectValue("Name", "invalidCharacters");
			}
			
			if (!MyRegexUtil.isValidCharacterInput(editCompositionForm.getComments())) {
				errors.rejectValue("Comments", "invalidCharacters");
			}
		}
	}
}
