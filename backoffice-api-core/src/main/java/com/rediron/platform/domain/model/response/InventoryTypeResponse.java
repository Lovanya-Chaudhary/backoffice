package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InventoryTypeResponse {
	
	@JsonProperty(value = "code_value")
	private String codeValue;
	
	@JsonProperty(value = "code_meaning")
	private String codeMeaning;

}
