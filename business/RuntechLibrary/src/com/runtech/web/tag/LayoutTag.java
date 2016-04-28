package com.runtech.web.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.xml.sax.SAXException;

import com.runtech.web.util.Constant;
import com.runtech.web.util.LayoutConfig;
import com.runtech.web.util.Page;

/**
 * @author Harry
 */
public class LayoutTag extends com.runtech.web.tag.BodyTag {
	
	public class Token {

		private int start;
		private int end;
		private String name;
		
		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}

		public String getName() {
			return name;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	protected String template;
	protected String errorPage;
	protected String templateContent;
	private Map<String, Object> tokenContentMap = new HashMap<String, Object>();
	
	
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}
	public int doEndTag() throws JspException {
	
		try{
			HttpServletRequest req = (HttpServletRequest)pageContext.getRequest();
			HttpServletResponse res = (HttpServletResponse)pageContext.getResponse();
			String templateString = null;
			if(this.templateContent!=null){
				templateString = this.templateContent;
			}
			else if(this.template!=null){
				templateString = getPageContentString(this.template);
			}
			if(templateString==null)
			{
				throw new JspException("no template defined");
			}
			String content = getBodyContentString();
			
			List<Token> tokenList = listToken(templateString);
			int curIdx = 0;
			for(int i=0;i<tokenList.size();i++){
				Token token = (Token)tokenList.get(i);
				pageContext.getOut().write(templateString.substring(curIdx ,token.start));				
				try{
					Object tokenContent = this.getTokenContent(token.name);
					if(tokenContent!=null){
						if(tokenContent instanceof String){
							pageContext.getOut().write((String)tokenContent);
						}else if(tokenContent instanceof Page){
							this.pageContext.include(((Page) tokenContent).getUri());
						}
					}else{
						Page tokenPage = getTokenPage(token.name);
						if(tokenPage!=null){
							this.pageContext.include(tokenPage.getUri());
						}
					}
				}catch (Exception e) {	
					exceptionLogger.error("LayoutTag.doEndTag",e);
					pageContext.setAttribute(Constant.EXCEPTION, e, PageContext.REQUEST_SCOPE);
					pageContext.include(errorPage);
				}
				curIdx = token.start + token.name.length();
			}
			pageContext.getOut().write(templateString.substring(curIdx));
		}catch(Exception e1){
			exceptionLogger.error("LayoutTag.doEndTag",e1);
			throw new JspException(e1.fillInStackTrace());
		}
		return EVAL_PAGE;
	}

	private Page getTokenPage(String name) throws IOException, SAXException {
		LayoutConfig layoutConfig = LayoutConfig.getConfig(pageContext.getServletContext());
		Page page = layoutConfig.getPage(name);
		return page;
	}
	private String getDefaultTokenContent(String token) {

		LayoutConfig layoutConfig;
		String tokenContent = null;
		try {
			layoutConfig = LayoutConfig.getConfig(pageContext.getServletContext());
			tokenContent = getPageContentString(layoutConfig.getPage(token).getUri());
		} catch (IOException e) {
			exceptionLogger.error("LayoutTag.getDefaultTokenContent",e);
		} catch (SAXException e) {
			exceptionLogger.error("LayoutTag.getDefaultTokenContent",e);
		}
		return tokenContent;
	}
	
	protected List<Token> listToken(String templateString) {
		String TOKEN_PATTERN = "\\$\\w*\\$";
		Pattern pattern = Pattern.compile(TOKEN_PATTERN);
		Matcher matcher = pattern.matcher(templateString);
		List<Token> tokenList = new ArrayList<Token>();
		while(matcher.find()){
			Token token = new Token();
			token.setStart(matcher.start());
			token.setEnd(matcher.end());
			token.setName(matcher.group());
			tokenList.add(token);
		}
		return tokenList;
	}
	
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	public String getErrorPage() {
		return errorPage;
	}
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	
	public String getTemplateContent() {
		return templateContent;
	}
	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}
	
	public void release()
	{
		super.release();
	}
	public void setTokenContent(String name, Object content) {

		this.tokenContentMap.put(name,content);
	}
	
	public Object getTokenContent(String name){
		return this.tokenContentMap.get(name);
	}
}
