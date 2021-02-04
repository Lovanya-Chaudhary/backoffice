package com.rediron.platform.domain.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Sites {
	
	@JsonProperty(value = "site_no")
	private List<SiteResponse> sites;

}
