package com.hizliyol.core.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hizliyol.core.dao.AdminJPADao;
import com.hizliyol.core.entity.UserDefinition;

@Service
@Transactional("transactionManager")
public class AdminUserDefinitionService {

	@Autowired
	private AdminJPADao adminJPADao;
	
	public List<UserDefinition> query(String userName){
		return adminJPADao.queryUserDefinitions(userName);
	}
	
	public UserDefinition save(UserDefinition model){
		return adminJPADao.save(model);
	}

	public void delete(UserDefinition model) {
		adminJPADao.delete(model);
	}
	
	public UserDefinition queryById(Integer id){
		return adminJPADao.queryById(id);
	}
}
