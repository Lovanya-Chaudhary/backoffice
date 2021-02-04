package com.rediron.platform.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.rediron.platform.domain.entities.InvtoryEntity;
import com.rediron.platform.domain.entities.InvtoryEntityId;

public interface InvtoryRepository extends CrudRepository<InvtoryEntity, InvtoryEntityId>{

}
