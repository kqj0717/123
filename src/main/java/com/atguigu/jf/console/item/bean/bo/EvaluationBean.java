package com.atguigu.jf.console.item.bean.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//主要是评价管理模块的列表
public class EvaluationBean {
	
	//评论账号
	private String userNickname;
	
	//商品id
    private Long evaluationId;

    //商品名称
    private String itemName;
    
    //商品图片
    private String itemPicUrl;
    
    //评论分值
    private BigDecimal evaluationGrade;
   
    //评论内容
    private String evaluationContent;
    
    //评论图片
    private List<String> evaluationPicUrls;
    
    //评论店铺
    private String shopName;
    
    //评论时间
    private Date evaluationTime;

    // 评论状态
 	private Integer evaluationStatus;
 	
	public Integer getEvaluationStatus() {
		return evaluationStatus;
	}

	public void setEvaluationStatus(Integer evaluationStatus) {
		this.evaluationStatus = evaluationStatus;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public Long getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPicUrl() {
		return itemPicUrl;
	}

	public void setItemPicUrl(String itemPicUrl) {
		this.itemPicUrl = itemPicUrl;
	}

	public BigDecimal getEvaluationGrade() {
		return evaluationGrade;
	}

	public void setEvaluationGrade(BigDecimal evaluationGrade) {
		this.evaluationGrade = evaluationGrade;
	}

	public String getEvaluationContent() {
		return evaluationContent;
	}

	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}

	public List<String> getEvaluationPicUrls() {
		return evaluationPicUrls;
	}

	public void setEvaluationPicUrls(List<String> evaluationPicUrls) {
		this.evaluationPicUrls = evaluationPicUrls;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Date getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(Date evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	@Override
	public String toString() {
		return "EvaluationBean [userNickname=" + userNickname + ", evaluationId=" + evaluationId + ", itemName="
				+ itemName + ", itemPicUrl=" + itemPicUrl + ", evaluationGrade=" + evaluationGrade
				+ ", evaluationContent=" + evaluationContent + ", evaluationPicUrls=" + evaluationPicUrls
				+ ", shopName=" + shopName + ", evaluationTime=" + evaluationTime + ", evaluationStatus="
				+ evaluationStatus + "]";
	}
    
	
	
   }