package com.rediron.platform.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StatusInfoModel {
	
	@JsonProperty(value = "itemizer_status_id")
	private String itemizerStatusId;
	
	@JsonProperty(value = "pos_sku_status_id")
	private String posSkuStatusId;
	
	@JsonProperty(value = "restrict_sale_id")
	private String restrictSaleId;
	
	// added new fields to accommodate the existing functionality in mmso
	@JsonProperty(value = "item_status_id")
	private String itemStatusId;

}
