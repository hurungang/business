package com.runtech.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import com.runtech.web.util.Page;

public class LayoutBodyTag extends BodyTag {

	private String name;
	private String page;
	
	@Override
	public int doEndTag() throws JspException {
		try {
			LayoutTag parentTag = (LayoutTag) super.getParent();
			if(parentTag!=null){
				if(this.page!=null){
					Page page = new Page(this.page);
					parentTag.setTokenContent(this.name, page);
				}else{
					String content = getBodyContentString();
					parentTag.setTokenContent(this.name, content);
				}
			}
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	

}
