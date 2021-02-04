package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StatusInfoDetailsResponse {
	
	@JsonProperty(value = "status_description")
	private String statusDescription;
	
	@JsonProperty(value = "status_value")
	private String statusValue;

}
