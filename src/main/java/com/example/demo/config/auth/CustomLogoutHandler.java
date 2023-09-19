package com.example.demo.config.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class CustomLogoutHandler implements LogoutHandler{

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		System.out.println("CustomLogoutHandler's logout");
		HttpSession session = request.getSession();
		if(session !=null)
		{
			session.invalidate();
		}
		System.out.println("session : " + session);
		
	}

}
