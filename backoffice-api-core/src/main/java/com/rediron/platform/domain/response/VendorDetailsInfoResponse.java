package com.rediron.platform.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VendorDetailsInfoResponse {
	
	@JsonProperty(value = "vendor_id")
	private String vendorId;
	
	@JsonProperty(value = "vendor_name")
	private String vendorName;

}
