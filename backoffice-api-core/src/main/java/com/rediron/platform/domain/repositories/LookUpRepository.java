package com.rediron.platform.domain.repositories;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.rediron.platform.domain.constants.IQueryConstants;
import com.rediron.platform.domain.model.response.DefaultDCDetailsResponse;
import com.rediron.platform.domain.model.response.PromoInfo;
import com.rediron.platform.domain.response.PriceQuantityPackage;
import com.rediron.platform.domain.response.Pricing;
import com.rediron.platform.domain.response.PricingQuantity;
import com.rediron.platform.domain.response.PromoDetails;
import com.rediron.platform.domain.response.PromoStatus;
import com.rediron.platform.domain.response.RegularPricing;
import com.rediron.platform.domain.response.SKULinkDetails;
import com.rediron.platform.domain.services.UtilityService;
import com.tomax.api.UserIdentity;

/**
 * @author lovanya.chaudhary
 *
 */
@Repository
public class LookUpRepository {

	Logger logger = LoggerFactory.getLogger(LookUpRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	UtilityService utilityService;

	public SKULinkDetails getSKULinkDetails(int linkSkuNo) {

		SKULinkDetails reqLinkSkuDetails = jdbcTemplate.query(IQueryConstants.LINK_SKU, ps -> {
			ps.setInt(1, linkSkuNo);
		}, rs -> {
			SKULinkDetails skuLinkDetails = new SKULinkDetails();
			while (rs.next()) {
				skuLinkDetails.setDescription((String) rs.getString("description"));

				if ("Y".equalsIgnoreCase((String) rs.getString("use_serial_fl")))
					skuLinkDetails.setSerializedItem(true);
				else
					skuLinkDetails.setSerializedItem(false);

				skuLinkDetails.setLinkSkuNumber((int) rs.getInt("link_sku_no"));
				skuLinkDetails.setDeptNo((int) rs.getInt("dept_no"));

				if ("Y".equalsIgnoreCase((String) rs.getString("non_inventory_fl")))
					skuLinkDetails.setNonInventory(true);
				else
					skuLinkDetails.setNonInventory(false);

				if ("Y".equalsIgnoreCase((String) rs.getString("supply_item_fl")))
					skuLinkDetails.setSupplyItem(true);
				else
					skuLinkDetails.setSupplyItem(false);

				if ("Y".equalsIgnoreCase((String) rs.getString("weighed_item_fl")))
					skuLinkDetails.setMeasuredItem(true);
				else
					skuLinkDetails.setMeasuredItem(false);

				skuLinkDetails.setSkuNumber((int) rs.getInt("sku_no"));

				skuLinkDetails.setQtyInTransit((BigDecimal) rs.getBigDecimal("qty_in_transit"));
				skuLinkDetails.setQtyOnBackord((BigDecimal) rs.getBigDecimal("qty_on_backord"));
				skuLinkDetails.setQtyOnHand((BigDecimal) rs.getBigDecimal("qty_on_hand"));
				skuLinkDetails.setQtyOnOrd((BigDecimal) rs.getBigDecimal("qty_on_ord"));
				skuLinkDetails.setQtyRsvd((BigDecimal) rs.getBigDecimal("qty_rsvd"));

			}
			return skuLinkDetails;
		});
		return reqLinkSkuDetails;
	}

	public int getCount(String mfgCode) {
		int mfgCount = utilityService.getDataFromQuery(IQueryConstants.MFGCODE, Integer.class,
				new Object[] { mfgCode });
		return mfgCount;
	}

	public BigDecimal getXRef(int skuNo) {
		BigDecimal xrefNo = utilityService.getDataFromQuery(IQueryConstants.XREF, BigDecimal.class,
				new Object[] { skuNo });
		return xrefNo;
	}

	// verify execution
	public BigDecimal getKITHDRCount(int skuNo) {
		BigDecimal kitCount = utilityService.getDataFromQuery(IQueryConstants.KITHDR_COUNT, BigDecimal.class,
				new Object[] { skuNo });
		return kitCount;
	}

	public BigDecimal getKITDTLCount(int skuNo) {
		BigDecimal kitCount = utilityService.getDataFromQuery(IQueryConstants.KITDTL_COUNT, BigDecimal.class,
				new Object[] { skuNo });
		return kitCount;
	}

	public BigDecimal getCNTSHEETCount(int skuNo) {
		BigDecimal cntSheetCount = utilityService.getDataFromQuery(IQueryConstants.CNTSHEET_COUNT, BigDecimal.class,
				new Object[] { skuNo });
		return cntSheetCount;
	}

	public BigDecimal getDeleteSkuCount(int skuNo) {
		BigDecimal delCount = utilityService.getDataFromQuery(IQueryConstants.DELETE_SKU_COUNT, BigDecimal.class,
				new Object[] { skuNo });
		return delCount;
	}

	public BigDecimal getMaxUpcModifier(String upcId) {
		BigDecimal maxUpcModifier = utilityService.getDataFromQuery(IQueryConstants.UPC_MODIFIER, BigDecimal.class,
				new Object[] { upcId });
		return maxUpcModifier;
	}

	// verify execution
	public String getDownloadFlag(int skuNo, int siteNo) {
		// TODO Auto-generated method stub
		String downloadFl = utilityService.getDataFromQuery(IQueryConstants.DOWNLOAD_FL, String.class,
				new Object[] { skuNo, siteNo });
		return downloadFl;
	}

	public List<Integer> getKITCoreItems(int skuNo, int siteNo) {
		// TODO Auto-generated method stub
		List<Integer> listOfSku = jdbcTemplate.query(IQueryConstants.KIT_CORE_FL, ps -> {
			ps.setInt(1, skuNo);
		}, rs -> {
			List<Integer> skuList = new ArrayList<>();

			while (rs.next()) {
				skuList.add(rs.getInt("kit_sku_no")); // check with kit_sku_no or sku_no_out
			}
			return skuList;
		});
		return listOfSku;
	}

	// verify needed
	public BigDecimal getPromoKeyNo(int siteNo, int skuNo) {
		// TODO Auto-generated method stub
		return utilityService.getDataFromQuery(IQueryConstants.PROMO_KEY, BigDecimal.class,
				new Object[] { siteNo, skuNo });
	}

	public PromoDetails getPromoPricing(BigDecimal promoKeyNo) {
		// TODO Auto-generated method stub
		PromoDetails promoDetails = jdbcTemplate.query(IQueryConstants.PROMO_PRICING, ps -> {
			ps.setBigDecimal(1, promoKeyNo);
		}, rs -> {
			PromoDetails details = new PromoDetails();

			while (rs.next()) {
				Pricing pricing = new Pricing();

				pricing.setPrice1(rs.getBigDecimal("price1"));
				pricing.setPrice2(rs.getBigDecimal("price2"));
				pricing.setPrice3(rs.getBigDecimal("price3"));
				pricing.setPrice4(rs.getBigDecimal("price4"));
				pricing.setPrice5(rs.getBigDecimal("price5"));

				pricing.setPrice6(rs.getBigDecimal("price6"));
				pricing.setPrice7(rs.getBigDecimal("price7"));
				pricing.setPrice8(rs.getBigDecimal("price8"));
				pricing.setPrice9(rs.getBigDecimal("price9"));
				pricing.setPrice10(rs.getBigDecimal("price10"));

				details.setPricing(pricing);

				PricingQuantity pricingQuantity = new PricingQuantity();
				pricingQuantity.setPriceQty1(rs.getBigDecimal("price_qty1"));
				pricingQuantity.setPriceQty2(rs.getBigDecimal("price_qty2"));
				pricingQuantity.setPriceQty3(rs.getBigDecimal("price_qty3"));
				pricingQuantity.setPriceQty4(rs.getBigDecimal("price_qty4"));
				pricingQuantity.setPriceQty5(rs.getBigDecimal("price_qty5"));

				pricingQuantity.setPriceQty6(rs.getBigDecimal("price_qty6"));
				pricingQuantity.setPriceQty7(rs.getBigDecimal("price_qty7"));
				pricingQuantity.setPriceQty8(rs.getBigDecimal("price_qty8"));
				pricingQuantity.setPriceQty9(rs.getBigDecimal("price_qty9"));
				pricingQuantity.setPriceQty10(rs.getBigDecimal("price_qty10"));

				details.setPricingQuantity(pricingQuantity);

				PriceQuantityPackage priceQuantityPackage = new PriceQuantityPackage();
				priceQuantityPackage.setPkgPrice1(rs.getBigDecimal("pkg_price1"));
				priceQuantityPackage.setPkgPrice2(rs.getBigDecimal("pkg_price2"));
				priceQuantityPackage.setPkgPrice3(rs.getBigDecimal("pkg_price3"));

				priceQuantityPackage.setPkgPriceQty1(rs.getBigDecimal("pkg_price_qty1"));
				priceQuantityPackage.setPkgPriceQty2(rs.getBigDecimal("pkg_price_qty2"));
				priceQuantityPackage.setPkgPriceQty3(rs.getBigDecimal("pkg_price_qty3"));

				details.setPriceQuantityPackage(priceQuantityPackage);

				PromoStatus promoStatus = new PromoStatus();
				promoStatus.setMarkdownPact(rs.getBigDecimal("markdown_pct"));
				promoStatus.setOffAmount(rs.getBigDecimal("off_amt"));
				promoStatus.setItemizerStatusId(rs.getString("itemizer_status_id"));
				promoStatus.setPomoCommissionCode(rs.getString("promo_commission_cd"));
				promoStatus.setSkuStatusId(rs.getString("SKU_STATUS_ID"));

				if ("Y".equalsIgnoreCase(rs.getString("sel_itemizer1_fl")))
					promoStatus.setSellItemizerFl1(true);
				else
					promoStatus.setSellItemizerFl1(false);

				if ("Y".equalsIgnoreCase(rs.getString("sel_itemizer2_fl")))
					promoStatus.setSellItemizerFl2(true);
				else
					promoStatus.setSellItemizerFl2(false);

				if ("Y".equalsIgnoreCase(rs.getString("update_itemizer_status_id_fl")))
					promoStatus.setUpdateItemizerStatusIdFl(true);
				else
					promoStatus.setUpdateItemizerStatusIdFl(false);

				if ("Y".equalsIgnoreCase(rs.getString("update_promo_commission_cd_fl")))
					promoStatus.setUpdatePromoCommissionCodeFl(true);
				else
					promoStatus.setUpdatePromoCommissionCodeFl(false);

				if ("Y".equalsIgnoreCase(rs.getString("update_sku_status_id_fl")))
					promoStatus.setUpdateSkuStatusIdFl(true);
				else
					promoStatus.setUpdateSkuStatusIdFl(false);

				details.setPromoStatus(promoStatus);
			}
			return details;
		});
		return promoDetails;
	}

	// did not like the way function is called repeatedly just to populate the
	// fields one at a time
	public BigDecimal doPriceMarkdown(int siteNo, BigDecimal pkgPrice, BigDecimal markdown, String type) {

		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("siteNo", siteNo)
				.addValue("pkgPrice", pkgPrice).addValue("markdown", markdown).addValue("type", type);

		return utilityService.getDataFromFunction("tmx", "pricing_upd", "markdown", paramMap, BigDecimal.class,
				new SqlOutParameter("number", Types.DECIMAL), new SqlParameter("siteNo", Types.BIGINT),
				new SqlParameter("pkgPrice", Types.DECIMAL), new SqlParameter("markdown", Types.DECIMAL),
				new SqlParameter("type", Types.CHAR));
	}

	// sql to util date coversion
	public PromoInfo getPromoInfo(BigDecimal promoKeyNo) {

		PromoInfo promoInfo = jdbcTemplate.query(IQueryConstants.PROMO_INFO, ps -> {
			ps.setBigDecimal(1, promoKeyNo);
		}, rs -> {
			PromoInfo info = new PromoInfo();

			while (rs.next()) {
				info.setAdId(rs.getString("ad_id"));
				info.setAdSiteNo(rs.getInt("ad_site_no"));

				if (null != rs.getDate("start_dt"))
					info.setStartDate(new Date(rs.getDate("start_dt").getTime()));

				if (null != rs.getDate("stop_dt"))
					info.setEndDate(new Date(rs.getDate("stop_dt").getTime()));
			}
			return info;
		});
		return promoInfo;
	}

	public RegularPricing getRegularPricing(int siteNo, int skuNo) {

		RegularPricing regularPricing = jdbcTemplate.query(IQueryConstants.REGULAR_PRICING, ps -> {
			ps.setInt(1, siteNo);
			ps.setInt(2, skuNo);
		}, rs -> {
			RegularPricing regPricing = new RegularPricing();

			while (rs.next()) {

				regPricing.setItemizerStatusId(rs.getString("itemizer_status_id"));
				regPricing.setSkuStatusId(rs.getString("sku_status_id"));

				PriceQuantityPackage priceQuantityPackage = new PriceQuantityPackage();
				priceQuantityPackage.setPkgPrice1(rs.getBigDecimal("pkg_price1"));
				priceQuantityPackage.setPkgPrice2(rs.getBigDecimal("pkg_price2"));
				priceQuantityPackage.setPkgPrice3(rs.getBigDecimal("pkg_price3"));

				priceQuantityPackage.setPkgPriceQty1(rs.getBigDecimal("pkg_price_qty1"));
				priceQuantityPackage.setPkgPriceQty2(rs.getBigDecimal("pkg_price_qty2"));
				priceQuantityPackage.setPkgPriceQty3(rs.getBigDecimal("pkg_price_qty3"));

				regPricing.setPriceQuantityPackage(priceQuantityPackage);

				PricingQuantity pricingQuantity = new PricingQuantity();
				pricingQuantity.setPriceQty1(rs.getBigDecimal("price_qty1"));
				pricingQuantity.setPriceQty2(rs.getBigDecimal("price_qty2"));
				pricingQuantity.setPriceQty3(rs.getBigDecimal("price_qty3"));
				pricingQuantity.setPriceQty4(rs.getBigDecimal("price_qty4"));
				pricingQuantity.setPriceQty5(rs.getBigDecimal("price_qty5"));

				pricingQuantity.setPriceQty6(rs.getBigDecimal("price_qty6"));
				pricingQuantity.setPriceQty7(rs.getBigDecimal("price_qty7"));
				pricingQuantity.setPriceQty8(rs.getBigDecimal("price_qty8"));
				pricingQuantity.setPriceQty9(rs.getBigDecimal("price_qty9"));
				pricingQuantity.setPriceQty10(rs.getBigDecimal("price_qty10"));
				regPricing.setPricingQuantity(pricingQuantity);

				Pricing pricing = new Pricing();
				pricing.setPrice1(rs.getBigDecimal("price1"));
				pricing.setPrice2(rs.getBigDecimal("price2"));
				pricing.setPrice3(rs.getBigDecimal("price3"));
				pricing.setPrice4(rs.getBigDecimal("price4"));
				pricing.setPrice5(rs.getBigDecimal("price5"));

				pricing.setPrice6(rs.getBigDecimal("price6"));
				pricing.setPrice7(rs.getBigDecimal("price7"));
				pricing.setPrice8(rs.getBigDecimal("price8"));
				pricing.setPrice9(rs.getBigDecimal("price9"));
				pricing.setPrice10(rs.getBigDecimal("price10"));

				regPricing.setPricing(pricing);

			}
			return regPricing;
		});
		return regularPricing;
	}

	// fetches the count for the promos of the newly generated sku if any
	public Map<String, Object> getPromoCount(int deptNo, int classNo, int lineNo) {

		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("deptNo", deptNo)
				.addValue("classNo", classNo).addValue("lineNo", lineNo);

		Map<String, Object> map = utilityService.getDataFromProcedure("tmx", "b_promotion", "chk_new_sku_promo",
				paramMap, new SqlOutParameter("number", Types.DECIMAL), new SqlParameter("deptNo", Types.BIGINT),
				new SqlParameter("classNo", Types.BIGINT), new SqlParameter("lineNo", Types.BIGINT));
		return map;
	}

	// this function will be used to insert the promos for the newly generated skus
	// in case of exceptional scenarios
	public Boolean insertSkuPromos(int deptNo, int classNo, int lineNo, int skuNo) {

		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("deptNo", deptNo)
				.addValue("classNo", classNo).addValue("lineNo", lineNo).addValue("skuNo", skuNo);

		Boolean flag = utilityService.getDataFromFunction("tmx", "b_promotion", "insert_sku_exception", paramMap,
				Boolean.class, new SqlParameter("deptNo", Types.BIGINT), new SqlParameter("classNo", Types.BIGINT),
				new SqlParameter("lineNo", Types.BIGINT), new SqlParameter("skuNo", Types.BIGINT));
		return flag;
	}

	// test
	public String getSupplierDescription(String vendorId) {
		// TODO Auto-generated method stub
		return utilityService.getDataFromQuery(IQueryConstants.SUPPLIER_NAME, String.class, new Object[] { vendorId });
	}

	public int saveOrUpdateItemCodes(UserIdentity domainUser, String code1Id, String code2Id, String code3Id,
			int skuNo) {
		// TODO Auto-generated method stub
		int rowsUpdated = jdbcTemplate.update(IQueryConstants.ITEM_CODES,
				new Object[] { code1Id, code2Id, code3Id, skuNo },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT });
		return rowsUpdated;
	}

