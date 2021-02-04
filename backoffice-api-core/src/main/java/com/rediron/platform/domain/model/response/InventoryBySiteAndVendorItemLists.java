package com.rediron.platform.domain.model.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InventoryBySiteAndVendorItemLists {
	
	@JsonProperty(value = "invbysit")
	private Invbysit invbysit;
	
	@JsonProperty(value = "vendor_items")
	private List<VendorItemResponse> vendorItems;

}
