package com.runtech.web.tag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

public class CacheTag extends BodyTagSupport {
	private static Logger		viewLogger=Logger.getLogger("ViewLogger");
	public final static String DEFAULT_CACHE_PATH = "/WEB-INF/cache";
	
	private String uuid;	
	private String scope = "application";	
	private String name;
	private String path = DEFAULT_CACHE_PATH;

	protected boolean reload = true;
	protected String timeKey=null;
	
	public int doStartTag() throws JspException 
	{
		timeKey = (name==null)?uuid:name;
		int iScope = getScope(scope);
		Long newestUpdated = (Long)pageContext.getAttribute(timeKey, iScope);
		if(newestUpdated==null)
		{
			newestUpdated=Calendar.getInstance().getTimeInMillis();
			pageContext.setAttribute(timeKey,newestUpdated,iScope);
		}
		reload = newestUpdated.longValue() > getLastUpdated();
		try
		{
			if(reload)
			{
				return EVAL_BODY_BUFFERED;
			}
			else
			{
				String content = getContentFromCache();
				if(content==null)
					content = executePage();
				
				pageContext.getOut().print(content);
			}
		}
		catch(IOException e)
		{
			viewLogger.error(this.getClass().getName()+".doStartTag()->"+e.toString());
			return EVAL_BODY_INCLUDE;	
		}	
		return SKIP_BODY;		
	}
	
	public int doEndTag() throws JspException 
	{
		try
		{
			if(reload)
			{
				String content = executePage();
				pageContext.getOut().print(content);
				updateCache(content);	
			}
		}
		catch(IOException e)
		{
			viewLogger.error(this.getClass().getName()+".doStartTag()->"+e.toString());
		}	
		return EVAL_PAGE;
	}
	
	private int getScope(String _scope){
		if("session".equalsIgnoreCase(_scope))
			return PageContext.SESSION_SCOPE;
		return PageContext.APPLICATION_SCOPE;
	}
	
	protected String executePage() throws IOException{
		StringBuffer content = new StringBuffer(1024);
		Reader reader = getBodyContent().getReader();
		char[] buf = new char[1024];
		do{
			int  rc = reader.read(buf);
			if(rc>0)
				content.append(buf,0,rc);
			if(rc<1024)
				break;
		}while(true);
		return content.toString();
	}
	
	protected long updateCache(String content) throws IOException{
		File f = new File(getCachePath());
		if(!f.getParentFile().exists())
			f.getParentFile().mkdirs();
		OutputStream out = null;
		try{
			out = new FileOutputStream(f);
			out.write(content.getBytes());
		}finally{
			if(out!=null)
				out.close();
		}
		return f.lastModified();
	}
	
	protected String getContentFromCache() throws IOException{
		String content = null;
		File f = new File(getCachePath());
		if(f.exists()){
			BufferedReader br = new BufferedReader(new FileReader(f));
			try{
				StringBuffer fc = new StringBuffer((int)f.length());
				String line;
				do{
					line = br.readLine();
					if(line==null)
						break;
					fc.append(line);
					fc.append("\r\n");
				}while(true);
				content = fc.toString();
			}finally{
				br.close();
			}
		}
		return content;
	}
	
	/**
	 * �õ���һ�λ���ĸ���ʱ��
	 * @return
	 */
	protected long getLastUpdated(){
		File f = new File(getCachePath());
		long updated = f.exists()?f.lastModified():-1L;
		f = null;
		return updated;
	}
	
	protected String getCachePath(){
		StringBuffer cachePath = new StringBuffer();
		cachePath.append(path);
		if(!path.endsWith("/"))
			cachePath.append("/");
		cachePath.append(uuid);
		cachePath.append('_');
		cachePath.append(scope);
		cachePath.append(".html");
		return pageContext.getServletContext().getRealPath(cachePath.toString());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public void release() {
		uuid = null;
		name = null;
		super.release();
	}
}
