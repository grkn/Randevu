package com.hizliyol.core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hizliyol.core.entity.Role;
import com.hizliyol.core.entity.UserManagement;
import com.hizliyol.core.lazymodel.UserLazyModel;
import com.hizliyol.core.service.UserService;

@Component
@Scope("view")
public class AdminCreateUserController extends BaseController {

	private UserManagement user = new UserManagement();

	private DualListModel<Role> roleDualList;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;

	private UserLazyModel userLazyModel;

	private UserManagement selectedUser;

	@PostConstruct
	public void init(){
		roleDualList = new DualListModel<>(userService.getRoles(), new ArrayList<>());
		userLazyModel = new UserLazyModel(userService);
	}
	
	
	public UserManagement getUser() {
		return user;
	}

	public void setUser(UserManagement user) {
		this.user = user;
	}

	public DualListModel<Role> getRoleDualList() {
		return roleDualList;
	}


	public void setRoleDualList(DualListModel<Role> roleDualList) {
		this.roleDualList = roleDualList;
	}
	
	public void insert() throws ClassNotFoundException, IOException{
		List<Role> role = roleDualList.getTarget();
		user.setPassword(encoder.encode(user.getPassword()));
		UserManagement userManagement = userService.getUserByUserName(user.getUsername());
		if(userManagement == null){
			role.forEach(rl -> user.getRoleSet().add(new Role(rl.getId(),rl.getRoleName())));
			userService.insert(user);
		}else{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(javax.faces.application.FacesMessage.SEVERITY_INFO,"",getMessage("user.already.defined")));
		}
		user = new UserManagement();
	}

	public void delete(UserManagement user){
		userService.delete(user);
	}


	public UserLazyModel getUserLazyModel() {
		return userLazyModel;
	}


	public void setUserLazyModel(UserLazyModel userLazyModel) {
		this.userLazyModel = userLazyModel;
	}


	public UserManagement getSelectedUser() {
		return selectedUser;
	}


	public void setSelectedUser(UserManagement selectedUser) {
		this.selectedUser = selectedUser;
	}

}
