package com.rediron.platform.domain.response;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class SkuPromo {
	
	private String adId;
	private int adSiteNo;
	private BigDecimal promoKeyNo;
	private Date stopDate;
	private String siteGroupId;

}
