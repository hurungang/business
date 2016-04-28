package com.runtech.onlineshop.security;

import java.util.Set;

import com.runtech.onlineshop.form.AdminRoleForm;
import com.runtech.onlineshop.model.AdminRoleModule;
import com.runtech.web.model.Authorization;
import com.runtech.web.model.Menu;
import com.runtech.web.model.Role;
import com.runtech.web.runtime.ModelException;

public class RoleImpl extends AdminRoleForm implements Role{
	
	public RoleImpl(Object adminRole) throws ModelException {
		super(adminRole);
	}
	
	public Authorization getAuthorization(Menu menuItem) {
		Set<AdminRoleModule> adminRoleModules = this.getAdminRoleModules();
		if(adminRoleModules!=null){
			for (AdminRoleModule adminRoleModule : adminRoleModules) {
				if(adminRoleModule.getAdminModule().getId().equals(menuItem.getId())){
					Authorization authorization = new AuthorizationImpl(adminRoleModule.getAccessCode());
					return authorization;
				}
			}
		}
		return new AuthorizationImpl();
	}

}
