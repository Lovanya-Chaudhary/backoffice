package com.rediron.platform.domain.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CONFIG_REQRD_FIELDS database table.
 * 
 */
@Entity
@Table(name="CONFIG_REQRD_FIELDS")
@NamedQuery(name="ConfigReqrdField.findAll", query="SELECT c FROM ConfigReqrdField c")
public class ConfigReqrdField implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConfigReqrdFieldPK id;

	@Column(name="DATA_TYPE")
	private String dataType;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	@Column(name="DEFAULT_VALUE")
	private String defaultValue;

	@Column(name="DFLT_VALUE_ALLOWED_FL")
	private String dfltValueAllowedFl;

	@Column(name="LABEL_TXT")
	private String labelTxt;

	@Column(name="NOT_NULL_FL")
	private String notNullFl;

	@Column(name="USER_CREATED")
	private String userCreated;

	@Column(name="USER_MODIFIED")
	private String userModified;

	public ConfigReqrdField() {
	}

	public ConfigReqrdFieldPK getId() {
		return this.id;
	}

	public void setId(ConfigReqrdFieldPK id) {
		this.id = id;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
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

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDfltValueAllowedFl() {
		return this.dfltValueAllowedFl;
	}

	public void setDfltValueAllowedFl(String dfltValueAllowedFl) {
		this.dfltValueAllowedFl = dfltValueAllowedFl;
	}

	public String getLabelTxt() {
		return this.labelTxt;
	}

	public void setLabelTxt(String labelTxt) {
		this.labelTxt = labelTxt;
	}

	public String getNotNullFl() {
		return this.notNullFl;
	}

	public void setNotNullFl(String notNullFl) {
		this.notNullFl = notNullFl;
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