package com.hizliyol.core.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hizliyol.core.util.LazyDataTableSortOrderUtil;
import org.hibernate.query.criteria.internal.CriteriaQueryImpl;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.hizliyol.core.entity.RandevuUser;
import com.hizliyol.core.entity.Role;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@Repository
public class UserDao extends AbstractJPADao{

	public RandevuUser getUser(String userName){
		List<RandevuUser> list = getEntityManager().createNamedQuery("RandevuUser.findByUsername").setParameter("username", userName).getResultList();
		if(CollectionUtils.isEmpty(list))
			return null;
		
		return list.get(0);
	}
	
	public List<Role> getRoles(){
		return getEntityManager().createNamedQuery("Role.findAll").getResultList();
	}

	public List<RandevuUser> getRandevuUserLazily(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<RandevuUser> criteriaQuery = cb.createQuery(RandevuUser.class);
		Root<RandevuUser> c = criteriaQuery.from(RandevuUser.class);
		LazyDataTableSortOrderUtil.sortAndFilterMethod(sortField, sortOrder, filters, cb, criteriaQuery, c);
		return getEntityManager().createQuery(criteriaQuery).setFirstResult(first).setMaxResults(pageSize).getResultList();
	}

	public Long getRandevuUserLazilyCount(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<RandevuUser> c = criteriaQuery.from(RandevuUser.class);
		criteriaQuery.select(cb.count(c));
		LazyDataTableSortOrderUtil.sortAndFilterMethod(sortField, sortOrder, filters, cb, criteriaQuery, c);
		return getEntityManager().createQuery(criteriaQuery).getSingleResult();
	}
}
