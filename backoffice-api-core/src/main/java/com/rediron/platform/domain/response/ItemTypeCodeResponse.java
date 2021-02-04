package com.rediron.platform.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemTypeCodeResponse {
	
	@JsonProperty(value = "item_type_cd")
	private String itemTypeCd;
	
	@JsonProperty(value = "display_label")
	private String displayLabel;

}
