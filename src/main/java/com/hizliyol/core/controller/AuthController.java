package com.hizliyol.core.controller;

import com.hizliyol.core.entity.Auhorization;
import com.hizliyol.core.entity.Role;
import com.hizliyol.core.service.AuthService;
import com.hizliyol.core.service.RoleService;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bilge_gilleez on 18.01.2018.
 */
@Component
@Scope("view")
public class AuthController extends BaseController{

    private Auhorization authorization = new Auhorization();

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    private List<Auhorization> authList;

    private List<Role> roleList;

    private Role selectedRole;

    @PostConstruct
    public void init(){
        roleList = roleService.getRoleList();
        authList = authService.findAll();
    }

    public void insert(){
        authorization.setRoleId(selectedRole);
        authService.insert(authorization);
        showInfoMessage("user.inserted.successfully");
        authList.add(authorization);
        authorization = new Auhorization();
    }

    public void delete(Auhorization auth){
        authService.delete(auth);
        authList.remove(auth);
    }

    public Auhorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Auhorization authorization) {
        this.authorization = authorization;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Role getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Role selectedRole) {
        this.selectedRole = selectedRole;
    }

    public List<Auhorization> getAuthList() {
        return authList;
    }

    public void setAuthList(List<Auhorization> authList) {
        this.authList = authList;
    }
}
