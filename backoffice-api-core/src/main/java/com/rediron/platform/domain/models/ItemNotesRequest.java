package com.rediron.platform.domain.models;

import lombok.Data;

@Data
public class ItemNotesRequest {

	private Integer skuNo;

	private Integer siteNo;

	private String groupId;

	private String typeCd;

	private String posHandling;

	private String backOfficeHandling;

	private String noteText;
	
	private Integer ownerId;

}
