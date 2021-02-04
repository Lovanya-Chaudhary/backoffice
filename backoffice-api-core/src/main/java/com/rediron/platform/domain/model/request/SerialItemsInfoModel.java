package com.rediron.platform.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SerialItemsInfoModel {
	
	// serialItems
	@JsonProperty(value = "is_serialized_item")
	private boolean isSerializedItem;
	
	@JsonProperty(value = "is_auto_gen_serial")
	private boolean isAutoGenSerial;

}
