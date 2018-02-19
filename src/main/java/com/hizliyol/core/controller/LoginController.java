package com.hizliyol.core.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hizliyol.core.session.SessionBean;

import ch.qos.logback.classic.Logger;


@Component
@Scope("view")
public class LoginController extends  BaseController {

	private Logger logger = (Logger) LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private SessionBean sessionBean;
	
	@PostConstruct
	public void init(){
		logger.info("LoginController initialized");
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public void logout() throws IOException {
		SecurityContextHolder.clearContext();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/ChatbotGateWay/login.xhtml?logout");
	}
	
}
