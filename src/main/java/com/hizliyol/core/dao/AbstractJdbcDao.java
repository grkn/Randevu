package com.hizliyol.core.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class AbstractJdbcDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	@Qualifier("randevuDatasource")
	public void init(DataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
}
