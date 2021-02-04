package com.rediron.platform.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rediron.platform.domain.entities.VendorItemEntity;
import com.rediron.platform.domain.entities.VendorItemEntityId;

public interface VendorItemRepository extends CrudRepository<VendorItemEntity, VendorItemEntityId>{
	
	@Query("Select v from VendorItemEntity v Where v.skuNo = ?1 and v.siteNo = ?2 ")
	List<VendorItemEntity> getVendorItemsForSkuAndSite(int skuNo, int siteNo);
	
	@Query("Select v from VendorItemEntity v Where v.skuNo = ?1 and v.vendorId = ?2 ")
	List<VendorItemEntity> getVendorItemsForSkuAndVendorId(int skuNo, String vendorId);
}
