package com.rediron.platform.domain.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Entity
@Table(name = "DOMAIN_REF")
@Data
public class DomainRefEntity
{
    /**
     * Embedded composite identifier.
     * 
     */
    @EmbeddedId
    private DomainRefEntityId identifier;
    /**
     * <p>Maps to table column: {@code PUBLIC_FL}.</p><p><b>Schema Remarks:</b> Flag indicating whether the code is public.  Public flags may be changed by local users.  The default is Y</p>
     * 
     */
    @Size(max = 1, message = "PUBLIC_FL should contain less than or equal to 1 characters")
    @Column(name = "PUBLIC_FL")
    private String publicFl;
    /**
     * <p>Maps to table column: {@code ENFORCE_FL}.</p><p><b>Schema Remarks:</b> Flag indicating whether values for this code will be validated in the application.  The default is Y</p>
     * 
     */
    @Size(max = 1, message = "ENFORCE_FL should contain less than or equal to 1 characters")
    @Column(name = "ENFORCE_FL")
    private String enforceFl;
    /**
     * <p>Maps to table column: {@code DESCRIPTION}.</p><p><b>Schema Remarks:</b> The description of the domain code, such as Address Types</p>
     * 
     */
    @Size(max = 60, message = "DESCRIPTION should contain less than or equal to 60 characters")
    @NotNull(message = "DESCRIPTION can not be null")
    @Column(name = "DESCRIPTION")
    private String description;
    /**
     * <p>Maps to table column: {@code DEFAULT_CD_VALUE}.</p><p><b>Schema Remarks:</b> The default code value for this domain code</p>
     * 
     */
    @Size(max = 5, message = "DEFAULT_CD_VALUE should contain less than or equal to 5 characters")
    @Column(name = "DEFAULT_CD_VALUE")
    private String defaultCdValue;
    /**
     * <p>Maps to table column: {@code DATE_CHANGED}.</p><p><b>Schema Remarks:</b> System entered column that stamps the current system date when the table is modified</p>
     * 
     */
    @Column(name = "DATE_CHANGED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateChanged;
    /**
     * <p>Maps to table column: {@code OPERATION_TYPE}.</p><p><b>Schema Remarks:</b> The type of action to perform for this table. (e.g.   I = Insert, D = Delete or U = Update, C = Update, I = insert)</p>
     * 
     */
    @Size(max = 1, message = "OPERATION_TYPE should contain less than or equal to 1 characters")
    @Column(name = "OPERATION_TYPE")
    private String operationType;
    /**
     * <p>Maps to table column: {@code REPLICATION_NO}.</p><p><b>Schema Remarks:</b> Indicator to process data operations at a specified site</p>
     * 
     */
    @Digits(integer = 16, fraction = 0, message = "REPLICATION_NO should have an integer component no longer than 16 and an a fraction component of exact size 0")
    @Column(name = "REPLICATION_NO")
    private Long replicationNo;
    /**
     * <p>Maps to table column: {@code REGISTER_REPLICATION_NO}.</p><p><b>Schema Remarks:</b> Indicator to process data operations at a specified sites register</p>
     * 
     */
    @Digits(integer = 16, fraction = 0, message = "REGISTER_REPLICATION_NO should have an integer component no longer than 16 and an a fraction component of exact size 0")
    @Column(name = "REGISTER_REPLICATION_NO")
    private Long registerReplicationNo;
    /**
     * <p>Maps to table column: {@code DATE_CREATED}.</p><p><b>Schema Remarks:</b> Date record was created</p>
     * 
     */
    @Column(name = "DATE_CREATED", insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    /**
     * <p>Maps to table column: {@code USER_CREATED}.</p><p><b>Schema Remarks:</b> Employee who created record</p>
     * 
     */
    @Column(name = "USER_CREATED", insertable = true, updatable = false)
    private String userCreated;
    /**
     * <p>Maps to table column: {@code DATE_MODIFIED}.</p><p><b>Schema Remarks:</b> Date record was modified by the application</p>
     * 
     */
    @Column(name = "DATE_MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    /**
     * <p>Maps to table column: {@code USER_MODIFIED}.</p><p><b>Schema Remarks:</b> Employee who modified record</p>
     * 
     */
    @Column(name = "USER_MODIFIED")
    private String userModified;
    /**
     * <p>Static value for named query 'DOMAIN_REF_BY_DESCRIPTION'<p>
     * <p>JPA Query:
     * </p>{@code Select dr from DomainRefEntity dr Where dr.description like :description}
     * 
     */
    public final static String DOMAIN_REF_BY_DESCRIPTION = "DOMAIN_REF_BY_DESCRIPTION";

    /**
     * <P>Empty constructor used to create new DomainRefEntity.</P>
     * 
     */
    public DomainRefEntity() {
    }


    /**
     * An equals implementation that only compares the identifier fields (business keys).
     * 
     */
    @Override
    public boolean equals(Object obj) {
        // Object level comparison
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass()!= obj.getClass()) {
            return false;
        }
        final DomainRefEntity other = ((DomainRefEntity) obj);
        // Identifier level comparison
        if (this.identifier.getDomainRefId() == null) {
            return false;
        } else {
            if (!this.identifier.getDomainRefId().equals(other.identifier.getDomainRefId())) {
                return false;
            }
        }
        return true;
    }

    /**
     * An equals impelmentation that only compares the non-identifier fields (data fields).
     * 
     */
    public boolean equalsData(Object obj) {
        // Object level comparison
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass()!= obj.getClass()) {
            return false;
        }
        final DomainRefEntity other = ((DomainRefEntity) obj);
        // Data level comparison
        if (publicFl == null) {
            if (other.publicFl!= null) {
                return false;
            }
        } else {
            if (!publicFl.equals(other.publicFl)) {
                return false;
            }
        }
        if (enforceFl == null) {
            if (other.enforceFl!= null) {
                return false;
            }
        } else {
            if (!enforceFl.equals(other.enforceFl)) {
                return false;
            }
        }
        if (description == null) {
            if (other.description!= null) {
                return false;
            }
        } else {
            if (!description.equals(other.description)) {
                return false;
            }
        }
        if (defaultCdValue == null) {
            if (other.defaultCdValue!= null) {
                return false;
            }
        } else {
            if (!defaultCdValue.equals(other.defaultCdValue)) {
                return false;
            }
        }
        if (operationType == null) {
            if (other.operationType!= null) {
                return false;
            }
        } else {
            if (!operationType.equals(other.operationType)) {
                return false;
            }
        }
        if (replicationNo == null) {
            if (other.replicationNo!= null) {
                return false;
            }
        } else {
            if (!replicationNo.equals(other.replicationNo)) {
                return false;
            }
        }
        if (registerReplicationNo == null) {
            if (other.registerReplicationNo!= null) {
                return false;
            }
        } else {
            if (!registerReplicationNo.equals(other.registerReplicationNo)) {
                return false;
            }
        }
        return true;
    }

    /**
     * An equals implementation that compares all fields.
     * 
     */
    public boolean equalsAll(Object obj) {
        // Object level comparison
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass()!= obj.getClass()) {
            return false;
        }
        final DomainRefEntity other = ((DomainRefEntity) obj);
        // Identifier level comparison
        if (this.identifier.getDomainRefId() == null) {
            return false;
        } else {
            if (!this.identifier.getDomainRefId().equals(other.identifier.getDomainRefId())) {
                return false;
            }
        }
        // Data level comparison
        if (publicFl == null) {
            if (other.publicFl!= null) {
                return false;
            }
        } else {
            if (!publicFl.equals(other.publicFl)) {
                return false;
            }
        }
        if (enforceFl == null) {
            if (other.enforceFl!= null) {
                return false;
            }
        } else {
            if (!enforceFl.equals(other.enforceFl)) {
                return false;
            }
        }
        if (description == null) {
            if (other.description!= null) {
                return false;
            }
        } else {
            if (!description.equals(other.description)) {
                return false;
            }
        }
        if (defaultCdValue == null) {
            if (other.defaultCdValue!= null) {
                return false;
            }
        } else {
            if (!defaultCdValue.equals(other.defaultCdValue)) {
                return false;
            }
        }
        if (operationType == null) {
            if (other.operationType!= null) {
                return false;
            }
        } else {
            if (!operationType.equals(other.operationType)) {
                return false;
            }
        }
        if (replicationNo == null) {
            if (other.replicationNo!= null) {
                return false;
            }
        } else {
            if (!replicationNo.equals(other.replicationNo)) {
                return false;
            }
        }
        if (registerReplicationNo == null) {
            if (other.registerReplicationNo!= null) {
                return false;
            }
        } else {
            if (!registerReplicationNo.equals(other.registerReplicationNo)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        // If any ID columns are null, compare just the object instance it self.  This preserves objects that have not been assigned ID's yet.
        if (this.identifier.getDomainRefId() == null) {
            return super.hashCode();
        }
        	
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier.getDomainRefId() == null) ? 0 : identifier.getDomainRefId().hashCode());
        return result;
    }

