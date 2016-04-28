/*
 * Created on Nov 9, 2007
 * Created by Harry
 */
package com.runtech.junit.http;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;


public class VirtualFilterConfig implements FilterConfig {

	private Map configMap = new HashMap();
	private String filterName;
	private ServletContext servletContext=new VirtualServletContext();
	
	public FilterConfig putInitParameter(String name, String value){
		this.configMap.put(name,value);
		return this;
	}
	/* (non-Javadoc)
	 * @see javax.servlet.FilterConfig#getFilterName()
	 */
	public String getFilterName() {
		return this.filterName;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.FilterConfig#getServletContext()
	 */
	public ServletContext getServletContext() {
		return this.servletContext;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.FilterConfig#getInitParameter(java.lang.String)
	 */
	public String getInitParameter(String name) {
		return (String)configMap.get(name);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.FilterConfig#getInitParameterNames()
	 */
	public Enumeration getInitParameterNames() {
		throw new RuntimeException();
	}

	/**
	 * @param filterName The filterName to set.
	 */
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	/**
	 * @param servletContext The servletContext to set.
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
