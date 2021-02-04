package com.rediron.platform.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GeneralStatusFlagsInfoModel {
	
	// generalStatusFlags
	@JsonProperty(value = "is_reorder_fl")
	private boolean isReorderFl;
	
	@JsonProperty(value = "is_discontinued_fl")
	private boolean isDiscontinuedFl;
	
	@JsonProperty(value = "is_obsolete_fl")
	private boolean isObsoleteFl;

}
