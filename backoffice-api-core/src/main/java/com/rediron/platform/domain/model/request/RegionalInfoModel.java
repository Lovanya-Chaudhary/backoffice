package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RegionalInfoModel {
	
	// replacement regional supersede
	@JsonProperty(value = "is_regional_supersede")
	private boolean isRegionalSupersede;
	
	@JsonProperty(value = "replacement_sku")
	private Integer replacementSKU;
	
	@JsonProperty(value = "lcu_qty")
	private BigDecimal lcuQty;
	
	@JsonProperty(value = "item_type")
	private String itemType;// currently mapped to inv type correct else Db field name mapping
	
	@JsonProperty(value = "buyer_iD")
	private String buyerID;// Db field name mapping
	
	@JsonProperty(value = "report_code")
	private Integer reportCode;
	
	@JsonProperty(value = "link_sku_number")
	private Integer linkSkuNumber;

}
