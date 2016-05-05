package com.runtech.onlineshop.model;
// Generated 2016-4-30 21:40:09 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CommodityOrder generated by hbm2java
 */
public class CommodityOrder implements java.io.Serializable {

	private Integer id;
	private Consignment consignment;
	private User user;
	private Date dealTime;
	private String quickRequest;
	private String quickPropose;
	private BigDecimal freight;
	private Integer deliveryAreaId;
	private String deliveryAddress;
	private String memo;
	private Date deliveryTime;
	private String deliveryCompany;
	private String deliveryTrackId;
	private String postcode;
	private String contact;
	private String contactPhone;
	private Date updateTime;
	private String status;
	private String comment;
	private String remark;
	private Set<CommodityOrderItem> commodityOrderItems = new HashSet<CommodityOrderItem>(0);

	public CommodityOrder() {
	}

	public CommodityOrder(Date dealTime) {
		this.dealTime = dealTime;
	}

	public CommodityOrder(Consignment consignment, User user, Date dealTime, String quickRequest, String quickPropose,
			BigDecimal freight, Integer deliveryAreaId, String deliveryAddress, String memo, Date deliveryTime,
			String deliveryCompany, String deliveryTrackId, String postcode, String contact, String contactPhone,
			Date updateTime, String status, String comment, String remark,
			Set<CommodityOrderItem> commodityOrderItems) {
		this.consignment = consignment;
		this.user = user;
		this.dealTime = dealTime;
		this.quickRequest = quickRequest;
		this.quickPropose = quickPropose;
		this.freight = freight;
		this.deliveryAreaId = deliveryAreaId;
		this.deliveryAddress = deliveryAddress;
		this.memo = memo;
		this.deliveryTime = deliveryTime;
		this.deliveryCompany = deliveryCompany;
		this.deliveryTrackId = deliveryTrackId;
		this.postcode = postcode;
		this.contact = contact;
		this.contactPhone = contactPhone;
		this.updateTime = updateTime;
		this.status = status;
		this.comment = comment;
		this.remark = remark;
		this.commodityOrderItems = commodityOrderItems;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Consignment getConsignment() {
		return this.consignment;
	}

	public void setConsignment(Consignment consignment) {
		this.consignment = consignment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDealTime() {
		return this.dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getQuickRequest() {
		return this.quickRequest;
	}

	public void setQuickRequest(String quickRequest) {
		this.quickRequest = quickRequest;
	}

	public String getQuickPropose() {
		return this.quickPropose;
	}

	public void setQuickPropose(String quickPropose) {
		this.quickPropose = quickPropose;
	}

	public BigDecimal getFreight() {
		return this.freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public Integer getDeliveryAreaId() {
		return this.deliveryAreaId;
	}

	public void setDeliveryAreaId(Integer deliveryAreaId) {
		this.deliveryAreaId = deliveryAreaId;
	}

	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryCompany() {
		return this.deliveryCompany;
	}

	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}

	public String getDeliveryTrackId() {
		return this.deliveryTrackId;
	}

	public void setDeliveryTrackId(String deliveryTrackId) {
		this.deliveryTrackId = deliveryTrackId;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public void setCommodityOrderItems(Set<CommodityOrderItem> commodityOrderItems) {
		this.commodityOrderItems = commodityOrderItems;
	}

}
