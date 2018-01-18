package com.hizliyol.core.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hizliyol.core.domain.UserDefinitionDto;
import com.hizliyol.core.entity.UserDefinition;
import com.hizliyol.core.service.AdminUserDefinitionService;
import com.hizliyol.core.session.SessionBean;

@Component
@Scope("view")
public class AdminUserDefinitionController  extends  BaseController{

	@Autowired
	private SessionBean sessionBean;
	
	@Autowired
	private AdminUserDefinitionService userDefinitionService;
	
	private List<UserDefinition> userDefinitionList;

	private UserDefinitionDto userDefinitionDto = new UserDefinitionDto();
	
	@PostConstruct
	public void init(){
		userDefinitionList = userDefinitionService.query(sessionBean.getUserName());
	}
	
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public UserDefinitionDto getUserDefinitionDto() {
		return userDefinitionDto;
	}

	public void setUserDefinitionDto(UserDefinitionDto userDefinitionDto) {
		this.userDefinitionDto = userDefinitionDto;
	}

	public void insert(){
		UserDefinition userDefinition = convertUserDefDtoToEntity(userDefinitionDto);
		userDefinition = userDefinitionService.save(userDefinition);
		if(userDefinitionList.contains(userDefinition)){
			userDefinitionList.remove(userDefinition);
			userDefinitionList.add(0,userDefinition);
		}else
		userDefinitionList.add(userDefinition);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(javax.faces.application.FacesMessage.SEVERITY_INFO, "",getMessage("user.inserted.successfully")));
	}

	private UserDefinition convertUserDefDtoToEntity(UserDefinitionDto dto) {
		UserDefinition model = new UserDefinition();
		model.setTimeInterval(dto.getInterval());
		model.setName(dto.getName());
		model.setResponsibility(dto.getResponsibility());
		model.setUserName(sessionBean.getUserName());
		model.setFreeDay(dto.getFreeDay());
		model.setFreeDayTwo(dto.getFreeDayTwo());
		model.setId(dto.getId());
		return model;
	}

	public void delete(UserDefinition model){
		userDefinitionService.delete(model);
		userDefinitionList.remove(model);
	}
	
	public void edit(UserDefinition model){
		UserDefinition userDefinition = userDefinitionService.queryById(model.getId());
		userDefinitionDto = userDefCovertEntityToDto(userDefinition);
		
	}
	
	private UserDefinitionDto userDefCovertEntityToDto(UserDefinition userDefinition) {
		UserDefinitionDto model = new UserDefinitionDto();
		model.setFreeDay(userDefinition.getFreeDay());
		model.setFreeDayTwo(userDefinition.getFreeDayTwo());
		model.setInterval(userDefinition.getTimeInterval());
		model.setName(userDefinition.getName());
		model.setResponsibility(userDefinition.getResponsibility());
		model.setId(userDefinition.getId());
		return model;
	}
	
	public void clear(){
		userDefinitionDto = new UserDefinitionDto();
	}

	public List<UserDefinition> getUserDefinitionList() {
		return userDefinitionList;
	}

	public void setUserDefinitionList(List<UserDefinition> userDefinitionList) {
		this.userDefinitionList = userDefinitionList;
	}

}
