package com.hizliyol.core.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.hizliyol.core.entity.RandevuUser;
import com.hizliyol.core.entity.Role;

@Repository
public class UserRoleDao extends AbstractJdbcDao{

	public void saveUserRole(RandevuUser user){
		MapSqlParameterSource[] arr = new MapSqlParameterSource[user.getRoleSet().size()];
		int counter = 0;
		for (Role role : user.getRoleSet()) {
			MapSqlParameterSource parameterSource = new MapSqlParameterSource();
			parameterSource.addValue("userId", user.getId());
			parameterSource.addValue("roleId", role.getId());
			arr[counter] = parameterSource;
			counter++;
		}
		getNamedParameterJdbcTemplate().batchUpdate("insert into user_jt_role (user_id,role_id) values(:userId,:roleId)", arr);
	}
	
}
