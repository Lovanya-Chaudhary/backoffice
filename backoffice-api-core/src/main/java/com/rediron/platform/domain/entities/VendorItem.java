//package com.rediron.platform.domain.entities;
//
//import java.io.Serializable;
//import javax.persistence.*;
//
//import lombok.Data;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//
///**
// * The persistent class for the VENDOR_ITEM database table.
// * 
// */
//@Entity
//@Table(name="VENDOR_ITEM")
//@NamedQuery(name="VendorItem.findAll", query="SELECT v FROM VendorItem v")
//@Data
//public class VendorItem implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@Column(name="SEQ_NO")
//	private long seqNo;
//
//	@Column(name="ADDITIONAL_VENDOR_ITEM")
//	private String additionalVendorItem;
//
//	@Column(name="BATCH_ID")
//	private BigDecimal batchId;
//
//	@Column(name="BATCH_SITE_NO")
//	private BigDecimal batchSiteNo;
//
//	@Column(name="CORE_COST")
//	private BigDecimal coreCost;
//
//	@Column(name="COST_ELEMENT1_AMT")
//	private BigDecimal costElement1Amt;
//
//	@Column(name="COST_ELEMENT2_AMT")
//	private BigDecimal costElement2Amt;
//
//	@Column(name="COST_FACTOR")
//	private BigDecimal costFactor;
//
//	@Column(name="CUBE")
//	private BigDecimal cube;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="DATE_CHANGED")
//	private Date dateChanged;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="DATE_CREATED")
//	private Date dateCreated;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="DATE_MODIFIED")
//	private Date dateModified;
//
//	@Column(name="DEFAULT_CLASS_CODE")
//	private String defaultClassCode;
//
//	private BigDecimal eoq;
//
//	@Column(name="EXTERNAL_INTERNAL_SUPPLIER")
//	private String externalInternalSupplier;
//
//	@Column(name="EXTERNAL_REF_ID")
//	private String externalRefId;
//
//	@Column(name="FORM_DIMENSION")
//	private String formDimension;
//
//	@Column(name="FREIGHT_AMT")
//	private BigDecimal freightAmt;
//
//	@Column(name="FREIGHT_PCT")
//	private BigDecimal freightPct;
//
//	@Column(name="FREIGHT_TOLERANCE_AMT")
//	private BigDecimal freightToleranceAmt;
//
//	@Column(name="FREIGHT_TOLERANCE_AMT_FL")
//	private String freightToleranceAmtFl;
//
//	@Column(name="FREIGHT_TOLERANCE_PCT")
//	private BigDecimal freightTolerancePct;
//
//	@Column(name="FREIGHT_TOLERANCE_PCT_FL")
//	private String freightTolerancePctFl;
//
//	private BigDecimal height;
//
//	@Column(name="IMMEDIATE_SUPPLIER")
//	private String immediateSupplier;
//
//	@Column(name="INV_TOLERANCE_AMT")
//	private BigDecimal invToleranceAmt;
//
//	@Column(name="INV_TOLERANCE_AMT_FL")
//	private String invToleranceAmtFl;
//
//	@Column(name="INV_TOLERANCE_PCT")
//	private BigDecimal invTolerancePct;
//
//	@Column(name="INV_TOLERANCE_PCT_FL")
//	private String invTolerancePctFl;
//
//	@Column(name="INV_TOLERANCE_QTY")
//	private BigDecimal invToleranceQty;
//
//	@Column(name="INV_TOLERANCE_QTY_FL")
//	private String invToleranceQtyFl;
//
//	@Column(name="LANDED_COST_ELEMENTS")
//	private String landedCostElements;
//
//	@Column(name="LAST_COST")
//	private BigDecimal lastCost;
//
//	@Column(name="LAST_COST_QTY")
//	private BigDecimal lastCostQty;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="LAST_COSTCHG_DT")
//	private Date lastCostchgDt;
//
//	@Column(name="LEAD_TIME")
//	private BigDecimal leadTime;
//
//	@Column(name="LENGTH")
//	private BigDecimal length;
//
//	private BigDecimal loq;
//
//	@Column(name="MAIN_VENDOR_ITEM_FL")
//	private String mainVendorItemFl;
//
//	@Column(name="MASTER_PACK_QTY")
//	private BigDecimal masterPackQty;
//
//	@Column(name="NET_COST")
//	private BigDecimal netCost;
//
//	@Column(name="OPERATION_TYPE")
//	private String operationType;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="ORDER_AVAILABILITY_ACTIVE_DT")
//	private Date orderAvailabilityActiveDt;
//
//	@Column(name="ORDER_AVAILABILITY_STATUS")
//	private String orderAvailabilityStatus;
//
//	@Column(name="PACK_COST")
//	private BigDecimal packCost;
//
//	@Column(name="PACK_CUBE")
//	private BigDecimal packCube;
//
//	@Column(name="PACK_QTY")
//	private BigDecimal packQty;
//
//	@Column(name="PACK_UM")
//	private String packUm;
//
//	@Column(name="PACK_WEIGHT")
//	private BigDecimal packWeight;
//
//	@Column(name="REGISTER_REPLICATION_NO")
//	private BigDecimal registerReplicationNo;
//
//	@Column(name="RELEASE_HORIZON")
//	private BigDecimal releaseHorizon;
//
//	@Column(name="REPLICATION_NO")
//	private BigDecimal replicationNo;
//
//	private String route;
//
//	@Column(name="SITE_NO")
//	private BigDecimal siteNo;
//
//	@Column(name="SKU_NO")
//	private BigDecimal skuNo;
//
//	@Column(name="SUPPLIER_REC_SCHED")
//	private String supplierRecSched;
//
//	@Column(name="UNIT_COST")
//	private BigDecimal unitCost;
//
//	@Column(name="UNIT_COST_UM")
//	private String unitCostUm;
//
//	@Column(name="UPLIFT_GROUP_ID")
//	private BigDecimal upliftGroupId;
//
//	@Column(name="USER_CREATED")
//	private String userCreated;
//
//	@Column(name="USER_MODIFIED")
//	private String userModified;
//
//	@Column(name="VEN_TYPE")
//	private String venType;
//
//	@Column(name="VENDOR_CURRENCY_PACK_COST")
//	private BigDecimal vendorCurrencyPackCost;
//
//	@Column(name="VENDOR_ID")
//	private String vendorId;
//
//	@Column(name="VENDOR_ITEM")
//	private String vendorItem;
//
//	private BigDecimal weight;
//
//	@Column(name="WHSE_COST_AMT")
//	private BigDecimal whseCostAmt;
//
//	private BigDecimal width;
//
//	public VendorItem() {
//	}
//}