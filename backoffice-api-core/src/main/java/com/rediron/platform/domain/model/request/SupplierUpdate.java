package com.rediron.platform.domain.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SupplierUpdate {
	
	@JsonProperty(value = "new_supplier")
	private String newSupplier;
	
	@JsonProperty(value = "previous_supplier")
	private String previousSupplier;

}
