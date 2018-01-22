package com.hizliyol.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hizliyol.core.data.ConstantItemDataDao;
import com.hizliyol.core.entity.ConstantItem;

@Service
public class ConstantItemService extends BaseService<ConstantItem, ConstantItemDataDao>{

	ConstantItemDataDao dataDao;
	
	@Autowired
	public ConstantItemService(ConstantItemDataDao obj) {
		super(obj);
		this.dataDao = obj;
	}

	public List<ConstantItem> findByType(String type) {
		return dataDao.findByType(type);
	}

}
