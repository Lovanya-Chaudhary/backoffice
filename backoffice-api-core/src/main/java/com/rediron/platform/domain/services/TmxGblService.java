package com.rediron.platform.domain.services;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rediron.platform.domain.repositories.TmxGblRepository;


/**
 * @author lovanya.chaudhary
 *
 */
@Service
public class TmxGblService {
	
	Logger logger = LoggerFactory.getLogger(TmxGblService.class);

	@Autowired
	private TmxGblRepository tmxGblRepository;

	// AUTO_SKU or others
	public String getFlag(String configName) {
		return tmxGblRepository.getFlag(configName);
	}

	// LOCAL_SKU with site no
	public String getSiteFlag(String configName, int siteNo) {
		return tmxGblRepository.getSiteFlag(configName, siteNo);
	}

	// MIN, MAX sku
	public BigDecimal getNum(String configName) {
		return tmxGblRepository.getNum(configName);
	}
	
	public String getText(String configName) {
		return tmxGblRepository.getText(configName);
	}
	
	public int getHqSite() {	
		return tmxGblRepository.getHqSite();
	}
	
	public int getCurrentSite() {	
		return tmxGblRepository.getCurrentSite();
	}
	
	public int getOwnerId() {
		return tmxGblRepository.getOwnerId();
	}
	
	// verify and implement function
	public boolean isPerCarQty() {
		return tmxGblRepository.isPerCarQty();
	}

}
