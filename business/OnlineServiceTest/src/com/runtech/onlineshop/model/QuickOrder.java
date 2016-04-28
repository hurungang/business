package com.runtech.onlineshop.model;
// Generated 2015-7-23 13:12:47 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

/**
 * QuickOrder generated by hbm2java
 */
public class QuickOrder implements java.io.Serializable {

	private Integer id;
	private User user;
	private Admin admin;
	private Area area;
	private String clientName;
	private String contact;
	private String contactPhone;
	private String commodityMemo;
	private String orderMemo;
	private Date dealTime;
	private String orderType;
	private String deliveryAddress;
	private Date planDeliveryTime;
	private String commodityConfirmComment;
	private Date confirmDate;
	private BigDecimal confirmPrice;
	private String orderComment;
	private Date deliveryTime;
	private String deliveryman;
	private String deliveryStatus;
	private Date updateTime;
	private String status;
	private String comment;
	private String remark;

	public QuickOrder() {
	}

	public QuickOrder(Integer id, Date dealTime, Date planDeliveryTime, Date confirmDate, Date deliveryTime,
			Date updateTime) {
		this.id = id;
		this.dealTime = dealTime;
		this.planDeliveryTime = planDeliveryTime;
		this.confirmDate = confirmDate;
		this.deliveryTime = deliveryTime;
		this.updateTime = updateTime;
	}

	public QuickOrder(Integer id, User user, Admin admin, Area area, String clientName, String contact,
			String contactPhone, String commodityMemo, String orderMemo, Date dealTime, String orderType,
			String deliveryAddress, Date planDeliveryTime, String commodityConfirmComment, Date confirmDate,
			BigDecimal confirmPrice, String orderComment, Date deliveryTime, String deliveryman, String deliveryStatus,
			Date updateTime, String status, String comment, String remark) {
		this.id = id;
		this.user = user;
		this.admin = admin;
		this.area = area;
		this.clientName = clientName;
		this.contact = contact;
		this.contactPhone = contactPhone;
		this.commodityMemo = commodityMemo;
		this.orderMemo = orderMemo;
		this.dealTime = dealTime;
		this.orderType = orderType;
		this.deliveryAddress = deliveryAddress;
		this.planDeliveryTime = planDeliveryTime;
		this.commodityConfirmComment = commodityConfirmComment;
		this.confirmDate = confirmDate;
		this.confirmPrice = confirmPrice;
		this.orderComment = orderComment;
		this.deliveryTime = deliveryTime;
		this.deliveryman = deliveryman;
		this.deliveryStatus = deliveryStatus;
		this.updateTime = updateTime;
		this.status = status;
		this.comment = comment;
		this.remark = remark;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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

	public String getCommodityMemo() {
		return this.commodityMemo;
	}

	public void setCommodityMemo(String commodityMemo) {
		this.commodityMemo = commodityMemo;
	}

	public String getOrderMemo() {
		return this.orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}

	public Date getDealTime() {
		return this.dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getPlanDeliveryTime() {
		return this.planDeliveryTime;
	}

	public void setPlanDeliveryTime(Date planDeliveryTime) {
		this.planDeliveryTime = planDeliveryTime;
	}

	public String getCommodityConfirmComment() {
		return this.commodityConfirmComment;
	}

	public void setCommodityConfirmComment(String commodityConfirmComment) {
		this.commodityConfirmComment = commodityConfirmComment;
	}

	public Date getConfirmDate() {
		return this.confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public BigDecimal getConfirmPrice() {
		return this.confirmPrice;
	}

	public void setConfirmPrice(BigDecimal confirmPrice) {
		this.confirmPrice = confirmPrice;
	}

	public String getOrderComment() {
		return this.orderComment;
	}

	public void setOrderComment(String orderComment) {
		this.orderComment = orderComment;
	}

	public Date getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryman() {
		return this.deliveryman;
	}

	public void setDeliveryman(String deliveryman) {
		this.deliveryman = deliveryman;
	}

	public String getDeliveryStatus() {
		return this.deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
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

}
