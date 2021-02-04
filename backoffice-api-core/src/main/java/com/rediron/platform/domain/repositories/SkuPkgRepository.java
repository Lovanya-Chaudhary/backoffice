package com.rediron.platform.domain.repositories;

import java.math.BigDecimal;
import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.rediron.platform.domain.services.UtilityService;

/**
 * @author lovanya.chaudhary
 *
 */
@Repository
public class SkuPkgRepository {
	
	Logger logger = LoggerFactory.getLogger(SkuPkgRepository.class);
	
	@Autowired
	UtilityService utilityService;
	
	public BigDecimal getNextSku(int skuNo) {
		
		SqlParameterSource paramMap = new MapSqlParameterSource()
				.addValue("sku_no", skuNo)
				.addValue("check_flag", "N");
		
		BigDecimal skuValue = utilityService.getDataFromFunction("tmx", "skupkg", "add_sku_check_digit", paramMap, BigDecimal.class,
				new SqlOutParameter("number", Types.DECIMAL),
				new SqlParameter("sku_no", Types.BIGINT),
				new SqlParameter("check_flag", Types.CHAR));
		
		if(skuValue != null)
			logger.info("Next SKU number is "+skuValue);

		return skuValue;
	}

}
