package com.rediron.platform.domain.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rediron.platform.domain.entities.DomainRefEntity;
import com.rediron.platform.domain.model.response.AttributeNameResponse;
import com.rediron.platform.domain.model.response.ClassDetails;
import com.rediron.platform.domain.model.response.Department;
import com.rediron.platform.domain.model.response.DepartmentGroupResponse;
import com.rediron.platform.domain.model.response.DomainRef;
import com.rediron.platform.domain.model.response.DomainRefCode;
import com.rediron.platform.domain.model.response.GroupsAllResponse;
import com.rediron.platform.domain.model.response.Invbysit;
import com.rediron.platform.domain.model.response.Inventory;
import com.rediron.platform.domain.model.response.InventoryGlobal;
import com.rediron.platform.domain.model.response.InventorySearchResult;
import com.rediron.platform.domain.model.response.ItemAttributeResponse;
import com.rediron.platform.domain.model.response.ItemAttributeResult;
import com.rediron.platform.domain.model.response.ItemAttributeValueResponse;
import com.rediron.platform.domain.model.response.ItemCreationDefaultsHdr;
import com.rediron.platform.domain.model.response.ItemNote;
import com.rediron.platform.domain.model.response.ItemUpcResponse;
import com.rediron.platform.domain.model.response.Line;
import com.rediron.platform.domain.model.response.SiteResponse;
import com.rediron.platform.domain.model.response.SkuInfoResponse;
import com.rediron.platform.domain.model.response.StatusClassHdr;
import com.rediron.platform.domain.model.response.UpdatedAttributeResponse;
import com.rediron.platform.domain.model.response.VendorItemResponse;
import com.tomax.config.attribute.dto.AttributeListValueAllDTO;
import com.tomax.config.attribute.dto.AttributeNameDTO;
import com.tomax.config.attribute.dto.ItemAttributeValueDTO;
import com.tomax.config.domainRef.dto.DomainRefCodeDTO;
import com.tomax.inventory.dto.ClassDTO;
import com.tomax.inventory.dto.DeptDTO;
import com.tomax.inventory.dto.DeptgrpDTO;
import com.tomax.inventory.dto.InvbysitDTO;
import com.tomax.inventory.dto.InvtoryDTO;
import com.tomax.inventory.dto.ItemCreationDefaultsHdrDTO;
import com.tomax.inventory.dto.ItemNoteDTO;
import com.tomax.inventory.dto.ItemupcDTO;
import com.tomax.inventory.dto.LineDTO;
import com.tomax.inventory.dto.StatusClassHdrDTO;
import com.tomax.inventory.dto.custom.InventoryGlobalInfoDTO;
import com.tomax.inventory.dto.custom.InventorySearchResultsDTO;
import com.tomax.inventory.dto.custom.ItemAttributeResultDTO;
import com.tomax.inventory.dto.custom.ItemNoteResultWithDomainRefDTO;
import com.tomax.site.dto.GroupsAllDTO;
import com.tomax.site.dto.SiteDTO;
import com.tomax.vendor.dto.custom.VendorItemInfoDTO;

/**
 * @author lovanya.chaudhary
 *
 */

@Component
public class MapToResponseUtil {

	Logger logger = LoggerFactory.getLogger(MapToResponseUtil.class);

	public ItemCreationDefaultsHdr getItemCreationDefaultsHdr(ItemCreationDefaultsHdrDTO itemType) {
		// TODO Auto-generated method stub
		ItemCreationDefaultsHdr itemCreationDefaultsHdr = null;

		if (itemType != null) {

			itemCreationDefaultsHdr = new ItemCreationDefaultsHdr();

			if (StringUtils.isNotBlank(itemType.getActiveFl()))
				itemCreationDefaultsHdr.setActiveFl(itemType.getActiveFl());

			if (StringUtils.isNotBlank(itemType.getAllowAttributesFl()))
				itemCreationDefaultsHdr.setAllowAttributesFl(itemType.getAllowAttributesFl());

			if (itemType.getDateCreated() != null)
				itemCreationDefaultsHdr.setDateCreated(itemType.getDateCreated());

			if (itemType.getDateModified() != null)
				itemCreationDefaultsHdr.setDateModified(itemType.getDateModified());

			if (StringUtils.isNotBlank(itemType.getDisplayFl()))
				itemCreationDefaultsHdr.setDisplayFl(itemType.getDisplayFl());

			if (StringUtils.isNotBlank(itemType.getDisplayLabel()))
				itemCreationDefaultsHdr.setDisplayLabel(itemType.getDisplayLabel());

			if (StringUtils.isNotBlank(itemType.getFunctionCd()))
				itemCreationDefaultsHdr.setFunctionCd(itemType.getFunctionCd());

			if (StringUtils.isNotBlank(itemType.getHintText()))
				itemCreationDefaultsHdr.setHintText(itemType.getHintText());

			if (itemType.getItemCreationId() != null)
				itemCreationDefaultsHdr.setItemCreationId(itemType.getItemCreationId());

			if (StringUtils.isNotBlank(itemType.getItemTypeCd()))
				itemCreationDefaultsHdr.setItemTypeCd(itemType.getItemTypeCd());

			if (StringUtils.isNotBlank(itemType.getUserCreated()))
				itemCreationDefaultsHdr.setUserCreated(itemType.getUserCreated());

			if (StringUtils.isNotBlank(itemType.getUserModified()))
				itemCreationDefaultsHdr.setUserModified(itemType.getUserModified());

		}
		return itemCreationDefaultsHdr;
	}

	public List<InventorySearchResult> getInventorySearchResultList(
			List<InventorySearchResultsDTO> inventorySearchResults) {
		// TODO Auto-generated method stub
		List<InventorySearchResult> list = null;
		if (!inventorySearchResults.isEmpty()) {
			list = new ArrayList<>();

			for (InventorySearchResultsDTO dto : inventorySearchResults) {

				InventorySearchResult result = new InventorySearchResult();

				if (StringUtils.isNotBlank(dto.getAttribute1Value()))
					result.setAttribute1Value(dto.getAttribute1Value());

				if (StringUtils.isNotBlank(dto.getAttribute2Value()))
					result.setAttribute2Value(dto.getAttribute2Value());

				if (StringUtils.isNotBlank(dto.getAttribute3Value()))
					result.setAttribute3Value(dto.getAttribute3Value());

				if (dto.getClassNo() != null)
					result.setClassNo(dto.getClassNo());

				if (StringUtils.isNotBlank(dto.getCode1Id()))
					result.setCode1Id(dto.getCode1Id());

				if (StringUtils.isNotBlank(dto.getCode2Id()))
					result.setCode2Id(dto.getCode2Id());

				if (StringUtils.isNotBlank(dto.getCode3Id()))
					result.setCode3Id(dto.getCode3Id());

				if (dto.getDeptgrpNo() != null)
					result.setDeptgrpNo(dto.getDeptgrpNo());

				if (dto.getDeptNo() != null)
					result.setDeptNo(dto.getDeptNo());

				if (StringUtils.isNotBlank(dto.getItemDescription()))
					result.setItemDescription(dto.getItemDescription());

				if (dto.getLineNo() != null)
					result.setLineNo(dto.getLineNo());

				if (dto.getLinkSkuNo() != null)
					result.setLinkSkuNo(dto.getLinkSkuNo());

				if (StringUtils.isNotBlank(dto.getLookupDesc()))
					result.setLookupDesc(dto.getLookupDesc());

				result.setMasterSku(dto.isMasterSku());

				if (StringUtils.isNotBlank(dto.getMfgCd()))
					result.setMfgCd(dto.getMfgCd());

				if (StringUtils.isNotBlank(dto.getPkgDesc()))
					result.setPkgDesc(dto.getPkgDesc());

				if (StringUtils.isNotBlank(dto.getReceiptDesc()))
					result.setReceiptDesc(dto.getReceiptDesc());

				if (dto.getReportFactor() != null)
					result.setReportFactor(dto.getReportFactor());

				if (StringUtils.isNotBlank(dto.getReportUm()))
					result.setReportUm(dto.getReportUm());

				if (StringUtils.isNotBlank(dto.getSellUm()))
					result.setSellUm(dto.getSellUm());

				if (dto.getSkuNo() != null)
					result.setSkuNo(dto.getSkuNo());

				if (StringUtils.isNotBlank(dto.getUpcId()))
					result.setUpcId(dto.getUpcId());

				if (StringUtils.isNotBlank(dto.getVendorId()))
					result.setVendorId(dto.getVendorId());

				if (StringUtils.isNotBlank(dto.getVendorItem()))
					result.setVendorItem(dto.getVendorItem());

				list.add(result);
			}
		}
		return list;
	}

	public List<ItemCreationDefaultsHdr> getItemCreationDefaultsHdrList(
			List<ItemCreationDefaultsHdrDTO> itemCreationItemTypes) {
		// TODO Auto-generated method stub

		List<ItemCreationDefaultsHdr> list = null;

		if (!itemCreationItemTypes.isEmpty()) {
			list = new ArrayList<>();
			for (ItemCreationDefaultsHdrDTO itemType : itemCreationItemTypes) {
				ItemCreationDefaultsHdr itemCreationDefaultsHdr = new ItemCreationDefaultsHdr();

				if (StringUtils.isNotBlank(itemType.getActiveFl()))
					itemCreationDefaultsHdr.setActiveFl(itemType.getActiveFl());

				if (StringUtils.isNotBlank(itemType.getAllowAttributesFl()))
					itemCreationDefaultsHdr.setAllowAttributesFl(itemType.getAllowAttributesFl());

				if (itemType.getDateCreated() != null)
					itemCreationDefaultsHdr.setDateCreated(itemType.getDateCreated());

				if (itemType.getDateModified() != null)
					itemCreationDefaultsHdr.setDateModified(itemType.getDateModified());

				if (StringUtils.isNotBlank(itemType.getDisplayFl()))
					itemCreationDefaultsHdr.setDisplayFl(itemType.getDisplayFl());

				if (StringUtils.isNotBlank(itemType.getDisplayLabel()))
					itemCreationDefaultsHdr.setDisplayLabel(itemType.getDisplayLabel());

				if (StringUtils.isNotBlank(itemType.getFunctionCd()))
					itemCreationDefaultsHdr.setFunctionCd(itemType.getFunctionCd());

				if (StringUtils.isNotBlank(itemType.getHintText()))
					itemCreationDefaultsHdr.setHintText(itemType.getHintText());

				if (itemType.getItemCreationId() != null)
					itemCreationDefaultsHdr.setItemCreationId(itemType.getItemCreationId());

				if (StringUtils.isNotBlank(itemType.getItemTypeCd()))
					itemCreationDefaultsHdr.setItemTypeCd(itemType.getItemTypeCd());

				if (StringUtils.isNotBlank(itemType.getUserCreated()))
					itemCreationDefaultsHdr.setUserCreated(itemType.getUserCreated());

				if (StringUtils.isNotBlank(itemType.getUserModified()))
					itemCreationDefaultsHdr.setUserModified(itemType.getUserModified());

				list.add(itemCreationDefaultsHdr);
			}
		}
		return list;
	}

