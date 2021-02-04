package com.rediron.platform.domain.models;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ItemLookUpModel {
	
	@NotNull
	@Digits(integer=4, fraction=0)
	@Min(value=1, message="Site number must be set.")
	private Integer siteNo;

	private Integer skuNo;

	private String UPCId;

	private String lookUpDescription;

	private String vendorId;
	
	private String vendorItem;
	
	private String mfgCd;

	private String customAttributeValue1;

	private String customAttributeValue2;

	private String customAttributeValue3;

	@Digits(integer=4, fraction=0)
	private Integer deptGroup;

	@Digits(integer=4, fraction=0)
	private Integer deptNo;

	@Digits(integer=4, fraction=0)
	private Integer classNo;

	@Digits(integer=4, fraction=0)
	private Integer lineNo;

}
