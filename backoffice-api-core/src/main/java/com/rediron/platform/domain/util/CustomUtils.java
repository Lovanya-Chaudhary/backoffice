package com.rediron.platform.domain.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.rediron.platform.domain.model.response.DomainRefCode;
import com.rediron.platform.domain.model.response.GLCategoryInfoResponse;
import com.rediron.platform.domain.model.response.TenderCertificateInfoResponse;
import com.rediron.platform.domain.response.AttributeListValuesInfoResponse;
import com.rediron.platform.domain.response.DealsInfoResponse;
import com.rediron.platform.domain.response.DuplicateItemInfoResponse;
import com.rediron.platform.domain.response.GlobalInfoResponse;
import com.rediron.platform.domain.response.InventorySearchInfoResponse;
import com.rediron.platform.domain.response.ItemTypeCodeResponse;
import com.rediron.platform.domain.response.KitInfoResponse;
import com.rediron.platform.domain.response.SiteGroupInfoResponse;
import com.rediron.platform.domain.response.SiteInfoResponse;
import com.rediron.platform.domain.response.StatesProvinceInfoResponse;
import com.rediron.platform.domain.response.VendorDetailsInfoResponse;
import com.tomax.ar.dto.GlcatDTO;
import com.tomax.config.attribute.dto.AttributeListValueAllDTO;
import com.tomax.config.domainRef.dto.DomainRefCodeDTO;
import com.tomax.deal.dto.DealdtlLiteDTO;
import com.tomax.inventory.dto.DeptgrpDTO;
import com.tomax.inventory.dto.ItemCreationDefaultsHdrDTO;
import com.tomax.inventory.dto.TenderCertificateProgramDTO;
import com.tomax.inventory.dto.custom.InventoryGlobalInfoDTO;
import com.tomax.inventory.dto.custom.InventorySearchResultsDTO;
import com.tomax.locale.dto.GeographicRegionDTO;
import com.tomax.site.dto.GroupsAllDTO;
import com.tomax.site.dto.SiteDTO;
import com.tomax.vendor.dto.VendorDTO;

public class CustomUtils {

	public static DealsInfoResponse mapToDealsInfoResponse(DealdtlLiteDTO dealDTO) {
		// TODO Auto-generated method stub

		DealsInfoResponse response = new DealsInfoResponse();

		if (dealDTO != null) {

			if (dealDTO.getAmtOff() != null && dealDTO.getAmtOff().doubleValue() > 0)
				response.setAmtOff(dealDTO.getAmtOff());

			if (dealDTO.getCoopAmtOff() != null && dealDTO.getCoopAmtOff().doubleValue() > 0)
				response.setCoopAmtOff(dealDTO.getCoopAmtOff());

			if (dealDTO.getDealNo() != null)
				response.setDealNo(dealDTO.getDealNo());

			if (StringUtils.isNotBlank(dealDTO.getDetailType()))
				response.setDetailType(dealDTO.getDetailType());

			if (dealDTO.getMaxQty() != null && dealDTO.getMaxQty().doubleValue() > 0)
				response.setMaxQty(dealDTO.getMaxQty());

			if (dealDTO.getPackQty() != null && dealDTO.getPackQty().doubleValue() > 0)
				response.setPackQty(dealDTO.getPackQty());

			if (StringUtils.isNotBlank(dealDTO.getPackType()))
				response.setPackType(dealDTO.getPackType());

			if (dealDTO.getPctOff() != null && dealDTO.getPctOff().doubleValue() > 0)
				response.setPctOff(dealDTO.getPctOff());

			if (dealDTO.getSeqNo() != null)
				response.setSeqNo(dealDTO.getSeqNo());

			if (dealDTO.getSiteNo() != null && dealDTO.getSiteNo() > 0)
				response.setSiteNo(dealDTO.getSiteNo());

			if (StringUtils.isNotBlank(dealDTO.getVendorItem()))
				response.setVendorItem(dealDTO.getVendorItem());
		}
		return response;
	}

