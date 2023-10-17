package com.example.demo.config.auth;

import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserMapper mapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String u_email) throws UsernameNotFoundException {
		log.info("U_EMAIL: " + u_email);
		UserDto dto = mapper.selectOne(u_email);

		if (dto == null) {
            throw new UsernameNotFoundException("User not found with username: " + u_email);
		}
		
		return new PrincipalDetails(dto);
	}

}
