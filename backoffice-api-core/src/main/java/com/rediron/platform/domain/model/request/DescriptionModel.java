package com.rediron.platform.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DescriptionModel {
	
	// descriptionInfo
	@JsonProperty(value = "receipt_description")
	private String receiptDesc;
	
	@JsonProperty(value = "alpha_lookup")
	private String lookupDesc;
	
	@JsonProperty(value = "product_size")
	private String productSize;

}
