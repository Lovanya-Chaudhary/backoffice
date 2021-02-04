package com.rediron.platform.domain.rnet;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.Errors.ErrorInfo;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.entities.InvbysitEntity;
import com.rediron.platform.domain.entities.InvtoryEntity;
import com.rediron.platform.domain.entities.ItemupcEntity;
import com.rediron.platform.domain.entities.SitgrpAllEntity;
import com.rediron.platform.domain.entities.VenSiteEntity;
import com.rediron.platform.domain.entities.VendorItemEntity;
import com.rediron.platform.domain.services.VendorService;
import com.tomax.api.UserIdentity;
import com.tomax.inventory.dto.InvbysitDTO;
import com.tomax.inventory.dto.InvtoryDTO;
import com.tomax.inventory.dto.ItemupcDTO;
import com.tomax.site.dto.SiteDTO;
import com.tomax.vendor.dto.VendorItemDTO;

@Component
public class InventoryVendorBO {

	@Autowired
	private RnetDomainConnector rnetDomainConnector;

	@Autowired
	private VendorValidator vendorValidator;

	@Autowired
	private VendorService bVendorService;

	@Autowired
	private Errors errors;

	public int getSkuAndPopulateEntities(UserIdentity domainUser, InvtoryEntity invtoryEntity,
			List<InvbysitEntity> invbysitEntities, List<ItemupcEntity> itemupcEntities,
			List<VendorItemEntity> vendorItemEntities) {

		int newSku = rnetDomainConnector.findNewSkuNumber(domainUser);

		invtoryEntity.setSkuNo(newSku);
		InventoryValidator.validateInvtory(invtoryEntity);
		invtoryEntity = rnetDomainConnector.createInventory(invtoryEntity);

		if (!CollectionUtils.isEmpty(itemupcEntities)) {
			for (ItemupcEntity itemupcEntity : itemupcEntities) {
				itemupcEntity.setSkuNo(newSku);
				InventoryValidator.validateItemUPCInsert(itemupcEntity);
				ItemupcEntity savedItemupcEntity = rnetDomainConnector.createItemUpc(itemupcEntity);
				if (savedItemupcEntity == null) {
					ErrorInfo error = errors.getErrors().get("ITEM_UPC");
					error.setMessage("Item upc insertion failed. Please try again later.");
					throw new ServiceException(error);
				}
			}
		} else {
			ItemupcEntity entity = new ItemupcEntity();
			entity.setTypeCd("U");
			entity.setPriceAdjAmt(new BigDecimal(0));
			entity.setUpcModifier(1);
			entity.setUpcSeq(1);
			entity.setSkuNo(newSku);
			String skuNoStr = String.valueOf(newSku);
			entity.setUpcId("4" + StringUtils.leftPad(skuNoStr,
					(RnetDomainConnector.getConfigrecByName(domainUser, "UPC_STORED_LEN").getConfigNo().intValue()) - 1,
					'0'));
			InventoryValidator.validateItemUPCInsert(entity);
			ItemupcEntity savedItemupcEntity = rnetDomainConnector.createItemUpc(entity);
			if (savedItemupcEntity == null) {
				ErrorInfo error = errors.getErrors().get("ITEM_UPC");
				error.setMessage("Item upc insertion failed. Please try again later.");
				throw new ServiceException(error);
			}
		}

		for (InvbysitEntity invbysitEntity : invbysitEntities) {
			invbysitEntity.setSkuNo(newSku);
			InventoryValidator.validateInvbysit(invbysitEntity);
			InvbysitEntity savedInvbysitEntity = rnetDomainConnector.createInvbysit(invbysitEntity);
			if (savedInvbysitEntity == null) {
				ErrorInfo error = errors.getErrors().get("INVBYSIT");
				error.setMessage("Invbysit insertion failed. Please try again later.");
				throw new ServiceException(error);
			}
		}

		if (vendorItemEntities != null) {
			if (!rnetDomainConnector.isRunningHQ(domainUser)) {
				ErrorInfo error = errors.getErrors().get("INVALID_CREDENTIALS");
				error.setMessage("Vendor Item cannot be created except on the HQ Database.");
				throw new ServiceException(error);
			}
			for (VendorItemEntity vendorItemEntity : vendorItemEntities) {
				vendorItemEntity.setSkuNo(newSku);
				vendorValidator.validateVendorItem(domainUser, vendorItemEntity);
				if (vendorItemEntity.getVendorCurrencyPackCost() == null) {
					if (!StringUtils.isBlank(rnetDomainConnector.getVendor(domainUser, vendorItemEntity.getVendorId())
							.getCurrencyCd())) {
						ErrorInfo error = errors.getErrors().get("VENDOR_ITEM");
						error.setMessage("Vendor: "
								+ vendorItemEntity.getVendorId() + " has a currencyCd of: " + rnetDomainConnector
										.getVendor(domainUser, vendorItemEntity.getVendorId()).getCurrencyCd()
								+ ", so the vendorCurrencyPackCost cannot be null.");
						throw new ServiceException(error);
					} else {
						vendorItemEntity.setVendorCurrencyPackCost(vendorItemEntity.getPackCost());
					}
				}
				vendorItemEntity.setSeqNo(bVendorService.getNextVendorItemSeqNo(domainUser).toBigInteger());
				VendorItemEntity savedVendorItemEntity = rnetDomainConnector.createVendorItem(vendorItemEntity);
				if (savedVendorItemEntity == null) {
					ErrorInfo error = errors.getErrors().get("VENDOR_ITEM");
					error.setMessage("Vendor Item insertion failed. Please try again later.");
					throw new ServiceException(error);
				}
			}
		}
		return newSku;
		// Remove this method call when sparse invbysit and vendor_item gets
		// implemented.
		// createFullItemData(domainUser, invbysitEntities, vendorItemEntities);
	}

