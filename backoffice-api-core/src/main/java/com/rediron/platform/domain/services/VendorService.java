package com.rediron.platform.domain.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.Errors.ErrorInfo;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.constants.IErrorConstants;
import com.rediron.platform.domain.entities.SupplierItemEntity;
import com.rediron.platform.domain.entities.VendorItemEntity;
import com.rediron.platform.domain.model.request.SuppliersToBeUpdated;
import com.rediron.platform.domain.model.request.VendorItemFieldsToUpdate;
import com.rediron.platform.domain.model.request.VendorItemModel;
import com.rediron.platform.domain.model.request.VendorRequest;
import com.rediron.platform.domain.model.response.DomainRefCode;
import com.rediron.platform.domain.model.response.SupplierDetailsResponse;
import com.rediron.platform.domain.model.response.VendorItemResponse;
import com.rediron.platform.domain.model.response.VendorsResponse;
import com.rediron.platform.domain.repositories.VendorItemRepository;
import com.rediron.platform.domain.repositories.VendorRepository;
import com.rediron.platform.domain.util.MapToResponseUtil;
import com.tomax.api.RNetApiContexts;
import com.tomax.api.UserIdentity;
import com.tomax.invVendorHelper.InventoryVendorDomainHelperAPI;
import com.tomax.vendor.api.VendorAPI;
import com.tomax.vendor.dto.VendorItemDTO;
import com.tomax.vendor.dto.VendorItemDTOId;
import com.tomax.vendor.dto.custom.VenItemFieldsToUpdateDTO;
import com.tomax.vendor.dto.custom.VenItemInfoSearchDTO;

/**
 * @author lovanya.chaudhary
 *
 */
@Service
public class VendorService {

	Logger logger = LoggerFactory.getLogger(VendorService.class);

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private DomainService domainService;

	@Autowired
	private LOVService lovService;
	
	@Autowired
	private LookUpService lookUpService;
	
	@Autowired
	private TmxGblService tmxGblService;

	@Autowired
	private Errors errors;

	@Autowired
	private MapToResponseUtil mapToResponseUtil;
	
	@Autowired
	private VendorItemRepository vendorItemRepository;

	private static InventoryVendorDomainHelperAPI inventoryVendorDomainHelperAPI = RNetApiContexts.hq
			.getStateless(InventoryVendorDomainHelperAPI.class);

	private static VendorAPI vendorAPI = RNetApiContexts.hq.getStateless(VendorAPI.class);

	// verify function

	public BigDecimal getOwnerIdFromVendor(int siteNo) {
		return vendorRepository.getOwnerIdFromVendor(siteNo);
	}

	public List<SupplierDetailsResponse> getAllSuppliersForSku(UserIdentity domainUser, int skuNo) {
		// TODO Auto-generated method stub
		return vendorRepository.getAllSuppliersForSku(domainUser, skuNo);
	}

	public SupplierItemEntity saveSupplier(UserIdentity domainUser, int skuNo, String supplierName) {
		// TODO Auto-generated method stub
		return vendorRepository.saveSupplier(domainUser, skuNo, supplierName);
	}

	public List<VendorItemResponse> getVendorItemInfo(UserIdentity domainUser, String siteGroupId, int skuNo,
			String vendorId) {
		// TODO Auto-generated method stub
		VenItemInfoSearchDTO venItemInfoSearchDTO = new VenItemInfoSearchDTO(skuNo, siteGroupId, null, vendorId);

		List<VendorItemResponse> vendorItems = mapToResponseUtil
				.getVendorItems(vendorAPI.getVendorItemInfo(domainUser, venItemInfoSearchDTO));
		return vendorItems;
	}

	public String createVendorItem(UserIdentity domainUser, VendorRequest vendorRequest, int siteNo, String groupId,
			int skuNo) {
		VendorItemDTO vendorItems = new VendorItemDTO();

		if (vendorRequest == null)
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("Vendor Model"));

		if (vendorRequest.getEoq() != null && vendorRequest.getEoq().doubleValue() > 0)
			vendorItems.setEoq(vendorRequest.getEoq());

		if (vendorRequest.getLeadTime() != null && vendorRequest.getLeadTime() > 0)
			vendorItems.setLeadTime(vendorRequest.getLeadTime());

		if (vendorRequest.getMasterPackQty() != null && vendorRequest.getMasterPackQty().doubleValue() > 0)
			vendorItems.setMasterPackQty(vendorRequest.getMasterPackQty());

