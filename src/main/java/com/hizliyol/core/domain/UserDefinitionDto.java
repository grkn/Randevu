package com.hizliyol.core.domain;

import java.util.Date;

public class UserDefinitionDto {
	private Integer id;
	private String name;
	private String responsibility;
	private Integer interval;
	private Date freeDay;
	private Date freeDayTwo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	@Override
	public String toString() {
		return "UserDefinitionDto [name=" + name + ", responsibility=" + responsibility + ", interval=" + interval
				+ "]";
	}

	public Date getFreeDay() {
		return freeDay;
	}

	public void setFreeDay(Date freeDay) {
		this.freeDay = freeDay;
	}

	public Date getFreeDayTwo() {
		return freeDayTwo;
	}

	public void setFreeDayTwo(Date freeDayTwo) {
		this.freeDayTwo = freeDayTwo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	
}
