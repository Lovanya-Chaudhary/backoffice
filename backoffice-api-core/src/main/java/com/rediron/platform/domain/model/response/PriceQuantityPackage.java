package com.rediron.platform.domain.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PriceQuantityPackage {
	
	@JsonProperty(value = "pkg_price_qty1")
	private BigDecimal pkgPriceQty1;
	
	@JsonProperty(value = "pkg_price_qty2")
	private BigDecimal pkgPriceQty2;
	
	@JsonProperty(value = "pkg_price_qty3")
	private BigDecimal pkgPriceQty3;
	
	@JsonProperty(value = "pkg_price1")
	private BigDecimal pkgPrice1;
	
	@JsonProperty(value = "pkg_price2")
	private BigDecimal pkgPrice2;
	
	@JsonProperty(value = "pkg_price3")
	private BigDecimal pkgPrice3;

}
