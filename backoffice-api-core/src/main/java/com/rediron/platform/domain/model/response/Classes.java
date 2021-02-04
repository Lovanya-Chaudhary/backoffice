package com.rediron.platform.domain.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Classes {
	
	@JsonProperty(value = "class_detail")
	private List<ClassDetails> classDetails;

}
