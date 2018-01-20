package com.hizliyol.core.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hizliyol.core.entity.ConstantItem;

public interface ConstantItemDataDao extends CrudRepository<ConstantItem, Integer>{

	List<ConstantItem> findByType(@Param("type") String type);

}
