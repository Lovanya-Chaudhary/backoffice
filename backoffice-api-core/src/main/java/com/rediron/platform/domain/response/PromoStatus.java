package com.rediron.platform.domain.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PromoStatus {
	
	private BigDecimal markdownPact;
	private BigDecimal offAmount;
	private boolean sellItemizerFl1;
	private boolean sellItemizerFl2;
	private String itemizerStatusId;
	private String skuStatusId;
	private boolean updateItemizerStatusIdFl;
	private boolean updateSkuStatusIdFl;
	private boolean updatePromoCommissionCodeFl;
	private String pomoCommissionCode;

}
