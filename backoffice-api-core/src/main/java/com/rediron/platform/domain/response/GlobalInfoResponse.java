package com.rediron.platform.domain.response;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class GlobalInfoResponse {
	
	private Integer skuNo;
	private String itemDescription;
	private String pkgDesc;
	
	private Integer deptNo;
	private Integer classNo;
	private Integer lineNo;

	private String upcId;
	private Integer upcModifier;
	private String vendorItem;
	
	// from global 
	
	private String receiptDesc;
	private String lookupDesc;
	private BigDecimal reportFactor;	
    private String sellUm;
    private String reportUm;
    
    private String catchWeightFl;
    private BigDecimal unitWeight;
    private BigDecimal unitCube;
    private String weighedItemFl;
    
    private String primaryVendorItem;
    private BigDecimal lcuQty;
    
    private String equivUm;
    private BigDecimal equivFactor;
    
    private Integer weeksHistory;
    private Integer periodsHistory;
    private Date createDt;
    private Date changeDt;
    
    private String captureOrderNoFl;
    private String captureSerialNoFl;
    private String downloadFl;
    private String negativePriceFl;
    
    private String reorderFl;
    private String discontinuedFl;
    private String obsoleteFl;
    
    private String storeCpnFl;
    private String nonInventoryFl;
    private String subInvFl;
    private String supplyItemFl;
    private String coreFl;
    private String flexibleItemFl;

    private Integer deptGrpNo;
    private String kitTypeCd;
    private Integer kitSkuNo;
    private boolean kitHdrFl;
    private BigDecimal warehouseInnerPack;
}