	public StatusClassHdr getStatusClassHdr(StatusClassHdrDTO statusClassHdrDTO) {
		// TODO Auto-generated method stub
		StatusClassHdr statusClassHdr = null;
		if (statusClassHdrDTO != null) {
			statusClassHdr = new StatusClassHdr();

			if (statusClassHdrDTO.getCwStatusValue() != null)
				statusClassHdr.setCwStatusValue(statusClassHdrDTO.getCwStatusValue());

			if (statusClassHdrDTO.getDateChanged() != null)
				statusClassHdr.setDateChanged(statusClassHdrDTO.getDateChanged());

			if (statusClassHdrDTO.getDateCreated() != null)
				statusClassHdr.setDateCreated(statusClassHdrDTO.getDateCreated());

			if (statusClassHdrDTO.getDateModified() != null)
				statusClassHdr.setDateModified(statusClassHdrDTO.getDateModified());

			if (StringUtils.isNotBlank(statusClassHdrDTO.getDescription()))
				statusClassHdr.setDescription(statusClassHdrDTO.getDescription());

			if (statusClassHdrDTO.getOldStatusValue() != null)
				statusClassHdr.setOldStatusValue(statusClassHdrDTO.getOldStatusValue());

			if (StringUtils.isNotBlank(statusClassHdrDTO.getOptStatusValue()))
				statusClassHdr.setOptStatusValue(statusClassHdrDTO.getOptStatusValue());

			if (statusClassHdrDTO.getProgdefNo() != null)
				statusClassHdr.setProgdefNo(statusClassHdrDTO.getProgdefNo());

			if (StringUtils.isNotBlank(statusClassHdrDTO.getStatusId()))
				statusClassHdr.setStatusId(statusClassHdrDTO.getStatusId());

			if (StringUtils.isNotBlank(statusClassHdrDTO.getStatusType()))
				statusClassHdr.setStatusType(statusClassHdrDTO.getStatusType());

			if (StringUtils.isNotBlank(statusClassHdrDTO.getUserCreated()))
				statusClassHdr.setUserCreated(statusClassHdrDTO.getUserCreated());

			if (StringUtils.isNotBlank(statusClassHdrDTO.getUserModified()))
				statusClassHdr.setUserModified(statusClassHdrDTO.getUserModified());

		}
		return statusClassHdr;
	}

	public List<DomainRefCode> getDomainRefCodes(List<DomainRefCodeDTO> domainRefCodes) {
		// TODO Auto-generated method stub
		List<DomainRefCode> list = null;
		if (domainRefCodes != null && !domainRefCodes.isEmpty()) {
			list = new ArrayList<>();
			for (DomainRefCodeDTO dto : domainRefCodes) {
				DomainRefCode domainCode = new DomainRefCode();

				if (dto.getDomainRefId() != null)
					domainCode.setDomainRefId(dto.getDomainRefId());

				if (StringUtils.isNotBlank(dto.getCodeValue()))
					domainCode.setCodeValue(dto.getCodeValue());

				if (StringUtils.isNotBlank(dto.getCodeMeaning()))
					domainCode.setCodeMeaning(dto.getCodeMeaning());

				list.add(domainCode);
			}
		}
		return list;
	}

	public List<ItemAttributeResult> getItemAttributeResultList(List<ItemAttributeResultDTO> itemAttributeBySku) {
		// TODO Auto-generated method stub
		List<ItemAttributeResult> list = null;
		if (!itemAttributeBySku.isEmpty()) {
			list = new ArrayList<>();
			for (ItemAttributeResultDTO dto : itemAttributeBySku) {
				ItemAttributeResult itemAttribute = new ItemAttributeResult();

				if (StringUtils.isNotBlank(dto.getAttributeDescription()))
					itemAttribute.setAttributeDescription(dto.getAttributeDescription());

				if (dto.getAttributeId() != null)
					itemAttribute.setAttributeId(dto.getAttributeId());

				if (StringUtils.isNotBlank(dto.getAttributeName()))
					itemAttribute.setAttributeName(dto.getAttributeName());

				if (StringUtils.isNotBlank(dto.getAttributeValue()))
					itemAttribute.setAttributeValue(dto.getAttributeValue());

				if (StringUtils.isNotBlank(dto.getLovFl()))
					itemAttribute.setLovFl(dto.getLovFl());

				if (dto.getSkuNo() != null)
					itemAttribute.setSkuNo(dto.getSkuNo());

				list.add(itemAttribute);
			}
		}
		return list;
	}

	public List<ItemUpcResponse> getItemUpcList(List<ItemupcDTO> itemUpcDtos) {
		// TODO Auto-generated method stub
		List<ItemUpcResponse> list = null;
		if (!itemUpcDtos.isEmpty()) {
			list = new ArrayList<>();
			for (ItemupcDTO dto : itemUpcDtos) {
				ItemUpcResponse itemUpc = new ItemUpcResponse();

				if (dto.getPriceAdjAmt() != null)
					itemUpc.setPriceAdjAmt(dto.getPriceAdjAmt());

				if (dto.getSkuNo() != null)
					itemUpc.setSkuNo(dto.getSkuNo());

				if (StringUtils.isNotBlank(dto.getTypeCd()))
					itemUpc.setTypeCd(dto.getTypeCd());

				if (dto.getUcc14Qty() != null)
					itemUpc.setUcc14Qty(dto.getUcc14Qty());

				if (StringUtils.isNotBlank(dto.getUcc14Uom()))
					itemUpc.setUcc14Uom(dto.getUcc14Uom());

				if (StringUtils.isNotBlank(dto.getUpcId()))
					itemUpc.setUpcId(dto.getUpcId());

				if (dto.getUpcModifier() != null)
					itemUpc.setUpcModifier(dto.getUpcModifier());

				if (dto.getUpcSeq() != null)
					itemUpc.setUpcSeq(dto.getUpcSeq());

				list.add(itemUpc);
			}
		}
		return list;
	}

	public InventoryGlobal getInventoryGlobalInfo(InventoryGlobalInfoDTO globalDTO) {
		// TODO Auto-generated method stub
		InventoryGlobal inventoryGlobal = null;
		if (globalDTO != null) {
			inventoryGlobal = new InventoryGlobal();

			if (globalDTO.getCertificateId() != null)
				inventoryGlobal.setCertificateId(globalDTO.getCertificateId());

			if (StringUtils.isNotBlank(globalDTO.getClassDesc()))
				inventoryGlobal.setClassDesc(globalDTO.getClassDesc());

			if (globalDTO.getDailyReceivingPresent() != null)
				inventoryGlobal.setDailyReceivingPresent(globalDTO.getDailyReceivingPresent());

			if (globalDTO.getDailySalesPresent() != null)
				inventoryGlobal.setDailySalesPresent(globalDTO.getDailySalesPresent());

			if (StringUtils.isNotBlank(globalDTO.getDeptDesc()))
				inventoryGlobal.setDeptDesc(globalDTO.getDeptDesc());

			if (StringUtils.isNotBlank(globalDTO.getDeptGrpDesc()))
				inventoryGlobal.setDeptGrpDesc(globalDTO.getDeptGrpDesc());

			if (globalDTO.getDeptGrpNo() != null)
				inventoryGlobal.setDeptGrpNo(globalDTO.getDeptGrpNo());

			if (StringUtils.isNotBlank(globalDTO.getGlCatIdDesc()))
				inventoryGlobal.setGlCatIdDesc(globalDTO.getGlCatIdDesc());

			if (globalDTO.getGlobalSuperSede() != null)
				inventoryGlobal.setGlobalSuperSede(globalDTO.getGlobalSuperSede());

			if (globalDTO.hasMultipleVendorItems() != null)
				inventoryGlobal.setHasMultipleVendorItems(globalDTO.hasMultipleVendorItems());

			if (globalDTO.getKitComponentFl() != null)
				inventoryGlobal.setKitComponentFl(globalDTO.getKitComponentFl());

			if (globalDTO.getKitHdrFl() != null)
				inventoryGlobal.setKitHdrFl(globalDTO.getKitHdrFl());

			if (globalDTO.getKitSkuNo() != null)
				inventoryGlobal.setKitSkuNo(globalDTO.getKitSkuNo());

			if (StringUtils.isNotBlank(globalDTO.getKitTypeCd()))
				inventoryGlobal.setKitTypeCd(globalDTO.getKitTypeCd());

			if (StringUtils.isNotBlank(globalDTO.getLineDesc()))
				inventoryGlobal.setLineDesc(globalDTO.getLineDesc());

			if (globalDTO.getPoDetailPresent() != null)
				inventoryGlobal.setPoDetailPresent(globalDTO.getPoDetailPresent());

			if (globalDTO.getReasonCd() != null)
				inventoryGlobal.setReasonCd(globalDTO.getReasonCd());

			if (globalDTO.getRegionalSuperSede() != null)
				inventoryGlobal.setRegionalSuperSede(globalDTO.getRegionalSuperSede());

			if (StringUtils.isNotBlank(globalDTO.getUpcId()))
				inventoryGlobal.setUpcId(globalDTO.getUpcId());

			if (StringUtils.isNotBlank(globalDTO.getVendorItem()))
				inventoryGlobal.setVendorItem(globalDTO.getVendorItem());

			if (globalDTO != null) {
				inventoryGlobal.setInventory(getInventory(globalDTO.getInvtoryDto()));
			}

		}
		return inventoryGlobal;
	}

