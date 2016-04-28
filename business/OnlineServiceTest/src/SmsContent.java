// default package
// Generated Jul 9, 2013 11:44:45 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * SmsContent generated by hbm2java
 */
public class SmsContent implements java.io.Serializable {

	private Integer id;
	private String content;
	private Integer updater;
	private Date updateTime;
	private String remark;

	public SmsContent() {
	}

	public SmsContent(String content, Date updateTime) {
		this.content = content;
		this.updateTime = updateTime;
	}

	public SmsContent(String content, Integer updater, Date updateTime,
			String remark) {
		this.content = content;
		this.updater = updater;
		this.updateTime = updateTime;
		this.remark = remark;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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
