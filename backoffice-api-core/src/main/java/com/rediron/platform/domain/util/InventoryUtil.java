package com.rediron.platform.domain.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.core.Errors.ErrorInfo;
import com.rediron.platform.domain.constants.IErrorConstants;
import com.rediron.platform.domain.constants.ISecurityConstants;
import com.rediron.platform.domain.constants.ITmxGblConstants;
import com.rediron.platform.domain.constants.ItemStatusTypeCode;
import com.rediron.platform.domain.constants.UserDefinedTypeCode;
import com.rediron.platform.domain.model.request.ChangeSiteInfoRequest;
import com.rediron.platform.domain.model.request.InvBySiteModel;
import com.rediron.platform.domain.model.request.InventoryModel;
import com.rediron.platform.domain.model.request.ItemCreationDefaultsInfoRequest;
import com.rediron.platform.domain.model.request.ReplenishmentInfoModel;
import com.rediron.platform.domain.model.request.StatusInfoModel;
import com.rediron.platform.domain.model.request.SummaryInfoRequest;
import com.rediron.platform.domain.model.request.SummaryReorderPoints;
import com.rediron.platform.domain.model.request.UpdateInvbysitRequest;
import com.rediron.platform.domain.model.request.VendorItemModel;
import com.rediron.platform.domain.model.response.BuyerDetailsResponse;
import com.rediron.platform.domain.model.response.CodeDetailsResponse;
import com.rediron.platform.domain.model.response.CommissionCodeDetailsResponse;
import com.rediron.platform.domain.model.response.CouponPDVDetails;
import com.rediron.platform.domain.model.response.DefaultDCDetailsResponse;
import com.rediron.platform.domain.model.response.DomainRefCode;
import com.rediron.platform.domain.model.response.GLCategoryInfoResponse;
import com.rediron.platform.domain.model.response.InventoryTypeResponse;
import com.rediron.platform.domain.model.response.LinkChargesDetailsResponse;
import com.rediron.platform.domain.model.response.PromoInfo;
import com.rediron.platform.domain.model.response.ReportCodeDetailsResponse;
import com.rediron.platform.domain.model.response.SiteGroupDetails;
import com.rediron.platform.domain.model.response.StatusDetailsResponse;
import com.rediron.platform.domain.model.response.TareDetailsResponse;
import com.rediron.platform.domain.model.response.TenderCertificateInfoResponse;
import com.rediron.platform.domain.response.PriceQuantityPackage;
import com.rediron.platform.domain.response.Pricing;
import com.rediron.platform.domain.response.PricingQuantity;
import com.rediron.platform.domain.response.PromoDetails;
import com.rediron.platform.domain.response.PromoStatus;
import com.rediron.platform.domain.response.RegularPricing;
import com.rediron.platform.domain.response.SKULinkDetails;
import com.rediron.platform.domain.services.DomainService;
import com.rediron.platform.domain.services.ItemService;
import com.rediron.platform.domain.services.LOVService;
import com.rediron.platform.domain.services.LookUpService;
import com.rediron.platform.domain.services.SecurityService;
import com.rediron.platform.domain.services.SkuPkgService;
import com.rediron.platform.domain.services.TmxGblService;
//import com.rediron.platform.domain.services.VendorItemService;
import com.rediron.platform.domain.services.VendorService;
import com.tomax.api.RNetApiContexts;
import com.tomax.api.UserIdentity;
import com.tomax.config.domainRef.dto.DomainRefCodeDTO;
import com.tomax.inventory.api.InventoryAPI;
import com.tomax.inventory.dto.ClassDTO;
import com.tomax.inventory.dto.ClassDTOId;
import com.tomax.inventory.dto.DeptDTO;
import com.tomax.inventory.dto.DeptDTOId;
import com.tomax.inventory.dto.InvbysitDTO;
import com.tomax.inventory.dto.InvtoryDTO;
import com.tomax.inventory.dto.ItemupcDTO;
import com.tomax.inventory.dto.LineDTO;
import com.tomax.inventory.dto.LineDTOId;
import com.tomax.inventory.dto.custom.CreateItemInfoDTO;
import com.tomax.inventory.dto.custom.InventoryGlobalInfoDTO;
import com.tomax.inventory.dto.custom.ItemReorderPointsDTO;
import com.tomax.vendor.dto.VendorItemDTO;

/**
 * @author lovanya.chaudhary
 *
 */

@Component
public class InventoryUtil {

	Logger logger = LoggerFactory.getLogger(InventoryUtil.class);

	private static InventoryAPI inventoryAPI = RNetApiContexts.hq.getStateless(InventoryAPI.class);

	@Autowired
	private Errors errors;

	@Autowired
	private TmxGblService tmxGblService;

	@Autowired
	private LOVService lovService;

	@Autowired
	private LookUpService lookUpService;

	@Autowired
	private ItemService bItemService;

	@Autowired
	private VendorService bVendorService;

//	@Autowired
//	private VendorItemService vendorItemService;

	@Autowired
	private SkuPkgService skuPkgService;

	@Autowired
	private SecurityService bSecurityService;

	@Autowired
	private DomainService domainService;

	// req res changes starts here

	public CreateItemInfoDTO validateAndMapToDTO(UserIdentity domainUser, CreateItemInfoDTO createItemInfoDTO,
			ItemCreationDefaultsInfoRequest itemCreateDefaultInfoModel) throws ServiceException {
		logger.debug("Mapping... " + itemCreateDefaultInfoModel.getInventoryModel().getItemDescription());

		InvtoryDTO invtoryDTO = createItemInfoDTO.getInvtoryDTO();
		ItemupcDTO itemupcDTO = createItemInfoDTO.getItemUpcDTO();
		InvbysitDTO invBySitDTO = createItemInfoDTO.getInvBySitDTO();
		VendorItemDTO vendorItemDTO = createItemInfoDTO.getVendorItemDTO();

		createItemInfoDTO.setVendorName(itemCreateDefaultInfoModel.getVendorName());
		createItemInfoDTO.setDeptGroupNo(itemCreateDefaultInfoModel.getDeptGrpNo());

		// inventory dto ends starts
		InventoryModel invModel = itemCreateDefaultInfoModel.getInventoryModel();

		VendorItemModel venItemModel = itemCreateDefaultInfoModel.getVendorItemModel();

		int minSkuValue = tmxGblService.getNum("MIN_SKU").intValue();
		int maxSkuValue = tmxGblService.getNum("MAX_SKU").intValue();
		int skuNo = itemCreateDefaultInfoModel.getSkuNo();

		if (bItemService.getSkuNumber(skuNo, "S") == null) {
			if (skuNo > minSkuValue && skuNo < maxSkuValue) {
				invtoryDTO.setSkuNo(skuNo);
			}
		} else {// else throw dup sku numb exception or alert user
			throw new ServiceException(errors.getErrors().get(IErrorConstants.DUPLICATE_SKU_NO));
		}

		invtoryDTO = validateInventoryModelNew(domainUser, invtoryDTO, invModel);

		if (venItemModel != null && venItemModel.getPackQty().doubleValue() > 1)
			invtoryDTO.setUseSerialFl("N");

		createItemInfoDTO.setInvtoryDTO(invtoryDTO);

		// inventory dto ends here

		// invbysit starts here

		List<SiteGroupDetails> listOfSiteGroups = lovService.getSiteGroupDetails();

		if (StringUtils.isNotBlank(itemCreateDefaultInfoModel.getSiteGroup())) {
			String siteGroupId = itemCreateDefaultInfoModel.getSiteGroup();

			if (listOfSiteGroups.stream().filter(obj -> StringUtils.isNotBlank(obj.getGroupId()))
					.anyMatch(o -> o.getGroupId().equalsIgnoreCase(siteGroupId))) {
				createItemInfoDTO.setSiteGroup(siteGroupId);
			}
			// else throw group id related exception
		} else {
			throw new ServiceException(errors.getErrors().get(IErrorConstants.SITE_GROUP_ID));
		}

		com.rediron.platform.domain.model.request.InvBySiteModel invbysitModel = itemCreateDefaultInfoModel
				.getInvBySiteModel();

		invBySitDTO = validateInvbysitModelNew(domainUser, invBySitDTO, invbysitModel);
		// need to set the site no by default it creates for all the sites default site
		// group ALL
		createItemInfoDTO.setInvBySitDTO(invBySitDTO);

		// invbysit ends here

		// item upc starts here
		// Map Item UPC
		com.rediron.platform.domain.model.request.ItemUpcModel itemUpcModel = itemCreateDefaultInfoModel
				.getItemUpcModel();

		if (itemupcDTO == null) {
			itemupcDTO = new ItemupcDTO();
		}

		if (itemUpcModel != null) {
			// make sure type code must be U
			if (itemUpcModel.getTypeCd() != null && itemUpcModel.getTypeCd().length() > 0) {
				itemupcDTO.setTypeCd(itemUpcModel.getTypeCd());
			}
			if (StringUtils.isNotBlank(itemUpcModel.getUpcId()) && StringUtils.isNumeric(itemUpcModel.getUpcId())) {
				int length = itemUpcModel.getUpcId().length();
				if ((length > 5 && length < 9) || (length > 10 && length < 15))
					itemupcDTO.setUpcId(itemUpcModel.getUpcId());
				else
					throw new ServiceException(errors.getErrors().get(IErrorConstants.UPC_LENGTH));

				// else throw upc length exception
			}

			if (itemUpcModel.getAdjAmtUPC() != null && itemUpcModel.getAdjAmtUPC().doubleValue() >= 0)
				itemupcDTO.setPriceAdjAmt(itemUpcModel.getAdjAmtUPC());

			// while creating item added upc is always primary hence seq no must always be 1
			itemupcDTO.setUpcSeq(new Integer(1));

			if (itemUpcModel.getModifierUPC() != null && itemUpcModel.getModifierUPC() >= 1
					&& itemUpcModel.getModifierUPC() < 100)
				itemupcDTO.setUpcModifier(itemUpcModel.getModifierUPC());
			else
				itemupcDTO.setUpcModifier(1);
		} else {
			itemupcDTO.setUpcModifier(1);
			itemupcDTO.setTypeCd("U");
			itemupcDTO.setUpcSeq(new Integer(1));
			itemupcDTO.setPriceAdjAmt(new BigDecimal(".00"));
		}

		createItemInfoDTO.setItemUpcDTO(itemupcDTO);

		// item upc ends here

		// Map Vendor Item

		if (venItemModel != null) {
			if (venItemModel.getVendorItem() != null && venItemModel.getVendorItem().length() > 0) {
				vendorItemDTO.setVendorItem(venItemModel.getVendorItem());
			} else {
				vendorItemDTO.setVendorItem("0");
			}
			if (venItemModel.getVendorId() != null && venItemModel.getVendorId().length() > 0) {
				vendorItemDTO.setVendorId(venItemModel.getVendorId());
			}
			if (venItemModel.getPackUm() != null && venItemModel.getPackUm().length() > 0) {
				vendorItemDTO.setPackUm(venItemModel.getPackUm());
			} else {
				vendorItemDTO.setPackUm("CASE");
			}
			if (venItemModel.getPackCost() != null && venItemModel.getPackCost().doubleValue() > 0) {
				vendorItemDTO.setPackCost(venItemModel.getPackCost());
			} else {
				vendorItemDTO.setPackCost(new BigDecimal("0.0000"));
			}

			if (venItemModel.getEoq() != null)
				vendorItemDTO.setEoq(venItemModel.getEoq());
			else
				vendorItemDTO.setEoq(new BigDecimal(0));

			if (venItemModel.getLeadTime() != null)
				vendorItemDTO.setLeadTime(venItemModel.getLeadTime());
			else
				vendorItemDTO.setLeadTime(0);

			// serialized validation part 2

			if (venItemModel.getPackQty() != null && venItemModel.getPackQty().doubleValue() > 0) {
				vendorItemDTO.setPackQty(venItemModel.getPackQty());
				vendorItemDTO.setMasterPackQty(venItemModel.getPackQty());
			} else {
				vendorItemDTO.setPackQty(new BigDecimal(1));
				vendorItemDTO.setMasterPackQty(new BigDecimal(1));
			}

		}

		createItemInfoDTO.setVendorItemDTO(vendorItemDTO);
		return (createItemInfoDTO);
	}

