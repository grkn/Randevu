package com.hizliyol.core.data;

import org.springframework.data.repository.CrudRepository;

import com.hizliyol.core.entity.School;

public interface SchoolDataDao extends CrudRepository<School, Integer> {

}