	public BigDecimal getPreviousReorderValue(int skuNo, int siteNo) {
		// TODO Auto-generated method stub
		return utilityService.getDataFromQuery(IQueryConstants.REORDER, BigDecimal.class,
				new Object[] { skuNo, siteNo });
	}

	public List<Integer> getSitesFromSiteGroupId(String siteGroupId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(IQueryConstants.SITES_FROM_SITEGROUPID, ps -> {
			ps.setString(1, siteGroupId);
		}, rs -> {
			List<Integer> sites = new ArrayList<>();
			while (rs.next())
				sites.add(rs.getInt("site_no"));
			return sites;
		});
	}

	public Integer getPODetailCount(int skuNo, int siteNo, int defaultDc) {
		return jdbcTemplate.queryForObject(IQueryConstants.PO_DETAIL_COUNT, new Object[] { siteNo, defaultDc, skuNo },
				new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER }, Integer.class);
	}

	public Integer getXrefDetailCount(int skuNo, int siteNo, int defaultDc) {
		return jdbcTemplate.queryForObject(IQueryConstants.XREF_DETAIL_COUNT, new Object[] { defaultDc, siteNo, skuNo },
				new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER }, Integer.class);
	}

	// it should fetch 1 record min to change the default dc value
	public List<DefaultDCDetailsResponse> getDefaultDCDetail(int defaultDc) {
		List<DefaultDCDetailsResponse> list = jdbcTemplate.query(IQueryConstants.DEFAULT_DC_SKU_LEVEL,
				ps -> {
					ps.setInt(1, defaultDc);
				},
				rs -> {
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

}
