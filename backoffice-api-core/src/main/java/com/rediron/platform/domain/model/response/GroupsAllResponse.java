package com.rediron.platform.domain.model.response;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GroupsAllResponse {
	
	@JsonProperty(value = "group_id")
	private String groupId;
	
	@JsonProperty(value = "description")
    private String description;
	
	@JsonProperty(value = "level_no")
    private Integer levelNo;
	
	@JsonProperty(value = "level_manager")
    private String levelManager;
	
	@JsonProperty(value = "type")
    private String type;
	
	@JsonProperty(value = "parent_group_id")
    private String parentGroupId;
	
	@JsonProperty(value = "date_created")
    private Date dateCreated;
	
	@JsonProperty(value = "user_created")
    private String userCreated;
	
	@JsonProperty(value = "date_modified")
    private Date dateModified;
	
	@JsonProperty(value = "user_modified")
    private String userModified;
	
	@JsonProperty(value = "type_name")
    private String typeName;
	
	@JsonProperty(value = "group_owner")
    private String groupOwner;
	
	@JsonProperty(value = "alt_group_id")
    private String altGroupId;

}
