package com.rediron.platform.domain.repositories;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rediron.platform.domain.constants.IQueryConstants;
import com.rediron.platform.domain.model.request.StatusInfoModel;
import com.rediron.platform.domain.model.request.SummaryInfoRequest;
import com.rediron.platform.domain.model.request.SummaryReorderPoints;
import com.rediron.platform.domain.model.request.UpdateInvbysitRequest;
import com.rediron.platform.domain.model.response.ItemCodeResponse;
import com.rediron.platform.domain.model.response.SummaryInfoResponse;
import com.rediron.platform.domain.model.response.VendorImportInfoResponse;
import com.tomax.api.UserIdentity;

/**
 * @author lovanya.chaudhary
 *
 */
@Repository
public class InventoryRepository {

	Logger logger = LoggerFactory.getLogger(ItemRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int updateInvBySitStatuses(UserIdentity domainUser, StatusInfoModel statusInfoModel, int skuNo, int siteNo) {

		return jdbcTemplate.update(IQueryConstants.INVBYSIT_STATUSES, ps -> {
			ps.setString(1, statusInfoModel.getItemStatusId());
			ps.setString(2, statusInfoModel.getRestrictSaleId());
			ps.setString(3, statusInfoModel.getPosSkuStatusId());
			ps.setString(4, statusInfoModel.getItemizerStatusId());
			ps.setInt(5, skuNo);
			ps.setInt(6, siteNo);
		});
	}

	public String getFlexibleFlag(int skuNo) {
		return jdbcTemplate.queryForObject(IQueryConstants.FLEXIBLE_ITEM_FL_BY_SKU, new Object[] { skuNo },
				String.class);
	}

	public Integer getOwnerId(Integer siteNo) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(IQueryConstants.OWNER_ID_BY_SITE_NO, new Object[] { siteNo }, Integer.class);
	}

