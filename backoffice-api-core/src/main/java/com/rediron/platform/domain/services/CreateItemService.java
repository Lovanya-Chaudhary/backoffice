package com.rediron.platform.domain.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.Errors.ErrorInfo;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.constants.IErrorConstants;
import com.rediron.platform.domain.constants.ISecurityConstants;
import com.rediron.platform.domain.constants.ITmxGblConstants;
import com.rediron.platform.domain.entities.InvbysitEntity;
import com.rediron.platform.domain.entities.InvtoryEntity;
import com.rediron.platform.domain.entities.ItemupcEntity;
import com.rediron.platform.domain.entities.VendorItemEntity;
import com.rediron.platform.domain.model.request.InvBySiteModel;
import com.rediron.platform.domain.model.request.InventoryModel;
import com.rediron.platform.domain.model.request.ItemUpcModel;
import com.rediron.platform.domain.model.request.VendorItemModel;
import com.rediron.platform.domain.model.response.BuyerDetailsResponse;
import com.rediron.platform.domain.model.response.CouponPDVDetails;
import com.rediron.platform.domain.model.response.DomainRefCode;
import com.rediron.platform.domain.model.response.GLCategoryInfoResponse;
import com.rediron.platform.domain.model.response.InventoryTypeResponse;
import com.rediron.platform.domain.model.response.ReportCodeDetailsResponse;
import com.rediron.platform.domain.model.response.SiteGroupDetails;
import com.rediron.platform.domain.model.response.TareDetailsResponse;
import com.rediron.platform.domain.model.response.TenderCertificateInfoResponse;
import com.rediron.platform.domain.model.response.VendorsResponse;
import com.rediron.platform.domain.repositories.ItemupcRepository;
import com.rediron.platform.domain.response.SKULinkDetails;
import com.rediron.platform.domain.rnet.CreateItemModel;
import com.rediron.platform.domain.rnet.InventoryValidator;
import com.rediron.platform.domain.rnet.InventoryVendorBO;
import com.rediron.security.common.domain.ConfigrecEntity;
import com.rediron.security.common.repository.ConfigrecRepository;
import com.tomax.api.RNetApiContexts;
import com.tomax.api.UserIdentity;
import com.tomax.inventory.api.InventoryAPI;
import com.tomax.inventory.dto.ClassDTO;
import com.tomax.inventory.dto.ClassDTOId;
import com.tomax.inventory.dto.DeptDTO;
import com.tomax.inventory.dto.DeptDTOId;
import com.tomax.inventory.dto.InvbysitDTO;
import com.tomax.inventory.dto.InvtoryDTO;
import com.tomax.inventory.dto.ItemCreationDefaultsHdrDTO;
import com.tomax.inventory.dto.ItemupcDTO;
import com.tomax.inventory.dto.LineDTO;
import com.tomax.inventory.dto.LineDTOId;
import com.tomax.inventory.dto.custom.CreateItemInfoDTO;
import com.tomax.inventory.dto.custom.ItemCreationDefaultsInfoDTO;
import com.tomax.vendor.dto.VendorItemDTO;

@Service
public class CreateItemService {

	private static Logger LOGGER = LoggerFactory.getLogger(CreateItemService.class);

	private static InventoryAPI inventoryAPI = RNetApiContexts.hq.getStateless(InventoryAPI.class);

	@Autowired
	private Errors errors;

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private TmxGblService tmxGblService;

	@Autowired
	private SkuPkgService skuPkgService;

	@Autowired
	private VendorService bVendorService;

	@Autowired
	private ItemService bItemService;

	@Autowired
	private LOVService lovService;

	@Autowired
	private LookUpService lookUpService;

	@Autowired
	private SecurityService bSecurityService;

	@Autowired
	private DomainService domainService;

	@Autowired
	private InventoryVendorBO inventoryVendorBO;

	@Autowired
	private ConfigrecRepository configrecRepository;

	@Autowired
	private ItemupcRepository itemupcRepository;

