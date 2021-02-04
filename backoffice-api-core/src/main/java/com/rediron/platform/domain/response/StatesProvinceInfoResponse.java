package com.rediron.platform.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StatesProvinceInfoResponse {
	
	@JsonProperty(value = "full_name")
    private String fullName;
	
	@JsonProperty(value = "short_name")
    private String shortName;

}
