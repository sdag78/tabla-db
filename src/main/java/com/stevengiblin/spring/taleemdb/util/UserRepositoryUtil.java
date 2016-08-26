package com.stevengiblin.spring.taleemdb.util;

import org.apache.commons.lang3.RandomStringUtils;

import com.stevengiblin.spring.taleemdb.entities.Usr;
import com.stevengiblin.spring.taleemdb.repositories.UsrRepository;

public class UserRepositoryUtil {

	public static String generateUniqueForgotPasswordCode(UsrRepository userRepository) {
		String forgotPasswordCode;
		do {
			forgotPasswordCode = RandomStringUtils.randomAlphanumeric(Usr.RANDOM_CODE_LENGTH);
		} while (UserRepositoryUtil.userRepositoryContainsForgotPasswordCode(forgotPasswordCode, userRepository));
		
		return forgotPasswordCode;
	}

	private static boolean userRepositoryContainsForgotPasswordCode(String forgotPasswordCode, UsrRepository userRepository) {
		Usr user = userRepository.findByForgotPasswordCode(forgotPasswordCode);
		return (user != null);
	}

}
