package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VendorItemFieldsToUpdate {
	
//	@JsonProperty(value = "copy_from_sit")
//	private Integer copyFromSit;
	
	@NotNull
	@JsonProperty(value = "copy_from_vendor_item")
	private String copyFromVendorItem;
	
	@NotNull
	@JsonProperty(value = "copy_from_pack_qty")
	private BigDecimal copyFromPackQty;
	
//	@JsonProperty(value = "copy_to_sit_grp")
//	private String copyToSitGrp;
	
	@NotNull
	@JsonProperty(value = "vendor_id")
	private String vendorId;
	
//	@JsonProperty(value = "sku_no")
//	private Integer skuNo;	
	
	@JsonProperty(value = "primary")
	private Boolean primary;
	
	@JsonProperty(value = "main")
	private Boolean main;
	
	@JsonProperty(value = "vendor_item")
	private String vendorItem;
	
	@JsonProperty(value = "pack_um")
	private String packUm;
	
	@JsonProperty(value = "pack_qty")
	private BigDecimal packQty;
	
	@JsonProperty(value = "master_pack_qty")
	private BigDecimal masterPackQty;
	
	@JsonProperty(value = "lead_time")
	private Integer leadTime;
	
	@JsonProperty(value = "eoq")
	private BigDecimal eoq;
	
	@JsonProperty(value = "oa_status")
	private String oaStatus;
	
	@JsonProperty(value = "pack_cost")
	private BigDecimal packCost;
	
	@JsonProperty(value = "update_primary_fl")
	private Boolean updatePrimaryFl = false;

	@JsonProperty(value = "update_main_fl")
	private Boolean updateMainFl = false;
	
	@JsonProperty(value = "update_vendor_item_fl")
	private Boolean updateVendorItemFl = false;
	
	@JsonProperty(value = "update_pack_um_fl")
	private Boolean updatePackUmFl = false;
	
	@JsonProperty(value = "update_pack_qty_fl")
	private Boolean updatePackQtyFl = false;
	
	@JsonProperty(value = "update_master_pack_qty_fl")
	private Boolean updateMasterPackQtyFl = false;
	
	@JsonProperty(value = "update_lead_time_fl")
	private Boolean updateLeadTimeFl = false;
	
	@JsonProperty(value = "update_eoq_fl")
	private Boolean updateEoqFl = false;
	
	@JsonProperty(value = "update_oa_status_fl")
	private Boolean updateOaStatusFl = false;
	
	@JsonProperty(value = "update_pack_cost_fl")
	private Boolean updatePackCostFl = false;
}
