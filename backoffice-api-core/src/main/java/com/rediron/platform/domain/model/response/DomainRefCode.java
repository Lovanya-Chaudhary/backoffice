package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DomainRefCode {
	
	@JsonProperty(value = "domain_ref_id")
	private Long domainRefId;
	
	@JsonProperty(value = "code_value")
	private String codeValue;
	
	@JsonProperty(value = "code_meaning")
	private String codeMeaning;

}
