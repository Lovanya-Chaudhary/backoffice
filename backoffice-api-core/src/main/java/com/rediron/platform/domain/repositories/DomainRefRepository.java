package com.rediron.platform.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import com.rediron.platform.domain.entities.DomainRefEntity;
import com.rediron.platform.domain.entities.DomainRefEntityId;

public interface DomainRefRepository extends CrudRepository<DomainRefEntity, DomainRefEntityId>{
}
