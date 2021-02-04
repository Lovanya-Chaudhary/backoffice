package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemAttributeResult {
	
	@JsonProperty(value = "sku_no")
	private Integer skuNo;
	
	@JsonProperty(value = "attribute_id")
	private Integer attributeId;
	
	@JsonProperty(value = "attribute_name")
	private String attributeName;
	
	@JsonProperty(value = "attribute_value")
	private String attributeValue;
	
	@JsonProperty(value = "attribute_description")
	private String attributeDescription;
	
	@JsonProperty(value = "lov_fl")
	private String lovFl;

}