	private InvbysitDTO validateInvbysitModelNew(UserIdentity domainUser, InvbysitDTO invBySitDTO,
			InvBySiteModel invbysitModel) {
		// TODO Auto-generated method stub
		BigDecimal haloValue = null;
		if (invbysitModel.getHaloGm() != null && invbysitModel.getHaloGm().doubleValue() > 0)
			haloValue = invbysitModel.getHaloGm();

		BigDecimal laloValue = null;
		if (invbysitModel.getLaloGm() != null && invbysitModel.getLaloGm().doubleValue() > 0)
			laloValue = invbysitModel.getLaloGm();

		if (laloValue.doubleValue() > haloValue.doubleValue())
			throw new ServiceException(errors.getErrors().get(IErrorConstants.LALO_GM));

		if (invbysitModel.getHaloGm() != null && invbysitModel.getHaloGm().doubleValue() > 0) {
			invBySitDTO.setHaloGm(invbysitModel.getHaloGm());
		}

		if (invbysitModel.getLaloGm() != null && invbysitModel.getLaloGm().doubleValue() > 0) {
			invBySitDTO.setLaloGm(invbysitModel.getLaloGm());
		}

		if (StringUtils.isNotBlank(invbysitModel.getCouponPDVId())) {
			String pdvId = invbysitModel.getCouponPDVId();
			List<CouponPDVDetails> codes = lovService.getCouponPDVDetails();
			if (codes.stream().filter(obj -> StringUtils.isNotBlank(obj.getCouponPDVId()))
					.anyMatch(o -> o.getCouponPDVId().equalsIgnoreCase(pdvId))) {
				invBySitDTO.setPurchaseVolumeId(pdvId);
			}
		}

		// pricing model starts here
		if (invbysitModel.getPricingModel() != null) {
			if (invbysitModel.getPricingModel().getPrice1() != null
					&& invbysitModel.getPricingModel().getPrice1().doubleValue() > 0) {
				invBySitDTO.setPrice1(invbysitModel.getPricingModel().getPrice1());
				invBySitDTO.setPriceQty1(1);
			} else
				invBySitDTO.setPriceQty1(0);
			if (invbysitModel.getPricingModel().getPrice2() != null
					&& invbysitModel.getPricingModel().getPrice2().doubleValue() > 0) {
				invBySitDTO.setPrice2(invbysitModel.getPricingModel().getPrice2());
				invBySitDTO.setPriceQty2(1);
			} else
				invBySitDTO.setPriceQty2(0);
			if (invbysitModel.getPricingModel().getPrice3() != null
					&& invbysitModel.getPricingModel().getPrice3().doubleValue() > 0) {
				invBySitDTO.setPrice3(invbysitModel.getPricingModel().getPrice3());
				invBySitDTO.setPriceQty3(1);
			} else
				invBySitDTO.setPriceQty3(0);
			if (invbysitModel.getPricingModel().getPrice4() != null
					&& invbysitModel.getPricingModel().getPrice4().doubleValue() > 0) {
				invBySitDTO.setPrice4(invbysitModel.getPricingModel().getPrice4());
				invBySitDTO.setPriceQty4(1);
			} else
				invBySitDTO.setPriceQty4(0);
			if (invbysitModel.getPricingModel().getPrice5() != null
					&& invbysitModel.getPricingModel().getPrice5().doubleValue() > 0) {
				invBySitDTO.setPrice5(invbysitModel.getPricingModel().getPrice5());
				invBySitDTO.setPriceQty5(1);
			} else
				invBySitDTO.setPriceQty5(0);
			if (invbysitModel.getPricingModel().getPrice6() != null
					&& invbysitModel.getPricingModel().getPrice6().doubleValue() > 0) {
				invBySitDTO.setPrice6(invbysitModel.getPricingModel().getPrice6());
				invBySitDTO.setPriceQty6(1);
			} else
				invBySitDTO.setPriceQty6(0);
			if (invbysitModel.getPricingModel().getPrice7() != null
					&& invbysitModel.getPricingModel().getPrice7().doubleValue() > 0) {
				invBySitDTO.setPrice7(invbysitModel.getPricingModel().getPrice7());
				invBySitDTO.setPriceQty7(1);
			} else
				invBySitDTO.setPriceQty7(0);
			if (invbysitModel.getPricingModel().getPrice8() != null
					&& invbysitModel.getPricingModel().getPrice8().doubleValue() > 0) {
				invBySitDTO.setPrice8(invbysitModel.getPricingModel().getPrice8());
				invBySitDTO.setPriceQty8(1);
			} else
				invBySitDTO.setPriceQty8(0);
			if (invbysitModel.getPricingModel().getPrice9() != null
					&& invbysitModel.getPricingModel().getPrice9().doubleValue() > 0) {
				invBySitDTO.setPrice9(invbysitModel.getPricingModel().getPrice9());
				invBySitDTO.setPriceQty9(1);
			} else
				invBySitDTO.setPriceQty9(0);
			if (invbysitModel.getPricingModel().getPrice10() != null
					&& invbysitModel.getPricingModel().getPrice10().doubleValue() > 0) {
				invBySitDTO.setPrice10(invbysitModel.getPricingModel().getPrice10());
				invBySitDTO.setPriceQty10(1);
			} else
				invBySitDTO.setPriceQty10(0);
			if (invbysitModel.getPricingModel().getAdditionalTax() != null
					&& invbysitModel.getPricingModel().getAdditionalTax().doubleValue() > 0)
				invBySitDTO.setAdditionalTax(invbysitModel.getPricingModel().getAdditionalTax());
		} else {
			invBySitDTO = setDefaultPricingModelNew(invBySitDTO);
		}

		// pricing model ends here

		// if reorder_pt > reorder_to then reorder_to = reorder_pt

		if (invbysitModel.getReplenishmentInfoModel() != null) {
			if (invbysitModel.getReplenishmentInfoModel().isReorderPointsLocked())
				invBySitDTO.setReorderLockedFl("Y");
			else
				invBySitDTO.setReorderLockedFl("N");

			BigDecimal reorderPtValue = null;
			if (invbysitModel.getReplenishmentInfoModel().getReorderPt() != null
					&& invbysitModel.getReplenishmentInfoModel().getReorderPt().doubleValue() > 0)
				reorderPtValue = invbysitModel.getReplenishmentInfoModel().getReorderPt();

			BigDecimal reorderToValue = null;
			if (invbysitModel.getReplenishmentInfoModel().getReorderTo() != null
					&& invbysitModel.getReplenishmentInfoModel().getReorderTo().doubleValue() > 0)
				reorderToValue = invbysitModel.getReplenishmentInfoModel().getReorderTo();

			if (reorderPtValue != null && reorderToValue != null) {
				if (reorderPtValue.doubleValue() > reorderToValue.doubleValue())
					reorderToValue = reorderPtValue;
			}

			if (reorderPtValue != null) {
				invBySitDTO.setReorderPt(reorderPtValue);
				invBySitDTO.setReorderLockedFl("Y");
			} else {
				invBySitDTO.setReorderPt(new BigDecimal(0));
				invBySitDTO.setReorderLockedFl("N");
			}

			if (reorderToValue != null) {
				invBySitDTO.setReorderTo(reorderToValue);
				invBySitDTO.setReorderLockedFl("Y");
				invBySitDTO.setStockingDt(new Date());
			} else {
				invBySitDTO.setReorderTo(new BigDecimal(0));
				invBySitDTO.setReorderLockedFl("N");
			}
		} else {
			invBySitDTO.setReorderPt(new BigDecimal(0));
			invBySitDTO.setReorderTo(new BigDecimal(0));
			invBySitDTO.setReorderLockedFl("N");
		}

		if (invbysitModel.getStatusInfoModel() != null) {
			if (invbysitModel.getStatusInfoModel().getPosSkuStatusId() != null
					&& invbysitModel.getStatusInfoModel().getPosSkuStatusId().length() > 0) {
				invBySitDTO.setPosSkuStatusId(invbysitModel.getStatusInfoModel().getPosSkuStatusId());
			}

			if (invbysitModel.getStatusInfoModel().getRestrictSaleId() != null
					&& invbysitModel.getStatusInfoModel().getRestrictSaleId().length() > 0) {
				invBySitDTO.setRestrictSaleId(invbysitModel.getStatusInfoModel().getRestrictSaleId());
			}

			// changes start here

			if (StringUtils.isNotBlank(invbysitModel.getStatusInfoModel().getItemizerStatusId()))
				invBySitDTO.setItemizerStatusId(invbysitModel.getStatusInfoModel().getItemizerStatusId());

			if (StringUtils.isNotBlank(invbysitModel.getStatusInfoModel().getItemStatusId()))
				invBySitDTO.setSkuStatusId(invbysitModel.getStatusInfoModel().getItemStatusId());
		}

//		if (StringUtils.isNotBlank(itemCreateDefaultInfoModel.getSiteGroup()))
//			invBySitDTO.setSiteNo(Integer.parseInt(itemCreateDefaultInfoModel.getSiteGroup()));

		// package qty and their prices

		if (invbysitModel.getQuantityPricingModel() != null) {
			if (invbysitModel.getQuantityPricingModel().getPkgPrice1() != null
					&& invbysitModel.getQuantityPricingModel().getPkgPrice1().doubleValue() > 0)
				invBySitDTO.setPkgPrice1(invbysitModel.getQuantityPricingModel().getPkgPrice1());

			if (invbysitModel.getQuantityPricingModel().getPkgPrice2() != null
					&& invbysitModel.getQuantityPricingModel().getPkgPrice2().doubleValue() > 0)
				invBySitDTO.setPkgPrice2(invbysitModel.getQuantityPricingModel().getPkgPrice2());

			if (invbysitModel.getQuantityPricingModel().getPkgPrice3() != null
					&& invbysitModel.getQuantityPricingModel().getPkgPrice3().doubleValue() > 0)
				invBySitDTO.setPkgPrice3(invbysitModel.getQuantityPricingModel().getPkgPrice3());

			if (invbysitModel.getQuantityPricingModel().getPkgPriceQty1() != null
					&& invbysitModel.getQuantityPricingModel().getPkgPriceQty1() > 0)
				invBySitDTO.setPkgPriceQty1(invbysitModel.getQuantityPricingModel().getPkgPriceQty1());
			else {
				invBySitDTO.setPkgPriceQty1(0);
				invBySitDTO.setPkgPrice1(new BigDecimal(0));

				invBySitDTO.setPkgPriceQty2(0);
				invBySitDTO.setPkgPrice2(new BigDecimal(0));

				invBySitDTO.setPkgPriceQty3(0);
				invBySitDTO.setPkgPrice3(new BigDecimal(0));
			}
			if ((invbysitModel.getQuantityPricingModel().getPkgPriceQty2() < invbysitModel.getQuantityPricingModel()
					.getPkgPriceQty1()) && (invbysitModel.getQuantityPricingModel().getPkgPriceQty2() != null)
					&& (invbysitModel.getQuantityPricingModel().getPkgPriceQty1() != null)) {
				logger.info("Price qty 2 should be greater than Price qty 1");
				throw new ServiceException(errors.getErrors().get(IErrorConstants.PRICE_QTY).formatMessage(2, 1));
			} else {
				if (invbysitModel.getQuantityPricingModel().getPkgPriceQty2() != null
						&& invbysitModel.getQuantityPricingModel().getPkgPriceQty2() > 0)
					invBySitDTO.setPkgPriceQty2(invbysitModel.getQuantityPricingModel().getPkgPriceQty2());
			}
			if ((invbysitModel.getQuantityPricingModel().getPkgPriceQty3() < invbysitModel.getQuantityPricingModel()
					.getPkgPriceQty2()) && (invbysitModel.getQuantityPricingModel().getPkgPriceQty3() != null)
					&& (invbysitModel.getQuantityPricingModel().getPkgPriceQty2() != null)) {
				logger.info("Price qty 3 should be greater than Price qty 2");
				throw new ServiceException(errors.getErrors().get(IErrorConstants.PRICE_QTY).formatMessage(3, 2));
			} else {
				if (invbysitModel.getQuantityPricingModel().getPkgPriceQty3() != null
						&& invbysitModel.getQuantityPricingModel().getPkgPriceQty3() > 0)
					invBySitDTO.setPkgPriceQty3(invbysitModel.getQuantityPricingModel().getPkgPriceQty3());
			}
		} else {
			invBySitDTO.setPkgPriceQty1(0);
			invBySitDTO.setPkgPrice1(new BigDecimal(0));

			invBySitDTO.setPkgPriceQty2(0);
			invBySitDTO.setPkgPrice2(new BigDecimal(0));

			invBySitDTO.setPkgPriceQty3(0);
			invBySitDTO.setPkgPrice3(new BigDecimal(0));
		}

		// qty and their prices ends

		if (invbysitModel.getSuggestedRetailInfoModel() != null) {
			if (invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailPrice() != null
					&& invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailPrice().doubleValue() > 0)
				invBySitDTO.setMsrpPrice(invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailPrice());
			else
				invBySitDTO.setMsrpPrice(new BigDecimal(0));

			if (invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailQty() != null
					&& invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailQty() > 0) {
				invBySitDTO.setMsrpQty(invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailQty());
			} else {
				invBySitDTO.setMsrpQty(0);
				invBySitDTO.setMsrpPrice(new BigDecimal(0));
				invBySitDTO.setEpoPct(new BigDecimal(0));
			}

			if (invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailEPO() != null
					&& invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailEPO().doubleValue() > 0
					&& invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailQty() != null
					&& invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailQty() > 0) {
				invBySitDTO.setEpoPct(invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailEPO());

				BigDecimal pQty1 = new BigDecimal(invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailQty());

				BigDecimal msrpPrice = new BigDecimal(0);
				if (invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailPrice() != null
						&& invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailPrice().doubleValue() > 0)
					msrpPrice = invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailPrice();

				BigDecimal msrpQty = new BigDecimal(
						invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailQty());
				BigDecimal epo = invbysitModel.getSuggestedRetailInfoModel().getSuggestedRetailEPO();

				BigDecimal price = pQty1.multiply((msrpPrice.divide(msrpQty))
						.subtract((msrpPrice.divide(msrpQty)).multiply(epo.divide(new BigDecimal(100)))));

				invBySitDTO = setDefaultPricingModelNew(invBySitDTO);

				invBySitDTO.setPrice1(price.setScale(2, RoundingMode.HALF_UP));
				invBySitDTO.setPriceQty1(pQty1.intValue());
			
			}
		} else {
			invBySitDTO.setMsrpQty(0);
			invBySitDTO.setMsrpPrice(new BigDecimal(0));
			invBySitDTO.setEpoPct(new BigDecimal(0));
		}

		// keep the property name as isAutomaticallyReplenished and use
		// automaticallyReplenished in json
		if (invbysitModel.getReplenishmentInfoModel() != null) {
			if (invbysitModel.getReplenishmentInfoModel().isAutomaticallyReplenished())
				invBySitDTO.setReplenishmentFl("Y");
			else
				invBySitDTO.setReplenishmentFl("N");

			if (invbysitModel.getReplenishmentInfoModel().isVelocityCodeLocked())
				invBySitDTO.setVelocityLockedFl("Y");
			else
				invBySitDTO.setVelocityLockedFl("N");

			if (StringUtils.isNotBlank(invbysitModel.getReplenishmentInfoModel().getVelocityCode())) {
				String velocityCode = invbysitModel.getReplenishmentInfoModel().getVelocityCode();
				List<DomainRefCode> codes = domainService.getDomainCodes(domainUser, "VELOCITY CODES");
				if (codes.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
						.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(velocityCode))) {
					invBySitDTO.setVelocityCd(velocityCode);
					invBySitDTO.setVelocityLockedFl("Y");
				}
			} else
				invBySitDTO.setVelocityLockedFl("N");

			if (invbysitModel.getReplenishmentInfoModel().getMaxOrderQty() != null
					&& invbysitModel.getReplenishmentInfoModel().getMaxOrderQty().doubleValue() > 0)
				invBySitDTO.setMaxOrderQty(invbysitModel.getReplenishmentInfoModel().getMaxOrderQty());
			else
				invBySitDTO.setMaxOrderQty(new BigDecimal(0));

			if (invbysitModel.getReplenishmentInfoModel().getWarehouseInnerPack() != null
					&& invbysitModel.getReplenishmentInfoModel().getWarehouseInnerPack().doubleValue() > 0)
				invBySitDTO.setWarehouseInnerPack(invbysitModel.getReplenishmentInfoModel().getWarehouseInnerPack());

			if (invbysitModel.getReplenishmentInfoModel().getProductStartDate() != null)
				invBySitDTO.setProductStartDt(
						getFormattedDate(invbysitModel.getReplenishmentInfoModel().getProductStartDate()));

			if (invbysitModel.getReplenishmentInfoModel().getProductEndDate() != null) {
				invBySitDTO.setProductEndDt(
						getFormattedDate(invbysitModel.getReplenishmentInfoModel().getProductEndDate()));
			}

			if (invbysitModel.getReplenishmentInfoModel().getLastReplenishmentDate() != null) {
				invBySitDTO.setLastReplenishmentDt(
						getFormattedDate(invbysitModel.getReplenishmentInfoModel().getLastReplenishmentDate()));
			}

			validateDateRanges(invbysitModel.getReplenishmentInfoModel().getProductStartDate(),
					invbysitModel.getReplenishmentInfoModel().getLastReplenishmentDate(),
					invbysitModel.getReplenishmentInfoModel().getProductEndDate());
		} else {
			invBySitDTO.setReplenishmentFl("N");
			invBySitDTO.setVelocityLockedFl("N");
		}

