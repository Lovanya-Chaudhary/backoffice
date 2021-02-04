package com.rediron.platform.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class POSFlagsInfoModel {
	
	// pos flags
	
	@JsonProperty(value = "is_capture_order_no_fl")
	private boolean isCaptureOrderNoFl;
	
	@JsonProperty(value = "is_capture_serial_no_fl")
	private boolean isCaptureSerialNoFl;
	
	@JsonProperty(value = "is_download_fl")
	private boolean isDownloadFl;
	
	@JsonProperty(value = "is_negative_price_fl")
	private boolean isNegativePriceFl;

}
