package com.runtech.web.model;

import java.util.List;

import com.runtech.web.runtime.ModelException;

public interface Menu extends Comparable<Menu> {
	public String getName();
	public Integer getId();
	public String getCode();
	public String getPage();
	public String getPageModelType();

	public List<Menu> getSubMenus() throws ModelException;
	public Menu getParentMenu() throws ModelException;
	public Menu getRootMenu() throws ModelException;
	public Menu setActiveMenu(String menuCode) throws ModelException;
	public Menu getActiveMenu() throws ModelException;
	public Menu getMenuByCode(String menuCode) throws ModelException;
	
	public void init() throws ModelException;
	public boolean isActive();
	public boolean isVisible();
	public void setActive(boolean active);
	
	public void setRole(Role role);
	public void setAuthorization(Authorization authorization);
	public Authorization getAuthorization();
	public Integer getPriority();
}
