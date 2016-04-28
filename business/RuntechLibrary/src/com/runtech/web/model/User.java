package com.runtech.web.model;

import java.util.List;

import com.runtech.web.runtime.ModelException;

public interface User {

	public void setName(String userName);

	public void setPassword(String userPassword);

	public void setMenu(Menu menu);
	
	public Menu getMenu();
	
	public Role getRole() throws ModelException;

	public Integer getId();
	
	public void init() throws ModelException;

	public List getLoginLogs() throws ModelException;
	
	public Cart getCart();
	
	public void clearCart();
}
