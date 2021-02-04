package com.rediron.platform.domain.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DuplicateItemInfoResponse {
	
	private Integer skuNumber;
	private String itemDescription;
	private String receiptDesc;
	private String pkgDesc;
	private String lookupDesc;
	
	private Integer deptNo;
	private Integer classNo;
	private Integer lineNo;
	private String reportUm;
	private String sellUm;
	
	private BigDecimal reportFactor;
	private String upcId;
	private String vendorId;
	private String vendorItem;
	private String mfgCd;

}
