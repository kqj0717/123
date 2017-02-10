package com.atguigu.jf.console.item.bean.pojo;

import java.util.Date;

public class IcitemPicture {
    private Long itempicId;

    private Long itemId;

    private String itemPicUrl;

    private Short itempicDfltFlag;

    private Integer itempicOrder;

    private Short dataState;

    private Long creator;

    private Date createTime;

    private Long modifyer;

    private Date modifyTime;

    public Long getItempicId() {
        return itempicId;
    }

    public void setItempicId(Long itempicId) {
        this.itempicId = itempicId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemPicUrl() {
        return itemPicUrl;
    }

    public void setItemPicUrl(String itemPicUrl) {
        this.itemPicUrl = itemPicUrl == null ? null : itemPicUrl.trim();
    }

    public Short getItempicDfltFlag() {
        return itempicDfltFlag;
    }

    public void setItempicDfltFlag(Short itempicDfltFlag) {
        this.itempicDfltFlag = itempicDfltFlag;
    }

    public Integer getItempicOrder() {
        return itempicOrder;
    }

    public void setItempicOrder(Integer itempicOrder) {
        this.itempicOrder = itempicOrder;
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