	public ItemCreationDefaultsInfoDTO getItemCreationDefaultsInfo(UserIdentity domainUser, String itemTypeCd)
			throws InvalidParameterException {
		if (itemTypeCd == null) {
			throw new InvalidParameterException("Item Type Code required");
		}
		ItemCreationDefaultsHdrDTO itemType = getItemCreateDefaultHdr(domainUser, itemTypeCd);

		if (itemType == null) {
			throw new ServiceException(errors.getErrors().get(IErrorConstants.ITEM_TYPE_NOT_FOUND));
		}
		return inventoryAPI.getItemCreationDefaults(domainUser, itemType.getItemCreationId());
	}

	public ItemCreationDefaultsHdrDTO getItemCreateDefaultHdr(UserIdentity domainUser, String itemTypeCode) {
		ItemCreationDefaultsHdrDTO itemType = null;
		List<ItemCreationDefaultsHdrDTO> dtos = inventoryAPI.getItemCreationItemTypes(domainUser);
		for (ItemCreationDefaultsHdrDTO type : dtos) {
			if (type.getItemTypeCd().equals(itemTypeCode)) {
				itemType = type;
				break;
			}
		}
		return itemType;
	}

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

	public int createItemFromDefaults(UserIdentity domainUser, ItemCreationDefaultsInfoDTO createItemDefaultInfoDTO,
			CreateItemModel itemCreateDefaultInfoModel) throws ServiceException {
		if (createItemDefaultInfoDTO == null) {
			throw new InvalidParameterException("Create Item Info required.");
		}
		CreateItemInfoDTO createItemInfoDTO = new CreateItemInfoDTO(createItemDefaultInfoDTO);
		Integer createdSkuNo = 0;
		Integer skuNo = itemCreateDefaultInfoModel.getSkuNo();
		if (canUserCreateNewItem(domainUser, skuNo)) {
			LOGGER.debug(
					"Creating Item for... " + createItemDefaultInfoDTO.getItemCreationDefaultsHdrDTO().getItemTypeCd());
			createdSkuNo = validateAndMapToDTO(domainUser, createItemInfoDTO, itemCreateDefaultInfoModel);
			LOGGER.info("Created Item for... " + createItemInfoDTO.getInvtoryDTO().getDescription() + "  skuNo: "
					+ createdSkuNo);
		} else {
			throw new ServiceException(errors.getErrors().get(IErrorConstants.USER_NOT_ALLOWED));
		}
		return createdSkuNo;
	}

