package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpdateInvbysitRequest {
	
	@JsonProperty(value = "additional_tax")
	private BigDecimal additionalTax;
	
	@JsonProperty(value = "halo_gm")
	private BigDecimal haloGm;
	
	@JsonProperty(value = "lalo_gm")
	private BigDecimal laloGm;
	
	@JsonProperty(value = "coupon_pdv_id")
	private String couponPDVId;
	
	@JsonProperty(value = "authorized")
	private boolean authorized;// Db field name
	
	@JsonProperty(value = "discount_allowed")
	private boolean discountAllowed;// nonDiscountFl
	
	@JsonProperty(value = "price_change_allowed")
	private boolean priceChangeAllowed;// Db field name
	
	@JsonProperty(value = "discontinued")
	private boolean discontinued; // discontinuedStFl
	
	@JsonProperty(value = "status_info_model")
	private StatusInfoModel statusInfoModel;
	
	@JsonProperty(value = "replenishment_info_model")
	private ReplenishmentInfoModel replenishmentInfoModel;
	
	@JsonProperty(value = "commission_cd")
	private Integer commissionCd;
	
	@JsonProperty(value = "tare_table_no")
	private String tareTableNo;
	
	// taking as integer need endpoint for LOV
	@JsonProperty(value = "link_charges")
	private Integer linkCharges;

}
