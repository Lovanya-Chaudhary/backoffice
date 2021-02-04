package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PromoDetails {
	
	@JsonProperty(value = "pricing")
	private Pricing pricing;
	
	@JsonProperty(value = "price_quantity_package")
	private PriceQuantityPackage priceQuantityPackage;
	
	@JsonProperty(value = "pricing_quantity")
	private PricingQuantity pricingQuantity;
	
	@JsonProperty(value = "promo_status")
	private PromoStatus promoStatus;

}
