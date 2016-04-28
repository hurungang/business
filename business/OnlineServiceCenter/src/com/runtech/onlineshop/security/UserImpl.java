package com.runtech.onlineshop.security;

import java.util.List;

import org.hibernate.criterion.Order;

import com.runtech.onlineshop.form.CommodityOrderForm;
import com.runtech.onlineshop.form.UserForm;
import com.runtech.onlineshop.model.AdminModule;
import com.runtech.onlineshop.model.AdminRole;
import com.runtech.onlineshop.model.UserLoginLog;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.model.Menu;
import com.runtech.web.model.Role;
import com.runtech.web.model.User;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class UserImpl extends UserForm implements User{


	private static final String SESSION_CART = "cart";
	private Menu menu;
	private Role role;
	private CommodityOrderForm cart;
	public UserImpl(Object admin) throws ModelException {
		super(admin);
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menuValue) {
		this.menu = menuValue;
	}

	public Role getRole() throws ModelException {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void init() throws ModelException{
		ModelHome userHome = new ModelHome();
		AdminRole roleExample = new AdminRole();
		roleExample.setName(Constant.CODE_END_USER_ROLE);
		this.role = (Role) new RoleImpl(userHome.findByKeyExample(roleExample));
		AdminModule moduleExample = new AdminModule();
		moduleExample.setCode(Constant.CODE_MODULE_ROOT);
		Menu menu = new MenuImpl(userHome.findByKeyExample(moduleExample));
		menu.setRole(this.role);
		menu.init();
		this.setMenu(menu);
	}
	
	public List getLoginLogs() throws ModelException{
		UserLoginLog loginLog = new UserLoginLog();
		loginLog.setUser((com.runtech.onlineshop.model.User) this.getModel());
		ModelHome modelHome = new ModelHome();
		return modelHome.list(loginLog, Order.desc("loginTime"), 0, 10);
	}
	
	public CommodityOrderForm getCart() {
		if(this.cart==null){
			this.setCart(new CommodityOrderForm());
		}
		return this.cart;
	}

	public void setCart(CommodityOrderForm cart) {
		this.cart = cart;
	}
	
	public void clearCart() {
		this.cart = null;
	}


}
