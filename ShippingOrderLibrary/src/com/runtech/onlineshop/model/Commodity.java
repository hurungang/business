package com.runtech.onlineshop.model;
// Generated 2016-5-2 13:07:14 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Commodity generated by hbm2java
 */
public class Commodity implements java.io.Serializable {

	private Integer id;
	private CommodityProvider commodityProvider;
	private CommodityCategory commodityCategory;
	private Area area;
	private Integer commodityBaseId;
	private Integer parentCommodityId;
	private String name;
	private String shortName;
	private String summary;
	private String standard;
	private Integer minimumBuy;
	private String picturePath;
	private Integer priority;
	private String status;
	private BigDecimal originalPrice;
	private BigDecimal price;
	private Integer updater;
	private Date updateTime;
	private String remark;
	private String description;
	private String specification;
	private String instruction;
	private Set<CommodityPicture> commodityPictures = new HashSet<CommodityPicture>(0);

	public Commodity() {
	}

	public Commodity(String name, String shortName, Integer minimumBuy, String picturePath, String status,
			BigDecimal price, Date updateTime) {
		this.name = name;
		this.shortName = shortName;
		this.minimumBuy = minimumBuy;
		this.picturePath = picturePath;
		this.status = status;
		this.price = price;
		this.updateTime = updateTime;
	}

	public Commodity(CommodityProvider commodityProvider, CommodityCategory commodityCategory, Integer commodityBaseId,
			Area area, Integer parentCommodityId, String name, String shortName, String summary, String standard,
			Integer minimumBuy, String picturePath, Integer priority, String status, BigDecimal originalPrice,
			BigDecimal price, Integer updater, Date updateTime, String remark, String description, String specification,
			String instruction,  Set<CommodityPicture> commodityPictures) {
		this.commodityProvider = commodityProvider;
		this.commodityCategory = commodityCategory;
		this.commodityBaseId = commodityBaseId;
		this.area = area;
		this.parentCommodityId = parentCommodityId;
		this.name = name;
		this.shortName = shortName;
		this.summary = summary;
		this.standard = standard;
		this.minimumBuy = minimumBuy;
		this.picturePath = picturePath;
		this.priority = priority;
		this.status = status;
		this.originalPrice = originalPrice;
		this.price = price;
		this.updater = updater;
		this.updateTime = updateTime;
		this.remark = remark;
		this.description = description;
		this.specification = specification;
		this.instruction = instruction;
		this.commodityPictures = commodityPictures;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CommodityProvider getCommodityProvider() {
		return this.commodityProvider;
	}

	public void setCommodityProvider(CommodityProvider commodityProvider) {
		this.commodityProvider = commodityProvider;
	}

	public CommodityCategory getCommodityCategory() {
		return this.commodityCategory;
	}

	public void setCommodityCategory(CommodityCategory commodityCategory) {
		this.commodityCategory = commodityCategory;
	}

	public Integer getCommodityBaseId() {
		return this.commodityBaseId;
	}

	public void setCommodityBaseId(Integer commodityBaseId) {
		this.commodityBaseId = commodityBaseId;
	}

	public Integer getParentCommodityId() {
		return this.parentCommodityId;
	}

	public void setParentCommodityId(Integer parentCommodityId) {
		this.parentCommodityId = parentCommodityId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Integer getMinimumBuy() {
		return this.minimumBuy;
	}

	public void setMinimumBuy(Integer minimumBuy) {
		this.minimumBuy = minimumBuy;
	}

	public String getPicturePath() {
		return this.picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getOriginalPrice() {
		return this.originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getUpdater() {
		return this.updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpecification() {
		return this.specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getInstruction() {
		return this.instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public Set<CommodityPicture> getCommodityPictures() {
		return this.commodityPictures;
	}

	public void setCommodityPictures(Set<CommodityPicture> commodityPictures) {
		this.commodityPictures = commodityPictures;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}