	public Inventory getInventory(InvtoryDTO invtoryDto) {
		// TODO Auto-generated method stub
		Inventory inventory = null;
		if (invtoryDto != null) {

			inventory = new Inventory();

			if (invtoryDto.getAttribute1Id() != null)
				inventory.setAttribute1Id(invtoryDto.getAttribute1Id());

			if (StringUtils.isNotBlank(invtoryDto.getAttribute1Value()))
				inventory.setAttribute1Value(invtoryDto.getAttribute1Value());

			if (invtoryDto.getAttribute2Id() != null)
				inventory.setAttribute2Id(invtoryDto.getAttribute2Id());

			if (StringUtils.isNotBlank(invtoryDto.getAttribute2Value()))
				inventory.setAttribute2Value(invtoryDto.getAttribute2Value());

			if (invtoryDto.getAttribute3Id() != null)
				inventory.setAttribute3Id(invtoryDto.getAttribute3Id());

			if (StringUtils.isNotBlank(invtoryDto.getAttribute3Value()))
				inventory.setAttribute3Value(invtoryDto.getAttribute3Value());

			if (invtoryDto.getAutoGenSerialsFl() != null)
				inventory.setAutoGenSerialsFl(invtoryDto.getAutoGenSerialsFl());

			if (invtoryDto.getAvailSaleDt() != null)
				inventory.setAvailSaleDt(invtoryDto.getAvailSaleDt());

			if (invtoryDto.getCaptureOrderNoFl() != null)
				inventory.setCaptureOrderNoFl(invtoryDto.getCaptureOrderNoFl());

			if (invtoryDto.getCaptureSerialNoFl() != null)
				inventory.setCaptureSerialNoFl(invtoryDto.getCaptureSerialNoFl());

			if (invtoryDto.getCatchWeightFl() != null)
				inventory.setCatchWeightFl(invtoryDto.getCatchWeightFl());

			if (invtoryDto.getChangeDt() != null)
				inventory.setChangeDt(invtoryDto.getChangeDt());

			if (invtoryDto.getChangeLabelDt() != null)
				inventory.setChangeLabelDt(invtoryDto.getChangeLabelDt());

			if (invtoryDto.getClassNo() != null)
				inventory.setClassNo(invtoryDto.getClassNo());

			if (StringUtils.isNotBlank(invtoryDto.getCode1Id()))
				inventory.setCode1Id(invtoryDto.getCode1Id());

			if (StringUtils.isNotBlank(invtoryDto.getCode2Id()))
				inventory.setCode2Id(invtoryDto.getCode2Id());

			if (StringUtils.isNotBlank(invtoryDto.getCode3Id()))
				inventory.setCode3Id(invtoryDto.getCode3Id());

			if (StringUtils.isNotBlank(invtoryDto.getCoreFl()))
				inventory.setCoreFl(invtoryDto.getCoreFl());

			if (invtoryDto.getDateCreated() != null)
				inventory.setDateCreated(invtoryDto.getDateCreated());

			if (invtoryDto.getDateModified() != null)
				inventory.setDateModified(invtoryDto.getDateModified());

			if (invtoryDto.getDeptNo() != null)
				inventory.setDeptNo(invtoryDto.getDeptNo());

			if (StringUtils.isNotBlank(invtoryDto.getDescription()))
				inventory.setDescription(invtoryDto.getDescription());

			if (StringUtils.isNotBlank(invtoryDto.getDiscontinuedFl()))
				inventory.setDiscontinuedFl(invtoryDto.getDiscontinuedFl());

			if (StringUtils.isNotBlank(invtoryDto.getDownloadFl()))
				inventory.setDownloadFl(invtoryDto.getDownloadFl());

			if (StringUtils.isNotBlank(invtoryDto.getEcommNonInventoryFl()))
				inventory.setEcommNonInventoryFl(invtoryDto.getEcommNonInventoryFl());

			if (StringUtils.isNotBlank(invtoryDto.getEmployeeId()))
				inventory.setEmployeeId(invtoryDto.getEmployeeId());

			if (invtoryDto.getEquivFactor() != null)
				inventory.setEquivFactor(invtoryDto.getEquivFactor());

			if (StringUtils.isNotBlank(invtoryDto.getEquivUm()))
				inventory.setEquivUm(invtoryDto.getEquivUm());

			if (StringUtils.isNotBlank(invtoryDto.getExternalRefId()))
				inventory.setExternalRefId(invtoryDto.getExternalRefId());

			if (StringUtils.isNotBlank(invtoryDto.getFlexibleItemFl()))
				inventory.setFlexibleItemFl(invtoryDto.getFlexibleItemFl());

			if (invtoryDto.getGlCatId() != null)
				inventory.setGlCatId(invtoryDto.getGlCatId());

			if (StringUtils.isNotBlank(invtoryDto.getHazUnNumberId()))
				inventory.setHazUnNumberId(invtoryDto.getHazUnNumberId());

			if (StringUtils.isNotBlank(invtoryDto.getHazClassId()))
				inventory.setHazClassId(invtoryDto.getHazClassId());

			if (invtoryDto.getHierarchyId() != null)
				inventory.setHierarchyId(invtoryDto.getHierarchyId());

			if (StringUtils.isNotBlank(invtoryDto.getInvItemLockFl()))
				inventory.setInvItemLockFl(invtoryDto.getInvItemLockFl());

			if (StringUtils.isNotBlank(invtoryDto.getInvType()))
				inventory.setInvType(invtoryDto.getInvType());

			if (StringUtils.isNotBlank(invtoryDto.getKitMemberFl()))
				inventory.setKitMemberFl(invtoryDto.getKitMemberFl());

			if (StringUtils.isNotBlank(invtoryDto.getLabelDesc1()))
				inventory.setLabelDesc1(invtoryDto.getLabelDesc1());

			if (StringUtils.isNotBlank(invtoryDto.getLabelDesc2()))
				inventory.setLabelDesc2(invtoryDto.getLabelDesc2());

			if (invtoryDto.getLastItemChangeDt() != null)
				inventory.setLastItemChangeDt(invtoryDto.getLastItemChangeDt());

			if (StringUtils.isNotBlank(invtoryDto.getLastItemChangeVendorId()))
				inventory.setLastItemChangeVendorId(invtoryDto.getLastItemChangeVendorId());

			if (invtoryDto.getLcuQty() != null)
				inventory.setLcuQty(invtoryDto.getLcuQty());

			if (invtoryDto.getLinkSkuNo() != null)
				inventory.setLinkSkuNo(invtoryDto.getLinkSkuNo());

			if (invtoryDto.getLineNo() != null)
				inventory.setLineNo(invtoryDto.getLineNo());

			if (StringUtils.isNotBlank(invtoryDto.getLookupDesc()))
				inventory.setLookupDesc(invtoryDto.getLookupDesc());

			if (StringUtils.isNotBlank(invtoryDto.getMfgCd()))
				inventory.setMfgCd(invtoryDto.getMfgCd());

			if (StringUtils.isNotBlank(invtoryDto.getNegativePriceFl()))
				inventory.setNegativePriceFl(invtoryDto.getNegativePriceFl());

			if (invtoryDto.getNewLabelDt() != null)
				inventory.setNewLabelDt(invtoryDto.getNewLabelDt());

			if (StringUtils.isNotBlank(invtoryDto.getNonInventoryFl()))
				inventory.setNonInventoryFl(invtoryDto.getNonInventoryFl());

			if (StringUtils.isNotBlank(invtoryDto.getObsoleteFl()))
				inventory.setObsoleteFl(invtoryDto.getObsoleteFl());

			if (StringUtils.isNotBlank(invtoryDto.getOperationType()))
				inventory.setOperationType(invtoryDto.getOperationType());

			if (StringUtils.isNotBlank(invtoryDto.getOtbFl()))
				inventory.setOtbFl(invtoryDto.getOtbFl());

			if (invtoryDto.getOwnerId() != null)
				inventory.setOwnerId(invtoryDto.getOwnerId());

			if (invtoryDto.getPackupActionId() != null)
				inventory.setPackupActionId(invtoryDto.getPackupActionId());

			if (invtoryDto.getPerCarQty() != null)
				inventory.setPerCarQty(invtoryDto.getPerCarQty());

			if (invtoryDto.getPeriodsHistory() != null)
				inventory.setPeriodsHistory(invtoryDto.getPeriodsHistory());

			if (StringUtils.isNotBlank(invtoryDto.getPkgDesc()))
				inventory.setPkgDesc(invtoryDto.getPkgDesc());

			if (invtoryDto.getPriceListSeq() != null)
				inventory.setPriceListSeq(invtoryDto.getPriceListSeq());

			if (StringUtils.isNotBlank(invtoryDto.getPrimaryVendorItem()))
				inventory.setPrimaryVendorItem(invtoryDto.getPrimaryVendorItem());

			if (StringUtils.isNotBlank(invtoryDto.getPrivateBrandFl()))
				inventory.setPrivateBrandFl(invtoryDto.getPrivateBrandFl());

			if (StringUtils.isNotBlank(invtoryDto.getProductHier4()))
				inventory.setProductHier4(invtoryDto.getProductHier4());

			if (StringUtils.isNotBlank(invtoryDto.getProductHier5()))
				inventory.setProductHier5(invtoryDto.getProductHier5());

			if (StringUtils.isNotBlank(invtoryDto.getProductHier6()))
				inventory.setProductHier6(invtoryDto.getProductHier6());

			if (StringUtils.isNotBlank(invtoryDto.getReceiptDesc()))
				inventory.setReceiptDesc(invtoryDto.getReceiptDesc());

			if (StringUtils.isNotBlank(invtoryDto.getRegionalPriceFl()))
				inventory.setRegionalPriceFl(invtoryDto.getRegionalPriceFl());

			if (StringUtils.isNotBlank(invtoryDto.getRegionalPromoFl()))
				inventory.setRegionalPromoFl(invtoryDto.getRegionalPromoFl());

			if (StringUtils.isNotBlank(invtoryDto.getRegionalSupersedeFl()))
				inventory.setRegionalSupersedeFl(invtoryDto.getRegionalSupersedeFl());

			if (invtoryDto.getRegisterReplicationNo() != null)
				inventory.setRegisterReplicationNo(invtoryDto.getRegisterReplicationNo());

			if (StringUtils.isNotBlank(invtoryDto.getReorderFl()))
				inventory.setReorderFl(invtoryDto.getReorderFl());

			if (invtoryDto.getReplacementSkuNo() != null)
				inventory.setReplacementSkuNo(invtoryDto.getReplacementSkuNo());

			if (invtoryDto.getReplicationNo() != null)
				inventory.setReplicationNo(invtoryDto.getReplicationNo());

			if (invtoryDto.getReportCd() != null)
				inventory.setReportCd(invtoryDto.getReportCd());

			if (invtoryDto.getReportFactor() != null)
				inventory.setReportFactor(invtoryDto.getReportFactor());

			if (StringUtils.isNotBlank(invtoryDto.getReportUm()))
				inventory.setReportUm(invtoryDto.getReportUm());

			if (StringUtils.isNotBlank(invtoryDto.getRestrictSaleFl()))
				inventory.setRestrictSaleFl(invtoryDto.getRestrictSaleFl());

			if (StringUtils.isNotBlank(invtoryDto.getRollupBucketFl()))
				inventory.setRollupBucketFl(invtoryDto.getRollupBucketFl());

			if (StringUtils.isNotBlank(invtoryDto.getRrpInvType()))
				inventory.setRrpInvType(invtoryDto.getRrpInvType());

			if (StringUtils.isNotBlank(invtoryDto.getSellUm()))
				inventory.setSellUm(invtoryDto.getSellUm());

			if (invtoryDto.getShelfType() != null)
				inventory.setShelfType(invtoryDto.getShelfType());

			if (invtoryDto.getSkuNo() != null)
				inventory.setSkuNo(invtoryDto.getSkuNo());

			if (StringUtils.isNotBlank(invtoryDto.getSpecialItemFl()))
				inventory.setSpecialItemFl(invtoryDto.getSpecialItemFl());

			if (StringUtils.isNotBlank(invtoryDto.getStoreCpnFl()))
				inventory.setStoreCpnFl(invtoryDto.getStoreCpnFl());

			if (StringUtils.isNotBlank(invtoryDto.getSubInvFl()))
				inventory.setSubInvFl(invtoryDto.getSubInvFl());

			if (invtoryDto.getSuggestedSellCd() != null)
				inventory.setSuggestedSellCd(invtoryDto.getSuggestedSellCd());

			if (StringUtils.isNotBlank(invtoryDto.getSupersededFl()))
				inventory.setSupersededFl(invtoryDto.getSupersededFl());

			if (StringUtils.isNotBlank(invtoryDto.getSupplementalUpcFl()))
				inventory.setSupplementalUpcFl(invtoryDto.getSupplementalUpcFl());

			if (StringUtils.isNotBlank(invtoryDto.getSupplyItemFl()))
				inventory.setSupplyItemFl(invtoryDto.getSupplyItemFl());

			if (StringUtils.isNotBlank(invtoryDto.getSystemWarrantyFl()))
				inventory.setSystemWarrantyFl(invtoryDto.getSystemWarrantyFl());

			if (StringUtils.isNotBlank(invtoryDto.getTareTableNo()))
				inventory.setTareTableNo(invtoryDto.getTareTableNo());

			if (invtoryDto.getTenderProgramId() != null)
				inventory.setTenderProgramId(invtoryDto.getTenderProgramId());

			if (invtoryDto.getTicketType() != null)
				inventory.setTicketType(invtoryDto.getTicketType());

			if (invtoryDto.getUnitCube() != null)
				inventory.setUnitCube(invtoryDto.getUnitCube());

			if (invtoryDto.getUnitWeight() != null)
				inventory.setUnitWeight(invtoryDto.getUnitWeight());

			if (invtoryDto.getUpcIdSeqNo() != null)
				inventory.setUpcIdSeqNo(invtoryDto.getUpcIdSeqNo());

			if (StringUtils.isNotBlank(invtoryDto.getUserCreated()))
				inventory.setUserCreated(invtoryDto.getUserCreated());

			if (StringUtils.isNotBlank(invtoryDto.getUserModified()))
				inventory.setUserModified(invtoryDto.getUserModified());

			if (StringUtils.isNotBlank(invtoryDto.getUseSerialFl()))
				inventory.setUseSerialFl(invtoryDto.getUseSerialFl());

			if (invtoryDto.getValidationSeqNo() != null)
				inventory.setValidationSeqNo(invtoryDto.getValidationSeqNo());

			if (invtoryDto.getVenitmSeqNo() != null)
				inventory.setVenitmSeqNo(invtoryDto.getVenitmSeqNo());

			if (invtoryDto.getWarehouseInnerPack() != null)
				inventory.setWarehouseInnerPack(invtoryDto.getWarehouseInnerPack());

			if (StringUtils.isNotBlank(invtoryDto.getWarrantyFl()))
				inventory.setWarrantyFl(invtoryDto.getWarrantyFl());

			if (StringUtils.isNotBlank(invtoryDto.getWebEnabledFl()))
				inventory.setWebEnabledFl(invtoryDto.getWebEnabledFl());

			if (invtoryDto.getWeeksHistory() != null)
				inventory.setWeeksHistory(invtoryDto.getWeeksHistory());

			if (StringUtils.isNotBlank(invtoryDto.getWeighedItemFl()))
				inventory.setWeighedItemFl(invtoryDto.getWeighedItemFl());

			if (invtoryDto.getXrefNo() != null)
				inventory.setXrefNo(invtoryDto.getXrefNo());
		}
		return inventory;
	}

