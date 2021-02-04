package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TareDetailsResponse {
	
	@JsonProperty(value = "tare_no")
	private String tareNo;
	
	@JsonProperty(value = "tare_description")
	private String tareDescription;
}
