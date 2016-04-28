package com.runtech.onlineshop.model;

// Generated Dec 23, 2011 3:16:11 PM by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CommodityPromotion generated by hbm2java
 */
public class CommodityPromotion implements java.io.Serializable {

	private Integer id;
	private Commodity commodity;
	private String typeCode;
	private String typeTitle;
	private String specialFlag;
	private String title;
	private String description;
	private BigDecimal price;
	private Integer minOrderCount;
	private Integer currentOrderCount;
	private Integer maxOrderCount;
	private Integer maxIndividualOrderCount;
	private String userType;
	private Integer userLevel;
	private String additionalCondition;
	private Date startTime;
	private Date endTime;
	private String regulationCode;
	private String status;
	private Integer priority;
	private String remark;
	private Set<CommodityOrderItem> commodityOrderItems = new HashSet<CommodityOrderItem>(
			0);

	public CommodityPromotion() {
	}

	public CommodityPromotion(Commodity commodity, BigDecimal price,
			Date startTime, Date endTime) {
		this.commodity = commodity;
		this.price = price;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public CommodityPromotion(Commodity commodity, String typeCode,
			String typeTitle, String specialFlag, String title, String description,
			BigDecimal price, Integer minOrderCount, Integer currentOrderCount,Integer maxOrderCount,
			String userType, Integer userLevel, String additionalCondition,
			Date startTime, Date endTime, String status, Integer priority,
			String remark, Set<CommodityOrderItem> commodityOrderItems) {
		this.commodity = commodity;
		this.typeCode = typeCode;
		this.typeTitle = typeTitle;
		this.specialFlag = specialFlag;
		this.title = title;
		this.description = description;
		this.price = price;
		this.minOrderCount = minOrderCount;
		this.currentOrderCount = currentOrderCount;
		this.maxOrderCount = maxOrderCount;
		this.userType = userType;
		this.userLevel = userLevel;
		this.additionalCondition = additionalCondition;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.priority = priority;
		this.remark = remark;
		this.commodityOrderItems = commodityOrderItems;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeTitle() {
		return this.typeTitle;
	}

	public void setTypeTitle(String typeTitle) {
		this.typeTitle = typeTitle;
	}

	public String getSpecialFlag() {
		return specialFlag;
	}

	public void setSpecialFlag(String specialFlag) {
		this.specialFlag = specialFlag;
	}
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getMinOrderCount() {
		return this.minOrderCount;
	}

	public void setMinOrderCount(Integer minOrderCount) {
		this.minOrderCount = minOrderCount;
	}

	public Integer getCurrentOrderCount() {
		return this.currentOrderCount;
	}

	public void setCurrentOrderCount(Integer currentOrderCount) {
		this.currentOrderCount = currentOrderCount;
	}

	public Integer getMaxOrderCount() {
		return maxOrderCount;
	}

	public void setMaxOrderCount(Integer maxOrderCount) {
		this.maxOrderCount = maxOrderCount;
	}


	public Integer getMaxIndividualOrderCount() {
		return maxIndividualOrderCount;
	}

	public void setMaxIndividualOrderCount(Integer maxIndividualOrderCount) {
		this.maxIndividualOrderCount = maxIndividualOrderCount;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getAdditionalCondition() {
		return this.additionalCondition;
	}

	public void setAdditionalCondition(String additionalCondition) {
		this.additionalCondition = additionalCondition;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRegulationCode() {
		return regulationCode;
	}

	public void setRegulationCode(String regulationCode) {
		this.regulationCode = regulationCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<CommodityOrderItem> getCommodityOrderItems() {
		return this.commodityOrderItems;
	}

	public void setCommodityOrderItems(
			Set<CommodityOrderItem> commodityOrderItems) {
		this.commodityOrderItems = commodityOrderItems;
	}

}
