package com.rediron.platform.domain.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemCreationDefaultsHdr {
	
	@JsonProperty(value = "item_creation_id")
	private Long itemCreationId;

	@JsonProperty(value = "item_type_cd")
	private String itemTypeCd;
	
	@JsonProperty(value = "display_label")
	private String displayLabel;
	
	@JsonProperty(value = "display_fl")
	private String displayFl;
	
	@JsonProperty(value = "function_cd")
	private String functionCd;
	
	@JsonProperty(value = "hint_text")
	private String hintText;
	
	@JsonProperty(value = "user_created")
	private String userCreated;
	
	@JsonProperty(value = "date_created")
	private Date dateCreated;
	
	@JsonProperty(value = "user_modified")
	private String userModified;
	
	@JsonProperty(value = "date_modified")
	private Date dateModified;
	
	@JsonProperty(value = "active_fl")
	private String activeFl;
	
	@JsonProperty(value = "allow_attributes_fl")
	private String allowAttributesFl;
}
