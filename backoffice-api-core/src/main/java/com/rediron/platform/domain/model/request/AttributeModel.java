package com.rediron.platform.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AttributeModel {
	
	@JsonProperty(value = "sku_no")
	private Integer skuNo;
	
	@JsonProperty(value = "attribute_id")
	private Integer attributeId;
	
	@JsonProperty(value = "lov_fl")
	private boolean lovFl;
	
	@JsonProperty(value = "functional_area_cd")
	private String functionalAreaCd;
	
	@JsonProperty(value = "attribute_value")
	private String attributeValue;
	
	@JsonProperty(value = "attribute_value_text")
	private String attributeValueText;

}
