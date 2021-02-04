package com.rediron.platform.domain.model.response;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SkuInfoResponse {

	@JsonProperty(value = "sku_no")
	private Integer skuNo;
	
	@JsonProperty(value = "item_description")
	private String itemDescription;

	// productInfo
	@JsonProperty(value = "department_group_no")
	private Integer deptGrpNo;
	
	@JsonProperty(value = "department_group_description")
	private String deptGrpDesc;
	
	@JsonProperty(value = "department_no")
	private Integer deptNo;
	
	@JsonProperty(value = "department_description")
	private String deptDesc;
	
	@JsonProperty(value = "class_no")
	private Integer classNo;
	
	@JsonProperty(value = "class_description")
	private String classDesc;
	
	@JsonProperty(value = "line_no")
	private Integer lineNo;
	
	@JsonProperty(value = "line_description")
	private String lineDesc;

	// descriptionInfo
	@JsonProperty(value = "receipt_description")
	private String receiptDesc;
	
	@JsonProperty(value = "alpha_lookup")
	private String lookupDesc;
	
	@JsonProperty(value = "product_size")
	private String productSize;// Db field name mapping

	// reportInfo
	@JsonProperty(value = "report_factor")
	private BigDecimal reportFactor;
	
	@JsonProperty(value = "sell_um")
	private String sellUm;
	
	@JsonProperty(value = "report_um")
	private String reportUm;

	// measurementInfo
	@JsonProperty(value = "catch_weight_fl")
	private boolean catchWeightFl;
	
	@JsonProperty(value = "measured_item")
	private boolean measuredItem;// WeighedItemFl measuredItem same else Db field name mapping
	
	@JsonProperty(value = "unit_measured")
	private BigDecimal unitMeasured;// UnitWeight unitMeasured same else else Db field name mapping
	
	@JsonProperty(value = "unit_code")
	private BigDecimal unitCode;// UnitCube unitCode same else Db field name mapping
	
	@JsonProperty(value = "tare_number")
	private String tareNumber;

	// itemIdentifier
	@JsonProperty(value = "primary_upc")
	private String primaryUPC;// on the basis of upc seq no
	
	@JsonProperty(value = "upc_id")
	private String upcId;
	
	@JsonProperty(value = "upc_modifier")
	private Integer upcModifier;
	
	@JsonProperty(value = "primary_vendor_item")
	private String primaryVendorItem;
	
	@JsonProperty(value = "id")
	private String id;// Db field name mapping
	
	@JsonProperty(value = "mfg_cd")
	private String mfgCd;
	
	@JsonProperty(value = "plu")
	private String plu;// Db field name mapping
	// PLU is on the basis of typeCd U - UPC / P - PLU / L - ESL

	// replacement regional supersede
	@JsonProperty(value = "regional_supersede")
	private boolean regionalSupersede;
	
	@JsonProperty(value = "replacement_sku")
	private Integer replacementSKU;
	
	@JsonProperty(value = "lcu_qty")
	private BigDecimal lcuQty;
	
	@JsonProperty(value = "item_type")
	private String itemType;// currently mapped to inv type correct else Db field name mapping
	
	@JsonProperty(value = "buyer_id")
	private String buyerID;// Db field name mapping
	
	@JsonProperty(value = "report_code")
	private Integer reportCode;
	
	@JsonProperty(value = "link_sku_number")
	private Integer linkSkuNumber;

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
	
	@JsonProperty(value = "gl_cat_id_desc")
	private String glCatIdDesc;
	
	@JsonProperty(value = "xref_number")
	private Integer xrefNumber;
	
	@JsonProperty(value = "create_dt")
	private Date createDt;
	
	@JsonProperty(value = "change_dt")
	private Date changeDt;

	// labelInfo
	@JsonProperty(value = "equiv_um")
	private String equivUm;
	
	@JsonProperty(value = "equiv_factor")
	private BigDecimal equivFactor;
	
	@JsonProperty(value = "private_label_item")
	private boolean privateLabelItem; // same as privateBrandFl?
	
	@JsonProperty(value = "label_description1")
	private String labelDescription1;
	
	@JsonProperty(value = "label_description2")
	private String labelDescription2;

	@JsonProperty(value = "tender_cert_id")
	private Integer tenderCertId;// // private String tenderProgramDesc;

	// pos flags
	@JsonProperty(value = "capture_order_no_fl")
	private boolean captureOrderNoFl;
	
	@JsonProperty(value = "capture_serial_no_fl")
	private boolean captureSerialNoFl;
	
	@JsonProperty(value = "download_fl")
	private boolean downloadFl;
	
	@JsonProperty(value = "negative_price_fl")
	private boolean negativePriceFl;

	// serialItems
	@JsonProperty(value = "serialized_item")
	private boolean serializedItem;
	
	@JsonProperty(value = "auto_gen_serial")
	private boolean autoGenSerial;

	// hazardous
	@JsonProperty(value = "haz_class_id")
	private String hazClassId;
	
	@JsonProperty(value = "haz_un_number_id")
	private String hazUnNumberId;

	// generalStatusFlags
	@JsonProperty(value = "reorder_fl")
	private boolean reorderFl;
	
	@JsonProperty(value = "discontinued_fl")
	private boolean discontinuedFl;
	
	@JsonProperty(value = "obsolete_fl")
	private boolean obsoleteFl;

	// itemTypeFlags
	@JsonProperty(value = "non_revenue_item")
	private boolean nonRevenueItem;// Db field name mapping
	
	@JsonProperty(value = "store_cpn_fl")
	private boolean storeCpnFl;
	
	@JsonProperty(value = "non_inventory_fl")
	private boolean nonInventoryFl;
	
	@JsonProperty(value = "sub_inv_fl")
	private boolean subInvFl;
	
	@JsonProperty(value = "supply_item_fl")
	private boolean supplyItemFl;
	
	@JsonProperty(value = "core_fl")
	private boolean coreFl;
	
	@JsonProperty(value = "flexible_item_fl")
	private boolean flexibleItemFl;

	// warehouseInnerPack;
	@JsonProperty(value = "pack_up_action")
	private Integer packUpAction;

	// extra fields maybe needed later
	@JsonProperty(value = "unit_weight")
	private BigDecimal unitWeight;
	
	@JsonProperty(value = "unit_cube")
	private BigDecimal unitCube;
	// private String vendorId;
	// private String vendorItem;
	
	@JsonProperty(value = "weighed_item_fl")
	private boolean weighedItemFl;
	// private String typeCd;
	
	// for kit item and count sheet flags
	@JsonProperty(value = "kit_item")
	private boolean kitItem;
	
	@JsonProperty(value = "count_sheet_item")
	private boolean countSheetItem;

}
