package com.rediron.platform.domain.model.response;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class SummaryInfoResponse {
	
	@JsonProperty(value = "site_no")
	private int siteNo;
	
	@JsonProperty(value = "site_description")
	private String siteDescription;
	
	@JsonProperty(value = "price")
	private BigDecimal price;
	
	@JsonProperty(value = "suggested_retail")
	private BigDecimal suggestedRetail;
	
	@JsonProperty(value = "reorder_pt")
	private BigDecimal reorderPt;
	
	@JsonProperty(value = "reorder_to")
	private BigDecimal reorderTo;
	
	@JsonProperty(value = "qty_on_hand")
	private BigDecimal qtyOnHand;
	
	@JsonProperty(value = "cost")
	private BigDecimal cost;
	
	@JsonProperty(value = "margin")
	private BigDecimal margin;
	
	@JsonProperty(value = "qty_on_order")
	private BigDecimal qtyOnOrder;
	
	@JsonProperty(value = "allocated_qty_in")
	private BigDecimal allocatedQtyIn;
	
	@JsonProperty(value = "allocated_qty_out")
	private BigDecimal allocatedQtyOut;
	
	@JsonProperty(value = "po_expected_date")
	private Date poExpectedDate;
	
	@JsonProperty(value = "is_authorized")
	private boolean authorized;

}
