package com.rediron.platform.domain.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SKULinkDetails {
	
	private String description;
	private boolean isSerializedItem;
	private Integer linkSkuNumber;
	private Integer deptNo;
	private boolean isNonInventory;
	private boolean isSupplyItem;
	private boolean isMeasuredItem;// WeighedItemFl measuredItem 
	private Integer skuNumber;
	
	private BigDecimal qtyRsvd;
	private BigDecimal qtyOnHand;
	
	private BigDecimal qtyOnOrd;
	private BigDecimal qtyOnBackord;
	private BigDecimal qtyInTransit;
	// additional details qty_on_ord + qty_on_backord + qty_in_transit + qty_on_hand + qty_rsvd

}
