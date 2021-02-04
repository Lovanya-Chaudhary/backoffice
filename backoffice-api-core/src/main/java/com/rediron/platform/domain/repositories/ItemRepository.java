package com.rediron.platform.domain.repositories;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.rediron.platform.domain.constants.IQueryConstants;
import com.rediron.platform.domain.response.SkuPromo;
import com.rediron.platform.domain.services.UtilityService;

/**
 * @author lovanya.chaudhary
 *
 */
@Repository
public class ItemRepository {
	
	Logger logger = LoggerFactory.getLogger(ItemRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	UtilityService utilityService;
	
	public BigDecimal getSkuNumber(int skuNumber, String itemType) {

		SqlParameterSource paramMap = new MapSqlParameterSource()
				.addValue("p_item_id", skuNumber)
				.addValue("p_item_type", itemType);
		
		BigDecimal skuNumberExisting = utilityService.getDataFromFunction("tmx", "b_item", "get_sku_no", paramMap, BigDecimal.class,
				new SqlOutParameter("number", Types.DECIMAL),
				new SqlParameter("p_item_id", Types.BIGINT),
				new SqlParameter("p_item_type", Types.VARCHAR));

		if(skuNumberExisting != null)
			System.out.println("Is sku "+skuNumber+" existing ==>> " + skuNumberExisting.intValue());
		
		return skuNumberExisting;
	}
	
	// movement function verify once
	public BigDecimal checkMovement(int skuNumber) {

		SqlParameterSource paramMap = new MapSqlParameterSource()
				.addValue("pn_sku_no", skuNumber);
		
		BigDecimal moveCount = utilityService.getDataFromFunction("tmx", "b_item", "item_has_movement", paramMap, BigDecimal.class,
				new SqlOutParameter("number", Types.DECIMAL),
				new SqlParameter("pn_sku_no", Types.BIGINT));

		if(moveCount != null)
			System.out.println("Sku "+skuNumber+" is in movement process if value is 1 else not ==>> " + moveCount.intValue());
		
		return moveCount;
	}

	public List<SkuPromo> getPromoSkuDetails(int deptNo, int classNo, int lineNo, String mode) {
		// TODO Auto-generated method stub
		String query = IQueryConstants.CHECK_PROMOS1;
		
		if(mode.equalsIgnoreCase("check"))
			query = IQueryConstants.CHECK_PROMOS1;
		else if(mode.equalsIgnoreCase("insert"))
			query = IQueryConstants.CHECK_PROMOS2;
		
		List<SkuPromo> list = jdbcTemplate.query(query,
				ps -> {
					BigDecimal deptNumber = null;
					BigDecimal classNumber = null;
					BigDecimal lineNumber = null;
					
					if(deptNo > 0)
						deptNumber = new BigDecimal(deptNo);
					
					if(classNo > 0)
						classNumber = new BigDecimal(classNo);
					
					if(lineNo > 0)
						lineNumber = new BigDecimal(lineNo);
					
					ps.setBigDecimal(1, deptNumber);
					ps.setBigDecimal(2, classNumber);
					ps.setBigDecimal(3, lineNumber);
				},
				rs -> {
					List<SkuPromo> skuDtos = new ArrayList<>();
					
					while(rs.next()) {
						SkuPromo dto = new SkuPromo();
						
						if(mode.equalsIgnoreCase("check")) {
							dto.setAdId(rs.getString("ad_id"));
							dto.setAdSiteNo(rs.getInt("ad_site_no"));
							dto.setPromoKeyNo(rs.getBigDecimal("promo_key_no"));
						}
						else if(mode.equalsIgnoreCase("insert")) {
							dto.setAdId(rs.getString("ad_id"));
							dto.setAdSiteNo(rs.getInt("ad_site_no"));
							dto.setSiteGroupId(rs.getString("group_id"));
							dto.setStopDate(new Date(rs.getDate("stop_dt").getTime()));
						}
						
						skuDtos.add(dto);
					}
					
					return skuDtos;
				});
		return list;
	}

}
