package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReportInfoModel {
	
	// reportInfo
	@NotNull
	@JsonProperty(value = "report_factor")
	private BigDecimal reportFactor;
	
	@NotNull
	@JsonProperty(value = "sell_unit_of_measure")
	private String sellUnitOfMeasure;
	
	@NotNull
	@JsonProperty(value = "report_unit_of_measure")
	private String reportUnitOfMeasure;

}
