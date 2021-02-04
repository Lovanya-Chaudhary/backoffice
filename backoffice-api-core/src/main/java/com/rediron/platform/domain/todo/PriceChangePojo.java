package com.rediron.platform.domain.todo;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlType(name = "promo_detail")
@XmlRootElement(name = "promotion")
@XmlAccessorType(XmlAccessType.NONE)
public class PriceChangePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "ad_id")
    private String adId;
    @XmlElement(name = "ad_site_no")
    private Integer adSiteNo;
    private String applyFl;
    private Integer classNo;
    private Double corePrice;
    private Double cost;
    private Integer deptgrpNo;
    private Integer deptNo;
    private String description;
    @XmlElement(name = "group_id")
    private String groupId;
    private String itemizerStatusId;
    private Integer lineNo;
    private Integer linksNo;
    @XmlElement(name = "markdown_pct")
    private Double markdownPct;
    @XmlElement(name = "pkg_price1")
    private Double pkgPrice1;
    @XmlElement(name = "pkg_price2")
    private Double pkgPrice2;
    @XmlElement(name = "pkg_price3")
    private Double pkgPrice3;
    @XmlElement(name = "pkg_price_qty1")
    private Integer pkgPriceQty1;
    @XmlElement(name = "pkg_price_qty2")
    private Integer pkgPriceQty2;
    @XmlElement(name = "pkg_price_qty3")
    private Integer pkgPriceQty3;
    @XmlElement(name = "price1")
    private Double price1;
    @XmlElement(name = "price10")
    private Double price10;
    @XmlElement(name = "price2")
    private Double price2;
    @XmlElement(name = "price3")
    private Double price3;
    @XmlElement(name = "price4")
    private Double price4;
    @XmlElement(name = "price5")
    private Double price5;
    @XmlElement(name = "price6")
    private Double price6;
    @XmlElement(name = "price7")
    private Double price7;
    @XmlElement(name = "price8")
    private Double price8;
    @XmlElement(name = "price9")
    private Double price9;
    @XmlElement(name = "price_qty1")
    private Integer priceQty1;
    @XmlElement(name = "price_qty10")
    private Integer priceQty10;
    @XmlElement(name = "price_qty2")
    private Integer priceQty2;
    @XmlElement(name = "price_qty3")
    private Integer priceQty3;
    @XmlElement(name = "price_qty4")
    private Integer priceQty4;
    @XmlElement(name = "price_qty5")
    private Integer priceQty5;
    @XmlElement(name = "price_qty6")
    private Integer priceQty6;
    @XmlElement(name = "price_qty7")
    private Integer priceQty7;
    @XmlElement(name = "price_qty8")
    private Integer priceQty8;
    @XmlElement(name = "price_qty9")
    private Integer priceQty9;
    @XmlElement(name = "price_qty")
    private Date processedDt;
    private String processedFl;
    private Integer promoKeyNo;
    private Double refPromoKeyNo;
    private String selItemizer1Fl;
    private String selItemizer2Fl;
    @XmlElement(name = "sku_no")
    private Integer skuNo;
    private String skuStatusId;
    @XmlElement(name = "start_date")
    private Date startDt;
    @XmlElement(name = "stop_date")
    private Date stopDt;
    private String type;
    private String type2;
    private Double unitPrice1;
    private Double unitPrice10;
    private Double unitPrice2;
    private Double unitPrice3;
    private Double unitPrice4;
    private Double unitPrice5;
    private Double unitPrice6;
    private Double unitPrice7;
    private Double unitPrice8;
    private Double unitPrice9;
    private String updateItemizerStatusIdFl;
    private String updateSkuStatusIdFl;
    private String vendorId;
    @XmlElement(name = "op_code")
    private String opCode;
    @XmlElement(name = "external_ref_id")
    private String externalRefId;
    @XmlElement(name = "external_ref2_id")
    private String externalRef2Id;
    @XmlElement(name = "item_id")
    private String itemId;
    @XmlElement(name = "item_type")
    private String itemType;
    private String colMap;

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public Integer getAdSiteNo() {
        return adSiteNo;
    }

    public void setAdSiteNo(Integer adSiteNo) {
        this.adSiteNo = adSiteNo;
    }

    public String getApplyFl() {
        return applyFl;
    }

    public void setApplyFl(String applyFl) {
        this.applyFl = applyFl;
    }

    public Integer getClassNo() {
        return classNo;
    }

    public void setClassNo(Integer classNo) {
        this.classNo = classNo;
    }

    public Double getCorePrice() {
        return corePrice;
    }

    public void setCorePrice(Double corePrice) {
        this.corePrice = corePrice;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getDeptgrpNo() {
        return deptgrpNo;
    }

    public void setDeptgrpNo(Integer deptgrpNo) {
        this.deptgrpNo = deptgrpNo;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getItemizerStatusId() {
        return itemizerStatusId;
    }

    public void setItemizerStatusId(String itemizerStatusId) {
        this.itemizerStatusId = itemizerStatusId;
    }

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public Integer getLinksNo() {
        return linksNo;
    }

    public void setLinksNo(Integer linksNo) {
        this.linksNo = linksNo;
    }

    public Double getMarkdownPct() {
        return markdownPct;
    }

    public void setMarkdownPct(Double markdownPct) {
        this.markdownPct = markdownPct;
    }

    public Double getPkgPrice1() {
        return pkgPrice1;
    }

    public void setPkgPrice1(Double pkgPrice1) {
        this.pkgPrice1 = pkgPrice1;
    }

    public Double getPkgPrice2() {
        return pkgPrice2;
    }

    public void setPkgPrice2(Double pkgPrice2) {
        this.pkgPrice2 = pkgPrice2;
    }

    public Double getPkgPrice3() {
        return pkgPrice3;
    }

    public void setPkgPrice3(Double pkgPrice3) {
        this.pkgPrice3 = pkgPrice3;
    }

    public Integer getPkgPriceQty1() {
        return pkgPriceQty1;
    }

    public void setPkgPriceQty1(Integer pkgPriceQty1) {
        this.pkgPriceQty1 = pkgPriceQty1;
    }

    public Integer getPkgPriceQty2() {
        return pkgPriceQty2;
    }

    public void setPkgPriceQty2(Integer pkgPriceQty2) {
        this.pkgPriceQty2 = pkgPriceQty2;
    }

    public Integer getPkgPriceQty3() {
        return pkgPriceQty3;
    }

    public void setPkgPriceQty3(Integer pkgPriceQty3) {
        this.pkgPriceQty3 = pkgPriceQty3;
    }
    
    public Double getPrice(int pos) {
        switch(pos) {
            case 1:
                return getPrice1();
            case 2:
                return getPrice2();
            case 3:
                return getPrice3();
            case 4:
                return getPrice4();
            case 5:
                return getPrice5();
            case 6:
                return getPrice6();
            case 7:
                return getPrice7();
            case 8:
                return getPrice8();
            case 9:
                return getPrice9();
            case 10:
                return getPrice10();
            default:
                throw new IndexOutOfBoundsException();
        }
    }
    
    public void setPrice(int pos, Double price) {
        switch(pos) {
            case 1:
                setPrice1(price);
                break;
            case 2:
                setPrice2(price);
                break;
            case 3:
                setPrice3(price);
                break;
            case 4:
                setPrice4(price);
                break;
            case 5:
                setPrice5(price);
                break;
            case 6:
                setPrice6(price);
                break;
            case 7:
                setPrice7(price);
                break;
            case 8:
                setPrice8(price);
                break;
            case 9:
                setPrice9(price);
                break;
            case 10:
                setPrice10(price);
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public Double getPrice1() {
        return price1;
    }

    public void setPrice1(Double price1) {
        this.price1 = price1;
    }

    public Double getPrice10() {
        return price10;
    }

    public void setPrice10(Double price10) {
        this.price10 = price10;
    }

    public Double getPrice2() {
        return price2;
    }

    public void setPrice2(Double price2) {
        this.price2 = price2;
    }

    public Double getPrice3() {
        return price3;
    }

    public void setPrice3(Double price3) {
        this.price3 = price3;
    }

    public Double getPrice4() {
        return price4;
    }

    public void setPrice4(Double price4) {
        this.price4 = price4;
    }

    public Double getPrice5() {
        return price5;
    }

    public void setPrice5(Double price5) {
        this.price5 = price5;
    }

    public Double getPrice6() {
        return price6;
    }

    public void setPrice6(Double price6) {
        this.price6 = price6;
    }

    public Double getPrice7() {
        return price7;
    }

    public void setPrice7(Double price7) {
        this.price7 = price7;
    }

    public Double getPrice8() {
        return price8;
    }

    public void setPrice8(Double price8) {
        this.price8 = price8;
    }

    public Double getPrice9() {
        return price9;
    }

    public void setPrice9(Double price9) {
        this.price9 = price9;
    }
    
    public Integer getPriceQty(int pos) {
        switch(pos) {
            case 1:
                return getPriceQty1();
            case 2:
                return getPriceQty2();
            case 3:
                return getPriceQty3();
            case 4:
                return getPriceQty4();
            case 5:
                return getPriceQty5();
            case 6:
                return getPriceQty6();
            case 7:
                return getPriceQty7();
            case 8:
                return getPriceQty8();
            case 9:
                return getPriceQty9();
            case 10:
                return getPriceQty10();
            default:
                throw new IndexOutOfBoundsException();
        }
    }
    
    public void setPriceQty(int pos, Integer qty) {
        switch(pos) {
            case 1:
                setPriceQty1(qty);
                break;
            case 2:
                setPriceQty2(qty);
                break;
            case 3:
                setPriceQty3(qty);
                break;
            case 4:
                setPriceQty4(qty);
                break;
            case 5:
                setPriceQty5(qty);
                break;
            case 6:
                setPriceQty6(qty);
                break;
            case 7:
                setPriceQty7(qty);
                break;
            case 8:
                setPriceQty8(qty);
                break;
            case 9:
                setPriceQty9(qty);
                break;
            case 10:
                setPriceQty10(qty);
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public Integer getPriceQty1() {
        return priceQty1;
    }

    public void setPriceQty1(Integer priceQty1) {
        this.priceQty1 = priceQty1;
    }

    public Integer getPriceQty10() {
        return priceQty10;
    }

    public void setPriceQty10(Integer priceQty10) {
        this.priceQty10 = priceQty10;
    }

    public Integer getPriceQty2() {
        return priceQty2;
    }

    public void setPriceQty2(Integer priceQty2) {
        this.priceQty2 = priceQty2;
    }

    public Integer getPriceQty3() {
        return priceQty3;
    }

    public void setPriceQty3(Integer priceQty3) {
        this.priceQty3 = priceQty3;
    }

    public Integer getPriceQty4() {
        return priceQty4;
    }

    public void setPriceQty4(Integer priceQty4) {
        this.priceQty4 = priceQty4;
    }

    public Integer getPriceQty5() {
        return priceQty5;
    }

    public void setPriceQty5(Integer priceQty5) {
        this.priceQty5 = priceQty5;
    }

    public Integer getPriceQty6() {
        return priceQty6;
    }

    public void setPriceQty6(Integer priceQty6) {
        this.priceQty6 = priceQty6;
    }

    public Integer getPriceQty7() {
        return priceQty7;
    }

    public void setPriceQty7(Integer priceQty7) {
        this.priceQty7 = priceQty7;
    }

    public Integer getPriceQty8() {
        return priceQty8;
    }

    public void setPriceQty8(Integer priceQty8) {
        this.priceQty8 = priceQty8;
    }

    public Integer getPriceQty9() {
        return priceQty9;
    }

    public void setPriceQty9(Integer priceQty9) {
        this.priceQty9 = priceQty9;
    }

    public Date getProcessedDt() {
        return processedDt;
    }

    public void setProcessedDt(Date processedDt) {
        this.processedDt = processedDt;
    }

    public String getProcessedFl() {
        return processedFl;
    }

    public void setProcessedFl(String processedFl) {
        this.processedFl = processedFl;
    }

    public Integer getPromoKeyNo() {
        return promoKeyNo;
    }

    public void setPromoKeyNo(Integer promoKeyNo) {
        this.promoKeyNo = promoKeyNo;
    }

    public Double getRefPromoKeyNo() {
        return refPromoKeyNo;
    }

    public void setRefPromoKeyNo(Double refPromoKeyNo) {
        this.refPromoKeyNo = refPromoKeyNo;
    }

    public String getSelItemizer1Fl() {
        return selItemizer1Fl;
    }

    public void setSelItemizer1Fl(String selItemizer1Fl) {
        this.selItemizer1Fl = selItemizer1Fl;
    }

    public String getSelItemizer2Fl() {
        return selItemizer2Fl;
    }

    public void setSelItemizer2Fl(String selItemizer2Fl) {
        this.selItemizer2Fl = selItemizer2Fl;
    }

    public Integer getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(Integer skuNo) {
        this.skuNo = skuNo;
    }

    public String getSkuStatusId() {
        return skuStatusId;
    }

    public void setSkuStatusId(String skuStatusId) {
        this.skuStatusId = skuStatusId;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getStopDt() {
        return stopDt;
    }

    public void setStopDt(Date stopDt) {
        this.stopDt = stopDt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public Double getUnitPrice1() {
        return unitPrice1;
    }

    public void setUnitPrice1(Double unitPrice1) {
        this.unitPrice1 = unitPrice1;
    }

    public Double getUnitPrice10() {
        return unitPrice10;
    }

    public void setUnitPrice10(Double unitPrice10) {
        this.unitPrice10 = unitPrice10;
    }

    public Double getUnitPrice2() {
        return unitPrice2;
    }

    public void setUnitPrice2(Double unitPrice2) {
        this.unitPrice2 = unitPrice2;
    }

    public Double getUnitPrice3() {
        return unitPrice3;
    }

    public void setUnitPrice3(Double unitPrice3) {
        this.unitPrice3 = unitPrice3;
    }

    public Double getUnitPrice4() {
        return unitPrice4;
    }

    public void setUnitPrice4(Double unitPrice4) {
        this.unitPrice4 = unitPrice4;
    }

    public Double getUnitPrice5() {
        return unitPrice5;
    }

    public void setUnitPrice5(Double unitPrice5) {
        this.unitPrice5 = unitPrice5;
    }

    public Double getUnitPrice6() {
        return unitPrice6;
    }

    public void setUnitPrice6(Double unitPrice6) {
        this.unitPrice6 = unitPrice6;
    }

    public Double getUnitPrice7() {
        return unitPrice7;
    }

    public void setUnitPrice7(Double unitPrice7) {
        this.unitPrice7 = unitPrice7;
    }

    public Double getUnitPrice8() {
        return unitPrice8;
    }

    public void setUnitPrice8(Double unitPrice8) {
        this.unitPrice8 = unitPrice8;
    }

    public Double getUnitPrice9() {
        return unitPrice9;
    }

    public void setUnitPrice9(Double unitPrice9) {
        this.unitPrice9 = unitPrice9;
    }

    public String getUpdateItemizerStatusIdFl() {
        return updateItemizerStatusIdFl;
    }

    public void setUpdateItemizerStatusIdFl(String updateItemizerStatusIdFl) {
        this.updateItemizerStatusIdFl = updateItemizerStatusIdFl;
    }

    public String getUpdateSkuStatusIdFl() {
        return updateSkuStatusIdFl;
    }

    public void setUpdateSkuStatusIdFl(String updateSkuStatusIdFl) {
        this.updateSkuStatusIdFl = updateSkuStatusIdFl;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getExternalRefId() {
        return externalRefId;
    }

    public void setExternalRefId(String externalRefId) {
        this.externalRefId = externalRefId;
    }

    public String getExternalRef2Id() {
        return externalRef2Id;
    }

    public void setExternalRef2Id(String externalRef2Id) {
        this.externalRef2Id = externalRef2Id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getColMap() {
        return colMap;
    }

    public void setColMap(String colMap) {
        this.colMap = colMap;
    }

    public void addColMap(String newField) {
        this.colMap = PojoUtils.addColMap(this.colMap, newField);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
