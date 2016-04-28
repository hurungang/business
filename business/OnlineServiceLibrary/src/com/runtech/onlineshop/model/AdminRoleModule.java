package com.runtech.onlineshop.model;

// Generated Apr 18, 2010 5:12:23 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * AdminRoleModule generated by hbm2java
 */
public class AdminRoleModule implements java.io.Serializable {

	private AdminRoleModuleId id;
	private AdminRole adminRole;
	private AdminModule adminModule;
	private Integer accessCode;
	private Integer updater;
	private Date updateTime;
	private String remark;

	public AdminRoleModule() {
	}

	public AdminRoleModule(AdminRoleModuleId id, AdminRole adminRole,
			AdminModule adminModule, Date updateTime) {
		this.id = id;
		this.adminRole = adminRole;
		this.adminModule = adminModule;
		this.updateTime = updateTime;
	}

	public AdminRoleModule(AdminRoleModuleId id, AdminRole adminRole,
			AdminModule adminModule, Integer accessCode, Integer updater,
			Date updateTime, String remark) {
		this.id = id;
		this.adminRole = adminRole;
		this.adminModule = adminModule;
		this.accessCode = accessCode;
		this.updater = updater;
		this.updateTime = updateTime;
		this.remark = remark;
	}

	public AdminRoleModuleId getId() {
		return this.id;
	}

	public void setId(AdminRoleModuleId id) {
		this.id = id;
	}

	public AdminRole getAdminRole() {
		return this.adminRole;
	}

	public void setAdminRole(AdminRole adminRole) {
		this.adminRole = adminRole;
	}

	public AdminModule getAdminModule() {
		return this.adminModule;
	}

	public void setAdminModule(AdminModule adminModule) {
		this.adminModule = adminModule;
	}

	public Integer getAccessCode() {
		return this.accessCode;
	}

	public void setAccessCode(Integer accessCode) {
		this.accessCode = accessCode;
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

}
