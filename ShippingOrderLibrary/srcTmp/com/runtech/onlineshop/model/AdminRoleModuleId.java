package com.runtech.onlineshop.model;
// Generated 2016-5-4 9:52:57 by Hibernate Tools 3.4.0.CR1

/**
 * AdminRoleModuleId generated by hbm2java
 */
public class AdminRoleModuleId implements java.io.Serializable {

	private Integer roleId;
	private Integer moduleId;

	public AdminRoleModuleId() {
	}

	public AdminRoleModuleId(Integer roleId, Integer moduleId) {
		this.roleId = roleId;
		this.moduleId = moduleId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AdminRoleModuleId))
			return false;
		AdminRoleModuleId castOther = (AdminRoleModuleId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this.getRoleId() != null
				&& castOther.getRoleId() != null && this.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getModuleId() == castOther.getModuleId()) || (this.getModuleId() != null
						&& castOther.getModuleId() != null && this.getModuleId().equals(castOther.getModuleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result + (getModuleId() == null ? 0 : this.getModuleId().hashCode());
		return result;
	}

}
