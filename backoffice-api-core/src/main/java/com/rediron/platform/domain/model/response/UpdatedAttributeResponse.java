package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UpdatedAttributeResponse {
	
	@JsonProperty(value = "attribute_id")
	private Integer attributeId;
	
	@JsonProperty(value = "hint_text")
	private String hintText;
	
	@JsonProperty(value = "name_text")
	private String nameText;
	
	@JsonProperty(value = "lov_fl")
	private boolean lovFl;

}
