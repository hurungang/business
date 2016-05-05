package com.runtech.web.form;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.criterion.Order;

import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.runtime.ModelException;

public interface ModelForm{
	public void copyFrom(Object model) throws ModelException;
	public void action(RuntechContext context) throws ModelException;
	public boolean prepare(RuntechContext context) throws ModelException;
	public void finish(RuntechContext context) throws ModelException;
	public Object getModel() throws ModelException;
	public void setFormId(String idValue);
	public String getFormId();
	public Order getDefaultOrder();
	public Serializable[] getIds();
	public Serializable getId();
	public void clearId();
}
