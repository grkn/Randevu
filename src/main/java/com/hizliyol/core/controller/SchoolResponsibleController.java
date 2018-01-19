package com.hizliyol.core.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hizliyol.core.entity.RandevuUser;
import com.hizliyol.core.entity.School;
import com.hizliyol.core.entity.SchoolResponsible;
import com.hizliyol.core.service.SchoolResponsibleService;
import com.hizliyol.core.service.SchoolService;
import com.hizliyol.core.service.UserService;

@Component
@Scope("view")
public class SchoolResponsibleController extends BaseController{

	@Autowired
	private SchoolResponsibleService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private SchoolService schoolService;
	
	private SchoolResponsible schoolResponsible = new SchoolResponsible();
	private List<SchoolResponsible> schoolResponsibleList;
	private List<RandevuUser> randevuUserList;
	private RandevuUser selectedRandevuUser;
	
	private School selectedSchool;
	private List<School> schoolList;
	
	@PostConstruct
	public void init(){
		schoolList = schoolService.findAll();
		schoolResponsibleList = service.findAll();
		randevuUserList = userService.findAll();
	}
	
	public void save(){
		schoolResponsibleList.remove(schoolResponsible);
		
		schoolResponsible.setSchoolId(selectedSchool);
		schoolResponsible.setUserId(selectedRandevuUser);
		service.save(schoolResponsible);
		schoolResponsibleList.add(schoolResponsible);
		schoolResponsible = new SchoolResponsible();
	}
	
	public void delete(SchoolResponsible item){
		service.delete(item.getId());
		schoolResponsibleList.remove(item);
	}

	public void update(Integer id){
		schoolResponsible = service.findById(id);
	}
	
	public List<School> getSchoolList() {
		return schoolList;
	}

	public void setSchoolList(List<School> schoolList) {
		this.schoolList = schoolList;
	}

	public School getSelectedSchool() {
		return selectedSchool;
	}

	public void setSelectedSchool(School selectedSchool) {
		this.selectedSchool = selectedSchool;
	}

	public SchoolResponsible getSchoolResponsible() {
		return schoolResponsible;
	}

	public void setSchoolResponsible(SchoolResponsible schoolResponsible) {
		this.schoolResponsible = schoolResponsible;
	}

	public List<SchoolResponsible> getSchoolResponsibleList() {
		return schoolResponsibleList;
	}

	public void setSchoolResponsibleList(List<SchoolResponsible> schoolResponsibleList) {
		this.schoolResponsibleList = schoolResponsibleList;
	}

	public List<RandevuUser> getRandevuUserList() {
		return randevuUserList;
	}

	public void setRandevuUserList(List<RandevuUser> randevuUserList) {
		this.randevuUserList = randevuUserList;
	}

	public RandevuUser getSelectedRandevuUser() {
		return selectedRandevuUser;
	}

	public void setSelectedRandevuUser(RandevuUser selectedRandevuUser) {
		this.selectedRandevuUser = selectedRandevuUser;
	}
	
}
