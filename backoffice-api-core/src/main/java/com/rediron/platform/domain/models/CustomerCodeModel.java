package com.rediron.platform.domain.models;

import lombok.Data;

@Data
public class CustomerCodeModel {

	private String customerCode;
	
	public CustomerCodeModel(String custCd )
	{
		this.customerCode = custCd;
	}
}
