package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LabelInfoModel {
	
	// labelInfo
	@JsonProperty(value = "equiv_um")
	private String equivUm;
	
	@JsonProperty(value = "equiv_factor")
	private BigDecimal equivFactor;
	
	@JsonProperty(value = "is_private_label_item")
	private boolean isPrivateLabelItem; // same as privateBrandFl?
	
	@JsonProperty(value = "label_description1")
	private String labelDescription1;
	
	@JsonProperty(value = "label_description2")
	private String labelDescription2;

}
