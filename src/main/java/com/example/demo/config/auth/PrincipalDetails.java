package com.example.demo.config.auth;

import com.example.demo.domain.daeguyo.CouponDto;
import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.daeguyo.ReviewDto;
import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.mapper.CouponMapper;
import com.example.demo.domain.mapper.OrderMapper;
import com.example.demo.domain.mapper.ReviewMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrincipalDetails implements UserDetails{
	private UserDto user;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList();
		
		collection.add(new GrantedAuthority(){
			@Override
			public String getAuthority() {
				return user.getRole();
			}
	
		} );
		
		return collection;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return user.getU_email();
		}

		public String getUserNickname(){
		return user.getNickname();
		}


	public String getUserPhoneNumber(){
		return user.getPhone();
	}

	public String getUserAddress(){
		return user.getAddr();

	}

	public String getGrade(){
		return user.getUser_grade();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}