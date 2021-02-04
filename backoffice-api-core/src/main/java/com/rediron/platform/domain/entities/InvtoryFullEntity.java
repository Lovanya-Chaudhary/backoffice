//
// @formatter:off
package com.rediron.platform.domain.entities;

import java.math.BigDecimal;
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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rediron.platform.domain.rnet.DtoToEntity;
import com.rediron.platform.domain.rnet.EntityManagerContext;
import com.rediron.platform.domain.rnet.RNetAbstractEntity;
import com.tomax.api.DataTransferObject;
import com.tomax.api.RNetExceptionProxy;
import com.tomax.api.RNetUnexpectedException;
import com.tomax.api.annotation.Child;
import com.tomax.inventory.dto.InvbysitFullDTO;
import com.tomax.inventory.dto.InvtoryDTOId;
import com.tomax.inventory.dto.InvtoryFullDTO;
import com.tomax.inventory.dto.ItemupcDTO;


@SuppressWarnings("all")
@Entity
@Table(name = "INVTORY")
public class InvtoryFullEntity
    extends RNetAbstractEntity<InvtoryFullEntity, InvtoryFullDTO>
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
    private InvtoryEntityId identifier;
    /**
     * <p>Bidirectional OneToMany inverse relationship to {@link InvbysitFullEntity InvbysitFullEntity}.</p><p><b>Column Mapping:</b><br/>
     * INVTORY.SKU_NO -> INVBYSIT.SKU_NO<br>
     * </p>
     * 
     */
    @Valid
    @OneToMany(mappedBy = "invtory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<InvbysitFullEntity> invbysits;
    /**
     * <p>Bidirectional OneToMany inverse relationship to {@link ItemupcEntity ItemupcEntity}.</p><p><b>Column Mapping:</b><br/>
     * INVTORY.SKU_NO -> ITEMUPC.SKU_NO<br>
     * </p>
     * 
     */
    @Valid
    @OneToMany(mappedBy = "invtory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ItemupcEntity> itemupcs;
    /**
     * <p>Maps to table column: {@code MFG_CD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 20, message = "MFG_CD should contain less than or equal to 20 characters")
    @Column(name = "MFG_CD")
    private String mfgCd;
    /**
     * <p>Maps to table column: {@code XREF_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 9, fraction = 0, message = "XREF_NO should have an integer component no longer than 9 and an a fraction component of exact size 0")
    @Column(name = "XREF_NO")
    private Integer xrefNo;
    /**
     * <p>Maps to table column: {@code DESCRIPTION}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 30, message = "DESCRIPTION should contain less than or equal to 30 characters")
    @NotNull(message = "DESCRIPTION can not be null")
    @Column(name = "DESCRIPTION")
    private String description;
    /**
     * <p>Maps to table column: {@code RECEIPT_DESC}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 26, message = "RECEIPT_DESC should contain less than or equal to 26 characters")
    @NotNull(message = "RECEIPT_DESC can not be null")
    @Column(name = "RECEIPT_DESC")
    private String receiptDesc;
    /**
     * <p>Maps to table column: {@code LOOKUP_DESC}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 250, message = "LOOKUP_DESC should contain less than or equal to 250 characters")
    @Column(name = "LOOKUP_DESC")
    private String lookupDesc;
    /**
     * <p>Maps to table column: {@code PKG_DESC}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 15, message = "PKG_DESC should contain less than or equal to 15 characters")
    @Column(name = "PKG_DESC")
    private String pkgDesc;
    /**
     * <p>Maps to table column: {@code DEPT_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "DEPT_NO should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @NotNull(message = "DEPT_NO can not be null")
    @Column(name = "DEPT_NO")
    private Integer deptNo;
    /**
     * <p>Maps to table column: {@code CLASS_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "CLASS_NO should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @NotNull(message = "CLASS_NO can not be null")
    @Column(name = "CLASS_NO")
    private Integer classNo;
    /**
     * <p>Maps to table column: {@code LINE_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 2, fraction = 0, message = "LINE_NO should have an integer component no longer than 2 and an a fraction component of exact size 0")
    @NotNull(message = "LINE_NO can not be null")
    @Column(name = "LINE_NO")
    private Integer lineNo;
    /**
     * <p>Maps to table column: {@code CODE1_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 5, message = "CODE1_ID should contain less than or equal to 5 characters")
    @Column(name = "CODE1_ID")
    private String code1Id;
    /**
     * <p>Maps to table column: {@code CODE2_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 5, message = "CODE2_ID should contain less than or equal to 5 characters")
    @Column(name = "CODE2_ID")
    private String code2Id;
    /**
     * <p>Maps to table column: {@code CODE3_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 5, message = "CODE3_ID should contain less than or equal to 5 characters")
    @Column(name = "CODE3_ID")
    private String code3Id;
    /**
     * <p>Maps to table column: {@code CREATE_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "CREATE_DT", insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDt;
    /**
     * <p>Maps to table column: {@code CHANGE_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "CHANGE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeDt;
    /**
     * <p>Maps to table column: {@code OTB_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "OTB_FL should contain less than or equal to 1 characters")
    @Column(name = "OTB_FL")
    private String otbFl;
    /**
     * <p>Maps to table column: {@code USE_SERIAL_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "USE_SERIAL_FL should contain less than or equal to 1 characters")
    @Column(name = "USE_SERIAL_FL")
    private String useSerialFl;
    /**
     * <p>Maps to table column: {@code GL_CAT_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 5, fraction = 0, message = "GL_CAT_ID should have an integer component no longer than 5 and an a fraction component of exact size 0")
    @Column(name = "GL_CAT_ID")
    private Integer glCatId;
    /**
     * <p>Maps to table column: {@code REPORT_FACTOR}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 2, message = "REPORT_FACTOR should have an integer component no longer than 4 and an a fraction component of exact size 2")
    @Column(name = "REPORT_FACTOR")
    private BigDecimal reportFactor;
    /**
     * <p>Maps to table column: {@code LCU_QTY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 2, message = "LCU_QTY should have an integer component no longer than 4 and an a fraction component of exact size 2")
    @Column(name = "LCU_QTY")
    private BigDecimal lcuQty;
    /**
     * <p>Maps to table column: {@code REPORT_UM}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 6, message = "REPORT_UM should contain less than or equal to 6 characters")
    @NotNull(message = "REPORT_UM can not be null")
    @Column(name = "REPORT_UM")
    private String reportUm;
    /**
     * <p>Maps to table column: {@code SELL_UM}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 6, message = "SELL_UM should contain less than or equal to 6 characters")
    @NotNull(message = "SELL_UM can not be null")
    @Column(name = "SELL_UM")
    private String sellUm;
    /**
     * <p>Maps to table column: {@code TICKET_TYPE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "TICKET_TYPE should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "TICKET_TYPE")
    private Integer ticketType;
    /**
     * <p>Maps to table column: {@code UNIT_WEIGHT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 2, message = "UNIT_WEIGHT should have an integer component no longer than 4 and an a fraction component of exact size 2")
    @Column(name = "UNIT_WEIGHT")
    private BigDecimal unitWeight;
    /**
     * <p>Maps to table column: {@code SHELF_TYPE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "SHELF_TYPE should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "SHELF_TYPE")
    private Integer shelfType;
    /**
     * <p>Maps to table column: {@code LINK_SKU_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 9, fraction = 0, message = "LINK_SKU_NO should have an integer component no longer than 9 and an a fraction component of exact size 0")
    @Column(name = "LINK_SKU_NO")
    private Integer linkSkuNo;
    /**
     * <p>Maps to table column: {@code REORDER_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "REORDER_FL should contain less than or equal to 1 characters")
    @Column(name = "REORDER_FL")
    private String reorderFl;
    /**
     * <p>Maps to table column: {@code SPECIAL_ITEM_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "SPECIAL_ITEM_FL should contain less than or equal to 1 characters")
    @Column(name = "SPECIAL_ITEM_FL")
    private String specialItemFl;
    /**
     * <p>Maps to table column: {@code WEEKS_HISTORY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "WEEKS_HISTORY should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "WEEKS_HISTORY")
    private Integer weeksHistory;
    /**
     * <p>Maps to table column: {@code PERIODS_HISTORY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "PERIODS_HISTORY should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "PERIODS_HISTORY")
    private Integer periodsHistory;
    /**
     * <p>Maps to table column: {@code STORE_CPN_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "STORE_CPN_FL should contain less than or equal to 1 characters")
    @Column(name = "STORE_CPN_FL")
    private String storeCpnFl;
    /**
     * <p>Maps to table column: {@code REPORT_CD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "REPORT_CD should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "REPORT_CD")
    private Integer reportCd;
    /**
     * <p>Maps to table column: {@code DOWNLOAD_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "DOWNLOAD_FL should contain less than or equal to 1 characters")
    @Column(name = "DOWNLOAD_FL")
    private String downloadFl;
    /**
     * <p>Maps to table column: {@code DISCONTINUED_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "DISCONTINUED_FL should contain less than or equal to 1 characters")
    @Column(name = "DISCONTINUED_FL")
    private String discontinuedFl;
    /**
     * <p>Maps to table column: {@code EQUIV_FACTOR}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 4, message = "EQUIV_FACTOR should have an integer component no longer than 6 and an a fraction component of exact size 4")
    @Column(name = "EQUIV_FACTOR")
    private BigDecimal equivFactor;
    /**
     * <p>Maps to table column: {@code NEW_LABEL_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "NEW_LABEL_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date newLabelDt;
    /**
     * <p>Maps to table column: {@code CHANGE_LABEL_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "CHANGE_LABEL_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeLabelDt;
    /**
     * <p>Maps to table column: {@code LABEL_DESC1}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 30, message = "LABEL_DESC1 should contain less than or equal to 30 characters")
    @Column(name = "LABEL_DESC1")
    private String labelDesc1;
    /**
     * <p>Maps to table column: {@code LABEL_DESC2}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 30, message = "LABEL_DESC2 should contain less than or equal to 30 characters")
    @Column(name = "LABEL_DESC2")
    private String labelDesc2;
    /**
     * <p>Maps to table column: {@code EQUIV_UM}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 4, message = "EQUIV_UM should contain less than or equal to 4 characters")
    @Column(name = "EQUIV_UM")
    private String equivUm;
    /**
     * <p>Maps to table column: {@code WAREHOUSE_INNER_PACK}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 5, fraction = 2, message = "WAREHOUSE_INNER_PACK should have an integer component no longer than 5 and an a fraction component of exact size 2")
    @Column(name = "WAREHOUSE_INNER_PACK")
    private BigDecimal warehouseInnerPack;
    /**
     * <p>Maps to table column: {@code OBSOLETE_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "OBSOLETE_FL should contain less than or equal to 1 characters")
    @Column(name = "OBSOLETE_FL")
    private String obsoleteFl;
    /**
     * <p>Maps to table column: {@code PER_CAR_QTY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PER_CAR_QTY should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PER_CAR_QTY")
    private Integer perCarQty;
    /**
     * <p>Maps to table column: {@code PRICE_LIST_SEQ}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 7, fraction = 0, message = "PRICE_LIST_SEQ should have an integer component no longer than 7 and an a fraction component of exact size 0")
    @Column(name = "PRICE_LIST_SEQ")
    private Integer priceListSeq;
    /**
     * <p>Maps to table column: {@code SUPERSEDED_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "SUPERSEDED_FL should contain less than or equal to 1 characters")
    @Column(name = "SUPERSEDED_FL")
    private String supersededFl;
    /**
     * <p>Maps to table column: {@code UNIT_CUBE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "UNIT_CUBE should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "UNIT_CUBE")
    private BigDecimal unitCube;
    /**
     * <p>Maps to table column: {@code CAPTURE_ORDER_NO_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "CAPTURE_ORDER_NO_FL should contain less than or equal to 1 characters")
    @Column(name = "CAPTURE_ORDER_NO_FL")
    private String captureOrderNoFl;
    /**
     * <p>Maps to table column: {@code CAPTURE_SERIAL_NO_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "CAPTURE_SERIAL_NO_FL should contain less than or equal to 1 characters")
    @Column(name = "CAPTURE_SERIAL_NO_FL")
    private String captureSerialNoFl;
    /**
     * <p>Maps to table column: {@code VENITM_SEQ_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 9, fraction = 0, message = "VENITM_SEQ_NO should have an integer component no longer than 9 and an a fraction component of exact size 0")
    @Column(name = "VENITM_SEQ_NO")
    private Integer venitmSeqNo;
    /**
     * <p>Maps to table column: {@code UPC_ID_SEQ_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 9, fraction = 0, message = "UPC_ID_SEQ_NO should have an integer component no longer than 9 and an a fraction component of exact size 0")
    @Column(name = "UPC_ID_SEQ_NO")
    private Integer upcIdSeqNo;
    /**
     * <p>Maps to table column: {@code EMPLOYEE_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 12, message = "EMPLOYEE_ID should contain less than or equal to 12 characters")
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;
    /**
     * <p>Maps to table column: {@code SUPPLEMENTAL_UPC_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "SUPPLEMENTAL_UPC_FL should contain less than or equal to 1 characters")
    @Column(name = "SUPPLEMENTAL_UPC_FL")
    private String supplementalUpcFl;
    /**
     * <p>Maps to table column: {@code WEIGHED_ITEM_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "WEIGHED_ITEM_FL should contain less than or equal to 1 characters")
    @Column(name = "WEIGHED_ITEM_FL")
    private String weighedItemFl;
    /**
     * <p>Maps to table column: {@code SUGGESTED_SELL_CD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "SUGGESTED_SELL_CD should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "SUGGESTED_SELL_CD")
    private Integer suggestedSellCd;
    /**
     * <p>Maps to table column: {@code VALIDATION_SEQ_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "VALIDATION_SEQ_NO should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "VALIDATION_SEQ_NO")
    private Integer validationSeqNo;
    /**
     * <p>Maps to table column: {@code RESTRICT_SALE_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "RESTRICT_SALE_FL should contain less than or equal to 1 characters")
    @Column(name = "RESTRICT_SALE_FL")
    private String restrictSaleFl;
    /**
     * <p>Maps to table column: {@code AVAIL_SALE_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "AVAIL_SALE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date availSaleDt;
    /**
     * <p>Maps to table column: {@code INV_ITEM_LOCK_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "INV_ITEM_LOCK_FL should contain less than or equal to 1 characters")
    @Column(name = "INV_ITEM_LOCK_FL")
    private String invItemLockFl;
    /**
     * <p>Maps to table column: {@code NON_INVENTORY_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "NON_INVENTORY_FL should contain less than or equal to 1 characters")
    @Column(name = "NON_INVENTORY_FL")
    private String nonInventoryFl;
    /**
     * <p>Maps to table column: {@code PRIVATE_BRAND_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "PRIVATE_BRAND_FL should contain less than or equal to 1 characters")
    @Column(name = "PRIVATE_BRAND_FL")
    private String privateBrandFl;
    /**
     * <p>Maps to table column: {@code LAST_ITEM_CHANGE_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_ITEM_CHANGE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastItemChangeDt;
    /**
     * <p>Maps to table column: {@code LAST_ITEM_CHANGE_VENDOR_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 9, message = "LAST_ITEM_CHANGE_VENDOR_ID should contain less than or equal to 9 characters")
    @Column(name = "LAST_ITEM_CHANGE_VENDOR_ID")
    private String lastItemChangeVendorId;
    /**
     * <p>Maps to table column: {@code TENDER_PROGRAM_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "TENDER_PROGRAM_ID should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "TENDER_PROGRAM_ID")
    private Integer tenderProgramId;
    /**
     * <p>Maps to table column: {@code EXTERNAL_REF_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 240, message = "EXTERNAL_REF_ID should contain less than or equal to 240 characters")
    @Column(name = "EXTERNAL_REF_ID")
    private String externalRefId;
    /**
     * <p>Maps to table column: {@code INV_TYPE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 5, message = "INV_TYPE should contain less than or equal to 5 characters")
    @Column(name = "INV_TYPE")
    private String invType;
    /**
     * <p>Maps to table column: {@code WARRANTY_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "WARRANTY_FL should contain less than or equal to 1 characters")
    @Column(name = "WARRANTY_FL")
    private String warrantyFl;
    /**
     * <p>Maps to table column: {@code SYSTEM_WARRANTY_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "SYSTEM_WARRANTY_FL should contain less than or equal to 1 characters")
    @Column(name = "SYSTEM_WARRANTY_FL")
    private String systemWarrantyFl;
    /**
     * <p>Maps to table column: {@code SUB_INV_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "SUB_INV_FL should contain less than or equal to 1 characters")
    @Column(name = "SUB_INV_FL")
    private String subInvFl;
    /**
     * <p>Maps to table column: {@code REPLICATION_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 16, fraction = 0, message = "REPLICATION_NO should have an integer component no longer than 16 and an a fraction component of exact size 0")
    @Column(name = "REPLICATION_NO")
    private Long replicationNo;
    /**
     * <p>Maps to table column: {@code OPERATION_TYPE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "OPERATION_TYPE should contain less than or equal to 1 characters")
    @Column(name = "OPERATION_TYPE")
    private String operationType;
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
     * <p>Maps to table column: {@code REPLACEMENT_SKU_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 9, fraction = 0, message = "REPLACEMENT_SKU_NO should have an integer component no longer than 9 and an a fraction component of exact size 0")
    @Column(name = "REPLACEMENT_SKU_NO")
    private Integer replacementSkuNo;
    /**
     * <p>Maps to table column: {@code HAZ_UN_NUMBER_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 12, message = "HAZ_UN_NUMBER_ID should contain less than or equal to 12 characters")
    @Column(name = "HAZ_UN_NUMBER_ID")
    private String hazUnNumberId;
    /**
     * <p>Maps to table column: {@code HAZ_CLASS_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 12, message = "HAZ_CLASS_ID should contain less than or equal to 12 characters")
    @Column(name = "HAZ_CLASS_ID")
    private String hazClassId;
    /**
     * <p>Maps to table column: {@code ROLLUP_BUCKET_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "ROLLUP_BUCKET_FL should contain less than or equal to 1 characters")
    @Column(name = "ROLLUP_BUCKET_FL")
    private String rollupBucketFl;
    /**
     * <p>Maps to table column: {@code TARE_TABLE_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 10, message = "TARE_TABLE_NO should contain less than or equal to 10 characters")
    @Column(name = "TARE_TABLE_NO")
    private String tareTableNo;
    /**
     * <p>Maps to table column: {@code CATCH_WEIGHT_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "CATCH_WEIGHT_FL should contain less than or equal to 1 characters")
    @Column(name = "CATCH_WEIGHT_FL")
    private String catchWeightFl;
    /**
     * <p>Maps to table column: {@code PRODUCT_HIER4}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 7, message = "PRODUCT_HIER4 should contain less than or equal to 7 characters")
    @Column(name = "PRODUCT_HIER4")
    private String productHier4;
    /**
     * <p>Maps to table column: {@code PRODUCT_HIER5}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 7, message = "PRODUCT_HIER5 should contain less than or equal to 7 characters")
    @Column(name = "PRODUCT_HIER5")
    private String productHier5;
    /**
     * <p>Maps to table column: {@code PRODUCT_HIER6}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 7, message = "PRODUCT_HIER6 should contain less than or equal to 7 characters")
    @Column(name = "PRODUCT_HIER6")
    private String productHier6;
    /**
     * <p>Maps to table column: {@code RRP_INV_TYPE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 11, message = "RRP_INV_TYPE should contain less than or equal to 11 characters")
    @Column(name = "RRP_INV_TYPE")
    private String rrpInvType;
    /**
     * <p>Maps to table column: {@code HIERARCHY_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 7, fraction = 0, message = "HIERARCHY_ID should have an integer component no longer than 7 and an a fraction component of exact size 0")
    @Column(name = "HIERARCHY_ID")
    private Integer hierarchyId;
    /**
     * <p>Maps to table column: {@code NEGATIVE_PRICE_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "NEGATIVE_PRICE_FL should contain less than or equal to 1 characters")
    @Column(name = "NEGATIVE_PRICE_FL")
    private String negativePriceFl;
    /**
     * <p>Maps to table column: {@code PACKUP_ACTION_ID}.</p><p><b>Schema Remarks:</b> This is the FK to PACKUP_ACTION.</p>
     * 
     */
    @Digits(integer = 7, fraction = 0, message = "PACKUP_ACTION_ID should have an integer component no longer than 7 and an a fraction component of exact size 0")
    @Column(name = "PACKUP_ACTION_ID")
    private Integer packupActionId;
    /**
     * <p>Maps to table column: {@code OWNER_ID}.</p><p><b>Schema Remarks:</b> This is the site that authored this invtory record.  Used for franchise models.</p>
     * 
     */
    @Digits(integer = 8, fraction = 0, message = "OWNER_ID should have an integer component no longer than 8 and an a fraction component of exact size 0")
    @Column(name = "OWNER_ID")
    private Integer ownerId;
    /**
     * <p>Maps to table column: {@code FLEXIBLE_ITEM_FL}.</p><p><b>Schema Remarks:</b> Flag indicating that the item has a FORCE component associated with it</p>
     * 
     */
    @Size(max = 1, message = "FLEXIBLE_ITEM_FL should contain less than or equal to 1 characters")
    @Column(name = "FLEXIBLE_ITEM_FL")
    private String flexibleItemFl;
    /**
     * <p>Maps to table column: {@code SUPPLY_ITEM_FL}.</p><p><b>Schema Remarks:</b> When set to Y the item is a supply item, Inventory levels and valuations are not tracked, however these items can be purchased, and transferred</p>
     * 
     */
    @Size(max = 1, message = "SUPPLY_ITEM_FL should contain less than or equal to 1 characters")
    @Column(name = "SUPPLY_ITEM_FL")
    private String supplyItemFl;
    /**
     * <p>Maps to table column: {@code ATTRIBUTE1_ID}.</p><p><b>Schema Remarks:</b> Attribute 1 ID</p>
     * 
     */
    @Digits(integer = 6, fraction = 0, message = "ATTRIBUTE1_ID should have an integer component no longer than 6 and an a fraction component of exact size 0")
    @Column(name = "ATTRIBUTE1_ID")
    private Integer attribute1Id;
    /**
     * <p>Maps to table column: {@code ATTRIBUTE2_ID}.</p><p><b>Schema Remarks:</b> Attribute 2 ID</p>
     * 
     */
    @Digits(integer = 6, fraction = 0, message = "ATTRIBUTE2_ID should have an integer component no longer than 6 and an a fraction component of exact size 0")
    @Column(name = "ATTRIBUTE2_ID")
    private Integer attribute2Id;
    /**
     * <p>Maps to table column: {@code ATTRIBUTE3_ID}.</p><p><b>Schema Remarks:</b> Attribute 3 ID</p>
     * 
     */
    @Digits(integer = 6, fraction = 0, message = "ATTRIBUTE3_ID should have an integer component no longer than 6 and an a fraction component of exact size 0")
    @Column(name = "ATTRIBUTE3_ID")
    private Integer attribute3Id;
    /**
     * <p>Maps to table column: {@code ATTRIBUTE1_VALUE}.</p><p><b>Schema Remarks:</b> Attribute 1 Value</p>
     * 
     */
    @Size(max = 250, message = "ATTRIBUTE1_VALUE should contain less than or equal to 250 characters")
    @Column(name = "ATTRIBUTE1_VALUE")
    private String attribute1Value;
    /**
     * <p>Maps to table column: {@code ATTRIBUTE2_VALUE}.</p><p><b>Schema Remarks:</b> Attribute 2 Value</p>
     * 
     */
    @Size(max = 250, message = "ATTRIBUTE2_VALUE should contain less than or equal to 250 characters")
    @Column(name = "ATTRIBUTE2_VALUE")
    private String attribute2Value;
    /**
     * <p>Maps to table column: {@code ATTRIBUTE3_VALUE}.</p><p><b>Schema Remarks:</b> Attribute 3 Value</p>
     * 
     */
    @Size(max = 250, message = "ATTRIBUTE3_VALUE should contain less than or equal to 250 characters")
    @Column(name = "ATTRIBUTE3_VALUE")
    private String attribute3Value;
    /**
     * <p>Maps to table column: {@code CORE_FL}.</p><p><b>Schema Remarks:</b> Set to Y when this item represents a CORE item</p>
     * 
     */
    @Size(max = 1, message = "CORE_FL should contain less than or equal to 1 characters")
    @Column(name = "CORE_FL")
    private String coreFl;
    /**
     * <p>Maps to table column: {@code KIT_MEMBER_FL}.</p><p><b>Schema Remarks:</b> Will be set to Y by the kit maintenance form and/or integration.  Indicates that this item is a participant in a kit of some type</p>
     * 
     */
    @Size(max = 1, message = "KIT_MEMBER_FL should contain less than or equal to 1 characters")
    @Column(name = "KIT_MEMBER_FL")
    private String kitMemberFl;
    /**
     * <p>Maps to table column: {@code PRIMARY_VENDOR_ITEM}.</p><p><b>Schema Remarks:</b> Primary Vendor Item</p>
     * 
     */
    @Size(max = 20, message = "PRIMARY_VENDOR_ITEM should contain less than or equal to 20 characters")
    @Column(name = "PRIMARY_VENDOR_ITEM")
    private String primaryVendorItem;
    /**
     * <p>Maps to table column: {@code AUTO_GEN_SERIALS_FL}.</p><p><b>Schema Remarks:</b> Flag to indicate that serial numbers are automatically system generated</p>
     * 
     */
    @Size(max = 1, message = "AUTO_GEN_SERIALS_FL should contain less than or equal to 1 characters")
    @Column(name = "AUTO_GEN_SERIALS_FL")
    private String autoGenSerialsFl;
    /**
     * <p>Maps to table column: {@code REGIONAL_PRICE_FL}.</p><p><b>Schema Remarks:</b> Indicates if this item participates in regional pricing</p>
     * 
     */
    @Size(max = 1, message = "REGIONAL_PRICE_FL should contain less than or equal to 1 characters")
    @Column(name = "REGIONAL_PRICE_FL")
    private String regionalPriceFl;
    /**
     * <p>Maps to table column: {@code REGIONAL_PROMO_FL}.</p><p><b>Schema Remarks:</b> Indicates if this item participates in regional promotional pricing</p>
     * 
     */
    @Size(max = 1, message = "REGIONAL_PROMO_FL should contain less than or equal to 1 characters")
    @Column(name = "REGIONAL_PROMO_FL")
    private String regionalPromoFl;
    /**
     * <p>Maps to table column: {@code REGIONAL_SUPERSEDE_FL}.</p><p><b>Schema Remarks:</b> Indicates that there is some kind of supersede involved with this item</p>
     * 
     */
    @Size(max = 1, message = "REGIONAL_SUPERSEDE_FL should contain less than or equal to 1 characters")
    @Column(name = "REGIONAL_SUPERSEDE_FL")
    private String regionalSupersedeFl;
    /**
     * <p>Maps to table column: {@code WEB_ENABLED_FL}.</p><p><b>Schema Remarks:</b> Indicates whether this item is eligible for ecommerce integration</p>
     * 
     */
    @Size(max = 1, message = "WEB_ENABLED_FL should contain less than or equal to 1 characters")
    @Column(name = "WEB_ENABLED_FL")
    private String webEnabledFl;
    /**
     * <p>Maps to table column: {@code ECOMM_NON_INVENTORY_FL}.</p><p><b>Schema Remarks:</b> Indicates whether this item is considered perpetually stocked for Ecommerce functionality</p>
     * 
     */
    @Size(max = 1, message = "ECOMM_NON_INVENTORY_FL should contain less than or equal to 1 characters")
    @Column(name = "ECOMM_NON_INVENTORY_FL")
    private String ecommNonInventoryFl;

    /**
     * <P>Empty constructor used to create new InvtoryFullEntity.</P>
     * 
     */
    public InvtoryFullEntity() {
        this.identifier = new InvtoryEntityId();
        	
        _init();
    }

    /**
     * Constructor used to create a new InvtoryFullEntity where the identifier will be created by copying the fields of the provided sourceId.
     * 
     */
    public InvtoryFullEntity(InvtoryEntityId sourceId) {
        this.identifier = new InvtoryEntityId(sourceId.getSkuNo());
        	
        _init();
    }

    /**
     * Constructor used to create a InvtoryFullEntity where the identifier will be created internally using the id arguments provided.  This is a convenient constructor used instead of instantiating a new identifier.
     * 
     */
    public InvtoryFullEntity(Integer skuNo) {
        this.identifier = new InvtoryEntityId(skuNo);
        	
        _init();
    }

    /**
     * Constructor used to create a InvtoryFullEntity where contents are provided by an object array typically returned by a native query
     * 
     */
    public InvtoryFullEntity(Object[] objectArray) {
        this.identifier = new InvtoryEntityId(((objectArray[ 0 ] == null)?null:((BigDecimal) objectArray[ 0 ]).intValue()));
        	
        this.mfgCd = ((objectArray[ 1 ] == null)?null:((String) objectArray[ 1 ]));
        this.xrefNo = ((objectArray[ 2 ] == null)?null:((BigDecimal) objectArray[ 2 ]).intValue());
        this.description = ((objectArray[ 3 ] == null)?null:((String) objectArray[ 3 ]));
        this.receiptDesc = ((objectArray[ 4 ] == null)?null:((String) objectArray[ 4 ]));
        this.lookupDesc = ((objectArray[ 5 ] == null)?null:((String) objectArray[ 5 ]));
        this.pkgDesc = ((objectArray[ 6 ] == null)?null:((String) objectArray[ 6 ]));
        this.deptNo = ((objectArray[ 7 ] == null)?null:((BigDecimal) objectArray[ 7 ]).intValue());
        this.classNo = ((objectArray[ 8 ] == null)?null:((BigDecimal) objectArray[ 8 ]).intValue());
        this.lineNo = ((objectArray[ 9 ] == null)?null:((BigDecimal) objectArray[ 9 ]).intValue());
        this.code1Id = ((objectArray[ 10 ] == null)?null:((String) objectArray[ 10 ]));
        this.code2Id = ((objectArray[ 11 ] == null)?null:((String) objectArray[ 11 ]));
        this.code3Id = ((objectArray[ 12 ] == null)?null:((String) objectArray[ 12 ]));
        this.createDt = ((objectArray[ 13 ] == null)?null:new Date(((Timestamp) objectArray[ 13 ]).getTime()));
        this.changeDt = ((objectArray[ 14 ] == null)?null:new Date(((Timestamp) objectArray[ 14 ]).getTime()));
        this.otbFl = ((objectArray[ 15 ] == null)?null:((String) objectArray[ 15 ]));
        this.useSerialFl = ((objectArray[ 16 ] == null)?null:((String) objectArray[ 16 ]));
        this.glCatId = ((objectArray[ 17 ] == null)?null:((BigDecimal) objectArray[ 17 ]).intValue());
        this.reportFactor = ((objectArray[ 18 ] == null)?null:((BigDecimal) objectArray[ 18 ]));
        this.lcuQty = ((objectArray[ 19 ] == null)?null:((BigDecimal) objectArray[ 19 ]));
        this.reportUm = ((objectArray[ 20 ] == null)?null:((String) objectArray[ 20 ]));
        this.sellUm = ((objectArray[ 21 ] == null)?null:((String) objectArray[ 21 ]));
        this.ticketType = ((objectArray[ 22 ] == null)?null:((BigDecimal) objectArray[ 22 ]).intValue());
        this.unitWeight = ((objectArray[ 23 ] == null)?null:((BigDecimal) objectArray[ 23 ]));
        this.shelfType = ((objectArray[ 24 ] == null)?null:((BigDecimal) objectArray[ 24 ]).intValue());
        this.linkSkuNo = ((objectArray[ 25 ] == null)?null:((BigDecimal) objectArray[ 25 ]).intValue());
        this.reorderFl = ((objectArray[ 26 ] == null)?null:((String) objectArray[ 26 ]));
        this.specialItemFl = ((objectArray[ 27 ] == null)?null:((String) objectArray[ 27 ]));
        this.weeksHistory = ((objectArray[ 28 ] == null)?null:((BigDecimal) objectArray[ 28 ]).intValue());
        this.periodsHistory = ((objectArray[ 29 ] == null)?null:((BigDecimal) objectArray[ 29 ]).intValue());
        this.storeCpnFl = ((objectArray[ 30 ] == null)?null:((String) objectArray[ 30 ]));
        this.reportCd = ((objectArray[ 31 ] == null)?null:((BigDecimal) objectArray[ 31 ]).intValue());
        this.downloadFl = ((objectArray[ 32 ] == null)?null:((String) objectArray[ 32 ]));
        this.discontinuedFl = ((objectArray[ 33 ] == null)?null:((String) objectArray[ 33 ]));
        this.equivFactor = ((objectArray[ 34 ] == null)?null:((BigDecimal) objectArray[ 34 ]));
        this.newLabelDt = ((objectArray[ 35 ] == null)?null:new Date(((Timestamp) objectArray[ 35 ]).getTime()));
        this.changeLabelDt = ((objectArray[ 36 ] == null)?null:new Date(((Timestamp) objectArray[ 36 ]).getTime()));
        this.labelDesc1 = ((objectArray[ 37 ] == null)?null:((String) objectArray[ 37 ]));
        this.labelDesc2 = ((objectArray[ 38 ] == null)?null:((String) objectArray[ 38 ]));
        this.equivUm = ((objectArray[ 39 ] == null)?null:((String) objectArray[ 39 ]));
        this.warehouseInnerPack = ((objectArray[ 40 ] == null)?null:((BigDecimal) objectArray[ 40 ]));
        this.obsoleteFl = ((objectArray[ 41 ] == null)?null:((String) objectArray[ 41 ]));
        this.perCarQty = ((objectArray[ 42 ] == null)?null:((BigDecimal) objectArray[ 42 ]).intValue());
        this.priceListSeq = ((objectArray[ 43 ] == null)?null:((BigDecimal) objectArray[ 43 ]).intValue());
        this.supersededFl = ((objectArray[ 44 ] == null)?null:((String) objectArray[ 44 ]));
        this.unitCube = ((objectArray[ 45 ] == null)?null:((BigDecimal) objectArray[ 45 ]));
        this.captureOrderNoFl = ((objectArray[ 46 ] == null)?null:((String) objectArray[ 46 ]));
        this.captureSerialNoFl = ((objectArray[ 47 ] == null)?null:((String) objectArray[ 47 ]));
        this.venitmSeqNo = ((objectArray[ 48 ] == null)?null:((BigDecimal) objectArray[ 48 ]).intValue());
        this.upcIdSeqNo = ((objectArray[ 49 ] == null)?null:((BigDecimal) objectArray[ 49 ]).intValue());
        this.employeeId = ((objectArray[ 50 ] == null)?null:((String) objectArray[ 50 ]));
        this.supplementalUpcFl = ((objectArray[ 51 ] == null)?null:((String) objectArray[ 51 ]));
        this.weighedItemFl = ((objectArray[ 52 ] == null)?null:((String) objectArray[ 52 ]));
        this.suggestedSellCd = ((objectArray[ 53 ] == null)?null:((BigDecimal) objectArray[ 53 ]).intValue());
        this.validationSeqNo = ((objectArray[ 54 ] == null)?null:((BigDecimal) objectArray[ 54 ]).intValue());
        this.restrictSaleFl = ((objectArray[ 55 ] == null)?null:((String) objectArray[ 55 ]));
        this.availSaleDt = ((objectArray[ 56 ] == null)?null:new Date(((Timestamp) objectArray[ 56 ]).getTime()));
        this.invItemLockFl = ((objectArray[ 57 ] == null)?null:((String) objectArray[ 57 ]));
        this.nonInventoryFl = ((objectArray[ 58 ] == null)?null:((String) objectArray[ 58 ]));
        this.privateBrandFl = ((objectArray[ 59 ] == null)?null:((String) objectArray[ 59 ]));
        this.lastItemChangeDt = ((objectArray[ 60 ] == null)?null:new Date(((Timestamp) objectArray[ 60 ]).getTime()));
        this.lastItemChangeVendorId = ((objectArray[ 61 ] == null)?null:((String) objectArray[ 61 ]));
        this.tenderProgramId = ((objectArray[ 62 ] == null)?null:((BigDecimal) objectArray[ 62 ]).intValue());
        this.externalRefId = ((objectArray[ 63 ] == null)?null:((String) objectArray[ 63 ]));
        this.invType = ((objectArray[ 64 ] == null)?null:((String) objectArray[ 64 ]));
        this.warrantyFl = ((objectArray[ 65 ] == null)?null:((String) objectArray[ 65 ]));
        this.systemWarrantyFl = ((objectArray[ 66 ] == null)?null:((String) objectArray[ 66 ]));
        this.subInvFl = ((objectArray[ 67 ] == null)?null:((String) objectArray[ 67 ]));
        this.replicationNo = ((objectArray[ 68 ] == null)?null:((BigDecimal) objectArray[ 68 ]).longValue());
        this.operationType = ((objectArray[ 69 ] == null)?null:((String) objectArray[ 69 ]));
        this.registerReplicationNo = ((objectArray[ 70 ] == null)?null:((BigDecimal) objectArray[ 70 ]).longValue());
        this.dateCreated = ((objectArray[ 71 ] == null)?null:new Date(((Timestamp) objectArray[ 71 ]).getTime()));
        this.userCreated = ((objectArray[ 72 ] == null)?null:((String) objectArray[ 72 ]));
        this.dateModified = ((objectArray[ 73 ] == null)?null:new Date(((Timestamp) objectArray[ 73 ]).getTime()));
        this.userModified = ((objectArray[ 74 ] == null)?null:((String) objectArray[ 74 ]));
        this.replacementSkuNo = ((objectArray[ 75 ] == null)?null:((BigDecimal) objectArray[ 75 ]).intValue());
        this.hazUnNumberId = ((objectArray[ 76 ] == null)?null:((String) objectArray[ 76 ]));
        this.hazClassId = ((objectArray[ 77 ] == null)?null:((String) objectArray[ 77 ]));
        this.rollupBucketFl = ((objectArray[ 78 ] == null)?null:((String) objectArray[ 78 ]));
        this.tareTableNo = ((objectArray[ 79 ] == null)?null:((String) objectArray[ 79 ]));
        this.catchWeightFl = ((objectArray[ 80 ] == null)?null:((String) objectArray[ 80 ]));
        this.productHier4 = ((objectArray[ 81 ] == null)?null:((String) objectArray[ 81 ]));
        this.productHier5 = ((objectArray[ 82 ] == null)?null:((String) objectArray[ 82 ]));
        this.productHier6 = ((objectArray[ 83 ] == null)?null:((String) objectArray[ 83 ]));
        this.rrpInvType = ((objectArray[ 84 ] == null)?null:((String) objectArray[ 84 ]));
        this.hierarchyId = ((objectArray[ 85 ] == null)?null:((BigDecimal) objectArray[ 85 ]).intValue());
        this.negativePriceFl = ((objectArray[ 86 ] == null)?null:((String) objectArray[ 86 ]));
        this.packupActionId = ((objectArray[ 87 ] == null)?null:((BigDecimal) objectArray[ 87 ]).intValue());
        this.ownerId = ((objectArray[ 88 ] == null)?null:((BigDecimal) objectArray[ 88 ]).intValue());
        this.flexibleItemFl = ((objectArray[ 89 ] == null)?null:((String) objectArray[ 89 ]));
        this.supplyItemFl = ((objectArray[ 90 ] == null)?null:((String) objectArray[ 90 ]));
        this.attribute1Id = ((objectArray[ 91 ] == null)?null:((BigDecimal) objectArray[ 91 ]).intValue());
        this.attribute2Id = ((objectArray[ 92 ] == null)?null:((BigDecimal) objectArray[ 92 ]).intValue());
        this.attribute3Id = ((objectArray[ 93 ] == null)?null:((BigDecimal) objectArray[ 93 ]).intValue());
        this.attribute1Value = ((objectArray[ 94 ] == null)?null:((String) objectArray[ 94 ]));
        this.attribute2Value = ((objectArray[ 95 ] == null)?null:((String) objectArray[ 95 ]));
        this.attribute3Value = ((objectArray[ 96 ] == null)?null:((String) objectArray[ 96 ]));
        this.coreFl = ((objectArray[ 97 ] == null)?null:((String) objectArray[ 97 ]));
        this.kitMemberFl = ((objectArray[ 98 ] == null)?null:((String) objectArray[ 98 ]));
        this.primaryVendorItem = ((objectArray[ 99 ] == null)?null:((String) objectArray[ 99 ]));
        this.autoGenSerialsFl = ((objectArray[ 100 ] == null)?null:((String) objectArray[ 100 ]));
        this.regionalPriceFl = ((objectArray[ 101 ] == null)?null:((String) objectArray[ 101 ]));
        this.regionalPromoFl = ((objectArray[ 102 ] == null)?null:((String) objectArray[ 102 ]));
        this.regionalSupersedeFl = ((objectArray[ 103 ] == null)?null:((String) objectArray[ 103 ]));
        this.webEnabledFl = ((objectArray[ 104 ] == null)?null:((String) objectArray[ 104 ]));
        this.ecommNonInventoryFl = ((objectArray[ 105 ] == null)?null:((String) objectArray[ 105 ]));
        	
        _init();
    }

    /**
     * <P>Copy constructor used to copy a InvtoryFullEntity where the identifier will be created by copying the fields of the provided sourceId.</P>
     * 
     */
    public InvtoryFullEntity(InvtoryFullEntity source, InvtoryEntityId sourceId) {
        this.identifier = new InvtoryEntityId(sourceId.getSkuNo());
        	
        _init();
        	
        this.mfgCd = source.getMfgCd();
        this.xrefNo = source.getXrefNo();
        this.description = source.getDescription();
        this.receiptDesc = source.getReceiptDesc();
        this.lookupDesc = source.getLookupDesc();
        this.pkgDesc = source.getPkgDesc();
        this.deptNo = source.getDeptNo();
        this.classNo = source.getClassNo();
        this.lineNo = source.getLineNo();
        this.code1Id = source.getCode1Id();
        this.code2Id = source.getCode2Id();
        this.code3Id = source.getCode3Id();
        this.createDt = source.getCreateDt();
        this.changeDt = source.getChangeDt();
        this.otbFl = source.getOtbFl();
        this.useSerialFl = source.getUseSerialFl();
        this.glCatId = source.getGlCatId();
        this.reportFactor = source.getReportFactor();
        this.lcuQty = source.getLcuQty();
        this.reportUm = source.getReportUm();
        this.sellUm = source.getSellUm();
        this.ticketType = source.getTicketType();
        this.unitWeight = source.getUnitWeight();
        this.shelfType = source.getShelfType();
        this.linkSkuNo = source.getLinkSkuNo();
        this.reorderFl = source.getReorderFl();
        this.specialItemFl = source.getSpecialItemFl();
        this.weeksHistory = source.getWeeksHistory();
        this.periodsHistory = source.getPeriodsHistory();
        this.storeCpnFl = source.getStoreCpnFl();
        this.reportCd = source.getReportCd();
        this.downloadFl = source.getDownloadFl();
        this.discontinuedFl = source.getDiscontinuedFl();
        this.equivFactor = source.getEquivFactor();
        this.newLabelDt = source.getNewLabelDt();
        this.changeLabelDt = source.getChangeLabelDt();
        this.labelDesc1 = source.getLabelDesc1();
        this.labelDesc2 = source.getLabelDesc2();
        this.equivUm = source.getEquivUm();
        this.warehouseInnerPack = source.getWarehouseInnerPack();
        this.obsoleteFl = source.getObsoleteFl();
        this.perCarQty = source.getPerCarQty();
        this.priceListSeq = source.getPriceListSeq();
        this.supersededFl = source.getSupersededFl();
        this.unitCube = source.getUnitCube();
        this.captureOrderNoFl = source.getCaptureOrderNoFl();
        this.captureSerialNoFl = source.getCaptureSerialNoFl();
        this.venitmSeqNo = source.getVenitmSeqNo();
        this.upcIdSeqNo = source.getUpcIdSeqNo();
        this.employeeId = source.getEmployeeId();
        this.supplementalUpcFl = source.getSupplementalUpcFl();
        this.weighedItemFl = source.getWeighedItemFl();
        this.suggestedSellCd = source.getSuggestedSellCd();
        this.validationSeqNo = source.getValidationSeqNo();
        this.restrictSaleFl = source.getRestrictSaleFl();
        this.availSaleDt = source.getAvailSaleDt();
        this.invItemLockFl = source.getInvItemLockFl();
        this.nonInventoryFl = source.getNonInventoryFl();
        this.privateBrandFl = source.getPrivateBrandFl();
        this.lastItemChangeDt = source.getLastItemChangeDt();
        this.lastItemChangeVendorId = source.getLastItemChangeVendorId();
        this.tenderProgramId = source.getTenderProgramId();
        this.externalRefId = source.getExternalRefId();
        this.invType = source.getInvType();
        this.warrantyFl = source.getWarrantyFl();
        this.systemWarrantyFl = source.getSystemWarrantyFl();
        this.subInvFl = source.getSubInvFl();
        this.replicationNo = source.getReplicationNo();
        this.operationType = source.getOperationType();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.replacementSkuNo = source.getReplacementSkuNo();
        this.hazUnNumberId = source.getHazUnNumberId();
        this.hazClassId = source.getHazClassId();
        this.rollupBucketFl = source.getRollupBucketFl();
        this.tareTableNo = source.getTareTableNo();
        this.catchWeightFl = source.getCatchWeightFl();
        this.productHier4 = source.getProductHier4();
        this.productHier5 = source.getProductHier5();
        this.productHier6 = source.getProductHier6();
        this.rrpInvType = source.getRrpInvType();
        this.hierarchyId = source.getHierarchyId();
        this.negativePriceFl = source.getNegativePriceFl();
        this.packupActionId = source.getPackupActionId();
        this.ownerId = source.getOwnerId();
        this.flexibleItemFl = source.getFlexibleItemFl();
        this.supplyItemFl = source.getSupplyItemFl();
        this.attribute1Id = source.getAttribute1Id();
        this.attribute2Id = source.getAttribute2Id();
        this.attribute3Id = source.getAttribute3Id();
        this.attribute1Value = source.getAttribute1Value();
        this.attribute2Value = source.getAttribute2Value();
        this.attribute3Value = source.getAttribute3Value();
        this.coreFl = source.getCoreFl();
        this.kitMemberFl = source.getKitMemberFl();
        this.primaryVendorItem = source.getPrimaryVendorItem();
        this.autoGenSerialsFl = source.getAutoGenSerialsFl();
        this.regionalPriceFl = source.getRegionalPriceFl();
        this.regionalPromoFl = source.getRegionalPromoFl();
        this.regionalSupersedeFl = source.getRegionalSupersedeFl();
        this.webEnabledFl = source.getWebEnabledFl();
        this.ecommNonInventoryFl = source.getEcommNonInventoryFl();
        	
        for (InvbysitFullEntity tempEntity: source.getInvbysits()) {
            tempEntity = new InvbysitFullEntity(tempEntity);
            tempEntity.setIdentifier(new InvbysitEntityId());
            addInvbysit(new InvbysitFullEntity(tempEntity));
        }
        for (ItemupcEntity tempEntity: source.getItemupcs()) {
            tempEntity = new ItemupcEntity(tempEntity);
            tempEntity.setIdentifier(new ItemupcEntityId());
            addItemupc(new ItemupcEntity(tempEntity));
        }
    }

    /**
     * <P>Copy constructor used to copy a InvtoryFullEntity including its identifier.</P>
     * 
     */
    public InvtoryFullEntity(InvtoryFullEntity source) {
        this.identifier = new InvtoryEntityId(source.getIdentifier().getSkuNo());
        	
        _init();
        	
        this.mfgCd = source.getMfgCd();
        this.xrefNo = source.getXrefNo();
        this.description = source.getDescription();
        this.receiptDesc = source.getReceiptDesc();
        this.lookupDesc = source.getLookupDesc();
        this.pkgDesc = source.getPkgDesc();
        this.deptNo = source.getDeptNo();
        this.classNo = source.getClassNo();
        this.lineNo = source.getLineNo();
        this.code1Id = source.getCode1Id();
        this.code2Id = source.getCode2Id();
        this.code3Id = source.getCode3Id();
        this.createDt = source.getCreateDt();
        this.changeDt = source.getChangeDt();
        this.otbFl = source.getOtbFl();
        this.useSerialFl = source.getUseSerialFl();
        this.glCatId = source.getGlCatId();
        this.reportFactor = source.getReportFactor();
        this.lcuQty = source.getLcuQty();
        this.reportUm = source.getReportUm();
        this.sellUm = source.getSellUm();
        this.ticketType = source.getTicketType();
        this.unitWeight = source.getUnitWeight();
        this.shelfType = source.getShelfType();
        this.linkSkuNo = source.getLinkSkuNo();
        this.reorderFl = source.getReorderFl();
        this.specialItemFl = source.getSpecialItemFl();
        this.weeksHistory = source.getWeeksHistory();
        this.periodsHistory = source.getPeriodsHistory();
        this.storeCpnFl = source.getStoreCpnFl();
        this.reportCd = source.getReportCd();
        this.downloadFl = source.getDownloadFl();
        this.discontinuedFl = source.getDiscontinuedFl();
        this.equivFactor = source.getEquivFactor();
        this.newLabelDt = source.getNewLabelDt();
        this.changeLabelDt = source.getChangeLabelDt();
        this.labelDesc1 = source.getLabelDesc1();
        this.labelDesc2 = source.getLabelDesc2();
        this.equivUm = source.getEquivUm();
        this.warehouseInnerPack = source.getWarehouseInnerPack();
        this.obsoleteFl = source.getObsoleteFl();
        this.perCarQty = source.getPerCarQty();
        this.priceListSeq = source.getPriceListSeq();
        this.supersededFl = source.getSupersededFl();
        this.unitCube = source.getUnitCube();
        this.captureOrderNoFl = source.getCaptureOrderNoFl();
        this.captureSerialNoFl = source.getCaptureSerialNoFl();
        this.venitmSeqNo = source.getVenitmSeqNo();
        this.upcIdSeqNo = source.getUpcIdSeqNo();
        this.employeeId = source.getEmployeeId();
        this.supplementalUpcFl = source.getSupplementalUpcFl();
        this.weighedItemFl = source.getWeighedItemFl();
        this.suggestedSellCd = source.getSuggestedSellCd();
        this.validationSeqNo = source.getValidationSeqNo();
        this.restrictSaleFl = source.getRestrictSaleFl();
        this.availSaleDt = source.getAvailSaleDt();
        this.invItemLockFl = source.getInvItemLockFl();
        this.nonInventoryFl = source.getNonInventoryFl();
        this.privateBrandFl = source.getPrivateBrandFl();
        this.lastItemChangeDt = source.getLastItemChangeDt();
        this.lastItemChangeVendorId = source.getLastItemChangeVendorId();
        this.tenderProgramId = source.getTenderProgramId();
        this.externalRefId = source.getExternalRefId();
        this.invType = source.getInvType();
        this.warrantyFl = source.getWarrantyFl();
        this.systemWarrantyFl = source.getSystemWarrantyFl();
        this.subInvFl = source.getSubInvFl();
        this.replicationNo = source.getReplicationNo();
        this.operationType = source.getOperationType();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.replacementSkuNo = source.getReplacementSkuNo();
        this.hazUnNumberId = source.getHazUnNumberId();
        this.hazClassId = source.getHazClassId();
        this.rollupBucketFl = source.getRollupBucketFl();
        this.tareTableNo = source.getTareTableNo();
        this.catchWeightFl = source.getCatchWeightFl();
        this.productHier4 = source.getProductHier4();
        this.productHier5 = source.getProductHier5();
        this.productHier6 = source.getProductHier6();
        this.rrpInvType = source.getRrpInvType();
        this.hierarchyId = source.getHierarchyId();
        this.negativePriceFl = source.getNegativePriceFl();
        this.packupActionId = source.getPackupActionId();
        this.ownerId = source.getOwnerId();
        this.flexibleItemFl = source.getFlexibleItemFl();
        this.supplyItemFl = source.getSupplyItemFl();
        this.attribute1Id = source.getAttribute1Id();
        this.attribute2Id = source.getAttribute2Id();
        this.attribute3Id = source.getAttribute3Id();
        this.attribute1Value = source.getAttribute1Value();
        this.attribute2Value = source.getAttribute2Value();
        this.attribute3Value = source.getAttribute3Value();
        this.coreFl = source.getCoreFl();
        this.kitMemberFl = source.getKitMemberFl();
        this.primaryVendorItem = source.getPrimaryVendorItem();
        this.autoGenSerialsFl = source.getAutoGenSerialsFl();
        this.regionalPriceFl = source.getRegionalPriceFl();
        this.regionalPromoFl = source.getRegionalPromoFl();
        this.regionalSupersedeFl = source.getRegionalSupersedeFl();
        this.webEnabledFl = source.getWebEnabledFl();
        this.ecommNonInventoryFl = source.getEcommNonInventoryFl();
        	
        for (InvbysitFullEntity tempEntity: source.getInvbysits()) {
            tempEntity = new InvbysitFullEntity(tempEntity);
            tempEntity.setIdentifier(new InvbysitEntityId());
            addInvbysit(new InvbysitFullEntity(tempEntity));
        }
        for (ItemupcEntity tempEntity: source.getItemupcs()) {
            tempEntity = new ItemupcEntity(tempEntity);
            tempEntity.setIdentifier(new ItemupcEntityId());
            addItemupc(new ItemupcEntity(tempEntity));
        }
    }

    /**
     * <P>Copy constructor used to copy a InvtoryEntity where the identifier will be created by copying the fields of the provided sourceId.</P>
     * 
     */
    public InvtoryFullEntity(InvtoryEntity source, InvtoryEntityId sourceId) {
        this.identifier = new InvtoryEntityId(sourceId.getSkuNo());
        	
        _init();
        	
        this.mfgCd = source.getMfgCd();
        this.xrefNo = source.getXrefNo();
        this.description = source.getDescription();
        this.receiptDesc = source.getReceiptDesc();
        this.lookupDesc = source.getLookupDesc();
        this.pkgDesc = source.getPkgDesc();
        this.deptNo = source.getDeptNo();
        this.classNo = source.getClassNo();
        this.lineNo = source.getLineNo();
        this.code1Id = source.getCode1Id();
        this.code2Id = source.getCode2Id();
        this.code3Id = source.getCode3Id();
        this.createDt = source.getCreateDt();
        this.changeDt = source.getChangeDt();
        this.otbFl = source.getOtbFl();
        this.useSerialFl = source.getUseSerialFl();
        this.glCatId = source.getGlCatId();
        this.reportFactor = source.getReportFactor();
        this.lcuQty = source.getLcuQty();
        this.reportUm = source.getReportUm();
        this.sellUm = source.getSellUm();
        this.ticketType = source.getTicketType();
        this.unitWeight = source.getUnitWeight();
        this.shelfType = source.getShelfType();
        this.linkSkuNo = source.getLinkSkuNo();
        this.reorderFl = source.getReorderFl();
        this.specialItemFl = source.getSpecialItemFl();
        this.weeksHistory = source.getWeeksHistory();
        this.periodsHistory = source.getPeriodsHistory();
        this.storeCpnFl = source.getStoreCpnFl();
        this.reportCd = source.getReportCd();
        this.downloadFl = source.getDownloadFl();
        this.discontinuedFl = source.getDiscontinuedFl();
        this.equivFactor = source.getEquivFactor();
        this.newLabelDt = source.getNewLabelDt();
        this.changeLabelDt = source.getChangeLabelDt();
        this.labelDesc1 = source.getLabelDesc1();
        this.labelDesc2 = source.getLabelDesc2();
        this.equivUm = source.getEquivUm();
        this.warehouseInnerPack = source.getWarehouseInnerPack();
        this.obsoleteFl = source.getObsoleteFl();
        this.perCarQty = source.getPerCarQty();
        this.priceListSeq = source.getPriceListSeq();
        this.supersededFl = source.getSupersededFl();
        this.unitCube = source.getUnitCube();
        this.captureOrderNoFl = source.getCaptureOrderNoFl();
        this.captureSerialNoFl = source.getCaptureSerialNoFl();
        this.venitmSeqNo = source.getVenitmSeqNo();
        this.upcIdSeqNo = source.getUpcIdSeqNo();
        this.employeeId = source.getEmployeeId();
        this.supplementalUpcFl = source.getSupplementalUpcFl();
        this.weighedItemFl = source.getWeighedItemFl();
        this.suggestedSellCd = source.getSuggestedSellCd();
        this.validationSeqNo = source.getValidationSeqNo();
        this.restrictSaleFl = source.getRestrictSaleFl();
        this.availSaleDt = source.getAvailSaleDt();
        this.invItemLockFl = source.getInvItemLockFl();
        this.nonInventoryFl = source.getNonInventoryFl();
        this.privateBrandFl = source.getPrivateBrandFl();
        this.lastItemChangeDt = source.getLastItemChangeDt();
        this.lastItemChangeVendorId = source.getLastItemChangeVendorId();
        this.tenderProgramId = source.getTenderProgramId();
        this.externalRefId = source.getExternalRefId();
        this.invType = source.getInvType();
        this.warrantyFl = source.getWarrantyFl();
        this.systemWarrantyFl = source.getSystemWarrantyFl();
        this.subInvFl = source.getSubInvFl();
        this.replicationNo = source.getReplicationNo();
        this.operationType = source.getOperationType();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.replacementSkuNo = source.getReplacementSkuNo();
        this.hazUnNumberId = source.getHazUnNumberId();
        this.hazClassId = source.getHazClassId();
        this.rollupBucketFl = source.getRollupBucketFl();
        this.tareTableNo = source.getTareTableNo();
        this.catchWeightFl = source.getCatchWeightFl();
        this.productHier4 = source.getProductHier4();
        this.productHier5 = source.getProductHier5();
        this.productHier6 = source.getProductHier6();
        this.rrpInvType = source.getRrpInvType();
        this.hierarchyId = source.getHierarchyId();
        this.negativePriceFl = source.getNegativePriceFl();
        this.packupActionId = source.getPackupActionId();
        this.ownerId = source.getOwnerId();
        this.flexibleItemFl = source.getFlexibleItemFl();
        this.supplyItemFl = source.getSupplyItemFl();
        this.attribute1Id = source.getAttribute1Id();
        this.attribute2Id = source.getAttribute2Id();
        this.attribute3Id = source.getAttribute3Id();
        this.attribute1Value = source.getAttribute1Value();
        this.attribute2Value = source.getAttribute2Value();
        this.attribute3Value = source.getAttribute3Value();
        this.coreFl = source.getCoreFl();
        this.kitMemberFl = source.getKitMemberFl();
        this.primaryVendorItem = source.getPrimaryVendorItem();
        this.autoGenSerialsFl = source.getAutoGenSerialsFl();
        this.regionalPriceFl = source.getRegionalPriceFl();
        this.regionalPromoFl = source.getRegionalPromoFl();
        this.regionalSupersedeFl = source.getRegionalSupersedeFl();
        this.webEnabledFl = source.getWebEnabledFl();
        this.ecommNonInventoryFl = source.getEcommNonInventoryFl();
    }

    /**
     * <P>Copy constructor used to copy a InvtoryEntity including its identifier.</P>
     * 
     */
    public InvtoryFullEntity(InvtoryEntity source) {
        this.identifier = new InvtoryEntityId(source.getIdentifier().getSkuNo());
        	
        _init();
        	
        this.mfgCd = source.getMfgCd();
        this.xrefNo = source.getXrefNo();
        this.description = source.getDescription();
        this.receiptDesc = source.getReceiptDesc();
        this.lookupDesc = source.getLookupDesc();
        this.pkgDesc = source.getPkgDesc();
        this.deptNo = source.getDeptNo();
        this.classNo = source.getClassNo();
        this.lineNo = source.getLineNo();
        this.code1Id = source.getCode1Id();
        this.code2Id = source.getCode2Id();
        this.code3Id = source.getCode3Id();
        this.createDt = source.getCreateDt();
        this.changeDt = source.getChangeDt();
        this.otbFl = source.getOtbFl();
        this.useSerialFl = source.getUseSerialFl();
        this.glCatId = source.getGlCatId();
        this.reportFactor = source.getReportFactor();
        this.lcuQty = source.getLcuQty();
        this.reportUm = source.getReportUm();
        this.sellUm = source.getSellUm();
        this.ticketType = source.getTicketType();
        this.unitWeight = source.getUnitWeight();
        this.shelfType = source.getShelfType();
        this.linkSkuNo = source.getLinkSkuNo();
        this.reorderFl = source.getReorderFl();
        this.specialItemFl = source.getSpecialItemFl();
        this.weeksHistory = source.getWeeksHistory();
        this.periodsHistory = source.getPeriodsHistory();
        this.storeCpnFl = source.getStoreCpnFl();
        this.reportCd = source.getReportCd();
        this.downloadFl = source.getDownloadFl();
        this.discontinuedFl = source.getDiscontinuedFl();
        this.equivFactor = source.getEquivFactor();
        this.newLabelDt = source.getNewLabelDt();
        this.changeLabelDt = source.getChangeLabelDt();
        this.labelDesc1 = source.getLabelDesc1();
        this.labelDesc2 = source.getLabelDesc2();
        this.equivUm = source.getEquivUm();
        this.warehouseInnerPack = source.getWarehouseInnerPack();
        this.obsoleteFl = source.getObsoleteFl();
        this.perCarQty = source.getPerCarQty();
        this.priceListSeq = source.getPriceListSeq();
        this.supersededFl = source.getSupersededFl();
        this.unitCube = source.getUnitCube();
        this.captureOrderNoFl = source.getCaptureOrderNoFl();
        this.captureSerialNoFl = source.getCaptureSerialNoFl();
        this.venitmSeqNo = source.getVenitmSeqNo();
        this.upcIdSeqNo = source.getUpcIdSeqNo();
        this.employeeId = source.getEmployeeId();
        this.supplementalUpcFl = source.getSupplementalUpcFl();
        this.weighedItemFl = source.getWeighedItemFl();
        this.suggestedSellCd = source.getSuggestedSellCd();
        this.validationSeqNo = source.getValidationSeqNo();
        this.restrictSaleFl = source.getRestrictSaleFl();
        this.availSaleDt = source.getAvailSaleDt();
        this.invItemLockFl = source.getInvItemLockFl();
        this.nonInventoryFl = source.getNonInventoryFl();
        this.privateBrandFl = source.getPrivateBrandFl();
        this.lastItemChangeDt = source.getLastItemChangeDt();
        this.lastItemChangeVendorId = source.getLastItemChangeVendorId();
        this.tenderProgramId = source.getTenderProgramId();
        this.externalRefId = source.getExternalRefId();
        this.invType = source.getInvType();
        this.warrantyFl = source.getWarrantyFl();
        this.systemWarrantyFl = source.getSystemWarrantyFl();
        this.subInvFl = source.getSubInvFl();
        this.replicationNo = source.getReplicationNo();
        this.operationType = source.getOperationType();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.replacementSkuNo = source.getReplacementSkuNo();
        this.hazUnNumberId = source.getHazUnNumberId();
        this.hazClassId = source.getHazClassId();
        this.rollupBucketFl = source.getRollupBucketFl();
        this.tareTableNo = source.getTareTableNo();
        this.catchWeightFl = source.getCatchWeightFl();
        this.productHier4 = source.getProductHier4();
        this.productHier5 = source.getProductHier5();
        this.productHier6 = source.getProductHier6();
        this.rrpInvType = source.getRrpInvType();
        this.hierarchyId = source.getHierarchyId();
        this.negativePriceFl = source.getNegativePriceFl();
        this.packupActionId = source.getPackupActionId();
        this.ownerId = source.getOwnerId();
        this.flexibleItemFl = source.getFlexibleItemFl();
        this.supplyItemFl = source.getSupplyItemFl();
        this.attribute1Id = source.getAttribute1Id();
        this.attribute2Id = source.getAttribute2Id();
        this.attribute3Id = source.getAttribute3Id();
        this.attribute1Value = source.getAttribute1Value();
        this.attribute2Value = source.getAttribute2Value();
        this.attribute3Value = source.getAttribute3Value();
        this.coreFl = source.getCoreFl();
        this.kitMemberFl = source.getKitMemberFl();
        this.primaryVendorItem = source.getPrimaryVendorItem();
        this.autoGenSerialsFl = source.getAutoGenSerialsFl();
        this.regionalPriceFl = source.getRegionalPriceFl();
        this.regionalPromoFl = source.getRegionalPromoFl();
        this.regionalSupersedeFl = source.getRegionalSupersedeFl();
        this.webEnabledFl = source.getWebEnabledFl();
        this.ecommNonInventoryFl = source.getEcommNonInventoryFl();
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public InvtoryEntityId getIdentifier() {
        return this.identifier;
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public void setIdentifier(InvtoryEntityId identifier) {
        this.identifier = identifier;
    }

    /**
     * Getter for {@link #invbysits invbysits}
     * 
     */
    @Child
    public Set<InvbysitFullEntity> getInvbysits() {
        return this.invbysits;
    }

    /**
     * Adder for invbysits
     * 
     */
    public void addInvbysit(InvbysitFullEntity invbysits) {
        if (invbysits == null) {
            return ;
        }
        	
        invbysits.setInvtory(this);
        getInvbysits().add(invbysits);
    }

    /**
     * Getter for {@link #itemupcs itemupcs}
     * 
     */
    @Child
    public Set<ItemupcEntity> getItemupcs() {
        return this.itemupcs;
    }

    /**
     * Adder for itemupcs
     * 
     */
    public void addItemupc(ItemupcEntity itemupcs) {
        if (itemupcs == null) {
            return ;
        }
        	
        itemupcs.setInvtory(this);
        getItemupcs().add(itemupcs);
    }

    /**
     * Getter for {@link InvtoryEntityId#skuNo identifier.skuNo}
     * 
     */
    @Access(AccessType.PROPERTY)
    @Column(name = "SKU_NO", insertable = false, updatable = false)
    public Integer getSkuNo() {
        return this.identifier.getSkuNo();
    }

    /**
     * Setter for {@link InvtoryEntityId#skuNo identifier.skuNo}
     * 
     */
    public Integer setSkuNo(Integer skuNo) {
        this.identifier.setSkuNo(skuNo);
        return this.identifier.getSkuNo();
    }

    /**
     * Getter for {@link #mfgCd mfgCd}
     * 
     */
    public String getMfgCd() {
        return this.mfgCd;
    }

    /**
     * Setter for {@link #mfgCd mfgCd}
     * 
     */
    public String setMfgCd(String mfgCd) {
        this.mfgCd = mfgCd;
        return this.mfgCd;
    }

    /**
     * Getter for {@link #xrefNo xrefNo}
     * 
     */
    public Integer getXrefNo() {
        return this.xrefNo;
    }

    /**
     * Setter for {@link #xrefNo xrefNo}
     * 
     */
    public Integer setXrefNo(Integer xrefNo) {
        this.xrefNo = xrefNo;
        return this.xrefNo;
    }

    /**
     * Getter for {@link #description description}
     * 
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for {@link #description description}
     * 
     */
    public String setDescription(String description) {
        this.description = description;
        return this.description;
    }

    /**
     * Getter for {@link #receiptDesc receiptDesc}
     * 
     */
    public String getReceiptDesc() {
        return this.receiptDesc;
    }

    /**
     * Setter for {@link #receiptDesc receiptDesc}
     * 
     */
    public String setReceiptDesc(String receiptDesc) {
        this.receiptDesc = receiptDesc;
        return this.receiptDesc;
    }

    /**
     * Getter for {@link #lookupDesc lookupDesc}
     * 
     */
    public String getLookupDesc() {
        return this.lookupDesc;
    }

    /**
     * Setter for {@link #lookupDesc lookupDesc}
     * 
     */
    public String setLookupDesc(String lookupDesc) {
        this.lookupDesc = lookupDesc;
        return this.lookupDesc;
    }

    /**
     * Getter for {@link #pkgDesc pkgDesc}
     * 
     */
    public String getPkgDesc() {
        return this.pkgDesc;
    }

    /**
     * Setter for {@link #pkgDesc pkgDesc}
     * 
     */
    public String setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
        return this.pkgDesc;
    }

    /**
     * Getter for {@link #deptNo deptNo}
     * 
     */
    public Integer getDeptNo() {
        return this.deptNo;
    }

    /**
     * Setter for {@link #deptNo deptNo}
     * 
     */
    public Integer setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
        return this.deptNo;
    }

    /**
     * Getter for {@link #classNo classNo}
     * 
     */
    public Integer getClassNo() {
        return this.classNo;
    }

    /**
     * Setter for {@link #classNo classNo}
     * 
     */
    public Integer setClassNo(Integer classNo) {
        this.classNo = classNo;
        return this.classNo;
    }

    /**
     * Getter for {@link #lineNo lineNo}
     * 
     */
    public Integer getLineNo() {
        return this.lineNo;
    }

    /**
     * Setter for {@link #lineNo lineNo}
     * 
     */
    public Integer setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
        return this.lineNo;
    }

    /**
     * Getter for {@link #code1Id code1Id}
     * 
     */
    public String getCode1Id() {
        return this.code1Id;
    }

    /**
     * Setter for {@link #code1Id code1Id}
     * 
     */
    public String setCode1Id(String code1Id) {
        this.code1Id = code1Id;
        return this.code1Id;
    }

    /**
     * Getter for {@link #code2Id code2Id}
     * 
     */
    public String getCode2Id() {
        return this.code2Id;
    }

    /**
     * Setter for {@link #code2Id code2Id}
     * 
     */
    public String setCode2Id(String code2Id) {
        this.code2Id = code2Id;
        return this.code2Id;
    }

    /**
     * Getter for {@link #code3Id code3Id}
     * 
     */
    public String getCode3Id() {
        return this.code3Id;
    }

    /**
     * Setter for {@link #code3Id code3Id}
     * 
     */
    public String setCode3Id(String code3Id) {
        this.code3Id = code3Id;
        return this.code3Id;
    }

    /**
     * Getter for {@link #createDt createDt}
     * 
     */
    public Date getCreateDt() {
        return this.createDt;
    }

    /**
     * Setter for {@link #createDt createDt}
     * 
     */
    public Date setCreateDt(Date createDt) {
        this.createDt = createDt;
        return this.createDt;
    }

    /**
     * Getter for {@link #changeDt changeDt}
     * 
     */
    public Date getChangeDt() {
        return this.changeDt;
    }

    /**
     * Setter for {@link #changeDt changeDt}
     * 
     */
    public Date setChangeDt(Date changeDt) {
        this.changeDt = changeDt;
        return this.changeDt;
    }

    /**
     * Getter for {@link #otbFl otbFl}
     * 
     */
    public String getOtbFl() {
        return this.otbFl;
    }

    /**
     * Setter for {@link #otbFl otbFl}
     * 
     */
    public String setOtbFl(String otbFl) {
        this.otbFl = otbFl;
        return this.otbFl;
    }

    /**
     * Getter for {@link #useSerialFl useSerialFl}
     * 
     */
    public String getUseSerialFl() {
        return this.useSerialFl;
    }

    /**
     * Setter for {@link #useSerialFl useSerialFl}
     * 
     */
    public String setUseSerialFl(String useSerialFl) {
        this.useSerialFl = useSerialFl;
        return this.useSerialFl;
    }

    /**
     * Getter for {@link #glCatId glCatId}
     * 
     */
    public Integer getGlCatId() {
        return this.glCatId;
    }

    /**
     * Setter for {@link #glCatId glCatId}
     * 
     */
    public Integer setGlCatId(Integer glCatId) {
        this.glCatId = glCatId;
        return this.glCatId;
    }

    /**
     * Getter for {@link #reportFactor reportFactor}
     * 
     */
    public BigDecimal getReportFactor() {
        return this.reportFactor;
    }

    /**
     * Setter for {@link #reportFactor reportFactor}
     * 
     */
    public BigDecimal setReportFactor(BigDecimal reportFactor) {
        if ((reportFactor!= null)&&(reportFactor.scale()!= 2)) {
            reportFactor = reportFactor.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.reportFactor = reportFactor;
        return this.reportFactor;
    }

    /**
     * Getter for {@link #lcuQty lcuQty}
     * 
     */
    public BigDecimal getLcuQty() {
        return this.lcuQty;
    }

    /**
     * Setter for {@link #lcuQty lcuQty}
     * 
     */
    public BigDecimal setLcuQty(BigDecimal lcuQty) {
        if ((lcuQty!= null)&&(lcuQty.scale()!= 2)) {
            lcuQty = lcuQty.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.lcuQty = lcuQty;
        return this.lcuQty;
    }

    /**
     * Getter for {@link #reportUm reportUm}
     * 
     */
    public String getReportUm() {
        return this.reportUm;
    }

    /**
     * Setter for {@link #reportUm reportUm}
     * 
     */
    public String setReportUm(String reportUm) {
        this.reportUm = reportUm;
        return this.reportUm;
    }

    /**
     * Getter for {@link #sellUm sellUm}
     * 
     */
    public String getSellUm() {
        return this.sellUm;
    }

    /**
     * Setter for {@link #sellUm sellUm}
     * 
     */
    public String setSellUm(String sellUm) {
        this.sellUm = sellUm;
        return this.sellUm;
    }

    /**
     * Getter for {@link #ticketType ticketType}
     * 
     */
    public Integer getTicketType() {
        return this.ticketType;
    }

    /**
     * Setter for {@link #ticketType ticketType}
     * 
     */
    public Integer setTicketType(Integer ticketType) {
        this.ticketType = ticketType;
        return this.ticketType;
    }

    /**
     * Getter for {@link #unitWeight unitWeight}
     * 
     */
    public BigDecimal getUnitWeight() {
        return this.unitWeight;
    }

    /**
     * Setter for {@link #unitWeight unitWeight}
     * 
     */
    public BigDecimal setUnitWeight(BigDecimal unitWeight) {
        if ((unitWeight!= null)&&(unitWeight.scale()!= 2)) {
            unitWeight = unitWeight.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.unitWeight = unitWeight;
        return this.unitWeight;
    }

    /**
     * Getter for {@link #shelfType shelfType}
     * 
     */
    public Integer getShelfType() {
        return this.shelfType;
    }

    /**
     * Setter for {@link #shelfType shelfType}
     * 
     */
    public Integer setShelfType(Integer shelfType) {
        this.shelfType = shelfType;
        return this.shelfType;
    }

    /**
     * Getter for {@link #linkSkuNo linkSkuNo}
     * 
     */
    public Integer getLinkSkuNo() {
        return this.linkSkuNo;
    }

    /**
     * Setter for {@link #linkSkuNo linkSkuNo}
     * 
     */
    public Integer setLinkSkuNo(Integer linkSkuNo) {
        this.linkSkuNo = linkSkuNo;
        return this.linkSkuNo;
    }

    /**
     * Getter for {@link #reorderFl reorderFl}
     * 
     */
    public String getReorderFl() {
        return this.reorderFl;
    }

    /**
     * Setter for {@link #reorderFl reorderFl}
     * 
     */
    public String setReorderFl(String reorderFl) {
        this.reorderFl = reorderFl;
        return this.reorderFl;
    }

    /**
     * Getter for {@link #specialItemFl specialItemFl}
     * 
     */
    public String getSpecialItemFl() {
        return this.specialItemFl;
    }

    /**
     * Setter for {@link #specialItemFl specialItemFl}
     * 
     */
    public String setSpecialItemFl(String specialItemFl) {
        this.specialItemFl = specialItemFl;
        return this.specialItemFl;
    }

    /**
     * Getter for {@link #weeksHistory weeksHistory}
     * 
     */
    public Integer getWeeksHistory() {
        return this.weeksHistory;
    }

    /**
     * Setter for {@link #weeksHistory weeksHistory}
     * 
     */
    public Integer setWeeksHistory(Integer weeksHistory) {
        this.weeksHistory = weeksHistory;
        return this.weeksHistory;
    }

    /**
     * Getter for {@link #periodsHistory periodsHistory}
     * 
     */
    public Integer getPeriodsHistory() {
        return this.periodsHistory;
    }

    /**
     * Setter for {@link #periodsHistory periodsHistory}
     * 
     */
    public Integer setPeriodsHistory(Integer periodsHistory) {
        this.periodsHistory = periodsHistory;
        return this.periodsHistory;
    }

    /**
     * Getter for {@link #storeCpnFl storeCpnFl}
     * 
     */
    public String getStoreCpnFl() {
        return this.storeCpnFl;
    }

    /**
     * Setter for {@link #storeCpnFl storeCpnFl}
     * 
     */
    public String setStoreCpnFl(String storeCpnFl) {
        this.storeCpnFl = storeCpnFl;
        return this.storeCpnFl;
    }

    /**
     * Getter for {@link #reportCd reportCd}
     * 
     */
    public Integer getReportCd() {
        return this.reportCd;
    }

    /**
     * Setter for {@link #reportCd reportCd}
     * 
     */
    public Integer setReportCd(Integer reportCd) {
        this.reportCd = reportCd;
        return this.reportCd;
    }

    /**
     * Getter for {@link #downloadFl downloadFl}
     * 
     */
    public String getDownloadFl() {
        return this.downloadFl;
    }

    /**
     * Setter for {@link #downloadFl downloadFl}
     * 
     */
    public String setDownloadFl(String downloadFl) {
        this.downloadFl = downloadFl;
        return this.downloadFl;
    }

    /**
     * Getter for {@link #discontinuedFl discontinuedFl}
     * 
     */
    public String getDiscontinuedFl() {
        return this.discontinuedFl;
    }

    /**
     * Setter for {@link #discontinuedFl discontinuedFl}
     * 
     */
    public String setDiscontinuedFl(String discontinuedFl) {
        this.discontinuedFl = discontinuedFl;
        return this.discontinuedFl;
    }

    /**
     * Getter for {@link #equivFactor equivFactor}
     * 
     */
    public BigDecimal getEquivFactor() {
        return this.equivFactor;
    }

    /**
     * Setter for {@link #equivFactor equivFactor}
     * 
     */
    public BigDecimal setEquivFactor(BigDecimal equivFactor) {
        if ((equivFactor!= null)&&(equivFactor.scale()!= 4)) {
            equivFactor = equivFactor.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.equivFactor = equivFactor;
        return this.equivFactor;
    }

    /**
     * Getter for {@link #newLabelDt newLabelDt}
     * 
     */
    public Date getNewLabelDt() {
        return this.newLabelDt;
    }

    /**
     * Setter for {@link #newLabelDt newLabelDt}
     * 
     */
    public Date setNewLabelDt(Date newLabelDt) {
        this.newLabelDt = newLabelDt;
        return this.newLabelDt;
    }

    /**
     * Getter for {@link #changeLabelDt changeLabelDt}
     * 
     */
    public Date getChangeLabelDt() {
        return this.changeLabelDt;
    }

    /**
     * Setter for {@link #changeLabelDt changeLabelDt}
     * 
     */
    public Date setChangeLabelDt(Date changeLabelDt) {
        this.changeLabelDt = changeLabelDt;
        return this.changeLabelDt;
    }

    /**
     * Getter for {@link #labelDesc1 labelDesc1}
     * 
     */
    public String getLabelDesc1() {
        return this.labelDesc1;
    }

    /**
     * Setter for {@link #labelDesc1 labelDesc1}
     * 
     */
    public String setLabelDesc1(String labelDesc1) {
        this.labelDesc1 = labelDesc1;
        return this.labelDesc1;
    }

    /**
     * Getter for {@link #labelDesc2 labelDesc2}
     * 
     */
    public String getLabelDesc2() {
        return this.labelDesc2;
    }

    /**
     * Setter for {@link #labelDesc2 labelDesc2}
     * 
     */
    public String setLabelDesc2(String labelDesc2) {
        this.labelDesc2 = labelDesc2;
        return this.labelDesc2;
    }

    /**
     * Getter for {@link #equivUm equivUm}
     * 
     */
    public String getEquivUm() {
        return this.equivUm;
    }

    /**
     * Setter for {@link #equivUm equivUm}
     * 
     */
    public String setEquivUm(String equivUm) {
        this.equivUm = equivUm;
        return this.equivUm;
    }

    /**
     * Getter for {@link #warehouseInnerPack warehouseInnerPack}
     * 
     */
    public BigDecimal getWarehouseInnerPack() {
        return this.warehouseInnerPack;
    }

    /**
     * Setter for {@link #warehouseInnerPack warehouseInnerPack}
     * 
     */
    public BigDecimal setWarehouseInnerPack(BigDecimal warehouseInnerPack) {
        if ((warehouseInnerPack!= null)&&(warehouseInnerPack.scale()!= 2)) {
            warehouseInnerPack = warehouseInnerPack.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.warehouseInnerPack = warehouseInnerPack;
        return this.warehouseInnerPack;
    }

    /**
     * Getter for {@link #obsoleteFl obsoleteFl}
     * 
     */
    public String getObsoleteFl() {
        return this.obsoleteFl;
    }

    /**
     * Setter for {@link #obsoleteFl obsoleteFl}
     * 
     */
    public String setObsoleteFl(String obsoleteFl) {
        this.obsoleteFl = obsoleteFl;
        return this.obsoleteFl;
    }

    /**
     * Getter for {@link #perCarQty perCarQty}
     * 
     */
    public Integer getPerCarQty() {
        return this.perCarQty;
    }

    /**
     * Setter for {@link #perCarQty perCarQty}
     * 
     */
    public Integer setPerCarQty(Integer perCarQty) {
        this.perCarQty = perCarQty;
        return this.perCarQty;
    }

    /**
     * Getter for {@link #priceListSeq priceListSeq}
     * 
     */
    public Integer getPriceListSeq() {
        return this.priceListSeq;
    }

    /**
     * Setter for {@link #priceListSeq priceListSeq}
     * 
     */
    public Integer setPriceListSeq(Integer priceListSeq) {
        this.priceListSeq = priceListSeq;
        return this.priceListSeq;
    }

    /**
     * Getter for {@link #supersededFl supersededFl}
     * 
     */
    public String getSupersededFl() {
        return this.supersededFl;
    }

    /**
     * Setter for {@link #supersededFl supersededFl}
     * 
     */
    public String setSupersededFl(String supersededFl) {
        this.supersededFl = supersededFl;
        return this.supersededFl;
    }

    /**
     * Getter for {@link #unitCube unitCube}
     * 
     */
    public BigDecimal getUnitCube() {
        return this.unitCube;
    }

    /**
     * Setter for {@link #unitCube unitCube}
     * 
     */
    public BigDecimal setUnitCube(BigDecimal unitCube) {
        if ((unitCube!= null)&&(unitCube.scale()!= 2)) {
            unitCube = unitCube.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.unitCube = unitCube;
        return this.unitCube;
    }

    /**
     * Getter for {@link #captureOrderNoFl captureOrderNoFl}
     * 
     */
    public String getCaptureOrderNoFl() {
        return this.captureOrderNoFl;
    }

    /**
     * Setter for {@link #captureOrderNoFl captureOrderNoFl}
     * 
     */
    public String setCaptureOrderNoFl(String captureOrderNoFl) {
        this.captureOrderNoFl = captureOrderNoFl;
        return this.captureOrderNoFl;
    }

    /**
     * Getter for {@link #captureSerialNoFl captureSerialNoFl}
     * 
     */
    public String getCaptureSerialNoFl() {
        return this.captureSerialNoFl;
    }

    /**
     * Setter for {@link #captureSerialNoFl captureSerialNoFl}
     * 
     */
    public String setCaptureSerialNoFl(String captureSerialNoFl) {
        this.captureSerialNoFl = captureSerialNoFl;
        return this.captureSerialNoFl;
    }

    /**
     * Getter for {@link #venitmSeqNo venitmSeqNo}
     * 
     */
    public Integer getVenitmSeqNo() {
        return this.venitmSeqNo;
    }

    /**
     * Setter for {@link #venitmSeqNo venitmSeqNo}
     * 
     */
    public Integer setVenitmSeqNo(Integer venitmSeqNo) {
        this.venitmSeqNo = venitmSeqNo;
        return this.venitmSeqNo;
    }

    /**
     * Getter for {@link #upcIdSeqNo upcIdSeqNo}
     * 
     */
    public Integer getUpcIdSeqNo() {
        return this.upcIdSeqNo;
    }

    /**
     * Setter for {@link #upcIdSeqNo upcIdSeqNo}
     * 
     */
    public Integer setUpcIdSeqNo(Integer upcIdSeqNo) {
        this.upcIdSeqNo = upcIdSeqNo;
        return this.upcIdSeqNo;
    }

    /**
     * Getter for {@link #employeeId employeeId}
     * 
     */
    public String getEmployeeId() {
        return this.employeeId;
    }

    /**
     * Setter for {@link #employeeId employeeId}
     * 
     */
    public String setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this.employeeId;
    }

    /**
     * Getter for {@link #supplementalUpcFl supplementalUpcFl}
     * 
     */
    public String getSupplementalUpcFl() {
        return this.supplementalUpcFl;
    }

    /**
     * Setter for {@link #supplementalUpcFl supplementalUpcFl}
     * 
     */
    public String setSupplementalUpcFl(String supplementalUpcFl) {
        this.supplementalUpcFl = supplementalUpcFl;
        return this.supplementalUpcFl;
    }

    /**
     * Getter for {@link #weighedItemFl weighedItemFl}
     * 
     */
    public String getWeighedItemFl() {
        return this.weighedItemFl;
    }

    /**
     * Setter for {@link #weighedItemFl weighedItemFl}
     * 
     */
    public String setWeighedItemFl(String weighedItemFl) {
        this.weighedItemFl = weighedItemFl;
        return this.weighedItemFl;
    }

    /**
     * Getter for {@link #suggestedSellCd suggestedSellCd}
     * 
     */
    public Integer getSuggestedSellCd() {
        return this.suggestedSellCd;
    }

    /**
     * Setter for {@link #suggestedSellCd suggestedSellCd}
     * 
     */
    public Integer setSuggestedSellCd(Integer suggestedSellCd) {
        this.suggestedSellCd = suggestedSellCd;
        return this.suggestedSellCd;
    }

    /**
     * Getter for {@link #validationSeqNo validationSeqNo}
     * 
     */
    public Integer getValidationSeqNo() {
        return this.validationSeqNo;
    }

    /**
     * Setter for {@link #validationSeqNo validationSeqNo}
     * 
     */
    public Integer setValidationSeqNo(Integer validationSeqNo) {
        this.validationSeqNo = validationSeqNo;
        return this.validationSeqNo;
    }

    /**
     * Getter for {@link #restrictSaleFl restrictSaleFl}
     * 
     */
    public String getRestrictSaleFl() {
        return this.restrictSaleFl;
    }

    /**
     * Setter for {@link #restrictSaleFl restrictSaleFl}
     * 
     */
    public String setRestrictSaleFl(String restrictSaleFl) {
        this.restrictSaleFl = restrictSaleFl;
        return this.restrictSaleFl;
    }

    /**
     * Getter for {@link #availSaleDt availSaleDt}
     * 
     */
    public Date getAvailSaleDt() {
        return this.availSaleDt;
    }

    /**
     * Setter for {@link #availSaleDt availSaleDt}
     * 
     */
    public Date setAvailSaleDt(Date availSaleDt) {
        this.availSaleDt = availSaleDt;
        return this.availSaleDt;
    }

    /**
     * Getter for {@link #invItemLockFl invItemLockFl}
     * 
     */
    public String getInvItemLockFl() {
        return this.invItemLockFl;
    }

    /**
     * Setter for {@link #invItemLockFl invItemLockFl}
     * 
     */
    public String setInvItemLockFl(String invItemLockFl) {
        this.invItemLockFl = invItemLockFl;
        return this.invItemLockFl;
    }

    /**
     * Getter for {@link #nonInventoryFl nonInventoryFl}
     * 
     */
    public String getNonInventoryFl() {
        return this.nonInventoryFl;
    }

    /**
     * Setter for {@link #nonInventoryFl nonInventoryFl}
     * 
     */
    public String setNonInventoryFl(String nonInventoryFl) {
        this.nonInventoryFl = nonInventoryFl;
        return this.nonInventoryFl;
    }

    /**
     * Getter for {@link #privateBrandFl privateBrandFl}
     * 
     */
    public String getPrivateBrandFl() {
        return this.privateBrandFl;
    }

    /**
     * Setter for {@link #privateBrandFl privateBrandFl}
     * 
     */
    public String setPrivateBrandFl(String privateBrandFl) {
        this.privateBrandFl = privateBrandFl;
        return this.privateBrandFl;
    }

    /**
     * Getter for {@link #lastItemChangeDt lastItemChangeDt}
     * 
     */
    public Date getLastItemChangeDt() {
        return this.lastItemChangeDt;
    }

    /**
     * Setter for {@link #lastItemChangeDt lastItemChangeDt}
     * 
     */
    public Date setLastItemChangeDt(Date lastItemChangeDt) {
        this.lastItemChangeDt = lastItemChangeDt;
        return this.lastItemChangeDt;
    }

    /**
     * Getter for {@link #lastItemChangeVendorId lastItemChangeVendorId}
     * 
     */
    public String getLastItemChangeVendorId() {
        return this.lastItemChangeVendorId;
    }

    /**
     * Setter for {@link #lastItemChangeVendorId lastItemChangeVendorId}
     * 
     */
    public String setLastItemChangeVendorId(String lastItemChangeVendorId) {
        this.lastItemChangeVendorId = lastItemChangeVendorId;
        return this.lastItemChangeVendorId;
    }

    /**
     * Getter for {@link #tenderProgramId tenderProgramId}
     * 
     */
    public Integer getTenderProgramId() {
        return this.tenderProgramId;
    }

    /**
     * Setter for {@link #tenderProgramId tenderProgramId}
     * 
     */
    public Integer setTenderProgramId(Integer tenderProgramId) {
        this.tenderProgramId = tenderProgramId;
        return this.tenderProgramId;
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
     * Getter for {@link #invType invType}
     * 
     */
    public String getInvType() {
        return this.invType;
    }

    /**
     * Setter for {@link #invType invType}
     * 
     */
    public String setInvType(String invType) {
        this.invType = invType;
        return this.invType;
    }

    /**
     * Getter for {@link #warrantyFl warrantyFl}
     * 
     */
    public String getWarrantyFl() {
        return this.warrantyFl;
    }

    /**
     * Setter for {@link #warrantyFl warrantyFl}
     * 
     */
    public String setWarrantyFl(String warrantyFl) {
        this.warrantyFl = warrantyFl;
        return this.warrantyFl;
    }

    /**
     * Getter for {@link #systemWarrantyFl systemWarrantyFl}
     * 
     */
    public String getSystemWarrantyFl() {
        return this.systemWarrantyFl;
    }

    /**
     * Setter for {@link #systemWarrantyFl systemWarrantyFl}
     * 
     */
    public String setSystemWarrantyFl(String systemWarrantyFl) {
        this.systemWarrantyFl = systemWarrantyFl;
        return this.systemWarrantyFl;
    }

    /**
     * Getter for {@link #subInvFl subInvFl}
     * 
     */
    public String getSubInvFl() {
        return this.subInvFl;
    }

    /**
     * Setter for {@link #subInvFl subInvFl}
     * 
     */
    public String setSubInvFl(String subInvFl) {
        this.subInvFl = subInvFl;
        return this.subInvFl;
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
     * Getter for {@link #replacementSkuNo replacementSkuNo}
     * 
     */
    public Integer getReplacementSkuNo() {
        return this.replacementSkuNo;
    }

    /**
     * Setter for {@link #replacementSkuNo replacementSkuNo}
     * 
     */
    public Integer setReplacementSkuNo(Integer replacementSkuNo) {
        this.replacementSkuNo = replacementSkuNo;
        return this.replacementSkuNo;
    }

    /**
     * Getter for {@link #hazUnNumberId hazUnNumberId}
     * 
     */
    public String getHazUnNumberId() {
        return this.hazUnNumberId;
    }

    /**
     * Setter for {@link #hazUnNumberId hazUnNumberId}
     * 
     */
    public String setHazUnNumberId(String hazUnNumberId) {
        this.hazUnNumberId = hazUnNumberId;
        return this.hazUnNumberId;
    }

    /**
     * Getter for {@link #hazClassId hazClassId}
     * 
     */
    public String getHazClassId() {
        return this.hazClassId;
    }

    /**
     * Setter for {@link #hazClassId hazClassId}
     * 
     */
    public String setHazClassId(String hazClassId) {
        this.hazClassId = hazClassId;
        return this.hazClassId;
    }

    /**
     * Getter for {@link #rollupBucketFl rollupBucketFl}
     * 
     */
    public String getRollupBucketFl() {
        return this.rollupBucketFl;
    }

    /**
     * Setter for {@link #rollupBucketFl rollupBucketFl}
     * 
     */
    public String setRollupBucketFl(String rollupBucketFl) {
        this.rollupBucketFl = rollupBucketFl;
        return this.rollupBucketFl;
    }

    /**
     * Getter for {@link #tareTableNo tareTableNo}
     * 
     */
    public String getTareTableNo() {
        return this.tareTableNo;
    }

    /**
     * Setter for {@link #tareTableNo tareTableNo}
     * 
     */
    public String setTareTableNo(String tareTableNo) {
        this.tareTableNo = tareTableNo;
        return this.tareTableNo;
    }

    /**
     * Getter for {@link #catchWeightFl catchWeightFl}
     * 
     */
    public String getCatchWeightFl() {
        return this.catchWeightFl;
    }

    /**
     * Setter for {@link #catchWeightFl catchWeightFl}
     * 
     */
    public String setCatchWeightFl(String catchWeightFl) {
        this.catchWeightFl = catchWeightFl;
        return this.catchWeightFl;
    }

    /**
     * Getter for {@link #productHier4 productHier4}
     * 
     */
    public String getProductHier4() {
        return this.productHier4;
    }

    /**
     * Setter for {@link #productHier4 productHier4}
     * 
     */
    public String setProductHier4(String productHier4) {
        this.productHier4 = productHier4;
        return this.productHier4;
    }

    /**
     * Getter for {@link #productHier5 productHier5}
     * 
     */
    public String getProductHier5() {
        return this.productHier5;
    }

    /**
     * Setter for {@link #productHier5 productHier5}
     * 
     */
    public String setProductHier5(String productHier5) {
        this.productHier5 = productHier5;
        return this.productHier5;
    }

    /**
     * Getter for {@link #productHier6 productHier6}
     * 
     */
    public String getProductHier6() {
        return this.productHier6;
    }

    /**
     * Setter for {@link #productHier6 productHier6}
     * 
     */
    public String setProductHier6(String productHier6) {
        this.productHier6 = productHier6;
        return this.productHier6;
    }

    /**
     * Getter for {@link #rrpInvType rrpInvType}
     * 
     */
    public String getRrpInvType() {
        return this.rrpInvType;
    }

    /**
     * Setter for {@link #rrpInvType rrpInvType}
     * 
     */
    public String setRrpInvType(String rrpInvType) {
        this.rrpInvType = rrpInvType;
        return this.rrpInvType;
    }

    /**
     * Getter for {@link #hierarchyId hierarchyId}
     * 
     */
    public Integer getHierarchyId() {
        return this.hierarchyId;
    }

    /**
     * Setter for {@link #hierarchyId hierarchyId}
     * 
     */
    public Integer setHierarchyId(Integer hierarchyId) {
        this.hierarchyId = hierarchyId;
        return this.hierarchyId;
    }

    /**
     * Getter for {@link #negativePriceFl negativePriceFl}
     * 
     */
    public String getNegativePriceFl() {
        return this.negativePriceFl;
    }

    /**
     * Setter for {@link #negativePriceFl negativePriceFl}
     * 
     */
    public String setNegativePriceFl(String negativePriceFl) {
        this.negativePriceFl = negativePriceFl;
        return this.negativePriceFl;
    }

    /**
     * Getter for {@link #packupActionId packupActionId}
     * 
     */
    public Integer getPackupActionId() {
        return this.packupActionId;
    }

    /**
     * Setter for {@link #packupActionId packupActionId}
     * 
     */
    public Integer setPackupActionId(Integer packupActionId) {
        this.packupActionId = packupActionId;
        return this.packupActionId;
    }

    /**
     * Getter for {@link #ownerId ownerId}
     * 
     */
    public Integer getOwnerId() {
        return this.ownerId;
    }

    /**
     * Setter for {@link #ownerId ownerId}
     * 
     */
    public Integer setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        return this.ownerId;
    }

    /**
     * Getter for {@link #flexibleItemFl flexibleItemFl}
     * 
     */
    public String getFlexibleItemFl() {
        return this.flexibleItemFl;
    }

    /**
     * Setter for {@link #flexibleItemFl flexibleItemFl}
     * 
     */
    public String setFlexibleItemFl(String flexibleItemFl) {
        this.flexibleItemFl = flexibleItemFl;
        return this.flexibleItemFl;
    }

    /**
     * Getter for {@link #supplyItemFl supplyItemFl}
     * 
     */
    public String getSupplyItemFl() {
        return this.supplyItemFl;
    }

    /**
     * Setter for {@link #supplyItemFl supplyItemFl}
     * 
     */
    public String setSupplyItemFl(String supplyItemFl) {
        this.supplyItemFl = supplyItemFl;
        return this.supplyItemFl;
    }

    /**
     * Getter for {@link #attribute1Id attribute1Id}
     * 
     */
    public Integer getAttribute1Id() {
        return this.attribute1Id;
    }

    /**
     * Setter for {@link #attribute1Id attribute1Id}
     * 
     */
    public Integer setAttribute1Id(Integer attribute1Id) {
        this.attribute1Id = attribute1Id;
        return this.attribute1Id;
    }

    /**
     * Getter for {@link #attribute2Id attribute2Id}
     * 
     */
    public Integer getAttribute2Id() {
        return this.attribute2Id;
    }

    /**
     * Setter for {@link #attribute2Id attribute2Id}
     * 
     */
    public Integer setAttribute2Id(Integer attribute2Id) {
        this.attribute2Id = attribute2Id;
        return this.attribute2Id;
    }

    /**
     * Getter for {@link #attribute3Id attribute3Id}
     * 
     */
    public Integer getAttribute3Id() {
        return this.attribute3Id;
    }

    /**
     * Setter for {@link #attribute3Id attribute3Id}
     * 
     */
    public Integer setAttribute3Id(Integer attribute3Id) {
        this.attribute3Id = attribute3Id;
        return this.attribute3Id;
    }

    /**
     * Getter for {@link #attribute1Value attribute1Value}
     * 
     */
    public String getAttribute1Value() {
        return this.attribute1Value;
    }

    /**
     * Setter for {@link #attribute1Value attribute1Value}
     * 
     */
    public String setAttribute1Value(String attribute1Value) {
        this.attribute1Value = attribute1Value;
        return this.attribute1Value;
    }

    /**
     * Getter for {@link #attribute2Value attribute2Value}
     * 
     */
    public String getAttribute2Value() {
        return this.attribute2Value;
    }

    /**
     * Setter for {@link #attribute2Value attribute2Value}
     * 
     */
    public String setAttribute2Value(String attribute2Value) {
        this.attribute2Value = attribute2Value;
        return this.attribute2Value;
    }

    /**
     * Getter for {@link #attribute3Value attribute3Value}
     * 
     */
    public String getAttribute3Value() {
        return this.attribute3Value;
    }

    /**
     * Setter for {@link #attribute3Value attribute3Value}
     * 
     */
    public String setAttribute3Value(String attribute3Value) {
        this.attribute3Value = attribute3Value;
        return this.attribute3Value;
    }

    /**
     * Getter for {@link #coreFl coreFl}
     * 
     */
    public String getCoreFl() {
        return this.coreFl;
    }

    /**
     * Setter for {@link #coreFl coreFl}
     * 
     */
    public String setCoreFl(String coreFl) {
        this.coreFl = coreFl;
        return this.coreFl;
    }

    /**
     * Getter for {@link #kitMemberFl kitMemberFl}
     * 
     */
    public String getKitMemberFl() {
        return this.kitMemberFl;
    }

    /**
     * Setter for {@link #kitMemberFl kitMemberFl}
     * 
     */
    public String setKitMemberFl(String kitMemberFl) {
        this.kitMemberFl = kitMemberFl;
        return this.kitMemberFl;
    }

    /**
     * Getter for {@link #primaryVendorItem primaryVendorItem}
     * 
     */
    public String getPrimaryVendorItem() {
        return this.primaryVendorItem;
    }

    /**
     * Setter for {@link #primaryVendorItem primaryVendorItem}
     * 
     */
    public String setPrimaryVendorItem(String primaryVendorItem) {
        this.primaryVendorItem = primaryVendorItem;
        return this.primaryVendorItem;
    }

    /**
     * Getter for {@link #autoGenSerialsFl autoGenSerialsFl}
     * 
     */
    public String getAutoGenSerialsFl() {
        return this.autoGenSerialsFl;
    }

    /**
     * Setter for {@link #autoGenSerialsFl autoGenSerialsFl}
     * 
     */
    public String setAutoGenSerialsFl(String autoGenSerialsFl) {
        this.autoGenSerialsFl = autoGenSerialsFl;
        return this.autoGenSerialsFl;
    }

    /**
     * Getter for {@link #regionalPriceFl regionalPriceFl}
     * 
     */
    public String getRegionalPriceFl() {
        return this.regionalPriceFl;
    }

    /**
     * Setter for {@link #regionalPriceFl regionalPriceFl}
     * 
     */
    public String setRegionalPriceFl(String regionalPriceFl) {
        this.regionalPriceFl = regionalPriceFl;
        return this.regionalPriceFl;
    }

    /**
     * Getter for {@link #regionalPromoFl regionalPromoFl}
     * 
     */
    public String getRegionalPromoFl() {
        return this.regionalPromoFl;
    }

    /**
     * Setter for {@link #regionalPromoFl regionalPromoFl}
     * 
     */
    public String setRegionalPromoFl(String regionalPromoFl) {
        this.regionalPromoFl = regionalPromoFl;
        return this.regionalPromoFl;
    }

    /**
     * Getter for {@link #regionalSupersedeFl regionalSupersedeFl}
     * 
     */
    public String getRegionalSupersedeFl() {
        return this.regionalSupersedeFl;
    }

    /**
     * Setter for {@link #regionalSupersedeFl regionalSupersedeFl}
     * 
     */
    public String setRegionalSupersedeFl(String regionalSupersedeFl) {
        this.regionalSupersedeFl = regionalSupersedeFl;
        return this.regionalSupersedeFl;
    }

    /**
     * Getter for {@link #webEnabledFl webEnabledFl}
     * 
     */
    public String getWebEnabledFl() {
        return this.webEnabledFl;
    }

    /**
     * Setter for {@link #webEnabledFl webEnabledFl}
     * 
     */
    public String setWebEnabledFl(String webEnabledFl) {
        this.webEnabledFl = webEnabledFl;
        return this.webEnabledFl;
    }

    /**
     * Getter for {@link #ecommNonInventoryFl ecommNonInventoryFl}
     * 
     */
    public String getEcommNonInventoryFl() {
        return this.ecommNonInventoryFl;
    }

    /**
     * Setter for {@link #ecommNonInventoryFl ecommNonInventoryFl}
     * 
     */
    public String setEcommNonInventoryFl(String ecommNonInventoryFl) {
        this.ecommNonInventoryFl = ecommNonInventoryFl;
        return this.ecommNonInventoryFl;
    }

    /**
     * @hide<P><I>Not for public use.</I></P>
     * 
     */
    private void _initDefaults() {
        if (this.otbFl == null) {
            this.otbFl = "N";
        }
        if (this.useSerialFl == null) {
            this.useSerialFl = "N";
        }
        if (this.reportFactor == null) {
            this.reportFactor = new BigDecimal("1.00");
        }
        if (this.lcuQty == null) {
            this.lcuQty = new BigDecimal("1.00");
        }
        if (this.ticketType == null) {
            this.ticketType = Integer.valueOf(0);
        }
        if (this.unitWeight == null) {
            this.unitWeight = new BigDecimal("0.00");
        }
        if (this.shelfType == null) {
            this.shelfType = Integer.valueOf(0);
        }
        if (this.reorderFl == null) {
            this.reorderFl = "Y";
        }
        if (this.specialItemFl == null) {
            this.specialItemFl = "N";
        }
        if (this.weeksHistory == null) {
            this.weeksHistory = Integer.valueOf(0);
        }
        if (this.periodsHistory == null) {
            this.periodsHistory = Integer.valueOf(0);
        }
        if (this.storeCpnFl == null) {
            this.storeCpnFl = "N";
        }
        if (this.downloadFl == null) {
            this.downloadFl = "Y";
        }
        if (this.discontinuedFl == null) {
            this.discontinuedFl = "N";
        }
        if (this.equivFactor == null) {
            this.equivFactor = new BigDecimal("1.0000");
        }
        if (this.equivUm == null) {
            this.equivUm = "each";
        }
        if (this.warehouseInnerPack == null) {
            this.warehouseInnerPack = new BigDecimal("1.00");
        }
        if (this.obsoleteFl == null) {
            this.obsoleteFl = "N";
        }
        if (this.perCarQty == null) {
            this.perCarQty = Integer.valueOf(1);
        }
        if (this.supersededFl == null) {
            this.supersededFl = "N";
        }
        if (this.unitCube == null) {
            this.unitCube = new BigDecimal("0.00");
        }
        if (this.captureOrderNoFl == null) {
            this.captureOrderNoFl = "N";
        }
        if (this.captureSerialNoFl == null) {
            this.captureSerialNoFl = "N";
        }
        if (this.venitmSeqNo == null) {
            this.venitmSeqNo = Integer.valueOf(0);
        }
        if (this.upcIdSeqNo == null) {
            this.upcIdSeqNo = Integer.valueOf(0);
        }
        if (this.supplementalUpcFl == null) {
            this.supplementalUpcFl = "N";
        }
        if (this.weighedItemFl == null) {
            this.weighedItemFl = "N";
        }
        if (this.restrictSaleFl == null) {
            this.restrictSaleFl = "N";
        }
        if (this.invItemLockFl == null) {
            this.invItemLockFl = "N";
        }
        if (this.nonInventoryFl == null) {
            this.nonInventoryFl = "N";
        }
        if (this.privateBrandFl == null) {
            this.privateBrandFl = "N";
        }
        if (this.subInvFl == null) {
            this.subInvFl = "N";
        }
        if (this.catchWeightFl == null) {
            this.catchWeightFl = "N";
        }
        if (this.negativePriceFl == null) {
            this.negativePriceFl = "N";
        }
        if (this.ownerId == null) {
            this.ownerId = Integer.valueOf(1);
        }
        if (this.supplyItemFl == null) {
            this.supplyItemFl = "N";
        }
        if (this.coreFl == null) {
            this.coreFl = "N";
        }
        if (this.kitMemberFl == null) {
            this.kitMemberFl = "N";
        }
        if (this.regionalPriceFl == null) {
            this.regionalPriceFl = "N";
        }
        if (this.regionalPromoFl == null) {
            this.regionalPromoFl = "N";
        }
        if (this.regionalSupersedeFl == null) {
            this.regionalSupersedeFl = "N";
        }
        if (this.webEnabledFl == null) {
            this.webEnabledFl = "N";
        }
        if (this.ecommNonInventoryFl == null) {
            this.ecommNonInventoryFl = "N";
        }
    }

    /**
     * @hide<P><I>Not for public use.</I></P><P>Initializes internal collections.</P>
     * 
     */
    private void _init() {
        this.invbysits = new HashSet<InvbysitFullEntity>();
        this.itemupcs = new HashSet<ItemupcEntity>();
    }

    /**
     * <P>Used to copy a InvtoryFullEntity including non-identifier fields only.</P>
     * 
     */
    public InvtoryFullEntity copyData() {
        final InvtoryFullEntity copy = new InvtoryFullEntity();
        	
        copy.mfgCd = this.mfgCd;
        copy.xrefNo = this.xrefNo;
        copy.description = this.description;
        copy.receiptDesc = this.receiptDesc;
        copy.lookupDesc = this.lookupDesc;
        copy.pkgDesc = this.pkgDesc;
        copy.deptNo = this.deptNo;
        copy.classNo = this.classNo;
        copy.lineNo = this.lineNo;
        copy.code1Id = this.code1Id;
        copy.code2Id = this.code2Id;
        copy.code3Id = this.code3Id;
        copy.createDt = this.createDt;
        copy.changeDt = this.changeDt;
        copy.otbFl = this.otbFl;
        copy.useSerialFl = this.useSerialFl;
        copy.glCatId = this.glCatId;
        copy.reportFactor = this.reportFactor;
        copy.lcuQty = this.lcuQty;
        copy.reportUm = this.reportUm;
        copy.sellUm = this.sellUm;
        copy.ticketType = this.ticketType;
        copy.unitWeight = this.unitWeight;
        copy.shelfType = this.shelfType;
        copy.linkSkuNo = this.linkSkuNo;
        copy.reorderFl = this.reorderFl;
        copy.specialItemFl = this.specialItemFl;
        copy.weeksHistory = this.weeksHistory;
        copy.periodsHistory = this.periodsHistory;
        copy.storeCpnFl = this.storeCpnFl;
        copy.reportCd = this.reportCd;
        copy.downloadFl = this.downloadFl;
        copy.discontinuedFl = this.discontinuedFl;
        copy.equivFactor = this.equivFactor;
        copy.newLabelDt = this.newLabelDt;
        copy.changeLabelDt = this.changeLabelDt;
        copy.labelDesc1 = this.labelDesc1;
        copy.labelDesc2 = this.labelDesc2;
        copy.equivUm = this.equivUm;
        copy.warehouseInnerPack = this.warehouseInnerPack;
        copy.obsoleteFl = this.obsoleteFl;
        copy.perCarQty = this.perCarQty;
        copy.priceListSeq = this.priceListSeq;
        copy.supersededFl = this.supersededFl;
        copy.unitCube = this.unitCube;
        copy.captureOrderNoFl = this.captureOrderNoFl;
        copy.captureSerialNoFl = this.captureSerialNoFl;
        copy.venitmSeqNo = this.venitmSeqNo;
        copy.upcIdSeqNo = this.upcIdSeqNo;
        copy.employeeId = this.employeeId;
        copy.supplementalUpcFl = this.supplementalUpcFl;
        copy.weighedItemFl = this.weighedItemFl;
        copy.suggestedSellCd = this.suggestedSellCd;
        copy.validationSeqNo = this.validationSeqNo;
        copy.restrictSaleFl = this.restrictSaleFl;
        copy.availSaleDt = this.availSaleDt;
        copy.invItemLockFl = this.invItemLockFl;
        copy.nonInventoryFl = this.nonInventoryFl;
        copy.privateBrandFl = this.privateBrandFl;
        copy.lastItemChangeDt = this.lastItemChangeDt;
        copy.lastItemChangeVendorId = this.lastItemChangeVendorId;
        copy.tenderProgramId = this.tenderProgramId;
        copy.externalRefId = this.externalRefId;
        copy.invType = this.invType;
        copy.warrantyFl = this.warrantyFl;
        copy.systemWarrantyFl = this.systemWarrantyFl;
        copy.subInvFl = this.subInvFl;
        copy.replicationNo = this.replicationNo;
        copy.operationType = this.operationType;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.replacementSkuNo = this.replacementSkuNo;
        copy.hazUnNumberId = this.hazUnNumberId;
        copy.hazClassId = this.hazClassId;
        copy.rollupBucketFl = this.rollupBucketFl;
        copy.tareTableNo = this.tareTableNo;
        copy.catchWeightFl = this.catchWeightFl;
        copy.productHier4 = this.productHier4;
        copy.productHier5 = this.productHier5;
        copy.productHier6 = this.productHier6;
        copy.rrpInvType = this.rrpInvType;
        copy.hierarchyId = this.hierarchyId;
        copy.negativePriceFl = this.negativePriceFl;
        copy.packupActionId = this.packupActionId;
        copy.ownerId = this.ownerId;
        copy.flexibleItemFl = this.flexibleItemFl;
        copy.supplyItemFl = this.supplyItemFl;
        copy.attribute1Id = this.attribute1Id;
        copy.attribute2Id = this.attribute2Id;
        copy.attribute3Id = this.attribute3Id;
        copy.attribute1Value = this.attribute1Value;
        copy.attribute2Value = this.attribute2Value;
        copy.attribute3Value = this.attribute3Value;
        copy.coreFl = this.coreFl;
        copy.kitMemberFl = this.kitMemberFl;
        copy.primaryVendorItem = this.primaryVendorItem;
        copy.autoGenSerialsFl = this.autoGenSerialsFl;
        copy.regionalPriceFl = this.regionalPriceFl;
        copy.regionalPromoFl = this.regionalPromoFl;
        copy.regionalSupersedeFl = this.regionalSupersedeFl;
        copy.webEnabledFl = this.webEnabledFl;
        copy.ecommNonInventoryFl = this.ecommNonInventoryFl;
        	
        for (InvbysitFullEntity tempEntity: this.getInvbysits()) {
            copy.addInvbysit(tempEntity.copyData());
        }
        for (ItemupcEntity tempEntity: this.getItemupcs()) {
            copy.addItemupc(tempEntity.copyData());
        }
        	
        return copy;
    }

    /**
     * <P>Used to copy a InvtoryFullEntity including its identifier.</P>
     * 
     */
    public InvtoryFullEntity copyDataAndId() {
        final InvtoryFullEntity copy = new InvtoryFullEntity();
        	
        copy.getIdentifier().setSkuNo(this.getIdentifier().getSkuNo());
        	
        copy.mfgCd = this.mfgCd;
        copy.xrefNo = this.xrefNo;
        copy.description = this.description;
        copy.receiptDesc = this.receiptDesc;
        copy.lookupDesc = this.lookupDesc;
        copy.pkgDesc = this.pkgDesc;
        copy.deptNo = this.deptNo;
        copy.classNo = this.classNo;
        copy.lineNo = this.lineNo;
        copy.code1Id = this.code1Id;
        copy.code2Id = this.code2Id;
        copy.code3Id = this.code3Id;
        copy.createDt = this.createDt;
        copy.changeDt = this.changeDt;
        copy.otbFl = this.otbFl;
        copy.useSerialFl = this.useSerialFl;
        copy.glCatId = this.glCatId;
        copy.reportFactor = this.reportFactor;
        copy.lcuQty = this.lcuQty;
        copy.reportUm = this.reportUm;
        copy.sellUm = this.sellUm;
        copy.ticketType = this.ticketType;
        copy.unitWeight = this.unitWeight;
        copy.shelfType = this.shelfType;
        copy.linkSkuNo = this.linkSkuNo;
        copy.reorderFl = this.reorderFl;
        copy.specialItemFl = this.specialItemFl;
        copy.weeksHistory = this.weeksHistory;
        copy.periodsHistory = this.periodsHistory;
        copy.storeCpnFl = this.storeCpnFl;
        copy.reportCd = this.reportCd;
        copy.downloadFl = this.downloadFl;
        copy.discontinuedFl = this.discontinuedFl;
        copy.equivFactor = this.equivFactor;
        copy.newLabelDt = this.newLabelDt;
        copy.changeLabelDt = this.changeLabelDt;
        copy.labelDesc1 = this.labelDesc1;
        copy.labelDesc2 = this.labelDesc2;
        copy.equivUm = this.equivUm;
        copy.warehouseInnerPack = this.warehouseInnerPack;
        copy.obsoleteFl = this.obsoleteFl;
        copy.perCarQty = this.perCarQty;
        copy.priceListSeq = this.priceListSeq;
        copy.supersededFl = this.supersededFl;
        copy.unitCube = this.unitCube;
        copy.captureOrderNoFl = this.captureOrderNoFl;
        copy.captureSerialNoFl = this.captureSerialNoFl;
        copy.venitmSeqNo = this.venitmSeqNo;
        copy.upcIdSeqNo = this.upcIdSeqNo;
        copy.employeeId = this.employeeId;
        copy.supplementalUpcFl = this.supplementalUpcFl;
        copy.weighedItemFl = this.weighedItemFl;
        copy.suggestedSellCd = this.suggestedSellCd;
        copy.validationSeqNo = this.validationSeqNo;
        copy.restrictSaleFl = this.restrictSaleFl;
        copy.availSaleDt = this.availSaleDt;
        copy.invItemLockFl = this.invItemLockFl;
        copy.nonInventoryFl = this.nonInventoryFl;
        copy.privateBrandFl = this.privateBrandFl;
        copy.lastItemChangeDt = this.lastItemChangeDt;
        copy.lastItemChangeVendorId = this.lastItemChangeVendorId;
        copy.tenderProgramId = this.tenderProgramId;
        copy.externalRefId = this.externalRefId;
        copy.invType = this.invType;
        copy.warrantyFl = this.warrantyFl;
        copy.systemWarrantyFl = this.systemWarrantyFl;
        copy.subInvFl = this.subInvFl;
        copy.replicationNo = this.replicationNo;
        copy.operationType = this.operationType;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.replacementSkuNo = this.replacementSkuNo;
        copy.hazUnNumberId = this.hazUnNumberId;
        copy.hazClassId = this.hazClassId;
        copy.rollupBucketFl = this.rollupBucketFl;
        copy.tareTableNo = this.tareTableNo;
        copy.catchWeightFl = this.catchWeightFl;
        copy.productHier4 = this.productHier4;
        copy.productHier5 = this.productHier5;
        copy.productHier6 = this.productHier6;
        copy.rrpInvType = this.rrpInvType;
        copy.hierarchyId = this.hierarchyId;
        copy.negativePriceFl = this.negativePriceFl;
        copy.packupActionId = this.packupActionId;
        copy.ownerId = this.ownerId;
        copy.flexibleItemFl = this.flexibleItemFl;
        copy.supplyItemFl = this.supplyItemFl;
        copy.attribute1Id = this.attribute1Id;
        copy.attribute2Id = this.attribute2Id;
        copy.attribute3Id = this.attribute3Id;
        copy.attribute1Value = this.attribute1Value;
        copy.attribute2Value = this.attribute2Value;
        copy.attribute3Value = this.attribute3Value;
        copy.coreFl = this.coreFl;
        copy.kitMemberFl = this.kitMemberFl;
        copy.primaryVendorItem = this.primaryVendorItem;
        copy.autoGenSerialsFl = this.autoGenSerialsFl;
        copy.regionalPriceFl = this.regionalPriceFl;
        copy.regionalPromoFl = this.regionalPromoFl;
        copy.regionalSupersedeFl = this.regionalSupersedeFl;
        copy.webEnabledFl = this.webEnabledFl;
        copy.ecommNonInventoryFl = this.ecommNonInventoryFl;
        	
        for (InvbysitFullEntity tempEntity: this.getInvbysits()) {
            copy.addInvbysit(tempEntity.copyDataAndId());
        }
        for (ItemupcEntity tempEntity: this.getItemupcs()) {
            copy.addItemupc(tempEntity.copyDataAndId());
        }
        	
        return copy;
    }

    /**
     * <P>Used to copy a InvtoryFullEntity including data and GENERATED identifiers only.</P>
     * 
     */
    public InvtoryFullEntity copyDataAndIdNonGenerated() {
        final InvtoryFullEntity copy = new InvtoryFullEntity();
        	
        copy.getIdentifier().setSkuNo(this.getIdentifier().getSkuNo());
        	
        copy.mfgCd = this.mfgCd;
        copy.xrefNo = this.xrefNo;
        copy.description = this.description;
        copy.receiptDesc = this.receiptDesc;
        copy.lookupDesc = this.lookupDesc;
        copy.pkgDesc = this.pkgDesc;
        copy.deptNo = this.deptNo;
        copy.classNo = this.classNo;
        copy.lineNo = this.lineNo;
        copy.code1Id = this.code1Id;
        copy.code2Id = this.code2Id;
        copy.code3Id = this.code3Id;
        copy.createDt = this.createDt;
        copy.changeDt = this.changeDt;
        copy.otbFl = this.otbFl;
        copy.useSerialFl = this.useSerialFl;
        copy.glCatId = this.glCatId;
        copy.reportFactor = this.reportFactor;
        copy.lcuQty = this.lcuQty;
        copy.reportUm = this.reportUm;
        copy.sellUm = this.sellUm;
        copy.ticketType = this.ticketType;
        copy.unitWeight = this.unitWeight;
        copy.shelfType = this.shelfType;
        copy.linkSkuNo = this.linkSkuNo;
        copy.reorderFl = this.reorderFl;
        copy.specialItemFl = this.specialItemFl;
        copy.weeksHistory = this.weeksHistory;
        copy.periodsHistory = this.periodsHistory;
        copy.storeCpnFl = this.storeCpnFl;
        copy.reportCd = this.reportCd;
        copy.downloadFl = this.downloadFl;
        copy.discontinuedFl = this.discontinuedFl;
        copy.equivFactor = this.equivFactor;
        copy.newLabelDt = this.newLabelDt;
        copy.changeLabelDt = this.changeLabelDt;
        copy.labelDesc1 = this.labelDesc1;
        copy.labelDesc2 = this.labelDesc2;
        copy.equivUm = this.equivUm;
        copy.warehouseInnerPack = this.warehouseInnerPack;
        copy.obsoleteFl = this.obsoleteFl;
        copy.perCarQty = this.perCarQty;
        copy.priceListSeq = this.priceListSeq;
        copy.supersededFl = this.supersededFl;
        copy.unitCube = this.unitCube;
        copy.captureOrderNoFl = this.captureOrderNoFl;
        copy.captureSerialNoFl = this.captureSerialNoFl;
        copy.venitmSeqNo = this.venitmSeqNo;
        copy.upcIdSeqNo = this.upcIdSeqNo;
        copy.employeeId = this.employeeId;
        copy.supplementalUpcFl = this.supplementalUpcFl;
        copy.weighedItemFl = this.weighedItemFl;
        copy.suggestedSellCd = this.suggestedSellCd;
        copy.validationSeqNo = this.validationSeqNo;
        copy.restrictSaleFl = this.restrictSaleFl;
        copy.availSaleDt = this.availSaleDt;
        copy.invItemLockFl = this.invItemLockFl;
        copy.nonInventoryFl = this.nonInventoryFl;
        copy.privateBrandFl = this.privateBrandFl;
        copy.lastItemChangeDt = this.lastItemChangeDt;
        copy.lastItemChangeVendorId = this.lastItemChangeVendorId;
        copy.tenderProgramId = this.tenderProgramId;
        copy.externalRefId = this.externalRefId;
        copy.invType = this.invType;
        copy.warrantyFl = this.warrantyFl;
        copy.systemWarrantyFl = this.systemWarrantyFl;
        copy.subInvFl = this.subInvFl;
        copy.replicationNo = this.replicationNo;
        copy.operationType = this.operationType;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.replacementSkuNo = this.replacementSkuNo;
        copy.hazUnNumberId = this.hazUnNumberId;
        copy.hazClassId = this.hazClassId;
        copy.rollupBucketFl = this.rollupBucketFl;
        copy.tareTableNo = this.tareTableNo;
        copy.catchWeightFl = this.catchWeightFl;
        copy.productHier4 = this.productHier4;
        copy.productHier5 = this.productHier5;
        copy.productHier6 = this.productHier6;
        copy.rrpInvType = this.rrpInvType;
        copy.hierarchyId = this.hierarchyId;
        copy.negativePriceFl = this.negativePriceFl;
        copy.packupActionId = this.packupActionId;
        copy.ownerId = this.ownerId;
        copy.flexibleItemFl = this.flexibleItemFl;
        copy.supplyItemFl = this.supplyItemFl;
        copy.attribute1Id = this.attribute1Id;
        copy.attribute2Id = this.attribute2Id;
        copy.attribute3Id = this.attribute3Id;
        copy.attribute1Value = this.attribute1Value;
        copy.attribute2Value = this.attribute2Value;
        copy.attribute3Value = this.attribute3Value;
        copy.coreFl = this.coreFl;
        copy.kitMemberFl = this.kitMemberFl;
        copy.primaryVendorItem = this.primaryVendorItem;
        copy.autoGenSerialsFl = this.autoGenSerialsFl;
        copy.regionalPriceFl = this.regionalPriceFl;
        copy.regionalPromoFl = this.regionalPromoFl;
        copy.regionalSupersedeFl = this.regionalSupersedeFl;
        copy.webEnabledFl = this.webEnabledFl;
        copy.ecommNonInventoryFl = this.ecommNonInventoryFl;
        	
        for (InvbysitFullEntity tempEntity: this.getInvbysits()) {
            copy.addInvbysit(tempEntity.copyDataAndIdNonGenerated());
        }
        for (ItemupcEntity tempEntity: this.getItemupcs()) {
            copy.addItemupc(tempEntity.copyDataAndIdNonGenerated());
        }
        	
        return copy;
    }

    /**
     * @hide<P><I>Not for public use.</I></P>This postLoad method is used to fix BigDecimal values being loaded from the JDBC driver.  Oracle has a known jdbc bug where it truncates the scale of BigDecimal.  This method restores the scale after load so that BigDecimals comparators will work again.
     * 
     */
    private void _setScales() {
        if (this.reportFactor!= null) {
            this.reportFactor = this.reportFactor.setScale(2);
        }
        if (this.lcuQty!= null) {
            this.lcuQty = this.lcuQty.setScale(2);
        }
        if (this.unitWeight!= null) {
            this.unitWeight = this.unitWeight.setScale(2);
        }
        if (this.equivFactor!= null) {
            this.equivFactor = this.equivFactor.setScale(4);
        }
        if (this.warehouseInnerPack!= null) {
            this.warehouseInnerPack = this.warehouseInnerPack.setScale(2);
        }
        if (this.unitCube!= null) {
            this.unitCube = this.unitCube.setScale(2);
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
        this.createDt = now;
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

    public static InvtoryFullDTO toDTO(InvtoryFullEntity entity) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return InvtoryFullEntity.toDTO(entity, map);
    }

    public static InvtoryFullDTO toDTO(InvtoryFullEntity entity, Map<Object, Object> map) {
        try {
            if (entity == null) {
                return null;
            }
            	
            if (map == null) {
                map = new HashMap<Object, Object>();
            } else {
                if (map.get(entity)!= null) {
                    return ((InvtoryFullDTO) map.get(entity));
                }
            }
            	
            entity.toDTO(map);
            	
            return ((InvtoryFullDTO) map.get(entity));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public static InvtoryFullEntity toEntity(InvtoryFullDTO dto) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return InvtoryFullEntity.toEntity(dto, map);
    }

    public static InvtoryFullEntity toEntity(InvtoryFullDTO dto, Map<Object, Object> map) {
        try {
            if (dto == null) {
                return null;
            }
            	
            if ((map!= null)&&(map.get(dto)!= null)) {
                return ((InvtoryFullEntity) map.get(dto));
            }
            	
            return ((InvtoryFullEntity) DtoToEntity.toEntity(InvtoryFullEntity.class, ((DataTransferObject) dto)));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public void assignAllChildren(InvtoryFullDTO dto, Map<Object, Object> map) {
        InvtoryFullEntity headerEntity = toSimpleEntity(dto, map);
        	
        for (InvbysitFullDTO childDTO: dto.getInvbysits()) {
            InvbysitFullEntity entity = ((InvbysitFullEntity) map.get(childDTO));
            if (entity!= null) {
                headerEntity.getInvbysits().add(entity);
                entity.setInvtory(headerEntity);
            } else {
                throw new RNetUnexpectedException("Copy misssed some child entry");
            }
        }
        	
        for (ItemupcDTO childDTO: dto.getItemupcs()) {
            ItemupcEntity entity = ((ItemupcEntity) map.get(childDTO));
            if (entity!= null) {
                headerEntity.getItemupcs().add(entity);
                entity.setInvtory(headerEntity);
            } else {
                throw new RNetUnexpectedException("Copy misssed some child entry");
            }
        }
        	
    }

    public void assignAllChildren(Map<Object, Object> map, Stack<Object> stack) {
        InvtoryFullDTO headerDto = toSimpleDto(map);
        	
        for (InvbysitFullEntity childEntity: this.invbysits) {
            InvbysitFullDTO dto = ((InvbysitFullDTO) map.get(childEntity));
            if (dto!= null) {
                headerDto.getInvbysits().add(dto);
                dto.setInvtory(headerDto);
            } else {
                if (stack.contains(childEntity)) {
                    dto = ((InvbysitFullEntity) stack.get(stack.indexOf(childEntity))).toDTO(map);
                    if (dto == null) {
                        throw new RNetUnexpectedException("Copy missed some child entry");
                    }
                    headerDto.getInvbysits().add(dto);
                    dto.setInvtory(headerDto);
                    map.put(childEntity, dto);
                } else {
                    throw new RNetUnexpectedException("Copy missed some child entry");
                }
            }
        }
        	
        for (ItemupcEntity childEntity: this.itemupcs) {
            ItemupcDTO dto = ((ItemupcDTO) map.get(childEntity));
            if (dto!= null) {
                headerDto.getItemupcs().add(dto);
                dto.setInvtory(headerDto);
            } else {
                if (stack.contains(childEntity)) {
                    dto = ((ItemupcEntity) stack.get(stack.indexOf(childEntity))).toDTO(map);
                    if (dto == null) {
                        throw new RNetUnexpectedException("Copy missed some child entry");
                    }
                    headerDto.getItemupcs().add(dto);
                    dto.setInvtory(headerDto);
                    map.put(childEntity, dto);
                } else {
                    throw new RNetUnexpectedException("Copy missed some child entry");
                }
            }
        }
        	
    }

    public void assignAllParents(Map<Object, Object> map) {
        toSimpleDto(map);
    }

    protected InvtoryFullDTO toSimpleDto(Map<Object, Object> map) {
        if (map.get(this)!= null) {
            return ((InvtoryFullDTO) map.get(this));
        }
        try {
            InvtoryFullDTO dto = new InvtoryFullDTO();
            dto.setIdentifier(new InvtoryDTOId(this.getSkuNo()));
            	
            dto.setMfgCd(this.getMfgCd());
            dto.setXrefNo(this.getXrefNo());
            dto.setDescription(this.getDescription());
            dto.setReceiptDesc(this.getReceiptDesc());
            dto.setLookupDesc(this.getLookupDesc());
            dto.setPkgDesc(this.getPkgDesc());
            dto.setDeptNo(this.getDeptNo());
            dto.setClassNo(this.getClassNo());
            dto.setLineNo(this.getLineNo());
            dto.setCode1Id(this.getCode1Id());
            dto.setCode2Id(this.getCode2Id());
            dto.setCode3Id(this.getCode3Id());
            dto.setCreateDt(this.getCreateDt());
            dto.setChangeDt(this.getChangeDt());
            dto.setOtbFl(this.getOtbFl());
            dto.setUseSerialFl(this.getUseSerialFl());
            dto.setGlCatId(this.getGlCatId());
            dto.setReportFactor(this.getReportFactor());
            dto.setLcuQty(this.getLcuQty());
            dto.setReportUm(this.getReportUm());
            dto.setSellUm(this.getSellUm());
            dto.setTicketType(this.getTicketType());
            dto.setUnitWeight(this.getUnitWeight());
            dto.setShelfType(this.getShelfType());
            dto.setLinkSkuNo(this.getLinkSkuNo());
            dto.setReorderFl(this.getReorderFl());
            dto.setSpecialItemFl(this.getSpecialItemFl());
            dto.setWeeksHistory(this.getWeeksHistory());
            dto.setPeriodsHistory(this.getPeriodsHistory());
            dto.setStoreCpnFl(this.getStoreCpnFl());
            dto.setReportCd(this.getReportCd());
            dto.setDownloadFl(this.getDownloadFl());
            dto.setDiscontinuedFl(this.getDiscontinuedFl());
            dto.setEquivFactor(this.getEquivFactor());
            dto.setNewLabelDt(this.getNewLabelDt());
            dto.setChangeLabelDt(this.getChangeLabelDt());
            dto.setLabelDesc1(this.getLabelDesc1());
            dto.setLabelDesc2(this.getLabelDesc2());
            dto.setEquivUm(this.getEquivUm());
            dto.setWarehouseInnerPack(this.getWarehouseInnerPack());
            dto.setObsoleteFl(this.getObsoleteFl());
            dto.setPerCarQty(this.getPerCarQty());
            dto.setPriceListSeq(this.getPriceListSeq());
            dto.setSupersededFl(this.getSupersededFl());
            dto.setUnitCube(this.getUnitCube());
            dto.setCaptureOrderNoFl(this.getCaptureOrderNoFl());
            dto.setCaptureSerialNoFl(this.getCaptureSerialNoFl());
            dto.setVenitmSeqNo(this.getVenitmSeqNo());
            dto.setUpcIdSeqNo(this.getUpcIdSeqNo());
            dto.setEmployeeId(this.getEmployeeId());
            dto.setSupplementalUpcFl(this.getSupplementalUpcFl());
            dto.setWeighedItemFl(this.getWeighedItemFl());
            dto.setSuggestedSellCd(this.getSuggestedSellCd());
            dto.setValidationSeqNo(this.getValidationSeqNo());
            dto.setRestrictSaleFl(this.getRestrictSaleFl());
            dto.setAvailSaleDt(this.getAvailSaleDt());
            dto.setInvItemLockFl(this.getInvItemLockFl());
            dto.setNonInventoryFl(this.getNonInventoryFl());
            dto.setPrivateBrandFl(this.getPrivateBrandFl());
            dto.setLastItemChangeDt(this.getLastItemChangeDt());
            dto.setLastItemChangeVendorId(this.getLastItemChangeVendorId());
            dto.setTenderProgramId(this.getTenderProgramId());
            dto.setExternalRefId(this.getExternalRefId());
            dto.setInvType(this.getInvType());
            dto.setWarrantyFl(this.getWarrantyFl());
            dto.setSystemWarrantyFl(this.getSystemWarrantyFl());
            dto.setSubInvFl(this.getSubInvFl());
            dto.setReplicationNo(this.getReplicationNo());
            dto.setOperationType(this.getOperationType());
            dto.setRegisterReplicationNo(this.getRegisterReplicationNo());
            dto.setDateCreated(this.getDateCreated());
            dto.setUserCreated(this.getUserCreated());
            dto.setDateModified(this.getDateModified());
            dto.setUserModified(this.getUserModified());
            dto.setReplacementSkuNo(this.getReplacementSkuNo());
            dto.setHazUnNumberId(this.getHazUnNumberId());
            dto.setHazClassId(this.getHazClassId());
            dto.setRollupBucketFl(this.getRollupBucketFl());
            dto.setTareTableNo(this.getTareTableNo());
            dto.setCatchWeightFl(this.getCatchWeightFl());
            dto.setProductHier4(this.getProductHier4());
            dto.setProductHier5(this.getProductHier5());
            dto.setProductHier6(this.getProductHier6());
            dto.setRrpInvType(this.getRrpInvType());
            dto.setHierarchyId(this.getHierarchyId());
            dto.setNegativePriceFl(this.getNegativePriceFl());
            dto.setPackupActionId(this.getPackupActionId());
            dto.setOwnerId(this.getOwnerId());
            dto.setFlexibleItemFl(this.getFlexibleItemFl());
            dto.setSupplyItemFl(this.getSupplyItemFl());
            dto.setAttribute1Id(this.getAttribute1Id());
            dto.setAttribute2Id(this.getAttribute2Id());
            dto.setAttribute3Id(this.getAttribute3Id());
            dto.setAttribute1Value(this.getAttribute1Value());
            dto.setAttribute2Value(this.getAttribute2Value());
            dto.setAttribute3Value(this.getAttribute3Value());
            dto.setCoreFl(this.getCoreFl());
            dto.setKitMemberFl(this.getKitMemberFl());
            dto.setPrimaryVendorItem(this.getPrimaryVendorItem());
            dto.setAutoGenSerialsFl(this.getAutoGenSerialsFl());
            dto.setRegionalPriceFl(this.getRegionalPriceFl());
            dto.setRegionalPromoFl(this.getRegionalPromoFl());
            dto.setRegionalSupersedeFl(this.getRegionalSupersedeFl());
            dto.setWebEnabledFl(this.getWebEnabledFl());
            dto.setEcommNonInventoryFl(this.getEcommNonInventoryFl());
            	
            map.put(this, dto);
            	
            return dto;
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public InvtoryFullEntity toSimpleEntity(DataTransferObject dto, Map<Object, Object> map) {
        if (map.get(dto)!= null) {
            return ((InvtoryFullEntity) map.get(dto));
        }
        try {
            InvtoryFullDTO oldDto = ((InvtoryFullDTO) dto);
            InvtoryFullEntity entity = new InvtoryFullEntity();
            entity.setIdentifier(new InvtoryEntityId(oldDto.getSkuNo()));
            	
            entity.setMfgCd(oldDto.getMfgCd());
            entity.setXrefNo(oldDto.getXrefNo());
            entity.setDescription(oldDto.getDescription());
            entity.setReceiptDesc(oldDto.getReceiptDesc());
            entity.setLookupDesc(oldDto.getLookupDesc());
            entity.setPkgDesc(oldDto.getPkgDesc());
            entity.setDeptNo(oldDto.getDeptNo());
            entity.setClassNo(oldDto.getClassNo());
            entity.setLineNo(oldDto.getLineNo());
            entity.setCode1Id(oldDto.getCode1Id());
            entity.setCode2Id(oldDto.getCode2Id());
            entity.setCode3Id(oldDto.getCode3Id());
            entity.setCreateDt(oldDto.getCreateDt());
            entity.setChangeDt(oldDto.getChangeDt());
            entity.setOtbFl(oldDto.getOtbFl());
            entity.setUseSerialFl(oldDto.getUseSerialFl());
            entity.setGlCatId(oldDto.getGlCatId());
            entity.setReportFactor(oldDto.getReportFactor());
            entity.setLcuQty(oldDto.getLcuQty());
            entity.setReportUm(oldDto.getReportUm());
            entity.setSellUm(oldDto.getSellUm());
            entity.setTicketType(oldDto.getTicketType());
            entity.setUnitWeight(oldDto.getUnitWeight());
            entity.setShelfType(oldDto.getShelfType());
            entity.setLinkSkuNo(oldDto.getLinkSkuNo());
            entity.setReorderFl(oldDto.getReorderFl());
            entity.setSpecialItemFl(oldDto.getSpecialItemFl());
            entity.setWeeksHistory(oldDto.getWeeksHistory());
            entity.setPeriodsHistory(oldDto.getPeriodsHistory());
            entity.setStoreCpnFl(oldDto.getStoreCpnFl());
            entity.setReportCd(oldDto.getReportCd());
            entity.setDownloadFl(oldDto.getDownloadFl());
            entity.setDiscontinuedFl(oldDto.getDiscontinuedFl());
            entity.setEquivFactor(oldDto.getEquivFactor());
            entity.setNewLabelDt(oldDto.getNewLabelDt());
            entity.setChangeLabelDt(oldDto.getChangeLabelDt());
            entity.setLabelDesc1(oldDto.getLabelDesc1());
            entity.setLabelDesc2(oldDto.getLabelDesc2());
            entity.setEquivUm(oldDto.getEquivUm());
            entity.setWarehouseInnerPack(oldDto.getWarehouseInnerPack());
            entity.setObsoleteFl(oldDto.getObsoleteFl());
            entity.setPerCarQty(oldDto.getPerCarQty());
            entity.setPriceListSeq(oldDto.getPriceListSeq());
            entity.setSupersededFl(oldDto.getSupersededFl());
            entity.setUnitCube(oldDto.getUnitCube());
            entity.setCaptureOrderNoFl(oldDto.getCaptureOrderNoFl());
            entity.setCaptureSerialNoFl(oldDto.getCaptureSerialNoFl());
            entity.setVenitmSeqNo(oldDto.getVenitmSeqNo());
            entity.setUpcIdSeqNo(oldDto.getUpcIdSeqNo());
            entity.setEmployeeId(oldDto.getEmployeeId());
            entity.setSupplementalUpcFl(oldDto.getSupplementalUpcFl());
            entity.setWeighedItemFl(oldDto.getWeighedItemFl());
            entity.setSuggestedSellCd(oldDto.getSuggestedSellCd());
            entity.setValidationSeqNo(oldDto.getValidationSeqNo());
            entity.setRestrictSaleFl(oldDto.getRestrictSaleFl());
            entity.setAvailSaleDt(oldDto.getAvailSaleDt());
            entity.setInvItemLockFl(oldDto.getInvItemLockFl());
            entity.setNonInventoryFl(oldDto.getNonInventoryFl());
            entity.setPrivateBrandFl(oldDto.getPrivateBrandFl());
            entity.setLastItemChangeDt(oldDto.getLastItemChangeDt());
            entity.setLastItemChangeVendorId(oldDto.getLastItemChangeVendorId());
            entity.setTenderProgramId(oldDto.getTenderProgramId());
            entity.setExternalRefId(oldDto.getExternalRefId());
            entity.setInvType(oldDto.getInvType());
            entity.setWarrantyFl(oldDto.getWarrantyFl());
            entity.setSystemWarrantyFl(oldDto.getSystemWarrantyFl());
            entity.setSubInvFl(oldDto.getSubInvFl());
            entity.setReplicationNo(oldDto.getReplicationNo());
            entity.setOperationType(oldDto.getOperationType());
            entity.setRegisterReplicationNo(oldDto.getRegisterReplicationNo());
            entity.setDateCreated(oldDto.getDateCreated());
            entity.setUserCreated(oldDto.getUserCreated());
            entity.setDateModified(oldDto.getDateModified());
            entity.setUserModified(oldDto.getUserModified());
            entity.setReplacementSkuNo(oldDto.getReplacementSkuNo());
            entity.setHazUnNumberId(oldDto.getHazUnNumberId());
            entity.setHazClassId(oldDto.getHazClassId());
            entity.setRollupBucketFl(oldDto.getRollupBucketFl());
            entity.setTareTableNo(oldDto.getTareTableNo());
            entity.setCatchWeightFl(oldDto.getCatchWeightFl());
            entity.setProductHier4(oldDto.getProductHier4());
            entity.setProductHier5(oldDto.getProductHier5());
            entity.setProductHier6(oldDto.getProductHier6());
            entity.setRrpInvType(oldDto.getRrpInvType());
            entity.setHierarchyId(oldDto.getHierarchyId());
            entity.setNegativePriceFl(oldDto.getNegativePriceFl());
            entity.setPackupActionId(oldDto.getPackupActionId());
            entity.setOwnerId(oldDto.getOwnerId());
            entity.setFlexibleItemFl(oldDto.getFlexibleItemFl());
            entity.setSupplyItemFl(oldDto.getSupplyItemFl());
            entity.setAttribute1Id(oldDto.getAttribute1Id());
            entity.setAttribute2Id(oldDto.getAttribute2Id());
            entity.setAttribute3Id(oldDto.getAttribute3Id());
            entity.setAttribute1Value(oldDto.getAttribute1Value());
            entity.setAttribute2Value(oldDto.getAttribute2Value());
            entity.setAttribute3Value(oldDto.getAttribute3Value());
            entity.setCoreFl(oldDto.getCoreFl());
            entity.setKitMemberFl(oldDto.getKitMemberFl());
            entity.setPrimaryVendorItem(oldDto.getPrimaryVendorItem());
            entity.setAutoGenSerialsFl(oldDto.getAutoGenSerialsFl());
            entity.setRegionalPriceFl(oldDto.getRegionalPriceFl());
            entity.setRegionalPromoFl(oldDto.getRegionalPromoFl());
            entity.setRegionalSupersedeFl(oldDto.getRegionalSupersedeFl());
            entity.setWebEnabledFl(oldDto.getWebEnabledFl());
            entity.setEcommNonInventoryFl(oldDto.getEcommNonInventoryFl());
            	
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
        List<Set<? extends RNetAbstractEntity>> list = new ArrayList<Set<? extends RNetAbstractEntity>>();
        	
        if ((this.invbysits!= null)&&(!this.invbysits.isEmpty())) {
            list.add(this.invbysits);
        }
        if ((this.itemupcs!= null)&&(!this.itemupcs.isEmpty())) {
            list.add(this.itemupcs);
        }
        	
        return list;
    }

    public static List<InvtoryFullDTO> toDTOList(Collection<InvtoryFullEntity> entities) {
        if (entities == null) {
            return null;
        }
        	
        List<InvtoryFullDTO> dtos = new ArrayList<InvtoryFullDTO>();
        for (InvtoryFullEntity entity: entities) {
            dtos.add(toDTO(entity));
        }
        	
        return dtos;
    }

    @Deprecated
    public static List<InvtoryFullDTO> toDTO(Collection<InvtoryFullEntity> entities) {
        return toDTOList(entities);
    }

    public static InvtoryDTOId toDTOId(InvtoryEntityId id) {
        if (id == null) {
            return null;
        }
        return new InvtoryDTOId(id.getSkuNo());
    }

    @Deprecated
    public static InvtoryDTOId toDTO(InvtoryEntityId id) {
        return toDTOId(id);
    }

    public static List<InvtoryDTOId> toDTOIdList(Collection<InvtoryEntityId> entityIds) {
        if (entityIds == null) {
            return null;
        }
        	
        List<InvtoryDTOId> dtoIds = new ArrayList<InvtoryDTOId>();
        for (InvtoryEntityId entity: entityIds) {
            dtoIds.add(toDTOId(entity));
        }
        	
        return dtoIds;
    }

    public static Set<InvtoryFullEntity> toEntitySet(Collection<InvtoryFullDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        Set<InvtoryFullEntity> entities = new HashSet<InvtoryFullEntity>();
        for (InvtoryFullDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static Set<InvtoryFullEntity> toEntity(Collection<InvtoryFullDTO> dtos) {
        return toEntitySet(dtos);
    }

    public static SortedSet<InvtoryFullEntity> toEntitySortedSet(Collection<InvtoryFullDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        SortedSet<InvtoryFullEntity> entities = new TreeSet<InvtoryFullEntity>();
        for (InvtoryFullDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static SortedSet<InvtoryFullEntity> toEntitySorted(Collection<InvtoryFullDTO> dtos) {
        return toEntitySortedSet(dtos);
    }

    public static List<InvtoryFullEntity> toEntityList(Collection<InvtoryFullDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        List<InvtoryFullEntity> entities = new ArrayList<InvtoryFullEntity>();
        for (InvtoryFullDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    public static InvtoryEntityId toEntityId(InvtoryDTOId dto) {
        if (dto == null) {
            return null;
        }
        return new InvtoryEntityId(dto.getSkuNo());
    }

    @Deprecated
    public static InvtoryEntityId toEntity(InvtoryDTOId id) {
        return toEntityId(id);
    }

    public static List<InvtoryEntityId> toEntityIdList(Collection<InvtoryDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        List<InvtoryEntityId> entityIds = new ArrayList<InvtoryEntityId>();
        for (InvtoryDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static Set<InvtoryEntityId> toEntityIdSet(Collection<InvtoryDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        Set<InvtoryEntityId> entityIds = new HashSet<InvtoryEntityId>();
        for (InvtoryDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static SortedSet<InvtoryEntityId> toEntityIdSortedSet(Collection<InvtoryDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        SortedSet<InvtoryEntityId> entityIds = new TreeSet<InvtoryEntityId>();
        for (InvtoryDTOId dtoId: dtoIds) {
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
        final InvtoryFullEntity other = ((InvtoryFullEntity) obj);
        // Identifier level comparison
        if (this.identifier.getSkuNo() == null) {
            return false;
        } else {
            if (!this.identifier.getSkuNo().equals(other.identifier.getSkuNo())) {
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
        final InvtoryFullEntity other = ((InvtoryFullEntity) obj);
        // Data level comparison
        if (mfgCd == null) {
            if (other.mfgCd!= null) {
                return false;
            }
        } else {
            if (!mfgCd.equals(other.mfgCd)) {
                return false;
            }
        }
        if (xrefNo == null) {
            if (other.xrefNo!= null) {
                return false;
            }
        } else {
            if (!xrefNo.equals(other.xrefNo)) {
                return false;
            }
        }
        if (description == null) {
            if (other.description!= null) {
                return false;
            }
        } else {
            if (!description.equals(other.description)) {
                return false;
            }
        }
        if (receiptDesc == null) {
            if (other.receiptDesc!= null) {
                return false;
            }
        } else {
            if (!receiptDesc.equals(other.receiptDesc)) {
                return false;
            }
        }
        if (lookupDesc == null) {
            if (other.lookupDesc!= null) {
                return false;
            }
        } else {
            if (!lookupDesc.equals(other.lookupDesc)) {
                return false;
            }
        }
        if (pkgDesc == null) {
            if (other.pkgDesc!= null) {
                return false;
            }
        } else {
            if (!pkgDesc.equals(other.pkgDesc)) {
                return false;
            }
        }
        if (deptNo == null) {
            if (other.deptNo!= null) {
                return false;
            }
        } else {
            if (!deptNo.equals(other.deptNo)) {
                return false;
            }
        }
        if (classNo == null) {
            if (other.classNo!= null) {
                return false;
            }
        } else {
            if (!classNo.equals(other.classNo)) {
                return false;
            }
        }
        if (lineNo == null) {
            if (other.lineNo!= null) {
                return false;
            }
        } else {
            if (!lineNo.equals(other.lineNo)) {
                return false;
            }
        }
        if (code1Id == null) {
            if (other.code1Id!= null) {
                return false;
            }
        } else {
            if (!code1Id.equals(other.code1Id)) {
                return false;
            }
        }
        if (code2Id == null) {
            if (other.code2Id!= null) {
                return false;
            }
        } else {
            if (!code2Id.equals(other.code2Id)) {
                return false;
            }
        }
        if (code3Id == null) {
            if (other.code3Id!= null) {
                return false;
            }
        } else {
            if (!code3Id.equals(other.code3Id)) {
                return false;
            }
        }
        if (changeDt == null) {
            if (other.changeDt!= null) {
                return false;
            }
        } else {
            if (!changeDt.equals(other.changeDt)) {
                return false;
            }
        }
        if (otbFl == null) {
            if (other.otbFl!= null) {
                return false;
            }
        } else {
            if (!otbFl.equals(other.otbFl)) {
                return false;
            }
        }
        if (useSerialFl == null) {
            if (other.useSerialFl!= null) {
                return false;
            }
        } else {
            if (!useSerialFl.equals(other.useSerialFl)) {
                return false;
            }
        }
        if (glCatId == null) {
            if (other.glCatId!= null) {
                return false;
            }
        } else {
            if (!glCatId.equals(other.glCatId)) {
                return false;
            }
        }
        if (reportFactor == null) {
            if (other.reportFactor!= null) {
                return false;
            }
        } else {
            if (!reportFactor.equals(other.reportFactor)) {
                return false;
            }
        }
        if (lcuQty == null) {
            if (other.lcuQty!= null) {
                return false;
            }
        } else {
            if (!lcuQty.equals(other.lcuQty)) {
                return false;
            }
        }
        if (reportUm == null) {
            if (other.reportUm!= null) {
                return false;
            }
        } else {
            if (!reportUm.equals(other.reportUm)) {
                return false;
            }
        }
        if (sellUm == null) {
            if (other.sellUm!= null) {
                return false;
            }
        } else {
            if (!sellUm.equals(other.sellUm)) {
                return false;
            }
        }
        if (ticketType == null) {
            if (other.ticketType!= null) {
                return false;
            }
        } else {
            if (!ticketType.equals(other.ticketType)) {
                return false;
            }
        }
        if (unitWeight == null) {
            if (other.unitWeight!= null) {
                return false;
            }
        } else {
            if (!unitWeight.equals(other.unitWeight)) {
                return false;
            }
        }
        if (shelfType == null) {
            if (other.shelfType!= null) {
                return false;
            }
        } else {
            if (!shelfType.equals(other.shelfType)) {
                return false;
            }
        }
        if (linkSkuNo == null) {
            if (other.linkSkuNo!= null) {
                return false;
            }
        } else {
            if (!linkSkuNo.equals(other.linkSkuNo)) {
                return false;
            }
        }
        if (reorderFl == null) {
            if (other.reorderFl!= null) {
                return false;
            }
        } else {
            if (!reorderFl.equals(other.reorderFl)) {
                return false;
            }
        }
        if (specialItemFl == null) {
            if (other.specialItemFl!= null) {
                return false;
            }
        } else {
            if (!specialItemFl.equals(other.specialItemFl)) {
                return false;
            }
        }
        if (weeksHistory == null) {
            if (other.weeksHistory!= null) {
                return false;
            }
        } else {
            if (!weeksHistory.equals(other.weeksHistory)) {
                return false;
            }
        }
        if (periodsHistory == null) {
            if (other.periodsHistory!= null) {
                return false;
            }
        } else {
            if (!periodsHistory.equals(other.periodsHistory)) {
                return false;
            }
        }
        if (storeCpnFl == null) {
            if (other.storeCpnFl!= null) {
                return false;
            }
        } else {
            if (!storeCpnFl.equals(other.storeCpnFl)) {
                return false;
            }
        }
        if (reportCd == null) {
            if (other.reportCd!= null) {
                return false;
            }
        } else {
            if (!reportCd.equals(other.reportCd)) {
                return false;
            }
        }
        if (downloadFl == null) {
            if (other.downloadFl!= null) {
                return false;
            }
        } else {
            if (!downloadFl.equals(other.downloadFl)) {
                return false;
            }
        }
        if (discontinuedFl == null) {
            if (other.discontinuedFl!= null) {
                return false;
            }
        } else {
            if (!discontinuedFl.equals(other.discontinuedFl)) {
                return false;
            }
        }
        if (equivFactor == null) {
            if (other.equivFactor!= null) {
                return false;
            }
        } else {
            if (!equivFactor.equals(other.equivFactor)) {
                return false;
            }
        }
        if (newLabelDt == null) {
            if (other.newLabelDt!= null) {
                return false;
            }
        } else {
            if (!newLabelDt.equals(other.newLabelDt)) {
                return false;
            }
        }
        if (changeLabelDt == null) {
            if (other.changeLabelDt!= null) {
                return false;
            }
        } else {
            if (!changeLabelDt.equals(other.changeLabelDt)) {
                return false;
            }
        }
        if (labelDesc1 == null) {
            if (other.labelDesc1 != null) {
                return false;
            }
        } else {
            if (!labelDesc1 .equals(other.labelDesc1)) {
                return false;
            }
        }
        if (labelDesc2 == null) {
            if (other.labelDesc2 != null) {
                return false;
            }
        } else {
            if (!labelDesc2 .equals(other.labelDesc2)) {
                return false;
            }
        }
        if (equivUm == null) {
            if (other.equivUm!= null) {
                return false;
            }
        } else {
            if (!equivUm.equals(other.equivUm)) {
                return false;
            }
        }
        if (warehouseInnerPack == null) {
            if (other.warehouseInnerPack!= null) {
                return false;
            }
        } else {
            if (!warehouseInnerPack.equals(other.warehouseInnerPack)) {
                return false;
            }
        }
        if (obsoleteFl == null) {
            if (other.obsoleteFl!= null) {
                return false;
            }
        } else {
            if (!obsoleteFl.equals(other.obsoleteFl)) {
                return false;
            }
        }
        if (perCarQty == null) {
            if (other.perCarQty!= null) {
                return false;
            }
        } else {
            if (!perCarQty.equals(other.perCarQty)) {
                return false;
            }
        }
        if (priceListSeq == null) {
            if (other.priceListSeq!= null) {
                return false;
            }
        } else {
            if (!priceListSeq.equals(other.priceListSeq)) {
                return false;
            }
        }
        if (supersededFl == null) {
            if (other.supersededFl!= null) {
                return false;
            }
        } else {
            if (!supersededFl.equals(other.supersededFl)) {
                return false;
            }
        }
        if (unitCube == null) {
            if (other.unitCube!= null) {
                return false;
            }
        } else {
            if (!unitCube.equals(other.unitCube)) {
                return false;
            }
        }
        if (captureOrderNoFl == null) {
            if (other.captureOrderNoFl!= null) {
                return false;
            }
        } else {
            if (!captureOrderNoFl.equals(other.captureOrderNoFl)) {
                return false;
            }
        }
        if (captureSerialNoFl == null) {
            if (other.captureSerialNoFl!= null) {
                return false;
            }
        } else {
            if (!captureSerialNoFl.equals(other.captureSerialNoFl)) {
                return false;
            }
        }
        if (venitmSeqNo == null) {
            if (other.venitmSeqNo!= null) {
                return false;
            }
        } else {
            if (!venitmSeqNo.equals(other.venitmSeqNo)) {
                return false;
            }
        }
        if (upcIdSeqNo == null) {
            if (other.upcIdSeqNo!= null) {
                return false;
            }
        } else {
            if (!upcIdSeqNo.equals(other.upcIdSeqNo)) {
                return false;
            }
        }
        if (employeeId == null) {
            if (other.employeeId!= null) {
                return false;
            }
        } else {
            if (!employeeId.equals(other.employeeId)) {
                return false;
            }
        }
        if (supplementalUpcFl == null) {
            if (other.supplementalUpcFl!= null) {
                return false;
            }
        } else {
            if (!supplementalUpcFl.equals(other.supplementalUpcFl)) {
                return false;
            }
        }
        if (weighedItemFl == null) {
            if (other.weighedItemFl!= null) {
                return false;
            }
        } else {
            if (!weighedItemFl.equals(other.weighedItemFl)) {
                return false;
            }
        }
        if (suggestedSellCd == null) {
            if (other.suggestedSellCd!= null) {
                return false;
            }
        } else {
            if (!suggestedSellCd.equals(other.suggestedSellCd)) {
                return false;
            }
        }
        if (validationSeqNo == null) {
            if (other.validationSeqNo!= null) {
                return false;
            }
        } else {
            if (!validationSeqNo.equals(other.validationSeqNo)) {
                return false;
            }
        }
        if (restrictSaleFl == null) {
            if (other.restrictSaleFl!= null) {
                return false;
            }
        } else {
            if (!restrictSaleFl.equals(other.restrictSaleFl)) {
                return false;
            }
        }
        if (availSaleDt == null) {
            if (other.availSaleDt!= null) {
                return false;
            }
        } else {
            if (!availSaleDt.equals(other.availSaleDt)) {
                return false;
            }
        }
        if (invItemLockFl == null) {
            if (other.invItemLockFl!= null) {
                return false;
            }
        } else {
            if (!invItemLockFl.equals(other.invItemLockFl)) {
                return false;
            }
        }
        if (nonInventoryFl == null) {
            if (other.nonInventoryFl!= null) {
                return false;
            }
        } else {
            if (!nonInventoryFl.equals(other.nonInventoryFl)) {
                return false;
            }
        }
        if (privateBrandFl == null) {
            if (other.privateBrandFl!= null) {
                return false;
            }
        } else {
            if (!privateBrandFl.equals(other.privateBrandFl)) {
                return false;
            }
        }
        if (lastItemChangeDt == null) {
            if (other.lastItemChangeDt!= null) {
                return false;
            }
        } else {
            if (!lastItemChangeDt.equals(other.lastItemChangeDt)) {
                return false;
            }
        }
        if (lastItemChangeVendorId == null) {
            if (other.lastItemChangeVendorId!= null) {
                return false;
            }
        } else {
            if (!lastItemChangeVendorId.equals(other.lastItemChangeVendorId)) {
                return false;
            }
        }
        if (tenderProgramId == null) {
            if (other.tenderProgramId!= null) {
                return false;
            }
        } else {
            if (!tenderProgramId.equals(other.tenderProgramId)) {
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
        if (invType == null) {
            if (other.invType!= null) {
                return false;
            }
        } else {
            if (!invType.equals(other.invType)) {
                return false;
            }
        }
        if (warrantyFl == null) {
            if (other.warrantyFl!= null) {
                return false;
            }
        } else {
            if (!warrantyFl.equals(other.warrantyFl)) {
                return false;
            }
        }
        if (systemWarrantyFl == null) {
            if (other.systemWarrantyFl!= null) {
                return false;
            }
        } else {
            if (!systemWarrantyFl.equals(other.systemWarrantyFl)) {
                return false;
            }
        }
        if (subInvFl == null) {
            if (other.subInvFl!= null) {
                return false;
            }
        } else {
            if (!subInvFl.equals(other.subInvFl)) {
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
        if (operationType == null) {
            if (other.operationType!= null) {
                return false;
            }
        } else {
            if (!operationType.equals(other.operationType)) {
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
        if (replacementSkuNo == null) {
            if (other.replacementSkuNo!= null) {
                return false;
            }
        } else {
            if (!replacementSkuNo.equals(other.replacementSkuNo)) {
                return false;
            }
        }
        if (hazUnNumberId == null) {
            if (other.hazUnNumberId!= null) {
                return false;
            }
        } else {
            if (!hazUnNumberId.equals(other.hazUnNumberId)) {
                return false;
            }
        }
        if (hazClassId == null) {
            if (other.hazClassId!= null) {
                return false;
            }
        } else {
            if (!hazClassId.equals(other.hazClassId)) {
                return false;
            }
        }
        if (rollupBucketFl == null) {
            if (other.rollupBucketFl!= null) {
                return false;
            }
        } else {
            if (!rollupBucketFl.equals(other.rollupBucketFl)) {
                return false;
            }
        }
        if (tareTableNo == null) {
            if (other.tareTableNo!= null) {
                return false;
            }
        } else {
            if (!tareTableNo.equals(other.tareTableNo)) {
                return false;
            }
        }
        if (catchWeightFl == null) {
            if (other.catchWeightFl!= null) {
                return false;
            }
        } else {
            if (!catchWeightFl.equals(other.catchWeightFl)) {
                return false;
            }
        }
        if (productHier4 == null) {
            if (other.productHier4 != null) {
                return false;
            }
        } else {
            if (!productHier4 .equals(other.productHier4)) {
                return false;
            }
        }
        if (productHier5 == null) {
            if (other.productHier5 != null) {
                return false;
            }
        } else {
            if (!productHier5 .equals(other.productHier5)) {
                return false;
            }
        }
        if (productHier6 == null) {
            if (other.productHier6 != null) {
                return false;
            }
        } else {
            if (!productHier6 .equals(other.productHier6)) {
                return false;
            }
        }
        if (rrpInvType == null) {
            if (other.rrpInvType!= null) {
                return false;
            }
        } else {
            if (!rrpInvType.equals(other.rrpInvType)) {
                return false;
            }
        }
        if (hierarchyId == null) {
            if (other.hierarchyId!= null) {
                return false;
            }
        } else {
            if (!hierarchyId.equals(other.hierarchyId)) {
                return false;
            }
        }
        if (negativePriceFl == null) {
            if (other.negativePriceFl!= null) {
                return false;
            }
        } else {
            if (!negativePriceFl.equals(other.negativePriceFl)) {
                return false;
            }
        }
        if (packupActionId == null) {
            if (other.packupActionId!= null) {
                return false;
            }
        } else {
            if (!packupActionId.equals(other.packupActionId)) {
                return false;
            }
        }
        if (ownerId == null) {
            if (other.ownerId!= null) {
                return false;
            }
        } else {
            if (!ownerId.equals(other.ownerId)) {
                return false;
            }
        }
        if (flexibleItemFl == null) {
            if (other.flexibleItemFl!= null) {
                return false;
            }
        } else {
            if (!flexibleItemFl.equals(other.flexibleItemFl)) {
                return false;
            }
        }
        if (supplyItemFl == null) {
            if (other.supplyItemFl!= null) {
                return false;
            }
        } else {
            if (!supplyItemFl.equals(other.supplyItemFl)) {
                return false;
            }
        }
        if (attribute1Id == null) {
            if (other.attribute1Id!= null) {
                return false;
            }
        } else {
            if (!attribute1Id.equals(other.attribute1Id)) {
                return false;
            }
        }
        if (attribute2Id == null) {
            if (other.attribute2Id!= null) {
                return false;
            }
        } else {
            if (!attribute2Id.equals(other.attribute2Id)) {
                return false;
            }
        }
        if (attribute3Id == null) {
            if (other.attribute3Id!= null) {
                return false;
            }
        } else {
            if (!attribute3Id.equals(other.attribute3Id)) {
                return false;
            }
        }
        if (attribute1Value == null) {
            if (other.attribute1Value!= null) {
                return false;
            }
        } else {
            if (!attribute1Value.equals(other.attribute1Value)) {
                return false;
            }
        }
        if (attribute2Value == null) {
            if (other.attribute2Value!= null) {
                return false;
            }
        } else {
            if (!attribute2Value.equals(other.attribute2Value)) {
                return false;
            }
        }
        if (attribute3Value == null) {
            if (other.attribute3Value!= null) {
                return false;
            }
        } else {
            if (!attribute3Value.equals(other.attribute3Value)) {
                return false;
            }
        }
        if (coreFl == null) {
            if (other.coreFl!= null) {
                return false;
            }
        } else {
            if (!coreFl.equals(other.coreFl)) {
                return false;
            }
        }
        if (kitMemberFl == null) {
            if (other.kitMemberFl!= null) {
                return false;
            }
        } else {
            if (!kitMemberFl.equals(other.kitMemberFl)) {
                return false;
            }
        }
        if (primaryVendorItem == null) {
            if (other.primaryVendorItem!= null) {
                return false;
            }
        } else {
            if (!primaryVendorItem.equals(other.primaryVendorItem)) {
                return false;
            }
        }
        if (autoGenSerialsFl == null) {
            if (other.autoGenSerialsFl!= null) {
                return false;
            }
        } else {
            if (!autoGenSerialsFl.equals(other.autoGenSerialsFl)) {
                return false;
            }
        }
        if (regionalPriceFl == null) {
            if (other.regionalPriceFl!= null) {
                return false;
            }
        } else {
            if (!regionalPriceFl.equals(other.regionalPriceFl)) {
                return false;
            }
        }
        if (regionalPromoFl == null) {
            if (other.regionalPromoFl!= null) {
                return false;
            }
        } else {
            if (!regionalPromoFl.equals(other.regionalPromoFl)) {
                return false;
            }
        }
        if (regionalSupersedeFl == null) {
            if (other.regionalSupersedeFl!= null) {
                return false;
            }
        } else {
            if (!regionalSupersedeFl.equals(other.regionalSupersedeFl)) {
                return false;
            }
        }
        if (webEnabledFl == null) {
            if (other.webEnabledFl!= null) {
                return false;
            }
        } else {
            if (!webEnabledFl.equals(other.webEnabledFl)) {
                return false;
            }
        }
        if (ecommNonInventoryFl == null) {
            if (other.ecommNonInventoryFl!= null) {
                return false;
            }
        } else {
            if (!ecommNonInventoryFl.equals(other.ecommNonInventoryFl)) {
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
        final InvtoryFullEntity other = ((InvtoryFullEntity) obj);
        // Identifier level comparison
        if (this.identifier.getSkuNo() == null) {
            return false;
        } else {
            if (!this.identifier.getSkuNo().equals(other.identifier.getSkuNo())) {
                return false;
            }
        }
        // Data level comparison
        if (mfgCd == null) {
            if (other.mfgCd!= null) {
                return false;
            }
        } else {
            if (!mfgCd.equals(other.mfgCd)) {
                return false;
            }
        }
        if (xrefNo == null) {
            if (other.xrefNo!= null) {
                return false;
            }
        } else {
            if (!xrefNo.equals(other.xrefNo)) {
                return false;
            }
        }
        if (description == null) {
            if (other.description!= null) {
                return false;
            }
        } else {
            if (!description.equals(other.description)) {
                return false;
            }
        }
        if (receiptDesc == null) {
            if (other.receiptDesc!= null) {
                return false;
            }
        } else {
            if (!receiptDesc.equals(other.receiptDesc)) {
                return false;
            }
        }
        if (lookupDesc == null) {
            if (other.lookupDesc!= null) {
                return false;
            }
        } else {
            if (!lookupDesc.equals(other.lookupDesc)) {
                return false;
            }
        }
        if (pkgDesc == null) {
            if (other.pkgDesc!= null) {
                return false;
            }
        } else {
            if (!pkgDesc.equals(other.pkgDesc)) {
                return false;
            }
        }
        if (deptNo == null) {
            if (other.deptNo!= null) {
                return false;
            }
        } else {
            if (!deptNo.equals(other.deptNo)) {
                return false;
            }
        }
        if (classNo == null) {
            if (other.classNo!= null) {
                return false;
            }
        } else {
            if (!classNo.equals(other.classNo)) {
                return false;
            }
        }
        if (lineNo == null) {
            if (other.lineNo!= null) {
                return false;
            }
        } else {
            if (!lineNo.equals(other.lineNo)) {
                return false;
            }
        }
        if (code1Id == null) {
            if (other.code1Id!= null) {
                return false;
            }
        } else {
            if (!code1Id.equals(other.code1Id)) {
                return false;
            }
        }
        if (code2Id == null) {
            if (other.code2Id!= null) {
                return false;
            }
        } else {
            if (!code2Id.equals(other.code2Id)) {
                return false;
            }
        }
        if (code3Id == null) {
            if (other.code3Id!= null) {
                return false;
            }
        } else {
            if (!code3Id.equals(other.code3Id)) {
                return false;
            }
        }
        if (changeDt == null) {
            if (other.changeDt!= null) {
                return false;
            }
        } else {
            if (!changeDt.equals(other.changeDt)) {
                return false;
            }
        }
        if (otbFl == null) {
            if (other.otbFl!= null) {
                return false;
            }
        } else {
            if (!otbFl.equals(other.otbFl)) {
                return false;
            }
        }
        if (useSerialFl == null) {
            if (other.useSerialFl!= null) {
                return false;
            }
        } else {
            if (!useSerialFl.equals(other.useSerialFl)) {
                return false;
            }
        }
        if (glCatId == null) {
            if (other.glCatId!= null) {
                return false;
            }
        } else {
            if (!glCatId.equals(other.glCatId)) {
                return false;
            }
        }
        if (reportFactor == null) {
            if (other.reportFactor!= null) {
                return false;
            }
        } else {
            if (!reportFactor.equals(other.reportFactor)) {
                return false;
            }
        }
        if (lcuQty == null) {
            if (other.lcuQty!= null) {
                return false;
            }
        } else {
            if (!lcuQty.equals(other.lcuQty)) {
                return false;
            }
        }
        if (reportUm == null) {
            if (other.reportUm!= null) {
                return false;
            }
        } else {
            if (!reportUm.equals(other.reportUm)) {
                return false;
            }
        }
        if (sellUm == null) {
            if (other.sellUm!= null) {
                return false;
            }
        } else {
            if (!sellUm.equals(other.sellUm)) {
                return false;
            }
        }
        if (ticketType == null) {
            if (other.ticketType!= null) {
                return false;
            }
        } else {
            if (!ticketType.equals(other.ticketType)) {
                return false;
            }
        }
        if (unitWeight == null) {
            if (other.unitWeight!= null) {
                return false;
            }
        } else {
            if (!unitWeight.equals(other.unitWeight)) {
                return false;
            }
        }
        if (shelfType == null) {
            if (other.shelfType!= null) {
                return false;
            }
        } else {
            if (!shelfType.equals(other.shelfType)) {
                return false;
            }
        }
        if (linkSkuNo == null) {
            if (other.linkSkuNo!= null) {
                return false;
            }
        } else {
            if (!linkSkuNo.equals(other.linkSkuNo)) {
                return false;
            }
        }
        if (reorderFl == null) {
            if (other.reorderFl!= null) {
                return false;
            }
        } else {
            if (!reorderFl.equals(other.reorderFl)) {
                return false;
            }
        }
        if (specialItemFl == null) {
            if (other.specialItemFl!= null) {
                return false;
            }
        } else {
            if (!specialItemFl.equals(other.specialItemFl)) {
                return false;
            }
        }
        if (weeksHistory == null) {
            if (other.weeksHistory!= null) {
                return false;
            }
        } else {
            if (!weeksHistory.equals(other.weeksHistory)) {
                return false;
            }
        }
        if (periodsHistory == null) {
            if (other.periodsHistory!= null) {
                return false;
            }
        } else {
            if (!periodsHistory.equals(other.periodsHistory)) {
                return false;
            }
        }
        if (storeCpnFl == null) {
            if (other.storeCpnFl!= null) {
                return false;
            }
        } else {
            if (!storeCpnFl.equals(other.storeCpnFl)) {
                return false;
            }
        }
        if (reportCd == null) {
            if (other.reportCd!= null) {
                return false;
            }
        } else {
            if (!reportCd.equals(other.reportCd)) {
                return false;
            }
        }
        if (downloadFl == null) {
            if (other.downloadFl!= null) {
                return false;
            }
        } else {
            if (!downloadFl.equals(other.downloadFl)) {
                return false;
            }
        }
        if (discontinuedFl == null) {
            if (other.discontinuedFl!= null) {
                return false;
            }
        } else {
            if (!discontinuedFl.equals(other.discontinuedFl)) {
                return false;
            }
        }
        if (equivFactor == null) {
            if (other.equivFactor!= null) {
                return false;
            }
        } else {
            if (!equivFactor.equals(other.equivFactor)) {
                return false;
            }
        }
        if (newLabelDt == null) {
            if (other.newLabelDt!= null) {
                return false;
            }
        } else {
            if (!newLabelDt.equals(other.newLabelDt)) {
                return false;
            }
        }
        if (changeLabelDt == null) {
            if (other.changeLabelDt!= null) {
                return false;
            }
        } else {
            if (!changeLabelDt.equals(other.changeLabelDt)) {
                return false;
            }
        }
        if (labelDesc1 == null) {
            if (other.labelDesc1 != null) {
                return false;
            }
        } else {
            if (!labelDesc1 .equals(other.labelDesc1)) {
                return false;
            }
        }
        if (labelDesc2 == null) {
            if (other.labelDesc2 != null) {
                return false;
            }
        } else {
            if (!labelDesc2 .equals(other.labelDesc2)) {
                return false;
            }
        }
        if (equivUm == null) {
            if (other.equivUm!= null) {
                return false;
            }
        } else {
            if (!equivUm.equals(other.equivUm)) {
                return false;
            }
        }
        if (warehouseInnerPack == null) {
            if (other.warehouseInnerPack!= null) {
                return false;
            }
        } else {
            if (!warehouseInnerPack.equals(other.warehouseInnerPack)) {
                return false;
            }
        }
        if (obsoleteFl == null) {
            if (other.obsoleteFl!= null) {
                return false;
            }
        } else {
            if (!obsoleteFl.equals(other.obsoleteFl)) {
                return false;
            }
        }
        if (perCarQty == null) {
            if (other.perCarQty!= null) {
                return false;
            }
        } else {
            if (!perCarQty.equals(other.perCarQty)) {
                return false;
            }
        }
        if (priceListSeq == null) {
            if (other.priceListSeq!= null) {
                return false;
            }
        } else {
            if (!priceListSeq.equals(other.priceListSeq)) {
                return false;
            }
        }
        if (supersededFl == null) {
            if (other.supersededFl!= null) {
                return false;
            }
        } else {
            if (!supersededFl.equals(other.supersededFl)) {
                return false;
            }
        }
        if (unitCube == null) {
            if (other.unitCube!= null) {
                return false;
            }
        } else {
            if (!unitCube.equals(other.unitCube)) {
                return false;
            }
        }
        if (captureOrderNoFl == null) {
            if (other.captureOrderNoFl!= null) {
                return false;
            }
        } else {
            if (!captureOrderNoFl.equals(other.captureOrderNoFl)) {
                return false;
            }
        }
        if (captureSerialNoFl == null) {
            if (other.captureSerialNoFl!= null) {
                return false;
            }
        } else {
            if (!captureSerialNoFl.equals(other.captureSerialNoFl)) {
                return false;
            }
        }
        if (venitmSeqNo == null) {
            if (other.venitmSeqNo!= null) {
                return false;
            }
        } else {
            if (!venitmSeqNo.equals(other.venitmSeqNo)) {
                return false;
            }
        }
        if (upcIdSeqNo == null) {
            if (other.upcIdSeqNo!= null) {
                return false;
            }
        } else {
            if (!upcIdSeqNo.equals(other.upcIdSeqNo)) {
                return false;
            }
        }
        if (employeeId == null) {
            if (other.employeeId!= null) {
                return false;
            }
        } else {
            if (!employeeId.equals(other.employeeId)) {
                return false;
            }
        }
        if (supplementalUpcFl == null) {
            if (other.supplementalUpcFl!= null) {
                return false;
            }
        } else {
            if (!supplementalUpcFl.equals(other.supplementalUpcFl)) {
                return false;
            }
        }
        if (weighedItemFl == null) {
            if (other.weighedItemFl!= null) {
                return false;
            }
        } else {
            if (!weighedItemFl.equals(other.weighedItemFl)) {
                return false;
            }
        }
        if (suggestedSellCd == null) {
            if (other.suggestedSellCd!= null) {
                return false;
            }
        } else {
            if (!suggestedSellCd.equals(other.suggestedSellCd)) {
                return false;
            }
        }
        if (validationSeqNo == null) {
            if (other.validationSeqNo!= null) {
                return false;
            }
        } else {
            if (!validationSeqNo.equals(other.validationSeqNo)) {
                return false;
            }
        }
        if (restrictSaleFl == null) {
            if (other.restrictSaleFl!= null) {
                return false;
            }
        } else {
            if (!restrictSaleFl.equals(other.restrictSaleFl)) {
                return false;
            }
        }
        if (availSaleDt == null) {
            if (other.availSaleDt!= null) {
                return false;
            }
        } else {
            if (!availSaleDt.equals(other.availSaleDt)) {
                return false;
            }
        }
        if (invItemLockFl == null) {
            if (other.invItemLockFl!= null) {
                return false;
            }
        } else {
            if (!invItemLockFl.equals(other.invItemLockFl)) {
                return false;
            }
        }
        if (nonInventoryFl == null) {
            if (other.nonInventoryFl!= null) {
                return false;
            }
        } else {
            if (!nonInventoryFl.equals(other.nonInventoryFl)) {
                return false;
            }
        }
        if (privateBrandFl == null) {
            if (other.privateBrandFl!= null) {
                return false;
            }
        } else {
            if (!privateBrandFl.equals(other.privateBrandFl)) {
                return false;
            }
        }
        if (lastItemChangeDt == null) {
            if (other.lastItemChangeDt!= null) {
                return false;
            }
        } else {
            if (!lastItemChangeDt.equals(other.lastItemChangeDt)) {
                return false;
            }
        }
        if (lastItemChangeVendorId == null) {
            if (other.lastItemChangeVendorId!= null) {
                return false;
            }
        } else {
            if (!lastItemChangeVendorId.equals(other.lastItemChangeVendorId)) {
                return false;
            }
        }
        if (tenderProgramId == null) {
            if (other.tenderProgramId!= null) {
                return false;
            }
        } else {
            if (!tenderProgramId.equals(other.tenderProgramId)) {
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
        if (invType == null) {
            if (other.invType!= null) {
                return false;
            }
        } else {
            if (!invType.equals(other.invType)) {
                return false;
            }
        }
        if (warrantyFl == null) {
            if (other.warrantyFl!= null) {
                return false;
            }
        } else {
            if (!warrantyFl.equals(other.warrantyFl)) {
                return false;
            }
        }
        if (systemWarrantyFl == null) {
            if (other.systemWarrantyFl!= null) {
                return false;
            }
        } else {
            if (!systemWarrantyFl.equals(other.systemWarrantyFl)) {
                return false;
            }
        }
        if (subInvFl == null) {
            if (other.subInvFl!= null) {
                return false;
            }
        } else {
            if (!subInvFl.equals(other.subInvFl)) {
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
        if (operationType == null) {
            if (other.operationType!= null) {
                return false;
            }
        } else {
            if (!operationType.equals(other.operationType)) {
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
        if (replacementSkuNo == null) {
            if (other.replacementSkuNo!= null) {
                return false;
            }
        } else {
            if (!replacementSkuNo.equals(other.replacementSkuNo)) {
                return false;
            }
        }
        if (hazUnNumberId == null) {
            if (other.hazUnNumberId!= null) {
                return false;
            }
        } else {
            if (!hazUnNumberId.equals(other.hazUnNumberId)) {
                return false;
            }
        }
        if (hazClassId == null) {
            if (other.hazClassId!= null) {
                return false;
            }
        } else {
            if (!hazClassId.equals(other.hazClassId)) {
                return false;
            }
        }
        if (rollupBucketFl == null) {
            if (other.rollupBucketFl!= null) {
                return false;
            }
        } else {
            if (!rollupBucketFl.equals(other.rollupBucketFl)) {
                return false;
            }
        }
        if (tareTableNo == null) {
            if (other.tareTableNo!= null) {
                return false;
            }
        } else {
            if (!tareTableNo.equals(other.tareTableNo)) {
                return false;
            }
        }
        if (catchWeightFl == null) {
            if (other.catchWeightFl!= null) {
                return false;
            }
        } else {
            if (!catchWeightFl.equals(other.catchWeightFl)) {
                return false;
            }
        }
        if (productHier4 == null) {
            if (other.productHier4 != null) {
                return false;
            }
        } else {
            if (!productHier4 .equals(other.productHier4)) {
                return false;
            }
        }
        if (productHier5 == null) {
            if (other.productHier5 != null) {
                return false;
            }
        } else {
            if (!productHier5 .equals(other.productHier5)) {
                return false;
            }
        }
        if (productHier6 == null) {
            if (other.productHier6 != null) {
                return false;
            }
        } else {
            if (!productHier6 .equals(other.productHier6)) {
                return false;
            }
        }
        if (rrpInvType == null) {
            if (other.rrpInvType!= null) {
                return false;
            }
        } else {
            if (!rrpInvType.equals(other.rrpInvType)) {
                return false;
            }
        }
        if (hierarchyId == null) {
            if (other.hierarchyId!= null) {
                return false;
            }
        } else {
            if (!hierarchyId.equals(other.hierarchyId)) {
                return false;
            }
        }
        if (negativePriceFl == null) {
            if (other.negativePriceFl!= null) {
                return false;
            }
        } else {
            if (!negativePriceFl.equals(other.negativePriceFl)) {
                return false;
            }
        }
        if (packupActionId == null) {
            if (other.packupActionId!= null) {
                return false;
            }
        } else {
            if (!packupActionId.equals(other.packupActionId)) {
                return false;
            }
        }
        if (ownerId == null) {
            if (other.ownerId!= null) {
                return false;
            }
        } else {
            if (!ownerId.equals(other.ownerId)) {
                return false;
            }
        }
        if (flexibleItemFl == null) {
            if (other.flexibleItemFl!= null) {
                return false;
            }
        } else {
            if (!flexibleItemFl.equals(other.flexibleItemFl)) {
                return false;
            }
        }
        if (supplyItemFl == null) {
            if (other.supplyItemFl!= null) {
                return false;
            }
        } else {
            if (!supplyItemFl.equals(other.supplyItemFl)) {
                return false;
            }
        }
        if (attribute1Id == null) {
            if (other.attribute1Id!= null) {
                return false;
            }
        } else {
            if (!attribute1Id.equals(other.attribute1Id)) {
                return false;
            }
        }
        if (attribute2Id == null) {
            if (other.attribute2Id!= null) {
                return false;
            }
        } else {
            if (!attribute2Id.equals(other.attribute2Id)) {
                return false;
            }
        }
        if (attribute3Id == null) {
            if (other.attribute3Id!= null) {
                return false;
            }
        } else {
            if (!attribute3Id.equals(other.attribute3Id)) {
                return false;
            }
        }
        if (attribute1Value == null) {
            if (other.attribute1Value!= null) {
                return false;
            }
        } else {
            if (!attribute1Value.equals(other.attribute1Value)) {
                return false;
            }
        }
        if (attribute2Value == null) {
            if (other.attribute2Value!= null) {
                return false;
            }
        } else {
            if (!attribute2Value.equals(other.attribute2Value)) {
                return false;
            }
        }
        if (attribute3Value == null) {
            if (other.attribute3Value!= null) {
                return false;
            }
        } else {
            if (!attribute3Value.equals(other.attribute3Value)) {
                return false;
            }
        }
        if (coreFl == null) {
            if (other.coreFl!= null) {
                return false;
            }
        } else {
            if (!coreFl.equals(other.coreFl)) {
                return false;
            }
        }
        if (kitMemberFl == null) {
            if (other.kitMemberFl!= null) {
                return false;
            }
        } else {
            if (!kitMemberFl.equals(other.kitMemberFl)) {
                return false;
            }
        }
        if (primaryVendorItem == null) {
            if (other.primaryVendorItem!= null) {
                return false;
            }
        } else {
            if (!primaryVendorItem.equals(other.primaryVendorItem)) {
                return false;
            }
        }
        if (autoGenSerialsFl == null) {
            if (other.autoGenSerialsFl!= null) {
                return false;
            }
        } else {
            if (!autoGenSerialsFl.equals(other.autoGenSerialsFl)) {
                return false;
            }
        }
        if (regionalPriceFl == null) {
            if (other.regionalPriceFl!= null) {
                return false;
            }
        } else {
            if (!regionalPriceFl.equals(other.regionalPriceFl)) {
                return false;
            }
        }
        if (regionalPromoFl == null) {
            if (other.regionalPromoFl!= null) {
                return false;
            }
        } else {
            if (!regionalPromoFl.equals(other.regionalPromoFl)) {
                return false;
            }
        }
        if (regionalSupersedeFl == null) {
            if (other.regionalSupersedeFl!= null) {
                return false;
            }
        } else {
            if (!regionalSupersedeFl.equals(other.regionalSupersedeFl)) {
                return false;
            }
        }
        if (webEnabledFl == null) {
            if (other.webEnabledFl!= null) {
                return false;
            }
        } else {
            if (!webEnabledFl.equals(other.webEnabledFl)) {
                return false;
            }
        }
        if (ecommNonInventoryFl == null) {
            if (other.ecommNonInventoryFl!= null) {
                return false;
            }
        } else {
            if (!ecommNonInventoryFl.equals(other.ecommNonInventoryFl)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        // If any ID columns are null, compare just the object instance it self.  This preserves objects that have not been assigned ID's yet.
        if (this.identifier.getSkuNo() == null) {
            return super.hashCode();
        }
        	
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier.getSkuNo() == null) ? 0 : identifier.getSkuNo().hashCode());
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
    public int compareTo(InvtoryFullEntity other) {
        return this.identifier.compareTo(other.identifier);
    }

    /**
     * This method displays bean properties for debugging purposes.  Do not use for anything else as it may change to enhance debugging in the future.
     * 
     */
    public String toString() {
        final StringBuilder temp = new StringBuilder();
        	
        temp.append("\n");
        temp.append("InvtoryFullEntity (INVTORY)\n");
        temp.append("  ID   1 : skuNo                          : SKU_NO                         : ").append(this.identifier.getSkuNo()).append("\n");
        temp.append("       2 : mfgCd                          : MFG_CD                         : ").append(mfgCd).append("\n");
        temp.append("       3 : xrefNo                         : XREF_NO                        : ").append(xrefNo).append("\n");
        temp.append("       4 : description                    : DESCRIPTION                    : ").append(description).append("\n");
        temp.append("       5 : receiptDesc                    : RECEIPT_DESC                   : ").append(receiptDesc).append("\n");
        temp.append("       6 : lookupDesc                     : LOOKUP_DESC                    : ").append(lookupDesc).append("\n");
        temp.append("       7 : pkgDesc                        : PKG_DESC                       : ").append(pkgDesc).append("\n");
        temp.append("       8 : deptNo                         : DEPT_NO                        : ").append(deptNo).append("\n");
        temp.append("       9 : classNo                        : CLASS_NO                       : ").append(classNo).append("\n");
        temp.append("      10 : lineNo                         : LINE_NO                        : ").append(lineNo).append("\n");
        temp.append("      11 : code1Id                        : CODE1_ID                       : ").append(code1Id).append("\n");
        temp.append("      12 : code2Id                        : CODE2_ID                       : ").append(code2Id).append("\n");
        temp.append("      13 : code3Id                        : CODE3_ID                       : ").append(code3Id).append("\n");
        temp.append("  AD  14 : createDt                       : CREATE_DT                      : ").append(createDt).append("\n");
        temp.append("      15 : changeDt                       : CHANGE_DT                      : ").append(changeDt).append("\n");
        temp.append("      16 : otbFl                          : OTB_FL                         : ").append(otbFl).append("\n");
        temp.append("      17 : useSerialFl                    : USE_SERIAL_FL                  : ").append(useSerialFl).append("\n");
        temp.append("      18 : glCatId                        : GL_CAT_ID                      : ").append(glCatId).append("\n");
        temp.append("      19 : reportFactor                   : REPORT_FACTOR                  : ").append(reportFactor).append("\n");
        temp.append("      20 : lcuQty                         : LCU_QTY                        : ").append(lcuQty).append("\n");
        temp.append("      21 : reportUm                       : REPORT_UM                      : ").append(reportUm).append("\n");
        temp.append("      22 : sellUm                         : SELL_UM                        : ").append(sellUm).append("\n");
        temp.append("      23 : ticketType                     : TICKET_TYPE                    : ").append(ticketType).append("\n");
        temp.append("      24 : unitWeight                     : UNIT_WEIGHT                    : ").append(unitWeight).append("\n");
        temp.append("      25 : shelfType                      : SHELF_TYPE                     : ").append(shelfType).append("\n");
        temp.append("      26 : linkSkuNo                      : LINK_SKU_NO                    : ").append(linkSkuNo).append("\n");
        temp.append("      27 : reorderFl                      : REORDER_FL                     : ").append(reorderFl).append("\n");
        temp.append("      28 : specialItemFl                  : SPECIAL_ITEM_FL                : ").append(specialItemFl).append("\n");
        temp.append("      29 : weeksHistory                   : WEEKS_HISTORY                  : ").append(weeksHistory).append("\n");
        temp.append("      30 : periodsHistory                 : PERIODS_HISTORY                : ").append(periodsHistory).append("\n");
        temp.append("      31 : storeCpnFl                     : STORE_CPN_FL                   : ").append(storeCpnFl).append("\n");
        temp.append("      32 : reportCd                       : REPORT_CD                      : ").append(reportCd).append("\n");
        temp.append("      33 : downloadFl                     : DOWNLOAD_FL                    : ").append(downloadFl).append("\n");
        temp.append("      34 : discontinuedFl                 : DISCONTINUED_FL                : ").append(discontinuedFl).append("\n");
        temp.append("      35 : equivFactor                    : EQUIV_FACTOR                   : ").append(equivFactor).append("\n");
        temp.append("      36 : newLabelDt                     : NEW_LABEL_DT                   : ").append(newLabelDt).append("\n");
        temp.append("      37 : changeLabelDt                  : CHANGE_LABEL_DT                : ").append(changeLabelDt).append("\n");
        temp.append("      38 : labelDesc1                     : LABEL_DESC1                    : ").append(labelDesc1).append("\n");
        temp.append("      39 : labelDesc2                     : LABEL_DESC2                    : ").append(labelDesc2).append("\n");
        temp.append("      40 : equivUm                        : EQUIV_UM                       : ").append(equivUm).append("\n");
        temp.append("      41 : warehouseInnerPack             : WAREHOUSE_INNER_PACK           : ").append(warehouseInnerPack).append("\n");
        temp.append("      42 : obsoleteFl                     : OBSOLETE_FL                    : ").append(obsoleteFl).append("\n");
        temp.append("      43 : perCarQty                      : PER_CAR_QTY                    : ").append(perCarQty).append("\n");
        temp.append("      44 : priceListSeq                   : PRICE_LIST_SEQ                 : ").append(priceListSeq).append("\n");
        temp.append("      45 : supersededFl                   : SUPERSEDED_FL                  : ").append(supersededFl).append("\n");
        temp.append("      46 : unitCube                       : UNIT_CUBE                      : ").append(unitCube).append("\n");
        temp.append("      47 : captureOrderNoFl               : CAPTURE_ORDER_NO_FL            : ").append(captureOrderNoFl).append("\n");
        temp.append("      48 : captureSerialNoFl              : CAPTURE_SERIAL_NO_FL           : ").append(captureSerialNoFl).append("\n");
        temp.append("      49 : venitmSeqNo                    : VENITM_SEQ_NO                  : ").append(venitmSeqNo).append("\n");
        temp.append("      50 : upcIdSeqNo                     : UPC_ID_SEQ_NO                  : ").append(upcIdSeqNo).append("\n");
        temp.append("      51 : employeeId                     : EMPLOYEE_ID                    : ").append(employeeId).append("\n");
        temp.append("      52 : supplementalUpcFl              : SUPPLEMENTAL_UPC_FL            : ").append(supplementalUpcFl).append("\n");
        temp.append("      53 : weighedItemFl                  : WEIGHED_ITEM_FL                : ").append(weighedItemFl).append("\n");
        temp.append("      54 : suggestedSellCd                : SUGGESTED_SELL_CD              : ").append(suggestedSellCd).append("\n");
        temp.append("      55 : validationSeqNo                : VALIDATION_SEQ_NO              : ").append(validationSeqNo).append("\n");
        temp.append("      56 : restrictSaleFl                 : RESTRICT_SALE_FL               : ").append(restrictSaleFl).append("\n");
        temp.append("      57 : availSaleDt                    : AVAIL_SALE_DT                  : ").append(availSaleDt).append("\n");
        temp.append("      58 : invItemLockFl                  : INV_ITEM_LOCK_FL               : ").append(invItemLockFl).append("\n");
        temp.append("      59 : nonInventoryFl                 : NON_INVENTORY_FL               : ").append(nonInventoryFl).append("\n");
        temp.append("      60 : privateBrandFl                 : PRIVATE_BRAND_FL               : ").append(privateBrandFl).append("\n");
        temp.append("      61 : lastItemChangeDt               : LAST_ITEM_CHANGE_DT            : ").append(lastItemChangeDt).append("\n");
        temp.append("      62 : lastItemChangeVendorId         : LAST_ITEM_CHANGE_VENDOR_ID     : ").append(lastItemChangeVendorId).append("\n");
        temp.append("      63 : tenderProgramId                : TENDER_PROGRAM_ID              : ").append(tenderProgramId).append("\n");
        temp.append("      64 : externalRefId                  : EXTERNAL_REF_ID                : ").append(externalRefId).append("\n");
        temp.append("      65 : invType                        : INV_TYPE                       : ").append(invType).append("\n");
        temp.append("      66 : warrantyFl                     : WARRANTY_FL                    : ").append(warrantyFl).append("\n");
        temp.append("      67 : systemWarrantyFl               : SYSTEM_WARRANTY_FL             : ").append(systemWarrantyFl).append("\n");
        temp.append("      68 : subInvFl                       : SUB_INV_FL                     : ").append(subInvFl).append("\n");
        temp.append("      69 : replicationNo                  : REPLICATION_NO                 : ").append(replicationNo).append("\n");
        temp.append("      70 : operationType                  : OPERATION_TYPE                 : ").append(operationType).append("\n");
        temp.append("      71 : registerReplicationNo          : REGISTER_REPLICATION_NO        : ").append(registerReplicationNo).append("\n");
        temp.append("  AD  72 : dateCreated                    : DATE_CREATED                   : ").append(dateCreated).append("\n");
        temp.append("  AD  73 : userCreated                    : USER_CREATED                   : ").append(userCreated).append("\n");
        temp.append("  AD  74 : dateModified                   : DATE_MODIFIED                  : ").append(dateModified).append("\n");
        temp.append("  AD  75 : userModified                   : USER_MODIFIED                  : ").append(userModified).append("\n");
        temp.append("      76 : replacementSkuNo               : REPLACEMENT_SKU_NO             : ").append(replacementSkuNo).append("\n");
        temp.append("      77 : hazUnNumberId                  : HAZ_UN_NUMBER_ID               : ").append(hazUnNumberId).append("\n");
        temp.append("      78 : hazClassId                     : HAZ_CLASS_ID                   : ").append(hazClassId).append("\n");
        temp.append("      79 : rollupBucketFl                 : ROLLUP_BUCKET_FL               : ").append(rollupBucketFl).append("\n");
        temp.append("      80 : tareTableNo                    : TARE_TABLE_NO                  : ").append(tareTableNo).append("\n");
        temp.append("      81 : catchWeightFl                  : CATCH_WEIGHT_FL                : ").append(catchWeightFl).append("\n");
        temp.append("      82 : productHier4                   : PRODUCT_HIER4                  : ").append(productHier4).append("\n");
        temp.append("      83 : productHier5                   : PRODUCT_HIER5                  : ").append(productHier5).append("\n");
        temp.append("      84 : productHier6                   : PRODUCT_HIER6                  : ").append(productHier6).append("\n");
        temp.append("      85 : rrpInvType                     : RRP_INV_TYPE                   : ").append(rrpInvType).append("\n");
        temp.append("      86 : hierarchyId                    : HIERARCHY_ID                   : ").append(hierarchyId).append("\n");
        temp.append("      87 : negativePriceFl                : NEGATIVE_PRICE_FL              : ").append(negativePriceFl).append("\n");
        temp.append("      88 : packupActionId                 : PACKUP_ACTION_ID               : ").append(packupActionId).append("\n");
        temp.append("      89 : ownerId                        : OWNER_ID                       : ").append(ownerId).append("\n");
        temp.append("      90 : flexibleItemFl                 : FLEXIBLE_ITEM_FL               : ").append(flexibleItemFl).append("\n");
        temp.append("      91 : supplyItemFl                   : SUPPLY_ITEM_FL                 : ").append(supplyItemFl).append("\n");
        temp.append("      92 : attribute1Id                   : ATTRIBUTE1_ID                  : ").append(attribute1Id).append("\n");
        temp.append("      93 : attribute2Id                   : ATTRIBUTE2_ID                  : ").append(attribute2Id).append("\n");
        temp.append("      94 : attribute3Id                   : ATTRIBUTE3_ID                  : ").append(attribute3Id).append("\n");
        temp.append("      95 : attribute1Value                : ATTRIBUTE1_VALUE               : ").append(attribute1Value).append("\n");
        temp.append("      96 : attribute2Value                : ATTRIBUTE2_VALUE               : ").append(attribute2Value).append("\n");
        temp.append("      97 : attribute3Value                : ATTRIBUTE3_VALUE               : ").append(attribute3Value).append("\n");
        temp.append("      98 : coreFl                         : CORE_FL                        : ").append(coreFl).append("\n");
        temp.append("      99 : kitMemberFl                    : KIT_MEMBER_FL                  : ").append(kitMemberFl).append("\n");
        temp.append("     100 : primaryVendorItem              : PRIMARY_VENDOR_ITEM            : ").append(primaryVendorItem).append("\n");
        temp.append("     101 : autoGenSerialsFl               : AUTO_GEN_SERIALS_FL            : ").append(autoGenSerialsFl).append("\n");
        temp.append("     102 : regionalPriceFl                : REGIONAL_PRICE_FL              : ").append(regionalPriceFl).append("\n");
        temp.append("     103 : regionalPromoFl                : REGIONAL_PROMO_FL              : ").append(regionalPromoFl).append("\n");
        temp.append("     104 : regionalSupersedeFl            : REGIONAL_SUPERSEDE_FL          : ").append(regionalSupersedeFl).append("\n");
        temp.append("     105 : webEnabledFl                   : WEB_ENABLED_FL                 : ").append(webEnabledFl).append("\n");
        temp.append("     106 : ecommNonInventoryFl            : ECOMM_NON_INVENTORY_FL         : ").append(ecommNonInventoryFl).append("\n");
        	
        return temp.toString();
    }

}
