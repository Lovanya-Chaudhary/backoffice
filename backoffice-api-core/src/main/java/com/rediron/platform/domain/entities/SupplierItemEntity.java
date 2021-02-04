package com.rediron.platform.domain.entities;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name="SUPPLIER_ITEM")
public class SupplierItemEntity{
	
    @Column(name = "DATE_CREATED", insertable = false, updatable = false)
    @Temporal(TemporalType.DATE)
	private Date dateCreated;
	
    @Column(name = "DATE_MODIFIED", insertable = false, updatable = false)
    @Temporal(TemporalType.DATE)
	private Date dateModified;
	
    @Id
    @Digits(integer = 9, fraction = 0, message = "SKU_NO should have an integer component no longer than 9 and an a fraction component of exact size 0", groups = {
            com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
        })
        @NotNull(message = "SKU_NO can not be null", groups = {
            com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
        })
        @Column(name = "SKU_NO")
	private Integer skuNo;
	
    @Column(name = "SUPPLIER_ID", insertable = true, updatable = true)
	private String supplierId;
	
    @Column(name = "USER_CREATED", insertable = true, updatable = false)
	private String userCreated;
	
    @Column(name = "USER_MODIFIED")
	private String userModified;

}
