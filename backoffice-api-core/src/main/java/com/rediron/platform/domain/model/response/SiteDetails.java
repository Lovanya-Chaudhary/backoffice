package com.rediron.platform.domain.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SiteDetails {
	
	@JsonProperty(value = "site_details")
	private List<SiteDetailResponse> siteDetails;

}
