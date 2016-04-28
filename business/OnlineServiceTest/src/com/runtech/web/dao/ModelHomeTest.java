package com.runtech.web.dao;


import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.AdminModule;
import com.runtech.onlineshop.model.AdminRole;

public class ModelHomeTest extends HibernateTest{
	ModelHome modelHome;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		modelHome = new ModelHome();
	}

	@Test
	public void testList() throws Exception {
		Admin admin = new Admin();
		List<Object> adminList = modelHome.findByExample(admin);
		for (Object model : adminList) {
			System.out.println(model.toString());
		}
		Assert.assertEquals(1, adminList.size());
	}
	
	@Test
	public void testListWithField() throws Exception {
		Admin admin = new Admin();
		AdminRole adminRole = (AdminRole) modelHome.findById(new AdminRole(), new Integer(2));
		admin.setAdminRole(adminRole);
		List<Object> adminList = modelHome.findByExample(admin);
		for (Object model : adminList) {
			System.out.println(model.toString());
		}
		Assert.assertEquals(1, adminList.size());
	}
	
	@Test
	public void testList2() throws Exception {
		Admin admin = new Admin();
		List<Object> adminList = modelHome.list(admin, null, 0, 1);
		for (Object model : adminList) {
			System.out.println(model.toString());
		}
		Assert.assertEquals(1, adminList.size());
	}

	@Test
	public void testListCount() throws Exception {
		AdminModule adminModule = new AdminModule();
		Integer count = modelHome.getCount(adminModule);
		Assert.assertEquals(true, count.intValue()>0);
	}
		
	public void testPersist() throws Exception {
		AdminRole role = new AdminRole();
		role.setName("tester");
//		AdminModule module = new AdminModule();
//		module.setName("testModule");
//		Admin admin = new Admin();
//		admin.setName("test");
//		admin.setPassword("test");
//		admin.setStatus("Enable");
//		admin.setAdminRole(role);
//		Set<Admin> adminSet = new HashSet<Admin>();
//		adminSet.add(admin);
//		role.getAdmins().add(admin);
		modelHome.beginTransaction();
		modelHome.save(role);
		AdminRole adminRole = new AdminRole();
		List<Object> adminRoleList = modelHome.findByExample(adminRole);
		for (Object model : adminRoleList) {
			System.out.println(model.toString());
		}
		modelHome.getSession().flush();
		adminRoleList = modelHome.findByExample(adminRole);
		for (Object model : adminRoleList) {
			System.out.println(model.toString());
		}
		modelHome.commit();
		modelHome.closeSession();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testQuery() throws Exception {
		List list = modelHome.executeQuery("select admin,role from Admin as admin join fetch admin.adminRole as role",0,3);
		for (Object object : list) {
			Assert.assertTrue(object instanceof Admin);
		}
	}
}
