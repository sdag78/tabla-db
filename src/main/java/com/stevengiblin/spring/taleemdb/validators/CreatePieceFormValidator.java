package com.stevengiblin.spring.taleemdb.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.stevengiblin.spring.taleemdb.dto.CreatePieceForm;

/*
 * validations:
 * 	pieceName not blank
 * 	numberOfBeats > 0
 */
@Component
public class CreatePieceFormValidator extends LocalValidatorFactoryBean {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(CreatePieceForm.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors, final Object... validationHints) {
		super.validate(obj,  errors, validationHints);
		
		if (!errors.hasErrors()) {
			CreatePieceForm createPieceForm = (CreatePieceForm) obj;
			
			if (!MyRegexUtil.isValidCharacterInput(createPieceForm.getName())) {
				errors.rejectValue("Name", "invalidCharacters");
			}
			
			if (!MyRegexUtil.isValidCharacterInput(createPieceForm.getDescription())) {
				errors.rejectValue("Description", "invalidCharacters");
			}
			
			if (!MyRegexUtil.isValidCharacterInput(createPieceForm.getPaltaTableNumber())) {
				errors.rejectValue("PaltaTableNumber", "invalidCharacters");
			}
			
			if (!MyRegexUtil.isValidCharacterInput(createPieceForm.getFirstFewBols())) {
				errors.rejectValue("FirstFewBols", "invalidCharacters");
			}
			
			if (!MyRegexUtil.isValidCharacterInput(createPieceForm.getSource())) {
				errors.rejectValue("Source", "invalidCharacters");
			}
			
		}
	}
}
