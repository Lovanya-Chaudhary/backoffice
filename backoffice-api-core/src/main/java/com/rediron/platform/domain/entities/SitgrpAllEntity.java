//
package com.rediron.platform.domain.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import com.rediron.platform.domain.rnet.DtoToEntity;
import com.rediron.platform.domain.rnet.EntityManagerContext;
import com.rediron.platform.domain.rnet.RNetAbstractEntity;
import com.tomax.api.DataTransferObject;
import com.tomax.api.RNetExceptionProxy;
import com.tomax.api.RNetUnexpectedException;
import com.tomax.site.dto.SitgrpAllDTO;
import com.tomax.site.dto.SitgrpAllDTOId;


@SuppressWarnings("all")
//@NamedQueries({
//    @NamedQuery(name = "NQ_SITES_FOR_GROUP", query = "Select sg from SitgrpAllEntity sg Where groupId = :groupId "),
//    @NamedQuery(name = "NQ_GROUP_FOR_SITE", query = "Select sg from SitgrpAllEntity sg Where siteNo = :siteNo ")
//})
@Entity
@Table(name = "SITGRP_ALL")
public class SitgrpAllEntity
    extends RNetAbstractEntity<SitgrpAllEntity, SitgrpAllDTO>
{

    /**
     * @hide<P><I>Not for public use.</I></P><P>Used to implement serialization.</P>
     * 
     */
    private final static long serialVersionUID = 1L;
    /**
     * Embedded composite identifier.
     * 
     */
    @EmbeddedId
    private SitgrpAllEntityId identifier;
    /**
     * <p>Maps to table column: {@code DATE_CREATED}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "DATE_CREATED", insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    /**
     * <p>Maps to table column: {@code USER_CREATED}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "USER_CREATED", insertable = true, updatable = false)
    private String userCreated;
    /**
     * <p>Maps to table column: {@code DATE_MODIFIED}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "DATE_MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    /**
     * <p>Maps to table column: {@code USER_MODIFIED}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Column(name = "USER_MODIFIED")
    private String userModified;
    /**
     * <p>Maps to table column: {@code FRANCHISE_HQ_FL}.</p><p><b>Schema Remarks:</b> <i>No remarks found</i></p>
     * 
     */
    @Size(max = 1, message = "FRANCHISE_HQ_FL should contain less than or equal to 1 characters")
    @Column(name = "FRANCHISE_HQ_FL")
    private String franchiseHqFl;
    /**
     * <p>Maps to table column: {@code OWNER_ID}.</p><p><b>Schema Remarks:</b> References the entity ownership for this site group</p>
     * 
     */
    @Digits(integer = 8, fraction = 0, message = "OWNER_ID should have an integer component no longer than 8 and an a fraction component of exact size 0")
    @Column(name = "OWNER_ID")
    private Integer ownerId;
    /**
     * <p>Static value for named query 'NQ_SITES_FOR_GROUP'<p>
     * <p>JPA Query:
     * </p>{@code Select sg from SitgrpAllEntity sg Where groupId = :groupId }
     * 
     */
    public final static String NQ_SITES_FOR_GROUP = "NQ_SITES_FOR_GROUP";
    /**
     * <p>Static value for named query 'NQ_GROUP_FOR_SITE'<p>
     * <p>JPA Query:
     * </p>{@code Select sg from SitgrpAllEntity sg Where siteNo = :siteNo }
     * 
     */
    public final static String NQ_GROUP_FOR_SITE = "NQ_GROUP_FOR_SITE";

    /**
     * <P>Empty constructor used to create new SitgrpAllEntity.</P>
     * 
     */
    public SitgrpAllEntity() {
        this.identifier = new SitgrpAllEntityId();
        	
        _init();
    }

    /**
     * Constructor used to create a new SitgrpAllEntity where the identifier will be created by copying the fields of the provided sourceId.
     * 
     */
    public SitgrpAllEntity(SitgrpAllEntityId sourceId) {
        this.identifier = new SitgrpAllEntityId(sourceId.getGroupId(), sourceId.getSiteNo());
        	
        _init();
    }

    /**
     * Constructor used to create a SitgrpAllEntity where the identifier will be created internally using the id arguments provided.  This is a convenient constructor used instead of instantiating a new identifier.
     * 
     */
    public SitgrpAllEntity(String groupId, Integer siteNo) {
        this.identifier = new SitgrpAllEntityId(groupId, siteNo);
        	
        _init();
    }

    /**
     * Constructor used to create a SitgrpAllEntity where contents are provided by an object array typically returned by a native query
     * 
     */
    public SitgrpAllEntity(Object[] objectArray) {
        this.identifier = new SitgrpAllEntityId(((objectArray[ 0 ] == null)?null:((String) objectArray[ 0 ])), ((objectArray[ 1 ] == null)?null:((BigDecimal) objectArray[ 1 ]).intValue()));
        	
        this.dateCreated = ((objectArray[ 2 ] == null)?null:new Date(((Timestamp) objectArray[ 2 ]).getTime()));
        this.userCreated = ((objectArray[ 3 ] == null)?null:((String) objectArray[ 3 ]));
        this.dateModified = ((objectArray[ 4 ] == null)?null:new Date(((Timestamp) objectArray[ 4 ]).getTime()));
        this.userModified = ((objectArray[ 5 ] == null)?null:((String) objectArray[ 5 ]));
        this.franchiseHqFl = ((objectArray[ 6 ] == null)?null:((String) objectArray[ 6 ]));
        this.ownerId = ((objectArray[ 7 ] == null)?null:((BigDecimal) objectArray[ 7 ]).intValue());
        	
        _init();
    }

    /**
     * <P>Copy constructor used to copy a SitgrpAllEntity where the identifier will be created by copying the fields of the provided sourceId.</P>
     * 
     */
    public SitgrpAllEntity(SitgrpAllEntity source, SitgrpAllEntityId sourceId) {
        this.identifier = new SitgrpAllEntityId(sourceId.getGroupId(), sourceId.getSiteNo());
        	
        _init();
        	
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.franchiseHqFl = source.getFranchiseHqFl();
        this.ownerId = source.getOwnerId();
    }

    /**
     * <P>Copy constructor used to copy a SitgrpAllEntity including its identifier.</P>
     * 
     */
    public SitgrpAllEntity(SitgrpAllEntity source) {
        this.identifier = new SitgrpAllEntityId(source.getIdentifier().getGroupId(), source.getIdentifier().getSiteNo());
        	
        _init();
        	
        this.dateCreated = source.getDateCreated();
        this.userCreated = source.getUserCreated();
        this.dateModified = source.getDateModified();
        this.userModified = source.getUserModified();
        this.franchiseHqFl = source.getFranchiseHqFl();
        this.ownerId = source.getOwnerId();
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public SitgrpAllEntityId getIdentifier() {
        return this.identifier;
    }

    /**
     * Embedded composite identifier.
     * 
     */
    public void setIdentifier(SitgrpAllEntityId identifier) {
        this.identifier = identifier;
    }

    /**
     * Getter for {@link SitgrpAllEntityId#groupId identifier.groupId}
     * 
     */
    @Access(AccessType.PROPERTY)
    @Column(name = "GROUP_ID", insertable = false, updatable = false)
    public String getGroupId() {
        return this.identifier.getGroupId();
    }

    /**
     * Setter for {@link SitgrpAllEntityId#groupId identifier.groupId}
     * 
     */
    public String setGroupId(String groupId) {
        this.identifier.setGroupId(groupId);
        return this.identifier.getGroupId();
    }

    /**
     * Getter for {@link SitgrpAllEntityId#siteNo identifier.siteNo}
     * 
     */
    @Access(AccessType.PROPERTY)
    @Column(name = "SITE_NO", insertable = false, updatable = false)
    public Integer getSiteNo() {
        return this.identifier.getSiteNo();
    }

    /**
     * Setter for {@link SitgrpAllEntityId#siteNo identifier.siteNo}
     * 
     */
    public Integer setSiteNo(Integer siteNo) {
        this.identifier.setSiteNo(siteNo);
        return this.identifier.getSiteNo();
    }

    /**
     * Getter for {@link #dateCreated dateCreated}
     * 
     */
    public Date getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Setter for {@link #dateCreated dateCreated}
     * 
     */
    public Date setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this.dateCreated;
    }

    /**
     * Getter for {@link #userCreated userCreated}
     * 
     */
    public String getUserCreated() {
        return this.userCreated;
    }

    /**
     * Setter for {@link #userCreated userCreated}
     * 
     */
    public String setUserCreated(String userCreated) {
        this.userCreated = userCreated;
        return this.userCreated;
    }

    /**
     * Getter for {@link #dateModified dateModified}
     * 
     */
    public Date getDateModified() {
        return this.dateModified;
    }

    /**
     * Setter for {@link #dateModified dateModified}
     * 
     */
    public Date setDateModified(Date dateModified) {
        this.dateModified = dateModified;
        return this.dateModified;
    }

    /**
     * Getter for {@link #userModified userModified}
     * 
     */
    public String getUserModified() {
        return this.userModified;
    }

    /**
     * Setter for {@link #userModified userModified}
     * 
     */
    public String setUserModified(String userModified) {
        this.userModified = userModified;
        return this.userModified;
    }

    /**
     * Getter for {@link #franchiseHqFl franchiseHqFl}
     * 
     */
    public String getFranchiseHqFl() {
        return this.franchiseHqFl;
    }

    /**
     * Setter for {@link #franchiseHqFl franchiseHqFl}
     * 
     */
    public String setFranchiseHqFl(String franchiseHqFl) {
        this.franchiseHqFl = franchiseHqFl;
        return this.franchiseHqFl;
    }

    /**
     * Getter for {@link #ownerId ownerId}
     * 
     */
    public Integer getOwnerId() {
        return this.ownerId;
    }

    /**
     * Setter for {@link #ownerId ownerId}
     * 
     */
    public Integer setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        return this.ownerId;
    }

    /**
     * @hide<P><I>Not for public use.</I></P>
     * 
     */
    private void _initDefaults() {
        if (this.franchiseHqFl == null) {
            this.franchiseHqFl = "N";
        }
        if (this.ownerId == null) {
            this.ownerId = Integer.valueOf(1);
        }
    }

    /**
     * @hide<P><I>Not for public use.</I></P><P>Initializes internal collections.</P>
     * 
     */
    private void _init() {
    }

    /**
     * <P>Used to copy a SitgrpAllEntity including non-identifier fields only.</P>
     * 
     */
    public SitgrpAllEntity copyData() {
        final SitgrpAllEntity copy = new SitgrpAllEntity();
        	
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.franchiseHqFl = this.franchiseHqFl;
        copy.ownerId = this.ownerId;
        	
        return copy;
    }

    /**
     * <P>Used to copy a SitgrpAllEntity including its identifier.</P>
     * 
     */
    public SitgrpAllEntity copyDataAndId() {
        final SitgrpAllEntity copy = new SitgrpAllEntity();
        	
        copy.getIdentifier().setGroupId(this.getIdentifier().getGroupId());
        copy.getIdentifier().setSiteNo(this.getIdentifier().getSiteNo());
        	
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.franchiseHqFl = this.franchiseHqFl;
        copy.ownerId = this.ownerId;
        	
        return copy;
    }

    /**
     * <P>Used to copy a SitgrpAllEntity including data and GENERATED identifiers only.</P>
     * 
     */
    public SitgrpAllEntity copyDataAndIdNonGenerated() {
        final SitgrpAllEntity copy = new SitgrpAllEntity();
        	
        copy.getIdentifier().setGroupId(this.getIdentifier().getGroupId());
        copy.getIdentifier().setSiteNo(this.getIdentifier().getSiteNo());
        	
        copy.dateCreated = this.dateCreated;
        copy.userCreated = this.userCreated;
        copy.dateModified = this.dateModified;
        copy.userModified = this.userModified;
        copy.franchiseHqFl = this.franchiseHqFl;
        copy.ownerId = this.ownerId;
        	
        return copy;
    }

    /**
     * @hide<P><I>Not for public use.</I></P>
     * 
     */
    @PrePersist
    private void _prePersist() {
        _initDefaults();
        	
        final Date now = new Date();
        final Timestamp nowTimestamp = new Timestamp(now.getTime());
        this.dateCreated = now;
        this.userCreated = ("~"+ EntityManagerContext.getAuditUser());
        this.dateModified = now;
        this.userModified = ("~"+ EntityManagerContext.getAuditUser());
    }

    /**
     * @hide<P><I>Not for public use.</I></P>
     * 
     */
    @PreUpdate
    private void _preUpdate() {
        final Date now = new Date();
        final Timestamp nowTimestamp = new Timestamp(now.getTime());
        this.dateModified = now;
        this.userModified = ("~"+ EntityManagerContext.getAuditUser());
    }

    public static SitgrpAllDTO toDTO(SitgrpAllEntity entity) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return SitgrpAllEntity.toDTO(entity, map);
    }

    public static SitgrpAllDTO toDTO(SitgrpAllEntity entity, Map<Object, Object> map) {
        try {
            if (entity == null) {
                return null;
            }
            	
            if (map == null) {
                map = new HashMap<Object, Object>();
            } else {
                if (map.get(entity)!= null) {
                    return ((SitgrpAllDTO) map.get(entity));
                }
            }
            	
            entity.toDTO(map);
            	
            return ((SitgrpAllDTO) map.get(entity));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public static SitgrpAllEntity toEntity(SitgrpAllDTO dto) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        return SitgrpAllEntity.toEntity(dto, map);
    }

    public static SitgrpAllEntity toEntity(SitgrpAllDTO dto, Map<Object, Object> map) {
        try {
            if (dto == null) {
                return null;
            }
            	
            if ((map!= null)&&(map.get(dto)!= null)) {
                return ((SitgrpAllEntity) map.get(dto));
            }
            	
            return ((SitgrpAllEntity) DtoToEntity.toEntity(SitgrpAllEntity.class, ((DataTransferObject) dto)));
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public void assignAllChildren(SitgrpAllDTO dto, Map<Object, Object> map) {
        toSimpleEntity(dto, map);
    }

    public void assignAllChildren(Map<Object, Object> map, Stack<Object> stack) {
        toSimpleDto(map);
    }

    public void assignAllParents(Map<Object, Object> map) {
        toSimpleDto(map);
    }

    protected SitgrpAllDTO toSimpleDto(Map<Object, Object> map) {
        if (map.get(this)!= null) {
            return ((SitgrpAllDTO) map.get(this));
        }
        try {
            SitgrpAllDTO dto = new SitgrpAllDTO();
            dto.setIdentifier(new SitgrpAllDTOId(this.getGroupId(), this.getSiteNo()));
            	
            dto.setDateCreated(this.getDateCreated());
            dto.setUserCreated(this.getUserCreated());
            dto.setDateModified(this.getDateModified());
            dto.setUserModified(this.getUserModified());
            dto.setFranchiseHqFl(this.getFranchiseHqFl());
            dto.setOwnerId(this.getOwnerId());
            	
            map.put(this, dto);
            	
            return dto;
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public SitgrpAllEntity toSimpleEntity(DataTransferObject dto, Map<Object, Object> map) {
        if (map.get(dto)!= null) {
            return ((SitgrpAllEntity) map.get(dto));
        }
        try {
            SitgrpAllDTO oldDto = ((SitgrpAllDTO) dto);
            SitgrpAllEntity entity = new SitgrpAllEntity();
            entity.setIdentifier(new SitgrpAllEntityId(oldDto.getGroupId(), oldDto.getSiteNo()));
            	
            entity.setDateCreated(oldDto.getDateCreated());
            entity.setUserCreated(oldDto.getUserCreated());
            entity.setDateModified(oldDto.getDateModified());
            entity.setUserModified(oldDto.getUserModified());
            entity.setFranchiseHqFl(oldDto.getFranchiseHqFl());
            entity.setOwnerId(oldDto.getOwnerId());
            	
            map.put(dto, entity);
            	
            return entity;
        } catch (Throwable e) {
            throw new RNetUnexpectedException(new RNetExceptionProxy(e));
        }
    }

    public List<RNetAbstractEntity> getParents() {
        return null;
    }

    public List<Set<? extends RNetAbstractEntity>> getChildren() {
        return null;
    }

    public static List<SitgrpAllDTO> toDTOList(Collection<SitgrpAllEntity> entities) {
        if (entities == null) {
            return null;
        }
        	
        List<SitgrpAllDTO> dtos = new ArrayList<SitgrpAllDTO>();
        for (SitgrpAllEntity entity: entities) {
            dtos.add(toDTO(entity));
        }
        	
        return dtos;
    }

    @Deprecated
    public static List<SitgrpAllDTO> toDTO(Collection<SitgrpAllEntity> entities) {
        return toDTOList(entities);
    }

    public static SitgrpAllDTOId toDTOId(SitgrpAllEntityId id) {
        if (id == null) {
            return null;
        }
        return new SitgrpAllDTOId(id.getGroupId(), id.getSiteNo());
    }

    @Deprecated
    public static SitgrpAllDTOId toDTO(SitgrpAllEntityId id) {
        return toDTOId(id);
    }

    public static List<SitgrpAllDTOId> toDTOIdList(Collection<SitgrpAllEntityId> entityIds) {
        if (entityIds == null) {
            return null;
        }
        	
        List<SitgrpAllDTOId> dtoIds = new ArrayList<SitgrpAllDTOId>();
        for (SitgrpAllEntityId entity: entityIds) {
            dtoIds.add(toDTOId(entity));
        }
        	
        return dtoIds;
    }

    public static Set<SitgrpAllEntity> toEntitySet(Collection<SitgrpAllDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        Set<SitgrpAllEntity> entities = new HashSet<SitgrpAllEntity>();
        for (SitgrpAllDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static Set<SitgrpAllEntity> toEntity(Collection<SitgrpAllDTO> dtos) {
        return toEntitySet(dtos);
    }

    public static SortedSet<SitgrpAllEntity> toEntitySortedSet(Collection<SitgrpAllDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        SortedSet<SitgrpAllEntity> entities = new TreeSet<SitgrpAllEntity>();
        for (SitgrpAllDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    @Deprecated
    public static SortedSet<SitgrpAllEntity> toEntitySorted(Collection<SitgrpAllDTO> dtos) {
        return toEntitySortedSet(dtos);
    }

    public static List<SitgrpAllEntity> toEntityList(Collection<SitgrpAllDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        	
        List<SitgrpAllEntity> entities = new ArrayList<SitgrpAllEntity>();
        for (SitgrpAllDTO dto: dtos) {
            entities.add(toEntity(dto));
        }
        	
        return entities;
    }

    public static SitgrpAllEntityId toEntityId(SitgrpAllDTOId dto) {
        if (dto == null) {
            return null;
        }
        return new SitgrpAllEntityId(dto.getGroupId(), dto.getSiteNo());
    }

    @Deprecated
    public static SitgrpAllEntityId toEntity(SitgrpAllDTOId id) {
        return toEntityId(id);
    }

    public static List<SitgrpAllEntityId> toEntityIdList(Collection<SitgrpAllDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        List<SitgrpAllEntityId> entityIds = new ArrayList<SitgrpAllEntityId>();
        for (SitgrpAllDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static Set<SitgrpAllEntityId> toEntityIdSet(Collection<SitgrpAllDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        Set<SitgrpAllEntityId> entityIds = new HashSet<SitgrpAllEntityId>();
        for (SitgrpAllDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
    }

    public static SortedSet<SitgrpAllEntityId> toEntityIdSortedSet(Collection<SitgrpAllDTOId> dtoIds) {
        if (dtoIds == null) {
            return null;
        }
        	
        SortedSet<SitgrpAllEntityId> entityIds = new TreeSet<SitgrpAllEntityId>();
        for (SitgrpAllDTOId dtoId: dtoIds) {
            entityIds.add(toEntityId(dtoId));
        }
        	
        return entityIds;
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
        final SitgrpAllEntity other = ((SitgrpAllEntity) obj);
        // Identifier level comparison
        if (this.identifier.getGroupId() == null) {
            return false;
        } else {
            if (!this.identifier.getGroupId().equals(other.identifier.getGroupId())) {
                return false;
            }
        }
        if (this.identifier.getSiteNo() == null) {
            return false;
        } else {
            if (!this.identifier.getSiteNo().equals(other.identifier.getSiteNo())) {
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
        final SitgrpAllEntity other = ((SitgrpAllEntity) obj);
        // Data level comparison
        if (franchiseHqFl == null) {
            if (other.franchiseHqFl!= null) {
                return false;
            }
        } else {
            if (!franchiseHqFl.equals(other.franchiseHqFl)) {
                return false;
            }
        }
        if (ownerId == null) {
            if (other.ownerId!= null) {
                return false;
            }
        } else {
            if (!ownerId.equals(other.ownerId)) {
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
        final SitgrpAllEntity other = ((SitgrpAllEntity) obj);
        // Identifier level comparison
        if (this.identifier.getGroupId() == null) {
            return false;
        } else {
            if (!this.identifier.getGroupId().equals(other.identifier.getGroupId())) {
                return false;
            }
        }
        if (this.identifier.getSiteNo() == null) {
            return false;
        } else {
            if (!this.identifier.getSiteNo().equals(other.identifier.getSiteNo())) {
                return false;
            }
        }
        // Data level comparison
        if (franchiseHqFl == null) {
            if (other.franchiseHqFl!= null) {
                return false;
            }
        } else {
            if (!franchiseHqFl.equals(other.franchiseHqFl)) {
                return false;
            }
        }
        if (ownerId == null) {
            if (other.ownerId!= null) {
                return false;
            }
        } else {
            if (!ownerId.equals(other.ownerId)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        // If any ID columns are null, compare just the object instance it self.  This preserves objects that have not been assigned ID's yet.
        if ((this.identifier.getGroupId() == null)||(this.identifier.getSiteNo() == null)) {
            return super.hashCode();
        }
        	
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier.getGroupId() == null) ? 0 : identifier.getGroupId().hashCode());
        result = prime * result + ((identifier.getSiteNo() == null) ? 0 : identifier.getSiteNo().hashCode());
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
    public int compareTo(SitgrpAllEntity other) {
        return this.identifier.compareTo(other.identifier);
    }

    /**
     * This method displays bean properties for debugging purposes.  Do not use for anything else as it may change to enhance debugging in the future.
     * 
     */
    public String toString() {
        final StringBuilder temp = new StringBuilder();
        	
        temp.append("\n");
        temp.append("SitgrpAllEntity (SITGRP_ALL)\n");
        temp.append("  ID   1 : groupId                        : GROUP_ID                       : ").append(this.identifier.getGroupId()).append("\n");
        temp.append("  ID   2 : siteNo                         : SITE_NO                        : ").append(this.identifier.getSiteNo()).append("\n");
        temp.append("  AD   3 : dateCreated                    : DATE_CREATED                   : ").append(dateCreated).append("\n");
        temp.append("  AD   4 : userCreated                    : USER_CREATED                   : ").append(userCreated).append("\n");
        temp.append("  AD   5 : dateModified                   : DATE_MODIFIED                  : ").append(dateModified).append("\n");
        temp.append("  AD   6 : userModified                   : USER_MODIFIED                  : ").append(userModified).append("\n");
        temp.append("       7 : franchiseHqFl                  : FRANCHISE_HQ_FL                : ").append(franchiseHqFl).append("\n");
        temp.append("       8 : ownerId                        : OWNER_ID                       : ").append(ownerId).append("\n");
        	
        return temp.toString();
    }

}
