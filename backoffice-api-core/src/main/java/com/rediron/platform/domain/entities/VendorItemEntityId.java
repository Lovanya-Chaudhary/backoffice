package com.rediron.platform.domain.entities;

import java.math.BigInteger;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.rediron.platform.domain.rnet.PersistableObjectIdentifier;


@SuppressWarnings("all")
@Embeddable
public class VendorItemEntityId
    implements PersistableObjectIdentifier<VendorItemEntityId>
{

    private final static long serialVersionUID = 1L;
    /**
     * <p>Maps to table column: {@code SEQ_NO}.</p>Warning.  Database defines precision as '20' for this column.  This exceeds the java long type used for the @Max java validation annotation.<p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 20, fraction = 0, message = "SEQ_NO should have an integer component no longer than 20 and an a fraction component of exact size 0", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @NotNull(message = "SEQ_NO can not be null", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @Column(name = "SEQ_NO")
    private BigInteger seqNo;

    /**
     * Empty constructor for use by persistence provider or entity constructor
     * 
     */
    public VendorItemEntityId() {
    }

    /**
     * Constructor for primary key {@code com.tomax.vendor.entity.VendorItemEntityId}
     * 
     * @param seqNo
     *     {@link BigInteger }
     */
    public VendorItemEntityId(BigInteger seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * Constructor used to create a VendorItemEntity where the identifier will be created internally using the id arguments provided inside a map.
     * 
     */
    public VendorItemEntityId(Map<Object, Object> map) {
        this.seqNo = new BigInteger(map.get("seqNo").toString());
    }

    /**
     * Getter for {@link VendorItemEntityId#seqNo identifier.seqNo}
     * 
     */
    public BigInteger getSeqNo() {
        return this.seqNo;
    }

    /**
     * Setter for {@link VendorItemEntityId#seqNo identifier.seqNo}
     * 
     */
    public BigInteger setSeqNo(BigInteger seqNo) {
        this.seqNo = seqNo;
        return this.seqNo;
    }

    @Override
    public boolean equals(Object obj) {
        // If any ID columns are null, compare just the object instance itself.  This preserves objects that have not been assigned ID's yet.
        if (this.seqNo == null) {
            return super.equals(obj);
        }
        	
        if (this == obj)
        	return true;
        if (obj == null)
        	return false;
        if (getClass() != obj.getClass())
        	return false;
        VendorItemEntityId other = (VendorItemEntityId) obj;
        if (seqNo == null) {
        	if (other.seqNo != null)
        		return false;
        } else if (!seqNo.equals(other.seqNo))
        	return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((seqNo == null) ? 0 : seqNo.hashCode());
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
    public int compareTo(VendorItemEntityId other) {
        if (other == null) {
            return (BEFORE);
        }
        	
        if ((this.seqNo == null)&&(other.seqNo!= null)) {
            return (AFTER);
        }
        if ((this.seqNo!= null)&&(other.seqNo == null)) {
            return (BEFORE);
        }
        if ((this.seqNo!= null)&&(other.seqNo!= null)) {
            return this.seqNo.compareTo(other.seqNo);
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
        temp.append("Identifier properties: VendorItemEntityId (VENDOR_ITEM)\n");
        temp.append("  ID   1 : seqNo                          : SEQ_NO                         : ").append(seqNo).append("\n");
        	
        return temp.toString();
    }

}
