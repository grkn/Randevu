package com.hizliyol.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hizliyol.core.entity.UserDefinition;

@Repository
public class AdminJPADao extends AbstractJPADao{

	public List<UserDefinition> queryUserDefinitions(String userName){
		List<UserDefinition> list = getEntityManager().createQuery("select ud from UserDefinition ud where ud.userName = :userName",UserDefinition.class)
				.setParameter("userName",userName).getResultList();
		return list;
	}
	
	public UserDefinition save(UserDefinition model){
		return getEntityManager().merge(model);
	}
	
	public void delete(UserDefinition model){
		getEntityManager().remove(getEntityManager().find(UserDefinition.class,model.getId()));
	}
	
	public UserDefinition queryById(Integer id){
		return getEntityManager().find(UserDefinition.class,id);
	}
	
}
