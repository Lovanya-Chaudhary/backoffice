package com.rediron.platform.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TenderCertificateTypeInfoModel {
	
	 @JsonProperty(value = "tender_cert_id")
	 private Integer tenderCertId;

}
