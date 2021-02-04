package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SummaryReorderPoints {
	
	@JsonProperty(value = "reorder_to")
	private BigDecimal reorderTo;
	
	@JsonProperty(value = "reorder_pt")
	private BigDecimal reorderPt;
	
	@JsonProperty(value = "site_no")
	private int siteNo;
	
	@JsonProperty(value = "is_authorized")
	private boolean authorized;

}
