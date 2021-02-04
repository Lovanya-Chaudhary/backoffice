package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CouponPDVDetails {
	
	@JsonProperty(value = "coupon_pdv_id")
	private String couponPDVId;
	
	@JsonProperty(value = "coupon_pdv_description")
	private String couponPDVDescription;

}
