package com.rediron.platform.domain.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class InventorySearchInfoResponse {
	
	private Integer skuNo;
	private String itemDescription;
	private String receiptDesc;
	private String pkgDesc;
	private String lookupDesc;
	private Integer deptgrpNo;
	private Integer deptNo;
	private Integer classNo;
	private Integer lineNo;
	private String reportUm;
	private String sellUm;
	private String code1Id;
	private String code2Id;
	private String code3Id;
	private BigDecimal reportFactor;
	private String upcId;
	private String attribute1Value;
	private String attribute2Value;
	private String attribute3Value;
	private boolean isMasterSku;
	private Integer linkSkuNo;
	private String vendorId;
	private String vendorItem;
	private String mfgCd;

}
