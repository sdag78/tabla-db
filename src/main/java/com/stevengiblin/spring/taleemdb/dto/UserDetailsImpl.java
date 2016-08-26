package com.stevengiblin.spring.taleemdb.dto;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.stevengiblin.spring.taleemdb.entities.Usr;
import com.stevengiblin.spring.taleemdb.entities.Usr.Role;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 6175577806135789535L;
	private Usr user;
	
	public Usr getUser() {
		return user;
	}

	public void setUser(Usr user) {
		this.user = user;
	}

	public UserDetailsImpl(Usr user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(user.getRoles().size() + 1);
		
		for (Role role : user.getRoles()) 
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
		
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