	public List<DepartmentGroupResponse> getDepartmentGroups(List<DeptgrpDTO> listOfDept) {
		// TODO Auto-generated method stub
		List<DepartmentGroupResponse> list = null;
		if (!listOfDept.isEmpty()) {
			list = new ArrayList<>();
			for (DeptgrpDTO dto : listOfDept) {
				DepartmentGroupResponse departmentGroup = new DepartmentGroupResponse();
				if (dto.getDeptgrpNo() != null)
					departmentGroup.setDeptGrpNo(dto.getDeptgrpNo());

				if (StringUtils.isNotBlank(dto.getDescription()))
					departmentGroup.setDescription(dto.getDescription());
				list.add(departmentGroup);
			}
		}
		return list;
	}

	public List<Department> getDepartments(List<DeptDTO> departmentDtos) {
		// TODO Auto-generated method stub
		List<Department> list = null;
		if (!departmentDtos.isEmpty()) {
			list = new ArrayList<>();
			for (DeptDTO dto : departmentDtos) {
				Department department = new Department();
				if (dto.getDeptNo() != null)
					department.setDeptNo(dto.getDeptNo());

				if (StringUtils.isNotBlank(dto.getDescription()))
					department.setDescription(dto.getDescription());
				list.add(department);
			}
		}
		return list;
	}

	public List<ClassDetails> getClassDetailsList(List<ClassDTO> classes) {
		// TODO Auto-generated method stub
		List<ClassDetails> list = null;
		if (!classes.isEmpty()) {
			list = new ArrayList<>();
			for (ClassDTO dto : classes) {
				ClassDetails classDetails = new ClassDetails();

				if (dto.getClassNo() != null)
					classDetails.setClassNo(dto.getClassNo());

				if (StringUtils.isNotBlank(dto.getDescription()))
					classDetails.setDescription(dto.getDescription());

				list.add(classDetails);
			}
		}
		return list;
	}

	public List<Line> getLines(List<LineDTO> lines) {
		// TODO Auto-generated method stub
		List<Line> list = null;
		if (!lines.isEmpty()) {
			list = new ArrayList<>();
			for (LineDTO dto : lines) {
				Line line = new Line();
				if (dto.getLineNo() != null)
					line.setLineNo(dto.getLineNo());

				if (StringUtils.isNotBlank(dto.getDescription()))
					line.setDescription(dto.getDescription());
				list.add(line);
			}
		}
		return list;
	}

	public List<ItemNote> getItemNotes(List<ItemNoteResultWithDomainRefDTO> itemNoteDtos) {
		// TODO Auto-generated method stub
		List<ItemNote> list = null;
		if (!itemNoteDtos.isEmpty()) {
			list = new ArrayList<>();
			for (ItemNoteResultWithDomainRefDTO dto : itemNoteDtos) {
				ItemNote itemNote = new ItemNote();

				if (dto.getBackOfficeHandling() != null) {

					if (StringUtils.isNotBlank(dto.getBackOfficeHandling().getCodeMeaning()))
						itemNote.setBackOfficeHandlingCodeMeaning(dto.getBackOfficeHandling().getCodeMeaning());

					if (StringUtils.isNotBlank(dto.getBackOfficeHandling().getCodeValue()))
						itemNote.setBackOfficeHandlingCodeValue(dto.getBackOfficeHandling().getCodeValue());
				}
				if (dto.getGroupId() != null) {
					if (StringUtils.isNotBlank(dto.getGroupId().getGroupId()))
						itemNote.setGroupId(dto.getGroupId().getGroupId());
				}

				if (dto.getNoteId() != null)
					itemNote.setNoteId(dto.getNoteId());

				if (StringUtils.isNotBlank(dto.getNoteText()))
					itemNote.setNoteText(dto.getNoteText());
				
				if (StringUtils.isNotBlank(dto.getOnwerID()))
					itemNote.setOwner(dto.getOnwerID());

				if (dto.getPosHandling() != null) {
					if (StringUtils.isNotBlank(dto.getPosHandling().getCodeMeaning()))
						itemNote.setPosHandlingCodeMeaning(dto.getPosHandling().getCodeMeaning());

					if (StringUtils.isNotBlank(dto.getPosHandling().getCodeValue()))
						itemNote.setPosHandlingCodeValue(dto.getPosHandling().getCodeValue());
				}

//				if (dto.getSiteNo() != null)
//					itemNote.setSiteNo(dto.getSiteNo());

				if (dto.getSkuNo() != null)
					itemNote.setSkuNo(dto.getSkuNo());

				if (dto.getTypeCd() != null) {

					if (StringUtils.isNotBlank(dto.getTypeCd().getCodeMeaning()))
						itemNote.setTypeCodeMeaning(dto.getTypeCd().getCodeMeaning());

					if (StringUtils.isNotBlank(dto.getTypeCd().getCodeValue()))
						itemNote.setTypeCodeValue(dto.getTypeCd().getCodeValue());
				}
				list.add(itemNote);
			}
		}
		return list;
	}

	public GroupsAllResponse getGroupsAll(GroupsAllDTO groupsAllDto) {
		// TODO Auto-generated method stub
		GroupsAllResponse groupsAll = null;
		if (groupsAllDto != null) {
			groupsAll = new GroupsAllResponse();

			if (StringUtils.isNotBlank(groupsAllDto.getAltGroupId()))
				groupsAll.setAltGroupId(groupsAllDto.getAltGroupId());

			if (groupsAllDto.getDateCreated() != null)
				groupsAll.setDateCreated(groupsAllDto.getDateCreated());

			if (groupsAllDto.getDateModified() != null)
				groupsAll.setDateModified(groupsAllDto.getDateModified());

			if (StringUtils.isNotBlank(groupsAllDto.getDescription()))
				groupsAll.setDescription(groupsAllDto.getDescription());

			if (StringUtils.isNotBlank(groupsAllDto.getGroupId()))
				groupsAll.setGroupId(groupsAllDto.getGroupId());

			if (StringUtils.isNotBlank(groupsAllDto.getGroupOwner()))
				groupsAll.setGroupOwner(groupsAllDto.getGroupOwner());

			if (StringUtils.isNotBlank(groupsAllDto.getLevelManager()))
				groupsAll.setLevelManager(groupsAllDto.getLevelManager());

			if (groupsAllDto.getLevelNo() != null)
				groupsAll.setLevelNo(groupsAllDto.getLevelNo());

			if (StringUtils.isNotBlank(groupsAllDto.getParentGroupId()))
				groupsAll.setParentGroupId(groupsAllDto.getParentGroupId());

			if (StringUtils.isNotBlank(groupsAllDto.getType()))
				groupsAll.setType(groupsAllDto.getType());

			if (StringUtils.isNotBlank(groupsAllDto.getTypeName()))
				groupsAll.setTypeName(groupsAllDto.getTypeName());

			if (StringUtils.isNotBlank(groupsAllDto.getUserCreated()))
				groupsAll.setUserCreated(groupsAllDto.getUserCreated());

			if (StringUtils.isNotBlank(groupsAllDto.getUserModified()))
				groupsAll.setUserModified(groupsAllDto.getUserModified());
		}
		return groupsAll;
	}

	public List<GroupsAllResponse> getGroupsAllList(List<GroupsAllDTO> groupsAllDtos) {
		// TODO Auto-generated method stub
		List<GroupsAllResponse> list = null;
		if (!groupsAllDtos.isEmpty()) {
			list = new ArrayList<>();
			for (GroupsAllDTO groupsAllDto : groupsAllDtos) {
				GroupsAllResponse groupsAll = new GroupsAllResponse();

				if (StringUtils.isNotBlank(groupsAllDto.getAltGroupId()))
					groupsAll.setAltGroupId(groupsAllDto.getAltGroupId());

				if (groupsAllDto.getDateCreated() != null)
					groupsAll.setDateCreated(groupsAllDto.getDateCreated());

				if (groupsAllDto.getDateModified() != null)
					groupsAll.setDateModified(groupsAllDto.getDateModified());

				if (StringUtils.isNotBlank(groupsAllDto.getDescription()))
					groupsAll.setDescription(groupsAllDto.getDescription());

				if (StringUtils.isNotBlank(groupsAllDto.getGroupId()))
					groupsAll.setGroupId(groupsAllDto.getGroupId());

				if (StringUtils.isNotBlank(groupsAllDto.getGroupOwner()))
					groupsAll.setGroupOwner(groupsAllDto.getGroupOwner());

				if (StringUtils.isNotBlank(groupsAllDto.getLevelManager()))
					groupsAll.setLevelManager(groupsAllDto.getLevelManager());

				if (groupsAllDto.getLevelNo() != null)
					groupsAll.setLevelNo(groupsAllDto.getLevelNo());

				if (StringUtils.isNotBlank(groupsAllDto.getParentGroupId()))
					groupsAll.setParentGroupId(groupsAllDto.getParentGroupId());

				if (StringUtils.isNotBlank(groupsAllDto.getType()))
					groupsAll.setType(groupsAllDto.getType());

				if (StringUtils.isNotBlank(groupsAllDto.getTypeName()))
					groupsAll.setTypeName(groupsAllDto.getTypeName());

				if (StringUtils.isNotBlank(groupsAllDto.getUserCreated()))
					groupsAll.setUserCreated(groupsAllDto.getUserCreated());

				if (StringUtils.isNotBlank(groupsAllDto.getUserModified()))
					groupsAll.setUserModified(groupsAllDto.getUserModified());

				list.add(groupsAll);
			}
		}
		return list;
	}

