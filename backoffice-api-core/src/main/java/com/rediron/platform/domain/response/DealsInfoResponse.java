package com.rediron.platform.domain.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DealsInfoResponse {
	
	private long dealNo;
	private Integer siteNo;
	private long seqNo;
	private String detailType;
	private String packType;
	
	private BigDecimal amtOff;
	private BigDecimal pctOff;
	private BigDecimal maxQty;
	private BigDecimal coopAmtOff;
	private String vendorItem;
	
	private BigDecimal packQty;

}
