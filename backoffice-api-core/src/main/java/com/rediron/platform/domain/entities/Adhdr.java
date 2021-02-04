package com.rediron.platform.domain.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ADHDR database table.
 * 
 */
@Entity
@NamedQuery(name="Adhdr.findAll", query="SELECT a FROM Adhdr a")
public class Adhdr implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AdhdrPK id;

	@Column(name="ACCT_TYPE")
	private String acctType;

	@Column(name="AD_TYPE")
	private BigDecimal adType;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CHANGED")
	private Date dateChanged;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	private String description;

	@Column(name="EXTERNAL_REF_ID")
	private String externalRefId;

	@Temporal(TemporalType.DATE)
	@Column(name="EXTRACT_DT")
	private Date extractDt;

	@Column(name="GROUP_ID")
	private String groupId;

	@Column(name="OPERATION_TYPE")
	private String operationType;

	@Temporal(TemporalType.DATE)
	@Column(name="ORIGINAL_STOP_DT")
	private Date originalStopDt;

	@Column(name="PROMO_RECEIPT_DESC")
	private String promoReceiptDesc;

	@Column(name="RECEIPT_DESCRIPTION")
	private String receiptDescription;

	@Column(name="REGISTER_REPLICATION_NO")
	private BigDecimal registerReplicationNo;

	@Column(name="RELEASE_CODE_FL")
	private String releaseCodeFl;

	@Column(name="REPLICATION_NO")
	private BigDecimal replicationNo;

	private String source;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DT")
	private Date startDt;

	@Column(name="START_TIME")
	private BigDecimal startTime;

	private String status;

	@Temporal(TemporalType.DATE)
	@Column(name="STOP_DT")
	private Date stopDt;

	@Column(name="STOP_TIME")
	private BigDecimal stopTime;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="USER_MODIFIED")
	private String userModified;

	@Column(name="WORKING_COUNTER")
	private BigDecimal workingCounter;

	public Adhdr() {
	}

	public AdhdrPK getId() {
		return this.id;
	}

	public void setId(AdhdrPK id) {
		this.id = id;
	}

	public String getAcctType() {
		return this.acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public BigDecimal getAdType() {
		return this.adType;
	}

	public void setAdType(BigDecimal adType) {
		this.adType = adType;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExternalRefId() {
		return this.externalRefId;
	}

	public void setExternalRefId(String externalRefId) {
		this.externalRefId = externalRefId;
	}

	public Date getExtractDt() {
		return this.extractDt;
	}

	public void setExtractDt(Date extractDt) {
		this.extractDt = extractDt;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getOperationType() {
		return this.operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public Date getOriginalStopDt() {
		return this.originalStopDt;
	}

	public void setOriginalStopDt(Date originalStopDt) {
		this.originalStopDt = originalStopDt;
	}

	public String getPromoReceiptDesc() {
		return this.promoReceiptDesc;
	}

	public void setPromoReceiptDesc(String promoReceiptDesc) {
		this.promoReceiptDesc = promoReceiptDesc;
	}

	public String getReceiptDescription() {
		return this.receiptDescription;
	}

	public void setReceiptDescription(String receiptDescription) {
		this.receiptDescription = receiptDescription;
	}

	public BigDecimal getRegisterReplicationNo() {
		return this.registerReplicationNo;
	}

	public void setRegisterReplicationNo(BigDecimal registerReplicationNo) {
		this.registerReplicationNo = registerReplicationNo;
	}

	public String getReleaseCodeFl() {
		return this.releaseCodeFl;
	}

	public void setReleaseCodeFl(String releaseCodeFl) {
		this.releaseCodeFl = releaseCodeFl;
	}

	public BigDecimal getReplicationNo() {
		return this.replicationNo;
	}

	public void setReplicationNo(BigDecimal replicationNo) {
		this.replicationNo = replicationNo;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public BigDecimal getStartTime() {
		return this.startTime;
	}

	public void setStartTime(BigDecimal startTime) {
		this.startTime = startTime;
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

	public BigDecimal getStopTime() {
		return this.stopTime;
	}

	public void setStopTime(BigDecimal stopTime) {
		this.stopTime = stopTime;
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

	public BigDecimal getWorkingCounter() {
		return this.workingCounter;
	}

	public void setWorkingCounter(BigDecimal workingCounter) {
		this.workingCounter = workingCounter;
	}

}