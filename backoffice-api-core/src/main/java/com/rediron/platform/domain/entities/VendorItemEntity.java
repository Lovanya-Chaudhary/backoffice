package com.rediron.platform.domain.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rediron.platform.domain.rnet.DtoToEntity;
import com.rediron.platform.domain.rnet.EntityManagerContext;
import com.rediron.platform.domain.rnet.RNetAbstractEntity;
import com.tomax.api.DataTransferObject;
import com.tomax.api.RNetExceptionProxy;
import com.tomax.api.RNetUnexpectedException;
import com.tomax.vendor.dto.VendorItemDTO;
import com.tomax.vendor.dto.VendorItemDTOId;



@SuppressWarnings("all")
//@NamedQueries({
//    @NamedQuery(name = "VENDOR_ITEM_BY_VENDOR", query = "Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo and v.vendorId = :vendorId "),
//    @NamedQuery(name = "VENDOR_ITEM_BY_VENDOR_AND_OWNER", query = "Select v from VendorItemEntity v Where v.vendorId = :vendorId and exists (select 'x' from SiteEntity s where s.siteNo = v.siteNo and s.ownerId in (:ownerIdList)) "),
//    @NamedQuery(name = "VENDOR_ITEM_COUNT_BY_VENDOR_AND_OWNER", query = "Select count(v.seqNo) from VendorItemEntity v Where v.vendorId = :vendorId and exists (select 'x' from SiteEntity s where s.siteNo = v.siteNo and s.ownerId in (:ownerIdList)) "),
//    @NamedQuery(name = "VENDOR_ITEM_COUNT_BY_VENDOR_SKU_AND_SITE", query = "Select count(v.seqNo)  from VendorItemEntity v Where v.vendorId = :vendorId  and v.siteNo = :siteNo and v.skuNo = :skuNo "),
//    @NamedQuery(name = "DELETE_VENDORS_BY_ITEMS", query = "delete from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo "),
//    @NamedQuery(name = "VENDORS_BY_ITEMS", query = "Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo "),
//    @NamedQuery(name = "VENDOR_ITEMS_BY_SITES_SKUS", query = "Select v from VendorItemEntity v Where v.skuNo In (:skuNos) and v.siteNo In (:siteNos) "),
//    @NamedQuery(name = "PRIMARY_VENDOR", query = "Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo and v.venType = 'P' And v.mainVendorItemFl = 'Y' "),
//    @NamedQuery(name = "PRIMARY_VENDOR_FOR_SKULIST", query = "Select v from VendorItemEntity v Where v.skuNo in :skuList and v.siteNo = :siteNo and v.venType = 'P' And v.mainVendorItemFl = 'Y' "),
//    @NamedQuery(name = "GET_MAIN_VENDOR_ITEM", query = "Select v From VendorItemEntity v Where v.skuNo = :skuNo And v.siteNo = :siteNo And v.vendorId = :vendorId And v.mainVendorItemFl = 'Y' "),
//    @NamedQuery(name = "GET_MAIN_VENDOR_ITEMS_FOR_SITES", query = "Select v From VendorItemEntity v Where v.skuNo = :skuNo And v.siteNo IN :siteList And v.vendorId = :vendorId And v.mainVendorItemFl = 'Y' "),
//    @NamedQuery(name = "VENDOR_ITEM_BY_VENDOR_SKU_SITE_PACKQTY", query = "Select v from VendorItemEntity v Where v.vendorId = :vendorId and v.skuNo = :skuNo and v.siteNo = :siteNo and v.packQty = :packQty "),
//    @NamedQuery(name = "GET_ALL_VENDOR_ITEMS_FOR_SKU", query = "Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo "),
//    @NamedQuery(name = "GET_ALL_VENDOR_ITEMS_FOR_SKU_SITE", query = "Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo "),
//    @NamedQuery(name = "GET_ALL_VENDOR_ITEMS_FOR_SKU_SITE_VENDOR", query = "Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo and v.vendorId = :vendorId "),
//    @NamedQuery(name = "GET_ALL_VENDOR_ITEMS_FOR_SKU_AND_VENDOR", query = "Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.vendorId = :vendorId "),
//    @NamedQuery(name = "GET_VENDOR_ITEMS_FOR_SEQ_LIST", query = "Select v from VendorItemEntity v Where v.seqNo in :seqList ")
//})
@Entity
@Table(name = "VENDOR_ITEM")
public class VendorItemEntity
    extends RNetAbstractEntity<VendorItemEntity, VendorItemDTO>
{

    /**
     * @hide<P><I>Not for public use.</I></P><P>Used to implement serialization.</P>
     * 
     */
    private final static long serialVersionUID = 1L;
    /**
     * Embedded composite identifier.
     * 
     */
    @EmbeddedId
//    @Generator({
//        @Property(field = "identifier.seqNo", position = 1, strategy = Strategy.SEQUENCE, sequence = "VENDOR_ITEM_SEQ")
//    })
    private VendorItemEntityId identifier;
    /**
     * <p>Maps to table column: {@code SKU_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 9, fraction = 0, message = "SKU_NO should have an integer component no longer than 9 and an a fraction component of exact size 0")
    @NotNull(message = "SKU_NO can not be null")
    @Column(name = "SKU_NO")
    private Integer skuNo;
    /**
     * <p>Maps to table column: {@code VENDOR_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 9, message = "VENDOR_ID should contain less than or equal to 9 characters")
    @NotNull(message = "VENDOR_ID can not be null")
    @Column(name = "VENDOR_ID")
    private String vendorId;
    /**
     * <p>Maps to table column: {@code SITE_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "SITE_NO should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @NotNull(message = "SITE_NO can not be null")
    @Column(name = "SITE_NO")
    private Integer siteNo;
    /**
     * <p>Maps to table column: {@code VEN_TYPE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "VEN_TYPE should contain less than or equal to 1 characters")
    @NotNull(message = "VEN_TYPE can not be null")
    @Column(name = "VEN_TYPE")
    private String venType;
    /**
     * <p>Maps to table column: {@code VENDOR_ITEM}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 20, message = "VENDOR_ITEM should contain less than or equal to 20 characters")
    @NotNull(message = "VENDOR_ITEM can not be null")
    @Column(name = "VENDOR_ITEM")
    private String vendorItem;
    /**
     * <p>Maps to table column: {@code MAIN_VENDOR_ITEM_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "MAIN_VENDOR_ITEM_FL should contain less than or equal to 1 characters")
    @NotNull(message = "MAIN_VENDOR_ITEM_FL can not be null")
    @Column(name = "MAIN_VENDOR_ITEM_FL")
    private String mainVendorItemFl;
    /**
     * <p>Maps to table column: {@code EOQ}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 2, message = "EOQ should have an integer component no longer than 10 and an a fraction component of exact size 2")
    @NotNull(message = "EOQ can not be null")
    @Column(name = "EOQ")
    private BigDecimal eoq;
    /**
     * <p>Maps to table column: {@code LEAD_TIME}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "LEAD_TIME should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @NotNull(message = "LEAD_TIME can not be null")
    @Column(name = "LEAD_TIME")
    private Integer leadTime;
    /**
     * <p>Maps to table column: {@code UNIT_COST}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 4, message = "UNIT_COST should have an integer component no longer than 6 and an a fraction component of exact size 4")
    @Column(name = "UNIT_COST")
    private BigDecimal unitCost;
    /**
     * <p>Maps to table column: {@code PACK_COST}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 4, message = "PACK_COST should have an integer component no longer than 6 and an a fraction component of exact size 4")
    @NotNull(message = "PACK_COST can not be null")
    @Column(name = "PACK_COST")
    private BigDecimal packCost;
    /**
     * <p>Maps to table column: {@code PACK_QTY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 2, message = "PACK_QTY should have an integer component no longer than 10 and an a fraction component of exact size 2")
    @Column(name = "PACK_QTY")
    private BigDecimal packQty;
    /**
     * <p>Maps to table column: {@code MASTER_PACK_QTY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 2, message = "MASTER_PACK_QTY should have an integer component no longer than 10 and an a fraction component of exact size 2")
    @Column(name = "MASTER_PACK_QTY")
    private BigDecimal masterPackQty;
    /**
     * <p>Maps to table column: {@code PACK_UM}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 4, message = "PACK_UM should contain less than or equal to 4 characters")
    @NotNull(message = "PACK_UM can not be null")
    @Column(name = "PACK_UM")
    private String packUm;
    /**
     * <p>Maps to table column: {@code LAST_COSTCHG_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_COSTCHG_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCostchgDt;
    /**
     * <p>Maps to table column: {@code LAST_COST}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 4, message = "LAST_COST should have an integer component no longer than 6 and an a fraction component of exact size 4")
    @Column(name = "LAST_COST")
    private BigDecimal lastCost;
    /**
     * <p>Maps to table column: {@code CORE_COST}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 4, message = "CORE_COST should have an integer component no longer than 6 and an a fraction component of exact size 4")
    @Column(name = "CORE_COST")
    private BigDecimal coreCost;
    /**
     * <p>Maps to table column: {@code NET_COST}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 4, message = "NET_COST should have an integer component no longer than 6 and an a fraction component of exact size 4")
    @Column(name = "NET_COST")
    private BigDecimal netCost;
    /**
     * <p>Maps to table column: {@code WHSE_COST_AMT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 4, message = "WHSE_COST_AMT should have an integer component no longer than 6 and an a fraction component of exact size 4")
    @Column(name = "WHSE_COST_AMT")
    private BigDecimal whseCostAmt;
    /**
     * <p>Maps to table column: {@code COST_ELEMENT1_AMT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 4, message = "COST_ELEMENT1_AMT should have an integer component no longer than 6 and an a fraction component of exact size 4")
    @Column(name = "COST_ELEMENT1_AMT")
    private BigDecimal costElement1Amt;
    /**
     * <p>Maps to table column: {@code COST_ELEMENT2_AMT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 4, message = "COST_ELEMENT2_AMT should have an integer component no longer than 6 and an a fraction component of exact size 4")
    @Column(name = "COST_ELEMENT2_AMT")
    private BigDecimal costElement2Amt;
    /**
     * <p>Maps to table column: {@code BATCH_SITE_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 9, fraction = 0, message = "BATCH_SITE_NO should have an integer component no longer than 9 and an a fraction component of exact size 0")
    @Column(name = "BATCH_SITE_NO")
    private Integer batchSiteNo;
    /**
     * <p>Maps to table column: {@code BATCH_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 0, message = "BATCH_ID should have an integer component no longer than 10 and an a fraction component of exact size 0")
    @Column(name = "BATCH_ID")
    private Long batchId;
    /**
     * <p>Maps to table column: {@code LOQ}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 2, message = "LOQ should have an integer component no longer than 10 and an a fraction component of exact size 2")
    @Column(name = "LOQ")
    private BigDecimal loq;
    /**
     * <p>Maps to table column: {@code FORM_DIMENSION}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 16, message = "FORM_DIMENSION should contain less than or equal to 16 characters")
    @Column(name = "FORM_DIMENSION")
    private String formDimension;
    /**
     * <p>Maps to table column: {@code FREIGHT_AMT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "FREIGHT_AMT should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "FREIGHT_AMT")
    private BigDecimal freightAmt;
    /**
     * <p>Maps to table column: {@code FREIGHT_PCT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 2, message = "FREIGHT_PCT should have an integer component no longer than 3 and an a fraction component of exact size 2")
    @Column(name = "FREIGHT_PCT")
    private BigDecimal freightPct;
    /**
     * <p>Maps to table column: {@code DATE_CHANGED}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "DATE_CHANGED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateChanged;
    /**
     * <p>Maps to table column: {@code ORDER_AVAILABILITY_STATUS}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 5, message = "ORDER_AVAILABILITY_STATUS should contain less than or equal to 5 characters")
    @Column(name = "ORDER_AVAILABILITY_STATUS")
    private String orderAvailabilityStatus;
    /**
     * <p>Maps to table column: {@code ORDER_AVAILABILITY_ACTIVE_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "ORDER_AVAILABILITY_ACTIVE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderAvailabilityActiveDt;
    /**
     * <p>Maps to table column: {@code EXTERNAL_REF_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 240, message = "EXTERNAL_REF_ID should contain less than or equal to 240 characters")
    @Column(name = "EXTERNAL_REF_ID")
    private String externalRefId;
    /**
     * <p>Maps to table column: {@code FREIGHT_TOLERANCE_AMT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "FREIGHT_TOLERANCE_AMT should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "FREIGHT_TOLERANCE_AMT")
    private BigDecimal freightToleranceAmt;
    /**
     * <p>Maps to table column: {@code FREIGHT_TOLERANCE_AMT_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "FREIGHT_TOLERANCE_AMT_FL should contain less than or equal to 1 characters")
    @Column(name = "FREIGHT_TOLERANCE_AMT_FL")
    private String freightToleranceAmtFl;
    /**
     * <p>Maps to table column: {@code FREIGHT_TOLERANCE_PCT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 2, message = "FREIGHT_TOLERANCE_PCT should have an integer component no longer than 3 and an a fraction component of exact size 2")
    @Column(name = "FREIGHT_TOLERANCE_PCT")
    private BigDecimal freightTolerancePct;
    /**
     * <p>Maps to table column: {@code FREIGHT_TOLERANCE_PCT_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "FREIGHT_TOLERANCE_PCT_FL should contain less than or equal to 1 characters")
    @Column(name = "FREIGHT_TOLERANCE_PCT_FL")
    private String freightTolerancePctFl;
    /**
     * <p>Maps to table column: {@code INV_TOLERANCE_AMT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "INV_TOLERANCE_AMT should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "INV_TOLERANCE_AMT")
    private BigDecimal invToleranceAmt;
    /**
     * <p>Maps to table column: {@code INV_TOLERANCE_AMT_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "INV_TOLERANCE_AMT_FL should contain less than or equal to 1 characters")
    @Column(name = "INV_TOLERANCE_AMT_FL")
    private String invToleranceAmtFl;
    /**
     * <p>Maps to table column: {@code INV_TOLERANCE_PCT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 2, message = "INV_TOLERANCE_PCT should have an integer component no longer than 3 and an a fraction component of exact size 2")
    @Column(name = "INV_TOLERANCE_PCT")
    private BigDecimal invTolerancePct;
    /**
     * <p>Maps to table column: {@code INV_TOLERANCE_PCT_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "INV_TOLERANCE_PCT_FL should contain less than or equal to 1 characters")
    @Column(name = "INV_TOLERANCE_PCT_FL")
    private String invTolerancePctFl;
    /**
     * <p>Maps to table column: {@code INV_TOLERANCE_QTY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "INV_TOLERANCE_QTY should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "INV_TOLERANCE_QTY")
    private BigDecimal invToleranceQty;
    /**
     * <p>Maps to table column: {@code INV_TOLERANCE_QTY_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "INV_TOLERANCE_QTY_FL should contain less than or equal to 1 characters")
    @Column(name = "INV_TOLERANCE_QTY_FL")
    private String invToleranceQtyFl;
    /**
     * <p>Maps to table column: {@code COST_FACTOR}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 2, message = "COST_FACTOR should have an integer component no longer than 3 and an a fraction component of exact size 2")
    @Column(name = "COST_FACTOR")
    private BigDecimal costFactor;
    /**
     * <p>Maps to table column: {@code OPERATION_TYPE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "OPERATION_TYPE should contain less than or equal to 1 characters")
    @Column(name = "OPERATION_TYPE")
    private String operationType;
    /**
     * <p>Maps to table column: {@code REPLICATION_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 16, fraction = 0, message = "REPLICATION_NO should have an integer component no longer than 16 and an a fraction component of exact size 0")
    @Column(name = "REPLICATION_NO")
    private Long replicationNo;
    /**
     * <p>Maps to table column: {@code REGISTER_REPLICATION_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 16, fraction = 0, message = "REGISTER_REPLICATION_NO should have an integer component no longer than 16 and an a fraction component of exact size 0")
    @Column(name = "REGISTER_REPLICATION_NO")
    private Long registerReplicationNo;
    /**
     * <p>Maps to table column: {@code DATE_CREATED}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "DATE_CREATED", insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    /**
     * <p>Maps to table column: {@code USER_CREATED}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "USER_CREATED", insertable = true, updatable = false)
    private String userCreated;
    /**
     * <p>Maps to table column: {@code DATE_MODIFIED}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "DATE_MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    /**
     * <p>Maps to table column: {@code USER_MODIFIED}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "USER_MODIFIED")
    private String userModified;
    /**
     * <p>Maps to table column: {@code LAST_COST_QTY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 2, message = "LAST_COST_QTY should have an integer component no longer than 10 and an a fraction component of exact size 2")
    @Column(name = "LAST_COST_QTY")
    private BigDecimal lastCostQty;
    /**
     * <p>Maps to table column: {@code UPLIFT_GROUP_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 0, message = "UPLIFT_GROUP_ID should have an integer component no longer than 8 and an a fraction component of exact size 0")
    @Column(name = "UPLIFT_GROUP_ID")
    private Integer upliftGroupId;
    /**
     * <p>Maps to table column: {@code VENDOR_CURRENCY_PACK_COST}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 4, message = "VENDOR_CURRENCY_PACK_COST should have an integer component no longer than 6 and an a fraction component of exact size 4")
    @Column(name = "VENDOR_CURRENCY_PACK_COST")
    private BigDecimal vendorCurrencyPackCost;
    /**
     * <p>Maps to table column: {@code SUPPLIER_REC_SCHED}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "SUPPLIER_REC_SCHED should contain less than or equal to 1 characters")
    @Column(name = "SUPPLIER_REC_SCHED")
    private String supplierRecSched;
    /**
     * <p>Maps to table column: {@code UNIT_COST_UM}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 5, message = "UNIT_COST_UM should contain less than or equal to 5 characters")
    @Column(name = "UNIT_COST_UM")
    private String unitCostUm;
    /**
     * <p>Maps to table column: {@code RELEASE_HORIZON}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 0, message = "RELEASE_HORIZON should have an integer component no longer than 6 and an a fraction component of exact size 0")
    @Column(name = "RELEASE_HORIZON")
    private Integer releaseHorizon;
    /**
     * <p>Maps to table column: {@code PACK_WEIGHT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PACK_WEIGHT should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PACK_WEIGHT")
    private BigDecimal packWeight;
    /**
     * <p>Maps to table column: {@code PACK_CUBE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PACK_CUBE should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PACK_CUBE")
    private BigDecimal packCube;
    /**
     * <p>Maps to table column: {@code ROUTE}.</p><p><b>Schema Remarks:</b> Delivery route (Inventory Management).</p>
     * 
     */
    @Size(max = 25, message = "ROUTE should contain less than or equal to 25 characters")
    @Column(name = "ROUTE")
    private String route;
    /**
     * <p>Maps to table column: {@code IMMEDIATE_SUPPLIER}.</p><p><b>Schema Remarks:</b> Vendor ID or Site Number of immediate supplier.</p>
     * 
     */
    @Size(max = 9, message = "IMMEDIATE_SUPPLIER should contain less than or equal to 9 characters")
    @Column(name = "IMMEDIATE_SUPPLIER")
    private String immediateSupplier;
    /**
     * <p>Maps to table column: {@code EXTERNAL_INTERNAL_SUPPLIER}.</p><p><b>Schema Remarks:</b> E for external or I for internal. I is used to indicate a site.</p>
     * 
     */
    @Size(max = 1, message = "EXTERNAL_INTERNAL_SUPPLIER should contain less than or equal to 1 characters")
    @Column(name = "EXTERNAL_INTERNAL_SUPPLIER")
    private String externalInternalSupplier;
    /**
     * <p>Maps to table column: {@code LENGTH}.</p><p><b>Schema Remarks:</b> Length  Details.</p>
     * 
     */
    @Digits(integer = 3, fraction = 3, message = "LENGTH should have an integer component no longer than 3 and an a fraction component of exact size 3")
    @Column(name = "LENGTH")
    private BigDecimal length;
    /**
     * <p>Maps to table column: {@code WIDTH}.</p><p><b>Schema Remarks:</b> Width Details.</p>
     * 
     */
    @Digits(integer = 3, fraction = 3, message = "WIDTH should have an integer component no longer than 3 and an a fraction component of exact size 3")
    @Column(name = "WIDTH")
    private BigDecimal width;
    /**
     * <p>Maps to table column: {@code HEIGHT}.</p><p><b>Schema Remarks:</b> Height Details.</p>
     * 
     */
    @Digits(integer = 3, fraction = 3, message = "HEIGHT should have an integer component no longer than 3 and an a fraction component of exact size 3")
    @Column(name = "HEIGHT")
    private BigDecimal height;
    /**
     * <p>Maps to table column: {@code WEIGHT}.</p><p><b>Schema Remarks:</b> Weight Details.</p>
     * 
     */
    @Digits(integer = 3, fraction = 3, message = "WEIGHT should have an integer component no longer than 3 and an a fraction component of exact size 3")
    @Column(name = "WEIGHT")
    private BigDecimal weight;
    /**
     * <p>Maps to table column: {@code CUBE}.</p><p><b>Schema Remarks:</b> Cube Details.</p>
     * 
     */
    @Digits(integer = 6, fraction = 3, message = "CUBE should have an integer component no longer than 6 and an a fraction component of exact size 3")
    @Column(name = "CUBE")
    private BigDecimal cube;
    /**
     * <p>Maps to table column: {@code LANDED_COST_ELEMENTS}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 500, message = "LANDED_COST_ELEMENTS should contain less than or equal to 500 characters")
    @Column(name = "LANDED_COST_ELEMENTS")
    private String landedCostElements;
    /**
     * <p>Maps to table column: {@code ADDITIONAL_VENDOR_ITEM}.</p><p><b>Schema Remarks:</b> Used to store any additional vendor item information that can be used for searching.</p>
     * 
     */
    @Size(max = 50, message = "ADDITIONAL_VENDOR_ITEM should contain less than or equal to 50 characters")
    @Column(name = "ADDITIONAL_VENDOR_ITEM")
    private String additionalVendorItem;
    /**
     * <p>Maps to table column: {@code DEFAULT_CLASS_CODE}.</p><p><b>Schema Remarks:</b> Contains the default class code for this item</p>
     * 
     */
    @Size(max = 5, message = "DEFAULT_CLASS_CODE should contain less than or equal to 5 characters")
    @Column(name = "DEFAULT_CLASS_CODE")
    private String defaultClassCode;
    /**
     * <p>Static value for named query 'VENDOR_ITEM_BY_VENDOR'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo and v.vendorId = :vendorId }
     * 
     */
    public final static String VENDOR_ITEM_BY_VENDOR = "VENDOR_ITEM_BY_VENDOR";
    /**
     * <p>Static value for named query 'VENDOR_ITEM_BY_VENDOR_AND_OWNER'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorItemEntity v Where v.vendorId = :vendorId and exists (select 'x' from SiteEntity s where s.siteNo = v.siteNo and s.ownerId in (:ownerIdList)) }
     * 
     */
    public final static String VENDOR_ITEM_BY_VENDOR_AND_OWNER = "VENDOR_ITEM_BY_VENDOR_AND_OWNER";
    /**
     * <p>Static value for named query 'VENDOR_ITEM_COUNT_BY_VENDOR_AND_OWNER'<p>
     * <p>JPA Query:
     * </p>{@code Select count(v.seqNo) from VendorItemEntity v Where v.vendorId = :vendorId and exists (select 'x' from SiteEntity s where s.siteNo = v.siteNo and s.ownerId in (:ownerIdList)) }
     * 
     */
    public final static String VENDOR_ITEM_COUNT_BY_VENDOR_AND_OWNER = "VENDOR_ITEM_COUNT_BY_VENDOR_AND_OWNER";
    /**
     * <p>Static value for named query 'VENDOR_ITEM_COUNT_BY_VENDOR_SKU_AND_SITE'<p>
     * <p>JPA Query:
     * </p>{@code Select count(v.seqNo)  from VendorItemEntity v Where v.vendorId = :vendorId  and v.siteNo = :siteNo and v.skuNo = :skuNo }
     * 
     */
    public final static String VENDOR_ITEM_COUNT_BY_VENDOR_SKU_AND_SITE = "VENDOR_ITEM_COUNT_BY_VENDOR_SKU_AND_SITE";
    /**
     * <p>Static value for named query 'DELETE_VENDORS_BY_ITEMS'<p>
     * <p>JPA Query:
     * </p>{@code delete from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo }
     * 
     */
    public final static String DELETE_VENDORS_BY_ITEMS = "DELETE_VENDORS_BY_ITEMS";
    /**
     * <p>Static value for named query 'VENDORS_BY_ITEMS'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo }
     * 
     */
    public final static String VENDORS_BY_ITEMS = "VENDORS_BY_ITEMS";
    /**
     * <p>Static value for named query 'VENDOR_ITEMS_BY_SITES_SKUS'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorItemEntity v Where v.skuNo In (:skuNos) and v.siteNo In (:siteNos) }
     * 
     */
    public final static String VENDOR_ITEMS_BY_SITES_SKUS = "VENDOR_ITEMS_BY_SITES_SKUS";
    /**
     * <p>Static value for named query 'PRIMARY_VENDOR'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo and v.venType = 'P' And v.mainVendorItemFl = 'Y' }
     * 
     */
    public final static String PRIMARY_VENDOR = "PRIMARY_VENDOR";
    /**
     * <p>Static value for named query 'PRIMARY_VENDOR_FOR_SKULIST'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorItemEntity v Where v.skuNo in :skuList and v.siteNo = :siteNo and v.venType = 'P' And v.mainVendorItemFl = 'Y' }
     * 
     */
    public final static String PRIMARY_VENDOR_FOR_SKULIST = "PRIMARY_VENDOR_FOR_SKULIST";
    /**
     * <p>Static value for named query 'GET_MAIN_VENDOR_ITEM'<p>
     * <p>JPA Query:
     * </p>{@code Select v From VendorItemEntity v Where v.skuNo = :skuNo And v.siteNo = :siteNo And v.vendorId = :vendorId And v.mainVendorItemFl = 'Y' }
     * 
     */
    public final static String GET_MAIN_VENDOR_ITEM = "GET_MAIN_VENDOR_ITEM";
    /**
     * <p>Static value for named query 'GET_MAIN_VENDOR_ITEMS_FOR_SITES'<p>
     * <p>JPA Query:
     * </p>{@code Select v From VendorItemEntity v Where v.skuNo = :skuNo And v.siteNo IN :siteList And v.vendorId = :vendorId And v.mainVendorItemFl = 'Y' }
     * 
     */
    public final static String GET_MAIN_VENDOR_ITEMS_FOR_SITES = "GET_MAIN_VENDOR_ITEMS_FOR_SITES";
    /**
     * <p>Static value for named query 'VENDOR_ITEM_BY_VENDOR_SKU_SITE_PACKQTY'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorItemEntity v Where v.vendorId = :vendorId and v.skuNo = :skuNo and v.siteNo = :siteNo and v.packQty = :packQty }
     * 
     */
    public final static String VENDOR_ITEM_BY_VENDOR_SKU_SITE_PACKQTY = "VENDOR_ITEM_BY_VENDOR_SKU_SITE_PACKQTY";
    /**
     * <p>Static value for named query 'GET_ALL_VENDOR_ITEMS_FOR_SKU'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo }
     * 
     */
    public final static String GET_ALL_VENDOR_ITEMS_FOR_SKU = "GET_ALL_VENDOR_ITEMS_FOR_SKU";
    /**
     * <p>Static value for named query 'GET_ALL_VENDOR_ITEMS_FOR_SKU_SITE'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo }
     * 
     */
    public final static String GET_ALL_VENDOR_ITEMS_FOR_SKU_SITE = "GET_ALL_VENDOR_ITEMS_FOR_SKU_SITE";
    /**
     * <p>Static value for named query 'GET_ALL_VENDOR_ITEMS_FOR_SKU_SITE_VENDOR'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.siteNo = :siteNo and v.vendorId = :vendorId }
     * 
     */
    public final static String GET_ALL_VENDOR_ITEMS_FOR_SKU_SITE_VENDOR = "GET_ALL_VENDOR_ITEMS_FOR_SKU_SITE_VENDOR";
    /**
     * <p>Static value for named query 'GET_ALL_VENDOR_ITEMS_FOR_SKU_AND_VENDOR'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorItemEntity v Where v.skuNo = :skuNo and v.vendorId = :vendorId }
     * 
     */
    public final static String GET_ALL_VENDOR_ITEMS_FOR_SKU_AND_VENDOR = "GET_ALL_VENDOR_ITEMS_FOR_SKU_AND_VENDOR";
    /**
     * <p>Static value for named query 'GET_VENDOR_ITEMS_FOR_SEQ_LIST'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorItemEntity v Where v.seqNo in :seqList }
     * 
     */
    public final static String GET_VENDOR_ITEMS_FOR_SEQ_LIST = "GET_VENDOR_ITEMS_FOR_SEQ_LIST";

    /**
     * <P>Empty constructor used to create new VendorItemEntity.</P>
     * 
     */
    public VendorItemEntity() {
        this.identifier = new VendorItemEntityId();
        	
        _init();
    }

    /**
     * Constructor used to create a new VendorItemEntity where the identifier will be created by copying the fields of the provided sourceId.
     * 
     */
    public VendorItemEntity(VendorItemEntityId sourceId) {
        this.identifier = new VendorItemEntityId(sourceId.getSeqNo());
        	
        _init();
    }

    /**
     * Constructor used to create a VendorItemEntity where the identifier will be created internally using the id arguments provided.  This is a convenient constructor used instead of instantiating a new identifier.
     * 
     */
    public VendorItemEntity(BigInteger seqNo) {
        this.identifier = new VendorItemEntityId(seqNo);
        	
        _init();
    }

    /**
     * Constructor used to create a VendorItemEntity where contents are provided by an object array typically returned by a native query
     * 
     */
    public VendorItemEntity(Object[] objectArray) {
        this.identifier = new VendorItemEntityId(((objectArray[ 0 ] == null)?null:((BigDecimal) objectArray[ 0 ]).toBigInteger()));
        	
        this.skuNo = ((objectArray[ 1 ] == null)?null:((BigDecimal) objectArray[ 1 ]).intValue());
        this.vendorId = ((objectArray[ 2 ] == null)?null:((String) objectArray[ 2 ]));
        this.siteNo = ((objectArray[ 3 ] == null)?null:((BigDecimal) objectArray[ 3 ]).intValue());
        this.venType = ((objectArray[ 4 ] == null)?null:((String) objectArray[ 4 ]));
        this.vendorItem = ((objectArray[ 5 ] == null)?null:((String) objectArray[ 5 ]));
        this.mainVendorItemFl = ((objectArray[ 6 ] == null)?null:((String) objectArray[ 6 ]));
        this.eoq = ((objectArray[ 7 ] == null)?null:((BigDecimal) objectArray[ 7 ]));
        this.leadTime = ((objectArray[ 8 ] == null)?null:((BigDecimal) objectArray[ 8 ]).intValue());
        this.unitCost = ((objectArray[ 9 ] == null)?null:((BigDecimal) objectArray[ 9 ]));
        this.packCost = ((objectArray[ 10 ] == null)?null:((BigDecimal) objectArray[ 10 ]));
        this.packQty = ((objectArray[ 11 ] == null)?null:((BigDecimal) objectArray[ 11 ]));
        this.masterPackQty = ((objectArray[ 12 ] == null)?null:((BigDecimal) objectArray[ 12 ]));
        this.packUm = ((objectArray[ 13 ] == null)?null:((String) objectArray[ 13 ]));
        this.lastCostchgDt = ((objectArray[ 14 ] == null)?null:new Date(((Timestamp) objectArray[ 14 ]).getTime()));
        this.lastCost = ((objectArray[ 15 ] == null)?null:((BigDecimal) objectArray[ 15 ]));
        this.coreCost = ((objectArray[ 16 ] == null)?null:((BigDecimal) objectArray[ 16 ]));
        this.netCost = ((objectArray[ 17 ] == null)?null:((BigDecimal) objectArray[ 17 ]));
        this.whseCostAmt = ((objectArray[ 18 ] == null)?null:((BigDecimal) objectArray[ 18 ]));
        this.costElement1Amt = ((objectArray[ 19 ] == null)?null:((BigDecimal) objectArray[ 19 ]));
        this.costElement2Amt = ((objectArray[ 20 ] == null)?null:((BigDecimal) objectArray[ 20 ]));
        this.batchSiteNo = ((objectArray[ 21 ] == null)?null:((BigDecimal) objectArray[ 21 ]).intValue());
        this.batchId = ((objectArray[ 22 ] == null)?null:((BigDecimal) objectArray[ 22 ]).longValue());
        this.loq = ((objectArray[ 23 ] == null)?null:((BigDecimal) objectArray[ 23 ]));
        this.formDimension = ((objectArray[ 24 ] == null)?null:((String) objectArray[ 24 ]));
        this.freightAmt = ((objectArray[ 25 ] == null)?null:((BigDecimal) objectArray[ 25 ]));
        this.freightPct = ((objectArray[ 26 ] == null)?null:((BigDecimal) objectArray[ 26 ]));
        this.dateChanged = ((objectArray[ 27 ] == null)?null:new Date(((Timestamp) objectArray[ 27 ]).getTime()));
        this.orderAvailabilityStatus = ((objectArray[ 28 ] == null)?null:((String) objectArray[ 28 ]));
        this.orderAvailabilityActiveDt = ((objectArray[ 29 ] == null)?null:new Date(((Timestamp) objectArray[ 29 ]).getTime()));
        this.externalRefId = ((objectArray[ 30 ] == null)?null:((String) objectArray[ 30 ]));
        this.freightToleranceAmt = ((objectArray[ 31 ] == null)?null:((BigDecimal) objectArray[ 31 ]));
        this.freightToleranceAmtFl = ((objectArray[ 32 ] == null)?null:((String) objectArray[ 32 ]));
        this.freightTolerancePct = ((objectArray[ 33 ] == null)?null:((BigDecimal) objectArray[ 33 ]));
        this.freightTolerancePctFl = ((objectArray[ 34 ] == null)?null:((String) objectArray[ 34 ]));
        this.invToleranceAmt = ((objectArray[ 35 ] == null)?null:((BigDecimal) objectArray[ 35 ]));
        this.invToleranceAmtFl = ((objectArray[ 36 ] == null)?null:((String) objectArray[ 36 ]));
        this.invTolerancePct = ((objectArray[ 37 ] == null)?null:((BigDecimal) objectArray[ 37 ]));
        this.invTolerancePctFl = ((objectArray[ 38 ] == null)?null:((String) objectArray[ 38 ]));
        this.invToleranceQty = ((objectArray[ 39 ] == null)?null:((BigDecimal) objectArray[ 39 ]));
        this.invToleranceQtyFl = ((objectArray[ 40 ] == null)?null:((String) objectArray[ 40 ]));
        this.costFactor = ((objectArray[ 41 ] == null)?null:((BigDecimal) objectArray[ 41 ]));
        this.operationType = ((objectArray[ 42 ] == null)?null:((String) objectArray[ 42 ]));
        this.replicationNo = ((objectArray[ 43 ] == null)?null:((BigDecimal) objectArray[ 43 ]).longValue());
        this.registerReplicationNo = ((objectArray[ 44 ] == null)?null:((BigDecimal) objectArray[ 44 ]).longValue());
        this.dateCreated = ((objectArray[ 45 ] == null)?null:new Date(((Timestamp) objectArray[ 45 ]).getTime()));
        this.userCreated = ((objectArray[ 46 ] == null)?null:((String) objectArray[ 46 ]));
        this.dateModified = ((objectArray[ 47 ] == null)?null:new Date(((Timestamp) objectArray[ 47 ]).getTime()));
        this.userModified = ((objectArray[ 48 ] == null)?null:((String) objectArray[ 48 ]));
        this.lastCostQty = ((objectArray[ 49 ] == null)?null:((BigDecimal) objectArray[ 49 ]));
        this.upliftGroupId = ((objectArray[ 50 ] == null)?null:((BigDecimal) objectArray[ 50 ]).intValue());
        this.vendorCurrencyPackCost = ((objectArray[ 51 ] == null)?null:((BigDecimal) objectArray[ 51 ]));
        this.supplierRecSched = ((objectArray[ 52 ] == null)?null:((String) objectArray[ 52 ]));
        this.unitCostUm = ((objectArray[ 53 ] == null)?null:((String) objectArray[ 53 ]));
        this.releaseHorizon = ((objectArray[ 54 ] == null)?null:((BigDecimal) objectArray[ 54 ]).intValue());
        this.packWeight = ((objectArray[ 55 ] == null)?null:((BigDecimal) objectArray[ 55 ]));
        this.packCube = ((objectArray[ 56 ] == null)?null:((BigDecimal) objectArray[ 56 ]));
        this.route = ((objectArray[ 57 ] == null)?null:((String) objectArray[ 57 ]));
        this.immediateSupplier = ((objectArray[ 58 ] == null)?null:((String) objectArray[ 58 ]));
        this.externalInternalSupplier = ((objectArray[ 59 ] == null)?null:((String) objectArray[ 59 ]));
        this.length = ((objectArray[ 60 ] == null)?null:((BigDecimal) objectArray[ 60 ]));
        this.width = ((objectArray[ 61 ] == null)?null:((BigDecimal) objectArray[ 61 ]));
        this.height = ((objectArray[ 62 ] == null)?null:((BigDecimal) objectArray[ 62 ]));
        this.weight = ((objectArray[ 63 ] == null)?null:((BigDecimal) objectArray[ 63 ]));
        this.cube = ((objectArray[ 64 ] == null)?null:((BigDecimal) objectArray[ 64 ]));
        this.landedCostElements = ((objectArray[ 65 ] == null)?null:((String) objectArray[ 65 ]));
        this.additionalVendorItem = ((objectArray[ 66 ] == null)?null:((String) objectArray[ 66 ]));
        this.defaultClassCode = ((objectArray[ 67 ] == null)?null:((String) objectArray[ 67 ]));
        	
        _init();
    }

    /**
     * <P>Copy constructor used to copy a VendorItemEntity where the identifier will be created by copying the fields of the provided sourceId.</P>
     * 
     */
    public VendorItemEntity(VendorItemEntity source, VendorItemEntityId sourceId) {
        this.identifier = new VendorItemEntityId(sourceId.getSeqNo());
        	
        _init();
        	
        this.skuNo = source.getSkuNo();
        this.vendorId = source.getVendorId();
        this.siteNo = source.getSiteNo();
        this.venType = source.getVenType();
        this.vendorItem = source.getVendorItem();
        this.mainVendorItemFl = source.getMainVendorItemFl();
        this.eoq = source.getEoq();
        this.leadTime = source.getLeadTime();
        this.unitCost = source.getUnitCost();
        this.packCost = source.getPackCost();
        this.packQty = source.getPackQty();
        this.masterPackQty = source.getMasterPackQty();
        this.packUm = source.getPackUm();
        this.lastCostchgDt = source.getLastCostchgDt();
        this.lastCost = source.getLastCost();
        this.coreCost = source.getCoreCost();
        this.netCost = source.getNetCost();
        this.whseCostAmt = source.getWhseCostAmt();
        this.costElement1Amt = source.getCostElement1Amt();
        this.costElement2Amt = source.getCostElement2Amt();
        this.batchSiteNo = source.getBatchSiteNo();
        this.batchId = source.getBatchId();
        this.loq = source.getLoq();
        this.formDimension = source.getFormDimension();
        this.freightAmt = source.getFreightAmt();
        this.freightPct = source.getFreightPct();
        this.dateChanged = source.getDateChanged();
        this.orderAvailabilityStatus = source.getOrderAvailabilityStatus();
        this.orderAvailabilityActiveDt = source.getOrderAvailabilityActiveDt();
        this.externalRefId = source.getExternalRefId();
        this.freightToleranceAmt = source.getFreightToleranceAmt();
        this.freightToleranceAmtFl = source.getFreightToleranceAmtFl();
        this.freightTolerancePct = source.getFreightTolerancePct();
        this.freightTolerancePctFl = source.getFreightTolerancePctFl();
        this.invToleranceAmt = source.getInvToleranceAmt();
        this.invToleranceAmtFl = source.getInvToleranceAmtFl();
        this.invTolerancePct = source.getInvTolerancePct();
        this.invTolerancePctFl = source.getInvTolerancePctFl();
        this.invToleranceQty = source.getInvToleranceQty();
        this.invToleranceQtyFl = source.getInvToleranceQtyFl();
        this.costFactor = source.getCostFactor();
        this.operationType = source.getOperationType();
        this.replicationNo = source.getReplicationNo();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.lastCostQty = source.getLastCostQty();
        this.upliftGroupId = source.getUpliftGroupId();
        this.vendorCurrencyPackCost = source.getVendorCurrencyPackCost();
        this.supplierRecSched = source.getSupplierRecSched();
        this.unitCostUm = source.getUnitCostUm();
        this.releaseHorizon = source.getReleaseHorizon();
        this.packWeight = source.getPackWeight();
        this.packCube = source.getPackCube();
        this.route = source.getRoute();
        this.immediateSupplier = source.getImmediateSupplier();
        this.externalInternalSupplier = source.getExternalInternalSupplier();
        this.length = source.getLength();
        this.width = source.getWidth();
        this.height = source.getHeight();
        this.weight = source.getWeight();
        this.cube = source.getCube();
        this.landedCostElements = source.getLandedCostElements();
        this.additionalVendorItem = source.getAdditionalVendorItem();
        this.defaultClassCode = source.getDefaultClassCode();
    }

    /**
     * <P>Copy constructor used to copy a VendorItemEntity including its identifier.</P>
     * 
     */
    public VendorItemEntity(VendorItemEntity source) {
        this.identifier = new VendorItemEntityId(source.getIdentifier().getSeqNo());
        	
        _init();
        	
        this.skuNo = source.getSkuNo();
        this.vendorId = source.getVendorId();
        this.siteNo = source.getSiteNo();
        this.venType = source.getVenType();
        this.vendorItem = source.getVendorItem();
        this.mainVendorItemFl = source.getMainVendorItemFl();
        this.eoq = source.getEoq();
        this.leadTime = source.getLeadTime();
        this.unitCost = source.getUnitCost();
        this.packCost = source.getPackCost();
        this.packQty = source.getPackQty();
        this.masterPackQty = source.getMasterPackQty();
        this.packUm = source.getPackUm();
        this.lastCostchgDt = source.getLastCostchgDt();
        this.lastCost = source.getLastCost();
        this.coreCost = source.getCoreCost();
        this.netCost = source.getNetCost();
        this.whseCostAmt = source.getWhseCostAmt();
        this.costElement1Amt = source.getCostElement1Amt();
        this.costElement2Amt = source.getCostElement2Amt();
        this.batchSiteNo = source.getBatchSiteNo();
        this.batchId = source.getBatchId();
        this.loq = source.getLoq();
        this.formDimension = source.getFormDimension();
        this.freightAmt = source.getFreightAmt();
        this.freightPct = source.getFreightPct();
        this.dateChanged = source.getDateChanged();
        this.orderAvailabilityStatus = source.getOrderAvailabilityStatus();
        this.orderAvailabilityActiveDt = source.getOrderAvailabilityActiveDt();
        this.externalRefId = source.getExternalRefId();
        this.freightToleranceAmt = source.getFreightToleranceAmt();
        this.freightToleranceAmtFl = source.getFreightToleranceAmtFl();
        this.freightTolerancePct = source.getFreightTolerancePct();
        this.freightTolerancePctFl = source.getFreightTolerancePctFl();
        this.invToleranceAmt = source.getInvToleranceAmt();
        this.invToleranceAmtFl = source.getInvToleranceAmtFl();
        this.invTolerancePct = source.getInvTolerancePct();
        this.invTolerancePctFl = source.getInvTolerancePctFl();
        this.invToleranceQty = source.getInvToleranceQty();
        this.invToleranceQtyFl = source.getInvToleranceQtyFl();
        this.costFactor = source.getCostFactor();
        this.operationType = source.getOperationType();
        this.replicationNo = source.getReplicationNo();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.lastCostQty = source.getLastCostQty();
        this.upliftGroupId = source.getUpliftGroupId();
        this.vendorCurrencyPackCost = source.getVendorCurrencyPackCost();
        this.supplierRecSched = source.getSupplierRecSched();
        this.unitCostUm = source.getUnitCostUm();
        this.releaseHorizon = source.getReleaseHorizon();
        this.packWeight = source.getPackWeight();
        this.packCube = source.getPackCube();
        this.route = source.getRoute();
        this.immediateSupplier = source.getImmediateSupplier();
        this.externalInternalSupplier = source.getExternalInternalSupplier();
        this.length = source.getLength();
        this.width = source.getWidth();
        this.height = source.getHeight();
        this.weight = source.getWeight();
        this.cube = source.getCube();
        this.landedCostElements = source.getLandedCostElements();
        this.additionalVendorItem = source.getAdditionalVendorItem();
        this.defaultClassCode = source.getDefaultClassCode();
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public VendorItemEntityId getIdentifier() {
        return this.identifier;
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public void setIdentifier(VendorItemEntityId identifier) {
        this.identifier = identifier;
    }

    /**
     * Getter for {@link VendorItemEntityId#seqNo identifier.seqNo}
     * 
     */
    @Access(AccessType.PROPERTY)
    @Column(name = "SEQ_NO", insertable = false, updatable = false)
    public BigInteger getSeqNo() {
        return this.identifier.getSeqNo();
    }

    /**
     * Setter for {@link VendorItemEntityId#seqNo identifier.seqNo}
     * 
     */
    public BigInteger setSeqNo(BigInteger seqNo) {
        this.identifier.setSeqNo(seqNo);
        return this.identifier.getSeqNo();
    }

    /**
     * Getter for {@link #skuNo skuNo}
     * 
     */
    public Integer getSkuNo() {
        return this.skuNo;
    }

    /**
     * Setter for {@link #skuNo skuNo}
     * 
     */
    public Integer setSkuNo(Integer skuNo) {
        this.skuNo = skuNo;
        return this.skuNo;
    }

    /**
     * Getter for {@link #vendorId vendorId}
     * 
     */
    public String getVendorId() {
        return this.vendorId;
    }

    /**
     * Setter for {@link #vendorId vendorId}
     * 
     */
    public String setVendorId(String vendorId) {
        this.vendorId = vendorId;
        return this.vendorId;
    }

    /**
     * Getter for {@link #siteNo siteNo}
     * 
     */
    public Integer getSiteNo() {
        return this.siteNo;
    }

    /**
     * Setter for {@link #siteNo siteNo}
     * 
     */
    public Integer setSiteNo(Integer siteNo) {
        this.siteNo = siteNo;
        return this.siteNo;
    }

    /**
     * Getter for {@link #venType venType}
     * 
     */
    public String getVenType() {
        return this.venType;
    }

    /**
     * Setter for {@link #venType venType}
     * 
     */
    public String setVenType(String venType) {
        this.venType = venType;
        return this.venType;
    }

    /**
     * Getter for {@link #vendorItem vendorItem}
     * 
     */
    public String getVendorItem() {
        return this.vendorItem;
    }

    /**
     * Setter for {@link #vendorItem vendorItem}
     * 
     */
    public String setVendorItem(String vendorItem) {
        this.vendorItem = vendorItem;
        return this.vendorItem;
    }

    /**
     * Getter for {@link #mainVendorItemFl mainVendorItemFl}
     * 
     */
    public String getMainVendorItemFl() {
        return this.mainVendorItemFl;
    }

    /**
     * Setter for {@link #mainVendorItemFl mainVendorItemFl}
     * 
     */
    public String setMainVendorItemFl(String mainVendorItemFl) {
        this.mainVendorItemFl = mainVendorItemFl;
        return this.mainVendorItemFl;
    }

    /**
     * Getter for {@link #eoq eoq}
     * 
     */
    public BigDecimal getEoq() {
        return this.eoq;
    }

    /**
     * Setter for {@link #eoq eoq}
     * 
     */
    public BigDecimal setEoq(BigDecimal eoq) {
        if ((eoq!= null)&&(eoq.scale()!= 2)) {
            eoq = eoq.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.eoq = eoq;
        return this.eoq;
    }

    /**
     * Getter for {@link #leadTime leadTime}
     * 
     */
    public Integer getLeadTime() {
        return this.leadTime;
    }

    /**
     * Setter for {@link #leadTime leadTime}
     * 
     */
    public Integer setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
        return this.leadTime;
    }

    /**
     * Getter for {@link #unitCost unitCost}
     * 
     */
    public BigDecimal getUnitCost() {
        return this.unitCost;
    }

    /**
     * Setter for {@link #unitCost unitCost}
     * 
     */
    public BigDecimal setUnitCost(BigDecimal unitCost) {
        if ((unitCost!= null)&&(unitCost.scale()!= 4)) {
            unitCost = unitCost.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.unitCost = unitCost;
        return this.unitCost;
    }

    /**
     * Getter for {@link #packCost packCost}
     * 
     */
    public BigDecimal getPackCost() {
        return this.packCost;
    }

    /**
     * Setter for {@link #packCost packCost}
     * 
     */
    public BigDecimal setPackCost(BigDecimal packCost) {
        if ((packCost!= null)&&(packCost.scale()!= 4)) {
            packCost = packCost.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.packCost = packCost;
        return this.packCost;
    }

    /**
     * Getter for {@link #packQty packQty}
     * 
     */
    public BigDecimal getPackQty() {
        return this.packQty;
    }

    /**
     * Setter for {@link #packQty packQty}
     * 
     */
    public BigDecimal setPackQty(BigDecimal packQty) {
        if ((packQty!= null)&&(packQty.scale()!= 2)) {
            packQty = packQty.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.packQty = packQty;
        return this.packQty;
    }

    /**
     * Getter for {@link #masterPackQty masterPackQty}
     * 
     */
    public BigDecimal getMasterPackQty() {
        return this.masterPackQty;
    }

    /**
     * Setter for {@link #masterPackQty masterPackQty}
     * 
     */
    public BigDecimal setMasterPackQty(BigDecimal masterPackQty) {
        if ((masterPackQty!= null)&&(masterPackQty.scale()!= 2)) {
            masterPackQty = masterPackQty.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.masterPackQty = masterPackQty;
        return this.masterPackQty;
    }

    /**
     * Getter for {@link #packUm packUm}
     * 
     */
    public String getPackUm() {
        return this.packUm;
    }

    /**
     * Setter for {@link #packUm packUm}
     * 
     */
    public String setPackUm(String packUm) {
        this.packUm = packUm;
        return this.packUm;
    }

    /**
     * Getter for {@link #lastCostchgDt lastCostchgDt}
     * 
     */
    public Date getLastCostchgDt() {
        return this.lastCostchgDt;
    }

    /**
     * Setter for {@link #lastCostchgDt lastCostchgDt}
     * 
     */
    public Date setLastCostchgDt(Date lastCostchgDt) {
        this.lastCostchgDt = lastCostchgDt;
        return this.lastCostchgDt;
    }

    /**
     * Getter for {@link #lastCost lastCost}
     * 
     */
    public BigDecimal getLastCost() {
        return this.lastCost;
    }

    /**
     * Setter for {@link #lastCost lastCost}
     * 
     */
    public BigDecimal setLastCost(BigDecimal lastCost) {
        if ((lastCost!= null)&&(lastCost.scale()!= 4)) {
            lastCost = lastCost.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.lastCost = lastCost;
        return this.lastCost;
    }

    /**
     * Getter for {@link #coreCost coreCost}
     * 
     */
    public BigDecimal getCoreCost() {
        return this.coreCost;
    }

    /**
     * Setter for {@link #coreCost coreCost}
     * 
     */
    public BigDecimal setCoreCost(BigDecimal coreCost) {
        if ((coreCost!= null)&&(coreCost.scale()!= 4)) {
            coreCost = coreCost.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.coreCost = coreCost;
        return this.coreCost;
    }

    /**
     * Getter for {@link #netCost netCost}
     * 
     */
    public BigDecimal getNetCost() {
        return this.netCost;
    }

    /**
     * Setter for {@link #netCost netCost}
     * 
     */
    public BigDecimal setNetCost(BigDecimal netCost) {
        if ((netCost!= null)&&(netCost.scale()!= 4)) {
            netCost = netCost.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.netCost = netCost;
        return this.netCost;
    }

    /**
     * Getter for {@link #whseCostAmt whseCostAmt}
     * 
     */
    public BigDecimal getWhseCostAmt() {
        return this.whseCostAmt;
    }

    /**
     * Setter for {@link #whseCostAmt whseCostAmt}
     * 
     */
    public BigDecimal setWhseCostAmt(BigDecimal whseCostAmt) {
        if ((whseCostAmt!= null)&&(whseCostAmt.scale()!= 4)) {
            whseCostAmt = whseCostAmt.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.whseCostAmt = whseCostAmt;
        return this.whseCostAmt;
    }

    /**
     * Getter for {@link #costElement1Amt costElement1Amt}
     * 
     */
    public BigDecimal getCostElement1Amt() {
        return this.costElement1Amt;
    }

    /**
     * Setter for {@link #costElement1Amt costElement1Amt}
     * 
     */
    public BigDecimal setCostElement1Amt(BigDecimal costElement1Amt) {
        if ((costElement1Amt!= null)&&(costElement1Amt.scale()!= 4)) {
            costElement1Amt = costElement1Amt.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.costElement1Amt = costElement1Amt;
        return this.costElement1Amt;
    }

    /**
     * Getter for {@link #costElement2Amt costElement2Amt}
     * 
     */
    public BigDecimal getCostElement2Amt() {
        return this.costElement2Amt;
    }

    /**
     * Setter for {@link #costElement2Amt costElement2Amt}
     * 
     */
    public BigDecimal setCostElement2Amt(BigDecimal costElement2Amt) {
        if ((costElement2Amt!= null)&&(costElement2Amt.scale()!= 4)) {
            costElement2Amt = costElement2Amt.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.costElement2Amt = costElement2Amt;
        return this.costElement2Amt;
    }

    /**
     * Getter for {@link #batchSiteNo batchSiteNo}
     * 
     */
    public Integer getBatchSiteNo() {
        return this.batchSiteNo;
    }

    /**
     * Setter for {@link #batchSiteNo batchSiteNo}
     * 
     */
    public Integer setBatchSiteNo(Integer batchSiteNo) {
        this.batchSiteNo = batchSiteNo;
        return this.batchSiteNo;
    }

    /**
     * Getter for {@link #batchId batchId}
     * 
     */
    public Long getBatchId() {
        return this.batchId;
    }

    /**
     * Setter for {@link #batchId batchId}
     * 
     */
    public Long setBatchId(Long batchId) {
        this.batchId = batchId;
        return this.batchId;
    }

    /**
     * Getter for {@link #loq loq}
     * 
     */
    public BigDecimal getLoq() {
        return this.loq;
    }

    /**
     * Setter for {@link #loq loq}
     * 
     */
    public BigDecimal setLoq(BigDecimal loq) {
        if ((loq!= null)&&(loq.scale()!= 2)) {
            loq = loq.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.loq = loq;
        return this.loq;
    }

    /**
     * Getter for {@link #formDimension formDimension}
     * 
     */
    public String getFormDimension() {
        return this.formDimension;
    }

    /**
     * Setter for {@link #formDimension formDimension}
     * 
     */
    public String setFormDimension(String formDimension) {
        this.formDimension = formDimension;
        return this.formDimension;
    }

    /**
     * Getter for {@link #freightAmt freightAmt}
     * 
     */
    public BigDecimal getFreightAmt() {
        return this.freightAmt;
    }

    /**
     * Setter for {@link #freightAmt freightAmt}
     * 
     */
    public BigDecimal setFreightAmt(BigDecimal freightAmt) {
        if ((freightAmt!= null)&&(freightAmt.scale()!= 2)) {
            freightAmt = freightAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.freightAmt = freightAmt;
        return this.freightAmt;
    }

    /**
     * Getter for {@link #freightPct freightPct}
     * 
     */
    public BigDecimal getFreightPct() {
        return this.freightPct;
    }

    /**
     * Setter for {@link #freightPct freightPct}
     * 
     */
    public BigDecimal setFreightPct(BigDecimal freightPct) {
        if ((freightPct!= null)&&(freightPct.scale()!= 2)) {
            freightPct = freightPct.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.freightPct = freightPct;
        return this.freightPct;
    }

    /**
     * Getter for {@link #dateChanged dateChanged}
     * 
     */
    public Date getDateChanged() {
        return this.dateChanged;
    }

    /**
     * Setter for {@link #dateChanged dateChanged}
     * 
     */
    public Date setDateChanged(Date dateChanged) {
        this.dateChanged = dateChanged;
        return this.dateChanged;
    }

    /**
     * Getter for {@link #orderAvailabilityStatus orderAvailabilityStatus}
     * 
     */
    public String getOrderAvailabilityStatus() {
        return this.orderAvailabilityStatus;
    }

    /**
     * Setter for {@link #orderAvailabilityStatus orderAvailabilityStatus}
     * 
     */
    public String setOrderAvailabilityStatus(String orderAvailabilityStatus) {
        this.orderAvailabilityStatus = orderAvailabilityStatus;
        return this.orderAvailabilityStatus;
    }

    /**
     * Getter for {@link #orderAvailabilityActiveDt orderAvailabilityActiveDt}
     * 
     */
    public Date getOrderAvailabilityActiveDt() {
        return this.orderAvailabilityActiveDt;
    }

    /**
     * Setter for {@link #orderAvailabilityActiveDt orderAvailabilityActiveDt}
     * 
     */
    public Date setOrderAvailabilityActiveDt(Date orderAvailabilityActiveDt) {
        this.orderAvailabilityActiveDt = orderAvailabilityActiveDt;
        return this.orderAvailabilityActiveDt;
    }

    /**
     * Getter for {@link #externalRefId externalRefId}
     * 
     */
    public String getExternalRefId() {
        return this.externalRefId;
    }

    /**
     * Setter for {@link #externalRefId externalRefId}
     * 
     */
    public String setExternalRefId(String externalRefId) {
        this.externalRefId = externalRefId;
        return this.externalRefId;
    }

    /**
     * Getter for {@link #freightToleranceAmt freightToleranceAmt}
     * 
     */
    public BigDecimal getFreightToleranceAmt() {
        return this.freightToleranceAmt;
    }

    /**
     * Setter for {@link #freightToleranceAmt freightToleranceAmt}
     * 
     */
    public BigDecimal setFreightToleranceAmt(BigDecimal freightToleranceAmt) {
        if ((freightToleranceAmt!= null)&&(freightToleranceAmt.scale()!= 2)) {
            freightToleranceAmt = freightToleranceAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.freightToleranceAmt = freightToleranceAmt;
        return this.freightToleranceAmt;
    }

    /**
     * Getter for {@link #freightToleranceAmtFl freightToleranceAmtFl}
     * 
     */
    public String getFreightToleranceAmtFl() {
        return this.freightToleranceAmtFl;
    }

    /**
     * Setter for {@link #freightToleranceAmtFl freightToleranceAmtFl}
     * 
     */
    public String setFreightToleranceAmtFl(String freightToleranceAmtFl) {
        this.freightToleranceAmtFl = freightToleranceAmtFl;
        return this.freightToleranceAmtFl;
    }

    /**
     * Getter for {@link #freightTolerancePct freightTolerancePct}
     * 
     */
    public BigDecimal getFreightTolerancePct() {
        return this.freightTolerancePct;
    }

    /**
     * Setter for {@link #freightTolerancePct freightTolerancePct}
     * 
     */
    public BigDecimal setFreightTolerancePct(BigDecimal freightTolerancePct) {
        if ((freightTolerancePct!= null)&&(freightTolerancePct.scale()!= 2)) {
            freightTolerancePct = freightTolerancePct.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.freightTolerancePct = freightTolerancePct;
        return this.freightTolerancePct;
    }

    /**
     * Getter for {@link #freightTolerancePctFl freightTolerancePctFl}
     * 
     */
    public String getFreightTolerancePctFl() {
        return this.freightTolerancePctFl;
    }

    /**
     * Setter for {@link #freightTolerancePctFl freightTolerancePctFl}
     * 
     */
    public String setFreightTolerancePctFl(String freightTolerancePctFl) {
        this.freightTolerancePctFl = freightTolerancePctFl;
        return this.freightTolerancePctFl;
    }

    /**
     * Getter for {@link #invToleranceAmt invToleranceAmt}
     * 
     */
    public BigDecimal getInvToleranceAmt() {
        return this.invToleranceAmt;
    }

    /**
     * Setter for {@link #invToleranceAmt invToleranceAmt}
     * 
     */
    public BigDecimal setInvToleranceAmt(BigDecimal invToleranceAmt) {
        if ((invToleranceAmt!= null)&&(invToleranceAmt.scale()!= 2)) {
            invToleranceAmt = invToleranceAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.invToleranceAmt = invToleranceAmt;
        return this.invToleranceAmt;
    }

    /**
     * Getter for {@link #invToleranceAmtFl invToleranceAmtFl}
     * 
     */
    public String getInvToleranceAmtFl() {
        return this.invToleranceAmtFl;
    }

    /**
     * Setter for {@link #invToleranceAmtFl invToleranceAmtFl}
     * 
     */
    public String setInvToleranceAmtFl(String invToleranceAmtFl) {
        this.invToleranceAmtFl = invToleranceAmtFl;
        return this.invToleranceAmtFl;
    }

    /**
     * Getter for {@link #invTolerancePct invTolerancePct}
     * 
     */
    public BigDecimal getInvTolerancePct() {
        return this.invTolerancePct;
    }

    /**
     * Setter for {@link #invTolerancePct invTolerancePct}
     * 
     */
    public BigDecimal setInvTolerancePct(BigDecimal invTolerancePct) {
        if ((invTolerancePct!= null)&&(invTolerancePct.scale()!= 2)) {
            invTolerancePct = invTolerancePct.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.invTolerancePct = invTolerancePct;
        return this.invTolerancePct;
    }

    /**
     * Getter for {@link #invTolerancePctFl invTolerancePctFl}
     * 
     */
    public String getInvTolerancePctFl() {
        return this.invTolerancePctFl;
    }

    /**
     * Setter for {@link #invTolerancePctFl invTolerancePctFl}
     * 
     */
    public String setInvTolerancePctFl(String invTolerancePctFl) {
        this.invTolerancePctFl = invTolerancePctFl;
        return this.invTolerancePctFl;
    }

    /**
     * Getter for {@link #invToleranceQty invToleranceQty}
     * 
     */
    public BigDecimal getInvToleranceQty() {
        return this.invToleranceQty;
    }

    /**
     * Setter for {@link #invToleranceQty invToleranceQty}
     * 
     */
    public BigDecimal setInvToleranceQty(BigDecimal invToleranceQty) {
        if ((invToleranceQty!= null)&&(invToleranceQty.scale()!= 2)) {
            invToleranceQty = invToleranceQty.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.invToleranceQty = invToleranceQty;
        return this.invToleranceQty;
    }

    /**
     * Getter for {@link #invToleranceQtyFl invToleranceQtyFl}
     * 
     */
    public String getInvToleranceQtyFl() {
        return this.invToleranceQtyFl;
    }

    /**
     * Setter for {@link #invToleranceQtyFl invToleranceQtyFl}
     * 
     */
    public String setInvToleranceQtyFl(String invToleranceQtyFl) {
        this.invToleranceQtyFl = invToleranceQtyFl;
        return this.invToleranceQtyFl;
    }

    /**
     * Getter for {@link #costFactor costFactor}
     * 
     */
    public BigDecimal getCostFactor() {
        return this.costFactor;
    }

    /**
     * Setter for {@link #costFactor costFactor}
     * 
     */
    public BigDecimal setCostFactor(BigDecimal costFactor) {
        if ((costFactor!= null)&&(costFactor.scale()!= 2)) {
            costFactor = costFactor.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.costFactor = costFactor;
        return this.costFactor;
    }

    /**
     * Getter for {@link #operationType operationType}
     * 
     */
    public String getOperationType() {
        return this.operationType;
    }

    /**
     * Setter for {@link #operationType operationType}
     * 
     */
    public String setOperationType(String operationType) {
        this.operationType = operationType;
        return this.operationType;
    }

    /**
     * Getter for {@link #replicationNo replicationNo}
     * 
     */
    public Long getReplicationNo() {
        return this.replicationNo;
    }

    /**
     * Setter for {@link #replicationNo replicationNo}
     * 
     */
    public Long setReplicationNo(Long replicationNo) {
        this.replicationNo = replicationNo;
        return this.replicationNo;
    }

    /**
     * Getter for {@link #registerReplicationNo registerReplicationNo}
     * 
     */
    public Long getRegisterReplicationNo() {
        return this.registerReplicationNo;
    }

    /**
     * Setter for {@link #registerReplicationNo registerReplicationNo}
     * 
     */
    public Long setRegisterReplicationNo(Long registerReplicationNo) {
        this.registerReplicationNo = registerReplicationNo;
        return this.registerReplicationNo;
    }

    /**
     * Getter for {@link #dateCreated dateCreated}
     * 
     */
    public Date getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Setter for {@link #dateCreated dateCreated}
     * 
     */
    public Date setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this.dateCreated;
    }

    /**
     * Getter for {@link #userCreated userCreated}
     * 
     */
    public String getUserCreated() {
        return this.userCreated;
    }

    /**
     * Setter for {@link #userCreated userCreated}
     * 
     */
    public String setUserCreated(String userCreated) {
        this.userCreated = userCreated;
        return this.userCreated;
    }

    /**
     * Getter for {@link #dateModified dateModified}
     * 
     */
    public Date getDateModified() {
        return this.dateModified;
    }

    /**
     * Setter for {@link #dateModified dateModified}
     * 
     */
    public Date setDateModified(Date dateModified) {
        this.dateModified = dateModified;
        return this.dateModified;
    }

    /**
     * Getter for {@link #userModified userModified}
     * 
     */
    public String getUserModified() {
        return this.userModified;
    }

    /**
     * Setter for {@link #userModified userModified}
     * 
     */
    public String setUserModified(String userModified) {
        this.userModified = userModified;
        return this.userModified;
    }

    /**
     * Getter for {@link #lastCostQty lastCostQty}
     * 
     */
    public BigDecimal getLastCostQty() {
        return this.lastCostQty;
    }

    /**
     * Setter for {@link #lastCostQty lastCostQty}
     * 
     */
    public BigDecimal setLastCostQty(BigDecimal lastCostQty) {
        if ((lastCostQty!= null)&&(lastCostQty.scale()!= 2)) {
            lastCostQty = lastCostQty.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.lastCostQty = lastCostQty;
        return this.lastCostQty;
    }

    /**
     * Getter for {@link #upliftGroupId upliftGroupId}
     * 
     */
    public Integer getUpliftGroupId() {
        return this.upliftGroupId;
    }

    /**
     * Setter for {@link #upliftGroupId upliftGroupId}
     * 
     */
    public Integer setUpliftGroupId(Integer upliftGroupId) {
        this.upliftGroupId = upliftGroupId;
        return this.upliftGroupId;
    }

    /**
     * Getter for {@link #vendorCurrencyPackCost vendorCurrencyPackCost}
     * 
     */
    public BigDecimal getVendorCurrencyPackCost() {
        return this.vendorCurrencyPackCost;
    }

    /**
     * Setter for {@link #vendorCurrencyPackCost vendorCurrencyPackCost}
     * 
     */
    public BigDecimal setVendorCurrencyPackCost(BigDecimal vendorCurrencyPackCost) {
        if ((vendorCurrencyPackCost!= null)&&(vendorCurrencyPackCost.scale()!= 4)) {
            vendorCurrencyPackCost = vendorCurrencyPackCost.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.vendorCurrencyPackCost = vendorCurrencyPackCost;
        return this.vendorCurrencyPackCost;
    }

    /**
     * Getter for {@link #supplierRecSched supplierRecSched}
     * 
     */
    public String getSupplierRecSched() {
        return this.supplierRecSched;
    }

    /**
     * Setter for {@link #supplierRecSched supplierRecSched}
     * 
     */
    public String setSupplierRecSched(String supplierRecSched) {
        this.supplierRecSched = supplierRecSched;
        return this.supplierRecSched;
    }

    /**
     * Getter for {@link #unitCostUm unitCostUm}
     * 
     */
    public String getUnitCostUm() {
        return this.unitCostUm;
    }

    /**
     * Setter for {@link #unitCostUm unitCostUm}
     * 
     */
    public String setUnitCostUm(String unitCostUm) {
        this.unitCostUm = unitCostUm;
        return this.unitCostUm;
    }

    /**
     * Getter for {@link #releaseHorizon releaseHorizon}
     * 
     */
    public Integer getReleaseHorizon() {
        return this.releaseHorizon;
    }

    /**
     * Setter for {@link #releaseHorizon releaseHorizon}
     * 
     */
    public Integer setReleaseHorizon(Integer releaseHorizon) {
        this.releaseHorizon = releaseHorizon;
        return this.releaseHorizon;
    }

    /**
     * Getter for {@link #packWeight packWeight}
     * 
     */
    public BigDecimal getPackWeight() {
        return this.packWeight;
    }

    /**
     * Setter for {@link #packWeight packWeight}
     * 
     */
    public BigDecimal setPackWeight(BigDecimal packWeight) {
        if ((packWeight!= null)&&(packWeight.scale()!= 2)) {
            packWeight = packWeight.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.packWeight = packWeight;
        return this.packWeight;
    }

    /**
     * Getter for {@link #packCube packCube}
     * 
     */
    public BigDecimal getPackCube() {
        return this.packCube;
    }

    /**
     * Setter for {@link #packCube packCube}
     * 
     */
    public BigDecimal setPackCube(BigDecimal packCube) {
        if ((packCube!= null)&&(packCube.scale()!= 2)) {
            packCube = packCube.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.packCube = packCube;
        return this.packCube;
    }

    /**
     * Getter for {@link #route route}
     * 
     */
    public String getRoute() {
        return this.route;
    }

    /**
     * Setter for {@link #route route}
     * 
     */
    public String setRoute(String route) {
        this.route = route;
        return this.route;
    }

    /**
     * Getter for {@link #immediateSupplier immediateSupplier}
     * 
     */
    public String getImmediateSupplier() {
        return this.immediateSupplier;
    }

    /**
     * Setter for {@link #immediateSupplier immediateSupplier}
     * 
     */
    public String setImmediateSupplier(String immediateSupplier) {
        this.immediateSupplier = immediateSupplier;
        return this.immediateSupplier;
    }

    /**
     * Getter for {@link #externalInternalSupplier externalInternalSupplier}
     * 
     */
    public String getExternalInternalSupplier() {
        return this.externalInternalSupplier;
    }

    /**
     * Setter for {@link #externalInternalSupplier externalInternalSupplier}
     * 
     */
    public String setExternalInternalSupplier(String externalInternalSupplier) {
        this.externalInternalSupplier = externalInternalSupplier;
        return this.externalInternalSupplier;
    }

    /**
     * Getter for {@link #length length}
     * 
     */
    public BigDecimal getLength() {
        return this.length;
    }

    /**
     * Setter for {@link #length length}
     * 
     */
    public BigDecimal setLength(BigDecimal length) {
        if ((length!= null)&&(length.scale()!= 3)) {
            length = length.setScale(3, BigDecimal.ROUND_HALF_UP);
        }
        this.length = length;
        return this.length;
    }

    /**
     * Getter for {@link #width width}
     * 
     */
    public BigDecimal getWidth() {
        return this.width;
    }

    /**
     * Setter for {@link #width width}
     * 
     */
    public BigDecimal setWidth(BigDecimal width) {
        if ((width!= null)&&(width.scale()!= 3)) {
            width = width.setScale(3, BigDecimal.ROUND_HALF_UP);
        }
        this.width = width;
        return this.width;
    }

    /**
     * Getter for {@link #height height}
     * 
     */
    public BigDecimal getHeight() {
        return this.height;
    }

    /**
     * Setter for {@link #height height}
     * 
     */
    public BigDecimal setHeight(BigDecimal height) {
        if ((height!= null)&&(height.scale()!= 3)) {
            height = height.setScale(3, BigDecimal.ROUND_HALF_UP);
        }
        this.height = height;
        return this.height;
    }

    /**
     * Getter for {@link #weight weight}
     * 
     */
    public BigDecimal getWeight() {
        return this.weight;
    }

    /**
     * Setter for {@link #weight weight}
     * 
     */
    public BigDecimal setWeight(BigDecimal weight) {
        if ((weight!= null)&&(weight.scale()!= 3)) {
            weight = weight.setScale(3, BigDecimal.ROUND_HALF_UP);
        }
        this.weight = weight;
        return this.weight;
    }

    /**
     * Getter for {@link #cube cube}
     * 
     */
    public BigDecimal getCube() {
        return this.cube;
    }

    /**
     * Setter for {@link #cube cube}
     * 
     */
    public BigDecimal setCube(BigDecimal cube) {
        if ((cube!= null)&&(cube.scale()!= 3)) {
            cube = cube.setScale(3, BigDecimal.ROUND_HALF_UP);
        }
        this.cube = cube;
        return this.cube;
    }

    /**
     * Getter for {@link #landedCostElements landedCostElements}
     * 
     */
    public String getLandedCostElements() {
        return this.landedCostElements;
    }

    /**
     * Setter for {@link #landedCostElements landedCostElements}
     * 
     */
    public String setLandedCostElements(String landedCostElements) {
        this.landedCostElements = landedCostElements;
        return this.landedCostElements;
    }

    /**
     * Getter for {@link #additionalVendorItem additionalVendorItem}
     * 
     */
    public String getAdditionalVendorItem() {
        return this.additionalVendorItem;
    }

    /**
     * Setter for {@link #additionalVendorItem additionalVendorItem}
     * 
     */
    public String setAdditionalVendorItem(String additionalVendorItem) {
        this.additionalVendorItem = additionalVendorItem;
        return this.additionalVendorItem;
    }

    /**
     * Getter for {@link #defaultClassCode defaultClassCode}
     * 
     */
    public String getDefaultClassCode() {
        return this.defaultClassCode;
    }

    /**
     * Setter for {@link #defaultClassCode defaultClassCode}
     * 
     */
    public String setDefaultClassCode(String defaultClassCode) {
        this.defaultClassCode = defaultClassCode;
        return this.defaultClassCode;
    }

    /**
     * @hide<P><I>Not for public use.</I></P>
     * 
     */
    private void _initDefaults() {
        if (this.unitCost == null) {
            this.unitCost = new BigDecimal("0.0000");
        }
        if (this.packQty == null) {
            this.packQty = new BigDecimal("1.00");
        }
        if (this.masterPackQty == null) {
            this.masterPackQty = new BigDecimal("1.00");
        }
        if (this.coreCost == null) {
            this.coreCost = new BigDecimal("0.0000");
        }
        if (this.netCost == null) {
            this.netCost = new BigDecimal("0.0000");
        }
        if (this.whseCostAmt == null) {
            this.whseCostAmt = new BigDecimal("0.0000");
        }
        if (this.costElement1Amt == null) {
            this.costElement1Amt = new BigDecimal("0.0000");
        }
        if (this.costElement2Amt == null) {
            this.costElement2Amt = new BigDecimal("0.0000");
        }
        if (this.freightToleranceAmt == null) {
            this.freightToleranceAmt = new BigDecimal("0.00");
        }
        if (this.freightToleranceAmtFl == null) {
            this.freightToleranceAmtFl = "N";
        }
        if (this.freightTolerancePct == null) {
            this.freightTolerancePct = new BigDecimal("0.00");
        }
        if (this.freightTolerancePctFl == null) {
            this.freightTolerancePctFl = "N";
        }
        if (this.invToleranceAmt == null) {
            this.invToleranceAmt = new BigDecimal("0.00");
        }
        if (this.invToleranceAmtFl == null) {
            this.invToleranceAmtFl = "N";
        }
        if (this.invTolerancePct == null) {
            this.invTolerancePct = new BigDecimal("0.00");
        }
        if (this.invTolerancePctFl == null) {
            this.invTolerancePctFl = "N";
        }
        if (this.invToleranceQty == null) {
            this.invToleranceQty = new BigDecimal("0.00");
        }
        if (this.invToleranceQtyFl == null) {
            this.invToleranceQtyFl = "N";
        }
        if (this.releaseHorizon == null) {
            this.releaseHorizon = Integer.valueOf(1);
        }
        if (this.packWeight == null) {
            this.packWeight = new BigDecimal("0.00");
        }
        if (this.packCube == null) {
            this.packCube = new BigDecimal("0.00");
        }
    }

    /**
     * @hide<P><I>Not for public use.</I></P><P>Initializes internal collections.</P>
     * 
     */
    private void _init() {
    }

    /**
     * <P>Used to copy a VendorItemEntity including non-identifier fields only.</P>
     * 
     */
    public VendorItemEntity copyData() {
        final VendorItemEntity copy = new VendorItemEntity();
        	
        copy.skuNo = this.skuNo;
        copy.vendorId = this.vendorId;
        copy.siteNo = this.siteNo;
        copy.venType = this.venType;
        copy.vendorItem = this.vendorItem;
        copy.mainVendorItemFl = this.mainVendorItemFl;
        copy.eoq = this.eoq;
        copy.leadTime = this.leadTime;
        copy.unitCost = this.unitCost;
        copy.packCost = this.packCost;
        copy.packQty = this.packQty;
        copy.masterPackQty = this.masterPackQty;
        copy.packUm = this.packUm;
        copy.lastCostchgDt = this.lastCostchgDt;
        copy.lastCost = this.lastCost;
        copy.coreCost = this.coreCost;
        copy.netCost = this.netCost;
        copy.whseCostAmt = this.whseCostAmt;
        copy.costElement1Amt = this.costElement1Amt;
        copy.costElement2Amt = this.costElement2Amt;
        copy.batchSiteNo = this.batchSiteNo;
        copy.batchId = this.batchId;
        copy.loq = this.loq;
        copy.formDimension = this.formDimension;
        copy.freightAmt = this.freightAmt;
        copy.freightPct = this.freightPct;
        copy.dateChanged = this.dateChanged;
        copy.orderAvailabilityStatus = this.orderAvailabilityStatus;
        copy.orderAvailabilityActiveDt = this.orderAvailabilityActiveDt;
        copy.externalRefId = this.externalRefId;
        copy.freightToleranceAmt = this.freightToleranceAmt;
        copy.freightToleranceAmtFl = this.freightToleranceAmtFl;
        copy.freightTolerancePct = this.freightTolerancePct;
        copy.freightTolerancePctFl = this.freightTolerancePctFl;
        copy.invToleranceAmt = this.invToleranceAmt;
        copy.invToleranceAmtFl = this.invToleranceAmtFl;
        copy.invTolerancePct = this.invTolerancePct;
        copy.invTolerancePctFl = this.invTolerancePctFl;
        copy.invToleranceQty = this.invToleranceQty;
        copy.invToleranceQtyFl = this.invToleranceQtyFl;
        copy.costFactor = this.costFactor;
        copy.operationType = this.operationType;
        copy.replicationNo = this.replicationNo;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.lastCostQty = this.lastCostQty;
        copy.upliftGroupId = this.upliftGroupId;
        copy.vendorCurrencyPackCost = this.vendorCurrencyPackCost;
        copy.supplierRecSched = this.supplierRecSched;
        copy.unitCostUm = this.unitCostUm;
        copy.releaseHorizon = this.releaseHorizon;
        copy.packWeight = this.packWeight;
        copy.packCube = this.packCube;
        copy.route = this.route;
        copy.immediateSupplier = this.immediateSupplier;
        copy.externalInternalSupplier = this.externalInternalSupplier;
        copy.length = this.length;
        copy.width = this.width;
        copy.height = this.height;
        copy.weight = this.weight;
        copy.cube = this.cube;
        copy.landedCostElements = this.landedCostElements;
        copy.additionalVendorItem = this.additionalVendorItem;
        copy.defaultClassCode = this.defaultClassCode;
        	
        return copy;
    }

    /**
     * <P>Used to copy a VendorItemEntity including its identifier.</P>
     * 
     */
    public VendorItemEntity copyDataAndId() {
        final VendorItemEntity copy = new VendorItemEntity();
        	
        copy.getIdentifier().setSeqNo(this.getIdentifier().getSeqNo());
        	
        copy.skuNo = this.skuNo;
        copy.vendorId = this.vendorId;
        copy.siteNo = this.siteNo;
        copy.venType = this.venType;
        copy.vendorItem = this.vendorItem;
        copy.mainVendorItemFl = this.mainVendorItemFl;
        copy.eoq = this.eoq;
        copy.leadTime = this.leadTime;
        copy.unitCost = this.unitCost;
        copy.packCost = this.packCost;
        copy.packQty = this.packQty;
        copy.masterPackQty = this.masterPackQty;
        copy.packUm = this.packUm;
        copy.lastCostchgDt = this.lastCostchgDt;
        copy.lastCost = this.lastCost;
        copy.coreCost = this.coreCost;
        copy.netCost = this.netCost;
        copy.whseCostAmt = this.whseCostAmt;
        copy.costElement1Amt = this.costElement1Amt;
        copy.costElement2Amt = this.costElement2Amt;
        copy.batchSiteNo = this.batchSiteNo;
        copy.batchId = this.batchId;
        copy.loq = this.loq;
        copy.formDimension = this.formDimension;
        copy.freightAmt = this.freightAmt;
        copy.freightPct = this.freightPct;
        copy.dateChanged = this.dateChanged;
        copy.orderAvailabilityStatus = this.orderAvailabilityStatus;
        copy.orderAvailabilityActiveDt = this.orderAvailabilityActiveDt;
        copy.externalRefId = this.externalRefId;
        copy.freightToleranceAmt = this.freightToleranceAmt;
        copy.freightToleranceAmtFl = this.freightToleranceAmtFl;
        copy.freightTolerancePct = this.freightTolerancePct;
        copy.freightTolerancePctFl = this.freightTolerancePctFl;
        copy.invToleranceAmt = this.invToleranceAmt;
        copy.invToleranceAmtFl = this.invToleranceAmtFl;
        copy.invTolerancePct = this.invTolerancePct;
        copy.invTolerancePctFl = this.invTolerancePctFl;
        copy.invToleranceQty = this.invToleranceQty;
        copy.invToleranceQtyFl = this.invToleranceQtyFl;
        copy.costFactor = this.costFactor;
        copy.operationType = this.operationType;
        copy.replicationNo = this.replicationNo;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.lastCostQty = this.lastCostQty;
        copy.upliftGroupId = this.upliftGroupId;
        copy.vendorCurrencyPackCost = this.vendorCurrencyPackCost;
        copy.supplierRecSched = this.supplierRecSched;
        copy.unitCostUm = this.unitCostUm;
        copy.releaseHorizon = this.releaseHorizon;
        copy.packWeight = this.packWeight;
        copy.packCube = this.packCube;
        copy.route = this.route;
        copy.immediateSupplier = this.immediateSupplier;
        copy.externalInternalSupplier = this.externalInternalSupplier;
        copy.length = this.length;
        copy.width = this.width;
        copy.height = this.height;
        copy.weight = this.weight;
        copy.cube = this.cube;
        copy.landedCostElements = this.landedCostElements;
        copy.additionalVendorItem = this.additionalVendorItem;
        copy.defaultClassCode = this.defaultClassCode;
        	
        return copy;
    }

    /**
     * <P>Used to copy a VendorItemEntity including data and GENERATED identifiers only.</P>
     * 
     */
    public VendorItemEntity copyDataAndIdNonGenerated() {
        final VendorItemEntity copy = new VendorItemEntity();
        	
        // NOT copying non-generated property: seqNo
        	
        copy.skuNo = this.skuNo;
        copy.vendorId = this.vendorId;
        copy.siteNo = this.siteNo;
        copy.venType = this.venType;
        copy.vendorItem = this.vendorItem;
        copy.mainVendorItemFl = this.mainVendorItemFl;
        copy.eoq = this.eoq;
        copy.leadTime = this.leadTime;
        copy.unitCost = this.unitCost;
        copy.packCost = this.packCost;
        copy.packQty = this.packQty;
        copy.masterPackQty = this.masterPackQty;
        copy.packUm = this.packUm;
        copy.lastCostchgDt = this.lastCostchgDt;
        copy.lastCost = this.lastCost;
        copy.coreCost = this.coreCost;
        copy.netCost = this.netCost;
        copy.whseCostAmt = this.whseCostAmt;
        copy.costElement1Amt = this.costElement1Amt;
        copy.costElement2Amt = this.costElement2Amt;
        copy.batchSiteNo = this.batchSiteNo;
        copy.batchId = this.batchId;
        copy.loq = this.loq;
        copy.formDimension = this.formDimension;
        copy.freightAmt = this.freightAmt;
        copy.freightPct = this.freightPct;
        copy.dateChanged = this.dateChanged;
        copy.orderAvailabilityStatus = this.orderAvailabilityStatus;
        copy.orderAvailabilityActiveDt = this.orderAvailabilityActiveDt;
        copy.externalRefId = this.externalRefId;
        copy.freightToleranceAmt = this.freightToleranceAmt;
        copy.freightToleranceAmtFl = this.freightToleranceAmtFl;
        copy.freightTolerancePct = this.freightTolerancePct;
        copy.freightTolerancePctFl = this.freightTolerancePctFl;
        copy.invToleranceAmt = this.invToleranceAmt;
        copy.invToleranceAmtFl = this.invToleranceAmtFl;
        copy.invTolerancePct = this.invTolerancePct;
        copy.invTolerancePctFl = this.invTolerancePctFl;
        copy.invToleranceQty = this.invToleranceQty;
        copy.invToleranceQtyFl = this.invToleranceQtyFl;
        copy.costFactor = this.costFactor;
        copy.operationType = this.operationType;
        copy.replicationNo = this.replicationNo;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.lastCostQty = this.lastCostQty;
        copy.upliftGroupId = this.upliftGroupId;
        copy.vendorCurrencyPackCost = this.vendorCurrencyPackCost;
        copy.supplierRecSched = this.supplierRecSched;
        copy.unitCostUm = this.unitCostUm;
        copy.releaseHorizon = this.releaseHorizon;
        copy.packWeight = this.packWeight;
        copy.packCube = this.packCube;
        copy.route = this.route;
        copy.immediateSupplier = this.immediateSupplier;
        copy.externalInternalSupplier = this.externalInternalSupplier;
        copy.length = this.length;
        copy.width = this.width;
        copy.height = this.height;
        copy.weight = this.weight;
        copy.cube = this.cube;
        copy.landedCostElements = this.landedCostElements;
        copy.additionalVendorItem = this.additionalVendorItem;
        copy.defaultClassCode = this.defaultClassCode;
        	
        return copy;
    }

    /**
     * @hide<P><I>Not for public use.</I></P>This postLoad method is used to fix BigDecimal values being loaded from the JDBC driver.  Oracle has a known jdbc bug where it truncates the scale of BigDecimal.  This method restores the scale after load so that BigDecimals comparators will work again.
     * 
     */
    private void _setScales() {
        if (this.eoq!= null) {
            this.eoq = this.eoq.setScale(2);
        }
        if (this.unitCost!= null) {
            this.unitCost = this.unitCost.setScale(4);
        }
        if (this.packCost!= null) {
            this.packCost = this.packCost.setScale(4);
        }
        if (this.packQty!= null) {
            this.packQty = this.packQty.setScale(2);
        }
        if (this.masterPackQty!= null) {
            this.masterPackQty = this.masterPackQty.setScale(2);
        }
        if (this.lastCost!= null) {
            this.lastCost = this.lastCost.setScale(4);
        }
        if (this.coreCost!= null) {
            this.coreCost = this.coreCost.setScale(4);
        }
        if (this.netCost!= null) {
            this.netCost = this.netCost.setScale(4);
        }
        if (this.whseCostAmt!= null) {
            this.whseCostAmt = this.whseCostAmt.setScale(4);
        }
        if (this.costElement1Amt!= null) {
            this.costElement1Amt = this.costElement1Amt.setScale(4);
        }
        if (this.costElement2Amt!= null) {
            this.costElement2Amt = this.costElement2Amt.setScale(4);
        }
        if (this.loq!= null) {
            this.loq = this.loq.setScale(2);
        }
        if (this.freightAmt!= null) {
            this.freightAmt = this.freightAmt.setScale(2);
        }
        if (this.freightPct!= null) {
            this.freightPct = this.freightPct.setScale(2);
        }
        if (this.freightToleranceAmt!= null) {
            this.freightToleranceAmt = this.freightToleranceAmt.setScale(2);
        }
        if (this.freightTolerancePct!= null) {
            this.freightTolerancePct = this.freightTolerancePct.setScale(2);
        }
        if (this.invToleranceAmt!= null) {
            this.invToleranceAmt = this.invToleranceAmt.setScale(2);
        }
        if (this.invTolerancePct!= null) {
            this.invTolerancePct = this.invTolerancePct.setScale(2);
        }
        if (this.invToleranceQty!= null) {
            this.invToleranceQty = this.invToleranceQty.setScale(2);
        }
        if (this.costFactor!= null) {
            this.costFactor = this.costFactor.setScale(2);
        }
        if (this.lastCostQty!= null) {
            this.lastCostQty = this.lastCostQty.setScale(2);
        }
        if (this.vendorCurrencyPackCost!= null) {
            this.vendorCurrencyPackCost = this.vendorCurrencyPackCost.setScale(4);
        }
        if (this.packWeight!= null) {
            this.packWeight = this.packWeight.setScale(2);
        }
        if (this.packCube!= null) {
            this.packCube = this.packCube.setScale(2);
        }
        if (this.length!= null) {
            this.length = this.length.setScale(3);
        }
        if (this.width!= null) {
            this.width = this.width.setScale(3);
        }
        if (this.height!= null) {
            this.height = this.height.setScale(3);
        }
        if (this.weight!= null) {
            this.weight = this.weight.setScale(3);
        }
        if (this.cube!= null) {
            this.cube = this.cube.setScale(3);
        }
    }

    /**
     * @hide<P><I>Not for public use.</I></P>
     * 
     */
    @PrePersist
    private void _prePersist() {
        _initDefaults();
        	
        final Date now = new Date();
        final Timestamp nowTimestamp = new Timestamp(now.getTime());
        this.dateChanged = now;
        this.dateCreated = now;
        this.userCreated = ("~"+ EntityManagerContext.getAuditUser());
        this.dateModified = now;
        this.userModified = ("~"+ EntityManagerContext.getAuditUser());
    }

    /**
     * @hide<P><I>Not for public use.</I></P>
     * 
     */
    @PreUpdate
    private void _preUpdate() {
        final Date now = new Date();
        final Timestamp nowTimestamp = new Timestamp(now.getTime());
        this.dateChanged = now;
        this.dateModified = now;
        this.userModified = ("~"+ EntityManagerContext.getAuditUser());
    }

    /**
     * @hide<P><I>Not for public use.</I></P>
     * 
     */
    @PostLoad
    private void _postLoad() {
        _setScales();
    }

    public static VendorItemDTO toDTO(VendorItemEntity entity) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return VendorItemEntity.toDTO(entity, map);
    }

    public static VendorItemDTO toDTO(VendorItemEntity entity, Map<Object, Object> map) {
        try {
            if (entity == null) {
                return null;
            }
            	
            if (map == null) {
                map = new HashMap<Object, Object>();
            } else {
                if (map.get(entity)!= null) {
                    return ((VendorItemDTO) map.get(entity));
                }
            }
            	
            entity.toDTO(map);
            	
            return ((VendorItemDTO) map.get(entity));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public static VendorItemEntity toEntity(VendorItemDTO dto) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return VendorItemEntity.toEntity(dto, map);
    }

    public static VendorItemEntity toEntity(VendorItemDTO dto, Map<Object, Object> map) {
        try {
            if (dto == null) {
                return null;
            }
            	
            if ((map!= null)&&(map.get(dto)!= null)) {
                return ((VendorItemEntity) map.get(dto));
            }
            	
            return ((VendorItemEntity) DtoToEntity.toEntity(VendorItemEntity.class, ((DataTransferObject) dto)));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public void assignAllChildren(VendorItemDTO dto, Map<Object, Object> map) {
        toSimpleEntity(dto, map);
    }

    public void assignAllChildren(Map<Object, Object> map, Stack<Object> stack) {
        toSimpleDto(map);
    }

    public void assignAllParents(Map<Object, Object> map) {
        toSimpleDto(map);
    }

    protected VendorItemDTO toSimpleDto(Map<Object, Object> map) {
        if (map.get(this)!= null) {
            return ((VendorItemDTO) map.get(this));
        }
        try {
            VendorItemDTO dto = new VendorItemDTO();
            dto.setIdentifier(new VendorItemDTOId(this.getSeqNo()));
            	
            dto.setSkuNo(this.getSkuNo());
            dto.setVendorId(this.getVendorId());
            dto.setSiteNo(this.getSiteNo());
            dto.setVenType(this.getVenType());
            dto.setVendorItem(this.getVendorItem());
            dto.setMainVendorItemFl(this.getMainVendorItemFl());
            dto.setEoq(this.getEoq());
            dto.setLeadTime(this.getLeadTime());
            dto.setUnitCost(this.getUnitCost());
            dto.setPackCost(this.getPackCost());
            dto.setPackQty(this.getPackQty());
            dto.setMasterPackQty(this.getMasterPackQty());
            dto.setPackUm(this.getPackUm());
            dto.setLastCostchgDt(this.getLastCostchgDt());
            dto.setLastCost(this.getLastCost());
            dto.setCoreCost(this.getCoreCost());
            dto.setNetCost(this.getNetCost());
            dto.setWhseCostAmt(this.getWhseCostAmt());
            dto.setCostElement1Amt(this.getCostElement1Amt());
            dto.setCostElement2Amt(this.getCostElement2Amt());
            dto.setBatchSiteNo(this.getBatchSiteNo());
            dto.setBatchId(this.getBatchId());
            dto.setLoq(this.getLoq());
            dto.setFormDimension(this.getFormDimension());
            dto.setFreightAmt(this.getFreightAmt());
            dto.setFreightPct(this.getFreightPct());
            dto.setDateChanged(this.getDateChanged());
            dto.setOrderAvailabilityStatus(this.getOrderAvailabilityStatus());
            dto.setOrderAvailabilityActiveDt(this.getOrderAvailabilityActiveDt());
            dto.setExternalRefId(this.getExternalRefId());
            dto.setFreightToleranceAmt(this.getFreightToleranceAmt());
            dto.setFreightToleranceAmtFl(this.getFreightToleranceAmtFl());
            dto.setFreightTolerancePct(this.getFreightTolerancePct());
            dto.setFreightTolerancePctFl(this.getFreightTolerancePctFl());
            dto.setInvToleranceAmt(this.getInvToleranceAmt());
            dto.setInvToleranceAmtFl(this.getInvToleranceAmtFl());
            dto.setInvTolerancePct(this.getInvTolerancePct());
            dto.setInvTolerancePctFl(this.getInvTolerancePctFl());
            dto.setInvToleranceQty(this.getInvToleranceQty());
            dto.setInvToleranceQtyFl(this.getInvToleranceQtyFl());
            dto.setCostFactor(this.getCostFactor());
            dto.setOperationType(this.getOperationType());
            dto.setReplicationNo(this.getReplicationNo());
            dto.setRegisterReplicationNo(this.getRegisterReplicationNo());
            dto.setDateCreated(this.getDateCreated());
            dto.setUserCreated(this.getUserCreated());
            dto.setDateModified(this.getDateModified());
            dto.setUserModified(this.getUserModified());
            dto.setLastCostQty(this.getLastCostQty());
            dto.setUpliftGroupId(this.getUpliftGroupId());
            dto.setVendorCurrencyPackCost(this.getVendorCurrencyPackCost());
            dto.setSupplierRecSched(this.getSupplierRecSched());
            dto.setUnitCostUm(this.getUnitCostUm());
            dto.setReleaseHorizon(this.getReleaseHorizon());
            dto.setPackWeight(this.getPackWeight());
            dto.setPackCube(this.getPackCube());
            dto.setRoute(this.getRoute());
            dto.setImmediateSupplier(this.getImmediateSupplier());
            dto.setExternalInternalSupplier(this.getExternalInternalSupplier());
            dto.setLength(this.getLength());
            dto.setWidth(this.getWidth());
            dto.setHeight(this.getHeight());
            dto.setWeight(this.getWeight());
            dto.setCube(this.getCube());
            dto.setLandedCostElements(this.getLandedCostElements());
            dto.setAdditionalVendorItem(this.getAdditionalVendorItem());
            dto.setDefaultClassCode(this.getDefaultClassCode());
            	
            map.put(this, dto);
            	
            return dto;
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public VendorItemEntity toSimpleEntity(DataTransferObject dto, Map<Object, Object> map) {
        if (map.get(dto)!= null) {
            return ((VendorItemEntity) map.get(dto));
        }
        try {
            VendorItemDTO oldDto = ((VendorItemDTO) dto);
            VendorItemEntity entity = new VendorItemEntity();
            entity.setIdentifier(new VendorItemEntityId(oldDto.getSeqNo()));
            	
            entity.setSkuNo(oldDto.getSkuNo());
            entity.setVendorId(oldDto.getVendorId());
            entity.setSiteNo(oldDto.getSiteNo());
            entity.setVenType(oldDto.getVenType());
            entity.setVendorItem(oldDto.getVendorItem());
            entity.setMainVendorItemFl(oldDto.getMainVendorItemFl());
            entity.setEoq(oldDto.getEoq());
            entity.setLeadTime(oldDto.getLeadTime());
            entity.setUnitCost(oldDto.getUnitCost());
            entity.setPackCost(oldDto.getPackCost());
            entity.setPackQty(oldDto.getPackQty());
            entity.setMasterPackQty(oldDto.getMasterPackQty());
            entity.setPackUm(oldDto.getPackUm());
            entity.setLastCostchgDt(oldDto.getLastCostchgDt());
            entity.setLastCost(oldDto.getLastCost());
            entity.setCoreCost(oldDto.getCoreCost());
            entity.setNetCost(oldDto.getNetCost());
            entity.setWhseCostAmt(oldDto.getWhseCostAmt());
            entity.setCostElement1Amt(oldDto.getCostElement1Amt());
            entity.setCostElement2Amt(oldDto.getCostElement2Amt());
            entity.setBatchSiteNo(oldDto.getBatchSiteNo());
            entity.setBatchId(oldDto.getBatchId());
            entity.setLoq(oldDto.getLoq());
            entity.setFormDimension(oldDto.getFormDimension());
            entity.setFreightAmt(oldDto.getFreightAmt());
            entity.setFreightPct(oldDto.getFreightPct());
            entity.setDateChanged(oldDto.getDateChanged());
            entity.setOrderAvailabilityStatus(oldDto.getOrderAvailabilityStatus());
            entity.setOrderAvailabilityActiveDt(oldDto.getOrderAvailabilityActiveDt());
            entity.setExternalRefId(oldDto.getExternalRefId());
            entity.setFreightToleranceAmt(oldDto.getFreightToleranceAmt());
            entity.setFreightToleranceAmtFl(oldDto.getFreightToleranceAmtFl());
            entity.setFreightTolerancePct(oldDto.getFreightTolerancePct());
            entity.setFreightTolerancePctFl(oldDto.getFreightTolerancePctFl());
            entity.setInvToleranceAmt(oldDto.getInvToleranceAmt());
            entity.setInvToleranceAmtFl(oldDto.getInvToleranceAmtFl());
            entity.setInvTolerancePct(oldDto.getInvTolerancePct());
            entity.setInvTolerancePctFl(oldDto.getInvTolerancePctFl());
            entity.setInvToleranceQty(oldDto.getInvToleranceQty());
            entity.setInvToleranceQtyFl(oldDto.getInvToleranceQtyFl());
            entity.setCostFactor(oldDto.getCostFactor());
            entity.setOperationType(oldDto.getOperationType());
            entity.setReplicationNo(oldDto.getReplicationNo());
            entity.setRegisterReplicationNo(oldDto.getRegisterReplicationNo());
            entity.setDateCreated(oldDto.getDateCreated());
            entity.setUserCreated(oldDto.getUserCreated());
            entity.setDateModified(oldDto.getDateModified());
            entity.setUserModified(oldDto.getUserModified());
            entity.setLastCostQty(oldDto.getLastCostQty());
            entity.setUpliftGroupId(oldDto.getUpliftGroupId());
            entity.setVendorCurrencyPackCost(oldDto.getVendorCurrencyPackCost());
            entity.setSupplierRecSched(oldDto.getSupplierRecSched());
            entity.setUnitCostUm(oldDto.getUnitCostUm());
            entity.setReleaseHorizon(oldDto.getReleaseHorizon());
            entity.setPackWeight(oldDto.getPackWeight());
            entity.setPackCube(oldDto.getPackCube());
            entity.setRoute(oldDto.getRoute());
            entity.setImmediateSupplier(oldDto.getImmediateSupplier());
            entity.setExternalInternalSupplier(oldDto.getExternalInternalSupplier());
            entity.setLength(oldDto.getLength());
            entity.setWidth(oldDto.getWidth());
            entity.setHeight(oldDto.getHeight());
            entity.setWeight(oldDto.getWeight());
            entity.setCube(oldDto.getCube());
            entity.setLandedCostElements(oldDto.getLandedCostElements());
            entity.setAdditionalVendorItem(oldDto.getAdditionalVendorItem());
            entity.setDefaultClassCode(oldDto.getDefaultClassCode());
            	
            map.put(dto, entity);
            	
            return entity;
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public List<RNetAbstractEntity> getParents() {
        return null;
    }

    public List<Set<? extends RNetAbstractEntity>> getChildren() {
        return null;
    }

    public static List<VendorItemDTO> toDTOList(Collection<VendorItemEntity> entities) {
        if (entities == null) {
            return null;
        }
        	
        List<VendorItemDTO> dtos = new ArrayList<VendorItemDTO>();
        for (VendorItemEntity entity: entities) {
            dtos.add(toDTO(entity));
        }
        	
        return dtos;
    }

    @Deprecated
    public static List<VendorItemDTO> toDTO(Collection<VendorItemEntity> entities) {
        return toDTOList(entities);
    }

    public static VendorItemDTOId toDTOId(VendorItemEntityId id) {
        if (id == null) {
            return null;
        }
        return new VendorItemDTOId(id.getSeqNo());
    }

    @Deprecated
    public static VendorItemDTOId toDTO(VendorItemEntityId id) {
        return toDTOId(id);
    }

    public static List<VendorItemDTOId> toDTOIdList(Collection<VendorItemEntityId> entityIds) {
        if (entityIds == null) {
            return null;
        }
        	
        List<VendorItemDTOId> dtoIds = new ArrayList<VendorItemDTOId>();
        for (VendorItemEntityId entity: entityIds) {
            dtoIds.add(toDTOId(entity));
        }
        	
        return dtoIds;
    }

    public static Set<VendorItemEntity> toEntitySet(Collection<VendorItemDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        Set<VendorItemEntity> entities = new HashSet<VendorItemEntity>();
        for (VendorItemDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static Set<VendorItemEntity> toEntity(Collection<VendorItemDTO> dtos) {
        return toEntitySet(dtos);
    }

    public static SortedSet<VendorItemEntity> toEntitySortedSet(Collection<VendorItemDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        SortedSet<VendorItemEntity> entities = new TreeSet<VendorItemEntity>();
        for (VendorItemDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static SortedSet<VendorItemEntity> toEntitySorted(Collection<VendorItemDTO> dtos) {
        return toEntitySortedSet(dtos);
    }

    public static List<VendorItemEntity> toEntityList(Collection<VendorItemDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        List<VendorItemEntity> entities = new ArrayList<VendorItemEntity>();
        for (VendorItemDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    public static VendorItemEntityId toEntityId(VendorItemDTOId dto) {
        if (dto == null) {
            return null;
        }
        return new VendorItemEntityId(dto.getSeqNo());
    }

    @Deprecated
    public static VendorItemEntityId toEntity(VendorItemDTOId id) {
        return toEntityId(id);
    }

    public static List<VendorItemEntityId> toEntityIdList(Collection<VendorItemDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        List<VendorItemEntityId> entityIds = new ArrayList<VendorItemEntityId>();
        for (VendorItemDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static Set<VendorItemEntityId> toEntityIdSet(Collection<VendorItemDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        Set<VendorItemEntityId> entityIds = new HashSet<VendorItemEntityId>();
        for (VendorItemDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static SortedSet<VendorItemEntityId> toEntityIdSortedSet(Collection<VendorItemDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        SortedSet<VendorItemEntityId> entityIds = new TreeSet<VendorItemEntityId>();
        for (VendorItemDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    /**
     * An equals implementation that only compares the identifier fields (business keys).
     * 
     */
    @Override
    public boolean equals(Object obj) {
        // Object level comparison
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass()!= obj.getClass()) {
            return false;
        }
        final VendorItemEntity other = ((VendorItemEntity) obj);
        // Identifier level comparison
        if (this.identifier.getSeqNo() == null) {
            return false;
        } else {
            if (!this.identifier.getSeqNo().equals(other.identifier.getSeqNo())) {
                return false;
            }
        }
        return true;
    }

    /**
     * An equals impelmentation that only compares the non-identifier fields (data fields).
     * 
     */
    public boolean equalsData(Object obj) {
        // Object level comparison
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass()!= obj.getClass()) {
            return false;
        }
        final VendorItemEntity other = ((VendorItemEntity) obj);
        // Data level comparison
        if (skuNo == null) {
            if (other.skuNo!= null) {
                return false;
            }
        } else {
            if (!skuNo.equals(other.skuNo)) {
                return false;
            }
        }
        if (vendorId == null) {
            if (other.vendorId!= null) {
                return false;
            }
        } else {
            if (!vendorId.equals(other.vendorId)) {
                return false;
            }
        }
        if (siteNo == null) {
            if (other.siteNo!= null) {
                return false;
            }
        } else {
            if (!siteNo.equals(other.siteNo)) {
                return false;
            }
        }
        if (venType == null) {
            if (other.venType!= null) {
                return false;
            }
        } else {
            if (!venType.equals(other.venType)) {
                return false;
            }
        }
        if (vendorItem == null) {
            if (other.vendorItem!= null) {
                return false;
            }
        } else {
            if (!vendorItem.equals(other.vendorItem)) {
                return false;
            }
        }
        if (mainVendorItemFl == null) {
            if (other.mainVendorItemFl!= null) {
                return false;
            }
        } else {
            if (!mainVendorItemFl.equals(other.mainVendorItemFl)) {
                return false;
            }
        }
        if (eoq == null) {
            if (other.eoq!= null) {
                return false;
            }
        } else {
            if (!eoq.equals(other.eoq)) {
                return false;
            }
        }
        if (leadTime == null) {
            if (other.leadTime!= null) {
                return false;
            }
        } else {
            if (!leadTime.equals(other.leadTime)) {
                return false;
            }
        }
        if (unitCost == null) {
            if (other.unitCost!= null) {
                return false;
            }
        } else {
            if (!unitCost.equals(other.unitCost)) {
                return false;
            }
        }
        if (packCost == null) {
            if (other.packCost!= null) {
                return false;
            }
        } else {
            if (!packCost.equals(other.packCost)) {
                return false;
            }
        }
        if (packQty == null) {
            if (other.packQty!= null) {
                return false;
            }
        } else {
            if (!packQty.equals(other.packQty)) {
                return false;
            }
        }
        if (masterPackQty == null) {
            if (other.masterPackQty!= null) {
                return false;
            }
        } else {
            if (!masterPackQty.equals(other.masterPackQty)) {
                return false;
            }
        }
        if (packUm == null) {
            if (other.packUm!= null) {
                return false;
            }
        } else {
            if (!packUm.equals(other.packUm)) {
                return false;
            }
        }
        if (lastCostchgDt == null) {
            if (other.lastCostchgDt!= null) {
                return false;
            }
        } else {
            if (!lastCostchgDt.equals(other.lastCostchgDt)) {
                return false;
            }
        }
        if (lastCost == null) {
            if (other.lastCost!= null) {
                return false;
            }
        } else {
            if (!lastCost.equals(other.lastCost)) {
                return false;
            }
        }
        if (coreCost == null) {
            if (other.coreCost!= null) {
                return false;
            }
        } else {
            if (!coreCost.equals(other.coreCost)) {
                return false;
            }
        }
        if (netCost == null) {
            if (other.netCost!= null) {
                return false;
            }
        } else {
            if (!netCost.equals(other.netCost)) {
                return false;
            }
        }
        if (whseCostAmt == null) {
            if (other.whseCostAmt!= null) {
                return false;
            }
        } else {
            if (!whseCostAmt.equals(other.whseCostAmt)) {
                return false;
            }
        }
        if (costElement1Amt == null) {
            if (other.costElement1Amt!= null) {
                return false;
            }
        } else {
            if (!costElement1Amt.equals(other.costElement1Amt)) {
                return false;
            }
        }
        if (costElement2Amt == null) {
            if (other.costElement2Amt!= null) {
                return false;
            }
        } else {
            if (!costElement2Amt.equals(other.costElement2Amt)) {
                return false;
            }
        }
        if (batchSiteNo == null) {
            if (other.batchSiteNo!= null) {
                return false;
            }
        } else {
            if (!batchSiteNo.equals(other.batchSiteNo)) {
                return false;
            }
        }
        if (batchId == null) {
            if (other.batchId!= null) {
                return false;
            }
        } else {
            if (!batchId.equals(other.batchId)) {
                return false;
            }
        }
        if (loq == null) {
            if (other.loq!= null) {
                return false;
            }
        } else {
            if (!loq.equals(other.loq)) {
                return false;
            }
        }
        if (formDimension == null) {
            if (other.formDimension!= null) {
                return false;
            }
        } else {
            if (!formDimension.equals(other.formDimension)) {
                return false;
            }
        }
        if (freightAmt == null) {
            if (other.freightAmt!= null) {
                return false;
            }
        } else {
            if (!freightAmt.equals(other.freightAmt)) {
                return false;
            }
        }
        if (freightPct == null) {
            if (other.freightPct!= null) {
                return false;
            }
        } else {
            if (!freightPct.equals(other.freightPct)) {
                return false;
            }
        }
        if (orderAvailabilityStatus == null) {
            if (other.orderAvailabilityStatus!= null) {
                return false;
            }
        } else {
            if (!orderAvailabilityStatus.equals(other.orderAvailabilityStatus)) {
                return false;
            }
        }
        if (orderAvailabilityActiveDt == null) {
            if (other.orderAvailabilityActiveDt!= null) {
                return false;
            }
        } else {
            if (!orderAvailabilityActiveDt.equals(other.orderAvailabilityActiveDt)) {
                return false;
            }
        }
        if (externalRefId == null) {
            if (other.externalRefId!= null) {
                return false;
            }
        } else {
            if (!externalRefId.equals(other.externalRefId)) {
                return false;
            }
        }
        if (freightToleranceAmt == null) {
            if (other.freightToleranceAmt!= null) {
                return false;
            }
        } else {
            if (!freightToleranceAmt.equals(other.freightToleranceAmt)) {
                return false;
            }
        }
        if (freightToleranceAmtFl == null) {
            if (other.freightToleranceAmtFl!= null) {
                return false;
            }
        } else {
            if (!freightToleranceAmtFl.equals(other.freightToleranceAmtFl)) {
                return false;
            }
        }
        if (freightTolerancePct == null) {
            if (other.freightTolerancePct!= null) {
                return false;
            }
        } else {
            if (!freightTolerancePct.equals(other.freightTolerancePct)) {
                return false;
            }
        }
        if (freightTolerancePctFl == null) {
            if (other.freightTolerancePctFl!= null) {
                return false;
            }
        } else {
            if (!freightTolerancePctFl.equals(other.freightTolerancePctFl)) {
                return false;
            }
        }
        if (invToleranceAmt == null) {
            if (other.invToleranceAmt!= null) {
                return false;
            }
        } else {
            if (!invToleranceAmt.equals(other.invToleranceAmt)) {
                return false;
            }
        }
        if (invToleranceAmtFl == null) {
            if (other.invToleranceAmtFl!= null) {
                return false;
            }
        } else {
            if (!invToleranceAmtFl.equals(other.invToleranceAmtFl)) {
                return false;
            }
        }
        if (invTolerancePct == null) {
            if (other.invTolerancePct!= null) {
                return false;
            }
        } else {
            if (!invTolerancePct.equals(other.invTolerancePct)) {
                return false;
            }
        }
        if (invTolerancePctFl == null) {
            if (other.invTolerancePctFl!= null) {
                return false;
            }
        } else {
            if (!invTolerancePctFl.equals(other.invTolerancePctFl)) {
                return false;
            }
        }
        if (invToleranceQty == null) {
            if (other.invToleranceQty!= null) {
                return false;
            }
        } else {
            if (!invToleranceQty.equals(other.invToleranceQty)) {
                return false;
            }
        }
        if (invToleranceQtyFl == null) {
            if (other.invToleranceQtyFl!= null) {
                return false;
            }
        } else {
            if (!invToleranceQtyFl.equals(other.invToleranceQtyFl)) {
                return false;
            }
        }
        if (costFactor == null) {
            if (other.costFactor!= null) {
                return false;
            }
        } else {
            if (!costFactor.equals(other.costFactor)) {
                return false;
            }
        }
        if (operationType == null) {
            if (other.operationType!= null) {
                return false;
            }
        } else {
            if (!operationType.equals(other.operationType)) {
                return false;
            }
        }
        if (replicationNo == null) {
            if (other.replicationNo!= null) {
                return false;
            }
        } else {
            if (!replicationNo.equals(other.replicationNo)) {
                return false;
            }
        }
        if (registerReplicationNo == null) {
            if (other.registerReplicationNo!= null) {
                return false;
            }
        } else {
            if (!registerReplicationNo.equals(other.registerReplicationNo)) {
                return false;
            }
        }
        if (lastCostQty == null) {
            if (other.lastCostQty!= null) {
                return false;
            }
        } else {
            if (!lastCostQty.equals(other.lastCostQty)) {
                return false;
            }
        }
        if (upliftGroupId == null) {
            if (other.upliftGroupId!= null) {
                return false;
            }
        } else {
            if (!upliftGroupId.equals(other.upliftGroupId)) {
                return false;
            }
        }
        if (vendorCurrencyPackCost == null) {
            if (other.vendorCurrencyPackCost!= null) {
                return false;
            }
        } else {
            if (!vendorCurrencyPackCost.equals(other.vendorCurrencyPackCost)) {
                return false;
            }
        }
        if (supplierRecSched == null) {
            if (other.supplierRecSched!= null) {
                return false;
            }
        } else {
            if (!supplierRecSched.equals(other.supplierRecSched)) {
                return false;
            }
        }
        if (unitCostUm == null) {
            if (other.unitCostUm!= null) {
                return false;
            }
        } else {
            if (!unitCostUm.equals(other.unitCostUm)) {
                return false;
            }
        }
        if (releaseHorizon == null) {
            if (other.releaseHorizon!= null) {
                return false;
            }
        } else {
            if (!releaseHorizon.equals(other.releaseHorizon)) {
                return false;
            }
        }
        if (packWeight == null) {
            if (other.packWeight!= null) {
                return false;
            }
        } else {
            if (!packWeight.equals(other.packWeight)) {
                return false;
            }
        }
        if (packCube == null) {
            if (other.packCube!= null) {
                return false;
            }
        } else {
            if (!packCube.equals(other.packCube)) {
                return false;
            }
        }
        if (route == null) {
            if (other.route!= null) {
                return false;
            }
        } else {
            if (!route.equals(other.route)) {
                return false;
            }
        }
        if (immediateSupplier == null) {
            if (other.immediateSupplier!= null) {
                return false;
            }
        } else {
            if (!immediateSupplier.equals(other.immediateSupplier)) {
                return false;
            }
        }
        if (externalInternalSupplier == null) {
            if (other.externalInternalSupplier!= null) {
                return false;
            }
        } else {
            if (!externalInternalSupplier.equals(other.externalInternalSupplier)) {
                return false;
            }
        }
        if (length == null) {
            if (other.length!= null) {
                return false;
            }
        } else {
            if (!length.equals(other.length)) {
                return false;
            }
        }
        if (width == null) {
            if (other.width!= null) {
                return false;
            }
        } else {
            if (!width.equals(other.width)) {
                return false;
            }
        }
        if (height == null) {
            if (other.height!= null) {
                return false;
            }
        } else {
            if (!height.equals(other.height)) {
                return false;
            }
        }
        if (weight == null) {
            if (other.weight!= null) {
                return false;
            }
        } else {
            if (!weight.equals(other.weight)) {
                return false;
            }
        }
        if (cube == null) {
            if (other.cube!= null) {
                return false;
            }
        } else {
            if (!cube.equals(other.cube)) {
                return false;
            }
        }
        if (landedCostElements == null) {
            if (other.landedCostElements!= null) {
                return false;
            }
        } else {
            if (!landedCostElements.equals(other.landedCostElements)) {
                return false;
            }
        }
        if (additionalVendorItem == null) {
            if (other.additionalVendorItem!= null) {
                return false;
            }
        } else {
            if (!additionalVendorItem.equals(other.additionalVendorItem)) {
                return false;
            }
        }
        if (defaultClassCode == null) {
            if (other.defaultClassCode!= null) {
                return false;
            }
        } else {
            if (!defaultClassCode.equals(other.defaultClassCode)) {
                return false;
            }
        }
        return true;
    }

    /**
     * An equals implementation that compares all fields.
     * 
     */
    public boolean equalsAll(Object obj) {
        // Object level comparison
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass()!= obj.getClass()) {
            return false;
        }
        final VendorItemEntity other = ((VendorItemEntity) obj);
        // Identifier level comparison
        if (this.identifier.getSeqNo() == null) {
            return false;
        } else {
            if (!this.identifier.getSeqNo().equals(other.identifier.getSeqNo())) {
                return false;
            }
        }
        // Data level comparison
        if (skuNo == null) {
            if (other.skuNo!= null) {
                return false;
            }
        } else {
            if (!skuNo.equals(other.skuNo)) {
                return false;
            }
        }
        if (vendorId == null) {
            if (other.vendorId!= null) {
                return false;
            }
        } else {
            if (!vendorId.equals(other.vendorId)) {
                return false;
            }
        }
        if (siteNo == null) {
            if (other.siteNo!= null) {
                return false;
            }
        } else {
            if (!siteNo.equals(other.siteNo)) {
                return false;
            }
        }
        if (venType == null) {
            if (other.venType!= null) {
                return false;
            }
        } else {
            if (!venType.equals(other.venType)) {
                return false;
            }
        }
        if (vendorItem == null) {
            if (other.vendorItem!= null) {
                return false;
            }
        } else {
            if (!vendorItem.equals(other.vendorItem)) {
                return false;
            }
        }
        if (mainVendorItemFl == null) {
            if (other.mainVendorItemFl!= null) {
                return false;
            }
        } else {
            if (!mainVendorItemFl.equals(other.mainVendorItemFl)) {
                return false;
            }
        }
        if (eoq == null) {
            if (other.eoq!= null) {
                return false;
            }
        } else {
            if (!eoq.equals(other.eoq)) {
                return false;
            }
        }
        if (leadTime == null) {
            if (other.leadTime!= null) {
                return false;
            }
        } else {
            if (!leadTime.equals(other.leadTime)) {
                return false;
            }
        }
        if (unitCost == null) {
            if (other.unitCost!= null) {
                return false;
            }
        } else {
            if (!unitCost.equals(other.unitCost)) {
                return false;
            }
        }
        if (packCost == null) {
            if (other.packCost!= null) {
                return false;
            }
        } else {
            if (!packCost.equals(other.packCost)) {
                return false;
            }
        }
        if (packQty == null) {
            if (other.packQty!= null) {
                return false;
            }
        } else {
            if (!packQty.equals(other.packQty)) {
                return false;
            }
        }
        if (masterPackQty == null) {
            if (other.masterPackQty!= null) {
                return false;
            }
        } else {
            if (!masterPackQty.equals(other.masterPackQty)) {
                return false;
            }
        }
        if (packUm == null) {
            if (other.packUm!= null) {
                return false;
            }
        } else {
            if (!packUm.equals(other.packUm)) {
                return false;
            }
        }
        if (lastCostchgDt == null) {
            if (other.lastCostchgDt!= null) {
                return false;
            }
        } else {
            if (!lastCostchgDt.equals(other.lastCostchgDt)) {
                return false;
            }
        }
        if (lastCost == null) {
            if (other.lastCost!= null) {
                return false;
            }
        } else {
            if (!lastCost.equals(other.lastCost)) {
                return false;
            }
        }
        if (coreCost == null) {
            if (other.coreCost!= null) {
                return false;
            }
        } else {
            if (!coreCost.equals(other.coreCost)) {
                return false;
            }
        }
        if (netCost == null) {
            if (other.netCost!= null) {
                return false;
            }
        } else {
            if (!netCost.equals(other.netCost)) {
                return false;
            }
        }
        if (whseCostAmt == null) {
            if (other.whseCostAmt!= null) {
                return false;
            }
        } else {
            if (!whseCostAmt.equals(other.whseCostAmt)) {
                return false;
            }
        }
        if (costElement1Amt == null) {
            if (other.costElement1Amt!= null) {
                return false;
            }
        } else {
            if (!costElement1Amt.equals(other.costElement1Amt)) {
                return false;
            }
        }
        if (costElement2Amt == null) {
            if (other.costElement2Amt!= null) {
                return false;
            }
        } else {
            if (!costElement2Amt.equals(other.costElement2Amt)) {
                return false;
            }
        }
        if (batchSiteNo == null) {
            if (other.batchSiteNo!= null) {
                return false;
            }
        } else {
            if (!batchSiteNo.equals(other.batchSiteNo)) {
                return false;
            }
        }
        if (batchId == null) {
            if (other.batchId!= null) {
                return false;
            }
        } else {
            if (!batchId.equals(other.batchId)) {
                return false;
            }
        }
        if (loq == null) {
            if (other.loq!= null) {
                return false;
            }
        } else {
            if (!loq.equals(other.loq)) {
                return false;
            }
        }
        if (formDimension == null) {
            if (other.formDimension!= null) {
                return false;
            }
        } else {
            if (!formDimension.equals(other.formDimension)) {
                return false;
            }
        }
        if (freightAmt == null) {
            if (other.freightAmt!= null) {
                return false;
            }
        } else {
            if (!freightAmt.equals(other.freightAmt)) {
                return false;
            }
        }
        if (freightPct == null) {
            if (other.freightPct!= null) {
                return false;
            }
        } else {
            if (!freightPct.equals(other.freightPct)) {
                return false;
            }
        }
        if (orderAvailabilityStatus == null) {
            if (other.orderAvailabilityStatus!= null) {
                return false;
            }
        } else {
            if (!orderAvailabilityStatus.equals(other.orderAvailabilityStatus)) {
                return false;
            }
        }
        if (orderAvailabilityActiveDt == null) {
            if (other.orderAvailabilityActiveDt!= null) {
                return false;
            }
        } else {
            if (!orderAvailabilityActiveDt.equals(other.orderAvailabilityActiveDt)) {
                return false;
            }
        }
        if (externalRefId == null) {
            if (other.externalRefId!= null) {
                return false;
            }
        } else {
            if (!externalRefId.equals(other.externalRefId)) {
                return false;
            }
        }
        if (freightToleranceAmt == null) {
            if (other.freightToleranceAmt!= null) {
                return false;
            }
        } else {
            if (!freightToleranceAmt.equals(other.freightToleranceAmt)) {
                return false;
            }
        }
        if (freightToleranceAmtFl == null) {
            if (other.freightToleranceAmtFl!= null) {
                return false;
            }
        } else {
            if (!freightToleranceAmtFl.equals(other.freightToleranceAmtFl)) {
                return false;
            }
        }
        if (freightTolerancePct == null) {
            if (other.freightTolerancePct!= null) {
                return false;
            }
        } else {
            if (!freightTolerancePct.equals(other.freightTolerancePct)) {
                return false;
            }
        }
        if (freightTolerancePctFl == null) {
            if (other.freightTolerancePctFl!= null) {
                return false;
            }
        } else {
            if (!freightTolerancePctFl.equals(other.freightTolerancePctFl)) {
                return false;
            }
        }
        if (invToleranceAmt == null) {
            if (other.invToleranceAmt!= null) {
                return false;
            }
        } else {
            if (!invToleranceAmt.equals(other.invToleranceAmt)) {
                return false;
            }
        }
        if (invToleranceAmtFl == null) {
            if (other.invToleranceAmtFl!= null) {
                return false;
            }
        } else {
            if (!invToleranceAmtFl.equals(other.invToleranceAmtFl)) {
                return false;
            }
        }
        if (invTolerancePct == null) {
            if (other.invTolerancePct!= null) {
                return false;
            }
        } else {
            if (!invTolerancePct.equals(other.invTolerancePct)) {
                return false;
            }
        }
        if (invTolerancePctFl == null) {
            if (other.invTolerancePctFl!= null) {
                return false;
            }
        } else {
            if (!invTolerancePctFl.equals(other.invTolerancePctFl)) {
                return false;
            }
        }
        if (invToleranceQty == null) {
            if (other.invToleranceQty!= null) {
                return false;
            }
        } else {
            if (!invToleranceQty.equals(other.invToleranceQty)) {
                return false;
            }
        }
        if (invToleranceQtyFl == null) {
            if (other.invToleranceQtyFl!= null) {
                return false;
            }
        } else {
            if (!invToleranceQtyFl.equals(other.invToleranceQtyFl)) {
                return false;
            }
        }
        if (costFactor == null) {
            if (other.costFactor!= null) {
                return false;
            }
        } else {
            if (!costFactor.equals(other.costFactor)) {
                return false;
            }
        }
        if (operationType == null) {
            if (other.operationType!= null) {
                return false;
            }
        } else {
            if (!operationType.equals(other.operationType)) {
                return false;
            }
        }
        if (replicationNo == null) {
            if (other.replicationNo!= null) {
                return false;
            }
        } else {
            if (!replicationNo.equals(other.replicationNo)) {
                return false;
            }
        }
        if (registerReplicationNo == null) {
            if (other.registerReplicationNo!= null) {
                return false;
            }
        } else {
            if (!registerReplicationNo.equals(other.registerReplicationNo)) {
                return false;
            }
        }
        if (lastCostQty == null) {
            if (other.lastCostQty!= null) {
                return false;
            }
        } else {
            if (!lastCostQty.equals(other.lastCostQty)) {
                return false;
            }
        }
        if (upliftGroupId == null) {
            if (other.upliftGroupId!= null) {
                return false;
            }
        } else {
            if (!upliftGroupId.equals(other.upliftGroupId)) {
                return false;
            }
        }
        if (vendorCurrencyPackCost == null) {
            if (other.vendorCurrencyPackCost!= null) {
                return false;
            }
        } else {
            if (!vendorCurrencyPackCost.equals(other.vendorCurrencyPackCost)) {
                return false;
            }
        }
        if (supplierRecSched == null) {
            if (other.supplierRecSched!= null) {
                return false;
            }
        } else {
            if (!supplierRecSched.equals(other.supplierRecSched)) {
                return false;
            }
        }
        if (unitCostUm == null) {
            if (other.unitCostUm!= null) {
                return false;
            }
        } else {
            if (!unitCostUm.equals(other.unitCostUm)) {
                return false;
            }
        }
        if (releaseHorizon == null) {
            if (other.releaseHorizon!= null) {
                return false;
            }
        } else {
            if (!releaseHorizon.equals(other.releaseHorizon)) {
                return false;
            }
        }
        if (packWeight == null) {
            if (other.packWeight!= null) {
                return false;
            }
        } else {
            if (!packWeight.equals(other.packWeight)) {
                return false;
            }
        }
        if (packCube == null) {
            if (other.packCube!= null) {
                return false;
            }
        } else {
            if (!packCube.equals(other.packCube)) {
                return false;
            }
        }
        if (route == null) {
            if (other.route!= null) {
                return false;
            }
        } else {
            if (!route.equals(other.route)) {
                return false;
            }
        }
        if (immediateSupplier == null) {
            if (other.immediateSupplier!= null) {
                return false;
            }
        } else {
            if (!immediateSupplier.equals(other.immediateSupplier)) {
                return false;
            }
        }
        if (externalInternalSupplier == null) {
            if (other.externalInternalSupplier!= null) {
                return false;
            }
        } else {
            if (!externalInternalSupplier.equals(other.externalInternalSupplier)) {
                return false;
            }
        }
        if (length == null) {
            if (other.length!= null) {
                return false;
            }
        } else {
            if (!length.equals(other.length)) {
                return false;
            }
        }
        if (width == null) {
            if (other.width!= null) {
                return false;
            }
        } else {
            if (!width.equals(other.width)) {
                return false;
            }
        }
        if (height == null) {
            if (other.height!= null) {
                return false;
            }
        } else {
            if (!height.equals(other.height)) {
                return false;
            }
        }
        if (weight == null) {
            if (other.weight!= null) {
                return false;
            }
        } else {
            if (!weight.equals(other.weight)) {
                return false;
            }
        }
        if (cube == null) {
            if (other.cube!= null) {
                return false;
            }
        } else {
            if (!cube.equals(other.cube)) {
                return false;
            }
        }
        if (landedCostElements == null) {
            if (other.landedCostElements!= null) {
                return false;
            }
        } else {
            if (!landedCostElements.equals(other.landedCostElements)) {
                return false;
            }
        }
        if (additionalVendorItem == null) {
            if (other.additionalVendorItem!= null) {
                return false;
            }
        } else {
            if (!additionalVendorItem.equals(other.additionalVendorItem)) {
                return false;
            }
        }
        if (defaultClassCode == null) {
            if (other.defaultClassCode!= null) {
                return false;
            }
        } else {
            if (!defaultClassCode.equals(other.defaultClassCode)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        // If any ID columns are null, compare just the object instance it self.  This preserves objects that have not been assigned ID's yet.
        if (this.identifier.getSeqNo() == null) {
            return super.hashCode();
        }
        	
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier.getSeqNo() == null) ? 0 : identifier.getSeqNo().hashCode());
        return result;
    }

    /**
     * Implementation of comparable.<p>This implementation of Comparable uses 'Natural Ordering' of the primary key columns
     * starting with the first ID column and ending with the last.  The exception is that 'null' id columns are treated as highest value (AFTER).
     * If the other object is null, then this object will be returned as BEFORE.</p>
     * 
     * @see <a href="http://docs.oracle.com/javase/tutorial/collections/interfaces/order.html">Object Ordering</a>
     * 
     */
    public int compareTo(VendorItemEntity other) {
        return this.identifier.compareTo(other.identifier);
    }

    /**
     * This method displays bean properties for debugging purposes.  Do not use for anything else as it may change to enhance debugging in the future.
     * 
     */
    public String toString() {
        final StringBuilder temp = new StringBuilder();
        	
        temp.append("\n");
        temp.append("VendorItemEntity (VENDOR_ITEM)\n");
        temp.append("  ID   1 : seqNo                          : SEQ_NO                         : ").append(this.identifier.getSeqNo()).append("\n");
        temp.append("       2 : skuNo                          : SKU_NO                         : ").append(skuNo).append("\n");
        temp.append("       3 : vendorId                       : VENDOR_ID                      : ").append(vendorId).append("\n");
        temp.append("       4 : siteNo                         : SITE_NO                        : ").append(siteNo).append("\n");
        temp.append("       5 : venType                        : VEN_TYPE                       : ").append(venType).append("\n");
        temp.append("       6 : vendorItem                     : VENDOR_ITEM                    : ").append(vendorItem).append("\n");
        temp.append("       7 : mainVendorItemFl               : MAIN_VENDOR_ITEM_FL            : ").append(mainVendorItemFl).append("\n");
        temp.append("       8 : eoq                            : EOQ                            : ").append(eoq).append("\n");
        temp.append("       9 : leadTime                       : LEAD_TIME                      : ").append(leadTime).append("\n");
        temp.append("      10 : unitCost                       : UNIT_COST                      : ").append(unitCost).append("\n");
        temp.append("      11 : packCost                       : PACK_COST                      : ").append(packCost).append("\n");
        temp.append("      12 : packQty                        : PACK_QTY                       : ").append(packQty).append("\n");
        temp.append("      13 : masterPackQty                  : MASTER_PACK_QTY                : ").append(masterPackQty).append("\n");
        temp.append("      14 : packUm                         : PACK_UM                        : ").append(packUm).append("\n");
        temp.append("      15 : lastCostchgDt                  : LAST_COSTCHG_DT                : ").append(lastCostchgDt).append("\n");
        temp.append("      16 : lastCost                       : LAST_COST                      : ").append(lastCost).append("\n");
        temp.append("      17 : coreCost                       : CORE_COST                      : ").append(coreCost).append("\n");
        temp.append("      18 : netCost                        : NET_COST                       : ").append(netCost).append("\n");
        temp.append("      19 : whseCostAmt                    : WHSE_COST_AMT                  : ").append(whseCostAmt).append("\n");
        temp.append("      20 : costElement1Amt                : COST_ELEMENT1_AMT              : ").append(costElement1Amt).append("\n");
        temp.append("      21 : costElement2Amt                : COST_ELEMENT2_AMT              : ").append(costElement2Amt).append("\n");
        temp.append("      22 : batchSiteNo                    : BATCH_SITE_NO                  : ").append(batchSiteNo).append("\n");
        temp.append("      23 : batchId                        : BATCH_ID                       : ").append(batchId).append("\n");
        temp.append("      24 : loq                            : LOQ                            : ").append(loq).append("\n");
        temp.append("      25 : formDimension                  : FORM_DIMENSION                 : ").append(formDimension).append("\n");
        temp.append("      26 : freightAmt                     : FREIGHT_AMT                    : ").append(freightAmt).append("\n");
        temp.append("      27 : freightPct                     : FREIGHT_PCT                    : ").append(freightPct).append("\n");
        temp.append("  AD  28 : dateChanged                    : DATE_CHANGED                   : ").append(dateChanged).append("\n");
        temp.append("      29 : orderAvailabilityStatus        : ORDER_AVAILABILITY_STATUS      : ").append(orderAvailabilityStatus).append("\n");
        temp.append("      30 : orderAvailabilityActiveDt      : ORDER_AVAILABILITY_ACTIVE_DT   : ").append(orderAvailabilityActiveDt).append("\n");
        temp.append("      31 : externalRefId                  : EXTERNAL_REF_ID                : ").append(externalRefId).append("\n");
        temp.append("      32 : freightToleranceAmt            : FREIGHT_TOLERANCE_AMT          : ").append(freightToleranceAmt).append("\n");
        temp.append("      33 : freightToleranceAmtFl          : FREIGHT_TOLERANCE_AMT_FL       : ").append(freightToleranceAmtFl).append("\n");
        temp.append("      34 : freightTolerancePct            : FREIGHT_TOLERANCE_PCT          : ").append(freightTolerancePct).append("\n");
        temp.append("      35 : freightTolerancePctFl          : FREIGHT_TOLERANCE_PCT_FL       : ").append(freightTolerancePctFl).append("\n");
        temp.append("      36 : invToleranceAmt                : INV_TOLERANCE_AMT              : ").append(invToleranceAmt).append("\n");
        temp.append("      37 : invToleranceAmtFl              : INV_TOLERANCE_AMT_FL           : ").append(invToleranceAmtFl).append("\n");
        temp.append("      38 : invTolerancePct                : INV_TOLERANCE_PCT              : ").append(invTolerancePct).append("\n");
        temp.append("      39 : invTolerancePctFl              : INV_TOLERANCE_PCT_FL           : ").append(invTolerancePctFl).append("\n");
        temp.append("      40 : invToleranceQty                : INV_TOLERANCE_QTY              : ").append(invToleranceQty).append("\n");
        temp.append("      41 : invToleranceQtyFl              : INV_TOLERANCE_QTY_FL           : ").append(invToleranceQtyFl).append("\n");
        temp.append("      42 : costFactor                     : COST_FACTOR                    : ").append(costFactor).append("\n");
        temp.append("      43 : operationType                  : OPERATION_TYPE                 : ").append(operationType).append("\n");
        temp.append("      44 : replicationNo                  : REPLICATION_NO                 : ").append(replicationNo).append("\n");
        temp.append("      45 : registerReplicationNo          : REGISTER_REPLICATION_NO        : ").append(registerReplicationNo).append("\n");
        temp.append("  AD  46 : dateCreated                    : DATE_CREATED                   : ").append(dateCreated).append("\n");
        temp.append("  AD  47 : userCreated                    : USER_CREATED                   : ").append(userCreated).append("\n");
        temp.append("  AD  48 : dateModified                   : DATE_MODIFIED                  : ").append(dateModified).append("\n");
        temp.append("  AD  49 : userModified                   : USER_MODIFIED                  : ").append(userModified).append("\n");
        temp.append("      50 : lastCostQty                    : LAST_COST_QTY                  : ").append(lastCostQty).append("\n");
        temp.append("      51 : upliftGroupId                  : UPLIFT_GROUP_ID                : ").append(upliftGroupId).append("\n");
        temp.append("      52 : vendorCurrencyPackCost         : VENDOR_CURRENCY_PACK_COST      : ").append(vendorCurrencyPackCost).append("\n");
        temp.append("      53 : supplierRecSched               : SUPPLIER_REC_SCHED             : ").append(supplierRecSched).append("\n");
        temp.append("      54 : unitCostUm                     : UNIT_COST_UM                   : ").append(unitCostUm).append("\n");
        temp.append("      55 : releaseHorizon                 : RELEASE_HORIZON                : ").append(releaseHorizon).append("\n");
        temp.append("      56 : packWeight                     : PACK_WEIGHT                    : ").append(packWeight).append("\n");
        temp.append("      57 : packCube                       : PACK_CUBE                      : ").append(packCube).append("\n");
        temp.append("      58 : route                          : ROUTE                          : ").append(route).append("\n");
        temp.append("      59 : immediateSupplier              : IMMEDIATE_SUPPLIER             : ").append(immediateSupplier).append("\n");
        temp.append("      60 : externalInternalSupplier       : EXTERNAL_INTERNAL_SUPPLIER     : ").append(externalInternalSupplier).append("\n");
        temp.append("      61 : length                         : LENGTH                         : ").append(length).append("\n");
        temp.append("      62 : width                          : WIDTH                          : ").append(width).append("\n");
        temp.append("      63 : height                         : HEIGHT                         : ").append(height).append("\n");
        temp.append("      64 : weight                         : WEIGHT                         : ").append(weight).append("\n");
        temp.append("      65 : cube                           : CUBE                           : ").append(cube).append("\n");
        temp.append("      66 : landedCostElements             : LANDED_COST_ELEMENTS           : ").append(landedCostElements).append("\n");
        temp.append("      67 : additionalVendorItem           : ADDITIONAL_VENDOR_ITEM         : ").append(additionalVendorItem).append("\n");
        temp.append("      68 : defaultClassCode               : DEFAULT_CLASS_CODE             : ").append(defaultClassCode).append("\n");
        	
        return temp.toString();
    }

}
