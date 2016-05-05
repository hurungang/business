package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.AdminRole;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class AdminRoleForm extends AdminRole implements ModelForm{

	private Integer[] ids;
	
	public AdminRoleForm() {
		super();
	}

	public AdminRoleForm(Object adminRole) throws ModelException {
		this.copyFrom(adminRole);
	}

	public void copyFrom(Object model) throws ModelException {
		try {
			AdminRole realModel = (AdminRole) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Object getModel() throws ModelException {
		try {
			AdminRole admin = new AdminRole();
			PropertyUtils.copyProperties(admin,this);
			return admin;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		this.setUpdater(context.getUser().getId());
		this.setUpdateTime(new Date());
		return true;
	}

	public List<Object> getAllRoles(){
		AdminRole adminRole = new AdminRole();
		ModelHome modelHome = new ModelHome();
		List<Object> list = modelHome.findByExample(adminRole);
		return list;	
	}

	public Order getDefaultOrder() {
		return Order.desc("id");
	}

	public void setFormId(String idValue) {
		String splitValues[];
		if(idValue!=null){
			splitValues = idValue.split(Constant.SYMBOL_COMMA_SPLIT);
			if(splitValues.length == 1)
			{
				super.setId(Integer.parseInt(idValue));
			}
			else
			{
				ids = new Integer[splitValues.length];
				for(int i = 0; i < splitValues.length; i++)
				{
					ids[i] = Integer.parseInt(splitValues[i].trim());
				}
			}
		}
	}
	
	public String getFormId() {
		return super.getId().toString();
	}
	
	public Integer[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}
		
	public void clearId() {
		this.setId(null);
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}

}
