package com.rediron.platform.domain.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="CUSTOMER_CATEGORY")
public class CustomerCategoryCdEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String custCategoryCd;
	
	private String description;
	
	
	public CustomerCategoryCdEntity()
	{
		
	}
	
	public CustomerCategoryCdEntity(String custCatCd, String desc )
	{
		this.custCategoryCd = custCatCd;		
		this.description = desc;
	}

}
