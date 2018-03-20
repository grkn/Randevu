package com.hizliyol.core.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hizliyol.core.domain.UserDetailDto;

import org.springframework.security.core.userdetails.User;

@Component
@Scope(scopeName = "session", value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionBean {
	
	private List<GrantedAuthority> list;
	private String userName;
	private UserDetailDto userDetailDto;
	private Locale currentLocale =new Locale("tr");
	
    public String switchLocale(String lang) {
        currentLocale = new Locale(lang);
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() +
            "?faces-redirect=true";
    }
	
	@PostConstruct
	public void init(){
	}

	public boolean isAdmin(){
		list = new ArrayList<>(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return list.stream().anyMatch(f -> f.getAuthority().equals("ROLE_ADMIN"));
	}

	public boolean isRoot(){
		list = new ArrayList<>(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return list.stream().anyMatch(f -> f.getAuthority().equals("ROLE_ROOT"));
	}

	public String getUserName() {
		if(SecurityContextHolder.getContext() != null  && SecurityContextHolder.getContext().getAuthentication() != null
		&& SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null){
			if("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())){
				return "NOTLOGINUSER";
			}
			User obj = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return obj.getUsername();
		}
		return null;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserDetailDto getUserDetailDto() {
		User obj = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return (UserDetailDto) obj;
	}

	public void setUserDetailDto(UserDetailDto userDetailDto) {
		this.userDetailDto = userDetailDto;
	}

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}
	
}
