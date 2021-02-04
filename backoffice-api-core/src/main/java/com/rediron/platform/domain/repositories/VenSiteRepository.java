package com.rediron.platform.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rediron.platform.domain.entities.VenSiteEntity;
import com.rediron.platform.domain.entities.VenSiteEntityId;

public interface VenSiteRepository extends CrudRepository<VenSiteEntity, VenSiteEntityId>{
	
	@Query("Select v from VenSiteEntity v where v.vendorId = ?1")
	List<VenSiteEntity> getVendorSitesForVendor(String vendorId);
}
