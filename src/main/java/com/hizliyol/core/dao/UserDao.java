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

import com.hizliyol.core.entity.UserManagement;
import com.hizliyol.core.entity.Role;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class UserDao extends AbstractJPADao{

	public UserManagement getUser(String userName){
		List<UserManagement> list = getEntityManager().createNamedQuery("UserManagement.findByUsername").setParameter("username", userName).getResultList();
		if(CollectionUtils.isEmpty(list))
			return null;
		
		return list.get(0);
	}
	
	public List<Role> getRoles(){
		return getEntityManager().createNamedQuery("Role.findAll").getResultList();
	}

	public List<UserManagement> getUserLazily(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserManagement> criteriaQuery = cb.createQuery(UserManagement.class);
		Root<UserManagement> c = criteriaQuery.from(UserManagement.class);
		List<Predicate> predicateList = LazyDataTableSortOrderUtil.sortAndFilterMethod(sortField, sortOrder, filters, cb, criteriaQuery, c);
		criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
		return getEntityManager().createQuery(criteriaQuery).setFirstResult(first).setMaxResults(pageSize).getResultList();
	}

	public Long getUserLazilyCount(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<UserManagement> c = criteriaQuery.from(UserManagement.class);
		criteriaQuery.select(cb.count(c));
		List<Predicate> predicateList = LazyDataTableSortOrderUtil.sortAndFilterMethod(sortField, sortOrder, filters, cb, criteriaQuery, c);
		criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
		return getEntityManager().createQuery(criteriaQuery).getSingleResult();
	}
}
