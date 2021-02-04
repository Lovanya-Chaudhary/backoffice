package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Line {
	
	@JsonProperty(value = "line_no")
	private Integer lineNo;
	
	@JsonProperty(value = "line_description")
	private String description;
	
}