	public int validateAndMapToDTO(UserIdentity domainUser, CreateItemInfoDTO createItemInfoDTO,
			CreateItemModel itemCreateDefaultInfoModel) throws ServiceException {
		LOGGER.debug("Mapping... " + itemCreateDefaultInfoModel.getInventoryModel().getItemDescription());

		InvtoryDTO invtoryDTO = createItemInfoDTO.getInvtoryDTO();
		ItemupcDTO itemupcDTO = createItemInfoDTO.getItemUpcDTO();
		InvbysitDTO invBySitDTO = createItemInfoDTO.getInvBySitDTO();
		VendorItemDTO vendorItemDTO = createItemInfoDTO.getVendorItemDTO();

		createItemInfoDTO.setVendorName(itemCreateDefaultInfoModel.getVendorName());
		createItemInfoDTO.setDeptGroupNo(itemCreateDefaultInfoModel.getDeptGrpNo());

		// inventory dto ends starts
		InventoryModel invModel = itemCreateDefaultInfoModel.getInventoryModel();

		List<VendorItemModel> venItemModels = itemCreateDefaultInfoModel.getListOfVendorItemModel();

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
		// inventory dto ends here
		InvtoryEntity invtory = inventoryVendorBO.mapToInvtoryEntity(invtoryDTO);

		List<SiteGroupDetails> listOfSiteGroups = lovService.getSiteGroupDetails();
		if (StringUtils.isNotBlank(itemCreateDefaultInfoModel.getSiteGroup())) {
			String siteGroupId = itemCreateDefaultInfoModel.getSiteGroup();
			if (!listOfSiteGroups.stream().filter(obj -> StringUtils.isNotBlank(obj.getGroupId()))
					.anyMatch(o -> o.getGroupId().equalsIgnoreCase(siteGroupId))) {
				throw new ServiceException(errors.getErrors().get(IErrorConstants.SITE_GROUP_ID));
			}
		}

		// invbysit starts here
		InvBySiteModel invbysitModel = itemCreateDefaultInfoModel.getInvBySiteModel();

		List<Integer> siteNos = lookUpService.getSitesFromSiteGroupId(itemCreateDefaultInfoModel.getSiteGroup());

		invBySitDTO = validateInvbysitModelNew(domainUser, invBySitDTO, invbysitModel);
		InvbysitEntity invbysitEn = null;// inventoryVendorBO.mapToInvBySitEntity(invBySitDTO);

		List<InvbysitEntity> listOfInvbysit = new ArrayList<InvbysitEntity>();

		// Map Item UPC
		List<ItemUpcModel> itemUpcModels = itemCreateDefaultInfoModel.getListOfItemUpcModel();
		List<ItemupcEntity> listOfItemupcEn = null;
		if (!CollectionUtils.isEmpty(itemUpcModels)) {
			listOfItemupcEn = getItemupcEntityList(domainUser, itemupcDTO, itemUpcModels);
		}
		if (!CollectionUtils.isEmpty(listOfItemupcEn)) {
			// check if more than 1 Primary UPC or any primary PLU / ESL or UCC
			checkItemUpcs(listOfItemupcEn);
			
			List<String> upcIds = new ArrayList<>();
			listOfItemupcEn.forEach(obj -> {
				if(obj.getUpcId() != null)
					upcIds.add(obj.getUpcId());
			});
			
			// check for the duplicate UPC id
			areUPCUnique(upcIds);
		}
		// item upc mapping ends here

		// Map Vendor Item
		List<VendorItemEntity> listOfVendorItemEn = null;
		if (!CollectionUtils.isEmpty(venItemModels)) {
			listOfVendorItemEn = getVendorItemEntityList(domainUser, vendorItemDTO, venItemModels);// new ArrayList<>();
		}
		// vendor items mapping ends here

		// sites for the site group id
		if (!CollectionUtils.containsInstance(siteNos, tmxGblService.getCurrentSite())) {
			siteNos.add(tmxGblService.getCurrentSite());
		}
		for (int siteNo : siteNos) {
			invbysitEn = inventoryVendorBO.mapToInvBySitEntity(invBySitDTO);
			invbysitEn.setSiteNo(siteNo);
			listOfInvbysit.add(invbysitEn);
		}

		List<VendorItemEntity> vendorItemsWithSiteNo = null;
		if (!CollectionUtils.isEmpty(listOfVendorItemEn)) {
			vendorItemsWithSiteNo = new ArrayList<>();
			for (int siteNo : siteNos) {
				for (VendorItemEntity vi : listOfVendorItemEn) {
					LOGGER.info("Site no is : " + siteNo);
					VendorItemEntity vitem = new VendorItemEntity(vi);
					vitem.setSiteNo(new Integer(siteNo));
					vendorItemsWithSiteNo.add(vitem);
				}
			}
		}

		if (!CollectionUtils.isEmpty(vendorItemsWithSiteNo)) {
			inventoryVendorBO.validateVendorItemEntities(domainUser, tmxGblService.getCurrentSite(),
					vendorItemsWithSiteNo);
		}
		inventoryVendorBO.validateInvbysitEntities(domainUser, tmxGblService.getCurrentSite(), listOfInvbysit);

		return inventoryVendorBO.getSkuAndPopulateEntities(domainUser, invtory, listOfInvbysit, listOfItemupcEn,
				vendorItemsWithSiteNo);
	}

