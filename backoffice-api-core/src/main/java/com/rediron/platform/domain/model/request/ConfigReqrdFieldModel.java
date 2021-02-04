package com.rediron.platform.domain.model.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConfigReqrdFieldModel {
	
	@NotNull
	@JsonProperty("form_name")
	private String formName;
	
	@NotNull
	@JsonProperty("block_name")
	private String blockName;
	
	@NotNull
	@JsonProperty("item_name")
	private String itemName;
	
	@JsonProperty("default_value")
	private String defaultValue;
	
	@NotNull
	@JsonProperty("not_null_fl")
	private String notNullFl;

}
