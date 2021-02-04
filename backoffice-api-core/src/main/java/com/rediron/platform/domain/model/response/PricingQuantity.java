package com.rediron.platform.domain.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PricingQuantity {
	
	@JsonProperty(value = "price_qty1")
	private BigDecimal priceQty1;
	
	@JsonProperty(value = "price_qty2")
	private BigDecimal priceQty2;
	
	@JsonProperty(value = "price_qty3")
	private BigDecimal priceQty3;
	
	@JsonProperty(value = "price_qty4")
	private BigDecimal priceQty4;
	
	@JsonProperty(value = "price_qty5")
	private BigDecimal priceQty5;
	
	@JsonProperty(value = "price_qty6")
	private BigDecimal priceQty6;
	
	@JsonProperty(value = "price_qty7")
	private BigDecimal priceQty7;
	
	@JsonProperty(value = "price_qty8")
	private BigDecimal priceQty8;
	
	@JsonProperty(value = "price_qty9")
	private BigDecimal priceQty9;
	
	@JsonProperty(value = "price_qty10")
	private BigDecimal priceQty10;
	
}
