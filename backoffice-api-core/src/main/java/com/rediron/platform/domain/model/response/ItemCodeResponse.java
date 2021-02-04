package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemCodeResponse {
	
	@JsonProperty(value = "code1_id")
	private String code1Id;
	
//	@JsonProperty(value = "code1_description")
//	private String code1Description;
	
	@JsonProperty(value = "code2_id")
	private String code2Id;
	
//	@JsonProperty(value = "code2_description")
//	private String code2Description;
	
	@JsonProperty(value = "code3_id")
	private String code3Id;
	
//	@JsonProperty(value = "code3_description")
//	private String code3Description;

}