	public SiteResponse getSite(SiteDTO siteDto) {
		// TODO Auto-generated method stub
		SiteResponse site = null;
		if (siteDto != null) {
			site = new SiteResponse();

			if (StringUtils.isNotBlank(siteDto.getAdr1()))
				site.setAdr1(siteDto.getAdr1());

			if (StringUtils.isNotBlank(siteDto.getAdr2()))
				site.setAdr2(siteDto.getAdr2());

			if (StringUtils.isNotBlank(siteDto.getCity()))
				site.setCity(siteDto.getCity());

			if (StringUtils.isNotBlank(siteDto.getCompanyCd()))
				site.setCompanyCd(siteDto.getCompanyCd());

			if (StringUtils.isNotBlank(siteDto.getCountry()))
				site.setCountry(siteDto.getCountry());

			if (StringUtils.isNotBlank(siteDto.getCounty()))
				site.setCounty(siteDto.getCounty());

			if (StringUtils.isNotBlank(siteDto.getFullName()))
				site.setFullName(siteDto.getFullName());

			if (StringUtils.isNotBlank(siteDto.getName()))
				site.setName(siteDto.getName());

			if (siteDto.getOwnerId() != null)
				site.setOwnerId(siteDto.getOwnerId());

			if (StringUtils.isNotBlank(siteDto.getSiteActive()))
				site.setSiteActive(siteDto.getSiteActive());

			if (siteDto.getSiteNo() != null)
				site.setSiteNo(siteDto.getSiteNo());

			if (StringUtils.isNotBlank(siteDto.getState()))
				site.setState(siteDto.getState());

			if (StringUtils.isNotBlank(siteDto.getWebEnabledFl()))
				site.setWebEnabledFl(siteDto.getWebEnabledFl());

			if (StringUtils.isNotBlank(siteDto.getZip()))
				site.setZip(siteDto.getZip());

		}
		return site;
	}

	public List<SiteResponse> getSites(List<SiteDTO> siteDtos) {
		// TODO Auto-generated method stub
		List<SiteResponse> list = null;
		if (!siteDtos.isEmpty()) {
			list = new ArrayList<>();
			for (SiteDTO siteDto : siteDtos) {
				SiteResponse site = new SiteResponse();

				if (StringUtils.isNotBlank(siteDto.getAdr1()))
					site.setAdr1(siteDto.getAdr1());

				if (StringUtils.isNotBlank(siteDto.getAdr2()))
					site.setAdr2(siteDto.getAdr2());

				if (StringUtils.isNotBlank(siteDto.getCity()))
					site.setCity(siteDto.getCity());

				if (StringUtils.isNotBlank(siteDto.getCompanyCd()))
					site.setCompanyCd(siteDto.getCompanyCd());

				if (StringUtils.isNotBlank(siteDto.getCountry()))
					site.setCountry(siteDto.getCountry());

				if (StringUtils.isNotBlank(siteDto.getCounty()))
					site.setCounty(siteDto.getCounty());

				if (StringUtils.isNotBlank(siteDto.getFullName()))
					site.setFullName(siteDto.getFullName());

				if (StringUtils.isNotBlank(siteDto.getName()))
					site.setName(siteDto.getName());

				if (siteDto.getOwnerId() != null)
					site.setOwnerId(siteDto.getOwnerId());

				if (StringUtils.isNotBlank(siteDto.getSiteActive()))
					site.setSiteActive(siteDto.getSiteActive());

				if (siteDto.getSiteNo() != null)
					site.setSiteNo(siteDto.getSiteNo());

				if (StringUtils.isNotBlank(siteDto.getState()))
					site.setState(siteDto.getState());

				if (StringUtils.isNotBlank(siteDto.getWebEnabledFl()))
					site.setWebEnabledFl(siteDto.getWebEnabledFl());

				if (StringUtils.isNotBlank(siteDto.getZip()))
					site.setZip(siteDto.getZip());

				list.add(site);
			}
		}
		return list;
	}

	public List<DomainRef> getDomainRefs(List<DomainRefEntity> domainRefs) {
		// TODO Auto-generated method stub
		List<DomainRef> list = null;
		if (!domainRefs.isEmpty()) {
			list = new ArrayList<>();
			for (DomainRefEntity dto : domainRefs) {
				DomainRef domainRef = new DomainRef();

				if (StringUtils.isNotBlank(dto.getDescription()))
					domainRef.setDescription(dto.getDescription());

				if (dto.getIdentifier() != null && dto.getIdentifier().getDomainRefId() != null)
					domainRef.setDomainRefId(dto.getIdentifier().getDomainRefId());

				list.add(domainRef);
			}
		}
		return list;
	}

	public List<VendorItemResponse> getVendorItems(List<VendorItemInfoDTO> vendorItemDtos) {
		// TODO Auto-generated method stub
		List<VendorItemResponse> list = null;
		if (!vendorItemDtos.isEmpty()) {
			list = new ArrayList<>();
			for (VendorItemInfoDTO dto : vendorItemDtos) {
				VendorItemResponse vendorItemInfo = new VendorItemResponse();

				if (dto.getLockLeadTime() != null)
					vendorItemInfo.setLockLeadTime(dto.getLockLeadTime());

				if (StringUtils.isNotBlank(dto.getVendorName()))
					vendorItemInfo.setVendorName(dto.getVendorName());

				if (dto.getVendorItemDTO() != null) {

					if (dto.getVendorItemDTO().getSeqNo() != null)
						vendorItemInfo.setSeqNo(dto.getVendorItemDTO().getSeqNo());

					if (dto.getVendorItemDTO().getDateChanged() != null)
						vendorItemInfo.setDateChanged(dto.getVendorItemDTO().getDateChanged());

					if (dto.getVendorItemDTO().getDateCreated() != null)
						vendorItemInfo.setDateCreated(dto.getVendorItemDTO().getDateCreated());

					if (dto.getVendorItemDTO().getDateModified() != null)
						vendorItemInfo.setDateModified(dto.getVendorItemDTO().getDateModified());

					if (dto.getVendorItemDTO().getEoq() != null)
						vendorItemInfo.setEoq(dto.getVendorItemDTO().getEoq());

					if (dto.getVendorItemDTO().getLeadTime() != null)
						vendorItemInfo.setLeadTime(dto.getVendorItemDTO().getLeadTime());

					if (dto.getVendorItemDTO().getLoq() != null)
						vendorItemInfo.setLoq(dto.getVendorItemDTO().getLoq());

					if (StringUtils.isNotBlank(dto.getVendorItemDTO().getMainVendorItemFl()))
						vendorItemInfo.setMainVendorItemFl(dto.getVendorItemDTO().getMainVendorItemFl());

					if (dto.getVendorItemDTO().getMasterPackQty() != null)
						vendorItemInfo.setMasterPackQty(dto.getVendorItemDTO().getMasterPackQty());

					if (StringUtils.isNotBlank(dto.getVendorItemDTO().getOperationType()))
						vendorItemInfo.setOperationType(dto.getVendorItemDTO().getOperationType());

					if (dto.getVendorItemDTO().getOrderAvailabilityActiveDt() != null)
						vendorItemInfo
								.setOrderAvailabilityActiveDt(dto.getVendorItemDTO().getOrderAvailabilityActiveDt());

					if (StringUtils.isNotBlank(dto.getVendorItemDTO().getOrderAvailabilityStatus()))
						vendorItemInfo.setOrderAvailabilityStatus(dto.getVendorItemDTO().getOrderAvailabilityStatus());

					if (dto.getVendorItemDTO().getPackCost() != null)
						vendorItemInfo.setPackCost(dto.getVendorItemDTO().getPackCost());

					if (dto.getVendorItemDTO().getPackCube() != null)
						vendorItemInfo.setPackCube(dto.getVendorItemDTO().getPackCube());

					if (dto.getVendorItemDTO().getPackQty() != null)
						vendorItemInfo.setPackQty(dto.getVendorItemDTO().getPackQty());

					if (StringUtils.isNotBlank(dto.getVendorItemDTO().getPackUm()))
						vendorItemInfo.setPackUm(dto.getVendorItemDTO().getPackUm());

					if (dto.getVendorItemDTO().getPackWeight() != null)
						vendorItemInfo.setPackWeight(dto.getVendorItemDTO().getPackWeight());

					if (dto.getVendorItemDTO().getSiteNo() != null)
						vendorItemInfo.setSiteNo(dto.getVendorItemDTO().getSiteNo());

					if (dto.getVendorItemDTO().getSkuNo() != null)
						vendorItemInfo.setSkuNo(dto.getVendorItemDTO().getSkuNo());

					if (dto.getVendorItemDTO().getUnitCost() != null)
						vendorItemInfo.setUnitCost(dto.getVendorItemDTO().getUnitCost());

					if (StringUtils.isNotBlank(dto.getVendorItemDTO().getUserCreated()))
						vendorItemInfo.setUserCreated(dto.getVendorItemDTO().getUserCreated());

					if (StringUtils.isNotBlank(dto.getVendorItemDTO().getUserModified()))
						vendorItemInfo.setUserModified(dto.getVendorItemDTO().getUserModified());

					if (dto.getVendorItemDTO().getVendorCurrencyPackCost() != null)
						vendorItemInfo.setVendorCurrencyPackCost(dto.getVendorItemDTO().getVendorCurrencyPackCost());

					if (StringUtils.isNotBlank(dto.getVendorItemDTO().getVendorId()))
						vendorItemInfo.setVendorId(dto.getVendorItemDTO().getVendorId());

					if (StringUtils.isNotBlank(dto.getVendorItemDTO().getVendorItem()))
						vendorItemInfo.setVendorItem(dto.getVendorItemDTO().getVendorItem());

					if (StringUtils.isNotBlank(dto.getVendorItemDTO().getVenType()))
						vendorItemInfo.setVenType(dto.getVendorItemDTO().getVenType());
				}
				list.add(vendorItemInfo);
			}
		}
		return list;
	}

	public SkuInfoResponse getSkuInfoResponse(InventoryGlobalInfoDTO globalInfo) {

		SkuInfoResponse response = new SkuInfoResponse();

		if (globalInfo.getInvtoryDto().getSkuNo() != null && globalInfo.getInvtoryDto().getSkuNo() > 0)
			response.setSkuNo(globalInfo.getInvtoryDto().getSkuNo());

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getDescription()))
			response.setItemDescription(globalInfo.getInvtoryDto().getDescription());

		// product info
		if (globalInfo.getDeptGrpNo() != null && globalInfo.getDeptGrpNo() > 0)
			response.setDeptGrpNo(globalInfo.getDeptGrpNo());

		if (StringUtils.isNotBlank(globalInfo.getDeptGrpDesc()))
			response.setDeptGrpDesc(globalInfo.getDeptGrpDesc());

		if (globalInfo.getInvtoryDto().getDeptNo() != null && globalInfo.getInvtoryDto().getDeptNo() > 0)
			response.setDeptNo(globalInfo.getInvtoryDto().getDeptNo());

		if (StringUtils.isNotBlank(globalInfo.getDeptDesc()))
			response.setDeptDesc(globalInfo.getDeptDesc());

		if (globalInfo.getInvtoryDto().getClassNo() != null && globalInfo.getInvtoryDto().getClassNo() > 0)
			response.setClassNo(globalInfo.getInvtoryDto().getClassNo());

		if (StringUtils.isNotBlank(globalInfo.getClassDesc()))
			response.setClassDesc(globalInfo.getClassDesc());

		if (globalInfo.getInvtoryDto().getLineNo() != null && globalInfo.getInvtoryDto().getLineNo() > 0)
			response.setLineNo(globalInfo.getInvtoryDto().getLineNo());

		if (StringUtils.isNotBlank(globalInfo.getLineDesc()))
			response.setLineDesc(globalInfo.getLineDesc());

		// description info
		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getReceiptDesc()))
			response.setReceiptDesc(globalInfo.getInvtoryDto().getReceiptDesc());

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getLookupDesc()))
			response.setLookupDesc(globalInfo.getInvtoryDto().getLookupDesc());

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getPkgDesc()))
			response.setProductSize(globalInfo.getInvtoryDto().getPkgDesc());

		// report info
		if (globalInfo.getInvtoryDto().getReportFactor() != null
				&& globalInfo.getInvtoryDto().getReportFactor().doubleValue() > 0)
			response.setReportFactor(globalInfo.getInvtoryDto().getReportFactor());

