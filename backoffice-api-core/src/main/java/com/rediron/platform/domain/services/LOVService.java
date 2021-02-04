package com.rediron.platform.domain.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rediron.platform.domain.model.response.BuyerDetailsResponse;
import com.rediron.platform.domain.model.response.CodeDetailsResponse;
import com.rediron.platform.domain.model.response.CommissionCodeDetailsResponse;
import com.rediron.platform.domain.model.response.CouponPDVDetails;
import com.rediron.platform.domain.model.response.DefaultDCDetailsResponse;
import com.rediron.platform.domain.model.response.GLCategoryInfoResponse;
import com.rediron.platform.domain.model.response.InventoryTypeResponse;
import com.rediron.platform.domain.model.response.LinkChargesDetailsResponse;
import com.rediron.platform.domain.model.response.ReportCodeDetailsResponse;
import com.rediron.platform.domain.model.response.SiteDetailResponse;
import com.rediron.platform.domain.model.response.SiteGroupDetails;
import com.rediron.platform.domain.model.response.StatusDetailsResponse;
import com.rediron.platform.domain.model.response.StatusInfoDetailsResponse;
import com.rediron.platform.domain.model.response.SupplierDetailsResponse;
import com.rediron.platform.domain.model.response.TareDetailsResponse;
import com.rediron.platform.domain.model.response.TenderCertificateInfoResponse;
import com.rediron.platform.domain.model.response.VendorsResponse;
import com.rediron.platform.domain.repositories.LOVRepository;


/**
 * @author lovanya.chaudhary
 *
 */
@Service
public class LOVService {
	
	protected static int progDefNo = 0;

	Logger logger = LoggerFactory.getLogger(LOVService.class);
	
	@Autowired
	private LOVRepository lovRepository;
	
	public List<InventoryTypeResponse> getInventoryType() {
		return lovRepository.getInventoryType();
	}

	// buyer details
	public List<BuyerDetailsResponse> getBuyerDetails(int siteNo) {
		return lovRepository.getBuyerDetails(siteNo);
	}
	
	public List<CodeDetailsResponse> getCodeDetails(String typeCode) {
		return lovRepository.getCodeDetails(typeCode);
	}

	public List<TareDetailsResponse> getTareDetails() {
		return lovRepository.getTareDetails();
	}
	
	public List<ReportCodeDetailsResponse> getReportCodeDetails() {
		return lovRepository.getReportCodeDetails();
	}
	
	public List<TenderCertificateInfoResponse> getTenderPrograms() {
		return lovRepository.getTenderPrograms();
	}
	
	// gl cat id 
	
	public List<GLCategoryInfoResponse> getGLCategoryDetails() {
		return lovRepository.getGLCategoryDetails();
	}
	
	public List<LinkChargesDetailsResponse> getLinkChargesDetails() {
		return lovRepository.getLinkChargesDetails();
	}
	
	public List<CommissionCodeDetailsResponse> getCommissionCodeDetails() {
		return lovRepository.getCommissionCodeDetails();
	}
	
	// check in postman from here
	public List<SiteGroupDetails> getSiteGroupDetails() {
		return lovRepository.getSiteGroupDetails();
	}
	
	public List<CouponPDVDetails> getCouponPDVDetails() {
		return lovRepository.getCouponPDVDetails();
	}
	
	// restrict item status
	public List<StatusDetailsResponse> getStatusDetails(String statusItem, boolean flexibleItem) {
		return lovRepository.getStatusDetails(statusItem, flexibleItem);
	}
	
	public List<StatusInfoDetailsResponse> getStatusInfoDetails(String statusItem, String statusId) {
		return lovRepository.getStatusInfoDetails(statusItem, statusId);
	}

	public List<DefaultDCDetailsResponse> getDefaultDCDetails() {
		return lovRepository.getDefaultDCDetails();
	}
	
	public List<SupplierDetailsResponse> getSupplierDetails() {
		return lovRepository.getSupplierDetails();
	}
	
	public List<SiteDetailResponse> getSiteDetails() {
		return lovRepository.getSiteDetails();
	}
	
	public List<String> getVendorIds() {
		return lovRepository.getVendorIds();
	}
	
	public List<SiteDetailResponse> getSitesForInvBySit(int skuNo){
		return lovRepository.getSitesForInvBySit(skuNo);
	}

	public List<VendorsResponse> getSuppliersByTypeCode(String typecode) {
		// TODO Auto-generated method stub
		return lovRepository.getSuppliersByTypeCode(typecode);
	}

}
