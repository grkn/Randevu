package com.hizliyol.core.data;

import org.springframework.data.repository.CrudRepository;

import com.hizliyol.core.entity.UserManagement;


public interface UserDataDao extends CrudRepository<UserManagement, Integer>{

}
