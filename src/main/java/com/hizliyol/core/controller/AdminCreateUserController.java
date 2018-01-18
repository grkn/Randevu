package com.hizliyol.core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.hizliyol.core.lazymodel.RandevuUserLazyModel;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hizliyol.core.entity.RandevuUser;
import com.hizliyol.core.entity.Role;
import com.hizliyol.core.service.UserService;
import com.hizliyol.core.util.DeepClone;

@Component
@Scope("view")
public class AdminCreateUserController extends BaseController {

	private RandevuUser user = new RandevuUser();

	private DualListModel<Role> roleDualList;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;

	private RandevuUserLazyModel randevuUserLazyModel;

	private RandevuUser selectedRandevuUser;

	@PostConstruct
	public void init(){
		roleDualList = new DualListModel<>(userService.getRoles(), new ArrayList<>());
		randevuUserLazyModel = new RandevuUserLazyModel(userService);
	}
	
	
	public RandevuUser getUser() {
		return user;
	}

	public void setUser(RandevuUser user) {
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
		RandevuUser randevuUser = userService.getUserByUserName(user.getUsername());
		if(randevuUser == null){
			role.forEach(rl -> user.getRoleSet().add(new Role(rl.getId(),rl.getRoleName())));
			userService.insert(user);
		}else{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(javax.faces.application.FacesMessage.SEVERITY_INFO,"",getMessage("user.already.defined")));
		}
		user = new RandevuUser();
	}

	public void delete(RandevuUser user){
		userService.delete(user);
	}

	public RandevuUserLazyModel getRandevuUserLazyModel() {
		return randevuUserLazyModel;
	}

	public void setRandevuUserLazyModel(RandevuUserLazyModel randevuUserLazyModel) {
		this.randevuUserLazyModel = randevuUserLazyModel;
	}

	public RandevuUser getSelectedRandevuUser() {
		return selectedRandevuUser;
	}

	public void setSelectedRandevuUser(RandevuUser selectedRandevuUser) {
		this.selectedRandevuUser = selectedRandevuUser;
	}
}
