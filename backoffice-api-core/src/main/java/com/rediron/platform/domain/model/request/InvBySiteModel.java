package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InvBySiteModel {
	
	// PriceLevelsModel	
	@JsonProperty(value = "pricing_model")
	private PricingModel pricingModel;
	
	@JsonProperty(value = "halo_gm")
	private BigDecimal haloGm;
	
	@JsonProperty(value = "lalo_gm")
	private BigDecimal laloGm;
	
	@JsonProperty(value = "coupon_pdv_id")
	private String couponPDVId;
	
	@JsonProperty(value = "is_authorized")
	private boolean isAuthorized;// Db field name
	
	@JsonProperty(value = "is_discount_allowed")
	private boolean isDiscountAllowed;// nonDiscountFl
	
	@JsonProperty(value = "is_price_change_allowed")
	private boolean isPriceChangeAllowed;// Db field name
	
	@JsonProperty(value = "is_discontinued")
	private boolean isDiscontinued; // discontinuedStFl
	
	// QP
	@JsonProperty(value = "quantity_pricing_model")
	private QuantityPricingModel quantityPricingModel;
	
	@JsonProperty(value = "suggested_retail_info_model")
	private SuggestedRetailInfoModel suggestedRetailInfoModel;
	
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
