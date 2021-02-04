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
import javax.annotation.Generated;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
import com.tomax.vendor.dto.VendorDTO;
import com.tomax.vendor.dto.VendorDTOId;

@SuppressWarnings("all")
//@NamedQueries({
//    @NamedQuery(name = "VENDOR_BY_ID", query = "Select v from VendorEntity v Where v.vendorId IN (:vendorId) "),
//    @NamedQuery(name = "VENDORS_BY_ITEM", query = "Select v from VendorEntity v, VenSiteEntity vs, VendorItemEntity vi Where v.vendorId = vi.vendorId and vi.siteNo = :siteNo and vi.skuNo = :skuNo and v.vendorId = vs.vendorId and vs.siteNo = vi.siteNo order by v.vendorId "),
//    @NamedQuery(name = "VENDORS_BY_ITEM_ACTIVE", query = "Select v from VendorEntity v, VenSiteEntity vs, VendorItemEntity vi Where v.vendorId = vi.vendorId and vi.siteNo =:siteNo and vi.skuNo = :skuNo and v.vendorId = vs.vendorId and vs.siteNo = vi.siteNo and vi.orderAvailabilityStatus ='ACT' order by v.vendorId "),
//    @NamedQuery(name = "VENDOR_NAME_BY_ID", query = "Select v.name from VendorEntity v Where v.vendorId = :vendorId ")
//})
@Entity
@Table(name = "VENDOR")
public class VendorEntity
    extends RNetAbstractEntity<VendorEntity, VendorDTO>
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
    private VendorEntityId identifier;
    /**
     * <p>Maps to table column: {@code NAME}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 40, message = "NAME should contain less than or equal to 40 characters")
    @NotNull(message = "NAME can not be null")
    @Column(name = "NAME")
    private String name;
    /**
     * <p>Maps to table column: {@code ADR1}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 40, message = "ADR1 should contain less than or equal to 40 characters")
    @NotNull(message = "ADR1 can not be null")
    @Column(name = "ADR1")
    private String adr1;
    /**
     * <p>Maps to table column: {@code ADR2}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 40, message = "ADR2 should contain less than or equal to 40 characters")
    @Column(name = "ADR2")
    private String adr2;
    /**
     * <p>Maps to table column: {@code CITY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 40, message = "CITY should contain less than or equal to 40 characters")
    @NotNull(message = "CITY can not be null")
    @Column(name = "CITY")
    private String city;
    /**
     * <p>Maps to table column: {@code STATE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 40, message = "STATE should contain less than or equal to 40 characters")
    @NotNull(message = "STATE can not be null")
    @Column(name = "STATE")
    private String state;
    /**
     * <p>Maps to table column: {@code ZIP}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 40, message = "ZIP should contain less than or equal to 40 characters")
    @NotNull(message = "ZIP can not be null")
    @Column(name = "ZIP")
    private String zip;
    /**
     * <p>Maps to table column: {@code COUNTRY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 40, message = "COUNTRY should contain less than or equal to 40 characters")
    @Column(name = "COUNTRY")
    private String country;
    /**
     * <p>Maps to table column: {@code CONTACT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 50, message = "CONTACT should contain less than or equal to 50 characters")
    @Column(name = "CONTACT")
    private String contact;
    /**
     * <p>Maps to table column: {@code CONTACT2}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 30, message = "CONTACT2 should contain less than or equal to 30 characters")
    @Column(name = "CONTACT2")
    private String contact2;
    /**
     * <p>Maps to table column: {@code PHONE_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 30, message = "PHONE_NO should contain less than or equal to 30 characters")
    @NotNull(message = "PHONE_NO can not be null")
    @Column(name = "PHONE_NO")
    private String phoneNo;
    /**
     * <p>Maps to table column: {@code PHONE2_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 20, message = "PHONE2_NO should contain less than or equal to 20 characters")
    @Column(name = "PHONE2_NO")
    private String phone2No;
    /**
     * <p>Maps to table column: {@code FAX_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 30, message = "FAX_NO should contain less than or equal to 30 characters")
    @Column(name = "FAX_NO")
    private String faxNo;
    /**
     * <p>Maps to table column: {@code EMAIL_ADDRESS}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 100, message = "EMAIL_ADDRESS should contain less than or equal to 100 characters")
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    /**
     * <p>Maps to table column: {@code AP_ADR1}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 40, message = "AP_ADR1 should contain less than or equal to 40 characters")
    @NotNull(message = "AP_ADR1 can not be null")
    @Column(name = "AP_ADR1")
    private String apAdr1;
    /**
     * <p>Maps to table column: {@code AP_ADR2}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 40, message = "AP_ADR2 should contain less than or equal to 40 characters")
    @Column(name = "AP_ADR2")
    private String apAdr2;
    /**
     * <p>Maps to table column: {@code AP_CITY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 40, message = "AP_CITY should contain less than or equal to 40 characters")
    @NotNull(message = "AP_CITY can not be null")
    @Column(name = "AP_CITY")
    private String apCity;
    /**
     * <p>Maps to table column: {@code AP_STATE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 40, message = "AP_STATE should contain less than or equal to 40 characters")
    @NotNull(message = "AP_STATE can not be null")
    @Column(name = "AP_STATE")
    private String apState;
    /**
     * <p>Maps to table column: {@code AP_ZIP}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 40, message = "AP_ZIP should contain less than or equal to 40 characters")
    @NotNull(message = "AP_ZIP can not be null")
    @Column(name = "AP_ZIP")
    private String apZip;
    /**
     * <p>Maps to table column: {@code AP_COUNTRY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 40, message = "AP_COUNTRY should contain less than or equal to 40 characters")
    @Column(name = "AP_COUNTRY")
    private String apCountry;
    /**
     * <p>Maps to table column: {@code AP_FLOW_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "AP_FLOW_FL should contain less than or equal to 1 characters")
    @Column(name = "AP_FLOW_FL")
    private String apFlowFl;
    /**
     * <p>Maps to table column: {@code AP_NUMBER}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 30, message = "AP_NUMBER should contain less than or equal to 30 characters")
    @Column(name = "AP_NUMBER")
    private String apNumber;
    /**
     * <p>Maps to table column: {@code PAYMENT_TERM_PCT1}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 2, message = "PAYMENT_TERM_PCT1 should have an integer component no longer than 3 and an a fraction component of exact size 2")
    @Column(name = "PAYMENT_TERM_PCT1")
    private BigDecimal paymentTermPct1;
    /**
     * <p>Maps to table column: {@code PAYMENT_TERM_PCT2}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 2, message = "PAYMENT_TERM_PCT2 should have an integer component no longer than 3 and an a fraction component of exact size 2")
    @Column(name = "PAYMENT_TERM_PCT2")
    private BigDecimal paymentTermPct2;
    /**
     * <p>Maps to table column: {@code PAYMENT_TERM_DAYS1}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "PAYMENT_TERM_DAYS1 should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "PAYMENT_TERM_DAYS1")
    private Integer paymentTermDays1;
    /**
     * <p>Maps to table column: {@code PAYMENT_TERM_DAYS2}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "PAYMENT_TERM_DAYS2 should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "PAYMENT_TERM_DAYS2")
    private Integer paymentTermDays2;
    /**
     * <p>Maps to table column: {@code PAY_STATUS_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "PAY_STATUS_FL should contain less than or equal to 1 characters")
    @Column(name = "PAY_STATUS_FL")
    private String payStatusFl;
    /**
     * <p>Maps to table column: {@code NET_DAYS}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "NET_DAYS should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "NET_DAYS")
    private Integer netDays;
    /**
     * <p>Maps to table column: {@code ORDER_STATUS_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "ORDER_STATUS_FL should contain less than or equal to 1 characters")
    @Column(name = "ORDER_STATUS_FL")
    private String orderStatusFl;
    /**
     * <p>Maps to table column: {@code LAST_INVOICE_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_INVOICE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastInvoiceDt;
    /**
     * <p>Maps to table column: {@code LAST_PAYMENT_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_PAYMENT_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPaymentDt;
    /**
     * <p>Maps to table column: {@code SHIP_VIA}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 10, message = "SHIP_VIA should contain less than or equal to 10 characters")
    @Column(name = "SHIP_VIA")
    private String shipVia;
    /**
     * <p>Maps to table column: {@code SHIP_TERMS}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 10, message = "SHIP_TERMS should contain less than or equal to 10 characters")
    @Column(name = "SHIP_TERMS")
    private String shipTerms;
    /**
     * <p>Maps to table column: {@code FOB}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 15, message = "FOB should contain less than or equal to 15 characters")
    @Column(name = "FOB")
    private String fob;
    /**
     * <p>Maps to table column: {@code COMMENT_TXT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 120, message = "COMMENT_TXT should contain less than or equal to 120 characters")
    @Column(name = "COMMENT_TXT")
    private String commentTxt;
    /**
     * <p>Maps to table column: {@code SHIP_INST_TXT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 240, message = "SHIP_INST_TXT should contain less than or equal to 240 characters")
    @Column(name = "SHIP_INST_TXT")
    private String shipInstTxt;
    /**
     * <p>Maps to table column: {@code EDI_AGENT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 120, message = "EDI_AGENT should contain less than or equal to 120 characters")
    @Column(name = "EDI_AGENT")
    private String ediAgent;
    /**
     * <p>Maps to table column: {@code EDI_TYPE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "EDI_TYPE should contain less than or equal to 1 characters")
    @Column(name = "EDI_TYPE")
    private String ediType;
    /**
     * <p>Maps to table column: {@code CALC_NET_COST_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "CALC_NET_COST_FL should contain less than or equal to 1 characters")
    @Column(name = "CALC_NET_COST_FL")
    private String calcNetCostFl;
    /**
     * <p>Maps to table column: {@code PRIMARY_CONTROL_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "PRIMARY_CONTROL_FL should contain less than or equal to 1 characters")
    @Column(name = "PRIMARY_CONTROL_FL")
    private String primaryControlFl;
    /**
     * <p>Maps to table column: {@code PRIMARY_CONTROL_N_TO_Y_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "PRIMARY_CONTROL_N_TO_Y_FL should contain less than or equal to 1 characters")
    @Column(name = "PRIMARY_CONTROL_N_TO_Y_FL")
    private String primaryControlNToYFl;
    /**
     * <p>Maps to table column: {@code DUNS_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 9, fraction = 0, message = "DUNS_NO should have an integer component no longer than 9 and an a fraction component of exact size 0")
    @Column(name = "DUNS_NO")
    private Integer dunsNo;
    /**
     * <p>Maps to table column: {@code COMM_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 0, message = "COMM_ID should have an integer component no longer than 10 and an a fraction component of exact size 0")
    @Column(name = "COMM_ID")
    private Long commId;
    /**
     * <p>Maps to table column: {@code EXT_CD_METHOD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "EXT_CD_METHOD should contain less than or equal to 1 characters")
    @Column(name = "EXT_CD_METHOD")
    private String extCdMethod;
    /**
     * <p>Maps to table column: {@code EXT_DIVISION_CD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 3, message = "EXT_DIVISION_CD should contain less than or equal to 3 characters")
    @Column(name = "EXT_DIVISION_CD")
    private String extDivisionCd;
    /**
     * <p>Maps to table column: {@code EXT_FILESEQ_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 7, fraction = 0, message = "EXT_FILESEQ_NO should have an integer component no longer than 7 and an a fraction component of exact size 0")
    @Column(name = "EXT_FILESEQ_NO")
    private Integer extFileseqNo;
    /**
     * <p>Maps to table column: {@code EXT_VENDOR_NUM}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 30, message = "EXT_VENDOR_NUM should contain less than or equal to 30 characters")
    @Column(name = "EXT_VENDOR_NUM")
    private String extVendorNum;
    /**
     * <p>Maps to table column: {@code EXT_VENDOR_SITE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 15, message = "EXT_VENDOR_SITE should contain less than or equal to 15 characters")
    @Column(name = "EXT_VENDOR_SITE")
    private String extVendorSite;
    /**
     * <p>Maps to table column: {@code CONSOL_FREIGHT_VENDOR_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 15, message = "CONSOL_FREIGHT_VENDOR_ID should contain less than or equal to 15 characters")
    @Column(name = "CONSOL_FREIGHT_VENDOR_ID")
    private String consolFreightVendorId;
    /**
     * <p>Maps to table column: {@code EXTRA_REPLEN_DAYS_ONE_TIME}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "EXTRA_REPLEN_DAYS_ONE_TIME should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "EXTRA_REPLEN_DAYS_ONE_TIME")
    private Integer extraReplenDaysOneTime;
    /**
     * <p>Maps to table column: {@code FREIGHT_MIN_STRATEGY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "FREIGHT_MIN_STRATEGY should contain less than or equal to 1 characters")
    @Column(name = "FREIGHT_MIN_STRATEGY")
    private String freightMinStrategy;
    /**
     * <p>Maps to table column: {@code FREIGHT_MIN_LINE_ITEMS}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 0, message = "FREIGHT_MIN_LINE_ITEMS should have an integer component no longer than 6 and an a fraction component of exact size 0")
    @Column(name = "FREIGHT_MIN_LINE_ITEMS")
    private Integer freightMinLineItems;
    /**
     * <p>Maps to table column: {@code FREIGHT_MIN_UNITS}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 0, message = "FREIGHT_MIN_UNITS should have an integer component no longer than 10 and an a fraction component of exact size 0")
    @Column(name = "FREIGHT_MIN_UNITS")
    private Long freightMinUnits;
    /**
     * <p>Maps to table column: {@code FREIGHT_MIN_CASES}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 0, message = "FREIGHT_MIN_CASES should have an integer component no longer than 10 and an a fraction component of exact size 0")
    @Column(name = "FREIGHT_MIN_CASES")
    private Long freightMinCases;
    /**
     * <p>Maps to table column: {@code FREIGHT_MIN_AMT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "FREIGHT_MIN_AMT should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "FREIGHT_MIN_AMT")
    private BigDecimal freightMinAmt;
    /**
     * <p>Maps to table column: {@code FREIGHT_MIN_WEIGHT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "FREIGHT_MIN_WEIGHT should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "FREIGHT_MIN_WEIGHT")
    private BigDecimal freightMinWeight;
    /**
     * <p>Maps to table column: {@code FREIGHT_MIN_CUBE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "FREIGHT_MIN_CUBE should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "FREIGHT_MIN_CUBE")
    private BigDecimal freightMinCube;
    /**
     * <p>Maps to table column: {@code FREIGHT_TOLERANCE_PCT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 2, message = "FREIGHT_TOLERANCE_PCT should have an integer component no longer than 3 and an a fraction component of exact size 2")
    @Column(name = "FREIGHT_TOLERANCE_PCT")
    private BigDecimal freightTolerancePct;
    /**
     * <p>Maps to table column: {@code MANDATORY_PURCH_MULT_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "MANDATORY_PURCH_MULT_FL should contain less than or equal to 1 characters")
    @Column(name = "MANDATORY_PURCH_MULT_FL")
    private String mandatoryPurchMultFl;
    /**
     * <p>Maps to table column: {@code NEXT_REPLEN_DATE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "NEXT_REPLEN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextReplenDate;
    /**
     * <p>Maps to table column: {@code ORDER_LIMIT_AMT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "ORDER_LIMIT_AMT should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "ORDER_LIMIT_AMT")
    private BigDecimal orderLimitAmt;
    /**
     * <p>Maps to table column: {@code REPLEN_INTERVAL_DAYS}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "REPLEN_INTERVAL_DAYS should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "REPLEN_INTERVAL_DAYS")
    private Integer replenIntervalDays;
    /**
     * <p>Maps to table column: {@code SEND_EDI_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "SEND_EDI_FL should contain less than or equal to 1 characters")
    @Column(name = "SEND_EDI_FL")
    private String sendEdiFl;
    /**
     * <p>Maps to table column: {@code STD_REPLEN_INTERVAL_DAYS}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "STD_REPLEN_INTERVAL_DAYS should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "STD_REPLEN_INTERVAL_DAYS")
    private Integer stdReplenIntervalDays;
    /**
     * <p>Maps to table column: {@code SITE_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "SITE_NO should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "SITE_NO")
    private Integer siteNo;
    /**
     * <p>Maps to table column: {@code INTERNAL_VENDOR_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "INTERNAL_VENDOR_FL should contain less than or equal to 1 characters")
    @Column(name = "INTERNAL_VENDOR_FL")
    private String internalVendorFl;
    /**
     * <p>Maps to table column: {@code AUTO_GENERATE_UPC_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "AUTO_GENERATE_UPC_FL should contain less than or equal to 1 characters")
    @Column(name = "AUTO_GENERATE_UPC_FL")
    private String autoGenerateUpcFl;
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
     * <p>Maps to table column: {@code FREIGHT_TOLERANCE_PCT_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "FREIGHT_TOLERANCE_PCT_FL should contain less than or equal to 1 characters")
    @Column(name = "FREIGHT_TOLERANCE_PCT_FL")
    private String freightTolerancePctFl;
    /**
     * <p>Maps to table column: {@code RECON_METHOD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "RECON_METHOD should contain less than or equal to 1 characters")
    @Column(name = "RECON_METHOD")
    private String reconMethod;
    /**
     * <p>Maps to table column: {@code COST_INCLUDES_FREIGHT_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "COST_INCLUDES_FREIGHT_FL should contain less than or equal to 1 characters")
    @Column(name = "COST_INCLUDES_FREIGHT_FL")
    private String costIncludesFreightFl;
    /**
     * <p>Maps to table column: {@code PRINT_FREIGHT_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "PRINT_FREIGHT_FL should contain less than or equal to 1 characters")
    @Column(name = "PRINT_FREIGHT_FL")
    private String printFreightFl;
    /**
     * <p>Maps to table column: {@code LEAD_TIME}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "LEAD_TIME should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "LEAD_TIME")
    private Integer leadTime;
    /**
     * <p>Maps to table column: {@code DSD_RCV_TYPE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "DSD_RCV_TYPE should contain less than or equal to 1 characters")
    @Column(name = "DSD_RCV_TYPE")
    private String dsdRcvType;
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
     * <p>Maps to table column: {@code DATE_CHANGED}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "DATE_CHANGED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateChanged;
    /**
     * <p>Maps to table column: {@code REGISTER_REPLICATION_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 16, fraction = 0, message = "REGISTER_REPLICATION_NO should have an integer component no longer than 16 and an a fraction component of exact size 0")
    @Column(name = "REGISTER_REPLICATION_NO")
    private Long registerReplicationNo;
    /**
     * <p>Maps to table column: {@code EXTERNAL_REF_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 240, message = "EXTERNAL_REF_ID should contain less than or equal to 240 characters")
    @Column(name = "EXTERNAL_REF_ID")
    private String externalRefId;
    /**
     * <p>Maps to table column: {@code MIN_PO_AMT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "MIN_PO_AMT should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "MIN_PO_AMT")
    private BigDecimal minPoAmt;
    /**
     * <p>Maps to table column: {@code VENDOR_TYPE_CD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "VENDOR_TYPE_CD should contain less than or equal to 1 characters")
    @Column(name = "VENDOR_TYPE_CD")
    private String vendorTypeCd;
    /**
     * <p>Maps to table column: {@code ORDER_ACCOUNT_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 25, message = "ORDER_ACCOUNT_ID should contain less than or equal to 25 characters")
    @Column(name = "ORDER_ACCOUNT_ID")
    private String orderAccountId;
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
     * <p>Maps to table column: {@code EMPLOYEE_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 12, message = "EMPLOYEE_ID should contain less than or equal to 12 characters")
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;
    /**
     * <p>Maps to table column: {@code TRADE_CURRENCY_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 3, message = "TRADE_CURRENCY_ID should contain less than or equal to 3 characters")
    @Column(name = "TRADE_CURRENCY_ID")
    private String tradeCurrencyId;
    /**
     * <p>Maps to table column: {@code BACKORDERS_ALLOWED_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "BACKORDERS_ALLOWED_FL should contain less than or equal to 1 characters")
    @Column(name = "BACKORDERS_ALLOWED_FL")
    private String backordersAllowedFl;
    /**
     * <p>Maps to table column: {@code LIMIT_RCVDOC_PER_INVOICE_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "LIMIT_RCVDOC_PER_INVOICE_FL should contain less than or equal to 1 characters")
    @Column(name = "LIMIT_RCVDOC_PER_INVOICE_FL")
    private String limitRcvdocPerInvoiceFl;
    /**
     * <p>Maps to table column: {@code CANCEL_LEAD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "CANCEL_LEAD should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "CANCEL_LEAD")
    private Integer cancelLead;
    /**
     * <p>Maps to table column: {@code EXPECT_LEAD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "EXPECT_LEAD should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "EXPECT_LEAD")
    private Integer expectLead;
    /**
     * <p>Maps to table column: {@code PREPAID_FREIGHT_AMT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PREPAID_FREIGHT_AMT should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PREPAID_FREIGHT_AMT")
    private BigDecimal prepaidFreightAmt;
    /**
     * <p>Maps to table column: {@code PREPAID_FREIGHT_WEIGHT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PREPAID_FREIGHT_WEIGHT should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PREPAID_FREIGHT_WEIGHT")
    private BigDecimal prepaidFreightWeight;
    /**
     * <p>Maps to table column: {@code PREPAID_FREIGHT_CUBE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PREPAID_FREIGHT_CUBE should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PREPAID_FREIGHT_CUBE")
    private BigDecimal prepaidFreightCube;
    /**
     * <p>Maps to table column: {@code MIN_PACK_WEIGHT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "MIN_PACK_WEIGHT should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "MIN_PACK_WEIGHT")
    private BigDecimal minPackWeight;
    /**
     * <p>Maps to table column: {@code MIN_PACK_CUBE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "MIN_PACK_CUBE should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "MIN_PACK_CUBE")
    private BigDecimal minPackCube;
    /**
     * <p>Maps to table column: {@code VENDOR_DISPOSITION}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 256, message = "VENDOR_DISPOSITION should contain less than or equal to 256 characters")
    @Column(name = "VENDOR_DISPOSITION")
    private String vendorDisposition;
    /**
     * <p>Maps to table column: {@code MIN_PO_CASE_QTY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "MIN_PO_CASE_QTY should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "MIN_PO_CASE_QTY")
    private BigDecimal minPoCaseQty;
    /**
     * <p>Maps to table column: {@code TERM_CODE}.</p><p><b>Schema Remarks:</b> FK to the PAYMENT_TERM_CODE table.</p>
     * 
     */
    @Size(max = 20, message = "TERM_CODE should contain less than or equal to 20 characters")
    @Column(name = "TERM_CODE")
    private String termCode;
    /**
     * <p>Maps to table column: {@code DUE_DT}.</p><p><b>Schema Remarks:</b> Payment terms due date.</p>
     * 
     */
    @Column(name = "DUE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDt;
    /**
     * <p>Maps to table column: {@code COST_REQUIRED_FL}.</p><p><b>Schema Remarks:</b> This flag indicates if entry of cost is required during input</p>
     * 
     */
    @Size(max = 1, message = "COST_REQUIRED_FL should contain less than or equal to 1 characters")
    @Column(name = "COST_REQUIRED_FL")
    private String costRequiredFl;
    /**
     * <p>Maps to table column: {@code RTV_ALLOWED_FL}.</p><p><b>Schema Remarks:</b> If set to Y this flag indicates that this vendor allows RTVs.</p>
     * 
     */
    @Size(max = 1, message = "RTV_ALLOWED_FL should contain less than or equal to 1 characters")
    @Column(name = "RTV_ALLOWED_FL")
    private String rtvAllowedFl;
    /**
     * <p>Maps to table column: {@code ORDER_INSTRUCTIONS}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 2000, message = "ORDER_INSTRUCTIONS should contain less than or equal to 2000 characters")
    @Column(name = "ORDER_INSTRUCTIONS")
    private String orderInstructions;
    /**
     * <p>Maps to table column: {@code LANDED_COST_ELEMENTS}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 500, message = "LANDED_COST_ELEMENTS should contain less than or equal to 500 characters")
    @Column(name = "LANDED_COST_ELEMENTS")
    private String landedCostElements;
    /**
     * <p>Maps to table column: {@code CURRENCY_CD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 12, message = "CURRENCY_CD should contain less than or equal to 12 characters")
    @Column(name = "CURRENCY_CD")
    private String currencyCd;
    /**
     * <p>Maps to table column: {@code OWNER_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 0, message = "OWNER_ID should have an integer component no longer than 8 and an a fraction component of exact size 0")
    @Column(name = "OWNER_ID")
    private Integer ownerId;
    /**
     * <p>Maps to table column: {@code LOCK_LEAD_TIME_FL}.</p><p><b>Schema Remarks:</b> Indicates if the lead time calculation can be changed</p>
     * 
     */
    @Size(max = 1, message = "LOCK_LEAD_TIME_FL should contain less than or equal to 1 characters")
    @Column(name = "LOCK_LEAD_TIME_FL")
    private String lockLeadTimeFl;
    /**
     * <p>Maps to table column: {@code DATE_LAST_CALCULATED}.</p><p><b>Schema Remarks:</b> Date last lead time was calculated</p>
     * 
     */
    @Column(name = "DATE_LAST_CALCULATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastCalculated;
    /**
     * <p>Static value for named query 'VENDOR_BY_ID'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorEntity v Where v.vendorId IN (:vendorId) }
     * 
     */
    public final static String VENDOR_BY_ID = "VENDOR_BY_ID";
    /**
     * <p>Static value for named query 'VENDORS_BY_ITEM'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorEntity v, VenSiteEntity vs, VendorItemEntity vi Where v.vendorId = vi.vendorId and vi.siteNo = :siteNo and vi.skuNo = :skuNo and v.vendorId = vs.vendorId and vs.siteNo = vi.siteNo order by v.vendorId }
     * 
     */
    public final static String VENDORS_BY_ITEM = "VENDORS_BY_ITEM";
    /**
     * <p>Static value for named query 'VENDORS_BY_ITEM_ACTIVE'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VendorEntity v, VenSiteEntity vs, VendorItemEntity vi Where v.vendorId = vi.vendorId and vi.siteNo =:siteNo and vi.skuNo = :skuNo and v.vendorId = vs.vendorId and vs.siteNo = vi.siteNo and vi.orderAvailabilityStatus ='ACT' order by v.vendorId }
     * 
     */
    public final static String VENDORS_BY_ITEM_ACTIVE = "VENDORS_BY_ITEM_ACTIVE";
    /**
     * <p>Static value for named query 'VENDOR_NAME_BY_ID'<p>
     * <p>JPA Query:
     * </p>{@code Select v.name from VendorEntity v Where v.vendorId = :vendorId }
     * 
     */
    public final static String VENDOR_NAME_BY_ID = "VENDOR_NAME_BY_ID";

    /**
     * <P>Empty constructor used to create new VendorEntity.</P>
     * 
     */
    public VendorEntity() {
        this.identifier = new VendorEntityId();
        	
        _init();
    }

    /**
     * Constructor used to create a new VendorEntity where the identifier will be created by copying the fields of the provided sourceId.
     * 
     */
    public VendorEntity(VendorEntityId sourceId) {
        this.identifier = new VendorEntityId(sourceId.getVendorId());
        	
        _init();
    }

    /**
     * Constructor used to create a VendorEntity where the identifier will be created internally using the id arguments provided.  This is a convenient constructor used instead of instantiating a new identifier.
     * 
     */
    public VendorEntity(String vendorId) {
        this.identifier = new VendorEntityId(vendorId);
        	
        _init();
    }

    /**
     * Constructor used to create a VendorEntity where contents are provided by an object array typically returned by a native query
     * 
     */
    public VendorEntity(Object[] objectArray) {
        this.identifier = new VendorEntityId(((objectArray[ 0 ] == null)?null:((String) objectArray[ 0 ])));
        	
        this.name = ((objectArray[ 1 ] == null)?null:((String) objectArray[ 1 ]));
        this.adr1 = ((objectArray[ 2 ] == null)?null:((String) objectArray[ 2 ]));
        this.adr2 = ((objectArray[ 3 ] == null)?null:((String) objectArray[ 3 ]));
        this.city = ((objectArray[ 4 ] == null)?null:((String) objectArray[ 4 ]));
        this.state = ((objectArray[ 5 ] == null)?null:((String) objectArray[ 5 ]));
        this.zip = ((objectArray[ 6 ] == null)?null:((String) objectArray[ 6 ]));
        this.country = ((objectArray[ 7 ] == null)?null:((String) objectArray[ 7 ]));
        this.contact = ((objectArray[ 8 ] == null)?null:((String) objectArray[ 8 ]));
        this.contact2 = ((objectArray[ 9 ] == null)?null:((String) objectArray[ 9 ]));
        this.phoneNo = ((objectArray[ 10 ] == null)?null:((String) objectArray[ 10 ]));
        this.phone2No = ((objectArray[ 11 ] == null)?null:((String) objectArray[ 11 ]));
        this.faxNo = ((objectArray[ 12 ] == null)?null:((String) objectArray[ 12 ]));
        this.emailAddress = ((objectArray[ 13 ] == null)?null:((String) objectArray[ 13 ]));
        this.apAdr1 = ((objectArray[ 14 ] == null)?null:((String) objectArray[ 14 ]));
        this.apAdr2 = ((objectArray[ 15 ] == null)?null:((String) objectArray[ 15 ]));
        this.apCity = ((objectArray[ 16 ] == null)?null:((String) objectArray[ 16 ]));
        this.apState = ((objectArray[ 17 ] == null)?null:((String) objectArray[ 17 ]));
        this.apZip = ((objectArray[ 18 ] == null)?null:((String) objectArray[ 18 ]));
        this.apCountry = ((objectArray[ 19 ] == null)?null:((String) objectArray[ 19 ]));
        this.apFlowFl = ((objectArray[ 20 ] == null)?null:((String) objectArray[ 20 ]));
        this.apNumber = ((objectArray[ 21 ] == null)?null:((String) objectArray[ 21 ]));
        this.paymentTermPct1 = ((objectArray[ 22 ] == null)?null:((BigDecimal) objectArray[ 22 ]));
        this.paymentTermPct2 = ((objectArray[ 23 ] == null)?null:((BigDecimal) objectArray[ 23 ]));
        this.paymentTermDays1 = ((objectArray[ 24 ] == null)?null:((BigDecimal) objectArray[ 24 ]).intValue());
        this.paymentTermDays2 = ((objectArray[ 25 ] == null)?null:((BigDecimal) objectArray[ 25 ]).intValue());
        this.payStatusFl = ((objectArray[ 26 ] == null)?null:((String) objectArray[ 26 ]));
        this.netDays = ((objectArray[ 27 ] == null)?null:((BigDecimal) objectArray[ 27 ]).intValue());
        this.orderStatusFl = ((objectArray[ 28 ] == null)?null:((String) objectArray[ 28 ]));
        this.lastInvoiceDt = ((objectArray[ 29 ] == null)?null:new Date(((Timestamp) objectArray[ 29 ]).getTime()));
        this.lastPaymentDt = ((objectArray[ 30 ] == null)?null:new Date(((Timestamp) objectArray[ 30 ]).getTime()));
        this.shipVia = ((objectArray[ 31 ] == null)?null:((String) objectArray[ 31 ]));
        this.shipTerms = ((objectArray[ 32 ] == null)?null:((String) objectArray[ 32 ]));
        this.fob = ((objectArray[ 33 ] == null)?null:((String) objectArray[ 33 ]));
        this.commentTxt = ((objectArray[ 34 ] == null)?null:((String) objectArray[ 34 ]));
        this.shipInstTxt = ((objectArray[ 35 ] == null)?null:((String) objectArray[ 35 ]));
        this.ediAgent = ((objectArray[ 36 ] == null)?null:((String) objectArray[ 36 ]));
        this.ediType = ((objectArray[ 37 ] == null)?null:((String) objectArray[ 37 ]));
        this.calcNetCostFl = ((objectArray[ 38 ] == null)?null:((String) objectArray[ 38 ]));
        this.primaryControlFl = ((objectArray[ 39 ] == null)?null:((String) objectArray[ 39 ]));
        this.primaryControlNToYFl = ((objectArray[ 40 ] == null)?null:((String) objectArray[ 40 ]));
        this.dunsNo = ((objectArray[ 41 ] == null)?null:((BigDecimal) objectArray[ 41 ]).intValue());
        this.commId = ((objectArray[ 42 ] == null)?null:((BigDecimal) objectArray[ 42 ]).longValue());
        this.extCdMethod = ((objectArray[ 43 ] == null)?null:((String) objectArray[ 43 ]));
        this.extDivisionCd = ((objectArray[ 44 ] == null)?null:((String) objectArray[ 44 ]));
        this.extFileseqNo = ((objectArray[ 45 ] == null)?null:((BigDecimal) objectArray[ 45 ]).intValue());
        this.extVendorNum = ((objectArray[ 46 ] == null)?null:((String) objectArray[ 46 ]));
        this.extVendorSite = ((objectArray[ 47 ] == null)?null:((String) objectArray[ 47 ]));
        this.consolFreightVendorId = ((objectArray[ 48 ] == null)?null:((String) objectArray[ 48 ]));
        this.extraReplenDaysOneTime = ((objectArray[ 49 ] == null)?null:((BigDecimal) objectArray[ 49 ]).intValue());
        this.freightMinStrategy = ((objectArray[ 50 ] == null)?null:((String) objectArray[ 50 ]));
        this.freightMinLineItems = ((objectArray[ 51 ] == null)?null:((BigDecimal) objectArray[ 51 ]).intValue());
        this.freightMinUnits = ((objectArray[ 52 ] == null)?null:((BigDecimal) objectArray[ 52 ]).longValue());
        this.freightMinCases = ((objectArray[ 53 ] == null)?null:((BigDecimal) objectArray[ 53 ]).longValue());
        this.freightMinAmt = ((objectArray[ 54 ] == null)?null:((BigDecimal) objectArray[ 54 ]));
        this.freightMinWeight = ((objectArray[ 55 ] == null)?null:((BigDecimal) objectArray[ 55 ]));
        this.freightMinCube = ((objectArray[ 56 ] == null)?null:((BigDecimal) objectArray[ 56 ]));
        this.freightTolerancePct = ((objectArray[ 57 ] == null)?null:((BigDecimal) objectArray[ 57 ]));
        this.mandatoryPurchMultFl = ((objectArray[ 58 ] == null)?null:((String) objectArray[ 58 ]));
        this.nextReplenDate = ((objectArray[ 59 ] == null)?null:new Date(((Timestamp) objectArray[ 59 ]).getTime()));
        this.orderLimitAmt = ((objectArray[ 60 ] == null)?null:((BigDecimal) objectArray[ 60 ]));
        this.replenIntervalDays = ((objectArray[ 61 ] == null)?null:((BigDecimal) objectArray[ 61 ]).intValue());
        this.sendEdiFl = ((objectArray[ 62 ] == null)?null:((String) objectArray[ 62 ]));
        this.stdReplenIntervalDays = ((objectArray[ 63 ] == null)?null:((BigDecimal) objectArray[ 63 ]).intValue());
        this.siteNo = ((objectArray[ 64 ] == null)?null:((BigDecimal) objectArray[ 64 ]).intValue());
        this.internalVendorFl = ((objectArray[ 65 ] == null)?null:((String) objectArray[ 65 ]));
        this.autoGenerateUpcFl = ((objectArray[ 66 ] == null)?null:((String) objectArray[ 66 ]));
        this.invToleranceQty = ((objectArray[ 67 ] == null)?null:((BigDecimal) objectArray[ 67 ]));
        this.invToleranceQtyFl = ((objectArray[ 68 ] == null)?null:((String) objectArray[ 68 ]));
        this.invTolerancePct = ((objectArray[ 69 ] == null)?null:((BigDecimal) objectArray[ 69 ]));
        this.invTolerancePctFl = ((objectArray[ 70 ] == null)?null:((String) objectArray[ 70 ]));
        this.invToleranceAmt = ((objectArray[ 71 ] == null)?null:((BigDecimal) objectArray[ 71 ]));
        this.invToleranceAmtFl = ((objectArray[ 72 ] == null)?null:((String) objectArray[ 72 ]));
        this.freightToleranceAmt = ((objectArray[ 73 ] == null)?null:((BigDecimal) objectArray[ 73 ]));
        this.freightToleranceAmtFl = ((objectArray[ 74 ] == null)?null:((String) objectArray[ 74 ]));
        this.freightTolerancePctFl = ((objectArray[ 75 ] == null)?null:((String) objectArray[ 75 ]));
        this.reconMethod = ((objectArray[ 76 ] == null)?null:((String) objectArray[ 76 ]));
        this.costIncludesFreightFl = ((objectArray[ 77 ] == null)?null:((String) objectArray[ 77 ]));
        this.printFreightFl = ((objectArray[ 78 ] == null)?null:((String) objectArray[ 78 ]));
        this.leadTime = ((objectArray[ 79 ] == null)?null:((BigDecimal) objectArray[ 79 ]).intValue());
        this.dsdRcvType = ((objectArray[ 80 ] == null)?null:((String) objectArray[ 80 ]));
        this.replicationNo = ((objectArray[ 81 ] == null)?null:((BigDecimal) objectArray[ 81 ]).longValue());
        this.operationType = ((objectArray[ 82 ] == null)?null:((String) objectArray[ 82 ]));
        this.dateChanged = ((objectArray[ 83 ] == null)?null:new Date(((Timestamp) objectArray[ 83 ]).getTime()));
        this.registerReplicationNo = ((objectArray[ 84 ] == null)?null:((BigDecimal) objectArray[ 84 ]).longValue());
        this.externalRefId = ((objectArray[ 85 ] == null)?null:((String) objectArray[ 85 ]));
        this.minPoAmt = ((objectArray[ 86 ] == null)?null:((BigDecimal) objectArray[ 86 ]));
        this.vendorTypeCd = ((objectArray[ 87 ] == null)?null:((String) objectArray[ 87 ]));
        this.orderAccountId = ((objectArray[ 88 ] == null)?null:((String) objectArray[ 88 ]));
        this.dateCreated = ((objectArray[ 89 ] == null)?null:new Date(((Timestamp) objectArray[ 89 ]).getTime()));
        this.userCreated = ((objectArray[ 90 ] == null)?null:((String) objectArray[ 90 ]));
        this.dateModified = ((objectArray[ 91 ] == null)?null:new Date(((Timestamp) objectArray[ 91 ]).getTime()));
        this.userModified = ((objectArray[ 92 ] == null)?null:((String) objectArray[ 92 ]));
        this.employeeId = ((objectArray[ 93 ] == null)?null:((String) objectArray[ 93 ]));
        this.tradeCurrencyId = ((objectArray[ 94 ] == null)?null:((String) objectArray[ 94 ]));
        this.backordersAllowedFl = ((objectArray[ 95 ] == null)?null:((String) objectArray[ 95 ]));
        this.limitRcvdocPerInvoiceFl = ((objectArray[ 96 ] == null)?null:((String) objectArray[ 96 ]));
        this.cancelLead = ((objectArray[ 97 ] == null)?null:((BigDecimal) objectArray[ 97 ]).intValue());
        this.expectLead = ((objectArray[ 98 ] == null)?null:((BigDecimal) objectArray[ 98 ]).intValue());
        this.prepaidFreightAmt = ((objectArray[ 99 ] == null)?null:((BigDecimal) objectArray[ 99 ]));
        this.prepaidFreightWeight = ((objectArray[ 100 ] == null)?null:((BigDecimal) objectArray[ 100 ]));
        this.prepaidFreightCube = ((objectArray[ 101 ] == null)?null:((BigDecimal) objectArray[ 101 ]));
        this.minPackWeight = ((objectArray[ 102 ] == null)?null:((BigDecimal) objectArray[ 102 ]));
        this.minPackCube = ((objectArray[ 103 ] == null)?null:((BigDecimal) objectArray[ 103 ]));
        this.vendorDisposition = ((objectArray[ 104 ] == null)?null:((String) objectArray[ 104 ]));
        this.minPoCaseQty = ((objectArray[ 105 ] == null)?null:((BigDecimal) objectArray[ 105 ]));
        this.termCode = ((objectArray[ 106 ] == null)?null:((String) objectArray[ 106 ]));
        this.dueDt = ((objectArray[ 107 ] == null)?null:new Date(((Timestamp) objectArray[ 107 ]).getTime()));
        this.costRequiredFl = ((objectArray[ 108 ] == null)?null:((String) objectArray[ 108 ]));
        this.rtvAllowedFl = ((objectArray[ 109 ] == null)?null:((String) objectArray[ 109 ]));
        this.orderInstructions = ((objectArray[ 110 ] == null)?null:((String) objectArray[ 110 ]));
        this.landedCostElements = ((objectArray[ 111 ] == null)?null:((String) objectArray[ 111 ]));
        this.currencyCd = ((objectArray[ 112 ] == null)?null:((String) objectArray[ 112 ]));
        this.ownerId = ((objectArray[ 113 ] == null)?null:((BigDecimal) objectArray[ 113 ]).intValue());
        this.lockLeadTimeFl = ((objectArray[ 114 ] == null)?null:((String) objectArray[ 114 ]));
        this.dateLastCalculated = ((objectArray[ 115 ] == null)?null:new Date(((Timestamp) objectArray[ 115 ]).getTime()));
        	
        _init();
    }

    /**
     * <P>Copy constructor used to copy a VendorEntity where the identifier will be created by copying the fields of the provided sourceId.</P>
     * 
     */
    public VendorEntity(VendorEntity source, VendorEntityId sourceId) {
        this.identifier = new VendorEntityId(sourceId.getVendorId());
        	
        _init();
        	
        this.name = source.getName();
        this.adr1 = source.getAdr1();
        this.adr2 = source.getAdr2();
        this.city = source.getCity();
        this.state = source.getState();
        this.zip = source.getZip();
        this.country = source.getCountry();
        this.contact = source.getContact();
        this.contact2 = source.getContact2();
        this.phoneNo = source.getPhoneNo();
        this.phone2No = source.getPhone2No();
        this.faxNo = source.getFaxNo();
        this.emailAddress = source.getEmailAddress();
        this.apAdr1 = source.getApAdr1();
        this.apAdr2 = source.getApAdr2();
        this.apCity = source.getApCity();
        this.apState = source.getApState();
        this.apZip = source.getApZip();
        this.apCountry = source.getApCountry();
        this.apFlowFl = source.getApFlowFl();
        this.apNumber = source.getApNumber();
        this.paymentTermPct1 = source.getPaymentTermPct1();
        this.paymentTermPct2 = source.getPaymentTermPct2();
        this.paymentTermDays1 = source.getPaymentTermDays1();
        this.paymentTermDays2 = source.getPaymentTermDays2();
        this.payStatusFl = source.getPayStatusFl();
        this.netDays = source.getNetDays();
        this.orderStatusFl = source.getOrderStatusFl();
        this.lastInvoiceDt = source.getLastInvoiceDt();
        this.lastPaymentDt = source.getLastPaymentDt();
        this.shipVia = source.getShipVia();
        this.shipTerms = source.getShipTerms();
        this.fob = source.getFob();
        this.commentTxt = source.getCommentTxt();
        this.shipInstTxt = source.getShipInstTxt();
        this.ediAgent = source.getEdiAgent();
        this.ediType = source.getEdiType();
        this.calcNetCostFl = source.getCalcNetCostFl();
        this.primaryControlFl = source.getPrimaryControlFl();
        this.primaryControlNToYFl = source.getPrimaryControlNToYFl();
        this.dunsNo = source.getDunsNo();
        this.commId = source.getCommId();
        this.extCdMethod = source.getExtCdMethod();
        this.extDivisionCd = source.getExtDivisionCd();
        this.extFileseqNo = source.getExtFileseqNo();
        this.extVendorNum = source.getExtVendorNum();
        this.extVendorSite = source.getExtVendorSite();
        this.consolFreightVendorId = source.getConsolFreightVendorId();
        this.extraReplenDaysOneTime = source.getExtraReplenDaysOneTime();
        this.freightMinStrategy = source.getFreightMinStrategy();
        this.freightMinLineItems = source.getFreightMinLineItems();
        this.freightMinUnits = source.getFreightMinUnits();
        this.freightMinCases = source.getFreightMinCases();
        this.freightMinAmt = source.getFreightMinAmt();
        this.freightMinWeight = source.getFreightMinWeight();
        this.freightMinCube = source.getFreightMinCube();
        this.freightTolerancePct = source.getFreightTolerancePct();
        this.mandatoryPurchMultFl = source.getMandatoryPurchMultFl();
        this.nextReplenDate = source.getNextReplenDate();
        this.orderLimitAmt = source.getOrderLimitAmt();
        this.replenIntervalDays = source.getReplenIntervalDays();
        this.sendEdiFl = source.getSendEdiFl();
        this.stdReplenIntervalDays = source.getStdReplenIntervalDays();
        this.siteNo = source.getSiteNo();
        this.internalVendorFl = source.getInternalVendorFl();
        this.autoGenerateUpcFl = source.getAutoGenerateUpcFl();
        this.invToleranceQty = source.getInvToleranceQty();
        this.invToleranceQtyFl = source.getInvToleranceQtyFl();
        this.invTolerancePct = source.getInvTolerancePct();
        this.invTolerancePctFl = source.getInvTolerancePctFl();
        this.invToleranceAmt = source.getInvToleranceAmt();
        this.invToleranceAmtFl = source.getInvToleranceAmtFl();
        this.freightToleranceAmt = source.getFreightToleranceAmt();
        this.freightToleranceAmtFl = source.getFreightToleranceAmtFl();
        this.freightTolerancePctFl = source.getFreightTolerancePctFl();
        this.reconMethod = source.getReconMethod();
        this.costIncludesFreightFl = source.getCostIncludesFreightFl();
        this.printFreightFl = source.getPrintFreightFl();
        this.leadTime = source.getLeadTime();
        this.dsdRcvType = source.getDsdRcvType();
        this.replicationNo = source.getReplicationNo();
        this.operationType = source.getOperationType();
        this.dateChanged = source.getDateChanged();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.externalRefId = source.getExternalRefId();
        this.minPoAmt = source.getMinPoAmt();
        this.vendorTypeCd = source.getVendorTypeCd();
        this.orderAccountId = source.getOrderAccountId();
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.employeeId = source.getEmployeeId();
        this.tradeCurrencyId = source.getTradeCurrencyId();
        this.backordersAllowedFl = source.getBackordersAllowedFl();
        this.limitRcvdocPerInvoiceFl = source.getLimitRcvdocPerInvoiceFl();
        this.cancelLead = source.getCancelLead();
        this.expectLead = source.getExpectLead();
        this.prepaidFreightAmt = source.getPrepaidFreightAmt();
        this.prepaidFreightWeight = source.getPrepaidFreightWeight();
        this.prepaidFreightCube = source.getPrepaidFreightCube();
        this.minPackWeight = source.getMinPackWeight();
        this.minPackCube = source.getMinPackCube();
        this.vendorDisposition = source.getVendorDisposition();
        this.minPoCaseQty = source.getMinPoCaseQty();
        this.termCode = source.getTermCode();
        this.dueDt = source.getDueDt();
        this.costRequiredFl = source.getCostRequiredFl();
        this.rtvAllowedFl = source.getRtvAllowedFl();
        this.orderInstructions = source.getOrderInstructions();
        this.landedCostElements = source.getLandedCostElements();
        this.currencyCd = source.getCurrencyCd();
        this.ownerId = source.getOwnerId();
        this.lockLeadTimeFl = source.getLockLeadTimeFl();
        this.dateLastCalculated = source.getDateLastCalculated();
    }

    /**
     * <P>Copy constructor used to copy a VendorEntity including its identifier.</P>
     * 
     */
    public VendorEntity(VendorEntity source) {
        this.identifier = new VendorEntityId(source.getIdentifier().getVendorId());
        	
        _init();
        	
        this.name = source.getName();
        this.adr1 = source.getAdr1();
        this.adr2 = source.getAdr2();
        this.city = source.getCity();
        this.state = source.getState();
        this.zip = source.getZip();
        this.country = source.getCountry();
        this.contact = source.getContact();
        this.contact2 = source.getContact2();
        this.phoneNo = source.getPhoneNo();
        this.phone2No = source.getPhone2No();
        this.faxNo = source.getFaxNo();
        this.emailAddress = source.getEmailAddress();
        this.apAdr1 = source.getApAdr1();
        this.apAdr2 = source.getApAdr2();
        this.apCity = source.getApCity();
        this.apState = source.getApState();
        this.apZip = source.getApZip();
        this.apCountry = source.getApCountry();
        this.apFlowFl = source.getApFlowFl();
        this.apNumber = source.getApNumber();
        this.paymentTermPct1 = source.getPaymentTermPct1();
        this.paymentTermPct2 = source.getPaymentTermPct2();
        this.paymentTermDays1 = source.getPaymentTermDays1();
        this.paymentTermDays2 = source.getPaymentTermDays2();
        this.payStatusFl = source.getPayStatusFl();
        this.netDays = source.getNetDays();
        this.orderStatusFl = source.getOrderStatusFl();
        this.lastInvoiceDt = source.getLastInvoiceDt();
        this.lastPaymentDt = source.getLastPaymentDt();
        this.shipVia = source.getShipVia();
        this.shipTerms = source.getShipTerms();
        this.fob = source.getFob();
        this.commentTxt = source.getCommentTxt();
        this.shipInstTxt = source.getShipInstTxt();
        this.ediAgent = source.getEdiAgent();
        this.ediType = source.getEdiType();
        this.calcNetCostFl = source.getCalcNetCostFl();
        this.primaryControlFl = source.getPrimaryControlFl();
        this.primaryControlNToYFl = source.getPrimaryControlNToYFl();
        this.dunsNo = source.getDunsNo();
        this.commId = source.getCommId();
        this.extCdMethod = source.getExtCdMethod();
        this.extDivisionCd = source.getExtDivisionCd();
        this.extFileseqNo = source.getExtFileseqNo();
        this.extVendorNum = source.getExtVendorNum();
        this.extVendorSite = source.getExtVendorSite();
        this.consolFreightVendorId = source.getConsolFreightVendorId();
        this.extraReplenDaysOneTime = source.getExtraReplenDaysOneTime();
        this.freightMinStrategy = source.getFreightMinStrategy();
        this.freightMinLineItems = source.getFreightMinLineItems();
        this.freightMinUnits = source.getFreightMinUnits();
        this.freightMinCases = source.getFreightMinCases();
        this.freightMinAmt = source.getFreightMinAmt();
        this.freightMinWeight = source.getFreightMinWeight();
        this.freightMinCube = source.getFreightMinCube();
        this.freightTolerancePct = source.getFreightTolerancePct();
        this.mandatoryPurchMultFl = source.getMandatoryPurchMultFl();
        this.nextReplenDate = source.getNextReplenDate();
        this.orderLimitAmt = source.getOrderLimitAmt();
        this.replenIntervalDays = source.getReplenIntervalDays();
        this.sendEdiFl = source.getSendEdiFl();
        this.stdReplenIntervalDays = source.getStdReplenIntervalDays();
        this.siteNo = source.getSiteNo();
        this.internalVendorFl = source.getInternalVendorFl();
        this.autoGenerateUpcFl = source.getAutoGenerateUpcFl();
        this.invToleranceQty = source.getInvToleranceQty();
        this.invToleranceQtyFl = source.getInvToleranceQtyFl();
        this.invTolerancePct = source.getInvTolerancePct();
        this.invTolerancePctFl = source.getInvTolerancePctFl();
        this.invToleranceAmt = source.getInvToleranceAmt();
        this.invToleranceAmtFl = source.getInvToleranceAmtFl();
        this.freightToleranceAmt = source.getFreightToleranceAmt();
        this.freightToleranceAmtFl = source.getFreightToleranceAmtFl();
        this.freightTolerancePctFl = source.getFreightTolerancePctFl();
        this.reconMethod = source.getReconMethod();
        this.costIncludesFreightFl = source.getCostIncludesFreightFl();
        this.printFreightFl = source.getPrintFreightFl();
        this.leadTime = source.getLeadTime();
        this.dsdRcvType = source.getDsdRcvType();
        this.replicationNo = source.getReplicationNo();
        this.operationType = source.getOperationType();
        this.dateChanged = source.getDateChanged();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.externalRefId = source.getExternalRefId();
        this.minPoAmt = source.getMinPoAmt();
        this.vendorTypeCd = source.getVendorTypeCd();
        this.orderAccountId = source.getOrderAccountId();
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.employeeId = source.getEmployeeId();
        this.tradeCurrencyId = source.getTradeCurrencyId();
        this.backordersAllowedFl = source.getBackordersAllowedFl();
        this.limitRcvdocPerInvoiceFl = source.getLimitRcvdocPerInvoiceFl();
        this.cancelLead = source.getCancelLead();
        this.expectLead = source.getExpectLead();
        this.prepaidFreightAmt = source.getPrepaidFreightAmt();
        this.prepaidFreightWeight = source.getPrepaidFreightWeight();
        this.prepaidFreightCube = source.getPrepaidFreightCube();
        this.minPackWeight = source.getMinPackWeight();
        this.minPackCube = source.getMinPackCube();
        this.vendorDisposition = source.getVendorDisposition();
        this.minPoCaseQty = source.getMinPoCaseQty();
        this.termCode = source.getTermCode();
        this.dueDt = source.getDueDt();
        this.costRequiredFl = source.getCostRequiredFl();
        this.rtvAllowedFl = source.getRtvAllowedFl();
        this.orderInstructions = source.getOrderInstructions();
        this.landedCostElements = source.getLandedCostElements();
        this.currencyCd = source.getCurrencyCd();
        this.ownerId = source.getOwnerId();
        this.lockLeadTimeFl = source.getLockLeadTimeFl();
        this.dateLastCalculated = source.getDateLastCalculated();
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public VendorEntityId getIdentifier() {
        return this.identifier;
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public void setIdentifier(VendorEntityId identifier) {
        this.identifier = identifier;
    }

    /**
     * Getter for {@link VendorEntityId#vendorId identifier.vendorId}
     * 
     */
    @Access(AccessType.PROPERTY)
    @Column(name = "VENDOR_ID", insertable = false, updatable = false)
    public String getVendorId() {
        return this.identifier.getVendorId();
    }

    /**
     * Setter for {@link VendorEntityId#vendorId identifier.vendorId}
     * 
     */
    public String setVendorId(String vendorId) {
        this.identifier.setVendorId(vendorId);
        return this.identifier.getVendorId();
    }

    /**
     * Getter for {@link #name name}
     * 
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for {@link #name name}
     * 
     */
    public String setName(String name) {
        this.name = name;
        return this.name;
    }

    /**
     * Getter for {@link #adr1 adr1}
     * 
     */
    public String getAdr1() {
        return this.adr1;
    }

    /**
     * Setter for {@link #adr1 adr1}
     * 
     */
    public String setAdr1(String adr1) {
        this.adr1 = adr1;
        return this.adr1;
    }

    /**
     * Getter for {@link #adr2 adr2}
     * 
     */
    public String getAdr2() {
        return this.adr2;
    }

    /**
     * Setter for {@link #adr2 adr2}
     * 
     */
    public String setAdr2(String adr2) {
        this.adr2 = adr2;
        return this.adr2;
    }

    /**
     * Getter for {@link #city city}
     * 
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Setter for {@link #city city}
     * 
     */
    public String setCity(String city) {
        this.city = city;
        return this.city;
    }

    /**
     * Getter for {@link #state state}
     * 
     */
    public String getState() {
        return this.state;
    }

    /**
     * Setter for {@link #state state}
     * 
     */
    public String setState(String state) {
        this.state = state;
        return this.state;
    }

    /**
     * Getter for {@link #zip zip}
     * 
     */
    public String getZip() {
        return this.zip;
    }

    /**
     * Setter for {@link #zip zip}
     * 
     */
    public String setZip(String zip) {
        this.zip = zip;
        return this.zip;
    }

    /**
     * Getter for {@link #country country}
     * 
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Setter for {@link #country country}
     * 
     */
    public String setCountry(String country) {
        this.country = country;
        return this.country;
    }

    /**
     * Getter for {@link #contact contact}
     * 
     */
    public String getContact() {
        return this.contact;
    }

    /**
     * Setter for {@link #contact contact}
     * 
     */
    public String setContact(String contact) {
        this.contact = contact;
        return this.contact;
    }

    /**
     * Getter for {@link #contact2 contact2}
     * 
     */
    public String getContact2() {
        return this.contact2;
    }

    /**
     * Setter for {@link #contact2 contact2}
     * 
     */
    public String setContact2(String contact2) {
        this.contact2 = contact2;
        return this.contact2;
    }

    /**
     * Getter for {@link #phoneNo phoneNo}
     * 
     */
    public String getPhoneNo() {
        return this.phoneNo;
    }

    /**
     * Setter for {@link #phoneNo phoneNo}
     * 
     */
    public String setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
        return this.phoneNo;
    }

    /**
     * Getter for {@link #phone2No phone2No}
     * 
     */
    public String getPhone2No() {
        return this.phone2No;
    }

    /**
     * Setter for {@link #phone2No phone2No}
     * 
     */
    public String setPhone2No(String phone2No) {
        this.phone2No = phone2No;
        return this.phone2No;
    }

    /**
     * Getter for {@link #faxNo faxNo}
     * 
     */
    public String getFaxNo() {
        return this.faxNo;
    }

    /**
     * Setter for {@link #faxNo faxNo}
     * 
     */
    public String setFaxNo(String faxNo) {
        this.faxNo = faxNo;
        return this.faxNo;
    }

    /**
     * Getter for {@link #emailAddress emailAddress}
     * 
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }

    /**
     * Setter for {@link #emailAddress emailAddress}
     * 
     */
    public String setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this.emailAddress;
    }

    /**
     * Getter for {@link #apAdr1 apAdr1}
     * 
     */
    public String getApAdr1() {
        return this.apAdr1;
    }

    /**
     * Setter for {@link #apAdr1 apAdr1}
     * 
     */
    public String setApAdr1(String apAdr1) {
        this.apAdr1 = apAdr1;
        return this.apAdr1;
    }

    /**
     * Getter for {@link #apAdr2 apAdr2}
     * 
     */
    public String getApAdr2() {
        return this.apAdr2;
    }

    /**
     * Setter for {@link #apAdr2 apAdr2}
     * 
     */
    public String setApAdr2(String apAdr2) {
        this.apAdr2 = apAdr2;
        return this.apAdr2;
    }

    /**
     * Getter for {@link #apCity apCity}
     * 
     */
    public String getApCity() {
        return this.apCity;
    }

    /**
     * Setter for {@link #apCity apCity}
     * 
     */
    public String setApCity(String apCity) {
        this.apCity = apCity;
        return this.apCity;
    }

    /**
     * Getter for {@link #apState apState}
     * 
     */
    public String getApState() {
        return this.apState;
    }

    /**
     * Setter for {@link #apState apState}
     * 
     */
    public String setApState(String apState) {
        this.apState = apState;
        return this.apState;
    }

    /**
     * Getter for {@link #apZip apZip}
     * 
     */
    public String getApZip() {
        return this.apZip;
    }

    /**
     * Setter for {@link #apZip apZip}
     * 
     */
    public String setApZip(String apZip) {
        this.apZip = apZip;
        return this.apZip;
    }

    /**
     * Getter for {@link #apCountry apCountry}
     * 
     */
    public String getApCountry() {
        return this.apCountry;
    }

    /**
     * Setter for {@link #apCountry apCountry}
     * 
     */
    public String setApCountry(String apCountry) {
        this.apCountry = apCountry;
        return this.apCountry;
    }

    /**
     * Getter for {@link #apFlowFl apFlowFl}
     * 
     */
    public String getApFlowFl() {
        return this.apFlowFl;
    }

    /**
     * Setter for {@link #apFlowFl apFlowFl}
     * 
     */
    public String setApFlowFl(String apFlowFl) {
        this.apFlowFl = apFlowFl;
        return this.apFlowFl;
    }

    /**
     * Getter for {@link #apNumber apNumber}
     * 
     */
    public String getApNumber() {
        return this.apNumber;
    }

    /**
     * Setter for {@link #apNumber apNumber}
     * 
     */
    public String setApNumber(String apNumber) {
        this.apNumber = apNumber;
        return this.apNumber;
    }

    /**
     * Getter for {@link #paymentTermPct1 paymentTermPct1}
     * 
     */
    public BigDecimal getPaymentTermPct1() {
        return this.paymentTermPct1;
    }

    /**
     * Setter for {@link #paymentTermPct1 paymentTermPct1}
     * 
     */
    public BigDecimal setPaymentTermPct1(BigDecimal paymentTermPct1) {
        if ((paymentTermPct1 != null)&&(paymentTermPct1 .scale()!= 2)) {
            paymentTermPct1 = paymentTermPct1 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.paymentTermPct1 = paymentTermPct1;
        return this.paymentTermPct1;
    }

    /**
     * Getter for {@link #paymentTermPct2 paymentTermPct2}
     * 
     */
    public BigDecimal getPaymentTermPct2() {
        return this.paymentTermPct2;
    }

    /**
     * Setter for {@link #paymentTermPct2 paymentTermPct2}
     * 
     */
    public BigDecimal setPaymentTermPct2(BigDecimal paymentTermPct2) {
        if ((paymentTermPct2 != null)&&(paymentTermPct2 .scale()!= 2)) {
            paymentTermPct2 = paymentTermPct2 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.paymentTermPct2 = paymentTermPct2;
        return this.paymentTermPct2;
    }

    /**
     * Getter for {@link #paymentTermDays1 paymentTermDays1}
     * 
     */
    public Integer getPaymentTermDays1() {
        return this.paymentTermDays1;
    }

    /**
     * Setter for {@link #paymentTermDays1 paymentTermDays1}
     * 
     */
    public Integer setPaymentTermDays1(Integer paymentTermDays1) {
        this.paymentTermDays1 = paymentTermDays1;
        return this.paymentTermDays1;
    }

    /**
     * Getter for {@link #paymentTermDays2 paymentTermDays2}
     * 
     */
    public Integer getPaymentTermDays2() {
        return this.paymentTermDays2;
    }

    /**
     * Setter for {@link #paymentTermDays2 paymentTermDays2}
     * 
     */
    public Integer setPaymentTermDays2(Integer paymentTermDays2) {
        this.paymentTermDays2 = paymentTermDays2;
        return this.paymentTermDays2;
    }

    /**
     * Getter for {@link #payStatusFl payStatusFl}
     * 
     */
    public String getPayStatusFl() {
        return this.payStatusFl;
    }

    /**
     * Setter for {@link #payStatusFl payStatusFl}
     * 
     */
    public String setPayStatusFl(String payStatusFl) {
        this.payStatusFl = payStatusFl;
        return this.payStatusFl;
    }

    /**
     * Getter for {@link #netDays netDays}
     * 
     */
    public Integer getNetDays() {
        return this.netDays;
    }

    /**
     * Setter for {@link #netDays netDays}
     * 
     */
    public Integer setNetDays(Integer netDays) {
        this.netDays = netDays;
        return this.netDays;
    }

    /**
     * Getter for {@link #orderStatusFl orderStatusFl}
     * 
     */
    public String getOrderStatusFl() {
        return this.orderStatusFl;
    }

    /**
     * Setter for {@link #orderStatusFl orderStatusFl}
     * 
     */
    public String setOrderStatusFl(String orderStatusFl) {
        this.orderStatusFl = orderStatusFl;
        return this.orderStatusFl;
    }

    /**
     * Getter for {@link #lastInvoiceDt lastInvoiceDt}
     * 
     */
    public Date getLastInvoiceDt() {
        return this.lastInvoiceDt;
    }

    /**
     * Setter for {@link #lastInvoiceDt lastInvoiceDt}
     * 
     */
    public Date setLastInvoiceDt(Date lastInvoiceDt) {
        this.lastInvoiceDt = lastInvoiceDt;
        return this.lastInvoiceDt;
    }

    /**
     * Getter for {@link #lastPaymentDt lastPaymentDt}
     * 
     */
    public Date getLastPaymentDt() {
        return this.lastPaymentDt;
    }

    /**
     * Setter for {@link #lastPaymentDt lastPaymentDt}
     * 
     */
    public Date setLastPaymentDt(Date lastPaymentDt) {
        this.lastPaymentDt = lastPaymentDt;
        return this.lastPaymentDt;
    }

    /**
     * Getter for {@link #shipVia shipVia}
     * 
     */
    public String getShipVia() {
        return this.shipVia;
    }

    /**
     * Setter for {@link #shipVia shipVia}
     * 
     */
    public String setShipVia(String shipVia) {
        this.shipVia = shipVia;
        return this.shipVia;
    }

    /**
     * Getter for {@link #shipTerms shipTerms}
     * 
     */
    public String getShipTerms() {
        return this.shipTerms;
    }

    /**
     * Setter for {@link #shipTerms shipTerms}
     * 
     */
    public String setShipTerms(String shipTerms) {
        this.shipTerms = shipTerms;
        return this.shipTerms;
    }

    /**
     * Getter for {@link #fob fob}
     * 
     */
    public String getFob() {
        return this.fob;
    }

    /**
     * Setter for {@link #fob fob}
     * 
     */
    public String setFob(String fob) {
        this.fob = fob;
        return this.fob;
    }

    /**
     * Getter for {@link #commentTxt commentTxt}
     * 
     */
    public String getCommentTxt() {
        return this.commentTxt;
    }

    /**
     * Setter for {@link #commentTxt commentTxt}
     * 
     */
    public String setCommentTxt(String commentTxt) {
        this.commentTxt = commentTxt;
        return this.commentTxt;
    }

    /**
     * Getter for {@link #shipInstTxt shipInstTxt}
     * 
     */
    public String getShipInstTxt() {
        return this.shipInstTxt;
    }

    /**
     * Setter for {@link #shipInstTxt shipInstTxt}
     * 
     */
    public String setShipInstTxt(String shipInstTxt) {
        this.shipInstTxt = shipInstTxt;
        return this.shipInstTxt;
    }

    /**
     * Getter for {@link #ediAgent ediAgent}
     * 
     */
    public String getEdiAgent() {
        return this.ediAgent;
    }

    /**
     * Setter for {@link #ediAgent ediAgent}
     * 
     */
    public String setEdiAgent(String ediAgent) {
        this.ediAgent = ediAgent;
        return this.ediAgent;
    }

    /**
     * Getter for {@link #ediType ediType}
     * 
     */
    public String getEdiType() {
        return this.ediType;
    }

    /**
     * Setter for {@link #ediType ediType}
     * 
     */
    public String setEdiType(String ediType) {
        this.ediType = ediType;
        return this.ediType;
    }

    /**
     * Getter for {@link #calcNetCostFl calcNetCostFl}
     * 
     */
    public String getCalcNetCostFl() {
        return this.calcNetCostFl;
    }

    /**
     * Setter for {@link #calcNetCostFl calcNetCostFl}
     * 
     */
    public String setCalcNetCostFl(String calcNetCostFl) {
        this.calcNetCostFl = calcNetCostFl;
        return this.calcNetCostFl;
    }

    /**
     * Getter for {@link #primaryControlFl primaryControlFl}
     * 
     */
    public String getPrimaryControlFl() {
        return this.primaryControlFl;
    }

    /**
     * Setter for {@link #primaryControlFl primaryControlFl}
     * 
     */
    public String setPrimaryControlFl(String primaryControlFl) {
        this.primaryControlFl = primaryControlFl;
        return this.primaryControlFl;
    }

    /**
     * Getter for {@link #primaryControlNToYFl primaryControlNToYFl}
     * 
     */
    public String getPrimaryControlNToYFl() {
        return this.primaryControlNToYFl;
    }

    /**
     * Setter for {@link #primaryControlNToYFl primaryControlNToYFl}
     * 
     */
    public String setPrimaryControlNToYFl(String primaryControlNToYFl) {
        this.primaryControlNToYFl = primaryControlNToYFl;
        return this.primaryControlNToYFl;
    }

    /**
     * Getter for {@link #dunsNo dunsNo}
     * 
     */
    public Integer getDunsNo() {
        return this.dunsNo;
    }

    /**
     * Setter for {@link #dunsNo dunsNo}
     * 
     */
    public Integer setDunsNo(Integer dunsNo) {
        this.dunsNo = dunsNo;
        return this.dunsNo;
    }

    /**
     * Getter for {@link #commId commId}
     * 
     */
    public Long getCommId() {
        return this.commId;
    }

    /**
     * Setter for {@link #commId commId}
     * 
     */
    public Long setCommId(Long commId) {
        this.commId = commId;
        return this.commId;
    }

    /**
     * Getter for {@link #extCdMethod extCdMethod}
     * 
     */
    public String getExtCdMethod() {
        return this.extCdMethod;
    }

    /**
     * Setter for {@link #extCdMethod extCdMethod}
     * 
     */
    public String setExtCdMethod(String extCdMethod) {
        this.extCdMethod = extCdMethod;
        return this.extCdMethod;
    }

    /**
     * Getter for {@link #extDivisionCd extDivisionCd}
     * 
     */
    public String getExtDivisionCd() {
        return this.extDivisionCd;
    }

    /**
     * Setter for {@link #extDivisionCd extDivisionCd}
     * 
     */
    public String setExtDivisionCd(String extDivisionCd) {
        this.extDivisionCd = extDivisionCd;
        return this.extDivisionCd;
    }

    /**
     * Getter for {@link #extFileseqNo extFileseqNo}
     * 
     */
    public Integer getExtFileseqNo() {
        return this.extFileseqNo;
    }

    /**
     * Setter for {@link #extFileseqNo extFileseqNo}
     * 
     */
    public Integer setExtFileseqNo(Integer extFileseqNo) {
        this.extFileseqNo = extFileseqNo;
        return this.extFileseqNo;
    }

    /**
     * Getter for {@link #extVendorNum extVendorNum}
     * 
     */
    public String getExtVendorNum() {
        return this.extVendorNum;
    }

    /**
     * Setter for {@link #extVendorNum extVendorNum}
     * 
     */
    public String setExtVendorNum(String extVendorNum) {
        this.extVendorNum = extVendorNum;
        return this.extVendorNum;
    }

    /**
     * Getter for {@link #extVendorSite extVendorSite}
     * 
     */
    public String getExtVendorSite() {
        return this.extVendorSite;
    }

    /**
     * Setter for {@link #extVendorSite extVendorSite}
     * 
     */
    public String setExtVendorSite(String extVendorSite) {
        this.extVendorSite = extVendorSite;
        return this.extVendorSite;
    }

    /**
     * Getter for {@link #consolFreightVendorId consolFreightVendorId}
     * 
     */
    public String getConsolFreightVendorId() {
        return this.consolFreightVendorId;
    }

    /**
     * Setter for {@link #consolFreightVendorId consolFreightVendorId}
     * 
     */
    public String setConsolFreightVendorId(String consolFreightVendorId) {
        this.consolFreightVendorId = consolFreightVendorId;
        return this.consolFreightVendorId;
    }

    /**
     * Getter for {@link #extraReplenDaysOneTime extraReplenDaysOneTime}
     * 
     */
    public Integer getExtraReplenDaysOneTime() {
        return this.extraReplenDaysOneTime;
    }

    /**
     * Setter for {@link #extraReplenDaysOneTime extraReplenDaysOneTime}
     * 
     */
    public Integer setExtraReplenDaysOneTime(Integer extraReplenDaysOneTime) {
        this.extraReplenDaysOneTime = extraReplenDaysOneTime;
        return this.extraReplenDaysOneTime;
    }

    /**
     * Getter for {@link #freightMinStrategy freightMinStrategy}
     * 
     */
    public String getFreightMinStrategy() {
        return this.freightMinStrategy;
    }

    /**
     * Setter for {@link #freightMinStrategy freightMinStrategy}
     * 
     */
    public String setFreightMinStrategy(String freightMinStrategy) {
        this.freightMinStrategy = freightMinStrategy;
        return this.freightMinStrategy;
    }

    /**
     * Getter for {@link #freightMinLineItems freightMinLineItems}
     * 
     */
    public Integer getFreightMinLineItems() {
        return this.freightMinLineItems;
    }

    /**
     * Setter for {@link #freightMinLineItems freightMinLineItems}
     * 
     */
    public Integer setFreightMinLineItems(Integer freightMinLineItems) {
        this.freightMinLineItems = freightMinLineItems;
        return this.freightMinLineItems;
    }

    /**
     * Getter for {@link #freightMinUnits freightMinUnits}
     * 
     */
    public Long getFreightMinUnits() {
        return this.freightMinUnits;
    }

    /**
     * Setter for {@link #freightMinUnits freightMinUnits}
     * 
     */
    public Long setFreightMinUnits(Long freightMinUnits) {
        this.freightMinUnits = freightMinUnits;
        return this.freightMinUnits;
    }

    /**
     * Getter for {@link #freightMinCases freightMinCases}
     * 
     */
    public Long getFreightMinCases() {
        return this.freightMinCases;
    }

    /**
     * Setter for {@link #freightMinCases freightMinCases}
     * 
     */
    public Long setFreightMinCases(Long freightMinCases) {
        this.freightMinCases = freightMinCases;
        return this.freightMinCases;
    }

    /**
     * Getter for {@link #freightMinAmt freightMinAmt}
     * 
     */
    public BigDecimal getFreightMinAmt() {
        return this.freightMinAmt;
    }

    /**
     * Setter for {@link #freightMinAmt freightMinAmt}
     * 
     */
    public BigDecimal setFreightMinAmt(BigDecimal freightMinAmt) {
        if ((freightMinAmt!= null)&&(freightMinAmt.scale()!= 2)) {
            freightMinAmt = freightMinAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.freightMinAmt = freightMinAmt;
        return this.freightMinAmt;
    }

    /**
     * Getter for {@link #freightMinWeight freightMinWeight}
     * 
     */
    public BigDecimal getFreightMinWeight() {
        return this.freightMinWeight;
    }

    /**
     * Setter for {@link #freightMinWeight freightMinWeight}
     * 
     */
    public BigDecimal setFreightMinWeight(BigDecimal freightMinWeight) {
        if ((freightMinWeight!= null)&&(freightMinWeight.scale()!= 2)) {
            freightMinWeight = freightMinWeight.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.freightMinWeight = freightMinWeight;
        return this.freightMinWeight;
    }

    /**
     * Getter for {@link #freightMinCube freightMinCube}
     * 
     */
    public BigDecimal getFreightMinCube() {
        return this.freightMinCube;
    }

    /**
     * Setter for {@link #freightMinCube freightMinCube}
     * 
     */
    public BigDecimal setFreightMinCube(BigDecimal freightMinCube) {
        if ((freightMinCube!= null)&&(freightMinCube.scale()!= 2)) {
            freightMinCube = freightMinCube.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.freightMinCube = freightMinCube;
        return this.freightMinCube;
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
     * Getter for {@link #mandatoryPurchMultFl mandatoryPurchMultFl}
     * 
     */
    public String getMandatoryPurchMultFl() {
        return this.mandatoryPurchMultFl;
    }

    /**
     * Setter for {@link #mandatoryPurchMultFl mandatoryPurchMultFl}
     * 
     */
    public String setMandatoryPurchMultFl(String mandatoryPurchMultFl) {
        this.mandatoryPurchMultFl = mandatoryPurchMultFl;
        return this.mandatoryPurchMultFl;
    }

    /**
     * Getter for {@link #nextReplenDate nextReplenDate}
     * 
     */
    public Date getNextReplenDate() {
        return this.nextReplenDate;
    }

    /**
     * Setter for {@link #nextReplenDate nextReplenDate}
     * 
     */
    public Date setNextReplenDate(Date nextReplenDate) {
        this.nextReplenDate = nextReplenDate;
        return this.nextReplenDate;
    }

    /**
     * Getter for {@link #orderLimitAmt orderLimitAmt}
     * 
     */
    public BigDecimal getOrderLimitAmt() {
        return this.orderLimitAmt;
    }

    /**
     * Setter for {@link #orderLimitAmt orderLimitAmt}
     * 
     */
    public BigDecimal setOrderLimitAmt(BigDecimal orderLimitAmt) {
        if ((orderLimitAmt!= null)&&(orderLimitAmt.scale()!= 2)) {
            orderLimitAmt = orderLimitAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.orderLimitAmt = orderLimitAmt;
        return this.orderLimitAmt;
    }

    /**
     * Getter for {@link #replenIntervalDays replenIntervalDays}
     * 
     */
    public Integer getReplenIntervalDays() {
        return this.replenIntervalDays;
    }

    /**
     * Setter for {@link #replenIntervalDays replenIntervalDays}
     * 
     */
    public Integer setReplenIntervalDays(Integer replenIntervalDays) {
        this.replenIntervalDays = replenIntervalDays;
        return this.replenIntervalDays;
    }

    /**
     * Getter for {@link #sendEdiFl sendEdiFl}
     * 
     */
    public String getSendEdiFl() {
        return this.sendEdiFl;
    }

    /**
     * Setter for {@link #sendEdiFl sendEdiFl}
     * 
     */
    public String setSendEdiFl(String sendEdiFl) {
        this.sendEdiFl = sendEdiFl;
        return this.sendEdiFl;
    }

    /**
     * Getter for {@link #stdReplenIntervalDays stdReplenIntervalDays}
     * 
     */
    public Integer getStdReplenIntervalDays() {
        return this.stdReplenIntervalDays;
    }

    /**
     * Setter for {@link #stdReplenIntervalDays stdReplenIntervalDays}
     * 
     */
    public Integer setStdReplenIntervalDays(Integer stdReplenIntervalDays) {
        this.stdReplenIntervalDays = stdReplenIntervalDays;
        return this.stdReplenIntervalDays;
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
     * Getter for {@link #internalVendorFl internalVendorFl}
     * 
     */
    public String getInternalVendorFl() {
        return this.internalVendorFl;
    }

    /**
     * Setter for {@link #internalVendorFl internalVendorFl}
     * 
     */
    public String setInternalVendorFl(String internalVendorFl) {
        this.internalVendorFl = internalVendorFl;
        return this.internalVendorFl;
    }

    /**
     * Getter for {@link #autoGenerateUpcFl autoGenerateUpcFl}
     * 
     */
    public String getAutoGenerateUpcFl() {
        return this.autoGenerateUpcFl;
    }

    /**
     * Setter for {@link #autoGenerateUpcFl autoGenerateUpcFl}
     * 
     */
    public String setAutoGenerateUpcFl(String autoGenerateUpcFl) {
        this.autoGenerateUpcFl = autoGenerateUpcFl;
        return this.autoGenerateUpcFl;
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
     * Getter for {@link #reconMethod reconMethod}
     * 
     */
    public String getReconMethod() {
        return this.reconMethod;
    }

    /**
     * Setter for {@link #reconMethod reconMethod}
     * 
     */
    public String setReconMethod(String reconMethod) {
        this.reconMethod = reconMethod;
        return this.reconMethod;
    }

    /**
     * Getter for {@link #costIncludesFreightFl costIncludesFreightFl}
     * 
     */
    public String getCostIncludesFreightFl() {
        return this.costIncludesFreightFl;
    }

    /**
     * Setter for {@link #costIncludesFreightFl costIncludesFreightFl}
     * 
     */
    public String setCostIncludesFreightFl(String costIncludesFreightFl) {
        this.costIncludesFreightFl = costIncludesFreightFl;
        return this.costIncludesFreightFl;
    }

    /**
     * Getter for {@link #printFreightFl printFreightFl}
     * 
     */
    public String getPrintFreightFl() {
        return this.printFreightFl;
    }

    /**
     * Setter for {@link #printFreightFl printFreightFl}
     * 
     */
    public String setPrintFreightFl(String printFreightFl) {
        this.printFreightFl = printFreightFl;
        return this.printFreightFl;
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
     * Getter for {@link #dsdRcvType dsdRcvType}
     * 
     */
    public String getDsdRcvType() {
        return this.dsdRcvType;
    }

    /**
     * Setter for {@link #dsdRcvType dsdRcvType}
     * 
     */
    public String setDsdRcvType(String dsdRcvType) {
        this.dsdRcvType = dsdRcvType;
        return this.dsdRcvType;
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
     * Getter for {@link #minPoAmt minPoAmt}
     * 
     */
    public BigDecimal getMinPoAmt() {
        return this.minPoAmt;
    }

    /**
     * Setter for {@link #minPoAmt minPoAmt}
     * 
     */
    public BigDecimal setMinPoAmt(BigDecimal minPoAmt) {
        if ((minPoAmt!= null)&&(minPoAmt.scale()!= 2)) {
            minPoAmt = minPoAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.minPoAmt = minPoAmt;
        return this.minPoAmt;
    }

    /**
     * Getter for {@link #vendorTypeCd vendorTypeCd}
     * 
     */
    public String getVendorTypeCd() {
        return this.vendorTypeCd;
    }

    /**
     * Setter for {@link #vendorTypeCd vendorTypeCd}
     * 
     */
    public String setVendorTypeCd(String vendorTypeCd) {
        this.vendorTypeCd = vendorTypeCd;
        return this.vendorTypeCd;
    }

    /**
     * Getter for {@link #orderAccountId orderAccountId}
     * 
     */
    public String getOrderAccountId() {
        return this.orderAccountId;
    }

    /**
     * Setter for {@link #orderAccountId orderAccountId}
     * 
     */
    public String setOrderAccountId(String orderAccountId) {
        this.orderAccountId = orderAccountId;
        return this.orderAccountId;
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
     * Getter for {@link #tradeCurrencyId tradeCurrencyId}
     * 
     */
    public String getTradeCurrencyId() {
        return this.tradeCurrencyId;
    }

    /**
     * Setter for {@link #tradeCurrencyId tradeCurrencyId}
     * 
     */
    public String setTradeCurrencyId(String tradeCurrencyId) {
        this.tradeCurrencyId = tradeCurrencyId;
        return this.tradeCurrencyId;
    }

    /**
     * Getter for {@link #backordersAllowedFl backordersAllowedFl}
     * 
     */
    public String getBackordersAllowedFl() {
        return this.backordersAllowedFl;
    }

    /**
     * Setter for {@link #backordersAllowedFl backordersAllowedFl}
     * 
     */
    public String setBackordersAllowedFl(String backordersAllowedFl) {
        this.backordersAllowedFl = backordersAllowedFl;
        return this.backordersAllowedFl;
    }

    /**
     * Getter for {@link #limitRcvdocPerInvoiceFl limitRcvdocPerInvoiceFl}
     * 
     */
    public String getLimitRcvdocPerInvoiceFl() {
        return this.limitRcvdocPerInvoiceFl;
    }

    /**
     * Setter for {@link #limitRcvdocPerInvoiceFl limitRcvdocPerInvoiceFl}
     * 
     */
    public String setLimitRcvdocPerInvoiceFl(String limitRcvdocPerInvoiceFl) {
        this.limitRcvdocPerInvoiceFl = limitRcvdocPerInvoiceFl;
        return this.limitRcvdocPerInvoiceFl;
    }

    /**
     * Getter for {@link #cancelLead cancelLead}
     * 
     */
    public Integer getCancelLead() {
        return this.cancelLead;
    }

    /**
     * Setter for {@link #cancelLead cancelLead}
     * 
     */
    public Integer setCancelLead(Integer cancelLead) {
        this.cancelLead = cancelLead;
        return this.cancelLead;
    }

    /**
     * Getter for {@link #expectLead expectLead}
     * 
     */
    public Integer getExpectLead() {
        return this.expectLead;
    }

    /**
     * Setter for {@link #expectLead expectLead}
     * 
     */
    public Integer setExpectLead(Integer expectLead) {
        this.expectLead = expectLead;
        return this.expectLead;
    }

    /**
     * Getter for {@link #prepaidFreightAmt prepaidFreightAmt}
     * 
     */
    public BigDecimal getPrepaidFreightAmt() {
        return this.prepaidFreightAmt;
    }

    /**
     * Setter for {@link #prepaidFreightAmt prepaidFreightAmt}
     * 
     */
    public BigDecimal setPrepaidFreightAmt(BigDecimal prepaidFreightAmt) {
        if ((prepaidFreightAmt!= null)&&(prepaidFreightAmt.scale()!= 2)) {
            prepaidFreightAmt = prepaidFreightAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.prepaidFreightAmt = prepaidFreightAmt;
        return this.prepaidFreightAmt;
    }

    /**
     * Getter for {@link #prepaidFreightWeight prepaidFreightWeight}
     * 
     */
    public BigDecimal getPrepaidFreightWeight() {
        return this.prepaidFreightWeight;
    }

    /**
     * Setter for {@link #prepaidFreightWeight prepaidFreightWeight}
     * 
     */
    public BigDecimal setPrepaidFreightWeight(BigDecimal prepaidFreightWeight) {
        if ((prepaidFreightWeight!= null)&&(prepaidFreightWeight.scale()!= 2)) {
            prepaidFreightWeight = prepaidFreightWeight.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.prepaidFreightWeight = prepaidFreightWeight;
        return this.prepaidFreightWeight;
    }

    /**
     * Getter for {@link #prepaidFreightCube prepaidFreightCube}
     * 
     */
    public BigDecimal getPrepaidFreightCube() {
        return this.prepaidFreightCube;
    }

    /**
     * Setter for {@link #prepaidFreightCube prepaidFreightCube}
     * 
     */
    public BigDecimal setPrepaidFreightCube(BigDecimal prepaidFreightCube) {
        if ((prepaidFreightCube!= null)&&(prepaidFreightCube.scale()!= 2)) {
            prepaidFreightCube = prepaidFreightCube.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.prepaidFreightCube = prepaidFreightCube;
        return this.prepaidFreightCube;
    }

    /**
     * Getter for {@link #minPackWeight minPackWeight}
     * 
     */
    public BigDecimal getMinPackWeight() {
        return this.minPackWeight;
    }

    /**
     * Setter for {@link #minPackWeight minPackWeight}
     * 
     */
    public BigDecimal setMinPackWeight(BigDecimal minPackWeight) {
        if ((minPackWeight!= null)&&(minPackWeight.scale()!= 2)) {
            minPackWeight = minPackWeight.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.minPackWeight = minPackWeight;
        return this.minPackWeight;
    }

    /**
     * Getter for {@link #minPackCube minPackCube}
     * 
     */
    public BigDecimal getMinPackCube() {
        return this.minPackCube;
    }

    /**
     * Setter for {@link #minPackCube minPackCube}
     * 
     */
    public BigDecimal setMinPackCube(BigDecimal minPackCube) {
        if ((minPackCube!= null)&&(minPackCube.scale()!= 2)) {
            minPackCube = minPackCube.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.minPackCube = minPackCube;
        return this.minPackCube;
    }

    /**
     * Getter for {@link #vendorDisposition vendorDisposition}
     * 
     */
    public String getVendorDisposition() {
        return this.vendorDisposition;
    }

    /**
     * Setter for {@link #vendorDisposition vendorDisposition}
     * 
     */
    public String setVendorDisposition(String vendorDisposition) {
        this.vendorDisposition = vendorDisposition;
        return this.vendorDisposition;
    }

    /**
     * Getter for {@link #minPoCaseQty minPoCaseQty}
     * 
     */
    public BigDecimal getMinPoCaseQty() {
        return this.minPoCaseQty;
    }

    /**
     * Setter for {@link #minPoCaseQty minPoCaseQty}
     * 
     */
    public BigDecimal setMinPoCaseQty(BigDecimal minPoCaseQty) {
        if ((minPoCaseQty!= null)&&(minPoCaseQty.scale()!= 2)) {
            minPoCaseQty = minPoCaseQty.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.minPoCaseQty = minPoCaseQty;
        return this.minPoCaseQty;
    }

    /**
     * Getter for {@link #termCode termCode}
     * 
     */
    public String getTermCode() {
        return this.termCode;
    }

    /**
     * Setter for {@link #termCode termCode}
     * 
     */
    public String setTermCode(String termCode) {
        this.termCode = termCode;
        return this.termCode;
    }

    /**
     * Getter for {@link #dueDt dueDt}
     * 
     */
    public Date getDueDt() {
        return this.dueDt;
    }

    /**
     * Setter for {@link #dueDt dueDt}
     * 
     */
    public Date setDueDt(Date dueDt) {
        this.dueDt = dueDt;
        return this.dueDt;
    }

    /**
     * Getter for {@link #costRequiredFl costRequiredFl}
     * 
     */
    public String getCostRequiredFl() {
        return this.costRequiredFl;
    }

    /**
     * Setter for {@link #costRequiredFl costRequiredFl}
     * 
     */
    public String setCostRequiredFl(String costRequiredFl) {
        this.costRequiredFl = costRequiredFl;
        return this.costRequiredFl;
    }

    /**
     * Getter for {@link #rtvAllowedFl rtvAllowedFl}
     * 
     */
    public String getRtvAllowedFl() {
        return this.rtvAllowedFl;
    }

    /**
     * Setter for {@link #rtvAllowedFl rtvAllowedFl}
     * 
     */
    public String setRtvAllowedFl(String rtvAllowedFl) {
        this.rtvAllowedFl = rtvAllowedFl;
        return this.rtvAllowedFl;
    }

    /**
     * Getter for {@link #orderInstructions orderInstructions}
     * 
     */
    public String getOrderInstructions() {
        return this.orderInstructions;
    }

    /**
     * Setter for {@link #orderInstructions orderInstructions}
     * 
     */
    public String setOrderInstructions(String orderInstructions) {
        this.orderInstructions = orderInstructions;
        return this.orderInstructions;
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
     * Getter for {@link #currencyCd currencyCd}
     * 
     */
    public String getCurrencyCd() {
        return this.currencyCd;
    }

    /**
     * Setter for {@link #currencyCd currencyCd}
     * 
     */
    public String setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
        return this.currencyCd;
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
     * Getter for {@link #lockLeadTimeFl lockLeadTimeFl}
     * 
     */
    public String getLockLeadTimeFl() {
        return this.lockLeadTimeFl;
    }

    /**
     * Setter for {@link #lockLeadTimeFl lockLeadTimeFl}
     * 
     */
    public String setLockLeadTimeFl(String lockLeadTimeFl) {
        this.lockLeadTimeFl = lockLeadTimeFl;
        return this.lockLeadTimeFl;
    }

    /**
     * Getter for {@link #dateLastCalculated dateLastCalculated}
     * 
     */
    public Date getDateLastCalculated() {
        return this.dateLastCalculated;
    }

    /**
     * Setter for {@link #dateLastCalculated dateLastCalculated}
     * 
     */
    public Date setDateLastCalculated(Date dateLastCalculated) {
        this.dateLastCalculated = dateLastCalculated;
        return this.dateLastCalculated;
    }

    /**
     * @hide<P><I>Not for public use.</I></P>
     * 
     */
    private void _initDefaults() {
        if (this.apFlowFl == null) {
            this.apFlowFl = "N";
        }
        if (this.paymentTermPct1 == null) {
            this.paymentTermPct1 = new BigDecimal("0.00");
        }
        if (this.paymentTermPct2 == null) {
            this.paymentTermPct2 = new BigDecimal("0.00");
        }
        if (this.paymentTermDays1 == null) {
            this.paymentTermDays1 = Integer.valueOf(0);
        }
        if (this.paymentTermDays2 == null) {
            this.paymentTermDays2 = Integer.valueOf(0);
        }
        if (this.payStatusFl == null) {
            this.payStatusFl = "N";
        }
        if (this.netDays == null) {
            this.netDays = Integer.valueOf(0);
        }
        if (this.orderStatusFl == null) {
            this.orderStatusFl = "N";
        }
        if (this.calcNetCostFl == null) {
            this.calcNetCostFl = "N";
        }
        if (this.primaryControlFl == null) {
            this.primaryControlFl = "N";
        }
        if (this.extraReplenDaysOneTime == null) {
            this.extraReplenDaysOneTime = Integer.valueOf(0);
        }
        if (this.freightMinStrategy == null) {
            this.freightMinStrategy = "V";
        }
        if (this.freightMinLineItems == null) {
            this.freightMinLineItems = Integer.valueOf(0);
        }
        if (this.freightMinUnits == null) {
            this.freightMinUnits = Long.valueOf(0L);
        }
        if (this.freightMinCases == null) {
            this.freightMinCases = Long.valueOf(0L);
        }
        if (this.freightMinAmt == null) {
            this.freightMinAmt = new BigDecimal("0.00");
        }
        if (this.freightMinWeight == null) {
            this.freightMinWeight = new BigDecimal("0.00");
        }
        if (this.freightMinCube == null) {
            this.freightMinCube = new BigDecimal("0.00");
        }
        if (this.freightTolerancePct == null) {
            this.freightTolerancePct = new BigDecimal("0.00");
        }
        if (this.mandatoryPurchMultFl == null) {
            this.mandatoryPurchMultFl = "N";
        }
        if (this.orderLimitAmt == null) {
            this.orderLimitAmt = new BigDecimal("0.00");
        }
        if (this.replenIntervalDays == null) {
            this.replenIntervalDays = Integer.valueOf(0);
        }
        if (this.sendEdiFl == null) {
            this.sendEdiFl = "N";
        }
        if (this.stdReplenIntervalDays == null) {
            this.stdReplenIntervalDays = Integer.valueOf(0);
        }
        if (this.internalVendorFl == null) {
            this.internalVendorFl = "N";
        }
        if (this.autoGenerateUpcFl == null) {
            this.autoGenerateUpcFl = "N";
        }
        if (this.invToleranceQty == null) {
            this.invToleranceQty = new BigDecimal("0.00");
        }
        if (this.invToleranceQtyFl == null) {
            this.invToleranceQtyFl = "N";
        }
        if (this.invTolerancePct == null) {
            this.invTolerancePct = new BigDecimal("0.00");
        }
        if (this.invTolerancePctFl == null) {
            this.invTolerancePctFl = "N";
        }
        if (this.invToleranceAmt == null) {
            this.invToleranceAmt = new BigDecimal("0.00");
        }
        if (this.invToleranceAmtFl == null) {
            this.invToleranceAmtFl = "N";
        }
        if (this.freightToleranceAmt == null) {
            this.freightToleranceAmt = new BigDecimal("0.00");
        }
        if (this.freightToleranceAmtFl == null) {
            this.freightToleranceAmtFl = "N";
        }
        if (this.freightTolerancePctFl == null) {
            this.freightTolerancePctFl = "N";
        }
        if (this.reconMethod == null) {
            this.reconMethod = "T";
        }
        if (this.costIncludesFreightFl == null) {
            this.costIncludesFreightFl = "N";
        }
        if (this.printFreightFl == null) {
            this.printFreightFl = "N";
        }
        if (this.dsdRcvType == null) {
            this.dsdRcvType = "L";
        }
        if (this.vendorTypeCd == null) {
            this.vendorTypeCd = "M";
        }
        if (this.backordersAllowedFl == null) {
            this.backordersAllowedFl = "Y";
        }
        if (this.limitRcvdocPerInvoiceFl == null) {
            this.limitRcvdocPerInvoiceFl = "N";
        }
        if (this.minPackWeight == null) {
            this.minPackWeight = new BigDecimal("0.00");
        }
        if (this.minPackCube == null) {
            this.minPackCube = new BigDecimal("0.00");
        }
        if (this.costRequiredFl == null) {
            this.costRequiredFl = "N";
        }
        if (this.rtvAllowedFl == null) {
            this.rtvAllowedFl = "Y";
        }
        if (this.ownerId == null) {
            this.ownerId = Integer.valueOf(1);
        }
        if (this.lockLeadTimeFl == null) {
            this.lockLeadTimeFl = "N";
        }
    }

    /**
     * @hide<P><I>Not for public use.</I></P><P>Initializes internal collections.</P>
     * 
     */
    private void _init() {
    }

    /**
     * <P>Used to copy a VendorEntity including non-identifier fields only.</P>
     * 
     */
    public VendorEntity copyData() {
        final VendorEntity copy = new VendorEntity();
        	
        copy.name = this.name;
        copy.adr1 = this.adr1;
        copy.adr2 = this.adr2;
        copy.city = this.city;
        copy.state = this.state;
        copy.zip = this.zip;
        copy.country = this.country;
        copy.contact = this.contact;
        copy.contact2 = this.contact2;
        copy.phoneNo = this.phoneNo;
        copy.phone2No = this.phone2No;
        copy.faxNo = this.faxNo;
        copy.emailAddress = this.emailAddress;
        copy.apAdr1 = this.apAdr1;
        copy.apAdr2 = this.apAdr2;
        copy.apCity = this.apCity;
        copy.apState = this.apState;
        copy.apZip = this.apZip;
        copy.apCountry = this.apCountry;
        copy.apFlowFl = this.apFlowFl;
        copy.apNumber = this.apNumber;
        copy.paymentTermPct1 = this.paymentTermPct1;
        copy.paymentTermPct2 = this.paymentTermPct2;
        copy.paymentTermDays1 = this.paymentTermDays1;
        copy.paymentTermDays2 = this.paymentTermDays2;
        copy.payStatusFl = this.payStatusFl;
        copy.netDays = this.netDays;
        copy.orderStatusFl = this.orderStatusFl;
        copy.lastInvoiceDt = this.lastInvoiceDt;
        copy.lastPaymentDt = this.lastPaymentDt;
        copy.shipVia = this.shipVia;
        copy.shipTerms = this.shipTerms;
        copy.fob = this.fob;
        copy.commentTxt = this.commentTxt;
        copy.shipInstTxt = this.shipInstTxt;
        copy.ediAgent = this.ediAgent;
        copy.ediType = this.ediType;
        copy.calcNetCostFl = this.calcNetCostFl;
        copy.primaryControlFl = this.primaryControlFl;
        copy.primaryControlNToYFl = this.primaryControlNToYFl;
        copy.dunsNo = this.dunsNo;
        copy.commId = this.commId;
        copy.extCdMethod = this.extCdMethod;
        copy.extDivisionCd = this.extDivisionCd;
        copy.extFileseqNo = this.extFileseqNo;
        copy.extVendorNum = this.extVendorNum;
        copy.extVendorSite = this.extVendorSite;
        copy.consolFreightVendorId = this.consolFreightVendorId;
        copy.extraReplenDaysOneTime = this.extraReplenDaysOneTime;
        copy.freightMinStrategy = this.freightMinStrategy;
        copy.freightMinLineItems = this.freightMinLineItems;
        copy.freightMinUnits = this.freightMinUnits;
        copy.freightMinCases = this.freightMinCases;
        copy.freightMinAmt = this.freightMinAmt;
        copy.freightMinWeight = this.freightMinWeight;
        copy.freightMinCube = this.freightMinCube;
        copy.freightTolerancePct = this.freightTolerancePct;
        copy.mandatoryPurchMultFl = this.mandatoryPurchMultFl;
        copy.nextReplenDate = this.nextReplenDate;
        copy.orderLimitAmt = this.orderLimitAmt;
        copy.replenIntervalDays = this.replenIntervalDays;
        copy.sendEdiFl = this.sendEdiFl;
        copy.stdReplenIntervalDays = this.stdReplenIntervalDays;
        copy.siteNo = this.siteNo;
        copy.internalVendorFl = this.internalVendorFl;
        copy.autoGenerateUpcFl = this.autoGenerateUpcFl;
        copy.invToleranceQty = this.invToleranceQty;
        copy.invToleranceQtyFl = this.invToleranceQtyFl;
        copy.invTolerancePct = this.invTolerancePct;
        copy.invTolerancePctFl = this.invTolerancePctFl;
        copy.invToleranceAmt = this.invToleranceAmt;
        copy.invToleranceAmtFl = this.invToleranceAmtFl;
        copy.freightToleranceAmt = this.freightToleranceAmt;
        copy.freightToleranceAmtFl = this.freightToleranceAmtFl;
        copy.freightTolerancePctFl = this.freightTolerancePctFl;
        copy.reconMethod = this.reconMethod;
        copy.costIncludesFreightFl = this.costIncludesFreightFl;
        copy.printFreightFl = this.printFreightFl;
        copy.leadTime = this.leadTime;
        copy.dsdRcvType = this.dsdRcvType;
        copy.replicationNo = this.replicationNo;
        copy.operationType = this.operationType;
        copy.dateChanged = this.dateChanged;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.externalRefId = this.externalRefId;
        copy.minPoAmt = this.minPoAmt;
        copy.vendorTypeCd = this.vendorTypeCd;
        copy.orderAccountId = this.orderAccountId;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.employeeId = this.employeeId;
        copy.tradeCurrencyId = this.tradeCurrencyId;
        copy.backordersAllowedFl = this.backordersAllowedFl;
        copy.limitRcvdocPerInvoiceFl = this.limitRcvdocPerInvoiceFl;
        copy.cancelLead = this.cancelLead;
        copy.expectLead = this.expectLead;
        copy.prepaidFreightAmt = this.prepaidFreightAmt;
        copy.prepaidFreightWeight = this.prepaidFreightWeight;
        copy.prepaidFreightCube = this.prepaidFreightCube;
        copy.minPackWeight = this.minPackWeight;
        copy.minPackCube = this.minPackCube;
        copy.vendorDisposition = this.vendorDisposition;
        copy.minPoCaseQty = this.minPoCaseQty;
        copy.termCode = this.termCode;
        copy.dueDt = this.dueDt;
        copy.costRequiredFl = this.costRequiredFl;
        copy.rtvAllowedFl = this.rtvAllowedFl;
        copy.orderInstructions = this.orderInstructions;
        copy.landedCostElements = this.landedCostElements;
        copy.currencyCd = this.currencyCd;
        copy.ownerId = this.ownerId;
        copy.lockLeadTimeFl = this.lockLeadTimeFl;
        copy.dateLastCalculated = this.dateLastCalculated;
        	
        return copy;
    }

    /**
     * <P>Used to copy a VendorEntity including its identifier.</P>
     * 
     */
    public VendorEntity copyDataAndId() {
        final VendorEntity copy = new VendorEntity();
        	
        copy.getIdentifier().setVendorId(this.getIdentifier().getVendorId());
        	
        copy.name = this.name;
        copy.adr1 = this.adr1;
        copy.adr2 = this.adr2;
        copy.city = this.city;
        copy.state = this.state;
        copy.zip = this.zip;
        copy.country = this.country;
        copy.contact = this.contact;
        copy.contact2 = this.contact2;
        copy.phoneNo = this.phoneNo;
        copy.phone2No = this.phone2No;
        copy.faxNo = this.faxNo;
        copy.emailAddress = this.emailAddress;
        copy.apAdr1 = this.apAdr1;
        copy.apAdr2 = this.apAdr2;
        copy.apCity = this.apCity;
        copy.apState = this.apState;
        copy.apZip = this.apZip;
        copy.apCountry = this.apCountry;
        copy.apFlowFl = this.apFlowFl;
        copy.apNumber = this.apNumber;
        copy.paymentTermPct1 = this.paymentTermPct1;
        copy.paymentTermPct2 = this.paymentTermPct2;
        copy.paymentTermDays1 = this.paymentTermDays1;
        copy.paymentTermDays2 = this.paymentTermDays2;
        copy.payStatusFl = this.payStatusFl;
        copy.netDays = this.netDays;
        copy.orderStatusFl = this.orderStatusFl;
        copy.lastInvoiceDt = this.lastInvoiceDt;
        copy.lastPaymentDt = this.lastPaymentDt;
        copy.shipVia = this.shipVia;
        copy.shipTerms = this.shipTerms;
        copy.fob = this.fob;
        copy.commentTxt = this.commentTxt;
        copy.shipInstTxt = this.shipInstTxt;
        copy.ediAgent = this.ediAgent;
        copy.ediType = this.ediType;
        copy.calcNetCostFl = this.calcNetCostFl;
        copy.primaryControlFl = this.primaryControlFl;
        copy.primaryControlNToYFl = this.primaryControlNToYFl;
        copy.dunsNo = this.dunsNo;
        copy.commId = this.commId;
        copy.extCdMethod = this.extCdMethod;
        copy.extDivisionCd = this.extDivisionCd;
        copy.extFileseqNo = this.extFileseqNo;
        copy.extVendorNum = this.extVendorNum;
        copy.extVendorSite = this.extVendorSite;
        copy.consolFreightVendorId = this.consolFreightVendorId;
        copy.extraReplenDaysOneTime = this.extraReplenDaysOneTime;
        copy.freightMinStrategy = this.freightMinStrategy;
        copy.freightMinLineItems = this.freightMinLineItems;
        copy.freightMinUnits = this.freightMinUnits;
        copy.freightMinCases = this.freightMinCases;
        copy.freightMinAmt = this.freightMinAmt;
        copy.freightMinWeight = this.freightMinWeight;
        copy.freightMinCube = this.freightMinCube;
        copy.freightTolerancePct = this.freightTolerancePct;
        copy.mandatoryPurchMultFl = this.mandatoryPurchMultFl;
        copy.nextReplenDate = this.nextReplenDate;
        copy.orderLimitAmt = this.orderLimitAmt;
        copy.replenIntervalDays = this.replenIntervalDays;
        copy.sendEdiFl = this.sendEdiFl;
        copy.stdReplenIntervalDays = this.stdReplenIntervalDays;
        copy.siteNo = this.siteNo;
        copy.internalVendorFl = this.internalVendorFl;
        copy.autoGenerateUpcFl = this.autoGenerateUpcFl;
        copy.invToleranceQty = this.invToleranceQty;
        copy.invToleranceQtyFl = this.invToleranceQtyFl;
        copy.invTolerancePct = this.invTolerancePct;
        copy.invTolerancePctFl = this.invTolerancePctFl;
        copy.invToleranceAmt = this.invToleranceAmt;
        copy.invToleranceAmtFl = this.invToleranceAmtFl;
        copy.freightToleranceAmt = this.freightToleranceAmt;
        copy.freightToleranceAmtFl = this.freightToleranceAmtFl;
        copy.freightTolerancePctFl = this.freightTolerancePctFl;
        copy.reconMethod = this.reconMethod;
        copy.costIncludesFreightFl = this.costIncludesFreightFl;
        copy.printFreightFl = this.printFreightFl;
        copy.leadTime = this.leadTime;
        copy.dsdRcvType = this.dsdRcvType;
        copy.replicationNo = this.replicationNo;
        copy.operationType = this.operationType;
        copy.dateChanged = this.dateChanged;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.externalRefId = this.externalRefId;
        copy.minPoAmt = this.minPoAmt;
        copy.vendorTypeCd = this.vendorTypeCd;
        copy.orderAccountId = this.orderAccountId;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.employeeId = this.employeeId;
        copy.tradeCurrencyId = this.tradeCurrencyId;
        copy.backordersAllowedFl = this.backordersAllowedFl;
        copy.limitRcvdocPerInvoiceFl = this.limitRcvdocPerInvoiceFl;
        copy.cancelLead = this.cancelLead;
        copy.expectLead = this.expectLead;
        copy.prepaidFreightAmt = this.prepaidFreightAmt;
        copy.prepaidFreightWeight = this.prepaidFreightWeight;
        copy.prepaidFreightCube = this.prepaidFreightCube;
        copy.minPackWeight = this.minPackWeight;
        copy.minPackCube = this.minPackCube;
        copy.vendorDisposition = this.vendorDisposition;
        copy.minPoCaseQty = this.minPoCaseQty;
        copy.termCode = this.termCode;
        copy.dueDt = this.dueDt;
        copy.costRequiredFl = this.costRequiredFl;
        copy.rtvAllowedFl = this.rtvAllowedFl;
        copy.orderInstructions = this.orderInstructions;
        copy.landedCostElements = this.landedCostElements;
        copy.currencyCd = this.currencyCd;
        copy.ownerId = this.ownerId;
        copy.lockLeadTimeFl = this.lockLeadTimeFl;
        copy.dateLastCalculated = this.dateLastCalculated;
        	
        return copy;
    }

    /**
     * <P>Used to copy a VendorEntity including data and GENERATED identifiers only.</P>
     * 
     */
    public VendorEntity copyDataAndIdNonGenerated() {
        final VendorEntity copy = new VendorEntity();
        	
        copy.getIdentifier().setVendorId(this.getIdentifier().getVendorId());
        	
        copy.name = this.name;
        copy.adr1 = this.adr1;
        copy.adr2 = this.adr2;
        copy.city = this.city;
        copy.state = this.state;
        copy.zip = this.zip;
        copy.country = this.country;
        copy.contact = this.contact;
        copy.contact2 = this.contact2;
        copy.phoneNo = this.phoneNo;
        copy.phone2No = this.phone2No;
        copy.faxNo = this.faxNo;
        copy.emailAddress = this.emailAddress;
        copy.apAdr1 = this.apAdr1;
        copy.apAdr2 = this.apAdr2;
        copy.apCity = this.apCity;
        copy.apState = this.apState;
        copy.apZip = this.apZip;
        copy.apCountry = this.apCountry;
        copy.apFlowFl = this.apFlowFl;
        copy.apNumber = this.apNumber;
        copy.paymentTermPct1 = this.paymentTermPct1;
        copy.paymentTermPct2 = this.paymentTermPct2;
        copy.paymentTermDays1 = this.paymentTermDays1;
        copy.paymentTermDays2 = this.paymentTermDays2;
        copy.payStatusFl = this.payStatusFl;
        copy.netDays = this.netDays;
        copy.orderStatusFl = this.orderStatusFl;
        copy.lastInvoiceDt = this.lastInvoiceDt;
        copy.lastPaymentDt = this.lastPaymentDt;
        copy.shipVia = this.shipVia;
        copy.shipTerms = this.shipTerms;
        copy.fob = this.fob;
        copy.commentTxt = this.commentTxt;
        copy.shipInstTxt = this.shipInstTxt;
        copy.ediAgent = this.ediAgent;
        copy.ediType = this.ediType;
        copy.calcNetCostFl = this.calcNetCostFl;
        copy.primaryControlFl = this.primaryControlFl;
        copy.primaryControlNToYFl = this.primaryControlNToYFl;
        copy.dunsNo = this.dunsNo;
        copy.commId = this.commId;
        copy.extCdMethod = this.extCdMethod;
        copy.extDivisionCd = this.extDivisionCd;
        copy.extFileseqNo = this.extFileseqNo;
        copy.extVendorNum = this.extVendorNum;
        copy.extVendorSite = this.extVendorSite;
        copy.consolFreightVendorId = this.consolFreightVendorId;
        copy.extraReplenDaysOneTime = this.extraReplenDaysOneTime;
        copy.freightMinStrategy = this.freightMinStrategy;
        copy.freightMinLineItems = this.freightMinLineItems;
        copy.freightMinUnits = this.freightMinUnits;
        copy.freightMinCases = this.freightMinCases;
        copy.freightMinAmt = this.freightMinAmt;
        copy.freightMinWeight = this.freightMinWeight;
        copy.freightMinCube = this.freightMinCube;
        copy.freightTolerancePct = this.freightTolerancePct;
        copy.mandatoryPurchMultFl = this.mandatoryPurchMultFl;
        copy.nextReplenDate = this.nextReplenDate;
        copy.orderLimitAmt = this.orderLimitAmt;
        copy.replenIntervalDays = this.replenIntervalDays;
        copy.sendEdiFl = this.sendEdiFl;
        copy.stdReplenIntervalDays = this.stdReplenIntervalDays;
        copy.siteNo = this.siteNo;
        copy.internalVendorFl = this.internalVendorFl;
        copy.autoGenerateUpcFl = this.autoGenerateUpcFl;
        copy.invToleranceQty = this.invToleranceQty;
        copy.invToleranceQtyFl = this.invToleranceQtyFl;
        copy.invTolerancePct = this.invTolerancePct;
        copy.invTolerancePctFl = this.invTolerancePctFl;
        copy.invToleranceAmt = this.invToleranceAmt;
        copy.invToleranceAmtFl = this.invToleranceAmtFl;
        copy.freightToleranceAmt = this.freightToleranceAmt;
        copy.freightToleranceAmtFl = this.freightToleranceAmtFl;
        copy.freightTolerancePctFl = this.freightTolerancePctFl;
        copy.reconMethod = this.reconMethod;
        copy.costIncludesFreightFl = this.costIncludesFreightFl;
        copy.printFreightFl = this.printFreightFl;
        copy.leadTime = this.leadTime;
        copy.dsdRcvType = this.dsdRcvType;
        copy.replicationNo = this.replicationNo;
        copy.operationType = this.operationType;
        copy.dateChanged = this.dateChanged;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.externalRefId = this.externalRefId;
        copy.minPoAmt = this.minPoAmt;
        copy.vendorTypeCd = this.vendorTypeCd;
        copy.orderAccountId = this.orderAccountId;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.employeeId = this.employeeId;
        copy.tradeCurrencyId = this.tradeCurrencyId;
        copy.backordersAllowedFl = this.backordersAllowedFl;
        copy.limitRcvdocPerInvoiceFl = this.limitRcvdocPerInvoiceFl;
        copy.cancelLead = this.cancelLead;
        copy.expectLead = this.expectLead;
        copy.prepaidFreightAmt = this.prepaidFreightAmt;
        copy.prepaidFreightWeight = this.prepaidFreightWeight;
        copy.prepaidFreightCube = this.prepaidFreightCube;
        copy.minPackWeight = this.minPackWeight;
        copy.minPackCube = this.minPackCube;
        copy.vendorDisposition = this.vendorDisposition;
        copy.minPoCaseQty = this.minPoCaseQty;
        copy.termCode = this.termCode;
        copy.dueDt = this.dueDt;
        copy.costRequiredFl = this.costRequiredFl;
        copy.rtvAllowedFl = this.rtvAllowedFl;
        copy.orderInstructions = this.orderInstructions;
        copy.landedCostElements = this.landedCostElements;
        copy.currencyCd = this.currencyCd;
        copy.ownerId = this.ownerId;
        copy.lockLeadTimeFl = this.lockLeadTimeFl;
        copy.dateLastCalculated = this.dateLastCalculated;
        	
        return copy;
    }

    /**
     * @hide<P><I>Not for public use.</I></P>This postLoad method is used to fix BigDecimal values being loaded from the JDBC driver.  Oracle has a known jdbc bug where it truncates the scale of BigDecimal.  This method restores the scale after load so that BigDecimals comparators will work again.
     * 
     */
    private void _setScales() {
        if (this.paymentTermPct1 != null) {
            this.paymentTermPct1 = this.paymentTermPct1 .setScale(2);
        }
        if (this.paymentTermPct2 != null) {
            this.paymentTermPct2 = this.paymentTermPct2 .setScale(2);
        }
        if (this.freightMinAmt!= null) {
            this.freightMinAmt = this.freightMinAmt.setScale(2);
        }
        if (this.freightMinWeight!= null) {
            this.freightMinWeight = this.freightMinWeight.setScale(2);
        }
        if (this.freightMinCube!= null) {
            this.freightMinCube = this.freightMinCube.setScale(2);
        }
        if (this.freightTolerancePct!= null) {
            this.freightTolerancePct = this.freightTolerancePct.setScale(2);
        }
        if (this.orderLimitAmt!= null) {
            this.orderLimitAmt = this.orderLimitAmt.setScale(2);
        }
        if (this.invToleranceQty!= null) {
            this.invToleranceQty = this.invToleranceQty.setScale(2);
        }
        if (this.invTolerancePct!= null) {
            this.invTolerancePct = this.invTolerancePct.setScale(2);
        }
        if (this.invToleranceAmt!= null) {
            this.invToleranceAmt = this.invToleranceAmt.setScale(2);
        }
        if (this.freightToleranceAmt!= null) {
            this.freightToleranceAmt = this.freightToleranceAmt.setScale(2);
        }
        if (this.minPoAmt!= null) {
            this.minPoAmt = this.minPoAmt.setScale(2);
        }
        if (this.prepaidFreightAmt!= null) {
            this.prepaidFreightAmt = this.prepaidFreightAmt.setScale(2);
        }
        if (this.prepaidFreightWeight!= null) {
            this.prepaidFreightWeight = this.prepaidFreightWeight.setScale(2);
        }
        if (this.prepaidFreightCube!= null) {
            this.prepaidFreightCube = this.prepaidFreightCube.setScale(2);
        }
        if (this.minPackWeight!= null) {
            this.minPackWeight = this.minPackWeight.setScale(2);
        }
        if (this.minPackCube!= null) {
            this.minPackCube = this.minPackCube.setScale(2);
        }
        if (this.minPoCaseQty!= null) {
            this.minPoCaseQty = this.minPoCaseQty.setScale(2);
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

    public static VendorDTO toDTO(VendorEntity entity) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return VendorEntity.toDTO(entity, map);
    }

    public static VendorDTO toDTO(VendorEntity entity, Map<Object, Object> map) {
        try {
            if (entity == null) {
                return null;
            }
            	
            if (map == null) {
                map = new HashMap<Object, Object>();
            } else {
                if (map.get(entity)!= null) {
                    return ((VendorDTO) map.get(entity));
                }
            }
            	
            entity.toDTO(map);
            	
            return ((VendorDTO) map.get(entity));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public static VendorEntity toEntity(VendorDTO dto) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return VendorEntity.toEntity(dto, map);
    }

    public static VendorEntity toEntity(VendorDTO dto, Map<Object, Object> map) {
        try {
            if (dto == null) {
                return null;
            }
            	
            if ((map!= null)&&(map.get(dto)!= null)) {
                return ((VendorEntity) map.get(dto));
            }
            	
            return ((VendorEntity) DtoToEntity.toEntity(VendorEntity.class, ((DataTransferObject) dto)));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public void assignAllChildren(VendorDTO dto, Map<Object, Object> map) {
        toSimpleEntity(dto, map);
    }

    public void assignAllChildren(Map<Object, Object> map, Stack<Object> stack) {
        toSimpleDto(map);
    }

    public void assignAllParents(Map<Object, Object> map) {
        toSimpleDto(map);
    }

    protected VendorDTO toSimpleDto(Map<Object, Object> map) {
        if (map.get(this)!= null) {
            return ((VendorDTO) map.get(this));
        }
        try {
            VendorDTO dto = new VendorDTO();
            dto.setIdentifier(new VendorDTOId(this.getVendorId()));
            	
            dto.setName(this.getName());
            dto.setAdr1(this.getAdr1());
            dto.setAdr2(this.getAdr2());
            dto.setCity(this.getCity());
            dto.setState(this.getState());
            dto.setZip(this.getZip());
            dto.setCountry(this.getCountry());
            dto.setContact(this.getContact());
            dto.setContact2(this.getContact2());
            dto.setPhoneNo(this.getPhoneNo());
            dto.setPhone2No(this.getPhone2No());
            dto.setFaxNo(this.getFaxNo());
            dto.setEmailAddress(this.getEmailAddress());
            dto.setApAdr1(this.getApAdr1());
            dto.setApAdr2(this.getApAdr2());
            dto.setApCity(this.getApCity());
            dto.setApState(this.getApState());
            dto.setApZip(this.getApZip());
            dto.setApCountry(this.getApCountry());
            dto.setApFlowFl(this.getApFlowFl());
            dto.setApNumber(this.getApNumber());
            dto.setPaymentTermPct1(this.getPaymentTermPct1());
            dto.setPaymentTermPct2(this.getPaymentTermPct2());
            dto.setPaymentTermDays1(this.getPaymentTermDays1());
            dto.setPaymentTermDays2(this.getPaymentTermDays2());
            dto.setPayStatusFl(this.getPayStatusFl());
            dto.setNetDays(this.getNetDays());
            dto.setOrderStatusFl(this.getOrderStatusFl());
            dto.setLastInvoiceDt(this.getLastInvoiceDt());
            dto.setLastPaymentDt(this.getLastPaymentDt());
            dto.setShipVia(this.getShipVia());
            dto.setShipTerms(this.getShipTerms());
            dto.setFob(this.getFob());
            dto.setCommentTxt(this.getCommentTxt());
            dto.setShipInstTxt(this.getShipInstTxt());
            dto.setEdiAgent(this.getEdiAgent());
            dto.setEdiType(this.getEdiType());
            dto.setCalcNetCostFl(this.getCalcNetCostFl());
            dto.setPrimaryControlFl(this.getPrimaryControlFl());
            dto.setPrimaryControlNToYFl(this.getPrimaryControlNToYFl());
            dto.setDunsNo(this.getDunsNo());
            dto.setCommId(this.getCommId());
            dto.setExtCdMethod(this.getExtCdMethod());
            dto.setExtDivisionCd(this.getExtDivisionCd());
            dto.setExtFileseqNo(this.getExtFileseqNo());
            dto.setExtVendorNum(this.getExtVendorNum());
            dto.setExtVendorSite(this.getExtVendorSite());
            dto.setConsolFreightVendorId(this.getConsolFreightVendorId());
            dto.setExtraReplenDaysOneTime(this.getExtraReplenDaysOneTime());
            dto.setFreightMinStrategy(this.getFreightMinStrategy());
            dto.setFreightMinLineItems(this.getFreightMinLineItems());
            dto.setFreightMinUnits(this.getFreightMinUnits());
            dto.setFreightMinCases(this.getFreightMinCases());
            dto.setFreightMinAmt(this.getFreightMinAmt());
            dto.setFreightMinWeight(this.getFreightMinWeight());
            dto.setFreightMinCube(this.getFreightMinCube());
            dto.setFreightTolerancePct(this.getFreightTolerancePct());
            dto.setMandatoryPurchMultFl(this.getMandatoryPurchMultFl());
            dto.setNextReplenDate(this.getNextReplenDate());
            dto.setOrderLimitAmt(this.getOrderLimitAmt());
            dto.setReplenIntervalDays(this.getReplenIntervalDays());
            dto.setSendEdiFl(this.getSendEdiFl());
            dto.setStdReplenIntervalDays(this.getStdReplenIntervalDays());
            dto.setSiteNo(this.getSiteNo());
            dto.setInternalVendorFl(this.getInternalVendorFl());
            dto.setAutoGenerateUpcFl(this.getAutoGenerateUpcFl());
            dto.setInvToleranceQty(this.getInvToleranceQty());
            dto.setInvToleranceQtyFl(this.getInvToleranceQtyFl());
            dto.setInvTolerancePct(this.getInvTolerancePct());
            dto.setInvTolerancePctFl(this.getInvTolerancePctFl());
            dto.setInvToleranceAmt(this.getInvToleranceAmt());
            dto.setInvToleranceAmtFl(this.getInvToleranceAmtFl());
            dto.setFreightToleranceAmt(this.getFreightToleranceAmt());
            dto.setFreightToleranceAmtFl(this.getFreightToleranceAmtFl());
            dto.setFreightTolerancePctFl(this.getFreightTolerancePctFl());
            dto.setReconMethod(this.getReconMethod());
            dto.setCostIncludesFreightFl(this.getCostIncludesFreightFl());
            dto.setPrintFreightFl(this.getPrintFreightFl());
            dto.setLeadTime(this.getLeadTime());
            dto.setDsdRcvType(this.getDsdRcvType());
            dto.setReplicationNo(this.getReplicationNo());
            dto.setOperationType(this.getOperationType());
            dto.setDateChanged(this.getDateChanged());
            dto.setRegisterReplicationNo(this.getRegisterReplicationNo());
            dto.setExternalRefId(this.getExternalRefId());
            dto.setMinPoAmt(this.getMinPoAmt());
            dto.setVendorTypeCd(this.getVendorTypeCd());
            dto.setOrderAccountId(this.getOrderAccountId());
            dto.setDateCreated(this.getDateCreated());
            dto.setUserCreated(this.getUserCreated());
            dto.setDateModified(this.getDateModified());
            dto.setUserModified(this.getUserModified());
            dto.setEmployeeId(this.getEmployeeId());
            dto.setTradeCurrencyId(this.getTradeCurrencyId());
            dto.setBackordersAllowedFl(this.getBackordersAllowedFl());
            dto.setLimitRcvdocPerInvoiceFl(this.getLimitRcvdocPerInvoiceFl());
            dto.setCancelLead(this.getCancelLead());
            dto.setExpectLead(this.getExpectLead());
            dto.setPrepaidFreightAmt(this.getPrepaidFreightAmt());
            dto.setPrepaidFreightWeight(this.getPrepaidFreightWeight());
            dto.setPrepaidFreightCube(this.getPrepaidFreightCube());
            dto.setMinPackWeight(this.getMinPackWeight());
            dto.setMinPackCube(this.getMinPackCube());
            dto.setVendorDisposition(this.getVendorDisposition());
            dto.setMinPoCaseQty(this.getMinPoCaseQty());
            dto.setTermCode(this.getTermCode());
            dto.setDueDt(this.getDueDt());
            dto.setCostRequiredFl(this.getCostRequiredFl());
            dto.setRtvAllowedFl(this.getRtvAllowedFl());
            dto.setOrderInstructions(this.getOrderInstructions());
            dto.setLandedCostElements(this.getLandedCostElements());
            dto.setCurrencyCd(this.getCurrencyCd());
            dto.setOwnerId(this.getOwnerId());
            dto.setLockLeadTimeFl(this.getLockLeadTimeFl());
            dto.setDateLastCalculated(this.getDateLastCalculated());
            	
            map.put(this, dto);
            	
            return dto;
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public VendorEntity toSimpleEntity(DataTransferObject dto, Map<Object, Object> map) {
        if (map.get(dto)!= null) {
            return ((VendorEntity) map.get(dto));
        }
        try {
            VendorDTO oldDto = ((VendorDTO) dto);
            VendorEntity entity = new VendorEntity();
            entity.setIdentifier(new VendorEntityId(oldDto.getVendorId()));
            	
            entity.setName(oldDto.getName());
            entity.setAdr1(oldDto.getAdr1());
            entity.setAdr2(oldDto.getAdr2());
            entity.setCity(oldDto.getCity());
            entity.setState(oldDto.getState());
            entity.setZip(oldDto.getZip());
            entity.setCountry(oldDto.getCountry());
            entity.setContact(oldDto.getContact());
            entity.setContact2(oldDto.getContact2());
            entity.setPhoneNo(oldDto.getPhoneNo());
            entity.setPhone2No(oldDto.getPhone2No());
            entity.setFaxNo(oldDto.getFaxNo());
            entity.setEmailAddress(oldDto.getEmailAddress());
            entity.setApAdr1(oldDto.getApAdr1());
            entity.setApAdr2(oldDto.getApAdr2());
            entity.setApCity(oldDto.getApCity());
            entity.setApState(oldDto.getApState());
            entity.setApZip(oldDto.getApZip());
            entity.setApCountry(oldDto.getApCountry());
            entity.setApFlowFl(oldDto.getApFlowFl());
            entity.setApNumber(oldDto.getApNumber());
            entity.setPaymentTermPct1(oldDto.getPaymentTermPct1());
            entity.setPaymentTermPct2(oldDto.getPaymentTermPct2());
            entity.setPaymentTermDays1(oldDto.getPaymentTermDays1());
            entity.setPaymentTermDays2(oldDto.getPaymentTermDays2());
            entity.setPayStatusFl(oldDto.getPayStatusFl());
            entity.setNetDays(oldDto.getNetDays());
            entity.setOrderStatusFl(oldDto.getOrderStatusFl());
            entity.setLastInvoiceDt(oldDto.getLastInvoiceDt());
            entity.setLastPaymentDt(oldDto.getLastPaymentDt());
            entity.setShipVia(oldDto.getShipVia());
            entity.setShipTerms(oldDto.getShipTerms());
            entity.setFob(oldDto.getFob());
            entity.setCommentTxt(oldDto.getCommentTxt());
            entity.setShipInstTxt(oldDto.getShipInstTxt());
            entity.setEdiAgent(oldDto.getEdiAgent());
            entity.setEdiType(oldDto.getEdiType());
            entity.setCalcNetCostFl(oldDto.getCalcNetCostFl());
            entity.setPrimaryControlFl(oldDto.getPrimaryControlFl());
            entity.setPrimaryControlNToYFl(oldDto.getPrimaryControlNToYFl());
            entity.setDunsNo(oldDto.getDunsNo());
            entity.setCommId(oldDto.getCommId());
            entity.setExtCdMethod(oldDto.getExtCdMethod());
            entity.setExtDivisionCd(oldDto.getExtDivisionCd());
            entity.setExtFileseqNo(oldDto.getExtFileseqNo());
            entity.setExtVendorNum(oldDto.getExtVendorNum());
            entity.setExtVendorSite(oldDto.getExtVendorSite());
            entity.setConsolFreightVendorId(oldDto.getConsolFreightVendorId());
            entity.setExtraReplenDaysOneTime(oldDto.getExtraReplenDaysOneTime());
            entity.setFreightMinStrategy(oldDto.getFreightMinStrategy());
            entity.setFreightMinLineItems(oldDto.getFreightMinLineItems());
            entity.setFreightMinUnits(oldDto.getFreightMinUnits());
            entity.setFreightMinCases(oldDto.getFreightMinCases());
            entity.setFreightMinAmt(oldDto.getFreightMinAmt());
            entity.setFreightMinWeight(oldDto.getFreightMinWeight());
            entity.setFreightMinCube(oldDto.getFreightMinCube());
            entity.setFreightTolerancePct(oldDto.getFreightTolerancePct());
            entity.setMandatoryPurchMultFl(oldDto.getMandatoryPurchMultFl());
            entity.setNextReplenDate(oldDto.getNextReplenDate());
            entity.setOrderLimitAmt(oldDto.getOrderLimitAmt());
            entity.setReplenIntervalDays(oldDto.getReplenIntervalDays());
            entity.setSendEdiFl(oldDto.getSendEdiFl());
            entity.setStdReplenIntervalDays(oldDto.getStdReplenIntervalDays());
            entity.setSiteNo(oldDto.getSiteNo());
            entity.setInternalVendorFl(oldDto.getInternalVendorFl());
            entity.setAutoGenerateUpcFl(oldDto.getAutoGenerateUpcFl());
            entity.setInvToleranceQty(oldDto.getInvToleranceQty());
            entity.setInvToleranceQtyFl(oldDto.getInvToleranceQtyFl());
            entity.setInvTolerancePct(oldDto.getInvTolerancePct());
            entity.setInvTolerancePctFl(oldDto.getInvTolerancePctFl());
            entity.setInvToleranceAmt(oldDto.getInvToleranceAmt());
            entity.setInvToleranceAmtFl(oldDto.getInvToleranceAmtFl());
            entity.setFreightToleranceAmt(oldDto.getFreightToleranceAmt());
            entity.setFreightToleranceAmtFl(oldDto.getFreightToleranceAmtFl());
            entity.setFreightTolerancePctFl(oldDto.getFreightTolerancePctFl());
            entity.setReconMethod(oldDto.getReconMethod());
            entity.setCostIncludesFreightFl(oldDto.getCostIncludesFreightFl());
            entity.setPrintFreightFl(oldDto.getPrintFreightFl());
            entity.setLeadTime(oldDto.getLeadTime());
            entity.setDsdRcvType(oldDto.getDsdRcvType());
            entity.setReplicationNo(oldDto.getReplicationNo());
            entity.setOperationType(oldDto.getOperationType());
            entity.setDateChanged(oldDto.getDateChanged());
            entity.setRegisterReplicationNo(oldDto.getRegisterReplicationNo());
            entity.setExternalRefId(oldDto.getExternalRefId());
            entity.setMinPoAmt(oldDto.getMinPoAmt());
            entity.setVendorTypeCd(oldDto.getVendorTypeCd());
            entity.setOrderAccountId(oldDto.getOrderAccountId());
            entity.setDateCreated(oldDto.getDateCreated());
            entity.setUserCreated(oldDto.getUserCreated());
            entity.setDateModified(oldDto.getDateModified());
            entity.setUserModified(oldDto.getUserModified());
            entity.setEmployeeId(oldDto.getEmployeeId());
            entity.setTradeCurrencyId(oldDto.getTradeCurrencyId());
            entity.setBackordersAllowedFl(oldDto.getBackordersAllowedFl());
            entity.setLimitRcvdocPerInvoiceFl(oldDto.getLimitRcvdocPerInvoiceFl());
            entity.setCancelLead(oldDto.getCancelLead());
            entity.setExpectLead(oldDto.getExpectLead());
            entity.setPrepaidFreightAmt(oldDto.getPrepaidFreightAmt());
            entity.setPrepaidFreightWeight(oldDto.getPrepaidFreightWeight());
            entity.setPrepaidFreightCube(oldDto.getPrepaidFreightCube());
            entity.setMinPackWeight(oldDto.getMinPackWeight());
            entity.setMinPackCube(oldDto.getMinPackCube());
            entity.setVendorDisposition(oldDto.getVendorDisposition());
            entity.setMinPoCaseQty(oldDto.getMinPoCaseQty());
            entity.setTermCode(oldDto.getTermCode());
            entity.setDueDt(oldDto.getDueDt());
            entity.setCostRequiredFl(oldDto.getCostRequiredFl());
            entity.setRtvAllowedFl(oldDto.getRtvAllowedFl());
            entity.setOrderInstructions(oldDto.getOrderInstructions());
            entity.setLandedCostElements(oldDto.getLandedCostElements());
            entity.setCurrencyCd(oldDto.getCurrencyCd());
            entity.setOwnerId(oldDto.getOwnerId());
            entity.setLockLeadTimeFl(oldDto.getLockLeadTimeFl());
            entity.setDateLastCalculated(oldDto.getDateLastCalculated());
            	
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

    public static List<VendorDTO> toDTOList(Collection<VendorEntity> entities) {
        if (entities == null) {
            return null;
        }
        	
        List<VendorDTO> dtos = new ArrayList<VendorDTO>();
        for (VendorEntity entity: entities) {
            dtos.add(toDTO(entity));
        }
        	
        return dtos;
    }

    @Deprecated
    public static List<VendorDTO> toDTO(Collection<VendorEntity> entities) {
        return toDTOList(entities);
    }

    public static VendorDTOId toDTOId(VendorEntityId id) {
        if (id == null) {
            return null;
        }
        return new VendorDTOId(id.getVendorId());
    }

    @Deprecated
    public static VendorDTOId toDTO(VendorEntityId id) {
        return toDTOId(id);
    }

    public static List<VendorDTOId> toDTOIdList(Collection<VendorEntityId> entityIds) {
        if (entityIds == null) {
            return null;
        }
        	
        List<VendorDTOId> dtoIds = new ArrayList<VendorDTOId>();
        for (VendorEntityId entity: entityIds) {
            dtoIds.add(toDTOId(entity));
        }
        	
        return dtoIds;
    }

    public static Set<VendorEntity> toEntitySet(Collection<VendorDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        Set<VendorEntity> entities = new HashSet<VendorEntity>();
        for (VendorDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static Set<VendorEntity> toEntity(Collection<VendorDTO> dtos) {
        return toEntitySet(dtos);
    }

    public static SortedSet<VendorEntity> toEntitySortedSet(Collection<VendorDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        SortedSet<VendorEntity> entities = new TreeSet<VendorEntity>();
        for (VendorDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static SortedSet<VendorEntity> toEntitySorted(Collection<VendorDTO> dtos) {
        return toEntitySortedSet(dtos);
    }

    public static List<VendorEntity> toEntityList(Collection<VendorDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        List<VendorEntity> entities = new ArrayList<VendorEntity>();
        for (VendorDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    public static VendorEntityId toEntityId(VendorDTOId dto) {
        if (dto == null) {
            return null;
        }
        return new VendorEntityId(dto.getVendorId());
    }

    @Deprecated
    public static VendorEntityId toEntity(VendorDTOId id) {
        return toEntityId(id);
    }

    public static List<VendorEntityId> toEntityIdList(Collection<VendorDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        List<VendorEntityId> entityIds = new ArrayList<VendorEntityId>();
        for (VendorDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static Set<VendorEntityId> toEntityIdSet(Collection<VendorDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        Set<VendorEntityId> entityIds = new HashSet<VendorEntityId>();
        for (VendorDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static SortedSet<VendorEntityId> toEntityIdSortedSet(Collection<VendorDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        SortedSet<VendorEntityId> entityIds = new TreeSet<VendorEntityId>();
        for (VendorDTOId dtoId: dtoIds) {
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
        final VendorEntity other = ((VendorEntity) obj);
        // Identifier level comparison
        if (this.identifier.getVendorId() == null) {
            return false;
        } else {
            if (!this.identifier.getVendorId().equals(other.identifier.getVendorId())) {
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
        final VendorEntity other = ((VendorEntity) obj);
        // Data level comparison
        if (name == null) {
            if (other.name!= null) {
                return false;
            }
        } else {
            if (!name.equals(other.name)) {
                return false;
            }
        }
        if (adr1 == null) {
            if (other.adr1 != null) {
                return false;
            }
        } else {
            if (!adr1 .equals(other.adr1)) {
                return false;
            }
        }
        if (adr2 == null) {
            if (other.adr2 != null) {
                return false;
            }
        } else {
            if (!adr2 .equals(other.adr2)) {
                return false;
            }
        }
        if (city == null) {
            if (other.city!= null) {
                return false;
            }
        } else {
            if (!city.equals(other.city)) {
                return false;
            }
        }
        if (state == null) {
            if (other.state!= null) {
                return false;
            }
        } else {
            if (!state.equals(other.state)) {
                return false;
            }
        }
        if (zip == null) {
            if (other.zip!= null) {
                return false;
            }
        } else {
            if (!zip.equals(other.zip)) {
                return false;
            }
        }
        if (country == null) {
            if (other.country!= null) {
                return false;
            }
        } else {
            if (!country.equals(other.country)) {
                return false;
            }
        }
        if (contact == null) {
            if (other.contact!= null) {
                return false;
            }
        } else {
            if (!contact.equals(other.contact)) {
                return false;
            }
        }
        if (contact2 == null) {
            if (other.contact2 != null) {
                return false;
            }
        } else {
            if (!contact2 .equals(other.contact2)) {
                return false;
            }
        }
        if (phoneNo == null) {
            if (other.phoneNo!= null) {
                return false;
            }
        } else {
            if (!phoneNo.equals(other.phoneNo)) {
                return false;
            }
        }
        if (phone2No == null) {
            if (other.phone2No!= null) {
                return false;
            }
        } else {
            if (!phone2No.equals(other.phone2No)) {
                return false;
            }
        }
        if (faxNo == null) {
            if (other.faxNo!= null) {
                return false;
            }
        } else {
            if (!faxNo.equals(other.faxNo)) {
                return false;
            }
        }
        if (emailAddress == null) {
            if (other.emailAddress!= null) {
                return false;
            }
        } else {
            if (!emailAddress.equals(other.emailAddress)) {
                return false;
            }
        }
        if (apAdr1 == null) {
            if (other.apAdr1 != null) {
                return false;
            }
        } else {
            if (!apAdr1 .equals(other.apAdr1)) {
                return false;
            }
        }
        if (apAdr2 == null) {
            if (other.apAdr2 != null) {
                return false;
            }
        } else {
            if (!apAdr2 .equals(other.apAdr2)) {
                return false;
            }
        }
        if (apCity == null) {
            if (other.apCity!= null) {
                return false;
            }
        } else {
            if (!apCity.equals(other.apCity)) {
                return false;
            }
        }
        if (apState == null) {
            if (other.apState!= null) {
                return false;
            }
        } else {
            if (!apState.equals(other.apState)) {
                return false;
            }
        }
        if (apZip == null) {
            if (other.apZip!= null) {
                return false;
            }
        } else {
            if (!apZip.equals(other.apZip)) {
                return false;
            }
        }
        if (apCountry == null) {
            if (other.apCountry!= null) {
                return false;
            }
        } else {
            if (!apCountry.equals(other.apCountry)) {
                return false;
            }
        }
        if (apFlowFl == null) {
            if (other.apFlowFl!= null) {
                return false;
            }
        } else {
            if (!apFlowFl.equals(other.apFlowFl)) {
                return false;
            }
        }
        if (apNumber == null) {
            if (other.apNumber!= null) {
                return false;
            }
        } else {
            if (!apNumber.equals(other.apNumber)) {
                return false;
            }
        }
        if (paymentTermPct1 == null) {
            if (other.paymentTermPct1 != null) {
                return false;
            }
        } else {
            if (!paymentTermPct1 .equals(other.paymentTermPct1)) {
                return false;
            }
        }
        if (paymentTermPct2 == null) {
            if (other.paymentTermPct2 != null) {
                return false;
            }
        } else {
            if (!paymentTermPct2 .equals(other.paymentTermPct2)) {
                return false;
            }
        }
        if (paymentTermDays1 == null) {
            if (other.paymentTermDays1 != null) {
                return false;
            }
        } else {
            if (!paymentTermDays1 .equals(other.paymentTermDays1)) {
                return false;
            }
        }
        if (paymentTermDays2 == null) {
            if (other.paymentTermDays2 != null) {
                return false;
            }
        } else {
            if (!paymentTermDays2 .equals(other.paymentTermDays2)) {
                return false;
            }
        }
        if (payStatusFl == null) {
            if (other.payStatusFl!= null) {
                return false;
            }
        } else {
            if (!payStatusFl.equals(other.payStatusFl)) {
                return false;
            }
        }
        if (netDays == null) {
            if (other.netDays!= null) {
                return false;
            }
        } else {
            if (!netDays.equals(other.netDays)) {
                return false;
            }
        }
        if (orderStatusFl == null) {
            if (other.orderStatusFl!= null) {
                return false;
            }
        } else {
            if (!orderStatusFl.equals(other.orderStatusFl)) {
                return false;
            }
        }
        if (lastInvoiceDt == null) {
            if (other.lastInvoiceDt!= null) {
                return false;
            }
        } else {
            if (!lastInvoiceDt.equals(other.lastInvoiceDt)) {
                return false;
            }
        }
        if (lastPaymentDt == null) {
            if (other.lastPaymentDt!= null) {
                return false;
            }
        } else {
            if (!lastPaymentDt.equals(other.lastPaymentDt)) {
                return false;
            }
        }
        if (shipVia == null) {
            if (other.shipVia!= null) {
                return false;
            }
        } else {
            if (!shipVia.equals(other.shipVia)) {
                return false;
            }
        }
        if (shipTerms == null) {
            if (other.shipTerms!= null) {
                return false;
            }
        } else {
            if (!shipTerms.equals(other.shipTerms)) {
                return false;
            }
        }
        if (fob == null) {
            if (other.fob!= null) {
                return false;
            }
        } else {
            if (!fob.equals(other.fob)) {
                return false;
            }
        }
        if (commentTxt == null) {
            if (other.commentTxt!= null) {
                return false;
            }
        } else {
            if (!commentTxt.equals(other.commentTxt)) {
                return false;
            }
        }
        if (shipInstTxt == null) {
            if (other.shipInstTxt!= null) {
                return false;
            }
        } else {
            if (!shipInstTxt.equals(other.shipInstTxt)) {
                return false;
            }
        }
        if (ediAgent == null) {
            if (other.ediAgent!= null) {
                return false;
            }
        } else {
            if (!ediAgent.equals(other.ediAgent)) {
                return false;
            }
        }
        if (ediType == null) {
            if (other.ediType!= null) {
                return false;
            }
        } else {
            if (!ediType.equals(other.ediType)) {
                return false;
            }
        }
        if (calcNetCostFl == null) {
            if (other.calcNetCostFl!= null) {
                return false;
            }
        } else {
            if (!calcNetCostFl.equals(other.calcNetCostFl)) {
                return false;
            }
        }
        if (primaryControlFl == null) {
            if (other.primaryControlFl!= null) {
                return false;
            }
        } else {
            if (!primaryControlFl.equals(other.primaryControlFl)) {
                return false;
            }
        }
        if (primaryControlNToYFl == null) {
            if (other.primaryControlNToYFl!= null) {
                return false;
            }
        } else {
            if (!primaryControlNToYFl.equals(other.primaryControlNToYFl)) {
                return false;
            }
        }
        if (dunsNo == null) {
            if (other.dunsNo!= null) {
                return false;
            }
        } else {
            if (!dunsNo.equals(other.dunsNo)) {
                return false;
            }
        }
        if (commId == null) {
            if (other.commId!= null) {
                return false;
            }
        } else {
            if (!commId.equals(other.commId)) {
                return false;
            }
        }
        if (extCdMethod == null) {
            if (other.extCdMethod!= null) {
                return false;
            }
        } else {
            if (!extCdMethod.equals(other.extCdMethod)) {
                return false;
            }
        }
        if (extDivisionCd == null) {
            if (other.extDivisionCd!= null) {
                return false;
            }
        } else {
            if (!extDivisionCd.equals(other.extDivisionCd)) {
                return false;
            }
        }
        if (extFileseqNo == null) {
            if (other.extFileseqNo!= null) {
                return false;
            }
        } else {
            if (!extFileseqNo.equals(other.extFileseqNo)) {
                return false;
            }
        }
        if (extVendorNum == null) {
            if (other.extVendorNum!= null) {
                return false;
            }
        } else {
            if (!extVendorNum.equals(other.extVendorNum)) {
                return false;
            }
        }
        if (extVendorSite == null) {
            if (other.extVendorSite!= null) {
                return false;
            }
        } else {
            if (!extVendorSite.equals(other.extVendorSite)) {
                return false;
            }
        }
        if (consolFreightVendorId == null) {
            if (other.consolFreightVendorId!= null) {
                return false;
            }
        } else {
            if (!consolFreightVendorId.equals(other.consolFreightVendorId)) {
                return false;
            }
        }
        if (extraReplenDaysOneTime == null) {
            if (other.extraReplenDaysOneTime!= null) {
                return false;
            }
        } else {
            if (!extraReplenDaysOneTime.equals(other.extraReplenDaysOneTime)) {
                return false;
            }
        }
        if (freightMinStrategy == null) {
            if (other.freightMinStrategy!= null) {
                return false;
            }
        } else {
            if (!freightMinStrategy.equals(other.freightMinStrategy)) {
                return false;
            }
        }
        if (freightMinLineItems == null) {
            if (other.freightMinLineItems!= null) {
                return false;
            }
        } else {
            if (!freightMinLineItems.equals(other.freightMinLineItems)) {
                return false;
            }
        }
        if (freightMinUnits == null) {
            if (other.freightMinUnits!= null) {
                return false;
            }
        } else {
            if (!freightMinUnits.equals(other.freightMinUnits)) {
                return false;
            }
        }
        if (freightMinCases == null) {
            if (other.freightMinCases!= null) {
                return false;
            }
        } else {
            if (!freightMinCases.equals(other.freightMinCases)) {
                return false;
            }
        }
        if (freightMinAmt == null) {
            if (other.freightMinAmt!= null) {
                return false;
            }
        } else {
            if (!freightMinAmt.equals(other.freightMinAmt)) {
                return false;
            }
        }
        if (freightMinWeight == null) {
            if (other.freightMinWeight!= null) {
                return false;
            }
        } else {
            if (!freightMinWeight.equals(other.freightMinWeight)) {
                return false;
            }
        }
        if (freightMinCube == null) {
            if (other.freightMinCube!= null) {
                return false;
            }
        } else {
            if (!freightMinCube.equals(other.freightMinCube)) {
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
        if (mandatoryPurchMultFl == null) {
            if (other.mandatoryPurchMultFl!= null) {
                return false;
            }
        } else {
            if (!mandatoryPurchMultFl.equals(other.mandatoryPurchMultFl)) {
                return false;
            }
        }
        if (nextReplenDate == null) {
            if (other.nextReplenDate!= null) {
                return false;
            }
        } else {
            if (!nextReplenDate.equals(other.nextReplenDate)) {
                return false;
            }
        }
        if (orderLimitAmt == null) {
            if (other.orderLimitAmt!= null) {
                return false;
            }
        } else {
            if (!orderLimitAmt.equals(other.orderLimitAmt)) {
                return false;
            }
        }
        if (replenIntervalDays == null) {
            if (other.replenIntervalDays!= null) {
                return false;
            }
        } else {
            if (!replenIntervalDays.equals(other.replenIntervalDays)) {
                return false;
            }
        }
        if (sendEdiFl == null) {
            if (other.sendEdiFl!= null) {
                return false;
            }
        } else {
            if (!sendEdiFl.equals(other.sendEdiFl)) {
                return false;
            }
        }
        if (stdReplenIntervalDays == null) {
            if (other.stdReplenIntervalDays!= null) {
                return false;
            }
        } else {
            if (!stdReplenIntervalDays.equals(other.stdReplenIntervalDays)) {
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
        if (internalVendorFl == null) {
            if (other.internalVendorFl!= null) {
                return false;
            }
        } else {
            if (!internalVendorFl.equals(other.internalVendorFl)) {
                return false;
            }
        }
        if (autoGenerateUpcFl == null) {
            if (other.autoGenerateUpcFl!= null) {
                return false;
            }
        } else {
            if (!autoGenerateUpcFl.equals(other.autoGenerateUpcFl)) {
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
        if (freightTolerancePctFl == null) {
            if (other.freightTolerancePctFl!= null) {
                return false;
            }
        } else {
            if (!freightTolerancePctFl.equals(other.freightTolerancePctFl)) {
                return false;
            }
        }
        if (reconMethod == null) {
            if (other.reconMethod!= null) {
                return false;
            }
        } else {
            if (!reconMethod.equals(other.reconMethod)) {
                return false;
            }
        }
        if (costIncludesFreightFl == null) {
            if (other.costIncludesFreightFl!= null) {
                return false;
            }
        } else {
            if (!costIncludesFreightFl.equals(other.costIncludesFreightFl)) {
                return false;
            }
        }
        if (printFreightFl == null) {
            if (other.printFreightFl!= null) {
                return false;
            }
        } else {
            if (!printFreightFl.equals(other.printFreightFl)) {
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
        if (dsdRcvType == null) {
            if (other.dsdRcvType!= null) {
                return false;
            }
        } else {
            if (!dsdRcvType.equals(other.dsdRcvType)) {
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
        if (externalRefId == null) {
            if (other.externalRefId!= null) {
                return false;
            }
        } else {
            if (!externalRefId.equals(other.externalRefId)) {
                return false;
            }
        }
        if (minPoAmt == null) {
            if (other.minPoAmt!= null) {
                return false;
            }
        } else {
            if (!minPoAmt.equals(other.minPoAmt)) {
                return false;
            }
        }
        if (vendorTypeCd == null) {
            if (other.vendorTypeCd!= null) {
                return false;
            }
        } else {
            if (!vendorTypeCd.equals(other.vendorTypeCd)) {
                return false;
            }
        }
        if (orderAccountId == null) {
            if (other.orderAccountId!= null) {
                return false;
            }
        } else {
            if (!orderAccountId.equals(other.orderAccountId)) {
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
        if (tradeCurrencyId == null) {
            if (other.tradeCurrencyId!= null) {
                return false;
            }
        } else {
            if (!tradeCurrencyId.equals(other.tradeCurrencyId)) {
                return false;
            }
        }
        if (backordersAllowedFl == null) {
            if (other.backordersAllowedFl!= null) {
                return false;
            }
        } else {
            if (!backordersAllowedFl.equals(other.backordersAllowedFl)) {
                return false;
            }
        }
        if (limitRcvdocPerInvoiceFl == null) {
            if (other.limitRcvdocPerInvoiceFl!= null) {
                return false;
            }
        } else {
            if (!limitRcvdocPerInvoiceFl.equals(other.limitRcvdocPerInvoiceFl)) {
                return false;
            }
        }
        if (cancelLead == null) {
            if (other.cancelLead!= null) {
                return false;
            }
        } else {
            if (!cancelLead.equals(other.cancelLead)) {
                return false;
            }
        }
        if (expectLead == null) {
            if (other.expectLead!= null) {
                return false;
            }
        } else {
            if (!expectLead.equals(other.expectLead)) {
                return false;
            }
        }
        if (prepaidFreightAmt == null) {
            if (other.prepaidFreightAmt!= null) {
                return false;
            }
        } else {
            if (!prepaidFreightAmt.equals(other.prepaidFreightAmt)) {
                return false;
            }
        }
        if (prepaidFreightWeight == null) {
            if (other.prepaidFreightWeight!= null) {
                return false;
            }
        } else {
            if (!prepaidFreightWeight.equals(other.prepaidFreightWeight)) {
                return false;
            }
        }
        if (prepaidFreightCube == null) {
            if (other.prepaidFreightCube!= null) {
                return false;
            }
        } else {
            if (!prepaidFreightCube.equals(other.prepaidFreightCube)) {
                return false;
            }
        }
        if (minPackWeight == null) {
            if (other.minPackWeight!= null) {
                return false;
            }
        } else {
            if (!minPackWeight.equals(other.minPackWeight)) {
                return false;
            }
        }
        if (minPackCube == null) {
            if (other.minPackCube!= null) {
                return false;
            }
        } else {
            if (!minPackCube.equals(other.minPackCube)) {
                return false;
            }
        }
        if (vendorDisposition == null) {
            if (other.vendorDisposition!= null) {
                return false;
            }
        } else {
            if (!vendorDisposition.equals(other.vendorDisposition)) {
                return false;
            }
        }
        if (minPoCaseQty == null) {
            if (other.minPoCaseQty!= null) {
                return false;
            }
        } else {
            if (!minPoCaseQty.equals(other.minPoCaseQty)) {
                return false;
            }
        }
        if (termCode == null) {
            if (other.termCode!= null) {
                return false;
            }
        } else {
            if (!termCode.equals(other.termCode)) {
                return false;
            }
        }
        if (dueDt == null) {
            if (other.dueDt!= null) {
                return false;
            }
        } else {
            if (!dueDt.equals(other.dueDt)) {
                return false;
            }
        }
        if (costRequiredFl == null) {
            if (other.costRequiredFl!= null) {
                return false;
            }
        } else {
            if (!costRequiredFl.equals(other.costRequiredFl)) {
                return false;
            }
        }
        if (rtvAllowedFl == null) {
            if (other.rtvAllowedFl!= null) {
                return false;
            }
        } else {
            if (!rtvAllowedFl.equals(other.rtvAllowedFl)) {
                return false;
            }
        }
        if (orderInstructions == null) {
            if (other.orderInstructions!= null) {
                return false;
            }
        } else {
            if (!orderInstructions.equals(other.orderInstructions)) {
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
        if (currencyCd == null) {
            if (other.currencyCd!= null) {
                return false;
            }
        } else {
            if (!currencyCd.equals(other.currencyCd)) {
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
        if (lockLeadTimeFl == null) {
            if (other.lockLeadTimeFl!= null) {
                return false;
            }
        } else {
            if (!lockLeadTimeFl.equals(other.lockLeadTimeFl)) {
                return false;
            }
        }
        if (dateLastCalculated == null) {
            if (other.dateLastCalculated!= null) {
                return false;
            }
        } else {
            if (!dateLastCalculated.equals(other.dateLastCalculated)) {
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
        final VendorEntity other = ((VendorEntity) obj);
        // Identifier level comparison
        if (this.identifier.getVendorId() == null) {
            return false;
        } else {
            if (!this.identifier.getVendorId().equals(other.identifier.getVendorId())) {
                return false;
            }
        }
        // Data level comparison
        if (name == null) {
            if (other.name!= null) {
                return false;
            }
        } else {
            if (!name.equals(other.name)) {
                return false;
            }
        }
        if (adr1 == null) {
            if (other.adr1 != null) {
                return false;
            }
        } else {
            if (!adr1 .equals(other.adr1)) {
                return false;
            }
        }
        if (adr2 == null) {
            if (other.adr2 != null) {
                return false;
            }
        } else {
            if (!adr2 .equals(other.adr2)) {
                return false;
            }
        }
        if (city == null) {
            if (other.city!= null) {
                return false;
            }
        } else {
            if (!city.equals(other.city)) {
                return false;
            }
        }
        if (state == null) {
            if (other.state!= null) {
                return false;
            }
        } else {
            if (!state.equals(other.state)) {
                return false;
            }
        }
        if (zip == null) {
            if (other.zip!= null) {
                return false;
            }
        } else {
            if (!zip.equals(other.zip)) {
                return false;
            }
        }
        if (country == null) {
            if (other.country!= null) {
                return false;
            }
        } else {
            if (!country.equals(other.country)) {
                return false;
            }
        }
        if (contact == null) {
            if (other.contact!= null) {
                return false;
            }
        } else {
            if (!contact.equals(other.contact)) {
                return false;
            }
        }
        if (contact2 == null) {
            if (other.contact2 != null) {
                return false;
            }
        } else {
            if (!contact2 .equals(other.contact2)) {
                return false;
            }
        }
        if (phoneNo == null) {
            if (other.phoneNo!= null) {
                return false;
            }
        } else {
            if (!phoneNo.equals(other.phoneNo)) {
                return false;
            }
        }
        if (phone2No == null) {
            if (other.phone2No!= null) {
                return false;
            }
        } else {
            if (!phone2No.equals(other.phone2No)) {
                return false;
            }
        }
        if (faxNo == null) {
            if (other.faxNo!= null) {
                return false;
            }
        } else {
            if (!faxNo.equals(other.faxNo)) {
                return false;
            }
        }
        if (emailAddress == null) {
            if (other.emailAddress!= null) {
                return false;
            }
        } else {
            if (!emailAddress.equals(other.emailAddress)) {
                return false;
            }
        }
        if (apAdr1 == null) {
            if (other.apAdr1 != null) {
                return false;
            }
        } else {
            if (!apAdr1 .equals(other.apAdr1)) {
                return false;
            }
        }
        if (apAdr2 == null) {
            if (other.apAdr2 != null) {
                return false;
            }
        } else {
            if (!apAdr2 .equals(other.apAdr2)) {
                return false;
            }
        }
        if (apCity == null) {
            if (other.apCity!= null) {
                return false;
            }
        } else {
            if (!apCity.equals(other.apCity)) {
                return false;
            }
        }
        if (apState == null) {
            if (other.apState!= null) {
                return false;
            }
        } else {
            if (!apState.equals(other.apState)) {
                return false;
            }
        }
        if (apZip == null) {
            if (other.apZip!= null) {
                return false;
            }
        } else {
            if (!apZip.equals(other.apZip)) {
                return false;
            }
        }
        if (apCountry == null) {
            if (other.apCountry!= null) {
                return false;
            }
        } else {
            if (!apCountry.equals(other.apCountry)) {
                return false;
            }
        }
        if (apFlowFl == null) {
            if (other.apFlowFl!= null) {
                return false;
            }
        } else {
            if (!apFlowFl.equals(other.apFlowFl)) {
                return false;
            }
        }
        if (apNumber == null) {
            if (other.apNumber!= null) {
                return false;
            }
        } else {
            if (!apNumber.equals(other.apNumber)) {
                return false;
            }
        }
        if (paymentTermPct1 == null) {
            if (other.paymentTermPct1 != null) {
                return false;
            }
        } else {
            if (!paymentTermPct1 .equals(other.paymentTermPct1)) {
                return false;
            }
        }
        if (paymentTermPct2 == null) {
            if (other.paymentTermPct2 != null) {
                return false;
            }
        } else {
            if (!paymentTermPct2 .equals(other.paymentTermPct2)) {
                return false;
            }
        }
        if (paymentTermDays1 == null) {
            if (other.paymentTermDays1 != null) {
                return false;
            }
        } else {
            if (!paymentTermDays1 .equals(other.paymentTermDays1)) {
                return false;
            }
        }
        if (paymentTermDays2 == null) {
            if (other.paymentTermDays2 != null) {
                return false;
            }
        } else {
            if (!paymentTermDays2 .equals(other.paymentTermDays2)) {
                return false;
            }
        }
        if (payStatusFl == null) {
            if (other.payStatusFl!= null) {
                return false;
            }
        } else {
            if (!payStatusFl.equals(other.payStatusFl)) {
                return false;
            }
        }
        if (netDays == null) {
            if (other.netDays!= null) {
                return false;
            }
        } else {
            if (!netDays.equals(other.netDays)) {
                return false;
            }
        }
        if (orderStatusFl == null) {
            if (other.orderStatusFl!= null) {
                return false;
            }
        } else {
            if (!orderStatusFl.equals(other.orderStatusFl)) {
                return false;
            }
        }
        if (lastInvoiceDt == null) {
            if (other.lastInvoiceDt!= null) {
                return false;
            }
        } else {
            if (!lastInvoiceDt.equals(other.lastInvoiceDt)) {
                return false;
            }
        }
        if (lastPaymentDt == null) {
            if (other.lastPaymentDt!= null) {
                return false;
            }
        } else {
            if (!lastPaymentDt.equals(other.lastPaymentDt)) {
                return false;
            }
        }
        if (shipVia == null) {
            if (other.shipVia!= null) {
                return false;
            }
        } else {
            if (!shipVia.equals(other.shipVia)) {
                return false;
            }
        }
        if (shipTerms == null) {
            if (other.shipTerms!= null) {
                return false;
            }
        } else {
            if (!shipTerms.equals(other.shipTerms)) {
                return false;
            }
        }
        if (fob == null) {
            if (other.fob!= null) {
                return false;
            }
        } else {
            if (!fob.equals(other.fob)) {
                return false;
            }
        }
        if (commentTxt == null) {
            if (other.commentTxt!= null) {
                return false;
            }
        } else {
            if (!commentTxt.equals(other.commentTxt)) {
                return false;
            }
        }
        if (shipInstTxt == null) {
            if (other.shipInstTxt!= null) {
                return false;
            }
        } else {
            if (!shipInstTxt.equals(other.shipInstTxt)) {
                return false;
            }
        }
        if (ediAgent == null) {
            if (other.ediAgent!= null) {
                return false;
            }
        } else {
            if (!ediAgent.equals(other.ediAgent)) {
                return false;
            }
        }
        if (ediType == null) {
            if (other.ediType!= null) {
                return false;
            }
        } else {
            if (!ediType.equals(other.ediType)) {
                return false;
            }
        }
        if (calcNetCostFl == null) {
            if (other.calcNetCostFl!= null) {
                return false;
            }
        } else {
            if (!calcNetCostFl.equals(other.calcNetCostFl)) {
                return false;
            }
        }
        if (primaryControlFl == null) {
            if (other.primaryControlFl!= null) {
                return false;
            }
        } else {
            if (!primaryControlFl.equals(other.primaryControlFl)) {
                return false;
            }
        }
        if (primaryControlNToYFl == null) {
            if (other.primaryControlNToYFl!= null) {
                return false;
            }
        } else {
            if (!primaryControlNToYFl.equals(other.primaryControlNToYFl)) {
                return false;
            }
        }
        if (dunsNo == null) {
            if (other.dunsNo!= null) {
                return false;
            }
        } else {
            if (!dunsNo.equals(other.dunsNo)) {
                return false;
            }
        }
        if (commId == null) {
            if (other.commId!= null) {
                return false;
            }
        } else {
            if (!commId.equals(other.commId)) {
                return false;
            }
        }
        if (extCdMethod == null) {
            if (other.extCdMethod!= null) {
                return false;
            }
        } else {
            if (!extCdMethod.equals(other.extCdMethod)) {
                return false;
            }
        }
        if (extDivisionCd == null) {
            if (other.extDivisionCd!= null) {
                return false;
            }
        } else {
            if (!extDivisionCd.equals(other.extDivisionCd)) {
                return false;
            }
        }
        if (extFileseqNo == null) {
            if (other.extFileseqNo!= null) {
                return false;
            }
        } else {
            if (!extFileseqNo.equals(other.extFileseqNo)) {
                return false;
            }
        }
        if (extVendorNum == null) {
            if (other.extVendorNum!= null) {
                return false;
            }
        } else {
            if (!extVendorNum.equals(other.extVendorNum)) {
                return false;
            }
        }
        if (extVendorSite == null) {
            if (other.extVendorSite!= null) {
                return false;
            }
        } else {
            if (!extVendorSite.equals(other.extVendorSite)) {
                return false;
            }
        }
        if (consolFreightVendorId == null) {
            if (other.consolFreightVendorId!= null) {
                return false;
            }
        } else {
            if (!consolFreightVendorId.equals(other.consolFreightVendorId)) {
                return false;
            }
        }
        if (extraReplenDaysOneTime == null) {
            if (other.extraReplenDaysOneTime!= null) {
                return false;
            }
        } else {
            if (!extraReplenDaysOneTime.equals(other.extraReplenDaysOneTime)) {
                return false;
            }
        }
        if (freightMinStrategy == null) {
            if (other.freightMinStrategy!= null) {
                return false;
            }
        } else {
            if (!freightMinStrategy.equals(other.freightMinStrategy)) {
                return false;
            }
        }
        if (freightMinLineItems == null) {
            if (other.freightMinLineItems!= null) {
                return false;
            }
        } else {
            if (!freightMinLineItems.equals(other.freightMinLineItems)) {
                return false;
            }
        }
        if (freightMinUnits == null) {
            if (other.freightMinUnits!= null) {
                return false;
            }
        } else {
            if (!freightMinUnits.equals(other.freightMinUnits)) {
                return false;
            }
        }
        if (freightMinCases == null) {
            if (other.freightMinCases!= null) {
                return false;
            }
        } else {
            if (!freightMinCases.equals(other.freightMinCases)) {
                return false;
            }
        }
        if (freightMinAmt == null) {
            if (other.freightMinAmt!= null) {
                return false;
            }
        } else {
            if (!freightMinAmt.equals(other.freightMinAmt)) {
                return false;
            }
        }
        if (freightMinWeight == null) {
            if (other.freightMinWeight!= null) {
                return false;
            }
        } else {
            if (!freightMinWeight.equals(other.freightMinWeight)) {
                return false;
            }
        }
        if (freightMinCube == null) {
            if (other.freightMinCube!= null) {
                return false;
            }
        } else {
            if (!freightMinCube.equals(other.freightMinCube)) {
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
        if (mandatoryPurchMultFl == null) {
            if (other.mandatoryPurchMultFl!= null) {
                return false;
            }
        } else {
            if (!mandatoryPurchMultFl.equals(other.mandatoryPurchMultFl)) {
                return false;
            }
        }
        if (nextReplenDate == null) {
            if (other.nextReplenDate!= null) {
                return false;
            }
        } else {
            if (!nextReplenDate.equals(other.nextReplenDate)) {
                return false;
            }
        }
        if (orderLimitAmt == null) {
            if (other.orderLimitAmt!= null) {
                return false;
            }
        } else {
            if (!orderLimitAmt.equals(other.orderLimitAmt)) {
                return false;
            }
        }
        if (replenIntervalDays == null) {
            if (other.replenIntervalDays!= null) {
                return false;
            }
        } else {
            if (!replenIntervalDays.equals(other.replenIntervalDays)) {
                return false;
            }
        }
        if (sendEdiFl == null) {
            if (other.sendEdiFl!= null) {
                return false;
            }
        } else {
            if (!sendEdiFl.equals(other.sendEdiFl)) {
                return false;
            }
        }
        if (stdReplenIntervalDays == null) {
            if (other.stdReplenIntervalDays!= null) {
                return false;
            }
        } else {
            if (!stdReplenIntervalDays.equals(other.stdReplenIntervalDays)) {
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
        if (internalVendorFl == null) {
            if (other.internalVendorFl!= null) {
                return false;
            }
        } else {
            if (!internalVendorFl.equals(other.internalVendorFl)) {
                return false;
            }
        }
        if (autoGenerateUpcFl == null) {
            if (other.autoGenerateUpcFl!= null) {
                return false;
            }
        } else {
            if (!autoGenerateUpcFl.equals(other.autoGenerateUpcFl)) {
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
        if (freightTolerancePctFl == null) {
            if (other.freightTolerancePctFl!= null) {
                return false;
            }
        } else {
            if (!freightTolerancePctFl.equals(other.freightTolerancePctFl)) {
                return false;
            }
        }
        if (reconMethod == null) {
            if (other.reconMethod!= null) {
                return false;
            }
        } else {
            if (!reconMethod.equals(other.reconMethod)) {
                return false;
            }
        }
        if (costIncludesFreightFl == null) {
            if (other.costIncludesFreightFl!= null) {
                return false;
            }
        } else {
            if (!costIncludesFreightFl.equals(other.costIncludesFreightFl)) {
                return false;
            }
        }
        if (printFreightFl == null) {
            if (other.printFreightFl!= null) {
                return false;
            }
        } else {
            if (!printFreightFl.equals(other.printFreightFl)) {
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
        if (dsdRcvType == null) {
            if (other.dsdRcvType!= null) {
                return false;
            }
        } else {
            if (!dsdRcvType.equals(other.dsdRcvType)) {
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
        if (externalRefId == null) {
            if (other.externalRefId!= null) {
                return false;
            }
        } else {
            if (!externalRefId.equals(other.externalRefId)) {
                return false;
            }
        }
        if (minPoAmt == null) {
            if (other.minPoAmt!= null) {
                return false;
            }
        } else {
            if (!minPoAmt.equals(other.minPoAmt)) {
                return false;
            }
        }
        if (vendorTypeCd == null) {
            if (other.vendorTypeCd!= null) {
                return false;
            }
        } else {
            if (!vendorTypeCd.equals(other.vendorTypeCd)) {
                return false;
            }
        }
        if (orderAccountId == null) {
            if (other.orderAccountId!= null) {
                return false;
            }
        } else {
            if (!orderAccountId.equals(other.orderAccountId)) {
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
        if (tradeCurrencyId == null) {
            if (other.tradeCurrencyId!= null) {
                return false;
            }
        } else {
            if (!tradeCurrencyId.equals(other.tradeCurrencyId)) {
                return false;
            }
        }
        if (backordersAllowedFl == null) {
            if (other.backordersAllowedFl!= null) {
                return false;
            }
        } else {
            if (!backordersAllowedFl.equals(other.backordersAllowedFl)) {
                return false;
            }
        }
        if (limitRcvdocPerInvoiceFl == null) {
            if (other.limitRcvdocPerInvoiceFl!= null) {
                return false;
            }
        } else {
            if (!limitRcvdocPerInvoiceFl.equals(other.limitRcvdocPerInvoiceFl)) {
                return false;
            }
        }
        if (cancelLead == null) {
            if (other.cancelLead!= null) {
                return false;
            }
        } else {
            if (!cancelLead.equals(other.cancelLead)) {
                return false;
            }
        }
        if (expectLead == null) {
            if (other.expectLead!= null) {
                return false;
            }
        } else {
            if (!expectLead.equals(other.expectLead)) {
                return false;
            }
        }
        if (prepaidFreightAmt == null) {
            if (other.prepaidFreightAmt!= null) {
                return false;
            }
        } else {
            if (!prepaidFreightAmt.equals(other.prepaidFreightAmt)) {
                return false;
            }
        }
        if (prepaidFreightWeight == null) {
            if (other.prepaidFreightWeight!= null) {
                return false;
            }
        } else {
            if (!prepaidFreightWeight.equals(other.prepaidFreightWeight)) {
                return false;
            }
        }
        if (prepaidFreightCube == null) {
            if (other.prepaidFreightCube!= null) {
                return false;
            }
        } else {
            if (!prepaidFreightCube.equals(other.prepaidFreightCube)) {
                return false;
            }
        }
        if (minPackWeight == null) {
            if (other.minPackWeight!= null) {
                return false;
            }
        } else {
            if (!minPackWeight.equals(other.minPackWeight)) {
                return false;
            }
        }
        if (minPackCube == null) {
            if (other.minPackCube!= null) {
                return false;
            }
        } else {
            if (!minPackCube.equals(other.minPackCube)) {
                return false;
            }
        }
        if (vendorDisposition == null) {
            if (other.vendorDisposition!= null) {
                return false;
            }
        } else {
            if (!vendorDisposition.equals(other.vendorDisposition)) {
                return false;
            }
        }
        if (minPoCaseQty == null) {
            if (other.minPoCaseQty!= null) {
                return false;
            }
        } else {
            if (!minPoCaseQty.equals(other.minPoCaseQty)) {
                return false;
            }
        }
        if (termCode == null) {
            if (other.termCode!= null) {
                return false;
            }
        } else {
            if (!termCode.equals(other.termCode)) {
                return false;
            }
        }
        if (dueDt == null) {
            if (other.dueDt!= null) {
                return false;
            }
        } else {
            if (!dueDt.equals(other.dueDt)) {
                return false;
            }
        }
        if (costRequiredFl == null) {
            if (other.costRequiredFl!= null) {
                return false;
            }
        } else {
            if (!costRequiredFl.equals(other.costRequiredFl)) {
                return false;
            }
        }
        if (rtvAllowedFl == null) {
            if (other.rtvAllowedFl!= null) {
                return false;
            }
        } else {
            if (!rtvAllowedFl.equals(other.rtvAllowedFl)) {
                return false;
            }
        }
        if (orderInstructions == null) {
            if (other.orderInstructions!= null) {
                return false;
            }
        } else {
            if (!orderInstructions.equals(other.orderInstructions)) {
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
        if (currencyCd == null) {
            if (other.currencyCd!= null) {
                return false;
            }
        } else {
            if (!currencyCd.equals(other.currencyCd)) {
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
        if (lockLeadTimeFl == null) {
            if (other.lockLeadTimeFl!= null) {
                return false;
            }
        } else {
            if (!lockLeadTimeFl.equals(other.lockLeadTimeFl)) {
                return false;
            }
        }
        if (dateLastCalculated == null) {
            if (other.dateLastCalculated!= null) {
                return false;
            }
        } else {
            if (!dateLastCalculated.equals(other.dateLastCalculated)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        // If any ID columns are null, compare just the object instance it self.  This preserves objects that have not been assigned ID's yet.
        if (this.identifier.getVendorId() == null) {
            return super.hashCode();
        }
        	
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier.getVendorId() == null) ? 0 : identifier.getVendorId().hashCode());
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
    public int compareTo(VendorEntity other) {
        return this.identifier.compareTo(other.identifier);
    }

    /**
     * This method displays bean properties for debugging purposes.  Do not use for anything else as it may change to enhance debugging in the future.
     * 
     */
    public String toString() {
        final StringBuilder temp = new StringBuilder();
        	
        temp.append("\n");
        temp.append("VendorEntity (VENDOR)\n");
        temp.append("  ID   1 : vendorId                       : VENDOR_ID                      : ").append(this.identifier.getVendorId()).append("\n");
        temp.append("       2 : name                           : NAME                           : ").append(name).append("\n");
        temp.append("       3 : adr1                           : ADR1                           : ").append(adr1).append("\n");
        temp.append("       4 : adr2                           : ADR2                           : ").append(adr2).append("\n");
        temp.append("       5 : city                           : CITY                           : ").append(city).append("\n");
        temp.append("       6 : state                          : STATE                          : ").append(state).append("\n");
        temp.append("       7 : zip                            : ZIP                            : ").append(zip).append("\n");
        temp.append("       8 : country                        : COUNTRY                        : ").append(country).append("\n");
        temp.append("       9 : contact                        : CONTACT                        : ").append(contact).append("\n");
        temp.append("      10 : contact2                       : CONTACT2                       : ").append(contact2).append("\n");
        temp.append("      11 : phoneNo                        : PHONE_NO                       : ").append(phoneNo).append("\n");
        temp.append("      12 : phone2No                       : PHONE2_NO                      : ").append(phone2No).append("\n");
        temp.append("      13 : faxNo                          : FAX_NO                         : ").append(faxNo).append("\n");
        temp.append("      14 : emailAddress                   : EMAIL_ADDRESS                  : ").append(emailAddress).append("\n");
        temp.append("      15 : apAdr1                         : AP_ADR1                        : ").append(apAdr1).append("\n");
        temp.append("      16 : apAdr2                         : AP_ADR2                        : ").append(apAdr2).append("\n");
        temp.append("      17 : apCity                         : AP_CITY                        : ").append(apCity).append("\n");
        temp.append("      18 : apState                        : AP_STATE                       : ").append(apState).append("\n");
        temp.append("      19 : apZip                          : AP_ZIP                         : ").append(apZip).append("\n");
        temp.append("      20 : apCountry                      : AP_COUNTRY                     : ").append(apCountry).append("\n");
        temp.append("      21 : apFlowFl                       : AP_FLOW_FL                     : ").append(apFlowFl).append("\n");
        temp.append("      22 : apNumber                       : AP_NUMBER                      : ").append(apNumber).append("\n");
        temp.append("      23 : paymentTermPct1                : PAYMENT_TERM_PCT1              : ").append(paymentTermPct1).append("\n");
        temp.append("      24 : paymentTermPct2                : PAYMENT_TERM_PCT2              : ").append(paymentTermPct2).append("\n");
        temp.append("      25 : paymentTermDays1               : PAYMENT_TERM_DAYS1             : ").append(paymentTermDays1).append("\n");
        temp.append("      26 : paymentTermDays2               : PAYMENT_TERM_DAYS2             : ").append(paymentTermDays2).append("\n");
        temp.append("      27 : payStatusFl                    : PAY_STATUS_FL                  : ").append(payStatusFl).append("\n");
        temp.append("      28 : netDays                        : NET_DAYS                       : ").append(netDays).append("\n");
        temp.append("      29 : orderStatusFl                  : ORDER_STATUS_FL                : ").append(orderStatusFl).append("\n");
        temp.append("      30 : lastInvoiceDt                  : LAST_INVOICE_DT                : ").append(lastInvoiceDt).append("\n");
        temp.append("      31 : lastPaymentDt                  : LAST_PAYMENT_DT                : ").append(lastPaymentDt).append("\n");
        temp.append("      32 : shipVia                        : SHIP_VIA                       : ").append(shipVia).append("\n");
        temp.append("      33 : shipTerms                      : SHIP_TERMS                     : ").append(shipTerms).append("\n");
        temp.append("      34 : fob                            : FOB                            : ").append(fob).append("\n");
        temp.append("      35 : commentTxt                     : COMMENT_TXT                    : ").append(commentTxt).append("\n");
        temp.append("      36 : shipInstTxt                    : SHIP_INST_TXT                  : ").append(shipInstTxt).append("\n");
        temp.append("      37 : ediAgent                       : EDI_AGENT                      : ").append(ediAgent).append("\n");
        temp.append("      38 : ediType                        : EDI_TYPE                       : ").append(ediType).append("\n");
        temp.append("      39 : calcNetCostFl                  : CALC_NET_COST_FL               : ").append(calcNetCostFl).append("\n");
        temp.append("      40 : primaryControlFl               : PRIMARY_CONTROL_FL             : ").append(primaryControlFl).append("\n");
        temp.append("      41 : primaryControlNToYFl           : PRIMARY_CONTROL_N_TO_Y_FL      : ").append(primaryControlNToYFl).append("\n");
        temp.append("      42 : dunsNo                         : DUNS_NO                        : ").append(dunsNo).append("\n");
        temp.append("      43 : commId                         : COMM_ID                        : ").append(commId).append("\n");
        temp.append("      44 : extCdMethod                    : EXT_CD_METHOD                  : ").append(extCdMethod).append("\n");
        temp.append("      45 : extDivisionCd                  : EXT_DIVISION_CD                : ").append(extDivisionCd).append("\n");
        temp.append("      46 : extFileseqNo                   : EXT_FILESEQ_NO                 : ").append(extFileseqNo).append("\n");
        temp.append("      47 : extVendorNum                   : EXT_VENDOR_NUM                 : ").append(extVendorNum).append("\n");
        temp.append("      48 : extVendorSite                  : EXT_VENDOR_SITE                : ").append(extVendorSite).append("\n");
        temp.append("      49 : consolFreightVendorId          : CONSOL_FREIGHT_VENDOR_ID       : ").append(consolFreightVendorId).append("\n");
        temp.append("      50 : extraReplenDaysOneTime         : EXTRA_REPLEN_DAYS_ONE_TIME     : ").append(extraReplenDaysOneTime).append("\n");
        temp.append("      51 : freightMinStrategy             : FREIGHT_MIN_STRATEGY           : ").append(freightMinStrategy).append("\n");
        temp.append("      52 : freightMinLineItems            : FREIGHT_MIN_LINE_ITEMS         : ").append(freightMinLineItems).append("\n");
        temp.append("      53 : freightMinUnits                : FREIGHT_MIN_UNITS              : ").append(freightMinUnits).append("\n");
        temp.append("      54 : freightMinCases                : FREIGHT_MIN_CASES              : ").append(freightMinCases).append("\n");
        temp.append("      55 : freightMinAmt                  : FREIGHT_MIN_AMT                : ").append(freightMinAmt).append("\n");
        temp.append("      56 : freightMinWeight               : FREIGHT_MIN_WEIGHT             : ").append(freightMinWeight).append("\n");
        temp.append("      57 : freightMinCube                 : FREIGHT_MIN_CUBE               : ").append(freightMinCube).append("\n");
        temp.append("      58 : freightTolerancePct            : FREIGHT_TOLERANCE_PCT          : ").append(freightTolerancePct).append("\n");
        temp.append("      59 : mandatoryPurchMultFl           : MANDATORY_PURCH_MULT_FL        : ").append(mandatoryPurchMultFl).append("\n");
        temp.append("      60 : nextReplenDate                 : NEXT_REPLEN_DATE               : ").append(nextReplenDate).append("\n");
        temp.append("      61 : orderLimitAmt                  : ORDER_LIMIT_AMT                : ").append(orderLimitAmt).append("\n");
        temp.append("      62 : replenIntervalDays             : REPLEN_INTERVAL_DAYS           : ").append(replenIntervalDays).append("\n");
        temp.append("      63 : sendEdiFl                      : SEND_EDI_FL                    : ").append(sendEdiFl).append("\n");
        temp.append("      64 : stdReplenIntervalDays          : STD_REPLEN_INTERVAL_DAYS       : ").append(stdReplenIntervalDays).append("\n");
        temp.append("      65 : siteNo                         : SITE_NO                        : ").append(siteNo).append("\n");
        temp.append("      66 : internalVendorFl               : INTERNAL_VENDOR_FL             : ").append(internalVendorFl).append("\n");
        temp.append("      67 : autoGenerateUpcFl              : AUTO_GENERATE_UPC_FL           : ").append(autoGenerateUpcFl).append("\n");
        temp.append("      68 : invToleranceQty                : INV_TOLERANCE_QTY              : ").append(invToleranceQty).append("\n");
        temp.append("      69 : invToleranceQtyFl              : INV_TOLERANCE_QTY_FL           : ").append(invToleranceQtyFl).append("\n");
        temp.append("      70 : invTolerancePct                : INV_TOLERANCE_PCT              : ").append(invTolerancePct).append("\n");
        temp.append("      71 : invTolerancePctFl              : INV_TOLERANCE_PCT_FL           : ").append(invTolerancePctFl).append("\n");
        temp.append("      72 : invToleranceAmt                : INV_TOLERANCE_AMT              : ").append(invToleranceAmt).append("\n");
        temp.append("      73 : invToleranceAmtFl              : INV_TOLERANCE_AMT_FL           : ").append(invToleranceAmtFl).append("\n");
        temp.append("      74 : freightToleranceAmt            : FREIGHT_TOLERANCE_AMT          : ").append(freightToleranceAmt).append("\n");
        temp.append("      75 : freightToleranceAmtFl          : FREIGHT_TOLERANCE_AMT_FL       : ").append(freightToleranceAmtFl).append("\n");
        temp.append("      76 : freightTolerancePctFl          : FREIGHT_TOLERANCE_PCT_FL       : ").append(freightTolerancePctFl).append("\n");
        temp.append("      77 : reconMethod                    : RECON_METHOD                   : ").append(reconMethod).append("\n");
        temp.append("      78 : costIncludesFreightFl          : COST_INCLUDES_FREIGHT_FL       : ").append(costIncludesFreightFl).append("\n");
        temp.append("      79 : printFreightFl                 : PRINT_FREIGHT_FL               : ").append(printFreightFl).append("\n");
        temp.append("      80 : leadTime                       : LEAD_TIME                      : ").append(leadTime).append("\n");
        temp.append("      81 : dsdRcvType                     : DSD_RCV_TYPE                   : ").append(dsdRcvType).append("\n");
        temp.append("      82 : replicationNo                  : REPLICATION_NO                 : ").append(replicationNo).append("\n");
        temp.append("      83 : operationType                  : OPERATION_TYPE                 : ").append(operationType).append("\n");
        temp.append("  AD  84 : dateChanged                    : DATE_CHANGED                   : ").append(dateChanged).append("\n");
        temp.append("      85 : registerReplicationNo          : REGISTER_REPLICATION_NO        : ").append(registerReplicationNo).append("\n");
        temp.append("      86 : externalRefId                  : EXTERNAL_REF_ID                : ").append(externalRefId).append("\n");
        temp.append("      87 : minPoAmt                       : MIN_PO_AMT                     : ").append(minPoAmt).append("\n");
        temp.append("      88 : vendorTypeCd                   : VENDOR_TYPE_CD                 : ").append(vendorTypeCd).append("\n");
        temp.append("      89 : orderAccountId                 : ORDER_ACCOUNT_ID               : ").append(orderAccountId).append("\n");
        temp.append("  AD  90 : dateCreated                    : DATE_CREATED                   : ").append(dateCreated).append("\n");
        temp.append("  AD  91 : userCreated                    : USER_CREATED                   : ").append(userCreated).append("\n");
        temp.append("  AD  92 : dateModified                   : DATE_MODIFIED                  : ").append(dateModified).append("\n");
        temp.append("  AD  93 : userModified                   : USER_MODIFIED                  : ").append(userModified).append("\n");
        temp.append("      94 : employeeId                     : EMPLOYEE_ID                    : ").append(employeeId).append("\n");
        temp.append("      95 : tradeCurrencyId                : TRADE_CURRENCY_ID              : ").append(tradeCurrencyId).append("\n");
        temp.append("      96 : backordersAllowedFl            : BACKORDERS_ALLOWED_FL          : ").append(backordersAllowedFl).append("\n");
        temp.append("      97 : limitRcvdocPerInvoiceFl        : LIMIT_RCVDOC_PER_INVOICE_FL    : ").append(limitRcvdocPerInvoiceFl).append("\n");
        temp.append("      98 : cancelLead                     : CANCEL_LEAD                    : ").append(cancelLead).append("\n");
        temp.append("      99 : expectLead                     : EXPECT_LEAD                    : ").append(expectLead).append("\n");
        temp.append("     100 : prepaidFreightAmt              : PREPAID_FREIGHT_AMT            : ").append(prepaidFreightAmt).append("\n");
        temp.append("     101 : prepaidFreightWeight           : PREPAID_FREIGHT_WEIGHT         : ").append(prepaidFreightWeight).append("\n");
        temp.append("     102 : prepaidFreightCube             : PREPAID_FREIGHT_CUBE           : ").append(prepaidFreightCube).append("\n");
        temp.append("     103 : minPackWeight                  : MIN_PACK_WEIGHT                : ").append(minPackWeight).append("\n");
        temp.append("     104 : minPackCube                    : MIN_PACK_CUBE                  : ").append(minPackCube).append("\n");
        temp.append("     105 : vendorDisposition              : VENDOR_DISPOSITION             : ").append(vendorDisposition).append("\n");
        temp.append("     106 : minPoCaseQty                   : MIN_PO_CASE_QTY                : ").append(minPoCaseQty).append("\n");
        temp.append("     107 : termCode                       : TERM_CODE                      : ").append(termCode).append("\n");
        temp.append("     108 : dueDt                          : DUE_DT                         : ").append(dueDt).append("\n");
        temp.append("     109 : costRequiredFl                 : COST_REQUIRED_FL               : ").append(costRequiredFl).append("\n");
        temp.append("     110 : rtvAllowedFl                   : RTV_ALLOWED_FL                 : ").append(rtvAllowedFl).append("\n");
        temp.append("     111 : orderInstructions              : ORDER_INSTRUCTIONS             : ").append(orderInstructions).append("\n");
        temp.append("     112 : landedCostElements             : LANDED_COST_ELEMENTS           : ").append(landedCostElements).append("\n");
        temp.append("     113 : currencyCd                     : CURRENCY_CD                    : ").append(currencyCd).append("\n");
        temp.append("     114 : ownerId                        : OWNER_ID                       : ").append(ownerId).append("\n");
        temp.append("     115 : lockLeadTimeFl                 : LOCK_LEAD_TIME_FL              : ").append(lockLeadTimeFl).append("\n");
        temp.append("     116 : dateLastCalculated             : DATE_LAST_CALCULATED           : ").append(dateLastCalculated).append("\n");
        	
        return temp.toString();
    }

}
