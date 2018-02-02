package com.hizliyol.core.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	
	public static <T,F> List<Predicate> sortAndFilterMethodForCountPostgre(String sortField, SortOrder sortOrder, Map<String, Object> filters, CriteriaBuilder cb, CriteriaQuery<T> criteriaQuery, Root<F> c) {
		return prepareFilter(filters, cb, c);

	}

    public static <T,F> List<Predicate> sortAndFilterMethod(String sortField, SortOrder sortOrder, Map<String, Object> filters, CriteriaBuilder cb, CriteriaQuery<T> criteriaQuery, Root<F> c) {
        if(sortField != null){
            if(sortOrder.name().equals("ASCENDING")){
                criteriaQuery.orderBy(cb.asc(c.get(sortField)));
            }else{
                criteriaQuery.orderBy(cb.desc(c.get(sortField)));
            }
        }
        return prepareFilter(filters, cb, c);
    }

	private static <F> List<Predicate> prepareFilter(Map<String, Object> filters, CriteriaBuilder cb, Root<F> c) {
		List<Predicate> predicates = new ArrayList<>();
        Set<Map.Entry<String,Object>> entrySet = filters.entrySet();
        for (Map.Entry<String,Object> entry :entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Expression<String> path = c.get(key);
            if(path.getJavaType().toString().contains("java.lang.String")){
                predicates.add(cb.like(path, new StringBuilder("%").append(value).append("%").toString()));
            }else
            if(path.getJavaType().toString().contains("java.util.Date")){
            	Expression<Date> pt = c.get(key);
                prepareDatePredicate(cb, predicates, value, pt);
            }else{
            	predicates.add(cb.equal(path, value));
            }
        }
        return predicates;
	}

	private static void prepareDatePredicate(CriteriaBuilder cb, List<Predicate> predicates, Object value,
			Expression<Date> path) {
		Constants constants = new Constants() {
		};
		
		Boolean parsed = Boolean.FALSE;
		try {
			Date dt = constants.getFormattedDate(String.valueOf(value), Constants.ddMMYYYYHHmm);
			Date maxDate = new Date(dt.getTime()+1);
			predicates.add(cb.between(path, dt,maxDate));
			parsed = Boolean.TRUE;
		} catch (ParseException e) {
		}
		
		if(!parsed){
			try {
				Date dt2 = constants.getFormattedDate(String.valueOf(value),Constants.mmYY);
				Date maxDate = new Date(dt2.getTime()+1);
				predicates.add(cb.between(path,dt2,maxDate));
			} catch (ParseException e) {
			}
		}
	}

}
