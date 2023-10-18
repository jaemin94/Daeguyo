package com.example.demo.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		System.out.println("CustomAuthenticationEntryPoint's commence : " + authException);
		System.out.println("CustomAuthenticationEntryPoint's commence : " + authException.getLocalizedMessage());
		System.out.println("CustomAuthenticationEntryPoint's commence : " + authException.getMessage());
		System.out.println("CustomAuthenticationEntryPoint's commence : " + authException.getCause());
		
		request.getSession().setAttribute("msg", "[SERVER ERROR]ID/PW가 올바르지 않습니다.");
		request.getRequestDispatcher("/login").forward(request, response);
		
	}

}
