package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DefaultDCDetailsResponse {
	
	@JsonProperty(value = "site_no")
	private int siteNo;
	
	@JsonProperty(value = "full_name")
	private String fullName;

}
