package com.runtech.onlineshop.model;
// Generated 2016-5-15 18:37:17 by Hibernate Tools 3.4.0.CR1

/**
 * CommodityPicture generated by hbm2java
 */
public class CommodityPicture implements java.io.Serializable {

	private Integer id;
	private Commodity commodity;
	private String path;
	private String category;
	private String description;
	private String remark;

	public CommodityPicture() {
	}

	public CommodityPicture(Commodity commodity, String path, String category, String description, String remark) {
		this.commodity = commodity;
		this.path = path;
		this.category = category;
		this.description = description;
		this.remark = remark;
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

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
