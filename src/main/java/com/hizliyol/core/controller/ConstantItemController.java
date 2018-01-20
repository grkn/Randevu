package com.hizliyol.core.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hizliyol.core.entity.ConstantItem;
import com.hizliyol.core.service.ConstantItemService;

@Component
@Scope("view")
public class ConstantItemController {

	private ConstantItem constantItem = new ConstantItem();
	private List<ConstantItem> constantItemList;

	@PostConstruct
	public void init(){
		constantItemList = constantItemService.findAll();
	}
	
	@Autowired
	private ConstantItemService constantItemService;
	
	public void save(){
		constantItemList.remove(constantItem);
		constantItemService.save(constantItem);
		constantItemList.add(constantItem);
		constantItem = new ConstantItem();
	}
	
	public void delete(ConstantItem constantItem){
		constantItemService.delete(constantItem.getId());
		constantItemList.remove(constantItem);
	}
	
	public void update(Integer id){
		constantItem = constantItemService.findById(id);
	}
	
	public ConstantItem getConstantItem() {
		return constantItem;
	}

	public void setConstantItem(ConstantItem constantItem) {
		this.constantItem = constantItem;
	}

	public List<ConstantItem> getConstantItemList() {
		return constantItemList;
	}

	public void setConstantItemList(List<ConstantItem> constantItemList) {
		this.constantItemList = constantItemList;
	}
	
}
