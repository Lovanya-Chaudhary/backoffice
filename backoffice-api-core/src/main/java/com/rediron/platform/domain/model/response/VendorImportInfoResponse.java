package com.rediron.platform.domain.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VendorImportInfoResponse {
	
	@JsonProperty(value = "change_date")
	private Date changeDate;
	
	@JsonProperty(value = "change_vendor")
	private String changeVendor;
	
	@JsonProperty(value = "lock_item")
	private boolean lockItem;

}
