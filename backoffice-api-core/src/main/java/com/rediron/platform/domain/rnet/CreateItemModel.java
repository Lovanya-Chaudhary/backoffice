package com.rediron.platform.domain.rnet;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rediron.platform.domain.model.request.InvBySiteModel;
import com.rediron.platform.domain.model.request.InventoryModel;
import com.rediron.platform.domain.model.request.ItemUpcModel;
import com.rediron.platform.domain.model.request.VendorItemModel;

import lombok.Data;

@Data
public class CreateItemModel {

	@JsonProperty(value = "vendor_name")
    private String vendorName;
    
	@JsonProperty(value = "department_group_no")
    @Digits(integer=4, fraction=0)
    private Integer deptGrpNo;
    
	@JsonProperty(value = "sku_no")
    private Integer skuNo;
    
    // use notblank instead
    @NotEmpty
    @JsonProperty(value = "site_group_id")
    private String siteGroup;
 
    @Valid
    @JsonProperty(value = "inventory_model")
    private InventoryModel inventoryModel;
    
    @Valid
    @JsonProperty(value = "item_upc_models")
    private List<ItemUpcModel> listOfItemUpcModel;
    
//    private List<ItemAttributeValueModel> itemAttributeValueModelList;
    @JsonProperty(value = "vendor_item_models")
    private List<VendorItemModel> listOfVendorItemModel;
    
    @JsonProperty(value = "invbysite_model")
    private InvBySiteModel invBySiteModel;
}
