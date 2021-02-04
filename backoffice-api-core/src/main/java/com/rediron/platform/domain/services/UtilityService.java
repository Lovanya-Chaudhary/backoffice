package com.rediron.platform.domain.services;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.rediron.platform.domain.entities.Adhdr;

@Service
public class UtilityService {
	
	Logger logger = LoggerFactory.getLogger(UtilityService.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private SimpleJdbcCall jdbcCall;
	
	/**
	 * @param <T>
	 * @param schemaName
	 * @param catalogName
	 * @param functionName
	 * @param paramMap
	 * @param resultClass
	 * @param sqlParameters
	 * @return
	 */
	public <T extends Object> T getDataFromFunction(String schemaName, String catalogName, String functionName, 
			SqlParameterSource paramMap, Class<T> resultClass, SqlParameter... sqlParameters) {
		jdbcTemplate.setResultsMapCaseInsensitive(true);

		if(sqlParameters != null)
			jdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(schemaName)
					.withCatalogName(catalogName) // package procedure																														// name
					.withFunctionName(functionName)
					.declareParameters(sqlParameters)
					.withoutProcedureColumnMetaDataAccess();
		else 
			jdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(schemaName)
					.withCatalogName(catalogName) // package procedure																														// name
					.withFunctionName(functionName)
					.withoutProcedureColumnMetaDataAccess();

		if(paramMap != null)
			return jdbcCall.executeFunction(resultClass, paramMap);
		else
			return jdbcCall.executeFunction(resultClass);
	}
	
	// need to test the below function
	public <T extends Object> T getDataFromQuery(String sqlQuery, Class<T> resultClass, Object[] objects) {
		return jdbcTemplate.queryForObject(sqlQuery, objects, resultClass);
	}
	
	// generic method to call procedures
	public Map<String, Object> getDataFromProcedure(String schemaName, String catalogName, String procedureName, 
			SqlParameterSource paramMap, SqlParameter... sqlParameters) {
		jdbcTemplate.setResultsMapCaseInsensitive(true);

		if(sqlParameters != null)
			jdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(schemaName)
					.withCatalogName(catalogName) // package procedure																														// name
					.withProcedureName(procedureName)
					.declareParameters(sqlParameters)
					.withoutProcedureColumnMetaDataAccess();
		else 
			jdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(schemaName)
					.withCatalogName(catalogName) // package procedure																														// name
					.withProcedureName(procedureName)
					.withoutProcedureColumnMetaDataAccess();

		if(paramMap != null)
			return jdbcCall.execute(paramMap);
		else
			return jdbcCall.execute();
	}
	
	public Map<String, Object> getData(String schemaName, String catalogName, String procedureName, 
			SqlParameterSource paramMap, SqlParameter... sqlParameters) {
		jdbcTemplate.setResultsMapCaseInsensitive(true);

		if(sqlParameters != null)
			jdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(schemaName)
					.withCatalogName(catalogName) // package procedure																														// name
					.withProcedureName(procedureName)
					.declareParameters(sqlParameters)
					.withoutProcedureColumnMetaDataAccess()
					.returningResultSet("adhdr_ptrec", BeanPropertyRowMapper.newInstance(Adhdr.class));
		else 
			jdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(schemaName)
					.withCatalogName(catalogName) // package procedure																														// name
					.withProcedureName(procedureName)
					.withoutProcedureColumnMetaDataAccess()
					.returningResultSet("adhdr_ptrec", BeanPropertyRowMapper.newInstance(Adhdr.class));
		if(paramMap != null)
			return jdbcCall.execute(paramMap);
		else
			return jdbcCall.execute();
	}

}
