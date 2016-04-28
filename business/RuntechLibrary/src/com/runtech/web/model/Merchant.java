package com.runtech.web.model;

import com.runtech.web.runtime.ModelException;

public interface Merchant {

	public void setLoginName(String userName);

	public void setPassword(String userPassword);

	public void setMenu(Menu menu);
	
	public Menu getMenu();
	
	public Role getRole() throws ModelException;
	
	public Integer getId();
}