	public static SiteGroupInfoResponse mapToSiteGroupInfoResponse(GroupsAllDTO groupsAllDTO) {
		// TODO Auto-generated method stub

		SiteGroupInfoResponse response = new SiteGroupInfoResponse();

		if (groupsAllDTO != null) {

			if (StringUtils.isNotBlank(groupsAllDTO.getAltGroupId()))
				response.setAltGroupId(groupsAllDTO.getAltGroupId());

			if (StringUtils.isNotBlank(groupsAllDTO.getDescription()))
				response.setDescription(groupsAllDTO.getDescription());

			if (StringUtils.isNotBlank(groupsAllDTO.getGroupId()))
				response.setGroupId(groupsAllDTO.getGroupId());

			if (StringUtils.isNotBlank(groupsAllDTO.getGroupOwner()))
				response.setGroupOwner(groupsAllDTO.getGroupOwner());

			if (StringUtils.isNotBlank(groupsAllDTO.getParentGroupId()))
				response.setParentGroupId(groupsAllDTO.getParentGroupId());

			if (StringUtils.isNotBlank(groupsAllDTO.getType()))
				response.setType(groupsAllDTO.getType());

			if (StringUtils.isNotBlank(groupsAllDTO.getTypeName()))
				response.setTypeName(groupsAllDTO.getTypeName());

		}
		return response;
	}

	public static List<DuplicateItemInfoResponse> mapToDuplicateItemsResponse(
			List<InventorySearchResultsDTO> duplicates) {
		// TODO Auto-generated method stub

		List<DuplicateItemInfoResponse> listResponse = new ArrayList<DuplicateItemInfoResponse>();

		if (!duplicates.isEmpty()) {

			for (InventorySearchResultsDTO invDTO : duplicates) {

				DuplicateItemInfoResponse response = new DuplicateItemInfoResponse();

				if (invDTO.getClassNo() != null && invDTO.getClassNo() > 0)
					response.setClassNo(invDTO.getClassNo());

				if (invDTO.getDeptNo() != null && invDTO.getDeptNo() > 0)
					response.setDeptNo(invDTO.getDeptNo());

				if (StringUtils.isNotBlank(invDTO.getItemDescription()))
					response.setItemDescription(invDTO.getItemDescription());

				if (invDTO.getLineNo() != null && invDTO.getLineNo() > 0)
					response.setLineNo(invDTO.getLineNo());

				if (StringUtils.isNotBlank(invDTO.getLookupDesc()))
					response.setLookupDesc(invDTO.getLookupDesc());

				if (StringUtils.isNotBlank(invDTO.getMfgCd()))
					response.setMfgCd(invDTO.getMfgCd());

				if (StringUtils.isNotBlank(invDTO.getPkgDesc()))
					response.setPkgDesc(invDTO.getPkgDesc());

				if (StringUtils.isNotBlank(invDTO.getReceiptDesc()))
					response.setReceiptDesc(invDTO.getReceiptDesc());

				if (invDTO.getReportFactor() != null && invDTO.getReportFactor().doubleValue() > 0)
					response.setReportFactor(invDTO.getReportFactor());

				if (StringUtils.isNotBlank(invDTO.getReportUm()))
					response.setReportUm(invDTO.getReportUm());

				if (StringUtils.isNotBlank(invDTO.getSellUm()))
					response.setSellUm(invDTO.getSellUm());

				if (invDTO.getSkuNo() != null && invDTO.getSkuNo() > 0)
					response.setSkuNumber(invDTO.getSkuNo());

				if (StringUtils.isNotBlank(invDTO.getUpcId()))
					response.setUpcId(invDTO.getUpcId());

				if (StringUtils.isNotBlank(invDTO.getVendorId()))
					response.setVendorId(invDTO.getVendorId());

				if (StringUtils.isNotBlank(invDTO.getVendorItem()))
					response.setVendorItem(invDTO.getVendorItem());

				listResponse.add(response);
			}
		}
		return listResponse;
	}

