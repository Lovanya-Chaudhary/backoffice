package com.rediron.platform.domain.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tomax.api.RNetApiContexts;
import com.tomax.api.UserIdentity;
import com.tomax.salesHistory.api.SalesHistoryAPI;
import com.tomax.salesHistory.dto.custom.SalesHistoryByPeriodDTO;

@Service
public class SalesService {
	
	Logger logger = LoggerFactory.getLogger(SalesService.class);
	
	private static SalesHistoryAPI salesApi = RNetApiContexts.hq.getStateless(SalesHistoryAPI.class);

	public List<SalesHistoryByPeriodDTO> getSalesHistory(UserIdentity domainUser, int siteNumber, int skuNumber) {
		// TODO Auto-generated method stub
		return salesApi.getSalesHistoryTotalsByPeriod(domainUser, siteNumber, skuNumber);
	}

}
