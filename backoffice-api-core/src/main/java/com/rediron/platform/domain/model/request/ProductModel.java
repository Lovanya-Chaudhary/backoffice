package com.rediron.platform.domain.model.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductModel {
	 
	 @NotNull
	 @Digits(integer=4, fraction=0)
	 @JsonProperty(value = "department_no")
	 private Integer deptNo;
	
	 @NotNull
	 @Digits(integer=4, fraction=0)
	 @JsonProperty(value = "class_no")
	 private Integer classNo;
	 	 
	 @NotNull
	 @Digits(integer=4, fraction=0)
	 @JsonProperty(value = "line_no")
	 private Integer lineNo;
	 
}