	private List<VendorItemEntity> getVendorItemEntityList(UserIdentity domainUser, VendorItemDTO vendorItemDTO,
			List<VendorItemModel> venItemModels) {

		List<VendorItemEntity> listOfVendorItemEn = null;
		if (!CollectionUtils.isEmpty(venItemModels)) {
			listOfVendorItemEn = new ArrayList<>();
			for (VendorItemModel venItemModel : venItemModels) {
				if (venItemModel != null) {

					vendorItemDTO = new VendorItemDTO();

					if (venItemModel.getVendorItem() != null && venItemModel.getVendorItem().length() > 0) {
						vendorItemDTO.setVendorItem(venItemModel.getVendorItem());
					} else {
						vendorItemDTO.setVendorItem("0");
					}
					if (StringUtils.isNotBlank(venItemModel.getVendorId())) {
						validateVendorId(domainUser, venItemModel.getVendorId());
						vendorItemDTO.setVendorId(venItemModel.getVendorId());
					}
					if (venItemModel.getPackUm() != null && venItemModel.getPackUm().length() > 0) {
						validatePackUm(domainUser, venItemModel.getPackUm());
						vendorItemDTO.setPackUm(venItemModel.getPackUm());
					} else {
						vendorItemDTO.setPackUm("CASE");
					}
					if (venItemModel.getPackCost() != null && venItemModel.getPackCost().doubleValue() > 0) {
						vendorItemDTO.setPackCost((getVendorCurrencyFactorByVendorId(venItemModel.getVendorId())
								.multiply(venItemModel.getPackCost())).setScale(4, BigDecimal.ROUND_HALF_UP));
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

					if (venItemModel.getPackQty() != null && venItemModel.getMasterPackQty() != null)
						checkFactor(venItemModel.getPackQty(), venItemModel.getMasterPackQty());

					if (venItemModel.getPackQty() != null && venItemModel.getPackQty().doubleValue() > 0) {
						vendorItemDTO.setPackQty(venItemModel.getPackQty());
//						vendorItemDTO.setMasterPackQty(venItemModel.getPackQty());
					} else {
						vendorItemDTO.setPackQty(new BigDecimal(1));
//						vendorItemDTO.setMasterPackQty(new BigDecimal(1));
					}

					if (venItemModel.getMasterPackQty() != null && venItemModel.getMasterPackQty().doubleValue() > 0)
						vendorItemDTO.setMasterPackQty(venItemModel.getMasterPackQty());
					else
						vendorItemDTO.setMasterPackQty(new BigDecimal(1));

					if (StringUtils.isNotBlank(venItemModel.getOrderAvailabilityStatus())) {
						validateOAStatus(domainUser, venItemModel.getOrderAvailabilityStatus());
						vendorItemDTO.setOrderAvailabilityStatus(venItemModel.getOrderAvailabilityStatus());
						vendorItemDTO.setOrderAvailabilityActiveDt(new Date());
					}

					if (venItemModel.isPrimaryVendor())
						vendorItemDTO.setVenType("P");
					else
						vendorItemDTO.setVenType("S");
					if (venItemModel.isMainVendor())
						vendorItemDTO.setMainVendorItemFl("Y");
					else
						vendorItemDTO.setMainVendorItemFl("N");

					vendorItemDTO.setVendorCurrencyPackCost(venItemModel.getPackCost());
					vendorItemDTO.setOperationType("I");
					vendorItemDTO.setCostFactor(new BigDecimal(1));

					VendorItemEntity vendorEn = inventoryVendorBO.mapToVendorEntity(vendorItemDTO);
//					vendorEn.setSiteNo(tmxGblService.getCurrentSite());
					listOfVendorItemEn.add(vendorEn);
				}
			}
		}
		return listOfVendorItemEn;
	}

	private void validateOAStatus(UserIdentity domainUser, String oaStatus) {
		List<DomainRefCode> oaStatusList = domainService.getDomainCodes(domainUser, "ORDER AVAILABILITY");
		if (StringUtils.isNotBlank(oaStatus)) {
			if (!CollectionUtils.isEmpty(oaStatusList)) {
				if (!oaStatusList.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
						.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(oaStatus))) {
					ErrorInfo error = errors.getErrors().get("DOMAIN_CODE_NOT_FOUND");
					error.setMessage("Invalid OA Status code. Please try again or use F9 for list of values.");
					throw new ServiceException(error);
				}
			}
		}
	}

