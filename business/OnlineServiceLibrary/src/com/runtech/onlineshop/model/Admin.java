package com.runtech.onlineshop.model;

// Generated May 2, 2011 11:17:40 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Admin generated by hbm2java
 */
public class Admin implements java.io.Serializable {

	private Integer id;
	private AdminRole adminRole;
	private String name;
	private String password;
	private String fullName;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private String status;
	private Integer updater;
	private Date updateTime;
	private String remark;
	private Set<QuickOrder> quickOrders = new HashSet<QuickOrder>(0);
	private Set<AdminLoginLog> adminLoginLogs = new HashSet<AdminLoginLog>(0);
	private Set<UserContract> userContracts = new HashSet<UserContract>(0);
	private Set<QuickComment> quickComments = new HashSet<QuickComment>(0);
	private Set<UserMaintainLog> userMaintainLogsForAdminId = new HashSet<UserMaintainLog>(
			0);
	private Set<User> users = new HashSet<User>(0);
	private Set<UserMaintainLog> userMaintainLogsForUpdater = new HashSet<UserMaintainLog>(
			0);

	public Admin() {
	}

	public Admin(AdminRole adminRole, String name, String password,
			String email, String status, Date updateTime) {
		this.adminRole = adminRole;
		this.name = name;
		this.password = password;
		this.email = email;
		this.status = status;
		this.updateTime = updateTime;
	}

	public Admin(AdminRole adminRole, String name, String password,
			String fullName, String email, String phone, String mobile,
			String address, String status, Integer updater, Date updateTime,
			String remark, Set<QuickOrder> quickOrders,
			Set<UserContract> userContracts, Set<QuickComment> quickComments,
			Set<UserMaintainLog> userMaintainLogsForAdminId, Set<User> users,
			Set<UserMaintainLog> userMaintainLogsForUpdater) {
		this.adminRole = adminRole;
		this.name = name;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.status = status;
		this.updater = updater;
		this.updateTime = updateTime;
		this.remark = remark;
		this.quickOrders = quickOrders;
		this.userContracts = userContracts;
		this.quickComments = quickComments;
		this.userMaintainLogsForAdminId = userMaintainLogsForAdminId;
		this.users = users;
		this.userMaintainLogsForUpdater = userMaintainLogsForUpdater;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdminRole getAdminRole() {
		return this.adminRole;
	}

	public void setAdminRole(AdminRole adminRole) {
		this.adminRole = adminRole;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<QuickOrder> getQuickOrders() {
		return this.quickOrders;
	}

	public void setQuickOrders(Set<QuickOrder> quickOrders) {
		this.quickOrders = quickOrders;
	}

	public Set<AdminLoginLog> getAdminLoginLogs() {
		return adminLoginLogs;
	}

	public void setAdminLoginLogs(Set<AdminLoginLog> adminLoginLogs) {
		this.adminLoginLogs = adminLoginLogs;
	}

	public Set<UserContract> getUserContracts() {
		return this.userContracts;
	}

	public void setUserContracts(Set<UserContract> userContracts) {
		this.userContracts = userContracts;
	}

	public Set<QuickComment> getQuickComments() {
		return this.quickComments;
	}

	public void setQuickComments(Set<QuickComment> quickComments) {
		this.quickComments = quickComments;
	}

	public Set<UserMaintainLog> getUserMaintainLogsForAdminId() {
		return this.userMaintainLogsForAdminId;
	}

	public void setUserMaintainLogsForAdminId(
			Set<UserMaintainLog> userMaintainLogsForAdminId) {
		this.userMaintainLogsForAdminId = userMaintainLogsForAdminId;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<UserMaintainLog> getUserMaintainLogsForUpdater() {
		return this.userMaintainLogsForUpdater;
	}

	public void setUserMaintainLogsForUpdater(
			Set<UserMaintainLog> userMaintainLogsForUpdater) {
		this.userMaintainLogsForUpdater = userMaintainLogsForUpdater;
	}

}
