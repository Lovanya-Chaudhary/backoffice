package com.rediron.platform.domain.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VendorItems {
	
	@JsonProperty(value = "vendor_item")
	List<VendorItemResponse> vendorItems;

}
