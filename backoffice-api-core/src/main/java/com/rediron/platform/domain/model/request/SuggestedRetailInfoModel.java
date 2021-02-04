package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SuggestedRetailInfoModel {
	
	@JsonProperty(value = "suggested_retail_qty")
	private Integer suggestedRetailQty; // msrp field has same value both are linked
	
	@JsonProperty(value = "suggested_retail_price")
	private BigDecimal suggestedRetailPrice; // last field in Price levels has same value both fields are linked
	
	@JsonProperty(value = "suggested_retail_epo")
	private BigDecimal suggestedRetailEPO;

}
