package com.hizliyol.core.data;

import org.springframework.data.repository.CrudRepository;

import com.hizliyol.core.entity.EnergyConsumption;

public interface EnergyConsumptionDataDao extends CrudRepository<EnergyConsumption, Integer> {

}
