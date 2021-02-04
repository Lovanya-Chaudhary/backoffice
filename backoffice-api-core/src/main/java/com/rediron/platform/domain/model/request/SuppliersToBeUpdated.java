package com.rediron.platform.domain.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SuppliersToBeUpdated {
	
	@JsonProperty(value = "update_supplier")
	List<SupplierUpdate> updateSuppliers;

}
