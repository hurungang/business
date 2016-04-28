package com.runtech.web.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.runtech.junit.http.VirtualBodyContent;
import com.runtech.junit.http.VirtualJspWriter;
import com.runtech.junit.http.VirtualPageContext;
import com.runtech.web.model.Authorization;
import com.runtech.web.model.Menu;
import com.runtech.web.model.Role;
import com.runtech.web.runtime.ModelException;

public class MenuTagTest extends TestCase{

	VirtualPageContext pageContext = new VirtualPageContext();
	private MenuTag menuTag;
	
	
	@Override
	protected void setUp() throws Exception {
		menuTag = new MenuTag();
		menuTag.setPageContext(this.pageContext);

	}

	@Test
	public void testEmpty() {
		Assert.assertEquals("", this.pageContext.getOut().getContent());
	}

	@Test
	public void testOneMenu() {
		Menu menu = new TestMenu(1, "test", "/page", "pageType", null, null);
		menuTag.setRoot(menu);
		try {
			VirtualJspWriter jspWriter = new VirtualJspWriter();
			jspWriter.write("<div/>");
			VirtualBodyContent bodyContent = new VirtualBodyContent(jspWriter);
			menuTag.setBodyContent(bodyContent);
			menuTag.doEndTag();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail();
		}
		Assert.assertEquals("<div/>", this.pageContext.getOut().getContent());
	}
	
	@Test
	public void testTwoMenu() {
		TestMenu menu = new TestMenu(1, "test", "/page", "pageType", null, null);
		TestMenu menu2 = new TestMenu(2, "test2", "/page2", "pageType2", null, null);
		List<Menu> subMenus = new ArrayList<Menu>();
		subMenus.add(menu2);
		menu.setSubMenus(subMenus);
		menu2.setParentMenu(menu);
		menuTag.setRoot(menu);
		try {
			VirtualJspWriter jspWriter = new VirtualJspWriter();
			jspWriter.write("<div/>");
			VirtualBodyContent bodyContent = new VirtualBodyContent(jspWriter);
			menuTag.setBodyContent(bodyContent);
			menuTag.doEndTag();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail();
		}
		Assert.assertEquals("<div/><div/>", this.pageContext.getOut().getContent());
	}
	
	
	class TestMenu implements Menu{

		private Menu rootMenu;
		private Menu parentMenu;
		private List<Menu> subMenus;
		private String pageModelType;
		private String page;
		private String name;
		private Integer id;

		public TestMenu(Integer id, String name, String page,
				String pageModelType, Menu parentMenu, Menu rootMenu) {
			super();
			this.id = id;
			this.name = name;
			this.page = page;
			this.pageModelType = pageModelType;
			this.parentMenu = parentMenu;
			this.rootMenu = rootMenu;
		}

		public Integer getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}

		public String getPage() {
			return this.page;
		}

		public String getPageModelType() {
			return this.pageModelType;
		}

		public Menu getParentMenu() throws ModelException {
			return this.parentMenu;
		}

		public Menu getRootMenu() throws ModelException {
			return rootMenu;
		}

		public List<Menu> getSubMenus() throws ModelException {
			return this.subMenus;
		}

		public void setSubMenus(List<Menu> subMenus) {
			this.subMenus = subMenus;
		}

		public void setRootMenu(Menu rootMenu) {
			this.rootMenu = rootMenu;
		}

		public void setParentMenu(Menu parentMenu) {
			this.parentMenu = parentMenu;
		}

		public void setPageModelType(String pageModelType) {
			this.pageModelType = pageModelType;
		}

		public void setPage(String page) {
			this.page = page;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}

		public String getCode() {
			// TODO Auto-generated method stub
			return null;
		}

		public int compareTo(Menu o) {
			// TODO Auto-generated method stub
			return 0;
		}

		public Authorization getAuthorization() {
			// TODO Auto-generated method stub
			return null;
		}

		public Menu getMenuByCode(String menuCode) throws ModelException {
			// TODO Auto-generated method stub
			return null;
		}

		public void init() throws ModelException {
			// TODO Auto-generated method stub
			
		}

		public boolean isActive() {
			// TODO Auto-generated method stub
			return false;
		}

		public void setActive(boolean active) {
			// TODO Auto-generated method stub
			
		}

		public Menu setActiveMenu(String menuCode) throws ModelException {
			// TODO Auto-generated method stub
			return null;
		}

		public void setAuthorization(Authorization authorization) {
			// TODO Auto-generated method stub
			
		}

		public void setRole(Role role) {
			// TODO Auto-generated method stub
			
		}

		public Integer getPriority() {
			// TODO Auto-generated method stub
			return null;
		}

		public Menu getActiveMenu() throws ModelException {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean isVisible() {
			// TODO Auto-generated method stub
			return false;
		}

		
	}
}
