package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CommissionCodeDetailsResponse {
	
	@JsonProperty(value = "commission_code")
	private int commissionCode;
	
	@JsonProperty(value = "commission_description")
	private String commissionDescription;

	@JsonProperty(value = "commission_type_code")
	private String commissionTypeCode;

}
