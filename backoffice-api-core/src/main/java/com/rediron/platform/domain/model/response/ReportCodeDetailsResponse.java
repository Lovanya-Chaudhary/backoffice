package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReportCodeDetailsResponse {
	
	@JsonProperty(value = "report_code")
	private int reportCode;
	
	@JsonProperty(value = "report_description")
	private String reportDescription;

}
