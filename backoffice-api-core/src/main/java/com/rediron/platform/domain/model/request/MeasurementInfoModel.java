package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MeasurementInfoModel {
	
	// measurementInfo
	@JsonProperty(value = "is_catch_weight_fl")
	private boolean isCatchWeightFl;
	
	@JsonProperty(value = "is_measured_item")
	private boolean isMeasuredItem;// WeighedItemFl measuredItem same else Db field name mapping
	
	@JsonProperty(value = "unit_measured")
	private BigDecimal unitMeasured;// UnitWeight unitMeasured same else else Db field name mapping
	
	@JsonProperty(value = "unit_code")
	private BigDecimal unitCode;// UnitCube unitCode same else Db field name mapping
	
	@JsonProperty(value = "tare_number")
	private String tareNumber;

}
