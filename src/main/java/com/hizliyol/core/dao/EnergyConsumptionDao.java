package com.hizliyol.core.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

import com.hizliyol.core.entity.EnergyConsumption;
import com.hizliyol.core.util.LazyDataTableSortOrderUtil;

@Repository
public class EnergyConsumptionDao extends AbstractJPADao {

	public Long getRandevuUserLazilyCount(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<EnergyConsumption> c = criteriaQuery.from(EnergyConsumption.class);
		criteriaQuery.select(cb.count(c));
		LazyDataTableSortOrderUtil.sortAndFilterMethod(sortField, sortOrder, filters, cb, criteriaQuery, c);
		return getEntityManager().createQuery(criteriaQuery).getSingleResult();
	}

	public List<EnergyConsumption> getRandevuUserLazily(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EnergyConsumption> criteriaQuery = cb.createQuery(EnergyConsumption.class);
		Root<EnergyConsumption> c = criteriaQuery.from(EnergyConsumption.class);
		LazyDataTableSortOrderUtil.sortAndFilterMethod(sortField, sortOrder, filters, cb, criteriaQuery, c);
		return getEntityManager().createQuery(criteriaQuery).setFirstResult(first).setMaxResults(pageSize)
				.getResultList();

	}

}