		List<DomainRefCode> oaStatusList = domainService.getDomainCodes(domainUser, "ORDER AVAILABILITY");

		if (StringUtils.isNotBlank(vendorRequest.getOrderAvailabilityStatus())) {
			if (!oaStatusList.isEmpty()) {
				String oaStatus = vendorRequest.getOrderAvailabilityStatus();
				if (oaStatusList.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
						.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(oaStatus))) {
					vendorItems.setOrderAvailabilityStatus(oaStatus);
				} else {
					ErrorInfo error = errors.getErrors().get("DOMAIN_CODE_NOT_FOUND");
					error.setMessage("Invalid OA Status code. Please try again or use F9 for list of values.");
					throw new ServiceException(error);
				}
			}
		}
		if (vendorRequest.getPackCost() != null && vendorRequest.getPackCost().doubleValue() > 0)
			vendorItems.setPackCost(vendorRequest.getPackCost());

		if (vendorRequest.getPackCube() != null && vendorRequest.getPackCube().doubleValue() > 0)
			vendorItems.setPackCube(vendorRequest.getPackCube());

		if (vendorRequest.getPackQty() != null && vendorRequest.getPackQty().doubleValue() > 0)
			vendorItems.setPackQty(vendorRequest.getPackQty());

		List<DomainRefCode> packUMList = domainService.getDomainCodes(domainUser, "PACK UM CODES");

		if (StringUtils.isNotBlank(vendorRequest.getPackUm())) {
			if (!packUMList.isEmpty()) {
				String packUM = vendorRequest.getPackUm();
				if (packUMList.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
						.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(packUM))) {
					vendorItems.setPackUm(vendorRequest.getPackUm());
				} else {
					ErrorInfo error = errors.getErrors().get("DOMAIN_CODE_NOT_FOUND");
					error.setMessage("Invalid Pack Um code. Please try again or use F9 for list of values.");
					throw new ServiceException(error);
				}
			}
		}
		if (vendorRequest.getPackWeight() != null && vendorRequest.getPackWeight().doubleValue() > 0)
			vendorItems.setPackWeight(vendorRequest.getPackWeight());

		List<VendorsResponse> vendors = lovService.getSuppliersByTypeCode("S");
		if (StringUtils.isNotBlank(vendorRequest.getVendorId())) {
			if (!vendors.isEmpty()) {
				String vendorId = vendorRequest.getVendorId();
				if (vendors.stream().filter(obj -> StringUtils.isNotBlank(obj.getVendorId()))
						.anyMatch(o -> o.getVendorId().equalsIgnoreCase(vendorId))) {
					throw new ServiceException(errors.getErrors().get("VENDOR_ITEM"));
				} else
					vendorItems.setVendorId(vendorId);
			}
		}

		if (StringUtils.isNotBlank(vendorRequest.getVendorItem()))
			vendorItems.setVendorItem(vendorRequest.getVendorItem());

		if (StringUtils.isNotBlank(vendorRequest.getVendorType()))
			vendorItems.setVenType(vendorRequest.getVendorType());

		if (StringUtils.isNotBlank(vendorRequest.getMainFlag()))
			vendorItems.setMainVendorItemFl(vendorRequest.getMainFlag());

		vendorItems.setSkuNo(skuNo);
		inventoryVendorDomainHelperAPI.createVendorItem(domainUser, vendorItems, siteNo, groupId);
		return "SUCCESS";
	}

	public String updateVendorItem(UserIdentity domainUser, VendorItemFieldsToUpdate vendorItemFieldsToUpdate,
			int copyFromSit, String copyToSitGrp, int skuNo) {

		if (vendorItemFieldsToUpdate == null)
			throw new ServiceException(errors.getErrors().get(IErrorConstants.NULL_OBJECT)
					.formatMessage("VendorItemFieldsToUpdate Model"));

		BigDecimal copyFromPackQty = new BigDecimal(0);
		if (vendorItemFieldsToUpdate.getCopyFromPackQty() != null
				&& vendorItemFieldsToUpdate.getCopyFromPackQty().doubleValue() > 0)
			copyFromPackQty = vendorItemFieldsToUpdate.getCopyFromPackQty();

//		int copyFromSit = 0;
//		if(vendorItemFieldsToUpdate.getCopyFromSit() != null && vendorItemFieldsToUpdate.getCopyFromSit() > 0)
//			copyFromSit = vendorItemFieldsToUpdate.getCopyFromSit();

		String copyFromVendotItem = "";
		if (StringUtils.isNotBlank(vendorItemFieldsToUpdate.getCopyFromVendorItem()))
			copyFromVendotItem = vendorItemFieldsToUpdate.getCopyFromVendorItem();

//		String copyToSitGrp = "";
//		if(StringUtils.isNotBlank(vendorItemFieldsToUpdate.getCopyToSitGrp()))
//			copyToSitGrp = vendorItemFieldsToUpdate.getCopyToSitGrp();

		String vendorId = "";
		if (StringUtils.isNotBlank(vendorItemFieldsToUpdate.getVendorId()))
			vendorId = vendorItemFieldsToUpdate.getVendorId();

//		if(vendorItemFieldsToUpdate.getSkuNo() != null)
//			skuNo = vendorItemFieldsToUpdate.getSkuNo();

		VenItemFieldsToUpdateDTO dto = new VenItemFieldsToUpdateDTO(copyFromSit, copyToSitGrp, copyFromVendotItem,
				copyFromPackQty, vendorId, skuNo);

		if (vendorItemFieldsToUpdate.getEoq() != null && vendorItemFieldsToUpdate.getEoq().doubleValue() > 0)
			dto.setEoq(vendorItemFieldsToUpdate.getEoq());

		if (vendorItemFieldsToUpdate.getLeadTime() != null && vendorItemFieldsToUpdate.getLeadTime() > 0)
			dto.setLeadTime(vendorItemFieldsToUpdate.getLeadTime());

		if (vendorItemFieldsToUpdate.getMain() != null)
			dto.setMain(vendorItemFieldsToUpdate.getMain());
		else
			dto.setMain(false);

		if (vendorItemFieldsToUpdate.getMasterPackQty() != null
				&& vendorItemFieldsToUpdate.getMasterPackQty().doubleValue() > 0)
			dto.setMasterPackQty(vendorItemFieldsToUpdate.getMasterPackQty());

		List<DomainRefCode> oaStatusList = domainService.getDomainCodes(domainUser, "ORDER AVAILABILITY");

		if (StringUtils.isNotBlank(vendorItemFieldsToUpdate.getOaStatus())) {
			if (!oaStatusList.isEmpty()) {
				String oaStatus = vendorItemFieldsToUpdate.getOaStatus();
				if (oaStatusList.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
						.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(oaStatus))) {
					dto.setOaStatus(oaStatus);
				} else {
					ErrorInfo error = errors.getErrors().get("DOMAIN_CODE_NOT_FOUND");
					error.setMessage("Invalid OA Status code. Please try again or use F9 for list of values.");
					throw new ServiceException(error);
				}
			}
		}
		if (vendorItemFieldsToUpdate.getPackCost() != null && vendorItemFieldsToUpdate.getPackCost().doubleValue() > 0)
			dto.setPackCost(vendorItemFieldsToUpdate.getPackCost().setScale(4, RoundingMode.HALF_UP));

		if (vendorItemFieldsToUpdate.getPackQty() != null && vendorItemFieldsToUpdate.getPackQty().doubleValue() > 0)
			dto.setPackQty(vendorItemFieldsToUpdate.getPackQty());

		List<DomainRefCode> packUMList = domainService.getDomainCodes(domainUser, "PACK UM CODES");

		if (StringUtils.isNotBlank(vendorItemFieldsToUpdate.getPackUm())) {
			if (!packUMList.isEmpty()) {
				String packUM = vendorItemFieldsToUpdate.getPackUm();
				if (packUMList.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
						.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(packUM))) {
					dto.setPackUm(vendorItemFieldsToUpdate.getPackUm());
				} else {
					ErrorInfo error = errors.getErrors().get("DOMAIN_CODE_NOT_FOUND");
					error.setMessage("Invalid Pack Um code. Please try again or use F9 for list of values.");
					throw new ServiceException(error);
				}
			}
		}
		if (vendorItemFieldsToUpdate.getPrimary() != null)
			dto.setPrimary(vendorItemFieldsToUpdate.getPrimary());
		else
			dto.setPrimary(false);

		if (StringUtils.isNotBlank(vendorItemFieldsToUpdate.getVendorItem()))
			dto.setVendorItem(vendorItemFieldsToUpdate.getVendorItem());

		if (vendorItemFieldsToUpdate.getUpdatePackQtyFl() != null)
			dto.setUpdatePackQtyFl(vendorItemFieldsToUpdate.getUpdatePackQtyFl());
		else
			dto.setUpdatePackQtyFl(false);

		if (vendorItemFieldsToUpdate.getUpdateMasterPackQtyFl() != null)
			dto.setUpdateMasterPackQtyFl(vendorItemFieldsToUpdate.getUpdateMasterPackQtyFl());
		else
			dto.setUpdateMasterPackQtyFl(false);

		if (vendorItemFieldsToUpdate.getUpdateEoqFl() != null)
			dto.setUpdateEoqFl(vendorItemFieldsToUpdate.getUpdateEoqFl());
		else
			dto.setUpdateEoqFl(false);

		if (vendorItemFieldsToUpdate.getUpdateLeadTimeFl() != null)
			dto.setUpdateLeadTimeFl(vendorItemFieldsToUpdate.getUpdateLeadTimeFl());
		else
			dto.setUpdateLeadTimeFl(false);

		if (vendorItemFieldsToUpdate.getUpdateOaStatusFl() != null)
			dto.setUpdateOaStatusFl(vendorItemFieldsToUpdate.getUpdateOaStatusFl());
		else
			dto.setUpdateOaStatusFl(false);

		if (vendorItemFieldsToUpdate.getUpdateMainFl() != null)
			dto.setUpdateMainFl(vendorItemFieldsToUpdate.getUpdateMainFl());
		else
			dto.setUpdateMainFl(false);

		if (vendorItemFieldsToUpdate.getUpdatePackCostFl() != null)
			dto.setUpdatePackCostFl(vendorItemFieldsToUpdate.getUpdatePackCostFl());
		else
			dto.setUpdatePackCostFl(false);

		if (vendorItemFieldsToUpdate.getUpdatePackUmFl() != null)
			dto.setUpdatePackUmFl(vendorItemFieldsToUpdate.getUpdatePackUmFl());
		else
			dto.setUpdatePackUmFl(false);

		if (vendorItemFieldsToUpdate.getUpdatePrimaryFl() != null)
			dto.setUpdatePrimaryFl(vendorItemFieldsToUpdate.getUpdatePrimaryFl());
		else
			dto.setUpdatePrimaryFl(false);

		if (vendorItemFieldsToUpdate.getUpdateVendorItemFl() != null)
			dto.setUpdateVendorItemFl(vendorItemFieldsToUpdate.getUpdateVendorItemFl());
		else
			dto.setUpdateVendorItemFl(false);
		vendorAPI.updateVendorItem(domainUser, dto);
		return "SUCCESS";
	}

	public String deleteVendorItem(UserIdentity domainUser, BigInteger seqNo) {
		VendorItemDTOId vendorItemId = new VendorItemDTOId();
		vendorItemId.setSeqNo(seqNo);
		vendorAPI.deleteVendorItem(domainUser, vendorItemId);
		return "SUCCESS";
	}

	public List<SupplierDetailsResponse> saveSuppliers(UserIdentity domainUser, int skuNo, List<String> supplierNames) {
		// TODO Auto-generated method stub
		return vendorRepository.saveSuppliers(domainUser, skuNo, supplierNames);
	}

	public List<SupplierDetailsResponse> updateSupplier(UserIdentity domainUser, int skuNo,
			SuppliersToBeUpdated suppliers) {
		// TODO Auto-generated method stub
		return vendorRepository.updateSupplier(domainUser, skuNo, suppliers);
	}

	public List<SupplierDetailsResponse> deleteSupplier(UserIdentity domainUser, int skuNo,
			List<String> supplierNames) {
		// TODO Auto-generated method stub
		return vendorRepository.deleteSupplier(domainUser, skuNo, supplierNames);
	}
	
	public BigDecimal getNextVendorItemSeqNo(UserIdentity domainUser) {
		return vendorRepository.getNextVendorItemSeqNo(domainUser);
	}
	
	public List<VendorItemEntity> getVendorItemsForSkuAndSite(UserIdentity domainUser, int skuNo, int siteNo) {
		return vendorItemRepository.getVendorItemsForSkuAndSite(skuNo, siteNo);
	}

	public VendorItemEntity createVendorItemBySkuAndSite(UserIdentity domainUser, VendorItemModel vendorItemModel, int siteNo, int skuNo) {
		List<DomainRefCode> packUMList = domainService.getDomainCodes(domainUser, "PACK UM CODES");
		List<DomainRefCode> oaStatusList = domainService.getDomainCodes(domainUser, "ORDER AVAILABILITY");
		List<VendorsResponse> vendors = lovService.getSuppliersByTypeCode("S");
		
		if (StringUtils.isNotBlank(vendorItemModel.getVendorId())) {
			if (!CollectionUtils.isEmpty(vendors)) {
				String vendorId = vendorItemModel.getVendorId();
				if (vendors.stream().filter(obj -> StringUtils.isNotBlank(obj.getVendorId()))
						.anyMatch(o -> o.getVendorId().equalsIgnoreCase(vendorId))) {
					ErrorInfo error = errors.getErrors().get("VENDOR_ITEM");
					error.setMessage("This is a Supplier vendor and cannot be added to an item.");
					throw new ServiceException(error);
				}
			}
		}

		if (StringUtils.isNotBlank(vendorItemModel.getOrderAvailabilityStatus())) {
			if (!CollectionUtils.isEmpty(oaStatusList)) {
				String oaStatus = vendorItemModel.getOrderAvailabilityStatus();
				if (!oaStatusList.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
						.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(oaStatus))) {
					ErrorInfo error = errors.getErrors().get("DOMAIN_CODE_NOT_FOUND");
					error.setMessage("Invalid OA Status code. Please try again or use F9 for list of values.");
					throw new ServiceException(error);
				}
			}
		}
		if (StringUtils.isNotBlank(vendorItemModel.getPackUm())) {
			if (!CollectionUtils.isEmpty(packUMList)) {
				String packUM = vendorItemModel.getPackUm();
				if (!packUMList.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
						.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(packUM))) {
					ErrorInfo error = errors.getErrors().get("DOMAIN_CODE_NOT_FOUND");
					error.setMessage("Invalid Pack Um code. Please try again or use F9 for list of values.");
					throw new ServiceException(error);
				}
			}
		}
		
		checkFactor(vendorItemModel.getPackQty(), vendorItemModel.getMasterPackQty());
		return vendorRepository.createVendorItemBySkuAndSite(domainUser, vendorItemModel, siteNo, skuNo);
	}

	public VendorItemEntity updateVendorItemBySeqNo(UserIdentity domainUser, VendorItemModel vendorItemModel, String seqNo) {
		checkFactor(vendorItemModel.getPackQty(), vendorItemModel.getMasterPackQty());
		return vendorRepository.updateVendorItemBySeqNo(domainUser, vendorItemModel, seqNo);
	}
	
	public List<VendorItemEntity> createVendorItemUsingSiteGroup(UserIdentity domainUser, VendorItemModel vendorItemModel, int siteNo, String groupId,
			int skuNo) {
		List<VendorItemEntity> viList = new ArrayList<>();
		List<Integer> siteNos = lookUpService.getSitesFromSiteGroupId(groupId);
		int currentSite = tmxGblService.getCurrentSite();
		checkFactor(vendorItemModel.getPackQty(), vendorItemModel.getMasterPackQty());
		if(!CollectionUtils.containsInstance(siteNos, currentSite)) {
			siteNos.add(currentSite);
		}
		for(int siteNum : siteNos) {
			VendorItemEntity vendorItem = vendorRepository.createVendorItemBySkuAndSite(domainUser, vendorItemModel, siteNum, skuNo);
			viList.add(vendorItem);
		}
		return viList;
	}

	public List<VendorItemEntity> updateAllVendorItemsForSkuAndVendorId(UserIdentity domainUser, VendorItemModel vendorItemModel, int skuNo) {
		checkFactor(vendorItemModel.getPackQty(), vendorItemModel.getMasterPackQty());
		return vendorRepository.updateAllVendorItemsForSkuAndVendorId(domainUser, vendorItemModel, skuNo);
	}
	
	private void checkFactor(BigDecimal packQty, BigDecimal masterPackQty) {
		if(masterPackQty.intValue() % packQty.intValue() != 0) {
			ErrorInfo error = errors.getErrors().get("VENDOR_ITEM");
			error.setMessage("Master Pack Qty must be a multiple of Pack Qty.");
			throw new ServiceException(error);
		}
	}
}
