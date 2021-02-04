package com.rediron.platform.domain.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StatusClassHdr {
	
	@JsonProperty(value = "status_id")
	private String statusId;
	
	@JsonProperty(value = "description")
	private String description;
	
	@JsonProperty(value = "progdef_no")
	private Integer progdefNo;
	
	@JsonProperty(value = "status_type")
	private String statusType;
	
	@JsonProperty(value = "cw_status")
	private Long cwStatusValue;
	
	@JsonProperty(value = "old_status")
	private Long oldStatusValue;
	
	@JsonProperty(value = "opt_status")
	private String optStatusValue;
	
	@JsonProperty(value = "date_changed")
	private Date dateChanged;
	
	@JsonProperty(value = "date_created")
	private Date dateCreated;
	
	@JsonProperty(value = "user_created")
	private String userCreated;
	
	@JsonProperty(value = "date_modified")
	private Date dateModified;
	
	@JsonProperty(value = "user_modified")
	private String userModified;
}
