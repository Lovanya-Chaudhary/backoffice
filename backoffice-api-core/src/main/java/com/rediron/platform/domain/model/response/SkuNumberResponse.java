package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SkuNumberResponse {
	
	@JsonProperty(value = "sku_no")
	private Integer skuNo;
	
	public SkuNumberResponse(Integer skuNo) {
		this.skuNo = skuNo;
	}
}
