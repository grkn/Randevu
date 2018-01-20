package com.hizliyol.core.lazymodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.hizliyol.core.entity.EnergyConsumption;
import com.hizliyol.core.service.EnergyConsumptionService;

public class EnergyConsumptionLazyModel  extends LazyDataModel<EnergyConsumption>{
	
	private List<EnergyConsumption> result;
	
	private EnergyConsumptionService service;
	
	public EnergyConsumptionLazyModel(EnergyConsumptionService service) {
		this.service = service;
	}
	
	@Override
    public EnergyConsumption getRowData(String rowKey) {
        for(EnergyConsumption randevuUser : result) {
            if(randevuUser.getId().equals(rowKey))
                return randevuUser;
        }

        return null;
    }

    @Override
    public Object getRowKey(EnergyConsumption randevuUser) {
        return randevuUser.getId();
    }

    @Override
    public List<EnergyConsumption> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        Long count = service.getRandevuUserLazilyCount(first,pageSize,sortField,sortOrder,filters);
        if(count == null || count == 0L)
            return new ArrayList<>();

        result = service.getRandevuUserLazily(first,pageSize,sortField,sortOrder,filters);

        setRowCount(count.intValue());
        setWrappedData(result);

        return result;
    }
    
}
