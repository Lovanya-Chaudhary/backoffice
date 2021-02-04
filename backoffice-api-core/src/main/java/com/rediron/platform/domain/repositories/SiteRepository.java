package com.rediron.platform.domain.repositories;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rediron.platform.domain.constants.IQueryConstants;
import com.rediron.platform.domain.model.response.SiteDetailResponse;

/**
 * @author lovanya.chaudhary
 *
 */
@Repository
public class SiteRepository {
	
	Logger logger = LoggerFactory.getLogger(SiteRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<SiteDetailResponse> getAllSites() {
		// TODO Auto-generated method stub
		List<SiteDetailResponse> list = jdbcTemplate.query(IQueryConstants.SITE_DETAILS,
				rs -> {
					List<SiteDetailResponse> list2 = new ArrayList<>();
					while (rs.next()) {
						SiteDetailResponse siteDetails = new SiteDetailResponse();
						siteDetails.setSiteNo(rs.getInt("site_no"));
						siteDetails.setSiteDescription(rs.getString("full_name"));
						siteDetails.setOwnerId(rs.getInt("owner_id"));
						list2.add(siteDetails);
					}
					return list2;
				});
		return list;
	}

}
