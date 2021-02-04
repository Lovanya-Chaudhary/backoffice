package com.rediron.platform.domain.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemAttributeResults {
	
	@JsonProperty(value = "item_attribute_result")
	List<ItemAttributeResult> itemAttributeResult;

}
