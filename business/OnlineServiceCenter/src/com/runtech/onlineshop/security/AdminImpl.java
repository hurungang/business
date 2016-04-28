package com.runtech.onlineshop.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Order;

import com.runtech.onlineshop.form.AdminForm;
import com.runtech.onlineshop.form.AdminModuleForm;
import com.runtech.onlineshop.form.WorkFlowForm;
import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.AdminLoginLog;
import com.runtech.onlineshop.model.AdminRole;
import com.runtech.onlineshop.model.WorkFlow;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.model.Cart;
import com.runtech.web.model.Menu;
import com.runtech.web.model.Role;
import com.runtech.web.model.User;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class AdminImpl extends AdminForm implements User{

	private Menu menu;
	
	public AdminImpl(Object admin) throws ModelException {
		super(admin);
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menuValue) {
		this.menu = menuValue;
	}

	public Role getRole() throws ModelException {
		return new RoleImpl(this.getAdminRole());
	}

	public void init() throws ModelException {
		// TODO Auto-generated method stub
		ModelHome adminHome = new ModelHome();
		AdminModuleForm moduleExample = new AdminModuleForm();
		moduleExample.setCode(Constant.CODE_MODULE_ROOT);
		Menu menu = new MenuImpl(adminHome.findByKeyExample(moduleExample.getModel()));
		menu.setRole(this.getRole());
		menu.init();
		this.setMenu(menu);
	}
	
	public List getLoginLogs() throws ModelException{
		AdminLoginLog loginLog = new AdminLoginLog();
		loginLog.setAdmin((Admin) this.getModel());
		ModelHome modelHome = new ModelHome();
		return modelHome.list(loginLog, Order.desc("loginTime"), 0, 10);
	}
	
	public List getWorkFlows() throws ModelException{
		ModelHome modelHome =new ModelHome();
		Role tempRole = this.getRole();
		List<WorkFlowForm> resultList = new ArrayList<WorkFlowForm>();
		if(tempRole!=null){
			AdminRole myRole = (AdminRole) modelHome.findById(new AdminRole(),tempRole.getId());
			Set<WorkFlow> workFlows = myRole.getWorkFlows();
			for (WorkFlow workFlow : workFlows) {
				WorkFlowForm workFlowForm = new WorkFlowForm(workFlow);
				resultList.add(workFlowForm);
			}
		}
		return resultList;
	}

	public Cart getCart() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clearCart() {
		// TODO Auto-generated method stub
		
	}
}
