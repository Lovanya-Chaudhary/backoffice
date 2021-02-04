package com.rediron.platform.domain.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PROMO_EXCEPT database table.
 * 
 */
@Entity
@Table(name="PROMO_EXCEPT")
@NamedQuery(name="PromoExcept.findAll", query="SELECT p FROM PromoExcept p")
public class PromoExcept implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PromoExceptPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CHANGED")
	private Date dateChanged;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	@Column(name="OPERATION_TYPE")
	private String operationType;

	@Column(name="PROMO_KEY_NO")
	private BigDecimal promoKeyNo;

	@Column(name="REGISTER_REPLICATION_NO")
	private BigDecimal registerReplicationNo;

	@Column(name="REPLICATION_NO")
	private BigDecimal replicationNo;

	private String status;

	@Temporal(TemporalType.DATE)
	@Column(name="STOP_DT")
	private Date stopDt;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="USER_MODIFIED")
	private String userModified;

	public PromoExcept() {
	}

	public PromoExceptPK getId() {
		return this.id;
	}

	public void setId(PromoExceptPK id) {
		this.id = id;
	}

	public Date getDateChanged() {
		return this.dateChanged;
	}

	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getOperationType() {
		return this.operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public BigDecimal getPromoKeyNo() {
		return this.promoKeyNo;
	}

	public void setPromoKeyNo(BigDecimal promoKeyNo) {
		this.promoKeyNo = promoKeyNo;
	}

	public BigDecimal getRegisterReplicationNo() {
		return this.registerReplicationNo;
	}

	public void setRegisterReplicationNo(BigDecimal registerReplicationNo) {
		this.registerReplicationNo = registerReplicationNo;
	}

	public BigDecimal getReplicationNo() {
		return this.replicationNo;
	}

	public void setReplicationNo(BigDecimal replicationNo) {
		this.replicationNo = replicationNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStopDt() {
		return this.stopDt;
	}

	public void setStopDt(Date stopDt) {
		this.stopDt = stopDt;
	}

	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public String getUserModified() {
		return this.userModified;
	}

	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}

}