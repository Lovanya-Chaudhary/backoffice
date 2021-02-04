package com.rediron.platform.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SuccessResponse {
	
	@JsonProperty(value = "message")
	private String message;

}
