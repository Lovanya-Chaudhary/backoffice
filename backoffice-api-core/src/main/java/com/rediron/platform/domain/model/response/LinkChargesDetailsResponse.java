package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LinkChargesDetailsResponse {
	
	@JsonProperty(value = "link_no")
	private int bottleLinkNo;
	
	@JsonProperty(value = "link_description")
	private String bottleLinkDescription;

}
