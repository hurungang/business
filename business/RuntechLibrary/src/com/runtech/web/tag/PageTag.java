package com.runtech.web.tag;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;


public class PageTag extends TagSupport {

	
	private Pager pager;
	private String action;
	private String command;

	@Override
	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
			if(action==null){
				this.action="";
			}
			Map<String,String[]> parameterMap = request.getParameterMap();
			StringBuffer queryStringBuffer=new StringBuffer();
			for (String name : parameterMap.keySet()) {
				if(!name.equalsIgnoreCase(command)&&!name.startsWith("$")){
					Object value = parameterMap.get(name);
					if(value instanceof String[]){
						for(String item: (String[])value){
							queryStringBuffer.append(name);
							queryStringBuffer.append("=");
							item = URLEncoder.encode(item,"utf-8");
							queryStringBuffer.append(item);
							queryStringBuffer.append("&");	
						}
					}else{
						queryStringBuffer.append(name);
						queryStringBuffer.append("=");
						String item = (String) value;
						item = URLEncoder.encode(item,"utf-8");
						queryStringBuffer.append(item);
						queryStringBuffer.append("&");	
					}
				}
			}
			String queryString = queryStringBuffer.toString();
			JspWriter out = this.pageContext.getOut();
			if(this.pager!=null){
				out.print("<div class='pager'>");
				int startCount = 1;
				if(this.pager.getPageIndex()>5){
					startCount = this.pager.getPageIndex() - 5;
				}
				int endCount = startCount +9;
				int totalPages = this.pager.getTotalPages();
				if(totalPages<=endCount){
					endCount = totalPages - 1;
				}
				if(this.pager.hasPreviousPage()){
					out.print("<span class='text'><a href='"+action+"?"+queryString+command+"="+(this.pager.getPageIndex()-1)+"'>prev<</a></span>");
				}
				for(int i = startCount;i <=endCount; i++){
					if(i==this.pager.getPageIndex()){
						out.print("<span class='number current'>"+i+"</span>");
					}else{
						out.print("<span class='number'><a href='"+action+"?"+queryString+command+"="+i+"'>"+i+"</a></span>");
					}
				}
				out.print("...");
				if(this.pager.getPageIndex()!=totalPages){
					out.print("<span class='number'><a href='"+action+"?"+queryString+command+"="+totalPages+"'>"+totalPages+"</a></span>");
				}else{
					out.print("<span class='number'>"+totalPages+"</span>");
				}
				if(this.pager.hasNextPage()){
					out.print("<span class='text'><a href='"+action+"?"+queryString+command+"="+(this.pager.getPageIndex()+1)+"'>>next</a></span>");
				}
			
				out.print("</div>");
			}
			return SKIP_BODY;
		} catch (IOException e) {
			throw new JspException(e.fillInStackTrace());
		}
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	
}
