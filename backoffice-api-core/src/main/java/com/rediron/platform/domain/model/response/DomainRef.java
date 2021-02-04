package com.rediron.platform.domain.model.response;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DomainRef {
	
	@JsonProperty(value = "domain_ref_id")
	private Long domainRefId;
	
	@JsonProperty(value = "description")
    private String description;

}
