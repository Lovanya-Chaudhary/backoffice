package com.rediron.platform.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InvbysitAndPromoDetails {
	
	@JsonProperty(value = "invbysit")
	private Invbysit invbysit;
	
	@JsonProperty(value = "promo_info")
	private PromoInfo promoInfo;

}
