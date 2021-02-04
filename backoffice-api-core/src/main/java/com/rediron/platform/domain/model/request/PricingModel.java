package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PricingModel {
	
	@JsonProperty(value = "price1")
	private BigDecimal price1;
	
	@JsonProperty(value = "price2")
	private BigDecimal price2;
	
	@JsonProperty(value = "price3")
	private BigDecimal price3;
	
	@JsonProperty(value = "price4")
	private BigDecimal price4;
	
	@JsonProperty(value = "price5")
	private BigDecimal price5;
	
	@JsonProperty(value = "price6")
	private BigDecimal price6;
	
	@JsonProperty(value = "price7")
	private BigDecimal price7;
	
	@JsonProperty(value = "price8")
	private BigDecimal price8;
	
	@JsonProperty(value = "price9")
	private BigDecimal price9;
	
	@JsonProperty(value = "price10")
	private BigDecimal price10;
	
	@JsonProperty(value = "msrp_qty")
	private Integer msrpQty; // msrp field has same value both are linked
	
	@JsonProperty(value = "msrp_price")
	private BigDecimal msrpPrice; // last field in Price levels has same value both fields are linked
	
	@JsonProperty(value = "additional_tax")
	private BigDecimal additionalTax;
	
}
