package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConfigReqrdFieldResponse {
	
	@JsonProperty("form_name")
	private String formName;

	@JsonProperty("block_name")
	private String blockName;

	@JsonProperty("item_name")
	private String itemName;
	
	@JsonProperty("not_null_fl")
	private String notNullFl;
	
	@JsonProperty("default_value")
	private String defaultValue;
	
	@JsonProperty("label_text")
	private String labelTxt;

}
