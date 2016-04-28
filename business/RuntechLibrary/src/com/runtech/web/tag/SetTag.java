package com.runtech.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts2.views.jsp.TagUtils;

import com.opensymphony.xwork2.util.ValueStack;

public class SetTag extends TagSupport{

	private String name;
	private Object value;
	@Override
	public int doEndTag() throws JspException {
		ValueStack stack = TagUtils.getStack(pageContext);
		stack.set(name, value);
		return super.doEndTag();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}

	
}
