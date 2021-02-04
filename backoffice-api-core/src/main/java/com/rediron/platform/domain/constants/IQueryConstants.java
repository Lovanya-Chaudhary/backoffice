package com.rediron.platform.domain.constants;

/**
 * @author lovanya.chaudhary
 *
 */
public interface IQueryConstants {

	String INVENTORY_TYPE = "select drc.code_value, drc.code_meaning\r\n"
			+ "from domain_ref_code drc, domain_ref_usage dru\r\n" + "where dru.domain_ref_id = drc.domain_ref_id\r\n"
			+ "and dru.table_name = 'INVTORY'\r\n" + "and dru.column_name = 'INV_TYPE'";

	String BUYER_INFO = "select e.employee_id employee_id,e.full_name  from employee e ,empbysit es\r\n"
			+ "where e.employee_id=es.employee_id\r\n" + "and es.site_no=:siteNo\r\n"
			+ "and (1=b_security.user_isa_buyer(e.employee_id,es.site_no))";

	String UDC_GALLON_COVERTER = "select code_id, description from code1 order by code_id";
	String UDC_STOCK_STATUS = "select code_id, description from code2 order by code_id";
	String UDC_SHELF_LIFE = "select code_id, description from code3 order by code_id";

	String TARE_INFO = "select tare_no, description from tare_table order by tare_no";

	String REPORT_CODE = "select report_cd, description from rptcod order by report_cd";

	String LINK_SKU = "select ibs.qty_on_backord, \r\n" + "				ibs.qty_in_transit, \r\n"
			+ "				ibs.qty_on_ord, \r\n" + "				ibs.qty_rsvd, \r\n"
			+ "				ibs.qty_on_hand,\r\n" + "                i.description, \r\n"
			+ "                i.use_serial_fl, \r\n" + "                i.link_sku_no, \r\n"
			+ "                i.dept_no, \r\n" + "                i.non_inventory_fl, \r\n"
			+ "                i.supply_item_fl, \r\n" + "                i.weighed_item_fl,\r\n"
			+ "                i.sku_no\r\n" + "		  from invbysit ibs, invtory i\r\n"
			+ "		 where ibs.sku_no= :linkSkuNo\r\n" + "		   and i.sku_no = ibs.sku_no\r\n"
			+ "		   and site_no = tmxgbl.my_site";

	String TENDER_CERTIFICATE_PROGRAM = "select tender_program_id, description from tender_certificate_program";

	String MFGCODE = "SELECT count(*) FROM invtory WHERE mfg_cd = :mfgCode and owner_id = (b_vendor.get_owner_id(tmxgbl.hq_site_no))";

	String GLCATID = "SELECT category_id, description FROM glcat";

	String XREF = "SELECT xref_no FROM invtory WHERE sku_no = :skuNo";

	String LINK_CHARGES = "select distinct bottle_link_no, description from bottlelk order by bottle_link_no";

	String COMMISSION_CODES = "select commission_cd, description1, decode(type_cd,'C','Commission','F','Flat Rate','SPIFF') \r\n"
			+ "type_cd from comcode order by commission_cd";

	String CNTSHEET_COUNT = "select count(*) from CNTSHEET_SKU where sku_no = :skuNo";

	String KITHDR_COUNT = "select count(*) from KITHDR where kit_sku_no = :skuNo";

	String KITDTL_COUNT = "select count(*) from KITDTL where kit_sku_no = :skuNo";

	String DELETE_SKU_COUNT = "select count(*) from delete_sku_queue where sku_no = :skuNo";

	String COUPON_PDV = "select purchase_volume_id, description from pdvdisc order by purchase_volume_id";

	String SITE_GROUPS = "select group_id, description from groups order by group_id";

	String UPC_MODIFIER = "select max(upc_modifier) as largestmodifier from itemupc where upc_id = :upcId";

	int RESTRICT_PROGDEFNO = 1111;
	int ITEM_PROGDEFNO = 101;
	int ITEMIZER_PROGDEFNO = 1802;
	int POS_PROGDEFNO = 5005;
	
	String STATUS_INFO = "select a.description , b.status_value from progdef a , status_class b\r\n" + 
			"where a.progdef_no = :progDefNo and a.name = b.name and b.status_id = :statusId order by 1";
	
	String STATUS_DETAILS = "select status_id,description from status_class_hdr where progdef_no = :progDefNo";

	String ITEMIZER_STATUS1 = "Select Status_id,Description from status_class_hdr where progdef_no= :progDefNo AND status_id not in\r\n"
			+ "(select status_id from status_class where NAME = 'KIT_SKU' AND STATUS_CLASS.STATUS_VALUE = 'Y')";

