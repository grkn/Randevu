package com.hizliyol.core.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

import com.hizliyol.core.entity.EnergyConsumption;
import com.hizliyol.core.entity.School;
import com.hizliyol.core.util.LazyDataTableSortOrderUtil;

@Repository
public class EnergyConsumptionDao extends AbstractJPADao {

	public Long getRandevuUserLazilyCount(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters,School school) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<EnergyConsumption> c = criteriaQuery.from(EnergyConsumption.class);
		criteriaQuery = criteriaQuery.select(cb.count(c));
		List<Predicate> predicateList = LazyDataTableSortOrderUtil.sortAndFilterMethodForCountPostgre(sortField, sortOrder, filters, cb, criteriaQuery, c);
		predicateList.add(cb.equal(c.get("schoolId"),school));
		predicateList.add(cb.equal(c.get("deleted"), Boolean.FALSE));
		criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
		
		List<Long> list = getEntityManager().createQuery(criteriaQuery).getResultList();
		//postgre fix it is really weird count order by statement requires group by expression in postgre
		Long total = 0L;
		for (Long long1 : list) {
			total += long1;
		}
		return total;
	}

	public List<EnergyConsumption> getRandevuUserLazily(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters,School school) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EnergyConsumption> criteriaQuery = cb.createQuery(EnergyConsumption.class);
		Root<EnergyConsumption> c = criteriaQuery.from(EnergyConsumption.class);
		criteriaQuery = criteriaQuery.select(c);
		
		List<Predicate> predicateList = LazyDataTableSortOrderUtil.sortAndFilterMethod(sortField, sortOrder, filters, cb, criteriaQuery, c);
		predicateList.add(cb.equal(c.get("schoolId"),school));
		predicateList.add(cb.equal(c.get("deleted"), Boolean.FALSE));
		criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
		return getEntityManager().createQuery(criteriaQuery).setFirstResult(first).setMaxResults(pageSize)
				.getResultList();

	}

}
