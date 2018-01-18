package com.hizliyol.core.lazymodel;

import com.hizliyol.core.entity.Role;
import com.hizliyol.core.service.RoleService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bilge_gilleez on 17.01.2018.
 */
public class RoleLazyModel extends LazyDataModel<Role> {

    private RoleService roleService;

    private List<Role> result;

    public RoleLazyModel(RoleService roleService){
        this.roleService = roleService;
    }

    @Override
    public Role getRowData(String rowKey) {
        for(Role randevuUser : result) {
            if(randevuUser.getId().equals(rowKey))
                return randevuUser;
        }

        return null;
    }

    @Override
    public Object getRowKey(Role role) {
        return role.getId();
    }

    @Override
    public List<Role> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        Long count = roleService.getLazilyCount(first,pageSize,sortField,sortOrder,filters);

        if(count == null || count == 0L)
            return new ArrayList<>();

        result = roleService.getLazily(first,pageSize,sortField,sortOrder,filters);

        setRowCount(count.intValue());
        setWrappedData(result);

        return result;
    }
}
