package com.stevengiblin.spring.taleemdb.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.stevengiblin.spring.taleemdb.dto.EditPieceForm;

/*
 * validations:
 * 	pieceName not blank
 * 	numberOfBeats > 0
 */
@Component
public class EditPieceFormValidator extends LocalValidatorFactoryBean {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(EditPieceForm.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors, final Object... validationHints) {
		super.validate(obj,  errors, validationHints);
		
		if (!errors.hasErrors()) {
			EditPieceForm editPieceForm = (EditPieceForm) obj;
			
			if (!MyRegexUtil.isValidCharacterInput(editPieceForm.getName())) {
				errors.rejectValue("Name", "invalidCharacters");
			}
			
			if (!MyRegexUtil.isValidCharacterInput(editPieceForm.getDescription())) {
				errors.rejectValue("Description", "invalidCharacters");
			}
			
			if (!MyRegexUtil.isValidCharacterInput(editPieceForm.getPaltaTableNumber())) {
				errors.rejectValue("PaltaTableNumber", "invalidCharacters");
			}
			
			if (!MyRegexUtil.isValidCharacterInput(editPieceForm.getFirstFewBols())) {
				errors.rejectValue("FirstFewBols", "invalidCharacters");
			}
			
			if (!MyRegexUtil.isValidCharacterInput(editPieceForm.getSource())) {
				errors.rejectValue("Source", "invalidCharacters");
			}
			
		}
	}
}
