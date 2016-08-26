package com.stevengiblin.spring.taleemdb.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stevengiblin.spring.taleemdb.dto.UserDetailsImpl;
import com.stevengiblin.spring.taleemdb.entities.Usr;

@Component
public class MyUtil {

	@Autowired
	public MyUtil(MessageSource messageSource) {
		MyUtil.messageSource = messageSource;
	}

	public static void flash(RedirectAttributes redirectAttributes, String kind, String messageKey) {
		redirectAttributes.addFlashAttribute("flashKind", kind);
		redirectAttributes.addFlashAttribute("flashMessage", MyUtil.getMessage(messageKey));
	}

	private static String activeProfiles;
	
	@Value("${spring.profiles.active}")
	public void setActiveProfile(String activeProfiles) {
		MyUtil.activeProfiles = activeProfiles;
	}
	
	public static boolean isDev() {
		return activeProfiles.equals("dev");
	}
	
	private static String hostAndPort;
	
	@Value("${hostAndPort}") 
	public void setHostAndPort(String hostAndPort) {
		MyUtil.hostAndPort = hostAndPort;
	}
	
	public static String hostUrl() {
		return (isDev() ? "http://" : "https://") + hostAndPort;
	}
	
	private static MessageSource messageSource;
	
	public static String getMessage(String messageKey, Object... args) {
		return messageSource.getMessage(messageKey, args, Locale.getDefault());
	}

	public static void validate(boolean valid, String msgContent, Object... args) {
		if (!valid) {
			throw new RuntimeException(getMessage(msgContent, args));
		}
	}
	
	public static Usr getSessionUser() {
		UserDetailsImpl auth = getAuth();
		return auth == null ? null : auth.getUser();
	}

	public static UserDetailsImpl getAuth() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			Object principal = auth.getPrincipal();
			if (principal instanceof UserDetailsImpl) {
				return (UserDetailsImpl) principal;
			}
		}
		return null;
	}

	public static boolean checkUserIdValid(long userId) {
		return (userId == getSessionUserId());
	}
	
	public static long getSessionUserId() {
		Usr user = MyUtil.getSessionUser(); 
		return user.getId();		
	}
}
