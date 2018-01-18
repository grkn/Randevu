package com.hizliyol.core.controller;

import com.hizliyol.core.entity.Auhorization;
import com.hizliyol.core.entity.Role;
import com.hizliyol.core.lazymodel.RoleLazyModel;
import com.hizliyol.core.service.RoleService;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by bilge_gilleez on 17.01.2018.
 */
@Component
@Scope("view")
public class RoleController extends BaseController{

    private Role role = new Role();

    private DualListModel<Auhorization> auhorizationDualListModel;

    private RoleLazyModel roleLazyModel;


    private Role selectedRole = new Role();


    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void init(){
        auhorizationDualListModel = new DualListModel<>(roleService.getAuthorizationList(),new ArrayList<>());
        roleLazyModel = new RoleLazyModel(roleService);
    }

    public void insert(){
        role.setAuhorizationSet(new HashSet<>(auhorizationDualListModel.getTarget()));
        roleService.insert(role);
        role = new Role();
    }

    public void delete(Role role){
        roleService.delete(role);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public DualListModel<Auhorization> getAuhorizationDualListModel() {
        return auhorizationDualListModel;
    }

    public void setAuhorizationDualListModel(DualListModel<Auhorization> auhorizationDualListModel) {
        this.auhorizationDualListModel = auhorizationDualListModel;
    }

    public RoleLazyModel getRoleLazyModel() {
        return roleLazyModel;
    }

    public void setRoleLazyModel(RoleLazyModel roleLazyModel) {
        this.roleLazyModel = roleLazyModel;
    }

    public Role getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Role selectedRole) {
        this.selectedRole = selectedRole;
    }
}
