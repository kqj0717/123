package com.atguigu.jf.console.item.bean.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Item {
    private Long itemId;

    private Long mallCatId;

    private Long itemCode;

    private Long mchtId;

    private String mchtName;

    private Integer itemType;

    private String itemMchtNo;

    private String itemName;

    private String itemShortName;

    private String itemDesc;

    private String itemDesc1;

    private String itemDesc2;

    private String itemDesc3;

    private String itemDesc4;

    private BigDecimal itemCostPrice;

    private BigDecimal itemMarketPrice;

    private BigDecimal itemSalePrice;

    private Long itemSaleNum;

    private Long itemNum;

    private Date itemValidStart;

    private Date itemValidEnd;

    private String itemSaleAttr;

    private Integer itemApprState;

    private Integer itemUdState;

    private Date itemStateTime;

    private Short itemSchdlonState;

    private Date itemSchdlonTime;

    private Date itemSchdldownTime;

    private Short itemForceoffState;

    private Date itemForceoffTime;

    private String itemForceoffReason;

    private String itemSeoKeyword;

    private String itemSeoDesc;

    private Integer itemMaxBuyNums;

    private Short itemSaleFlag;

    private String optlPayTypeStr;

    private Integer dataVersion;

    private Short dataState;

    private Long creator;

    private Date createTime;

    private Long modifyer;

    private Date modifyTime;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getMallCatId() {
        return mallCatId;
    }

    public void setMallCatId(Long mallCatId) {
        this.mallCatId = mallCatId;
    }

    public Long getItemCode() {
        return itemCode;
    }

    public void setItemCode(Long itemCode) {
        this.itemCode = itemCode;
    }

    public Long getMchtId() {
        return mchtId;
    }

    public void setMchtId(Long mchtId) {
        this.mchtId = mchtId;
    }

    public String getMchtName() {
        return mchtName;
    }

    public void setMchtName(String mchtName) {
        this.mchtName = mchtName == null ? null : mchtName.trim();
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getItemMchtNo() {
        return itemMchtNo;
    }

    public void setItemMchtNo(String itemMchtNo) {
        this.itemMchtNo = itemMchtNo == null ? null : itemMchtNo.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemShortName() {
        return itemShortName;
    }

    public void setItemShortName(String itemShortName) {
        this.itemShortName = itemShortName == null ? null : itemShortName.trim();
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }

    public String getItemDesc1() {
        return itemDesc1;
    }

    public void setItemDesc1(String itemDesc1) {
        this.itemDesc1 = itemDesc1 == null ? null : itemDesc1.trim();
    }

    public String getItemDesc2() {
        return itemDesc2;
    }

    public void setItemDesc2(String itemDesc2) {
        this.itemDesc2 = itemDesc2 == null ? null : itemDesc2.trim();
    }

    public String getItemDesc3() {
        return itemDesc3;
    }

    public void setItemDesc3(String itemDesc3) {
        this.itemDesc3 = itemDesc3 == null ? null : itemDesc3.trim();
    }

    public String getItemDesc4() {
        return itemDesc4;
    }

    public void setItemDesc4(String itemDesc4) {
        this.itemDesc4 = itemDesc4 == null ? null : itemDesc4.trim();
    }

    public BigDecimal getItemCostPrice() {
        return itemCostPrice;
    }

    public void setItemCostPrice(BigDecimal itemCostPrice) {
        this.itemCostPrice = itemCostPrice;
    }

    public BigDecimal getItemMarketPrice() {
        return itemMarketPrice;
    }

    public void setItemMarketPrice(BigDecimal itemMarketPrice) {
        this.itemMarketPrice = itemMarketPrice;
    }

    public BigDecimal getItemSalePrice() {
        return itemSalePrice;
    }

    public void setItemSalePrice(BigDecimal itemSalePrice) {
        this.itemSalePrice = itemSalePrice;
    }

    public Long getItemSaleNum() {
        return itemSaleNum;
    }

    public void setItemSaleNum(Long itemSaleNum) {
        this.itemSaleNum = itemSaleNum;
    }

    public Long getItemNum() {
        return itemNum;
    }

    public void setItemNum(Long itemNum) {
        this.itemNum = itemNum;
    }

    public Date getItemValidStart() {
        return itemValidStart;
    }

    public void setItemValidStart(Date itemValidStart) {
        this.itemValidStart = itemValidStart;
    }

    public Date getItemValidEnd() {
        return itemValidEnd;
    }

    public void setItemValidEnd(Date itemValidEnd) {
        this.itemValidEnd = itemValidEnd;
    }

    public String getItemSaleAttr() {
        return itemSaleAttr;
    }

    public void setItemSaleAttr(String itemSaleAttr) {
        this.itemSaleAttr = itemSaleAttr == null ? null : itemSaleAttr.trim();
    }

    public Integer getItemApprState() {
        return itemApprState;
    }

    public void setItemApprState(Integer itemApprState) {
        this.itemApprState = itemApprState;
    }

    public Integer getItemUdState() {
        return itemUdState;
    }

    public void setItemUdState(Integer itemUdState) {
        this.itemUdState = itemUdState;
    }

    public Date getItemStateTime() {
        return itemStateTime;
    }

    public void setItemStateTime(Date itemStateTime) {
        this.itemStateTime = itemStateTime;
    }

    public Short getItemSchdlonState() {
        return itemSchdlonState;
    }

    public void setItemSchdlonState(Short itemSchdlonState) {
        this.itemSchdlonState = itemSchdlonState;
    }

    public Date getItemSchdlonTime() {
        return itemSchdlonTime;
    }

    public void setItemSchdlonTime(Date itemSchdlonTime) {
        this.itemSchdlonTime = itemSchdlonTime;
    }

    public Date getItemSchdldownTime() {
        return itemSchdldownTime;
    }

    public void setItemSchdldownTime(Date itemSchdldownTime) {
        this.itemSchdldownTime = itemSchdldownTime;
    }

    public Short getItemForceoffState() {
        return itemForceoffState;
    }

    public void setItemForceoffState(Short itemForceoffState) {
        this.itemForceoffState = itemForceoffState;
    }

    public Date getItemForceoffTime() {
        return itemForceoffTime;
    }

    public void setItemForceoffTime(Date itemForceoffTime) {
        this.itemForceoffTime = itemForceoffTime;
    }

    public String getItemForceoffReason() {
        return itemForceoffReason;
    }

    public void setItemForceoffReason(String itemForceoffReason) {
        this.itemForceoffReason = itemForceoffReason == null ? null : itemForceoffReason.trim();
    }

    public String getItemSeoKeyword() {
        return itemSeoKeyword;
    }

    public void setItemSeoKeyword(String itemSeoKeyword) {
        this.itemSeoKeyword = itemSeoKeyword == null ? null : itemSeoKeyword.trim();
    }

    public String getItemSeoDesc() {
        return itemSeoDesc;
    }

    public void setItemSeoDesc(String itemSeoDesc) {
        this.itemSeoDesc = itemSeoDesc == null ? null : itemSeoDesc.trim();
    }

    public Integer getItemMaxBuyNums() {
        return itemMaxBuyNums;
    }

    public void setItemMaxBuyNums(Integer itemMaxBuyNums) {
        this.itemMaxBuyNums = itemMaxBuyNums;
    }

    public Short getItemSaleFlag() {
        return itemSaleFlag;
    }

    public void setItemSaleFlag(Short itemSaleFlag) {
        this.itemSaleFlag = itemSaleFlag;
    }

    public String getOptlPayTypeStr() {
        return optlPayTypeStr;
    }

    public void setOptlPayTypeStr(String optlPayTypeStr) {
        this.optlPayTypeStr = optlPayTypeStr == null ? null : optlPayTypeStr.trim();
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }

    public Short getDataState() {
        return dataState;
    }

    public void setDataState(Short dataState) {
        this.dataState = dataState;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyer() {
        return modifyer;
    }

    public void setModifyer(Long modifyer) {
        this.modifyer = modifyer;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}