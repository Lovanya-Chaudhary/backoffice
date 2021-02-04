package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class VendorItemModel {
	
	@JsonProperty(value = "pack_cost")
	private BigDecimal packCost;
	
	@JsonProperty(value = "pack_qty")
	private BigDecimal packQty;
	
	@JsonProperty(value = "pack_um")
	private String packUm;
	
	@JsonProperty(value = "vendor_id")
	private String vendorId;
	
	@JsonProperty(value = "vendor_item")
	private String vendorItem;
	
	// required additional fields
	@JsonProperty(value = "master_pack_qty")
	private BigDecimal masterPackQty;
	
	// lead time 
	@JsonProperty(value = "lead_time")
	private Integer leadTime;
	// eoq
	@JsonProperty(value = "eoq")
	private BigDecimal eoq;
	
	// order availability status
	@JsonProperty(value = "order_availability_status")
	private String orderAvailabilityStatus;
	// active date current date
	
	@JsonProperty(value = "pack_weight")
	private BigDecimal packWeight;
	
	@JsonProperty(value = "pack_cube")
	private BigDecimal packCube;
	
	@JsonProperty(value = "primary_vendor")
	private boolean primaryVendor;
	
	@JsonProperty(value = "main_vendor")
	private boolean mainVendor;

}