	String ITEMIZER_STATUS2 = "Select Status_id,Description from status_class_hdr where progdef_no= :progDefNo AND status_id not in\r\n"
			+ "(select status_id from status_class where NAME = 'KIT_SKU' AND STATUS_CLASS.STATUS_VALUE = 'Y')\r\n"
			+ "AND status_id not in (select status_id from status_class where status_value = 'Y' and (UPPER(name) = UPPER('F_COST') OR UPPER(name) = UPPER('F_DESC') OR UPPER(name) = UPPER('F_PRICE')))";
	
	String DEFAULT_DC = "SELECT s.site_no,s.full_name\r\n" + 
			"FROM site_attribute_value sav, site s\r\n" + 
			"WHERE sav.attribute_id IN (SELECT attribute_id\r\n" + 
			"FROM attribute_name\r\n" + 
			"WHERE name_text = 'DISTRIBUTION_CENTER_FL')\r\n" + 
			"AND sav.attribute_value = 'Y'\r\n" + 
			"AND sav.site_no = s.site_no";
	
	String DOWNLOAD_FL = "select download_fl from invbysit where sku_no = :skuNo and site_no = :siteNo";
	
	// check the below query for the kit related items to go for the authorisation
	String KIT_CORE_FL = "select h.kit_sku_no sku_no_out  from kithdr h, kitdtl d where h.kit_sku_no = d.kit_sku_no and h.kit_type_cd = 'CORE'  \r\n" + 
			"and h.kit_sku_no = :skuNo \r\n" + 
			"union\r\n" + 
			"select h.kit_sku_no sku_no_out  from kithdr h, kitdtl d where h.kit_sku_no = d.kit_sku_no     \r\n" + 
			"and h.kit_type_cd = 'CORE'  and d.sku_no = :skuNo  \r\n" + 
			"union                        \r\n" + 
			"select d.sku_no sku_no_out from kithdr h, kitdtl d where h.kit_sku_no = d.kit_sku_no and h.kit_type_cd = 'CORE' \r\n" + 
			"and h.kit_sku_no = :skuNo  \r\n" + 
			"union                         \r\n" + 
			"select d1.sku_no sku_no_out from kitdtl d1 where d1.kit_sku_no in (select h.kit_sku_no          \r\n" + 
			"from kithdr h, kitdtl d where h.kit_sku_no = d.kit_sku_no and h.kit_type_cd = 'CORE' and d.sku_no = :skuNo )";
	
	String PROMO_KEY = "select promo_key_no from invbysit where site_no = :siteNo and sku_no = :skuNo";
	
	String PROMO_PRICING = "select markdown_pct, off_amt, sel_itemizer1_fl, sel_itemizer2_fl, itemizer_status_id, SKU_STATUS_ID, \r\n" + 
			"price_qty1, price_qty2, price_qty3, price_qty4, price_qty5,price_qty6, price_qty7, price_qty8, price_qty9, price_qty10, \r\n" + 
			"price1, price2, price3, price4, price5, price6,price7, price8, price9, price10, \r\n" + 
			"pkg_price_qty1, pkg_price_qty2, pkg_price_qty3, pkg_price1, pkg_price2, pkg_price3, \r\n" + 
			"UPDATE_ITEMIZER_STATUS_ID_FL, UPDATE_SKU_STATUS_ID_FL, update_promo_commission_cd_fl, promo_commission_cd  \r\n" + 
			"from pricechg where promo_key_no = :promoKeyNo";
	
	String PROMO_INFO = "select start_dt, stop_dt, ad_id, ad_site_no  from pricechg where promo_key_no = :promoKeyNo";
	
	String REGULAR_PRICING = "select itemizer_status_id, sku_status_id, \r\n" + 
			"price_qty1, price_qty2, price_qty3, price_qty4, price_qty5, price_qty6, price_qty7, price_qty8, price_qty9, price_qty10, \r\n" + 
			"price1, price2, price3, price4, price5, price6, price7, price8, price9, price10,\r\n" + 
			"pkg_price_qty1, pkg_price_qty2, pkg_price_qty3, pkg_price1, pkg_price2, pkg_price3\r\n" + 
			"from invbysit where site_no = :siteNo and sku_no = :skuNo";
	
	String SUPPLIER_DETAILS = "select vendor_id ,name from vendor where vendor_type_cd = 'S'";
	
	String SUPPLIER_NAME = "select name from vendor where vendor_id = :vendorId and vendor_type_cd = 'S'";
	
	String ITEM_CODES = "update invtory set CODE1_ID = :code1Id , CODE2_ID = :code2Id, CODE3_ID = :code3Id where sku_no = :skuNo";
	
