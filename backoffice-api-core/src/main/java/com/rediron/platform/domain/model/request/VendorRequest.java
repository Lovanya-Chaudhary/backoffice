package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VendorRequest {
	
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
	
	@JsonProperty(value = "master_pack_qty")
	private BigDecimal masterPackQty;
	
	@JsonProperty(value = "lead_time")
	private Integer leadTime;
	
	@JsonProperty(value = "eoq")
	private BigDecimal eoq;
	
	@JsonProperty(value = "oa_status")
	private String orderAvailabilityStatus;
	
	@JsonProperty(value = "pack_weight")
	private BigDecimal packWeight;
	
	@JsonProperty(value = "pack_cube")
	private BigDecimal packCube;
	
	// it acts as the primary flag for the vendors
	@JsonProperty(value = "vendor_type")
	private String vendorType;
	
	@JsonProperty(value = "main_flag")
	private String mainFlag;

}
