package com.rediron.platform.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemNotesModel {

	@JsonProperty(value = "sku_no")
	private Integer skuNo;

	@JsonProperty(value = "site_no")
	private Integer siteNo;

	@JsonProperty(value = "group_id")
	private String groupId;

	@JsonProperty(value = "type_cd")
	private String typeCd;

	@JsonProperty(value = "pos_handling")
	private String posHandling;

	@JsonProperty(value = "backoffice_handling")
	private String backOfficeHandling;

	@JsonProperty(value = "note_text")
	private String noteText;
	
	@JsonProperty(value = "owner_id")
	private Integer ownerId;

}
