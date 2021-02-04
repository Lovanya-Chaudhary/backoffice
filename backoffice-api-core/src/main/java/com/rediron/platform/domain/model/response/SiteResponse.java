package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SiteResponse {
	
	@JsonProperty(value = "site_no")
	private Integer siteNo;
	
	@JsonProperty(value = "full_name")
    private String fullName;
	
	@JsonProperty(value = "name")
    private String name;
	
	@JsonProperty(value = "adr1")
    private String adr1;
	
	@JsonProperty(value = "adr2")
    private String adr2;
	
	@JsonProperty(value = "city")
    private String city;
	
	@JsonProperty(value = "state")
    private String state;
	
	@JsonProperty(value = "county")
    private String county;
	
	@JsonProperty(value = "zip")
    private String zip;
	
	@JsonProperty(value = "owner_id")
    private Integer ownerId;
	
	@JsonProperty(value = "country")
    private String country;
	
	@JsonProperty(value = "company_cd")
    private String companyCd;
	
	@JsonProperty(value = "web_enabled_fl")
    private String webEnabledFl;
	
	@JsonProperty(value = "site_active")
    private String siteActive;

}
