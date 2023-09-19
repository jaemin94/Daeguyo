package com.example.demo.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		System.out.println("CustomAccessDeniedHandler's handle");
		System.out.println("권한 없음! : " +accessDeniedException);
		System.out.println("권한 없음! : " +accessDeniedException.getMessage());
		
		request.setAttribute("msg", accessDeniedException.getMessage());
		request.getRequestDispatcher("").forward(request, response);
	}

}
