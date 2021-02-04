package com.rediron.platform.domain.entities;

import java.util.Map;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rediron.platform.domain.rnet.PersistableObjectIdentifier;



@SuppressWarnings("all")
@Embeddable
public class VendorEntityId
    implements PersistableObjectIdentifier<VendorEntityId>
{

    private final static long serialVersionUID = 1L;
    /**
     * <p>Maps to table column: {@code VENDOR_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 9, message = "VENDOR_ID should contain less than or equal to 9 characters", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @NotNull(message = "VENDOR_ID can not be null", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @Column(name = "VENDOR_ID")
    private String vendorId;

    /**
     * Empty constructor for use by persistence provider or entity constructor
     * 
     */
    public VendorEntityId() {
    }

    /**
     * Constructor for primary key {@code com.tomax.vendor.entity.VendorEntityId}
     * 
     * @param vendorId
     *     {@link String }
     */
    public VendorEntityId(String vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * Constructor used to create a VendorEntity where the identifier will be created internally using the id arguments provided inside a map.
     * 
     */
    public VendorEntityId(Map<Object, Object> map) {
        this.vendorId = new String(map.get("vendorId").toString());
    }

    /**
     * Getter for {@link VendorEntityId#vendorId identifier.vendorId}
     * 
     */
    public String getVendorId() {
        return this.vendorId;
    }

    /**
     * Setter for {@link VendorEntityId#vendorId identifier.vendorId}
     * 
     */
    public String setVendorId(String vendorId) {
        this.vendorId = vendorId;
        return this.vendorId;
    }

    @Override
    public boolean equals(Object obj) {
        // If any ID columns are null, compare just the object instance itself.  This preserves objects that have not been assigned ID's yet.
        if (this.vendorId == null) {
            return super.equals(obj);
        }
        	
        if (this == obj)
        	return true;
        if (obj == null)
        	return false;
        if (getClass() != obj.getClass())
        	return false;
        VendorEntityId other = (VendorEntityId) obj;
        if (vendorId == null) {
        	if (other.vendorId != null)
        		return false;
        } else if (!vendorId.equals(other.vendorId))
        	return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vendorId == null) ? 0 : vendorId.hashCode());
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
    public int compareTo(VendorEntityId other) {
        if (other == null) {
            return (BEFORE);
        }
        	
        if ((this.vendorId == null)&&(other.vendorId!= null)) {
            return (AFTER);
        }
        if ((this.vendorId!= null)&&(other.vendorId == null)) {
            return (BEFORE);
        }
        if ((this.vendorId!= null)&&(other.vendorId!= null)) {
            return this.vendorId.compareTo(other.vendorId);
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
        temp.append("Identifier properties: VendorEntityId (VENDOR)\n");
        temp.append("  ID   1 : vendorId                       : VENDOR_ID                      : ").append(vendorId).append("\n");
        	
        return temp.toString();
    }

}