    /**
     * Implementation of comparable.<p>This implementation of Comparable uses 'Natural Ordering' of the primary key columns
     * starting with the first ID column and ending with the last.  The exception is that 'null' id columns are treated as highest value (AFTER).
     * If the other object is null, then this object will be returned as BEFORE.</p>
     * 
     * @see <a href="http://docs.oracle.com/javase/tutorial/collections/interfaces/order.html">Object Ordering</a>
     * 
     */


    /**
     * This method displays bean properties for debugging purposes.  Do not use for anything else as it may change to enhance debugging in the future.
     * 
     */
    public String toString() {
        final StringBuilder temp = new StringBuilder();
        	
        temp.append("\n");
        temp.append("DomainRefEntity (DOMAIN_REF)\n");
        temp.append("  ID   1 : domainRefId                    : DOMAIN_REF_ID                  : ").append(this.identifier.getDomainRefId()).append("\n");
        temp.append("       2 : publicFl                       : PUBLIC_FL                      : ").append(publicFl).append("\n");
        temp.append("       3 : enforceFl                      : ENFORCE_FL                     : ").append(enforceFl).append("\n");
        temp.append("       4 : description                    : DESCRIPTION                    : ").append(description).append("\n");
        temp.append("       5 : defaultCdValue                 : DEFAULT_CD_VALUE               : ").append(defaultCdValue).append("\n");
        temp.append("  AD   6 : dateChanged                    : DATE_CHANGED                   : ").append(dateChanged).append("\n");
        temp.append("       7 : operationType                  : OPERATION_TYPE                 : ").append(operationType).append("\n");
        temp.append("       8 : replicationNo                  : REPLICATION_NO                 : ").append(replicationNo).append("\n");
        temp.append("       9 : registerReplicationNo          : REGISTER_REPLICATION_NO        : ").append(registerReplicationNo).append("\n");
        temp.append("  AD  10 : dateCreated                    : DATE_CREATED                   : ").append(dateCreated).append("\n");
        temp.append("  AD  11 : userCreated                    : USER_CREATED                   : ").append(userCreated).append("\n");
        temp.append("  AD  12 : dateModified                   : DATE_MODIFIED                  : ").append(dateModified).append("\n");
        temp.append("  AD  13 : userModified                   : USER_MODIFIED                  : ").append(userModified).append("\n");
        	
        return temp.toString();
    }

}
