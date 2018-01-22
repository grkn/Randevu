package com.hizliyol.core.dao;

import com.hizliyol.core.entity.Role;
import com.hizliyol.core.util.LazyDataTableSortOrderUtil;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

/**
 * Created by bilge_gilleez on 17.01.2018.
 */
@Repository
public class RoleDao extends AbstractJPADao {

    public Long getLazilyCount(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        Root<Role> c = criteriaQuery.from(Role.class);
        criteriaQuery.select(cb.count(c));
        List<Predicate> predicateList = LazyDataTableSortOrderUtil.sortAndFilterMethod(sortField, sortOrder, filters, cb, criteriaQuery, c);
		criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }

    public List<Role> getLazily(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = cb.createQuery(Role.class);
        Root<Role> c = criteriaQuery.from(Role.class);
        List<Predicate> predicateList = LazyDataTableSortOrderUtil.sortAndFilterMethod(sortField, sortOrder, filters, cb, criteriaQuery, c);
		criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
		return getEntityManager().createQuery(criteriaQuery).setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    public List<Role> queryRoles(){
        return getEntityManager().createNamedQuery("Role.findAll").getResultList();
    }
}
