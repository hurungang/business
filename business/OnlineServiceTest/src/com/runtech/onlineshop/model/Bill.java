package com.runtech.onlineshop.model;
// Generated 2015-7-23 13:12:47 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Bill generated by hbm2java
 */
public class Bill implements java.io.Serializable {

	private Integer id;
	private String title;
	private Date billTime;
	private Date updateTime;
	private Date billStartTime;
	private Date billEndTime;
	private String invoiceNumber;
	private String comment;
	private BigDecimal totalAmount;
	private BigDecimal reduceAmount;
	private BigDecimal paidAmount;
	private String status;
	private String remark;
	private Set<BillCommodityOrder> billCommodityOrders = new HashSet<BillCommodityOrder>(0);

	public Bill() {
	}

	public Bill(Integer id, String title, Date billTime) {
		this.id = id;
		this.title = title;
		this.billTime = billTime;
	}

	public Bill(Integer id, String title, Date billTime, Date updateTime, Date billStartTime, Date billEndTime,
			String invoiceNumber, String comment, BigDecimal totalAmount, BigDecimal reduceAmount,
			BigDecimal paidAmount, String status, String remark, Set<BillCommodityOrder> billCommodityOrders) {
		this.id = id;
		this.title = title;
		this.billTime = billTime;
		this.updateTime = updateTime;
		this.billStartTime = billStartTime;
		this.billEndTime = billEndTime;
		this.invoiceNumber = invoiceNumber;
		this.comment = comment;
		this.totalAmount = totalAmount;
		this.reduceAmount = reduceAmount;
		this.paidAmount = paidAmount;
		this.status = status;
		this.remark = remark;
		this.billCommodityOrders = billCommodityOrders;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBillTime() {
		return this.billTime;
	}

	public void setBillTime(Date billTime) {
		this.billTime = billTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getBillStartTime() {
		return this.billStartTime;
	}

	public void setBillStartTime(Date billStartTime) {
		this.billStartTime = billStartTime;
	}

	public Date getBillEndTime() {
		return this.billEndTime;
	}

	public void setBillEndTime(Date billEndTime) {
		this.billEndTime = billEndTime;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getReduceAmount() {
		return this.reduceAmount;
	}

	public void setReduceAmount(BigDecimal reduceAmount) {
		this.reduceAmount = reduceAmount;
	}

	public BigDecimal getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<BillCommodityOrder> getBillCommodityOrders() {
		return this.billCommodityOrders;
	}

	public void setBillCommodityOrders(Set<BillCommodityOrder> billCommodityOrders) {
		this.billCommodityOrders = billCommodityOrders;
	}

}
