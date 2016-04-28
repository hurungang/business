package com.runtech.onlineshop.model;
// Generated 2015-7-23 13:12:47 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LabelWord generated by hbm2java
 */
public class LabelWord implements java.io.Serializable {

	private String labelName;
	private Date updateTime;
	private Integer updater;
	private String remark;
	private Set<LabelCommodity> labelCommodities = new HashSet<LabelCommodity>(0);
	private Set<LabelContent> labelContents = new HashSet<LabelContent>(0);

	public LabelWord() {
	}

	public LabelWord(String labelName, Date updateTime) {
		this.labelName = labelName;
		this.updateTime = updateTime;
	}

	public LabelWord(String labelName, Date updateTime, Integer updater, String remark,
			Set<LabelCommodity> labelCommodities, Set<LabelContent> labelContents) {
		this.labelName = labelName;
		this.updateTime = updateTime;
		this.updater = updater;
		this.remark = remark;
		this.labelCommodities = labelCommodities;
		this.labelContents = labelContents;
	}

	public String getLabelName() {
		return this.labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdater() {
		return this.updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<LabelCommodity> getLabelCommodities() {
		return this.labelCommodities;
	}

	public void setLabelCommodities(Set<LabelCommodity> labelCommodities) {
		this.labelCommodities = labelCommodities;
	}

	public Set<LabelContent> getLabelContents() {
		return this.labelContents;
	}

	public void setLabelContents(Set<LabelContent> labelContents) {
		this.labelContents = labelContents;
	}

}
