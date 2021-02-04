package com.rediron.platform.domain.response;
import lombok.Data;

@Data
public class SiteInfoResponse {
	
	private Integer siteNo;
    private String fullName;
    private String name;
    private String adr1;
    private String adr2;
    private String city;
    private String state;
    private String county;
    private String zip;
    private Integer ownerId;
    private String country;
    private String companyCd;
    private String webEnabledFl;
    private String siteActive;

}
