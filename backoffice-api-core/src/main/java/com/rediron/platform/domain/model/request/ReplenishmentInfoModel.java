package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReplenishmentInfoModel {
	
	@JsonProperty(value = "reorder_pt")
	private BigDecimal reorderPt;
	
	@JsonProperty(value = "reorder_to")
	private BigDecimal reorderTo;	
	
	@JsonProperty(value = "is_automatically_replenished")
	private boolean isAutomaticallyReplenished;
	
	@JsonProperty(value = "is_reorder_points_locked")
	private boolean isReorderPointsLocked;
	
	@JsonProperty(value = "is_velocity_code_locked")
	private boolean isVelocityCodeLocked;
	
	@JsonProperty(value = "velocity_code")
	private String velocityCode;
	
	@JsonProperty(value = "max_order_qty")
	private BigDecimal maxOrderQty;
	
	@JsonProperty(value = "exit_velocity_code")
	private String exitVelocityCode;
	
	@JsonProperty(value = "warehouse_inner_pack")
	private BigDecimal warehouseInnerPack; // same as inner pack
	
	@JsonProperty(value = "product_start_date")
	private String productStartDate;
	
	@JsonProperty(value = "product_end_date")
	private String productEndDate;
	
	@JsonProperty(value = "last_replenishment_date")
	private String lastReplenishmentDate;
	
	@JsonProperty(value = "default_dc")
	private Integer defaultDC;

}
