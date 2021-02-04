package com.rediron.platform.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HazardousInfoModel {
	
	// hazardous
	@JsonProperty(value = "haz_class_id")
	private String hazClassId;
	
	@JsonProperty(value = "haz_un_number_id")
	private String hazUnNumberId;

}
