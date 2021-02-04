package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CodeDetailsResponse {
	
	@JsonProperty(value = "code_value")
	private String codeValue;
	
	@JsonProperty(value = "code_description")
	private String codeDescription;

}
