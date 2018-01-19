package com.hizliyol.core.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hizliyol.core.entity.School;
import com.hizliyol.core.service.SchoolService;

@Component
@Scope("view")
public class SchoolController extends BaseController{

	private School school = new School();
	
	private List<School> schoolList;
	
	@Autowired
	private SchoolService schoolService;
	
	
	
	@PostConstruct
	public void init(){
		schoolList =schoolService.findAll();
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
	public void insert(){
		schoolList.remove(school);
		schoolService.insert(school);
		
		schoolList.add(school);
		school = new School();
	}
	
	public void delete(School item){
		schoolService.delete(item);
		schoolList.remove(item);
	}
	
	public void update(Integer id){
		school = schoolService.findById(id);
	}

	public List<School> getSchoolList() {
		return schoolList;
	}

	public void setSchoolList(List<School> schoolList) {
		this.schoolList = schoolList;
	}
}
