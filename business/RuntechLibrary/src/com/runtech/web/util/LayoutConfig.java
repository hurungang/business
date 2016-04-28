
package com.runtech.web.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.ExtendedBaseRules;
import org.xml.sax.SAXException;

public class LayoutConfig {

	private List<Page> pages;
	private boolean freezed = false;
	private static LayoutConfig config;
	private static File configFile;
	private static long lastModified;

	private LayoutConfig(){
		pages = new ArrayList<Page>();
	}
	
	/**
	 * ��������layout.xml
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	public static LayoutConfig getConfig(ServletContext context) throws IOException, SAXException{
		if(config!=null && configFile.lastModified()==lastModified){
			return config;
		}
		config = new LayoutConfig();
        Digester dig = new Digester();
        dig.push(config);
        dig.setValidating(false);
        dig.setRules(new ExtendedBaseRules());
        dig.addSetProperties("layout");

        dig.addObjectCreate("layout/pages/page", Page.class);
        dig.addSetProperties("layout/pages/page");
        dig.addBeanPropertySetter("layout/pages/page" + "/?");
        dig.addSetNext("layout/pages/page", "addPage");
        
        InputStream in = null;
        if(context != null){
        	in = context.getResourceAsStream("/WEB-INF/layout.xml");
        	configFile = new File(context.getRealPath("/WEB-INF/layout.xml"));
        }
        if(in == null){
        	in = LayoutConfig.class.getResourceAsStream("layout.xml");
        	configFile = new File(LayoutConfig.class.getResource("layout.xml").getPath());
        }
        try{
        	lastModified = configFile.lastModified();
        	dig.parse(in);
        	config.freezed = true;
        }finally{
        	if(in!=null)
        		in.close();
        }
        return config;
	}
	
	public Page getPage(int index) {
		return (Page)pages.get(index);
	}
	
	public Page getPage(String name) {
		for (Page page : pages) {
			if(page.name.equals(name)){
				return page;
			}
		}
		return null;
	}
	
	public int pageSize() {
		return pages.size();
	}
	
	public void addPage(Page page) throws IllegalAccessException{
		if(!freezed)
			pages.add(page);
		else
			throw new IllegalAccessException("LayoutConfig is fozened.");
	}
	
	public static void main(String[] args) throws Exception{
		LayoutConfig config = LayoutConfig.getConfig(null);
		int pc = config.pageSize();
		for(int i=0;i<pc;i++){
			Page p = config.getPage(i);
			System.out.println("Name:"+p.getName()+",uri:"+p.getUri()+",param:"+p.getParam());
		}
		config = LayoutConfig.getConfig(null);
		pc = config.pageSize();
		for(int i=0;i<pc;i++){
			Page p = config.getPage(i);
			System.out.println("Name:"+p.getName()+",uri:"+p.getUri()+",param:"+p.getParam());
		}
	}
	
}
