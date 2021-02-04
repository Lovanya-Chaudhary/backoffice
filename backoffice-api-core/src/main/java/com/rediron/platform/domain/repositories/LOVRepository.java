package com.rediron.platform.domain.repositories;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rediron.platform.domain.constants.IQueryConstants;
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

/**
 * @author lovanya.chaudhary
 *
 */
@Repository
public class LOVRepository {

	protected static int progDefNo = 0;

	Logger logger = LoggerFactory.getLogger(LOVRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<InventoryTypeResponse> getInventoryType() {

		List<InventoryTypeResponse> list = jdbcTemplate.query(IQueryConstants.INVENTORY_TYPE, rs -> {
			List<InventoryTypeResponse> list2 = new ArrayList<InventoryTypeResponse>();
			while (rs.next()) {
				InventoryTypeResponse invType = new InventoryTypeResponse();
				invType.setCodeValue((String) rs.getString("code_value"));
				invType.setCodeMeaning((String) rs.getString("code_meaning"));
				list2.add(invType);
			}
			return list2;
		});
		return list;
	}

	// buyer details
	public List<BuyerDetailsResponse> getBuyerDetails(int siteNo) {

		List<BuyerDetailsResponse> list = jdbcTemplate.query(IQueryConstants.BUYER_INFO, ps -> {
			ps.setInt(1, siteNo);
		}, rs -> {
			List<BuyerDetailsResponse> list2 = new ArrayList<BuyerDetailsResponse>();
			while (rs.next()) {
				BuyerDetailsResponse bd = new BuyerDetailsResponse();
				bd.setEmployeeId((String) rs.getString("employee_id"));
				bd.setFullName((String) rs.getString("full_name"));
				list2.add(bd);
			}
			return list2;
		});
		return list;
	}

	public List<CodeDetailsResponse> getCodeDetails(String typeCode) {

		String query = IQueryConstants.UDC_GALLON_COVERTER;

		if ("gallon".equalsIgnoreCase(typeCode))
			query = IQueryConstants.UDC_GALLON_COVERTER;
		else if ("shelf".equalsIgnoreCase(typeCode))
			query = IQueryConstants.UDC_SHELF_LIFE;
		else if ("stock".equalsIgnoreCase(typeCode))
			query = IQueryConstants.UDC_STOCK_STATUS;

		List<CodeDetailsResponse> list = jdbcTemplate.query(query, rs -> {
			List<CodeDetailsResponse> list2 = new ArrayList<CodeDetailsResponse>();
			while (rs.next()) {
				CodeDetailsResponse codeDetails = new CodeDetailsResponse();
				codeDetails.setCodeValue((String) rs.getString("code_id"));
				codeDetails.setCodeDescription((String) rs.getString("description"));
				list2.add(codeDetails);
			}
			return list2;
		});
		return list;
	}

	public List<TareDetailsResponse> getTareDetails() {
		// TODO Auto-generated method stub

		List<TareDetailsResponse> list = jdbcTemplate.query(IQueryConstants.TARE_INFO, rs -> {
			List<TareDetailsResponse> list2 = new ArrayList<TareDetailsResponse>();
			while (rs.next()) {
				TareDetailsResponse tareDetails = new TareDetailsResponse();
				tareDetails.setTareNo((String) rs.getString("tare_no"));
				tareDetails.setTareDescription((String) rs.getString("description"));
				list2.add(tareDetails);
			}
			return list2;
		});
		return list;
	}

	public List<ReportCodeDetailsResponse> getReportCodeDetails() {
		// TODO Auto-generated method stub

		List<ReportCodeDetailsResponse> list = jdbcTemplate.query(IQueryConstants.REPORT_CODE, rs -> {
			List<ReportCodeDetailsResponse> list2 = new ArrayList<ReportCodeDetailsResponse>();
			while (rs.next()) {
				ReportCodeDetailsResponse reportDetails = new ReportCodeDetailsResponse();
				reportDetails.setReportCode((Integer) rs.getInt("report_cd"));
				reportDetails.setReportDescription((String) rs.getString("description"));
				list2.add(reportDetails);
			}
			return list2;
		});
		return list;
	}

	public List<TenderCertificateInfoResponse> getTenderPrograms() {
		// TODO Auto-generated method stub

		List<TenderCertificateInfoResponse> list = jdbcTemplate.query(IQueryConstants.TENDER_CERTIFICATE_PROGRAM,
				rs -> {
					List<TenderCertificateInfoResponse> list2 = new ArrayList<>();
					while (rs.next()) {
						TenderCertificateInfoResponse tenderCertificateInfoResponse = new TenderCertificateInfoResponse();
						tenderCertificateInfoResponse.setTenderProgramId((Integer) rs.getInt("tender_program_id"));
						tenderCertificateInfoResponse
								.setTenderCertificateDescription((String) rs.getString("description"));
						list2.add(tenderCertificateInfoResponse);
					}
					return list2;
				});
		return list;
	}

	// gl cat id

	public List<GLCategoryInfoResponse> getGLCategoryDetails() {
		// TODO Auto-generated method stub

		List<GLCategoryInfoResponse> list = jdbcTemplate.query(IQueryConstants.GLCATID, rs -> {
			List<GLCategoryInfoResponse> list2 = new ArrayList<>();
			while (rs.next()) {
				GLCategoryInfoResponse glCategoryDetails = new GLCategoryInfoResponse();
				glCategoryDetails.setGlCatId((Integer) rs.getInt("category_id"));
				glCategoryDetails.setGlCatDescription((String) rs.getString("description"));
				list2.add(glCategoryDetails);
			}
			return list2;
		});
		return list;
	}

	public List<LinkChargesDetailsResponse> getLinkChargesDetails() {

		List<LinkChargesDetailsResponse> list = jdbcTemplate.query(IQueryConstants.LINK_CHARGES, rs -> {
			List<LinkChargesDetailsResponse> list2 = new ArrayList<>();
			while (rs.next()) {
				LinkChargesDetailsResponse linkChargesDetails = new LinkChargesDetailsResponse();
				linkChargesDetails.setBottleLinkNo((Integer) rs.getInt("bottle_link_no"));
				linkChargesDetails.setBottleLinkDescription((String) rs.getString("description"));
				list2.add(linkChargesDetails);
			}
			return list2;
		});
		return list;
	}

	public List<CommissionCodeDetailsResponse> getCommissionCodeDetails() {
		List<CommissionCodeDetailsResponse> list = jdbcTemplate.query(IQueryConstants.COMMISSION_CODES, rs -> {
			List<CommissionCodeDetailsResponse> list2 = new ArrayList<>();
			while (rs.next()) {
				CommissionCodeDetailsResponse commissionCodeDetails = new CommissionCodeDetailsResponse();
				commissionCodeDetails.setCommissionCode((Integer) rs.getInt("commission_cd"));
				commissionCodeDetails.setCommissionDescription((String) rs.getString("description1"));
				commissionCodeDetails.setCommissionTypeCode((String) rs.getString("type_cd"));
				list2.add(commissionCodeDetails);
			}
			return list2;
		});
		return list;
	}

	// check in postman from here
	public List<SiteGroupDetails> getSiteGroupDetails() {
		List<SiteGroupDetails> list = jdbcTemplate.query(IQueryConstants.SITE_GROUPS, rs -> {
			List<SiteGroupDetails> list2 = new ArrayList<>();
			while (rs.next()) {
				SiteGroupDetails siteGroupDetails = new SiteGroupDetails();
				siteGroupDetails.setGroupId((String) rs.getString("group_id"));
				siteGroupDetails.setDescription((String) rs.getString("description"));
				list2.add(siteGroupDetails);
			}
			return list2;
		});
		return list;
	}

	public List<CouponPDVDetails> getCouponPDVDetails() {
		// TODO Auto-generated method stub

		List<CouponPDVDetails> list = jdbcTemplate.query(IQueryConstants.COUPON_PDV, rs -> {
			List<CouponPDVDetails> list2 = new ArrayList<>();
			while (rs.next()) {
				CouponPDVDetails couponPDVDetails = new CouponPDVDetails();
				couponPDVDetails.setCouponPDVId((String) rs.getString("purchase_volume_id"));
				couponPDVDetails.setCouponPDVDescription((String) rs.getString("description"));
				list2.add(couponPDVDetails);
			}
			return list2;
		});
		return list;
	}

	// restrict item status
	public List<StatusDetailsResponse> getStatusDetails(String statusItem, boolean flexibleItem) {

		// default provide restrict status value
		progDefNo = 1111;
		String sqlQuery = IQueryConstants.STATUS_DETAILS;
		if ("restrict".equalsIgnoreCase(statusItem))
			progDefNo = IQueryConstants.RESTRICT_PROGDEFNO;
		else if ("item".equalsIgnoreCase(statusItem)) {
			progDefNo = IQueryConstants.ITEM_PROGDEFNO;
			if (flexibleItem)
				sqlQuery = IQueryConstants.ITEMIZER_STATUS1;
			else
				sqlQuery = IQueryConstants.ITEMIZER_STATUS2;
		} else if ("itemizer".equalsIgnoreCase(statusItem))
			progDefNo = IQueryConstants.ITEMIZER_PROGDEFNO;
		else if ("pos".equalsIgnoreCase(statusItem))
			progDefNo = IQueryConstants.POS_PROGDEFNO;

		List<StatusDetailsResponse> statusDetailsList = jdbcTemplate.query(sqlQuery, ps -> {
			ps.setInt(1, progDefNo);
		}, rs -> {
			List<StatusDetailsResponse> list2 = new ArrayList<>();
			while (rs.next()) {
				StatusDetailsResponse statusDetails = new StatusDetailsResponse();
				statusDetails.setStatusId((String) rs.getString("status_id"));
				statusDetails.setStatusDescription((String) rs.getString("description"));
				list2.add(statusDetails);
			}
			return list2;
		});
		return statusDetailsList;
	}

	public List<StatusInfoDetailsResponse> getStatusInfoDetails(String statusItem, String statusId) {

		if ("restrict".equalsIgnoreCase(statusItem))
			progDefNo = IQueryConstants.RESTRICT_PROGDEFNO;
		else if ("item".equalsIgnoreCase(statusItem))
			progDefNo = IQueryConstants.ITEM_PROGDEFNO;
		else if ("itemizer".equalsIgnoreCase(statusItem))
			progDefNo = IQueryConstants.ITEMIZER_PROGDEFNO;
		else if ("pos".equalsIgnoreCase(statusItem))
			progDefNo = IQueryConstants.POS_PROGDEFNO;

		List<StatusInfoDetailsResponse> reqStatusInfoDetailsList = jdbcTemplate.query(IQueryConstants.STATUS_INFO, ps -> {
			ps.setInt(1, progDefNo);
			ps.setString(2, statusId);
		}, rs -> {
			List<StatusInfoDetailsResponse> statusInfoDetailsList = new ArrayList<>();
			while (rs.next()) {
				StatusInfoDetailsResponse statusInfoDetails = new StatusInfoDetailsResponse();
				statusInfoDetails.setStatusDescription(rs.getString("description"));
				statusInfoDetails.setStatusValue(rs.getString("status_value"));
				statusInfoDetailsList.add(statusInfoDetails);
			}
			return statusInfoDetailsList;
		});
		return reqStatusInfoDetailsList;
	}

	// defualt DC details
	public List<DefaultDCDetailsResponse> getDefaultDCDetails() {
		List<DefaultDCDetailsResponse> list = jdbcTemplate.query(IQueryConstants.DEFAULT_DC, rs -> {
			List<DefaultDCDetailsResponse> list2 = new ArrayList<>();
			while (rs.next()) {
				DefaultDCDetailsResponse defaultDCDetails = new DefaultDCDetailsResponse();
				defaultDCDetails.setSiteNo(rs.getInt("site_no"));
				defaultDCDetails.setFullName(rs.getString("full_name"));
				list2.add(defaultDCDetails);
			}
			return list2;
		});
		return list;
	}

	// supplier info
	public List<SupplierDetailsResponse> getSupplierDetails() {
		List<SupplierDetailsResponse> list = jdbcTemplate.query(IQueryConstants.SUPPLIER_DETAILS, rs -> {
			List<SupplierDetailsResponse> list2 = new ArrayList<>();
			while (rs.next()) {
				SupplierDetailsResponse supplierDetails = new SupplierDetailsResponse();
				supplierDetails.setSupplierId(rs.getString("vendor_id"));
				supplierDetails.setSupplierDescription(rs.getString("name"));
				list2.add(supplierDetails);
			}
			return list2;
		});
		return list;
	}

	public List<SiteDetailResponse> getSiteDetails() {
		List<SiteDetailResponse> list = jdbcTemplate.query(IQueryConstants.SITE_DETAILS, rs -> {
			List<SiteDetailResponse> list2 = new ArrayList<>();
			while (rs.next()) {
				SiteDetailResponse siteDetails = new SiteDetailResponse();
				siteDetails.setSiteNo(rs.getInt("site_no"));
				siteDetails.setSiteDescription(rs.getString("full_name"));
				siteDetails.setOwnerId(rs.getInt("owner_id"));
				list2.add(siteDetails);
			}
			return list2;
		});
		return list;
	}

	public List<String> getVendorIds() {
		// TODO Auto-generated method stub
		List<String> list = jdbcTemplate.query(IQueryConstants.VENDORS, rs -> {
			List<String> list2 = new ArrayList<>();
			while (rs.next()) {
				list2.add(rs.getString("vendor_id"));
			}
			return list2;
		});
		return list;
	}
	
	public List<SiteDetailResponse> getSitesForInvBySit(int skuNo){
		
		return jdbcTemplate.query(IQueryConstants.SITES_INVBYSIT,
				ps -> {
					ps.setInt(1, skuNo);
				},
				rs -> {
					List<SiteDetailResponse> sitesList = new ArrayList<>();
					while(rs.next()) {
						SiteDetailResponse sd = new SiteDetailResponse();
						sd.setSiteNo(rs.getInt("site_no"));
						sd.setSiteDescription(rs.getString("full_name"));
						sd.setOwnerId(rs.getInt("owner_id"));
						sitesList.add(sd);
					}
					return sitesList;
				});
	}

	public List<VendorsResponse> getSuppliersByTypeCode(String typecode) {
		// TODO Auto-generated method stub select name, vendor_id from vendor where vendor_type_cd = 'S'
		return jdbcTemplate.query(IQueryConstants.VENDORS_BY_TYPE_CODE,
				ps -> {
					ps.setString(1, typecode);
				},
				rs -> {
					List<VendorsResponse> sitesList = new ArrayList<>();
					while(rs.next()) {
						VendorsResponse vendor = new VendorsResponse();
						vendor.setVendorId(rs.getString("vendor_id"));
						vendor.setVendorName(rs.getString("name"));
						sitesList.add(vendor);
					}
					return sitesList;
				});
	}

}
