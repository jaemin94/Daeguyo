package com.example.demo.config.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {


		System.out.println("CustomAuthenticationFailureHandler's onAuthenticationFailure");
		
		request.getSession().setAttribute("msg", "[SERVER ERROR]ID/PW를 확인해주세요.");
//		request.getSession().setAttribute("msg", exception.getMessage());
		response.sendRedirect(request.getContextPath() + "/login");
	}

}
