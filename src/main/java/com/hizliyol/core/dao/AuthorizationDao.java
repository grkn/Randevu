package com.hizliyol.core.dao;

import com.hizliyol.core.entity.Auhorization;
import com.hizliyol.core.entity.Role;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * Created by bilge_gilleez on 17.01.2018.
 */
@Repository
public class AuthorizationDao extends AbstractJdbcDao{

    public void insert(Role role){
        MapSqlParameterSource []arr = new MapSqlParameterSource[role.getAuhorizationSet().size()];

        int count = 0;
        for (Auhorization auth : role.getAuhorizationSet()) {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("authName",auth.getAuthName());
            parameterSource.addValue("roleId",role.getId());
            arr[count] = parameterSource;
            count++;
        }


        getNamedParameterJdbcTemplate().batchUpdate("insert into auhorization (auth_name,role_id) values(:authName,:roleId)",arr);
    }

}
