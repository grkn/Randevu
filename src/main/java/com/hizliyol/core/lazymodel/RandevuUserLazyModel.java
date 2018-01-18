package com.hizliyol.core.lazymodel;

import com.hizliyol.core.entity.RandevuUser;
import com.hizliyol.core.service.UserService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bilge_gilleez on 17.01.2018.
 */
public class RandevuUserLazyModel extends LazyDataModel<RandevuUser> {

    private UserService userService;

    private List<RandevuUser> result;

    public RandevuUserLazyModel(UserService userService){
        this.userService = userService;
    }

    @Override
    public RandevuUser getRowData(String rowKey) {
        for(RandevuUser randevuUser : result) {
            if(randevuUser.getId().equals(rowKey))
                return randevuUser;
        }

        return null;
    }

    @Override
    public Object getRowKey(RandevuUser randevuUser) {
        return randevuUser.getId();
    }

    @Override
    public List<RandevuUser> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        Long count = userService.getRandevuUserLazilyCount(first,pageSize,sortField,sortOrder,filters);
        if(count == null || count == 0L)
            return new ArrayList<>();

        result = userService.getRandevuUserLazily(first,pageSize,sortField,sortOrder,filters);

        setRowCount(count.intValue());
        setWrappedData(result);

        return result;
    }
}
