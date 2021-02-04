package com.rediron.platform.domain.services;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rediron.platform.domain.repositories.SkuPkgRepository;


/**
 * @author lovanya.chaudhary
 *
 */
@Service
public class SkuPkgService {
	
	Logger logger = LoggerFactory.getLogger(SkuPkgService.class);
	
	@Autowired
	private SkuPkgRepository skuPkgRepository;
	
	// manually entering sku number check
	// in case of overloaded functions declare in / out params else multiple
	// signature exception will be thrown
	public BigDecimal getNextSku(int skuNo) {
		return skuPkgRepository.getNextSku(skuNo);
	}

}
