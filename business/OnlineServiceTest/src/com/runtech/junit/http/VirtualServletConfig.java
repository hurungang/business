package com.runtech.junit.http;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class VirtualServletConfig implements ServletConfig {

	Map initParameterMap = new HashMap();
	public String getInitParameter(String arg0) {
		// TODO Auto-generated method stub
		return (String) initParameterMap.get(arg0);
	}

	public void setInitParameter(String key, String value){
		initParameterMap.put(key, value);
	}
	public Enumeration getInitParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServletName() {
		// TODO Auto-generated method stub
		return null;
	}

}
