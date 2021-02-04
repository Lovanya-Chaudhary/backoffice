package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SiteDetailResponse {
	
	@JsonProperty(value = "site_no")
	private int siteNo;
	
	@JsonProperty(value = "site_description")
	private String siteDescription;
	
	@JsonProperty(value = "owner_id")
	private int ownerId;

}
