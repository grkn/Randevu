package com.hizliyol.core.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.hizliyol.core.domain.UserDetailDto;
import com.hizliyol.core.entity.Auhorization;
import com.hizliyol.core.entity.RandevuUser;
import com.hizliyol.core.entity.Role;
import com.hizliyol.core.service.SchoolResponsibleService;
import com.hizliyol.core.service.UserService;

@Component
public class CustomAuthentication implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Autowired
	private SchoolResponsibleService schoolResponsibleService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RandevuUser user = userService.getUserByUserName(username);
		
		if(null == user)
			throw new UsernameNotFoundException("Kullanýcý bulunamadý");
		
		List<GrantedAuthority> list = new ArrayList<>();
		Set<Role> roleSet = user.getRoleSet();
		for (Role role : roleSet) {
			Set<Auhorization> authSet = role.getAuhorizationSet();
			for (Auhorization auhorization : authSet) {
				list.add(new SimpleGrantedAuthority(auhorization.getAuthName()));
			}
		}
		
		List<GrantedAuthority> filtered = list.stream().filter(ga -> ga.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());
		UserDetailDto userDetail;
		if(!CollectionUtils.isEmpty(filtered)){
			userDetail = new UserDetailDto(new User(user.getUsername(),user.getPassword(),list),user.getFirstName(),user.getLastName(),user.getEmail(),new HashSet<>(schoolResponsibleService.findAll()));
		}else{
			userDetail = new UserDetailDto(new User(user.getUsername(),user.getPassword(),list),user.getFirstName(),user.getLastName(),user.getEmail(),user.getSchoolResponsibleSet());
		}
		return userDetail;		
	}

}
