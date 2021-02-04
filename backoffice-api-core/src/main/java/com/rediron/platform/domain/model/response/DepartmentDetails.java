package com.rediron.platform.domain.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DepartmentDetails {
	
	@JsonProperty(value = "department_details")
	private List<DepartmentDetail> departmentDetail;

}
