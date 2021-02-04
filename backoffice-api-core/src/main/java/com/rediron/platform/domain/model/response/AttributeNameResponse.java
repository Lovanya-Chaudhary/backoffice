package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class AttributeNameResponse {
	
	@JsonProperty(value = "sku_no")
	private Integer skuNo;
	
	@JsonProperty(value = "attribute_id")
	private Integer attributeId;
	
	@JsonProperty(value = "attribute_value")
	private String attributeValue;
	
	@JsonProperty(value = "functional_area_cd")
	private String functionalAreaCd;

}
