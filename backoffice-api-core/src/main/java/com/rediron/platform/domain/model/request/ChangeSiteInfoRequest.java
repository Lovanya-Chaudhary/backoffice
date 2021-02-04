package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ChangeSiteInfoRequest {
	
	@JsonProperty(value = "additional_tax")
	private BigDecimal additionalTax;
	
	@JsonProperty(value = "reorder_to")
	private BigDecimal reorderTo;
	
	@JsonProperty(value = "reorder_pt")
	private BigDecimal reorderPt;
	
	@JsonProperty(value = "discount_allowed")
	private boolean discountAllowed;
	
	@JsonProperty(value = "allow_price_change")
	private boolean allowPriceChange;
	
	@JsonProperty(value = "discontinued")
	private boolean discontinued;
	
	@JsonProperty(value = "authorized")
	private boolean authorized;
	
	@JsonProperty(value = "commission_code")
	private int commissionCode;
	
	@JsonProperty(value = "link_no")
	private int bottleLinkNo;
	
	@JsonProperty(value = "tare_no")
	private String tareNo;
	
	@JsonProperty(value = "default_dc")
	private int defaultDC;
	
	// lock forecasting flag
	@JsonProperty(value = "reorder_points_locked")
	private boolean reorderPointsLocked;
	
	// velocity data flag
	@JsonProperty(value = "velocity_code_locked")
	private boolean velocityCodeLocked;
	
	@JsonProperty(value = "automatically_replenished")
	private boolean automaticallyReplenished;
	
	@JsonProperty(value = "velocity_code")
	private String velocityCode;
	
	@JsonProperty(value = "exit_velocity_code")
	private String exitVelocityCode;
	
	@JsonProperty(value = "max_order_qty")
	private BigDecimal maxOrderQty;
	
	 // same as inner pack qty
	@JsonProperty(value = "warehouse_inner_pack")
	private BigDecimal warehouseInnerPack;
	
	@JsonProperty(value = "status_info_model")
	private StatusInfoModel statusInfoModel;

}
