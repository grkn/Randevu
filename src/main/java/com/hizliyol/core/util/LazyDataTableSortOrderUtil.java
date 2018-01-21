package com.hizliyol.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;

/**
 * Created by bilge_gilleez on 17.01.2018.
 */
public class LazyDataTableSortOrderUtil {

    public static <T,F> List<Predicate> sortAndFilterMethod(String sortField, SortOrder sortOrder, Map<String, Object> filters, CriteriaBuilder cb, CriteriaQuery<T> criteriaQuery, Root<F> c) {
        if(sortField != null){
            if(sortOrder.name().equals("ASCENDING")){
                criteriaQuery.orderBy(cb.asc(c.get(sortField)));
            }else{
                criteriaQuery.orderBy(cb.desc(c.get(sortField)));
            }
        }
        List<Predicate> predicates = new ArrayList<>();
        Set<Map.Entry<String,Object>> entrySet = filters.entrySet();
        for (Map.Entry<String,Object> entry :entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Expression<String> path = c.get(key);
            if(path.getJavaType().toString().contains("java.lang.String")){
                predicates.add(cb.like(path, new StringBuilder("%").append(value).append("%").toString()));
            }else{
                predicates.add(cb.equal(path, value));
            }
        }
        return predicates;
    }
}
