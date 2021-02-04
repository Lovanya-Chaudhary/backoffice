//
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
public class SitgrpAllEntityId
    implements PersistableObjectIdentifier<SitgrpAllEntityId>
{

    private final static long serialVersionUID = 1L;
    /**
     * <p>Maps to table column: {@code GROUP_ID}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 4, message = "GROUP_ID should contain less than or equal to 4 characters", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @NotNull(message = "GROUP_ID can not be null", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @Column(name = "GROUP_ID")
    private String groupId;
    /**
     * <p>Maps to table column: {@code SITE_NO}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
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
    public SitgrpAllEntityId() {
    }

    /**
     * Constructor for primary key {@code com.tomax.site.entity.SitgrpAllEntityId}
     * 
     * @param groupId
     *     {@link String }
     * @param siteNo
     *     {@link Integer }
     */
    public SitgrpAllEntityId(String groupId, Integer siteNo) {
        this.groupId = groupId;
        this.siteNo = siteNo;
    }

    /**
     * Constructor used to create a SitgrpAllEntity where the identifier will be created internally using the id arguments provided inside a map.
     * 
     */
    public SitgrpAllEntityId(Map<Object, Object> map) {
        this.groupId = new String(map.get("groupId").toString());
        this.siteNo = Integer.valueOf(map.get("siteNo").toString());
    }

    /**
     * Getter for {@link SitgrpAllEntityId#groupId identifier.groupId}
     * 
     */
    public String getGroupId() {
        return this.groupId;
    }

    /**
     * Setter for {@link SitgrpAllEntityId#groupId identifier.groupId}
     * 
     */
    public String setGroupId(String groupId) {
        this.groupId = groupId;
        return this.groupId;
    }

    /**
     * Getter for {@link SitgrpAllEntityId#siteNo identifier.siteNo}
     * 
     */
    public Integer getSiteNo() {
        return this.siteNo;
    }

    /**
     * Setter for {@link SitgrpAllEntityId#siteNo identifier.siteNo}
     * 
     */
    public Integer setSiteNo(Integer siteNo) {
        this.siteNo = siteNo;
        return this.siteNo;
    }

    @Override
    public boolean equals(Object obj) {
        // If any ID columns are null, compare just the object instance itself.  This preserves objects that have not been assigned ID's yet.
        if ((this.groupId == null)||(this.siteNo == null)) {
            return super.equals(obj);
        }
        	
        if (this == obj)
        	return true;
        if (obj == null)
        	return false;
        if (getClass() != obj.getClass())
        	return false;
        SitgrpAllEntityId other = (SitgrpAllEntityId) obj;
        if (groupId == null) {
        	if (other.groupId != null)
        		return false;
        } else if (!groupId.equals(other.groupId))
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
        result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
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
    public int compareTo(SitgrpAllEntityId other) {
        if (other == null) {
            return (BEFORE);
        }
        	
        int c;
        	
        if ((this.groupId == null)&&(other.groupId!= null)) {
            return (AFTER);
        }
        if ((this.groupId!= null)&&(other.groupId == null)) {
            return (BEFORE);
        }
        if ((this.groupId!= null)&&(other.groupId!= null)) {
            c = this.groupId.compareTo(other.groupId);
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
        temp.append("Identifier properties: SitgrpAllEntityId (SITGRP_ALL)\n");
        temp.append("  ID   1 : groupId                        : GROUP_ID                       : ").append(groupId).append("\n");
        temp.append("  ID   2 : siteNo                         : SITE_NO                        : ").append(siteNo).append("\n");
        	
        return temp.toString();
    }

}
