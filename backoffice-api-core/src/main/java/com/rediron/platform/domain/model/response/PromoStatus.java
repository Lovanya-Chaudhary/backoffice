package com.rediron.platform.domain.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PromoStatus {
	
	@JsonProperty(value = "markdown_pact")
	private BigDecimal markdownPact;
	
	@JsonProperty(value = "off_amount")
	private BigDecimal offAmount;
	
	@JsonProperty(value = "sell_itemizer_fl1")
	private boolean sellItemizerFl1;
	
	
	@JsonProperty(value = "sell_itemizer_fl2")
	private boolean sellItemizerFl2;
	
	@JsonProperty(value = "itemizer_status_id")
	private String itemizerStatusId;
	
	@JsonProperty(value = "sku_status_id")
	private String skuStatusId;
	
	@JsonProperty(value = "update_itemizer_status_id_fl")
	private boolean updateItemizerStatusIdFl;
	
	@JsonProperty(value = "update_sku_status_id_fl")
	private boolean updateSkuStatusIdFl;
	
	@JsonProperty(value = "update_promo_commission_code_fl")
	private boolean updatePromoCommissionCodeFl;
	
	@JsonProperty(value = "pomo_commission_code")
	private String pomoCommissionCode;

}
