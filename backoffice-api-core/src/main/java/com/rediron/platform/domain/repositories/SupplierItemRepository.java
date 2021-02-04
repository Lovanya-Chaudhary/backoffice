package com.rediron.platform.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.rediron.platform.domain.entities.SupplierItemEntity;

// CrudRepository<SupplierItemEntity, Long>
public interface SupplierItemRepository extends CrudRepository<SupplierItemEntity, Long> {
	
	List<SupplierItemEntity> findBySkuNo(Integer skuNumber);

}
