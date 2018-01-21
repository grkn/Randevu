package com.hizliyol.core.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.hizliyol.core.domain.EnerjiDto;
import com.hizliyol.core.domain.UserDetailDto;
import com.hizliyol.core.entity.ConstantItem;
import com.hizliyol.core.entity.EnergyConsumption;
import com.hizliyol.core.entity.School;
import com.hizliyol.core.lazymodel.EnergyConsumptionLazyModel;
import com.hizliyol.core.service.ConstantItemService;
import com.hizliyol.core.service.EnergyConsumptionService;
import com.hizliyol.core.session.SessionBean;

@Component
@Scope("view")
public class HomePageController extends BaseController {

	@Autowired
	private SessionBean sessionBean;

	@Autowired
	private ConstantItemService constantItemService;

	@Autowired
	private EnergyConsumptionService energyConsumptionService;

	private UserDetailDto user;
	private Set<School> schoolList = new HashSet<>();
	private School selectedSchool;
	private List<EnerjiDto> enerjiList = new ArrayList<>();
	private List<ConstantItem> constantList;
	private ConstantItem selectedConstant;
	private EnergyConsumptionLazyModel lazyModel;
	private EnergyConsumption selectedEnergyConsumption;
	@PostConstruct
	public void init() {
		setUser(sessionBean.getUserDetailDto());
		user.getSchoolResponsibleSet().forEach(item -> schoolList.add(item.getSchoolId()));
		if (!CollectionUtils.isEmpty(schoolList)) {
			selectedSchool = (School) schoolList.toArray()[0];
		}
		constantList = constantItemService.findByType("ENERGY_TYPE");
		if (!CollectionUtils.isEmpty(constantList)) {
			selectedConstant = constantList.get(0);
		}
		 lazyModel = new EnergyConsumptionLazyModel(energyConsumptionService,selectedSchool);
	}
	public void add() {
		EnerjiDto model = new EnerjiDto();
		model.setLabel(selectedConstant.getName());
		enerjiList.add(model);
	}

	public void remove(Integer index) {
		enerjiList.remove(index.intValue());
	}

	public void delete(EnergyConsumption entity){
		entity.setDeleted(true);
		energyConsumptionService.save(entity);
	}
	
	public void insert(Integer index) {
		EnerjiDto item = enerjiList.get(index);
		EnergyConsumption entity = new EnergyConsumption();
		entity.setSchoolId(selectedSchool);
		entity.setValue(item.getValue());
		entity.setType(item.getLabel());
		entity.setSelectedDate(item.getDate());
		entity.setDeleted(false);
		energyConsumptionService.save(entity);
		enerjiList.remove(index.intValue());
		
		showInfoMessage("comsumption.save.successful");
		
	}

	public UserDetailDto getUser() {
		return user;
	}

	public void setUser(UserDetailDto user) {
		this.user = user;
	}

	public Set<School> getSchoolList() {
		return schoolList;
	}

	public void setSchoolList(Set<School> schoolList) {
		this.schoolList = schoolList;
	}

	public School getSelectedSchool() {
		return selectedSchool;
	}

	public void setSelectedSchool(School selectedSchool) {
		this.selectedSchool = selectedSchool;
	}

	public List<EnerjiDto> getEnerjiList() {
		return enerjiList;
	}

	public void setEnerjiList(List<EnerjiDto> enerjiList) {
		this.enerjiList = enerjiList;
	}

	public List<ConstantItem> getConstantList() {
		return constantList;
	}

	public void setConstantList(List<ConstantItem> constantList) {
		this.constantList = constantList;
	}

	public ConstantItem getSelectedConstant() {
		return selectedConstant;
	}

	public void setSelectedConstant(ConstantItem selectedConstant) {
		this.selectedConstant = selectedConstant;
	}

	public EnergyConsumptionService getEnergyConsumptionService() {
		return energyConsumptionService;
	}

	public void setEnergyConsumptionService(EnergyConsumptionService energyConsumptionService) {
		this.energyConsumptionService = energyConsumptionService;
	}

	public EnergyConsumptionLazyModel getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(EnergyConsumptionLazyModel lazyModel) {
		this.lazyModel = lazyModel;
	}

	public EnergyConsumption getSelectedEnergyConsumption() {
		return selectedEnergyConsumption;
	}

	public void setSelectedEnergyConsumption(EnergyConsumption selectedEnergyConsumption) {
		this.selectedEnergyConsumption = selectedEnergyConsumption;
	}

}
