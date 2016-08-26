package com.stevengiblin.spring.taleemdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stevengiblin.spring.taleemdb.entities.Usr;

public interface UsrRepository extends JpaRepository<Usr, Long> {

	//@Query("select u from User u where u.email =?1") // optional
	Usr findByEmail(String email);

	//@Query("select u from User u where u.forgotPasswordCode =?1") // optional 
	Usr findByForgotPasswordCode(String forgotPasswordCode);

		
}
