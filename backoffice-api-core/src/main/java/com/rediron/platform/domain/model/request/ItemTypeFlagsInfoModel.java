package com.rediron.platform.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemTypeFlagsInfoModel {
	
	// itemTypeFlags
	@JsonProperty(value = "is_non_revenue_item")
	private boolean isNonRevenueItem;// Db field name mapping
	
	@JsonProperty(value = "is_store_cpn_fl")
	private boolean isStoreCpnFl;
	
	@JsonProperty(value = "is_non_inventory_fl")
	private boolean isNonInventoryFl;
	
	@JsonProperty(value = "is_sub_inv_fl")
	private boolean isSubInvFl;
	
	@JsonProperty(value = "is_supply_item_fl")
	private boolean isSupplyItemFl;
	
	@JsonProperty(value = "is_core_fl")
	private boolean isCoreFl;
	
	@JsonProperty(value = "is_flexible_item_fl")
	private boolean isFlexibleItemFl;

}
