package com.example.demo.config;

import com.example.demo.config.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// security-context.xml 설정 내용

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private PrincipalDetailService PrincipalDetailService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();													// 중간 공격을 맞아주는 설정
		
		http
			.authorizeRequests()
//				.antMatchers("/favicon.ico").permitAll()
				.antMatchers("/css/**").permitAll()// 인가 처리
				.antMatchers("/js/**").permitAll()// 인가 처리
				.antMatchers("/images/**").permitAll()// 인가 처리


				.antMatchers("/", "/public","/verifyIamport2/getToken","/verifyIamport2/cancel","/verifyIamport2/searchOne","/verifyIamport2/searchAll").permitAll()
				.antMatchers("/sms/send","/checkPhone","/sendEmail","/checkEmail").permitAll()
				.antMatchers("/memberJoin","/login").permitAll()	// 기본 권한
				.antMatchers("/menu_catagory","/selectRest","/selectRest/{food_catagory}","/review_tab").permitAll() // 주문정보
				.antMatchers("/orderCheck","/orderCheck1").permitAll()	// 관리자 입장에서의 주문내역


																				// hasRole을 사용시 기본적으로 Role_ 이 제공된다
				.antMatchers("/user").hasRole("User")                            // Role_User
				.antMatchers("/member").hasRole("Member")                        // Role_Member
				.antMatchers("/admin").hasRole("Admin")                            // Role_Admin
				.antMatchers("/deleteOrder/**").authenticated()
				.anyRequest().authenticated()									// 나머지 URL은 모두 인증작업이 완료된 이후 접근가능

				.and()

				.formLogin()													// 로그인이 되지 않은 상태에서 자동으로 로그인 페이지로 리다이렉팅이 된다
				.loginPage("/login").permitAll()                        // 커스텀 로그인 페이지 설정
				.usernameParameter("u_email")
				.successHandler(new CustomLoginSuccessHandler())				// 로그인시 역활에 따른 페이지 설정
				.failureHandler(new CustomAuthenticationFailureHandler())		// 로그인 실패 예외처리

				.and()
				.oauth2Login()
				
				.and()
				
				.logout()
				.logoutUrl("/logout")
				.permitAll()
				.addLogoutHandler(new CustomLogoutHandler())					// 로그아웃시 세션초기화
				.logoutSuccessHandler(new CustomLogoutSuccessHandler())        // 로그아웃 성공적으로 되었을경우 기본위치로 페이지 이동

				.and()
				.exceptionHandling()
				.authenticationEntryPoint(new CustomAuthenticationEntryPoint())	// 인증 실패 예외처리
				.accessDeniedHandler(new CustomAccessDeniedHandler());			// 권한 실패 예외처리

//		http
//				.rememberMe()
//				.alwaysRemember(false)
//				.rememberMeParameter("remember-me")
//				.tokenValiditySeconds(60*60)
//				.tokenRepository(tokenRepository())
//				.userDetailsService(principalDetailsService);
	}

	// 인증처리 함수
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// auth.userDetailsService : DB 연결할때 사용되는 함수

		auth.userDetailsService(PrincipalDetailService).passwordEncoder(passwordEncoder);

//		auth
//				.inMemoryAuthentication()
//				.withUser("user")
//				.password(passwordEncoder.encode("1234"))
//				.roles("User");
//
//		auth
//				.inMemoryAuthentication()
//				.withUser("member")
//				.password(passwordEncoder.encode("1234"))
//				.roles("Member");
//
//		auth
//				.inMemoryAuthentication()
//				.withUser("admin")
//				.password(passwordEncoder.encode("1234"))
//				.roles("Admin");
		
	}

//	@Autowired
//	private DataSource datasource;
//
//	@Bean
//	public PersistentTokenRepository tokenRepository(){
//		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
//		repo.setDataSource(datasource);
//		return repo;
//	}


}
