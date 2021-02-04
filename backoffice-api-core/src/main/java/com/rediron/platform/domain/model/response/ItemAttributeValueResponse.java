package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ItemAttributeValueResponse {
	
	@JsonProperty(value = "code_value")
    private String codeValue;
	
	@JsonProperty(value = "code_meaning")
    private String codeMeaning;
	
	@JsonProperty(value = "attribute_id")
    private Integer attributeId;
	
	@JsonProperty(value = "owner_id")
    private Integer ownerId;
	
	@JsonProperty(value = "lov_fl")
    private boolean lovFl;
	
	@JsonProperty(value = "name_text")
    private String nameText;
	
	@JsonProperty(value = "hint_text")
    private String hintText;

}
