package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClassDetails {
	
	@JsonProperty(value = "class_no")
	private Integer classNo;
	
	@JsonProperty(value = "class_description")
	private String description;

}
