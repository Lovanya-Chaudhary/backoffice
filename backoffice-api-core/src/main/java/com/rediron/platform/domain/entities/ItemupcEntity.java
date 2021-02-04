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
import com.rediron.platform.domain.rnet.EntityManagerContext;
import com.rediron.platform.domain.rnet.RNetAbstractEntity;
import com.tomax.api.DataTransferObject;
import com.tomax.api.RNetExceptionProxy;
import com.tomax.api.RNetUnexpectedException;
import com.tomax.api.annotation.Parent;
import com.tomax.inventory.dto.InvtoryFullDTO;
import com.tomax.inventory.dto.ItemupcDTO;
import com.tomax.inventory.dto.ItemupcDTOId;


@SuppressWarnings("all")
//@NamedQueries({
//    @NamedQuery(name = "GET_PRIMARY_UPC_FOR_SKU", query = "Select i From ItemupcEntity i Where i.skuNo = :skuNo And typeCd = 'U' and upcSeq = 1 "),
//    @NamedQuery(name = "GET_ITEM_UPC_FOR_SKU_TYPECD", query = "Select i From ItemupcEntity i Where i.skuNo = :skuNo And typeCd = :typeCd "),
//    @NamedQuery(name = "GET_ITEM_UPC_FOR_SKU", query = "Select i From ItemupcEntity i Where i.skuNo = :skuNo order by i.upcSeq  "),
//    @NamedQuery(name = "GET_ITEM_UPC_FOR_SKUS_TYPECD", query = "Select i From ItemupcEntity i Where i.skuNo in (:skuNos) And typeCd = :typeCd "),
//    @NamedQuery(name = "GET_ITEM_UPC_FOR_UPCID_MODIFIER", query = "Select i From ItemupcEntity i Where i.upcId = :upcId And i.upcModifier = :upcModifier "),
//    @NamedQuery(name = "GET_ITEM_UPC_BY_UPC_ID", query = "Select i From ItemupcEntity i Where i.upcId = :upcId ")
//})
@Entity
@Table(name = "ITEMUPC")
public class ItemupcEntity
    extends RNetAbstractEntity<ItemupcEntity, ItemupcDTO>
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
    private ItemupcEntityId identifier;
    /**
     * <p>Bidirectional ManyToOne owning relationship to {@link InvtoryFullEntity InvtoryFullEntity}.</p><p><b>Column Mapping:</b><br/>
     * ITEMUPC.SKU_NO -> INVTORY.SKU_NO<br>
     * </p>
     * 
     */
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "SKU_NO", referencedColumnName = "SKU_NO", insertable = false, updatable = false)
    })
    private InvtoryFullEntity invtory;
    /**
     * <p>Maps to table column: {@code PRICE_ADJ_AMT}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 4, fraction = 2, message = "PRICE_ADJ_AMT should have an integer component no longer than 4 and an a fraction component of exact size 2")
    @Column(name = "PRICE_ADJ_AMT")
    private BigDecimal priceAdjAmt;
    /**
     * <p>Maps to table column: {@code UPC_SEQ}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 2, fraction = 0, message = "UPC_SEQ should have an integer component no longer than 2 and an a fraction component of exact size 0")
    @Column(name = "UPC_SEQ")
    private Integer upcSeq;
    /**
     * <p>Maps to table column: {@code TYPE_CD}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "TYPE_CD should contain less than or equal to 1 characters")
    @Column(name = "TYPE_CD")
    private String typeCd;
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
     * <p>Maps to table column: {@code UCC14_UOM}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 6, message = "UCC14_UOM should contain less than or equal to 6 characters")
    @Column(name = "UCC14_UOM")
    private String ucc14Uom;
    /**
     * <p>Maps to table column: {@code UCC14_QTY}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 6, fraction = 2, message = "UCC14_QTY should have an integer component no longer than 6 and an a fraction component of exact size 2")
    @Column(name = "UCC14_QTY")
    private BigDecimal ucc14Qty;
    /**
     * <p>Static value for named query 'GET_PRIMARY_UPC_FOR_SKU'<p>
     * <p>JPA Query:
     * </p>{@code Select i From ItemupcEntity i Where i.skuNo = :skuNo And typeCd = 'U' and upcSeq = 1 }
     * 
     */
    public final static String GET_PRIMARY_UPC_FOR_SKU = "GET_PRIMARY_UPC_FOR_SKU";
    /**
     * <p>Static value for named query 'GET_ITEM_UPC_FOR_SKU_TYPECD'<p>
     * <p>JPA Query:
     * </p>{@code Select i From ItemupcEntity i Where i.skuNo = :skuNo And typeCd = :typeCd }
     * 
     */
    public final static String GET_ITEM_UPC_FOR_SKU_TYPECD = "GET_ITEM_UPC_FOR_SKU_TYPECD";
    /**
     * <p>Static value for named query 'GET_ITEM_UPC_FOR_SKU'<p>
     * <p>JPA Query:
     * </p>{@code Select i From ItemupcEntity i Where i.skuNo = :skuNo order by i.upcSeq  }
     * 
     */
    public final static String GET_ITEM_UPC_FOR_SKU = "GET_ITEM_UPC_FOR_SKU";
    /**
     * <p>Static value for named query 'GET_ITEM_UPC_FOR_SKUS_TYPECD'<p>
     * <p>JPA Query:
     * </p>{@code Select i From ItemupcEntity i Where i.skuNo in (:skuNos) And typeCd = :typeCd }
     * 
     */
    public final static String GET_ITEM_UPC_FOR_SKUS_TYPECD = "GET_ITEM_UPC_FOR_SKUS_TYPECD";
    /**
     * <p>Static value for named query 'GET_ITEM_UPC_FOR_UPCID_MODIFIER'<p>
     * <p>JPA Query:
     * </p>{@code Select i From ItemupcEntity i Where i.upcId = :upcId And i.upcModifier = :upcModifier }
     * 
     */
    public final static String GET_ITEM_UPC_FOR_UPCID_MODIFIER = "GET_ITEM_UPC_FOR_UPCID_MODIFIER";
    /**
     * <p>Static value for named query 'GET_ITEM_UPC_BY_UPC_ID'<p>
     * <p>JPA Query:
     * </p>{@code Select i From ItemupcEntity i Where i.upcId = :upcId }
     * 
     */
    public final static String GET_ITEM_UPC_BY_UPC_ID = "GET_ITEM_UPC_BY_UPC_ID";

    /**
     * <P>Empty constructor used to create new ItemupcEntity.</P>
     * 
     */
    public ItemupcEntity() {
        this.identifier = new ItemupcEntityId();
        	
        _init();
    }

    /**
     * Constructor used to create a new ItemupcEntity where the identifier will be created by copying the fields of the provided sourceId.
     * 
     */
    public ItemupcEntity(ItemupcEntityId sourceId) {
        this.identifier = new ItemupcEntityId(sourceId.getUpcId(), sourceId.getSkuNo(), sourceId.getUpcModifier());
        	
        _init();
    }

    /**
     * Constructor used to create a ItemupcEntity where the identifier will be created internally using the id arguments provided.  This is a convenient constructor used instead of instantiating a new identifier.
     * 
     */
    public ItemupcEntity(String upcId, Integer skuNo, Integer upcModifier) {
        this.identifier = new ItemupcEntityId(upcId, skuNo, upcModifier);
        	
        _init();
    }

    /**
     * Constructor used to create a ItemupcEntity where contents are provided by an object array typically returned by a native query
     * 
     */
    public ItemupcEntity(Object[] objectArray) {
        this.identifier = new ItemupcEntityId(((objectArray[ 0 ] == null)?null:((String) objectArray[ 0 ])), ((objectArray[ 1 ] == null)?null:((BigDecimal) objectArray[ 1 ]).intValue()), ((objectArray[ 3 ] == null)?null:((BigDecimal) objectArray[ 3 ]).intValue()));
        	
        this.priceAdjAmt = ((objectArray[ 2 ] == null)?null:((BigDecimal) objectArray[ 2 ]));
        this.upcSeq = ((objectArray[ 4 ] == null)?null:((BigDecimal) objectArray[ 4 ]).intValue());
        this.typeCd = ((objectArray[ 5 ] == null)?null:((String) objectArray[ 5 ]));
        this.replicationNo = ((objectArray[ 6 ] == null)?null:((BigDecimal) objectArray[ 6 ]).longValue());
        this.operationType = ((objectArray[ 7 ] == null)?null:((String) objectArray[ 7 ]));
        this.registerReplicationNo = ((objectArray[ 8 ] == null)?null:((BigDecimal) objectArray[ 8 ]).longValue());
        this.dateCreated = ((objectArray[ 9 ] == null)?null:new Date(((Timestamp) objectArray[ 9 ]).getTime()));
        this.userCreated = ((objectArray[ 10 ] == null)?null:((String) objectArray[ 10 ]));
        this.dateModified = ((objectArray[ 11 ] == null)?null:new Date(((Timestamp) objectArray[ 11 ]).getTime()));
        this.userModified = ((objectArray[ 12 ] == null)?null:((String) objectArray[ 12 ]));
        this.ucc14Uom = ((objectArray[ 13 ] == null)?null:((String) objectArray[ 13 ]));
        this.ucc14Qty = ((objectArray[ 14 ] == null)?null:((BigDecimal) objectArray[ 14 ]));
        	
        _init();
    }

    /**
     * <P>Copy constructor used to copy a ItemupcEntity where the identifier will be created by copying the fields of the provided sourceId.</P>
     * 
     */
    public ItemupcEntity(ItemupcEntity source, ItemupcEntityId sourceId) {
        this.identifier = new ItemupcEntityId(sourceId.getUpcId(), sourceId.getSkuNo(), sourceId.getUpcModifier());
        	
        _init();
        	
        this.priceAdjAmt = source.getPriceAdjAmt();
        this.upcSeq = source.getUpcSeq();
        this.typeCd = source.getTypeCd();
        this.replicationNo = source.getReplicationNo();
        this.operationType = source.getOperationType();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.ucc14Uom = source.getUcc14Uom();
        this.ucc14Qty = source.getUcc14Qty();
    }

    /**
     * <P>Copy constructor used to copy a ItemupcEntity including its identifier.</P>
     * 
     */
    public ItemupcEntity(ItemupcEntity source) {
        this.identifier = new ItemupcEntityId(source.getIdentifier().getUpcId(), source.getIdentifier().getSkuNo(), source.getIdentifier().getUpcModifier());
        	
        _init();
        	
        this.priceAdjAmt = source.getPriceAdjAmt();
        this.upcSeq = source.getUpcSeq();
        this.typeCd = source.getTypeCd();
        this.replicationNo = source.getReplicationNo();
        this.operationType = source.getOperationType();
        this.registerReplicationNo = source.getRegisterReplicationNo();
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.ucc14Uom = source.getUcc14Uom();
        this.ucc14Qty = source.getUcc14Qty();
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public ItemupcEntityId getIdentifier() {
        return this.identifier;
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public void setIdentifier(ItemupcEntityId identifier) {
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
     * Getter for {@link ItemupcEntityId#upcId identifier.upcId}
     * 
     */
    @Access(AccessType.PROPERTY)
    @Column(name = "UPC_ID", insertable = false, updatable = false)
    public String getUpcId() {
        return this.identifier.getUpcId();
    }

    /**
     * Setter for {@link ItemupcEntityId#upcId identifier.upcId}
     * 
     */
    public String setUpcId(String upcId) {
        this.identifier.setUpcId(upcId);
        return this.identifier.getUpcId();
    }

    /**
     * Getter for {@link ItemupcEntityId#skuNo identifier.skuNo}
     * 
     */
    @Access(AccessType.PROPERTY)
    @Column(name = "SKU_NO", insertable = false, updatable = false)
    public Integer getSkuNo() {
        return this.identifier.getSkuNo();
    }

    /**
     * Setter for {@link ItemupcEntityId#skuNo identifier.skuNo}
     * 
     */
    public Integer setSkuNo(Integer skuNo) {
        this.identifier.setSkuNo(skuNo);
        return this.identifier.getSkuNo();
    }

    /**
     * Getter for {@link #priceAdjAmt priceAdjAmt}
     * 
     */
    public BigDecimal getPriceAdjAmt() {
        return this.priceAdjAmt;
    }

    /**
     * Setter for {@link #priceAdjAmt priceAdjAmt}
     * 
     */
    public BigDecimal setPriceAdjAmt(BigDecimal priceAdjAmt) {
        if ((priceAdjAmt!= null)&&(priceAdjAmt.scale()!= 2)) {
            priceAdjAmt = priceAdjAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.priceAdjAmt = priceAdjAmt;
        return this.priceAdjAmt;
    }

    /**
     * Getter for {@link ItemupcEntityId#upcModifier identifier.upcModifier}
     * 
     */
    @Access(AccessType.PROPERTY)
    @Column(name = "UPC_MODIFIER", insertable = false, updatable = false)
    public Integer getUpcModifier() {
        return this.identifier.getUpcModifier();
    }

    /**
     * Setter for {@link ItemupcEntityId#upcModifier identifier.upcModifier}
     * 
     */
    public Integer setUpcModifier(Integer upcModifier) {
        this.identifier.setUpcModifier(upcModifier);
        return this.identifier.getUpcModifier();
    }

    /**
     * Getter for {@link #upcSeq upcSeq}
     * 
     */
    public Integer getUpcSeq() {
        return this.upcSeq;
    }

    /**
     * Setter for {@link #upcSeq upcSeq}
     * 
     */
    public Integer setUpcSeq(Integer upcSeq) {
        this.upcSeq = upcSeq;
        return this.upcSeq;
    }

    /**
     * Getter for {@link #typeCd typeCd}
     * 
     */
    public String getTypeCd() {
        return this.typeCd;
    }

    /**
     * Setter for {@link #typeCd typeCd}
     * 
     */
    public String setTypeCd(String typeCd) {
        this.typeCd = typeCd;
        return this.typeCd;
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
     * Getter for {@link #ucc14Uom ucc14Uom}
     * 
     */
    public String getUcc14Uom() {
        return this.ucc14Uom;
    }

    /**
     * Setter for {@link #ucc14Uom ucc14Uom}
     * 
     */
    public String setUcc14Uom(String ucc14Uom) {
        this.ucc14Uom = ucc14Uom;
        return this.ucc14Uom;
    }

    /**
     * Getter for {@link #ucc14Qty ucc14Qty}
     * 
     */
    public BigDecimal getUcc14Qty() {
        return this.ucc14Qty;
    }

    /**
     * Setter for {@link #ucc14Qty ucc14Qty}
     * 
     */
    public BigDecimal setUcc14Qty(BigDecimal ucc14Qty) {
        if ((ucc14Qty!= null)&&(ucc14Qty.scale()!= 2)) {
            ucc14Qty = ucc14Qty.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        this.ucc14Qty = ucc14Qty;
        return this.ucc14Qty;
    }

    /**
     * @hide<P><I>Not for public use.</I></P>
     * 
     */
    private void _initDefaults() {
        if (this.priceAdjAmt == null) {
            this.priceAdjAmt = new BigDecimal("0.00");
        }
        if (this.upcSeq == null) {
            this.upcSeq = Integer.valueOf(1);
        }
        if (this.typeCd == null) {
            this.typeCd = "U";
        }
    }

    /**
     * @hide<P><I>Not for public use.</I></P><P>Initializes internal collections.</P>
     * 
     */
    private void _init() {
    }

    /**
     * <P>Used to copy a ItemupcEntity including non-identifier fields only.</P>
     * 
     */
    public ItemupcEntity copyData() {
        final ItemupcEntity copy = new ItemupcEntity();
        	
        copy.priceAdjAmt = this.priceAdjAmt;
        copy.upcSeq = this.upcSeq;
        copy.typeCd = this.typeCd;
        copy.replicationNo = this.replicationNo;
        copy.operationType = this.operationType;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.ucc14Uom = this.ucc14Uom;
        copy.ucc14Qty = this.ucc14Qty;
        	
        return copy;
    }

    /**
     * <P>Used to copy a ItemupcEntity including its identifier.</P>
     * 
     */
    public ItemupcEntity copyDataAndId() {
        final ItemupcEntity copy = new ItemupcEntity();
        	
        copy.getIdentifier().setUpcId(this.getIdentifier().getUpcId());
        copy.getIdentifier().setSkuNo(this.getIdentifier().getSkuNo());
        copy.getIdentifier().setUpcModifier(this.getIdentifier().getUpcModifier());
        	
        copy.priceAdjAmt = this.priceAdjAmt;
        copy.upcSeq = this.upcSeq;
        copy.typeCd = this.typeCd;
        copy.replicationNo = this.replicationNo;
        copy.operationType = this.operationType;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.ucc14Uom = this.ucc14Uom;
        copy.ucc14Qty = this.ucc14Qty;
        	
        return copy;
    }

    /**
     * <P>Used to copy a ItemupcEntity including data and GENERATED identifiers only.</P>
     * 
     */
    public ItemupcEntity copyDataAndIdNonGenerated() {
        final ItemupcEntity copy = new ItemupcEntity();
        	
        copy.getIdentifier().setUpcId(this.getIdentifier().getUpcId());
        copy.getIdentifier().setSkuNo(this.getIdentifier().getSkuNo());
        copy.getIdentifier().setUpcModifier(this.getIdentifier().getUpcModifier());
        	
        copy.priceAdjAmt = this.priceAdjAmt;
        copy.upcSeq = this.upcSeq;
        copy.typeCd = this.typeCd;
        copy.replicationNo = this.replicationNo;
        copy.operationType = this.operationType;
        copy.registerReplicationNo = this.registerReplicationNo;
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.ucc14Uom = this.ucc14Uom;
        copy.ucc14Qty = this.ucc14Qty;
        	
        return copy;
    }

    /**
     * @hide<P><I>Not for public use.</I></P>This postLoad method is used to fix BigDecimal values being loaded from the JDBC driver.  Oracle has a known jdbc bug where it truncates the scale of BigDecimal.  This method restores the scale after load so that BigDecimals comparators will work again.
     * 
     */
    private void _setScales() {
        if (this.priceAdjAmt!= null) {
            this.priceAdjAmt = this.priceAdjAmt.setScale(2);
        }
        if (this.ucc14Qty!= null) {
            this.ucc14Qty = this.ucc14Qty.setScale(2);
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

    public static ItemupcDTO toDTO(ItemupcEntity entity) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return ItemupcEntity.toDTO(entity, map);
    }

    public static ItemupcDTO toDTO(ItemupcEntity entity, Map<Object, Object> map) {
        try {
            if (entity == null) {
                return null;
            }
            	
            if (map == null) {
                map = new HashMap<Object, Object>();
            } else {
                if (map.get(entity)!= null) {
                    return ((ItemupcDTO) map.get(entity));
                }
            }
            	
            entity.toDTO(map);
            	
            return ((ItemupcDTO) map.get(entity));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public static ItemupcEntity toEntity(ItemupcDTO dto) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return ItemupcEntity.toEntity(dto, map);
    }

    public static ItemupcEntity toEntity(ItemupcDTO dto, Map<Object, Object> map) {
        try {
            if (dto == null) {
                return null;
            }
            	
            if ((map!= null)&&(map.get(dto)!= null)) {
                return ((ItemupcEntity) map.get(dto));
            }
            	
            return ((ItemupcEntity) DtoToEntity.toEntity(ItemupcEntity.class, ((DataTransferObject) dto)));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public void assignAllChildren(ItemupcDTO dto, Map<Object, Object> map) {
        toSimpleEntity(dto, map);
    }

    public void assignAllChildren(Map<Object, Object> map, Stack<Object> stack) {
        toSimpleDto(map);
    }

    public void assignAllParents(Map<Object, Object> map) {
        ItemupcDTO dto = toSimpleDto(map);
        	
        InvtoryFullDTO invtoryDto = ((InvtoryFullDTO) map.get(this.invtory));
        if (invtoryDto!= null) {
            dto.setInvtory(invtoryDto);
        } else {
            if (this.invtory!= null) {
                dto.setInvtory(this.invtory.toDTO(map));
            }
        }
        	
    }

    protected ItemupcDTO toSimpleDto(Map<Object, Object> map) {
        if (map.get(this)!= null) {
            return ((ItemupcDTO) map.get(this));
        }
        try {
            ItemupcDTO dto = new ItemupcDTO();
            dto.setIdentifier(new ItemupcDTOId(this.getUpcId(), this.getSkuNo(), this.getUpcModifier()));
            	
            dto.setPriceAdjAmt(this.getPriceAdjAmt());
            dto.setUpcSeq(this.getUpcSeq());
            dto.setTypeCd(this.getTypeCd());
            dto.setReplicationNo(this.getReplicationNo());
            dto.setOperationType(this.getOperationType());
            dto.setRegisterReplicationNo(this.getRegisterReplicationNo());
            dto.setDateCreated(this.getDateCreated());
            dto.setUserCreated(this.getUserCreated());
            dto.setDateModified(this.getDateModified());
            dto.setUserModified(this.getUserModified());
            dto.setUcc14Uom(this.getUcc14Uom());
            dto.setUcc14Qty(this.getUcc14Qty());
            	
            map.put(this, dto);
            	
            return dto;
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public ItemupcEntity toSimpleEntity(DataTransferObject dto, Map<Object, Object> map) {
        if (map.get(dto)!= null) {
            return ((ItemupcEntity) map.get(dto));
        }
        try {
            ItemupcDTO oldDto = ((ItemupcDTO) dto);
            ItemupcEntity entity = new ItemupcEntity();
            entity.setIdentifier(new ItemupcEntityId(oldDto.getUpcId(), oldDto.getSkuNo(), oldDto.getUpcModifier()));
            	
            entity.setPriceAdjAmt(oldDto.getPriceAdjAmt());
            entity.setUpcSeq(oldDto.getUpcSeq());
            entity.setTypeCd(oldDto.getTypeCd());
            entity.setReplicationNo(oldDto.getReplicationNo());
            entity.setOperationType(oldDto.getOperationType());
            entity.setRegisterReplicationNo(oldDto.getRegisterReplicationNo());
            entity.setDateCreated(oldDto.getDateCreated());
            entity.setUserCreated(oldDto.getUserCreated());
            entity.setDateModified(oldDto.getDateModified());
            entity.setUserModified(oldDto.getUserModified());
            entity.setUcc14Uom(oldDto.getUcc14Uom());
            entity.setUcc14Qty(oldDto.getUcc14Qty());
            	
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

    public static List<ItemupcDTO> toDTOList(Collection<ItemupcEntity> entities) {
        if (entities == null) {
            return null;
        }
        	
        List<ItemupcDTO> dtos = new ArrayList<ItemupcDTO>();
        for (ItemupcEntity entity: entities) {
            dtos.add(toDTO(entity));
        }
        	
        return dtos;
    }

    @Deprecated
    public static List<ItemupcDTO> toDTO(Collection<ItemupcEntity> entities) {
        return toDTOList(entities);
    }

    public static ItemupcDTOId toDTOId(ItemupcEntityId id) {
        if (id == null) {
            return null;
        }
        return new ItemupcDTOId(id.getUpcId(), id.getSkuNo(), id.getUpcModifier());
    }

    @Deprecated
    public static ItemupcDTOId toDTO(ItemupcEntityId id) {
        return toDTOId(id);
    }

    public static List<ItemupcDTOId> toDTOIdList(Collection<ItemupcEntityId> entityIds) {
        if (entityIds == null) {
            return null;
        }
        	
        List<ItemupcDTOId> dtoIds = new ArrayList<ItemupcDTOId>();
        for (ItemupcEntityId entity: entityIds) {
            dtoIds.add(toDTOId(entity));
        }
        	
        return dtoIds;
    }

    public static Set<ItemupcEntity> toEntitySet(Collection<ItemupcDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        Set<ItemupcEntity> entities = new HashSet<ItemupcEntity>();
        for (ItemupcDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static Set<ItemupcEntity> toEntity(Collection<ItemupcDTO> dtos) {
        return toEntitySet(dtos);
    }

    public static SortedSet<ItemupcEntity> toEntitySortedSet(Collection<ItemupcDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        SortedSet<ItemupcEntity> entities = new TreeSet<ItemupcEntity>();
        for (ItemupcDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static SortedSet<ItemupcEntity> toEntitySorted(Collection<ItemupcDTO> dtos) {
        return toEntitySortedSet(dtos);
    }

    public static List<ItemupcEntity> toEntityList(Collection<ItemupcDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        List<ItemupcEntity> entities = new ArrayList<ItemupcEntity>();
        for (ItemupcDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    public static ItemupcEntityId toEntityId(ItemupcDTOId dto) {
        if (dto == null) {
            return null;
        }
        return new ItemupcEntityId(dto.getUpcId(), dto.getSkuNo(), dto.getUpcModifier());
    }

    @Deprecated
    public static ItemupcEntityId toEntity(ItemupcDTOId id) {
        return toEntityId(id);
    }

    public static List<ItemupcEntityId> toEntityIdList(Collection<ItemupcDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        List<ItemupcEntityId> entityIds = new ArrayList<ItemupcEntityId>();
        for (ItemupcDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static Set<ItemupcEntityId> toEntityIdSet(Collection<ItemupcDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        Set<ItemupcEntityId> entityIds = new HashSet<ItemupcEntityId>();
        for (ItemupcDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static SortedSet<ItemupcEntityId> toEntityIdSortedSet(Collection<ItemupcDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        SortedSet<ItemupcEntityId> entityIds = new TreeSet<ItemupcEntityId>();
        for (ItemupcDTOId dtoId: dtoIds) {
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
        final ItemupcEntity other = ((ItemupcEntity) obj);
        // Identifier level comparison
        if (this.identifier.getUpcId() == null) {
            return false;
        } else {
            if (!this.identifier.getUpcId().equals(other.identifier.getUpcId())) {
                return false;
            }
        }
        if (this.identifier.getSkuNo() == null) {
            return false;
        } else {
            if (!this.identifier.getSkuNo().equals(other.identifier.getSkuNo())) {
                return false;
            }
        }
        if (this.identifier.getUpcModifier() == null) {
            return false;
        } else {
            if (!this.identifier.getUpcModifier().equals(other.identifier.getUpcModifier())) {
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
        final ItemupcEntity other = ((ItemupcEntity) obj);
        // Data level comparison
        if (priceAdjAmt == null) {
            if (other.priceAdjAmt!= null) {
                return false;
            }
        } else {
            if (!priceAdjAmt.equals(other.priceAdjAmt)) {
                return false;
            }
        }
        if (upcSeq == null) {
            if (other.upcSeq!= null) {
                return false;
            }
        } else {
            if (!upcSeq.equals(other.upcSeq)) {
                return false;
            }
        }
        if (typeCd == null) {
            if (other.typeCd!= null) {
                return false;
            }
        } else {
            if (!typeCd.equals(other.typeCd)) {
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
        if (ucc14Uom == null) {
            if (other.ucc14Uom!= null) {
                return false;
            }
        } else {
            if (!ucc14Uom.equals(other.ucc14Uom)) {
                return false;
            }
        }
        if (ucc14Qty == null) {
            if (other.ucc14Qty!= null) {
                return false;
            }
        } else {
            if (!ucc14Qty.equals(other.ucc14Qty)) {
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
        final ItemupcEntity other = ((ItemupcEntity) obj);
        // Identifier level comparison
        if (this.identifier.getUpcId() == null) {
            return false;
        } else {
            if (!this.identifier.getUpcId().equals(other.identifier.getUpcId())) {
                return false;
            }
        }
        if (this.identifier.getSkuNo() == null) {
            return false;
        } else {
            if (!this.identifier.getSkuNo().equals(other.identifier.getSkuNo())) {
                return false;
            }
        }
        if (this.identifier.getUpcModifier() == null) {
            return false;
        } else {
            if (!this.identifier.getUpcModifier().equals(other.identifier.getUpcModifier())) {
                return false;
            }
        }
        // Data level comparison
        if (priceAdjAmt == null) {
            if (other.priceAdjAmt!= null) {
                return false;
            }
        } else {
            if (!priceAdjAmt.equals(other.priceAdjAmt)) {
                return false;
            }
        }
        if (upcSeq == null) {
            if (other.upcSeq!= null) {
                return false;
            }
        } else {
            if (!upcSeq.equals(other.upcSeq)) {
                return false;
            }
        }
        if (typeCd == null) {
            if (other.typeCd!= null) {
                return false;
            }
        } else {
            if (!typeCd.equals(other.typeCd)) {
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
        if (ucc14Uom == null) {
            if (other.ucc14Uom!= null) {
                return false;
            }
        } else {
            if (!ucc14Uom.equals(other.ucc14Uom)) {
                return false;
            }
        }
        if (ucc14Qty == null) {
            if (other.ucc14Qty!= null) {
                return false;
            }
        } else {
            if (!ucc14Qty.equals(other.ucc14Qty)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        // If any ID columns are null, compare just the object instance it self.  This preserves objects that have not been assigned ID's yet.
        if (((this.identifier.getUpcId() == null)||(this.identifier.getSkuNo() == null))||(this.identifier.getUpcModifier() == null)) {
            return super.hashCode();
        }
        	
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier.getUpcId() == null) ? 0 : identifier.getUpcId().hashCode());
        result = prime * result + ((identifier.getSkuNo() == null) ? 0 : identifier.getSkuNo().hashCode());
        result = prime * result + ((identifier.getUpcModifier() == null) ? 0 : identifier.getUpcModifier().hashCode());
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
    public int compareTo(ItemupcEntity other) {
        return this.identifier.compareTo(other.identifier);
    }

    /**
     * This method displays bean properties for debugging purposes.  Do not use for anything else as it may change to enhance debugging in the future.
     * 
     */
    public String toString() {
        final StringBuilder temp = new StringBuilder();
        	
        temp.append("\n");
        temp.append("ItemupcEntity (ITEMUPC)\n");
        temp.append("  ID   1 : upcId                          : UPC_ID                         : ").append(this.identifier.getUpcId()).append("\n");
        temp.append("  ID   2 : skuNo                          : SKU_NO                         : ").append(this.identifier.getSkuNo()).append("\n");
        temp.append("       3 : priceAdjAmt                    : PRICE_ADJ_AMT                  : ").append(priceAdjAmt).append("\n");
        temp.append("  ID   4 : upcModifier                    : UPC_MODIFIER                   : ").append(this.identifier.getUpcModifier()).append("\n");
        temp.append("       5 : upcSeq                         : UPC_SEQ                        : ").append(upcSeq).append("\n");
        temp.append("       6 : typeCd                         : TYPE_CD                        : ").append(typeCd).append("\n");
        temp.append("       7 : replicationNo                  : REPLICATION_NO                 : ").append(replicationNo).append("\n");
        temp.append("       8 : operationType                  : OPERATION_TYPE                 : ").append(operationType).append("\n");
        temp.append("       9 : registerReplicationNo          : REGISTER_REPLICATION_NO        : ").append(registerReplicationNo).append("\n");
        temp.append("  AD  10 : dateCreated                    : DATE_CREATED                   : ").append(dateCreated).append("\n");
        temp.append("  AD  11 : userCreated                    : USER_CREATED                   : ").append(userCreated).append("\n");
        temp.append("  AD  12 : dateModified                   : DATE_MODIFIED                  : ").append(dateModified).append("\n");
        temp.append("  AD  13 : userModified                   : USER_MODIFIED                  : ").append(userModified).append("\n");
        temp.append("      14 : ucc14Uom                       : UCC14_UOM                      : ").append(ucc14Uom).append("\n");
        temp.append("      15 : ucc14Qty                       : UCC14_QTY                      : ").append(ucc14Qty).append("\n");
        	
        return temp.toString();
    }

}
