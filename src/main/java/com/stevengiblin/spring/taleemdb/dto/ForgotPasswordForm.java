package com.stevengiblin.spring.taleemdb.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.stevengiblin.spring.taleemdb.entities.Usr;

public class ForgotPasswordForm {
	
	@NotNull
	@Size(min=1, max=Usr.EMAIL_MAX, message="{emailSizeError}")
	@Pattern(regexp=Usr.EMAIL_PATTERN, message="{emailPatternError}")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
