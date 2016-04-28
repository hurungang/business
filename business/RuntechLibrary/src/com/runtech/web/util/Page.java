package com.runtech.web.util;

import org.apache.commons.lang.StringUtils;
public class Page {
	String name;
	String uri;
	String param;
	
	public Page(String pagePath) {
		this.uri = pagePath;
	}
	
	public Page() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(obj instanceof Page){
			Page p = (Page)obj;
			return StringUtils.equals(name, p.getName()) &&
				   StringUtils.equals(uri, p.getUri()) &&
				   StringUtils.equals(param, p.getParam());
		}
		return false;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("NAME:");
		sb.append(name);
		sb.append(",URI:");
		sb.append(uri);
		sb.append(",PARAM:");
		sb.append(param);
		return sb.toString();
	}
	
	public Object clone(){
		Page p = new Page();
		p.setName(name);
		p.setParam(param);
		p.setUri(uri);
		return p;
	}
}
