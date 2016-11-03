package com.runtech.onlineshop.model;
// Generated 2016-5-15 18:37:17 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CommodityCategory generated by hbm2java
 */
public class CommodityCategory implements java.io.Serializable {

	private Integer id;
	private CommodityCategory commodityCategory;
	private String name;
	private String description;
	private String status;
	private Integer updater;
	private Date updateTime;
	private Integer priority;
	private String remark;
	private Set<Commodity> commodities = new HashSet<Commodity>(0);
	private Set<CommodityCategory> commodityCategories = new HashSet<CommodityCategory>(0);
	private Set<Content> contents = new HashSet<Content>(0);

	public CommodityCategory() {
	}

	public CommodityCategory(String name, String status, Date updateTime) {
		this.name = name;
		this.status = status;
		this.updateTime = updateTime;
	}

	public CommodityCategory(CommodityCategory commodityCategory, String name, String description, String status,
			Integer updater, Date updateTime, Integer priority, String remark, Set<Commodity> commodities,
			Set<CommodityCategory> commodityCategories, Set<Content> contents) {
		this.commodityCategory = commodityCategory;
		this.name = name;
		this.description = description;
		this.status = status;
		this.updater = updater;
		this.updateTime = updateTime;
		this.priority = priority;
		this.remark = remark;
		this.commodities = commodities;
		this.commodityCategories = commodityCategories;
		this.contents = contents;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CommodityCategory getCommodityCategory() {
		return this.commodityCategory;
	}

	public void setCommodityCategory(CommodityCategory commodityCategory) {
		this.commodityCategory = commodityCategory;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Set<Commodity> getCommodities() {
		return this.commodities;
	}

	public void setCommodities(Set<Commodity> commodities) {
		this.commodities = commodities;
	}

	public Set<CommodityCategory> getCommodityCategories() {
		return this.commodityCategories;
	}

	public void setCommodityCategories(Set<CommodityCategory> commodityCategories) {
		this.commodityCategories = commodityCategories;
	}

	public Set<Content> getContents() {
		return this.contents;
	}

	public void setContents(Set<Content> contents) {
		this.contents = contents;
	}

}
