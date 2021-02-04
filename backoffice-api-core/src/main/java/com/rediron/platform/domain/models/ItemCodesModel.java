package com.rediron.platform.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemCodesModel {
	
	@JsonProperty(value = "code1_id")
	private String code1Id;
	
	@JsonProperty(value = "code2_id")
	private String code2Id;
	
	@JsonProperty(value = "code3_id")
	private String code3Id;
}
