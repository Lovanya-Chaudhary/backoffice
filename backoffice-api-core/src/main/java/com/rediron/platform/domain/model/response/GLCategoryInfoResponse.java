package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GLCategoryInfoResponse {
	
	@JsonProperty(value = "gl_cat_description")
	private String glCatDescription;
	
	@JsonProperty(value = "gl_cat_id")
	private Integer glCatId;

}
