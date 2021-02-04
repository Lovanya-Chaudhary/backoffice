package com.rediron.platform.domain.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConfigReqrdFieldsRequest {
	
	@JsonProperty(value = "config_models")
	private List<ConfigReqrdFieldModel> listOfConfigReqrdFieldModel;
}
