package com.rediron.platform.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.rediron.platform.domain.entities.VendorEntity;
import com.rediron.platform.domain.entities.VendorEntityId;

public interface VendorsRepository extends CrudRepository<VendorEntity, VendorEntityId>{
}
