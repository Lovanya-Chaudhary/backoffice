package com.rediron.platform.domain.model.response;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Invbysit {
	
	@JsonProperty(value = "promo_key_no")
    private Long promoKeyNo;
	
	@JsonProperty(value = "reorder_to")
    private BigDecimal reorderTo;
	
	@JsonProperty(value = "reorder_pt")
    private BigDecimal reorderPt;
    
	@JsonProperty(value = "price_qty1")
    private Integer priceQty1;
	
	@JsonProperty(value = "price_qty2")
    private Integer priceQty2;
	
	@JsonProperty(value = "price_qty3")
    private Integer priceQty3;
	
	@JsonProperty(value = "price_qty4")
    private Integer priceQty4;
	
	@JsonProperty(value = "price_qty5")
    private Integer priceQty5;
	
	@JsonProperty(value = "price_qty6")
    private Integer priceQty6;
	
	@JsonProperty(value = "price_qty7")
    private Integer priceQty7;
	
	@JsonProperty(value = "price_qty8")
    private Integer priceQty8;
	
	@JsonProperty(value = "price_qty9")
    private Integer priceQty9;
	
	@JsonProperty(value = "price_qty10")
    private Integer priceQty10;
	
	@JsonProperty(value = "price1")
    private BigDecimal price1;
	
	@JsonProperty(value = "price2")
    private BigDecimal price2;
	
	@JsonProperty(value = "price3")
    private BigDecimal price3;
	
	@JsonProperty(value = "price4")
    private BigDecimal price4;
	
	@JsonProperty(value = "price5")
    private BigDecimal price5;
	
	@JsonProperty(value = "price6")
    private BigDecimal price6;
	
	@JsonProperty(value = "price7")
    private BigDecimal price7;
	
	@JsonProperty(value = "price8")
    private BigDecimal price8;
	
	@JsonProperty(value = "price9")
    private BigDecimal price9;
	
	@JsonProperty(value = "price10")
    private BigDecimal price10;
	
	@JsonProperty(value = "epo_pct")
    private BigDecimal epoPct;
	
	@JsonProperty(value = "msrp_unit_price")
    private BigDecimal msrpUnitPrice;
	
	@JsonProperty(value = "msrp_price")
    private BigDecimal msrpPrice;
	
	@JsonProperty(value = "msrp_qty")
    private Integer msrpQty;
	
	@JsonProperty(value = "pkg_price_qty1")
    private Integer pkgPriceQty1;
	
	@JsonProperty(value = "pkg_price_qty2")
    private Integer pkgPriceQty2;
	
	@JsonProperty(value = "pkg_price_qty3")
    private Integer pkgPriceQty3;
	
	@JsonProperty(value = "pkg_price1")
    private BigDecimal pkgPrice1;
	
	@JsonProperty(value = "pkg_price2")
    private BigDecimal pkgPrice2;
    
	@JsonProperty(value = "pkg_price3")
	private BigDecimal pkgPrice3;
	
	@JsonProperty(value = "commission_cd")
    private Integer commissionCd;
	
	@JsonProperty(value = "last_rcvd_dt")
    private Date lastRcvdDt;
	
	@JsonProperty(value = "qty_last_rcvd")
    private BigDecimal qtyLastRcvd;
	
	@JsonProperty(value = "max_order_qty")
    private BigDecimal maxOrderQty;
	
	@JsonProperty(value = "download_fl")
    private String downloadFl;
	
	@JsonProperty(value = "allow_pchg_fl")
    private String allowPchgFl;
	
	@JsonProperty(value = "superseded_fl")    
    private String supersededFl;
	
	@JsonProperty(value = "prior_reorder_pt")
    private BigDecimal priorReorderPt;
	
	@JsonProperty(value = "prior_reorder_to")
    private BigDecimal priorReorderTo;
    
	@JsonProperty(value = "sku_status_id")
    private String skuStatusId;
	
	@JsonProperty(value = "non_discount_fl")
    private String nonDiscountFl;
	
	@JsonProperty(value = "restrict_sale_id")
    private String restrictSaleId;
    
	@JsonProperty(value = "new_sku_status_id")
    private String newSkuStatusId;
	
	@JsonProperty(value = "itemizer_status_id")
    private String itemizerStatusId;
    
	@JsonProperty(value = "download_ntoy_fl")
    private String downloadNToYFl;
	
	@JsonProperty(value = "warehouse_inner_pack")
    private BigDecimal warehouseInnerPack;
	
	@JsonProperty(value = "halo_gm")
    private BigDecimal haloGm;
	
	@JsonProperty(value = "lalo_gm")
    private BigDecimal laloGm;
	
	@JsonProperty(value = "replenishment_fl")
    private String replenishmentFl;
	
	@JsonProperty(value = "velocity_cd")
    private String velocityCd;
	
	@JsonProperty(value = "velocity_locked_fl")
    private String velocityLockedFl;
	
	@JsonProperty(value = "velocity_change_dt")
    private Date velocityChangeDt;
	
	@JsonProperty(value = "reorder_locked_fl")
    private String reorderLockedFl;
	
	@JsonProperty(value = "reorder_now_fl")
    private String reorderNowFl;
    
	@JsonProperty(value = "date_changed")
    private Date dateChanged;
	
	@JsonProperty(value = "default_dc_sku_level")
    private Integer defaultDcSkuLevel;
	
	@JsonProperty(value = "discontinued_st_fl")
    private String discontinuedStFl;
	
	@JsonProperty(value = "discontinued_dt")
    private Date discontinuedDt;
	
	@JsonProperty(value = "discontinued_by")
    private String discontinuedBy;
	
	@JsonProperty(value = "tare_table_no")
    private String tareTableNo;
	
	@JsonProperty(value = "promo_commission_cd")
    private Long promoCommissionCd;
	
	@JsonProperty(value = "additional_tax")
    private BigDecimal additionalTax;
	
	@JsonProperty(value = "pos_sku_status_id")    
    private String posSkuStatusId;
    
	@JsonProperty(value = "stocking_dt")
    private Date stockingDt;
	
	@JsonProperty(value = "class_code")
    private String classCode;
	
	@JsonProperty(value = "product_start_dt")
    private Date productStartDt;
	
	@JsonProperty(value = "product_end_dt")
    private Date productEndDt;
	
	@JsonProperty(value = "last_replenishment_dt")
    private Date lastReplenishmentDt;
	
	@JsonProperty(value = "qty_rsvd")
	private BigDecimal qtyRsvd;
	
	@JsonProperty(value = "qty_on_hand")
	private BigDecimal qtyOnHand;
	
	@JsonProperty(value = "qty_on_ord")
	private BigDecimal qtyOnOrd;
	
	@JsonProperty(value = "qty_on_backord")
	private BigDecimal qtyOnBackord;
	
	@JsonProperty(value = "qty_in_transit")
	private BigDecimal qtyInTransit;

}
