package com.hizliyol.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.hizliyol.core.entity.Auhorization;
import com.hizliyol.core.entity.UserManagement;
import com.hizliyol.core.entity.Role;

@Repository
public class UserRoleDao extends AbstractJdbcDao {

	public void saveUserRole(UserManagement user) {
		MapSqlParameterSource[] arr = new MapSqlParameterSource[user.getRoleSet().size()];
		int counter = 0;
		for (Role role : user.getRoleSet()) {
			MapSqlParameterSource parameterSource = new MapSqlParameterSource();
			parameterSource.addValue("userId", user.getId());
			parameterSource.addValue("roleId", role.getId());
			arr[counter] = parameterSource;
			counter++;
		}
		getNamedParameterJdbcTemplate()
				.batchUpdate("insert into user_jt_role (user_id,role_id) values(:userId,:roleId)", arr);
	}

	public List<Auhorization> getByRole(List<Integer> roleId) {
		String sql = "select distinct auth.id,auth.auth_name from role rl left outer join auhorization auth on rl.id = auth.role_id where rl.id in (:role_id)";
		return getNamedParameterJdbcTemplate().query(sql, new MapSqlParameterSource().addValue("role_id", roleId),
				new RowMapper<Auhorization>() {
					@Override
					public Auhorization mapRow(ResultSet rs, int arg1) throws SQLException {
						Auhorization auth = new Auhorization();
						auth.setId(rs.getInt("id"));
						auth.setAuthName(rs.getString("auth_name"));
						return auth;
					}
				});
	}

	public List<Role> getRoleByNames(String userId, Role... roles) {
		String sql = "select * from role left outer join user_jt_role ujr on role.id = ujr.role_id where role_name in (:roleNames) and ujr.user_id = :userId";
		List<String> roleNames = new ArrayList<>();
		for (Role role : roles) {
			roleNames.add(role.getRoleName());
		}
		return getNamedParameterJdbcTemplate().query(sql, new MapSqlParameterSource().addValue("roleNames", roleNames)
				.addValue("userId", Integer.parseInt(userId)),
				new RowMapper<Role>() {
					@Override
					public Role mapRow(ResultSet rs, int arg1) throws SQLException {
						Role role = new Role();
						role.setId(rs.getInt("id"));
						role.setRoleName(rs.getString("role_name"));
						return role;
					}
				});
	}
	
	public void deleteRoleByRoleIdList(List<Role> deleteList, Integer id) {
		List<Integer> roleIdList = new ArrayList<>();
		for (Role role : deleteList) {
			roleIdList.add(role.getId());
		}
		String sql = "delete from user_jt_role where role_id in (:idList) and user_id = :userId";
		getNamedParameterJdbcTemplate().update(sql, new MapSqlParameterSource().addValue("idList", roleIdList).addValue("userId", id));
	}

}
