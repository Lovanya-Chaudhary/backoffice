package com.rediron.platform.domain.model.response;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InventorySearchResult {
	
	@JsonProperty(value = "sku_no")
	private Integer skuNo;
	
	@JsonProperty(value = "item_description")
	private String itemDescription;
	
	@JsonProperty(value = "receipt_description")
	private String receiptDesc;
	
	@JsonProperty(value = "pkg_description")
	private String pkgDesc;
	
	@JsonProperty(value = "lookup_description")
	private String lookupDesc;
	
	@JsonProperty(value = "deptgrp_no")
	private Integer deptgrpNo;
	
	@JsonProperty(value = "dept_no")
	private Integer deptNo;
	
	@JsonProperty(value = "class_no")
	private Integer classNo;
	
	@JsonProperty(value = "line_no")
	private Integer lineNo;
	
	@JsonProperty(value = "report_um")
	private String reportUm;
	
	@JsonProperty(value = "sell_um")
	private String sellUm;
	
	@JsonProperty(value = "code1_id")
	private String code1Id;
	
	@JsonProperty(value = "code2_id")
	private String code2Id;
	
	@JsonProperty(value = "code3_id")
	private String code3Id;
	
	@JsonProperty(value = "report_factor")
	private BigDecimal reportFactor;
	
	@JsonProperty(value = "upc_id")
	private String upcId;
	
	@JsonProperty(value = "attribute1_value")
	private String attribute1Value;
	
	@JsonProperty(value = "attribute2_value")
	private String attribute2Value;
	
	@JsonProperty(value = "attribute3_value")
	private String attribute3Value;
	
	@JsonProperty(value = "is_master_sku")
	private boolean isMasterSku;
	
	@JsonProperty(value = "link_sku_no")
	private Integer linkSkuNo;
	
	@JsonProperty(value = "vendor_id")
	private String vendorId;
	
	@JsonProperty(value = "vendor_item")
	private String vendorItem;
	
	@JsonProperty(value = "mfg_cd")
	private String mfgCd;
}
