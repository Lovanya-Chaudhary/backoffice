package com.rediron.platform.domain.model.response;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Inventory {

	@JsonProperty(value = "sku_no")
	private Integer skuNo;

	@JsonProperty(value = "mfg_cd")
	private String mfgCd;

	@JsonProperty(value = "xref_ro")
	private Integer xrefNo;

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "receipt_description")
	private String receiptDesc;

	@JsonProperty(value = "lookup_description")
	private String lookupDesc;

	@JsonProperty(value = "pkg_description")
	private String pkgDesc;

	@JsonProperty(value = "department_no")
	private Integer deptNo;

	@JsonProperty(value = "class_no")
	private Integer classNo;

	@JsonProperty(value = "line_no")
	private Integer lineNo;

	@JsonProperty(value = "code1_id")
	private String code1Id;

	@JsonProperty(value = "code2_id")
	private String code2Id;

	@JsonProperty(value = "code3_id")
	private String code3Id;

	@JsonProperty(value = "create_dt")
	private Date createDt;

	@JsonProperty(value = "change_dt")
	private Date changeDt;

	@JsonProperty(value = "otb_fl")
	private String otbFl;

	@JsonProperty(value = "use_serial_fl")
	private String useSerialFl;

	@JsonProperty(value = "gl_cat_id")
	private Integer glCatId;

	@JsonProperty(value = "report_factor")
	private BigDecimal reportFactor;

	@JsonProperty(value = "lcu_qty")
	private BigDecimal lcuQty;

	@JsonProperty(value = "report_um")
	private String reportUm;

	@JsonProperty(value = "sell_um")
	private String sellUm;

	@JsonProperty(value = "ticket_type")
	private Integer ticketType;

	@JsonProperty(value = "unit_weight")
	private BigDecimal unitWeight;

	@JsonProperty(value = "shelf_type")
	private Integer shelfType;

	@JsonProperty(value = "link_sku_no")
	private Integer linkSkuNo;

	@JsonProperty(value = "reorder_fl")
	private String reorderFl;

	@JsonProperty(value = "special_item_fl")
	private String specialItemFl;

	@JsonProperty(value = "weeks_history")
	private Integer weeksHistory;
	
	@JsonProperty(value = "periods_history")
	private Integer periodsHistory;
	
	@JsonProperty(value = "store_cpn_fl")
	private String storeCpnFl;
	
	@JsonProperty(value = "report_cd")
	private Integer reportCd;
	
	@JsonProperty(value = "download_fl")
	private String downloadFl;
	
	@JsonProperty(value = "discontinued_fl")
	private String discontinuedFl;
	
	@JsonProperty(value = "equiv_factor")
	private BigDecimal equivFactor;
	
	@JsonProperty(value = "new_label_dt")
	private Date newLabelDt;
	
	@JsonProperty(value = "change_label_dt")
	private Date changeLabelDt;
	
	@JsonProperty(value = "label_desc1")
	private String labelDesc1;
	
	@JsonProperty(value = "label_desc2")
	private String labelDesc2;
	
	@JsonProperty(value = "equiv_um")
	private String equivUm;
	
	@JsonProperty(value = "warehouse_inner_pack")
	private BigDecimal warehouseInnerPack;
	
	@JsonProperty(value = "obsolete_fl")
	private String obsoleteFl;
	
	@JsonProperty(value = "per_car_qty")
	private Integer perCarQty;
	
	@JsonProperty(value = "price_list_seq")
	private Integer priceListSeq;
	
	@JsonProperty(value = "superseded_fl")
	private String supersededFl;
	
	@JsonProperty(value = "unit_cube")
	private BigDecimal unitCube;
	
	@JsonProperty(value = "capture_order_no_fl")
	private String captureOrderNoFl;
	
	@JsonProperty(value = "capture_serial_no_fl")
	private String captureSerialNoFl;
	
	@JsonProperty(value = "venitm_seq_no")
	private Integer venitmSeqNo;
	
	@JsonProperty(value = "upc_id_seq_no")
	private Integer upcIdSeqNo;
	
	@JsonProperty(value = "employee_id")
	private String employeeId;
	
	@JsonProperty(value = "supplemental_upc_fl")
	private String supplementalUpcFl;
	
	@JsonProperty(value = "weighed_item_fl")
	private String weighedItemFl;
	
	@JsonProperty(value = "suggested_sell_cd")
	private Integer suggestedSellCd;
	
	@JsonProperty(value = "validation_seq_no")
	private Integer validationSeqNo;
	
	@JsonProperty(value = "restrict_sale_fl")
	private String restrictSaleFl;
	
	@JsonProperty(value = "avail_sale_dt")
	private Date availSaleDt;
	
	@JsonProperty(value = "inv_item_lock_fl")
	private String invItemLockFl;
	
	@JsonProperty(value = "non_inventory_fl")
	private String nonInventoryFl;
	
	@JsonProperty(value = "private_brand_fl")
	private String privateBrandFl;
	
	@JsonProperty(value = "last_item_change_dt")
	private Date lastItemChangeDt;
	
	@JsonProperty(value = "last_item_change_vendor_id")
	private String lastItemChangeVendorId;
	
	@JsonProperty(value = "tender_program_id")
	private Integer tenderProgramId;
	
	@JsonProperty(value = "external_ref_id")
	private String externalRefId;
	
	@JsonProperty(value = "inv_type")
	private String invType;
	
	@JsonProperty(value = "warranty_fl")
	private String warrantyFl;
	
	@JsonProperty(value = "system_warranty_fl")
	private String systemWarrantyFl;
	
	@JsonProperty(value = "sub_inv_fl")
	private String subInvFl;
	
	@JsonProperty(value = "replication_no")
	private Long replicationNo;
	
	@JsonProperty(value = "operation_type")
	private String operationType;
	
	@JsonProperty(value = "register_replication_no")
	private Long registerReplicationNo;
	
	@JsonProperty(value = "date_created")
	private Date dateCreated;
	
	@JsonProperty(value = "user_created")
	private String userCreated;
	
	@JsonProperty(value = "date_modified")
	private Date dateModified;
	
	@JsonProperty(value = "user_modified")
	private String userModified;
	
	@JsonProperty(value = "replacement_sku_no")
	private Integer replacementSkuNo;
	
	@JsonProperty(value = "haz_un_number_id")
	private String hazUnNumberId;
	
	@JsonProperty(value = "haz_class_id")
	private String hazClassId;
	
	@JsonProperty(value = "rollup_bucket_fl")
	private String rollupBucketFl;
	
	@JsonProperty(value = "tare_table_no")
	private String tareTableNo;
	
	@JsonProperty(value = "catch_weight_fl")
	private String catchWeightFl;
	
	@JsonProperty(value = "product_hier4")
	private String productHier4;
	
	@JsonProperty(value = "product_hier5")
	private String productHier5;
	
	@JsonProperty(value = "product_hier6")
	private String productHier6;
	
	@JsonProperty(value = "rrp_inv_type")
	private String rrpInvType;
	
	@JsonProperty(value = "hierarchy_id")
	private Integer hierarchyId;
	
	@JsonProperty(value = "negative_price_fl")
	private String negativePriceFl;
	
	@JsonProperty(value = "packup_action_id")
	private Integer packupActionId;
	
	@JsonProperty(value = "owner_id")
	private Integer ownerId;
	
	@JsonProperty(value = "flexible_item_fl")
	private String flexibleItemFl;
	
	@JsonProperty(value = "supply_item_fl")
	private String supplyItemFl;
	
	@JsonProperty(value = "attribute1_id")
	private Integer attribute1Id;
	
	@JsonProperty(value = "attribute2_id")
	private Integer attribute2Id;
	
	@JsonProperty(value = "attribute3_id")
	private Integer attribute3Id;

	@JsonProperty(value = "attribute1_value")
	private String attribute1Value;
	
	@JsonProperty(value = "attribute2_value")
	private String attribute2Value;
	
	@JsonProperty(value = "attribute3_value")
	private String attribute3Value;
	
	@JsonProperty(value = "core_fl")
	private String coreFl;
	
	@JsonProperty(value = "kit_member_fl")
	private String kitMemberFl;
	
	@JsonProperty(value = "primary_vendor_item")
	private String primaryVendorItem;
	
	@JsonProperty(value = "auto_gen_serials_fl")
	private String autoGenSerialsFl;
	
	@JsonProperty(value = "regional_price_fl")
	private String regionalPriceFl;
	
	@JsonProperty(value = "regional_promo_fl")
	private String regionalPromoFl;
	
	@JsonProperty(value = "regional_supersede_fl")
	private String regionalSupersedeFl;
	
	@JsonProperty(value = "web_enabled_fl")
	private String webEnabledFl;
	
	@JsonProperty(value = "ecomm_non_inventory_fl")
	private String ecommNonInventoryFl;
}
