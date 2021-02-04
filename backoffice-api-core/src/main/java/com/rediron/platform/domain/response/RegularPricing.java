package com.rediron.platform.domain.response;

import lombok.Data;

@Data
public class RegularPricing {
	
	private Pricing pricing;
	private PriceQuantityPackage priceQuantityPackage;
	private PricingQuantity pricingQuantity;
	
	private String itemizerStatusId;
	private String skuStatusId;

}
