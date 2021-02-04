package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TenderCertificateInfoResponse {
	
	@JsonProperty(value = "tender_certificate_description")
	private String tenderCertificateDescription;
	
	@JsonProperty(value = "tender_program_id")
	private Integer tenderProgramId;

}
