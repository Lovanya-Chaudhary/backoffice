package com.rediron.platform.domain.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PromoInfo {
	
	@JsonProperty(value = "start_date")
	private Date startDate;
	
	@JsonProperty(value = "end_date")
	private Date endDate;
	
	@JsonProperty(value = "ad_id")
	private String adId;
	
	@JsonProperty(value = "ad_site_no")
	private int adSiteNo;

}
