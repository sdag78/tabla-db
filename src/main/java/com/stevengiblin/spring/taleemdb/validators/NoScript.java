package com.stevengiblin.spring.taleemdb.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NoScriptValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoScript {

	String message() default "There were invalid characters included which are not permitted (Only a-z A-Z 0-9 ,.-= allowed)";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}