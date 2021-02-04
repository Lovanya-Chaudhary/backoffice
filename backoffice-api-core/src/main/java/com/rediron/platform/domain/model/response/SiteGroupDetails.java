package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SiteGroupDetails {
	
	@JsonProperty(value = "group_id")
	private String groupId;
	
	@JsonProperty(value = "description")
	private String description;

}