	public static GlobalInfoResponse mapToGlobalInfoResponse(InventoryGlobalInfoDTO globalDTO) {
		// TODO Auto-generated method stub
		GlobalInfoResponse response = new GlobalInfoResponse();

		if (globalDTO != null) {

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getCaptureOrderNoFl()))
				response.setCaptureOrderNoFl(globalDTO.getInvtoryDto().getCaptureOrderNoFl());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getCaptureSerialNoFl()))
				response.setCaptureSerialNoFl(globalDTO.getInvtoryDto().getCaptureSerialNoFl());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getCatchWeightFl()))
				response.setCatchWeightFl(globalDTO.getInvtoryDto().getCatchWeightFl());

			if (globalDTO.getInvtoryDto().getChangeDt() != null)
				response.setChangeDt(globalDTO.getInvtoryDto().getChangeDt());

			if (globalDTO.getInvtoryDto().getClassNo() != null && globalDTO.getInvtoryDto().getClassNo() > 0)
				response.setClassNo(globalDTO.getInvtoryDto().getClassNo());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getCoreFl()))
				response.setCoreFl(globalDTO.getInvtoryDto().getCoreFl());

			if (globalDTO.getInvtoryDto().getCreateDt() != null)
				response.setCreateDt(globalDTO.getInvtoryDto().getCreateDt());

			if (globalDTO.getDeptGrpNo() != null && globalDTO.getDeptGrpNo() > 0)
				response.setDeptGrpNo(globalDTO.getDeptGrpNo());

			if (globalDTO.getInvtoryDto().getDeptNo() != null && globalDTO.getInvtoryDto().getDeptNo() > 0)
				response.setDeptNo(globalDTO.getInvtoryDto().getDeptNo());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getDiscontinuedFl()))
				response.setDiscontinuedFl(globalDTO.getInvtoryDto().getDiscontinuedFl());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getDownloadFl()))
				response.setDownloadFl(globalDTO.getInvtoryDto().getDownloadFl());

			if (globalDTO.getInvtoryDto().getEquivFactor() != null
					&& globalDTO.getInvtoryDto().getEquivFactor().doubleValue() > 0)
				response.setEquivFactor(globalDTO.getInvtoryDto().getEquivFactor());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getEquivUm()))
				response.setEquivUm(globalDTO.getInvtoryDto().getEquivUm());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getFlexibleItemFl()))
				response.setFlexibleItemFl(globalDTO.getInvtoryDto().getFlexibleItemFl());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getDescription()))
				response.setItemDescription(globalDTO.getInvtoryDto().getDescription());

			response.setKitHdrFl(globalDTO.getKitHdrFl());

			if (globalDTO.getKitSkuNo() != null && globalDTO.getKitSkuNo() > 0)
				response.setKitSkuNo(globalDTO.getKitSkuNo());

			if (StringUtils.isNotBlank(globalDTO.getKitTypeCd()))
				response.setKitTypeCd(globalDTO.getKitTypeCd());

			if (globalDTO.getInvtoryDto().getLcuQty() != null
					&& globalDTO.getInvtoryDto().getLcuQty().doubleValue() > 0)
				response.setLcuQty(globalDTO.getInvtoryDto().getLcuQty());

			if (globalDTO.getInvtoryDto().getLineNo() != null && globalDTO.getInvtoryDto().getLineNo() > 0)
				response.setLineNo(globalDTO.getInvtoryDto().getLineNo());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getLookupDesc()))
				response.setLookupDesc(globalDTO.getInvtoryDto().getLookupDesc());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getNegativePriceFl()))
				response.setNegativePriceFl(globalDTO.getInvtoryDto().getNegativePriceFl());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getNonInventoryFl()))
				response.setNonInventoryFl(globalDTO.getInvtoryDto().getNonInventoryFl());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getObsoleteFl()))
				response.setObsoleteFl(globalDTO.getInvtoryDto().getObsoleteFl());

			if (globalDTO.getInvtoryDto().getPeriodsHistory() != null
					&& globalDTO.getInvtoryDto().getPeriodsHistory() > 0)
				response.setPeriodsHistory(globalDTO.getInvtoryDto().getPeriodsHistory());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getPkgDesc()))
				response.setPkgDesc(globalDTO.getInvtoryDto().getPkgDesc());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getPrimaryVendorItem()))
				response.setPrimaryVendorItem(globalDTO.getInvtoryDto().getPrimaryVendorItem());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getReceiptDesc()))
				response.setReceiptDesc(globalDTO.getInvtoryDto().getReceiptDesc());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getReorderFl()))
				response.setReorderFl(globalDTO.getInvtoryDto().getReorderFl());

			if (globalDTO.getInvtoryDto().getReportFactor() != null
					&& globalDTO.getInvtoryDto().getReportFactor().doubleValue() > 0)
				response.setReportFactor(globalDTO.getInvtoryDto().getReportFactor());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getWeighedItemFl()))
				response.setWeighedItemFl(globalDTO.getInvtoryDto().getWeighedItemFl());

			if (globalDTO.getInvtoryDto().getWeeksHistory() != null && globalDTO.getInvtoryDto().getWeeksHistory() > 0)
				response.setWeeksHistory(globalDTO.getInvtoryDto().getWeeksHistory());

			if (globalDTO.getInvtoryDto().getWarehouseInnerPack() != null
					&& globalDTO.getInvtoryDto().getWarehouseInnerPack().doubleValue() > 0)
				response.setWarehouseInnerPack(globalDTO.getInvtoryDto().getWarehouseInnerPack());

			if (StringUtils.isNotBlank(globalDTO.getVendorItem()))
				response.setVendorItem(globalDTO.getVendorItem());

			if (StringUtils.isNotBlank(globalDTO.getUpcId()))
				response.setUpcId(globalDTO.getUpcId());

			if (globalDTO.getInvtoryDto().getUnitWeight() != null
					&& globalDTO.getInvtoryDto().getUnitWeight().doubleValue() > 0)
				response.setUnitWeight(globalDTO.getInvtoryDto().getUnitWeight());

			if (globalDTO.getInvtoryDto().getUnitCube() != null
					&& globalDTO.getInvtoryDto().getUnitCube().doubleValue() > 0)
				response.setUnitCube(globalDTO.getInvtoryDto().getUnitCube());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getSupplyItemFl()))
				response.setSupplyItemFl(globalDTO.getInvtoryDto().getSupplyItemFl());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getSubInvFl()))
				response.setSubInvFl(globalDTO.getInvtoryDto().getSubInvFl());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getStoreCpnFl()))
				response.setStoreCpnFl(globalDTO.getInvtoryDto().getStoreCpnFl());

			if (globalDTO.getInvtoryDto().getSkuNo() != null && globalDTO.getInvtoryDto().getSkuNo() > 0)
				response.setSkuNo(globalDTO.getInvtoryDto().getSkuNo());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getSellUm()))
				response.setSellUm(globalDTO.getInvtoryDto().getSellUm());

			if (StringUtils.isNotBlank(globalDTO.getInvtoryDto().getReportUm()))
				response.setReportUm(globalDTO.getInvtoryDto().getReportUm());

		}
		return response;
	}

	public static KitInfoResponse mapToKitResponse(InventoryGlobalInfoDTO invDTO) {
		// TODO Auto-generated method stub

		KitInfoResponse response = new KitInfoResponse();

		if (invDTO != null) {

			if (invDTO.getKitComponentFl() != null)
				response.setKitComponentFl(invDTO.getKitComponentFl());

			if (invDTO.getKitHdrFl() != null)
				response.setKitHdrFl(invDTO.getKitHdrFl());

			if (StringUtils.isNotBlank(invDTO.getInvtoryDto().getKitMemberFl()))
				response.setKitMemberFl(invDTO.getInvtoryDto().getKitMemberFl());

			if (invDTO.getKitSkuNo() != null && invDTO.getKitSkuNo() > 0)
				response.setKitSkuNo(invDTO.getKitSkuNo());

			if (StringUtils.isNotBlank(invDTO.getKitTypeCd()))
				response.setKitTypeCd(invDTO.getKitTypeCd());

			if (StringUtils.isNotBlank(invDTO.getInvtoryDto().getMfgCd()))
				response.setMfgCd(invDTO.getInvtoryDto().getMfgCd());

			if (StringUtils.isNotBlank(invDTO.getInvtoryDto().getPrimaryVendorItem()))
				response.setPrimaryVendorItem(invDTO.getInvtoryDto().getPrimaryVendorItem());

			if (StringUtils.isNotBlank(invDTO.getUpcId()))
				response.setUpcId(invDTO.getUpcId());

		}

		return response;
	}

	public static List<ItemTypeCodeResponse> mapToItemTypeCodeResponse(
			List<ItemCreationDefaultsHdrDTO> sortedItemTypes) {
		// TODO Auto-generated method stub

		List<ItemTypeCodeResponse> listResponse = new ArrayList<ItemTypeCodeResponse>();

		if (!sortedItemTypes.isEmpty()) {

			for (ItemCreationDefaultsHdrDTO itemDTO : sortedItemTypes) {

				ItemTypeCodeResponse itemTypeCode = new ItemTypeCodeResponse();

				if (StringUtils.isNotBlank(itemDTO.getDisplayLabel()))
					itemTypeCode.setDisplayLabel(itemDTO.getDisplayLabel());

				if (StringUtils.isNotBlank(itemDTO.getItemTypeCd()))
					itemTypeCode.setItemTypeCd(itemDTO.getItemTypeCd());

				listResponse.add(itemTypeCode);
			}
		}
		return listResponse;
	}

	public static SiteInfoResponse mapToSiteInfoResponse(SiteDTO siteDTO) {
		// TODO Auto-generated method stub
		SiteInfoResponse response = new SiteInfoResponse();

		if (siteDTO.getIdentifier().getSiteNo() > 0) {

			if (StringUtils.isNotBlank(siteDTO.getAdr1()))
				response.setAdr1(siteDTO.getAdr1());

			if (StringUtils.isNotBlank(siteDTO.getAdr2()))
				response.setAdr2(siteDTO.getAdr2());

			if (StringUtils.isNotBlank(siteDTO.getCity()))
				response.setCity(siteDTO.getCity());

			if (StringUtils.isNotBlank(siteDTO.getCompanyCd()))
				response.setCompanyCd(siteDTO.getCompanyCd());

			if (StringUtils.isNotBlank(siteDTO.getCountry()))
				response.setCountry(siteDTO.getCountry());

			if (StringUtils.isNotBlank(siteDTO.getCounty()))
				response.setCounty(siteDTO.getCounty());

			if (StringUtils.isNotBlank(siteDTO.getFullName()))
				response.setFullName(siteDTO.getFullName());

			if (StringUtils.isNotBlank(siteDTO.getName()))
				response.setName(siteDTO.getName());

			if (siteDTO.getOwnerId() != null && siteDTO.getOwnerId() > 0)
				response.setOwnerId(siteDTO.getOwnerId());

			if (StringUtils.isNotBlank(siteDTO.getSiteActive()))
				response.setSiteActive(siteDTO.getSiteActive());

			if (siteDTO.getSiteNo() != null && siteDTO.getSiteNo() > 0)
				response.setSiteNo(siteDTO.getSiteNo());

			if (StringUtils.isNotBlank(siteDTO.getState()))
				response.setState(siteDTO.getState());

			if (StringUtils.isNotBlank(siteDTO.getWebEnabledFl()))
				response.setWebEnabledFl(siteDTO.getWebEnabledFl());

			if (StringUtils.isNotBlank(siteDTO.getZip()))
				response.setZip(siteDTO.getZip());
		}

		return response;
	}

	public static List<InventorySearchInfoResponse> mapToInventorySearchList(List<InventorySearchResultsDTO> invItems) {
		// TODO Auto-generated method stub

		List<InventorySearchInfoResponse> list = new ArrayList<>();

		if (!invItems.isEmpty()) {

			for (InventorySearchResultsDTO dto : invItems) {

				InventorySearchInfoResponse response = new InventorySearchInfoResponse();

				if (dto.getSkuNo() != null && dto.getSkuNo() > 0)
					response.setSkuNo(dto.getSkuNo());

				if (StringUtils.isNotBlank(dto.getAttribute1Value()))
					response.setAttribute1Value(dto.getAttribute1Value());

				if (StringUtils.isNotBlank(dto.getAttribute2Value()))
					response.setAttribute2Value(dto.getAttribute2Value());

				if (StringUtils.isNotBlank(dto.getAttribute3Value()))
					response.setAttribute3Value(dto.getAttribute3Value());

				if (dto.getClassNo() != null && dto.getClassNo() > 0)
					response.setClassNo(dto.getClassNo());

				if (StringUtils.isNotBlank(dto.getCode1Id()))
					response.setCode1Id(dto.getCode1Id());

				if (StringUtils.isNotBlank(dto.getCode2Id()))
					response.setCode2Id(dto.getCode2Id());

				if (StringUtils.isNotBlank(dto.getCode3Id()))
					response.setCode3Id(dto.getCode3Id());

				if (dto.getDeptgrpNo() != null && dto.getDeptgrpNo() > 0)
					response.setDeptgrpNo(dto.getDeptgrpNo());

				if (dto.getDeptNo() != null && dto.getDeptNo() > 0)
					response.setDeptNo(dto.getDeptNo());

				if (StringUtils.isNotBlank(dto.getItemDescription()))
					response.setItemDescription(dto.getItemDescription());

				if (dto.getLineNo() != null && dto.getLineNo() > 0)
					response.setLineNo(dto.getLineNo());

				if (dto.getLinkSkuNo() != null && dto.getLinkSkuNo() > 0)
					response.setLinkSkuNo(dto.getLinkSkuNo());

				if (StringUtils.isNotBlank(dto.getLookupDesc()))
					response.setLookupDesc(dto.getLookupDesc());

				// boolean not Boolean hence no check needed
				response.setMasterSku(dto.isMasterSku());

				if (StringUtils.isNotBlank(dto.getMfgCd()))
					response.setMfgCd(dto.getMfgCd());

				if (StringUtils.isNotBlank(dto.getPkgDesc()))
					response.setPkgDesc(dto.getPkgDesc());

				if (StringUtils.isNotBlank(dto.getReceiptDesc()))
					response.setReceiptDesc(dto.getReceiptDesc());

				if (dto.getReportFactor() != null && dto.getReportFactor().doubleValue() > 0)
					response.setReportFactor(dto.getReportFactor());

				if (StringUtils.isNotBlank(dto.getReportUm()))
					response.setReportUm(dto.getReportUm());

				if (StringUtils.isNotBlank(dto.getSellUm()))
					response.setSellUm(dto.getSellUm());

				if (StringUtils.isNotBlank(dto.getUpcId()))
					response.setUpcId(dto.getUpcId());

				if (StringUtils.isNotBlank(dto.getVendorId()))
					response.setVendorId(dto.getVendorId());

				if (StringUtils.isNotBlank(dto.getVendorItem()))
					response.setVendorItem(dto.getVendorItem());

				list.add(response);
			}
		}
		return list;
	}

	public static List<DomainRefCode> mapToDomainRefCodes(List<DomainRefCodeDTO> listOfCodes) {
		// TODO Auto-generated method stub

		List<DomainRefCode> list = new ArrayList<>();

		if (!listOfCodes.isEmpty()) {
			for (DomainRefCodeDTO dto : listOfCodes) {

				DomainRefCode response = new DomainRefCode();

				if (StringUtils.isNotBlank(dto.getCodeMeaning()))
					response.setCodeMeaning(dto.getCodeMeaning());

				if (StringUtils.isNotBlank(dto.getIdentifier().getCodeValue()))
					response.setCodeValue(dto.getIdentifier().getCodeValue());

				if (dto.getIdentifier().getDomainRefId() != null)
					response.setDomainRefId(dto.getIdentifier().getDomainRefId());

				list.add(response);
			}
		}

		return list;
	}

	public static Boolean stringToBool(String value) {
		if (value == null)
			return false;
		if ("Y".equalsIgnoreCase(value))
			return true;
		return false;
	}

	public static VendorDetailsInfoResponse mapToVendorDetails(VendorDTO vendorDto) {
		// TODO Auto-generated method stub

		VendorDetailsInfoResponse response = new VendorDetailsInfoResponse();

		if (StringUtils.isNotBlank(vendorDto.getIdentifier().getVendorId()))
			response.setVendorId(vendorDto.getIdentifier().getVendorId());

		if (StringUtils.isNotBlank(vendorDto.getName()))
			response.setVendorName(vendorDto.getName());

		return response;
	}

	public static List<TenderCertificateInfoResponse> mapToCertificateResponse(List<TenderCertificateProgramDTO> list) {
		// TODO Auto-generated method stub
		List<TenderCertificateInfoResponse> listResponse = new ArrayList<>();

		if (!list.isEmpty()) {

			for (TenderCertificateProgramDTO dto : list) {

				TenderCertificateInfoResponse response = new TenderCertificateInfoResponse();

				if (StringUtils.isNotBlank(dto.getDescription()))
					response.setTenderCertificateDescription(dto.getDescription());

				if (dto.getIdentifier().getTenderProgramId() != null && dto.getIdentifier().getTenderProgramId() > 0)
					response.setTenderProgramId(dto.getIdentifier().getTenderProgramId());

				listResponse.add(response);
			}
		}
		return listResponse;
	}

	public static List<GLCategoryInfoResponse> mapToGLCatResponse(List<GlcatDTO> list) {
		// TODO Auto-generated method stub

		List<GLCategoryInfoResponse> response = new ArrayList<>();

		if (!list.isEmpty()) {

			for (GlcatDTO dto : list) {
				GLCategoryInfoResponse res = new GLCategoryInfoResponse();

				if (StringUtils.isNotBlank(dto.getDescription()))
					res.setGlCatDescription(dto.getDescription());

				if (dto.getCategoryId() != null && dto.getCategoryId() > 0)
					res.setGlCatId(dto.getCategoryId());

				response.add(res);
			}
		}
		return response;
	}

	public static List<StatesProvinceInfoResponse> mapToStates(List<GeographicRegionDTO> states) {
		// TODO Auto-generated method stub
		List<StatesProvinceInfoResponse> response = new ArrayList<>();
		if (!states.isEmpty()) {

			for (GeographicRegionDTO dto : states) {

				StatesProvinceInfoResponse res = new StatesProvinceInfoResponse();

				if (StringUtils.isNotBlank(dto.getFullName()))
					res.setFullName(dto.getFullName());

				if (StringUtils.isNotBlank(dto.getShortName()))
					res.setShortName(dto.getShortName());

				response.add(res);
			}
		}
		return response;
	}

	public static List<String> mapToVendorTypes(List<DomainRefCodeDTO> vendorTypes) {
		// TODO Auto-generated method stub
		List<String> response = new ArrayList<>();
		if (!vendorTypes.isEmpty()) {

			for (DomainRefCodeDTO domainDTO : vendorTypes) {

				if (StringUtils.isNotBlank(domainDTO.getCodeMeaning()))
					response.add(domainDTO.getCodeMeaning());
			}
		}
		return response;
	}

	public static List<AttributeListValuesInfoResponse> mapToAttributeListValues(
			List<AttributeListValueAllDTO> attrList) {
		// TODO Auto-generated method stub

		List<AttributeListValuesInfoResponse> listResponse = new ArrayList<>();

		if (!attrList.isEmpty()) {
			for (AttributeListValueAllDTO dto : attrList) {

				AttributeListValuesInfoResponse response = new AttributeListValuesInfoResponse();

				if (StringUtils.isNotBlank(dto.getCodeMeaning()))
					response.setCodeMeaning(dto.getCodeMeaning());

				if (StringUtils.isNotBlank(dto.getCodeValue()))
					response.setCodeValue(dto.getCodeValue());

				listResponse.add(response);
			}

		}
		return listResponse;
	}

}