	public void createFullItemData(UserIdentity domainUser, List<InvbysitEntity> invbysitEntities,
			List<VendorItemEntity> vendorItemEntities) {
		Integer hqSiteNo = rnetDomainConnector.getHQSite(domainUser);

		Set<SitgrpAllEntity> sites = null;
		List<SitgrpAllEntity> listOfSites = rnetDomainConnector.getSiteGroupAll(domainUser, "ALL");
		sites = new HashSet<SitgrpAllEntity>(listOfSites);
		InvbysitEntity invbysitEntity = invbysitEntities.get(0);

		for (SitgrpAllEntity site : sites) {
			boolean flag = false;

			// Check to see if Invbysit record exists in the passed in collection.

			for (InvbysitEntity ibs : invbysitEntities) {
				if (ibs.getSiteNo().equals(site.getSiteNo())) {
					flag = true;
					break;
				}
			}

			if (!flag) { // Create Invbysit record only if it is not part of the collection passed in.

				InvbysitEntity newEntity = new InvbysitEntity(invbysitEntity);

				newEntity.setSiteNo(site.getSiteNo());

				if (site.getSiteNo().equals(hqSiteNo)) {
					newEntity.setDownloadFl("Y");
				} else {
					newEntity.setDownloadFl("N");
				}
				newEntity.setPrice1(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
				newEntity.setUnitPrice1(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
				InventoryValidator.validateInvbysitInsert(newEntity);
				InvbysitEntity savedNewEntity = rnetDomainConnector.createInvbysit(newEntity);
				if (savedNewEntity == null) {
					ErrorInfo error = errors.getErrors().get("INVBYSIT");
					error.setMessage("Invbysit insertion failed. Please try again later.");
					throw new ServiceException(error);
				}
			}
		}

		if (vendorItemEntities != null && vendorItemEntities.size() > 0) {
			VendorItemEntity vendorItemEntity = vendorItemEntities.get(0);

			Set<VenSiteEntity> vendorSites = null;
			List<VenSiteEntity> listOfVensites = rnetDomainConnector.getVendorSitesForVendor(domainUser,
					vendorItemEntity.getVendorId());
			vendorSites = new HashSet<>(listOfVensites);
			for (VenSiteEntity vendorSite : vendorSites) {

				boolean flag = false;

				// Check to see if Vendor_Item record exists in the passed in collection.

				for (VendorItemEntity vi : vendorItemEntities) {
					if (vi.getSiteNo().equals(vendorSite.getSiteNo())) {
						flag = true;
						break;
					}
				}

				if (!flag) { // Create Vendor_Item record only if it is not part of the collection passed in.
					VendorItemEntity newEntity = new VendorItemEntity(vendorItemEntity);
					newEntity.setSeqNo(null);
					newEntity.setSiteNo(vendorSite.getSiteNo());
					newEntity.setPackCost(BigDecimal.ZERO.setScale(4, BigDecimal.ROUND_HALF_UP));
					newEntity.setUnitCost(BigDecimal.ZERO.setScale(4, BigDecimal.ROUND_HALF_UP));
					newEntity.setVendorCurrencyPackCost(BigDecimal.ZERO.setScale(4, BigDecimal.ROUND_HALF_UP));
					newEntity.setCoreCost(BigDecimal.ZERO.setScale(4, BigDecimal.ROUND_HALF_UP));

					vendorValidator.validateVendorItemCurrencyPackCost(domainUser, newEntity);
					VendorItemEntity savedNewEntity = rnetDomainConnector.createVendorItem(newEntity);
					if (savedNewEntity == null) {
						ErrorInfo error = errors.getErrors().get("VENDOR_ITEM");
						error.setMessage("Vendor Item insertion failed. Please try again later.");
						throw new ServiceException(error);
					}
				}
			}
		}
	}

	public void validateInvbysitEntities(UserIdentity domainUser, int mySite, List<InvbysitEntity> invbysitEntities) {
		// validate mySite
		SiteDTO siteDto = RnetDomainConnector.getSite(domainUser, mySite);
		if (siteDto == null) {
			ErrorInfo error = errors.getErrors().get("SITE_GROUP_ID");
			error.setMessage("mySite: " + mySite + " is not a valid site number");
			throw new ServiceException(error);
		}
		Integer ownerId = siteDto.getOwnerId();
		Integer hqSite = rnetDomainConnector.getHQSite(domainUser);
		// If running in postgres, you just need to validate that invbysit
		// doesn't have any other sites in it.
		if (!rnetDomainConnector.isRunningHQ(domainUser)) {
			if (invbysitEntities.size() > 1) {
				ErrorInfo error = errors.getErrors().get("INVBYSIT");
				error.setMessage(
						"Stores can only create invbysit records for themselves and more than one invbysit Entity was passed in the list.");
				throw new ServiceException(error);
			}
		} else {
			if (mySite == hqSite) { // HQ can create for ANYONE
				// no need to validate anything
			} else {
				if (ownerId == null) { // only can create for myself and HQ
					if (invbysitEntities.size() > 2) {
						ErrorInfo error = errors.getErrors().get("INVBYSIT");
						error.setMessage("Site " + mySite
								+ "'s ownerId is not defined and as a result can only create items for itself and HQ.  There were "
								+ invbysitEntities.size() + " invbysit records in the list.");
						throw new ServiceException(error);
					}
					for (InvbysitEntity invbysitEntity : invbysitEntities) {
						if (invbysitEntity.getSiteNo() != mySite && invbysitEntity.getSiteNo() != hqSite) {
							ErrorInfo error = errors.getErrors().get("INVBYSIT");
							error.setMessage("Site " + mySite + " cannot create invbysit records for site "
									+ invbysitEntity.getSiteNo());
							throw new ServiceException(error);
						}
					}
				} else { // ownerId is not null, so validate that we're not
							// creating invbysit records outside of that
							// entity ownership
					for (InvbysitEntity invbysitEntity : invbysitEntities) {
						if (invbysitEntity.getSiteNo() != hqSite || invbysitEntity.getSiteNo() != mySite) {
							SiteDTO insertingSite = RnetDomainConnector.getSite(domainUser, invbysitEntity.getSiteNo());
							if (insertingSite.getOwnerId() != ownerId) {
								ErrorInfo error = errors.getErrors().get("INVBYSIT");
								error.setMessage("Site " + mySite + " cannot create invbysit records for site: "
										+ invbysitEntity.getSiteNo());
								throw new ServiceException(error);
							}
						}
					}
				}
			}

			// validate that we ALWAYS have an HQ site's invbysit record
			boolean hqInvbysitRecordFound = false;
			InvbysitEntity mySiteInvbysit = null;
			for (InvbysitEntity invbysitEntity : invbysitEntities) {
				if (invbysitEntity.getSiteNo().equals(hqSite)) {
					hqInvbysitRecordFound = true;
				}
				if (invbysitEntity.getSiteNo().equals(mySite)) {
					mySiteInvbysit = invbysitEntity;
				}
			}
			if (!hqInvbysitRecordFound) { // copy mySiteInvbysit for HQ
				InvbysitEntity hqInvbysitEntity = new InvbysitEntity(mySiteInvbysit);
				hqInvbysitEntity.setSiteNo(hqSite);

				// Price1 and UnitPrice1 should be ZERO when creating a new item,
				hqInvbysitEntity.setPrice1(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
				hqInvbysitEntity.setUnitPrice1(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
				hqInvbysitEntity.setDownloadFl("Y");
				invbysitEntities.add(hqInvbysitEntity);
			}
		}
	}

	public void validateVendorItemEntities(UserIdentity domainUser, Integer mySite,
			List<VendorItemEntity> vendorItemEntities) {
		if (vendorItemEntities == null) {
			return;
		}
		SiteDTO siteDto = RnetDomainConnector.getSite(domainUser, mySite);
		if (siteDto == null) {
			ErrorInfo error = errors.getErrors().get("VENDOR_ITEM");
			error.setMessage("mySite: " + mySite + " is not a valid site number");
			throw new ServiceException(error);
		}
		Integer ownerId = siteDto.getOwnerId();
		Integer hqSite = rnetDomainConnector.getHQSite(domainUser);
		// If running in postgres, you just need to validate that invbysit
		// doesn't have any other sites in it.
		if (!rnetDomainConnector.isRunningHQ(domainUser)) {
			for (VendorItemEntity vendorItemEntity : vendorItemEntities) {
				if (vendorItemEntity.getSiteNo() == null) {
					ErrorInfo error = errors.getErrors().get("VENDOR_ITEM");
					error.setMessage("Site is required in VendorItem");
					throw new ServiceException(error);
				}
				if (vendorItemEntity.getSiteNo() != mySite) {
					ErrorInfo error = errors.getErrors().get("VENDOR_ITEM");
					error.setMessage(
							"Your site can only create vendorItem records for itself.  You tried creating a vendorItem for site: "
									+ vendorItemEntity.getSiteNo());
					throw new ServiceException(error);
				}
			}
		} else {
			if (mySite == hqSite) { // HQ can create for ANYONE
				// no need to validate anything
			} else {
				if (ownerId == null) { // only can create for myself and HQ
					for (VendorItemEntity vendorItemEntity : vendorItemEntities) {
						if (!vendorItemEntity.getSiteNo().equals(mySite)
								&& !vendorItemEntity.getSiteNo().equals(hqSite)) {
							ErrorInfo error = errors.getErrors().get("VENDOR_ITEM");
							error.setMessage("Site " + mySite + " cannot create vendorItem records for site "
									+ vendorItemEntity.getSiteNo());
							throw new ServiceException(error);
						}
					}
				} else { // ownerId is not null, so validate that we're not
							// creating invbysit records outside of that
							// entity ownership
					for (VendorItemEntity vendorItemEntity : vendorItemEntities) {
						if (!vendorItemEntity.getSiteNo().equals(hqSite)
								|| !vendorItemEntity.getSiteNo().equals(mySite)) {
							SiteDTO insertingSite = RnetDomainConnector.getSite(domainUser,
									vendorItemEntity.getSiteNo());
							if (!insertingSite.getOwnerId().equals(ownerId)) {
								ErrorInfo error = errors.getErrors().get("VENDOR_ITEM");
								error.setMessage("Site " + mySite + " cannot create vendorItem records for site: "
										+ vendorItemEntity.getSiteNo());
								throw new ServiceException(error);
							}
						}
					}
				}
			}
		}

	}

	public VendorItemEntity mapToVendorEntity(VendorItemDTO vendorItemDTO) {

		VendorItemEntity venEntity = new VendorItemEntity();
		venEntity.setVendorId(vendorItemDTO.getVendorId());
		venEntity.setVendorItem(vendorItemDTO.getVendorItem());
		venEntity.setPackCost(vendorItemDTO.getPackCost());
		venEntity.setPackQty(vendorItemDTO.getPackQty());
		venEntity.setPackUm(vendorItemDTO.getPackUm());
		venEntity.setMasterPackQty(vendorItemDTO.getMasterPackQty());
		venEntity.setLeadTime(vendorItemDTO.getLeadTime());
		venEntity.setEoq(vendorItemDTO.getEoq());
		if(StringUtils.isNotBlank(vendorItemDTO.getOrderAvailabilityStatus())) {
			venEntity.setOrderAvailabilityStatus(vendorItemDTO.getOrderAvailabilityStatus());
			venEntity.setOrderAvailabilityActiveDt(new Date());
		}
		venEntity.setPackWeight(vendorItemDTO.getPackWeight());
		venEntity.setPackCube(vendorItemDTO.getPackCube());
		venEntity.setVenType(vendorItemDTO.getVenType());
		venEntity.setMainVendorItemFl(vendorItemDTO.getMainVendorItemFl());
		venEntity.setVendorCurrencyPackCost(vendorItemDTO.getVendorCurrencyPackCost());
		venEntity.setOperationType("I");
		venEntity.setCostFactor(new BigDecimal(1));
		return venEntity;
	}

	public ItemupcEntity mapToItemUpcEntity(ItemupcDTO itemUpcDTO) {

		ItemupcEntity itemUpc = new ItemupcEntity();
		itemUpc.setUpcId(itemUpcDTO.getUpcId());
		itemUpc.setTypeCd(itemUpcDTO.getTypeCd());
		itemUpc.setUpcModifier(itemUpcDTO.getUpcModifier());
		itemUpc.setUpcSeq(itemUpcDTO.getUpcSeq());
		itemUpc.setPriceAdjAmt(itemUpcDTO.getPriceAdjAmt());
		itemUpc.setUcc14Qty(itemUpcDTO.getUcc14Qty());
		itemUpc.setUcc14Uom(itemUpcDTO.getUcc14Uom());

		return itemUpc;
	}

	public InvbysitEntity mapToInvBySitEntity(InvbysitDTO invBySitDTO) {
		InvbysitEntity invbysitEntity = new InvbysitEntity();
		// prices 1 - 10
		invbysitEntity.setPrice1(invBySitDTO.getPrice1());
		invbysitEntity.setPrice2(invBySitDTO.getPrice2());
		invbysitEntity.setPrice3(invBySitDTO.getPrice3());
		invbysitEntity.setPrice4(invBySitDTO.getPrice4());
		invbysitEntity.setPrice5(invBySitDTO.getPrice5());

		invbysitEntity.setPrice6(invBySitDTO.getPrice6());
		invbysitEntity.setPrice7(invBySitDTO.getPrice7());
		invbysitEntity.setPrice8(invBySitDTO.getPrice8());
		invbysitEntity.setPrice9(invBySitDTO.getPrice9());
		invbysitEntity.setPrice10(invBySitDTO.getPrice10());

		invbysitEntity.setPriceQty1(invBySitDTO.getPriceQty1());
		invbysitEntity.setPriceQty2(invBySitDTO.getPriceQty2());
		invbysitEntity.setPriceQty3(invBySitDTO.getPriceQty3());
		invbysitEntity.setPriceQty4(invBySitDTO.getPriceQty4());
		invbysitEntity.setPriceQty5(invBySitDTO.getPriceQty5());

		invbysitEntity.setPriceQty6(invBySitDTO.getPriceQty6());
		invbysitEntity.setPriceQty7(invBySitDTO.getPriceQty7());
		invbysitEntity.setPriceQty8(invBySitDTO.getPriceQty8());
		invbysitEntity.setPriceQty9(invBySitDTO.getPriceQty9());
		invbysitEntity.setPriceQty10(invBySitDTO.getPriceQty10());

		invbysitEntity.setLaloGm(invBySitDTO.getLaloGm());
		invbysitEntity.setHaloGm(invBySitDTO.getHaloGm());
		invbysitEntity.setPurchaseVolumeId(invBySitDTO.getPurchaseVolumeId());
		invbysitEntity.setAllowPchgFl(invBySitDTO.getAllowPchgFl());
		invbysitEntity.setDownloadFl(invBySitDTO.getDownloadFl());
		invbysitEntity.setDiscontinuedStFl(invBySitDTO.getDiscontinuedStFl());
		invbysitEntity.setNonDiscountFl(invBySitDTO.getNonDiscountFl());

		invbysitEntity.setPkgPriceQty1(invBySitDTO.getPkgPriceQty1());
		invbysitEntity.setPkgPriceQty2(invBySitDTO.getPkgPriceQty2());
		invbysitEntity.setPkgPriceQty3(invBySitDTO.getPkgPriceQty3());
		invbysitEntity.setPkgPrice1(invBySitDTO.getPkgPrice1());
		invbysitEntity.setPkgPrice2(invBySitDTO.getPkgPrice2());
		invbysitEntity.setPkgPrice3(invBySitDTO.getPkgPrice3());

		invbysitEntity.setMsrpQty(invBySitDTO.getMsrpQty());
		invbysitEntity.setMsrpPrice(invBySitDTO.getMsrpPrice());
		invbysitEntity.setEpoPct(invBySitDTO.getEpoPct());

		invbysitEntity.setSkuStatusId(invBySitDTO.getSkuStatusId());
		invbysitEntity.setRestrictSaleId(invBySitDTO.getRestrictSaleId());
		invbysitEntity.setPosSkuStatusId(invBySitDTO.getPosSkuStatusId());
		invbysitEntity.setItemizerStatusId(invBySitDTO.getItemizerStatusId());

		invbysitEntity.setReorderPt(invBySitDTO.getReorderPt());
		invbysitEntity.setReorderTo(invBySitDTO.getReorderTo());
		invbysitEntity.setReorderLockedFl(invBySitDTO.getReorderLockedFl());
		invbysitEntity.setReplenishmentFl(invBySitDTO.getReplenishmentFl());
		invbysitEntity.setVelocityLockedFl(invBySitDTO.getVelocityLockedFl());
		invbysitEntity.setVelocityCd(invBySitDTO.getVelocityCd());
		invbysitEntity.setClassCode(invBySitDTO.getClassCode());
		invbysitEntity.setMaxOrderQty(invBySitDTO.getMaxOrderQty());
		invbysitEntity.setWarehouseInnerPack(invBySitDTO.getWarehouseInnerPack());
		invbysitEntity.setProductStartDt(invBySitDTO.getProductStartDt());
		invbysitEntity.setProductEndDt(invBySitDTO.getProductEndDt());
		invbysitEntity.setLastReplenishmentDt(invBySitDTO.getLastReplenishmentDt());
		invbysitEntity.setDefaultDcSkuLevel(invBySitDTO.getDefaultDcSkuLevel());

		invbysitEntity.setCommissionCd(invBySitDTO.getCommissionCd());
		invbysitEntity.setTareTableNo(invBySitDTO.getTareTableNo());
		invbysitEntity.setBottleLinkNo(invBySitDTO.getBottleLinkNo());
		invbysitEntity.setAdditionalTax(invBySitDTO.getAdditionalTax());
		return invbysitEntity;
	}

	public InvtoryEntity mapToInvtoryEntity(InvtoryDTO invtoryDTO) {

		InvtoryEntity invEntity = new InvtoryEntity();
		invEntity.setPackupActionId(invtoryDTO.getPackupActionId());
//		invEntity.setOwnerId(ownerId);

		invEntity.setDescription(invtoryDTO.getDescription());
		invEntity.setReceiptDesc(invtoryDTO.getReceiptDesc());
		invEntity.setLookupDesc(invtoryDTO.getLookupDesc());

		invEntity.setDeptNo(invtoryDTO.getDeptNo());
		invEntity.setClassNo(invtoryDTO.getClassNo());
		invEntity.setLineNo(invtoryDTO.getLineNo());

		invEntity.setReportFactor(invtoryDTO.getReportFactor());
		invEntity.setReportUm(invtoryDTO.getReportUm());
		invEntity.setSellUm(invtoryDTO.getSellUm());

		invEntity.setCatchWeightFl(invtoryDTO.getCatchWeightFl());
		invEntity.setWeighedItemFl(invtoryDTO.getWeighedItemFl());
		invEntity.setUnitCube(invtoryDTO.getUnitCube());
		invEntity.setUnitWeight(invtoryDTO.getUnitWeight());
		invEntity.setTareTableNo(invtoryDTO.getTareTableNo());

		invEntity.setMfgCd(invtoryDTO.getMfgCd());

		invEntity.setRegionalSupersedeFl(invtoryDTO.getRegionalSupersedeFl());
		invEntity.setReplacementSkuNo(invtoryDTO.getReplacementSkuNo());
		invEntity.setLcuQty(invtoryDTO.getLcuQty());
		invEntity.setInvType(invtoryDTO.getInvType());
		invEntity.setEmployeeId(invtoryDTO.getEmployeeId());
		invEntity.setReportCd(invtoryDTO.getReportCd());
		invEntity.setLinkSkuNo(invtoryDTO.getLinkSkuNo());

		invEntity.setWarehouseInnerPack(invtoryDTO.getWarehouseInnerPack());
		invEntity.setPriceListSeq(invtoryDTO.getPriceListSeq());
		invEntity.setWeeksHistory(invtoryDTO.getWeeksHistory());
		invEntity.setPeriodsHistory(invtoryDTO.getPeriodsHistory());
		invEntity.setGlCatId(invtoryDTO.getGlCatId());
		invEntity.setXrefNo(invtoryDTO.getXrefNo());

		invEntity.setEquivUm(invtoryDTO.getEquivUm());
		invEntity.setEquivFactor(invtoryDTO.getEquivFactor());
		invEntity.setPrivateBrandFl(invtoryDTO.getPrivateBrandFl());
		invEntity.setLabelDesc1(invtoryDTO.getLabelDesc1());
		invEntity.setLabelDesc2(invtoryDTO.getLabelDesc2());

		invEntity.setTenderProgramId(invtoryDTO.getTenderProgramId());

		invEntity.setUseSerialFl(invtoryDTO.getUseSerialFl());
		invEntity.setAutoGenSerialsFl(invtoryDTO.getAutoGenSerialsFl());

		// non rev item
		invEntity.setSpecialItemFl(invtoryDTO.getSpecialItemFl());
		invEntity.setStoreCpnFl(invtoryDTO.getStoreCpnFl());
		invEntity.setNonInventoryFl(invtoryDTO.getNonInventoryFl());
		invEntity.setSubInvFl(invtoryDTO.getSubInvFl());
		invEntity.setSupplyItemFl(invtoryDTO.getSupplyItemFl());
		invEntity.setCoreFl(invtoryDTO.getCoreFl());
		invEntity.setFlexibleItemFl(invtoryDTO.getFlexibleItemFl());

		invEntity.setReorderFl(invtoryDTO.getReorderFl());
		invEntity.setDiscontinuedFl(invtoryDTO.getDiscontinuedFl());
		invEntity.setObsoleteFl(invtoryDTO.getObsoleteFl());

		invEntity.setCaptureSerialNoFl(invtoryDTO.getCaptureSerialNoFl());
		invEntity.setCaptureOrderNoFl(invtoryDTO.getCaptureOrderNoFl());
		invEntity.setDownloadFl(invtoryDTO.getDownloadFl());
		invEntity.setNegativePriceFl(invtoryDTO.getNegativePriceFl());

		invEntity.setCreateDt(invtoryDTO.getCreateDt());
		invEntity.setChangeDt(invtoryDTO.getChangeDt());
		invEntity.setDateModified(invtoryDTO.getDateModified());

		return invEntity;
	}
}
