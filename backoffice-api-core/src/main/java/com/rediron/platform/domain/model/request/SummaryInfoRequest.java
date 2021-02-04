package com.rediron.platform.domain.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SummaryInfoRequest {
	
	@JsonProperty(value = "reorder_points")
	private List<SummaryReorderPoints> reorderPoints;

}
