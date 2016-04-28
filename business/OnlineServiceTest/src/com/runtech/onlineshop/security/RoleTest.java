package com.runtech.onlineshop.security;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.runtech.onlineshop.form.AdminForm;
import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.AdminModule;
import com.runtech.onlineshop.model.AdminRole;
import com.runtech.onlineshop.model.AdminRoleModule;
import com.runtech.web.dao.HibernateTest;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.model.Authorization;
import com.runtech.web.model.Menu;
import com.runtech.web.model.User;
import com.runtech.web.util.Constant;

public class RoleTest extends HibernateTest{
	
	ModelHome modelHome;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		modelHome = new ModelHome();
	}
	
	@Test
	public void testRoleModuleLoad() throws Exception {
		AdminRole role = new AdminRole();
		role.setName("AdminRole");
		role = (AdminRole) modelHome.findByKeyExample(role);
		Assert.assertEquals(new Integer(1), role.getId());
		Set<AdminRoleModule> adminRoleModules = role.getAdminRoleModules();
		Assert.assertEquals(true, adminRoleModules.size()>0);
	}
	
	@Test
	public void testRole() throws Exception {
		AdminForm adminExample = new AdminForm();
		adminExample.setName("admin");
		adminExample.setPassword("admin");
		ModelHome adminHome = new ModelHome();
		Admin admin = (Admin) adminHome.findByKeyExample(adminExample.getModel());
		AdminModule moduleExample = new AdminModule();
		moduleExample.setCode(Constant.CODE_MODULE_ROOT);
		Menu menu = new MenuImpl((AdminModule) adminHome.findByKeyExample(moduleExample));
		if(admin!=null){
			User user = new UserImpl(admin);
			menu.setRole(user.getRole());
			menu.init();
			user.setMenu(menu);
		}
		Menu root = menu.getMenuByCode("root");
		Authorization authorization = root.getAuthorization();
		Assert.assertEquals(true, authorization.isExecutable());
	}
}
