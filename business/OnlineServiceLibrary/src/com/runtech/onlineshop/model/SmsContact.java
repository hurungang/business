package com.runtech.onlineshop.model;

// Generated Dec 23, 2011 3:16:11 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * SmsContact generated by hbm2java
 */
public class SmsContact implements java.io.Serializable {

	private Integer id;
	private SmsGroup smsGroup;
	private String phoneNumber;
	private String contact;
	private Integer updater;
	private Date updateTime;
	private String status;
	private String remark;

	public SmsContact() {
	}

	public SmsContact(Integer id, SmsGroup smsGroup, String phoneNumber,
			Date updateTime) {
		this.id = id;
		this.smsGroup = smsGroup;
		this.phoneNumber = phoneNumber;
		this.updateTime = updateTime;
	}

	public SmsContact(Integer id, SmsGroup smsGroup, String phoneNumber,
			String contact, Integer updater, Date updateTime, String status,
			String remark) {
		this.id = id;
		this.smsGroup = smsGroup;
		this.phoneNumber = phoneNumber;
		this.contact = contact;
		this.updater = updater;
		this.updateTime = updateTime;
		this.status = status;
		this.remark = remark;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SmsGroup getSmsGroup() {
		return this.smsGroup;
	}

	public void setSmsGroup(SmsGroup smsGroup) {
		this.smsGroup = smsGroup;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

}
