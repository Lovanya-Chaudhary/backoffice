package com.rediron.platform.domain.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rediron.platform.domain.entities.ItemupcEntity;
import com.rediron.platform.domain.entities.ItemupcEntityId;

public interface ItemupcRepository extends CrudRepository<ItemupcEntity, ItemupcEntityId>{
	List<ItemupcEntity> findByIdentifierUpcId(String upcId);
}
