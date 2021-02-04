package com.rediron.platform.domain.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SKULinkDetails {
	
	@JsonProperty(value = "description")
	private String description;
	
	@JsonProperty(value = "is_serialized_item")
	private boolean isSerializedItem;
	
	@JsonProperty(value = "link_sku_no")
	private Integer linkSkuNumber;
	
	@JsonProperty(value = "department_no")
	private Integer deptNo;
	
	@JsonProperty(value = "is_non_inventory")
	private boolean isNonInventory;
	
	@JsonProperty(value = "is_supply_item")
	private boolean isSupplyItem;
	
	@JsonProperty(value = "is_measured_item")
	private boolean isMeasuredItem;// WeighedItemFl measuredItem 
	
	@JsonProperty(value = "sku_no")
	private Integer skuNumber;
	
	@JsonProperty(value = "qty_rsvd")
	private BigDecimal qtyRsvd;
	
	@JsonProperty(value = "qty_on_hand")
	private BigDecimal qtyOnHand;
	
	@JsonProperty(value = "qty_on_ord")
	private BigDecimal qtyOnOrd;
	
	@JsonProperty(value = "qty_on_backord")
	private BigDecimal qtyOnBackord;
	
	@JsonProperty(value = "qty_in_transit")
	private BigDecimal qtyInTransit;
	// additional details qty_on_ord + qty_on_backord + qty_in_transit + qty_on_hand + qty_rsvd

}
