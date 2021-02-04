package com.rediron.platform.domain.model.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemLookUpModel {
	
	@NotNull
	@Digits(integer=4, fraction=0)
	@Min(value=1, message="Site number must be set.")
	@JsonProperty(value = "site_no")
	private Integer siteNo;

	@JsonProperty(value = "sku_no")
	private Integer skuNo;

	@JsonProperty(value = "upc_id")
	private String UPCId;

	@JsonProperty(value = "look_up_description")
	private String lookUpDescription;

	@JsonProperty(value = "vendor_id")
	private String vendorId;
	
	@JsonProperty(value = "vendor_item")
	private String vendorItem;
	
	@JsonProperty(value = "mfg_cd")
	private String mfgCd;

	@JsonProperty(value = "custom_attribute_value1")
	private String customAttributeValue1;

	@JsonProperty(value = "custom_attribute_value2")
	private String customAttributeValue2;

	@JsonProperty(value = "custom_attribute_value3")
	private String customAttributeValue3;

	@Digits(integer=4, fraction=0)
	@JsonProperty(value = "dept_group")
	private Integer deptGroup;

	@Digits(integer=4, fraction=0)
	@JsonProperty(value = "dept_no")
	private Integer deptNo;

	@Digits(integer=4, fraction=0)
	@JsonProperty(value = "class_no")
	private Integer classNo;

	@Digits(integer=4, fraction=0)
	@JsonProperty(value = "line_no")
	private Integer lineNo;

}
