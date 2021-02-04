package com.rediron.platform.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class KitInfoResponse {
	
	@JsonProperty(value = "primary_vendor_item")
	private String primaryVendorItem;
	
	@JsonProperty(value = "kit_member_fl")
	private String kitMemberFl;
	
	@JsonProperty(value = "mfg_cd")
	private String mfgCd;
	
	@JsonProperty(value = "upc_id")
	private String upcId;
	
	@JsonProperty(value = "kit_type_cd")
	private String kitTypeCd;
	
	@JsonProperty(value = "kit_sku_no")
	private Integer kitSkuNo;
	
	@JsonProperty(value = "kit_component_fl")
	private boolean kitComponentFl;
	
	@JsonProperty(value = "kit_hdr_fl")
	private boolean kitHdrFl;

}
