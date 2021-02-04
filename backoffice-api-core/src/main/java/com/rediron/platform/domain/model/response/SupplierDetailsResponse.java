package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SupplierDetailsResponse {
	
	// same as vendor id
	@JsonProperty(value = "supplier_id")
	private String supplierId;
	
	// same as vendor name
	@JsonProperty(value = "supplier_description")
	private String supplierDescription;

}
