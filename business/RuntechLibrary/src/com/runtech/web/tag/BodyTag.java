package com.runtech.web.tag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

public class BodyTag extends BodyTagSupport {

	protected final Logger exceptionLogger=Logger.getLogger(BodyTag.class);
	private static final int BUFFER_SIZE = 1024;
	protected String getBodyContentString() throws IOException {
		
		Reader reader = getBodyContent().getReader();
		char[] buf = new char[BUFFER_SIZE];
		StringBuffer contentBuffer = new StringBuffer(BUFFER_SIZE);
		do{
			int  rc = reader.read(buf);
			if(rc>0)
				contentBuffer.append(buf,0,rc);
			if(rc<BUFFER_SIZE)
				break;
		}while(true);
		String content = contentBuffer.toString();
		return content;
	}

	protected String getPageContentString(String pagePath) throws IOException {
		String realPath = pageContext.getServletContext().getRealPath(pagePath);
	
		File f = new File(realPath);
		String content = null;
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

}