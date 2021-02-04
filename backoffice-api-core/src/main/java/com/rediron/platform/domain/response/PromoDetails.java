package com.rediron.platform.domain.response;

import lombok.Data;

@Data
public class PromoDetails {
	
	private Pricing pricing;
	private PriceQuantityPackage priceQuantityPackage;
	private PricingQuantity pricingQuantity;
	private PromoStatus promoStatus;

}
