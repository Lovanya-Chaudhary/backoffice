package com.rediron.platform.domain.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EMPLOYEE database table.
 * 
 */
@Entity
@Table(name="EMPLOYEE")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLOYEE_ID")
	private String employeeId;

	@Column(name="ACCESS_LEVEL_CD")
	private String accessLevelCd;

	@Column(name="ADDR1")
	private String addr1;

	@Column(name="ADDR2")
	private String addr2;

	@Column(name="ADVANCES_OWING")
	private BigDecimal advancesOwing;

	@Column(name="ALL_TIME_CARD_EDIT_FL")
	private String allTimeCardEditFl;

	@Column(name="ASSIGNED_GROUP_ID")
	private String assignedGroupId;

	@Column(name="CITY")
	private String city;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CHANGED")
	private Date dateChanged;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	@Column(name="DEDUCT1")
	private String deduct1;
	@Column(name="DEDUCT2")
	private String deduct2;
	@Column(name="DEDUCT3")
	private String deduct3;
	@Column(name="DEDUCT4")
	private String deduct4;
	@Column(name="DEDUCT5")
	private String deduct5;

	@Column(name="DEFAULT_PASSWORD_EMPLOYEE_FL")
	private String defaultPasswordEmployeeFl;

	@Column(name="EARNINGS_CODE")
	private String earningsCode;

	@Column(name="EIC_NO")
	private BigDecimal eicNo;

	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name="EMPLOYEE_NO")
	private BigDecimal employeeNo;

	@Column(name="FEDERAL_EXEMPT_NO")
	private BigDecimal federalExemptNo;

	@Column(name="FEDERAL_TAX_AUTH")
	private String federalTaxAuth;

	@Column(name="FILING_STATUS")
	private String filingStatus;

	@Column(name="FULL_NAME")
	private String fullName;

	@Column(name="IDM_ID")
	private BigDecimal idmId;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_PAID_DT")
	private Date lastPaidDt;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_RAISE_DT")
	private Date lastRaiseDt;

	@Column(name="LOCAL_EXEMPT_NO")
	private BigDecimal localExemptNo;

	@Column(name="LOCAL_TAX_AUTH")
	private String localTaxAuth;

	@Column(name="LP_RESILIENT_PAGE_ID")
	private BigDecimal lpResilientPageId;

	@Column(name="LP_START_PAGE_ID")
	private BigDecimal lpStartPageId;

	@Column(name="OPERATION_TYPE")
	private String operationType;

	@Column(name="OTHER_EXEMPT_NO")
	private BigDecimal otherExemptNo;

	@Column(name="OTHER_TAX_AUTH")
	private String otherTaxAuth;

	@Column(name="PASSWORD")
	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name="PASSWORD_CHANGE_DT")
	private Date passwordChangeDt;

	@Column(name="PASSWORD_HINT")
	private String passwordHint;

	@Column(name="PAY_CODE")
	private String payCode;

	@Column(name="PAY_PERIOD")
	private String payPeriod;

	@Column(name="PHONE")
	private String phone;

	@Column(name="PRICE_LEVEL_NO")
	private BigDecimal priceLevelNo;

	@Column(name="PRIMARY_SITE_NO")
	private BigDecimal primarySiteNo;

	@Column(name="RATE_CODE1")
	private String rateCode1;

	@Column(name="RATE_CODE2")
	private String rateCode2;

	@Column(name="RATE_CODE3")
	private String rateCode3;

	@Column(name="RATE_CODE4")
	private String rateCode4;

	@Column(name="RATE1")
	private BigDecimal rate1;

	@Column(name="RATE2")
	private BigDecimal rate2;

	@Column(name="RATE3")
	private BigDecimal rate3;

	@Column(name="RATE4")
	private BigDecimal rate4;

	@Column(name="REGISTER_REPLICATION_NO")
	private BigDecimal registerReplicationNo;

	@Column(name="REPLICATION_NO")
	private BigDecimal replicationNo;

	@Column(name="SHORT_NAME")
	private String shortName;

	@Column(name="SICK_DUE")
	private BigDecimal sickDue;

	@Column(name="SSN")
	private String ssn;

	@Column(name="STANDARD_HOURS")
	private BigDecimal standardHours;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DT")
	private Date startDt;

	@Column(name="STATE")
	private String state;

	@Column(name="STATE_EXEMPT_NO")
	private BigDecimal stateExemptNo;

	@Column(name="STATE_TAX_AUTH")
	private String stateTaxAuth;

	@Temporal(TemporalType.DATE)
	@Column(name="TERMINATION_DT")
	private Date terminationDt;

	@Column(name="TIME_CARD_EDIT_FL")
	private String timeCardEditFl;

	@Column(name="TMXUSER_ID")
	private String tmxuserId;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="USER_MODIFIED")
	private String userModified;

	@Column(name="VACATION_DUE")
	private BigDecimal vacationDue;

	@Column(name="VACATION_PAID")
	private BigDecimal vacationPaid;

	@Column(name="ZIP")
	private String zip;

	public Employee() {
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getAccessLevelCd() {
		return this.accessLevelCd;
	}

	public void setAccessLevelCd(String accessLevelCd) {
		this.accessLevelCd = accessLevelCd;
	}

	public String getAddr1() {
		return this.addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return this.addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public BigDecimal getAdvancesOwing() {
		return this.advancesOwing;
	}

	public void setAdvancesOwing(BigDecimal advancesOwing) {
		this.advancesOwing = advancesOwing;
	}

	public String getAllTimeCardEditFl() {
		return this.allTimeCardEditFl;
	}

	public void setAllTimeCardEditFl(String allTimeCardEditFl) {
		this.allTimeCardEditFl = allTimeCardEditFl;
	}

	public String getAssignedGroupId() {
		return this.assignedGroupId;
	}

	public void setAssignedGroupId(String assignedGroupId) {
		this.assignedGroupId = assignedGroupId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getDeduct1() {
		return this.deduct1;
	}

	public void setDeduct1(String deduct1) {
		this.deduct1 = deduct1;
	}

	public String getDeduct2() {
		return this.deduct2;
	}

	public void setDeduct2(String deduct2) {
		this.deduct2 = deduct2;
	}

	public String getDeduct3() {
		return this.deduct3;
	}

	public void setDeduct3(String deduct3) {
		this.deduct3 = deduct3;
	}

	public String getDeduct4() {
		return this.deduct4;
	}

	public void setDeduct4(String deduct4) {
		this.deduct4 = deduct4;
	}

	public String getDeduct5() {
		return this.deduct5;
	}

	public void setDeduct5(String deduct5) {
		this.deduct5 = deduct5;
	}

	public String getDefaultPasswordEmployeeFl() {
		return this.defaultPasswordEmployeeFl;
	}

	public void setDefaultPasswordEmployeeFl(String defaultPasswordEmployeeFl) {
		this.defaultPasswordEmployeeFl = defaultPasswordEmployeeFl;
	}

	public String getEarningsCode() {
		return this.earningsCode;
	}

	public void setEarningsCode(String earningsCode) {
		this.earningsCode = earningsCode;
	}

	public BigDecimal getEicNo() {
		return this.eicNo;
	}

	public void setEicNo(BigDecimal eicNo) {
		this.eicNo = eicNo;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public BigDecimal getEmployeeNo() {
		return this.employeeNo;
	}

	public void setEmployeeNo(BigDecimal employeeNo) {
		this.employeeNo = employeeNo;
	}

	public BigDecimal getFederalExemptNo() {
		return this.federalExemptNo;
	}

	public void setFederalExemptNo(BigDecimal federalExemptNo) {
		this.federalExemptNo = federalExemptNo;
	}

	public String getFederalTaxAuth() {
		return this.federalTaxAuth;
	}

	public void setFederalTaxAuth(String federalTaxAuth) {
		this.federalTaxAuth = federalTaxAuth;
	}

	public String getFilingStatus() {
		return this.filingStatus;
	}

	public void setFilingStatus(String filingStatus) {
		this.filingStatus = filingStatus;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public BigDecimal getIdmId() {
		return this.idmId;
	}

	public void setIdmId(BigDecimal idmId) {
		this.idmId = idmId;
	}

	public Date getLastPaidDt() {
		return this.lastPaidDt;
	}

	public void setLastPaidDt(Date lastPaidDt) {
		this.lastPaidDt = lastPaidDt;
	}

	public Date getLastRaiseDt() {
		return this.lastRaiseDt;
	}

	public void setLastRaiseDt(Date lastRaiseDt) {
		this.lastRaiseDt = lastRaiseDt;
	}

	public BigDecimal getLocalExemptNo() {
		return this.localExemptNo;
	}

	public void setLocalExemptNo(BigDecimal localExemptNo) {
		this.localExemptNo = localExemptNo;
	}

	public String getLocalTaxAuth() {
		return this.localTaxAuth;
	}

	public void setLocalTaxAuth(String localTaxAuth) {
		this.localTaxAuth = localTaxAuth;
	}

	public BigDecimal getLpResilientPageId() {
		return this.lpResilientPageId;
	}

	public void setLpResilientPageId(BigDecimal lpResilientPageId) {
		this.lpResilientPageId = lpResilientPageId;
	}

	public BigDecimal getLpStartPageId() {
		return this.lpStartPageId;
	}

	public void setLpStartPageId(BigDecimal lpStartPageId) {
		this.lpStartPageId = lpStartPageId;
	}

	public String getOperationType() {
		return this.operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public BigDecimal getOtherExemptNo() {
		return this.otherExemptNo;
	}

	public void setOtherExemptNo(BigDecimal otherExemptNo) {
		this.otherExemptNo = otherExemptNo;
	}

	public String getOtherTaxAuth() {
		return this.otherTaxAuth;
	}

	public void setOtherTaxAuth(String otherTaxAuth) {
		this.otherTaxAuth = otherTaxAuth;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPasswordChangeDt() {
		return this.passwordChangeDt;
	}

	public void setPasswordChangeDt(Date passwordChangeDt) {
		this.passwordChangeDt = passwordChangeDt;
	}

	public String getPasswordHint() {
		return this.passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public String getPayCode() {
		return this.payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getPayPeriod() {
		return this.payPeriod;
	}

	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getPriceLevelNo() {
		return this.priceLevelNo;
	}

	public void setPriceLevelNo(BigDecimal priceLevelNo) {
		this.priceLevelNo = priceLevelNo;
	}

	public BigDecimal getPrimarySiteNo() {
		return this.primarySiteNo;
	}

	public void setPrimarySiteNo(BigDecimal primarySiteNo) {
		this.primarySiteNo = primarySiteNo;
	}

	public String getRateCode1() {
		return this.rateCode1;
	}

	public void setRateCode1(String rateCode1) {
		this.rateCode1 = rateCode1;
	}

	public String getRateCode2() {
		return this.rateCode2;
	}

	public void setRateCode2(String rateCode2) {
		this.rateCode2 = rateCode2;
	}

	public String getRateCode3() {
		return this.rateCode3;
	}

	public void setRateCode3(String rateCode3) {
		this.rateCode3 = rateCode3;
	}

	public String getRateCode4() {
		return this.rateCode4;
	}

	public void setRateCode4(String rateCode4) {
		this.rateCode4 = rateCode4;
	}

	public BigDecimal getRate1() {
		return this.rate1;
	}

	public void setRate1(BigDecimal rate1) {
		this.rate1 = rate1;
	}

	public BigDecimal getRate2() {
		return this.rate2;
	}

	public void setRate2(BigDecimal rate2) {
		this.rate2 = rate2;
	}

	public BigDecimal getRate3() {
		return this.rate3;
	}

	public void setRate3(BigDecimal rate3) {
		this.rate3 = rate3;
	}

	public BigDecimal getRate4() {
		return this.rate4;
	}

	public void setRate4(BigDecimal rate4) {
		this.rate4 = rate4;
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

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public BigDecimal getSickDue() {
		return this.sickDue;
	}

	public void setSickDue(BigDecimal sickDue) {
		this.sickDue = sickDue;
	}

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public BigDecimal getStandardHours() {
		return this.standardHours;
	}

	public void setStandardHours(BigDecimal standardHours) {
		this.standardHours = standardHours;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getStateExemptNo() {
		return this.stateExemptNo;
	}

	public void setStateExemptNo(BigDecimal stateExemptNo) {
		this.stateExemptNo = stateExemptNo;
	}

	public String getStateTaxAuth() {
		return this.stateTaxAuth;
	}

	public void setStateTaxAuth(String stateTaxAuth) {
		this.stateTaxAuth = stateTaxAuth;
	}

	public Date getTerminationDt() {
		return this.terminationDt;
	}

	public void setTerminationDt(Date terminationDt) {
		this.terminationDt = terminationDt;
	}

	public String getTimeCardEditFl() {
		return this.timeCardEditFl;
	}

	public void setTimeCardEditFl(String timeCardEditFl) {
		this.timeCardEditFl = timeCardEditFl;
	}

	public String getTmxuserId() {
		return this.tmxuserId;
	}

	public void setTmxuserId(String tmxuserId) {
		this.tmxuserId = tmxuserId;
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

	public BigDecimal getVacationDue() {
		return this.vacationDue;
	}

	public void setVacationDue(BigDecimal vacationDue) {
		this.vacationDue = vacationDue;
	}

	public BigDecimal getVacationPaid() {
		return this.vacationPaid;
	}

	public void setVacationPaid(BigDecimal vacationPaid) {
		this.vacationPaid = vacationPaid;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}