package com.hizliyol.core.data;

import com.hizliyol.core.entity.Auhorization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bilge_gilleez on 17.01.2018.
 */
public interface AuthorizationDataDao extends CrudRepository<Auhorization,Integer> {

    public List<Auhorization> findByAuthName(@Param("authName") String authName);

}
