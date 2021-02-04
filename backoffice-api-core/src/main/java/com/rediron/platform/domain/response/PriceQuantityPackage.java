package com.rediron.platform.domain.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PriceQuantityPackage {
	
	private BigDecimal pkgPriceQty1;
	private BigDecimal pkgPriceQty2;
	private BigDecimal pkgPriceQty3;
	private BigDecimal pkgPrice1;
	private BigDecimal pkgPrice2;
	private BigDecimal pkgPrice3;

}
