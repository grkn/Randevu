package com.hizliyol.core.service;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hizliyol.core.dao.EnergyConsumptionDao;
import com.hizliyol.core.data.EnergyConsumptionDataDao;
import com.hizliyol.core.entity.EnergyConsumption;

@Service
@org.springframework.transaction.annotation.Transactional
public class EnergyConsumptionService extends BaseService<EnergyConsumption,EnergyConsumptionDataDao>{

	@Autowired
	EnergyConsumptionDao energyConsumptionDao;
	
	@Autowired
	public EnergyConsumptionService(EnergyConsumptionDataDao obj) {
		super(obj);
	}

	public Long getRandevuUserLazilyCount(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		return energyConsumptionDao.getRandevuUserLazilyCount( first,  pageSize,  sortField,  sortOrder,
				filters);
	}

	public List<EnergyConsumption> getRandevuUserLazily(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		return energyConsumptionDao.getRandevuUserLazily( first,  pageSize,  sortField,  sortOrder,
				filters);
	}

}
