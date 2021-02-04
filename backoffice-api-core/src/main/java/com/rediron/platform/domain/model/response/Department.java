package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Department {
	
	@JsonProperty(value = "department_no")
	private Integer deptNo;
	
	@JsonProperty(value = "department_description")
	private String description;

}
