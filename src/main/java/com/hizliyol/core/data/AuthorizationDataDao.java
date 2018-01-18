package com.hizliyol.core.data;

import com.hizliyol.core.entity.Auhorization;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bilge_gilleez on 17.01.2018.
 */
public interface AuthorizationDataDao extends CrudRepository<Auhorization,Integer> {

}
