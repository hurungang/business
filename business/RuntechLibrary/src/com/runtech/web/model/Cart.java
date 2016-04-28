package com.runtech.web.model;

import java.io.IOException;
import java.util.List;

import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.runtime.RuntechRuntimeException;

public interface Cart {

	public List<CartItem> getItems() throws ModelException;
	public void add(CartItem item) throws ModelException;
	public CartItem get(Object cartItemId);
	public void calcPrice() throws ModelException;
	public void remove(Integer integer) throws ModelException;
	public void setStatus(String status);
	public void save(RuntechContext context) throws ModelException, RuntimeException, IOException, RuntechRuntimeException;
	public void setUser(User user);
	public boolean isEmpty();
	public Order saveOrder(RuntechContext context, ModelHome modelHome) throws ModelException;
	public Order saveQuickOrder(RuntechContext context, ModelHome modelHome) throws ModelException;
}
