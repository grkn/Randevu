package com.hizliyol.core.data;

import org.springframework.data.repository.CrudRepository;

import com.hizliyol.core.entity.RandevuUser;


public interface UserDataDao extends CrudRepository<RandevuUser, Integer>{

}
