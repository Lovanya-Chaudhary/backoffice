package com.rediron.platform.domain.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DomainRefCodes {
	
	@JsonProperty(value = "domain_ref_code")
	private List<DomainRefCode> domainRefCode;

}
