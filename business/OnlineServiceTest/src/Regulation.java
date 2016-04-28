// default package
// Generated Jul 9, 2013 11:44:45 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Regulation generated by hbm2java
 */
public class Regulation implements java.io.Serializable {

	private Integer id;
	private String regulationCode;
	private String statements;
	private String status;
	private Integer updater;
	private Date updateTime;
	private String remark;

	public Regulation() {
	}

	public Regulation(Integer id, String regulationCode, String statements,
			Date updateTime) {
		this.id = id;
		this.regulationCode = regulationCode;
		this.statements = statements;
		this.updateTime = updateTime;
	}

	public Regulation(Integer id, String regulationCode, String statements,
			String status, Integer updater, Date updateTime, String remark) {
		this.id = id;
		this.regulationCode = regulationCode;
		this.statements = statements;
		this.status = status;
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

	public String getRegulationCode() {
		return this.regulationCode;
	}

	public void setRegulationCode(String regulationCode) {
		this.regulationCode = regulationCode;
	}

	public String getStatements() {
		return this.statements;
	}

	public void setStatements(String statements) {
		this.statements = statements;
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

}
