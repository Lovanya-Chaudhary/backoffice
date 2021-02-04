package com.rediron.platform.domain.rnet;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rediron.platform.domain.entities.VendorItemEntity;
import com.tomax.api.RNetNullArgumentException;
import com.tomax.api.RNetObjectDataConstraintException;
import com.tomax.api.RNetUnableToPerformException;
import com.tomax.api.UserIdentity;
import com.tomax.commons.validator.RNetObjectValidator;
import com.tomax.config.dto.ConfigrecDTO;
import com.tomax.site.dto.SiteDTO;
import com.tomax.vendor.dto.VendorDTO;
import com.tomax.vendor.dto.VendorItemDTO;

@Component
public class VendorValidator {
	
	@Autowired
	private RnetDomainConnector rnetDomainConnector;
	
	public static <T> void performBeanValidations(T t) {
		RNetObjectValidator.validate(t);
	}
	
	private void validateOrManipulateCostFieldsFromVendorItem(VendorItemEntity vendorItemEntity) {
		if (vendorItemEntity.getUnitCost() != null) {
			if (vendorItemEntity.getPackCost() != null) {
				if (vendorItemEntity.getUnitCost().compareTo(vendorItemEntity.getPackCost()) != 0) {
					if (vendorItemEntity.getPackQty() == null) {
						throw new RNetObjectDataConstraintException("UnitCost and PackCost don't match.");
					} else {
						if (vendorItemEntity.getPackQty() == null
								|| vendorItemEntity.getPackQty().equals(
										new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP))) {
							vendorItemEntity.setPackQty(new BigDecimal(1.00).setScale(2, BigDecimal.ROUND_HALF_UP));
						}
						if (vendorItemEntity.getPackCost().compareTo(
								new BigDecimal(0).setScale(4, BigDecimal.ROUND_HALF_UP)) > 0) {
							// packCost is populated, so setUnitCost
							BigDecimal calculatedUnitCost = vendorItemEntity.getPackCost()
									.divide(vendorItemEntity.getPackQty(), 4, BigDecimal.ROUND_HALF_UP);
							if (vendorItemEntity.getUnitCost().compareTo(
									new BigDecimal(0).setScale(4, BigDecimal.ROUND_HALF_UP)) > 0
									&& !vendorItemEntity.getUnitCost().equals(calculatedUnitCost)) {
								throw new RNetObjectDataConstraintException("UnitCost and PackCost don't match.");
							}
							vendorItemEntity.setUnitCost(calculatedUnitCost);
						} else {
							// unitPrice1 is populated, so setPrice1
							vendorItemEntity.setPackCost(vendorItemEntity.getUnitCost());
							vendorItemEntity.setPackQty(new BigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP));
						}
					}
				}
			} else {
				vendorItemEntity.setPackCost(vendorItemEntity.getUnitCost());
				vendorItemEntity.setPackQty(new BigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		} else {
			// unitPrice1 is null, so go off price1
			if (vendorItemEntity.getPackCost() != null) {
				if (vendorItemEntity.getUnitCost() != null) {
					if (!vendorItemEntity.getUnitCost().equals(vendorItemEntity.getPackCost())) {
						if (vendorItemEntity.getPackQty() == null) {
							throw new RNetObjectDataConstraintException("UnitCost and PackCost don't match.");
						} else {
							if (vendorItemEntity.getPackQty() == null
									|| vendorItemEntity.getPackQty().equals(
											new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP))) {
								vendorItemEntity.setPackQty(new BigDecimal(1.00).setScale(2, BigDecimal.ROUND_HALF_UP));
							}
							BigDecimal calculatedUnitCost = vendorItemEntity.getPackCost()
									.divide(vendorItemEntity.getPackQty()).setScale(4, BigDecimal.ROUND_HALF_UP);
							;
							if (!vendorItemEntity.getUnitCost().equals(calculatedUnitCost)) {
								throw new RNetObjectDataConstraintException("UnitCost and PackCost don't match.");
							}
						}
					}
				} else {
					vendorItemEntity.setUnitCost(vendorItemEntity.getPackCost());
				}
			}
		}
	}
	
	public void validateVendorItem(UserIdentity domainUser, VendorItemEntity vendorItemEntity) {
		validateOrManipulateCostFieldsFromVendorItem(vendorItemEntity);
		performBeanValidations(vendorItemEntity);

		// if this is a brand new vendorItemEntity, the seqNo should be null
		if (vendorItemEntity.getSeqNo() == null) {
			// we are inserting a new vendorItem, so set venType appropriately
			// Make sure the site/sku has a primary vendor, if not, this is it!
			VendorItemDTO primaryVendor = RnetDomainConnector.getPrimaryVendorItem(domainUser,
					vendorItemEntity.getSiteNo(), vendorItemEntity.getSkuNo());
//			VendorItemEntity primaryVendorItemEntity = VendorHelper.getPrimaryVendorItem(em,
//					vendorItemEntity.getSiteNo(), vendorItemEntity.getSkuNo());

			// if for this site/sku there is already a primary vendor item, and it is NOT the same vendor, mark this new
			// one
			// with venType = 'S', otherwise if its for the same vendor, it's OK to set venType to P.
			if (primaryVendor == null
					|| vendorItemEntity.getVendorId().equals(primaryVendor.getVendorId())) {
				vendorItemEntity.setVenType("P");
			} else {
				vendorItemEntity.setVenType("S");
			}
			VendorItemDTO mainVendor = RnetDomainConnector.getMainVendorItem(domainUser, vendorItemEntity.getSiteNo(),
					vendorItemEntity.getSkuNo(), vendorItemEntity.getVendorId());
			
//			VendorItemEntity mainVendorItemEntity = VendorHelper.getMainVendorItem(em, vendorItemEntity.getSiteNo(),
//					vendorItemEntity.getSkuNo(), vendorItemEntity.getVendorId());
			if (mainVendor == null) {
				vendorItemEntity.setMainVendorItemFl("Y");
			} else {
				vendorItemEntity.setMainVendorItemFl("N");
			}
		} else {
			VendorItemDTO persistedVendorItem = RnetDomainConnector.getVendorItem(domainUser, vendorItemEntity.getSeqNo());
//			VendorItemEntity persistedVendorItemEntity = VendorHelper.getVendorItem(em, vendorItemEntity.getSeqNo());
			// we must be updating the vendorItem, so make sure that if the vendorItem's venType has been set to P, that
			// all other vendors for this site/sku are demoted to venType S.
			List<VendorItemEntity> vendorItemsForSiteAndSkus = rnetDomainConnector.getVendorItemsForSkuAndSite(domainUser,
					vendorItemEntity.getSkuNo(), vendorItemEntity.getSiteNo());
//			List<VendorItemEntity> vendorItemsForSiteAndSku = VendorHelper.getVendorItemsForSiteAndSku(em,
//					vendorItemEntity.getSiteNo(), vendorItemEntity.getSkuNo());

			// enforce that we aren't setting this to S, when it was P, cause we need to have at least one Primary, not
			// a bunch of secondaries!
			if (vendorItemEntity.getVenType().equals("S") && persistedVendorItem != null
					&& persistedVendorItem.getVenType().equals("P")) {
				throw new RNetObjectDataConstraintException(
						"You cannot set this to be a secondary vendor type, there needs to be at least one that is primary.  First set which one you want to be primary, and this record will automatically be demoted to 'S'econdary.");
			} else if (vendorItemEntity.getVenType().equals("P") && persistedVendorItem != null
					&& persistedVendorItem.getVenType().equals("S")) {
//				throw new RNetObjectDataConstraintException("getVendorItemsForSiteAndSku method needed !!!!");
				/*
				 * If this was S and now we're setting it to P, then go through all the
				 * other VendorItems and if you items
				 * from a different Vendor that have venType = "P", then:
				 * 1) demote them!
				 * 2) promote any other vendorItems that might exist for the same site/sku/vendorId
				 */
				// changes start 
				for (VendorItemEntity otherVendorItems : vendorItemsForSiteAndSkus) {
					if (otherVendorItems.getVendorId().equals(vendorItemEntity.getVendorId())) {
						otherVendorItems.setVenType(vendorItemEntity.getVenType());
					} else {
						otherVendorItems.setVenType("S");
					}
				}
				// changes end
			}
			// make sure the main_vendor_item_fl is set appropriately.
			if (vendorItemEntity.getMainVendorItemFl().equals("N") && persistedVendorItem != null
					&& persistedVendorItem.getMainVendorItemFl().equals("Y")) {
				throw new RNetObjectDataConstraintException(
						"You cannot set this to be as the mainVendorItem, there needs to be at least one mainVendorItem per site/sku/vendor.  First set which one you want to the main vendorItem, and this vendorItem will automatically be demoted.");
			} else if (vendorItemEntity.getMainVendorItemFl().equals("Y") && persistedVendorItem != null
					&& persistedVendorItem.getMainVendorItemFl().equals("N")) {
				/*
				 * If this was N and now we're setting it to Y, then go through all the
				 * other VendorItems for this site/sku/Vendor and
				 * 1) demote as non primary!
				 */
				List<VendorItemDTO> vendorItemsForVendor = RnetDomainConnector.getVendorItems(domainUser, vendorItemEntity.getSiteNo(), vendorItemEntity.getSkuNo(), vendorItemEntity.getVendorId());
//				List<VendorItemEntity> vendorItemsForVendor = VendorHelper.getVendorItems(em,
//						vendorItemEntity.getSiteNo(), vendorItemEntity.getSkuNo(), vendorItemEntity.getVendorId());
				for (VendorItemDTO otherVendorItems : vendorItemsForVendor) {
					if (otherVendorItems.getSeqNo().equals(vendorItemEntity.getSeqNo())) {
						otherVendorItems.setMainVendorItemFl(vendorItemEntity.getMainVendorItemFl());
					} else {
						otherVendorItems.setMainVendorItemFl("N");
					}
				}
			}
		}

		// validate that the site can have this vendorItem record
		SiteDTO siteDto = RnetDomainConnector.getSite(domainUser, vendorItemEntity.getSiteNo());
//		SiteEntity siteEntity = SiteHelper.getSite(em, vendorItemEntity.getSiteNo());
		ConfigrecDTO configrecDTO = RnetDomainConnector.getConfigrecByName(domainUser, "GLOBAL_ENTITY_VENDOR");
		VendorDTO vendorDto = rnetDomainConnector.getVendor(domainUser, vendorItemEntity.getVendorId());
//		VendorEntity vendorEntity = VendorHelper.getVendor(em, vendorItemEntity.getVendorId());
		if (vendorItemEntity.getSiteNo() != rnetDomainConnector.getHQSite(domainUser) 
			&& !vendorDto.getOwnerId().equals(siteDto.getOwnerId()) 
			&& !"Y".equals(configrecDTO.getConfigFl())) {
			// if the vendor is for owner 1, then we're OK, otherwise through exception.  See RNET-3275 at Jeff William's comment.
			if (!vendorDto.getOwnerId().equals(1)){
				throw new RNetObjectDataConstraintException("Vendor " + vendorItemEntity.getVendorId()
						+ " is setup for owner: " + vendorDto.getOwnerId() + ", and site "
						+ vendorItemEntity.getSiteNo() + " is setup for owner: " + siteDto.getOwnerId()
						+ ".  Unable to create vendorItem record for this site.");
			}
		}
	}
	
	public void validateVendorItemCurrencyPackCost(UserIdentity domainUser, VendorItemEntity vendorItemEntity) {
		if (vendorItemEntity == null) {
			throw new RNetNullArgumentException("vendorItemEntity is required.");
		}
		if (!rnetDomainConnector.isRunningHQ(domainUser)) {
			throw new RNetUnableToPerformException("Vendor Item cannot be created except on the HQ Database.");
		}
		validateVendorItem(domainUser, vendorItemEntity);
		if (vendorItemEntity.getVendorCurrencyPackCost() == null) {
			if (!StringUtils.isBlank(rnetDomainConnector.getVendor(domainUser, vendorItemEntity.getVendorId()).getCurrencyCd())) {
				throw new RNetObjectDataConstraintException("Vendor: " + vendorItemEntity.getVendorId()
						+ " has a currencyCd of: " + rnetDomainConnector.getVendor(domainUser, vendorItemEntity.getVendorId()).getCurrencyCd()
						+ ", so the vendorCurrencyPackCost cannot be null.");
			} else {
				vendorItemEntity.setVendorCurrencyPackCost(vendorItemEntity.getPackCost());
			}
		}
		validateVendorItem(domainUser, vendorItemEntity);
	}
}
