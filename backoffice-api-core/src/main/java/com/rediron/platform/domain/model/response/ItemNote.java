package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemNote {
	
	@JsonProperty(value = "note_id")
	private Integer noteId;

	@JsonProperty(value = "sku_no")
	private Integer skuNo;

//	@JsonProperty(value = "site_no")
//	private Integer siteNo;

	@JsonProperty(value = "group_id")
	private String groupId;

	@JsonProperty(value = "type_code_meaning")
	private String typeCodeMeaning;

	@JsonProperty(value = "pos_handling_code_meaning")
	private String posHandlingCodeMeaning;

	@JsonProperty(value = "back_office_handling_code_meaning")
	private String backOfficeHandlingCodeMeaning;
	
	@JsonProperty(value = "type_code_value")
	private String typeCodeValue;

	@JsonProperty(value = "pos_handling_code_value")
	private String posHandlingCodeValue;

	@JsonProperty(value = "back_office_handling_code_value")
	private String backOfficeHandlingCodeValue;

	@JsonProperty(value = "note_text")
	private String noteText;
	
	@JsonProperty(value = "owner")
	private String owner;


}
