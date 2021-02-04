package com.rediron.platform.domain.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PROMO_EXCEPT database table.
 * 
 */
@Embeddable
public class PromoExceptPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="AD_SITE_NO", insertable=false, updatable=false)
	private long adSiteNo;

	@Column(name="AD_ID", insertable=false, updatable=false)
	private String adId;

	@Column(name="SITE_NO", insertable=false, updatable=false)
	private long siteNo;

	@Column(name="SKU_NO", insertable=false, updatable=false)
	private long skuNo;

	public PromoExceptPK() {
	}
	public long getAdSiteNo() {
		return this.adSiteNo;
	}
	public void setAdSiteNo(long adSiteNo) {
		this.adSiteNo = adSiteNo;
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
	public long getSkuNo() {
		return this.skuNo;
	}
	public void setSkuNo(long skuNo) {
		this.skuNo = skuNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PromoExceptPK)) {
			return false;
		}
		PromoExceptPK castOther = (PromoExceptPK)other;
		return 
			(this.adSiteNo == castOther.adSiteNo)
			&& this.adId.equals(castOther.adId)
			&& (this.siteNo == castOther.siteNo)
			&& (this.skuNo == castOther.skuNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.adSiteNo ^ (this.adSiteNo >>> 32)));
		hash = hash * prime + this.adId.hashCode();
		hash = hash * prime + ((int) (this.siteNo ^ (this.siteNo >>> 32)));
		hash = hash * prime + ((int) (this.skuNo ^ (this.skuNo >>> 32)));
		
		return hash;
	}
}