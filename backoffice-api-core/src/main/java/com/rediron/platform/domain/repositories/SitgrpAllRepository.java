package com.rediron.platform.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rediron.platform.domain.entities.SitgrpAllEntity;
import com.rediron.platform.domain.entities.SitgrpAllEntityId;

public interface SitgrpAllRepository extends CrudRepository<SitgrpAllEntity, SitgrpAllEntityId>{
	 
	@Query("Select sg from SitgrpAllEntity sg Where groupId = ?1")
	List<SitgrpAllEntity> getSiteGroupAll(String siteGroupId);
}
