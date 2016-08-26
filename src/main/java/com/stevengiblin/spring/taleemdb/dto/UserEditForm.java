package com.stevengiblin.spring.taleemdb.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.stevengiblin.spring.taleemdb.entities.Usr.Role;

public class UserEditForm {

	@NotNull
	@Size(min=1, max=100, message="{nameSizeError}")
	private String name;
	
	private Set<Role> roles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