		if (StringUtils.isNotBlank(invbysitModel.getTareTableNo()))
			invBySitDTO.setTareTableNo(invbysitModel.getTareTableNo());

		if (invbysitModel.getCommissionCd() != null && invbysitModel.getCommissionCd() > 0)
			invBySitDTO.setCommissionCd(invbysitModel.getCommissionCd());

		if (invbysitModel.isDiscountAllowed())
			invBySitDTO.setNonDiscountFl("Y");
		else
			invBySitDTO.setNonDiscountFl("N");

		if (invbysitModel.isDiscontinued()) {
			invBySitDTO.setDiscontinuedStFl("Y");
			invBySitDTO.setDiscontinuedBy(domainUser.getUserName());
		} else
			invBySitDTO.setDiscontinuedStFl("N");

		// changes end here
		return invBySitDTO;
	}

	private InvbysitDTO setDefaultPricingModelNew(InvbysitDTO invBySitDTO) {
		// TODO Auto-generated method stub
		invBySitDTO.setPrice1(new BigDecimal(0));
		invBySitDTO.setPriceQty1(0);

		invBySitDTO.setPrice2(new BigDecimal(0));
		invBySitDTO.setPriceQty2(0);

		invBySitDTO.setPrice3(new BigDecimal(0));
		invBySitDTO.setPriceQty3(0);

		invBySitDTO.setPrice4(new BigDecimal(0));
		invBySitDTO.setPriceQty4(0);

		invBySitDTO.setPrice5(new BigDecimal(0));
		invBySitDTO.setPriceQty5(0);

		invBySitDTO.setPrice6(new BigDecimal(0));
		invBySitDTO.setPriceQty6(0);

		invBySitDTO.setPrice7(new BigDecimal(0));
		invBySitDTO.setPriceQty7(0);

		invBySitDTO.setPrice8(new BigDecimal(0));
		invBySitDTO.setPriceQty8(0);

		invBySitDTO.setPrice9(new BigDecimal(0));
		invBySitDTO.setPriceQty9(0);

		invBySitDTO.setPrice10(new BigDecimal(0));
		invBySitDTO.setPriceQty10(0);

		return invBySitDTO;
	}

	private InvtoryDTO validateInventoryModelNew(UserIdentity domainUser, InvtoryDTO invtoryDTO,
			InventoryModel invModel) {
		// TODO Auto-generated method stub
		if (invModel.getPackUpAction() != null && invModel.getPackUpAction() > 0)
			invtoryDTO.setPackupActionId(invModel.getPackUpAction());

		if (invModel.getOwnerId() != null && invModel.getOwnerId() > 0)
			invtoryDTO.setOwnerId(invModel.getOwnerId());
		else {
			int siteNo = tmxGblService.getCurrentSite();
			if (siteNo > 0) {
				BigDecimal ownerId = bVendorService.getOwnerIdFromVendor(siteNo);
				if (ownerId != null) {
					invtoryDTO.setOwnerId(ownerId.intValue());
				}
			}
		}

		// description / receipt / look up description validations
		if (StringUtils.isNotBlank(invModel.getItemDescription())) {

			if (invModel.getItemDescription().length() > 31)
				invtoryDTO.setDescription(invModel.getItemDescription().substring(0, 30));
			else
				invtoryDTO.setDescription(invModel.getItemDescription());

			if (invModel.getItemDescription().length() > 27)
				invtoryDTO.setReceiptDesc(invModel.getItemDescription().substring(0, 26));
			else
				invtoryDTO.setReceiptDesc(invModel.getItemDescription());

			String flag = tmxGblService.getFlag(ITmxGblConstants.LONG_LOOKUP_DESC);

			if (flag.equalsIgnoreCase("Y")) {
				invtoryDTO.setLookupDesc(invModel.getItemDescription().toLowerCase());
			} else {
				if (invModel.getItemDescription().length() > 18)
					invtoryDTO.setLookupDesc(invModel.getItemDescription().toLowerCase().substring(0, 18));
				else
					invtoryDTO.setLookupDesc(invModel.getItemDescription());
			}

		}

		// validation on dept
		int deptNo = invModel.getProductModel().getDeptNo();
		if (invModel.getProductModel().getDeptNo() != null && invModel.getProductModel().getDeptNo() > 0) {

			DeptDTOId deptId = new DeptDTOId(deptNo);
			DeptDTO dept = inventoryAPI.getDept(domainUser, deptId);

			if (dept != null && dept.getDeptNo() != null && dept.getDeptNo() > 0) {
				invtoryDTO.setDeptNo(deptNo);

				if (StringUtils.isNotBlank(dept.getNonRevenueFl()))
					invtoryDTO.setSpecialItemFl(dept.getNonRevenueFl());

				if (StringUtils.isNotBlank(dept.getEmployeeId()))
					invtoryDTO.setEmployeeId(dept.getEmployeeId());
			}
		} else {
			throw new ServiceException(errors.getErrors().get(IErrorConstants.INVALID_DEPARTMENT));
		}

		int classNo = invModel.getProductModel().getClassNo();
		if (invModel.getProductModel().getClassNo() != null && invModel.getProductModel().getClassNo() > 0) {
			ClassDTOId classId = new ClassDTOId(deptNo, classNo);
			ClassDTO classDto = inventoryAPI.getClass(domainUser, classId);
			if (classDto != null && classDto.getClassNo() != null && classDto.getClassNo() > 0)
				invtoryDTO.setClassNo(classNo);
		} else {
			throw new ServiceException(errors.getErrors().get(IErrorConstants.INVALID_CLASS));
		}

		int lineNo = invModel.getProductModel().getLineNo();
		if (invModel.getProductModel().getLineNo() != null && invModel.getProductModel().getLineNo() > 0) {
			LineDTOId lineId = new LineDTOId(deptNo, classNo, lineNo);
			LineDTO lineDto = inventoryAPI.getLine(domainUser, lineId);
			if (lineDto != null && lineDto.getLineNo() != null && lineDto.getLineNo() > 0)
				invtoryDTO.setLineNo(lineNo);
		} else {
			throw new ServiceException(errors.getErrors().get(IErrorConstants.INVALID_LINE));
		}

		// default it should use below value
		BigDecimal defaultReportFactor = tmxGblService.getNum(ITmxGblConstants.REPORT_FACTOR);

		if (invModel.getReportInfoModel().getReportFactor() != null) {
			BigDecimal reportFactor = invModel.getReportInfoModel().getReportFactor();

			if (reportFactor != null)
				invtoryDTO.setReportFactor(reportFactor);
			else
				invtoryDTO.setReportFactor(defaultReportFactor);
		}

		if (StringUtils.isNotBlank(invModel.getReportInfoModel().getReportUnitOfMeasure())) {
			invtoryDTO.setReportUm(invModel.getReportInfoModel().getReportUnitOfMeasure());
		} else {
			String defaultReportUm = tmxGblService.getText(ITmxGblConstants.REPORT_UM);
			invtoryDTO.setReportUm(defaultReportUm);
		}

		if (StringUtils.isNotBlank(invModel.getReportInfoModel().getSellUnitOfMeasure())) {
			invtoryDTO.setSellUm(invModel.getReportInfoModel().getSellUnitOfMeasure());
		} else {
			String defaultSellUm = tmxGblService.getText(ITmxGblConstants.SELL_UM);
			invtoryDTO.setSellUm(defaultSellUm);
		}

		// MeasurementInfoModel starts

		if (invModel.getMeasurementInfoModel() != null) {
			// check once
			boolean catchFl = invModel.getMeasurementInfoModel().isCatchWeightFl();

			if (catchFl) {

				invtoryDTO.setCatchWeightFl("Y");
				invtoryDTO.setWeighedItemFl("Y");

				// unit measure is mandatory when setWeighedItemFl is Y else alert
				if (invModel.getMeasurementInfoModel().getUnitMeasured() != null
						&& invModel.getMeasurementInfoModel().getUnitMeasured().doubleValue() > 1)
					invtoryDTO.setLcuQty(invModel.getMeasurementInfoModel().getUnitMeasured());
				else
					invtoryDTO.setLcuQty(new BigDecimal(1.00));
			} else {
				invtoryDTO.setCatchWeightFl("N");
				invtoryDTO.setWeighedItemFl("N");
				if (invModel.getRegionalInfoModel() != null && invModel.getRegionalInfoModel().getLcuQty() != null
						&& invModel.getRegionalInfoModel().getLcuQty().doubleValue() > 1)
					invtoryDTO.setLcuQty(invModel.getRegionalInfoModel().getLcuQty());
				else
					invtoryDTO.setLcuQty(new BigDecimal(1));
			}

			if (invModel.getMeasurementInfoModel().getUnitCode() != null
					&& invModel.getMeasurementInfoModel().getUnitCode().doubleValue() >= 0)
				invtoryDTO.setUnitCube(invModel.getMeasurementInfoModel().getUnitCode());

			if (invModel.getMeasurementInfoModel().getUnitMeasured() != null
					&& invModel.getMeasurementInfoModel().getUnitMeasured().doubleValue() >= 0)
				invtoryDTO.setUnitWeight(invModel.getMeasurementInfoModel().getUnitMeasured());

			if (StringUtils.isNotBlank(invModel.getMeasurementInfoModel().getTareNumber())) {
				String tareNo = invModel.getMeasurementInfoModel().getTareNumber();
				List<TareDetailsResponse> tareList = lovService.getTareDetails();
				if (tareList.stream().filter(obj -> StringUtils.isNotBlank(obj.getTareNo()))
						.anyMatch(o -> o.getTareNo().equalsIgnoreCase(tareNo))) {
					invtoryDTO.setTareTableNo(tareNo);
				}
			}
		} else {
			invtoryDTO.setCatchWeightFl("N");
			invtoryDTO.setWeighedItemFl("N");
			invtoryDTO.setUnitCube(new BigDecimal("0.00"));
		}

		// MeasurementInfoModel ends
		String mfgValue = tmxGblService.getFlag("INV_UNIQUE_MFG_CD_REQUIRED");
		String mfgCode = invModel.getItemIdentifiersInfoModel().getMfgCd();
		int mfgCount = lookUpService.getCount(mfgCode);

		logger.info("mfg code unique ==>> " + mfgValue);
		logger.info("MFG Code count ==>> " + lookUpService.getCount(mfgCode));

		if (("Y".equalsIgnoreCase(mfgValue)) && (mfgCount == 0)) {
			if (StringUtils.isNotBlank(mfgCode)) {
				invtoryDTO.setMfgCd(mfgCode);
			}
		} else if ("N".equalsIgnoreCase(mfgValue)) {
			if (StringUtils.isNotBlank(mfgCode)) {
				invtoryDTO.setMfgCd(mfgCode);
			}
		} else {
			throw new ServiceException(errors.getErrors().get(IErrorConstants.MFG_CODE_NOT_ALLOWED));
		}
//		else throw mfg code exception 

		// RegionalInfoModel starts
		if (invModel.getRegionalInfoModel() != null) {
			if (invModel.getRegionalInfoModel().isRegionalSupersede())
				invtoryDTO.setRegionalSupersedeFl("Y");
			else
				invtoryDTO.setRegionalSupersedeFl("N");

			if (invModel.getRegionalInfoModel().getReplacementSKU() != null
					&& invModel.getRegionalInfoModel().getReplacementSKU() > 0)
				invtoryDTO.setReplacementSkuNo(invModel.getRegionalInfoModel().getReplacementSKU());

			if (StringUtils.isNotBlank(invModel.getRegionalInfoModel().getItemType())) {
				String itemType = invModel.getRegionalInfoModel().getItemType();
				List<InventoryTypeResponse> availableItemTypes = lovService.getInventoryType();

				if (availableItemTypes.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
						.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(itemType))) {
					invtoryDTO.setInvType(itemType);
					if (!("CRD".equalsIgnoreCase(itemType))) {
						invtoryDTO.setSubInvFl("N");
					}
				}
			}

			if (StringUtils.isNotBlank(invModel.getRegionalInfoModel().getBuyerID())) {
				String buyerId = invModel.getRegionalInfoModel().getBuyerID();
				List<BuyerDetailsResponse> buyers = lovService.getBuyerDetails(tmxGblService.getCurrentSite());
				if (buyers.stream().filter(obj -> StringUtils.isNotBlank(obj.getEmployeeId()))
						.anyMatch(o -> o.getEmployeeId().equalsIgnoreCase(buyerId))) {
					invtoryDTO.setEmployeeId(buyerId);
				}
			}

			if (invModel.getRegionalInfoModel().getReportCode() != null
					&& invModel.getRegionalInfoModel().getReportCode() > 0) {
				int reportCd = invModel.getRegionalInfoModel().getReportCode();
				List<ReportCodeDetailsResponse> availableReportCd = lovService.getReportCodeDetails();
				if (availableReportCd.stream().filter(obj -> obj.getReportCode() > 0)
						.anyMatch(o -> o.getReportCode() == reportCd))
					invtoryDTO.setReportCd(reportCd);
			}
		} else {
			invtoryDTO.setRegionalSupersedeFl("N");
		}

		// RegionalInfoModel ends linksku in SerialItemsInfoModel section due to related
		// validations

		// InnerPackInfoModel starts

		if (invModel.getInnerPackInfoModel() != null) {
			if (invModel.getInnerPackInfoModel().getInnerPackQty() != null
					&& invModel.getInnerPackInfoModel().getInnerPackQty().doubleValue() > 0)
				invtoryDTO.setWarehouseInnerPack(invModel.getInnerPackInfoModel().getInnerPackQty());
			else
				invtoryDTO.setWarehouseInnerPack(new BigDecimal(1));

			if (invModel.getInnerPackInfoModel().getPriceListSeq() != null
					&& invModel.getInnerPackInfoModel().getPriceListSeq() > 0)
				invtoryDTO.setPriceListSeq(invModel.getInnerPackInfoModel().getPriceListSeq());
			else
				invtoryDTO.setPriceListSeq(0);

			int weekHistory = tmxGblService.getNum("WEEKS_OF_HISTORY").intValue();

			if (invModel.getInnerPackInfoModel().getWeeksHistory() != null
					&& invModel.getInnerPackInfoModel().getWeeksHistory() > 0)
				invtoryDTO.setWeeksHistory(invModel.getInnerPackInfoModel().getWeeksHistory());
			else
				invtoryDTO.setWeeksHistory(weekHistory);

			int periodHistory = tmxGblService.getNum("PERIODS_OF_HISTORY").intValue();

			if (invModel.getInnerPackInfoModel().getPeriodsHistory() != null
					&& invModel.getInnerPackInfoModel().getPeriodsHistory() > 0)
				invtoryDTO.setPeriodsHistory(invModel.getInnerPackInfoModel().getPeriodsHistory());
			else
				invtoryDTO.setPeriodsHistory(periodHistory);

			if (invModel.getInnerPackInfoModel().getGlCatID() != null
					&& invModel.getInnerPackInfoModel().getGlCatID() > 0) {
				int glCatId = invModel.getInnerPackInfoModel().getGlCatID();
				List<GLCategoryInfoResponse> glcatList = lovService.getGLCategoryDetails();

				if (glcatList.stream().filter(obj -> obj.getGlCatId() != null)
						.anyMatch(o -> o.getGlCatId() == glCatId)) {
					invtoryDTO.setGlCatId(glCatId);
				}
			}

			if (invModel.getInnerPackInfoModel().getXrefNumber() != null
					&& invModel.getInnerPackInfoModel().getXrefNumber() > 0) {
				int xrefNo = invModel.getInnerPackInfoModel().getXrefNumber();
				if (lookUpService.getXRef(xrefNo) != null) {
					int xrefNoInDb = lookUpService.getXRef(xrefNo).intValue();
					invtoryDTO.setXrefNo(xrefNoInDb);
				}
			}
		} else {
			invtoryDTO.setWarehouseInnerPack(new BigDecimal(1));
			invtoryDTO.setPriceListSeq(0);
			invtoryDTO.setPeriodsHistory(tmxGblService.getNum("PERIODS_OF_HISTORY") == null ? 0
					: tmxGblService.getNum("PERIODS_OF_HISTORY").intValue());
			invtoryDTO.setWeeksHistory(tmxGblService.getNum("WEEKS_OF_HISTORY") == null ? 0
					: tmxGblService.getNum("WEEKS_OF_HISTORY").intValue());
		}

		// InnerPackInfoModel ends

		// LabelInfoModel starts

		if (invModel.getLabelInfoModel() != null) {
			if (StringUtils.isNotBlank(invModel.getLabelInfoModel().getEquivUm()))
				invtoryDTO.setEquivUm(invModel.getLabelInfoModel().getEquivUm());

			if (invModel.getLabelInfoModel().getEquivFactor() != null
					&& invModel.getLabelInfoModel().getEquivFactor().doubleValue() >= 0)
				invtoryDTO.setEquivFactor(invModel.getLabelInfoModel().getEquivFactor());

			if (StringUtils.isNotBlank(invModel.getLabelInfoModel().getLabelDescription1()))
				invtoryDTO.setLabelDesc1(invModel.getLabelInfoModel().getLabelDescription1());

			if (StringUtils.isNotBlank(invModel.getLabelInfoModel().getLabelDescription2()))
				invtoryDTO.setLabelDesc2(invModel.getLabelInfoModel().getLabelDescription2());

			if (invModel.getLabelInfoModel().isPrivateLabelItem())
				invtoryDTO.setPrivateBrandFl("Y");
			else
				invtoryDTO.setPrivateBrandFl("N");
		} else {
			invtoryDTO.setEquivUm("EA");
			invtoryDTO.setEquivFactor(new BigDecimal("1.0000"));
		}

		// LabelInfoModel ends

		// check if tender validation is working
		if (invModel.getTenderCertificateTypeInfoModel() != null) {
			if (invModel.getTenderCertificateTypeInfoModel().getTenderCertId() != null
					&& invModel.getTenderCertificateTypeInfoModel().getTenderCertId() > 0) {
				int tenderProgramId = invModel.getTenderCertificateTypeInfoModel().getTenderCertId();
				List<TenderCertificateInfoResponse> tenderPrograms = lovService.getTenderPrograms();

				if (tenderPrograms.stream().filter(obj -> obj.getTenderProgramId() != null)
						.anyMatch(o -> o.getTenderProgramId() == tenderProgramId)) {
					invtoryDTO.setTenderProgramId(tenderProgramId);
				}
			}
		}

		// SerialItemsInfoModel starts
		if (invModel.getSerialItemsInfoModel() != null) {
			if (invModel.getSerialItemsInfoModel().isSerializedItem()) {
				invtoryDTO.setUseSerialFl("Y");
				invtoryDTO.setReportUm("EA");
				invtoryDTO.setCaptureSerialNoFl("Y");
				invtoryDTO.setNonInventoryFl("N");
			}

			if (invModel.getRegionalInfoModel() != null) {
				if (invModel.getRegionalInfoModel().getLinkSkuNumber() != null) {
					int linkSkuNo = invModel.getRegionalInfoModel().getLinkSkuNumber();
					if (linkSkuNo > 0) {
						SKULinkDetails skuLinkdetails = lookUpService.getSKULinkDetails(linkSkuNo);
						if (!skuLinkdetails.isSerializedItem() && !skuLinkdetails.isSupplyItem()
								&& !skuLinkdetails.isNonInventory() && !skuLinkdetails.isMeasuredItem()
								&& (skuLinkdetails.getLinkSkuNumber() == null)
								&& !(skuLinkdetails.getDeptNo() == linkSkuNo)) {
							if (invModel.getRegionalInfoModel().getLinkSkuNumber() != null && linkSkuNo > 0)
								invtoryDTO.setLinkSkuNo(linkSkuNo);
							invtoryDTO.setUseSerialFl("N");
						}
					}
				}
			}

			if (invModel.getSerialItemsInfoModel().isAutoGenSerial())
				invtoryDTO.setAutoGenSerialsFl("Y");
			else
				invtoryDTO.setAutoGenSerialsFl("N");
		} else {
			invtoryDTO.setUseSerialFl("N");
			invtoryDTO.setAutoGenSerialsFl("N");
		}

		// SerialItemsInfoModel ends

		// ItemTypeFlagsInfoModel starts

		if (invModel.getItemTypeFlagsInfoModel() != null) {
			if (invModel.getItemTypeFlagsInfoModel().isNonRevenueItem())
				invtoryDTO.setSpecialItemFl("Y");
			else
				invtoryDTO.setSpecialItemFl("N");

			if (invModel.getItemTypeFlagsInfoModel().isStoreCpnFl())
				invtoryDTO.setStoreCpnFl("Y");
			else
				invtoryDTO.setStoreCpnFl("N");

			if (invModel.getItemTypeFlagsInfoModel().isNonInventoryFl())
				invtoryDTO.setNonInventoryFl("Y");
			else
				invtoryDTO.setNonInventoryFl("N");

			if (invModel.getItemTypeFlagsInfoModel().isSubInvFl())
				invtoryDTO.setSubInvFl("Y");
			else
				invtoryDTO.setSubInvFl("N");

			if (invModel.getItemTypeFlagsInfoModel().isSupplyItemFl())
				invtoryDTO.setSupplyItemFl("Y");
			else
				invtoryDTO.setSupplyItemFl("N");

			if (invModel.getItemTypeFlagsInfoModel().isCoreFl())
				invtoryDTO.setCoreFl("Y");
			else
				invtoryDTO.setCoreFl("N");

			if (invModel.getItemTypeFlagsInfoModel().isFlexibleItemFl())
				invtoryDTO.setFlexibleItemFl("Y");
			else
				invtoryDTO.setFlexibleItemFl("N");
		} else {
			invtoryDTO.setSpecialItemFl("N");
			invtoryDTO.setStoreCpnFl("N");
			invtoryDTO.setNonInventoryFl("N");
			invtoryDTO.setSubInvFl("N");
			invtoryDTO.setSupplyItemFl("N");
			invtoryDTO.setCoreFl("N");
			invtoryDTO.setFlexibleItemFl("N");
		}

		// ItemTypeFlagsInfoModel ends

		// GeneralStatusFlagsInfoModel starts

		if (invModel.getGeneralStatusFlagsInfoModel() != null) {
			if (invModel.getGeneralStatusFlagsInfoModel().isReorderFl())
				invtoryDTO.setReorderFl("Y");
			else
				invtoryDTO.setReorderFl("N");

			if (invModel.getGeneralStatusFlagsInfoModel().isDiscontinuedFl())
				invtoryDTO.setDiscontinuedFl("Y");
			else
				invtoryDTO.setDiscontinuedFl("N");

			if (invModel.getGeneralStatusFlagsInfoModel().isObsoleteFl())
				invtoryDTO.setObsoleteFl("Y");
			else
				invtoryDTO.setObsoleteFl("N");
		} else {
			invtoryDTO.setReorderFl("N");
			invtoryDTO.setDiscontinuedFl("N");
			invtoryDTO.setObsoleteFl("N");
		}

		// GeneralStatusFlagsInfoModel ends

		// POSFlagsInfoModel starts

		if (invModel.getPosFlagsInfoModel() != null) {
			if (invModel.getPosFlagsInfoModel().isCaptureSerialNoFl())
				invtoryDTO.setCaptureSerialNoFl("Y");
			else
				invtoryDTO.setCaptureSerialNoFl("N");

			if (invModel.getPosFlagsInfoModel().isCaptureOrderNoFl())
				invtoryDTO.setCaptureOrderNoFl("Y");
			else
				invtoryDTO.setCaptureOrderNoFl("N");

			if (invModel.getPosFlagsInfoModel().isDownloadFl())
				invtoryDTO.setDownloadFl("Y");
			else
				invtoryDTO.setDownloadFl("N");

			if (invModel.getPosFlagsInfoModel().isNegativePriceFl())
				invtoryDTO.setNegativePriceFl("Y");
			else
				invtoryDTO.setNegativePriceFl("N");
		} else {
			invtoryDTO.setCaptureSerialNoFl("N");
			invtoryDTO.setCaptureOrderNoFl("N");
			invtoryDTO.setDownloadFl("N");
			invtoryDTO.setNegativePriceFl("N");
		}
		// POSFlagsInfoModel ends

		invtoryDTO.setCreateDt(new Date());
		invtoryDTO.setChangeDt(new Date());
		invtoryDTO.setDateModified(new Date());

		return invtoryDTO;
	}

	// req res changes ends here

	private void validateInventoryBySitModel(InvBySiteModel invBySiteModel) {
		// TODO Auto-generated method stub
		if (invBySiteModel == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("InvBySite Model"));
		if (invBySiteModel.getPricingModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Pricing Model"));
		if (invBySiteModel.getQuantityPricingModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Quantity Pricing Model"));
		if (invBySiteModel.getReplenishmentInfoModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Replenishment Info Model"));
		if (invBySiteModel.getStatusInfoModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Status Info Model"));
		if (invBySiteModel.getSuggestedRetailInfoModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Suggested Retail Info Model"));
	}

	public InvtoryDTO validateAndMapUpdateInvModel(UserIdentity domainUser,
			com.rediron.platform.domain.model.request.InventoryModel inventoryModel, int skuNo, int siteNo) {
		// TODO Auto-generated method stub
		InventoryGlobalInfoDTO skuDetails = inventoryAPI.getInventoryItemGlobalInfo(domainUser, skuNo, siteNo);
		if (skuDetails == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("INVTORY GLOBAL"));

		InvtoryDTO dto = skuDetails.getInvtoryDto();
		if (dto == null)
			throw new ServiceException(errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("INVTORY"));

		BigDecimal countSheet = getCNTSheetCount(domainUser, skuNo);
		boolean countSheetFlag = false;

		if (countSheet != null && countSheet.doubleValue() > 1)
			countSheetFlag = true;

		if (StringUtils.isNotBlank(inventoryModel.getItemDescription())) {
			if (inventoryModel.getItemDescription().length() > 31)
				dto.setDescription(inventoryModel.getItemDescription().substring(0, 30));
			else
				dto.setDescription(inventoryModel.getItemDescription());

			if (inventoryModel.getItemDescription().length() > 27)
				dto.setReceiptDesc(inventoryModel.getItemDescription().substring(0, 26));
			else
				dto.setReceiptDesc(inventoryModel.getItemDescription());

			String flag = tmxGblService.getFlag(ITmxGblConstants.LONG_LOOKUP_DESC);

			if (flag.equalsIgnoreCase("Y")) {
				dto.setLookupDesc(inventoryModel.getItemDescription().toLowerCase());
			} else {

				if (inventoryModel.getItemDescription().length() > 18)
					dto.setLookupDesc(inventoryModel.getItemDescription().toLowerCase().substring(0, 18));
				else
					dto.setLookupDesc(inventoryModel.getItemDescription());
			}
		}

		if (inventoryModel.getProductModel() != null && !countSheetFlag) {

			int deptNo = 0;
			int classNo = 0;
			int lineNo = 0;

			if (inventoryModel.getProductModel().getDeptNo() != null)
				deptNo = inventoryModel.getProductModel().getDeptNo();

			if (inventoryModel.getProductModel().getClassNo() != null)
				classNo = inventoryModel.getProductModel().getClassNo();

			if (inventoryModel.getProductModel().getLineNo() != null)
				lineNo = inventoryModel.getProductModel().getLineNo();

			if (deptNo > 0) {
				DeptDTOId deptId = new DeptDTOId(deptNo);
				DeptDTO dept = inventoryAPI.getDept(domainUser, deptId);
				if (inventoryModel.getProductModel().getDeptNo() != null
						&& inventoryModel.getProductModel().getDeptNo() > 0) {
					if (dept != null && dept.getDeptNo() != null && dept.getDeptNo() > 0) {
						dto.setDeptNo(deptNo);

						if (StringUtils.isNotBlank(dept.getNonRevenueFl()))
							dto.setSpecialItemFl(dept.getNonRevenueFl());

						if (StringUtils.isNotBlank(dept.getEmployeeId()))
							dto.setEmployeeId(dept.getEmployeeId());
					}
				}
			}

			// if !cntsheet flag then only update
			if (classNo > 0) {
				if (inventoryModel.getProductModel().getClassNo() != null
						&& inventoryModel.getProductModel().getClassNo() > 0) {
					ClassDTOId classId = new ClassDTOId(deptNo, classNo);
					ClassDTO classDto = inventoryAPI.getClass(domainUser, classId);
					if (classDto != null && classDto.getClassNo() != null && classDto.getClassNo() > 0)
						dto.setClassNo(classNo);
				}
			}

			// if !cntsheet flag then only update
			if (lineNo > 0) {
				if (inventoryModel.getProductModel().getLineNo() != null
						&& inventoryModel.getProductModel().getLineNo() > 0) {
					LineDTOId lineId = new LineDTOId(deptNo, classNo, lineNo);
					LineDTO lineDto = inventoryAPI.getLine(domainUser, lineId);
					if (lineDto != null && lineDto.getLineNo() != null && lineDto.getLineNo() > 0) {
						dto.setLineNo(lineNo);
						if (StringUtils.isNotBlank(lineDto.getEmployeeId()))
							dto.setEmployeeId(lineDto.getEmployeeId());
					}
				}
			}
		} else {
			dto.setSpecialItemFl("Y");
		}

		if (inventoryModel.getDescriptionModel() != null) {
			if (StringUtils.isNotBlank(inventoryModel.getDescriptionModel().getProductSize()))
				dto.setPkgDesc(inventoryModel.getDescriptionModel().getProductSize());
		}

		if (inventoryModel.getItemIdentifiersInfoModel() != null) {
			String mfgValue = tmxGblService.getFlag("INV_UNIQUE_MFG_CD_REQUIRED");
			String mfgCode = inventoryModel.getItemIdentifiersInfoModel().getMfgCd();
			int mfgCount = lookUpService.getCount(mfgCode);

			logger.info("mfg code unique ==>> " + mfgValue);
			logger.info("MFG Code count ==>> " + lookUpService.getCount(mfgCode));

			if (StringUtils.isNotBlank(mfgCode)) {
				if (("Y".equalsIgnoreCase(mfgValue)) && (mfgCount == 0)) {
					dto.setMfgCd(mfgCode);
				} else {// else mfg will be same as earlier
					throw new ServiceException(errors.getErrors().get(IErrorConstants.MFG_CODE_NOT_ALLOWED));
				}
			}
		}

		if (inventoryModel.getReportInfoModel() != null) {

			if (StringUtils.isNotBlank(inventoryModel.getReportInfoModel().getSellUnitOfMeasure()))
				dto.setSellUm(inventoryModel.getReportInfoModel().getSellUnitOfMeasure());

			if (StringUtils.isNotBlank(inventoryModel.getReportInfoModel().getReportUnitOfMeasure()))
				dto.setReportUm(inventoryModel.getReportInfoModel().getReportUnitOfMeasure());

			if (inventoryModel.getReportInfoModel().getReportFactor() != null
					&& inventoryModel.getReportInfoModel().getReportFactor().doubleValue() > 0)
				dto.setReportFactor(inventoryModel.getReportInfoModel().getReportFactor());
		}

		// check once starts
		if (inventoryModel.getMeasurementInfoModel() != null) {
			boolean catchFl = inventoryModel.getMeasurementInfoModel().isCatchWeightFl();

			if (catchFl) {
				int moveCount = 0;
				if (bItemService.checkMovement(dto.getSkuNo()) != null)
					moveCount = bItemService.checkMovement(dto.getSkuNo()).intValue();
				if (moveCount != 1) {
					if (inventoryModel.getMeasurementInfoModel().isMeasuredItem())
						dto.setWeighedItemFl("Y");
				}
				// unit measure is mandatory when setWeighedItemFl is Y else throw exception /
				// alert
				if (inventoryModel.getMeasurementInfoModel().getUnitMeasured() != null
						&& inventoryModel.getMeasurementInfoModel().getUnitMeasured().doubleValue() > 1)
					dto.setLcuQty(inventoryModel.getMeasurementInfoModel().getUnitMeasured());
				else
					dto.setLcuQty(new BigDecimal(1.00));
			} else {

				int moveCount = 0;
				if (bItemService.checkMovement(dto.getSkuNo()) != null)
					moveCount = bItemService.checkMovement(dto.getSkuNo()).intValue();
				if (moveCount != 1) {
					if (!inventoryModel.getMeasurementInfoModel().isMeasuredItem())
						dto.setWeighedItemFl("N");
				}

				if (inventoryModel.getRegionalInfoModel() != null) {
					if (inventoryModel.getRegionalInfoModel().getLcuQty() != null
							&& inventoryModel.getRegionalInfoModel().getLcuQty().doubleValue() > 1)
						dto.setLcuQty(inventoryModel.getRegionalInfoModel().getLcuQty());
					else
						dto.setLcuQty(new BigDecimal(1.00));
				}
			}
			// check once ends

			if (inventoryModel.getMeasurementInfoModel().getUnitMeasured() != null
					&& inventoryModel.getMeasurementInfoModel().getUnitMeasured().doubleValue() > 0)
				dto.setUnitWeight(inventoryModel.getMeasurementInfoModel().getUnitMeasured());

			if (inventoryModel.getMeasurementInfoModel().getUnitCode() != null
					&& inventoryModel.getMeasurementInfoModel().getUnitCode().doubleValue() > 0)
				dto.setUnitCube(inventoryModel.getMeasurementInfoModel().getUnitCode());

			if (StringUtils.isNotBlank(inventoryModel.getMeasurementInfoModel().getTareNumber())) {
				// will check if the entered value is in db table
				String tareNo = inventoryModel.getMeasurementInfoModel().getTareNumber();
				List<TareDetailsResponse> tareList = lovService.getTareDetails();
				if (tareList.stream().filter(obj -> StringUtils.isNotBlank(obj.getTareNo()))
						.anyMatch(o -> o.getTareNo().equalsIgnoreCase(tareNo))) {
					dto.setTareTableNo(tareNo);
				}
			}
		}

		if (inventoryModel.getRegionalInfoModel() != null) {

			if (StringUtils.isNotBlank(inventoryModel.getRegionalInfoModel().getBuyerID())) {
				String buyerId = inventoryModel.getRegionalInfoModel().getBuyerID();
				List<BuyerDetailsResponse> buyers = lovService.getBuyerDetails(tmxGblService.getCurrentSite());

				// initially it threw exception check creating item once
				if (buyers.stream().filter(obj -> StringUtils.isNotBlank(obj.getEmployeeId()))
						.anyMatch(o -> o.getEmployeeId().equalsIgnoreCase(buyerId))) {
					dto.setEmployeeId(buyerId);
				}
			}

			if (inventoryModel.getRegionalInfoModel().getReportCode() != null
					&& inventoryModel.getRegionalInfoModel().getReportCode() > 0) {
				int reportCd = inventoryModel.getRegionalInfoModel().getReportCode();
				List<ReportCodeDetailsResponse> availableReportCd = lovService.getReportCodeDetails();
				if (availableReportCd.stream().filter(obj -> obj.getReportCode() > 0)
						.anyMatch(o -> o.getReportCode() == reportCd))
					dto.setReportCd(reportCd);
			}

			if (StringUtils.isNotBlank(inventoryModel.getRegionalInfoModel().getItemType())) {
				String itemType = inventoryModel.getRegionalInfoModel().getItemType();
				List<InventoryTypeResponse> availableItemTypes = lovService.getInventoryType();
				if (availableItemTypes.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
						.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(itemType))) {
					dto.setInvType(itemType);
					if (!("CRD".equalsIgnoreCase(itemType))) {
						dto.setSubInvFl("N");
					}
				}
			}

			if (inventoryModel.getRegionalInfoModel().isRegionalSupersede())
				dto.setRegionalSupersedeFl("Y");
			else
				dto.setRegionalSupersedeFl("N");

			if (inventoryModel.getRegionalInfoModel().getReplacementSKU() != null
					&& inventoryModel.getRegionalInfoModel().getReplacementSKU() > 0)
				dto.setReplacementSkuNo(inventoryModel.getRegionalInfoModel().getReplacementSKU());
		}

		// how can pack qty be checked at this state of item coz it can have multiple
		// vendor items for same combo of sku and site
//		BigDecimal packQty = null;
//		VendorItem vendorItem = vendorItemService.getVendorItem(skuNo);
//		if (vendorItem != null) {
//			if (null != vendorItem.getPackQty())
//				packQty = vendorItem.getPackQty();
//		}

		if (inventoryModel.getSerialItemsInfoModel() != null) {
//			if(inventoryModel.getSerialItemsInfoModel().isSerializedItem() && packQty != null && packQty.intValue() <= 1)
			if (inventoryModel.getSerialItemsInfoModel().isSerializedItem()) {
				dto.setUseSerialFl("Y");
				dto.setReportUm("EA");
				dto.setCaptureSerialNoFl("Y");
				dto.setNonInventoryFl("N");
			}
		}

		if (inventoryModel.getRegionalInfoModel() != null) {
			if (inventoryModel.getRegionalInfoModel().getLinkSkuNumber() != null) {
				int linkSkuNo = inventoryModel.getRegionalInfoModel().getLinkSkuNumber();
				if (linkSkuNo > 0) {
					validateLinkSku(linkSkuNo);
					if (inventoryModel.getRegionalInfoModel().getLinkSkuNumber() != null && linkSkuNo > 0) {
						dto.setLinkSkuNo(linkSkuNo);
						dto.setUseSerialFl("N");
					}
				}
			}
		}

		if (inventoryModel.getSerialItemsInfoModel() != null) {
			if (inventoryModel.getSerialItemsInfoModel().isAutoGenSerial())
				dto.setAutoGenSerialsFl("Y");
			else
				dto.setAutoGenSerialsFl("N");
		}

		if (inventoryModel.getTenderCertificateTypeInfoModel() != null) {
			if (inventoryModel.getTenderCertificateTypeInfoModel().getTenderCertId() != null
					&& inventoryModel.getTenderCertificateTypeInfoModel().getTenderCertId() > 0) {
				int tenderProgramId = inventoryModel.getTenderCertificateTypeInfoModel().getTenderCertId();
				List<TenderCertificateInfoResponse> tenderPrograms = lovService.getTenderPrograms();
				if (tenderPrograms.stream().filter(obj -> obj.getTenderProgramId() != null)
						.anyMatch(o -> o.getTenderProgramId() == tenderProgramId)) {
					dto.setTenderProgramId(tenderProgramId);
				}
			}
		}

		if (inventoryModel.getLabelInfoModel() != null) {
			if (StringUtils.isNotBlank(inventoryModel.getLabelInfoModel().getEquivUm())) {
				String equivUm = inventoryModel.getLabelInfoModel().getEquivUm();
				List<DomainRefCode> codes = domainService.getDomainCodes(domainUser, "PACK UM CODES");
				if (codes.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
						.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(equivUm))) {
					dto.setEquivUm(inventoryModel.getLabelInfoModel().getEquivUm());
				}
			}

			if (inventoryModel.getLabelInfoModel().isPrivateLabelItem())
				dto.setPrivateBrandFl("Y");
			else
				dto.setPrivateBrandFl("N");

			if (inventoryModel.getLabelInfoModel().getEquivFactor() != null
					&& inventoryModel.getLabelInfoModel().getEquivFactor().doubleValue() > 0)
				dto.setEquivFactor(inventoryModel.getLabelInfoModel().getEquivFactor());

			if (StringUtils.isNotBlank(inventoryModel.getLabelInfoModel().getLabelDescription1()))
				dto.setLabelDesc1(inventoryModel.getLabelInfoModel().getLabelDescription1());

			if (StringUtils.isNotBlank(inventoryModel.getLabelInfoModel().getLabelDescription2()))
				dto.setLabelDesc2(inventoryModel.getLabelInfoModel().getLabelDescription2());
		}

		if (inventoryModel.getInnerPackInfoModel() != null) {
			if (inventoryModel.getInnerPackInfoModel().getInnerPackQty() != null
					&& inventoryModel.getInnerPackInfoModel().getInnerPackQty().doubleValue() > 0)
				dto.setWarehouseInnerPack(inventoryModel.getInnerPackInfoModel().getInnerPackQty());

			if (inventoryModel.getInnerPackInfoModel().getPriceListSeq() != null
					&& inventoryModel.getInnerPackInfoModel().getPriceListSeq() > 0)
				dto.setPriceListSeq(inventoryModel.getInnerPackInfoModel().getPriceListSeq());

			if (inventoryModel.getInnerPackInfoModel().getWeeksHistory() != null
					&& inventoryModel.getInnerPackInfoModel().getWeeksHistory() > 0)
				dto.setWeeksHistory(inventoryModel.getInnerPackInfoModel().getWeeksHistory());

			if (inventoryModel.getInnerPackInfoModel().getPeriodsHistory() != null
					&& inventoryModel.getInnerPackInfoModel().getPeriodsHistory() > 0)
				dto.setPeriodsHistory(inventoryModel.getInnerPackInfoModel().getPeriodsHistory());

			if (inventoryModel.getInnerPackInfoModel().getGlCatID() != null
					&& inventoryModel.getInnerPackInfoModel().getGlCatID() > 0) {
				int glCatId = inventoryModel.getInnerPackInfoModel().getGlCatID();
				List<GLCategoryInfoResponse> glcatList = lovService.getGLCategoryDetails();
				if (glcatList.stream().filter(obj -> obj.getGlCatId() != null)
						.anyMatch(o -> o.getGlCatId() == glCatId)) {
					dto.setGlCatId(glCatId);
				}
			}

			if (inventoryModel.getInnerPackInfoModel().getXrefNumber() != null
					&& inventoryModel.getInnerPackInfoModel().getXrefNumber() > 0) {
				int xrefNo = inventoryModel.getInnerPackInfoModel().getXrefNumber();
				if (lookUpService.getXRef(xrefNo) != null) {
					int xrefNoInDb = lookUpService.getXRef(xrefNo).intValue();
					dto.setXrefNo(xrefNoInDb);
				}
			}
		}

		dto.setDateModified(new Date());
		dto.setChangeDt(new Date());

		if (inventoryModel.getPosFlagsInfoModel() != null) {
			if (inventoryModel.getPosFlagsInfoModel().isCaptureOrderNoFl())
				dto.setCaptureOrderNoFl("Y");
			else
				dto.setCaptureOrderNoFl("N");

			if (inventoryModel.getPosFlagsInfoModel().isCaptureSerialNoFl())
				dto.setCaptureSerialNoFl("Y");
			else
				dto.setCaptureSerialNoFl("N");

			if (lookUpService.getDeleteSkuCount(skuNo) != null) {
				int deleteSku = lookUpService.getDeleteSkuCount(skuNo).intValue();
				if (deleteSku == 0) {
					if (inventoryModel.getPosFlagsInfoModel().isDownloadFl())
						dto.setDownloadFl("Y");
					else
						dto.setDownloadFl("N");
					if (!(inventoryModel.getRegionalInfoModel() != null
							&& inventoryModel.getRegionalInfoModel().getReplacementSKU() != null
							&& inventoryModel.getRegionalInfoModel().getReplacementSKU() > 0)
							&& "N".equalsIgnoreCase(
									tmxGblService.getSiteFlag("INV_ALLOW_REORDER_ON_RPLC_SKU", siteNo))) {
						if (inventoryModel.getGeneralStatusFlagsInfoModel().isReorderFl())
							dto.setReorderFl("Y");
						else
							dto.setReorderFl("N");
					} else
						dto.setReorderFl("N");
				}
			}

			if (inventoryModel.getPosFlagsInfoModel().isNegativePriceFl())
				dto.setNegativePriceFl("Y");
			else
				dto.setNegativePriceFl("N");
		}

		if (inventoryModel.getHazardousInfoModel() != null) {
			if (StringUtils.isNotBlank(inventoryModel.getHazardousInfoModel().getHazClassId()))
				dto.setHazClassId(inventoryModel.getHazardousInfoModel().getHazClassId());

			if (StringUtils.isNotBlank(inventoryModel.getHazardousInfoModel().getHazUnNumberId()))
				dto.setHazUnNumberId(inventoryModel.getHazardousInfoModel().getHazUnNumberId());
		}

		if (inventoryModel.getGeneralStatusFlagsInfoModel() != null) {
			if (inventoryModel.getGeneralStatusFlagsInfoModel().isDiscontinuedFl())
				dto.setDiscontinuedFl("Y");
			else
				dto.setDiscontinuedFl("N");

			if (inventoryModel.getGeneralStatusFlagsInfoModel().isObsoleteFl())
				dto.setObsoleteFl("Y");
			else
				dto.setObsoleteFl("N");
		}

		if (inventoryModel.getItemTypeFlagsInfoModel() != null) {
			// on the basis of kit flag value of the dto set it to Y
			// if item is present in kit hdr then it must be set to Y kit dtl may / may not
			// have item
			BigDecimal count = getKitHdrCount(domainUser, skuNo);
			if (count != null && count.doubleValue() >= 1) {
				dto.setSpecialItemFl("Y");
			} else {
				if (inventoryModel.getItemTypeFlagsInfoModel().isNonRevenueItem())
					dto.setSpecialItemFl("Y");
				else
					dto.setSpecialItemFl("N");
			}

			if (inventoryModel.getItemTypeFlagsInfoModel().isStoreCpnFl())
				dto.setStoreCpnFl("Y");
			else
				dto.setStoreCpnFl("N");

			if (inventoryModel.getItemTypeFlagsInfoModel().isNonInventoryFl())
				dto.setNonInventoryFl("Y");
			else
				dto.setNonInventoryFl("N");

			if (inventoryModel.getItemTypeFlagsInfoModel().isSubInvFl())
				dto.setSubInvFl("Y");
			else
				dto.setSubInvFl("N");

			if (inventoryModel.getItemTypeFlagsInfoModel().isSupplyItemFl())
				dto.setSupplyItemFl("Y");
			else
				dto.setSupplyItemFl("N");

			if (inventoryModel.getItemTypeFlagsInfoModel().isCoreFl())
				dto.setCoreFl("Y");
			else
				dto.setCoreFl("N");

			if (inventoryModel.getItemTypeFlagsInfoModel().isFlexibleItemFl())
				dto.setFlexibleItemFl("Y");
			else
				dto.setFlexibleItemFl("N");
		}

		if (inventoryModel.getPackUpAction() != null && inventoryModel.getPackUpAction() > 0)
			dto.setPackupActionId(inventoryModel.getPackUpAction());

		return dto;
	}

	private void validateInventoryModel(InventoryModel inventoryModel) {
		// TODO Auto-generated method stub
		if (inventoryModel == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Inventory Model"));
		if (inventoryModel.getDescriptionModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Description Model"));
		if (inventoryModel.getGeneralStatusFlagsInfoModel() == null)
			throw new ServiceException(errors.getErrors().get(IErrorConstants.NULL_OBJECT)
					.formatMessage("General Status Flags Info Model"));
		if (inventoryModel.getHazardousInfoModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Hazardous Info Model"));
		if (inventoryModel.getInnerPackInfoModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Inner Pack Info Model"));
		if (inventoryModel.getItemDescription() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Item Description"));
		if (inventoryModel.getItemIdentifiersInfoModel() == null)
			throw new ServiceException(errors.getErrors().get(IErrorConstants.NULL_OBJECT)
					.formatMessage("Item Identifiers Description Info Model"));
		if (inventoryModel.getItemTypeFlagsInfoModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Item Type Flags Info Model"));
		if (inventoryModel.getLabelInfoModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Label Info Model"));
		if (inventoryModel.getMeasurementInfoModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Measurement Info Model"));
		if (inventoryModel.getPosFlagsInfoModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Pos Flags Info Model"));
		if (inventoryModel.getProductModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Product Model"));
		if (inventoryModel.getRegionalInfoModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Regional Info Model"));
		if (inventoryModel.getReportInfoModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Report Info Model"));
		if (inventoryModel.getSerialItemsInfoModel() == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Serial Items Info Model"));
		if (inventoryModel.getTenderCertificateTypeInfoModel() == null)
			throw new ServiceException(errors.getErrors().get(IErrorConstants.NULL_OBJECT)
					.formatMessage("Tender Certificate Type Info Model"));
	}

	// validating Link Sku field
	private void validateLinkSku(int linkSkuNo) {
		// TODO Auto-generated method stub
		SKULinkDetails skuLinkdetails = lookUpService.getSKULinkDetails(linkSkuNo);
		if (skuLinkdetails != null) {
			if (skuLinkdetails.isSerializedItem()) {
				throw new ServiceException(errors.getErrors().get(IErrorConstants.SERIAL_ITEM));
			}
			if (skuLinkdetails.getLinkSkuNumber() != null && skuLinkdetails.getLinkSkuNumber() > 0) {
				throw new ServiceException(errors.getErrors().get(IErrorConstants.LINK_SKU).formatMessage(linkSkuNo));
			}
			if (skuLinkdetails.getDeptNo() != null && skuLinkdetails.getDeptNo() > 0
					&& skuLinkdetails.getLinkSkuNumber() != null
					&& (skuLinkdetails.getDeptNo() == skuLinkdetails.getLinkSkuNumber())) {
				throw new ServiceException(errors.getErrors().get(IErrorConstants.DEPARTMENT_LINK_SKU));
			}
			if (skuLinkdetails.isNonInventory()) {
				throw new ServiceException(errors.getErrors().get(IErrorConstants.NON_INV_LINK));
			}
			if (skuLinkdetails.isSupplyItem()) {
				throw new ServiceException(errors.getErrors().get(IErrorConstants.SUPPLY_LINK));
			}
			if (skuLinkdetails.isMeasuredItem()) {
				throw new ServiceException(errors.getErrors().get(IErrorConstants.WEIGHT_LINK));
			}
			if (skuLinkdetails.getSkuNumber() != null && skuLinkdetails.getLinkSkuNumber() != null
					&& (skuLinkdetails.getSkuNumber() == skuLinkdetails.getLinkSkuNumber())) {
				throw new ServiceException(errors.getErrors().get(IErrorConstants.SELF_LINK));
			}
			if (skuLinkdetails.getQtyOnOrd() != null && skuLinkdetails.getQtyOnHand() != null
					&& skuLinkdetails.getQtyInTransit() != null && skuLinkdetails.getQtyOnHand() != null
					&& skuLinkdetails.getQtyRsvd() != null) {
				if ((skuLinkdetails.getQtyOnOrd().doubleValue() + skuLinkdetails.getQtyOnHand().doubleValue()
						+ skuLinkdetails.getQtyInTransit().doubleValue() > 0)
						|| (skuLinkdetails.getQtyOnHand().doubleValue()
								+ skuLinkdetails.getQtyRsvd().doubleValue() > 0))
					throw new ServiceException(errors.getErrors().get(IErrorConstants.BREAK_LINK));
			}
		}
	}

	// will use below function to check the permissions to create item on current or
	// hq site
	public boolean canUserCreateNewItem(UserIdentity domainUser, Integer skuNo) {
		// TODO Auto-generated method stub
		int siteNo = tmxGblService.getCurrentSite();
		int hqSite = tmxGblService.getHqSite();
		String siteFlag = tmxGblService.getSiteFlag(ITmxGblConstants.LOCAL_SKU, siteNo);
		int nextSkuNumber = tmxGblService.getNum("NEXT_SKU").intValue();
		BigDecimal nextSku = skuPkgService.getNextSku(nextSkuNumber);
		System.out.println("next sku number ==>> " + nextSku);

		if (((hqSite != siteNo)
				&& (!bSecurityService.isUserAllowed(domainUser, siteNo, ISecurityConstants.CREATE_LOCAL_ITEMS)))
				|| ((siteNo != hqSite) && siteFlag.equalsIgnoreCase("N")) || (nextSku.intValue() == skuNo))
			return false;
		else
			return true;
	}

	// authorise items starts here
	// both below functions will be consumed in update inventory but need to check
	// with Venkat about site no and site groups
	// will be used for all the site dtos returned on the basis of the site group id
	// provided
	public boolean isValidSiteNo(UserIdentity domainUser, int skuNumber, int siteNumber) {
		// TODO Auto-generated method stub
		String downloadFl = lookUpService.getDownloadFlag(skuNumber, siteNumber);

		if ("N".equalsIgnoreCase(downloadFl)) {
			boolean flag = bSecurityService.isUserAllowed(domainUser, siteNumber,
					ISecurityConstants.DOWNLOAD_FL_CHANGE);
			if (flag) {
				// if permission retrieved sends true then it means allowed
				return true;// ask ui to alert user with yes or cancel
			} else {
				// if permission retrieved sends false then unauthorized and don't allow user to
				// update
				return false;
			}
		} else
			return true;
	}

	public boolean authoriseItem(UserIdentity domainUser, int skuNumber, int siteNumber) {
		// TODO Auto-generated method stub
		// output unknown will ask Venkat whether its output is boolean or anything else
		// or void
		boolean flag = bSecurityService.isItemAuthorised(domainUser, skuNumber, siteNumber);

		// need to ask why is the call needed before and after as per the JIRA
		List<Integer> listOfSku = lookUpService.getKITCoreItems(skuNumber, siteNumber);

		if (!listOfSku.isEmpty()) {
			for (int sku : listOfSku) {
				String downloadFl = lookUpService.getDownloadFlag(sku, siteNumber);
				if ("N".equalsIgnoreCase(downloadFl)) {
					bSecurityService.isItemAuthorised(domainUser, sku, siteNumber);
					return true;
				}
			}
		}
		return false;
	}

	// authorise items ends here

	public BigDecimal getPromoKeyNo(UserIdentity domainUser, int skuNumber, int siteNumber) {
		// TODO Auto-generated method stub
		return lookUpService.getPromoKeyNo(siteNumber, skuNumber);
	}

	public InvbysitDTO getPromoPricing(UserIdentity domainUser, BigDecimal promoKeyNo, InvbysitDTO invbysitDTO,
			int siteNumber) {
		// TODO Auto-generated method stub
		PromoDetails promoDetails = lookUpService.getPromoPricing(promoKeyNo);
		if (promoDetails != null) {
			invbysitDTO = populatePromoPrices(promoDetails, invbysitDTO, siteNumber);
		}
		return invbysitDTO;
	}

	private InvbysitDTO populatePromoPrices(PromoDetails promoDetails, InvbysitDTO invbysitDTO, int siteNumber) {
		// TODO Auto-generated method stub
		Pricing priceLevels = promoDetails.getPricing();
		PricingQuantity priceQty = promoDetails.getPricingQuantity();
		PriceQuantityPackage qtyPackages = promoDetails.getPriceQuantityPackage();
		PromoStatus promoStatus = promoDetails.getPromoStatus();

		BigDecimal markdownPrice = promoStatus.getMarkdownPact();
		BigDecimal offAmount = promoStatus.getOffAmount();

		if (priceLevels != null) {
			invbysitDTO = getPricingLevels(priceLevels, invbysitDTO);

			if (markdownPrice != null && markdownPrice.doubleValue() > 0) {
				BigDecimal markdownPrice1 = lookUpService.getMarkeddownPrice(siteNumber, priceLevels.getPrice1(),
						markdownPrice, "P");

				if (markdownPrice1 != null && markdownPrice1.doubleValue() > 0) {
					if (offAmount != null && offAmount.doubleValue() > 0)
						invbysitDTO.setPrice1(markdownPrice1.subtract(offAmount));
					else
						invbysitDTO.setPrice1(markdownPrice1);
				}
			}
		}

		if (priceQty != null)
			invbysitDTO = getPricingQuantity(priceQty, invbysitDTO);

		if (qtyPackages != null) {

			invbysitDTO = getPkgPriceAndQuantity(qtyPackages, invbysitDTO);

			if (qtyPackages.getPkgPrice1() != null && qtyPackages.getPkgPrice1().doubleValue() > 0) {

				if (markdownPrice != null && markdownPrice.doubleValue() > 0) {
					BigDecimal markdownPrice1 = lookUpService.getMarkeddownPrice(siteNumber, qtyPackages.getPkgPrice1(),
							markdownPrice, "P");

					if (markdownPrice1 != null && markdownPrice1.doubleValue() > 0) {
						if (offAmount != null && offAmount.doubleValue() > 0)
							invbysitDTO.setPkgPrice1(markdownPrice1.subtract(offAmount));
						else
							invbysitDTO.setPkgPrice1(markdownPrice1);
					}
				} else
					invbysitDTO.setPkgPrice1(qtyPackages.getPkgPrice1());
			}

			if (qtyPackages.getPkgPrice2() != null && qtyPackages.getPkgPrice2().doubleValue() > 0) {
				if (markdownPrice != null && markdownPrice.doubleValue() > 0) {
					BigDecimal markdownPrice1 = lookUpService.getMarkeddownPrice(siteNumber, qtyPackages.getPkgPrice2(),
							markdownPrice, "P");

					if (markdownPrice1 != null && markdownPrice1.doubleValue() > 0) {
						if (offAmount != null && offAmount.doubleValue() > 0)
							invbysitDTO.setPkgPrice2(markdownPrice1.subtract(offAmount));
						else
							invbysitDTO.setPkgPrice2(markdownPrice1);
					}
				} else
					invbysitDTO.setPkgPrice2(qtyPackages.getPkgPrice2());
			}

			if (qtyPackages.getPkgPrice3() != null && qtyPackages.getPkgPrice3().doubleValue() > 0) {
				if (markdownPrice != null && markdownPrice.doubleValue() > 0) {
					BigDecimal markdownPrice1 = lookUpService.getMarkeddownPrice(siteNumber, qtyPackages.getPkgPrice3(),
							markdownPrice, "P");

					if (markdownPrice1 != null && markdownPrice1.doubleValue() > 0) {
						if (offAmount != null && offAmount.doubleValue() > 0)
							invbysitDTO.setPkgPrice3(markdownPrice1.subtract(offAmount));
						else
							invbysitDTO.setPkgPrice3(markdownPrice1);
					}
				} else
					invbysitDTO.setPkgPrice3(qtyPackages.getPkgPrice3());
			}
		}
		return invbysitDTO;
	}

	private InvbysitDTO getPkgPriceAndQuantity(PriceQuantityPackage qtyPackages, InvbysitDTO invbysitDTO) {
		// TODO Auto-generated method stub

		if (qtyPackages != null) {

			if (qtyPackages.getPkgPrice1() != null && qtyPackages.getPkgPrice1().doubleValue() > 0)
				invbysitDTO.setPkgPrice1(qtyPackages.getPkgPrice1());

			if (qtyPackages.getPkgPrice2() != null && qtyPackages.getPkgPrice2().doubleValue() > 0)
				invbysitDTO.setPkgPrice2(qtyPackages.getPkgPrice2());

			if (qtyPackages.getPkgPrice3() != null && qtyPackages.getPkgPrice3().doubleValue() > 0)
				invbysitDTO.setPkgPrice3(qtyPackages.getPkgPrice3());

			if (qtyPackages.getPkgPriceQty1() != null && qtyPackages.getPkgPriceQty1().doubleValue() > 0)
				invbysitDTO.setPkgPriceQty1(qtyPackages.getPkgPriceQty1().intValue());

			if (qtyPackages.getPkgPriceQty2() != null && qtyPackages.getPkgPriceQty2().doubleValue() > 0)
				invbysitDTO.setPkgPriceQty2(qtyPackages.getPkgPriceQty2().intValue());

			if (qtyPackages.getPkgPriceQty3() != null && qtyPackages.getPkgPriceQty3().doubleValue() > 0)
				invbysitDTO.setPkgPriceQty3(qtyPackages.getPkgPriceQty3().intValue());

		}
		return invbysitDTO;
	}

	private InvbysitDTO getPricingQuantity(PricingQuantity priceQty, InvbysitDTO invbysitDTO) {
		// TODO Auto-generated method stub

		if (priceQty != null) {

			if (priceQty.getPriceQty1() != null && priceQty.getPriceQty1().doubleValue() > 0)
				invbysitDTO.setPriceQty1(priceQty.getPriceQty1().intValue());

			if (priceQty.getPriceQty2() != null && priceQty.getPriceQty2().doubleValue() > 0)
				invbysitDTO.setPriceQty2(priceQty.getPriceQty2().intValue());

			if (priceQty.getPriceQty3() != null && priceQty.getPriceQty3().doubleValue() > 0)
				invbysitDTO.setPriceQty3(priceQty.getPriceQty3().intValue());

			if (priceQty.getPriceQty4() != null && priceQty.getPriceQty4().doubleValue() > 0)
				invbysitDTO.setPriceQty4(priceQty.getPriceQty4().intValue());

			if (priceQty.getPriceQty5() != null && priceQty.getPriceQty5().doubleValue() > 0)
				invbysitDTO.setPriceQty5(priceQty.getPriceQty5().intValue());

			if (priceQty.getPriceQty6() != null && priceQty.getPriceQty6().doubleValue() > 0)
				invbysitDTO.setPriceQty6(priceQty.getPriceQty6().intValue());

			if (priceQty.getPriceQty7() != null && priceQty.getPriceQty7().doubleValue() > 0)
				invbysitDTO.setPriceQty7(priceQty.getPriceQty7().intValue());

			if (priceQty.getPriceQty8() != null && priceQty.getPriceQty8().doubleValue() > 0)
				invbysitDTO.setPriceQty8(priceQty.getPriceQty8().intValue());

			if (priceQty.getPriceQty9() != null && priceQty.getPriceQty9().doubleValue() > 0)
				invbysitDTO.setPriceQty9(priceQty.getPriceQty9().intValue());

			if (priceQty.getPriceQty10() != null && priceQty.getPriceQty10().doubleValue() > 0)
				invbysitDTO.setPriceQty10(priceQty.getPriceQty10().intValue());

		}
		return invbysitDTO;
	}

	private InvbysitDTO getPricingLevels(Pricing priceLevels, InvbysitDTO invbysitDTO) {
		// TODO Auto-generated method stub

		if (priceLevels != null) {

			if (priceLevels.getPrice1() != null && priceLevels.getPrice1().doubleValue() > 0)
				invbysitDTO.setPrice1(priceLevels.getPrice1());

			if (priceLevels.getPrice2() != null && priceLevels.getPrice2().doubleValue() > 0)
				invbysitDTO.setPrice2(priceLevels.getPrice2());

			if (priceLevels.getPrice3() != null && priceLevels.getPrice3().doubleValue() > 0)
				invbysitDTO.setPrice3(priceLevels.getPrice3());

			if (priceLevels.getPrice4() != null && priceLevels.getPrice4().doubleValue() > 0)
				invbysitDTO.setPrice4(priceLevels.getPrice4());

			if (priceLevels.getPrice5() != null && priceLevels.getPrice5().doubleValue() > 0)
				invbysitDTO.setPrice5(priceLevels.getPrice5());

			if (priceLevels.getPrice6() != null && priceLevels.getPrice6().doubleValue() > 0)
				invbysitDTO.setPrice6(priceLevels.getPrice6());

			if (priceLevels.getPrice7() != null && priceLevels.getPrice7().doubleValue() > 0)
				invbysitDTO.setPrice7(priceLevels.getPrice7());

			if (priceLevels.getPrice8() != null && priceLevels.getPrice8().doubleValue() > 0)
				invbysitDTO.setPrice8(priceLevels.getPrice8());

			if (priceLevels.getPrice9() != null && priceLevels.getPrice9().doubleValue() > 0)
				invbysitDTO.setPrice9(priceLevels.getPrice9());

			if (priceLevels.getPrice10() != null && priceLevels.getPrice10().doubleValue() > 0)
				invbysitDTO.setPrice10(priceLevels.getPrice10());

		}
		return invbysitDTO;
	}

	public InvbysitDTO getRegularPricing(UserIdentity domainUser, BigDecimal promoKeyNo, InvbysitDTO invbysitDTO,
			int siteNumber, int skuNumber) {
		// TODO Auto-generated method stub
		RegularPricing regularPricing = lookUpService.getRegularPricing(siteNumber, skuNumber);

		if (regularPricing != null) {
			Pricing priceLevels = regularPricing.getPricing();
			PricingQuantity priceQty = regularPricing.getPricingQuantity();
			PriceQuantityPackage qtyPackages = regularPricing.getPriceQuantityPackage();

			if (priceLevels != null)
				invbysitDTO = getPricingLevels(priceLevels, invbysitDTO);

			if (priceQty != null)
				invbysitDTO = getPricingQuantity(priceQty, invbysitDTO);

			if (qtyPackages != null)
				invbysitDTO = getPkgPriceAndQuantity(qtyPackages, invbysitDTO);
		}

		return invbysitDTO;
	}

	public PromoInfo getPromoInfo(UserIdentity domainUser, BigDecimal promoKeyNo) {
		// TODO Auto-generated method stub
		return lookUpService.getPromoInfo(promoKeyNo);
	}

	public BigDecimal getCNTSheetCount(UserIdentity domainUser, int skuNumber) {
		// TODO Auto-generated method stub
		return lookUpService.getCNTSHEETCount(skuNumber);
	}

	public BigDecimal getKitHdrCount(UserIdentity domainUser, int skuNumber) {
		// TODO Auto-generated method stub
		return lookUpService.getKITHDRCount(skuNumber);
	}

	public int saveOrUpdateItemCodes(UserIdentity domainUser, String code1Id, String code2Id, String code3Id,
			int skuNo) {
		// TODO Auto-generated method stub
		return lookUpService.saveOrUpdateItemCodes(domainUser, code1Id, code2Id, code3Id, skuNo);
	}

	public List<CodeDetailsResponse> getCodeDetails(UserDefinedTypeCode typeCode) {
		// TODO Auto-generated method stub
		return lovService.getCodeDetails(typeCode.value());
	}

	public StatusInfoModel validateStatus(StatusInfoModel statusInfoModel, String flexibleFl) {

		boolean flexibleItem;
		if ("Y".equalsIgnoreCase(flexibleFl))
			flexibleItem = true;
		else if ("N".equalsIgnoreCase(flexibleFl))
			flexibleItem = false;
		else
			flexibleItem = false;

		StatusInfoModel statusModel = new StatusInfoModel();

		if (statusInfoModel != null) {
			// restrict sale id
			if (StringUtils.isNotBlank(statusInfoModel.getRestrictSaleId())) {
				String statusId = statusInfoModel.getRestrictSaleId();
				List<StatusDetailsResponse> statuses = lovService.getStatusDetails(ItemStatusTypeCode.RESTRICT.value(),
						flexibleItem);
				if (statuses.stream().filter(obj -> StringUtils.isNotBlank(obj.getStatusId()))
						.anyMatch(o -> o.getStatusId().equalsIgnoreCase(statusId))) {
					statusModel.setRestrictSaleId(statusId);
				} else
					throw new ServiceException(errors.getErrors().get("STATUS_ID").formatMessage(statusId));
			}

			// item status is sku status
			if (StringUtils.isNotBlank(statusInfoModel.getItemStatusId())) {
				String statusId = statusInfoModel.getItemStatusId();
				List<StatusDetailsResponse> statuses = lovService.getStatusDetails(ItemStatusTypeCode.ITEM.value(),
						flexibleItem);
				if (statuses.stream().filter(obj -> StringUtils.isNotBlank(obj.getStatusId()))
						.anyMatch(o -> o.getStatusId().equalsIgnoreCase(statusId))) {
					statusModel.setItemStatusId(statusId);
				} else
					throw new ServiceException(errors.getErrors().get("STATUS_ID").formatMessage(statusId));
			}

			// pos sku status id
			if (StringUtils.isNotBlank(statusInfoModel.getPosSkuStatusId())) {
				String statusId = statusInfoModel.getPosSkuStatusId();
				List<StatusDetailsResponse> statuses = lovService.getStatusDetails(ItemStatusTypeCode.POS.value(),
						flexibleItem);
				if (statuses.stream().filter(obj -> StringUtils.isNotBlank(obj.getStatusId()))
						.anyMatch(o -> o.getStatusId().equalsIgnoreCase(statusId))) {
					statusModel.setPosSkuStatusId(statusId);
				} else
					throw new ServiceException(errors.getErrors().get("STATUS_ID").formatMessage(statusId));
			}

			// itemizer status id
			if (StringUtils.isNotBlank(statusInfoModel.getItemizerStatusId())) {
				String statusId = statusInfoModel.getItemizerStatusId();
				List<StatusDetailsResponse> statuses = lovService.getStatusDetails(ItemStatusTypeCode.ITEMIZER.value(),
						flexibleItem);
				if (statuses.stream().filter(obj -> StringUtils.isNotBlank(obj.getStatusId()))
						.anyMatch(o -> o.getStatusId().equalsIgnoreCase(statusId))) {
					statusModel.setItemizerStatusId(statusId);
				} else
					throw new ServiceException(errors.getErrors().get("STATUS_ID").formatMessage(statusId));
			}
		}
		return statusModel;
	}

	public List<ItemReorderPointsDTO> getItemReorderPointsDTO(int skuNo,
			List<SummaryReorderPoints> summaryReorderPoints) {
		// TODO Auto-generated method stub
		List<ItemReorderPointsDTO> itemReorderPointsDTO = null;
		if (summaryReorderPoints != null && !summaryReorderPoints.isEmpty()) {
			itemReorderPointsDTO = new ArrayList<>();
			for (SummaryReorderPoints summaryReorder : summaryReorderPoints) {
				ItemReorderPointsDTO dto = new ItemReorderPointsDTO(summaryReorder.getSiteNo(), skuNo,
						summaryReorder.getReorderTo(), summaryReorder.getReorderPt());
				itemReorderPointsDTO.add(dto);
			}
		}
		return itemReorderPointsDTO;
	}

	public UpdateInvbysitRequest validateAndMapUpdateInvbysit(UserIdentity domainUser, int skuNo, int siteNo,
			UpdateInvbysitRequest updateInvbysitRequest) {
		UpdateInvbysitRequest response = new UpdateInvbysitRequest();
		InvbysitDTO invbysitDto = inventoryAPI.getInvbysit(domainUser, skuNo, siteNo);

		if (invbysitDto == null)
			throw new ServiceException(errors.getErrors().get("ITEM_NOT_FOUND").formatMessage(siteNo));

		if (updateInvbysitRequest.isAuthorized())
			response.setAuthorized(true);
		else
			response.setAuthorized(false);

		if (updateInvbysitRequest.isDiscontinued()) {
			response.setDiscontinued(true);
		} else
			response.setDiscontinued(false);

		if (updateInvbysitRequest.isDiscountAllowed())
			response.setDiscountAllowed(true);
		else
			response.setDiscountAllowed(false);

		if (updateInvbysitRequest.isPriceChangeAllowed())
			response.setPriceChangeAllowed(true);
		else
			response.setPriceChangeAllowed(false);

		if (updateInvbysitRequest.getAdditionalTax() != null)
			response.setAdditionalTax(updateInvbysitRequest.getAdditionalTax().setScale(2, BigDecimal.ROUND_HALF_UP));
		else
			response.setAdditionalTax(invbysitDto.getAdditionalTax());

		if (updateInvbysitRequest.getCommissionCd() != null) {
			Integer commisionCode = updateInvbysitRequest.getCommissionCd();
			List<CommissionCodeDetailsResponse> codes = lovService.getCommissionCodeDetails();
			if (codes.stream().filter(obj -> obj.getCommissionCode() > 0)
					.anyMatch(o -> o.getCommissionCode() == commisionCode)) {
				response.setCommissionCd(commisionCode);
			}
		} else
			response.setCommissionCd(invbysitDto.getCommissionCd());

		if (StringUtils.isNotBlank(updateInvbysitRequest.getCouponPDVId())) {
			String pdvId = updateInvbysitRequest.getCouponPDVId();
			List<CouponPDVDetails> codes = lovService.getCouponPDVDetails();
			if (codes.stream().filter(obj -> StringUtils.isNotBlank(obj.getCouponPDVId()))
					.anyMatch(o -> o.getCouponPDVId().equalsIgnoreCase(pdvId))) {
				response.setCouponPDVId(pdvId);
			}
		} else
			response.setCouponPDVId(invbysitDto.getPurchaseVolumeId());

		BigDecimal laloGm = null;
		BigDecimal haloGm = null;

		if (updateInvbysitRequest.getLaloGm() != null) {
			laloGm = (updateInvbysitRequest.getLaloGm()).setScale(2, RoundingMode.HALF_UP);
		} else
			laloGm = invbysitDto.getLaloGm();

		if (updateInvbysitRequest.getHaloGm() != null) {
			haloGm = (updateInvbysitRequest.getHaloGm()).setScale(2, RoundingMode.HALF_UP);
		} else
			haloGm = invbysitDto.getHaloGm();

		if (laloGm != null && haloGm != null) {
			// laloGm.compareTo(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP)) < 0
			// ||
			// confirm -ve values once from JIRA
			if (laloGm.compareTo(haloGm) > 0)
				throw new ServiceException(errors.getErrors().get("LALO_GM"));
		}
		response.setLaloGm(laloGm);
		response.setHaloGm(haloGm);

		if (updateInvbysitRequest.getLinkCharges() != null) {
			Integer linkedCharge = updateInvbysitRequest.getLinkCharges().intValue();
			List<LinkChargesDetailsResponse> codes = lovService.getLinkChargesDetails();
			if (codes.stream().filter(obj -> obj.getBottleLinkNo() > 0)
					.anyMatch(o -> o.getBottleLinkNo() == linkedCharge)) {
				response.setLinkCharges(linkedCharge);
			}
		} else
			response.setLinkCharges(invbysitDto.getBottleLinkNo());

		if (StringUtils.isNotBlank(updateInvbysitRequest.getTareTableNo())) {
			String tareNo = updateInvbysitRequest.getTareTableNo();
			List<TareDetailsResponse> tareList = lovService.getTareDetails();
			if (tareList.stream().filter(obj -> StringUtils.isNotBlank(obj.getTareNo()))
					.anyMatch(o -> o.getTareNo().equalsIgnoreCase(tareNo))) {
				response.setTareTableNo(tareNo);
			}
		} else
			response.setTareTableNo(invbysitDto.getTareTableNo());

		if (updateInvbysitRequest.getReplenishmentInfoModel() != null) {

			ReplenishmentInfoModel replenishmentInfoModel = new ReplenishmentInfoModel();

			BigDecimal reorderTo = null;
			BigDecimal reorderPt = null;

			if (updateInvbysitRequest.getReplenishmentInfoModel().getReorderPt() != null) {
				reorderPt = updateInvbysitRequest.getReplenishmentInfoModel().getReorderPt().setScale(2,
						RoundingMode.HALF_UP);
			} else
				reorderPt = invbysitDto.getReorderPt();

			if (updateInvbysitRequest.getReplenishmentInfoModel().getReorderTo() != null) {
				reorderTo = updateInvbysitRequest.getReplenishmentInfoModel().getReorderTo().setScale(2,
						RoundingMode.HALF_UP);
			} else
				reorderTo = invbysitDto.getReorderTo();

			if (reorderPt != null && reorderPt.compareTo(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP)) < 0) {
				reorderPt = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
			}

			if (reorderTo != null) {
				if (reorderPt != null) {
					if (reorderTo.compareTo(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP)) < 0
							|| reorderTo.compareTo(reorderPt) < 0) {
						reorderTo = reorderPt;
					}
				}
			}

			replenishmentInfoModel.setReorderPt(reorderPt);
			replenishmentInfoModel.setReorderTo(reorderTo);

			if (updateInvbysitRequest.getReplenishmentInfoModel().getDefaultDC() != null) {
				Integer defaultDc = updateInvbysitRequest.getReplenishmentInfoModel().getDefaultDC();
				List<DefaultDCDetailsResponse> codes = lovService.getDefaultDCDetails();
				if (codes.stream().filter(obj -> obj.getSiteNo() > 0).anyMatch(o -> o.getSiteNo() == defaultDc)) {
					replenishmentInfoModel.setDefaultDC(defaultDc);
				}
			} else
				replenishmentInfoModel.setDefaultDC(invbysitDto.getDefaultDcSkuLevel());

			if (StringUtils.isNotBlank(updateInvbysitRequest.getReplenishmentInfoModel().getExitVelocityCode())) {
				replenishmentInfoModel
						.setExitVelocityCode(updateInvbysitRequest.getReplenishmentInfoModel().getExitVelocityCode());
			} else
				replenishmentInfoModel.setExitVelocityCode(invbysitDto.getClassCode());

			if (updateInvbysitRequest.getReplenishmentInfoModel().getLastReplenishmentDate() != null)
				replenishmentInfoModel.setLastReplenishmentDate(
						updateInvbysitRequest.getReplenishmentInfoModel().getLastReplenishmentDate());
			else
				replenishmentInfoModel.setLastReplenishmentDate(fromDateToString(invbysitDto.getLastReplenishmentDt()));

			if (updateInvbysitRequest.getReplenishmentInfoModel().getMaxOrderQty() != null)
				replenishmentInfoModel
						.setMaxOrderQty(updateInvbysitRequest.getReplenishmentInfoModel().getMaxOrderQty());
			else
				replenishmentInfoModel.setMaxOrderQty(invbysitDto.getMaxOrderQty());

			if (updateInvbysitRequest.getReplenishmentInfoModel().getWarehouseInnerPack() != null)
				replenishmentInfoModel.setWarehouseInnerPack(
						updateInvbysitRequest.getReplenishmentInfoModel().getWarehouseInnerPack());
			else
				replenishmentInfoModel.setMaxOrderQty(invbysitDto.getWarehouseInnerPack());

			if (updateInvbysitRequest.getReplenishmentInfoModel().getProductStartDate() != null)
				replenishmentInfoModel
						.setProductStartDate(updateInvbysitRequest.getReplenishmentInfoModel().getProductStartDate());
			else
				replenishmentInfoModel.setProductStartDate(fromDateToString(invbysitDto.getProductStartDt()));

			if (updateInvbysitRequest.getReplenishmentInfoModel().getProductEndDate() != null)
				replenishmentInfoModel
						.setProductEndDate(updateInvbysitRequest.getReplenishmentInfoModel().getProductEndDate());
			else
				replenishmentInfoModel.setProductEndDate(fromDateToString(invbysitDto.getProductEndDt()));
			
			validateDateRanges(updateInvbysitRequest.getReplenishmentInfoModel().getProductStartDate(),
					updateInvbysitRequest.getReplenishmentInfoModel().getLastReplenishmentDate(),
					updateInvbysitRequest.getReplenishmentInfoModel().getProductEndDate());

			if (updateInvbysitRequest.getReplenishmentInfoModel().isVelocityCodeLocked())
				replenishmentInfoModel.setVelocityCodeLocked(true);
			else
				replenishmentInfoModel.setVelocityCodeLocked(false);

			if (updateInvbysitRequest.getReplenishmentInfoModel().isAutomaticallyReplenished())
				replenishmentInfoModel.setAutomaticallyReplenished(true);
			else
				replenishmentInfoModel.setAutomaticallyReplenished(false);

			if (updateInvbysitRequest.getReplenishmentInfoModel().isReorderPointsLocked())
				replenishmentInfoModel.setReorderPointsLocked(true);
			else
				replenishmentInfoModel.setReorderPointsLocked(false);

			if (StringUtils.isNotBlank(updateInvbysitRequest.getReplenishmentInfoModel().getVelocityCode())) {
				String velocityCode = updateInvbysitRequest.getReplenishmentInfoModel().getVelocityCode();
				List<DomainRefCode> codes = domainService.getDomainCodes(domainUser, "VELOCITY CODES");
				if (codes.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
						.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(velocityCode))) {
					replenishmentInfoModel.setVelocityCode(velocityCode);
					replenishmentInfoModel.setVelocityCodeLocked(true);
				}
			} else
				replenishmentInfoModel.setVelocityCode(invbysitDto.getVelocityCd());

			response.setReplenishmentInfoModel(replenishmentInfoModel);

		} else {
			response.setReplenishmentInfoModel(getReplenishInfoFromInvbysitDTO(invbysitDto));
		}
		StatusInfoModel statusInfo = new StatusInfoModel();
		if (updateInvbysitRequest.getStatusInfoModel() != null) {
			statusInfo = new StatusInfoModel();
			statusInfo.setItemStatusId(updateInvbysitRequest.getStatusInfoModel().getItemStatusId());
			statusInfo.setRestrictSaleId(updateInvbysitRequest.getStatusInfoModel().getRestrictSaleId());
			statusInfo.setPosSkuStatusId(updateInvbysitRequest.getStatusInfoModel().getPosSkuStatusId());
			statusInfo.setItemizerStatusId(updateInvbysitRequest.getStatusInfoModel().getItemizerStatusId());
		} else {
			statusInfo = getStatusInfoFromInvbysitDTO(invbysitDto);
		}
		response.setStatusInfoModel(statusInfo);
		return response;
	}

	private StatusInfoModel getStatusInfoFromInvbysitDTO(InvbysitDTO invbysitDto) {
		StatusInfoModel statusInfo = new StatusInfoModel();
		statusInfo.setItemStatusId(invbysitDto.getSkuStatusId());
		statusInfo.setRestrictSaleId(invbysitDto.getRestrictSaleId());
		statusInfo.setPosSkuStatusId(invbysitDto.getPosSkuStatusId());
		statusInfo.setItemizerStatusId(invbysitDto.getItemizerStatusId());
		return statusInfo;
	}

	private ReplenishmentInfoModel getReplenishInfoFromInvbysitDTO(InvbysitDTO invbysitDto) {
		ReplenishmentInfoModel replenishInfo = new ReplenishmentInfoModel();

		if ("Y".equalsIgnoreCase(invbysitDto.getReplenishmentFl()))
			replenishInfo.setAutomaticallyReplenished(true);
		else
			replenishInfo.setAutomaticallyReplenished(false);

		replenishInfo.setDefaultDC(invbysitDto.getDefaultDcSkuLevel());
		replenishInfo.setExitVelocityCode(invbysitDto.getClassCode());
		replenishInfo.setLastReplenishmentDate(fromDateToString(invbysitDto.getLastReplenishmentDt()));
		replenishInfo.setMaxOrderQty(invbysitDto.getMaxOrderQty());
		replenishInfo.setProductEndDate(fromDateToString(invbysitDto.getProductEndDt()));
		replenishInfo.setProductStartDate(fromDateToString(invbysitDto.getProductStartDt()));

		if ("Y".equalsIgnoreCase(invbysitDto.getReorderLockedFl()))
			replenishInfo.setReorderPointsLocked(true);
		else
			replenishInfo.setReorderPointsLocked(false);

		replenishInfo.setReorderPt(invbysitDto.getReorderPt());
		replenishInfo.setReorderTo(invbysitDto.getReorderTo());
		replenishInfo.setVelocityCode(invbysitDto.getVelocityCd());

		if ("Y".equalsIgnoreCase(invbysitDto.getVelocityLockedFl()))
			replenishInfo.setVelocityCodeLocked(true);
		else
			replenishInfo.setVelocityCodeLocked(false);

		replenishInfo.setWarehouseInnerPack(invbysitDto.getWarehouseInnerPack());
		return replenishInfo;
	}

	public List<Integer> getSitesFromSiteGroupId(String siteGroupId) {
		return lookUpService.getSitesFromSiteGroupId(siteGroupId);
	}

	public UpdateInvbysitRequest getUpdateInvbysitRequest(ChangeSiteInfoRequest changeSiteInfoRequest) {
		UpdateInvbysitRequest request = new UpdateInvbysitRequest();

		request.setAdditionalTax(changeSiteInfoRequest.getAdditionalTax());
		request.setLinkCharges(changeSiteInfoRequest.getBottleLinkNo());
		request.setCommissionCd(changeSiteInfoRequest.getCommissionCode());
		request.setTareTableNo(changeSiteInfoRequest.getTareNo());

		request.setAuthorized(changeSiteInfoRequest.isAuthorized());
		request.setPriceChangeAllowed(changeSiteInfoRequest.isAllowPriceChange());
		request.setDiscountAllowed(changeSiteInfoRequest.isDiscountAllowed());
		request.setDiscontinued(changeSiteInfoRequest.isDiscontinued());

		ReplenishmentInfoModel replenishmentInfoModel = new ReplenishmentInfoModel();
		replenishmentInfoModel.setDefaultDC(changeSiteInfoRequest.getDefaultDC());
		replenishmentInfoModel.setMaxOrderQty(changeSiteInfoRequest.getMaxOrderQty());
		replenishmentInfoModel.setExitVelocityCode(changeSiteInfoRequest.getExitVelocityCode());
		replenishmentInfoModel.setReorderPt(changeSiteInfoRequest.getReorderPt());
		replenishmentInfoModel.setReorderTo(changeSiteInfoRequest.getReorderTo());
		replenishmentInfoModel.setVelocityCode(changeSiteInfoRequest.getVelocityCode());
		replenishmentInfoModel.setWarehouseInnerPack(changeSiteInfoRequest.getWarehouseInnerPack());

		replenishmentInfoModel.setAutomaticallyReplenished(changeSiteInfoRequest.isAutomaticallyReplenished());
		replenishmentInfoModel.setVelocityCodeLocked(changeSiteInfoRequest.isVelocityCodeLocked());
		replenishmentInfoModel.setReorderPointsLocked(changeSiteInfoRequest.isReorderPointsLocked());

		request.setReplenishmentInfoModel(replenishmentInfoModel);

		request.setStatusInfoModel(changeSiteInfoRequest.getStatusInfoModel());
		return request;
	}

	private String fromDateToString(Date currentDate) {
		String dateString = null;
		if(currentDate != null)
			dateString = DateFormatUtils.format(currentDate, "MM/dd/yy");
		return dateString;
	}

	public Integer getCurrentSite() {
		return tmxGblService.getCurrentSite();
	}

	public void validateDateRanges(String productStartDate, String lastReplenishmentDate, String productEndDate) {

		Date prStartDate = getFormattedDate(productStartDate);
		Date lastRepDate = getFormattedDate(lastReplenishmentDate);
		Date prEndDate = getFormattedDate(productEndDate);

		if (prStartDate == null && (lastRepDate != null || prEndDate != null)) {
			ErrorInfo error = errors.getErrors().get("INVALID_DATE");
			error.setMessage(
					"Product Last Date and Last Replenishment Date cannot have a value if Product Start Date is blank.");
			throw new ServiceException(error);
		}

		if (prStartDate != null && prEndDate != null && prEndDate.before(prStartDate)) {
			ErrorInfo error = errors.getErrors().get("INVALID_DATE");
			error.setMessage("Product End Date cannot be an earlier date than Product Start Date.");
			throw new ServiceException(error);
		}

		if (prStartDate != null && lastRepDate != null && lastRepDate.before(prStartDate)) {
			ErrorInfo error = errors.getErrors().get("INVALID_DATE");
			error.setMessage("Last Replenishment Date cannot be an earlier date than Product Start Date.");
			throw new ServiceException(error);
		}

		if (prStartDate != null && lastRepDate != null && prEndDate != null
				&& (lastRepDate.before(prStartDate) || lastRepDate.after(prEndDate))) {
			ErrorInfo error = errors.getErrors().get("INVALID_DATE");
			error.setMessage("Last Replenishment Date must be a date between Product Start Date and Product End Date.");
			throw new ServiceException(error);
		}
	}

	private Date getFormattedDate(String dateString) {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = null;
		try {
			if (StringUtils.isNotBlank(dateString))
				date = formatter.parse(dateString);
		} catch (ParseException e1) {
			ErrorInfo error = errors.getErrors().get("INVALID_DATE");
			error.setMessage("Please enter date in MM/dd/yy format.");
			throw new ServiceException(error);
		}
		return date;
	}

}