	String SITE_DETAILS = "select distinct s.site_no, s.full_name, s.owner_id from site s order by site_no";
	
	String VENDORS = "select vendor_id from vendor where vendor_type_cd != 'S'";
	
	String CHECK_PROMOS1 = "SELECT DISTINCT p.ad_id, p.ad_site_no, p.promo_key_no\r\n" + 
			"			  FROM pricechg p\r\n" + 
			"			 WHERE dept_no = :deptNo\r\n" + 
			"			   AND class_no = :classNo\r\n" + 
			"			   AND line_no = :lineNo\r\n" + 
			"			   AND master_promo_key_no IS NULL\r\n" + 
			"			   AND p.stop_dt >= tmxgbl.current_day";
	
	String CHECK_PROMOS2 = "SELECT ad_id, ad_site_no, stop_dt, group_id\r\n" + 
			"			  FROM PRICECHG\r\n" + 
			"			 WHERE dept_no = :deptNo\r\n" + 
			"			   AND class_no = :classNo\r\n" + 
			"			   AND line_no = :lineNo\r\n" + 
			"			   AND master_promo_key_no is null\r\n" + 
			"			   AND STOP_DT >= Tmxgbl.current_day";
	
	String REORDER = "select nvl(reorder_pt,0) from invbysit where sku_no = :skuNo and site_no = :siteNo";
	
	String SITES_FROM_SITEGROUPID = "select site_no from sitgrp where group_id = :groupId";
	
	String SITES_INVBYSIT = "select distinct ibs.site_no, s.full_name, s.owner_id from invbysit ibs, site s, sitgrp sg \r\n" + 
			"where s.site_no = sg.site_no \r\n" + 
			"and s.site_no = ibs.site_no \r\n" + 
			"and ibs.sku_no = :skuNo order by 1";
	
	String SUPPLIERS_SKU = "select supp.supplier_id, vend.name from TMX.SUPPLIER_ITEM supp join TMX.vendor vend on \r\n" + 
			"vend.vendor_id = supp.supplier_id where sku_no = :skuNo";

	String ADD_SUPPLIERS = "INSERT INTO TMX.SUPPLIER_ITEM (SKU_NO, SUPPLIER_ID, DATE_CREATED, USER_CREATED, DATE_MODIFIED, USER_MODIFIED) VALUES (?, ?, ?, ?, ?, ?)";
	
	String UPDATE_SUPPLIERS = "UPDATE TMX.SUPPLIER_ITEM SET SUPPLIER_ID = ?, DATE_MODIFIED = ?, USER_MODIFIED = ? where SKU_NO = ? AND SUPPLIER_ID = ?";
	
	String DELETE_SUPPLIERS = "DELETE FROM TMX.SUPPLIER_ITEM where SUPPLIER_ID = ? and SKU_NO = ?";
	
	String VENDORS_BY_TYPE_CODE = "select name, vendor_id from vendor where vendor_type_cd = :typecode";
	
	String CODE_DETAILS_BY_SKU = "select code1_id, c1.description from invtory join code1 c1 on code1_id = c1.code_id where sku_no = :skuNo\r\n" + 
			"union\r\n" + 
			"select code2_id, c2.description from invtory join code2 c2 on code2_id = c2.code_id where sku_no = :skuNo\r\n" + 
			"union\r\n" + 
			"select code3_id, c3.description from invtory join code3 c3 on code3_id = c3.code_id where sku_no = :skuNo";
	
	String INVBYSIT_STATUSES = "update invbysit set sku_status_id = :skuStatus, restrict_sale_id = :restrictSale, \r\n" + 
			"pos_sku_status_id = :posSkuStatus, itemizer_status_id = :itemizerStatus where sku_no = :skuNo and site_no = :siteNo";
	
	String FLEXIBLE_ITEM_FL_BY_SKU = "select flexible_item_fl from invtory where sku_no = :skuNo";
	
	String OWNER_ID_BY_SITE_NO = "select owner_id from site s where site_no = :siteNo";
	
	String DOMAIN_CODE_MEANING = "select code_meaning from DOMAIN_REF_CODE where DOMAIN_REF_ID IN (select DOMAIN_REF_ID from DOMAIN_REF where \r\n" + 
			"DESCRIPTION = :type AND domain_ref_code.active_fl = 'Y') and code_value = :code";
	
	String ITEM_CODES_BY_SKU = "select code1_id, code2_id, code3_id from invtory where sku_no = :skuNo";
	
	String VENDOR_IMPORT_BY_SKU = "select inv_item_lock_fl, last_item_change_vendor_id, last_item_change_dt  from invtory where sku_no = :skuNo";
	
