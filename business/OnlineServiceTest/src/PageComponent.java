// default package
// Generated Jul 9, 2013 11:44:45 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PageComponent generated by hbm2java
 */
public class PageComponent implements java.io.Serializable {

	private Integer id;
	private String code;
	private String name;
	private String type;
	private Integer start;
	private Integer fetchSize;
	private String query;
	private String body;
	private String status;
	private Integer updater;
	private Date updateTime;
	private String remark;
	private Set<PageTemplateComponent> pageTemplateComponents = new HashSet<PageTemplateComponent>(
			0);

	public PageComponent() {
	}

	public PageComponent(String code, String type, Date updateTime) {
		this.code = code;
		this.type = type;
		this.updateTime = updateTime;
	}

	public PageComponent(String code, String name, String type, Integer start,
			Integer fetchSize, String query, String body, String status,
			Integer updater, Date updateTime, String remark,
			Set<PageTemplateComponent> pageTemplateComponents) {
		this.code = code;
		this.name = name;
		this.type = type;
		this.start = start;
		this.fetchSize = fetchSize;
		this.query = query;
		this.body = body;
		this.status = status;
		this.updater = updater;
		this.updateTime = updateTime;
		this.remark = remark;
		this.pageTemplateComponents = pageTemplateComponents;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStart() {
		return this.start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getFetchSize() {
		return this.fetchSize;
	}

	public void setFetchSize(Integer fetchSize) {
		this.fetchSize = fetchSize;
	}

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
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

	public Set<PageTemplateComponent> getPageTemplateComponents() {
		return this.pageTemplateComponents;
	}

	public void setPageTemplateComponents(
			Set<PageTemplateComponent> pageTemplateComponents) {
		this.pageTemplateComponents = pageTemplateComponents;
	}

}
