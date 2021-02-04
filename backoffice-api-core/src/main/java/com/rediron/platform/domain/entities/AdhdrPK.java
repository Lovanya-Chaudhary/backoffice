package com.rediron.platform.domain.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ADHDR database table.
 * 
 */
@Embeddable
public class AdhdrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="AD_ID")
	private String adId;

	@Column(name="SITE_NO", insertable=false, updatable=false)
	private long siteNo;

	public AdhdrPK() {
	}
	public String getAdId() {
		return this.adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public long getSiteNo() {
		return this.siteNo;
	}
	public void setSiteNo(long siteNo) {
		this.siteNo = siteNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdhdrPK)) {
			return false;
		}
		AdhdrPK castOther = (AdhdrPK)other;
		return 
			this.adId.equals(castOther.adId)
			&& (this.siteNo == castOther.siteNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.adId.hashCode();
		hash = hash * prime + ((int) (this.siteNo ^ (this.siteNo >>> 32)));
		
		return hash;
	}
}