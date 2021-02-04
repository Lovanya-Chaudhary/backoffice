package com.rediron.platform.domain.entities;

import java.io.Serializable;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Embeddable
@Data
public class DomainRefEntityId implements Serializable
{

    private final static long serialVersionUID = 1L;
    /**
     * <p>Maps to table column: {@code DOMAIN_REF_ID}.</p><p><b>Schema Remarks:</b> Unique identifier for a domain code</p>
     * 
     */
    @Digits(integer = 10, fraction = 0, message = "DOMAIN_REF_ID should have an integer component no longer than 10 and an a fraction component of exact size 0", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @NotNull(message = "DOMAIN_REF_ID can not be null", groups = {
        com.tomax.api.validation.RNetObjectLifecycle.RETRIEVE.class
    })
    @Column(name = "DOMAIN_REF_ID")
    private Long domainRefId;

    /**
     * Empty constructor for use by persistence provider or entity constructor
     * 
     */
    public DomainRefEntityId() {
    }

    /**
     * Constructor for primary key {@code com.tomax.config.domainRef.entity.DomainRefEntityId}
     * 
     * @param domainRefId
     *     {@link Long }
     */
    public DomainRefEntityId(Long domainRefId) {
        this.domainRefId = domainRefId;
    }

    /**
     * Constructor used to create a DomainRefEntity where the identifier will be created internally using the id arguments provided inside a map.
     * 
     */
    public DomainRefEntityId(Map<Object, Object> map) {
        this.domainRefId = Long.valueOf(map.get("domainRefId").toString());
    }

    /**
     * Getter for {@link DomainRefEntityId#domainRefId identifier.domainRefId}
     * 
     */
    public Long getDomainRefId() {
        return this.domainRefId;
    }

    /**
     * Setter for {@link DomainRefEntityId#domainRefId identifier.domainRefId}
     * 
     */
    public Long setDomainRefId(Long domainRefId) {
        this.domainRefId = domainRefId;
        return this.domainRefId;
    }

    @Override
    public boolean equals(Object obj) {
        // If any ID columns are null, compare just the object instance itself.  This preserves objects that have not been assigned ID's yet.
        if (this.domainRefId == null) {
            return super.equals(obj);
        }
        	
        if (this == obj)
        	return true;
        if (obj == null)
        	return false;
        if (getClass() != obj.getClass())
        	return false;
        DomainRefEntityId other = (DomainRefEntityId) obj;
        if (domainRefId == null) {
        	if (other.domainRefId != null)
        		return false;
        } else if (!domainRefId.equals(other.domainRefId))
        	return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((domainRefId == null) ? 0 : domainRefId.hashCode());
        return result;
    }

    /**
     * This method displays entity ID properties for debugging purposes.  Do not use for anything else as it may change to enhance debugging in the future.
     * 
     */
    public String toString() {
        final StringBuilder temp = new StringBuilder();
        	
        temp.append("\n");
        temp.append("Identifier properties: DomainRefEntityId (DOMAIN_REF)\n");
        temp.append("  ID   1 : domainRefId                    : DOMAIN_REF_ID                  : ").append(domainRefId).append("\n");
        	
        return temp.toString();
    }

}
