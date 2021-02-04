//package com.rediron.platform.domain.services;
//
//import java.math.BigDecimal;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.rediron.platform.domain.entities.VendorItem;
//import com.rediron.platform.domain.repositories.VendorItemRepository;
//
//@Service
//public class VendorItemService {
//	
//	@Autowired
//	private VendorItemRepository vendorItemRepository;
//	
//	public VendorItem getVendorItem(int skuNo) {
//		VendorItem vendorItem = vendorItemRepository.findBySkuNo(new BigDecimal(skuNo));
//		return vendorItem;
//	}
//
//}
