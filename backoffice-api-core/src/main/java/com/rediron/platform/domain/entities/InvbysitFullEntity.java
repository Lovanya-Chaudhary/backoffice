//
// @formatter:off
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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import com.rediron.platform.domain.rnet.DtoToEntity;
import com.rediron.platform.domain.rnet.RNetAbstractEntity;
import com.tomax.api.DataTransferObject;
import com.tomax.api.RNetExceptionProxy;
import com.tomax.api.RNetUnexpectedException;
import com.tomax.api.annotation.Parent;
import com.tomax.inventory.dto.InvbysitDTOId;
import com.tomax.inventory.dto.InvbysitFullDTO;
import com.tomax.inventory.dto.InvtoryFullDTO;



@SuppressWarnings("all")
@Entity
@Table(name = "INVBYSIT")
public class InvbysitFullEntity
    extends RNetAbstractEntity<InvbysitFullEntity, InvbysitFullDTO>
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
    private InvbysitEntityId identifier;
    /**
     * <p>Bidirectional ManyToOne owning relationship to {@link InvtoryFullEntity InvtoryFullEntity}.</p><p><b>Column Mapping:</b><br/>
     * INVBYSIT.SKU_NO -> INVTORY.SKU_NO<br>
     * </p>
     * 
     */
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "SKU_NO", referencedColumnName = "SKU_NO", insertable = false, updatable = false)
    })
    private InvtoryFullEntity invtory;
    /**
     * <p>Maps to table column: {@code PROMO_KEY_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 0, message = "PROMO_KEY_NO should have an integer component no longer than 10 and an a fraction component of exact size 0")
    @Column(name = "PROMO_KEY_NO")
    private Long promoKeyNo;
    /**
     * <p>Maps to table column: {@code LOCATION1}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 10, message = "LOCATION1 should contain less than or equal to 10 characters")
    @Column(name = "LOCATION1")
    private String location1;
    /**
     * <p>Maps to table column: {@code LOCATION2}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 10, message = "LOCATION2 should contain less than or equal to 10 characters")
    @Column(name = "LOCATION2")
    private String location2;
    /**
     * <p>Maps to table column: {@code MIN_LEVEL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "MIN_LEVEL should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "MIN_LEVEL")
    private BigDecimal minLevel;
    /**
     * <p>Maps to table column: {@code MAX_LEVEL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "MAX_LEVEL should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "MAX_LEVEL")
    private BigDecimal maxLevel;
    /**
     * <p>Maps to table column: {@code REORDER_PT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "REORDER_PT should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "REORDER_PT")
    private BigDecimal reorderPt;
    /**
     * <p>Maps to table column: {@code REORDER_TO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "REORDER_TO should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "REORDER_TO")
    private BigDecimal reorderTo;
    /**
     * <p>Maps to table column: {@code PRICE_TYPE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "PRICE_TYPE should contain less than or equal to 1 characters")
    @Column(name = "PRICE_TYPE")
    private String priceType;
    /**
     * <p>Maps to table column: {@code UNIT_PRICE1}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "UNIT_PRICE1 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "UNIT_PRICE1")
    private BigDecimal unitPrice1;
    /**
     * <p>Maps to table column: {@code UNIT_PRICE2}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "UNIT_PRICE2 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "UNIT_PRICE2")
    private BigDecimal unitPrice2;
    /**
     * <p>Maps to table column: {@code UNIT_PRICE3}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "UNIT_PRICE3 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "UNIT_PRICE3")
    private BigDecimal unitPrice3;
    /**
     * <p>Maps to table column: {@code UNIT_PRICE4}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "UNIT_PRICE4 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "UNIT_PRICE4")
    private BigDecimal unitPrice4;
    /**
     * <p>Maps to table column: {@code UNIT_PRICE5}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "UNIT_PRICE5 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "UNIT_PRICE5")
    private BigDecimal unitPrice5;
    /**
     * <p>Maps to table column: {@code UNIT_PRICE6}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "UNIT_PRICE6 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "UNIT_PRICE6")
    private BigDecimal unitPrice6;
    /**
     * <p>Maps to table column: {@code UNIT_PRICE7}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "UNIT_PRICE7 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "UNIT_PRICE7")
    private BigDecimal unitPrice7;
    /**
     * <p>Maps to table column: {@code UNIT_PRICE8}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "UNIT_PRICE8 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "UNIT_PRICE8")
    private BigDecimal unitPrice8;
    /**
     * <p>Maps to table column: {@code UNIT_PRICE9}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "UNIT_PRICE9 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "UNIT_PRICE9")
    private BigDecimal unitPrice9;
    /**
     * <p>Maps to table column: {@code UNIT_PRICE10}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "UNIT_PRICE10 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "UNIT_PRICE10")
    private BigDecimal unitPrice10;
    /**
     * <p>Maps to table column: {@code PRICE_QTY1}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PRICE_QTY1 should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PRICE_QTY1")
    private Integer priceQty1;
    /**
     * <p>Maps to table column: {@code PRICE_QTY2}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PRICE_QTY2 should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PRICE_QTY2")
    private Integer priceQty2;
    /**
     * <p>Maps to table column: {@code PRICE_QTY3}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PRICE_QTY3 should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PRICE_QTY3")
    private Integer priceQty3;
    /**
     * <p>Maps to table column: {@code PRICE_QTY4}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PRICE_QTY4 should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PRICE_QTY4")
    private Integer priceQty4;
    /**
     * <p>Maps to table column: {@code PRICE_QTY5}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PRICE_QTY5 should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PRICE_QTY5")
    private Integer priceQty5;
    /**
     * <p>Maps to table column: {@code PRICE_QTY6}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PRICE_QTY6 should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PRICE_QTY6")
    private Integer priceQty6;
    /**
     * <p>Maps to table column: {@code PRICE_QTY7}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PRICE_QTY7 should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PRICE_QTY7")
    private Integer priceQty7;
    /**
     * <p>Maps to table column: {@code PRICE_QTY8}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PRICE_QTY8 should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PRICE_QTY8")
    private Integer priceQty8;
    /**
     * <p>Maps to table column: {@code PRICE_QTY9}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PRICE_QTY9 should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PRICE_QTY9")
    private Integer priceQty9;
    /**
     * <p>Maps to table column: {@code PRICE_QTY10}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PRICE_QTY10 should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PRICE_QTY10")
    private Integer priceQty10;
    /**
     * <p>Maps to table column: {@code PRICE1}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PRICE1 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PRICE1")
    private BigDecimal price1;
    /**
     * <p>Maps to table column: {@code PRICE2}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PRICE2 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PRICE2")
    private BigDecimal price2;
    /**
     * <p>Maps to table column: {@code PRICE3}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PRICE3 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PRICE3")
    private BigDecimal price3;
    /**
     * <p>Maps to table column: {@code PRICE4}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PRICE4 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PRICE4")
    private BigDecimal price4;
    /**
     * <p>Maps to table column: {@code PRICE5}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PRICE5 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PRICE5")
    private BigDecimal price5;
    /**
     * <p>Maps to table column: {@code PRICE6}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PRICE6 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PRICE6")
    private BigDecimal price6;
    /**
     * <p>Maps to table column: {@code PRICE7}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PRICE7 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PRICE7")
    private BigDecimal price7;
    /**
     * <p>Maps to table column: {@code PRICE8}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PRICE8 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PRICE8")
    private BigDecimal price8;
    /**
     * <p>Maps to table column: {@code PRICE9}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PRICE9 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PRICE9")
    private BigDecimal price9;
    /**
     * <p>Maps to table column: {@code PRICE10}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PRICE10 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PRICE10")
    private BigDecimal price10;
    /**
     * <p>Maps to table column: {@code EPO_PCT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 2, message = "EPO_PCT should have an integer component no longer than 3 and an a fraction component of exact size 2")
    @Column(name = "EPO_PCT")
    private BigDecimal epoPct;
    /**
     * <p>Maps to table column: {@code MSRP_UNIT_PRICE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "MSRP_UNIT_PRICE should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "MSRP_UNIT_PRICE")
    private BigDecimal msrpUnitPrice;
    /**
     * <p>Maps to table column: {@code MSRP_PRICE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "MSRP_PRICE should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "MSRP_PRICE")
    private BigDecimal msrpPrice;
    /**
     * <p>Maps to table column: {@code MSRP_QTY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "MSRP_QTY should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "MSRP_QTY")
    private Integer msrpQty;
    /**
     * <p>Maps to table column: {@code PKG_PRICE_QTY1}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PKG_PRICE_QTY1 should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PKG_PRICE_QTY1")
    private Integer pkgPriceQty1;
    /**
     * <p>Maps to table column: {@code PKG_PRICE_QTY2}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PKG_PRICE_QTY2 should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PKG_PRICE_QTY2")
    private Integer pkgPriceQty2;
    /**
     * <p>Maps to table column: {@code PKG_PRICE_QTY3}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PKG_PRICE_QTY3 should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PKG_PRICE_QTY3")
    private Integer pkgPriceQty3;
    /**
     * <p>Maps to table column: {@code PKG_PRICE1}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PKG_PRICE1 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PKG_PRICE1")
    private BigDecimal pkgPrice1;
    /**
     * <p>Maps to table column: {@code PKG_PRICE2}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PKG_PRICE2 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PKG_PRICE2")
    private BigDecimal pkgPrice2;
    /**
     * <p>Maps to table column: {@code PKG_PRICE3}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PKG_PRICE3 should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PKG_PRICE3")
    private BigDecimal pkgPrice3;
    /**
     * <p>Maps to table column: {@code PRE_MARKDOWN_PRICE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "PRE_MARKDOWN_PRICE should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "PRE_MARKDOWN_PRICE")
    private BigDecimal preMarkdownPrice;
    /**
     * <p>Maps to table column: {@code NO_OF_FACINGS}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "NO_OF_FACINGS should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "NO_OF_FACINGS")
    private Integer noOfFacings;
    /**
     * <p>Maps to table column: {@code SHELF_VELOCITY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 0, message = "SHELF_VELOCITY should have an integer component no longer than 10 and an a fraction component of exact size 0")
    @Column(name = "SHELF_VELOCITY")
    private Long shelfVelocity;
    /**
     * <p>Maps to table column: {@code COMMISSION_CD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "COMMISSION_CD should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "COMMISSION_CD")
    private Integer commissionCd;
    /**
     * <p>Maps to table column: {@code INV_COST}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "INV_COST should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "INV_COST")
    private BigDecimal invCost;
    /**
     * <p>Maps to table column: {@code STANDARD_COST}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 4, message = "STANDARD_COST should have an integer component no longer than 6 and an a fraction component of exact size 4")
    @Column(name = "STANDARD_COST")
    private BigDecimal standardCost;
    /**
     * <p>Maps to table column: {@code AVG_AGE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 1, message = "AVG_AGE should have an integer component no longer than 4 and an a fraction component of exact size 1")
    @Column(name = "AVG_AGE")
    private BigDecimal avgAge;
    /**
     * <p>Maps to table column: {@code FIRST_RCVD_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "FIRST_RCVD_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstRcvdDt;
    /**
     * <p>Maps to table column: {@code LAST_RCVD_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_RCVD_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastRcvdDt;
    /**
     * <p>Maps to table column: {@code QTY_LAST_RCVD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 2, message = "QTY_LAST_RCVD should have an integer component no longer than 10 and an a fraction component of exact size 2")
    @Column(name = "QTY_LAST_RCVD")
    private BigDecimal qtyLastRcvd;
    /**
     * <p>Maps to table column: {@code LAST_ORD_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_ORD_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastOrdDt;
    /**
     * <p>Maps to table column: {@code LAST_SOLD_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_SOLD_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSoldDt;
    /**
     * <p>Maps to table column: {@code LAST_SOLD_PREVIOUS_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_SOLD_PREVIOUS_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSoldPreviousDt;
    /**
     * <p>Maps to table column: {@code QTY_ON_HAND}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 2, message = "QTY_ON_HAND should have an integer component no longer than 10 and an a fraction component of exact size 2")
    @Column(name = "QTY_ON_HAND")
    private BigDecimal qtyOnHand;
    /**
     * <p>Maps to table column: {@code QTY_RSVD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 2, message = "QTY_RSVD should have an integer component no longer than 10 and an a fraction component of exact size 2")
    @Column(name = "QTY_RSVD")
    private BigDecimal qtyRsvd;
    /**
     * <p>Maps to table column: {@code QTY_ON_ORD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 2, message = "QTY_ON_ORD should have an integer component no longer than 10 and an a fraction component of exact size 2")
    @Column(name = "QTY_ON_ORD")
    private BigDecimal qtyOnOrd;
    /**
     * <p>Maps to table column: {@code QTY_ON_BACKORD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 2, message = "QTY_ON_BACKORD should have an integer component no longer than 10 and an a fraction component of exact size 2")
    @Column(name = "QTY_ON_BACKORD")
    private BigDecimal qtyOnBackord;
    /**
     * <p>Maps to table column: {@code QTY_IN_TRANSIT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 2, message = "QTY_IN_TRANSIT should have an integer component no longer than 10 and an a fraction component of exact size 2")
    @Column(name = "QTY_IN_TRANSIT")
    private BigDecimal qtyInTransit;
    /**
     * <p>Maps to table column: {@code QTY_RTV}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 2, message = "QTY_RTV should have an integer component no longer than 10 and an a fraction component of exact size 2")
    @Column(name = "QTY_RTV")
    private BigDecimal qtyRtv;
    /**
     * <p>Maps to table column: {@code EXP_RCVD_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "EXP_RCVD_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expRcvdDt;
    /**
     * <p>Maps to table column: {@code EXP_BO_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "EXP_BO_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expBoDt;
    /**
     * <p>Maps to table column: {@code QTY_COUNTED}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 2, message = "QTY_COUNTED should have an integer component no longer than 10 and an a fraction component of exact size 2")
    @Column(name = "QTY_COUNTED")
    private BigDecimal qtyCounted;
    /**
     * <p>Maps to table column: {@code MAX_ORDER_QTY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 7, fraction = 2, message = "MAX_ORDER_QTY should have an integer component no longer than 7 and an a fraction component of exact size 2")
    @Column(name = "MAX_ORDER_QTY")
    private BigDecimal maxOrderQty;
    /**
     * <p>Maps to table column: {@code MIX_MATCH_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "MIX_MATCH_NO should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "MIX_MATCH_NO")
    private Integer mixMatchNo;
    /**
     * <p>Maps to table column: {@code BOTTLE_LINK_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "BOTTLE_LINK_NO should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "BOTTLE_LINK_NO")
    private Integer bottleLinkNo;
    /**
     * <p>Maps to table column: {@code LAST_PRICE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "LAST_PRICE should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "LAST_PRICE")
    private BigDecimal lastPrice;
    /**
     * <p>Maps to table column: {@code LAST_PCHG_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_PCHG_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPchgDt;
    /**
     * <p>Maps to table column: {@code DOWNLOAD_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "DOWNLOAD_FL should contain less than or equal to 1 characters")
    @Column(name = "DOWNLOAD_FL")
    private String downloadFl;
    /**
     * <p>Maps to table column: {@code ALLOW_PCHG_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "ALLOW_PCHG_FL should contain less than or equal to 1 characters")
    @Column(name = "ALLOW_PCHG_FL")
    private String allowPchgFl;
    /**
     * <p>Maps to table column: {@code COS_PCT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 2, message = "COS_PCT should have an integer component no longer than 3 and an a fraction component of exact size 2")
    @Column(name = "COS_PCT")
    private BigDecimal cosPct;
    /**
     * <p>Maps to table column: {@code SUPERSEDED_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "SUPERSEDED_FL should contain less than or equal to 1 characters")
    @Column(name = "SUPERSEDED_FL")
    private String supersededFl;
    /**
     * <p>Maps to table column: {@code PRIOR_REORDER_PT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "PRIOR_REORDER_PT should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "PRIOR_REORDER_PT")
    private BigDecimal priorReorderPt;
    /**
     * <p>Maps to table column: {@code PRIOR_REORDER_TO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "PRIOR_REORDER_TO should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "PRIOR_REORDER_TO")
    private BigDecimal priorReorderTo;
    /**
     * <p>Maps to table column: {@code WARRANTY_INV_COST}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "WARRANTY_INV_COST should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "WARRANTY_INV_COST")
    private BigDecimal warrantyInvCost;
    /**
     * <p>Maps to table column: {@code WARRANTY_QTY_ON_HAND}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 0, message = "WARRANTY_QTY_ON_HAND should have an integer component no longer than 10 and an a fraction component of exact size 0")
    @Column(name = "WARRANTY_QTY_ON_HAND")
    private Long warrantyQtyOnHand;
    /**
     * <p>Maps to table column: {@code WEIGHTED_LEAD_TIME}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "WEIGHTED_LEAD_TIME should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "WEIGHTED_LEAD_TIME")
    private BigDecimal weightedLeadTime;
    /**
     * <p>Maps to table column: {@code CORE_SELL_PRICE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "CORE_SELL_PRICE should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "CORE_SELL_PRICE")
    private BigDecimal coreSellPrice;
    /**
     * <p>Maps to table column: {@code MO_DEMAND_FORECAST}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 10, message = "MO_DEMAND_FORECAST should have an integer component no longer than 10 and an a fraction component of exact size 10")
    @Column(name = "MO_DEMAND_FORECAST")
    private BigDecimal moDemandForecast;
    /**
     * <p>Maps to table column: {@code CORE_INV_COST}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "CORE_INV_COST should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "CORE_INV_COST")
    private BigDecimal coreInvCost;
    /**
     * <p>Maps to table column: {@code CORE_QTY_ON_HAND}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 10, fraction = 0, message = "CORE_QTY_ON_HAND should have an integer component no longer than 10 and an a fraction component of exact size 0")
    @Column(name = "CORE_QTY_ON_HAND")
    private Long coreQtyOnHand;
    /**
     * <p>Maps to table column: {@code SKU_STATUS_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 15, message = "SKU_STATUS_ID should contain less than or equal to 15 characters")
    @Column(name = "SKU_STATUS_ID")
    private String skuStatusId;
    /**
     * <p>Maps to table column: {@code NON_DISCOUNT_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "NON_DISCOUNT_FL should contain less than or equal to 1 characters")
    @Column(name = "NON_DISCOUNT_FL")
    private String nonDiscountFl;
    /**
     * <p>Maps to table column: {@code RESTRICT_SALE_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 15, message = "RESTRICT_SALE_ID should contain less than or equal to 15 characters")
    @Column(name = "RESTRICT_SALE_ID")
    private String restrictSaleId;
    /**
     * <p>Maps to table column: {@code LABEL_CD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "LABEL_CD should contain less than or equal to 1 characters")
    @Column(name = "LABEL_CD")
    private String labelCd;
    /**
     * <p>Maps to table column: {@code LAST_PRICE_INC_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_PRICE_INC_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPriceIncDt;
    /**
     * <p>Maps to table column: {@code LAST_PRICE_DEC_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_PRICE_DEC_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPriceDecDt;
    /**
     * <p>Maps to table column: {@code LAST_CYCLE_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_CYCLE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCycleDt;
    /**
     * <p>Maps to table column: {@code LAST_PRICE_VER_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_PRICE_VER_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPriceVerDt;
    /**
     * <p>Maps to table column: {@code LAST_CHANGE_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_CHANGE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChangeDt;
    /**
     * <p>Maps to table column: {@code NEW_SKU_STATUS_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 15, message = "NEW_SKU_STATUS_ID should contain less than or equal to 15 characters")
    @Column(name = "NEW_SKU_STATUS_ID")
    private String newSkuStatusId;
    /**
     * <p>Maps to table column: {@code ITEMIZER_STATUS_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 15, message = "ITEMIZER_STATUS_ID should contain less than or equal to 15 characters")
    @Column(name = "ITEMIZER_STATUS_ID")
    private String itemizerStatusId;
    /**
     * <p>Maps to table column: {@code SCAN_DEPT_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 4, message = "SCAN_DEPT_NO should contain less than or equal to 4 characters")
    @Column(name = "SCAN_DEPT_NO")
    private String scanDeptNo;
    /**
     * <p>Maps to table column: {@code SCAN_SUBDEPT_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 4, message = "SCAN_SUBDEPT_NO should contain less than or equal to 4 characters")
    @Column(name = "SCAN_SUBDEPT_NO")
    private String scanSubdeptNo;
    /**
     * <p>Maps to table column: {@code DOWNLOAD_N_TO_Y_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "DOWNLOAD_N_TO_Y_FL should contain less than or equal to 1 characters")
    @Column(name = "DOWNLOAD_N_TO_Y_FL")
    private String downloadNToYFl;
    /**
     * <p>Maps to table column: {@code LABEL_QTY1}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "LABEL_QTY1 should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "LABEL_QTY1")
    private Integer labelQty1;
    /**
     * <p>Maps to table column: {@code LABEL_QTY2}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 3, fraction = 0, message = "LABEL_QTY2 should have an integer component no longer than 3 and an a fraction component of exact size 0")
    @Column(name = "LABEL_QTY2")
    private Integer labelQty2;
    /**
     * <p>Maps to table column: {@code WAREHOUSE_INNER_PACK}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 5, fraction = 2, message = "WAREHOUSE_INNER_PACK should have an integer component no longer than 5 and an a fraction component of exact size 2")
    @Column(name = "WAREHOUSE_INNER_PACK")
    private BigDecimal warehouseInnerPack;
    /**
     * <p>Maps to table column: {@code LAST_XFER_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "LAST_XFER_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastXferDt;
    /**
     * <p>Maps to table column: {@code PRICE_OPTION_CD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 2, fraction = 0, message = "PRICE_OPTION_CD should have an integer component no longer than 2 and an a fraction component of exact size 0")
    @Column(name = "PRICE_OPTION_CD")
    private Integer priceOptionCd;
    /**
     * <p>Maps to table column: {@code PRICE_ATTRIBUTE_CD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PRICE_ATTRIBUTE_CD should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PRICE_ATTRIBUTE_CD")
    private Integer priceAttributeCd;
    /**
     * <p>Maps to table column: {@code PROMO_RECORD_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "PROMO_RECORD_NO should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "PROMO_RECORD_NO")
    private Integer promoRecordNo;
    /**
     * <p>Maps to table column: {@code LAST_VENDOR_COST}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 4, message = "LAST_VENDOR_COST should have an integer component no longer than 6 and an a fraction component of exact size 4")
    @Column(name = "LAST_VENDOR_COST")
    private BigDecimal lastVendorCost;
    /**
     * <p>Maps to table column: {@code AVG_MOVEMENT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 4, message = "AVG_MOVEMENT should have an integer component no longer than 8 and an a fraction component of exact size 4")
    @Column(name = "AVG_MOVEMENT")
    private BigDecimal avgMovement;
    /**
     * <p>Maps to table column: {@code HALO_GM}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 2, message = "HALO_GM should have an integer component no longer than 4 and an a fraction component of exact size 2")
    @Column(name = "HALO_GM")
    private BigDecimal haloGm;
    /**
     * <p>Maps to table column: {@code LALO_GM}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 2, message = "LALO_GM should have an integer component no longer than 4 and an a fraction component of exact size 2")
    @Column(name = "LALO_GM")
    private BigDecimal laloGm;
    /**
     * <p>Maps to table column: {@code REPLENISHMENT_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "REPLENISHMENT_FL should contain less than or equal to 1 characters")
    @Column(name = "REPLENISHMENT_FL")
    private String replenishmentFl;
    /**
     * <p>Maps to table column: {@code VELOCITY_CD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "VELOCITY_CD should contain less than or equal to 1 characters")
    @Column(name = "VELOCITY_CD")
    private String velocityCd;
    /**
     * <p>Maps to table column: {@code VELOCITY_LOCKED_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "VELOCITY_LOCKED_FL should contain less than or equal to 1 characters")
    @Column(name = "VELOCITY_LOCKED_FL")
    private String velocityLockedFl;
    /**
     * <p>Maps to table column: {@code VELOCITY_CHANGE_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "VELOCITY_CHANGE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date velocityChangeDt;
    /**
     * <p>Maps to table column: {@code REORDER_LOCKED_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "REORDER_LOCKED_FL should contain less than or equal to 1 characters")
    @Column(name = "REORDER_LOCKED_FL")
    private String reorderLockedFl;
    /**
     * <p>Maps to table column: {@code REORDER_NOW_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "REORDER_NOW_FL should contain less than or equal to 1 characters")
    @Column(name = "REORDER_NOW_FL")
    private String reorderNowFl;
    /**
     * <p>Maps to table column: {@code PROFILE_DATA}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 16, message = "PROFILE_DATA should contain less than or equal to 16 characters")
    @Column(name = "PROFILE_DATA")
    private String profileData;
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
     * <p>Maps to table column: {@code REORDERPOINT_SOURCE_CD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "REORDERPOINT_SOURCE_CD should contain less than or equal to 1 characters")
    @Column(name = "REORDERPOINT_SOURCE_CD")
    private String reorderpointSourceCd;
    /**
     * <p>Maps to table column: {@code DEFAULT_DC_SKU_LEVEL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "DEFAULT_DC_SKU_LEVEL should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "DEFAULT_DC_SKU_LEVEL")
    private Integer defaultDcSkuLevel;
    /**
     * <p>Maps to table column: {@code PRICE_CALC_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 15, message = "PRICE_CALC_ID should contain less than or equal to 15 characters")
    @Column(name = "PRICE_CALC_ID")
    private String priceCalcId;
    /**
     * <p>Maps to table column: {@code PROFILE_DATA_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "PROFILE_DATA_FL should contain less than or equal to 1 characters")
    @Column(name = "PROFILE_DATA_FL")
    private String profileDataFl;
    /**
     * <p>Maps to table column: {@code DISCONTINUED_ST_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "DISCONTINUED_ST_FL should contain less than or equal to 1 characters")
    @Column(name = "DISCONTINUED_ST_FL")
    private String discontinuedStFl;
    /**
     * <p>Maps to table column: {@code DISCONTINUED_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "DISCONTINUED_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date discontinuedDt;
    /**
     * <p>Maps to table column: {@code DISCONTINUED_BY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 20, message = "DISCONTINUED_BY should contain less than or equal to 20 characters")
    @Column(name = "DISCONTINUED_BY")
    private String discontinuedBy;
    /**
     * <p>Maps to table column: {@code TARE_TABLE_NO}.</p><p><b>Schema Remarks:</b> Points to a tare_table entry.  NOTE that there is no integrity constraint on this column...(By design)</p>
     * 
     */
    @Size(max = 10, message = "TARE_TABLE_NO should contain less than or equal to 10 characters")
    @Column(name = "TARE_TABLE_NO")
    private String tareTableNo;
    /**
     * <p>Maps to table column: {@code PROMO_COMMISSION_CD}.</p><p><b>Schema Remarks:</b> Promotion commission code.</p>
     * 
     */
    @Digits(integer = 10, fraction = 0, message = "PROMO_COMMISSION_CD should have an integer component no longer than 10 and an a fraction component of exact size 0")
    @Column(name = "PROMO_COMMISSION_CD")
    private Long promoCommissionCd;
    /**
     * <p>Maps to table column: {@code ADDITIONAL_TAX}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "ADDITIONAL_TAX should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "ADDITIONAL_TAX")
    private BigDecimal additionalTax;
    /**
     * <p>Maps to table column: {@code NEGATIVE_QOH_FL}.</p><p><b>Schema Remarks:</b> This flag will be set to Y if the qty on hand goes negative and set to NULL if the qty on hand is zero or positive.</p>
     * 
     */
    @Size(max = 1, message = "NEGATIVE_QOH_FL should contain less than or equal to 1 characters")
    @Column(name = "NEGATIVE_QOH_FL")
    private String negativeQohFl;
    /**
     * <p>Maps to table column: {@code POS_SKU_STATUS_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 15, message = "POS_SKU_STATUS_ID should contain less than or equal to 15 characters")
    @Column(name = "POS_SKU_STATUS_ID")
    private String posSkuStatusId;
    /**
     * <p>Maps to table column: {@code VENDOR_ITEM_SEQ_NO}.</p>Warning.  Database defines precision as '20' for this column.  This exceeds the java long type used for the @Max java validation annotation.<p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 20, fraction = 0, message = "VENDOR_ITEM_SEQ_NO should have an integer component no longer than 20 and an a fraction component of exact size 0")
    @Column(name = "VENDOR_ITEM_SEQ_NO")
    private BigInteger vendorItemSeqNo;
    /**
     * <p>Maps to table column: {@code REPLACEMENT_COST_AMT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 4, message = "REPLACEMENT_COST_AMT should have an integer component no longer than 8 and an a fraction component of exact size 4")
    @Column(name = "REPLACEMENT_COST_AMT")
    private BigDecimal replacementCostAmt;
    /**
     * <p>Maps to table column: {@code FIRST_PRICE}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 2, message = "FIRST_PRICE should have an integer component no longer than 8 and an a fraction component of exact size 2")
    @Column(name = "FIRST_PRICE")
    private BigDecimal firstPrice;
    /**
     * <p>Maps to table column: {@code FIRST_COST}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 8, fraction = 4, message = "FIRST_COST should have an integer component no longer than 8 and an a fraction component of exact size 4")
    @Column(name = "FIRST_COST")
    private BigDecimal firstCost;
    /**
     * <p>Maps to table column: {@code FIRST_SOLD_DT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "FIRST_SOLD_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstSoldDt;
    /**
     * <p>Maps to table column: {@code STOCKING_DT}.</p><p><b>Schema Remarks:</b> Date this item was stocked in the store.</p>
     * 
     */
    @Column(name = "STOCKING_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stockingDt;
    /**
     * <p>Maps to table column: {@code CLASS_CODE}.</p><p><b>Schema Remarks:</b> Classification Code</p>
     * 
     */
    @Size(max = 5, message = "CLASS_CODE should contain less than or equal to 5 characters")
    @Column(name = "CLASS_CODE")
    private String classCode;
    /**
     * <p>Maps to table column: {@code NEGATIVE_QTY_SITE_NO}.</p><p><b>Schema Remarks:</b> This column will have the site number populated by the BU trigger if the qty_on_hand+qty_rsvd is negative, otherwise it will be null</p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "NEGATIVE_QTY_SITE_NO should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "NEGATIVE_QTY_SITE_NO")
    private Integer negativeQtySiteNo;
    /**
     * <p>Maps to table column: {@code PURCHASE_VOLUME_ID}.</p><p><b>Schema Remarks:</b> Populated by the value enteredin the PDV ID field on the Inventory by Site form</p>
     * 
     */
    @Size(max = 3, message = "PURCHASE_VOLUME_ID should contain less than or equal to 3 characters")
    @Column(name = "PURCHASE_VOLUME_ID")
    private String purchaseVolumeId;
    /**
     * <p>Maps to table column: {@code REORDER_SITE_NO}.</p><p><b>Schema Remarks:</b> This a sparce populated column that will have the site number if the item needs to be re-ordered for this site.  This column is populated by the BI/BU INVBYSIT trigger</p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "REORDER_SITE_NO should have an integer component no longer than 4 and an a fraction component of exact size 0")
    @Column(name = "REORDER_SITE_NO")
    private Integer reorderSiteNo;
    /**
     * <p>Maps to table column: {@code PRODUCT_START_DT}.</p><p><b>Schema Remarks:</b> The date when the product entered (or will enter) the assortment.</p>
     * 
     */
    @Column(name = "PRODUCT_START_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date productStartDt;
    /**
     * <p>Maps to table column: {@code PRODUCT_END_DT}.</p><p><b>Schema Remarks:</b> The date when the product will go out of the assortment.</p>
     * 
     */
    @Column(name = "PRODUCT_END_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date productEndDt;
    /**
     * <p>Maps to table column: {@code LAST_REPLENISHMENT_DT}.</p><p><b>Schema Remarks:</b> Last date for replenishment orders for this SKU. In some situations, this is a date earlier than the product end date.</p>
     * 
     */
    @Column(name = "LAST_REPLENISHMENT_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastReplenishmentDt;
    /**
     * <p>Maps to table column: {@code REPLICATE_PRICES_FL}.</p><p><b>Schema Remarks:</b> Flag that indicates if prices should be replicated</p>
     * 
     */
    @Size(max = 1, message = "REPLICATE_PRICES_FL should contain less than or equal to 1 characters")
    @Column(name = "REPLICATE_PRICES_FL")
    private String replicatePricesFl;
    /**
     * <p>Maps to table column: {@code LAST_PRICECHG_TIME}.</p><p><b>Schema Remarks:</b> Time of the most recent price change</p>
     * 
     */
    @Column(name = "LAST_PRICECHG_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPricechgTime;
    /**
     * <p>Maps to table column: {@code AVG_DLY_SALES}.</p><p><b>Schema Remarks:</b> Average daily sales for this item (requires a job to update value)</p>
     * 
     */
    @Digits(integer = 5, fraction = 4, message = "AVG_DLY_SALES should have an integer component no longer than 5 and an a fraction component of exact size 4")
    @Column(name = "AVG_DLY_SALES")
    private BigDecimal avgDlySales;

    /**
     * <P>Empty constructor used to create new InvbysitFullEntity.</P>
     * 
     */
    public InvbysitFullEntity() {
        this.identifier = new InvbysitEntityId();
        	
        _init();
    }

    /**
     * Constructor used to create a new InvbysitFullEntity where the identifier will be created by copying the fields of the provided sourceId.
     * 
     */
    public InvbysitFullEntity(InvbysitEntityId sourceId) {
        this.identifier = new InvbysitEntityId(sourceId.getSkuNo(), sourceId.getSiteNo());
        	
        _init();
    }

    /**
     * Constructor used to create a InvbysitFullEntity where the identifier will be created internally using the id arguments provided.  This is a convenient constructor used instead of instantiating a new identifier.
     * 
     */
    public InvbysitFullEntity(Integer skuNo, Integer siteNo) {
        this.identifier = new InvbysitEntityId(skuNo, siteNo);
        	
        _init();
    }

    /**
     * Constructor used to create a InvbysitFullEntity where contents are provided by an object array typically returned by a native query
     * 
     */
    public InvbysitFullEntity(Object[] objectArray) {
        this.identifier = new InvbysitEntityId(((objectArray[ 1 ] == null)?null:((BigDecimal) objectArray[ 1 ]).intValue()), ((objectArray[ 2 ] == null)?null:((BigDecimal) objectArray[ 2 ]).intValue()));
        	
        this.promoKeyNo = ((objectArray[ 0 ] == null)?null:((BigDecimal) objectArray[ 0 ]).longValue());
        this.location1 = ((objectArray[ 3 ] == null)?null:((String) objectArray[ 3 ]));
        this.location2 = ((objectArray[ 4 ] == null)?null:((String) objectArray[ 4 ]));
        this.minLevel = ((objectArray[ 5 ] == null)?null:((BigDecimal) objectArray[ 5 ]));
        this.maxLevel = ((objectArray[ 6 ] == null)?null:((BigDecimal) objectArray[ 6 ]));
        this.reorderPt = ((objectArray[ 7 ] == null)?null:((BigDecimal) objectArray[ 7 ]));
        this.reorderTo = ((objectArray[ 8 ] == null)?null:((BigDecimal) objectArray[ 8 ]));
        this.priceType = ((objectArray[ 9 ] == null)?null:((String) objectArray[ 9 ]));
        this.unitPrice1 = ((objectArray[ 10 ] == null)?null:((BigDecimal) objectArray[ 10 ]));
        this.unitPrice2 = ((objectArray[ 11 ] == null)?null:((BigDecimal) objectArray[ 11 ]));
        this.unitPrice3 = ((objectArray[ 12 ] == null)?null:((BigDecimal) objectArray[ 12 ]));
        this.unitPrice4 = ((objectArray[ 13 ] == null)?null:((BigDecimal) objectArray[ 13 ]));
        this.unitPrice5 = ((objectArray[ 14 ] == null)?null:((BigDecimal) objectArray[ 14 ]));
        this.unitPrice6 = ((objectArray[ 15 ] == null)?null:((BigDecimal) objectArray[ 15 ]));
        this.unitPrice7 = ((objectArray[ 16 ] == null)?null:((BigDecimal) objectArray[ 16 ]));
        this.unitPrice8 = ((objectArray[ 17 ] == null)?null:((BigDecimal) objectArray[ 17 ]));
        this.unitPrice9 = ((objectArray[ 18 ] == null)?null:((BigDecimal) objectArray[ 18 ]));
        this.unitPrice10 = ((objectArray[ 19 ] == null)?null:((BigDecimal) objectArray[ 19 ]));
        this.priceQty1 = ((objectArray[ 20 ] == null)?null:((BigDecimal) objectArray[ 20 ]).intValue());
        this.priceQty2 = ((objectArray[ 21 ] == null)?null:((BigDecimal) objectArray[ 21 ]).intValue());
        this.priceQty3 = ((objectArray[ 22 ] == null)?null:((BigDecimal) objectArray[ 22 ]).intValue());
        this.priceQty4 = ((objectArray[ 23 ] == null)?null:((BigDecimal) objectArray[ 23 ]).intValue());
        this.priceQty5 = ((objectArray[ 24 ] == null)?null:((BigDecimal) objectArray[ 24 ]).intValue());
        this.priceQty6 = ((objectArray[ 25 ] == null)?null:((BigDecimal) objectArray[ 25 ]).intValue());
        this.priceQty7 = ((objectArray[ 26 ] == null)?null:((BigDecimal) objectArray[ 26 ]).intValue());
        this.priceQty8 = ((objectArray[ 27 ] == null)?null:((BigDecimal) objectArray[ 27 ]).intValue());
        this.priceQty9 = ((objectArray[ 28 ] == null)?null:((BigDecimal) objectArray[ 28 ]).intValue());
        this.priceQty10 = ((objectArray[ 29 ] == null)?null:((BigDecimal) objectArray[ 29 ]).intValue());
        this.price1 = ((objectArray[ 30 ] == null)?null:((BigDecimal) objectArray[ 30 ]));
        this.price2 = ((objectArray[ 31 ] == null)?null:((BigDecimal) objectArray[ 31 ]));
        this.price3 = ((objectArray[ 32 ] == null)?null:((BigDecimal) objectArray[ 32 ]));
        this.price4 = ((objectArray[ 33 ] == null)?null:((BigDecimal) objectArray[ 33 ]));
        this.price5 = ((objectArray[ 34 ] == null)?null:((BigDecimal) objectArray[ 34 ]));
        this.price6 = ((objectArray[ 35 ] == null)?null:((BigDecimal) objectArray[ 35 ]));
        this.price7 = ((objectArray[ 36 ] == null)?null:((BigDecimal) objectArray[ 36 ]));
        this.price8 = ((objectArray[ 37 ] == null)?null:((BigDecimal) objectArray[ 37 ]));
        this.price9 = ((objectArray[ 38 ] == null)?null:((BigDecimal) objectArray[ 38 ]));
        this.price10 = ((objectArray[ 39 ] == null)?null:((BigDecimal) objectArray[ 39 ]));
        this.epoPct = ((objectArray[ 40 ] == null)?null:((BigDecimal) objectArray[ 40 ]));
        this.msrpUnitPrice = ((objectArray[ 41 ] == null)?null:((BigDecimal) objectArray[ 41 ]));
        this.msrpPrice = ((objectArray[ 42 ] == null)?null:((BigDecimal) objectArray[ 42 ]));
        this.msrpQty = ((objectArray[ 43 ] == null)?null:((BigDecimal) objectArray[ 43 ]).intValue());
        this.pkgPriceQty1 = ((objectArray[ 44 ] == null)?null:((BigDecimal) objectArray[ 44 ]).intValue());
        this.pkgPriceQty2 = ((objectArray[ 45 ] == null)?null:((BigDecimal) objectArray[ 45 ]).intValue());
        this.pkgPriceQty3 = ((objectArray[ 46 ] == null)?null:((BigDecimal) objectArray[ 46 ]).intValue());
        this.pkgPrice1 = ((objectArray[ 47 ] == null)?null:((BigDecimal) objectArray[ 47 ]));
        this.pkgPrice2 = ((objectArray[ 48 ] == null)?null:((BigDecimal) objectArray[ 48 ]));
        this.pkgPrice3 = ((objectArray[ 49 ] == null)?null:((BigDecimal) objectArray[ 49 ]));
        this.preMarkdownPrice = ((objectArray[ 50 ] == null)?null:((BigDecimal) objectArray[ 50 ]));
        this.noOfFacings = ((objectArray[ 51 ] == null)?null:((BigDecimal) objectArray[ 51 ]).intValue());
        this.shelfVelocity = ((objectArray[ 52 ] == null)?null:((BigDecimal) objectArray[ 52 ]).longValue());
        this.commissionCd = ((objectArray[ 53 ] == null)?null:((BigDecimal) objectArray[ 53 ]).intValue());
        this.invCost = ((objectArray[ 54 ] == null)?null:((BigDecimal) objectArray[ 54 ]));
        this.standardCost = ((objectArray[ 55 ] == null)?null:((BigDecimal) objectArray[ 55 ]));
        this.avgAge = ((objectArray[ 56 ] == null)?null:((BigDecimal) objectArray[ 56 ]));
        this.firstRcvdDt = ((objectArray[ 57 ] == null)?null:new Date(((Timestamp) objectArray[ 57 ]).getTime()));
        this.lastRcvdDt = ((objectArray[ 58 ] == null)?null:new Date(((Timestamp) objectArray[ 58 ]).getTime()));
        this.qtyLastRcvd = ((objectArray[ 59 ] == null)?null:((BigDecimal) objectArray[ 59 ]));
        this.lastOrdDt = ((objectArray[ 60 ] == null)?null:new Date(((Timestamp) objectArray[ 60 ]).getTime()));
        this.lastSoldDt = ((objectArray[ 61 ] == null)?null:new Date(((Timestamp) objectArray[ 61 ]).getTime()));
        this.lastSoldPreviousDt = ((objectArray[ 62 ] == null)?null:new Date(((Timestamp) objectArray[ 62 ]).getTime()));
        this.qtyOnHand = ((objectArray[ 63 ] == null)?null:((BigDecimal) objectArray[ 63 ]));
        this.qtyRsvd = ((objectArray[ 64 ] == null)?null:((BigDecimal) objectArray[ 64 ]));
        this.qtyOnOrd = ((objectArray[ 65 ] == null)?null:((BigDecimal) objectArray[ 65 ]));
        this.qtyOnBackord = ((objectArray[ 66 ] == null)?null:((BigDecimal) objectArray[ 66 ]));
        this.qtyInTransit = ((objectArray[ 67 ] == null)?null:((BigDecimal) objectArray[ 67 ]));
        this.qtyRtv = ((objectArray[ 68 ] == null)?null:((BigDecimal) objectArray[ 68 ]));
        this.expRcvdDt = ((objectArray[ 69 ] == null)?null:new Date(((Timestamp) objectArray[ 69 ]).getTime()));
        this.expBoDt = ((objectArray[ 70 ] == null)?null:new Date(((Timestamp) objectArray[ 70 ]).getTime()));
        this.qtyCounted = ((objectArray[ 71 ] == null)?null:((BigDecimal) objectArray[ 71 ]));
        this.maxOrderQty = ((objectArray[ 72 ] == null)?null:((BigDecimal) objectArray[ 72 ]));
        this.mixMatchNo = ((objectArray[ 73 ] == null)?null:((BigDecimal) objectArray[ 73 ]).intValue());
        this.bottleLinkNo = ((objectArray[ 74 ] == null)?null:((BigDecimal) objectArray[ 74 ]).intValue());
        this.lastPrice = ((objectArray[ 75 ] == null)?null:((BigDecimal) objectArray[ 75 ]));
        this.lastPchgDt = ((objectArray[ 76 ] == null)?null:new Date(((Timestamp) objectArray[ 76 ]).getTime()));
        this.downloadFl = ((objectArray[ 77 ] == null)?null:((String) objectArray[ 77 ]));
        this.allowPchgFl = ((objectArray[ 78 ] == null)?null:((String) objectArray[ 78 ]));
        this.cosPct = ((objectArray[ 79 ] == null)?null:((BigDecimal) objectArray[ 79 ]));
        this.supersededFl = ((objectArray[ 80 ] == null)?null:((String) objectArray[ 80 ]));
        this.priorReorderPt = ((objectArray[ 81 ] == null)?null:((BigDecimal) objectArray[ 81 ]));
        this.priorReorderTo = ((objectArray[ 82 ] == null)?null:((BigDecimal) objectArray[ 82 ]));
        this.warrantyInvCost = ((objectArray[ 83 ] == null)?null:((BigDecimal) objectArray[ 83 ]));
        this.warrantyQtyOnHand = ((objectArray[ 84 ] == null)?null:((BigDecimal) objectArray[ 84 ]).longValue());
        this.weightedLeadTime = ((objectArray[ 85 ] == null)?null:((BigDecimal) objectArray[ 85 ]));
        this.coreSellPrice = ((objectArray[ 86 ] == null)?null:((BigDecimal) objectArray[ 86 ]));
        this.moDemandForecast = ((objectArray[ 87 ] == null)?null:((BigDecimal) objectArray[ 87 ]));
        this.coreInvCost = ((objectArray[ 88 ] == null)?null:((BigDecimal) objectArray[ 88 ]));
        this.coreQtyOnHand = ((objectArray[ 89 ] == null)?null:((BigDecimal) objectArray[ 89 ]).longValue());
        this.skuStatusId = ((objectArray[ 90 ] == null)?null:((String) objectArray[ 90 ]));
        this.nonDiscountFl = ((objectArray[ 91 ] == null)?null:((String) objectArray[ 91 ]));
        this.restrictSaleId = ((objectArray[ 92 ] == null)?null:((String) objectArray[ 92 ]));
        this.labelCd = ((objectArray[ 93 ] == null)?null:((String) objectArray[ 93 ]));
        this.lastPriceIncDt = ((objectArray[ 94 ] == null)?null:new Date(((Timestamp) objectArray[ 94 ]).getTime()));
        this.lastPriceDecDt = ((objectArray[ 95 ] == null)?null:new Date(((Timestamp) objectArray[ 95 ]).getTime()));
        this.lastCycleDt = ((objectArray[ 96 ] == null)?null:new Date(((Timestamp) objectArray[ 96 ]).getTime()));
        this.lastPriceVerDt = ((objectArray[ 97 ] == null)?null:new Date(((Timestamp) objectArray[ 97 ]).getTime()));
        this.lastChangeDt = ((objectArray[ 98 ] == null)?null:new Date(((Timestamp) objectArray[ 98 ]).getTime()));
        this.newSkuStatusId = ((objectArray[ 99 ] == null)?null:((String) objectArray[ 99 ]));
        this.itemizerStatusId = ((objectArray[ 100 ] == null)?null:((String) objectArray[ 100 ]));
        this.scanDeptNo = ((objectArray[ 101 ] == null)?null:((String) objectArray[ 101 ]));
        this.scanSubdeptNo = ((objectArray[ 102 ] == null)?null:((String) objectArray[ 102 ]));
        this.downloadNToYFl = ((objectArray[ 103 ] == null)?null:((String) objectArray[ 103 ]));
        this.labelQty1 = ((objectArray[ 104 ] == null)?null:((BigDecimal) objectArray[ 104 ]).intValue());
        this.labelQty2 = ((objectArray[ 105 ] == null)?null:((BigDecimal) objectArray[ 105 ]).intValue());
        this.warehouseInnerPack = ((objectArray[ 106 ] == null)?null:((BigDecimal) objectArray[ 106 ]));
        this.lastXferDt = ((objectArray[ 107 ] == null)?null:new Date(((Timestamp) objectArray[ 107 ]).getTime()));
        this.priceOptionCd = ((objectArray[ 108 ] == null)?null:((BigDecimal) objectArray[ 108 ]).intValue());
        this.priceAttributeCd = ((objectArray[ 109 ] == null)?null:((BigDecimal) objectArray[ 109 ]).intValue());
        this.promoRecordNo = ((objectArray[ 110 ] == null)?null:((BigDecimal) objectArray[ 110 ]).intValue());
        this.lastVendorCost = ((objectArray[ 111 ] == null)?null:((BigDecimal) objectArray[ 111 ]));
        this.avgMovement = ((objectArray[ 112 ] == null)?null:((BigDecimal) objectArray[ 112 ]));
        this.haloGm = ((objectArray[ 113 ] == null)?null:((BigDecimal) objectArray[ 113 ]));
        this.laloGm = ((objectArray[ 114 ] == null)?null:((BigDecimal) objectArray[ 114 ]));
        this.replenishmentFl = ((objectArray[ 115 ] == null)?null:((String) objectArray[ 115 ]));
        this.velocityCd = ((objectArray[ 116 ] == null)?null:((String) objectArray[ 116 ]));
        this.velocityLockedFl = ((objectArray[ 117 ] == null)?null:((String) objectArray[ 117 ]));
        this.velocityChangeDt = ((objectArray[ 118 ] == null)?null:new Date(((Timestamp) objectArray[ 118 ]).getTime()));
        this.reorderLockedFl = ((objectArray[ 119 ] == null)?null:((String) objectArray[ 119 ]));
        this.reorderNowFl = ((objectArray[ 120 ] == null)?null:((String) objectArray[ 120 ]));
        this.profileData = ((objectArray[ 121 ] == null)?null:((String) objectArray[ 121 ]));
        this.replicationNo = ((objectArray[ 122 ] == null)?null:((BigDecimal) objectArray[ 122 ]).longValue());
        this.operationType = ((objectArray[ 123 ] == null)?null:((String) objectArray[ 123 ]));
        this.dateChanged = ((objectArray[ 124 ] == null)?null:new Date(((Timestamp) objectArray[ 124 ]).getTime()));
        this.registerReplicationNo = ((objectArray[ 125 ] == null)?null:((BigDecimal) objectArray[ 125 ]).longValue());
        this.reorderpointSourceCd = ((objectArray[ 126 ] == null)?null:((String) objectArray[ 126 ]));
        this.defaultDcSkuLevel = ((objectArray[ 127 ] == null)?null:((BigDecimal) objectArray[ 127 ]).intValue());
        this.priceCalcId = ((objectArray[ 128 ] == null)?null:((String) objectArray[ 128 ]));
        this.profileDataFl = ((objectArray[ 129 ] == null)?null:((String) objectArray[ 129 ]));
        this.discontinuedStFl = ((objectArray[ 130 ] == null)?null:((String) objectArray[ 130 ]));
        this.discontinuedDt = ((objectArray[ 131 ] == null)?null:new Date(((Timestamp) objectArray[ 131 ]).getTime()));
        this.discontinuedBy = ((objectArray[ 132 ] == null)?null:((String) objectArray[ 132 ]));
        this.tareTableNo = ((objectArray[ 133 ] == null)?null:((String) objectArray[ 133 ]));
        this.promoCommissionCd = ((objectArray[ 134 ] == null)?null:((BigDecimal) objectArray[ 134 ]).longValue());
        this.additionalTax = ((objectArray[ 135 ] == null)?null:((BigDecimal) objectArray[ 135 ]));
        this.negativeQohFl = ((objectArray[ 136 ] == null)?null:((String) objectArray[ 136 ]));
        this.posSkuStatusId = ((objectArray[ 137 ] == null)?null:((String) objectArray[ 137 ]));
        this.vendorItemSeqNo = ((objectArray[ 138 ] == null)?null:((BigDecimal) objectArray[ 138 ]).toBigInteger());
        this.replacementCostAmt = ((objectArray[ 139 ] == null)?null:((BigDecimal) objectArray[ 139 ]));
        this.firstPrice = ((objectArray[ 140 ] == null)?null:((BigDecimal) objectArray[ 140 ]));
        this.firstCost = ((objectArray[ 141 ] == null)?null:((BigDecimal) objectArray[ 141 ]));
        this.firstSoldDt = ((objectArray[ 142 ] == null)?null:new Date(((Timestamp) objectArray[ 142 ]).getTime()));
        this.stockingDt = ((objectArray[ 143 ] == null)?null:new Date(((Timestamp) objectArray[ 143 ]).getTime()));
        this.classCode = ((objectArray[ 144 ] == null)?null:((String) objectArray[ 144 ]));
        this.negativeQtySiteNo = ((objectArray[ 145 ] == null)?null:((BigDecimal) objectArray[ 145 ]).intValue());
        this.purchaseVolumeId = ((objectArray[ 146 ] == null)?null:((String) objectArray[ 146 ]));
        this.reorderSiteNo = ((objectArray[ 147 ] == null)?null:((BigDecimal) objectArray[ 147 ]).intValue());
        this.productStartDt = ((objectArray[ 148 ] == null)?null:new Date(((Timestamp) objectArray[ 148 ]).getTime()));
        this.productEndDt = ((objectArray[ 149 ] == null)?null:new Date(((Timestamp) objectArray[ 149 ]).getTime()));
        this.lastReplenishmentDt = ((objectArray[ 150 ] == null)?null:new Date(((Timestamp) objectArray[ 150 ]).getTime()));
        this.replicatePricesFl = ((objectArray[ 151 ] == null)?null:((String) objectArray[ 151 ]));
        this.lastPricechgTime = ((objectArray[ 152 ] == null)?null:new Date(((Timestamp) objectArray[ 152 ]).getTime()));
        this.avgDlySales = ((objectArray[ 153 ] == null)?null:((BigDecimal) objectArray[ 153 ]));
        	
        _init();
    }

    /**
     * <P>Copy constructor used to copy a InvbysitFullEntity where the identifier will be created by copying the fields of the provided sourceId.</P>
     * 
     */
    public InvbysitFullEntity(InvbysitFullEntity source, InvbysitEntityId sourceId) {
        this.identifier = new InvbysitEntityId(sourceId.getSkuNo(), sourceId.getSiteNo());
        	
        _init();
        	
        this.promoKeyNo = source.getPromoKeyNo();
        this.location1 = source.getLocation1();
        this.location2 = source.getLocation2();
        this.minLevel = source.getMinLevel();
        this.maxLevel = source.getMaxLevel();
        this.reorderPt = source.getReorderPt();
        this.reorderTo = source.getReorderTo();
        this.priceType = source.getPriceType();
        this.unitPrice1 = source.getUnitPrice1();
        this.unitPrice2 = source.getUnitPrice2();
        this.unitPrice3 = source.getUnitPrice3();
        this.unitPrice4 = source.getUnitPrice4();
        this.unitPrice5 = source.getUnitPrice5();
        this.unitPrice6 = source.getUnitPrice6();
        this.unitPrice7 = source.getUnitPrice7();
        this.unitPrice8 = source.getUnitPrice8();
        this.unitPrice9 = source.getUnitPrice9();
        this.unitPrice10 = source.getUnitPrice10();
        this.priceQty1 = source.getPriceQty1();
        this.priceQty2 = source.getPriceQty2();
        this.priceQty3 = source.getPriceQty3();
        this.priceQty4 = source.getPriceQty4();
        this.priceQty5 = source.getPriceQty5();
        this.priceQty6 = source.getPriceQty6();
        this.priceQty7 = source.getPriceQty7();
        this.priceQty8 = source.getPriceQty8();
        this.priceQty9 = source.getPriceQty9();
        this.priceQty10 = source.getPriceQty10();
        this.price1 = source.getPrice1();
        this.price2 = source.getPrice2();
        this.price3 = source.getPrice3();
        this.price4 = source.getPrice4();
        this.price5 = source.getPrice5();
        this.price6 = source.getPrice6();
        this.price7 = source.getPrice7();
        this.price8 = source.getPrice8();
        this.price9 = source.getPrice9();
        this.price10 = source.getPrice10();
        this.epoPct = source.getEpoPct();
        this.msrpUnitPrice = source.getMsrpUnitPrice();
        this.msrpPrice = source.getMsrpPrice();
        this.msrpQty = source.getMsrpQty();
        this.pkgPriceQty1 = source.getPkgPriceQty1();
        this.pkgPriceQty2 = source.getPkgPriceQty2();
        this.pkgPriceQty3 = source.getPkgPriceQty3();
        this.pkgPrice1 = source.getPkgPrice1();
        this.pkgPrice2 = source.getPkgPrice2();
        this.pkgPrice3 = source.getPkgPrice3();
        this.preMarkdownPrice = source.getPreMarkdownPrice();
        this.noOfFacings = source.getNoOfFacings();
        this.shelfVelocity = source.getShelfVelocity();
        this.commissionCd = source.getCommissionCd();
        this.invCost = source.getInvCost();
        this.standardCost = source.getStandardCost();
        this.avgAge = source.getAvgAge();
        this.firstRcvdDt = source.getFirstRcvdDt();
        this.lastRcvdDt = source.getLastRcvdDt();
        this.qtyLastRcvd = source.getQtyLastRcvd();
        this.lastOrdDt = source.getLastOrdDt();
        this.lastSoldDt = source.getLastSoldDt();
        this.lastSoldPreviousDt = source.getLastSoldPreviousDt();
        this.qtyOnHand = source.getQtyOnHand();
        this.qtyRsvd = source.getQtyRsvd();
        this.qtyOnOrd = source.getQtyOnOrd();
        this.qtyOnBackord = source.getQtyOnBackord();
        this.qtyInTransit = source.getQtyInTransit();
        this.qtyRtv = source.getQtyRtv();
        this.expRcvdDt = source.getExpRcvdDt();
        this.expBoDt = source.getExpBoDt();
        this.qtyCounted = source.getQtyCounted();
        this.maxOrderQty = source.getMaxOrderQty();
        this.mixMatchNo = source.getMixMatchNo();
        this.bottleLinkNo = source.getBottleLinkNo();
        this.lastPrice = source.getLastPrice();
        this.lastPchgDt = source.getLastPchgDt();
        this.downloadFl = source.getDownloadFl();
        this.allowPchgFl = source.getAllowPchgFl();
        this.cosPct = source.getCosPct();
        this.supersededFl = source.getSupersededFl();
        this.priorReorderPt = source.getPriorReorderPt();
        this.priorReorderTo = source.getPriorReorderTo();
        this.warrantyInvCost = source.getWarrantyInvCost();
        this.warrantyQtyOnHand = source.getWarrantyQtyOnHand();
        this.weightedLeadTime = source.getWeightedLeadTime();
        this.coreSellPrice = source.getCoreSellPrice();
        this.moDemandForecast = source.getMoDemandForecast();
        this.coreInvCost = source.getCoreInvCost();
        this.coreQtyOnHand = source.getCoreQtyOnHand();
        this.skuStatusId = source.getSkuStatusId();
        this.nonDiscountFl = source.getNonDiscountFl();
        this.restrictSaleId = source.getRestrictSaleId();
        this.labelCd = source.getLabelCd();
        this.lastPriceIncDt = source.getLastPriceIncDt();
        this.lastPriceDecDt = source.getLastPriceDecDt();
        this.lastCycleDt = source.getLastCycleDt();
        this.lastPriceVerDt = source.getLastPriceVerDt();
        this.lastChangeDt = source.getLastChangeDt();
        this.newSkuStatusId = source.getNewSkuStatusId();
        this.itemizerStatusId = source.getItemizerStatusId();
        this.scanDeptNo = source.getScanDeptNo();
        this.scanSubdeptNo = source.getScanSubdeptNo();
        this.downloadNToYFl = source.getDownloadNToYFl();
        this.labelQty1 = source.getLabelQty1();
        this.labelQty2 = source.getLabelQty2();
        this.warehouseInnerPack = source.getWarehouseInnerPack();
        this.lastXferDt = source.getLastXferDt();
        this.priceOptionCd = source.getPriceOptionCd();
        this.priceAttributeCd = source.getPriceAttributeCd();
        this.promoRecordNo = source.getPromoRecordNo();
        this.lastVendorCost = source.getLastVendorCost();
        this.avgMovement = source.getAvgMovement();
        this.haloGm = source.getHaloGm();
        this.laloGm = source.getLaloGm();
        this.replenishmentFl = source.getReplenishmentFl();
        this.velocityCd = source.getVelocityCd();
        this.velocityLockedFl = source.getVelocityLockedFl();
        this.velocityChangeDt = source.getVelocityChangeDt();
        this.reorderLockedFl = source.getReorderLockedFl();
        this.reorderNowFl = source.getReorderNowFl();
        this.profileData = source.getProfileData();
        this.replicationNo = source.getReplicationNo();
        this.operationType = source.getOperationType();
        this.dateChanged = source.getDateChanged();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.reorderpointSourceCd = source.getReorderpointSourceCd();
        this.defaultDcSkuLevel = source.getDefaultDcSkuLevel();
        this.priceCalcId = source.getPriceCalcId();
        this.profileDataFl = source.getProfileDataFl();
        this.discontinuedStFl = source.getDiscontinuedStFl();
        this.discontinuedDt = source.getDiscontinuedDt();
        this.discontinuedBy = source.getDiscontinuedBy();
        this.tareTableNo = source.getTareTableNo();
        this.promoCommissionCd = source.getPromoCommissionCd();
        this.additionalTax = source.getAdditionalTax();
        this.negativeQohFl = source.getNegativeQohFl();
        this.posSkuStatusId = source.getPosSkuStatusId();
        this.vendorItemSeqNo = source.getVendorItemSeqNo();
        this.replacementCostAmt = source.getReplacementCostAmt();
        this.firstPrice = source.getFirstPrice();
        this.firstCost = source.getFirstCost();
        this.firstSoldDt = source.getFirstSoldDt();
        this.stockingDt = source.getStockingDt();
        this.classCode = source.getClassCode();
        this.negativeQtySiteNo = source.getNegativeQtySiteNo();
        this.purchaseVolumeId = source.getPurchaseVolumeId();
        this.reorderSiteNo = source.getReorderSiteNo();
        this.productStartDt = source.getProductStartDt();
        this.productEndDt = source.getProductEndDt();
        this.lastReplenishmentDt = source.getLastReplenishmentDt();
        this.replicatePricesFl = source.getReplicatePricesFl();
        this.lastPricechgTime = source.getLastPricechgTime();
        this.avgDlySales = source.getAvgDlySales();
    }

    /**
     * <P>Copy constructor used to copy a InvbysitFullEntity including its identifier.</P>
     * 
     */
    public InvbysitFullEntity(InvbysitFullEntity source) {
        this.identifier = new InvbysitEntityId(source.getIdentifier().getSkuNo(), source.getIdentifier().getSiteNo());
        	
        _init();
        	
        this.promoKeyNo = source.getPromoKeyNo();
        this.location1 = source.getLocation1();
        this.location2 = source.getLocation2();
        this.minLevel = source.getMinLevel();
        this.maxLevel = source.getMaxLevel();
        this.reorderPt = source.getReorderPt();
        this.reorderTo = source.getReorderTo();
        this.priceType = source.getPriceType();
        this.unitPrice1 = source.getUnitPrice1();
        this.unitPrice2 = source.getUnitPrice2();
        this.unitPrice3 = source.getUnitPrice3();
        this.unitPrice4 = source.getUnitPrice4();
        this.unitPrice5 = source.getUnitPrice5();
        this.unitPrice6 = source.getUnitPrice6();
        this.unitPrice7 = source.getUnitPrice7();
        this.unitPrice8 = source.getUnitPrice8();
        this.unitPrice9 = source.getUnitPrice9();
        this.unitPrice10 = source.getUnitPrice10();
        this.priceQty1 = source.getPriceQty1();
        this.priceQty2 = source.getPriceQty2();
        this.priceQty3 = source.getPriceQty3();
        this.priceQty4 = source.getPriceQty4();
        this.priceQty5 = source.getPriceQty5();
        this.priceQty6 = source.getPriceQty6();
        this.priceQty7 = source.getPriceQty7();
        this.priceQty8 = source.getPriceQty8();
        this.priceQty9 = source.getPriceQty9();
        this.priceQty10 = source.getPriceQty10();
        this.price1 = source.getPrice1();
        this.price2 = source.getPrice2();
        this.price3 = source.getPrice3();
        this.price4 = source.getPrice4();
        this.price5 = source.getPrice5();
        this.price6 = source.getPrice6();
        this.price7 = source.getPrice7();
        this.price8 = source.getPrice8();
        this.price9 = source.getPrice9();
        this.price10 = source.getPrice10();
        this.epoPct = source.getEpoPct();
        this.msrpUnitPrice = source.getMsrpUnitPrice();
        this.msrpPrice = source.getMsrpPrice();
        this.msrpQty = source.getMsrpQty();
        this.pkgPriceQty1 = source.getPkgPriceQty1();
        this.pkgPriceQty2 = source.getPkgPriceQty2();
        this.pkgPriceQty3 = source.getPkgPriceQty3();
        this.pkgPrice1 = source.getPkgPrice1();
        this.pkgPrice2 = source.getPkgPrice2();
        this.pkgPrice3 = source.getPkgPrice3();
        this.preMarkdownPrice = source.getPreMarkdownPrice();
        this.noOfFacings = source.getNoOfFacings();
        this.shelfVelocity = source.getShelfVelocity();
        this.commissionCd = source.getCommissionCd();
        this.invCost = source.getInvCost();
        this.standardCost = source.getStandardCost();
        this.avgAge = source.getAvgAge();
        this.firstRcvdDt = source.getFirstRcvdDt();
        this.lastRcvdDt = source.getLastRcvdDt();
        this.qtyLastRcvd = source.getQtyLastRcvd();
        this.lastOrdDt = source.getLastOrdDt();
        this.lastSoldDt = source.getLastSoldDt();
        this.lastSoldPreviousDt = source.getLastSoldPreviousDt();
        this.qtyOnHand = source.getQtyOnHand();
        this.qtyRsvd = source.getQtyRsvd();
        this.qtyOnOrd = source.getQtyOnOrd();
        this.qtyOnBackord = source.getQtyOnBackord();
        this.qtyInTransit = source.getQtyInTransit();
        this.qtyRtv = source.getQtyRtv();
        this.expRcvdDt = source.getExpRcvdDt();
        this.expBoDt = source.getExpBoDt();
        this.qtyCounted = source.getQtyCounted();
        this.maxOrderQty = source.getMaxOrderQty();
        this.mixMatchNo = source.getMixMatchNo();
        this.bottleLinkNo = source.getBottleLinkNo();
        this.lastPrice = source.getLastPrice();
        this.lastPchgDt = source.getLastPchgDt();
        this.downloadFl = source.getDownloadFl();
        this.allowPchgFl = source.getAllowPchgFl();
        this.cosPct = source.getCosPct();
        this.supersededFl = source.getSupersededFl();
        this.priorReorderPt = source.getPriorReorderPt();
        this.priorReorderTo = source.getPriorReorderTo();
        this.warrantyInvCost = source.getWarrantyInvCost();
        this.warrantyQtyOnHand = source.getWarrantyQtyOnHand();
        this.weightedLeadTime = source.getWeightedLeadTime();
        this.coreSellPrice = source.getCoreSellPrice();
        this.moDemandForecast = source.getMoDemandForecast();
        this.coreInvCost = source.getCoreInvCost();
        this.coreQtyOnHand = source.getCoreQtyOnHand();
        this.skuStatusId = source.getSkuStatusId();
        this.nonDiscountFl = source.getNonDiscountFl();
        this.restrictSaleId = source.getRestrictSaleId();
        this.labelCd = source.getLabelCd();
        this.lastPriceIncDt = source.getLastPriceIncDt();
        this.lastPriceDecDt = source.getLastPriceDecDt();
        this.lastCycleDt = source.getLastCycleDt();
        this.lastPriceVerDt = source.getLastPriceVerDt();
        this.lastChangeDt = source.getLastChangeDt();
        this.newSkuStatusId = source.getNewSkuStatusId();
        this.itemizerStatusId = source.getItemizerStatusId();
        this.scanDeptNo = source.getScanDeptNo();
        this.scanSubdeptNo = source.getScanSubdeptNo();
        this.downloadNToYFl = source.getDownloadNToYFl();
        this.labelQty1 = source.getLabelQty1();
        this.labelQty2 = source.getLabelQty2();
        this.warehouseInnerPack = source.getWarehouseInnerPack();
        this.lastXferDt = source.getLastXferDt();
        this.priceOptionCd = source.getPriceOptionCd();
        this.priceAttributeCd = source.getPriceAttributeCd();
        this.promoRecordNo = source.getPromoRecordNo();
        this.lastVendorCost = source.getLastVendorCost();
        this.avgMovement = source.getAvgMovement();
        this.haloGm = source.getHaloGm();
        this.laloGm = source.getLaloGm();
        this.replenishmentFl = source.getReplenishmentFl();
        this.velocityCd = source.getVelocityCd();
        this.velocityLockedFl = source.getVelocityLockedFl();
        this.velocityChangeDt = source.getVelocityChangeDt();
        this.reorderLockedFl = source.getReorderLockedFl();
        this.reorderNowFl = source.getReorderNowFl();
        this.profileData = source.getProfileData();
        this.replicationNo = source.getReplicationNo();
        this.operationType = source.getOperationType();
        this.dateChanged = source.getDateChanged();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.reorderpointSourceCd = source.getReorderpointSourceCd();
        this.defaultDcSkuLevel = source.getDefaultDcSkuLevel();
        this.priceCalcId = source.getPriceCalcId();
        this.profileDataFl = source.getProfileDataFl();
        this.discontinuedStFl = source.getDiscontinuedStFl();
        this.discontinuedDt = source.getDiscontinuedDt();
        this.discontinuedBy = source.getDiscontinuedBy();
        this.tareTableNo = source.getTareTableNo();
        this.promoCommissionCd = source.getPromoCommissionCd();
        this.additionalTax = source.getAdditionalTax();
        this.negativeQohFl = source.getNegativeQohFl();
        this.posSkuStatusId = source.getPosSkuStatusId();
        this.vendorItemSeqNo = source.getVendorItemSeqNo();
        this.replacementCostAmt = source.getReplacementCostAmt();
        this.firstPrice = source.getFirstPrice();
        this.firstCost = source.getFirstCost();
        this.firstSoldDt = source.getFirstSoldDt();
        this.stockingDt = source.getStockingDt();
        this.classCode = source.getClassCode();
        this.negativeQtySiteNo = source.getNegativeQtySiteNo();
        this.purchaseVolumeId = source.getPurchaseVolumeId();
        this.reorderSiteNo = source.getReorderSiteNo();
        this.productStartDt = source.getProductStartDt();
        this.productEndDt = source.getProductEndDt();
        this.lastReplenishmentDt = source.getLastReplenishmentDt();
        this.replicatePricesFl = source.getReplicatePricesFl();
        this.lastPricechgTime = source.getLastPricechgTime();
        this.avgDlySales = source.getAvgDlySales();
    }

    /**
     * <P>Copy constructor used to copy a InvbysitEntity where the identifier will be created by copying the fields of the provided sourceId.</P>
     * 
     */
    public InvbysitFullEntity(InvbysitEntity source, InvbysitEntityId sourceId) {
        this.identifier = new InvbysitEntityId(sourceId.getSkuNo(), sourceId.getSiteNo());
        	
        _init();
        	
        this.promoKeyNo = source.getPromoKeyNo();
        this.location1 = source.getLocation1();
        this.location2 = source.getLocation2();
        this.minLevel = source.getMinLevel();
        this.maxLevel = source.getMaxLevel();
        this.reorderPt = source.getReorderPt();
        this.reorderTo = source.getReorderTo();
        this.priceType = source.getPriceType();
        this.unitPrice1 = source.getUnitPrice1();
        this.unitPrice2 = source.getUnitPrice2();
        this.unitPrice3 = source.getUnitPrice3();
        this.unitPrice4 = source.getUnitPrice4();
        this.unitPrice5 = source.getUnitPrice5();
        this.unitPrice6 = source.getUnitPrice6();
        this.unitPrice7 = source.getUnitPrice7();
        this.unitPrice8 = source.getUnitPrice8();
        this.unitPrice9 = source.getUnitPrice9();
        this.unitPrice10 = source.getUnitPrice10();
        this.priceQty1 = source.getPriceQty1();
        this.priceQty2 = source.getPriceQty2();
        this.priceQty3 = source.getPriceQty3();
        this.priceQty4 = source.getPriceQty4();
        this.priceQty5 = source.getPriceQty5();
        this.priceQty6 = source.getPriceQty6();
        this.priceQty7 = source.getPriceQty7();
        this.priceQty8 = source.getPriceQty8();
        this.priceQty9 = source.getPriceQty9();
        this.priceQty10 = source.getPriceQty10();
        this.price1 = source.getPrice1();
        this.price2 = source.getPrice2();
        this.price3 = source.getPrice3();
        this.price4 = source.getPrice4();
        this.price5 = source.getPrice5();
        this.price6 = source.getPrice6();
        this.price7 = source.getPrice7();
        this.price8 = source.getPrice8();
        this.price9 = source.getPrice9();
        this.price10 = source.getPrice10();
        this.epoPct = source.getEpoPct();
        this.msrpUnitPrice = source.getMsrpUnitPrice();
        this.msrpPrice = source.getMsrpPrice();
        this.msrpQty = source.getMsrpQty();
        this.pkgPriceQty1 = source.getPkgPriceQty1();
        this.pkgPriceQty2 = source.getPkgPriceQty2();
        this.pkgPriceQty3 = source.getPkgPriceQty3();
        this.pkgPrice1 = source.getPkgPrice1();
        this.pkgPrice2 = source.getPkgPrice2();
        this.pkgPrice3 = source.getPkgPrice3();
        this.preMarkdownPrice = source.getPreMarkdownPrice();
        this.noOfFacings = source.getNoOfFacings();
        this.shelfVelocity = source.getShelfVelocity();
        this.commissionCd = source.getCommissionCd();
        this.invCost = source.getInvCost();
        this.standardCost = source.getStandardCost();
        this.avgAge = source.getAvgAge();
        this.firstRcvdDt = source.getFirstRcvdDt();
        this.lastRcvdDt = source.getLastRcvdDt();
        this.qtyLastRcvd = source.getQtyLastRcvd();
        this.lastOrdDt = source.getLastOrdDt();
        this.lastSoldDt = source.getLastSoldDt();
        this.lastSoldPreviousDt = source.getLastSoldPreviousDt();
        this.qtyOnHand = source.getQtyOnHand();
        this.qtyRsvd = source.getQtyRsvd();
        this.qtyOnOrd = source.getQtyOnOrd();
        this.qtyOnBackord = source.getQtyOnBackord();
        this.qtyInTransit = source.getQtyInTransit();
        this.qtyRtv = source.getQtyRtv();
        this.expRcvdDt = source.getExpRcvdDt();
        this.expBoDt = source.getExpBoDt();
        this.qtyCounted = source.getQtyCounted();
        this.maxOrderQty = source.getMaxOrderQty();
        this.mixMatchNo = source.getMixMatchNo();
        this.bottleLinkNo = source.getBottleLinkNo();
        this.lastPrice = source.getLastPrice();
        this.lastPchgDt = source.getLastPchgDt();
        this.downloadFl = source.getDownloadFl();
        this.allowPchgFl = source.getAllowPchgFl();
        this.cosPct = source.getCosPct();
        this.supersededFl = source.getSupersededFl();
        this.priorReorderPt = source.getPriorReorderPt();
        this.priorReorderTo = source.getPriorReorderTo();
        this.warrantyInvCost = source.getWarrantyInvCost();
        this.warrantyQtyOnHand = source.getWarrantyQtyOnHand();
        this.weightedLeadTime = source.getWeightedLeadTime();
        this.coreSellPrice = source.getCoreSellPrice();
        this.moDemandForecast = source.getMoDemandForecast();
        this.coreInvCost = source.getCoreInvCost();
        this.coreQtyOnHand = source.getCoreQtyOnHand();
        this.skuStatusId = source.getSkuStatusId();
        this.nonDiscountFl = source.getNonDiscountFl();
        this.restrictSaleId = source.getRestrictSaleId();
        this.labelCd = source.getLabelCd();
        this.lastPriceIncDt = source.getLastPriceIncDt();
        this.lastPriceDecDt = source.getLastPriceDecDt();
        this.lastCycleDt = source.getLastCycleDt();
        this.lastPriceVerDt = source.getLastPriceVerDt();
        this.lastChangeDt = source.getLastChangeDt();
        this.newSkuStatusId = source.getNewSkuStatusId();
        this.itemizerStatusId = source.getItemizerStatusId();
        this.scanDeptNo = source.getScanDeptNo();
        this.scanSubdeptNo = source.getScanSubdeptNo();
        this.downloadNToYFl = source.getDownloadNToYFl();
        this.labelQty1 = source.getLabelQty1();
        this.labelQty2 = source.getLabelQty2();
        this.warehouseInnerPack = source.getWarehouseInnerPack();
        this.lastXferDt = source.getLastXferDt();
        this.priceOptionCd = source.getPriceOptionCd();
        this.priceAttributeCd = source.getPriceAttributeCd();
        this.promoRecordNo = source.getPromoRecordNo();
        this.lastVendorCost = source.getLastVendorCost();
        this.avgMovement = source.getAvgMovement();
        this.haloGm = source.getHaloGm();
        this.laloGm = source.getLaloGm();
        this.replenishmentFl = source.getReplenishmentFl();
        this.velocityCd = source.getVelocityCd();
        this.velocityLockedFl = source.getVelocityLockedFl();
        this.velocityChangeDt = source.getVelocityChangeDt();
        this.reorderLockedFl = source.getReorderLockedFl();
        this.reorderNowFl = source.getReorderNowFl();
        this.profileData = source.getProfileData();
        this.replicationNo = source.getReplicationNo();
        this.operationType = source.getOperationType();
        this.dateChanged = source.getDateChanged();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.reorderpointSourceCd = source.getReorderpointSourceCd();
        this.defaultDcSkuLevel = source.getDefaultDcSkuLevel();
        this.priceCalcId = source.getPriceCalcId();
        this.profileDataFl = source.getProfileDataFl();
        this.discontinuedStFl = source.getDiscontinuedStFl();
        this.discontinuedDt = source.getDiscontinuedDt();
        this.discontinuedBy = source.getDiscontinuedBy();
        this.tareTableNo = source.getTareTableNo();
        this.promoCommissionCd = source.getPromoCommissionCd();
        this.additionalTax = source.getAdditionalTax();
        this.negativeQohFl = source.getNegativeQohFl();
        this.posSkuStatusId = source.getPosSkuStatusId();
        this.vendorItemSeqNo = source.getVendorItemSeqNo();
        this.replacementCostAmt = source.getReplacementCostAmt();
        this.firstPrice = source.getFirstPrice();
        this.firstCost = source.getFirstCost();
        this.firstSoldDt = source.getFirstSoldDt();
        this.stockingDt = source.getStockingDt();
        this.classCode = source.getClassCode();
        this.negativeQtySiteNo = source.getNegativeQtySiteNo();
        this.purchaseVolumeId = source.getPurchaseVolumeId();
        this.reorderSiteNo = source.getReorderSiteNo();
        this.productStartDt = source.getProductStartDt();
        this.productEndDt = source.getProductEndDt();
        this.lastReplenishmentDt = source.getLastReplenishmentDt();
        this.replicatePricesFl = source.getReplicatePricesFl();
        this.lastPricechgTime = source.getLastPricechgTime();
        this.avgDlySales = source.getAvgDlySales();
    }

    /**
     * <P>Copy constructor used to copy a InvbysitEntity including its identifier.</P>
     * 
     */
    public InvbysitFullEntity(InvbysitEntity source) {
        this.identifier = new InvbysitEntityId(source.getIdentifier().getSkuNo(), source.getIdentifier().getSiteNo());
        	
        _init();
        	
        this.promoKeyNo = source.getPromoKeyNo();
        this.location1 = source.getLocation1();
        this.location2 = source.getLocation2();
        this.minLevel = source.getMinLevel();
        this.maxLevel = source.getMaxLevel();
        this.reorderPt = source.getReorderPt();
        this.reorderTo = source.getReorderTo();
        this.priceType = source.getPriceType();
        this.unitPrice1 = source.getUnitPrice1();
        this.unitPrice2 = source.getUnitPrice2();
        this.unitPrice3 = source.getUnitPrice3();
        this.unitPrice4 = source.getUnitPrice4();
        this.unitPrice5 = source.getUnitPrice5();
        this.unitPrice6 = source.getUnitPrice6();
        this.unitPrice7 = source.getUnitPrice7();
        this.unitPrice8 = source.getUnitPrice8();
        this.unitPrice9 = source.getUnitPrice9();
        this.unitPrice10 = source.getUnitPrice10();
        this.priceQty1 = source.getPriceQty1();
        this.priceQty2 = source.getPriceQty2();
        this.priceQty3 = source.getPriceQty3();
        this.priceQty4 = source.getPriceQty4();
        this.priceQty5 = source.getPriceQty5();
        this.priceQty6 = source.getPriceQty6();
        this.priceQty7 = source.getPriceQty7();
        this.priceQty8 = source.getPriceQty8();
        this.priceQty9 = source.getPriceQty9();
        this.priceQty10 = source.getPriceQty10();
        this.price1 = source.getPrice1();
        this.price2 = source.getPrice2();
        this.price3 = source.getPrice3();
        this.price4 = source.getPrice4();
        this.price5 = source.getPrice5();
        this.price6 = source.getPrice6();
        this.price7 = source.getPrice7();
        this.price8 = source.getPrice8();
        this.price9 = source.getPrice9();
        this.price10 = source.getPrice10();
        this.epoPct = source.getEpoPct();
        this.msrpUnitPrice = source.getMsrpUnitPrice();
        this.msrpPrice = source.getMsrpPrice();
        this.msrpQty = source.getMsrpQty();
        this.pkgPriceQty1 = source.getPkgPriceQty1();
        this.pkgPriceQty2 = source.getPkgPriceQty2();
        this.pkgPriceQty3 = source.getPkgPriceQty3();
        this.pkgPrice1 = source.getPkgPrice1();
        this.pkgPrice2 = source.getPkgPrice2();
        this.pkgPrice3 = source.getPkgPrice3();
        this.preMarkdownPrice = source.getPreMarkdownPrice();
        this.noOfFacings = source.getNoOfFacings();
        this.shelfVelocity = source.getShelfVelocity();
        this.commissionCd = source.getCommissionCd();
        this.invCost = source.getInvCost();
        this.standardCost = source.getStandardCost();
        this.avgAge = source.getAvgAge();
        this.firstRcvdDt = source.getFirstRcvdDt();
        this.lastRcvdDt = source.getLastRcvdDt();
        this.qtyLastRcvd = source.getQtyLastRcvd();
        this.lastOrdDt = source.getLastOrdDt();
        this.lastSoldDt = source.getLastSoldDt();
        this.lastSoldPreviousDt = source.getLastSoldPreviousDt();
        this.qtyOnHand = source.getQtyOnHand();
        this.qtyRsvd = source.getQtyRsvd();
        this.qtyOnOrd = source.getQtyOnOrd();
        this.qtyOnBackord = source.getQtyOnBackord();
        this.qtyInTransit = source.getQtyInTransit();
        this.qtyRtv = source.getQtyRtv();
        this.expRcvdDt = source.getExpRcvdDt();
        this.expBoDt = source.getExpBoDt();
        this.qtyCounted = source.getQtyCounted();
        this.maxOrderQty = source.getMaxOrderQty();
        this.mixMatchNo = source.getMixMatchNo();
        this.bottleLinkNo = source.getBottleLinkNo();
        this.lastPrice = source.getLastPrice();
        this.lastPchgDt = source.getLastPchgDt();
        this.downloadFl = source.getDownloadFl();
        this.allowPchgFl = source.getAllowPchgFl();
        this.cosPct = source.getCosPct();
        this.supersededFl = source.getSupersededFl();
        this.priorReorderPt = source.getPriorReorderPt();
        this.priorReorderTo = source.getPriorReorderTo();
        this.warrantyInvCost = source.getWarrantyInvCost();
        this.warrantyQtyOnHand = source.getWarrantyQtyOnHand();
        this.weightedLeadTime = source.getWeightedLeadTime();
        this.coreSellPrice = source.getCoreSellPrice();
        this.moDemandForecast = source.getMoDemandForecast();
        this.coreInvCost = source.getCoreInvCost();
        this.coreQtyOnHand = source.getCoreQtyOnHand();
        this.skuStatusId = source.getSkuStatusId();
        this.nonDiscountFl = source.getNonDiscountFl();
        this.restrictSaleId = source.getRestrictSaleId();
        this.labelCd = source.getLabelCd();
        this.lastPriceIncDt = source.getLastPriceIncDt();
        this.lastPriceDecDt = source.getLastPriceDecDt();
        this.lastCycleDt = source.getLastCycleDt();
        this.lastPriceVerDt = source.getLastPriceVerDt();
        this.lastChangeDt = source.getLastChangeDt();
        this.newSkuStatusId = source.getNewSkuStatusId();
        this.itemizerStatusId = source.getItemizerStatusId();
        this.scanDeptNo = source.getScanDeptNo();
        this.scanSubdeptNo = source.getScanSubdeptNo();
        this.downloadNToYFl = source.getDownloadNToYFl();
        this.labelQty1 = source.getLabelQty1();
        this.labelQty2 = source.getLabelQty2();
        this.warehouseInnerPack = source.getWarehouseInnerPack();
        this.lastXferDt = source.getLastXferDt();
        this.priceOptionCd = source.getPriceOptionCd();
        this.priceAttributeCd = source.getPriceAttributeCd();
        this.promoRecordNo = source.getPromoRecordNo();
        this.lastVendorCost = source.getLastVendorCost();
        this.avgMovement = source.getAvgMovement();
        this.haloGm = source.getHaloGm();
        this.laloGm = source.getLaloGm();
        this.replenishmentFl = source.getReplenishmentFl();
        this.velocityCd = source.getVelocityCd();
        this.velocityLockedFl = source.getVelocityLockedFl();
        this.velocityChangeDt = source.getVelocityChangeDt();
        this.reorderLockedFl = source.getReorderLockedFl();
        this.reorderNowFl = source.getReorderNowFl();
        this.profileData = source.getProfileData();
        this.replicationNo = source.getReplicationNo();
        this.operationType = source.getOperationType();
        this.dateChanged = source.getDateChanged();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.reorderpointSourceCd = source.getReorderpointSourceCd();
        this.defaultDcSkuLevel = source.getDefaultDcSkuLevel();
        this.priceCalcId = source.getPriceCalcId();
        this.profileDataFl = source.getProfileDataFl();
        this.discontinuedStFl = source.getDiscontinuedStFl();
        this.discontinuedDt = source.getDiscontinuedDt();
        this.discontinuedBy = source.getDiscontinuedBy();
        this.tareTableNo = source.getTareTableNo();
        this.promoCommissionCd = source.getPromoCommissionCd();
        this.additionalTax = source.getAdditionalTax();
        this.negativeQohFl = source.getNegativeQohFl();
        this.posSkuStatusId = source.getPosSkuStatusId();
        this.vendorItemSeqNo = source.getVendorItemSeqNo();
        this.replacementCostAmt = source.getReplacementCostAmt();
        this.firstPrice = source.getFirstPrice();
        this.firstCost = source.getFirstCost();
        this.firstSoldDt = source.getFirstSoldDt();
        this.stockingDt = source.getStockingDt();
        this.classCode = source.getClassCode();
        this.negativeQtySiteNo = source.getNegativeQtySiteNo();
        this.purchaseVolumeId = source.getPurchaseVolumeId();
        this.reorderSiteNo = source.getReorderSiteNo();
        this.productStartDt = source.getProductStartDt();
        this.productEndDt = source.getProductEndDt();
        this.lastReplenishmentDt = source.getLastReplenishmentDt();
        this.replicatePricesFl = source.getReplicatePricesFl();
        this.lastPricechgTime = source.getLastPricechgTime();
        this.avgDlySales = source.getAvgDlySales();
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public InvbysitEntityId getIdentifier() {
        return this.identifier;
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public void setIdentifier(InvbysitEntityId identifier) {
        this.identifier = identifier;
    }

    /**
     * Getter for {@link #invtory invtory}
     * 
     */
    @Parent
    public InvtoryFullEntity getInvtory() {
        return invtory;
    }

    /**
     * Setter for {@link #invtory invtory}
     * 
     */
    public void setInvtory(InvtoryFullEntity invtory) {
        this.invtory = invtory;
    }

    /**
     * Getter for {@link #promoKeyNo promoKeyNo}
     * 
     */
    public Long getPromoKeyNo() {
        return this.promoKeyNo;
    }

    /**
     * Setter for {@link #promoKeyNo promoKeyNo}
     * 
     */
    public Long setPromoKeyNo(Long promoKeyNo) {
        this.promoKeyNo = promoKeyNo;
        return this.promoKeyNo;
    }

    /**
     * Getter for {@link InvbysitEntityId#skuNo identifier.skuNo}
     * 
     */
    @Access(AccessType.PROPERTY)
    @Column(name = "SKU_NO", insertable = false, updatable = false)
    public Integer getSkuNo() {
        return this.identifier.getSkuNo();
    }

    /**
     * Setter for {@link InvbysitEntityId#skuNo identifier.skuNo}
     * 
     */
    public Integer setSkuNo(Integer skuNo) {
        this.identifier.setSkuNo(skuNo);
        return this.identifier.getSkuNo();
    }

    /**
     * Getter for {@link InvbysitEntityId#siteNo identifier.siteNo}
     * 
     */
    @Access(AccessType.PROPERTY)
    @Column(name = "SITE_NO", insertable = false, updatable = false)
    public Integer getSiteNo() {
        return this.identifier.getSiteNo();
    }

    /**
     * Setter for {@link InvbysitEntityId#siteNo identifier.siteNo}
     * 
     */
    public Integer setSiteNo(Integer siteNo) {
        this.identifier.setSiteNo(siteNo);
        return this.identifier.getSiteNo();
    }

    /**
     * Getter for {@link #location1 location1}
     * 
     */
    public String getLocation1() {
        return this.location1;
    }

    /**
     * Setter for {@link #location1 location1}
     * 
     */
    public String setLocation1(String location1) {
        this.location1 = location1;
        return this.location1;
    }

    /**
     * Getter for {@link #location2 location2}
     * 
     */
    public String getLocation2() {
        return this.location2;
    }

    /**
     * Setter for {@link #location2 location2}
     * 
     */
    public String setLocation2(String location2) {
        this.location2 = location2;
        return this.location2;
    }

    /**
     * Getter for {@link #minLevel minLevel}
     * 
     */
    public BigDecimal getMinLevel() {
        return this.minLevel;
    }

    /**
     * Setter for {@link #minLevel minLevel}
     * 
     */
    public BigDecimal setMinLevel(BigDecimal minLevel) {
        if ((minLevel!= null)&&(minLevel.scale()!= 2)) {
            minLevel = minLevel.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.minLevel = minLevel;
        return this.minLevel;
    }

    /**
     * Getter for {@link #maxLevel maxLevel}
     * 
     */
    public BigDecimal getMaxLevel() {
        return this.maxLevel;
    }

    /**
     * Setter for {@link #maxLevel maxLevel}
     * 
     */
    public BigDecimal setMaxLevel(BigDecimal maxLevel) {
        if ((maxLevel!= null)&&(maxLevel.scale()!= 2)) {
            maxLevel = maxLevel.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.maxLevel = maxLevel;
        return this.maxLevel;
    }

    /**
     * Getter for {@link #reorderPt reorderPt}
     * 
     */
    public BigDecimal getReorderPt() {
        return this.reorderPt;
    }

    /**
     * Setter for {@link #reorderPt reorderPt}
     * 
     */
    public BigDecimal setReorderPt(BigDecimal reorderPt) {
        if ((reorderPt!= null)&&(reorderPt.scale()!= 2)) {
            reorderPt = reorderPt.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.reorderPt = reorderPt;
        return this.reorderPt;
    }

    /**
     * Getter for {@link #reorderTo reorderTo}
     * 
     */
    public BigDecimal getReorderTo() {
        return this.reorderTo;
    }

    /**
     * Setter for {@link #reorderTo reorderTo}
     * 
     */
    public BigDecimal setReorderTo(BigDecimal reorderTo) {
        if ((reorderTo!= null)&&(reorderTo.scale()!= 2)) {
            reorderTo = reorderTo.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.reorderTo = reorderTo;
        return this.reorderTo;
    }

    /**
     * Getter for {@link #priceType priceType}
     * 
     */
    public String getPriceType() {
        return this.priceType;
    }

    /**
     * Setter for {@link #priceType priceType}
     * 
     */
    public String setPriceType(String priceType) {
        this.priceType = priceType;
        return this.priceType;
    }

    /**
     * Getter for {@link #unitPrice1 unitPrice1}
     * 
     */
    public BigDecimal getUnitPrice1() {
        return this.unitPrice1;
    }

    /**
     * Setter for {@link #unitPrice1 unitPrice1}
     * 
     */
    public BigDecimal setUnitPrice1(BigDecimal unitPrice1) {
        if ((unitPrice1 != null)&&(unitPrice1 .scale()!= 2)) {
            unitPrice1 = unitPrice1 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.unitPrice1 = unitPrice1;
        return this.unitPrice1;
    }

    /**
     * Getter for {@link #unitPrice2 unitPrice2}
     * 
     */
    public BigDecimal getUnitPrice2() {
        return this.unitPrice2;
    }

    /**
     * Setter for {@link #unitPrice2 unitPrice2}
     * 
     */
    public BigDecimal setUnitPrice2(BigDecimal unitPrice2) {
        if ((unitPrice2 != null)&&(unitPrice2 .scale()!= 2)) {
            unitPrice2 = unitPrice2 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.unitPrice2 = unitPrice2;
        return this.unitPrice2;
    }

    /**
     * Getter for {@link #unitPrice3 unitPrice3}
     * 
     */
    public BigDecimal getUnitPrice3() {
        return this.unitPrice3;
    }

    /**
     * Setter for {@link #unitPrice3 unitPrice3}
     * 
     */
    public BigDecimal setUnitPrice3(BigDecimal unitPrice3) {
        if ((unitPrice3 != null)&&(unitPrice3 .scale()!= 2)) {
            unitPrice3 = unitPrice3 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.unitPrice3 = unitPrice3;
        return this.unitPrice3;
    }

    /**
     * Getter for {@link #unitPrice4 unitPrice4}
     * 
     */
    public BigDecimal getUnitPrice4() {
        return this.unitPrice4;
    }

    /**
     * Setter for {@link #unitPrice4 unitPrice4}
     * 
     */
    public BigDecimal setUnitPrice4(BigDecimal unitPrice4) {
        if ((unitPrice4 != null)&&(unitPrice4 .scale()!= 2)) {
            unitPrice4 = unitPrice4 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.unitPrice4 = unitPrice4;
        return this.unitPrice4;
    }

    /**
     * Getter for {@link #unitPrice5 unitPrice5}
     * 
     */
    public BigDecimal getUnitPrice5() {
        return this.unitPrice5;
    }

    /**
     * Setter for {@link #unitPrice5 unitPrice5}
     * 
     */
    public BigDecimal setUnitPrice5(BigDecimal unitPrice5) {
        if ((unitPrice5 != null)&&(unitPrice5 .scale()!= 2)) {
            unitPrice5 = unitPrice5 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.unitPrice5 = unitPrice5;
        return this.unitPrice5;
    }

    /**
     * Getter for {@link #unitPrice6 unitPrice6}
     * 
     */
    public BigDecimal getUnitPrice6() {
        return this.unitPrice6;
    }

    /**
     * Setter for {@link #unitPrice6 unitPrice6}
     * 
     */
    public BigDecimal setUnitPrice6(BigDecimal unitPrice6) {
        if ((unitPrice6 != null)&&(unitPrice6 .scale()!= 2)) {
            unitPrice6 = unitPrice6 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.unitPrice6 = unitPrice6;
        return this.unitPrice6;
    }

    /**
     * Getter for {@link #unitPrice7 unitPrice7}
     * 
     */
    public BigDecimal getUnitPrice7() {
        return this.unitPrice7;
    }

    /**
     * Setter for {@link #unitPrice7 unitPrice7}
     * 
     */
    public BigDecimal setUnitPrice7(BigDecimal unitPrice7) {
        if ((unitPrice7 != null)&&(unitPrice7 .scale()!= 2)) {
            unitPrice7 = unitPrice7 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.unitPrice7 = unitPrice7;
        return this.unitPrice7;
    }

    /**
     * Getter for {@link #unitPrice8 unitPrice8}
     * 
     */
    public BigDecimal getUnitPrice8() {
        return this.unitPrice8;
    }

    /**
     * Setter for {@link #unitPrice8 unitPrice8}
     * 
     */
    public BigDecimal setUnitPrice8(BigDecimal unitPrice8) {
        if ((unitPrice8 != null)&&(unitPrice8 .scale()!= 2)) {
            unitPrice8 = unitPrice8 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.unitPrice8 = unitPrice8;
        return this.unitPrice8;
    }

    /**
     * Getter for {@link #unitPrice9 unitPrice9}
     * 
     */
    public BigDecimal getUnitPrice9() {
        return this.unitPrice9;
    }

    /**
     * Setter for {@link #unitPrice9 unitPrice9}
     * 
     */
    public BigDecimal setUnitPrice9(BigDecimal unitPrice9) {
        if ((unitPrice9 != null)&&(unitPrice9 .scale()!= 2)) {
            unitPrice9 = unitPrice9 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.unitPrice9 = unitPrice9;
        return this.unitPrice9;
    }

    /**
     * Getter for {@link #unitPrice10 unitPrice10}
     * 
     */
    public BigDecimal getUnitPrice10() {
        return this.unitPrice10;
    }

    /**
     * Setter for {@link #unitPrice10 unitPrice10}
     * 
     */
    public BigDecimal setUnitPrice10(BigDecimal unitPrice10) {
        if ((unitPrice10 != null)&&(unitPrice10 .scale()!= 2)) {
            unitPrice10 = unitPrice10 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.unitPrice10 = unitPrice10;
        return this.unitPrice10;
    }

    /**
     * Getter for {@link #priceQty1 priceQty1}
     * 
     */
    public Integer getPriceQty1() {
        return this.priceQty1;
    }

    /**
     * Setter for {@link #priceQty1 priceQty1}
     * 
     */
    public Integer setPriceQty1(Integer priceQty1) {
        this.priceQty1 = priceQty1;
        return this.priceQty1;
    }

    /**
     * Getter for {@link #priceQty2 priceQty2}
     * 
     */
    public Integer getPriceQty2() {
        return this.priceQty2;
    }

    /**
     * Setter for {@link #priceQty2 priceQty2}
     * 
     */
    public Integer setPriceQty2(Integer priceQty2) {
        this.priceQty2 = priceQty2;
        return this.priceQty2;
    }

    /**
     * Getter for {@link #priceQty3 priceQty3}
     * 
     */
    public Integer getPriceQty3() {
        return this.priceQty3;
    }

    /**
     * Setter for {@link #priceQty3 priceQty3}
     * 
     */
    public Integer setPriceQty3(Integer priceQty3) {
        this.priceQty3 = priceQty3;
        return this.priceQty3;
    }

    /**
     * Getter for {@link #priceQty4 priceQty4}
     * 
     */
    public Integer getPriceQty4() {
        return this.priceQty4;
    }

    /**
     * Setter for {@link #priceQty4 priceQty4}
     * 
     */
    public Integer setPriceQty4(Integer priceQty4) {
        this.priceQty4 = priceQty4;
        return this.priceQty4;
    }

    /**
     * Getter for {@link #priceQty5 priceQty5}
     * 
     */
    public Integer getPriceQty5() {
        return this.priceQty5;
    }

    /**
     * Setter for {@link #priceQty5 priceQty5}
     * 
     */
    public Integer setPriceQty5(Integer priceQty5) {
        this.priceQty5 = priceQty5;
        return this.priceQty5;
    }

    /**
     * Getter for {@link #priceQty6 priceQty6}
     * 
     */
    public Integer getPriceQty6() {
        return this.priceQty6;
    }

    /**
     * Setter for {@link #priceQty6 priceQty6}
     * 
     */
    public Integer setPriceQty6(Integer priceQty6) {
        this.priceQty6 = priceQty6;
        return this.priceQty6;
    }

    /**
     * Getter for {@link #priceQty7 priceQty7}
     * 
     */
    public Integer getPriceQty7() {
        return this.priceQty7;
    }

    /**
     * Setter for {@link #priceQty7 priceQty7}
     * 
     */
    public Integer setPriceQty7(Integer priceQty7) {
        this.priceQty7 = priceQty7;
        return this.priceQty7;
    }

    /**
     * Getter for {@link #priceQty8 priceQty8}
     * 
     */
    public Integer getPriceQty8() {
        return this.priceQty8;
    }

    /**
     * Setter for {@link #priceQty8 priceQty8}
     * 
     */
    public Integer setPriceQty8(Integer priceQty8) {
        this.priceQty8 = priceQty8;
        return this.priceQty8;
    }

    /**
     * Getter for {@link #priceQty9 priceQty9}
     * 
     */
    public Integer getPriceQty9() {
        return this.priceQty9;
    }

    /**
     * Setter for {@link #priceQty9 priceQty9}
     * 
     */
    public Integer setPriceQty9(Integer priceQty9) {
        this.priceQty9 = priceQty9;
        return this.priceQty9;
    }

    /**
     * Getter for {@link #priceQty10 priceQty10}
     * 
     */
    public Integer getPriceQty10() {
        return this.priceQty10;
    }

    /**
     * Setter for {@link #priceQty10 priceQty10}
     * 
     */
    public Integer setPriceQty10(Integer priceQty10) {
        this.priceQty10 = priceQty10;
        return this.priceQty10;
    }

    /**
     * Getter for {@link #price1 price1}
     * 
     */
    public BigDecimal getPrice1() {
        return this.price1;
    }

    /**
     * Setter for {@link #price1 price1}
     * 
     */
    public BigDecimal setPrice1(BigDecimal price1) {
        if ((price1 != null)&&(price1 .scale()!= 2)) {
            price1 = price1 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.price1 = price1;
        return this.price1;
    }

    /**
     * Getter for {@link #price2 price2}
     * 
     */
    public BigDecimal getPrice2() {
        return this.price2;
    }

    /**
     * Setter for {@link #price2 price2}
     * 
     */
    public BigDecimal setPrice2(BigDecimal price2) {
        if ((price2 != null)&&(price2 .scale()!= 2)) {
            price2 = price2 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.price2 = price2;
        return this.price2;
    }

    /**
     * Getter for {@link #price3 price3}
     * 
     */
    public BigDecimal getPrice3() {
        return this.price3;
    }

    /**
     * Setter for {@link #price3 price3}
     * 
     */
    public BigDecimal setPrice3(BigDecimal price3) {
        if ((price3 != null)&&(price3 .scale()!= 2)) {
            price3 = price3 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.price3 = price3;
        return this.price3;
    }

    /**
     * Getter for {@link #price4 price4}
     * 
     */
    public BigDecimal getPrice4() {
        return this.price4;
    }

    /**
     * Setter for {@link #price4 price4}
     * 
     */
    public BigDecimal setPrice4(BigDecimal price4) {
        if ((price4 != null)&&(price4 .scale()!= 2)) {
            price4 = price4 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.price4 = price4;
        return this.price4;
    }

    /**
     * Getter for {@link #price5 price5}
     * 
     */
    public BigDecimal getPrice5() {
        return this.price5;
    }

    /**
     * Setter for {@link #price5 price5}
     * 
     */
    public BigDecimal setPrice5(BigDecimal price5) {
        if ((price5 != null)&&(price5 .scale()!= 2)) {
            price5 = price5 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.price5 = price5;
        return this.price5;
    }

    /**
     * Getter for {@link #price6 price6}
     * 
     */
    public BigDecimal getPrice6() {
        return this.price6;
    }

    /**
     * Setter for {@link #price6 price6}
     * 
     */
    public BigDecimal setPrice6(BigDecimal price6) {
        if ((price6 != null)&&(price6 .scale()!= 2)) {
            price6 = price6 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.price6 = price6;
        return this.price6;
    }

    /**
     * Getter for {@link #price7 price7}
     * 
     */
    public BigDecimal getPrice7() {
        return this.price7;
    }

    /**
     * Setter for {@link #price7 price7}
     * 
     */
    public BigDecimal setPrice7(BigDecimal price7) {
        if ((price7 != null)&&(price7 .scale()!= 2)) {
            price7 = price7 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.price7 = price7;
        return this.price7;
    }

    /**
     * Getter for {@link #price8 price8}
     * 
     */
    public BigDecimal getPrice8() {
        return this.price8;
    }

    /**
     * Setter for {@link #price8 price8}
     * 
     */
    public BigDecimal setPrice8(BigDecimal price8) {
        if ((price8 != null)&&(price8 .scale()!= 2)) {
            price8 = price8 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.price8 = price8;
        return this.price8;
    }

    /**
     * Getter for {@link #price9 price9}
     * 
     */
    public BigDecimal getPrice9() {
        return this.price9;
    }

    /**
     * Setter for {@link #price9 price9}
     * 
     */
    public BigDecimal setPrice9(BigDecimal price9) {
        if ((price9 != null)&&(price9 .scale()!= 2)) {
            price9 = price9 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.price9 = price9;
        return this.price9;
    }

    /**
     * Getter for {@link #price10 price10}
     * 
     */
    public BigDecimal getPrice10() {
        return this.price10;
    }

    /**
     * Setter for {@link #price10 price10}
     * 
     */
    public BigDecimal setPrice10(BigDecimal price10) {
        if ((price10 != null)&&(price10 .scale()!= 2)) {
            price10 = price10 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.price10 = price10;
        return this.price10;
    }

    /**
     * Getter for {@link #epoPct epoPct}
     * 
     */
    public BigDecimal getEpoPct() {
        return this.epoPct;
    }

    /**
     * Setter for {@link #epoPct epoPct}
     * 
     */
    public BigDecimal setEpoPct(BigDecimal epoPct) {
        if ((epoPct!= null)&&(epoPct.scale()!= 2)) {
            epoPct = epoPct.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.epoPct = epoPct;
        return this.epoPct;
    }

    /**
     * Getter for {@link #msrpUnitPrice msrpUnitPrice}
     * 
     */
    public BigDecimal getMsrpUnitPrice() {
        return this.msrpUnitPrice;
    }

    /**
     * Setter for {@link #msrpUnitPrice msrpUnitPrice}
     * 
     */
    public BigDecimal setMsrpUnitPrice(BigDecimal msrpUnitPrice) {
        if ((msrpUnitPrice!= null)&&(msrpUnitPrice.scale()!= 2)) {
            msrpUnitPrice = msrpUnitPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.msrpUnitPrice = msrpUnitPrice;
        return this.msrpUnitPrice;
    }

    /**
     * Getter for {@link #msrpPrice msrpPrice}
     * 
     */
    public BigDecimal getMsrpPrice() {
        return this.msrpPrice;
    }

    /**
     * Setter for {@link #msrpPrice msrpPrice}
     * 
     */
    public BigDecimal setMsrpPrice(BigDecimal msrpPrice) {
        if ((msrpPrice!= null)&&(msrpPrice.scale()!= 2)) {
            msrpPrice = msrpPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.msrpPrice = msrpPrice;
        return this.msrpPrice;
    }

    /**
     * Getter for {@link #msrpQty msrpQty}
     * 
     */
    public Integer getMsrpQty() {
        return this.msrpQty;
    }

    /**
     * Setter for {@link #msrpQty msrpQty}
     * 
     */
    public Integer setMsrpQty(Integer msrpQty) {
        this.msrpQty = msrpQty;
        return this.msrpQty;
    }

    /**
     * Getter for {@link #pkgPriceQty1 pkgPriceQty1}
     * 
     */
    public Integer getPkgPriceQty1() {
        return this.pkgPriceQty1;
    }

    /**
     * Setter for {@link #pkgPriceQty1 pkgPriceQty1}
     * 
     */
    public Integer setPkgPriceQty1(Integer pkgPriceQty1) {
        this.pkgPriceQty1 = pkgPriceQty1;
        return this.pkgPriceQty1;
    }

    /**
     * Getter for {@link #pkgPriceQty2 pkgPriceQty2}
     * 
     */
    public Integer getPkgPriceQty2() {
        return this.pkgPriceQty2;
    }

    /**
     * Setter for {@link #pkgPriceQty2 pkgPriceQty2}
     * 
     */
    public Integer setPkgPriceQty2(Integer pkgPriceQty2) {
        this.pkgPriceQty2 = pkgPriceQty2;
        return this.pkgPriceQty2;
    }

    /**
     * Getter for {@link #pkgPriceQty3 pkgPriceQty3}
     * 
     */
    public Integer getPkgPriceQty3() {
        return this.pkgPriceQty3;
    }

    /**
     * Setter for {@link #pkgPriceQty3 pkgPriceQty3}
     * 
     */
    public Integer setPkgPriceQty3(Integer pkgPriceQty3) {
        this.pkgPriceQty3 = pkgPriceQty3;
        return this.pkgPriceQty3;
    }

    /**
     * Getter for {@link #pkgPrice1 pkgPrice1}
     * 
     */
    public BigDecimal getPkgPrice1() {
        return this.pkgPrice1;
    }

    /**
     * Setter for {@link #pkgPrice1 pkgPrice1}
     * 
     */
    public BigDecimal setPkgPrice1(BigDecimal pkgPrice1) {
        if ((pkgPrice1 != null)&&(pkgPrice1 .scale()!= 2)) {
            pkgPrice1 = pkgPrice1 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.pkgPrice1 = pkgPrice1;
        return this.pkgPrice1;
    }

    /**
     * Getter for {@link #pkgPrice2 pkgPrice2}
     * 
     */
    public BigDecimal getPkgPrice2() {
        return this.pkgPrice2;
    }

    /**
     * Setter for {@link #pkgPrice2 pkgPrice2}
     * 
     */
    public BigDecimal setPkgPrice2(BigDecimal pkgPrice2) {
        if ((pkgPrice2 != null)&&(pkgPrice2 .scale()!= 2)) {
            pkgPrice2 = pkgPrice2 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.pkgPrice2 = pkgPrice2;
        return this.pkgPrice2;
    }

    /**
     * Getter for {@link #pkgPrice3 pkgPrice3}
     * 
     */
    public BigDecimal getPkgPrice3() {
        return this.pkgPrice3;
    }

    /**
     * Setter for {@link #pkgPrice3 pkgPrice3}
     * 
     */
    public BigDecimal setPkgPrice3(BigDecimal pkgPrice3) {
        if ((pkgPrice3 != null)&&(pkgPrice3 .scale()!= 2)) {
            pkgPrice3 = pkgPrice3 .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.pkgPrice3 = pkgPrice3;
        return this.pkgPrice3;
    }

    /**
     * Getter for {@link #preMarkdownPrice preMarkdownPrice}
     * 
     */
    public BigDecimal getPreMarkdownPrice() {
        return this.preMarkdownPrice;
    }

    /**
     * Setter for {@link #preMarkdownPrice preMarkdownPrice}
     * 
     */
    public BigDecimal setPreMarkdownPrice(BigDecimal preMarkdownPrice) {
        if ((preMarkdownPrice!= null)&&(preMarkdownPrice.scale()!= 2)) {
            preMarkdownPrice = preMarkdownPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.preMarkdownPrice = preMarkdownPrice;
        return this.preMarkdownPrice;
    }

    /**
     * Getter for {@link #noOfFacings noOfFacings}
     * 
     */
    public Integer getNoOfFacings() {
        return this.noOfFacings;
    }

    /**
     * Setter for {@link #noOfFacings noOfFacings}
     * 
     */
    public Integer setNoOfFacings(Integer noOfFacings) {
        this.noOfFacings = noOfFacings;
        return this.noOfFacings;
    }

    /**
     * Getter for {@link #shelfVelocity shelfVelocity}
     * 
     */
    public Long getShelfVelocity() {
        return this.shelfVelocity;
    }

    /**
     * Setter for {@link #shelfVelocity shelfVelocity}
     * 
     */
    public Long setShelfVelocity(Long shelfVelocity) {
        this.shelfVelocity = shelfVelocity;
        return this.shelfVelocity;
    }

    /**
     * Getter for {@link #commissionCd commissionCd}
     * 
     */
    public Integer getCommissionCd() {
        return this.commissionCd;
    }

    /**
     * Setter for {@link #commissionCd commissionCd}
     * 
     */
    public Integer setCommissionCd(Integer commissionCd) {
        this.commissionCd = commissionCd;
        return this.commissionCd;
    }

    /**
     * Getter for {@link #invCost invCost}
     * 
     */
    public BigDecimal getInvCost() {
        return this.invCost;
    }

    /**
     * Setter for {@link #invCost invCost}
     * 
     */
    public BigDecimal setInvCost(BigDecimal invCost) {
        if ((invCost!= null)&&(invCost.scale()!= 2)) {
            invCost = invCost.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.invCost = invCost;
        return this.invCost;
    }

    /**
     * Getter for {@link #standardCost standardCost}
     * 
     */
    public BigDecimal getStandardCost() {
        return this.standardCost;
    }

    /**
     * Setter for {@link #standardCost standardCost}
     * 
     */
    public BigDecimal setStandardCost(BigDecimal standardCost) {
        if ((standardCost!= null)&&(standardCost.scale()!= 4)) {
            standardCost = standardCost.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.standardCost = standardCost;
        return this.standardCost;
    }

    /**
     * Getter for {@link #avgAge avgAge}
     * 
     */
    public BigDecimal getAvgAge() {
        return this.avgAge;
    }

    /**
     * Setter for {@link #avgAge avgAge}
     * 
     */
    public BigDecimal setAvgAge(BigDecimal avgAge) {
        if ((avgAge!= null)&&(avgAge.scale()!= 1)) {
            avgAge = avgAge.setScale(1, BigDecimal.ROUND_HALF_UP);
        }
        this.avgAge = avgAge;
        return this.avgAge;
    }

    /**
     * Getter for {@link #firstRcvdDt firstRcvdDt}
     * 
     */
    public Date getFirstRcvdDt() {
        return this.firstRcvdDt;
    }

    /**
     * Setter for {@link #firstRcvdDt firstRcvdDt}
     * 
     */
    public Date setFirstRcvdDt(Date firstRcvdDt) {
        this.firstRcvdDt = firstRcvdDt;
        return this.firstRcvdDt;
    }

    /**
     * Getter for {@link #lastRcvdDt lastRcvdDt}
     * 
     */
    public Date getLastRcvdDt() {
        return this.lastRcvdDt;
    }

    /**
     * Setter for {@link #lastRcvdDt lastRcvdDt}
     * 
     */
    public Date setLastRcvdDt(Date lastRcvdDt) {
        this.lastRcvdDt = lastRcvdDt;
        return this.lastRcvdDt;
    }

    /**
     * Getter for {@link #qtyLastRcvd qtyLastRcvd}
     * 
     */
    public BigDecimal getQtyLastRcvd() {
        return this.qtyLastRcvd;
    }

    /**
     * Setter for {@link #qtyLastRcvd qtyLastRcvd}
     * 
     */
    public BigDecimal setQtyLastRcvd(BigDecimal qtyLastRcvd) {
        if ((qtyLastRcvd!= null)&&(qtyLastRcvd.scale()!= 2)) {
            qtyLastRcvd = qtyLastRcvd.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.qtyLastRcvd = qtyLastRcvd;
        return this.qtyLastRcvd;
    }

    /**
     * Getter for {@link #lastOrdDt lastOrdDt}
     * 
     */
    public Date getLastOrdDt() {
        return this.lastOrdDt;
    }

    /**
     * Setter for {@link #lastOrdDt lastOrdDt}
     * 
     */
    public Date setLastOrdDt(Date lastOrdDt) {
        this.lastOrdDt = lastOrdDt;
        return this.lastOrdDt;
    }

    /**
     * Getter for {@link #lastSoldDt lastSoldDt}
     * 
     */
    public Date getLastSoldDt() {
        return this.lastSoldDt;
    }

    /**
     * Setter for {@link #lastSoldDt lastSoldDt}
     * 
     */
    public Date setLastSoldDt(Date lastSoldDt) {
        this.lastSoldDt = lastSoldDt;
        return this.lastSoldDt;
    }

    /**
     * Getter for {@link #lastSoldPreviousDt lastSoldPreviousDt}
     * 
     */
    public Date getLastSoldPreviousDt() {
        return this.lastSoldPreviousDt;
    }

    /**
     * Setter for {@link #lastSoldPreviousDt lastSoldPreviousDt}
     * 
     */
    public Date setLastSoldPreviousDt(Date lastSoldPreviousDt) {
        this.lastSoldPreviousDt = lastSoldPreviousDt;
        return this.lastSoldPreviousDt;
    }

    /**
     * Getter for {@link #qtyOnHand qtyOnHand}
     * 
     */
    public BigDecimal getQtyOnHand() {
        return this.qtyOnHand;
    }

    /**
     * Setter for {@link #qtyOnHand qtyOnHand}
     * 
     */
    public BigDecimal setQtyOnHand(BigDecimal qtyOnHand) {
        if ((qtyOnHand!= null)&&(qtyOnHand.scale()!= 2)) {
            qtyOnHand = qtyOnHand.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.qtyOnHand = qtyOnHand;
        return this.qtyOnHand;
    }

    /**
     * Getter for {@link #qtyRsvd qtyRsvd}
     * 
     */
    public BigDecimal getQtyRsvd() {
        return this.qtyRsvd;
    }

    /**
     * Setter for {@link #qtyRsvd qtyRsvd}
     * 
     */
    public BigDecimal setQtyRsvd(BigDecimal qtyRsvd) {
        if ((qtyRsvd!= null)&&(qtyRsvd.scale()!= 2)) {
            qtyRsvd = qtyRsvd.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.qtyRsvd = qtyRsvd;
        return this.qtyRsvd;
    }

    /**
     * Getter for {@link #qtyOnOrd qtyOnOrd}
     * 
     */
    public BigDecimal getQtyOnOrd() {
        return this.qtyOnOrd;
    }

    /**
     * Setter for {@link #qtyOnOrd qtyOnOrd}
     * 
     */
    public BigDecimal setQtyOnOrd(BigDecimal qtyOnOrd) {
        if ((qtyOnOrd!= null)&&(qtyOnOrd.scale()!= 2)) {
            qtyOnOrd = qtyOnOrd.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.qtyOnOrd = qtyOnOrd;
        return this.qtyOnOrd;
    }

    /**
     * Getter for {@link #qtyOnBackord qtyOnBackord}
     * 
     */
    public BigDecimal getQtyOnBackord() {
        return this.qtyOnBackord;
    }

    /**
     * Setter for {@link #qtyOnBackord qtyOnBackord}
     * 
     */
    public BigDecimal setQtyOnBackord(BigDecimal qtyOnBackord) {
        if ((qtyOnBackord!= null)&&(qtyOnBackord.scale()!= 2)) {
            qtyOnBackord = qtyOnBackord.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.qtyOnBackord = qtyOnBackord;
        return this.qtyOnBackord;
    }

    /**
     * Getter for {@link #qtyInTransit qtyInTransit}
     * 
     */
    public BigDecimal getQtyInTransit() {
        return this.qtyInTransit;
    }

    /**
     * Setter for {@link #qtyInTransit qtyInTransit}
     * 
     */
    public BigDecimal setQtyInTransit(BigDecimal qtyInTransit) {
        if ((qtyInTransit!= null)&&(qtyInTransit.scale()!= 2)) {
            qtyInTransit = qtyInTransit.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.qtyInTransit = qtyInTransit;
        return this.qtyInTransit;
    }

    /**
     * Getter for {@link #qtyRtv qtyRtv}
     * 
     */
    public BigDecimal getQtyRtv() {
        return this.qtyRtv;
    }

    /**
     * Setter for {@link #qtyRtv qtyRtv}
     * 
     */
    public BigDecimal setQtyRtv(BigDecimal qtyRtv) {
        if ((qtyRtv!= null)&&(qtyRtv.scale()!= 2)) {
            qtyRtv = qtyRtv.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.qtyRtv = qtyRtv;
        return this.qtyRtv;
    }

    /**
     * Getter for {@link #expRcvdDt expRcvdDt}
     * 
     */
    public Date getExpRcvdDt() {
        return this.expRcvdDt;
    }

    /**
     * Setter for {@link #expRcvdDt expRcvdDt}
     * 
     */
    public Date setExpRcvdDt(Date expRcvdDt) {
        this.expRcvdDt = expRcvdDt;
        return this.expRcvdDt;
    }

    /**
     * Getter for {@link #expBoDt expBoDt}
     * 
     */
    public Date getExpBoDt() {
        return this.expBoDt;
    }

    /**
     * Setter for {@link #expBoDt expBoDt}
     * 
     */
    public Date setExpBoDt(Date expBoDt) {
        this.expBoDt = expBoDt;
        return this.expBoDt;
    }

    /**
     * Getter for {@link #qtyCounted qtyCounted}
     * 
     */
    public BigDecimal getQtyCounted() {
        return this.qtyCounted;
    }

    /**
     * Setter for {@link #qtyCounted qtyCounted}
     * 
     */
    public BigDecimal setQtyCounted(BigDecimal qtyCounted) {
        if ((qtyCounted!= null)&&(qtyCounted.scale()!= 2)) {
            qtyCounted = qtyCounted.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.qtyCounted = qtyCounted;
        return this.qtyCounted;
    }

    /**
     * Getter for {@link #maxOrderQty maxOrderQty}
     * 
     */
    public BigDecimal getMaxOrderQty() {
        return this.maxOrderQty;
    }

    /**
     * Setter for {@link #maxOrderQty maxOrderQty}
     * 
     */
    public BigDecimal setMaxOrderQty(BigDecimal maxOrderQty) {
        if ((maxOrderQty!= null)&&(maxOrderQty.scale()!= 2)) {
            maxOrderQty = maxOrderQty.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.maxOrderQty = maxOrderQty;
        return this.maxOrderQty;
    }

    /**
     * Getter for {@link #mixMatchNo mixMatchNo}
     * 
     */
    public Integer getMixMatchNo() {
        return this.mixMatchNo;
    }

    /**
     * Setter for {@link #mixMatchNo mixMatchNo}
     * 
     */
    public Integer setMixMatchNo(Integer mixMatchNo) {
        this.mixMatchNo = mixMatchNo;
        return this.mixMatchNo;
    }

    /**
     * Getter for {@link #bottleLinkNo bottleLinkNo}
     * 
     */
    public Integer getBottleLinkNo() {
        return this.bottleLinkNo;
    }

    /**
     * Setter for {@link #bottleLinkNo bottleLinkNo}
     * 
     */
    public Integer setBottleLinkNo(Integer bottleLinkNo) {
        this.bottleLinkNo = bottleLinkNo;
        return this.bottleLinkNo;
    }

    /**
     * Getter for {@link #lastPrice lastPrice}
     * 
     */
    public BigDecimal getLastPrice() {
        return this.lastPrice;
    }

    /**
     * Setter for {@link #lastPrice lastPrice}
     * 
     */
    public BigDecimal setLastPrice(BigDecimal lastPrice) {
        if ((lastPrice!= null)&&(lastPrice.scale()!= 2)) {
            lastPrice = lastPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.lastPrice = lastPrice;
        return this.lastPrice;
    }

    /**
     * Getter for {@link #lastPchgDt lastPchgDt}
     * 
     */
    public Date getLastPchgDt() {
        return this.lastPchgDt;
    }

    /**
     * Setter for {@link #lastPchgDt lastPchgDt}
     * 
     */
    public Date setLastPchgDt(Date lastPchgDt) {
        this.lastPchgDt = lastPchgDt;
        return this.lastPchgDt;
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
     * Getter for {@link #allowPchgFl allowPchgFl}
     * 
     */
    public String getAllowPchgFl() {
        return this.allowPchgFl;
    }

    /**
     * Setter for {@link #allowPchgFl allowPchgFl}
     * 
     */
    public String setAllowPchgFl(String allowPchgFl) {
        this.allowPchgFl = allowPchgFl;
        return this.allowPchgFl;
    }

    /**
     * Getter for {@link #cosPct cosPct}
     * 
     */
    public BigDecimal getCosPct() {
        return this.cosPct;
    }

    /**
     * Setter for {@link #cosPct cosPct}
     * 
     */
    public BigDecimal setCosPct(BigDecimal cosPct) {
        if ((cosPct!= null)&&(cosPct.scale()!= 2)) {
            cosPct = cosPct.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.cosPct = cosPct;
        return this.cosPct;
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
     * Getter for {@link #priorReorderPt priorReorderPt}
     * 
     */
    public BigDecimal getPriorReorderPt() {
        return this.priorReorderPt;
    }

    /**
     * Setter for {@link #priorReorderPt priorReorderPt}
     * 
     */
    public BigDecimal setPriorReorderPt(BigDecimal priorReorderPt) {
        if ((priorReorderPt!= null)&&(priorReorderPt.scale()!= 2)) {
            priorReorderPt = priorReorderPt.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.priorReorderPt = priorReorderPt;
        return this.priorReorderPt;
    }

    /**
     * Getter for {@link #priorReorderTo priorReorderTo}
     * 
     */
    public BigDecimal getPriorReorderTo() {
        return this.priorReorderTo;
    }

    /**
     * Setter for {@link #priorReorderTo priorReorderTo}
     * 
     */
    public BigDecimal setPriorReorderTo(BigDecimal priorReorderTo) {
        if ((priorReorderTo!= null)&&(priorReorderTo.scale()!= 2)) {
            priorReorderTo = priorReorderTo.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.priorReorderTo = priorReorderTo;
        return this.priorReorderTo;
    }

    /**
     * Getter for {@link #warrantyInvCost warrantyInvCost}
     * 
     */
    public BigDecimal getWarrantyInvCost() {
        return this.warrantyInvCost;
    }

    /**
     * Setter for {@link #warrantyInvCost warrantyInvCost}
     * 
     */
    public BigDecimal setWarrantyInvCost(BigDecimal warrantyInvCost) {
        if ((warrantyInvCost!= null)&&(warrantyInvCost.scale()!= 2)) {
            warrantyInvCost = warrantyInvCost.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.warrantyInvCost = warrantyInvCost;
        return this.warrantyInvCost;
    }

    /**
     * Getter for {@link #warrantyQtyOnHand warrantyQtyOnHand}
     * 
     */
    public Long getWarrantyQtyOnHand() {
        return this.warrantyQtyOnHand;
    }

    /**
     * Setter for {@link #warrantyQtyOnHand warrantyQtyOnHand}
     * 
     */
    public Long setWarrantyQtyOnHand(Long warrantyQtyOnHand) {
        this.warrantyQtyOnHand = warrantyQtyOnHand;
        return this.warrantyQtyOnHand;
    }

    /**
     * Getter for {@link #weightedLeadTime weightedLeadTime}
     * 
     */
    public BigDecimal getWeightedLeadTime() {
        return this.weightedLeadTime;
    }

    /**
     * Setter for {@link #weightedLeadTime weightedLeadTime}
     * 
     */
    public BigDecimal setWeightedLeadTime(BigDecimal weightedLeadTime) {
        if ((weightedLeadTime!= null)&&(weightedLeadTime.scale()!= 2)) {
            weightedLeadTime = weightedLeadTime.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.weightedLeadTime = weightedLeadTime;
        return this.weightedLeadTime;
    }

    /**
     * Getter for {@link #coreSellPrice coreSellPrice}
     * 
     */
    public BigDecimal getCoreSellPrice() {
        return this.coreSellPrice;
    }

    /**
     * Setter for {@link #coreSellPrice coreSellPrice}
     * 
     */
    public BigDecimal setCoreSellPrice(BigDecimal coreSellPrice) {
        if ((coreSellPrice!= null)&&(coreSellPrice.scale()!= 2)) {
            coreSellPrice = coreSellPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.coreSellPrice = coreSellPrice;
        return this.coreSellPrice;
    }

    /**
     * Getter for {@link #moDemandForecast moDemandForecast}
     * 
     */
    public BigDecimal getMoDemandForecast() {
        return this.moDemandForecast;
    }

    /**
     * Setter for {@link #moDemandForecast moDemandForecast}
     * 
     */
    public BigDecimal setMoDemandForecast(BigDecimal moDemandForecast) {
        if ((moDemandForecast!= null)&&(moDemandForecast.scale()!= 10)) {
            moDemandForecast = moDemandForecast.setScale(10, BigDecimal.ROUND_HALF_UP);
        }
        this.moDemandForecast = moDemandForecast;
        return this.moDemandForecast;
    }

    /**
     * Getter for {@link #coreInvCost coreInvCost}
     * 
     */
    public BigDecimal getCoreInvCost() {
        return this.coreInvCost;
    }

    /**
     * Setter for {@link #coreInvCost coreInvCost}
     * 
     */
    public BigDecimal setCoreInvCost(BigDecimal coreInvCost) {
        if ((coreInvCost!= null)&&(coreInvCost.scale()!= 2)) {
            coreInvCost = coreInvCost.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.coreInvCost = coreInvCost;
        return this.coreInvCost;
    }

    /**
     * Getter for {@link #coreQtyOnHand coreQtyOnHand}
     * 
     */
    public Long getCoreQtyOnHand() {
        return this.coreQtyOnHand;
    }

    /**
     * Setter for {@link #coreQtyOnHand coreQtyOnHand}
     * 
     */
    public Long setCoreQtyOnHand(Long coreQtyOnHand) {
        this.coreQtyOnHand = coreQtyOnHand;
        return this.coreQtyOnHand;
    }

    /**
     * Getter for {@link #skuStatusId skuStatusId}
     * 
     */
    public String getSkuStatusId() {
        return this.skuStatusId;
    }

    /**
     * Setter for {@link #skuStatusId skuStatusId}
     * 
     */
    public String setSkuStatusId(String skuStatusId) {
        this.skuStatusId = skuStatusId;
        return this.skuStatusId;
    }

    /**
     * Getter for {@link #nonDiscountFl nonDiscountFl}
     * 
     */
    public String getNonDiscountFl() {
        return this.nonDiscountFl;
    }

    /**
     * Setter for {@link #nonDiscountFl nonDiscountFl}
     * 
     */
    public String setNonDiscountFl(String nonDiscountFl) {
        this.nonDiscountFl = nonDiscountFl;
        return this.nonDiscountFl;
    }

    /**
     * Getter for {@link #restrictSaleId restrictSaleId}
     * 
     */
    public String getRestrictSaleId() {
        return this.restrictSaleId;
    }

    /**
     * Setter for {@link #restrictSaleId restrictSaleId}
     * 
     */
    public String setRestrictSaleId(String restrictSaleId) {
        this.restrictSaleId = restrictSaleId;
        return this.restrictSaleId;
    }

    /**
     * Getter for {@link #labelCd labelCd}
     * 
     */
    public String getLabelCd() {
        return this.labelCd;
    }

    /**
     * Setter for {@link #labelCd labelCd}
     * 
     */
    public String setLabelCd(String labelCd) {
        this.labelCd = labelCd;
        return this.labelCd;
    }

    /**
     * Getter for {@link #lastPriceIncDt lastPriceIncDt}
     * 
     */
    public Date getLastPriceIncDt() {
        return this.lastPriceIncDt;
    }

    /**
     * Setter for {@link #lastPriceIncDt lastPriceIncDt}
     * 
     */
    public Date setLastPriceIncDt(Date lastPriceIncDt) {
        this.lastPriceIncDt = lastPriceIncDt;
        return this.lastPriceIncDt;
    }

    /**
     * Getter for {@link #lastPriceDecDt lastPriceDecDt}
     * 
     */
    public Date getLastPriceDecDt() {
        return this.lastPriceDecDt;
    }

    /**
     * Setter for {@link #lastPriceDecDt lastPriceDecDt}
     * 
     */
    public Date setLastPriceDecDt(Date lastPriceDecDt) {
        this.lastPriceDecDt = lastPriceDecDt;
        return this.lastPriceDecDt;
    }

    /**
     * Getter for {@link #lastCycleDt lastCycleDt}
     * 
     */
    public Date getLastCycleDt() {
        return this.lastCycleDt;
    }

    /**
     * Setter for {@link #lastCycleDt lastCycleDt}
     * 
     */
    public Date setLastCycleDt(Date lastCycleDt) {
        this.lastCycleDt = lastCycleDt;
        return this.lastCycleDt;
    }

    /**
     * Getter for {@link #lastPriceVerDt lastPriceVerDt}
     * 
     */
    public Date getLastPriceVerDt() {
        return this.lastPriceVerDt;
    }

    /**
     * Setter for {@link #lastPriceVerDt lastPriceVerDt}
     * 
     */
    public Date setLastPriceVerDt(Date lastPriceVerDt) {
        this.lastPriceVerDt = lastPriceVerDt;
        return this.lastPriceVerDt;
    }

    /**
     * Getter for {@link #lastChangeDt lastChangeDt}
     * 
     */
    public Date getLastChangeDt() {
        return this.lastChangeDt;
    }

    /**
     * Setter for {@link #lastChangeDt lastChangeDt}
     * 
     */
    public Date setLastChangeDt(Date lastChangeDt) {
        this.lastChangeDt = lastChangeDt;
        return this.lastChangeDt;
    }

    /**
     * Getter for {@link #newSkuStatusId newSkuStatusId}
     * 
     */
    public String getNewSkuStatusId() {
        return this.newSkuStatusId;
    }

    /**
     * Setter for {@link #newSkuStatusId newSkuStatusId}
     * 
     */
    public String setNewSkuStatusId(String newSkuStatusId) {
        this.newSkuStatusId = newSkuStatusId;
        return this.newSkuStatusId;
    }

    /**
     * Getter for {@link #itemizerStatusId itemizerStatusId}
     * 
     */
    public String getItemizerStatusId() {
        return this.itemizerStatusId;
    }

    /**
     * Setter for {@link #itemizerStatusId itemizerStatusId}
     * 
     */
    public String setItemizerStatusId(String itemizerStatusId) {
        this.itemizerStatusId = itemizerStatusId;
        return this.itemizerStatusId;
    }

    /**
     * Getter for {@link #scanDeptNo scanDeptNo}
     * 
     */
    public String getScanDeptNo() {
        return this.scanDeptNo;
    }

    /**
     * Setter for {@link #scanDeptNo scanDeptNo}
     * 
     */
    public String setScanDeptNo(String scanDeptNo) {
        this.scanDeptNo = scanDeptNo;
        return this.scanDeptNo;
    }

    /**
     * Getter for {@link #scanSubdeptNo scanSubdeptNo}
     * 
     */
    public String getScanSubdeptNo() {
        return this.scanSubdeptNo;
    }

    /**
     * Setter for {@link #scanSubdeptNo scanSubdeptNo}
     * 
     */
    public String setScanSubdeptNo(String scanSubdeptNo) {
        this.scanSubdeptNo = scanSubdeptNo;
        return this.scanSubdeptNo;
    }

    /**
     * Getter for {@link #downloadNToYFl downloadNToYFl}
     * 
     */
    public String getDownloadNToYFl() {
        return this.downloadNToYFl;
    }

    /**
     * Setter for {@link #downloadNToYFl downloadNToYFl}
     * 
     */
    public String setDownloadNToYFl(String downloadNToYFl) {
        this.downloadNToYFl = downloadNToYFl;
        return this.downloadNToYFl;
    }

    /**
     * Getter for {@link #labelQty1 labelQty1}
     * 
     */
    public Integer getLabelQty1() {
        return this.labelQty1;
    }

    /**
     * Setter for {@link #labelQty1 labelQty1}
     * 
     */
    public Integer setLabelQty1(Integer labelQty1) {
        this.labelQty1 = labelQty1;
        return this.labelQty1;
    }

    /**
     * Getter for {@link #labelQty2 labelQty2}
     * 
     */
    public Integer getLabelQty2() {
        return this.labelQty2;
    }

    /**
     * Setter for {@link #labelQty2 labelQty2}
     * 
     */
    public Integer setLabelQty2(Integer labelQty2) {
        this.labelQty2 = labelQty2;
        return this.labelQty2;
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
     * Getter for {@link #lastXferDt lastXferDt}
     * 
     */
    public Date getLastXferDt() {
        return this.lastXferDt;
    }

    /**
     * Setter for {@link #lastXferDt lastXferDt}
     * 
     */
    public Date setLastXferDt(Date lastXferDt) {
        this.lastXferDt = lastXferDt;
        return this.lastXferDt;
    }

    /**
     * Getter for {@link #priceOptionCd priceOptionCd}
     * 
     */
    public Integer getPriceOptionCd() {
        return this.priceOptionCd;
    }

    /**
     * Setter for {@link #priceOptionCd priceOptionCd}
     * 
     */
    public Integer setPriceOptionCd(Integer priceOptionCd) {
        this.priceOptionCd = priceOptionCd;
        return this.priceOptionCd;
    }

    /**
     * Getter for {@link #priceAttributeCd priceAttributeCd}
     * 
     */
    public Integer getPriceAttributeCd() {
        return this.priceAttributeCd;
    }

    /**
     * Setter for {@link #priceAttributeCd priceAttributeCd}
     * 
     */
    public Integer setPriceAttributeCd(Integer priceAttributeCd) {
        this.priceAttributeCd = priceAttributeCd;
        return this.priceAttributeCd;
    }

    /**
     * Getter for {@link #promoRecordNo promoRecordNo}
     * 
     */
    public Integer getPromoRecordNo() {
        return this.promoRecordNo;
    }

    /**
     * Setter for {@link #promoRecordNo promoRecordNo}
     * 
     */
    public Integer setPromoRecordNo(Integer promoRecordNo) {
        this.promoRecordNo = promoRecordNo;
        return this.promoRecordNo;
    }

    /**
     * Getter for {@link #lastVendorCost lastVendorCost}
     * 
     */
    public BigDecimal getLastVendorCost() {
        return this.lastVendorCost;
    }

    /**
     * Setter for {@link #lastVendorCost lastVendorCost}
     * 
     */
    public BigDecimal setLastVendorCost(BigDecimal lastVendorCost) {
        if ((lastVendorCost!= null)&&(lastVendorCost.scale()!= 4)) {
            lastVendorCost = lastVendorCost.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.lastVendorCost = lastVendorCost;
        return this.lastVendorCost;
    }

    /**
     * Getter for {@link #avgMovement avgMovement}
     * 
     */
    public BigDecimal getAvgMovement() {
        return this.avgMovement;
    }

    /**
     * Setter for {@link #avgMovement avgMovement}
     * 
     */
    public BigDecimal setAvgMovement(BigDecimal avgMovement) {
        if ((avgMovement!= null)&&(avgMovement.scale()!= 4)) {
            avgMovement = avgMovement.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.avgMovement = avgMovement;
        return this.avgMovement;
    }

    /**
     * Getter for {@link #haloGm haloGm}
     * 
     */
    public BigDecimal getHaloGm() {
        return this.haloGm;
    }

    /**
     * Setter for {@link #haloGm haloGm}
     * 
     */
    public BigDecimal setHaloGm(BigDecimal haloGm) {
        if ((haloGm!= null)&&(haloGm.scale()!= 2)) {
            haloGm = haloGm.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.haloGm = haloGm;
        return this.haloGm;
    }

    /**
     * Getter for {@link #laloGm laloGm}
     * 
     */
    public BigDecimal getLaloGm() {
        return this.laloGm;
    }

    /**
     * Setter for {@link #laloGm laloGm}
     * 
     */
    public BigDecimal setLaloGm(BigDecimal laloGm) {
        if ((laloGm!= null)&&(laloGm.scale()!= 2)) {
            laloGm = laloGm.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.laloGm = laloGm;
        return this.laloGm;
    }

    /**
     * Getter for {@link #replenishmentFl replenishmentFl}
     * 
     */
    public String getReplenishmentFl() {
        return this.replenishmentFl;
    }

    /**
     * Setter for {@link #replenishmentFl replenishmentFl}
     * 
     */
    public String setReplenishmentFl(String replenishmentFl) {
        this.replenishmentFl = replenishmentFl;
        return this.replenishmentFl;
    }

    /**
     * Getter for {@link #velocityCd velocityCd}
     * 
     */
    public String getVelocityCd() {
        return this.velocityCd;
    }

    /**
     * Setter for {@link #velocityCd velocityCd}
     * 
     */
    public String setVelocityCd(String velocityCd) {
        this.velocityCd = velocityCd;
        return this.velocityCd;
    }

    /**
     * Getter for {@link #velocityLockedFl velocityLockedFl}
     * 
     */
    public String getVelocityLockedFl() {
        return this.velocityLockedFl;
    }

    /**
     * Setter for {@link #velocityLockedFl velocityLockedFl}
     * 
     */
    public String setVelocityLockedFl(String velocityLockedFl) {
        this.velocityLockedFl = velocityLockedFl;
        return this.velocityLockedFl;
    }

    /**
     * Getter for {@link #velocityChangeDt velocityChangeDt}
     * 
     */
    public Date getVelocityChangeDt() {
        return this.velocityChangeDt;
    }

    /**
     * Setter for {@link #velocityChangeDt velocityChangeDt}
     * 
     */
    public Date setVelocityChangeDt(Date velocityChangeDt) {
        this.velocityChangeDt = velocityChangeDt;
        return this.velocityChangeDt;
    }

    /**
     * Getter for {@link #reorderLockedFl reorderLockedFl}
     * 
     */
    public String getReorderLockedFl() {
        return this.reorderLockedFl;
    }

    /**
     * Setter for {@link #reorderLockedFl reorderLockedFl}
     * 
     */
    public String setReorderLockedFl(String reorderLockedFl) {
        this.reorderLockedFl = reorderLockedFl;
        return this.reorderLockedFl;
    }

    /**
     * Getter for {@link #reorderNowFl reorderNowFl}
     * 
     */
    public String getReorderNowFl() {
        return this.reorderNowFl;
    }

    /**
     * Setter for {@link #reorderNowFl reorderNowFl}
     * 
     */
    public String setReorderNowFl(String reorderNowFl) {
        this.reorderNowFl = reorderNowFl;
        return this.reorderNowFl;
    }

    /**
     * Getter for {@link #profileData profileData}
     * 
     */
    public String getProfileData() {
        return this.profileData;
    }

    /**
     * Setter for {@link #profileData profileData}
     * 
     */
    public String setProfileData(String profileData) {
        this.profileData = profileData;
        return this.profileData;
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
     * Getter for {@link #reorderpointSourceCd reorderpointSourceCd}
     * 
     */
    public String getReorderpointSourceCd() {
        return this.reorderpointSourceCd;
    }

    /**
     * Setter for {@link #reorderpointSourceCd reorderpointSourceCd}
     * 
     */
    public String setReorderpointSourceCd(String reorderpointSourceCd) {
        this.reorderpointSourceCd = reorderpointSourceCd;
        return this.reorderpointSourceCd;
    }

    /**
     * Getter for {@link #defaultDcSkuLevel defaultDcSkuLevel}
     * 
     */
    public Integer getDefaultDcSkuLevel() {
        return this.defaultDcSkuLevel;
    }

    /**
     * Setter for {@link #defaultDcSkuLevel defaultDcSkuLevel}
     * 
     */
    public Integer setDefaultDcSkuLevel(Integer defaultDcSkuLevel) {
        this.defaultDcSkuLevel = defaultDcSkuLevel;
        return this.defaultDcSkuLevel;
    }

    /**
     * Getter for {@link #priceCalcId priceCalcId}
     * 
     */
    public String getPriceCalcId() {
        return this.priceCalcId;
    }

    /**
     * Setter for {@link #priceCalcId priceCalcId}
     * 
     */
    public String setPriceCalcId(String priceCalcId) {
        this.priceCalcId = priceCalcId;
        return this.priceCalcId;
    }

    /**
     * Getter for {@link #profileDataFl profileDataFl}
     * 
     */
    public String getProfileDataFl() {
        return this.profileDataFl;
    }

    /**
     * Setter for {@link #profileDataFl profileDataFl}
     * 
     */
    public String setProfileDataFl(String profileDataFl) {
        this.profileDataFl = profileDataFl;
        return this.profileDataFl;
    }

    /**
     * Getter for {@link #discontinuedStFl discontinuedStFl}
     * 
     */
    public String getDiscontinuedStFl() {
        return this.discontinuedStFl;
    }

    /**
     * Setter for {@link #discontinuedStFl discontinuedStFl}
     * 
     */
    public String setDiscontinuedStFl(String discontinuedStFl) {
        this.discontinuedStFl = discontinuedStFl;
        return this.discontinuedStFl;
    }

    /**
     * Getter for {@link #discontinuedDt discontinuedDt}
     * 
     */
    public Date getDiscontinuedDt() {
        return this.discontinuedDt;
    }

    /**
     * Setter for {@link #discontinuedDt discontinuedDt}
     * 
     */
    public Date setDiscontinuedDt(Date discontinuedDt) {
        this.discontinuedDt = discontinuedDt;
        return this.discontinuedDt;
    }

    /**
     * Getter for {@link #discontinuedBy discontinuedBy}
     * 
     */
    public String getDiscontinuedBy() {
        return this.discontinuedBy;
    }

    /**
     * Setter for {@link #discontinuedBy discontinuedBy}
     * 
     */
    public String setDiscontinuedBy(String discontinuedBy) {
        this.discontinuedBy = discontinuedBy;
        return this.discontinuedBy;
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
     * Getter for {@link #promoCommissionCd promoCommissionCd}
     * 
     */
    public Long getPromoCommissionCd() {
        return this.promoCommissionCd;
    }

    /**
     * Setter for {@link #promoCommissionCd promoCommissionCd}
     * 
     */
    public Long setPromoCommissionCd(Long promoCommissionCd) {
        this.promoCommissionCd = promoCommissionCd;
        return this.promoCommissionCd;
    }

    /**
     * Getter for {@link #additionalTax additionalTax}
     * 
     */
    public BigDecimal getAdditionalTax() {
        return this.additionalTax;
    }

    /**
     * Setter for {@link #additionalTax additionalTax}
     * 
     */
    public BigDecimal setAdditionalTax(BigDecimal additionalTax) {
        if ((additionalTax!= null)&&(additionalTax.scale()!= 2)) {
            additionalTax = additionalTax.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.additionalTax = additionalTax;
        return this.additionalTax;
    }

    /**
     * Getter for {@link #negativeQohFl negativeQohFl}
     * 
     */
    public String getNegativeQohFl() {
        return this.negativeQohFl;
    }

    /**
     * Setter for {@link #negativeQohFl negativeQohFl}
     * 
     */
    public String setNegativeQohFl(String negativeQohFl) {
        this.negativeQohFl = negativeQohFl;
        return this.negativeQohFl;
    }

    /**
     * Getter for {@link #posSkuStatusId posSkuStatusId}
     * 
     */
    public String getPosSkuStatusId() {
        return this.posSkuStatusId;
    }

    /**
     * Setter for {@link #posSkuStatusId posSkuStatusId}
     * 
     */
    public String setPosSkuStatusId(String posSkuStatusId) {
        this.posSkuStatusId = posSkuStatusId;
        return this.posSkuStatusId;
    }

    /**
     * Getter for {@link #vendorItemSeqNo vendorItemSeqNo}
     * 
     */
    public BigInteger getVendorItemSeqNo() {
        return this.vendorItemSeqNo;
    }

    /**
     * Setter for {@link #vendorItemSeqNo vendorItemSeqNo}
     * 
     */
    public BigInteger setVendorItemSeqNo(BigInteger vendorItemSeqNo) {
        this.vendorItemSeqNo = vendorItemSeqNo;
        return this.vendorItemSeqNo;
    }

    /**
     * Getter for {@link #replacementCostAmt replacementCostAmt}
     * 
     */
    public BigDecimal getReplacementCostAmt() {
        return this.replacementCostAmt;
    }

    /**
     * Setter for {@link #replacementCostAmt replacementCostAmt}
     * 
     */
    public BigDecimal setReplacementCostAmt(BigDecimal replacementCostAmt) {
        if ((replacementCostAmt!= null)&&(replacementCostAmt.scale()!= 4)) {
            replacementCostAmt = replacementCostAmt.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.replacementCostAmt = replacementCostAmt;
        return this.replacementCostAmt;
    }

    /**
     * Getter for {@link #firstPrice firstPrice}
     * 
     */
    public BigDecimal getFirstPrice() {
        return this.firstPrice;
    }

    /**
     * Setter for {@link #firstPrice firstPrice}
     * 
     */
    public BigDecimal setFirstPrice(BigDecimal firstPrice) {
        if ((firstPrice!= null)&&(firstPrice.scale()!= 2)) {
            firstPrice = firstPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.firstPrice = firstPrice;
        return this.firstPrice;
    }

    /**
     * Getter for {@link #firstCost firstCost}
     * 
     */
    public BigDecimal getFirstCost() {
        return this.firstCost;
    }

    /**
     * Setter for {@link #firstCost firstCost}
     * 
     */
    public BigDecimal setFirstCost(BigDecimal firstCost) {
        if ((firstCost!= null)&&(firstCost.scale()!= 4)) {
            firstCost = firstCost.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.firstCost = firstCost;
        return this.firstCost;
    }

    /**
     * Getter for {@link #firstSoldDt firstSoldDt}
     * 
     */
    public Date getFirstSoldDt() {
        return this.firstSoldDt;
    }

    /**
     * Setter for {@link #firstSoldDt firstSoldDt}
     * 
     */
    public Date setFirstSoldDt(Date firstSoldDt) {
        this.firstSoldDt = firstSoldDt;
        return this.firstSoldDt;
    }

    /**
     * Getter for {@link #stockingDt stockingDt}
     * 
     */
    public Date getStockingDt() {
        return this.stockingDt;
    }

    /**
     * Setter for {@link #stockingDt stockingDt}
     * 
     */
    public Date setStockingDt(Date stockingDt) {
        this.stockingDt = stockingDt;
        return this.stockingDt;
    }

    /**
     * Getter for {@link #classCode classCode}
     * 
     */
    public String getClassCode() {
        return this.classCode;
    }

    /**
     * Setter for {@link #classCode classCode}
     * 
     */
    public String setClassCode(String classCode) {
        this.classCode = classCode;
        return this.classCode;
    }

    /**
     * Getter for {@link #negativeQtySiteNo negativeQtySiteNo}
     * 
     */
    public Integer getNegativeQtySiteNo() {
        return this.negativeQtySiteNo;
    }

    /**
     * Setter for {@link #negativeQtySiteNo negativeQtySiteNo}
     * 
     */
    public Integer setNegativeQtySiteNo(Integer negativeQtySiteNo) {
        this.negativeQtySiteNo = negativeQtySiteNo;
        return this.negativeQtySiteNo;
    }

    /**
     * Getter for {@link #purchaseVolumeId purchaseVolumeId}
     * 
     */
    public String getPurchaseVolumeId() {
        return this.purchaseVolumeId;
    }

    /**
     * Setter for {@link #purchaseVolumeId purchaseVolumeId}
     * 
     */
    public String setPurchaseVolumeId(String purchaseVolumeId) {
        this.purchaseVolumeId = purchaseVolumeId;
        return this.purchaseVolumeId;
    }

    /**
     * Getter for {@link #reorderSiteNo reorderSiteNo}
     * 
     */
    public Integer getReorderSiteNo() {
        return this.reorderSiteNo;
    }

    /**
     * Setter for {@link #reorderSiteNo reorderSiteNo}
     * 
     */
    public Integer setReorderSiteNo(Integer reorderSiteNo) {
        this.reorderSiteNo = reorderSiteNo;
        return this.reorderSiteNo;
    }

    /**
     * Getter for {@link #productStartDt productStartDt}
     * 
     */
    public Date getProductStartDt() {
        return this.productStartDt;
    }

    /**
     * Setter for {@link #productStartDt productStartDt}
     * 
     */
    public Date setProductStartDt(Date productStartDt) {
        this.productStartDt = productStartDt;
        return this.productStartDt;
    }

    /**
     * Getter for {@link #productEndDt productEndDt}
     * 
     */
    public Date getProductEndDt() {
        return this.productEndDt;
    }

    /**
     * Setter for {@link #productEndDt productEndDt}
     * 
     */
    public Date setProductEndDt(Date productEndDt) {
        this.productEndDt = productEndDt;
        return this.productEndDt;
    }

    /**
     * Getter for {@link #lastReplenishmentDt lastReplenishmentDt}
     * 
     */
    public Date getLastReplenishmentDt() {
        return this.lastReplenishmentDt;
    }

    /**
     * Setter for {@link #lastReplenishmentDt lastReplenishmentDt}
     * 
     */
    public Date setLastReplenishmentDt(Date lastReplenishmentDt) {
        this.lastReplenishmentDt = lastReplenishmentDt;
        return this.lastReplenishmentDt;
    }

    /**
     * Getter for {@link #replicatePricesFl replicatePricesFl}
     * 
     */
    public String getReplicatePricesFl() {
        return this.replicatePricesFl;
    }

    /**
     * Setter for {@link #replicatePricesFl replicatePricesFl}
     * 
     */
    public String setReplicatePricesFl(String replicatePricesFl) {
        this.replicatePricesFl = replicatePricesFl;
        return this.replicatePricesFl;
    }

    /**
     * Getter for {@link #lastPricechgTime lastPricechgTime}
     * 
     */
    public Date getLastPricechgTime() {
        return this.lastPricechgTime;
    }

    /**
     * Setter for {@link #lastPricechgTime lastPricechgTime}
     * 
     */
    public Date setLastPricechgTime(Date lastPricechgTime) {
        this.lastPricechgTime = lastPricechgTime;
        return this.lastPricechgTime;
    }

    /**
     * Getter for {@link #avgDlySales avgDlySales}
     * 
     */
    public BigDecimal getAvgDlySales() {
        return this.avgDlySales;
    }

    /**
     * Setter for {@link #avgDlySales avgDlySales}
     * 
     */
    public BigDecimal setAvgDlySales(BigDecimal avgDlySales) {
        if ((avgDlySales!= null)&&(avgDlySales.scale()!= 4)) {
            avgDlySales = avgDlySales.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        this.avgDlySales = avgDlySales;
        return this.avgDlySales;
    }

    /**
     * @hide<P><I>Not for public use.</I></P>
     * 
     */
    private void _initDefaults() {
        if (this.minLevel == null) {
            this.minLevel = new BigDecimal("0.00");
        }
        if (this.maxLevel == null) {
            this.maxLevel = new BigDecimal("0.00");
        }
        if (this.reorderPt == null) {
            this.reorderPt = new BigDecimal("0.00");
        }
        if (this.reorderTo == null) {
            this.reorderTo = new BigDecimal("0.00");
        }
        if (this.priceType == null) {
            this.priceType = "R";
        }
        if (this.unitPrice1 == null) {
            this.unitPrice1 = new BigDecimal("0.00");
        }
        if (this.unitPrice2 == null) {
            this.unitPrice2 = new BigDecimal("0.00");
        }
        if (this.unitPrice3 == null) {
            this.unitPrice3 = new BigDecimal("0.00");
        }
        if (this.unitPrice4 == null) {
            this.unitPrice4 = new BigDecimal("0.00");
        }
        if (this.unitPrice5 == null) {
            this.unitPrice5 = new BigDecimal("0.00");
        }
        if (this.unitPrice6 == null) {
            this.unitPrice6 = new BigDecimal("0.00");
        }
        if (this.unitPrice7 == null) {
            this.unitPrice7 = new BigDecimal("0.00");
        }
        if (this.unitPrice8 == null) {
            this.unitPrice8 = new BigDecimal("0.00");
        }
        if (this.unitPrice9 == null) {
            this.unitPrice9 = new BigDecimal("0.00");
        }
        if (this.unitPrice10 == null) {
            this.unitPrice10 = new BigDecimal("0.00");
        }
        if (this.priceQty1 == null) {
            this.priceQty1 = Integer.valueOf(0);
        }
        if (this.priceQty2 == null) {
            this.priceQty2 = Integer.valueOf(0);
        }
        if (this.priceQty3 == null) {
            this.priceQty3 = Integer.valueOf(0);
        }
        if (this.priceQty4 == null) {
            this.priceQty4 = Integer.valueOf(0);
        }
        if (this.priceQty5 == null) {
            this.priceQty5 = Integer.valueOf(0);
        }
        if (this.priceQty6 == null) {
            this.priceQty6 = Integer.valueOf(0);
        }
        if (this.priceQty7 == null) {
            this.priceQty7 = Integer.valueOf(0);
        }
        if (this.priceQty8 == null) {
            this.priceQty8 = Integer.valueOf(0);
        }
        if (this.priceQty9 == null) {
            this.priceQty9 = Integer.valueOf(0);
        }
        if (this.priceQty10 == null) {
            this.priceQty10 = Integer.valueOf(0);
        }
        if (this.price1 == null) {
            this.price1 = new BigDecimal("0.00");
        }
        if (this.price2 == null) {
            this.price2 = new BigDecimal("0.00");
        }
        if (this.price3 == null) {
            this.price3 = new BigDecimal("0.00");
        }
        if (this.price4 == null) {
            this.price4 = new BigDecimal("0.00");
        }
        if (this.price5 == null) {
            this.price5 = new BigDecimal("0.00");
        }
        if (this.price6 == null) {
            this.price6 = new BigDecimal("0.00");
        }
        if (this.price7 == null) {
            this.price7 = new BigDecimal("0.00");
        }
        if (this.price8 == null) {
            this.price8 = new BigDecimal("0.00");
        }
        if (this.price9 == null) {
            this.price9 = new BigDecimal("0.00");
        }
        if (this.price10 == null) {
            this.price10 = new BigDecimal("0.00");
        }
        if (this.epoPct == null) {
            this.epoPct = new BigDecimal("0.00");
        }
        if (this.msrpUnitPrice == null) {
            this.msrpUnitPrice = new BigDecimal("0.00");
        }
        if (this.msrpPrice == null) {
            this.msrpPrice = new BigDecimal("0.00");
        }
        if (this.msrpQty == null) {
            this.msrpQty = Integer.valueOf(0);
        }
        if (this.pkgPriceQty1 == null) {
            this.pkgPriceQty1 = Integer.valueOf(0);
        }
        if (this.pkgPriceQty2 == null) {
            this.pkgPriceQty2 = Integer.valueOf(0);
        }
        if (this.pkgPriceQty3 == null) {
            this.pkgPriceQty3 = Integer.valueOf(0);
        }
        if (this.pkgPrice1 == null) {
            this.pkgPrice1 = new BigDecimal("0.00");
        }
        if (this.pkgPrice2 == null) {
            this.pkgPrice2 = new BigDecimal("0.00");
        }
        if (this.pkgPrice3 == null) {
            this.pkgPrice3 = new BigDecimal("0.00");
        }
        if (this.preMarkdownPrice == null) {
            this.preMarkdownPrice = new BigDecimal("0.00");
        }
        if (this.noOfFacings == null) {
            this.noOfFacings = Integer.valueOf(0);
        }
        if (this.shelfVelocity == null) {
            this.shelfVelocity = Long.valueOf(0L);
        }
        if (this.invCost == null) {
            this.invCost = new BigDecimal("0.00");
        }
        if (this.avgAge == null) {
            this.avgAge = new BigDecimal("0.0");
        }
        if (this.qtyLastRcvd == null) {
            this.qtyLastRcvd = new BigDecimal("0.00");
        }
        if (this.qtyOnHand == null) {
            this.qtyOnHand = new BigDecimal("0.00");
        }
        if (this.qtyRsvd == null) {
            this.qtyRsvd = new BigDecimal("0.00");
        }
        if (this.qtyOnOrd == null) {
            this.qtyOnOrd = new BigDecimal("0.00");
        }
        if (this.qtyOnBackord == null) {
            this.qtyOnBackord = new BigDecimal("0.00");
        }
        if (this.qtyInTransit == null) {
            this.qtyInTransit = new BigDecimal("0.00");
        }
        if (this.qtyCounted == null) {
            this.qtyCounted = new BigDecimal("0.00");
        }
        if (this.maxOrderQty == null) {
            this.maxOrderQty = new BigDecimal("0.00");
        }
        if (this.lastPrice == null) {
            this.lastPrice = new BigDecimal("0.00");
        }
        if (this.downloadFl == null) {
            this.downloadFl = "Y";
        }
        if (this.allowPchgFl == null) {
            this.allowPchgFl = "Y";
        }
        if (this.supersededFl == null) {
            this.supersededFl = "N";
        }
        if (this.priorReorderPt == null) {
            this.priorReorderPt = new BigDecimal("0.00");
        }
        if (this.priorReorderTo == null) {
            this.priorReorderTo = new BigDecimal("0.00");
        }
        if (this.warrantyInvCost == null) {
            this.warrantyInvCost = new BigDecimal("0.00");
        }
        if (this.warrantyQtyOnHand == null) {
            this.warrantyQtyOnHand = Long.valueOf(0L);
        }
        if (this.weightedLeadTime == null) {
            this.weightedLeadTime = new BigDecimal("0.00");
        }
        if (this.coreSellPrice == null) {
            this.coreSellPrice = new BigDecimal("0.00");
        }
        if (this.moDemandForecast == null) {
            this.moDemandForecast = new BigDecimal("0.0000000000");
        }
        if (this.coreInvCost == null) {
            this.coreInvCost = new BigDecimal("0.00");
        }
        if (this.coreQtyOnHand == null) {
            this.coreQtyOnHand = Long.valueOf(0L);
        }
        if (this.nonDiscountFl == null) {
            this.nonDiscountFl = "N";
        }
        if (this.warehouseInnerPack == null) {
            this.warehouseInnerPack = new BigDecimal("1.00");
        }
        if (this.priceOptionCd == null) {
            this.priceOptionCd = Integer.valueOf(0);
        }
        if (this.priceAttributeCd == null) {
            this.priceAttributeCd = Integer.valueOf(0);
        }
        if (this.replenishmentFl == null) {
            this.replenishmentFl = "N";
        }
        if (this.velocityLockedFl == null) {
            this.velocityLockedFl = "N";
        }
        if (this.reorderLockedFl == null) {
            this.reorderLockedFl = "N";
        }
        if (this.reorderpointSourceCd == null) {
            this.reorderpointSourceCd = "M";
        }
        if (this.discontinuedStFl == null) {
            this.discontinuedStFl = "N";
        }
        if (this.replacementCostAmt == null) {
            this.replacementCostAmt = new BigDecimal("0.0000");
        }
        if (this.avgDlySales == null) {
            this.avgDlySales = new BigDecimal("0.0000");
        }
    }

    /**
     * @hide<P><I>Not for public use.</I></P><P>Initializes internal collections.</P>
     * 
     */
    private void _init() {
    }

    /**
     * <P>Used to copy a InvbysitFullEntity including non-identifier fields only.</P>
     * 
     */
    public InvbysitFullEntity copyData() {
        final InvbysitFullEntity copy = new InvbysitFullEntity();
        	
        copy.promoKeyNo = this.promoKeyNo;
        copy.location1 = this.location1;
        copy.location2 = this.location2;
        copy.minLevel = this.minLevel;
        copy.maxLevel = this.maxLevel;
        copy.reorderPt = this.reorderPt;
        copy.reorderTo = this.reorderTo;
        copy.priceType = this.priceType;
        copy.unitPrice1 = this.unitPrice1;
        copy.unitPrice2 = this.unitPrice2;
        copy.unitPrice3 = this.unitPrice3;
        copy.unitPrice4 = this.unitPrice4;
        copy.unitPrice5 = this.unitPrice5;
        copy.unitPrice6 = this.unitPrice6;
        copy.unitPrice7 = this.unitPrice7;
        copy.unitPrice8 = this.unitPrice8;
        copy.unitPrice9 = this.unitPrice9;
        copy.unitPrice10 = this.unitPrice10;
        copy.priceQty1 = this.priceQty1;
        copy.priceQty2 = this.priceQty2;
        copy.priceQty3 = this.priceQty3;
        copy.priceQty4 = this.priceQty4;
        copy.priceQty5 = this.priceQty5;
        copy.priceQty6 = this.priceQty6;
        copy.priceQty7 = this.priceQty7;
        copy.priceQty8 = this.priceQty8;
        copy.priceQty9 = this.priceQty9;
        copy.priceQty10 = this.priceQty10;
        copy.price1 = this.price1;
        copy.price2 = this.price2;
        copy.price3 = this.price3;
        copy.price4 = this.price4;
        copy.price5 = this.price5;
        copy.price6 = this.price6;
        copy.price7 = this.price7;
        copy.price8 = this.price8;
        copy.price9 = this.price9;
        copy.price10 = this.price10;
        copy.epoPct = this.epoPct;
        copy.msrpUnitPrice = this.msrpUnitPrice;
        copy.msrpPrice = this.msrpPrice;
        copy.msrpQty = this.msrpQty;
        copy.pkgPriceQty1 = this.pkgPriceQty1;
        copy.pkgPriceQty2 = this.pkgPriceQty2;
        copy.pkgPriceQty3 = this.pkgPriceQty3;
        copy.pkgPrice1 = this.pkgPrice1;
        copy.pkgPrice2 = this.pkgPrice2;
        copy.pkgPrice3 = this.pkgPrice3;
        copy.preMarkdownPrice = this.preMarkdownPrice;
        copy.noOfFacings = this.noOfFacings;
        copy.shelfVelocity = this.shelfVelocity;
        copy.commissionCd = this.commissionCd;
        copy.invCost = this.invCost;
        copy.standardCost = this.standardCost;
        copy.avgAge = this.avgAge;
        copy.firstRcvdDt = this.firstRcvdDt;
        copy.lastRcvdDt = this.lastRcvdDt;
        copy.qtyLastRcvd = this.qtyLastRcvd;
        copy.lastOrdDt = this.lastOrdDt;
        copy.lastSoldDt = this.lastSoldDt;
        copy.lastSoldPreviousDt = this.lastSoldPreviousDt;
        copy.qtyOnHand = this.qtyOnHand;
        copy.qtyRsvd = this.qtyRsvd;
        copy.qtyOnOrd = this.qtyOnOrd;
        copy.qtyOnBackord = this.qtyOnBackord;
        copy.qtyInTransit = this.qtyInTransit;
        copy.qtyRtv = this.qtyRtv;
        copy.expRcvdDt = this.expRcvdDt;
        copy.expBoDt = this.expBoDt;
        copy.qtyCounted = this.qtyCounted;
        copy.maxOrderQty = this.maxOrderQty;
        copy.mixMatchNo = this.mixMatchNo;
        copy.bottleLinkNo = this.bottleLinkNo;
        copy.lastPrice = this.lastPrice;
        copy.lastPchgDt = this.lastPchgDt;
        copy.downloadFl = this.downloadFl;
        copy.allowPchgFl = this.allowPchgFl;
        copy.cosPct = this.cosPct;
        copy.supersededFl = this.supersededFl;
        copy.priorReorderPt = this.priorReorderPt;
        copy.priorReorderTo = this.priorReorderTo;
        copy.warrantyInvCost = this.warrantyInvCost;
        copy.warrantyQtyOnHand = this.warrantyQtyOnHand;
        copy.weightedLeadTime = this.weightedLeadTime;
        copy.coreSellPrice = this.coreSellPrice;
        copy.moDemandForecast = this.moDemandForecast;
        copy.coreInvCost = this.coreInvCost;
        copy.coreQtyOnHand = this.coreQtyOnHand;
        copy.skuStatusId = this.skuStatusId;
        copy.nonDiscountFl = this.nonDiscountFl;
        copy.restrictSaleId = this.restrictSaleId;
        copy.labelCd = this.labelCd;
        copy.lastPriceIncDt = this.lastPriceIncDt;
        copy.lastPriceDecDt = this.lastPriceDecDt;
        copy.lastCycleDt = this.lastCycleDt;
        copy.lastPriceVerDt = this.lastPriceVerDt;
        copy.lastChangeDt = this.lastChangeDt;
        copy.newSkuStatusId = this.newSkuStatusId;
        copy.itemizerStatusId = this.itemizerStatusId;
        copy.scanDeptNo = this.scanDeptNo;
        copy.scanSubdeptNo = this.scanSubdeptNo;
        copy.downloadNToYFl = this.downloadNToYFl;
        copy.labelQty1 = this.labelQty1;
        copy.labelQty2 = this.labelQty2;
        copy.warehouseInnerPack = this.warehouseInnerPack;
        copy.lastXferDt = this.lastXferDt;
        copy.priceOptionCd = this.priceOptionCd;
        copy.priceAttributeCd = this.priceAttributeCd;
        copy.promoRecordNo = this.promoRecordNo;
        copy.lastVendorCost = this.lastVendorCost;
        copy.avgMovement = this.avgMovement;
        copy.haloGm = this.haloGm;
        copy.laloGm = this.laloGm;
        copy.replenishmentFl = this.replenishmentFl;
        copy.velocityCd = this.velocityCd;
        copy.velocityLockedFl = this.velocityLockedFl;
        copy.velocityChangeDt = this.velocityChangeDt;
        copy.reorderLockedFl = this.reorderLockedFl;
        copy.reorderNowFl = this.reorderNowFl;
        copy.profileData = this.profileData;
        copy.replicationNo = this.replicationNo;
        copy.operationType = this.operationType;
        copy.dateChanged = this.dateChanged;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.reorderpointSourceCd = this.reorderpointSourceCd;
        copy.defaultDcSkuLevel = this.defaultDcSkuLevel;
        copy.priceCalcId = this.priceCalcId;
        copy.profileDataFl = this.profileDataFl;
        copy.discontinuedStFl = this.discontinuedStFl;
        copy.discontinuedDt = this.discontinuedDt;
        copy.discontinuedBy = this.discontinuedBy;
        copy.tareTableNo = this.tareTableNo;
        copy.promoCommissionCd = this.promoCommissionCd;
        copy.additionalTax = this.additionalTax;
        copy.negativeQohFl = this.negativeQohFl;
        copy.posSkuStatusId = this.posSkuStatusId;
        copy.vendorItemSeqNo = this.vendorItemSeqNo;
        copy.replacementCostAmt = this.replacementCostAmt;
        copy.firstPrice = this.firstPrice;
        copy.firstCost = this.firstCost;
        copy.firstSoldDt = this.firstSoldDt;
        copy.stockingDt = this.stockingDt;
        copy.classCode = this.classCode;
        copy.negativeQtySiteNo = this.negativeQtySiteNo;
        copy.purchaseVolumeId = this.purchaseVolumeId;
        copy.reorderSiteNo = this.reorderSiteNo;
        copy.productStartDt = this.productStartDt;
        copy.productEndDt = this.productEndDt;
        copy.lastReplenishmentDt = this.lastReplenishmentDt;
        copy.replicatePricesFl = this.replicatePricesFl;
        copy.lastPricechgTime = this.lastPricechgTime;
        copy.avgDlySales = this.avgDlySales;
        	
        return copy;
    }

    /**
     * <P>Used to copy a InvbysitFullEntity including its identifier.</P>
     * 
     */
    public InvbysitFullEntity copyDataAndId() {
        final InvbysitFullEntity copy = new InvbysitFullEntity();
        	
        copy.getIdentifier().setSkuNo(this.getIdentifier().getSkuNo());
        copy.getIdentifier().setSiteNo(this.getIdentifier().getSiteNo());
        	
        copy.promoKeyNo = this.promoKeyNo;
        copy.location1 = this.location1;
        copy.location2 = this.location2;
        copy.minLevel = this.minLevel;
        copy.maxLevel = this.maxLevel;
        copy.reorderPt = this.reorderPt;
        copy.reorderTo = this.reorderTo;
        copy.priceType = this.priceType;
        copy.unitPrice1 = this.unitPrice1;
        copy.unitPrice2 = this.unitPrice2;
        copy.unitPrice3 = this.unitPrice3;
        copy.unitPrice4 = this.unitPrice4;
        copy.unitPrice5 = this.unitPrice5;
        copy.unitPrice6 = this.unitPrice6;
        copy.unitPrice7 = this.unitPrice7;
        copy.unitPrice8 = this.unitPrice8;
        copy.unitPrice9 = this.unitPrice9;
        copy.unitPrice10 = this.unitPrice10;
        copy.priceQty1 = this.priceQty1;
        copy.priceQty2 = this.priceQty2;
        copy.priceQty3 = this.priceQty3;
        copy.priceQty4 = this.priceQty4;
        copy.priceQty5 = this.priceQty5;
        copy.priceQty6 = this.priceQty6;
        copy.priceQty7 = this.priceQty7;
        copy.priceQty8 = this.priceQty8;
        copy.priceQty9 = this.priceQty9;
        copy.priceQty10 = this.priceQty10;
        copy.price1 = this.price1;
        copy.price2 = this.price2;
        copy.price3 = this.price3;
        copy.price4 = this.price4;
        copy.price5 = this.price5;
        copy.price6 = this.price6;
        copy.price7 = this.price7;
        copy.price8 = this.price8;
        copy.price9 = this.price9;
        copy.price10 = this.price10;
        copy.epoPct = this.epoPct;
        copy.msrpUnitPrice = this.msrpUnitPrice;
        copy.msrpPrice = this.msrpPrice;
        copy.msrpQty = this.msrpQty;
        copy.pkgPriceQty1 = this.pkgPriceQty1;
        copy.pkgPriceQty2 = this.pkgPriceQty2;
        copy.pkgPriceQty3 = this.pkgPriceQty3;
        copy.pkgPrice1 = this.pkgPrice1;
        copy.pkgPrice2 = this.pkgPrice2;
        copy.pkgPrice3 = this.pkgPrice3;
        copy.preMarkdownPrice = this.preMarkdownPrice;
        copy.noOfFacings = this.noOfFacings;
        copy.shelfVelocity = this.shelfVelocity;
        copy.commissionCd = this.commissionCd;
        copy.invCost = this.invCost;
        copy.standardCost = this.standardCost;
        copy.avgAge = this.avgAge;
        copy.firstRcvdDt = this.firstRcvdDt;
        copy.lastRcvdDt = this.lastRcvdDt;
        copy.qtyLastRcvd = this.qtyLastRcvd;
        copy.lastOrdDt = this.lastOrdDt;
        copy.lastSoldDt = this.lastSoldDt;
        copy.lastSoldPreviousDt = this.lastSoldPreviousDt;
        copy.qtyOnHand = this.qtyOnHand;
        copy.qtyRsvd = this.qtyRsvd;
        copy.qtyOnOrd = this.qtyOnOrd;
        copy.qtyOnBackord = this.qtyOnBackord;
        copy.qtyInTransit = this.qtyInTransit;
        copy.qtyRtv = this.qtyRtv;
        copy.expRcvdDt = this.expRcvdDt;
        copy.expBoDt = this.expBoDt;
        copy.qtyCounted = this.qtyCounted;
        copy.maxOrderQty = this.maxOrderQty;
        copy.mixMatchNo = this.mixMatchNo;
        copy.bottleLinkNo = this.bottleLinkNo;
        copy.lastPrice = this.lastPrice;
        copy.lastPchgDt = this.lastPchgDt;
        copy.downloadFl = this.downloadFl;
        copy.allowPchgFl = this.allowPchgFl;
        copy.cosPct = this.cosPct;
        copy.supersededFl = this.supersededFl;
        copy.priorReorderPt = this.priorReorderPt;
        copy.priorReorderTo = this.priorReorderTo;
        copy.warrantyInvCost = this.warrantyInvCost;
        copy.warrantyQtyOnHand = this.warrantyQtyOnHand;
        copy.weightedLeadTime = this.weightedLeadTime;
        copy.coreSellPrice = this.coreSellPrice;
        copy.moDemandForecast = this.moDemandForecast;
        copy.coreInvCost = this.coreInvCost;
        copy.coreQtyOnHand = this.coreQtyOnHand;
        copy.skuStatusId = this.skuStatusId;
        copy.nonDiscountFl = this.nonDiscountFl;
        copy.restrictSaleId = this.restrictSaleId;
        copy.labelCd = this.labelCd;
        copy.lastPriceIncDt = this.lastPriceIncDt;
        copy.lastPriceDecDt = this.lastPriceDecDt;
        copy.lastCycleDt = this.lastCycleDt;
        copy.lastPriceVerDt = this.lastPriceVerDt;
        copy.lastChangeDt = this.lastChangeDt;
        copy.newSkuStatusId = this.newSkuStatusId;
        copy.itemizerStatusId = this.itemizerStatusId;
        copy.scanDeptNo = this.scanDeptNo;
        copy.scanSubdeptNo = this.scanSubdeptNo;
        copy.downloadNToYFl = this.downloadNToYFl;
        copy.labelQty1 = this.labelQty1;
        copy.labelQty2 = this.labelQty2;
        copy.warehouseInnerPack = this.warehouseInnerPack;
        copy.lastXferDt = this.lastXferDt;
        copy.priceOptionCd = this.priceOptionCd;
        copy.priceAttributeCd = this.priceAttributeCd;
        copy.promoRecordNo = this.promoRecordNo;
        copy.lastVendorCost = this.lastVendorCost;
        copy.avgMovement = this.avgMovement;
        copy.haloGm = this.haloGm;
        copy.laloGm = this.laloGm;
        copy.replenishmentFl = this.replenishmentFl;
        copy.velocityCd = this.velocityCd;
        copy.velocityLockedFl = this.velocityLockedFl;
        copy.velocityChangeDt = this.velocityChangeDt;
        copy.reorderLockedFl = this.reorderLockedFl;
        copy.reorderNowFl = this.reorderNowFl;
        copy.profileData = this.profileData;
        copy.replicationNo = this.replicationNo;
        copy.operationType = this.operationType;
        copy.dateChanged = this.dateChanged;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.reorderpointSourceCd = this.reorderpointSourceCd;
        copy.defaultDcSkuLevel = this.defaultDcSkuLevel;
        copy.priceCalcId = this.priceCalcId;
        copy.profileDataFl = this.profileDataFl;
        copy.discontinuedStFl = this.discontinuedStFl;
        copy.discontinuedDt = this.discontinuedDt;
        copy.discontinuedBy = this.discontinuedBy;
        copy.tareTableNo = this.tareTableNo;
        copy.promoCommissionCd = this.promoCommissionCd;
        copy.additionalTax = this.additionalTax;
        copy.negativeQohFl = this.negativeQohFl;
        copy.posSkuStatusId = this.posSkuStatusId;
        copy.vendorItemSeqNo = this.vendorItemSeqNo;
        copy.replacementCostAmt = this.replacementCostAmt;
        copy.firstPrice = this.firstPrice;
        copy.firstCost = this.firstCost;
        copy.firstSoldDt = this.firstSoldDt;
        copy.stockingDt = this.stockingDt;
        copy.classCode = this.classCode;
        copy.negativeQtySiteNo = this.negativeQtySiteNo;
        copy.purchaseVolumeId = this.purchaseVolumeId;
        copy.reorderSiteNo = this.reorderSiteNo;
        copy.productStartDt = this.productStartDt;
        copy.productEndDt = this.productEndDt;
        copy.lastReplenishmentDt = this.lastReplenishmentDt;
        copy.replicatePricesFl = this.replicatePricesFl;
        copy.lastPricechgTime = this.lastPricechgTime;
        copy.avgDlySales = this.avgDlySales;
        	
        return copy;
    }

    /**
     * <P>Used to copy a InvbysitFullEntity including data and GENERATED identifiers only.</P>
     * 
     */
    public InvbysitFullEntity copyDataAndIdNonGenerated() {
        final InvbysitFullEntity copy = new InvbysitFullEntity();
        	
        copy.getIdentifier().setSkuNo(this.getIdentifier().getSkuNo());
        copy.getIdentifier().setSiteNo(this.getIdentifier().getSiteNo());
        	
        copy.promoKeyNo = this.promoKeyNo;
        copy.location1 = this.location1;
        copy.location2 = this.location2;
        copy.minLevel = this.minLevel;
        copy.maxLevel = this.maxLevel;
        copy.reorderPt = this.reorderPt;
        copy.reorderTo = this.reorderTo;
        copy.priceType = this.priceType;
        copy.unitPrice1 = this.unitPrice1;
        copy.unitPrice2 = this.unitPrice2;
        copy.unitPrice3 = this.unitPrice3;
        copy.unitPrice4 = this.unitPrice4;
        copy.unitPrice5 = this.unitPrice5;
        copy.unitPrice6 = this.unitPrice6;
        copy.unitPrice7 = this.unitPrice7;
        copy.unitPrice8 = this.unitPrice8;
        copy.unitPrice9 = this.unitPrice9;
        copy.unitPrice10 = this.unitPrice10;
        copy.priceQty1 = this.priceQty1;
        copy.priceQty2 = this.priceQty2;
        copy.priceQty3 = this.priceQty3;
        copy.priceQty4 = this.priceQty4;
        copy.priceQty5 = this.priceQty5;
        copy.priceQty6 = this.priceQty6;
        copy.priceQty7 = this.priceQty7;
        copy.priceQty8 = this.priceQty8;
        copy.priceQty9 = this.priceQty9;
        copy.priceQty10 = this.priceQty10;
        copy.price1 = this.price1;
        copy.price2 = this.price2;
        copy.price3 = this.price3;
        copy.price4 = this.price4;
        copy.price5 = this.price5;
        copy.price6 = this.price6;
        copy.price7 = this.price7;
        copy.price8 = this.price8;
        copy.price9 = this.price9;
        copy.price10 = this.price10;
        copy.epoPct = this.epoPct;
        copy.msrpUnitPrice = this.msrpUnitPrice;
        copy.msrpPrice = this.msrpPrice;
        copy.msrpQty = this.msrpQty;
        copy.pkgPriceQty1 = this.pkgPriceQty1;
        copy.pkgPriceQty2 = this.pkgPriceQty2;
        copy.pkgPriceQty3 = this.pkgPriceQty3;
        copy.pkgPrice1 = this.pkgPrice1;
        copy.pkgPrice2 = this.pkgPrice2;
        copy.pkgPrice3 = this.pkgPrice3;
        copy.preMarkdownPrice = this.preMarkdownPrice;
        copy.noOfFacings = this.noOfFacings;
        copy.shelfVelocity = this.shelfVelocity;
        copy.commissionCd = this.commissionCd;
        copy.invCost = this.invCost;
        copy.standardCost = this.standardCost;
        copy.avgAge = this.avgAge;
        copy.firstRcvdDt = this.firstRcvdDt;
        copy.lastRcvdDt = this.lastRcvdDt;
        copy.qtyLastRcvd = this.qtyLastRcvd;
        copy.lastOrdDt = this.lastOrdDt;
        copy.lastSoldDt = this.lastSoldDt;
        copy.lastSoldPreviousDt = this.lastSoldPreviousDt;
        copy.qtyOnHand = this.qtyOnHand;
        copy.qtyRsvd = this.qtyRsvd;
        copy.qtyOnOrd = this.qtyOnOrd;
        copy.qtyOnBackord = this.qtyOnBackord;
        copy.qtyInTransit = this.qtyInTransit;
        copy.qtyRtv = this.qtyRtv;
        copy.expRcvdDt = this.expRcvdDt;
        copy.expBoDt = this.expBoDt;
        copy.qtyCounted = this.qtyCounted;
        copy.maxOrderQty = this.maxOrderQty;
        copy.mixMatchNo = this.mixMatchNo;
        copy.bottleLinkNo = this.bottleLinkNo;
        copy.lastPrice = this.lastPrice;
        copy.lastPchgDt = this.lastPchgDt;
        copy.downloadFl = this.downloadFl;
        copy.allowPchgFl = this.allowPchgFl;
        copy.cosPct = this.cosPct;
        copy.supersededFl = this.supersededFl;
        copy.priorReorderPt = this.priorReorderPt;
        copy.priorReorderTo = this.priorReorderTo;
        copy.warrantyInvCost = this.warrantyInvCost;
        copy.warrantyQtyOnHand = this.warrantyQtyOnHand;
        copy.weightedLeadTime = this.weightedLeadTime;
        copy.coreSellPrice = this.coreSellPrice;
        copy.moDemandForecast = this.moDemandForecast;
        copy.coreInvCost = this.coreInvCost;
        copy.coreQtyOnHand = this.coreQtyOnHand;
        copy.skuStatusId = this.skuStatusId;
        copy.nonDiscountFl = this.nonDiscountFl;
        copy.restrictSaleId = this.restrictSaleId;
        copy.labelCd = this.labelCd;
        copy.lastPriceIncDt = this.lastPriceIncDt;
        copy.lastPriceDecDt = this.lastPriceDecDt;
        copy.lastCycleDt = this.lastCycleDt;
        copy.lastPriceVerDt = this.lastPriceVerDt;
        copy.lastChangeDt = this.lastChangeDt;
        copy.newSkuStatusId = this.newSkuStatusId;
        copy.itemizerStatusId = this.itemizerStatusId;
        copy.scanDeptNo = this.scanDeptNo;
        copy.scanSubdeptNo = this.scanSubdeptNo;
        copy.downloadNToYFl = this.downloadNToYFl;
        copy.labelQty1 = this.labelQty1;
        copy.labelQty2 = this.labelQty2;
        copy.warehouseInnerPack = this.warehouseInnerPack;
        copy.lastXferDt = this.lastXferDt;
        copy.priceOptionCd = this.priceOptionCd;
        copy.priceAttributeCd = this.priceAttributeCd;
        copy.promoRecordNo = this.promoRecordNo;
        copy.lastVendorCost = this.lastVendorCost;
        copy.avgMovement = this.avgMovement;
        copy.haloGm = this.haloGm;
        copy.laloGm = this.laloGm;
        copy.replenishmentFl = this.replenishmentFl;
        copy.velocityCd = this.velocityCd;
        copy.velocityLockedFl = this.velocityLockedFl;
        copy.velocityChangeDt = this.velocityChangeDt;
        copy.reorderLockedFl = this.reorderLockedFl;
        copy.reorderNowFl = this.reorderNowFl;
        copy.profileData = this.profileData;
        copy.replicationNo = this.replicationNo;
        copy.operationType = this.operationType;
        copy.dateChanged = this.dateChanged;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.reorderpointSourceCd = this.reorderpointSourceCd;
        copy.defaultDcSkuLevel = this.defaultDcSkuLevel;
        copy.priceCalcId = this.priceCalcId;
        copy.profileDataFl = this.profileDataFl;
        copy.discontinuedStFl = this.discontinuedStFl;
        copy.discontinuedDt = this.discontinuedDt;
        copy.discontinuedBy = this.discontinuedBy;
        copy.tareTableNo = this.tareTableNo;
        copy.promoCommissionCd = this.promoCommissionCd;
        copy.additionalTax = this.additionalTax;
        copy.negativeQohFl = this.negativeQohFl;
        copy.posSkuStatusId = this.posSkuStatusId;
        copy.vendorItemSeqNo = this.vendorItemSeqNo;
        copy.replacementCostAmt = this.replacementCostAmt;
        copy.firstPrice = this.firstPrice;
        copy.firstCost = this.firstCost;
        copy.firstSoldDt = this.firstSoldDt;
        copy.stockingDt = this.stockingDt;
        copy.classCode = this.classCode;
        copy.negativeQtySiteNo = this.negativeQtySiteNo;
        copy.purchaseVolumeId = this.purchaseVolumeId;
        copy.reorderSiteNo = this.reorderSiteNo;
        copy.productStartDt = this.productStartDt;
        copy.productEndDt = this.productEndDt;
        copy.lastReplenishmentDt = this.lastReplenishmentDt;
        copy.replicatePricesFl = this.replicatePricesFl;
        copy.lastPricechgTime = this.lastPricechgTime;
        copy.avgDlySales = this.avgDlySales;
        	
        return copy;
    }

    /**
     * @hide<P><I>Not for public use.</I></P>This postLoad method is used to fix BigDecimal values being loaded from the JDBC driver.  Oracle has a known jdbc bug where it truncates the scale of BigDecimal.  This method restores the scale after load so that BigDecimals comparators will work again.
     * 
     */
    private void _setScales() {
        if (this.minLevel!= null) {
            this.minLevel = this.minLevel.setScale(2);
        }
        if (this.maxLevel!= null) {
            this.maxLevel = this.maxLevel.setScale(2);
        }
        if (this.reorderPt!= null) {
            this.reorderPt = this.reorderPt.setScale(2);
        }
        if (this.reorderTo!= null) {
            this.reorderTo = this.reorderTo.setScale(2);
        }
        if (this.unitPrice1 != null) {
            this.unitPrice1 = this.unitPrice1 .setScale(2);
        }
        if (this.unitPrice2 != null) {
            this.unitPrice2 = this.unitPrice2 .setScale(2);
        }
        if (this.unitPrice3 != null) {
            this.unitPrice3 = this.unitPrice3 .setScale(2);
        }
        if (this.unitPrice4 != null) {
            this.unitPrice4 = this.unitPrice4 .setScale(2);
        }
        if (this.unitPrice5 != null) {
            this.unitPrice5 = this.unitPrice5 .setScale(2);
        }
        if (this.unitPrice6 != null) {
            this.unitPrice6 = this.unitPrice6 .setScale(2);
        }
        if (this.unitPrice7 != null) {
            this.unitPrice7 = this.unitPrice7 .setScale(2);
        }
        if (this.unitPrice8 != null) {
            this.unitPrice8 = this.unitPrice8 .setScale(2);
        }
        if (this.unitPrice9 != null) {
            this.unitPrice9 = this.unitPrice9 .setScale(2);
        }
        if (this.unitPrice10 != null) {
            this.unitPrice10 = this.unitPrice10 .setScale(2);
        }
        if (this.price1 != null) {
            this.price1 = this.price1 .setScale(2);
        }
        if (this.price2 != null) {
            this.price2 = this.price2 .setScale(2);
        }
        if (this.price3 != null) {
            this.price3 = this.price3 .setScale(2);
        }
        if (this.price4 != null) {
            this.price4 = this.price4 .setScale(2);
        }
        if (this.price5 != null) {
            this.price5 = this.price5 .setScale(2);
        }
        if (this.price6 != null) {
            this.price6 = this.price6 .setScale(2);
        }
        if (this.price7 != null) {
            this.price7 = this.price7 .setScale(2);
        }
        if (this.price8 != null) {
            this.price8 = this.price8 .setScale(2);
        }
        if (this.price9 != null) {
            this.price9 = this.price9 .setScale(2);
        }
        if (this.price10 != null) {
            this.price10 = this.price10 .setScale(2);
        }
        if (this.epoPct!= null) {
            this.epoPct = this.epoPct.setScale(2);
        }
        if (this.msrpUnitPrice!= null) {
            this.msrpUnitPrice = this.msrpUnitPrice.setScale(2);
        }
        if (this.msrpPrice!= null) {
            this.msrpPrice = this.msrpPrice.setScale(2);
        }
        if (this.pkgPrice1 != null) {
            this.pkgPrice1 = this.pkgPrice1 .setScale(2);
        }
        if (this.pkgPrice2 != null) {
            this.pkgPrice2 = this.pkgPrice2 .setScale(2);
        }
        if (this.pkgPrice3 != null) {
            this.pkgPrice3 = this.pkgPrice3 .setScale(2);
        }
        if (this.preMarkdownPrice!= null) {
            this.preMarkdownPrice = this.preMarkdownPrice.setScale(2);
        }
        if (this.invCost!= null) {
            this.invCost = this.invCost.setScale(2);
        }
        if (this.standardCost!= null) {
            this.standardCost = this.standardCost.setScale(4);
        }
        if (this.avgAge!= null) {
            this.avgAge = this.avgAge.setScale(1);
        }
        if (this.qtyLastRcvd!= null) {
            this.qtyLastRcvd = this.qtyLastRcvd.setScale(2);
        }
        if (this.qtyOnHand!= null) {
            this.qtyOnHand = this.qtyOnHand.setScale(2);
        }
        if (this.qtyRsvd!= null) {
            this.qtyRsvd = this.qtyRsvd.setScale(2);
        }
        if (this.qtyOnOrd!= null) {
            this.qtyOnOrd = this.qtyOnOrd.setScale(2);
        }
        if (this.qtyOnBackord!= null) {
            this.qtyOnBackord = this.qtyOnBackord.setScale(2);
        }
        if (this.qtyInTransit!= null) {
            this.qtyInTransit = this.qtyInTransit.setScale(2);
        }
        if (this.qtyRtv!= null) {
            this.qtyRtv = this.qtyRtv.setScale(2);
        }
        if (this.qtyCounted!= null) {
            this.qtyCounted = this.qtyCounted.setScale(2);
        }
        if (this.maxOrderQty!= null) {
            this.maxOrderQty = this.maxOrderQty.setScale(2);
        }
        if (this.lastPrice!= null) {
            this.lastPrice = this.lastPrice.setScale(2);
        }
        if (this.cosPct!= null) {
            this.cosPct = this.cosPct.setScale(2);
        }
        if (this.priorReorderPt!= null) {
            this.priorReorderPt = this.priorReorderPt.setScale(2);
        }
        if (this.priorReorderTo!= null) {
            this.priorReorderTo = this.priorReorderTo.setScale(2);
        }
        if (this.warrantyInvCost!= null) {
            this.warrantyInvCost = this.warrantyInvCost.setScale(2);
        }
        if (this.weightedLeadTime!= null) {
            this.weightedLeadTime = this.weightedLeadTime.setScale(2);
        }
        if (this.coreSellPrice!= null) {
            this.coreSellPrice = this.coreSellPrice.setScale(2);
        }
        if (this.moDemandForecast!= null) {
            this.moDemandForecast = this.moDemandForecast.setScale(10);
        }
        if (this.coreInvCost!= null) {
            this.coreInvCost = this.coreInvCost.setScale(2);
        }
        if (this.warehouseInnerPack!= null) {
            this.warehouseInnerPack = this.warehouseInnerPack.setScale(2);
        }
        if (this.lastVendorCost!= null) {
            this.lastVendorCost = this.lastVendorCost.setScale(4);
        }
        if (this.avgMovement!= null) {
            this.avgMovement = this.avgMovement.setScale(4);
        }
        if (this.haloGm!= null) {
            this.haloGm = this.haloGm.setScale(2);
        }
        if (this.laloGm!= null) {
            this.laloGm = this.laloGm.setScale(2);
        }
        if (this.additionalTax!= null) {
            this.additionalTax = this.additionalTax.setScale(2);
        }
        if (this.replacementCostAmt!= null) {
            this.replacementCostAmt = this.replacementCostAmt.setScale(4);
        }
        if (this.firstPrice!= null) {
            this.firstPrice = this.firstPrice.setScale(2);
        }
        if (this.firstCost!= null) {
            this.firstCost = this.firstCost.setScale(4);
        }
        if (this.avgDlySales!= null) {
            this.avgDlySales = this.avgDlySales.setScale(4);
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
    }

    /**
     * @hide<P><I>Not for public use.</I></P>
     * 
     */
    @PostLoad
    private void _postLoad() {
        _setScales();
    }

    public static InvbysitFullDTO toDTO(InvbysitFullEntity entity) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return InvbysitFullEntity.toDTO(entity, map);
    }

    public static InvbysitFullDTO toDTO(InvbysitFullEntity entity, Map<Object, Object> map) {
        try {
            if (entity == null) {
                return null;
            }
            	
            if (map == null) {
                map = new HashMap<Object, Object>();
            } else {
                if (map.get(entity)!= null) {
                    return ((InvbysitFullDTO) map.get(entity));
                }
            }
            	
            entity.toDTO(map);
            	
            return ((InvbysitFullDTO) map.get(entity));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public static InvbysitFullEntity toEntity(InvbysitFullDTO dto) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return InvbysitFullEntity.toEntity(dto, map);
    }

    public static InvbysitFullEntity toEntity(InvbysitFullDTO dto, Map<Object, Object> map) {
        try {
            if (dto == null) {
                return null;
            }
            	
            if ((map!= null)&&(map.get(dto)!= null)) {
                return ((InvbysitFullEntity) map.get(dto));
            }
            	
            return ((InvbysitFullEntity) DtoToEntity.toEntity(InvbysitFullEntity.class, ((DataTransferObject) dto)));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public void assignAllChildren(InvbysitFullDTO dto, Map<Object, Object> map) {
        toSimpleEntity(dto, map);
    }

    public void assignAllChildren(Map<Object, Object> map, Stack<Object> stack) {
        toSimpleDto(map);
    }

    public void assignAllParents(Map<Object, Object> map) {
        InvbysitFullDTO dto = toSimpleDto(map);
        	
        InvtoryFullDTO invtoryDto = ((InvtoryFullDTO) map.get(this.invtory));
        if (invtoryDto!= null) {
            dto.setInvtory(invtoryDto);
        } else {
            if (this.invtory!= null) {
                dto.setInvtory(this.invtory.toDTO(map));
            }
        }
        	
    }

    protected InvbysitFullDTO toSimpleDto(Map<Object, Object> map) {
        if (map.get(this)!= null) {
            return ((InvbysitFullDTO) map.get(this));
        }
        try {
            InvbysitFullDTO dto = new InvbysitFullDTO();
            dto.setIdentifier(new InvbysitDTOId(this.getSkuNo(), this.getSiteNo()));
            	
            dto.setPromoKeyNo(this.getPromoKeyNo());
            dto.setLocation1(this.getLocation1());
            dto.setLocation2(this.getLocation2());
            dto.setMinLevel(this.getMinLevel());
            dto.setMaxLevel(this.getMaxLevel());
            dto.setReorderPt(this.getReorderPt());
            dto.setReorderTo(this.getReorderTo());
            dto.setPriceType(this.getPriceType());
            dto.setUnitPrice1(this.getUnitPrice1());
            dto.setUnitPrice2(this.getUnitPrice2());
            dto.setUnitPrice3(this.getUnitPrice3());
            dto.setUnitPrice4(this.getUnitPrice4());
            dto.setUnitPrice5(this.getUnitPrice5());
            dto.setUnitPrice6(this.getUnitPrice6());
            dto.setUnitPrice7(this.getUnitPrice7());
            dto.setUnitPrice8(this.getUnitPrice8());
            dto.setUnitPrice9(this.getUnitPrice9());
            dto.setUnitPrice10(this.getUnitPrice10());
            dto.setPriceQty1(this.getPriceQty1());
            dto.setPriceQty2(this.getPriceQty2());
            dto.setPriceQty3(this.getPriceQty3());
            dto.setPriceQty4(this.getPriceQty4());
            dto.setPriceQty5(this.getPriceQty5());
            dto.setPriceQty6(this.getPriceQty6());
            dto.setPriceQty7(this.getPriceQty7());
            dto.setPriceQty8(this.getPriceQty8());
            dto.setPriceQty9(this.getPriceQty9());
            dto.setPriceQty10(this.getPriceQty10());
            dto.setPrice1(this.getPrice1());
            dto.setPrice2(this.getPrice2());
            dto.setPrice3(this.getPrice3());
            dto.setPrice4(this.getPrice4());
            dto.setPrice5(this.getPrice5());
            dto.setPrice6(this.getPrice6());
            dto.setPrice7(this.getPrice7());
            dto.setPrice8(this.getPrice8());
            dto.setPrice9(this.getPrice9());
            dto.setPrice10(this.getPrice10());
            dto.setEpoPct(this.getEpoPct());
            dto.setMsrpUnitPrice(this.getMsrpUnitPrice());
            dto.setMsrpPrice(this.getMsrpPrice());
            dto.setMsrpQty(this.getMsrpQty());
            dto.setPkgPriceQty1(this.getPkgPriceQty1());
            dto.setPkgPriceQty2(this.getPkgPriceQty2());
            dto.setPkgPriceQty3(this.getPkgPriceQty3());
            dto.setPkgPrice1(this.getPkgPrice1());
            dto.setPkgPrice2(this.getPkgPrice2());
            dto.setPkgPrice3(this.getPkgPrice3());
            dto.setPreMarkdownPrice(this.getPreMarkdownPrice());
            dto.setNoOfFacings(this.getNoOfFacings());
            dto.setShelfVelocity(this.getShelfVelocity());
            dto.setCommissionCd(this.getCommissionCd());
            dto.setInvCost(this.getInvCost());
            dto.setStandardCost(this.getStandardCost());
            dto.setAvgAge(this.getAvgAge());
            dto.setFirstRcvdDt(this.getFirstRcvdDt());
            dto.setLastRcvdDt(this.getLastRcvdDt());
            dto.setQtyLastRcvd(this.getQtyLastRcvd());
            dto.setLastOrdDt(this.getLastOrdDt());
            dto.setLastSoldDt(this.getLastSoldDt());
            dto.setLastSoldPreviousDt(this.getLastSoldPreviousDt());
            dto.setQtyOnHand(this.getQtyOnHand());
            dto.setQtyRsvd(this.getQtyRsvd());
            dto.setQtyOnOrd(this.getQtyOnOrd());
            dto.setQtyOnBackord(this.getQtyOnBackord());
            dto.setQtyInTransit(this.getQtyInTransit());
            dto.setQtyRtv(this.getQtyRtv());
            dto.setExpRcvdDt(this.getExpRcvdDt());
            dto.setExpBoDt(this.getExpBoDt());
            dto.setQtyCounted(this.getQtyCounted());
            dto.setMaxOrderQty(this.getMaxOrderQty());
            dto.setMixMatchNo(this.getMixMatchNo());
            dto.setBottleLinkNo(this.getBottleLinkNo());
            dto.setLastPrice(this.getLastPrice());
            dto.setLastPchgDt(this.getLastPchgDt());
            dto.setDownloadFl(this.getDownloadFl());
            dto.setAllowPchgFl(this.getAllowPchgFl());
            dto.setCosPct(this.getCosPct());
            dto.setSupersededFl(this.getSupersededFl());
            dto.setPriorReorderPt(this.getPriorReorderPt());
            dto.setPriorReorderTo(this.getPriorReorderTo());
            dto.setWarrantyInvCost(this.getWarrantyInvCost());
            dto.setWarrantyQtyOnHand(this.getWarrantyQtyOnHand());
            dto.setWeightedLeadTime(this.getWeightedLeadTime());
            dto.setCoreSellPrice(this.getCoreSellPrice());
            dto.setMoDemandForecast(this.getMoDemandForecast());
            dto.setCoreInvCost(this.getCoreInvCost());
            dto.setCoreQtyOnHand(this.getCoreQtyOnHand());
            dto.setSkuStatusId(this.getSkuStatusId());
            dto.setNonDiscountFl(this.getNonDiscountFl());
            dto.setRestrictSaleId(this.getRestrictSaleId());
            dto.setLabelCd(this.getLabelCd());
            dto.setLastPriceIncDt(this.getLastPriceIncDt());
            dto.setLastPriceDecDt(this.getLastPriceDecDt());
            dto.setLastCycleDt(this.getLastCycleDt());
            dto.setLastPriceVerDt(this.getLastPriceVerDt());
            dto.setLastChangeDt(this.getLastChangeDt());
            dto.setNewSkuStatusId(this.getNewSkuStatusId());
            dto.setItemizerStatusId(this.getItemizerStatusId());
            dto.setScanDeptNo(this.getScanDeptNo());
            dto.setScanSubdeptNo(this.getScanSubdeptNo());
            dto.setDownloadNToYFl(this.getDownloadNToYFl());
            dto.setLabelQty1(this.getLabelQty1());
            dto.setLabelQty2(this.getLabelQty2());
            dto.setWarehouseInnerPack(this.getWarehouseInnerPack());
            dto.setLastXferDt(this.getLastXferDt());
            dto.setPriceOptionCd(this.getPriceOptionCd());
            dto.setPriceAttributeCd(this.getPriceAttributeCd());
            dto.setPromoRecordNo(this.getPromoRecordNo());
            dto.setLastVendorCost(this.getLastVendorCost());
            dto.setAvgMovement(this.getAvgMovement());
            dto.setHaloGm(this.getHaloGm());
            dto.setLaloGm(this.getLaloGm());
            dto.setReplenishmentFl(this.getReplenishmentFl());
            dto.setVelocityCd(this.getVelocityCd());
            dto.setVelocityLockedFl(this.getVelocityLockedFl());
            dto.setVelocityChangeDt(this.getVelocityChangeDt());
            dto.setReorderLockedFl(this.getReorderLockedFl());
            dto.setReorderNowFl(this.getReorderNowFl());
            dto.setProfileData(this.getProfileData());
            dto.setReplicationNo(this.getReplicationNo());
            dto.setOperationType(this.getOperationType());
            dto.setDateChanged(this.getDateChanged());
            dto.setRegisterReplicationNo(this.getRegisterReplicationNo());
            dto.setReorderpointSourceCd(this.getReorderpointSourceCd());
            dto.setDefaultDcSkuLevel(this.getDefaultDcSkuLevel());
            dto.setPriceCalcId(this.getPriceCalcId());
            dto.setProfileDataFl(this.getProfileDataFl());
            dto.setDiscontinuedStFl(this.getDiscontinuedStFl());
            dto.setDiscontinuedDt(this.getDiscontinuedDt());
            dto.setDiscontinuedBy(this.getDiscontinuedBy());
            dto.setTareTableNo(this.getTareTableNo());
            dto.setPromoCommissionCd(this.getPromoCommissionCd());
            dto.setAdditionalTax(this.getAdditionalTax());
            dto.setNegativeQohFl(this.getNegativeQohFl());
            dto.setPosSkuStatusId(this.getPosSkuStatusId());
            dto.setVendorItemSeqNo(this.getVendorItemSeqNo());
            dto.setReplacementCostAmt(this.getReplacementCostAmt());
            dto.setFirstPrice(this.getFirstPrice());
            dto.setFirstCost(this.getFirstCost());
            dto.setFirstSoldDt(this.getFirstSoldDt());
            dto.setStockingDt(this.getStockingDt());
            dto.setClassCode(this.getClassCode());
            dto.setNegativeQtySiteNo(this.getNegativeQtySiteNo());
            dto.setPurchaseVolumeId(this.getPurchaseVolumeId());
            dto.setReorderSiteNo(this.getReorderSiteNo());
            dto.setProductStartDt(this.getProductStartDt());
            dto.setProductEndDt(this.getProductEndDt());
            dto.setLastReplenishmentDt(this.getLastReplenishmentDt());
            dto.setReplicatePricesFl(this.getReplicatePricesFl());
            dto.setLastPricechgTime(this.getLastPricechgTime());
            dto.setAvgDlySales(this.getAvgDlySales());
            	
            map.put(this, dto);
            	
            return dto;
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public InvbysitFullEntity toSimpleEntity(DataTransferObject dto, Map<Object, Object> map) {
        if (map.get(dto)!= null) {
            return ((InvbysitFullEntity) map.get(dto));
        }
        try {
            InvbysitFullDTO oldDto = ((InvbysitFullDTO) dto);
            InvbysitFullEntity entity = new InvbysitFullEntity();
            entity.setIdentifier(new InvbysitEntityId(oldDto.getSkuNo(), oldDto.getSiteNo()));
            	
            entity.setPromoKeyNo(oldDto.getPromoKeyNo());
            entity.setLocation1(oldDto.getLocation1());
            entity.setLocation2(oldDto.getLocation2());
            entity.setMinLevel(oldDto.getMinLevel());
            entity.setMaxLevel(oldDto.getMaxLevel());
            entity.setReorderPt(oldDto.getReorderPt());
            entity.setReorderTo(oldDto.getReorderTo());
            entity.setPriceType(oldDto.getPriceType());
            entity.setUnitPrice1(oldDto.getUnitPrice1());
            entity.setUnitPrice2(oldDto.getUnitPrice2());
            entity.setUnitPrice3(oldDto.getUnitPrice3());
            entity.setUnitPrice4(oldDto.getUnitPrice4());
            entity.setUnitPrice5(oldDto.getUnitPrice5());
            entity.setUnitPrice6(oldDto.getUnitPrice6());
            entity.setUnitPrice7(oldDto.getUnitPrice7());
            entity.setUnitPrice8(oldDto.getUnitPrice8());
            entity.setUnitPrice9(oldDto.getUnitPrice9());
            entity.setUnitPrice10(oldDto.getUnitPrice10());
            entity.setPriceQty1(oldDto.getPriceQty1());
            entity.setPriceQty2(oldDto.getPriceQty2());
            entity.setPriceQty3(oldDto.getPriceQty3());
            entity.setPriceQty4(oldDto.getPriceQty4());
            entity.setPriceQty5(oldDto.getPriceQty5());
            entity.setPriceQty6(oldDto.getPriceQty6());
            entity.setPriceQty7(oldDto.getPriceQty7());
            entity.setPriceQty8(oldDto.getPriceQty8());
            entity.setPriceQty9(oldDto.getPriceQty9());
            entity.setPriceQty10(oldDto.getPriceQty10());
            entity.setPrice1(oldDto.getPrice1());
            entity.setPrice2(oldDto.getPrice2());
            entity.setPrice3(oldDto.getPrice3());
            entity.setPrice4(oldDto.getPrice4());
            entity.setPrice5(oldDto.getPrice5());
            entity.setPrice6(oldDto.getPrice6());
            entity.setPrice7(oldDto.getPrice7());
            entity.setPrice8(oldDto.getPrice8());
            entity.setPrice9(oldDto.getPrice9());
            entity.setPrice10(oldDto.getPrice10());
            entity.setEpoPct(oldDto.getEpoPct());
            entity.setMsrpUnitPrice(oldDto.getMsrpUnitPrice());
            entity.setMsrpPrice(oldDto.getMsrpPrice());
            entity.setMsrpQty(oldDto.getMsrpQty());
            entity.setPkgPriceQty1(oldDto.getPkgPriceQty1());
            entity.setPkgPriceQty2(oldDto.getPkgPriceQty2());
            entity.setPkgPriceQty3(oldDto.getPkgPriceQty3());
            entity.setPkgPrice1(oldDto.getPkgPrice1());
            entity.setPkgPrice2(oldDto.getPkgPrice2());
            entity.setPkgPrice3(oldDto.getPkgPrice3());
            entity.setPreMarkdownPrice(oldDto.getPreMarkdownPrice());
            entity.setNoOfFacings(oldDto.getNoOfFacings());
            entity.setShelfVelocity(oldDto.getShelfVelocity());
            entity.setCommissionCd(oldDto.getCommissionCd());
            entity.setInvCost(oldDto.getInvCost());
            entity.setStandardCost(oldDto.getStandardCost());
            entity.setAvgAge(oldDto.getAvgAge());
            entity.setFirstRcvdDt(oldDto.getFirstRcvdDt());
            entity.setLastRcvdDt(oldDto.getLastRcvdDt());
            entity.setQtyLastRcvd(oldDto.getQtyLastRcvd());
            entity.setLastOrdDt(oldDto.getLastOrdDt());
            entity.setLastSoldDt(oldDto.getLastSoldDt());
            entity.setLastSoldPreviousDt(oldDto.getLastSoldPreviousDt());
            entity.setQtyOnHand(oldDto.getQtyOnHand());
            entity.setQtyRsvd(oldDto.getQtyRsvd());
            entity.setQtyOnOrd(oldDto.getQtyOnOrd());
            entity.setQtyOnBackord(oldDto.getQtyOnBackord());
            entity.setQtyInTransit(oldDto.getQtyInTransit());
            entity.setQtyRtv(oldDto.getQtyRtv());
            entity.setExpRcvdDt(oldDto.getExpRcvdDt());
            entity.setExpBoDt(oldDto.getExpBoDt());
            entity.setQtyCounted(oldDto.getQtyCounted());
            entity.setMaxOrderQty(oldDto.getMaxOrderQty());
            entity.setMixMatchNo(oldDto.getMixMatchNo());
            entity.setBottleLinkNo(oldDto.getBottleLinkNo());
            entity.setLastPrice(oldDto.getLastPrice());
            entity.setLastPchgDt(oldDto.getLastPchgDt());
            entity.setDownloadFl(oldDto.getDownloadFl());
            entity.setAllowPchgFl(oldDto.getAllowPchgFl());
            entity.setCosPct(oldDto.getCosPct());
            entity.setSupersededFl(oldDto.getSupersededFl());
            entity.setPriorReorderPt(oldDto.getPriorReorderPt());
            entity.setPriorReorderTo(oldDto.getPriorReorderTo());
            entity.setWarrantyInvCost(oldDto.getWarrantyInvCost());
            entity.setWarrantyQtyOnHand(oldDto.getWarrantyQtyOnHand());
            entity.setWeightedLeadTime(oldDto.getWeightedLeadTime());
            entity.setCoreSellPrice(oldDto.getCoreSellPrice());
            entity.setMoDemandForecast(oldDto.getMoDemandForecast());
            entity.setCoreInvCost(oldDto.getCoreInvCost());
            entity.setCoreQtyOnHand(oldDto.getCoreQtyOnHand());
            entity.setSkuStatusId(oldDto.getSkuStatusId());
            entity.setNonDiscountFl(oldDto.getNonDiscountFl());
            entity.setRestrictSaleId(oldDto.getRestrictSaleId());
            entity.setLabelCd(oldDto.getLabelCd());
            entity.setLastPriceIncDt(oldDto.getLastPriceIncDt());
            entity.setLastPriceDecDt(oldDto.getLastPriceDecDt());
            entity.setLastCycleDt(oldDto.getLastCycleDt());
            entity.setLastPriceVerDt(oldDto.getLastPriceVerDt());
            entity.setLastChangeDt(oldDto.getLastChangeDt());
            entity.setNewSkuStatusId(oldDto.getNewSkuStatusId());
            entity.setItemizerStatusId(oldDto.getItemizerStatusId());
            entity.setScanDeptNo(oldDto.getScanDeptNo());
            entity.setScanSubdeptNo(oldDto.getScanSubdeptNo());
            entity.setDownloadNToYFl(oldDto.getDownloadNToYFl());
            entity.setLabelQty1(oldDto.getLabelQty1());
            entity.setLabelQty2(oldDto.getLabelQty2());
            entity.setWarehouseInnerPack(oldDto.getWarehouseInnerPack());
            entity.setLastXferDt(oldDto.getLastXferDt());
            entity.setPriceOptionCd(oldDto.getPriceOptionCd());
            entity.setPriceAttributeCd(oldDto.getPriceAttributeCd());
            entity.setPromoRecordNo(oldDto.getPromoRecordNo());
            entity.setLastVendorCost(oldDto.getLastVendorCost());
            entity.setAvgMovement(oldDto.getAvgMovement());
            entity.setHaloGm(oldDto.getHaloGm());
            entity.setLaloGm(oldDto.getLaloGm());
            entity.setReplenishmentFl(oldDto.getReplenishmentFl());
            entity.setVelocityCd(oldDto.getVelocityCd());
            entity.setVelocityLockedFl(oldDto.getVelocityLockedFl());
            entity.setVelocityChangeDt(oldDto.getVelocityChangeDt());
            entity.setReorderLockedFl(oldDto.getReorderLockedFl());
            entity.setReorderNowFl(oldDto.getReorderNowFl());
            entity.setProfileData(oldDto.getProfileData());
            entity.setReplicationNo(oldDto.getReplicationNo());
            entity.setOperationType(oldDto.getOperationType());
            entity.setDateChanged(oldDto.getDateChanged());
            entity.setRegisterReplicationNo(oldDto.getRegisterReplicationNo());
            entity.setReorderpointSourceCd(oldDto.getReorderpointSourceCd());
            entity.setDefaultDcSkuLevel(oldDto.getDefaultDcSkuLevel());
            entity.setPriceCalcId(oldDto.getPriceCalcId());
            entity.setProfileDataFl(oldDto.getProfileDataFl());
            entity.setDiscontinuedStFl(oldDto.getDiscontinuedStFl());
            entity.setDiscontinuedDt(oldDto.getDiscontinuedDt());
            entity.setDiscontinuedBy(oldDto.getDiscontinuedBy());
            entity.setTareTableNo(oldDto.getTareTableNo());
            entity.setPromoCommissionCd(oldDto.getPromoCommissionCd());
            entity.setAdditionalTax(oldDto.getAdditionalTax());
            entity.setNegativeQohFl(oldDto.getNegativeQohFl());
            entity.setPosSkuStatusId(oldDto.getPosSkuStatusId());
            entity.setVendorItemSeqNo(oldDto.getVendorItemSeqNo());
            entity.setReplacementCostAmt(oldDto.getReplacementCostAmt());
            entity.setFirstPrice(oldDto.getFirstPrice());
            entity.setFirstCost(oldDto.getFirstCost());
            entity.setFirstSoldDt(oldDto.getFirstSoldDt());
            entity.setStockingDt(oldDto.getStockingDt());
            entity.setClassCode(oldDto.getClassCode());
            entity.setNegativeQtySiteNo(oldDto.getNegativeQtySiteNo());
            entity.setPurchaseVolumeId(oldDto.getPurchaseVolumeId());
            entity.setReorderSiteNo(oldDto.getReorderSiteNo());
            entity.setProductStartDt(oldDto.getProductStartDt());
            entity.setProductEndDt(oldDto.getProductEndDt());
            entity.setLastReplenishmentDt(oldDto.getLastReplenishmentDt());
            entity.setReplicatePricesFl(oldDto.getReplicatePricesFl());
            entity.setLastPricechgTime(oldDto.getLastPricechgTime());
            entity.setAvgDlySales(oldDto.getAvgDlySales());
            	
            map.put(dto, entity);
            	
            return entity;
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public List<RNetAbstractEntity> getParents() {
        List<RNetAbstractEntity> list = new ArrayList<>();
        	
        if (this.invtory!= null) {
            list.add(this.invtory);
        }
        	
        return list;
    }

    public List<Set<? extends RNetAbstractEntity>> getChildren() {
        return null;
    }

    public static List<InvbysitFullDTO> toDTOList(Collection<InvbysitFullEntity> entities) {
        if (entities == null) {
            return null;
        }
        	
        List<InvbysitFullDTO> dtos = new ArrayList<InvbysitFullDTO>();
        for (InvbysitFullEntity entity: entities) {
            dtos.add(toDTO(entity));
        }
        	
        return dtos;
    }

    @Deprecated
    public static List<InvbysitFullDTO> toDTO(Collection<InvbysitFullEntity> entities) {
        return toDTOList(entities);
    }

    public static InvbysitDTOId toDTOId(InvbysitEntityId id) {
        if (id == null) {
            return null;
        }
        return new InvbysitDTOId(id.getSkuNo(), id.getSiteNo());
    }

    @Deprecated
    public static InvbysitDTOId toDTO(InvbysitEntityId id) {
        return toDTOId(id);
    }

    public static List<InvbysitDTOId> toDTOIdList(Collection<InvbysitEntityId> entityIds) {
        if (entityIds == null) {
            return null;
        }
        	
        List<InvbysitDTOId> dtoIds = new ArrayList<InvbysitDTOId>();
        for (InvbysitEntityId entity: entityIds) {
            dtoIds.add(toDTOId(entity));
        }
        	
        return dtoIds;
    }

    public static Set<InvbysitFullEntity> toEntitySet(Collection<InvbysitFullDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        Set<InvbysitFullEntity> entities = new HashSet<InvbysitFullEntity>();
        for (InvbysitFullDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static Set<InvbysitFullEntity> toEntity(Collection<InvbysitFullDTO> dtos) {
        return toEntitySet(dtos);
    }

    public static SortedSet<InvbysitFullEntity> toEntitySortedSet(Collection<InvbysitFullDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        SortedSet<InvbysitFullEntity> entities = new TreeSet<InvbysitFullEntity>();
        for (InvbysitFullDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static SortedSet<InvbysitFullEntity> toEntitySorted(Collection<InvbysitFullDTO> dtos) {
        return toEntitySortedSet(dtos);
    }

    public static List<InvbysitFullEntity> toEntityList(Collection<InvbysitFullDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        List<InvbysitFullEntity> entities = new ArrayList<InvbysitFullEntity>();
        for (InvbysitFullDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    public static InvbysitEntityId toEntityId(InvbysitDTOId dto) {
        if (dto == null) {
            return null;
        }
        return new InvbysitEntityId(dto.getSkuNo(), dto.getSiteNo());
    }

    @Deprecated
    public static InvbysitEntityId toEntity(InvbysitDTOId id) {
        return toEntityId(id);
    }

    public static List<InvbysitEntityId> toEntityIdList(Collection<InvbysitDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        List<InvbysitEntityId> entityIds = new ArrayList<InvbysitEntityId>();
        for (InvbysitDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static Set<InvbysitEntityId> toEntityIdSet(Collection<InvbysitDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        Set<InvbysitEntityId> entityIds = new HashSet<InvbysitEntityId>();
        for (InvbysitDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static SortedSet<InvbysitEntityId> toEntityIdSortedSet(Collection<InvbysitDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        SortedSet<InvbysitEntityId> entityIds = new TreeSet<InvbysitEntityId>();
        for (InvbysitDTOId dtoId: dtoIds) {
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
        final InvbysitFullEntity other = ((InvbysitFullEntity) obj);
        // Identifier level comparison
        if (this.identifier.getSkuNo() == null) {
            return false;
        } else {
            if (!this.identifier.getSkuNo().equals(other.identifier.getSkuNo())) {
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
        final InvbysitFullEntity other = ((InvbysitFullEntity) obj);
        // Data level comparison
        if (promoKeyNo == null) {
            if (other.promoKeyNo!= null) {
                return false;
            }
        } else {
            if (!promoKeyNo.equals(other.promoKeyNo)) {
                return false;
            }
        }
        if (location1 == null) {
            if (other.location1 != null) {
                return false;
            }
        } else {
            if (!location1 .equals(other.location1)) {
                return false;
            }
        }
        if (location2 == null) {
            if (other.location2 != null) {
                return false;
            }
        } else {
            if (!location2 .equals(other.location2)) {
                return false;
            }
        }
        if (minLevel == null) {
            if (other.minLevel!= null) {
                return false;
            }
        } else {
            if (!minLevel.equals(other.minLevel)) {
                return false;
            }
        }
        if (maxLevel == null) {
            if (other.maxLevel!= null) {
                return false;
            }
        } else {
            if (!maxLevel.equals(other.maxLevel)) {
                return false;
            }
        }
        if (reorderPt == null) {
            if (other.reorderPt!= null) {
                return false;
            }
        } else {
            if (!reorderPt.equals(other.reorderPt)) {
                return false;
            }
        }
        if (reorderTo == null) {
            if (other.reorderTo!= null) {
                return false;
            }
        } else {
            if (!reorderTo.equals(other.reorderTo)) {
                return false;
            }
        }
        if (priceType == null) {
            if (other.priceType!= null) {
                return false;
            }
        } else {
            if (!priceType.equals(other.priceType)) {
                return false;
            }
        }
        if (unitPrice1 == null) {
            if (other.unitPrice1 != null) {
                return false;
            }
        } else {
            if (!unitPrice1 .equals(other.unitPrice1)) {
                return false;
            }
        }
        if (unitPrice2 == null) {
            if (other.unitPrice2 != null) {
                return false;
            }
        } else {
            if (!unitPrice2 .equals(other.unitPrice2)) {
                return false;
            }
        }
        if (unitPrice3 == null) {
            if (other.unitPrice3 != null) {
                return false;
            }
        } else {
            if (!unitPrice3 .equals(other.unitPrice3)) {
                return false;
            }
        }
        if (unitPrice4 == null) {
            if (other.unitPrice4 != null) {
                return false;
            }
        } else {
            if (!unitPrice4 .equals(other.unitPrice4)) {
                return false;
            }
        }
        if (unitPrice5 == null) {
            if (other.unitPrice5 != null) {
                return false;
            }
        } else {
            if (!unitPrice5 .equals(other.unitPrice5)) {
                return false;
            }
        }
        if (unitPrice6 == null) {
            if (other.unitPrice6 != null) {
                return false;
            }
        } else {
            if (!unitPrice6 .equals(other.unitPrice6)) {
                return false;
            }
        }
        if (unitPrice7 == null) {
            if (other.unitPrice7 != null) {
                return false;
            }
        } else {
            if (!unitPrice7 .equals(other.unitPrice7)) {
                return false;
            }
        }
        if (unitPrice8 == null) {
            if (other.unitPrice8 != null) {
                return false;
            }
        } else {
            if (!unitPrice8 .equals(other.unitPrice8)) {
                return false;
            }
        }
        if (unitPrice9 == null) {
            if (other.unitPrice9 != null) {
                return false;
            }
        } else {
            if (!unitPrice9 .equals(other.unitPrice9)) {
                return false;
            }
        }
        if (unitPrice10 == null) {
            if (other.unitPrice10 != null) {
                return false;
            }
        } else {
            if (!unitPrice10 .equals(other.unitPrice10)) {
                return false;
            }
        }
        if (priceQty1 == null) {
            if (other.priceQty1 != null) {
                return false;
            }
        } else {
            if (!priceQty1 .equals(other.priceQty1)) {
                return false;
            }
        }
        if (priceQty2 == null) {
            if (other.priceQty2 != null) {
                return false;
            }
        } else {
            if (!priceQty2 .equals(other.priceQty2)) {
                return false;
            }
        }
        if (priceQty3 == null) {
            if (other.priceQty3 != null) {
                return false;
            }
        } else {
            if (!priceQty3 .equals(other.priceQty3)) {
                return false;
            }
        }
        if (priceQty4 == null) {
            if (other.priceQty4 != null) {
                return false;
            }
        } else {
            if (!priceQty4 .equals(other.priceQty4)) {
                return false;
            }
        }
        if (priceQty5 == null) {
            if (other.priceQty5 != null) {
                return false;
            }
        } else {
            if (!priceQty5 .equals(other.priceQty5)) {
                return false;
            }
        }
        if (priceQty6 == null) {
            if (other.priceQty6 != null) {
                return false;
            }
        } else {
            if (!priceQty6 .equals(other.priceQty6)) {
                return false;
            }
        }
        if (priceQty7 == null) {
            if (other.priceQty7 != null) {
                return false;
            }
        } else {
            if (!priceQty7 .equals(other.priceQty7)) {
                return false;
            }
        }
        if (priceQty8 == null) {
            if (other.priceQty8 != null) {
                return false;
            }
        } else {
            if (!priceQty8 .equals(other.priceQty8)) {
                return false;
            }
        }
        if (priceQty9 == null) {
            if (other.priceQty9 != null) {
                return false;
            }
        } else {
            if (!priceQty9 .equals(other.priceQty9)) {
                return false;
            }
        }
        if (priceQty10 == null) {
            if (other.priceQty10 != null) {
                return false;
            }
        } else {
            if (!priceQty10 .equals(other.priceQty10)) {
                return false;
            }
        }
        if (price1 == null) {
            if (other.price1 != null) {
                return false;
            }
        } else {
            if (!price1 .equals(other.price1)) {
                return false;
            }
        }
        if (price2 == null) {
            if (other.price2 != null) {
                return false;
            }
        } else {
            if (!price2 .equals(other.price2)) {
                return false;
            }
        }
        if (price3 == null) {
            if (other.price3 != null) {
                return false;
            }
        } else {
            if (!price3 .equals(other.price3)) {
                return false;
            }
        }
        if (price4 == null) {
            if (other.price4 != null) {
                return false;
            }
        } else {
            if (!price4 .equals(other.price4)) {
                return false;
            }
        }
        if (price5 == null) {
            if (other.price5 != null) {
                return false;
            }
        } else {
            if (!price5 .equals(other.price5)) {
                return false;
            }
        }
        if (price6 == null) {
            if (other.price6 != null) {
                return false;
            }
        } else {
            if (!price6 .equals(other.price6)) {
                return false;
            }
        }
        if (price7 == null) {
            if (other.price7 != null) {
                return false;
            }
        } else {
            if (!price7 .equals(other.price7)) {
                return false;
            }
        }
        if (price8 == null) {
            if (other.price8 != null) {
                return false;
            }
        } else {
            if (!price8 .equals(other.price8)) {
                return false;
            }
        }
        if (price9 == null) {
            if (other.price9 != null) {
                return false;
            }
        } else {
            if (!price9 .equals(other.price9)) {
                return false;
            }
        }
        if (price10 == null) {
            if (other.price10 != null) {
                return false;
            }
        } else {
            if (!price10 .equals(other.price10)) {
                return false;
            }
        }
        if (epoPct == null) {
            if (other.epoPct!= null) {
                return false;
            }
        } else {
            if (!epoPct.equals(other.epoPct)) {
                return false;
            }
        }
        if (msrpUnitPrice == null) {
            if (other.msrpUnitPrice!= null) {
                return false;
            }
        } else {
            if (!msrpUnitPrice.equals(other.msrpUnitPrice)) {
                return false;
            }
        }
        if (msrpPrice == null) {
            if (other.msrpPrice!= null) {
                return false;
            }
        } else {
            if (!msrpPrice.equals(other.msrpPrice)) {
                return false;
            }
        }
        if (msrpQty == null) {
            if (other.msrpQty!= null) {
                return false;
            }
        } else {
            if (!msrpQty.equals(other.msrpQty)) {
                return false;
            }
        }
        if (pkgPriceQty1 == null) {
            if (other.pkgPriceQty1 != null) {
                return false;
            }
        } else {
            if (!pkgPriceQty1 .equals(other.pkgPriceQty1)) {
                return false;
            }
        }
        if (pkgPriceQty2 == null) {
            if (other.pkgPriceQty2 != null) {
                return false;
            }
        } else {
            if (!pkgPriceQty2 .equals(other.pkgPriceQty2)) {
                return false;
            }
        }
        if (pkgPriceQty3 == null) {
            if (other.pkgPriceQty3 != null) {
                return false;
            }
        } else {
            if (!pkgPriceQty3 .equals(other.pkgPriceQty3)) {
                return false;
            }
        }
        if (pkgPrice1 == null) {
            if (other.pkgPrice1 != null) {
                return false;
            }
        } else {
            if (!pkgPrice1 .equals(other.pkgPrice1)) {
                return false;
            }
        }
        if (pkgPrice2 == null) {
            if (other.pkgPrice2 != null) {
                return false;
            }
        } else {
            if (!pkgPrice2 .equals(other.pkgPrice2)) {
                return false;
            }
        }
        if (pkgPrice3 == null) {
            if (other.pkgPrice3 != null) {
                return false;
            }
        } else {
            if (!pkgPrice3 .equals(other.pkgPrice3)) {
                return false;
            }
        }
        if (preMarkdownPrice == null) {
            if (other.preMarkdownPrice!= null) {
                return false;
            }
        } else {
            if (!preMarkdownPrice.equals(other.preMarkdownPrice)) {
                return false;
            }
        }
        if (noOfFacings == null) {
            if (other.noOfFacings!= null) {
                return false;
            }
        } else {
            if (!noOfFacings.equals(other.noOfFacings)) {
                return false;
            }
        }
        if (shelfVelocity == null) {
            if (other.shelfVelocity!= null) {
                return false;
            }
        } else {
            if (!shelfVelocity.equals(other.shelfVelocity)) {
                return false;
            }
        }
        if (commissionCd == null) {
            if (other.commissionCd!= null) {
                return false;
            }
        } else {
            if (!commissionCd.equals(other.commissionCd)) {
                return false;
            }
        }
        if (invCost == null) {
            if (other.invCost!= null) {
                return false;
            }
        } else {
            if (!invCost.equals(other.invCost)) {
                return false;
            }
        }
        if (standardCost == null) {
            if (other.standardCost!= null) {
                return false;
            }
        } else {
            if (!standardCost.equals(other.standardCost)) {
                return false;
            }
        }
        if (avgAge == null) {
            if (other.avgAge!= null) {
                return false;
            }
        } else {
            if (!avgAge.equals(other.avgAge)) {
                return false;
            }
        }
        if (firstRcvdDt == null) {
            if (other.firstRcvdDt!= null) {
                return false;
            }
        } else {
            if (!firstRcvdDt.equals(other.firstRcvdDt)) {
                return false;
            }
        }
        if (lastRcvdDt == null) {
            if (other.lastRcvdDt!= null) {
                return false;
            }
        } else {
            if (!lastRcvdDt.equals(other.lastRcvdDt)) {
                return false;
            }
        }
        if (qtyLastRcvd == null) {
            if (other.qtyLastRcvd!= null) {
                return false;
            }
        } else {
            if (!qtyLastRcvd.equals(other.qtyLastRcvd)) {
                return false;
            }
        }
        if (lastOrdDt == null) {
            if (other.lastOrdDt!= null) {
                return false;
            }
        } else {
            if (!lastOrdDt.equals(other.lastOrdDt)) {
                return false;
            }
        }
        if (lastSoldDt == null) {
            if (other.lastSoldDt!= null) {
                return false;
            }
        } else {
            if (!lastSoldDt.equals(other.lastSoldDt)) {
                return false;
            }
        }
        if (lastSoldPreviousDt == null) {
            if (other.lastSoldPreviousDt!= null) {
                return false;
            }
        } else {
            if (!lastSoldPreviousDt.equals(other.lastSoldPreviousDt)) {
                return false;
            }
        }
        if (qtyOnHand == null) {
            if (other.qtyOnHand!= null) {
                return false;
            }
        } else {
            if (!qtyOnHand.equals(other.qtyOnHand)) {
                return false;
            }
        }
        if (qtyRsvd == null) {
            if (other.qtyRsvd!= null) {
                return false;
            }
        } else {
            if (!qtyRsvd.equals(other.qtyRsvd)) {
                return false;
            }
        }
        if (qtyOnOrd == null) {
            if (other.qtyOnOrd!= null) {
                return false;
            }
        } else {
            if (!qtyOnOrd.equals(other.qtyOnOrd)) {
                return false;
            }
        }
        if (qtyOnBackord == null) {
            if (other.qtyOnBackord!= null) {
                return false;
            }
        } else {
            if (!qtyOnBackord.equals(other.qtyOnBackord)) {
                return false;
            }
        }
        if (qtyInTransit == null) {
            if (other.qtyInTransit!= null) {
                return false;
            }
        } else {
            if (!qtyInTransit.equals(other.qtyInTransit)) {
                return false;
            }
        }
        if (qtyRtv == null) {
            if (other.qtyRtv!= null) {
                return false;
            }
        } else {
            if (!qtyRtv.equals(other.qtyRtv)) {
                return false;
            }
        }
        if (expRcvdDt == null) {
            if (other.expRcvdDt!= null) {
                return false;
            }
        } else {
            if (!expRcvdDt.equals(other.expRcvdDt)) {
                return false;
            }
        }
        if (expBoDt == null) {
            if (other.expBoDt!= null) {
                return false;
            }
        } else {
            if (!expBoDt.equals(other.expBoDt)) {
                return false;
            }
        }
        if (qtyCounted == null) {
            if (other.qtyCounted!= null) {
                return false;
            }
        } else {
            if (!qtyCounted.equals(other.qtyCounted)) {
                return false;
            }
        }
        if (maxOrderQty == null) {
            if (other.maxOrderQty!= null) {
                return false;
            }
        } else {
            if (!maxOrderQty.equals(other.maxOrderQty)) {
                return false;
            }
        }
        if (mixMatchNo == null) {
            if (other.mixMatchNo!= null) {
                return false;
            }
        } else {
            if (!mixMatchNo.equals(other.mixMatchNo)) {
                return false;
            }
        }
        if (bottleLinkNo == null) {
            if (other.bottleLinkNo!= null) {
                return false;
            }
        } else {
            if (!bottleLinkNo.equals(other.bottleLinkNo)) {
                return false;
            }
        }
        if (lastPrice == null) {
            if (other.lastPrice!= null) {
                return false;
            }
        } else {
            if (!lastPrice.equals(other.lastPrice)) {
                return false;
            }
        }
        if (lastPchgDt == null) {
            if (other.lastPchgDt!= null) {
                return false;
            }
        } else {
            if (!lastPchgDt.equals(other.lastPchgDt)) {
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
        if (allowPchgFl == null) {
            if (other.allowPchgFl!= null) {
                return false;
            }
        } else {
            if (!allowPchgFl.equals(other.allowPchgFl)) {
                return false;
            }
        }
        if (cosPct == null) {
            if (other.cosPct!= null) {
                return false;
            }
        } else {
            if (!cosPct.equals(other.cosPct)) {
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
        if (priorReorderPt == null) {
            if (other.priorReorderPt!= null) {
                return false;
            }
        } else {
            if (!priorReorderPt.equals(other.priorReorderPt)) {
                return false;
            }
        }
        if (priorReorderTo == null) {
            if (other.priorReorderTo!= null) {
                return false;
            }
        } else {
            if (!priorReorderTo.equals(other.priorReorderTo)) {
                return false;
            }
        }
        if (warrantyInvCost == null) {
            if (other.warrantyInvCost!= null) {
                return false;
            }
        } else {
            if (!warrantyInvCost.equals(other.warrantyInvCost)) {
                return false;
            }
        }
        if (warrantyQtyOnHand == null) {
            if (other.warrantyQtyOnHand!= null) {
                return false;
            }
        } else {
            if (!warrantyQtyOnHand.equals(other.warrantyQtyOnHand)) {
                return false;
            }
        }
        if (weightedLeadTime == null) {
            if (other.weightedLeadTime!= null) {
                return false;
            }
        } else {
            if (!weightedLeadTime.equals(other.weightedLeadTime)) {
                return false;
            }
        }
        if (coreSellPrice == null) {
            if (other.coreSellPrice!= null) {
                return false;
            }
        } else {
            if (!coreSellPrice.equals(other.coreSellPrice)) {
                return false;
            }
        }
        if (moDemandForecast == null) {
            if (other.moDemandForecast!= null) {
                return false;
            }
        } else {
            if (!moDemandForecast.equals(other.moDemandForecast)) {
                return false;
            }
        }
        if (coreInvCost == null) {
            if (other.coreInvCost!= null) {
                return false;
            }
        } else {
            if (!coreInvCost.equals(other.coreInvCost)) {
                return false;
            }
        }
        if (coreQtyOnHand == null) {
            if (other.coreQtyOnHand!= null) {
                return false;
            }
        } else {
            if (!coreQtyOnHand.equals(other.coreQtyOnHand)) {
                return false;
            }
        }
        if (skuStatusId == null) {
            if (other.skuStatusId!= null) {
                return false;
            }
        } else {
            if (!skuStatusId.equals(other.skuStatusId)) {
                return false;
            }
        }
        if (nonDiscountFl == null) {
            if (other.nonDiscountFl!= null) {
                return false;
            }
        } else {
            if (!nonDiscountFl.equals(other.nonDiscountFl)) {
                return false;
            }
        }
        if (restrictSaleId == null) {
            if (other.restrictSaleId!= null) {
                return false;
            }
        } else {
            if (!restrictSaleId.equals(other.restrictSaleId)) {
                return false;
            }
        }
        if (labelCd == null) {
            if (other.labelCd!= null) {
                return false;
            }
        } else {
            if (!labelCd.equals(other.labelCd)) {
                return false;
            }
        }
        if (lastPriceIncDt == null) {
            if (other.lastPriceIncDt!= null) {
                return false;
            }
        } else {
            if (!lastPriceIncDt.equals(other.lastPriceIncDt)) {
                return false;
            }
        }
        if (lastPriceDecDt == null) {
            if (other.lastPriceDecDt!= null) {
                return false;
            }
        } else {
            if (!lastPriceDecDt.equals(other.lastPriceDecDt)) {
                return false;
            }
        }
        if (lastCycleDt == null) {
            if (other.lastCycleDt!= null) {
                return false;
            }
        } else {
            if (!lastCycleDt.equals(other.lastCycleDt)) {
                return false;
            }
        }
        if (lastPriceVerDt == null) {
            if (other.lastPriceVerDt!= null) {
                return false;
            }
        } else {
            if (!lastPriceVerDt.equals(other.lastPriceVerDt)) {
                return false;
            }
        }
        if (lastChangeDt == null) {
            if (other.lastChangeDt!= null) {
                return false;
            }
        } else {
            if (!lastChangeDt.equals(other.lastChangeDt)) {
                return false;
            }
        }
        if (newSkuStatusId == null) {
            if (other.newSkuStatusId!= null) {
                return false;
            }
        } else {
            if (!newSkuStatusId.equals(other.newSkuStatusId)) {
                return false;
            }
        }
        if (itemizerStatusId == null) {
            if (other.itemizerStatusId!= null) {
                return false;
            }
        } else {
            if (!itemizerStatusId.equals(other.itemizerStatusId)) {
                return false;
            }
        }
        if (scanDeptNo == null) {
            if (other.scanDeptNo!= null) {
                return false;
            }
        } else {
            if (!scanDeptNo.equals(other.scanDeptNo)) {
                return false;
            }
        }
        if (scanSubdeptNo == null) {
            if (other.scanSubdeptNo!= null) {
                return false;
            }
        } else {
            if (!scanSubdeptNo.equals(other.scanSubdeptNo)) {
                return false;
            }
        }
        if (downloadNToYFl == null) {
            if (other.downloadNToYFl!= null) {
                return false;
            }
        } else {
            if (!downloadNToYFl.equals(other.downloadNToYFl)) {
                return false;
            }
        }
        if (labelQty1 == null) {
            if (other.labelQty1 != null) {
                return false;
            }
        } else {
            if (!labelQty1 .equals(other.labelQty1)) {
                return false;
            }
        }
        if (labelQty2 == null) {
            if (other.labelQty2 != null) {
                return false;
            }
        } else {
            if (!labelQty2 .equals(other.labelQty2)) {
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
        if (lastXferDt == null) {
            if (other.lastXferDt!= null) {
                return false;
            }
        } else {
            if (!lastXferDt.equals(other.lastXferDt)) {
                return false;
            }
        }
        if (priceOptionCd == null) {
            if (other.priceOptionCd!= null) {
                return false;
            }
        } else {
            if (!priceOptionCd.equals(other.priceOptionCd)) {
                return false;
            }
        }
        if (priceAttributeCd == null) {
            if (other.priceAttributeCd!= null) {
                return false;
            }
        } else {
            if (!priceAttributeCd.equals(other.priceAttributeCd)) {
                return false;
            }
        }
        if (promoRecordNo == null) {
            if (other.promoRecordNo!= null) {
                return false;
            }
        } else {
            if (!promoRecordNo.equals(other.promoRecordNo)) {
                return false;
            }
        }
        if (lastVendorCost == null) {
            if (other.lastVendorCost!= null) {
                return false;
            }
        } else {
            if (!lastVendorCost.equals(other.lastVendorCost)) {
                return false;
            }
        }
        if (avgMovement == null) {
            if (other.avgMovement!= null) {
                return false;
            }
        } else {
            if (!avgMovement.equals(other.avgMovement)) {
                return false;
            }
        }
        if (haloGm == null) {
            if (other.haloGm!= null) {
                return false;
            }
        } else {
            if (!haloGm.equals(other.haloGm)) {
                return false;
            }
        }
        if (laloGm == null) {
            if (other.laloGm!= null) {
                return false;
            }
        } else {
            if (!laloGm.equals(other.laloGm)) {
                return false;
            }
        }
        if (replenishmentFl == null) {
            if (other.replenishmentFl!= null) {
                return false;
            }
        } else {
            if (!replenishmentFl.equals(other.replenishmentFl)) {
                return false;
            }
        }
        if (velocityCd == null) {
            if (other.velocityCd!= null) {
                return false;
            }
        } else {
            if (!velocityCd.equals(other.velocityCd)) {
                return false;
            }
        }
        if (velocityLockedFl == null) {
            if (other.velocityLockedFl!= null) {
                return false;
            }
        } else {
            if (!velocityLockedFl.equals(other.velocityLockedFl)) {
                return false;
            }
        }
        if (velocityChangeDt == null) {
            if (other.velocityChangeDt!= null) {
                return false;
            }
        } else {
            if (!velocityChangeDt.equals(other.velocityChangeDt)) {
                return false;
            }
        }
        if (reorderLockedFl == null) {
            if (other.reorderLockedFl!= null) {
                return false;
            }
        } else {
            if (!reorderLockedFl.equals(other.reorderLockedFl)) {
                return false;
            }
        }
        if (reorderNowFl == null) {
            if (other.reorderNowFl!= null) {
                return false;
            }
        } else {
            if (!reorderNowFl.equals(other.reorderNowFl)) {
                return false;
            }
        }
        if (profileData == null) {
            if (other.profileData!= null) {
                return false;
            }
        } else {
            if (!profileData.equals(other.profileData)) {
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
        if (reorderpointSourceCd == null) {
            if (other.reorderpointSourceCd!= null) {
                return false;
            }
        } else {
            if (!reorderpointSourceCd.equals(other.reorderpointSourceCd)) {
                return false;
            }
        }
        if (defaultDcSkuLevel == null) {
            if (other.defaultDcSkuLevel!= null) {
                return false;
            }
        } else {
            if (!defaultDcSkuLevel.equals(other.defaultDcSkuLevel)) {
                return false;
            }
        }
        if (priceCalcId == null) {
            if (other.priceCalcId!= null) {
                return false;
            }
        } else {
            if (!priceCalcId.equals(other.priceCalcId)) {
                return false;
            }
        }
        if (profileDataFl == null) {
            if (other.profileDataFl!= null) {
                return false;
            }
        } else {
            if (!profileDataFl.equals(other.profileDataFl)) {
                return false;
            }
        }
        if (discontinuedStFl == null) {
            if (other.discontinuedStFl!= null) {
                return false;
            }
        } else {
            if (!discontinuedStFl.equals(other.discontinuedStFl)) {
                return false;
            }
        }
        if (discontinuedDt == null) {
            if (other.discontinuedDt!= null) {
                return false;
            }
        } else {
            if (!discontinuedDt.equals(other.discontinuedDt)) {
                return false;
            }
        }
        if (discontinuedBy == null) {
            if (other.discontinuedBy!= null) {
                return false;
            }
        } else {
            if (!discontinuedBy.equals(other.discontinuedBy)) {
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
        if (promoCommissionCd == null) {
            if (other.promoCommissionCd!= null) {
                return false;
            }
        } else {
            if (!promoCommissionCd.equals(other.promoCommissionCd)) {
                return false;
            }
        }
        if (additionalTax == null) {
            if (other.additionalTax!= null) {
                return false;
            }
        } else {
            if (!additionalTax.equals(other.additionalTax)) {
                return false;
            }
        }
        if (negativeQohFl == null) {
            if (other.negativeQohFl!= null) {
                return false;
            }
        } else {
            if (!negativeQohFl.equals(other.negativeQohFl)) {
                return false;
            }
        }
        if (posSkuStatusId == null) {
            if (other.posSkuStatusId!= null) {
                return false;
            }
        } else {
            if (!posSkuStatusId.equals(other.posSkuStatusId)) {
                return false;
            }
        }
        if (vendorItemSeqNo == null) {
            if (other.vendorItemSeqNo!= null) {
                return false;
            }
        } else {
            if (!vendorItemSeqNo.equals(other.vendorItemSeqNo)) {
                return false;
            }
        }
        if (replacementCostAmt == null) {
            if (other.replacementCostAmt!= null) {
                return false;
            }
        } else {
            if (!replacementCostAmt.equals(other.replacementCostAmt)) {
                return false;
            }
        }
        if (firstPrice == null) {
            if (other.firstPrice!= null) {
                return false;
            }
        } else {
            if (!firstPrice.equals(other.firstPrice)) {
                return false;
            }
        }
        if (firstCost == null) {
            if (other.firstCost!= null) {
                return false;
            }
        } else {
            if (!firstCost.equals(other.firstCost)) {
                return false;
            }
        }
        if (firstSoldDt == null) {
            if (other.firstSoldDt!= null) {
                return false;
            }
        } else {
            if (!firstSoldDt.equals(other.firstSoldDt)) {
                return false;
            }
        }
        if (stockingDt == null) {
            if (other.stockingDt!= null) {
                return false;
            }
        } else {
            if (!stockingDt.equals(other.stockingDt)) {
                return false;
            }
        }
        if (classCode == null) {
            if (other.classCode!= null) {
                return false;
            }
        } else {
            if (!classCode.equals(other.classCode)) {
                return false;
            }
        }
        if (negativeQtySiteNo == null) {
            if (other.negativeQtySiteNo!= null) {
                return false;
            }
        } else {
            if (!negativeQtySiteNo.equals(other.negativeQtySiteNo)) {
                return false;
            }
        }
        if (purchaseVolumeId == null) {
            if (other.purchaseVolumeId!= null) {
                return false;
            }
        } else {
            if (!purchaseVolumeId.equals(other.purchaseVolumeId)) {
                return false;
            }
        }
        if (reorderSiteNo == null) {
            if (other.reorderSiteNo!= null) {
                return false;
            }
        } else {
            if (!reorderSiteNo.equals(other.reorderSiteNo)) {
                return false;
            }
        }
        if (productStartDt == null) {
            if (other.productStartDt!= null) {
                return false;
            }
        } else {
            if (!productStartDt.equals(other.productStartDt)) {
                return false;
            }
        }
        if (productEndDt == null) {
            if (other.productEndDt!= null) {
                return false;
            }
        } else {
            if (!productEndDt.equals(other.productEndDt)) {
                return false;
            }
        }
        if (lastReplenishmentDt == null) {
            if (other.lastReplenishmentDt!= null) {
                return false;
            }
        } else {
            if (!lastReplenishmentDt.equals(other.lastReplenishmentDt)) {
                return false;
            }
        }
        if (replicatePricesFl == null) {
            if (other.replicatePricesFl!= null) {
                return false;
            }
        } else {
            if (!replicatePricesFl.equals(other.replicatePricesFl)) {
                return false;
            }
        }
        if (lastPricechgTime == null) {
            if (other.lastPricechgTime!= null) {
                return false;
            }
        } else {
            if (!lastPricechgTime.equals(other.lastPricechgTime)) {
                return false;
            }
        }
        if (avgDlySales == null) {
            if (other.avgDlySales!= null) {
                return false;
            }
        } else {
            if (!avgDlySales.equals(other.avgDlySales)) {
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
        final InvbysitFullEntity other = ((InvbysitFullEntity) obj);
        // Identifier level comparison
        if (this.identifier.getSkuNo() == null) {
            return false;
        } else {
            if (!this.identifier.getSkuNo().equals(other.identifier.getSkuNo())) {
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
        if (promoKeyNo == null) {
            if (other.promoKeyNo!= null) {
                return false;
            }
        } else {
            if (!promoKeyNo.equals(other.promoKeyNo)) {
                return false;
            }
        }
        if (location1 == null) {
            if (other.location1 != null) {
                return false;
            }
        } else {
            if (!location1 .equals(other.location1)) {
                return false;
            }
        }
        if (location2 == null) {
            if (other.location2 != null) {
                return false;
            }
        } else {
            if (!location2 .equals(other.location2)) {
                return false;
            }
        }
        if (minLevel == null) {
            if (other.minLevel!= null) {
                return false;
            }
        } else {
            if (!minLevel.equals(other.minLevel)) {
                return false;
            }
        }
        if (maxLevel == null) {
            if (other.maxLevel!= null) {
                return false;
            }
        } else {
            if (!maxLevel.equals(other.maxLevel)) {
                return false;
            }
        }
        if (reorderPt == null) {
            if (other.reorderPt!= null) {
                return false;
            }
        } else {
            if (!reorderPt.equals(other.reorderPt)) {
                return false;
            }
        }
        if (reorderTo == null) {
            if (other.reorderTo!= null) {
                return false;
            }
        } else {
            if (!reorderTo.equals(other.reorderTo)) {
                return false;
            }
        }
        if (priceType == null) {
            if (other.priceType!= null) {
                return false;
            }
        } else {
            if (!priceType.equals(other.priceType)) {
                return false;
            }
        }
        if (unitPrice1 == null) {
            if (other.unitPrice1 != null) {
                return false;
            }
        } else {
            if (!unitPrice1 .equals(other.unitPrice1)) {
                return false;
            }
        }
        if (unitPrice2 == null) {
            if (other.unitPrice2 != null) {
                return false;
            }
        } else {
            if (!unitPrice2 .equals(other.unitPrice2)) {
                return false;
            }
        }
        if (unitPrice3 == null) {
            if (other.unitPrice3 != null) {
                return false;
            }
        } else {
            if (!unitPrice3 .equals(other.unitPrice3)) {
                return false;
            }
        }
        if (unitPrice4 == null) {
            if (other.unitPrice4 != null) {
                return false;
            }
        } else {
            if (!unitPrice4 .equals(other.unitPrice4)) {
                return false;
            }
        }
        if (unitPrice5 == null) {
            if (other.unitPrice5 != null) {
                return false;
            }
        } else {
            if (!unitPrice5 .equals(other.unitPrice5)) {
                return false;
            }
        }
        if (unitPrice6 == null) {
            if (other.unitPrice6 != null) {
                return false;
            }
        } else {
            if (!unitPrice6 .equals(other.unitPrice6)) {
                return false;
            }
        }
        if (unitPrice7 == null) {
            if (other.unitPrice7 != null) {
                return false;
            }
        } else {
            if (!unitPrice7 .equals(other.unitPrice7)) {
                return false;
            }
        }
        if (unitPrice8 == null) {
            if (other.unitPrice8 != null) {
                return false;
            }
        } else {
            if (!unitPrice8 .equals(other.unitPrice8)) {
                return false;
            }
        }
        if (unitPrice9 == null) {
            if (other.unitPrice9 != null) {
                return false;
            }
        } else {
            if (!unitPrice9 .equals(other.unitPrice9)) {
                return false;
            }
        }
        if (unitPrice10 == null) {
            if (other.unitPrice10 != null) {
                return false;
            }
        } else {
            if (!unitPrice10 .equals(other.unitPrice10)) {
                return false;
            }
        }
        if (priceQty1 == null) {
            if (other.priceQty1 != null) {
                return false;
            }
        } else {
            if (!priceQty1 .equals(other.priceQty1)) {
                return false;
            }
        }
        if (priceQty2 == null) {
            if (other.priceQty2 != null) {
                return false;
            }
        } else {
            if (!priceQty2 .equals(other.priceQty2)) {
                return false;
            }
        }
        if (priceQty3 == null) {
            if (other.priceQty3 != null) {
                return false;
            }
        } else {
            if (!priceQty3 .equals(other.priceQty3)) {
                return false;
            }
        }
        if (priceQty4 == null) {
            if (other.priceQty4 != null) {
                return false;
            }
        } else {
            if (!priceQty4 .equals(other.priceQty4)) {
                return false;
            }
        }
        if (priceQty5 == null) {
            if (other.priceQty5 != null) {
                return false;
            }
        } else {
            if (!priceQty5 .equals(other.priceQty5)) {
                return false;
            }
        }
        if (priceQty6 == null) {
            if (other.priceQty6 != null) {
                return false;
            }
        } else {
            if (!priceQty6 .equals(other.priceQty6)) {
                return false;
            }
        }
        if (priceQty7 == null) {
            if (other.priceQty7 != null) {
                return false;
            }
        } else {
            if (!priceQty7 .equals(other.priceQty7)) {
                return false;
            }
        }
        if (priceQty8 == null) {
            if (other.priceQty8 != null) {
                return false;
            }
        } else {
            if (!priceQty8 .equals(other.priceQty8)) {
                return false;
            }
        }
        if (priceQty9 == null) {
            if (other.priceQty9 != null) {
                return false;
            }
        } else {
            if (!priceQty9 .equals(other.priceQty9)) {
                return false;
            }
        }
        if (priceQty10 == null) {
            if (other.priceQty10 != null) {
                return false;
            }
        } else {
            if (!priceQty10 .equals(other.priceQty10)) {
                return false;
            }
        }
        if (price1 == null) {
            if (other.price1 != null) {
                return false;
            }
        } else {
            if (!price1 .equals(other.price1)) {
                return false;
            }
        }
        if (price2 == null) {
            if (other.price2 != null) {
                return false;
            }
        } else {
            if (!price2 .equals(other.price2)) {
                return false;
            }
        }
        if (price3 == null) {
            if (other.price3 != null) {
                return false;
            }
        } else {
            if (!price3 .equals(other.price3)) {
                return false;
            }
        }
        if (price4 == null) {
            if (other.price4 != null) {
                return false;
            }
        } else {
            if (!price4 .equals(other.price4)) {
                return false;
            }
        }
        if (price5 == null) {
            if (other.price5 != null) {
                return false;
            }
        } else {
            if (!price5 .equals(other.price5)) {
                return false;
            }
        }
        if (price6 == null) {
            if (other.price6 != null) {
                return false;
            }
        } else {
            if (!price6 .equals(other.price6)) {
                return false;
            }
        }
        if (price7 == null) {
            if (other.price7 != null) {
                return false;
            }
        } else {
            if (!price7 .equals(other.price7)) {
                return false;
            }
        }
        if (price8 == null) {
            if (other.price8 != null) {
                return false;
            }
        } else {
            if (!price8 .equals(other.price8)) {
                return false;
            }
        }
        if (price9 == null) {
            if (other.price9 != null) {
                return false;
            }
        } else {
            if (!price9 .equals(other.price9)) {
                return false;
            }
        }
        if (price10 == null) {
            if (other.price10 != null) {
                return false;
            }
        } else {
            if (!price10 .equals(other.price10)) {
                return false;
            }
        }
        if (epoPct == null) {
            if (other.epoPct!= null) {
                return false;
            }
        } else {
            if (!epoPct.equals(other.epoPct)) {
                return false;
            }
        }
        if (msrpUnitPrice == null) {
            if (other.msrpUnitPrice!= null) {
                return false;
            }
        } else {
            if (!msrpUnitPrice.equals(other.msrpUnitPrice)) {
                return false;
            }
        }
        if (msrpPrice == null) {
            if (other.msrpPrice!= null) {
                return false;
            }
        } else {
            if (!msrpPrice.equals(other.msrpPrice)) {
                return false;
            }
        }
        if (msrpQty == null) {
            if (other.msrpQty!= null) {
                return false;
            }
        } else {
            if (!msrpQty.equals(other.msrpQty)) {
                return false;
            }
        }
        if (pkgPriceQty1 == null) {
            if (other.pkgPriceQty1 != null) {
                return false;
            }
        } else {
            if (!pkgPriceQty1 .equals(other.pkgPriceQty1)) {
                return false;
            }
        }
        if (pkgPriceQty2 == null) {
            if (other.pkgPriceQty2 != null) {
                return false;
            }
        } else {
            if (!pkgPriceQty2 .equals(other.pkgPriceQty2)) {
                return false;
            }
        }
        if (pkgPriceQty3 == null) {
            if (other.pkgPriceQty3 != null) {
                return false;
            }
        } else {
            if (!pkgPriceQty3 .equals(other.pkgPriceQty3)) {
                return false;
            }
        }
        if (pkgPrice1 == null) {
            if (other.pkgPrice1 != null) {
                return false;
            }
        } else {
            if (!pkgPrice1 .equals(other.pkgPrice1)) {
                return false;
            }
        }
        if (pkgPrice2 == null) {
            if (other.pkgPrice2 != null) {
                return false;
            }
        } else {
            if (!pkgPrice2 .equals(other.pkgPrice2)) {
                return false;
            }
        }
        if (pkgPrice3 == null) {
            if (other.pkgPrice3 != null) {
                return false;
            }
        } else {
            if (!pkgPrice3 .equals(other.pkgPrice3)) {
                return false;
            }
        }
        if (preMarkdownPrice == null) {
            if (other.preMarkdownPrice!= null) {
                return false;
            }
        } else {
            if (!preMarkdownPrice.equals(other.preMarkdownPrice)) {
                return false;
            }
        }
        if (noOfFacings == null) {
            if (other.noOfFacings!= null) {
                return false;
            }
        } else {
            if (!noOfFacings.equals(other.noOfFacings)) {
                return false;
            }
        }
        if (shelfVelocity == null) {
            if (other.shelfVelocity!= null) {
                return false;
            }
        } else {
            if (!shelfVelocity.equals(other.shelfVelocity)) {
                return false;
            }
        }
        if (commissionCd == null) {
            if (other.commissionCd!= null) {
                return false;
            }
        } else {
            if (!commissionCd.equals(other.commissionCd)) {
                return false;
            }
        }
        if (invCost == null) {
            if (other.invCost!= null) {
                return false;
            }
        } else {
            if (!invCost.equals(other.invCost)) {
                return false;
            }
        }
        if (standardCost == null) {
            if (other.standardCost!= null) {
                return false;
            }
        } else {
            if (!standardCost.equals(other.standardCost)) {
                return false;
            }
        }
        if (avgAge == null) {
            if (other.avgAge!= null) {
                return false;
            }
        } else {
            if (!avgAge.equals(other.avgAge)) {
                return false;
            }
        }
        if (firstRcvdDt == null) {
            if (other.firstRcvdDt!= null) {
                return false;
            }
        } else {
            if (!firstRcvdDt.equals(other.firstRcvdDt)) {
                return false;
            }
        }
        if (lastRcvdDt == null) {
            if (other.lastRcvdDt!= null) {
                return false;
            }
        } else {
            if (!lastRcvdDt.equals(other.lastRcvdDt)) {
                return false;
            }
        }
        if (qtyLastRcvd == null) {
            if (other.qtyLastRcvd!= null) {
                return false;
            }
        } else {
            if (!qtyLastRcvd.equals(other.qtyLastRcvd)) {
                return false;
            }
        }
        if (lastOrdDt == null) {
            if (other.lastOrdDt!= null) {
                return false;
            }
        } else {
            if (!lastOrdDt.equals(other.lastOrdDt)) {
                return false;
            }
        }
        if (lastSoldDt == null) {
            if (other.lastSoldDt!= null) {
                return false;
            }
        } else {
            if (!lastSoldDt.equals(other.lastSoldDt)) {
                return false;
            }
        }
        if (lastSoldPreviousDt == null) {
            if (other.lastSoldPreviousDt!= null) {
                return false;
            }
        } else {
            if (!lastSoldPreviousDt.equals(other.lastSoldPreviousDt)) {
                return false;
            }
        }
        if (qtyOnHand == null) {
            if (other.qtyOnHand!= null) {
                return false;
            }
        } else {
            if (!qtyOnHand.equals(other.qtyOnHand)) {
                return false;
            }
        }
        if (qtyRsvd == null) {
            if (other.qtyRsvd!= null) {
                return false;
            }
        } else {
            if (!qtyRsvd.equals(other.qtyRsvd)) {
                return false;
            }
        }
        if (qtyOnOrd == null) {
            if (other.qtyOnOrd!= null) {
                return false;
            }
        } else {
            if (!qtyOnOrd.equals(other.qtyOnOrd)) {
                return false;
            }
        }
        if (qtyOnBackord == null) {
            if (other.qtyOnBackord!= null) {
                return false;
            }
        } else {
            if (!qtyOnBackord.equals(other.qtyOnBackord)) {
                return false;
            }
        }
        if (qtyInTransit == null) {
            if (other.qtyInTransit!= null) {
                return false;
            }
        } else {
            if (!qtyInTransit.equals(other.qtyInTransit)) {
                return false;
            }
        }
        if (qtyRtv == null) {
            if (other.qtyRtv!= null) {
                return false;
            }
        } else {
            if (!qtyRtv.equals(other.qtyRtv)) {
                return false;
            }
        }
        if (expRcvdDt == null) {
            if (other.expRcvdDt!= null) {
                return false;
            }
        } else {
            if (!expRcvdDt.equals(other.expRcvdDt)) {
                return false;
            }
        }
        if (expBoDt == null) {
            if (other.expBoDt!= null) {
                return false;
            }
        } else {
            if (!expBoDt.equals(other.expBoDt)) {
                return false;
            }
        }
        if (qtyCounted == null) {
            if (other.qtyCounted!= null) {
                return false;
            }
        } else {
            if (!qtyCounted.equals(other.qtyCounted)) {
                return false;
            }
        }
        if (maxOrderQty == null) {
            if (other.maxOrderQty!= null) {
                return false;
            }
        } else {
            if (!maxOrderQty.equals(other.maxOrderQty)) {
                return false;
            }
        }
        if (mixMatchNo == null) {
            if (other.mixMatchNo!= null) {
                return false;
            }
        } else {
            if (!mixMatchNo.equals(other.mixMatchNo)) {
                return false;
            }
        }
        if (bottleLinkNo == null) {
            if (other.bottleLinkNo!= null) {
                return false;
            }
        } else {
            if (!bottleLinkNo.equals(other.bottleLinkNo)) {
                return false;
            }
        }
        if (lastPrice == null) {
            if (other.lastPrice!= null) {
                return false;
            }
        } else {
            if (!lastPrice.equals(other.lastPrice)) {
                return false;
            }
        }
        if (lastPchgDt == null) {
            if (other.lastPchgDt!= null) {
                return false;
            }
        } else {
            if (!lastPchgDt.equals(other.lastPchgDt)) {
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
        if (allowPchgFl == null) {
            if (other.allowPchgFl!= null) {
                return false;
            }
        } else {
            if (!allowPchgFl.equals(other.allowPchgFl)) {
                return false;
            }
        }
        if (cosPct == null) {
            if (other.cosPct!= null) {
                return false;
            }
        } else {
            if (!cosPct.equals(other.cosPct)) {
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
        if (priorReorderPt == null) {
            if (other.priorReorderPt!= null) {
                return false;
            }
        } else {
            if (!priorReorderPt.equals(other.priorReorderPt)) {
                return false;
            }
        }
        if (priorReorderTo == null) {
            if (other.priorReorderTo!= null) {
                return false;
            }
        } else {
            if (!priorReorderTo.equals(other.priorReorderTo)) {
                return false;
            }
        }
        if (warrantyInvCost == null) {
            if (other.warrantyInvCost!= null) {
                return false;
            }
        } else {
            if (!warrantyInvCost.equals(other.warrantyInvCost)) {
                return false;
            }
        }
        if (warrantyQtyOnHand == null) {
            if (other.warrantyQtyOnHand!= null) {
                return false;
            }
        } else {
            if (!warrantyQtyOnHand.equals(other.warrantyQtyOnHand)) {
                return false;
            }
        }
        if (weightedLeadTime == null) {
            if (other.weightedLeadTime!= null) {
                return false;
            }
        } else {
            if (!weightedLeadTime.equals(other.weightedLeadTime)) {
                return false;
            }
        }
        if (coreSellPrice == null) {
            if (other.coreSellPrice!= null) {
                return false;
            }
        } else {
            if (!coreSellPrice.equals(other.coreSellPrice)) {
                return false;
            }
        }
        if (moDemandForecast == null) {
            if (other.moDemandForecast!= null) {
                return false;
            }
        } else {
            if (!moDemandForecast.equals(other.moDemandForecast)) {
                return false;
            }
        }
        if (coreInvCost == null) {
            if (other.coreInvCost!= null) {
                return false;
            }
        } else {
            if (!coreInvCost.equals(other.coreInvCost)) {
                return false;
            }
        }
        if (coreQtyOnHand == null) {
            if (other.coreQtyOnHand!= null) {
                return false;
            }
        } else {
            if (!coreQtyOnHand.equals(other.coreQtyOnHand)) {
                return false;
            }
        }
        if (skuStatusId == null) {
            if (other.skuStatusId!= null) {
                return false;
            }
        } else {
            if (!skuStatusId.equals(other.skuStatusId)) {
                return false;
            }
        }
        if (nonDiscountFl == null) {
            if (other.nonDiscountFl!= null) {
                return false;
            }
        } else {
            if (!nonDiscountFl.equals(other.nonDiscountFl)) {
                return false;
            }
        }
        if (restrictSaleId == null) {
            if (other.restrictSaleId!= null) {
                return false;
            }
        } else {
            if (!restrictSaleId.equals(other.restrictSaleId)) {
                return false;
            }
        }
        if (labelCd == null) {
            if (other.labelCd!= null) {
                return false;
            }
        } else {
            if (!labelCd.equals(other.labelCd)) {
                return false;
            }
        }
        if (lastPriceIncDt == null) {
            if (other.lastPriceIncDt!= null) {
                return false;
            }
        } else {
            if (!lastPriceIncDt.equals(other.lastPriceIncDt)) {
                return false;
            }
        }
        if (lastPriceDecDt == null) {
            if (other.lastPriceDecDt!= null) {
                return false;
            }
        } else {
            if (!lastPriceDecDt.equals(other.lastPriceDecDt)) {
                return false;
            }
        }
        if (lastCycleDt == null) {
            if (other.lastCycleDt!= null) {
                return false;
            }
        } else {
            if (!lastCycleDt.equals(other.lastCycleDt)) {
                return false;
            }
        }
        if (lastPriceVerDt == null) {
            if (other.lastPriceVerDt!= null) {
                return false;
            }
        } else {
            if (!lastPriceVerDt.equals(other.lastPriceVerDt)) {
                return false;
            }
        }
        if (lastChangeDt == null) {
            if (other.lastChangeDt!= null) {
                return false;
            }
        } else {
            if (!lastChangeDt.equals(other.lastChangeDt)) {
                return false;
            }
        }
        if (newSkuStatusId == null) {
            if (other.newSkuStatusId!= null) {
                return false;
            }
        } else {
            if (!newSkuStatusId.equals(other.newSkuStatusId)) {
                return false;
            }
        }
        if (itemizerStatusId == null) {
            if (other.itemizerStatusId!= null) {
                return false;
            }
        } else {
            if (!itemizerStatusId.equals(other.itemizerStatusId)) {
                return false;
            }
        }
        if (scanDeptNo == null) {
            if (other.scanDeptNo!= null) {
                return false;
            }
        } else {
            if (!scanDeptNo.equals(other.scanDeptNo)) {
                return false;
            }
        }
        if (scanSubdeptNo == null) {
            if (other.scanSubdeptNo!= null) {
                return false;
            }
        } else {
            if (!scanSubdeptNo.equals(other.scanSubdeptNo)) {
                return false;
            }
        }
        if (downloadNToYFl == null) {
            if (other.downloadNToYFl!= null) {
                return false;
            }
        } else {
            if (!downloadNToYFl.equals(other.downloadNToYFl)) {
                return false;
            }
        }
        if (labelQty1 == null) {
            if (other.labelQty1 != null) {
                return false;
            }
        } else {
            if (!labelQty1 .equals(other.labelQty1)) {
                return false;
            }
        }
        if (labelQty2 == null) {
            if (other.labelQty2 != null) {
                return false;
            }
        } else {
            if (!labelQty2 .equals(other.labelQty2)) {
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
        if (lastXferDt == null) {
            if (other.lastXferDt!= null) {
                return false;
            }
        } else {
            if (!lastXferDt.equals(other.lastXferDt)) {
                return false;
            }
        }
        if (priceOptionCd == null) {
            if (other.priceOptionCd!= null) {
                return false;
            }
        } else {
            if (!priceOptionCd.equals(other.priceOptionCd)) {
                return false;
            }
        }
        if (priceAttributeCd == null) {
            if (other.priceAttributeCd!= null) {
                return false;
            }
        } else {
            if (!priceAttributeCd.equals(other.priceAttributeCd)) {
                return false;
            }
        }
        if (promoRecordNo == null) {
            if (other.promoRecordNo!= null) {
                return false;
            }
        } else {
            if (!promoRecordNo.equals(other.promoRecordNo)) {
                return false;
            }
        }
        if (lastVendorCost == null) {
            if (other.lastVendorCost!= null) {
                return false;
            }
        } else {
            if (!lastVendorCost.equals(other.lastVendorCost)) {
                return false;
            }
        }
        if (avgMovement == null) {
            if (other.avgMovement!= null) {
                return false;
            }
        } else {
            if (!avgMovement.equals(other.avgMovement)) {
                return false;
            }
        }
        if (haloGm == null) {
            if (other.haloGm!= null) {
                return false;
            }
        } else {
            if (!haloGm.equals(other.haloGm)) {
                return false;
            }
        }
        if (laloGm == null) {
            if (other.laloGm!= null) {
                return false;
            }
        } else {
            if (!laloGm.equals(other.laloGm)) {
                return false;
            }
        }
        if (replenishmentFl == null) {
            if (other.replenishmentFl!= null) {
                return false;
            }
        } else {
            if (!replenishmentFl.equals(other.replenishmentFl)) {
                return false;
            }
        }
        if (velocityCd == null) {
            if (other.velocityCd!= null) {
                return false;
            }
        } else {
            if (!velocityCd.equals(other.velocityCd)) {
                return false;
            }
        }
        if (velocityLockedFl == null) {
            if (other.velocityLockedFl!= null) {
                return false;
            }
        } else {
            if (!velocityLockedFl.equals(other.velocityLockedFl)) {
                return false;
            }
        }
        if (velocityChangeDt == null) {
            if (other.velocityChangeDt!= null) {
                return false;
            }
        } else {
            if (!velocityChangeDt.equals(other.velocityChangeDt)) {
                return false;
            }
        }
        if (reorderLockedFl == null) {
            if (other.reorderLockedFl!= null) {
                return false;
            }
        } else {
            if (!reorderLockedFl.equals(other.reorderLockedFl)) {
                return false;
            }
        }
        if (reorderNowFl == null) {
            if (other.reorderNowFl!= null) {
                return false;
            }
        } else {
            if (!reorderNowFl.equals(other.reorderNowFl)) {
                return false;
            }
        }
        if (profileData == null) {
            if (other.profileData!= null) {
                return false;
            }
        } else {
            if (!profileData.equals(other.profileData)) {
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
        if (reorderpointSourceCd == null) {
            if (other.reorderpointSourceCd!= null) {
                return false;
            }
        } else {
            if (!reorderpointSourceCd.equals(other.reorderpointSourceCd)) {
                return false;
            }
        }
        if (defaultDcSkuLevel == null) {
            if (other.defaultDcSkuLevel!= null) {
                return false;
            }
        } else {
            if (!defaultDcSkuLevel.equals(other.defaultDcSkuLevel)) {
                return false;
            }
        }
        if (priceCalcId == null) {
            if (other.priceCalcId!= null) {
                return false;
            }
        } else {
            if (!priceCalcId.equals(other.priceCalcId)) {
                return false;
            }
        }
        if (profileDataFl == null) {
            if (other.profileDataFl!= null) {
                return false;
            }
        } else {
            if (!profileDataFl.equals(other.profileDataFl)) {
                return false;
            }
        }
        if (discontinuedStFl == null) {
            if (other.discontinuedStFl!= null) {
                return false;
            }
        } else {
            if (!discontinuedStFl.equals(other.discontinuedStFl)) {
                return false;
            }
        }
        if (discontinuedDt == null) {
            if (other.discontinuedDt!= null) {
                return false;
            }
        } else {
            if (!discontinuedDt.equals(other.discontinuedDt)) {
                return false;
            }
        }
        if (discontinuedBy == null) {
            if (other.discontinuedBy!= null) {
                return false;
            }
        } else {
            if (!discontinuedBy.equals(other.discontinuedBy)) {
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
        if (promoCommissionCd == null) {
            if (other.promoCommissionCd!= null) {
                return false;
            }
        } else {
            if (!promoCommissionCd.equals(other.promoCommissionCd)) {
                return false;
            }
        }
        if (additionalTax == null) {
            if (other.additionalTax!= null) {
                return false;
            }
        } else {
            if (!additionalTax.equals(other.additionalTax)) {
                return false;
            }
        }
        if (negativeQohFl == null) {
            if (other.negativeQohFl!= null) {
                return false;
            }
        } else {
            if (!negativeQohFl.equals(other.negativeQohFl)) {
                return false;
            }
        }
        if (posSkuStatusId == null) {
            if (other.posSkuStatusId!= null) {
                return false;
            }
        } else {
            if (!posSkuStatusId.equals(other.posSkuStatusId)) {
                return false;
            }
        }
        if (vendorItemSeqNo == null) {
            if (other.vendorItemSeqNo!= null) {
                return false;
            }
        } else {
            if (!vendorItemSeqNo.equals(other.vendorItemSeqNo)) {
                return false;
            }
        }
        if (replacementCostAmt == null) {
            if (other.replacementCostAmt!= null) {
                return false;
            }
        } else {
            if (!replacementCostAmt.equals(other.replacementCostAmt)) {
                return false;
            }
        }
        if (firstPrice == null) {
            if (other.firstPrice!= null) {
                return false;
            }
        } else {
            if (!firstPrice.equals(other.firstPrice)) {
                return false;
            }
        }
        if (firstCost == null) {
            if (other.firstCost!= null) {
                return false;
            }
        } else {
            if (!firstCost.equals(other.firstCost)) {
                return false;
            }
        }
        if (firstSoldDt == null) {
            if (other.firstSoldDt!= null) {
                return false;
            }
        } else {
            if (!firstSoldDt.equals(other.firstSoldDt)) {
                return false;
            }
        }
        if (stockingDt == null) {
            if (other.stockingDt!= null) {
                return false;
            }
        } else {
            if (!stockingDt.equals(other.stockingDt)) {
                return false;
            }
        }
        if (classCode == null) {
            if (other.classCode!= null) {
                return false;
            }
        } else {
            if (!classCode.equals(other.classCode)) {
                return false;
            }
        }
        if (negativeQtySiteNo == null) {
            if (other.negativeQtySiteNo!= null) {
                return false;
            }
        } else {
            if (!negativeQtySiteNo.equals(other.negativeQtySiteNo)) {
                return false;
            }
        }
        if (purchaseVolumeId == null) {
            if (other.purchaseVolumeId!= null) {
                return false;
            }
        } else {
            if (!purchaseVolumeId.equals(other.purchaseVolumeId)) {
                return false;
            }
        }
        if (reorderSiteNo == null) {
            if (other.reorderSiteNo!= null) {
                return false;
            }
        } else {
            if (!reorderSiteNo.equals(other.reorderSiteNo)) {
                return false;
            }
        }
        if (productStartDt == null) {
            if (other.productStartDt!= null) {
                return false;
            }
        } else {
            if (!productStartDt.equals(other.productStartDt)) {
                return false;
            }
        }
        if (productEndDt == null) {
            if (other.productEndDt!= null) {
                return false;
            }
        } else {
            if (!productEndDt.equals(other.productEndDt)) {
                return false;
            }
        }
        if (lastReplenishmentDt == null) {
            if (other.lastReplenishmentDt!= null) {
                return false;
            }
        } else {
            if (!lastReplenishmentDt.equals(other.lastReplenishmentDt)) {
                return false;
            }
        }
        if (replicatePricesFl == null) {
            if (other.replicatePricesFl!= null) {
                return false;
            }
        } else {
            if (!replicatePricesFl.equals(other.replicatePricesFl)) {
                return false;
            }
        }
        if (lastPricechgTime == null) {
            if (other.lastPricechgTime!= null) {
                return false;
            }
        } else {
            if (!lastPricechgTime.equals(other.lastPricechgTime)) {
                return false;
            }
        }
        if (avgDlySales == null) {
            if (other.avgDlySales!= null) {
                return false;
            }
        } else {
            if (!avgDlySales.equals(other.avgDlySales)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        // If any ID columns are null, compare just the object instance it self.  This preserves objects that have not been assigned ID's yet.
        if ((this.identifier.getSkuNo() == null)||(this.identifier.getSiteNo() == null)) {
            return super.hashCode();
        }
        	
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier.getSkuNo() == null) ? 0 : identifier.getSkuNo().hashCode());
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
    public int compareTo(InvbysitFullEntity other) {
        return this.identifier.compareTo(other.identifier);
    }

    /**
     * This method displays bean properties for debugging purposes.  Do not use for anything else as it may change to enhance debugging in the future.
     * 
     */
    public String toString() {
        final StringBuilder temp = new StringBuilder();
        	
        temp.append("\n");
        temp.append("InvbysitFullEntity (INVBYSIT)\n");
        temp.append("       1 : promoKeyNo                     : PROMO_KEY_NO                   : ").append(promoKeyNo).append("\n");
        temp.append("  ID   2 : skuNo                          : SKU_NO                         : ").append(this.identifier.getSkuNo()).append("\n");
        temp.append("  ID   3 : siteNo                         : SITE_NO                        : ").append(this.identifier.getSiteNo()).append("\n");
        temp.append("       4 : location1                      : LOCATION1                      : ").append(location1).append("\n");
        temp.append("       5 : location2                      : LOCATION2                      : ").append(location2).append("\n");
        temp.append("       6 : minLevel                       : MIN_LEVEL                      : ").append(minLevel).append("\n");
        temp.append("       7 : maxLevel                       : MAX_LEVEL                      : ").append(maxLevel).append("\n");
        temp.append("       8 : reorderPt                      : REORDER_PT                     : ").append(reorderPt).append("\n");
        temp.append("       9 : reorderTo                      : REORDER_TO                     : ").append(reorderTo).append("\n");
        temp.append("      10 : priceType                      : PRICE_TYPE                     : ").append(priceType).append("\n");
        temp.append("      11 : unitPrice1                     : UNIT_PRICE1                    : ").append(unitPrice1).append("\n");
        temp.append("      12 : unitPrice2                     : UNIT_PRICE2                    : ").append(unitPrice2).append("\n");
        temp.append("      13 : unitPrice3                     : UNIT_PRICE3                    : ").append(unitPrice3).append("\n");
        temp.append("      14 : unitPrice4                     : UNIT_PRICE4                    : ").append(unitPrice4).append("\n");
        temp.append("      15 : unitPrice5                     : UNIT_PRICE5                    : ").append(unitPrice5).append("\n");
        temp.append("      16 : unitPrice6                     : UNIT_PRICE6                    : ").append(unitPrice6).append("\n");
        temp.append("      17 : unitPrice7                     : UNIT_PRICE7                    : ").append(unitPrice7).append("\n");
        temp.append("      18 : unitPrice8                     : UNIT_PRICE8                    : ").append(unitPrice8).append("\n");
        temp.append("      19 : unitPrice9                     : UNIT_PRICE9                    : ").append(unitPrice9).append("\n");
        temp.append("      20 : unitPrice10                    : UNIT_PRICE10                   : ").append(unitPrice10).append("\n");
        temp.append("      21 : priceQty1                      : PRICE_QTY1                     : ").append(priceQty1).append("\n");
        temp.append("      22 : priceQty2                      : PRICE_QTY2                     : ").append(priceQty2).append("\n");
        temp.append("      23 : priceQty3                      : PRICE_QTY3                     : ").append(priceQty3).append("\n");
        temp.append("      24 : priceQty4                      : PRICE_QTY4                     : ").append(priceQty4).append("\n");
        temp.append("      25 : priceQty5                      : PRICE_QTY5                     : ").append(priceQty5).append("\n");
        temp.append("      26 : priceQty6                      : PRICE_QTY6                     : ").append(priceQty6).append("\n");
        temp.append("      27 : priceQty7                      : PRICE_QTY7                     : ").append(priceQty7).append("\n");
        temp.append("      28 : priceQty8                      : PRICE_QTY8                     : ").append(priceQty8).append("\n");
        temp.append("      29 : priceQty9                      : PRICE_QTY9                     : ").append(priceQty9).append("\n");
        temp.append("      30 : priceQty10                     : PRICE_QTY10                    : ").append(priceQty10).append("\n");
        temp.append("      31 : price1                         : PRICE1                         : ").append(price1).append("\n");
        temp.append("      32 : price2                         : PRICE2                         : ").append(price2).append("\n");
        temp.append("      33 : price3                         : PRICE3                         : ").append(price3).append("\n");
        temp.append("      34 : price4                         : PRICE4                         : ").append(price4).append("\n");
        temp.append("      35 : price5                         : PRICE5                         : ").append(price5).append("\n");
        temp.append("      36 : price6                         : PRICE6                         : ").append(price6).append("\n");
        temp.append("      37 : price7                         : PRICE7                         : ").append(price7).append("\n");
        temp.append("      38 : price8                         : PRICE8                         : ").append(price8).append("\n");
        temp.append("      39 : price9                         : PRICE9                         : ").append(price9).append("\n");
        temp.append("      40 : price10                        : PRICE10                        : ").append(price10).append("\n");
        temp.append("      41 : epoPct                         : EPO_PCT                        : ").append(epoPct).append("\n");
        temp.append("      42 : msrpUnitPrice                  : MSRP_UNIT_PRICE                : ").append(msrpUnitPrice).append("\n");
        temp.append("      43 : msrpPrice                      : MSRP_PRICE                     : ").append(msrpPrice).append("\n");
        temp.append("      44 : msrpQty                        : MSRP_QTY                       : ").append(msrpQty).append("\n");
        temp.append("      45 : pkgPriceQty1                   : PKG_PRICE_QTY1                 : ").append(pkgPriceQty1).append("\n");
        temp.append("      46 : pkgPriceQty2                   : PKG_PRICE_QTY2                 : ").append(pkgPriceQty2).append("\n");
        temp.append("      47 : pkgPriceQty3                   : PKG_PRICE_QTY3                 : ").append(pkgPriceQty3).append("\n");
        temp.append("      48 : pkgPrice1                      : PKG_PRICE1                     : ").append(pkgPrice1).append("\n");
        temp.append("      49 : pkgPrice2                      : PKG_PRICE2                     : ").append(pkgPrice2).append("\n");
        temp.append("      50 : pkgPrice3                      : PKG_PRICE3                     : ").append(pkgPrice3).append("\n");
        temp.append("      51 : preMarkdownPrice               : PRE_MARKDOWN_PRICE             : ").append(preMarkdownPrice).append("\n");
        temp.append("      52 : noOfFacings                    : NO_OF_FACINGS                  : ").append(noOfFacings).append("\n");
        temp.append("      53 : shelfVelocity                  : SHELF_VELOCITY                 : ").append(shelfVelocity).append("\n");
        temp.append("      54 : commissionCd                   : COMMISSION_CD                  : ").append(commissionCd).append("\n");
        temp.append("      55 : invCost                        : INV_COST                       : ").append(invCost).append("\n");
        temp.append("      56 : standardCost                   : STANDARD_COST                  : ").append(standardCost).append("\n");
        temp.append("      57 : avgAge                         : AVG_AGE                        : ").append(avgAge).append("\n");
        temp.append("      58 : firstRcvdDt                    : FIRST_RCVD_DT                  : ").append(firstRcvdDt).append("\n");
        temp.append("      59 : lastRcvdDt                     : LAST_RCVD_DT                   : ").append(lastRcvdDt).append("\n");
        temp.append("      60 : qtyLastRcvd                    : QTY_LAST_RCVD                  : ").append(qtyLastRcvd).append("\n");
        temp.append("      61 : lastOrdDt                      : LAST_ORD_DT                    : ").append(lastOrdDt).append("\n");
        temp.append("      62 : lastSoldDt                     : LAST_SOLD_DT                   : ").append(lastSoldDt).append("\n");
        temp.append("      63 : lastSoldPreviousDt             : LAST_SOLD_PREVIOUS_DT          : ").append(lastSoldPreviousDt).append("\n");
        temp.append("      64 : qtyOnHand                      : QTY_ON_HAND                    : ").append(qtyOnHand).append("\n");
        temp.append("      65 : qtyRsvd                        : QTY_RSVD                       : ").append(qtyRsvd).append("\n");
        temp.append("      66 : qtyOnOrd                       : QTY_ON_ORD                     : ").append(qtyOnOrd).append("\n");
        temp.append("      67 : qtyOnBackord                   : QTY_ON_BACKORD                 : ").append(qtyOnBackord).append("\n");
        temp.append("      68 : qtyInTransit                   : QTY_IN_TRANSIT                 : ").append(qtyInTransit).append("\n");
        temp.append("      69 : qtyRtv                         : QTY_RTV                        : ").append(qtyRtv).append("\n");
        temp.append("      70 : expRcvdDt                      : EXP_RCVD_DT                    : ").append(expRcvdDt).append("\n");
        temp.append("      71 : expBoDt                        : EXP_BO_DT                      : ").append(expBoDt).append("\n");
        temp.append("      72 : qtyCounted                     : QTY_COUNTED                    : ").append(qtyCounted).append("\n");
        temp.append("      73 : maxOrderQty                    : MAX_ORDER_QTY                  : ").append(maxOrderQty).append("\n");
        temp.append("      74 : mixMatchNo                     : MIX_MATCH_NO                   : ").append(mixMatchNo).append("\n");
        temp.append("      75 : bottleLinkNo                   : BOTTLE_LINK_NO                 : ").append(bottleLinkNo).append("\n");
        temp.append("      76 : lastPrice                      : LAST_PRICE                     : ").append(lastPrice).append("\n");
        temp.append("      77 : lastPchgDt                     : LAST_PCHG_DT                   : ").append(lastPchgDt).append("\n");
        temp.append("      78 : downloadFl                     : DOWNLOAD_FL                    : ").append(downloadFl).append("\n");
        temp.append("      79 : allowPchgFl                    : ALLOW_PCHG_FL                  : ").append(allowPchgFl).append("\n");
        temp.append("      80 : cosPct                         : COS_PCT                        : ").append(cosPct).append("\n");
        temp.append("      81 : supersededFl                   : SUPERSEDED_FL                  : ").append(supersededFl).append("\n");
        temp.append("      82 : priorReorderPt                 : PRIOR_REORDER_PT               : ").append(priorReorderPt).append("\n");
        temp.append("      83 : priorReorderTo                 : PRIOR_REORDER_TO               : ").append(priorReorderTo).append("\n");
        temp.append("      84 : warrantyInvCost                : WARRANTY_INV_COST              : ").append(warrantyInvCost).append("\n");
        temp.append("      85 : warrantyQtyOnHand              : WARRANTY_QTY_ON_HAND           : ").append(warrantyQtyOnHand).append("\n");
        temp.append("      86 : weightedLeadTime               : WEIGHTED_LEAD_TIME             : ").append(weightedLeadTime).append("\n");
        temp.append("      87 : coreSellPrice                  : CORE_SELL_PRICE                : ").append(coreSellPrice).append("\n");
        temp.append("      88 : moDemandForecast               : MO_DEMAND_FORECAST             : ").append(moDemandForecast).append("\n");
        temp.append("      89 : coreInvCost                    : CORE_INV_COST                  : ").append(coreInvCost).append("\n");
        temp.append("      90 : coreQtyOnHand                  : CORE_QTY_ON_HAND               : ").append(coreQtyOnHand).append("\n");
        temp.append("      91 : skuStatusId                    : SKU_STATUS_ID                  : ").append(skuStatusId).append("\n");
        temp.append("      92 : nonDiscountFl                  : NON_DISCOUNT_FL                : ").append(nonDiscountFl).append("\n");
        temp.append("      93 : restrictSaleId                 : RESTRICT_SALE_ID               : ").append(restrictSaleId).append("\n");
        temp.append("      94 : labelCd                        : LABEL_CD                       : ").append(labelCd).append("\n");
        temp.append("      95 : lastPriceIncDt                 : LAST_PRICE_INC_DT              : ").append(lastPriceIncDt).append("\n");
        temp.append("      96 : lastPriceDecDt                 : LAST_PRICE_DEC_DT              : ").append(lastPriceDecDt).append("\n");
        temp.append("      97 : lastCycleDt                    : LAST_CYCLE_DT                  : ").append(lastCycleDt).append("\n");
        temp.append("      98 : lastPriceVerDt                 : LAST_PRICE_VER_DT              : ").append(lastPriceVerDt).append("\n");
        temp.append("      99 : lastChangeDt                   : LAST_CHANGE_DT                 : ").append(lastChangeDt).append("\n");
        temp.append("     100 : newSkuStatusId                 : NEW_SKU_STATUS_ID              : ").append(newSkuStatusId).append("\n");
        temp.append("     101 : itemizerStatusId               : ITEMIZER_STATUS_ID             : ").append(itemizerStatusId).append("\n");
        temp.append("     102 : scanDeptNo                     : SCAN_DEPT_NO                   : ").append(scanDeptNo).append("\n");
        temp.append("     103 : scanSubdeptNo                  : SCAN_SUBDEPT_NO                : ").append(scanSubdeptNo).append("\n");
        temp.append("     104 : downloadNToYFl                 : DOWNLOAD_N_TO_Y_FL             : ").append(downloadNToYFl).append("\n");
        temp.append("     105 : labelQty1                      : LABEL_QTY1                     : ").append(labelQty1).append("\n");
        temp.append("     106 : labelQty2                      : LABEL_QTY2                     : ").append(labelQty2).append("\n");
        temp.append("     107 : warehouseInnerPack             : WAREHOUSE_INNER_PACK           : ").append(warehouseInnerPack).append("\n");
        temp.append("     108 : lastXferDt                     : LAST_XFER_DT                   : ").append(lastXferDt).append("\n");
        temp.append("     109 : priceOptionCd                  : PRICE_OPTION_CD                : ").append(priceOptionCd).append("\n");
        temp.append("     110 : priceAttributeCd               : PRICE_ATTRIBUTE_CD             : ").append(priceAttributeCd).append("\n");
        temp.append("     111 : promoRecordNo                  : PROMO_RECORD_NO                : ").append(promoRecordNo).append("\n");
        temp.append("     112 : lastVendorCost                 : LAST_VENDOR_COST               : ").append(lastVendorCost).append("\n");
        temp.append("     113 : avgMovement                    : AVG_MOVEMENT                   : ").append(avgMovement).append("\n");
        temp.append("     114 : haloGm                         : HALO_GM                        : ").append(haloGm).append("\n");
        temp.append("     115 : laloGm                         : LALO_GM                        : ").append(laloGm).append("\n");
        temp.append("     116 : replenishmentFl                : REPLENISHMENT_FL               : ").append(replenishmentFl).append("\n");
        temp.append("     117 : velocityCd                     : VELOCITY_CD                    : ").append(velocityCd).append("\n");
        temp.append("     118 : velocityLockedFl               : VELOCITY_LOCKED_FL             : ").append(velocityLockedFl).append("\n");
        temp.append("     119 : velocityChangeDt               : VELOCITY_CHANGE_DT             : ").append(velocityChangeDt).append("\n");
        temp.append("     120 : reorderLockedFl                : REORDER_LOCKED_FL              : ").append(reorderLockedFl).append("\n");
        temp.append("     121 : reorderNowFl                   : REORDER_NOW_FL                 : ").append(reorderNowFl).append("\n");
        temp.append("     122 : profileData                    : PROFILE_DATA                   : ").append(profileData).append("\n");
        temp.append("     123 : replicationNo                  : REPLICATION_NO                 : ").append(replicationNo).append("\n");
        temp.append("     124 : operationType                  : OPERATION_TYPE                 : ").append(operationType).append("\n");
        temp.append("  AD 125 : dateChanged                    : DATE_CHANGED                   : ").append(dateChanged).append("\n");
        temp.append("     126 : registerReplicationNo          : REGISTER_REPLICATION_NO        : ").append(registerReplicationNo).append("\n");
        temp.append("     127 : reorderpointSourceCd           : REORDERPOINT_SOURCE_CD         : ").append(reorderpointSourceCd).append("\n");
        temp.append("     128 : defaultDcSkuLevel              : DEFAULT_DC_SKU_LEVEL           : ").append(defaultDcSkuLevel).append("\n");
        temp.append("     129 : priceCalcId                    : PRICE_CALC_ID                  : ").append(priceCalcId).append("\n");
        temp.append("     130 : profileDataFl                  : PROFILE_DATA_FL                : ").append(profileDataFl).append("\n");
        temp.append("     131 : discontinuedStFl               : DISCONTINUED_ST_FL             : ").append(discontinuedStFl).append("\n");
        temp.append("     132 : discontinuedDt                 : DISCONTINUED_DT                : ").append(discontinuedDt).append("\n");
        temp.append("     133 : discontinuedBy                 : DISCONTINUED_BY                : ").append(discontinuedBy).append("\n");
        temp.append("     134 : tareTableNo                    : TARE_TABLE_NO                  : ").append(tareTableNo).append("\n");
        temp.append("     135 : promoCommissionCd              : PROMO_COMMISSION_CD            : ").append(promoCommissionCd).append("\n");
        temp.append("     136 : additionalTax                  : ADDITIONAL_TAX                 : ").append(additionalTax).append("\n");
        temp.append("     137 : negativeQohFl                  : NEGATIVE_QOH_FL                : ").append(negativeQohFl).append("\n");
        temp.append("     138 : posSkuStatusId                 : POS_SKU_STATUS_ID              : ").append(posSkuStatusId).append("\n");
        temp.append("     139 : vendorItemSeqNo                : VENDOR_ITEM_SEQ_NO             : ").append(vendorItemSeqNo).append("\n");
        temp.append("     140 : replacementCostAmt             : REPLACEMENT_COST_AMT           : ").append(replacementCostAmt).append("\n");
        temp.append("     141 : firstPrice                     : FIRST_PRICE                    : ").append(firstPrice).append("\n");
        temp.append("     142 : firstCost                      : FIRST_COST                     : ").append(firstCost).append("\n");
        temp.append("     143 : firstSoldDt                    : FIRST_SOLD_DT                  : ").append(firstSoldDt).append("\n");
        temp.append("     144 : stockingDt                     : STOCKING_DT                    : ").append(stockingDt).append("\n");
        temp.append("     145 : classCode                      : CLASS_CODE                     : ").append(classCode).append("\n");
        temp.append("     146 : negativeQtySiteNo              : NEGATIVE_QTY_SITE_NO           : ").append(negativeQtySiteNo).append("\n");
        temp.append("     147 : purchaseVolumeId               : PURCHASE_VOLUME_ID             : ").append(purchaseVolumeId).append("\n");
        temp.append("     148 : reorderSiteNo                  : REORDER_SITE_NO                : ").append(reorderSiteNo).append("\n");
        temp.append("     149 : productStartDt                 : PRODUCT_START_DT               : ").append(productStartDt).append("\n");
        temp.append("     150 : productEndDt                   : PRODUCT_END_DT                 : ").append(productEndDt).append("\n");
        temp.append("     151 : lastReplenishmentDt            : LAST_REPLENISHMENT_DT          : ").append(lastReplenishmentDt).append("\n");
        temp.append("     152 : replicatePricesFl              : REPLICATE_PRICES_FL            : ").append(replicatePricesFl).append("\n");
        temp.append("     153 : lastPricechgTime               : LAST_PRICECHG_TIME             : ").append(lastPricechgTime).append("\n");
        temp.append("     154 : avgDlySales                    : AVG_DLY_SALES                  : ").append(avgDlySales).append("\n");
        	
        return temp.toString();
    }

}
