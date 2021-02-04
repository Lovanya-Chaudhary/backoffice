package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AttributeDetail {
	
	@JsonProperty(value = "attribute_id")
	private Integer attributeId;
	
	@JsonProperty(value = "name_text")
	private String nameText;
	
	@JsonProperty(value = "active_fl")
	private String activeFl;
	
	@JsonProperty(value = "lov_fl")
    private String lovFl;
	
	@JsonProperty(value = "type_cd")
    private String typeCd;
	
	@JsonProperty(value = "hint_text")
    private String hintText;

}
