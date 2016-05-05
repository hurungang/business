package com.runtech.onlineshop.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.runtech.onlineshop.form.AdminModuleForm;
import com.runtech.onlineshop.model.AdminModule;
import com.runtech.web.model.Authorization;
import com.runtech.web.model.Menu;
import com.runtech.web.model.Role;
import com.runtech.web.runtime.ModelException;

public class MenuImpl extends AdminModuleForm implements Menu{
	public Menu parentMenu;
	public Integer parentAdminModuleId;
	public boolean active;
	public String activeMenuCode;
	public Role role;
	public Authorization authorization = new AuthorizationImpl();
	private ArrayList<Menu> subMenus;
	private Menu rootMenu;
	private Menu activeMenu;

	public MenuImpl() {
	}

	public MenuImpl(Object adminModule) throws ModelException {
		super(adminModule);
	}

	public void init() throws ModelException {
		if(this.role!=null){
			this.setRole(role);
			this.setAuthorization(this.role.getAuthorization(this));
		}
		this.subMenus = new ArrayList<Menu>();
		Set<AdminModule> adminModules = this.getAdminModules();
		for (AdminModule adminModule : adminModules) {
			MenuImpl menuItem = new MenuImpl(adminModule);
			menuItem.setRole(this.role);
			menuItem.setParentMenu(this);
			menuItem.init();
			this.subMenus.add(menuItem);
		}
		Collections.sort(this.subMenus);
	}

	public int compareTo(Menu o) {
		Integer priority = this.getPriority();
		Integer priority2 = o.getPriority();
		if(priority==null){
			if(priority2==null){
				return 0;
			}else{
				return -1;
			}
		}else if(priority2==null){
			return 1;
		}
		return priority.compareTo(priority2);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPageModelType() {
		return this.getModelType();
	}
	
	public Menu setActiveMenu(String menuCode) throws ModelException {
		activeMenu = null;
		if(menuCode!=null){
			if(this.getCode()!=null&&this.getCode().equals(menuCode)){
				this.setActive(true);
				activeMenu = this;
			}else{
				this.setActive(false);
			}
		}
		if(this.subMenus!=null){
			for (Menu menuItem : subMenus) {
				Menu tempActiveMenu = menuItem.setActiveMenu(menuCode);
				if(tempActiveMenu!=null){
					activeMenu = tempActiveMenu;
					menuItem.setActive(true);
				}else{
					menuItem.setActive(false);
				}
			}
		}
		return activeMenu;
	}

	public Menu getActiveMenu() {
		return activeMenu;
	}

	public void setRole(Role roleValue) {
		this.role = roleValue;
	}

	public Authorization getAuthorization() {
		return this.authorization;
	}

	public void setAuthorization(Authorization authorizationValue) {
		this.authorization = authorizationValue;
	}

	public boolean isVisible() {
		if(this.authorization!=null){
			if(this.getIsHidden()){
				return false;
			}else{
				return this.authorization.isReadable()||this.authorization.isWritable()||this.authorization.isExecutable()||this.authorization.isSupervisable();
			}
		}else{
			return false;
		}
	}

	public Menu getRootMenu() throws ModelException {
		if(this.rootMenu == null){
			Menu menu = this;
			Menu parentMenu = this.getParentMenu();
			while(parentMenu!=null){
				menu = parentMenu;
				parentMenu = menu.getParentMenu();
			}
			this.rootMenu = menu;
		}
		return this.rootMenu;
	}

	public Menu getParentMenu() throws ModelException {
		return this.parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public List<Menu> getSubMenus(){
		return this.subMenus;
	}

	public Menu getMenuByCode(String menuCode) throws ModelException {		
		if(menuCode!=null){
			if(this.getCode()!=null&&this.getCode().equals(menuCode)){
				return this;
			}else{
				List<Menu> subMenus = this.getSubMenus();
				for (Menu menuItem : subMenus) {
					Menu tempMenu = menuItem.getMenuByCode(menuCode);
					if(tempMenu!=null){
						return tempMenu;
					}
				}
			}
		}
		return null;
	}
	
	
}