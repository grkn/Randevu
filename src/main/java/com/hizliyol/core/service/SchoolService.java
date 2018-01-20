package com.hizliyol.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hizliyol.core.data.SchoolDataDao;
import com.hizliyol.core.entity.School;

@Service
@org.springframework.transaction.annotation.Transactional
public class SchoolService {

	@Autowired
	private SchoolDataDao schoolDataDao;
	
	public void insert(School school){
		schoolDataDao.save(school);
	}
	
	public List<School> findAll(){
		List<School> list = new ArrayList<>();
		schoolDataDao.findAll().forEach(school -> list.add(school));
		return list;
	}
	
	public void delete(School school){
		schoolDataDao.delete(school);
	}
	
	public School findById(Integer id){
		return schoolDataDao.findById(id).get();
	}
	
}
