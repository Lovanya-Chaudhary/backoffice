package com.rediron.platform.domain.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemAttributesDetails {
	
	@JsonProperty(value = "item_attributes_details")
	private List<ItemAttributesDetail> itemAttributesDetail;

}
