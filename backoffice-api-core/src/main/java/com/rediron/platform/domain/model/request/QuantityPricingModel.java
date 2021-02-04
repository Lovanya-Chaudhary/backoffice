package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuantityPricingModel {
	
	@JsonProperty(value = "pkg_price_qty1")
	private Integer pkgPriceQty1;
	
	@JsonProperty(value = "pkg_price1")
	private BigDecimal pkgPrice1;
	
	@JsonProperty(value = "pkg_price_qty2")
	private Integer pkgPriceQty2;// pkgPriceQty2 > pkgPriceQty1
	
	@JsonProperty(value = "pkg_price2")
	private BigDecimal pkgPrice2;
	
	@JsonProperty(value = "pkg_price_qty3")
	private Integer pkgPriceQty3;// pkgPriceQty3 > pkgPriceQty2
	
	@JsonProperty(value = "pkg_price3")
	private BigDecimal pkgPrice3;

}
