//
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
import com.tomax.vendor.dto.VenSiteDTO;
import com.tomax.vendor.dto.VenSiteDTOId;



@SuppressWarnings("all")
//@NamedQueries({
//    @NamedQuery(name = "VENDOR_BY_SITE_NO", query = "Select v from VenSiteEntity v where v.siteNo = :siteNo "),
//    @NamedQuery(name = "VENSIT_BY_VENDOR", query = "Select v from VenSiteEntity v where v.vendorId = :vendorId "),
//    @NamedQuery(name = "VENDOR_BY_ASN_RCV_ALLOWED_FL", query = "Select v from VenSiteEntity v where v.asnRcvAllowedFl = :asnRcvAllowedFl "),
//    @NamedQuery(name = "VENDOR_BY_SITE_AND_ASN", query = "Select v from VenSiteEntity v where v.asnRcvAllowedFl = :asnRcvAllowedFl And v.siteNo = :siteNo "),
//    @NamedQuery(name = "VENSIT_BY_LST_IDS", query = "Select v from VenSiteEntity v where v.identifier In (:ids) order by v.primaryVendorFl desc ")
//})
@Entity
@Table(name = "VEN_SITE")
public class VenSiteEntity
    extends RNetAbstractEntity<VenSiteEntity, VenSiteDTO>
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
    private VenSiteEntityId identifier;
    /**
     * <p>Maps to table column: {@code DSD_TYPE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "DSD_TYPE should contain less than or equal to 1 characters")
    @NotNull(message = "DSD_TYPE can not be null")
    @Column(name = "DSD_TYPE")
    private String dsdType;
    /**
     * <p>Maps to table column: {@code RECEIVE_LEVEL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "RECEIVE_LEVEL should contain less than or equal to 1 characters")
    @Column(name = "RECEIVE_LEVEL")
    private String receiveLevel;
    /**
     * <p>Maps to table column: {@code INVOICE_ROUNDING_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "INVOICE_ROUNDING_FL should contain less than or equal to 1 characters")
    @NotNull(message = "INVOICE_ROUNDING_FL can not be null")
    @Column(name = "INVOICE_ROUNDING_FL")
    private String invoiceRoundingFl;
    /**
     * <p>Maps to table column: {@code RECEIVE_EXCEPTION_QTY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "RECEIVE_EXCEPTION_QTY should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "RECEIVE_EXCEPTION_QTY")
    private BigDecimal receiveExceptionQty;
    /**
     * <p>Maps to table column: {@code RECEIVE_EXCEPTION_AMT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "RECEIVE_EXCEPTION_AMT should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "RECEIVE_EXCEPTION_AMT")
    private BigDecimal receiveExceptionAmt;
    /**
     * <p>Maps to table column: {@code EXT_CUST_ENTITY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 4, message = "EXT_CUST_ENTITY should contain less than or equal to 4 characters")
    @Column(name = "EXT_CUST_ENTITY")
    private String extCustEntity;
    /**
     * <p>Maps to table column: {@code EXT_CUST_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 5, message = "EXT_CUST_ID should contain less than or equal to 5 characters")
    @Column(name = "EXT_CUST_ID")
    private String extCustId;
    /**
     * <p>Maps to table column: {@code PAYMENT_METHOD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "PAYMENT_METHOD should contain less than or equal to 1 characters")
    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;
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
     * <p>Maps to table column: {@code RETURN_CORE_WEIGHT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 5, fraction = 2, message = "RETURN_CORE_WEIGHT should have an integer component no longer than 5 and an a fraction component of exact size 2")
    @Column(name = "RETURN_CORE_WEIGHT")
    private BigDecimal returnCoreWeight;
    /**
     * <p>Maps to table column: {@code BASE_ZONE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 3, message = "BASE_ZONE should contain less than or equal to 3 characters")
    @Column(name = "BASE_ZONE")
    private String baseZone;
    /**
     * <p>Maps to table column: {@code PROMO_ZONE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 3, message = "PROMO_ZONE should contain less than or equal to 3 characters")
    @Column(name = "PROMO_ZONE")
    private String promoZone;
    /**
     * <p>Maps to table column: {@code PRIMARY_VENDOR_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "PRIMARY_VENDOR_FL should contain less than or equal to 1 characters")
    @Column(name = "PRIMARY_VENDOR_FL")
    private String primaryVendorFl;
    /**
     * <p>Maps to table column: {@code SET_TO_PRIMARY_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "SET_TO_PRIMARY_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date setToPrimaryDt;
    /**
     * <p>Maps to table column: {@code USER_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 15, message = "USER_ID should contain less than or equal to 15 characters")
    @Column(name = "USER_ID")
    private String userId;
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
     * <p>Maps to table column: {@code DATE_CHANGED}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "DATE_CHANGED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateChanged;
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
     * <p>Maps to table column: {@code RECV_BY_INV_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "RECV_BY_INV_FL should contain less than or equal to 1 characters")
    @Column(name = "RECV_BY_INV_FL")
    private String recvByInvFl;
    /**
     * <p>Maps to table column: {@code ASN_RCV_ALLOWED_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "ASN_RCV_ALLOWED_FL should contain less than or equal to 1 characters")
    @Column(name = "ASN_RCV_ALLOWED_FL")
    private String asnRcvAllowedFl;
    /**
     * <p>Maps to table column: {@code ACCOUNT_NO}.</p><p><b>Schema Remarks:</b> This account number field will allow custom filtering based on account number.  This is a custom field.</p>
     * 
     */
    @Size(max = 30, message = "ACCOUNT_NO should contain less than or equal to 30 characters")
    @Column(name = "ACCOUNT_NO")
    private String accountNo;
    /**
     * <p>Maps to table column: {@code ALLOW_EDI_FL}.</p><p><b>Schema Remarks:</b> This optional field will be used by custom processing.</p>
     * 
     */
    @Size(max = 1, message = "ALLOW_EDI_FL should contain less than or equal to 1 characters")
    @Column(name = "ALLOW_EDI_FL")
    private String allowEdiFl;
    /**
     * <p>Maps to table column: {@code LANDED_COST_ELEMENTS}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 500, message = "LANDED_COST_ELEMENTS should contain less than or equal to 500 characters")
    @Column(name = "LANDED_COST_ELEMENTS")
    private String landedCostElements;
    /**
     * <p>Maps to table column: {@code EDI_VEN_SITE_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 15, message = "EDI_VEN_SITE_ID should contain less than or equal to 15 characters")
    @Column(name = "EDI_VEN_SITE_ID")
    private String ediVenSiteId;
    /**
     * <p>Maps to table column: {@code EMPLOYEE_ID}.</p><p><b>Schema Remarks:</b> Employee associated with the buyer for the vendor for this site</p>
     * 
     */
    @Size(max = 12, message = "EMPLOYEE_ID should contain less than or equal to 12 characters")
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;
    /**
     * <p>Maps to table column: {@code CONTACT}.</p><p><b>Schema Remarks:</b> Name or other contact information for the vendor</p>
     * 
     */
    @Size(max = 50, message = "CONTACT should contain less than or equal to 50 characters")
    @Column(name = "CONTACT")
    private String contact;
    /**
     * <p>Maps to table column: {@code CONTACT2}.</p><p><b>Schema Remarks:</b> Name or other contact information for the vendor</p>
     * 
     */
    @Size(max = 30, message = "CONTACT2 should contain less than or equal to 30 characters")
    @Column(name = "CONTACT2")
    private String contact2;
    /**
     * <p>Maps to table column: {@code EMAIL_ADDRESS}.</p><p><b>Schema Remarks:</b> Vendor email address</p>
     * 
     */
    @Size(max = 100, message = "EMAIL_ADDRESS should contain less than or equal to 100 characters")
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    /**
     * <p>Maps to table column: {@code PHONE_NO}.</p><p><b>Schema Remarks:</b> vendor phone number</p>
     * 
     */
    @Size(max = 30, message = "PHONE_NO should contain less than or equal to 30 characters")
    @Column(name = "PHONE_NO")
    private String phoneNo;
    /**
     * <p>Maps to table column: {@code PHONE2_NO}.</p><p><b>Schema Remarks:</b> Second vendor phone number</p>
     * 
     */
    @Size(max = 20, message = "PHONE2_NO should contain less than or equal to 20 characters")
    @Column(name = "PHONE2_NO")
    private String phone2No;
    /**
     * <p>Maps to table column: {@code FAX_NO}.</p><p><b>Schema Remarks:</b> Vendor Fax number</p>
     * 
     */
    @Size(max = 30, message = "FAX_NO should contain less than or equal to 30 characters")
    @Column(name = "FAX_NO")
    private String faxNo;
    /**
     * <p>Maps to table column: {@code SHIP_TERMS}.</p><p><b>Schema Remarks:</b> An identifier for the timing between shipment and payment (e.g. 30 days, COD)</p>
     * 
     */
    @Size(max = 10, message = "SHIP_TERMS should contain less than or equal to 10 characters")
    @Column(name = "SHIP_TERMS")
    private String shipTerms;
    /**
     * <p>Maps to table column: {@code SHIP_VIA}.</p><p><b>Schema Remarks:</b> The carrier to be used for shipping the items</p>
     * 
     */
    @Size(max = 10, message = "SHIP_VIA should contain less than or equal to 10 characters")
    @Column(name = "SHIP_VIA")
    private String shipVia;
    /**
     * <p>Maps to table column: {@code FOB}.</p><p><b>Schema Remarks:</b> Freight On Board</p>
     * 
     */
    @Size(max = 15, message = "FOB should contain less than or equal to 15 characters")
    @Column(name = "FOB")
    private String fob;
    /**
     * <p>Maps to table column: {@code LEAD_TIME}.</p><p><b>Schema Remarks:</b> Number of days that will be added with current day to arrive at the required date for PO</p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "LEAD_TIME should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "LEAD_TIME")
    private Integer leadTime;
    /**
     * <p>Maps to table column: {@code CANCEL_LEAD}.</p><p><b>Schema Remarks:</b> Number of days that will be added with current day to arrive at the cancel date for PO</p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "CANCEL_LEAD should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "CANCEL_LEAD")
    private Integer cancelLead;
    /**
     * <p>Maps to table column: {@code EXPECT_LEAD}.</p><p><b>Schema Remarks:</b> Number of days that will be added with current day to arrive at the expected date in PO</p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "EXPECT_LEAD should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "EXPECT_LEAD")
    private Integer expectLead;
    /**
     * <p>Maps to table column: {@code TERM_CODE}.</p><p><b>Schema Remarks:</b> FK to the PAYMENT_TERM_CODE table. Contains the Terms and Conditions</p>
     * 
     */
    @Size(max = 20, message = "TERM_CODE should contain less than or equal to 20 characters")
    @Column(name = "TERM_CODE")
    private String termCode;
    /**
     * <p>Maps to table column: {@code NET_DAYS}.</p><p><b>Schema Remarks:</b> Number of days after receipt the vendor requires payment</p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "NET_DAYS should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "NET_DAYS")
    private Integer netDays;
    /**
     * <p>Maps to table column: {@code PAYMENT_TERM_DAYS1}.</p><p><b>Schema Remarks:</b> Corresponding days payment must be received to receive  PAYMENT_TERM_PCT1 discount</p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "PAYMENT_TERM_DAYS1 should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "PAYMENT_TERM_DAYS1")
    private Integer paymentTermDays1;
    /**
     * <p>Maps to table column: {@code PAYMENT_TERM_DAYS2}.</p><p><b>Schema Remarks:</b> Corresponding days payment must be received to receive PAYMENT_TERM_PCT2 discount</p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "PAYMENT_TERM_DAYS2 should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "PAYMENT_TERM_DAYS2")
    private Integer paymentTermDays2;
    /**
     * <p>Maps to table column: {@code PAYMENT_TERM_PCT1}.</p><p><b>Schema Remarks:</b> Vendor percentage discount for payment within a specified time period</p>
     * 
     */
    @Digits(integer = 3, fraction = 2, message = "PAYMENT_TERM_PCT1 should have an integer component no longer than 3 and an a fraction component of exact size 2")
    @Column(name = "PAYMENT_TERM_PCT1")
    private BigDecimal paymentTermPct1;
    /**
     * <p>Maps to table column: {@code PAYMENT_TERM_PCT2}.</p><p><b>Schema Remarks:</b> Vendor percentage discount for payment within an alternate specified time period</p>
     * 
     */
    @Digits(integer = 3, fraction = 2, message = "PAYMENT_TERM_PCT2 should have an integer component no longer than 3 and an a fraction component of exact size 2")
    @Column(name = "PAYMENT_TERM_PCT2")
    private BigDecimal paymentTermPct2;
    /**
     * <p>Maps to table column: {@code DUE_DT}.</p><p><b>Schema Remarks:</b> Payment terms due date</p>
     * 
     */
    @Column(name = "DUE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDt;
    /**
     * <p>Maps to table column: {@code MIN_PO_AMT}.</p><p><b>Schema Remarks:</b> Minimum Amount the PO has to be in order to for the PO to be accepted</p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "MIN_PO_AMT should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "MIN_PO_AMT")
    private BigDecimal minPoAmt;
    /**
     * <p>Maps to table column: {@code LOCK_LEAD_TIME_FL}.</p><p><b>Schema Remarks:</b> When set to 'Y', the record is not considered for the Lead Time Calculation for the vendor/site</p>
     * 
     */
    @Size(max = 1, message = "LOCK_LEAD_TIME_FL should contain less than or equal to 1 characters")
    @Column(name = "LOCK_LEAD_TIME_FL")
    private String lockLeadTimeFl;
    /**
     * <p>Static value for named query 'VENDOR_BY_SITE_NO'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VenSiteEntity v where v.siteNo = :siteNo }
     * 
     */
    public final static String VENDOR_BY_SITE_NO = "VENDOR_BY_SITE_NO";
    /**
     * <p>Static value for named query 'VENSIT_BY_VENDOR'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VenSiteEntity v where v.vendorId = :vendorId }
     * 
     */
    public final static String VENSIT_BY_VENDOR = "VENSIT_BY_VENDOR";
    /**
     * <p>Static value for named query 'VENDOR_BY_ASN_RCV_ALLOWED_FL'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VenSiteEntity v where v.asnRcvAllowedFl = :asnRcvAllowedFl }
     * 
     */
    public final static String VENDOR_BY_ASN_RCV_ALLOWED_FL = "VENDOR_BY_ASN_RCV_ALLOWED_FL";
    /**
     * <p>Static value for named query 'VENDOR_BY_SITE_AND_ASN'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VenSiteEntity v where v.asnRcvAllowedFl = :asnRcvAllowedFl And v.siteNo = :siteNo }
     * 
     */
    public final static String VENDOR_BY_SITE_AND_ASN = "VENDOR_BY_SITE_AND_ASN";
    /**
     * <p>Static value for named query 'VENSIT_BY_LST_IDS'<p>
     * <p>JPA Query:
     * </p>{@code Select v from VenSiteEntity v where v.identifier In (:ids) order by v.primaryVendorFl desc }
     * 
     */
    public final static String VENSIT_BY_LST_IDS = "VENSIT_BY_LST_IDS";

    /**
     * <P>Empty constructor used to create new VenSiteEntity.</P>
     * 
     */
    public VenSiteEntity() {
        this.identifier = new VenSiteEntityId();
        	
        _init();
    }

    /**
     * Constructor used to create a new VenSiteEntity where the identifier will be created by copying the fields of the provided sourceId.
     * 
     */
    public VenSiteEntity(VenSiteEntityId sourceId) {
        this.identifier = new VenSiteEntityId(sourceId.getVendorId(), sourceId.getSiteNo());
        	
        _init();
    }

    /**
     * Constructor used to create a VenSiteEntity where the identifier will be created internally using the id arguments provided.  This is a convenient constructor used instead of instantiating a new identifier.
     * 
     */
    public VenSiteEntity(String vendorId, Integer siteNo) {
        this.identifier = new VenSiteEntityId(vendorId, siteNo);
        	
        _init();
    }

    /**
     * Constructor used to create a VenSiteEntity where contents are provided by an object array typically returned by a native query
     * 
     */
    public VenSiteEntity(Object[] objectArray) {
        this.identifier = new VenSiteEntityId(((objectArray[ 0 ] == null)?null:((String) objectArray[ 0 ])), ((objectArray[ 1 ] == null)?null:((BigDecimal) objectArray[ 1 ]).intValue()));
        	
        this.dsdType = ((objectArray[ 2 ] == null)?null:((String) objectArray[ 2 ]));
        this.receiveLevel = ((objectArray[ 3 ] == null)?null:((String) objectArray[ 3 ]));
        this.invoiceRoundingFl = ((objectArray[ 4 ] == null)?null:((String) objectArray[ 4 ]));
        this.receiveExceptionQty = ((objectArray[ 5 ] == null)?null:((BigDecimal) objectArray[ 5 ]));
        this.receiveExceptionAmt = ((objectArray[ 6 ] == null)?null:((BigDecimal) objectArray[ 6 ]));
        this.extCustEntity = ((objectArray[ 7 ] == null)?null:((String) objectArray[ 7 ]));
        this.extCustId = ((objectArray[ 8 ] == null)?null:((String) objectArray[ 8 ]));
        this.paymentMethod = ((objectArray[ 9 ] == null)?null:((String) objectArray[ 9 ]));
        this.freightMinLineItems = ((objectArray[ 10 ] == null)?null:((BigDecimal) objectArray[ 10 ]).intValue());
        this.freightMinUnits = ((objectArray[ 11 ] == null)?null:((BigDecimal) objectArray[ 11 ]).longValue());
        this.freightMinCases = ((objectArray[ 12 ] == null)?null:((BigDecimal) objectArray[ 12 ]).longValue());
        this.freightMinAmt = ((objectArray[ 13 ] == null)?null:((BigDecimal) objectArray[ 13 ]));
        this.freightMinWeight = ((objectArray[ 14 ] == null)?null:((BigDecimal) objectArray[ 14 ]));
        this.freightMinCube = ((objectArray[ 15 ] == null)?null:((BigDecimal) objectArray[ 15 ]));
        this.freightTolerancePct = ((objectArray[ 16 ] == null)?null:((BigDecimal) objectArray[ 16 ]));
        this.returnCoreWeight = ((objectArray[ 17 ] == null)?null:((BigDecimal) objectArray[ 17 ]));
        this.baseZone = ((objectArray[ 18 ] == null)?null:((String) objectArray[ 18 ]));
        this.promoZone = ((objectArray[ 19 ] == null)?null:((String) objectArray[ 19 ]));
        this.primaryVendorFl = ((objectArray[ 20 ] == null)?null:((String) objectArray[ 20 ]));
        this.setToPrimaryDt = ((objectArray[ 21 ] == null)?null:new Date(((Timestamp) objectArray[ 21 ]).getTime()));
        this.userId = ((objectArray[ 22 ] == null)?null:((String) objectArray[ 22 ]));
        this.invToleranceQty = ((objectArray[ 23 ] == null)?null:((BigDecimal) objectArray[ 23 ]));
        this.invToleranceQtyFl = ((objectArray[ 24 ] == null)?null:((String) objectArray[ 24 ]));
        this.invTolerancePct = ((objectArray[ 25 ] == null)?null:((BigDecimal) objectArray[ 25 ]));
        this.invTolerancePctFl = ((objectArray[ 26 ] == null)?null:((String) objectArray[ 26 ]));
        this.invToleranceAmt = ((objectArray[ 27 ] == null)?null:((BigDecimal) objectArray[ 27 ]));
        this.invToleranceAmtFl = ((objectArray[ 28 ] == null)?null:((String) objectArray[ 28 ]));
        this.freightToleranceAmt = ((objectArray[ 29 ] == null)?null:((BigDecimal) objectArray[ 29 ]));
        this.freightToleranceAmtFl = ((objectArray[ 30 ] == null)?null:((String) objectArray[ 30 ]));
        this.freightTolerancePctFl = ((objectArray[ 31 ] == null)?null:((String) objectArray[ 31 ]));
        this.reconMethod = ((objectArray[ 32 ] == null)?null:((String) objectArray[ 32 ]));
        this.dateChanged = ((objectArray[ 33 ] == null)?null:new Date(((Timestamp) objectArray[ 33 ]).getTime()));
        this.dateCreated = ((objectArray[ 34 ] == null)?null:new Date(((Timestamp) objectArray[ 34 ]).getTime()));
        this.userCreated = ((objectArray[ 35 ] == null)?null:((String) objectArray[ 35 ]));
        this.dateModified = ((objectArray[ 36 ] == null)?null:new Date(((Timestamp) objectArray[ 36 ]).getTime()));
        this.userModified = ((objectArray[ 37 ] == null)?null:((String) objectArray[ 37 ]));
        this.recvByInvFl = ((objectArray[ 38 ] == null)?null:((String) objectArray[ 38 ]));
        this.asnRcvAllowedFl = ((objectArray[ 39 ] == null)?null:((String) objectArray[ 39 ]));
        this.accountNo = ((objectArray[ 40 ] == null)?null:((String) objectArray[ 40 ]));
        this.allowEdiFl = ((objectArray[ 41 ] == null)?null:((String) objectArray[ 41 ]));
        this.landedCostElements = ((objectArray[ 42 ] == null)?null:((String) objectArray[ 42 ]));
        this.ediVenSiteId = ((objectArray[ 43 ] == null)?null:((String) objectArray[ 43 ]));
        this.employeeId = ((objectArray[ 44 ] == null)?null:((String) objectArray[ 44 ]));
        this.contact = ((objectArray[ 45 ] == null)?null:((String) objectArray[ 45 ]));
        this.contact2 = ((objectArray[ 46 ] == null)?null:((String) objectArray[ 46 ]));
        this.emailAddress = ((objectArray[ 47 ] == null)?null:((String) objectArray[ 47 ]));
        this.phoneNo = ((objectArray[ 48 ] == null)?null:((String) objectArray[ 48 ]));
        this.phone2No = ((objectArray[ 49 ] == null)?null:((String) objectArray[ 49 ]));
        this.faxNo = ((objectArray[ 50 ] == null)?null:((String) objectArray[ 50 ]));
        this.shipTerms = ((objectArray[ 51 ] == null)?null:((String) objectArray[ 51 ]));
        this.shipVia = ((objectArray[ 52 ] == null)?null:((String) objectArray[ 52 ]));
        this.fob = ((objectArray[ 53 ] == null)?null:((String) objectArray[ 53 ]));
        this.leadTime = ((objectArray[ 54 ] == null)?null:((BigDecimal) objectArray[ 54 ]).intValue());
        this.cancelLead = ((objectArray[ 55 ] == null)?null:((BigDecimal) objectArray[ 55 ]).intValue());
        this.expectLead = ((objectArray[ 56 ] == null)?null:((BigDecimal) objectArray[ 56 ]).intValue());
        this.termCode = ((objectArray[ 57 ] == null)?null:((String) objectArray[ 57 ]));
        this.netDays = ((objectArray[ 58 ] == null)?null:((BigDecimal) objectArray[ 58 ]).intValue());
        this.paymentTermDays1 = ((objectArray[ 59 ] == null)?null:((BigDecimal) objectArray[ 59 ]).intValue());
        this.paymentTermDays2 = ((objectArray[ 60 ] == null)?null:((BigDecimal) objectArray[ 60 ]).intValue());
        this.paymentTermPct1 = ((objectArray[ 61 ] == null)?null:((BigDecimal) objectArray[ 61 ]));
        this.paymentTermPct2 = ((objectArray[ 62 ] == null)?null:((BigDecimal) objectArray[ 62 ]));
        this.dueDt = ((objectArray[ 63 ] == null)?null:new Date(((Timestamp) objectArray[ 63 ]).getTime()));
        this.minPoAmt = ((objectArray[ 64 ] == null)?null:((BigDecimal) objectArray[ 64 ]));
        this.lockLeadTimeFl = ((objectArray[ 65 ] == null)?null:((String) objectArray[ 65 ]));
        	
        _init();
    }

    /**
     * <P>Copy constructor used to copy a VenSiteEntity where the identifier will be created by copying the fields of the provided sourceId.</P>
     * 
     */
    public VenSiteEntity(VenSiteEntity source, VenSiteEntityId sourceId) {
        this.identifier = new VenSiteEntityId(sourceId.getVendorId(), sourceId.getSiteNo());
        	
        _init();
        	
        this.dsdType = source.getDsdType();
        this.receiveLevel = source.getReceiveLevel();
        this.invoiceRoundingFl = source.getInvoiceRoundingFl();
        this.receiveExceptionQty = source.getReceiveExceptionQty();
        this.receiveExceptionAmt = source.getReceiveExceptionAmt();
        this.extCustEntity = source.getExtCustEntity();
        this.extCustId = source.getExtCustId();
        this.paymentMethod = source.getPaymentMethod();
        this.freightMinLineItems = source.getFreightMinLineItems();
        this.freightMinUnits = source.getFreightMinUnits();
        this.freightMinCases = source.getFreightMinCases();
        this.freightMinAmt = source.getFreightMinAmt();
        this.freightMinWeight = source.getFreightMinWeight();
        this.freightMinCube = source.getFreightMinCube();
        this.freightTolerancePct = source.getFreightTolerancePct();
        this.returnCoreWeight = source.getReturnCoreWeight();
        this.baseZone = source.getBaseZone();
        this.promoZone = source.getPromoZone();
        this.primaryVendorFl = source.getPrimaryVendorFl();
        this.setToPrimaryDt = source.getSetToPrimaryDt();
        this.userId = source.getUserId();
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
        this.dateChanged = source.getDateChanged();
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.recvByInvFl = source.getRecvByInvFl();
        this.asnRcvAllowedFl = source.getAsnRcvAllowedFl();
        this.accountNo = source.getAccountNo();
        this.allowEdiFl = source.getAllowEdiFl();
        this.landedCostElements = source.getLandedCostElements();
        this.ediVenSiteId = source.getEdiVenSiteId();
        this.employeeId = source.getEmployeeId();
        this.contact = source.getContact();
        this.contact2 = source.getContact2();
        this.emailAddress = source.getEmailAddress();
        this.phoneNo = source.getPhoneNo();
        this.phone2No = source.getPhone2No();
        this.faxNo = source.getFaxNo();
        this.shipTerms = source.getShipTerms();
        this.shipVia = source.getShipVia();
        this.fob = source.getFob();
        this.leadTime = source.getLeadTime();
        this.cancelLead = source.getCancelLead();
        this.expectLead = source.getExpectLead();
        this.termCode = source.getTermCode();
        this.netDays = source.getNetDays();
        this.paymentTermDays1 = source.getPaymentTermDays1();
        this.paymentTermDays2 = source.getPaymentTermDays2();
        this.paymentTermPct1 = source.getPaymentTermPct1();
        this.paymentTermPct2 = source.getPaymentTermPct2();
        this.dueDt = source.getDueDt();
        this.minPoAmt = source.getMinPoAmt();
        this.lockLeadTimeFl = source.getLockLeadTimeFl();
    }

    /**
     * <P>Copy constructor used to copy a VenSiteEntity including its identifier.</P>
     * 
     */
    public VenSiteEntity(VenSiteEntity source) {
        this.identifier = new VenSiteEntityId(source.getIdentifier().getVendorId(), source.getIdentifier().getSiteNo());
        	
        _init();
        	
        this.dsdType = source.getDsdType();
        this.receiveLevel = source.getReceiveLevel();
        this.invoiceRoundingFl = source.getInvoiceRoundingFl();
        this.receiveExceptionQty = source.getReceiveExceptionQty();
        this.receiveExceptionAmt = source.getReceiveExceptionAmt();
        this.extCustEntity = source.getExtCustEntity();
        this.extCustId = source.getExtCustId();
        this.paymentMethod = source.getPaymentMethod();
        this.freightMinLineItems = source.getFreightMinLineItems();
        this.freightMinUnits = source.getFreightMinUnits();
        this.freightMinCases = source.getFreightMinCases();
        this.freightMinAmt = source.getFreightMinAmt();
        this.freightMinWeight = source.getFreightMinWeight();
        this.freightMinCube = source.getFreightMinCube();
        this.freightTolerancePct = source.getFreightTolerancePct();
        this.returnCoreWeight = source.getReturnCoreWeight();
        this.baseZone = source.getBaseZone();
        this.promoZone = source.getPromoZone();
        this.primaryVendorFl = source.getPrimaryVendorFl();
        this.setToPrimaryDt = source.getSetToPrimaryDt();
        this.userId = source.getUserId();
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
        this.dateChanged = source.getDateChanged();
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.recvByInvFl = source.getRecvByInvFl();
        this.asnRcvAllowedFl = source.getAsnRcvAllowedFl();
        this.accountNo = source.getAccountNo();
        this.allowEdiFl = source.getAllowEdiFl();
        this.landedCostElements = source.getLandedCostElements();
        this.ediVenSiteId = source.getEdiVenSiteId();
        this.employeeId = source.getEmployeeId();
        this.contact = source.getContact();
        this.contact2 = source.getContact2();
        this.emailAddress = source.getEmailAddress();
        this.phoneNo = source.getPhoneNo();
        this.phone2No = source.getPhone2No();
        this.faxNo = source.getFaxNo();
        this.shipTerms = source.getShipTerms();
        this.shipVia = source.getShipVia();
        this.fob = source.getFob();
        this.leadTime = source.getLeadTime();
        this.cancelLead = source.getCancelLead();
        this.expectLead = source.getExpectLead();
        this.termCode = source.getTermCode();
        this.netDays = source.getNetDays();
        this.paymentTermDays1 = source.getPaymentTermDays1();
        this.paymentTermDays2 = source.getPaymentTermDays2();
        this.paymentTermPct1 = source.getPaymentTermPct1();
        this.paymentTermPct2 = source.getPaymentTermPct2();
        this.dueDt = source.getDueDt();
        this.minPoAmt = source.getMinPoAmt();
        this.lockLeadTimeFl = source.getLockLeadTimeFl();
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public VenSiteEntityId getIdentifier() {
        return this.identifier;
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public void setIdentifier(VenSiteEntityId identifier) {
        this.identifier = identifier;
    }

    /**
     * Getter for {@link VenSiteEntityId#vendorId identifier.vendorId}
     * 
     */
    @Access(AccessType.PROPERTY)
    @Column(name = "VENDOR_ID", insertable = false, updatable = false)
    public String getVendorId() {
        return this.identifier.getVendorId();
    }

    /**
     * Setter for {@link VenSiteEntityId#vendorId identifier.vendorId}
     * 
     */
    public String setVendorId(String vendorId) {
        this.identifier.setVendorId(vendorId);
        return this.identifier.getVendorId();
    }

    /**
     * Getter for {@link VenSiteEntityId#siteNo identifier.siteNo}
     * 
     */
    @Access(AccessType.PROPERTY)
    @Column(name = "SITE_NO", insertable = false, updatable = false)
    public Integer getSiteNo() {
        return this.identifier.getSiteNo();
    }

    /**
     * Setter for {@link VenSiteEntityId#siteNo identifier.siteNo}
     * 
     */
    public Integer setSiteNo(Integer siteNo) {
        this.identifier.setSiteNo(siteNo);
        return this.identifier.getSiteNo();
    }

    /**
     * Getter for {@link #dsdType dsdType}
     * 
     */
    public String getDsdType() {
        return this.dsdType;
    }

    /**
     * Setter for {@link #dsdType dsdType}
     * 
     */
    public String setDsdType(String dsdType) {
        this.dsdType = dsdType;
        return this.dsdType;
    }

    /**
     * Getter for {@link #receiveLevel receiveLevel}
     * 
     */
    public String getReceiveLevel() {
        return this.receiveLevel;
    }

    /**
     * Setter for {@link #receiveLevel receiveLevel}
     * 
     */
    public String setReceiveLevel(String receiveLevel) {
        this.receiveLevel = receiveLevel;
        return this.receiveLevel;
    }

    /**
     * Getter for {@link #invoiceRoundingFl invoiceRoundingFl}
     * 
     */
    public String getInvoiceRoundingFl() {
        return this.invoiceRoundingFl;
    }

    /**
     * Setter for {@link #invoiceRoundingFl invoiceRoundingFl}
     * 
     */
    public String setInvoiceRoundingFl(String invoiceRoundingFl) {
        this.invoiceRoundingFl = invoiceRoundingFl;
        return this.invoiceRoundingFl;
    }

    /**
     * Getter for {@link #receiveExceptionQty receiveExceptionQty}
     * 
     */
    public BigDecimal getReceiveExceptionQty() {
        return this.receiveExceptionQty;
    }

    /**
     * Setter for {@link #receiveExceptionQty receiveExceptionQty}
     * 
     */
    public BigDecimal setReceiveExceptionQty(BigDecimal receiveExceptionQty) {
        if ((receiveExceptionQty!= null)&&(receiveExceptionQty.scale()!= 2)) {
            receiveExceptionQty = receiveExceptionQty.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.receiveExceptionQty = receiveExceptionQty;
        return this.receiveExceptionQty;
    }

    /**
     * Getter for {@link #receiveExceptionAmt receiveExceptionAmt}
     * 
     */
    public BigDecimal getReceiveExceptionAmt() {
        return this.receiveExceptionAmt;
    }

    /**
     * Setter for {@link #receiveExceptionAmt receiveExceptionAmt}
     * 
     */
    public BigDecimal setReceiveExceptionAmt(BigDecimal receiveExceptionAmt) {
        if ((receiveExceptionAmt!= null)&&(receiveExceptionAmt.scale()!= 2)) {
            receiveExceptionAmt = receiveExceptionAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.receiveExceptionAmt = receiveExceptionAmt;
        return this.receiveExceptionAmt;
    }

    /**
     * Getter for {@link #extCustEntity extCustEntity}
     * 
     */
    public String getExtCustEntity() {
        return this.extCustEntity;
    }

    /**
     * Setter for {@link #extCustEntity extCustEntity}
     * 
     */
    public String setExtCustEntity(String extCustEntity) {
        this.extCustEntity = extCustEntity;
        return this.extCustEntity;
    }

    /**
     * Getter for {@link #extCustId extCustId}
     * 
     */
    public String getExtCustId() {
        return this.extCustId;
    }

    /**
     * Setter for {@link #extCustId extCustId}
     * 
     */
    public String setExtCustId(String extCustId) {
        this.extCustId = extCustId;
        return this.extCustId;
    }

    /**
     * Getter for {@link #paymentMethod paymentMethod}
     * 
     */
    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    /**
     * Setter for {@link #paymentMethod paymentMethod}
     * 
     */
    public String setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this.paymentMethod;
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
     * Getter for {@link #returnCoreWeight returnCoreWeight}
     * 
     */
    public BigDecimal getReturnCoreWeight() {
        return this.returnCoreWeight;
    }

    /**
     * Setter for {@link #returnCoreWeight returnCoreWeight}
     * 
     */
    public BigDecimal setReturnCoreWeight(BigDecimal returnCoreWeight) {
        if ((returnCoreWeight!= null)&&(returnCoreWeight.scale()!= 2)) {
            returnCoreWeight = returnCoreWeight.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.returnCoreWeight = returnCoreWeight;
        return this.returnCoreWeight;
    }

    /**
     * Getter for {@link #baseZone baseZone}
     * 
     */
    public String getBaseZone() {
        return this.baseZone;
    }

    /**
     * Setter for {@link #baseZone baseZone}
     * 
     */
    public String setBaseZone(String baseZone) {
        this.baseZone = baseZone;
        return this.baseZone;
    }

    /**
     * Getter for {@link #promoZone promoZone}
     * 
     */
    public String getPromoZone() {
        return this.promoZone;
    }

    /**
     * Setter for {@link #promoZone promoZone}
     * 
     */
    public String setPromoZone(String promoZone) {
        this.promoZone = promoZone;
        return this.promoZone;
    }

    /**
     * Getter for {@link #primaryVendorFl primaryVendorFl}
     * 
     */
    public String getPrimaryVendorFl() {
        return this.primaryVendorFl;
    }

    /**
     * Setter for {@link #primaryVendorFl primaryVendorFl}
     * 
     */
    public String setPrimaryVendorFl(String primaryVendorFl) {
        this.primaryVendorFl = primaryVendorFl;
        return this.primaryVendorFl;
    }

    /**
     * Getter for {@link #setToPrimaryDt setToPrimaryDt}
     * 
     */
    public Date getSetToPrimaryDt() {
        return this.setToPrimaryDt;
    }

    /**
     * Setter for {@link #setToPrimaryDt setToPrimaryDt}
     * 
     */
    public Date setSetToPrimaryDt(Date setToPrimaryDt) {
        this.setToPrimaryDt = setToPrimaryDt;
        return this.setToPrimaryDt;
    }

    /**
     * Getter for {@link #userId userId}
     * 
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Setter for {@link #userId userId}
     * 
     */
    public String setUserId(String userId) {
        this.userId = userId;
        return this.userId;
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
     * Getter for {@link #recvByInvFl recvByInvFl}
     * 
     */
    public String getRecvByInvFl() {
        return this.recvByInvFl;
    }

    /**
     * Setter for {@link #recvByInvFl recvByInvFl}
     * 
     */
    public String setRecvByInvFl(String recvByInvFl) {
        this.recvByInvFl = recvByInvFl;
        return this.recvByInvFl;
    }

    /**
     * Getter for {@link #asnRcvAllowedFl asnRcvAllowedFl}
     * 
     */
    public String getAsnRcvAllowedFl() {
        return this.asnRcvAllowedFl;
    }

    /**
     * Setter for {@link #asnRcvAllowedFl asnRcvAllowedFl}
     * 
     */
    public String setAsnRcvAllowedFl(String asnRcvAllowedFl) {
        this.asnRcvAllowedFl = asnRcvAllowedFl;
        return this.asnRcvAllowedFl;
    }

    /**
     * Getter for {@link #accountNo accountNo}
     * 
     */
    public String getAccountNo() {
        return this.accountNo;
    }

    /**
     * Setter for {@link #accountNo accountNo}
     * 
     */
    public String setAccountNo(String accountNo) {
        this.accountNo = accountNo;
        return this.accountNo;
    }

    /**
     * Getter for {@link #allowEdiFl allowEdiFl}
     * 
     */
    public String getAllowEdiFl() {
        return this.allowEdiFl;
    }

    /**
     * Setter for {@link #allowEdiFl allowEdiFl}
     * 
     */
    public String setAllowEdiFl(String allowEdiFl) {
        this.allowEdiFl = allowEdiFl;
        return this.allowEdiFl;
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
     * Getter for {@link #ediVenSiteId ediVenSiteId}
     * 
     */
    public String getEdiVenSiteId() {
        return this.ediVenSiteId;
    }

    /**
     * Setter for {@link #ediVenSiteId ediVenSiteId}
     * 
     */
    public String setEdiVenSiteId(String ediVenSiteId) {
        this.ediVenSiteId = ediVenSiteId;
        return this.ediVenSiteId;
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
     * @hide<P><I>Not for public use.</I></P>
     * 
     */
    private void _initDefaults() {
        if (this.receiveLevel == null) {
            this.receiveLevel = "T";
        }
        if (this.receiveExceptionQty == null) {
            this.receiveExceptionQty = new BigDecimal("0.00");
        }
        if (this.receiveExceptionAmt == null) {
            this.receiveExceptionAmt = new BigDecimal("0.00");
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
        if (this.returnCoreWeight == null) {
            this.returnCoreWeight = new BigDecimal("0.00");
        }
        if (this.primaryVendorFl == null) {
            this.primaryVendorFl = "N";
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
        if (this.asnRcvAllowedFl == null) {
            this.asnRcvAllowedFl = "N";
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
     * <P>Used to copy a VenSiteEntity including non-identifier fields only.</P>
     * 
     */
    public VenSiteEntity copyData() {
        final VenSiteEntity copy = new VenSiteEntity();
        	
        copy.dsdType = this.dsdType;
        copy.receiveLevel = this.receiveLevel;
        copy.invoiceRoundingFl = this.invoiceRoundingFl;
        copy.receiveExceptionQty = this.receiveExceptionQty;
        copy.receiveExceptionAmt = this.receiveExceptionAmt;
        copy.extCustEntity = this.extCustEntity;
        copy.extCustId = this.extCustId;
        copy.paymentMethod = this.paymentMethod;
        copy.freightMinLineItems = this.freightMinLineItems;
        copy.freightMinUnits = this.freightMinUnits;
        copy.freightMinCases = this.freightMinCases;
        copy.freightMinAmt = this.freightMinAmt;
        copy.freightMinWeight = this.freightMinWeight;
        copy.freightMinCube = this.freightMinCube;
        copy.freightTolerancePct = this.freightTolerancePct;
        copy.returnCoreWeight = this.returnCoreWeight;
        copy.baseZone = this.baseZone;
        copy.promoZone = this.promoZone;
        copy.primaryVendorFl = this.primaryVendorFl;
        copy.setToPrimaryDt = this.setToPrimaryDt;
        copy.userId = this.userId;
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
        copy.dateChanged = this.dateChanged;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.recvByInvFl = this.recvByInvFl;
        copy.asnRcvAllowedFl = this.asnRcvAllowedFl;
        copy.accountNo = this.accountNo;
        copy.allowEdiFl = this.allowEdiFl;
        copy.landedCostElements = this.landedCostElements;
        copy.ediVenSiteId = this.ediVenSiteId;
        copy.employeeId = this.employeeId;
        copy.contact = this.contact;
        copy.contact2 = this.contact2;
        copy.emailAddress = this.emailAddress;
        copy.phoneNo = this.phoneNo;
        copy.phone2No = this.phone2No;
        copy.faxNo = this.faxNo;
        copy.shipTerms = this.shipTerms;
        copy.shipVia = this.shipVia;
        copy.fob = this.fob;
        copy.leadTime = this.leadTime;
        copy.cancelLead = this.cancelLead;
        copy.expectLead = this.expectLead;
        copy.termCode = this.termCode;
        copy.netDays = this.netDays;
        copy.paymentTermDays1 = this.paymentTermDays1;
        copy.paymentTermDays2 = this.paymentTermDays2;
        copy.paymentTermPct1 = this.paymentTermPct1;
        copy.paymentTermPct2 = this.paymentTermPct2;
        copy.dueDt = this.dueDt;
        copy.minPoAmt = this.minPoAmt;
        copy.lockLeadTimeFl = this.lockLeadTimeFl;
        	
        return copy;
    }

    /**
     * <P>Used to copy a VenSiteEntity including its identifier.</P>
     * 
     */
    public VenSiteEntity copyDataAndId() {
        final VenSiteEntity copy = new VenSiteEntity();
        	
        copy.getIdentifier().setVendorId(this.getIdentifier().getVendorId());
        copy.getIdentifier().setSiteNo(this.getIdentifier().getSiteNo());
        	
        copy.dsdType = this.dsdType;
        copy.receiveLevel = this.receiveLevel;
        copy.invoiceRoundingFl = this.invoiceRoundingFl;
        copy.receiveExceptionQty = this.receiveExceptionQty;
        copy.receiveExceptionAmt = this.receiveExceptionAmt;
        copy.extCustEntity = this.extCustEntity;
        copy.extCustId = this.extCustId;
        copy.paymentMethod = this.paymentMethod;
        copy.freightMinLineItems = this.freightMinLineItems;
        copy.freightMinUnits = this.freightMinUnits;
        copy.freightMinCases = this.freightMinCases;
        copy.freightMinAmt = this.freightMinAmt;
        copy.freightMinWeight = this.freightMinWeight;
        copy.freightMinCube = this.freightMinCube;
        copy.freightTolerancePct = this.freightTolerancePct;
        copy.returnCoreWeight = this.returnCoreWeight;
        copy.baseZone = this.baseZone;
        copy.promoZone = this.promoZone;
        copy.primaryVendorFl = this.primaryVendorFl;
        copy.setToPrimaryDt = this.setToPrimaryDt;
        copy.userId = this.userId;
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
        copy.dateChanged = this.dateChanged;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.recvByInvFl = this.recvByInvFl;
        copy.asnRcvAllowedFl = this.asnRcvAllowedFl;
        copy.accountNo = this.accountNo;
        copy.allowEdiFl = this.allowEdiFl;
        copy.landedCostElements = this.landedCostElements;
        copy.ediVenSiteId = this.ediVenSiteId;
        copy.employeeId = this.employeeId;
        copy.contact = this.contact;
        copy.contact2 = this.contact2;
        copy.emailAddress = this.emailAddress;
        copy.phoneNo = this.phoneNo;
        copy.phone2No = this.phone2No;
        copy.faxNo = this.faxNo;
        copy.shipTerms = this.shipTerms;
        copy.shipVia = this.shipVia;
        copy.fob = this.fob;
        copy.leadTime = this.leadTime;
        copy.cancelLead = this.cancelLead;
        copy.expectLead = this.expectLead;
        copy.termCode = this.termCode;
        copy.netDays = this.netDays;
        copy.paymentTermDays1 = this.paymentTermDays1;
        copy.paymentTermDays2 = this.paymentTermDays2;
        copy.paymentTermPct1 = this.paymentTermPct1;
        copy.paymentTermPct2 = this.paymentTermPct2;
        copy.dueDt = this.dueDt;
        copy.minPoAmt = this.minPoAmt;
        copy.lockLeadTimeFl = this.lockLeadTimeFl;
        	
        return copy;
    }

    /**
     * <P>Used to copy a VenSiteEntity including data and GENERATED identifiers only.</P>
     * 
     */
    public VenSiteEntity copyDataAndIdNonGenerated() {
        final VenSiteEntity copy = new VenSiteEntity();
        	
        copy.getIdentifier().setVendorId(this.getIdentifier().getVendorId());
        copy.getIdentifier().setSiteNo(this.getIdentifier().getSiteNo());
        	
        copy.dsdType = this.dsdType;
        copy.receiveLevel = this.receiveLevel;
        copy.invoiceRoundingFl = this.invoiceRoundingFl;
        copy.receiveExceptionQty = this.receiveExceptionQty;
        copy.receiveExceptionAmt = this.receiveExceptionAmt;
        copy.extCustEntity = this.extCustEntity;
        copy.extCustId = this.extCustId;
        copy.paymentMethod = this.paymentMethod;
        copy.freightMinLineItems = this.freightMinLineItems;
        copy.freightMinUnits = this.freightMinUnits;
        copy.freightMinCases = this.freightMinCases;
        copy.freightMinAmt = this.freightMinAmt;
        copy.freightMinWeight = this.freightMinWeight;
        copy.freightMinCube = this.freightMinCube;
        copy.freightTolerancePct = this.freightTolerancePct;
        copy.returnCoreWeight = this.returnCoreWeight;
        copy.baseZone = this.baseZone;
        copy.promoZone = this.promoZone;
        copy.primaryVendorFl = this.primaryVendorFl;
        copy.setToPrimaryDt = this.setToPrimaryDt;
        copy.userId = this.userId;
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
        copy.dateChanged = this.dateChanged;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.recvByInvFl = this.recvByInvFl;
        copy.asnRcvAllowedFl = this.asnRcvAllowedFl;
        copy.accountNo = this.accountNo;
        copy.allowEdiFl = this.allowEdiFl;
        copy.landedCostElements = this.landedCostElements;
        copy.ediVenSiteId = this.ediVenSiteId;
        copy.employeeId = this.employeeId;
        copy.contact = this.contact;
        copy.contact2 = this.contact2;
        copy.emailAddress = this.emailAddress;
        copy.phoneNo = this.phoneNo;
        copy.phone2No = this.phone2No;
        copy.faxNo = this.faxNo;
        copy.shipTerms = this.shipTerms;
        copy.shipVia = this.shipVia;
        copy.fob = this.fob;
        copy.leadTime = this.leadTime;
        copy.cancelLead = this.cancelLead;
        copy.expectLead = this.expectLead;
        copy.termCode = this.termCode;
        copy.netDays = this.netDays;
        copy.paymentTermDays1 = this.paymentTermDays1;
        copy.paymentTermDays2 = this.paymentTermDays2;
        copy.paymentTermPct1 = this.paymentTermPct1;
        copy.paymentTermPct2 = this.paymentTermPct2;
        copy.dueDt = this.dueDt;
        copy.minPoAmt = this.minPoAmt;
        copy.lockLeadTimeFl = this.lockLeadTimeFl;
        	
        return copy;
    }

    /**
     * @hide<P><I>Not for public use.</I></P>This postLoad method is used to fix BigDecimal values being loaded from the JDBC driver.  Oracle has a known jdbc bug where it truncates the scale of BigDecimal.  This method restores the scale after load so that BigDecimals comparators will work again.
     * 
     */
    private void _setScales() {
        if (this.receiveExceptionQty!= null) {
            this.receiveExceptionQty = this.receiveExceptionQty.setScale(2);
        }
        if (this.receiveExceptionAmt!= null) {
            this.receiveExceptionAmt = this.receiveExceptionAmt.setScale(2);
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
        if (this.returnCoreWeight!= null) {
            this.returnCoreWeight = this.returnCoreWeight.setScale(2);
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
        if (this.paymentTermPct1 != null) {
            this.paymentTermPct1 = this.paymentTermPct1 .setScale(2);
        }
        if (this.paymentTermPct2 != null) {
            this.paymentTermPct2 = this.paymentTermPct2 .setScale(2);
        }
        if (this.minPoAmt!= null) {
            this.minPoAmt = this.minPoAmt.setScale(2);
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

    public static VenSiteDTO toDTO(VenSiteEntity entity) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return VenSiteEntity.toDTO(entity, map);
    }

    public static VenSiteDTO toDTO(VenSiteEntity entity, Map<Object, Object> map) {
        try {
            if (entity == null) {
                return null;
            }
            	
            if (map == null) {
                map = new HashMap<Object, Object>();
            } else {
                if (map.get(entity)!= null) {
                    return ((VenSiteDTO) map.get(entity));
                }
            }
            	
            entity.toDTO(map);
            	
            return ((VenSiteDTO) map.get(entity));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public static VenSiteEntity toEntity(VenSiteDTO dto) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return VenSiteEntity.toEntity(dto, map);
    }

    public static VenSiteEntity toEntity(VenSiteDTO dto, Map<Object, Object> map) {
        try {
            if (dto == null) {
                return null;
            }
            	
            if ((map!= null)&&(map.get(dto)!= null)) {
                return ((VenSiteEntity) map.get(dto));
            }
            	
            return ((VenSiteEntity) DtoToEntity.toEntity(VenSiteEntity.class, ((DataTransferObject) dto)));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public void assignAllChildren(VenSiteDTO dto, Map<Object, Object> map) {
        toSimpleEntity(dto, map);
    }

    public void assignAllChildren(Map<Object, Object> map, Stack<Object> stack) {
        toSimpleDto(map);
    }

    public void assignAllParents(Map<Object, Object> map) {
        toSimpleDto(map);
    }

    protected VenSiteDTO toSimpleDto(Map<Object, Object> map) {
        if (map.get(this)!= null) {
            return ((VenSiteDTO) map.get(this));
        }
        try {
            VenSiteDTO dto = new VenSiteDTO();
            dto.setIdentifier(new VenSiteDTOId(this.getVendorId(), this.getSiteNo()));
            	
            dto.setDsdType(this.getDsdType());
            dto.setReceiveLevel(this.getReceiveLevel());
            dto.setInvoiceRoundingFl(this.getInvoiceRoundingFl());
            dto.setReceiveExceptionQty(this.getReceiveExceptionQty());
            dto.setReceiveExceptionAmt(this.getReceiveExceptionAmt());
            dto.setExtCustEntity(this.getExtCustEntity());
            dto.setExtCustId(this.getExtCustId());
            dto.setPaymentMethod(this.getPaymentMethod());
            dto.setFreightMinLineItems(this.getFreightMinLineItems());
            dto.setFreightMinUnits(this.getFreightMinUnits());
            dto.setFreightMinCases(this.getFreightMinCases());
            dto.setFreightMinAmt(this.getFreightMinAmt());
            dto.setFreightMinWeight(this.getFreightMinWeight());
            dto.setFreightMinCube(this.getFreightMinCube());
            dto.setFreightTolerancePct(this.getFreightTolerancePct());
            dto.setReturnCoreWeight(this.getReturnCoreWeight());
            dto.setBaseZone(this.getBaseZone());
            dto.setPromoZone(this.getPromoZone());
            dto.setPrimaryVendorFl(this.getPrimaryVendorFl());
            dto.setSetToPrimaryDt(this.getSetToPrimaryDt());
            dto.setUserId(this.getUserId());
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
            dto.setDateChanged(this.getDateChanged());
            dto.setDateCreated(this.getDateCreated());
            dto.setUserCreated(this.getUserCreated());
            dto.setDateModified(this.getDateModified());
            dto.setUserModified(this.getUserModified());
            dto.setRecvByInvFl(this.getRecvByInvFl());
            dto.setAsnRcvAllowedFl(this.getAsnRcvAllowedFl());
            dto.setAccountNo(this.getAccountNo());
            dto.setAllowEdiFl(this.getAllowEdiFl());
            dto.setLandedCostElements(this.getLandedCostElements());
            dto.setEdiVenSiteId(this.getEdiVenSiteId());
            dto.setEmployeeId(this.getEmployeeId());
            dto.setContact(this.getContact());
            dto.setContact2(this.getContact2());
            dto.setEmailAddress(this.getEmailAddress());
            dto.setPhoneNo(this.getPhoneNo());
            dto.setPhone2No(this.getPhone2No());
            dto.setFaxNo(this.getFaxNo());
            dto.setShipTerms(this.getShipTerms());
            dto.setShipVia(this.getShipVia());
            dto.setFob(this.getFob());
            dto.setLeadTime(this.getLeadTime());
            dto.setCancelLead(this.getCancelLead());
            dto.setExpectLead(this.getExpectLead());
            dto.setTermCode(this.getTermCode());
            dto.setNetDays(this.getNetDays());
            dto.setPaymentTermDays1(this.getPaymentTermDays1());
            dto.setPaymentTermDays2(this.getPaymentTermDays2());
            dto.setPaymentTermPct1(this.getPaymentTermPct1());
            dto.setPaymentTermPct2(this.getPaymentTermPct2());
            dto.setDueDt(this.getDueDt());
            dto.setMinPoAmt(this.getMinPoAmt());
            dto.setLockLeadTimeFl(this.getLockLeadTimeFl());
            	
            map.put(this, dto);
            	
            return dto;
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public VenSiteEntity toSimpleEntity(DataTransferObject dto, Map<Object, Object> map) {
        if (map.get(dto)!= null) {
            return ((VenSiteEntity) map.get(dto));
        }
        try {
            VenSiteDTO oldDto = ((VenSiteDTO) dto);
            VenSiteEntity entity = new VenSiteEntity();
            entity.setIdentifier(new VenSiteEntityId(oldDto.getVendorId(), oldDto.getSiteNo()));
            	
            entity.setDsdType(oldDto.getDsdType());
            entity.setReceiveLevel(oldDto.getReceiveLevel());
            entity.setInvoiceRoundingFl(oldDto.getInvoiceRoundingFl());
            entity.setReceiveExceptionQty(oldDto.getReceiveExceptionQty());
            entity.setReceiveExceptionAmt(oldDto.getReceiveExceptionAmt());
            entity.setExtCustEntity(oldDto.getExtCustEntity());
            entity.setExtCustId(oldDto.getExtCustId());
            entity.setPaymentMethod(oldDto.getPaymentMethod());
            entity.setFreightMinLineItems(oldDto.getFreightMinLineItems());
            entity.setFreightMinUnits(oldDto.getFreightMinUnits());
            entity.setFreightMinCases(oldDto.getFreightMinCases());
            entity.setFreightMinAmt(oldDto.getFreightMinAmt());
            entity.setFreightMinWeight(oldDto.getFreightMinWeight());
            entity.setFreightMinCube(oldDto.getFreightMinCube());
            entity.setFreightTolerancePct(oldDto.getFreightTolerancePct());
            entity.setReturnCoreWeight(oldDto.getReturnCoreWeight());
            entity.setBaseZone(oldDto.getBaseZone());
            entity.setPromoZone(oldDto.getPromoZone());
            entity.setPrimaryVendorFl(oldDto.getPrimaryVendorFl());
            entity.setSetToPrimaryDt(oldDto.getSetToPrimaryDt());
            entity.setUserId(oldDto.getUserId());
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
            entity.setDateChanged(oldDto.getDateChanged());
            entity.setDateCreated(oldDto.getDateCreated());
            entity.setUserCreated(oldDto.getUserCreated());
            entity.setDateModified(oldDto.getDateModified());
            entity.setUserModified(oldDto.getUserModified());
            entity.setRecvByInvFl(oldDto.getRecvByInvFl());
            entity.setAsnRcvAllowedFl(oldDto.getAsnRcvAllowedFl());
            entity.setAccountNo(oldDto.getAccountNo());
            entity.setAllowEdiFl(oldDto.getAllowEdiFl());
            entity.setLandedCostElements(oldDto.getLandedCostElements());
            entity.setEdiVenSiteId(oldDto.getEdiVenSiteId());
            entity.setEmployeeId(oldDto.getEmployeeId());
            entity.setContact(oldDto.getContact());
            entity.setContact2(oldDto.getContact2());
            entity.setEmailAddress(oldDto.getEmailAddress());
            entity.setPhoneNo(oldDto.getPhoneNo());
            entity.setPhone2No(oldDto.getPhone2No());
            entity.setFaxNo(oldDto.getFaxNo());
            entity.setShipTerms(oldDto.getShipTerms());
            entity.setShipVia(oldDto.getShipVia());
            entity.setFob(oldDto.getFob());
            entity.setLeadTime(oldDto.getLeadTime());
            entity.setCancelLead(oldDto.getCancelLead());
            entity.setExpectLead(oldDto.getExpectLead());
            entity.setTermCode(oldDto.getTermCode());
            entity.setNetDays(oldDto.getNetDays());
            entity.setPaymentTermDays1(oldDto.getPaymentTermDays1());
            entity.setPaymentTermDays2(oldDto.getPaymentTermDays2());
            entity.setPaymentTermPct1(oldDto.getPaymentTermPct1());
            entity.setPaymentTermPct2(oldDto.getPaymentTermPct2());
            entity.setDueDt(oldDto.getDueDt());
            entity.setMinPoAmt(oldDto.getMinPoAmt());
            entity.setLockLeadTimeFl(oldDto.getLockLeadTimeFl());
            	
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

    public static List<VenSiteDTO> toDTOList(Collection<VenSiteEntity> entities) {
        if (entities == null) {
            return null;
        }
        	
        List<VenSiteDTO> dtos = new ArrayList<VenSiteDTO>();
        for (VenSiteEntity entity: entities) {
            dtos.add(toDTO(entity));
        }
        	
        return dtos;
    }

    @Deprecated
    public static List<VenSiteDTO> toDTO(Collection<VenSiteEntity> entities) {
        return toDTOList(entities);
    }

    public static VenSiteDTOId toDTOId(VenSiteEntityId id) {
        if (id == null) {
            return null;
        }
        return new VenSiteDTOId(id.getVendorId(), id.getSiteNo());
    }

    @Deprecated
    public static VenSiteDTOId toDTO(VenSiteEntityId id) {
        return toDTOId(id);
    }

    public static List<VenSiteDTOId> toDTOIdList(Collection<VenSiteEntityId> entityIds) {
        if (entityIds == null) {
            return null;
        }
        	
        List<VenSiteDTOId> dtoIds = new ArrayList<VenSiteDTOId>();
        for (VenSiteEntityId entity: entityIds) {
            dtoIds.add(toDTOId(entity));
        }
        	
        return dtoIds;
    }

    public static Set<VenSiteEntity> toEntitySet(Collection<VenSiteDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        Set<VenSiteEntity> entities = new HashSet<VenSiteEntity>();
        for (VenSiteDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static Set<VenSiteEntity> toEntity(Collection<VenSiteDTO> dtos) {
        return toEntitySet(dtos);
    }

    public static SortedSet<VenSiteEntity> toEntitySortedSet(Collection<VenSiteDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        SortedSet<VenSiteEntity> entities = new TreeSet<VenSiteEntity>();
        for (VenSiteDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static SortedSet<VenSiteEntity> toEntitySorted(Collection<VenSiteDTO> dtos) {
        return toEntitySortedSet(dtos);
    }

    public static List<VenSiteEntity> toEntityList(Collection<VenSiteDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        List<VenSiteEntity> entities = new ArrayList<VenSiteEntity>();
        for (VenSiteDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    public static VenSiteEntityId toEntityId(VenSiteDTOId dto) {
        if (dto == null) {
            return null;
        }
        return new VenSiteEntityId(dto.getVendorId(), dto.getSiteNo());
    }

    @Deprecated
    public static VenSiteEntityId toEntity(VenSiteDTOId id) {
        return toEntityId(id);
    }

    public static List<VenSiteEntityId> toEntityIdList(Collection<VenSiteDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        List<VenSiteEntityId> entityIds = new ArrayList<VenSiteEntityId>();
        for (VenSiteDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static Set<VenSiteEntityId> toEntityIdSet(Collection<VenSiteDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        Set<VenSiteEntityId> entityIds = new HashSet<VenSiteEntityId>();
        for (VenSiteDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static SortedSet<VenSiteEntityId> toEntityIdSortedSet(Collection<VenSiteDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        SortedSet<VenSiteEntityId> entityIds = new TreeSet<VenSiteEntityId>();
        for (VenSiteDTOId dtoId: dtoIds) {
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
        final VenSiteEntity other = ((VenSiteEntity) obj);
        // Identifier level comparison
        if (this.identifier.getVendorId() == null) {
            return false;
        } else {
            if (!this.identifier.getVendorId().equals(other.identifier.getVendorId())) {
                return false;
            }
        }
        if (this.identifier.getSiteNo() == null) {
            return false;
        } else {
            if (!this.identifier.getSiteNo().equals(other.identifier.getSiteNo())) {
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
        final VenSiteEntity other = ((VenSiteEntity) obj);
        // Data level comparison
        if (dsdType == null) {
            if (other.dsdType!= null) {
                return false;
            }
        } else {
            if (!dsdType.equals(other.dsdType)) {
                return false;
            }
        }
        if (receiveLevel == null) {
            if (other.receiveLevel!= null) {
                return false;
            }
        } else {
            if (!receiveLevel.equals(other.receiveLevel)) {
                return false;
            }
        }
        if (invoiceRoundingFl == null) {
            if (other.invoiceRoundingFl!= null) {
                return false;
            }
        } else {
            if (!invoiceRoundingFl.equals(other.invoiceRoundingFl)) {
                return false;
            }
        }
        if (receiveExceptionQty == null) {
            if (other.receiveExceptionQty!= null) {
                return false;
            }
        } else {
            if (!receiveExceptionQty.equals(other.receiveExceptionQty)) {
                return false;
            }
        }
        if (receiveExceptionAmt == null) {
            if (other.receiveExceptionAmt!= null) {
                return false;
            }
        } else {
            if (!receiveExceptionAmt.equals(other.receiveExceptionAmt)) {
                return false;
            }
        }
        if (extCustEntity == null) {
            if (other.extCustEntity!= null) {
                return false;
            }
        } else {
            if (!extCustEntity.equals(other.extCustEntity)) {
                return false;
            }
        }
        if (extCustId == null) {
            if (other.extCustId!= null) {
                return false;
            }
        } else {
            if (!extCustId.equals(other.extCustId)) {
                return false;
            }
        }
        if (paymentMethod == null) {
            if (other.paymentMethod!= null) {
                return false;
            }
        } else {
            if (!paymentMethod.equals(other.paymentMethod)) {
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
        if (returnCoreWeight == null) {
            if (other.returnCoreWeight!= null) {
                return false;
            }
        } else {
            if (!returnCoreWeight.equals(other.returnCoreWeight)) {
                return false;
            }
        }
        if (baseZone == null) {
            if (other.baseZone!= null) {
                return false;
            }
        } else {
            if (!baseZone.equals(other.baseZone)) {
                return false;
            }
        }
        if (promoZone == null) {
            if (other.promoZone!= null) {
                return false;
            }
        } else {
            if (!promoZone.equals(other.promoZone)) {
                return false;
            }
        }
        if (primaryVendorFl == null) {
            if (other.primaryVendorFl!= null) {
                return false;
            }
        } else {
            if (!primaryVendorFl.equals(other.primaryVendorFl)) {
                return false;
            }
        }
        if (setToPrimaryDt == null) {
            if (other.setToPrimaryDt!= null) {
                return false;
            }
        } else {
            if (!setToPrimaryDt.equals(other.setToPrimaryDt)) {
                return false;
            }
        }
        if (userId == null) {
            if (other.userId!= null) {
                return false;
            }
        } else {
            if (!userId.equals(other.userId)) {
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
        if (recvByInvFl == null) {
            if (other.recvByInvFl!= null) {
                return false;
            }
        } else {
            if (!recvByInvFl.equals(other.recvByInvFl)) {
                return false;
            }
        }
        if (asnRcvAllowedFl == null) {
            if (other.asnRcvAllowedFl!= null) {
                return false;
            }
        } else {
            if (!asnRcvAllowedFl.equals(other.asnRcvAllowedFl)) {
                return false;
            }
        }
        if (accountNo == null) {
            if (other.accountNo!= null) {
                return false;
            }
        } else {
            if (!accountNo.equals(other.accountNo)) {
                return false;
            }
        }
        if (allowEdiFl == null) {
            if (other.allowEdiFl!= null) {
                return false;
            }
        } else {
            if (!allowEdiFl.equals(other.allowEdiFl)) {
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
        if (ediVenSiteId == null) {
            if (other.ediVenSiteId!= null) {
                return false;
            }
        } else {
            if (!ediVenSiteId.equals(other.ediVenSiteId)) {
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
        if (emailAddress == null) {
            if (other.emailAddress!= null) {
                return false;
            }
        } else {
            if (!emailAddress.equals(other.emailAddress)) {
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
        if (shipTerms == null) {
            if (other.shipTerms!= null) {
                return false;
            }
        } else {
            if (!shipTerms.equals(other.shipTerms)) {
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
        if (fob == null) {
            if (other.fob!= null) {
                return false;
            }
        } else {
            if (!fob.equals(other.fob)) {
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
        if (termCode == null) {
            if (other.termCode!= null) {
                return false;
            }
        } else {
            if (!termCode.equals(other.termCode)) {
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
        if (dueDt == null) {
            if (other.dueDt!= null) {
                return false;
            }
        } else {
            if (!dueDt.equals(other.dueDt)) {
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
        if (lockLeadTimeFl == null) {
            if (other.lockLeadTimeFl!= null) {
                return false;
            }
        } else {
            if (!lockLeadTimeFl.equals(other.lockLeadTimeFl)) {
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
        final VenSiteEntity other = ((VenSiteEntity) obj);
        // Identifier level comparison
        if (this.identifier.getVendorId() == null) {
            return false;
        } else {
            if (!this.identifier.getVendorId().equals(other.identifier.getVendorId())) {
                return false;
            }
        }
        if (this.identifier.getSiteNo() == null) {
            return false;
        } else {
            if (!this.identifier.getSiteNo().equals(other.identifier.getSiteNo())) {
                return false;
            }
        }
        // Data level comparison
        if (dsdType == null) {
            if (other.dsdType!= null) {
                return false;
            }
        } else {
            if (!dsdType.equals(other.dsdType)) {
                return false;
            }
        }
        if (receiveLevel == null) {
            if (other.receiveLevel!= null) {
                return false;
            }
        } else {
            if (!receiveLevel.equals(other.receiveLevel)) {
                return false;
            }
        }
        if (invoiceRoundingFl == null) {
            if (other.invoiceRoundingFl!= null) {
                return false;
            }
        } else {
            if (!invoiceRoundingFl.equals(other.invoiceRoundingFl)) {
                return false;
            }
        }
        if (receiveExceptionQty == null) {
            if (other.receiveExceptionQty!= null) {
                return false;
            }
        } else {
            if (!receiveExceptionQty.equals(other.receiveExceptionQty)) {
                return false;
            }
        }
        if (receiveExceptionAmt == null) {
            if (other.receiveExceptionAmt!= null) {
                return false;
            }
        } else {
            if (!receiveExceptionAmt.equals(other.receiveExceptionAmt)) {
                return false;
            }
        }
        if (extCustEntity == null) {
            if (other.extCustEntity!= null) {
                return false;
            }
        } else {
            if (!extCustEntity.equals(other.extCustEntity)) {
                return false;
            }
        }
        if (extCustId == null) {
            if (other.extCustId!= null) {
                return false;
            }
        } else {
            if (!extCustId.equals(other.extCustId)) {
                return false;
            }
        }
        if (paymentMethod == null) {
            if (other.paymentMethod!= null) {
                return false;
            }
        } else {
            if (!paymentMethod.equals(other.paymentMethod)) {
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
        if (returnCoreWeight == null) {
            if (other.returnCoreWeight!= null) {
                return false;
            }
        } else {
            if (!returnCoreWeight.equals(other.returnCoreWeight)) {
                return false;
            }
        }
        if (baseZone == null) {
            if (other.baseZone!= null) {
                return false;
            }
        } else {
            if (!baseZone.equals(other.baseZone)) {
                return false;
            }
        }
        if (promoZone == null) {
            if (other.promoZone!= null) {
                return false;
            }
        } else {
            if (!promoZone.equals(other.promoZone)) {
                return false;
            }
        }
        if (primaryVendorFl == null) {
            if (other.primaryVendorFl!= null) {
                return false;
            }
        } else {
            if (!primaryVendorFl.equals(other.primaryVendorFl)) {
                return false;
            }
        }
        if (setToPrimaryDt == null) {
            if (other.setToPrimaryDt!= null) {
                return false;
            }
        } else {
            if (!setToPrimaryDt.equals(other.setToPrimaryDt)) {
                return false;
            }
        }
        if (userId == null) {
            if (other.userId!= null) {
                return false;
            }
        } else {
            if (!userId.equals(other.userId)) {
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
        if (recvByInvFl == null) {
            if (other.recvByInvFl!= null) {
                return false;
            }
        } else {
            if (!recvByInvFl.equals(other.recvByInvFl)) {
                return false;
            }
        }
        if (asnRcvAllowedFl == null) {
            if (other.asnRcvAllowedFl!= null) {
                return false;
            }
        } else {
            if (!asnRcvAllowedFl.equals(other.asnRcvAllowedFl)) {
                return false;
            }
        }
        if (accountNo == null) {
            if (other.accountNo!= null) {
                return false;
            }
        } else {
            if (!accountNo.equals(other.accountNo)) {
                return false;
            }
        }
        if (allowEdiFl == null) {
            if (other.allowEdiFl!= null) {
                return false;
            }
        } else {
            if (!allowEdiFl.equals(other.allowEdiFl)) {
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
        if (ediVenSiteId == null) {
            if (other.ediVenSiteId!= null) {
                return false;
            }
        } else {
            if (!ediVenSiteId.equals(other.ediVenSiteId)) {
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
        if (emailAddress == null) {
            if (other.emailAddress!= null) {
                return false;
            }
        } else {
            if (!emailAddress.equals(other.emailAddress)) {
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
        if (shipTerms == null) {
            if (other.shipTerms!= null) {
                return false;
            }
        } else {
            if (!shipTerms.equals(other.shipTerms)) {
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
        if (fob == null) {
            if (other.fob!= null) {
                return false;
            }
        } else {
            if (!fob.equals(other.fob)) {
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
        if (termCode == null) {
            if (other.termCode!= null) {
                return false;
            }
        } else {
            if (!termCode.equals(other.termCode)) {
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
        if (dueDt == null) {
            if (other.dueDt!= null) {
                return false;
            }
        } else {
            if (!dueDt.equals(other.dueDt)) {
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
        if (lockLeadTimeFl == null) {
            if (other.lockLeadTimeFl!= null) {
                return false;
            }
        } else {
            if (!lockLeadTimeFl.equals(other.lockLeadTimeFl)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        // If any ID columns are null, compare just the object instance it self.  This preserves objects that have not been assigned ID's yet.
        if ((this.identifier.getVendorId() == null)||(this.identifier.getSiteNo() == null)) {
            return super.hashCode();
        }
        	
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier.getVendorId() == null) ? 0 : identifier.getVendorId().hashCode());
        result = prime * result + ((identifier.getSiteNo() == null) ? 0 : identifier.getSiteNo().hashCode());
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
    public int compareTo(VenSiteEntity other) {
        return this.identifier.compareTo(other.identifier);
    }

    /**
     * This method displays bean properties for debugging purposes.  Do not use for anything else as it may change to enhance debugging in the future.
     * 
     */
    public String toString() {
        final StringBuilder temp = new StringBuilder();
        	
        temp.append("\n");
        temp.append("VenSiteEntity (VEN_SITE)\n");
        temp.append("  ID   1 : vendorId                       : VENDOR_ID                      : ").append(this.identifier.getVendorId()).append("\n");
        temp.append("  ID   2 : siteNo                         : SITE_NO                        : ").append(this.identifier.getSiteNo()).append("\n");
        temp.append("       3 : dsdType                        : DSD_TYPE                       : ").append(dsdType).append("\n");
        temp.append("       4 : receiveLevel                   : RECEIVE_LEVEL                  : ").append(receiveLevel).append("\n");
        temp.append("       5 : invoiceRoundingFl              : INVOICE_ROUNDING_FL            : ").append(invoiceRoundingFl).append("\n");
        temp.append("       6 : receiveExceptionQty            : RECEIVE_EXCEPTION_QTY          : ").append(receiveExceptionQty).append("\n");
        temp.append("       7 : receiveExceptionAmt            : RECEIVE_EXCEPTION_AMT          : ").append(receiveExceptionAmt).append("\n");
        temp.append("       8 : extCustEntity                  : EXT_CUST_ENTITY                : ").append(extCustEntity).append("\n");
        temp.append("       9 : extCustId                      : EXT_CUST_ID                    : ").append(extCustId).append("\n");
        temp.append("      10 : paymentMethod                  : PAYMENT_METHOD                 : ").append(paymentMethod).append("\n");
        temp.append("      11 : freightMinLineItems            : FREIGHT_MIN_LINE_ITEMS         : ").append(freightMinLineItems).append("\n");
        temp.append("      12 : freightMinUnits                : FREIGHT_MIN_UNITS              : ").append(freightMinUnits).append("\n");
        temp.append("      13 : freightMinCases                : FREIGHT_MIN_CASES              : ").append(freightMinCases).append("\n");
        temp.append("      14 : freightMinAmt                  : FREIGHT_MIN_AMT                : ").append(freightMinAmt).append("\n");
        temp.append("      15 : freightMinWeight               : FREIGHT_MIN_WEIGHT             : ").append(freightMinWeight).append("\n");
        temp.append("      16 : freightMinCube                 : FREIGHT_MIN_CUBE               : ").append(freightMinCube).append("\n");
        temp.append("      17 : freightTolerancePct            : FREIGHT_TOLERANCE_PCT          : ").append(freightTolerancePct).append("\n");
        temp.append("      18 : returnCoreWeight               : RETURN_CORE_WEIGHT             : ").append(returnCoreWeight).append("\n");
        temp.append("      19 : baseZone                       : BASE_ZONE                      : ").append(baseZone).append("\n");
        temp.append("      20 : promoZone                      : PROMO_ZONE                     : ").append(promoZone).append("\n");
        temp.append("      21 : primaryVendorFl                : PRIMARY_VENDOR_FL              : ").append(primaryVendorFl).append("\n");
        temp.append("      22 : setToPrimaryDt                 : SET_TO_PRIMARY_DT              : ").append(setToPrimaryDt).append("\n");
        temp.append("      23 : userId                         : USER_ID                        : ").append(userId).append("\n");
        temp.append("      24 : invToleranceQty                : INV_TOLERANCE_QTY              : ").append(invToleranceQty).append("\n");
        temp.append("      25 : invToleranceQtyFl              : INV_TOLERANCE_QTY_FL           : ").append(invToleranceQtyFl).append("\n");
        temp.append("      26 : invTolerancePct                : INV_TOLERANCE_PCT              : ").append(invTolerancePct).append("\n");
        temp.append("      27 : invTolerancePctFl              : INV_TOLERANCE_PCT_FL           : ").append(invTolerancePctFl).append("\n");
        temp.append("      28 : invToleranceAmt                : INV_TOLERANCE_AMT              : ").append(invToleranceAmt).append("\n");
        temp.append("      29 : invToleranceAmtFl              : INV_TOLERANCE_AMT_FL           : ").append(invToleranceAmtFl).append("\n");
        temp.append("      30 : freightToleranceAmt            : FREIGHT_TOLERANCE_AMT          : ").append(freightToleranceAmt).append("\n");
        temp.append("      31 : freightToleranceAmtFl          : FREIGHT_TOLERANCE_AMT_FL       : ").append(freightToleranceAmtFl).append("\n");
        temp.append("      32 : freightTolerancePctFl          : FREIGHT_TOLERANCE_PCT_FL       : ").append(freightTolerancePctFl).append("\n");
        temp.append("      33 : reconMethod                    : RECON_METHOD                   : ").append(reconMethod).append("\n");
        temp.append("  AD  34 : dateChanged                    : DATE_CHANGED                   : ").append(dateChanged).append("\n");
        temp.append("  AD  35 : dateCreated                    : DATE_CREATED                   : ").append(dateCreated).append("\n");
        temp.append("  AD  36 : userCreated                    : USER_CREATED                   : ").append(userCreated).append("\n");
        temp.append("  AD  37 : dateModified                   : DATE_MODIFIED                  : ").append(dateModified).append("\n");
        temp.append("  AD  38 : userModified                   : USER_MODIFIED                  : ").append(userModified).append("\n");
        temp.append("      39 : recvByInvFl                    : RECV_BY_INV_FL                 : ").append(recvByInvFl).append("\n");
        temp.append("      40 : asnRcvAllowedFl                : ASN_RCV_ALLOWED_FL             : ").append(asnRcvAllowedFl).append("\n");
        temp.append("      41 : accountNo                      : ACCOUNT_NO                     : ").append(accountNo).append("\n");
        temp.append("      42 : allowEdiFl                     : ALLOW_EDI_FL                   : ").append(allowEdiFl).append("\n");
        temp.append("      43 : landedCostElements             : LANDED_COST_ELEMENTS           : ").append(landedCostElements).append("\n");
        temp.append("      44 : ediVenSiteId                   : EDI_VEN_SITE_ID                : ").append(ediVenSiteId).append("\n");
        temp.append("      45 : employeeId                     : EMPLOYEE_ID                    : ").append(employeeId).append("\n");
        temp.append("      46 : contact                        : CONTACT                        : ").append(contact).append("\n");
        temp.append("      47 : contact2                       : CONTACT2                       : ").append(contact2).append("\n");
        temp.append("      48 : emailAddress                   : EMAIL_ADDRESS                  : ").append(emailAddress).append("\n");
        temp.append("      49 : phoneNo                        : PHONE_NO                       : ").append(phoneNo).append("\n");
        temp.append("      50 : phone2No                       : PHONE2_NO                      : ").append(phone2No).append("\n");
        temp.append("      51 : faxNo                          : FAX_NO                         : ").append(faxNo).append("\n");
        temp.append("      52 : shipTerms                      : SHIP_TERMS                     : ").append(shipTerms).append("\n");
        temp.append("      53 : shipVia                        : SHIP_VIA                       : ").append(shipVia).append("\n");
        temp.append("      54 : fob                            : FOB                            : ").append(fob).append("\n");
        temp.append("      55 : leadTime                       : LEAD_TIME                      : ").append(leadTime).append("\n");
        temp.append("      56 : cancelLead                     : CANCEL_LEAD                    : ").append(cancelLead).append("\n");
        temp.append("      57 : expectLead                     : EXPECT_LEAD                    : ").append(expectLead).append("\n");
        temp.append("      58 : termCode                       : TERM_CODE                      : ").append(termCode).append("\n");
        temp.append("      59 : netDays                        : NET_DAYS                       : ").append(netDays).append("\n");
        temp.append("      60 : paymentTermDays1               : PAYMENT_TERM_DAYS1             : ").append(paymentTermDays1).append("\n");
        temp.append("      61 : paymentTermDays2               : PAYMENT_TERM_DAYS2             : ").append(paymentTermDays2).append("\n");
        temp.append("      62 : paymentTermPct1                : PAYMENT_TERM_PCT1              : ").append(paymentTermPct1).append("\n");
        temp.append("      63 : paymentTermPct2                : PAYMENT_TERM_PCT2              : ").append(paymentTermPct2).append("\n");
        temp.append("      64 : dueDt                          : DUE_DT                         : ").append(dueDt).append("\n");
        temp.append("      65 : minPoAmt                       : MIN_PO_AMT                     : ").append(minPoAmt).append("\n");
        temp.append("      66 : lockLeadTimeFl                 : LOCK_LEAD_TIME_FL              : ").append(lockLeadTimeFl).append("\n");
        	
        return temp.toString();
    }

}
