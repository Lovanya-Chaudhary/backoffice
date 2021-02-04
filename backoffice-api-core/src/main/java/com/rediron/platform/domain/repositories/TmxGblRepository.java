package com.rediron.platform.domain.repositories;

import java.math.BigDecimal;
import java.sql.Types;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.rediron.platform.domain.services.UtilityService;

/**
 * @author lovanya.chaudhary
 *
 */
@Repository
public class TmxGblRepository {
	
	Logger logger = LoggerFactory.getLogger(TmxGblRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	UtilityService utilityService;

	// AUTO_SKU or others
	public String getFlag(String configName) {
		
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("NAME_IN", configName);
		
		// 1. 
		String flag = utilityService.getDataFromFunction("tmx", "tmxgbl", "get_flag", paramMap, String.class,
				new SqlOutParameter("number", Types.VARCHAR),
				new SqlParameter("NAME_IN", Types.VARCHAR));
		
		if(StringUtils.isNotBlank(flag))
			logger.info("Flag for the "+configName+" is ==>> "+flag);

		return flag;
	}

	// LOCAL_SKU with site no
	public String getSiteFlag(String configName, int siteNo) {
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("NAME_IN", configName).addValue("SITE_IN",
				siteNo);		
		// 2. 
		String flag = utilityService.getDataFromFunction("tmx", "tmxgbl", "getsite_flag", paramMap, String.class,
				new SqlOutParameter("number", Types.VARCHAR),
				new SqlParameter("NAME_IN", Types.VARCHAR),
				new SqlParameter("SITE_IN", Types.BIGINT));
		
		if(StringUtils.isNotBlank(flag))
			logger.info("Flag for the "+configName+" on site : "+siteNo+" is ==>> "+flag);

		return flag;
	}

	// MIN, MAX sku
	public BigDecimal getNum(String configName) {
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("NAME_IN", configName);
		
		// 2. 
		BigDecimal flagValue = utilityService.getDataFromFunction("tmx", "tmxgbl", "get_num", paramMap, BigDecimal.class,
				new SqlOutParameter("number", Types.DECIMAL),
				new SqlParameter("NAME_IN", Types.VARCHAR));
		
		if(flagValue != null)
			logger.info("Flag for the "+configName+" is ==>> "+flagValue);

		return flagValue;
	}
	
	public String getText(String configName) {
		
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("NAME_IN", configName);
		// 2. 
		String flagValue = utilityService.getDataFromFunction("tmx", "tmxgbl", "get_text", paramMap, String.class,
				new SqlOutParameter("number", Types.VARCHAR),
				new SqlParameter("NAME_IN", Types.VARCHAR));
		
		if(StringUtils.isNotBlank(flagValue))
			logger.info("Flag for the "+configName+" is ==>> "+flagValue);

		return flagValue;
	}
	
	public int getHqSite() {
		
		BigDecimal hqSiteNo = utilityService.getDataFromFunction("tmx", "tmxgbl", "hq_site_no", null, BigDecimal.class,
				new SqlOutParameter("number", Types.DECIMAL));

		if(hqSiteNo != null)
			logger.info("HQ site ==>> " + hqSiteNo.intValue());
		
		return hqSiteNo.intValue();
	}
	
	public int getCurrentSite() {
		BigDecimal currentSiteNo = utilityService.getDataFromFunction("tmx", "tmxgbl", "my_site", null, BigDecimal.class,
				new SqlOutParameter("number", Types.DECIMAL));
		
		if(currentSiteNo != null)
			logger.info("Current site ==>> " + currentSiteNo.intValue());
		
		return currentSiteNo.intValue();
	}
	
	public int getOwnerId() {
		
		BigDecimal ownerId = utilityService.getDataFromFunction("tmx", "tmxgbl", "my_owner_id", null, BigDecimal.class,
				new SqlOutParameter("number", Types.DECIMAL));

		if(ownerId != null)
			logger.info("Owner Id ==>> " + ownerId.intValue());
		
		return ownerId.intValue();
	}
	
	// verify and implement function
	public boolean isPerCarQty() {
		
		jdbcTemplate.setResultsMapCaseInsensitive(true);

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName("tmx")
				.withCatalogName("tmxgbl") // package procedure																														// name
				.withFunctionName("for_auto");

		Boolean flag = jdbcCall.executeFunction(Boolean.class);

		if(flag != null)
		System.out.println("Is per car qty enabled ==>> " + flag);
		
		return flag;
	}

}