	String SUMMARY_INFO = "select site_no, b_site.get_site_desc(site_no) as site_description, \r\n" + 
			"price1, msrp_price as suggested_retail,reorder_pt, reorder_to, \r\n" + 
			"invbysit_qty.get_invbysit_qty(site_no,  sku_no,'QTY_ON_HAND') as qty_on_hand, \r\n" + 
			"avg_cost.avg_cost(sku_no, site_no) as cost,\r\n" + 
			"calc_margin.calc_margin(avg_cost.avg_cost(sku_no, site_no),(price1/price_qty1)) as margin,\r\n" + 
			"INVBYSIT_QTY.get_invbysit_qty(site_no,  sku_no,'QTY_ON_ORD') as qty_on_ord,\r\n" + 
			"INVBYSIT_QTY.get_item_alloc_qty_in(site_no, sku_no) as alloc_qty_in,\r\n" + 
			"INVBYSIT_QTY.get_item_alloc_qty_out(site_no, sku_no) as alloc_qty_out,\r\n" + 
			"exp_rcvd_dt, download_fl \r\n" +
			"from invbysit where site_no IN (select site_no from sitgrp where group_id = :siteGroupId) and sku_no = :skuNo order by site_no asc";
	
	String UPDATE_REORDER_POINTS = "update invbysit set reorder_to = :reorderTo, reorder_pt = :reorderPt, download_fl = :authorized where sku_no = :skuNo and site_no = :siteNo";
	
	String UPDATE_INVBYSIT_INFO = "update invbysit set \r\n" + 
			"additional_tax       = :additionaltax,\r\n" + 
			"lalo_gm              = :lalogm,\r\n" + 
			"halo_gm              = :halogm,\r\n" + 
			"purchase_volume_id   = :purchasevolumeid,\r\n" + 
			"non_discount_fl      = :nondiscountfl,\r\n" + 
			"allow_pchg_fl        = :allowpricechangefl,\r\n" + 
			"discontinued_st_fl   = :discontinuedstfl,\r\n" + 
			"discontinued_by      = :discontinuedby,\r\n" + 
			"sku_status_id        = :skustatus, \r\n" + 
			"restrict_sale_id     = :restrictsale,\r\n" + 
			"pos_sku_status_id    = :posskustatus, \r\n" + 
			"itemizer_status_id   = :itemizerstatus,   \r\n" + 
			"reorder_pt           = :reorderpt,   \r\n" + 
			"reorder_to           = :reorderto,   \r\n" + 
			"velocity_cd          = :velocitycd,\r\n" + 
			"replenishment_fl     = :replenishfl,\r\n" + 
			"reorder_locked_fl    = :reorderlockfl,\r\n" + 
			"velocity_locked_fl   = :velocitylockfl,\r\n" + 
			"class_code           = :classcode,\r\n" + 
			"warehouse_inner_pack = :warehouseinnerpack,   \r\n" + 
			"max_order_qty        = :maxorderqty, \r\n" + 
			"default_dc_sku_level = :defaultdc,\r\n" + 
			"product_start_dt     = :productstartdate,\r\n" + 
			"last_replenishment_dt = :lastreplenishmentdate,\r\n" + 
			"product_end_dt       = :productenddate,\r\n" + 
			"commission_cd        = :commissioncode,\r\n" + 
			"tare_table_no        = :taretableno,\r\n" + 
			"bottle_link_no       = :linkedcharges,\r\n" + 
			"download_fl          = :downloadfl\r\n" + 
			"where sku_no = :skuno and site_no = :siteno";
	
	String PO_DETAIL_COUNT = "select count(*) from po_dtl where po_site_no in (:siteNo, tmxgbl.hq_site_no)\r\n" + 
			"and site_no= :defaultDc and sku_no = :skuNo and NVL(po_status,'N') in ('O','P')";
	
	String XREF_DETAIL_COUNT = "select count(*) from xfer_dtl where SHIP_SITE_NO = :defaultDc and RECV_SITE_NO = :siteNo\r\n" + 
			"and sku_no= :skuNo and NVL(xfer_status,'N') in ('I','S')";
	
	String DEFAULT_DC_SKU_LEVEL = "select s.site_no, s.full_name from site_attribute_value sav, site s\r\n" + 
			"where sav.attribute_id IN (select attribute_id from attribute_name\r\n" + 
			"where name_text = 'DISTRIBUTION_CENTER_FL') and sav.attribute_value = 'Y'\r\n" + 
			"and s.site_no = :defaultDc and sav.site_no = s.site_no";
}
