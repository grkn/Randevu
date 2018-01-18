package com.hizliyol.core.service;

import java.util.List;
import java.util.Map;


import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hizliyol.core.dao.UserDao;
import com.hizliyol.core.dao.UserRoleDao;
import com.hizliyol.core.data.UserDataDao;
import com.hizliyol.core.entity.RandevuUser;
import com.hizliyol.core.entity.Role;

@Service
@Transactional("transactionManager")
public class UserService{
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserDataDao userDataDao;

	@Autowired
	private UserRoleDao userRoleDao;
	
	public RandevuUser getUserByUserName(String userName){
		return userDao.getUser(userName);
	}
	
	public List<Role> getRoles(){
		return userDao.getRoles();
	}
	
	public void insert(RandevuUser user){
		userDataDao.save(user);
		if(!CollectionUtils.isEmpty(user.getRoleSet()))
		userRoleDao.saveUserRole(user);
	}

	public List<RandevuUser> getRandevuUserLazily(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		return userDao.getRandevuUserLazily(first, pageSize, sortField, sortOrder, filters);
	}

	public Long getRandevuUserLazilyCount(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		return userDao.getRandevuUserLazilyCount(first, pageSize, sortField, sortOrder, filters);
	}

	public void delete(RandevuUser user) {
		userDataDao.delete(user);
	}
}
