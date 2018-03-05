package com.hizliyol.core.service;

import com.hizliyol.core.dao.AuthorizationDao;
import com.hizliyol.core.dao.RoleDao;
import com.hizliyol.core.data.AuthorizationDataDao;
import com.hizliyol.core.data.RoleDataDao;
import com.hizliyol.core.entity.Auhorization;
import com.hizliyol.core.entity.Role;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional("transactionManager")
public class RoleService {

    @Autowired
    private RoleDataDao roleDataDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private AuthorizationDataDao authorizationDataDao;

    public List<Role> getRoleList(){
        return roleDao.queryRoles();
    }

    public List<Auhorization> getAuthorizationList(){
        Set<Auhorization> hashSet = new HashSet<>();
        authorizationDataDao.findAll().forEach(auhorization -> hashSet.add(auhorization));
        return new ArrayList<>(hashSet);
    }

    public void insert(Role role){
        roleDataDao.save(role);
        if(!CollectionUtils.isEmpty(role.getAuhorizationSet())){
            authorizationDao.insert(role);
        }
    }

    public void delete(Role selectedRole) {
        roleDataDao.delete(selectedRole);
    }

    public Long getLazilyCount(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return roleDao.getLazilyCount(first, pageSize,sortField, sortOrder,filters);
    }

    public List<Role> getLazily(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return roleDao.getLazily(first,pageSize,sortField,sortOrder,filters);
    }
    
}
