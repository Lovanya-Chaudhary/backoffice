package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RegularPricing {
	
	@JsonProperty(value = "pricing")
	private Pricing pricing;
	
	@JsonProperty(value = "price_quantity_package")
	private PriceQuantityPackage priceQuantityPackage;
	
	@JsonProperty(value = "pricing_quantity")
	private PricingQuantity pricingQuantity;
	
	@JsonProperty(value = "itemizer_status_id")
	private String itemizerStatusId;
	
	@JsonProperty(value = "sku_status_id")
	private String skuStatusId;

}
