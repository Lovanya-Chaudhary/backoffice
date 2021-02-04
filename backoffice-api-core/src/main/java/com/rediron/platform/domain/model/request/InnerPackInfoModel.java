package com.rediron.platform.domain.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InnerPackInfoModel {
	
	// packedQtySection
	@JsonProperty(value = "inner_pack_qty")
	private BigDecimal innerPackQty; // Db field name mapping
	
	@JsonProperty(value = "price_list_seq")
	private Integer priceListSeq;
	
	@JsonProperty(value = "weeks_history")
	private Integer weeksHistory;
	
	@JsonProperty(value = "periods_history")
	private Integer periodsHistory;
	
	@JsonProperty(value = "gl_cat_iD")
	private Integer glCatID;
	
	@JsonProperty(value = "xref_number")
	private Integer xrefNumber;
//	private Date createDt;
//	private Date changeDt;

}
