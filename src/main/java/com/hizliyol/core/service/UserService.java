package com.hizliyol.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hizliyol.core.dao.UserDao;
import com.hizliyol.core.dao.UserRoleDao;
import com.hizliyol.core.data.UserDataDao;
import com.hizliyol.core.entity.Auhorization;
import com.hizliyol.core.entity.UserManagement;
import com.hizliyol.core.entity.Role;

@Service
@Transactional("transactionManager")
public class UserService extends BaseService<UserManagement, UserDataDao> {

	private UserDataDao userDataDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	JdbcClientDetailsService clientDetails;

	public UserService(@Autowired UserDataDao userDataDao) {
		super(userDataDao);
		this.userDataDao = userDataDao;
	}

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	public UserManagement getUserByUserName(String userName) {
		return userDao.getUser(userName);
	}

	public List<Role> getRoles() {
		return userDao.getRoles();
	}

	public void insert(UserManagement user) {
		final UserManagement resultUser = userDataDao.save(user);
		if (!CollectionUtils.isEmpty(user.getRoleSet()))
			userRoleDao.saveUserRole(resultUser);

		ClientDetails clientDetailsEntity = new ClientDetails() {
			@Override
			public boolean isSecretRequired() {
				return true;
			}

			@Override
			public boolean isScoped() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isAutoApprove(String scope) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public Set<String> getScope() {
				Set<String> scope = new HashSet<>();
				scope.add("read");
				scope.add("write");
				return scope;
			}

			@Override
			public Set<String> getResourceIds() {
				// TODO Auto-generated method stub
				return new HashSet<>();
			}

			@Override
			public Set<String> getRegisteredRedirectUri() {
				// TODO Auto-generated method stub
				return new HashSet<>();
			}

			@Override
			public Integer getRefreshTokenValiditySeconds() {
				// TODO Auto-generated method stub
				return 315360000;
			}

			@Override
			public String getClientSecret() {
				// TODO Auto-generated method stub
				return resultUser.getPassword();
			}

			@Override
			public String getClientId() {
				// TODO Auto-generated method stub
				return resultUser.getUsername();
			}

			@Override
			public Set<String> getAuthorizedGrantTypes() {
				Set<String> grantedTypes = new HashSet<>();
				grantedTypes.add("password");
				grantedTypes.add("authorization_code");
				grantedTypes.add("refresh_token");
				return grantedTypes;
			}

			@Override
			public Collection<GrantedAuthority> getAuthorities() {
				Set<GrantedAuthority> set = new HashSet<>();
				List<Integer> list = new ArrayList<>();
				for (Role rl : resultUser.getRoleSet()) {
					list.add(rl.getId());
				}

				for (Auhorization auth : userRoleDao.getByRole(list)) {
					set.add(new SimpleGrantedAuthority(auth.getAuthName()));
				}

				return set;
			}

			@Override
			public Map<String, Object> getAdditionalInformation() {
				// TODO Auto-generated method stub
				return new LinkedHashMap<>();
			}

			@Override
			public Integer getAccessTokenValiditySeconds() {
				// TODO Auto-generated method stub
				return 315360000;
			}
		};
		clientDetails.addClientDetails(clientDetailsEntity);
	}

	public List<UserManagement> getUserLazily(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		return userDao.getUserLazily(first, pageSize, sortField, sortOrder, filters);
	}

	public Long getUserLazilyCount(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		return userDao.getUserLazilyCount(first, pageSize, sortField, sortOrder, filters);
	}

	public void delete(UserManagement user) {
		userDataDao.delete(user);
	}

	public void change(UserManagement userManagement, List<Role> roleList, List<Role> deletedList) {
		userManagement.setPassword(passwordEncoder.encode(userManagement.getPassword()));
		final UserManagement resultUser = userDataDao.save(userManagement);
		if (!CollectionUtils.isEmpty(roleList)) {
			resultUser.setRoleSet(new HashSet<>(roleList));
			userRoleDao.saveUserRole(resultUser);
		}
		if(!CollectionUtils.isEmpty(deletedList)) {
			userRoleDao.deleteRoleByRoleIdList(deletedList, userManagement.getId());
		}
		
		clientDetails.updateClientSecret(userManagement.getUsername(), userManagement.getPassword());

	}

	public List<Role> getUserRoleList(String userId, Role... roles) {
		return this.userRoleDao.getRoleByNames(userId, roles);
	}

}
