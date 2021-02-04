package com.rediron.platform.domain.model.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InventorySearchResults {
	
	@JsonProperty(value = "inventory_search_result")
	List<InventorySearchResult> inventorySearchResult;
}
