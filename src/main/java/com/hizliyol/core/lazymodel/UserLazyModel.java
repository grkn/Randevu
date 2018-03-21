package com.hizliyol.core.lazymodel;

import com.hizliyol.core.entity.UserManagement;
import com.hizliyol.core.service.UserService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bilge_gilleez on 17.01.2018.
 */
public class UserLazyModel extends LazyDataModel<UserManagement> {

    private UserService userService;

    private List<UserManagement> result;

    public UserLazyModel(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserManagement getRowData(String rowKey) {
        for(UserManagement userManagement : result) {
            if(userManagement.getId().equals(rowKey))
                return userManagement;
        }

        return null;
    }
    
    @Override
    public Object getRowKey(UserManagement userManagement) {
        return userManagement.getId();
    }

    @Override
    public List<UserManagement> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        Long count = userService.getUserLazilyCount(first,pageSize,sortField,sortOrder,filters);
        if(count == null || count == 0L)
            return new ArrayList<>();

        result = userService.getUserLazily(first,pageSize,sortField,sortOrder,filters);

        setRowCount(count.intValue());
        setWrappedData(result);

        return result;
    }
}
