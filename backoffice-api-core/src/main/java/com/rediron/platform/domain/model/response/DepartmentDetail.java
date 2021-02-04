package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DepartmentDetail {
	
	@JsonProperty(value = "department_no")
	private Integer deptNo;
	
	@JsonProperty(value = "department_description")
	private String description;
	
	@JsonProperty(value = "department_group_no")
	private Integer deptGrpNo;

}
