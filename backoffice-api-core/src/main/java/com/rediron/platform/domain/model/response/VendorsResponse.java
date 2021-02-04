package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VendorsResponse {
	
	@JsonProperty(value = "vendor_name")
	private String vendorName;
	
	@JsonProperty(value = "vendor_id")
	private String vendorId;

}
