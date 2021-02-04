package com.rediron.platform.domain.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Lines {
	
	@JsonProperty(value = "lines")
	private List<Line> lines;

}
