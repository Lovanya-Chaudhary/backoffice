package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InventoryGlobal {
	
	@JsonProperty(value = "inventory")
	private Inventory inventory;
	
	@JsonProperty(value = "vendor_item")
	private String vendorItem;
	
	@JsonProperty(value = "upc_id")
	private String upcId;
	
	@JsonProperty(value = "department_group_no")
	private Integer deptGrpNo;
	
	@JsonProperty(value = "kit_type_cd")
	private String kitTypeCd;
	
	@JsonProperty(value = "kit_sku_no")
	private Integer kitSkuNo;
	
	@JsonProperty(value = "department_description")
	private String deptDesc;
	
	@JsonProperty(value = "department_group_description")
	private String deptGrpDesc;
	
	@JsonProperty(value = "line_description")
	private String lineDesc;
	
	@JsonProperty(value = "class_description")
	private String classDesc;
	
	@JsonProperty(value = "gl_cat_id_description")
	private String glCatIdDesc;
	
	@JsonProperty(value = "certificate_id")
	private Integer certificateId;
	
	@JsonProperty(value = "reason_cd")
	private Integer reasonCd;
	
	@JsonProperty(value = "regional_super_sede")
	private Boolean regionalSuperSede;
	
	@JsonProperty(value = "global_super_sede")
	private Boolean globalSuperSede;
	
	@JsonProperty(value = "has_multiple_vendor_items")
	private Boolean hasMultipleVendorItems = false;
	
	@JsonProperty(value = "kit_component_fl")
	private Boolean kitComponentFl = false;
	
	@JsonProperty(value = "kit_hdr_fl")
	private Boolean kitHdrFl = false;
	
	@JsonProperty(value = "daily_sales_present")
	private Boolean dailySalesPresent = false;
	
	@JsonProperty(value = "daily_receiving_present")
	private Boolean dailyReceivingPresent = false;
	
	@JsonProperty(value = "po_detail_present")
	private Boolean poDetailPresent = false;

}