	private void validatePackUm(UserIdentity domainUser, String packUm) {
		List<DomainRefCode> packUMList = domainService.getDomainCodes(domainUser, "PACK UM CODES");
		if (StringUtils.isNotBlank(packUm)) {
			if (!CollectionUtils.isEmpty(packUMList)) {
				if (!packUMList.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
						.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(packUm))) {
					ErrorInfo error = errors.getErrors().get("DOMAIN_CODE_NOT_FOUND");
					error.setMessage("Invalid Pack Um code. Please try again or use F9 for list of values.");
					throw new ServiceException(error);
				}
			}
		}
	}

	private void validateVendorId(UserIdentity domainUser, String vendorId) {
		List<VendorsResponse> vendors = lovService.getSuppliersByTypeCode("S");
		if (StringUtils.isNotBlank(vendorId)) {
			if (!CollectionUtils.isEmpty(vendors)) {
				if (vendors.stream().filter(obj -> StringUtils.isNotBlank(obj.getVendorId()))
						.anyMatch(o -> o.getVendorId().equalsIgnoreCase(vendorId))) {
					ErrorInfo error = errors.getErrors().get("VENDOR_ITEM");
					error.setMessage("This is a Supplier vendor and cannot be added to an item.");
					throw new ServiceException(error);
				}
			}
		}
	}

	private void checkFactor(BigDecimal packQty, BigDecimal masterPackQty) {
		if (masterPackQty.intValue() % packQty.intValue() != 0) {
			ErrorInfo error = errors.getErrors().get("VENDOR_ITEM");
			error.setMessage("Master Pack Qty must be a multiple of Pack Qty.");
			throw new ServiceException(error);
		}
	}

	public BigDecimal getVendorCurrencyFactorByVendorId(String vendorId) {

		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("p_vendor_id", vendorId);
		BigDecimal currencyFactor = utilityService.getDataFromFunction("tmx", "b_landed_cost", "vendor_currency_factor",
				paramMap, BigDecimal.class, new SqlOutParameter("number", Types.DECIMAL),
				new SqlParameter("p_vendor_id", Types.VARCHAR));

		if (currencyFactor != null)
			LOGGER.info("Currency Factor for the vendor id : " + vendorId + " is : " + currencyFactor);

		return currencyFactor;
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
			LOGGER.error("Please use the specified date format.");
			ErrorInfo error = errors.getErrors().get("INVALID_DATE");
			error.setMessage("Please enter date in MM/dd/yy format.");
			throw new ServiceException(error);
		}
		LOGGER.info("Date param passed : " + date);
		return date;
	}

	private List<ItemupcEntity> getItemupcEntityList(UserIdentity domainUser, ItemupcDTO itemupcDTO,
			List<ItemUpcModel> itemUpcModels) {

		List<ItemupcEntity> listOfItemupcEn = null;
		List<ItemupcEntity> dup = null;
		if (!CollectionUtils.isEmpty(itemUpcModels)) {
			listOfItemupcEn = new ArrayList<>();
			List<Integer> modifiers = null;
			List<ConfigrecEntity> configrecEntityList = new ArrayList<ConfigrecEntity>();
			configrecRepository.findByConfigrecEntityIdConfigName("INVENTOR_FMB_RCPT_DESC_LEN");
			configrecEntityList.add(configrecRepository.findByConfigrecEntityIdConfigName("UPC_STORED_LEN"));
			configrecEntityList.add(configrecRepository.findByConfigrecEntityIdConfigName("NUMERIC_UPC"));
			configrecEntityList.add(configrecRepository.findByConfigrecEntityIdConfigName("MAX_PLU"));

			for (ItemUpcModel itemUpcModel : itemUpcModels) {
				if (itemUpcModel != null) {
					itemupcDTO = new ItemupcDTO();

					if (StringUtils.isNotBlank(itemUpcModel.getTypeCd())) {
						itemupcDTO.setTypeCd(itemUpcModel.getTypeCd());
					}else {
						ErrorInfo error = errors.getErrors().get("ITEM_UPC");
						error.setMessage("Specify UPC type code U / P / E / C.");
						throw new ServiceException(error);
					}
						
					if (StringUtils.isNotBlank(itemUpcModel.getUpcId())
							&& StringUtils.isNumeric(itemUpcModel.getUpcId())) {
						if ("U".equalsIgnoreCase(itemUpcModel.getTypeCd())) {
							int length = itemUpcModel.getUpcId().length();
							if ((length > 5 && length < 9) || (length > 10 && length < 15)) {
								itemupcDTO.setUpcId(getPaddedUPC(domainUser, itemUpcModel.getUpcId()));
							} else
								throw new ServiceException(errors.getErrors().get(IErrorConstants.UPC_LENGTH));
						} else {
							itemupcDTO.setUpcId(itemUpcModel.getUpcId());
						}
					} else {
						ErrorInfo error = errors.getErrors().get("ITEM_UPC");
						error.setMessage("UPC Id can not be null.");
						throw new ServiceException(error);
					}

					if (itemUpcModel.getAdjAmtUPC() != null && itemUpcModel.getAdjAmtUPC().doubleValue() >= 0)
						itemupcDTO.setPriceAdjAmt(itemUpcModel.getAdjAmtUPC());

					if (itemUpcModel.getIsPrimaryUPC())
						itemupcDTO.setUpcSeq(new Integer(1));
					else
						itemupcDTO.setUpcSeq(new Integer(2));
					
					if(itemUpcModel.getTypeCd().equalsIgnoreCase("C")) {
						itemupcDTO.setUcc14Qty(itemUpcModel.getUcc14QtyUPS());
						itemupcDTO.setUcc14Uom(itemUpcModel.getUcc14UmUPS());
					}

					if (itemUpcModel.getModifierUPC() != null && itemUpcModel.getModifierUPC() >= 1
							&& itemUpcModel.getModifierUPC() < 100) {
						if ("U".equalsIgnoreCase(itemUpcModel.getTypeCd())) {
							modifiers = new ArrayList<>();
							dup = itemupcRepository
									.findByIdentifierUpcId(getPaddedUPC(domainUser, itemUpcModel.getUpcId()));
							if (!CollectionUtils.isEmpty(dup)) {
								for (ItemupcEntity entity : dup) {
									modifiers.add(entity.getUpcModifier());
								}
								itemupcDTO.setUpcModifier(Collections.max(modifiers) + 1);
							} else
								itemupcDTO.setUpcModifier(itemUpcModel.getModifierUPC());
						} else {
							modifiers = new ArrayList<>();
							dup = itemupcRepository.findByIdentifierUpcId(itemUpcModel.getUpcId());
							if (!CollectionUtils.isEmpty(dup)) {
								for (ItemupcEntity entity : dup) {
									modifiers.add(entity.getUpcModifier());
								}
								itemupcDTO.setUpcModifier(Collections.max(modifiers) + 1);
							} else
								itemupcDTO.setUpcModifier(itemUpcModel.getModifierUPC());
						}
					} else {
						itemupcDTO.setUpcModifier(1);
					}
					ItemupcEntity itemUPCEn = inventoryVendorBO.mapToItemUpcEntity(itemupcDTO);
					InventoryValidator.validateUPCIDByType(itemUPCEn, configrecEntityList);
					listOfItemupcEn.add(itemUPCEn);
				}
			}
		}
		return listOfItemupcEn;
	}

	private String getPaddedUPC(UserIdentity domainUser, String upcId) {

		if (!StringUtils.isNotBlank(upcId)) {
			ErrorInfo error = errors.getErrors().get("ITEM_UPC");
			error.setMessage("UPC Id can not be null.");
			throw new ServiceException(error);
		}

		if (!StringUtils.isNumeric(upcId)) {
			ErrorInfo error = errors.getErrors().get("ITEM_UPC");
			error.setMessage("UPC Id should be numeric.");
			throw new ServiceException(error);
		}

		upcId = StringUtils.leftPad(upcId,
				(configrecRepository.findByConfigrecEntityIdConfigName("UPC_STORED_LEN").getConfigNo().intValue()),
				'0');
		return upcId;
	}

	public void areUPCUnique(List<String> upcIds) {
		Set<String> upc = new HashSet<>();
		for (String id : upcIds) {
			if (!upc.add(id)) {
				ErrorInfo error = errors.getErrors().get("ITEM_UPC");
				error.setMessage("UPC Id "+id+" is duplicate which is not allowed. Use different UPC Id.");
				throw new ServiceException(error);
			}
		}
	}

	public void checkItemUpcs(List<ItemupcEntity> upcEntityList) {
		int upcPrimarycounter = 0;
		int pluPrimarycounter = 0;
		int eslPrimarycounter = 0;
		int uccPrimarycounter = 0;
		for (ItemupcEntity upc : upcEntityList) {
			if (upc.getTypeCd().equalsIgnoreCase("U") && upc.getUpcSeq() == 1) {
				upcPrimarycounter++;
			}
			if (upc.getTypeCd().equalsIgnoreCase("P") && upc.getUpcSeq() == 1) {
				pluPrimarycounter++;
			}
			if (upc.getTypeCd().equalsIgnoreCase("E") && upc.getUpcSeq() == 1) {
				eslPrimarycounter++;
			}
			if (upc.getTypeCd().equalsIgnoreCase("C") && upc.getUpcSeq() == 1) {
				uccPrimarycounter++;
			}
		}

		LOGGER.info("upcPrimarycounter value is : " + upcPrimarycounter);
		LOGGER.info("pluPrimarycounter value is : " + pluPrimarycounter);
		LOGGER.info("eslPrimarycounter value is : " + eslPrimarycounter);
		LOGGER.info("uccPrimarycounter value is : " + uccPrimarycounter);

		if (upcPrimarycounter == 0 || upcPrimarycounter > 1 || pluPrimarycounter > 0 || eslPrimarycounter > 0
				|| uccPrimarycounter > 0) {
			ErrorInfo error = errors.getErrors().get("ITEM_UPC");
			error.setMessage("Item should have 1 Primary Upc only and no Primary PLU / ESL / UCC.");
			throw new ServiceException(error);
		}
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
				LOGGER.info("Price qty 2 should be greater than Price qty 1");
				ErrorInfo error = errors.getErrors().get(IErrorConstants.PRICE_QTY);
				error.setMessage("Price qty 2 should be greater than Price qty 1");
				throw new ServiceException(error);
			} else {
				if (invbysitModel.getQuantityPricingModel().getPkgPriceQty2() != null
						&& invbysitModel.getQuantityPricingModel().getPkgPriceQty2() > 0)
					invBySitDTO.setPkgPriceQty2(invbysitModel.getQuantityPricingModel().getPkgPriceQty2());
			}
			if ((invbysitModel.getQuantityPricingModel().getPkgPriceQty3() < invbysitModel.getQuantityPricingModel()
					.getPkgPriceQty2()) && (invbysitModel.getQuantityPricingModel().getPkgPriceQty3() != null)
					&& (invbysitModel.getQuantityPricingModel().getPkgPriceQty2() != null)) {
				LOGGER.info("Price qty 3 should be greater than Price qty 2");
				ErrorInfo error = errors.getErrors().get(IErrorConstants.PRICE_QTY);
				error.setMessage("Price qty 3 should be greater than Price qty 2");
				throw new ServiceException(error);
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

		LOGGER.info("mfg code unique ==>> " + mfgValue);
		LOGGER.info("MFG Code count ==>> " + lookUpService.getCount(mfgCode));

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

}
