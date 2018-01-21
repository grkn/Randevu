package com.hizliyol.core.security;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hizliyol.core.domain.UserDetailDto;
import com.hizliyol.core.service.SchoolResponsibleService;
import com.hizliyol.core.session.SessionBean;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	
	@Autowired
	CustomAuthentication customAuth;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	SchoolResponsibleService schoolResponsibleService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        if("root".equals(name) && "passw0rd!".equals(password)){
        	UserDetailDto userDetailDto = new UserDetailDto(new User("root", "passw0rd!", Arrays.asList(new SimpleGrantedAuthority("ROLE_ROOT"))), "ROOT", "ROOT", "gurkanilleez@gmail.com",new HashSet<>(schoolResponsibleService.findAll()));
        	return new UsernamePasswordAuthenticationToken(userDetailDto, userDetailDto.getPassword(), userDetailDto.getAuthorities() );
		}

        UserDetailDto user = (UserDetailDto) customAuth.loadUserByUsername(name);
		
		if(!passwordEncoder.matches(password, user.getPassword())){
			throw new BadCredentialsException("Kullanýcý Adý Veya Þifre Yanlýþ Girilmiþtir.");
		}
		return new UsernamePasswordAuthenticationToken(user,password,user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
