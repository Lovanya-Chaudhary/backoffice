package com.rediron.platform.domain.model.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemIdentifiersInfoModel {

	@NotNull
	@JsonProperty(value = "mfg_cd")
	private String mfgCd;

}
