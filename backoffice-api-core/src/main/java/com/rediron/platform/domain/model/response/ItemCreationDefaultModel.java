package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemCreationDefaultModel {
	
	@JsonProperty(value = "item_type_cd")
	private String itemTypeCd;
	
	@JsonProperty(value = "display_label")
	private String displayLabel;
	
	@JsonProperty(value = "function_cd")
	String functionCd;
	
	@JsonProperty(value = "hint_text")
	String hintText;
}
