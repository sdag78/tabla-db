package com.stevengiblin.spring.taleemdb.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoScriptValidator implements ConstraintValidator<NoScript, String> {

	@Override
	public void initialize(NoScript noScript) { }

	@Override
	public boolean isValid(String userInput, ConstraintValidatorContext cxt) {
		if(userInput == null) {
			return false;
		}
		return MyRegexUtil.isValidCharacterInput(userInput);
	}

}
