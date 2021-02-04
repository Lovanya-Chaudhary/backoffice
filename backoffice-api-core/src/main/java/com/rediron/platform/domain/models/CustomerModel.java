package com.rediron.platform.domain.models;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class CustomerModel 
{	
	@NotNull
	@Size(min = 5, max = 30)
	private String name;
	
	@NotNull
	@Digits(integer=4, fraction=0)
	@Min(value=1, message="Primary Site number must be set.")
	private Integer primarySiteNo;
	
	@NotNull
	@Size(min = 1, max = 5)
    private String categoryCd;

	private Integer ownerId;
	private String customerId;
	private String firstName;
	private String middleName;
}
