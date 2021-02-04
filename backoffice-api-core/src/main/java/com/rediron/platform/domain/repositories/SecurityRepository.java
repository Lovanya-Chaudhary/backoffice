package com.rediron.platform.domain.repositories;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.rediron.platform.domain.services.UtilityService;
import com.tomax.api.UserIdentity;

/**
 * @author lovanya.chaudhary
 *
 */
@Repository
public class SecurityRepository {

	Logger logger = LoggerFactory.getLogger(SecurityRepository.class);

	@Autowired
	UtilityService utilityService;

	public boolean isUserAllowed(UserIdentity domainUser, int siteNo, String configName) {

		// 1.
		BigDecimal permValue = utilityService.getDataFromFunction("tmx", "b_security", "idm_permission", null,
				BigDecimal.class, new SqlOutParameter("number", Types.DECIMAL));

		if (permValue != null)
			logger.info("Permission value is " + permValue);

		SqlParameterSource paramMap = new MapSqlParameterSource()
				.addValue("p_employee_id", domainUser.getUserName().toUpperCase().trim()).addValue("p_site_no", siteNo)
				.addValue("p_permission_type", permValue.intValue()).addValue("p_permission", configName.trim());

		// 2.
		BigDecimal allowedValue = utilityService.getDataFromFunction("tmx", "b_security", "user_has_permission",
				paramMap, BigDecimal.class, new SqlOutParameter("number", Types.DECIMAL),
				new SqlParameter("p_employee_id", Types.VARCHAR), new SqlParameter("p_site_no", Types.INTEGER),
				new SqlParameter("p_permission_type", Types.INTEGER), new SqlParameter("p_permission", Types.VARCHAR));

		if (allowedValue != null) {
			logger.info("Permission value is " + allowedValue);

			if (allowedValue.intValue() == 1)
				return true;
		}
		return false;
	}

	// procedure call to be tested as output is unknown and no valid data available
	// need to ask for the data set up
	public boolean isItemAuthorised(UserIdentity domainUser, int skuNo, int siteNo) {
		// TODO Auto-generated method stub
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("sku_no", skuNo).addValue("p_site_no",
				siteNo);

		Map<String, Object> output = utilityService.getDataFromProcedure("tmx", "m_item", "authorize_item_for_site",
				paramMap, new SqlOutParameter("number", Types.DECIMAL), new SqlParameter("p_sku_no", Types.INTEGER),
				new SqlParameter("p_auth_site_no", Types.INTEGER));

		if (output != null)
			logger.info("Procedure call testing ==>> " + output);

		return false;
	}

}
