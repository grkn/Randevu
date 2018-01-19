package com.hizliyol.core.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hizliyol.core.domain.UserDetailDto;
import com.hizliyol.core.session.SessionBean;

@Component
@Scope("view")
public class HomePageController {

	@Autowired
	private SessionBean sessionBean;
	
	private UserDetailDto user;
	
	@PostConstruct
	public void init(){
		setUser(sessionBean.getUserDetailDto());
	}

	public UserDetailDto getUser() {
		return user;
	}

	public void setUser(UserDetailDto user) {
		this.user = user;
	}
	
}
