package com.rediron.platform.domain.models;

import lombok.Data;

@Data
public class CustomerCategoryModel 
{
	private String custCategoryCd;
	private String description;
	
	public CustomerCategoryModel(String custCatCd, String desc )
	{
		this.custCategoryCd = custCatCd;
		this.description = desc;
	}
}
