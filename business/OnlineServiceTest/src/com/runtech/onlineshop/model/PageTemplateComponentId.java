package com.runtech.onlineshop.model;
// Generated 2015-7-23 13:12:47 by Hibernate Tools 3.4.0.CR1

/**
 * PageTemplateComponentId generated by hbm2java
 */
public class PageTemplateComponentId implements java.io.Serializable {

	private Integer templateId;
	private Integer componentId;

	public PageTemplateComponentId() {
	}

	public PageTemplateComponentId(Integer templateId, Integer componentId) {
		this.templateId = templateId;
		this.componentId = componentId;
	}

	public Integer getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getComponentId() {
		return this.componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PageTemplateComponentId))
			return false;
		PageTemplateComponentId castOther = (PageTemplateComponentId) other;

		return ((this.getTemplateId() == castOther.getTemplateId()) || (this.getTemplateId() != null
				&& castOther.getTemplateId() != null && this.getTemplateId().equals(castOther.getTemplateId())))
				&& ((this.getComponentId() == castOther.getComponentId())
						|| (this.getComponentId() != null && castOther.getComponentId() != null
								&& this.getComponentId().equals(castOther.getComponentId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTemplateId() == null ? 0 : this.getTemplateId().hashCode());
		result = 37 * result + (getComponentId() == null ? 0 : this.getComponentId().hashCode());
		return result;
	}

}
