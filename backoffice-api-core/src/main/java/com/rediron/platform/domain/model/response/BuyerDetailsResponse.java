package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BuyerDetailsResponse {
	
	@JsonProperty(value = "employee_id")
	private String employeeId;
	
	@JsonProperty(value = "full_name")
	private String fullName;

}
