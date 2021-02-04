package com.rediron.platform.domain.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemUpcResponse {
	
	@JsonProperty(value = "upc_id")
	private String upcId;
	
	@JsonProperty(value = "sku_no")
	private Integer skuNo;
	
	@JsonProperty(value = "upc_modifier")
	private Integer upcModifier;
	
	@JsonProperty(value = "price_adj_amt")
    private BigDecimal priceAdjAmt;
	
	@JsonProperty(value = "upc_seq")
    private Integer upcSeq;
	
	@JsonProperty(value = "type_cd")
    private String typeCd;
    
    @JsonProperty(value = "ucc14_uom")
    private String ucc14Uom;
    
    @JsonProperty(value = "ucc14_qty")
    private BigDecimal ucc14Qty;

}
