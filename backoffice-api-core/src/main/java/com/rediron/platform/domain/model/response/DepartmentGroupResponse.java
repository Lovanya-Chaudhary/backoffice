package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DepartmentGroupResponse {
	
	@JsonProperty(value = "department_group_no")
	private Integer deptGrpNo;
	
	@JsonProperty(value = "department_group_description")
	private String description;
	
}
