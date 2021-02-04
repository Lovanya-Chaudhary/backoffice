package com.rediron.platform.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MenuItem {
	
	@JsonProperty("menu_text")
	private String menuText;
	
	@JsonProperty("menu_seq")
	private int menuSeq;
	
	@JsonProperty("launch_seq")
	private int launchSeq;
	
	@JsonProperty("menu_type")
	private String menuType;
	
	@JsonProperty("object_id")
	private int objectId;
	
	@JsonProperty("parameters")
	private String parameters;
	
	@JsonProperty("object_name")
	private String objectName;
	
	@JsonProperty("param_req_fl")
	private String paramReqFl;
}
