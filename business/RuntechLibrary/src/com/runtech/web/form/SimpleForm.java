package com.runtech.web.form;

import java.io.Serializable;

import org.hibernate.criterion.Order;

import com.runtech.web.action.PageAction;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.runtime.ModelException;

public class SimpleForm implements ModelForm {

	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub

	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFormId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getModel() throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public void prepare(PageAction action) {
		// TODO Auto-generated method stub

	}

	public void setFormId(String idValue) {
		// TODO Auto-generated method stub

	}

	public Serializable[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void clearId() {
		// TODO Auto-generated method stub
		
	}

	public void action(PageAction action) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		return true;
	}

}
