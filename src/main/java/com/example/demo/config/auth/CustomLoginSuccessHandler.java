package com.example.demo.config.auth;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		System.out.println("CustomLoginSuccessHandler's onAuthenticationSuccess");
		Collection<? extends GrantedAuthority> collection = authentication.getAuthorities();
		collection.forEach((role)->{
			
			System.out.println(role);
			String role_str=role.getAuthority();
			String member_id = authentication.getName();
			System.out.println("member_id : " + member_id);
			System.out.println(role_str);
			request.getSession().setAttribute("role", role_str);
			request.getSession().setAttribute("username", member_id);
			
			
			try {
			if(role_str.equals("ROLE_User"))
			{
				System.out.println("USER 페이지로 이동!");
				
					response.sendRedirect(request.getContextPath()+"/");
			}else if(role_str.equals("ROLE_Member"))
			{
				System.out.println("Member 페이지로 이동!");
				response.sendRedirect(request.getContextPath()+"/");
			}else if(role_str.equals("ROLE_Admin"))
			{
				System.out.println("Admin 페이지로 이동!");
				response.sendRedirect(request.getContextPath()+"/");
			}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

}
