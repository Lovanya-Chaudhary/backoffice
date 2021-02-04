package com.rediron.platform.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AttributeListValuesInfoResponse {
	
	@JsonProperty(value = "code_meaning")
    private String codeMeaning;
	
	@JsonProperty(value = "code_value")
    private String codeValue;

}
