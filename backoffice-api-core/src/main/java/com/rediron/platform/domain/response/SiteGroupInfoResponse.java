package com.rediron.platform.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SiteGroupInfoResponse {
	
	@JsonProperty(value = "group_id")
	private String groupId;
	
	@JsonProperty(value = "description")
	private String description;
	
	@JsonProperty(value = "type")
	private String type;    
	
	@JsonProperty(value = "parent_group_id")
    private String parentGroupId;
	
	@JsonProperty(value = "type_name")
	private String typeName;
	
	@JsonProperty(value = "group_owner")
	private String groupOwner;
	
	@JsonProperty(value = "alt_group_id")
	private String altGroupId;

}
