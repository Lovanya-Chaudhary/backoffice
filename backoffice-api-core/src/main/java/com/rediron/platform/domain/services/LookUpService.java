package com.rediron.platform.domain.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rediron.platform.domain.model.response.DefaultDCDetailsResponse;
import com.rediron.platform.domain.model.response.PromoInfo;
import com.rediron.platform.domain.repositories.LookUpRepository;
import com.rediron.platform.domain.response.PromoDetails;
import com.rediron.platform.domain.response.RegularPricing;
import com.rediron.platform.domain.response.SKULinkDetails;
import com.tomax.api.UserIdentity;


/**
 * @author lovanya.chaudhary
 *
 */
@Service
public class LookUpService {
	
	Logger logger = LoggerFactory.getLogger(LookUpService.class);
	
	@Autowired
	private LookUpRepository lookUpRepository;
	
	public SKULinkDetails getSKULinkDetails(int linkSkuNo) {
		
		return lookUpRepository.getSKULinkDetails(linkSkuNo);
	}
	
	public int getCount(String mfgCode) {
		return lookUpRepository.getCount(mfgCode);
	}
	
	public BigDecimal getXRef(int skuNo) {
		return lookUpRepository.getXRef(skuNo);
	}
	
	// verify execution 
	public BigDecimal getKITHDRCount(int skuNo) {
		return lookUpRepository.getKITHDRCount(skuNo);
	}
	
	public BigDecimal getKITDTLCount(int skuNo) {
		return lookUpRepository.getKITDTLCount(skuNo);
	}
	
	public BigDecimal getCNTSHEETCount(int skuNo) {
		return lookUpRepository.getCNTSHEETCount(skuNo);
	}
	
	public BigDecimal getDeleteSkuCount(int skuNo) {
		return lookUpRepository.getDeleteSkuCount(skuNo);
	}
	
	public BigDecimal getMaxUpcModifier(String upcId) {
		return lookUpRepository.getMaxUpcModifier(upcId);
	}
	
	public String getDownloadFlag(int skuNo, int siteNo) {
		return lookUpRepository.getDownloadFlag(skuNo, siteNo);
	}
	
	public List<Integer> getKITCoreItems(int skuNo, int siteNo) {
		return lookUpRepository.getKITCoreItems(skuNo, siteNo);
	}
	
	public BigDecimal getPromoKeyNo(int siteNo, int skuNo) {
		return lookUpRepository.getPromoKeyNo(siteNo, skuNo);
	}
	
	// mark down prices verify once
	public BigDecimal getMarkeddownPrice(int siteNo, BigDecimal pkgPrice, BigDecimal markdown, String type) {
		return lookUpRepository.doPriceMarkdown(siteNo, pkgPrice, markdown, type);
	}
	
	// need to check execution also incomplete at the moment
	public PromoDetails getPromoPricing(BigDecimal promoKeyNo) {
		// don't change price values better consume it in insert / update mode after checking
		return lookUpRepository.getPromoPricing(promoKeyNo);
	} 
	
	// need to check execution also incomplete at the moment
	public PromoInfo getPromoInfo(BigDecimal promoKeyNo) {
		// don't change price values better consume it in insert / update mode after checking
		return lookUpRepository.getPromoInfo(promoKeyNo);
	} 
	
	public RegularPricing getRegularPricing(int siteNo, int skuNo) {
		// don't change price values better consume it in insert / update mode after checking
		return lookUpRepository.getRegularPricing(siteNo, skuNo);
	}
	
	// fetches the count for the promos of the newly generated sku if any
	public Map<String, Object> getPromoCount(int deptNo, int classNo, int lineNo) {
		return lookUpRepository.getPromoCount(deptNo, classNo, lineNo);
	}
	
	// this function will be used to insert the promos for the newly generated skus in case of exceptional scenarios
	public Boolean insertSkuPromos(int deptNo, int classNo, int lineNo, int skuNo) {
		return lookUpRepository.insertSkuPromos(deptNo, classNo, lineNo, skuNo);
	}
	
	public String getSupplierDescription(String vendorId) {
		return lookUpRepository.getSupplierDescription(vendorId);
	}

	public int saveOrUpdateItemCodes(UserIdentity domainUser, String code1Id, String code2Id, String code3Id,
			int skuNo) {
		// TODO Auto-generated method stub
		return lookUpRepository.saveOrUpdateItemCodes(domainUser, code1Id, code2Id, code3Id, skuNo);
	}
	
	public BigDecimal getPreviousReorderValue(int skuNo, int siteNo) {
		return lookUpRepository.getPreviousReorderValue(skuNo, siteNo);
	}
	
	public List<Integer> getSitesFromSiteGroupId(String siteGroupId) {
		return lookUpRepository.getSitesFromSiteGroupId(siteGroupId);
	}

	public Integer getPODetailCount(int skuNo, int siteNo, int defaultDc) {
		return lookUpRepository.getPODetailCount(skuNo, siteNo, defaultDc);
	}

	public Integer getXrefDetailCount(int skuNo, int siteNo, int defaultDc) {
		return lookUpRepository.getXrefDetailCount(skuNo, siteNo, defaultDc);
	}

	public List<DefaultDCDetailsResponse> getDefaultDCDetail(int defaultDc) {
		return lookUpRepository.getDefaultDCDetail(defaultDc);
	}

}