	public String getDomainCodeMeaning(String code, String type) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(IQueryConstants.DOMAIN_CODE_MEANING, new Object[] { type, code },
				String.class);
	}

	public ItemCodeResponse getItemCodes(UserIdentity domainUser, int skuNo) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(IQueryConstants.ITEM_CODES_BY_SKU, ps -> {
			ps.setInt(1, skuNo);
//			ps.setInt(2, skuNo);
//			ps.setInt(3, skuNo);
		}, rs -> {
			ItemCodeResponse itemCodeResponse = new ItemCodeResponse();
			while (rs.next()) {
				itemCodeResponse.setCode1Id(rs.getString("code1_id"));
//					itemCodeResponse.setCode1Description(rs.getString("description"));
				itemCodeResponse.setCode2Id(rs.getString("code2_id"));
//					itemCodeResponse.setCode2Description(rs.getString("description"));
				itemCodeResponse.setCode3Id(rs.getString("code3_id"));
//					itemCodeResponse.setCode3Description(rs.getString("description"));
			}
			return itemCodeResponse;
		});
	}

	public VendorImportInfoResponse getVendorImport(UserIdentity domainUser, int skuNo) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(IQueryConstants.VENDOR_IMPORT_BY_SKU, ps -> {
			ps.setInt(1, skuNo);
		}, rs -> {
			VendorImportInfoResponse vendorImportInfoResponse = new VendorImportInfoResponse();
			while (rs.next()) {
				if ("Y".equalsIgnoreCase(rs.getString("inv_item_lock_fl")))
					vendorImportInfoResponse.setLockItem(true);
				else
					vendorImportInfoResponse.setLockItem(false);
				vendorImportInfoResponse.setChangeVendor(rs.getString("last_item_change_vendor_id"));

				if (null != rs.getDate("last_item_change_dt"))
					vendorImportInfoResponse.setChangeDate(new Date(rs.getDate("last_item_change_dt").getTime()));
			}
			return vendorImportInfoResponse;
		});
	}

	public List<SummaryInfoResponse> getSummaryInfo(UserIdentity domainUser, int skuNo, String siteGroupId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(IQueryConstants.SUMMARY_INFO, ps -> {
			ps.setString(1, siteGroupId);
			ps.setInt(2, skuNo);
		}, rs -> {
			List<SummaryInfoResponse> summary = new ArrayList<>();
			while (rs.next()) {
				SummaryInfoResponse response = new SummaryInfoResponse();
				response.setAllocatedQtyIn(rs.getBigDecimal("alloc_qty_in"));
				response.setAllocatedQtyOut(rs.getBigDecimal("alloc_qty_out"));
				if ("Y".equalsIgnoreCase(rs.getString("download_fl")))
					response.setAuthorized(true);
				else
					response.setAuthorized(false);
				response.setCost(rs.getBigDecimal("cost"));
				response.setMargin(rs.getBigDecimal("margin"));

				if (null != rs.getDate("exp_rcvd_dt"))
					response.setPoExpectedDate(new Date(rs.getDate("exp_rcvd_dt").getTime()));

				response.setPrice(rs.getBigDecimal("price1"));
				response.setQtyOnHand(rs.getBigDecimal("qty_on_hand"));
				response.setQtyOnOrder(rs.getBigDecimal("qty_on_ord"));
				response.setReorderPt(rs.getBigDecimal("reorder_pt"));
				response.setReorderTo(rs.getBigDecimal("reorder_to"));
				response.setSiteDescription(rs.getString("site_description"));
				response.setSiteNo(rs.getInt("site_no"));
				response.setSuggestedRetail(rs.getBigDecimal("suggested_retail"));
				summary.add(response);
			}
			return summary;
		});
	}

	public List<SummaryInfoResponse> updateSummaryInfo(UserIdentity domainUser, int skuNo, String siteGroupId,
			SummaryInfoRequest summaryInfoRequest) {
		logger.info("Entered transaction method === ");
		List<SummaryReorderPoints> reorderList = null;
		int count = 0;
		if (summaryInfoRequest != null) {
			reorderList = summaryInfoRequest.getReorderPoints();
			if (reorderList != null && !reorderList.isEmpty()) {
				for (SummaryReorderPoints reorderPoint : reorderList) {

					if (reorderPoint.getReorderPt() != null && reorderPoint.getReorderPt()
							.compareTo(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP)) < 0) {
						reorderPoint.setReorderPt(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP));
					}

					if (reorderPoint.getReorderTo() != null) {
						if (reorderPoint.getReorderPt() != null) {
							if (reorderPoint.getReorderTo()
									.compareTo(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP)) < 0
									|| reorderPoint.getReorderTo().compareTo(reorderPoint.getReorderPt()) < 0) {
								reorderPoint.setReorderTo(reorderPoint.getReorderPt());
							}
						}
					}

					jdbcTemplate.update(IQueryConstants.UPDATE_REORDER_POINTS, ps -> {
						ps.setBigDecimal(1, reorderPoint.getReorderTo());
						ps.setBigDecimal(2, reorderPoint.getReorderPt());
						ps.setString(3, reorderPoint.isAuthorized() ? "Y" : "N");
						ps.setInt(4, skuNo);
						ps.setInt(5, reorderPoint.getSiteNo());
					});
					count++;
				}
			}
		}
		logger.info("Updated rows count : " + count);
		return getSummaryInfo(domainUser, skuNo, siteGroupId);
	}

	public int updateInvbysit(UserIdentity domainUser, int skuNo, int siteNo,
			UpdateInvbysitRequest updateInvbysitRequest) {

		logger.info("Entered updateInvbysit method === ");
		int count = 0;

		jdbcTemplate.update(IQueryConstants.UPDATE_INVBYSIT_INFO, ps -> {
			ps.setBigDecimal(1, updateInvbysitRequest.getAdditionalTax());
			ps.setBigDecimal(2, updateInvbysitRequest.getLaloGm());
			ps.setBigDecimal(3, updateInvbysitRequest.getHaloGm());
			ps.setString(4, updateInvbysitRequest.getCouponPDVId());

			// non_discount_fl will work opposite to the discount_fl
			ps.setString(5, updateInvbysitRequest.isDiscountAllowed() ? "N" : "Y");
			ps.setString(6, updateInvbysitRequest.isPriceChangeAllowed() ? "Y" : "N");
			ps.setString(7, updateInvbysitRequest.isDiscontinued() ? "Y" : "N");

			if (updateInvbysitRequest.isDiscontinued())
				ps.setString(8, domainUser.getUserName());
			else
				ps.setString(8, null);

			ps.setString(9,
					updateInvbysitRequest.getStatusInfoModel() != null
							? updateInvbysitRequest.getStatusInfoModel().getItemStatusId()
							: null);
			ps.setString(10,
					updateInvbysitRequest.getStatusInfoModel() != null
							? updateInvbysitRequest.getStatusInfoModel().getRestrictSaleId()
							: null);
			ps.setString(11,
					updateInvbysitRequest.getStatusInfoModel() != null
							? updateInvbysitRequest.getStatusInfoModel().getPosSkuStatusId()
							: null);
			ps.setString(12,
					updateInvbysitRequest.getStatusInfoModel() != null
							? updateInvbysitRequest.getStatusInfoModel().getItemizerStatusId()
							: null);

			ps.setBigDecimal(13, updateInvbysitRequest.getReplenishmentInfoModel().getReorderPt());
			ps.setBigDecimal(14, updateInvbysitRequest.getReplenishmentInfoModel().getReorderTo());
			ps.setString(15, updateInvbysitRequest.getReplenishmentInfoModel().getVelocityCode());

			ps.setString(16,
					updateInvbysitRequest.getReplenishmentInfoModel().isAutomaticallyReplenished() ? "Y" : "N");
			ps.setString(17, updateInvbysitRequest.getReplenishmentInfoModel().isReorderPointsLocked() ? "Y" : "N");
			ps.setString(18, updateInvbysitRequest.getReplenishmentInfoModel().isVelocityCodeLocked() ? "Y" : "N");

			ps.setString(19, updateInvbysitRequest.getReplenishmentInfoModel().getExitVelocityCode());
			ps.setBigDecimal(20, updateInvbysitRequest.getReplenishmentInfoModel().getWarehouseInnerPack());
			ps.setBigDecimal(21, updateInvbysitRequest.getReplenishmentInfoModel().getMaxOrderQty());

			ps.setBigDecimal(22,
					updateInvbysitRequest.getReplenishmentInfoModel().getDefaultDC() != null
							? new BigDecimal(updateInvbysitRequest.getReplenishmentInfoModel().getDefaultDC())
							: null);

			if (updateInvbysitRequest.getReplenishmentInfoModel() != null) {
				ps.setDate(23, updateInvbysitRequest.getReplenishmentInfoModel().getProductStartDate() != null
						? new java.sql.Date(
								getFormattedDate(updateInvbysitRequest.getReplenishmentInfoModel().getProductStartDate()).getTime())
						: null);
				ps.setDate(24, updateInvbysitRequest.getReplenishmentInfoModel().getLastReplenishmentDate() != null
						? new java.sql.Date(
								getFormattedDate(updateInvbysitRequest.getReplenishmentInfoModel().getLastReplenishmentDate()).getTime())
						: null);
				ps.setDate(25,
						updateInvbysitRequest.getReplenishmentInfoModel().getProductEndDate() != null
								? new java.sql.Date(
										getFormattedDate(updateInvbysitRequest.getReplenishmentInfoModel().getProductEndDate()).getTime())
								: null);
			} else {
				ps.setDate(23, null);
				ps.setDate(24, null);
				ps.setDate(25, null);
			}

			ps.setBigDecimal(26,
					updateInvbysitRequest.getCommissionCd() != null
							? new BigDecimal(updateInvbysitRequest.getCommissionCd())
							: null);

			ps.setString(27,
					StringUtils.isNotBlank(updateInvbysitRequest.getTareTableNo())
							? updateInvbysitRequest.getTareTableNo()
							: null);
			// need to check how can we updated null or no info
			ps.setBigDecimal(28,
					updateInvbysitRequest.getLinkCharges() != null
							? new BigDecimal(updateInvbysitRequest.getLinkCharges())
							: null);
			ps.setString(29, updateInvbysitRequest.isAuthorized() ? "Y" : "N");
			ps.setInt(30, skuNo);
			ps.setInt(31, siteNo);
		});
		count++;
		logger.info("Updated records : " + count);
		return count;
	}
	
	private Date getFormattedDate(String dateString) {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return date;
	}

}
