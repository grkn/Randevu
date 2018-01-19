package com.hizliyol.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hizliyol.core.data.SchoolResponsibleDataDao;
import com.hizliyol.core.entity.SchoolResponsible;

@Service
public class SchoolResponsibleService extends BaseService<SchoolResponsible,SchoolResponsibleDataDao>{

	
	public SchoolResponsibleService(@Autowired SchoolResponsibleDataDao schoolResponsibleDataDao) {
		super(schoolResponsibleDataDao);
	}
	
}
