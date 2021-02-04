package com.rediron.platform.domain.model.response;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VendorItemResponse {

	@JsonProperty(value = "seq_no")
	private BigInteger seqNo;

	@JsonProperty(value = "sku_no")
	private Integer skuNo;

	@JsonProperty(value = "vendor_name")
	private String vendorName;

	@JsonProperty(value = "vendor_id")
	private String vendorId;

	@JsonProperty(value = "site_no")
	private Integer siteNo;

	@JsonProperty(value = "ven_type")
	private String venType;

	@JsonProperty(value = "vendor_item")
	private String vendorItem;

	@JsonProperty(value = "lock_lead_time")
	private Boolean lockLeadTime;

	@JsonProperty(value = "main_vendor_item_fl")
	private String mainVendorItemFl;

	@JsonProperty(value = "eoq")
	private BigDecimal eoq;

	@JsonProperty(value = "lead_time")
	private Integer leadTime;

	@JsonProperty(value = "unit_cost")
	private BigDecimal unitCost;

	@JsonProperty(value = "pack_cost")
	private BigDecimal packCost;

	@JsonProperty(value = "pack_qty")
	private BigDecimal packQty;

	@JsonProperty(value = "master_pack_qty")
	private BigDecimal masterPackQty;

	@JsonProperty(value = "pack_um")
	private String packUm;

	@JsonProperty(value = "loq")
	private BigDecimal loq;

	@JsonProperty(value = "date_changed")
	private Date dateChanged;

	@JsonProperty(value = "oa_status")
	private String orderAvailabilityStatus;

	@JsonProperty(value = "oa_active_date")
	private Date orderAvailabilityActiveDt;

	@JsonProperty(value = "operation_type")
	private String operationType;

	@JsonProperty(value = "date_created")
	private Date dateCreated;

	@JsonProperty(value = "user_created")
	private String userCreated;

	@JsonProperty(value = "date_modified")
	private Date dateModified;

	@JsonProperty(value = "user_modified")
	private String userModified;

	@JsonProperty(value = "vendor_currency_pack_post")
	private BigDecimal vendorCurrencyPackCost;

	@JsonProperty(value = "pack_weight")
	private BigDecimal packWeight;

	@JsonProperty(value = "pack_cube")
	private BigDecimal packCube;
}
