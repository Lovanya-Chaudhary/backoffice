//
// @formatter:off
package com.rediron.platform.domain.entities;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.rediron.platform.domain.rnet.PersistableObjectIdentifier;



@SuppressWarnings("all")
@Embeddable
public class InvbysitEntityId
    implements PersistableObjectIdentifier<InvbysitEntityId>
{

    private final static long serialVersionUID = 1L;
    /**
     * <p>Maps to table column: {@code SKU_NO}.</p><p><b>Schema Remarks:</b> Stock Keeping Unit.  The unique identifier for the product item.  This item can be user defined or automatically generated</p>
     * 
     */
    @Digits(integer = 9, fraction = 0, message = "SKU_NO should have an integer component no longer than 9 and an a fraction component of exact size 0", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @NotNull(message = "SKU_NO can not be null", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @Column(name = "SKU_NO")
    private Integer skuNo;
    /**
     * <p>Maps to table column: {@code SITE_NO}.</p><p><b>Schema Remarks:</b> A unique number to define a location</p>
     * 
     */
    @Digits(integer = 4, fraction = 0, message = "SITE_NO should have an integer component no longer than 4 and an a fraction component of exact size 0", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @NotNull(message = "SITE_NO can not be null", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @Column(name = "SITE_NO")
    private Integer siteNo;

    /**
     * Empty constructor for use by persistence provider or entity constructor
     * 
     */
    public InvbysitEntityId() {
    }

    /**
     * Constructor for primary key {@code com.tomax.inventory.entity.InvbysitEntityId}
     * 
     * @param siteNo
     *     {@link Integer }
     * @param skuNo
     *     {@link Integer }
     */
    public InvbysitEntityId(Integer skuNo, Integer siteNo) {
        this.skuNo = skuNo;
        this.siteNo = siteNo;
    }

    /**
     * Constructor used to create a InvbysitFullEntity where the identifier will be created internally using the id arguments provided inside a map.
     * 
     */
    public InvbysitEntityId(Map<Object, Object> map) {
        this.skuNo = new Integer(map.get("skuNo").toString());
        this.siteNo = new Integer(map.get("siteNo").toString());
    }

    /**
     * Getter for {@link InvbysitEntityId#skuNo identifier.skuNo}
     * 
     */
    public Integer getSkuNo() {
        return this.skuNo;
    }

    /**
     * Setter for {@link InvbysitEntityId#skuNo identifier.skuNo}
     * 
     */
    public Integer setSkuNo(Integer skuNo) {
        this.skuNo = skuNo;
        return this.skuNo;
    }

    /**
     * Getter for {@link InvbysitEntityId#siteNo identifier.siteNo}
     * 
     */
    public Integer getSiteNo() {
        return this.siteNo;
    }

    /**
     * Setter for {@link InvbysitEntityId#siteNo identifier.siteNo}
     * 
     */
    public Integer setSiteNo(Integer siteNo) {
        this.siteNo = siteNo;
        return this.siteNo;
    }

    @Override
    public boolean equals(Object obj) {
        // If any ID columns are null, compare just the object instance itself.  This preserves objects that have not been assigned ID's yet.
        if ((this.skuNo == null)||(this.siteNo == null)) {
            return super.equals(obj);
        }
        	
        if (this == obj)
        	return true;
        if (obj == null)
        	return false;
        if (getClass() != obj.getClass())
        	return false;
        InvbysitEntityId other = (InvbysitEntityId) obj;
        if (skuNo == null) {
        	if (other.skuNo != null)
        		return false;
        } else if (!skuNo.equals(other.skuNo))
        	return false;
        if (siteNo == null) {
        	if (other.siteNo != null)
        		return false;
        } else if (!siteNo.equals(other.siteNo))
        	return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((skuNo == null) ? 0 : skuNo.hashCode());
        result = prime * result + ((siteNo == null) ? 0 : siteNo.hashCode());
        return result;
    }

    /**
     * <p>Implementation of comparable.  This implementation of Comparable uses 'Natural Ordering' of the primary key columns
     * starting with the first ID column and ending with the last.  The exception is that 'null' id columns are treated as highest value (AFTER).
     * If the other object is null, then this object will be returned as BEFORE.</p>
     * 
     * @see <a href="http://docs.oracle.com/javase/tutorial/collections/interfaces/order.html">Object Ordering</a>
     * 
     */
    public int compareTo(InvbysitEntityId other) {
        if (other == null) {
            return (BEFORE);
        }
        	
        int c;
        	
        if ((this.skuNo == null)&&(other.skuNo!= null)) {
            return (AFTER);
        }
        if ((this.skuNo!= null)&&(other.skuNo == null)) {
            return (BEFORE);
        }
        if ((this.skuNo!= null)&&(other.skuNo!= null)) {
            c = this.skuNo.compareTo(other.skuNo);
            if (c!= (EQUAL)) {
                return c;
            }
        }
        	
        if ((this.siteNo == null)&&(other.siteNo!= null)) {
            return (AFTER);
        }
        if ((this.siteNo!= null)&&(other.siteNo == null)) {
            return (BEFORE);
        }
        if ((this.siteNo!= null)&&(other.siteNo!= null)) {
            return this.siteNo.compareTo(other.siteNo);
        }
        	
        /*
         * We assume if all identifier columns are null that the object is new and is 'ALWAYS' unique.
         * This is counter-intuitive to how Comparable is typically implemented but we have no choice
         * because our Entities/DTO's don't always start out with values in the ID columns.  This
         * implementation is more consistent with a Database than with Java.
         */
        return (AFTER);
    }

    /**
     * This method displays entity ID properties for debugging purposes.  Do not use for anything else as it may change to enhance debugging in the future.
     * 
     */
    public String toString() {
        final StringBuilder temp = new StringBuilder();
        	
        temp.append("\n");
        temp.append("Identifier properties: InvbysitEntityId (INVBYSIT)\n");
        temp.append("  ID   2 : skuNo                          : SKU_NO                         : ").append(skuNo).append("\n");
        temp.append("  ID   3 : siteNo                         : SITE_NO                        : ").append(siteNo).append("\n");
        	
        return temp.toString();
    }

}
