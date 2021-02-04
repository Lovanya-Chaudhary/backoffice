package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemUpcModel {

	@Value("U")
	@JsonProperty(value = "type_cd")
	private String typeCd;
	
	@NotNull
	@JsonProperty(value = "upc_id")
	private String upcId;
	
	@JsonProperty(value = "adj_amt_upc")
	private BigDecimal adjAmtUPC;
	
	@JsonProperty(value = "modifier_upc")
	private Integer modifierUPC;
	
	@JsonProperty(value = "is_primary_upc")
	private Boolean isPrimaryUPC;
	
	@JsonProperty(value = "ucc14_um_ups")
	private String ucc14UmUPS;
	
	@JsonProperty(value = "ucc14_qty_ups")
	private BigDecimal ucc14QtyUPS;
}
