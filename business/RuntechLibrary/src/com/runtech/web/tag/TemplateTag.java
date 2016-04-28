package com.runtech.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

//import org.apache.jasper.runtime.BodyContentImpl;
//import org.apache.jasper.runtime.JspWriterImpl;

public class TemplateTag extends BodyTagSupport {

	private String templateBody;
	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			this.pageContext.getOut().append(this.getBodyContent().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getTemplateBody() {
		return templateBody;
	}
	
	public void setTemplateBody(String templateBody) {
		this.templateBody = templateBody;
	}

	@Override
	public void setBodyContent(BodyContent bodyContent) {
		// TODO Auto-generated method stub
		try {
			bodyContent.write(templateBody, 0, templateBody.length());
			this.pageContext.pushBody();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.setBodyContent(bodyContent);
	}


}
