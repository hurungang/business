package com.runtech.web.dao;


import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.junit.Before;

import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.AdminModule;
import com.runtech.onlineshop.model.AdminRole;
import com.runtech.onlineshop.model.AdminRoleModule;
import com.runtech.onlineshop.model.Area;
import com.runtech.onlineshop.model.Commodity;
import com.runtech.onlineshop.model.CommodityCategory;
import com.runtech.onlineshop.model.CommodityDetail;
import com.runtech.onlineshop.model.CommodityProvider;
import com.runtech.onlineshop.model.Content;
import com.runtech.onlineshop.model.SystemCode;

public class HibernateTest {

	@Before
	public void setUp() throws Exception {
	    Configuration configuration = 
	        new Configuration();
	    configuration.setProperty(
	        Environment.DRIVER, 
	        "com.mysql.jdbc.Driver");
	    configuration.setProperty(
	        Environment.URL, 
	        "jdbc:mysql://localhost/shop");
	    configuration.setProperty(
	        Environment.USER, "root");
	    configuration.setProperty(
	        Environment.DIALECT, 
	        "org.hibernate.dialect.MySQL5Dialect");
	    configuration.setProperty(
	        Environment.SHOW_SQL, "true");
	    configuration.setProperty(
	        Environment.HBM2DDL_AUTO, "create-drop");
	    configuration.addClass(Admin.class);
	    configuration.addClass(AdminRole.class);
	    configuration.addClass(AdminModule.class);
	    configuration.addClass(AdminRoleModule.class);
	    configuration.addClass(Area.class);
	    configuration.addClass(Commodity.class);
	    configuration.addClass(CommodityCategory.class);
	    configuration.addClass(CommodityDetail.class);
	    configuration.addClass(CommodityProvider.class);
	    configuration.addClass(Content.class);
	    configuration.addClass(SystemCode.class);

	}

}
