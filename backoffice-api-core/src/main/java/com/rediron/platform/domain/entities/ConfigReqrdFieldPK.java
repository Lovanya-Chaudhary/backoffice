package com.rediron.platform.domain.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CONFIG_REQRD_FIELDS database table.
 * 
 */
@Embeddable
public class ConfigReqrdFieldPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FORM_NAME")
	private String formName;

	@Column(name="BLOCK_NAME")
	private String blockName;

	@Column(name="ITEM_NAME")
	private String itemName;

	public ConfigReqrdFieldPK() {
	}
	public String getFormName() {
		return this.formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getBlockName() {
		return this.blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getItemName() {
		return this.itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ConfigReqrdFieldPK)) {
			return false;
		}
		ConfigReqrdFieldPK castOther = (ConfigReqrdFieldPK)other;
		return 
			this.formName.equals(castOther.formName)
			&& this.blockName.equals(castOther.blockName)
			&& this.itemName.equals(castOther.itemName);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.formName.hashCode();
		hash = hash * prime + this.blockName.hashCode();
		hash = hash * prime + this.itemName.hashCode();
		
		return hash;
	}
}