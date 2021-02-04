package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StatusDetailsResponse {
	
	@JsonProperty(value = "status_id")
	private String statusId;
	
	@JsonProperty(value = "status_description")
	private String statusDescription;

}