//		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getReportUm()))
//			response.setReportUm(globalInfo.getInvtoryDto().getReportUm());
//
//		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getSellUm()))
//			response.setSellUm(globalInfo.getInvtoryDto().getSellUm());

		// measurement info
		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getCatchWeightFl())) {

			if (globalInfo.getInvtoryDto().getCatchWeightFl().equalsIgnoreCase("Y"))
				response.setCatchWeightFl(true);
			else
				response.setCatchWeightFl(false);
		}

		if (globalInfo.getInvtoryDto().getUnitWeight() != null
				&& globalInfo.getInvtoryDto().getUnitWeight().doubleValue() >= 0)
			response.setUnitWeight(globalInfo.getInvtoryDto().getUnitWeight());

		if (globalInfo.getInvtoryDto().getUnitWeight() != null
				&& globalInfo.getInvtoryDto().getUnitWeight().doubleValue() >= 0)
			response.setUnitMeasured(globalInfo.getInvtoryDto().getUnitWeight());

		if (globalInfo.getInvtoryDto().getUnitCube() != null
				&& globalInfo.getInvtoryDto().getUnitCube().doubleValue() >= 0)
			response.setUnitCube(globalInfo.getInvtoryDto().getUnitCube());

		if (globalInfo.getInvtoryDto().getUnitCube() != null
				&& globalInfo.getInvtoryDto().getUnitCube().doubleValue() >= 0)
			response.setUnitCode(globalInfo.getInvtoryDto().getUnitCube());

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getWeighedItemFl())) {
			if (globalInfo.getInvtoryDto().getWeighedItemFl().equalsIgnoreCase("Y"))
				response.setWeighedItemFl(true);
			else
				response.setWeighedItemFl(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getWeighedItemFl())) {
			if (globalInfo.getInvtoryDto().getWeighedItemFl().equalsIgnoreCase("Y"))
				response.setMeasuredItem(true);
			else
				response.setMeasuredItem(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getTareTableNo()))
			response.setTareNumber(globalInfo.getInvtoryDto().getTareTableNo());

		// regional supersede info
		if (globalInfo.getInvtoryDto().getLcuQty() != null && globalInfo.getInvtoryDto().getLcuQty().doubleValue() > 0)
			response.setLcuQty(globalInfo.getInvtoryDto().getLcuQty());

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getRegionalSupersedeFl())) {
			if (globalInfo.getInvtoryDto().getRegionalSupersedeFl().equalsIgnoreCase("Y"))
				response.setRegionalSupersede(true);
			else
				response.setRegionalSupersede(false);
		}

		if (globalInfo.getInvtoryDto().getReplacementSkuNo() != null
				&& globalInfo.getInvtoryDto().getReplacementSkuNo() > 0)
			response.setReplacementSKU(globalInfo.getInvtoryDto().getReplacementSkuNo());

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getInvType()))
			response.setItemType(globalInfo.getInvtoryDto().getInvType());

		if (globalInfo.getInvtoryDto().getReportCd() != null && globalInfo.getInvtoryDto().getReportCd() > 0)
			response.setReportCode(globalInfo.getInvtoryDto().getReportCd());

		if (globalInfo.getInvtoryDto().getLinkSkuNo() != null && globalInfo.getInvtoryDto().getLinkSkuNo() > 0)
			response.setLinkSkuNumber(globalInfo.getInvtoryDto().getLinkSkuNo());

		// additional field in response
		// link sku number description should be displayed as suggested with the help of
		// query

		// serial flags
		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getUseSerialFl())) {

			if (globalInfo.getInvtoryDto().getUseSerialFl().equalsIgnoreCase("Y"))
				response.setSerializedItem(true);
			else
				response.setSerializedItem(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getAutoGenSerialsFl())) {

			if (globalInfo.getInvtoryDto().getAutoGenSerialsFl().equalsIgnoreCase("Y"))
				response.setAutoGenSerial(true);
			else
				response.setAutoGenSerial(false);

		}

		if (globalInfo.getInvtoryDto().getTenderProgramId() != null
				&& globalInfo.getInvtoryDto().getTenderProgramId() > 0)
			response.setTenderCertId(globalInfo.getInvtoryDto().getTenderProgramId());

		// label info
		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getEquivUm()))
			response.setEquivUm(globalInfo.getInvtoryDto().getEquivUm());

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getPrivateBrandFl())) {
			if (globalInfo.getInvtoryDto().getPrivateBrandFl().equalsIgnoreCase("Y"))
				response.setPrivateLabelItem(true);
			else
				response.setPrivateLabelItem(false);
		}

		if (globalInfo.getInvtoryDto().getEquivFactor() != null
				&& globalInfo.getInvtoryDto().getEquivFactor().doubleValue() > 0)
			response.setEquivFactor(globalInfo.getInvtoryDto().getEquivFactor());

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getLabelDesc1()))
			response.setLabelDescription1(globalInfo.getInvtoryDto().getLabelDesc1());

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getLabelDesc2()))
			response.setLabelDescription2(globalInfo.getInvtoryDto().getLabelDesc2());

		// packedQtySection
		if (globalInfo.getInvtoryDto().getWarehouseInnerPack() != null
				&& globalInfo.getInvtoryDto().getWarehouseInnerPack().doubleValue() >= 0)
			response.setInnerPackQty(globalInfo.getInvtoryDto().getWarehouseInnerPack());

		if (globalInfo.getInvtoryDto().getPriceListSeq() != null && globalInfo.getInvtoryDto().getPriceListSeq() >= 0)
			response.setPriceListSeq(globalInfo.getInvtoryDto().getPriceListSeq());

		if (globalInfo.getInvtoryDto().getWeeksHistory() != null && globalInfo.getInvtoryDto().getWeeksHistory() >= 0)
			response.setWeeksHistory(globalInfo.getInvtoryDto().getWeeksHistory());

		if (globalInfo.getInvtoryDto().getPeriodsHistory() != null
				&& globalInfo.getInvtoryDto().getPeriodsHistory() >= 0)
			response.setPeriodsHistory(globalInfo.getInvtoryDto().getPeriodsHistory());

		if (globalInfo.getInvtoryDto().getGlCatId() != null && globalInfo.getInvtoryDto().getGlCatId() > 0)
			response.setGlCatID(globalInfo.getInvtoryDto().getGlCatId());

		if (StringUtils.isNotBlank(globalInfo.getGlCatIdDesc()))
			response.setGlCatIdDesc(globalInfo.getGlCatIdDesc());

		if (globalInfo.getInvtoryDto().getXrefNo() != null && globalInfo.getInvtoryDto().getXrefNo() > 0)
			response.setXrefNumber(globalInfo.getInvtoryDto().getXrefNo());

		if (globalInfo.getInvtoryDto().getDateCreated() != null) {
			// later code is shifted to date converter custom function
			Date val = globalInfo.getInvtoryDto().getDateCreated();

			Date value = new Date(val.getTime());
			System.out.println("value of date created is ======>> " + value);

//			Timestamp ts=new Timestamp(globalInfo.getInvtoryDto().getDateCreated().getTime());
//			Date date2=new Date(ts.getTime());
//			System.out.println(date2);
			response.setCreateDt(value);
		}

		if (globalInfo.getInvtoryDto().getDateModified() != null) {
			Date val = globalInfo.getInvtoryDto().getDateModified();
//			System.out.println(val+" ======================== val");
//			System.out.println(val.getTime()+" ======================== time only");
//			System.out.println(new Date(val.getTime())+" ======================== long date");
			Date value = new Date(val.getTime());
			System.out.println("value of date modified is ======>> " + value);

//			Timestamp ts=new Timestamp(val.getTime());
//			Date date2=new Date(ts.getTime());
//			System.out.println(date2);

			response.setChangeDt(value);
		}

		// POS flags info
		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getCaptureOrderNoFl())) {

			if (globalInfo.getInvtoryDto().getCaptureOrderNoFl().equalsIgnoreCase("Y"))
				response.setCaptureOrderNoFl(true);
			else
				response.setCaptureOrderNoFl(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getCaptureSerialNoFl())) {

			if (globalInfo.getInvtoryDto().getCaptureSerialNoFl().equalsIgnoreCase("Y"))
				response.setCaptureSerialNoFl(true);
			else
				response.setCaptureSerialNoFl(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getDownloadFl())) {
			if (globalInfo.getInvtoryDto().getDownloadFl().equalsIgnoreCase("Y"))
				response.setDownloadFl(true);
			else
				response.setDownloadFl(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getNegativePriceFl())) {
			if (globalInfo.getInvtoryDto().getNegativePriceFl().equalsIgnoreCase("Y"))
				response.setNegativePriceFl(true);
			else
				response.setNegativePriceFl(false);
		}

		// hazard class and number ids
		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getHazClassId()))
			response.setHazClassId(globalInfo.getInvtoryDto().getHazClassId());

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getHazUnNumberId()))
			response.setHazUnNumberId(globalInfo.getInvtoryDto().getHazUnNumberId());

		// general status flags info
		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getReorderFl())) {
			if (globalInfo.getInvtoryDto().getReorderFl().equalsIgnoreCase("Y"))
				response.setReorderFl(true);
			else
				response.setReorderFl(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getDiscontinuedFl())) {
			if (globalInfo.getInvtoryDto().getDiscontinuedFl().equalsIgnoreCase("Y"))
				response.setDiscontinuedFl(true);
			else
				response.setDiscontinuedFl(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getObsoleteFl())) {
			if (globalInfo.getInvtoryDto().getObsoleteFl().equalsIgnoreCase("Y"))
				response.setObsoleteFl(true);
			else
				response.setObsoleteFl(false);
		}

		// item type flags info
		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getSpecialItemFl())) {
			if (globalInfo.getInvtoryDto().getSpecialItemFl().equalsIgnoreCase("Y"))
				response.setNonRevenueItem(true);
			else
				response.setNonRevenueItem(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getStoreCpnFl())) {
			if (globalInfo.getInvtoryDto().getStoreCpnFl().equalsIgnoreCase("Y"))
				response.setStoreCpnFl(true);
			else
				response.setStoreCpnFl(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getNonInventoryFl())) {
			if (globalInfo.getInvtoryDto().getNonInventoryFl().equalsIgnoreCase("Y"))
				response.setNonInventoryFl(true);
			else
				response.setNonInventoryFl(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getSubInvFl())) {
			if (globalInfo.getInvtoryDto().getSubInvFl().equalsIgnoreCase("Y"))
				response.setSubInvFl(true);
			else
				response.setSubInvFl(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getSupplyItemFl())) {
			if (globalInfo.getInvtoryDto().getSupplyItemFl().equalsIgnoreCase("Y"))
				response.setSupplyItemFl(true);
			else
				response.setSupplyItemFl(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getCoreFl())) {
			if (globalInfo.getInvtoryDto().getCoreFl().equalsIgnoreCase("Y"))
				response.setCoreFl(true);
			else
				response.setCoreFl(false);
		}

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getFlexibleItemFl())) {
			if (globalInfo.getInvtoryDto().getFlexibleItemFl().equalsIgnoreCase("Y"))
				response.setFlexibleItemFl(true);
			else
				response.setFlexibleItemFl(false);
		}

		if (globalInfo.getInvtoryDto().getPackupActionId() != null
				&& globalInfo.getInvtoryDto().getPackupActionId() > 0)
			response.setPackUpAction(globalInfo.getInvtoryDto().getPackupActionId());

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getEmployeeId()))
			response.setBuyerID(globalInfo.getInvtoryDto().getEmployeeId());

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getPrimaryVendorItem()))
			response.setPrimaryVendorItem(globalInfo.getInvtoryDto().getPrimaryVendorItem());

		if (StringUtils.isNotBlank(globalInfo.getInvtoryDto().getMfgCd()))
			response.setMfgCd(globalInfo.getInvtoryDto().getMfgCd());

		return response;
	}

	public List<ItemAttributeResponse> getItemAttributes(List<ItemAttributeResultDTO> result) {
		// TODO Auto-generated method stub
		List<ItemAttributeResponse> list = new ArrayList<>();
		if (!result.isEmpty()) {
			for (ItemAttributeResultDTO dto : result) {
				ItemAttributeResponse item = new ItemAttributeResponse();

				if (StringUtils.isNotBlank(dto.getAttributeDescription()))
					item.setAttributeDescription(dto.getAttributeDescription());

				if (dto.getAttributeId() != null)
					item.setAttributeId(dto.getAttributeId());

				if (StringUtils.isNotBlank(dto.getAttributeName()))
					item.setAttributeName(dto.getAttributeName());

				if (StringUtils.isNotBlank(dto.getAttributeValue()))
					item.setAttributeValue(dto.getAttributeValue());
				item.setSkuNo(dto.getSkuNo());

				if ("Y".equalsIgnoreCase(dto.getLovFl()))
					item.setLovFl(true);
				else
					item.setLovFl(false);

				list.add(item);
			}
		}
		return list;
	}

	public ItemUpcResponse getItemUpc(ItemupcDTO itemUPCDto) {
		// TODO Auto-generated method stub
		ItemUpcResponse itemUPC = null;
		if (itemUPCDto != null) {
			itemUPC = new ItemUpcResponse();

			if (StringUtils.isNotBlank(itemUPCDto.getUpcId()))
				itemUPC.setUpcId(itemUPCDto.getUpcId());

			if (itemUPCDto.getSkuNo() != null && itemUPCDto.getSkuNo() > 0)
				itemUPC.setSkuNo(itemUPCDto.getSkuNo());

			if (itemUPCDto.getUpcModifier() != null && itemUPCDto.getUpcModifier() > 0)
				itemUPC.setUpcModifier(itemUPCDto.getUpcModifier());

			if (itemUPCDto.getUpcSeq() != null && itemUPCDto.getUpcSeq() > 0)
				itemUPC.setUpcSeq(itemUPCDto.getUpcSeq());

			if (StringUtils.isNotBlank(itemUPCDto.getTypeCd()))
				itemUPC.setTypeCd(itemUPCDto.getTypeCd());

			if (itemUPCDto.getPriceAdjAmt() != null && itemUPCDto.getPriceAdjAmt().doubleValue() > 0)
				itemUPC.setPriceAdjAmt(itemUPCDto.getPriceAdjAmt());

			if (StringUtils.isNotBlank(itemUPCDto.getUcc14Uom()))
				itemUPC.setUcc14Uom(itemUPCDto.getUcc14Uom());

			if (itemUPCDto.getUcc14Qty() != null && itemUPCDto.getUcc14Qty().doubleValue() > 0)
				itemUPC.setUcc14Qty(itemUPCDto.getUcc14Qty());
		}

		return itemUPC;
	}

	public ItemNote getItemNoteResponse(ItemNoteDTO dto) {
		ItemNote itemNote = null;

		if (dto != null) {
			itemNote = new ItemNote();

			if (StringUtils.isNotBlank(dto.getBackOfficeHandling()))
				itemNote.setBackOfficeHandlingCodeValue(dto.getBackOfficeHandling());

			if (StringUtils.isNotBlank(dto.getGroupId()))
				itemNote.setGroupId(dto.getGroupId());

			if (StringUtils.isNotBlank(dto.getOwner()))
				itemNote.setOwner(dto.getOwner());

			if (dto.getNoteId() != null)
				itemNote.setNoteId(dto.getNoteId());

			if (StringUtils.isNotBlank(dto.getNoteText()))
				itemNote.setNoteText(dto.getNoteText());

			if (StringUtils.isNotBlank(dto.getPosHandling()))
				itemNote.setPosHandlingCodeValue(dto.getPosHandling());

//			if (dto.getSiteNo() != null)
//				itemNote.setSiteNo(dto.getSiteNo());

			if (dto.getSkuNo() != null)
				itemNote.setSkuNo(dto.getSkuNo());

			if (StringUtils.isNotBlank(dto.getTypeCd()))
				itemNote.setTypeCodeValue(dto.getTypeCd());
		}

		return itemNote;
	}

	public List<ItemNote> getItemNotesList(List<ItemNoteDTO> itemNoteDtos) {
		// TODO Auto-generated method stub
		List<ItemNote> list = null;
		if (!itemNoteDtos.isEmpty()) {
			list = new ArrayList<>();
			for (ItemNoteDTO dto : itemNoteDtos) {
				ItemNote itemNote = new ItemNote();

				if (StringUtils.isNotBlank(dto.getBackOfficeHandling()))
					itemNote.setBackOfficeHandlingCodeValue(dto.getBackOfficeHandling());

				if (StringUtils.isNotBlank(dto.getGroupId()))
					itemNote.setGroupId(dto.getGroupId());

				if (StringUtils.isNotBlank(dto.getOwner()))
					itemNote.setOwner(dto.getOwner());

				if (dto.getNoteId() != null)
					itemNote.setNoteId(dto.getNoteId());

				if (StringUtils.isNotBlank(dto.getNoteText()))
					itemNote.setNoteText(dto.getNoteText());

				if (StringUtils.isNotBlank(dto.getPosHandling()))
					itemNote.setPosHandlingCodeValue(dto.getPosHandling());

//				if (dto.getSiteNo() != null)
//					itemNote.setSiteNo(dto.getSiteNo());

				if (dto.getSkuNo() != null)
					itemNote.setSkuNo(dto.getSkuNo());

				if (StringUtils.isNotBlank(dto.getTypeCd()))
					itemNote.setTypeCodeValue(dto.getTypeCd());

				list.add(itemNote);
			}
		}
		return list;
	}

	public List<ItemAttributeResponse> getItemAttributeList(List<AttributeNameDTO> attributeNameDtos) {
		// TODO Auto-generated method stub
		List<ItemAttributeResponse> list = null;
		if (!attributeNameDtos.isEmpty()) {
			list = new ArrayList<>();
			for (AttributeNameDTO dto : attributeNameDtos) {
				ItemAttributeResponse itemAttribute = new ItemAttributeResponse();

				if (dto.getAttributeId() != null)
					itemAttribute.setAttributeId(dto.getAttributeId());

				if (StringUtils.isNotBlank(dto.getNameText()))
					itemAttribute.setAttributeName(dto.getNameText());

				if (StringUtils.isNotBlank(dto.getHintText()))
					itemAttribute.setAttributeDescription(dto.getHintText());

				if (StringUtils.isNotBlank(dto.getLovFl())) {
					if ("Y".equalsIgnoreCase(dto.getLovFl()))
						itemAttribute.setLovFl(true);
					else
						itemAttribute.setLovFl(false);
				}
				list.add(itemAttribute);
			}
		}
		return list;
	}

	public AttributeNameResponse getItemAttribute(ItemAttributeValueDTO itemAttributeDto) {
		AttributeNameResponse itemAttribute = null;
		if (itemAttributeDto != null) {
			itemAttribute = new AttributeNameResponse();

			if (itemAttributeDto.getAttributeId() != null)
				itemAttribute.setAttributeId(itemAttributeDto.getAttributeId());

			if (StringUtils.isNotBlank(itemAttributeDto.getAttributeValue()))
				itemAttribute.setAttributeValue(itemAttributeDto.getAttributeValue());

			if (StringUtils.isNotBlank(itemAttributeDto.getFunctionalAreaCd()))
				itemAttribute.setFunctionalAreaCd(itemAttributeDto.getFunctionalAreaCd());

			if (itemAttributeDto.getSkuNo() != null)
				itemAttribute.setSkuNo(itemAttributeDto.getSkuNo());
		}
		return itemAttribute;
	}

	public List<ItemAttributeValueResponse> getItemAttributeValues(List<AttributeListValueAllDTO> attributeValueDtos) {
		// TODO Auto-generated method stub
		List<ItemAttributeValueResponse> list = null;
		if (attributeValueDtos != null && !attributeValueDtos.isEmpty()) {
			list = new ArrayList<>();
			for (AttributeListValueAllDTO dto : attributeValueDtos) {
				ItemAttributeValueResponse itemAttributeValue = new ItemAttributeValueResponse();

				if (dto.getAttributeId() != null)
					itemAttributeValue.setAttributeId(dto.getAttributeId());

				if (StringUtils.isNotBlank(dto.getCodeMeaning()))
					itemAttributeValue.setCodeMeaning(dto.getCodeMeaning());

				if (StringUtils.isNotBlank(dto.getCodeValue()))
					itemAttributeValue.setCodeValue(dto.getCodeValue());

				if (dto.getAttributeName() != null) {
					if (StringUtils.isNotBlank(dto.getAttributeName().getHintText()))
						itemAttributeValue.setHintText(dto.getAttributeName().getHintText());

					if ("Y".equalsIgnoreCase(dto.getAttributeName().getLovFl()))
						itemAttributeValue.setLovFl(true);
					else
						itemAttributeValue.setLovFl(false);

					if (StringUtils.isNotBlank(dto.getAttributeName().getNameText()))
						itemAttributeValue.setNameText(dto.getAttributeName().getNameText());
				}

				if (dto.getOwnerId() != null)
					itemAttributeValue.setOwnerId(dto.getOwnerId());
				list.add(itemAttributeValue);
			}
		}
		return list;
	}

	public UpdatedAttributeResponse getAttributeName(AttributeNameDTO attrDto) {
		UpdatedAttributeResponse response = null;
		if (attrDto != null) {
			response = new UpdatedAttributeResponse();

			if (attrDto.getAttributeId() != null)
				response.setAttributeId(attrDto.getAttributeId());

			if (StringUtils.isNotBlank(attrDto.getHintText()))
				response.setHintText(attrDto.getHintText());

			if ("Y".equalsIgnoreCase(attrDto.getLovFl()))
				response.setLovFl(true);
			else
				response.setLovFl(false);

			if (StringUtils.isNotBlank(attrDto.getNameText()))
				response.setNameText(attrDto.getNameText());
		}
		return response;
	}

	public Invbysit getInvBySit(InvbysitDTO invbysitDTO) {
		Invbysit invbysit = null;
		if(invbysitDTO != null) {
			invbysit = new Invbysit();
			
			if(invbysitDTO.getPrice1() != null)
			invbysit.setPrice1(invbysitDTO.getPrice1());
			
			if(invbysitDTO.getPrice2() != null)
			invbysit.setPrice2(invbysitDTO.getPrice2());
			
			if(invbysitDTO.getPrice3() != null)
			invbysit.setPrice3(invbysitDTO.getPrice3());
			
			if(invbysitDTO.getPrice4() != null)
			invbysit.setPrice4(invbysitDTO.getPrice4());
			
			if(invbysitDTO.getPrice5() != null)
			invbysit.setPrice5(invbysitDTO.getPrice5());
			
			if(invbysitDTO.getPrice6() != null)
			invbysit.setPrice6(invbysitDTO.getPrice6());
			
			if(invbysitDTO.getPrice7() != null)
			invbysit.setPrice7(invbysitDTO.getPrice7());
			
			if(invbysitDTO.getPrice8() != null)
			invbysit.setPrice8(invbysitDTO.getPrice8());
			
			if(invbysitDTO.getPrice9() != null)
			invbysit.setPrice9(invbysitDTO.getPrice9());
			
			if(invbysitDTO.getPrice10() != null)
			invbysit.setPrice10(invbysitDTO.getPrice10());
			
			if(invbysitDTO.getPriceQty1() != null)
			invbysit.setPriceQty1(invbysitDTO.getPriceQty1());
			
			if(invbysitDTO.getPriceQty2() != null)
			invbysit.setPriceQty2(invbysitDTO.getPriceQty2());
			
			if(invbysitDTO.getPriceQty3() != null)
			invbysit.setPriceQty3(invbysitDTO.getPriceQty3());
			
			if(invbysitDTO.getPriceQty4() != null)
			invbysit.setPriceQty4(invbysitDTO.getPriceQty4());
			
			if(invbysitDTO.getPriceQty5() != null)
			invbysit.setPriceQty5(invbysitDTO.getPriceQty5());
			
			if(invbysitDTO.getPriceQty6() != null)
			invbysit.setPriceQty6(invbysitDTO.getPriceQty6());
			
			if(invbysitDTO.getPriceQty7() != null)
			invbysit.setPriceQty7(invbysitDTO.getPriceQty7());
			
			if(invbysitDTO.getPriceQty8() != null)
			invbysit.setPriceQty8(invbysitDTO.getPriceQty8());
			
			if(invbysitDTO.getPriceQty9() != null)
			invbysit.setPriceQty9(invbysitDTO.getPriceQty9());
			
			if(invbysitDTO.getPriceQty10() != null)
			invbysit.setPriceQty10(invbysitDTO.getPriceQty10());
			
			if(invbysitDTO.getPkgPrice1() != null)
			invbysit.setPkgPrice1(invbysitDTO.getPkgPrice1());
			
			if(invbysitDTO.getPkgPrice2() != null)
			invbysit.setPkgPrice2(invbysitDTO.getPkgPrice2());
			
			if(invbysitDTO.getPkgPrice3() != null)
			invbysit.setPkgPrice3(invbysitDTO.getPkgPrice3());
			
			if(invbysitDTO.getPkgPriceQty1() != null)
			invbysit.setPkgPriceQty1(invbysitDTO.getPkgPriceQty1());
			
			if(invbysitDTO.getPkgPriceQty2() != null)
			invbysit.setPkgPriceQty2(invbysitDTO.getPkgPriceQty2());
			
			if(invbysitDTO.getPkgPriceQty3() != null)
			invbysit.setPkgPriceQty3(invbysitDTO.getPkgPriceQty3());
			
			if(invbysitDTO.getAdditionalTax() != null)
			invbysit.setAdditionalTax(invbysitDTO.getAdditionalTax());
			
			if(StringUtils.isNotBlank(invbysitDTO.getAllowPchgFl()))
			invbysit.setAllowPchgFl(invbysitDTO.getAllowPchgFl());
			
			if(StringUtils.isNotBlank(invbysitDTO.getClassCode()))
			invbysit.setClassCode(invbysitDTO.getClassCode());
			
			if(invbysitDTO.getCommissionCd() != null)
			invbysit.setCommissionCd(invbysitDTO.getCommissionCd());
			
			if(invbysitDTO.getDateChanged() != null)
			invbysit.setDateChanged(invbysitDTO.getDateChanged());
			
			if(invbysitDTO.getDefaultDcSkuLevel() != null)
			invbysit.setDefaultDcSkuLevel(invbysitDTO.getDefaultDcSkuLevel());
			
			if(StringUtils.isNotBlank(invbysitDTO.getDiscontinuedBy()))
			invbysit.setDiscontinuedBy(invbysitDTO.getDiscontinuedBy());
			
			if(invbysitDTO.getDiscontinuedDt() != null)
			invbysit.setDiscontinuedDt(invbysitDTO.getDiscontinuedDt());
			
			if(StringUtils.isNotBlank(invbysitDTO.getDiscontinuedStFl()))
			invbysit.setDiscontinuedStFl(invbysitDTO.getDiscontinuedStFl());
			
			if(StringUtils.isNotBlank(invbysitDTO.getDownloadFl()))
			invbysit.setDownloadFl(invbysitDTO.getDownloadFl());
			
			if(StringUtils.isNotBlank(invbysitDTO.getDownloadNToYFl()))
			invbysit.setDownloadNToYFl(invbysitDTO.getDownloadNToYFl());
			
			if(invbysitDTO.getEpoPct() != null)
			invbysit.setEpoPct(invbysitDTO.getEpoPct());
			
			if(invbysitDTO.getHaloGm() != null)
			invbysit.setHaloGm(invbysitDTO.getHaloGm());
			
			if(StringUtils.isNotBlank(invbysitDTO.getItemizerStatusId()))
			invbysit.setItemizerStatusId(invbysitDTO.getItemizerStatusId());
			
			if(invbysitDTO.getLaloGm() != null)
			invbysit.setLaloGm(invbysitDTO.getLaloGm());
			
			if(invbysitDTO.getLastRcvdDt() != null)
			invbysit.setLastRcvdDt(invbysitDTO.getLastRcvdDt());
			
			if(invbysitDTO.getLastReplenishmentDt() != null)
			invbysit.setLastReplenishmentDt(invbysitDTO.getLastReplenishmentDt());
			
			if(invbysitDTO.getMaxOrderQty() != null)
			invbysit.setMaxOrderQty(invbysitDTO.getMaxOrderQty());
			
			if(invbysitDTO.getMsrpPrice() != null)
			invbysit.setMsrpPrice(invbysitDTO.getMsrpPrice());
			
			if(invbysitDTO.getMsrpQty() != null)
			invbysit.setMsrpQty(invbysitDTO.getMsrpQty());
			
			if(invbysitDTO.getMsrpUnitPrice() != null)
			invbysit.setMsrpUnitPrice(invbysitDTO.getMsrpUnitPrice());
			
			if(StringUtils.isNotBlank(invbysitDTO.getNewSkuStatusId()))
			invbysit.setNewSkuStatusId(invbysitDTO.getNewSkuStatusId());
			
			if(StringUtils.isNotBlank(invbysitDTO.getNonDiscountFl()))
			invbysit.setNonDiscountFl(invbysitDTO.getNonDiscountFl());
			
			if(StringUtils.isNotBlank(invbysitDTO.getPosSkuStatusId()))
			invbysit.setPosSkuStatusId(invbysitDTO.getPosSkuStatusId());
			
			if(invbysitDTO.getPriorReorderPt() != null)
			invbysit.setPriorReorderPt(invbysitDTO.getPriorReorderPt());
			
			if(invbysitDTO.getPriorReorderTo() != null)
			invbysit.setPriorReorderTo(invbysitDTO.getPriorReorderTo());
			
			if(invbysitDTO.getProductEndDt() != null)
			invbysit.setProductEndDt(invbysitDTO.getProductEndDt());
			
			if(invbysitDTO.getProductStartDt() != null)
			invbysit.setProductStartDt(invbysitDTO.getProductStartDt());
			
			if(invbysitDTO.getPromoCommissionCd() != null)
			invbysit.setPromoCommissionCd(invbysitDTO.getPromoCommissionCd());
			
			if(invbysitDTO.getPromoKeyNo() != null)
			invbysit.setPromoKeyNo(invbysitDTO.getPromoKeyNo());
			
			if(invbysitDTO.getQtyLastRcvd() != null)
			invbysit.setQtyLastRcvd(invbysitDTO.getQtyLastRcvd());
			
			if(StringUtils.isNotBlank(invbysitDTO.getReorderLockedFl()))
			invbysit.setReorderLockedFl(invbysitDTO.getReorderLockedFl());
			
			if(StringUtils.isNotBlank(invbysitDTO.getReorderNowFl()))
			invbysit.setReorderNowFl(invbysitDTO.getReorderNowFl());
			
			if(invbysitDTO.getReorderPt() != null)
			invbysit.setReorderPt(invbysitDTO.getReorderPt());
			
			if(invbysitDTO.getReorderTo() != null)
			invbysit.setReorderTo(invbysitDTO.getReorderTo());
			
			if(StringUtils.isNotBlank(invbysitDTO.getReplenishmentFl()))
			invbysit.setReplenishmentFl(invbysitDTO.getReplenishmentFl());
			
			if(StringUtils.isNotBlank(invbysitDTO.getRestrictSaleId()))
			invbysit.setRestrictSaleId(invbysitDTO.getRestrictSaleId());
			
			if(StringUtils.isNotBlank(invbysitDTO.getSkuStatusId()))
			invbysit.setSkuStatusId(invbysitDTO.getSkuStatusId());
			
			if(invbysitDTO.getStockingDt() != null)
			invbysit.setStockingDt(invbysitDTO.getStockingDt());
			
			if(StringUtils.isNotBlank(invbysitDTO.getSupersededFl()))
			invbysit.setSupersededFl(invbysitDTO.getSupersededFl());
			
			if(StringUtils.isNotBlank(invbysitDTO.getTareTableNo()))
			invbysit.setTareTableNo(invbysitDTO.getTareTableNo());
			
			if(invbysitDTO.getWarehouseInnerPack() != null)
			invbysit.setWarehouseInnerPack(invbysitDTO.getWarehouseInnerPack());
			
			if(StringUtils.isNotBlank(invbysitDTO.getVelocityCd()))
			invbysit.setVelocityCd(invbysitDTO.getVelocityCd());
			
			if(invbysitDTO.getVelocityChangeDt() != null)
			invbysit.setVelocityChangeDt(invbysitDTO.getVelocityChangeDt());
			
			if(StringUtils.isNotBlank(invbysitDTO.getVelocityLockedFl()))
			invbysit.setVelocityLockedFl(invbysitDTO.getVelocityLockedFl());
			
			if(invbysitDTO.getQtyInTransit() != null)
			invbysit.setQtyInTransit(invbysitDTO.getQtyInTransit());
			
			if(invbysitDTO.getQtyOnBackord() != null)
			invbysit.setQtyOnBackord(invbysitDTO.getQtyOnBackord());
			
			if(invbysitDTO.getQtyOnHand() != null)
			invbysit.setQtyOnHand(invbysitDTO.getQtyOnHand());
			
			if(invbysitDTO.getQtyOnOrd() != null)
			invbysit.setQtyOnOrd(invbysitDTO.getQtyOnOrd());
			
			if(invbysitDTO.getQtyRsvd() != null)
			invbysit.setQtyRsvd(invbysitDTO.getQtyRsvd());
			
		}
		return invbysit;
	}

}
