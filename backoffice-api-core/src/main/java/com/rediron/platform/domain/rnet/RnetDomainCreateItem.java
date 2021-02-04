package com.rediron.platform.domain.rnet;

import com.tomax.api.RNetApiContexts;
import com.tomax.api.UserIdentity;
import com.tomax.inventory.api.InventoryAPI;
import com.tomax.inventory.dto.InvbysitDTO;
import com.tomax.inventory.dto.InvtoryDTO;
import com.tomax.inventory.dto.ItemupcDTO;
import com.tomax.inventory.dto.custom.CreateItemInfoDTO;
import com.tomax.vendor.api.VendorAPI;
import com.tomax.vendor.dto.VendorItemDTO;

public class RnetDomainCreateItem {
	
	private static InventoryAPI inventoryAPI = RNetApiContexts.hq.getStateless(InventoryAPI.class);
	private static VendorAPI vendorAPI = RNetApiContexts.hq.getStateless(VendorAPI.class);
	
	public Integer createNewItem(UserIdentity domainUser, CreateItemInfoDTO createItemInfoDTO) {		
		InvtoryDTO invtoryDto = inventoryAPI.createItem(domainUser, createItemInfoDTO.getInvtoryDTO());
		InvbysitDTO invbysitDto = inventoryAPI.createInvbysit(domainUser, createItemInfoDTO.getInvBySitDTO());
		ItemupcDTO itemUpcDto = inventoryAPI.createUPC(domainUser, createItemInfoDTO.getItemUpcDTO());
		VendorItemDTO vendorItemDto = vendorAPI.createVendorItem(domainUser, createItemInfoDTO.getVendorItemDTO());
		return invtoryDto.getSkuNo();		
	}

}
