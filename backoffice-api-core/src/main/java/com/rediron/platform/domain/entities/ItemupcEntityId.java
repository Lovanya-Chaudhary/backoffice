//
// @formatter:off
package com.rediron.platform.domain.entities;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rediron.platform.domain.rnet.PersistableObjectIdentifier;


@SuppressWarnings("all")
@Embeddable
public class ItemupcEntityId
    implements PersistableObjectIdentifier<ItemupcEntityId>
{

    private final static long serialVersionUID = 1L;
    /**
     * <p>Maps to table column: {@code UPC_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 18, message = "UPC_ID should contain less than or equal to 18 characters", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @NotNull(message = "UPC_ID can not be null", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @Column(name = "UPC_ID")
    private String upcId;
    /**
     * <p>Maps to table column: {@code SKU_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
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
     * <p>Maps to table column: {@code UPC_MODIFIER}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Digits(integer = 2, fraction = 0, message = "UPC_MODIFIER should have an integer component no longer than 2 and an a fraction component of exact size 0", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @Column(name = "UPC_MODIFIER")
    private Integer upcModifier;

    /**
     * Empty constructor for use by persistence provider or entity constructor
     * 
     */
    public ItemupcEntityId() {
    }

    /**
     * Constructor for primary key {@code com.tomax.inventory.entity.ItemupcEntityId}
     * 
     * @param upcId
     *     {@link String }
     * @param skuNo
     *     {@link Integer }
     * @param upcModifier
     *     {@link Integer }
     */
    public ItemupcEntityId(String upcId, Integer skuNo, Integer upcModifier) {
        this.upcId = upcId;
        this.skuNo = skuNo;
        this.upcModifier = upcModifier;
    }

    /**
     * Constructor used to create a ItemupcEntity where the identifier will be created internally using the id arguments provided inside a map.
     * 
     */
    public ItemupcEntityId(Map<Object, Object> map) {
        this.upcId = new String(map.get("upcId").toString());
        this.skuNo = Integer.valueOf(map.get("skuNo").toString());
        this.upcModifier = Integer.valueOf(map.get("upcModifier").toString());
    }

    /**
     * Getter for {@link ItemupcEntityId#upcId identifier.upcId}
     * 
     */
    public String getUpcId() {
        return this.upcId;
    }

    /**
     * Setter for {@link ItemupcEntityId#upcId identifier.upcId}
     * 
     */
    public String setUpcId(String upcId) {
        this.upcId = upcId;
        return this.upcId;
    }

    /**
     * Getter for {@link ItemupcEntityId#skuNo identifier.skuNo}
     * 
     */
    public Integer getSkuNo() {
        return this.skuNo;
    }

    /**
     * Setter for {@link ItemupcEntityId#skuNo identifier.skuNo}
     * 
     */
    public Integer setSkuNo(Integer skuNo) {
        this.skuNo = skuNo;
        return this.skuNo;
    }

    /**
     * Getter for {@link ItemupcEntityId#upcModifier identifier.upcModifier}
     * 
     */
    public Integer getUpcModifier() {
        return this.upcModifier;
    }

    /**
     * Setter for {@link ItemupcEntityId#upcModifier identifier.upcModifier}
     * 
     */
    public Integer setUpcModifier(Integer upcModifier) {
        this.upcModifier = upcModifier;
        return this.upcModifier;
    }

    @Override
    public boolean equals(Object obj) {
        // If any ID columns are null, compare just the object instance itself.  This preserves objects that have not been assigned ID's yet.
        if (((this.upcId == null)||(this.skuNo == null))||(this.upcModifier == null)) {
            return super.equals(obj);
        }
        	
        if (this == obj)
        	return true;
        if (obj == null)
        	return false;
        if (getClass() != obj.getClass())
        	return false;
        ItemupcEntityId other = (ItemupcEntityId) obj;
        if (upcId == null) {
        	if (other.upcId != null)
        		return false;
        } else if (!upcId.equals(other.upcId))
        	return false;
        if (skuNo == null) {
        	if (other.skuNo != null)
        		return false;
        } else if (!skuNo.equals(other.skuNo))
        	return false;
        if (upcModifier == null) {
        	if (other.upcModifier != null)
        		return false;
        } else if (!upcModifier.equals(other.upcModifier))
        	return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((upcId == null) ? 0 : upcId.hashCode());
        result = prime * result + ((skuNo == null) ? 0 : skuNo.hashCode());
        result = prime * result + ((upcModifier == null) ? 0 : upcModifier.hashCode());
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
    public int compareTo(ItemupcEntityId other) {
        if (other == null) {
            return (BEFORE);
        }
        	
        int c;
        	
        if ((this.upcId == null)&&(other.upcId!= null)) {
            return (AFTER);
        }
        if ((this.upcId!= null)&&(other.upcId == null)) {
            return (BEFORE);
        }
        if ((this.upcId!= null)&&(other.upcId!= null)) {
            c = this.upcId.compareTo(other.upcId);
            if (c!= (EQUAL)) {
                return c;
            }
        }
        	
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
        	
        if ((this.upcModifier == null)&&(other.upcModifier!= null)) {
            return (AFTER);
        }
        if ((this.upcModifier!= null)&&(other.upcModifier == null)) {
            return (BEFORE);
        }
        if ((this.upcModifier!= null)&&(other.upcModifier!= null)) {
            return this.upcModifier.compareTo(other.upcModifier);
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
        temp.append("Identifier properties: ItemupcEntityId (ITEMUPC)\n");
        temp.append("  ID   1 : upcId                          : UPC_ID                         : ").append(upcId).append("\n");
        temp.append("  ID   2 : skuNo                          : SKU_NO                         : ").append(skuNo).append("\n");
        temp.append("  ID   4 : upcModifier                    : UPC_MODIFIER                   : ").append(upcModifier).append("\n");
        	
        return temp.toString();
    }

}
