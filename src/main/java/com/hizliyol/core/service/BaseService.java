package com.hizliyol.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public abstract class BaseService<T,E extends CrudRepository<T, Integer>> {

	private Object instance;


	public BaseService(Object obj) {
		instance = obj;
	}

	public T findById(Integer id){
		return ((E) instance).findById(id).get();
	}
	
	public List<T> findAll(){
		List<T> list = new ArrayList<>();
		((E)instance).findAll().forEach(obj -> list.add(obj));
		return list;
	}
	
	public void save(T t){
		((E) instance).save(t);
	}
	
	public void delete(Integer id){
		((E)instance).deleteById(id);
	}
	
}
