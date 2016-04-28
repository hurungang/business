package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.AdminRole;
import com.runtech.onlineshop.model.User;
import com.runtech.web.action.PageAction;
import com.runtech.web.action.StrutsAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class AdminForm extends Admin implements ModelForm{

	private Integer roleId;
	private Integer ids[];
	private String actionType;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
	public AdminForm() {
		super();
	}
	
	public AdminForm(Object admin) throws ModelException {
		this.copyFrom(admin);
	}
	
	public void copyFrom(Object model) throws ModelException {
		try {
			Admin realModel = (Admin) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Object getModel() throws ModelException {
		try {
			Admin admin = new Admin();
			PropertyUtils.copyProperties(admin,this);
			return admin;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public void action(RuntechContext context) throws ModelException {
		StrutsAction action = context.getAction();
		if (this.getActionType().equals(Constant.ACTION_APPROVE))
		{
			ModelHome modelHome = new ModelHome();
			Admin admin = (Admin)modelHome.findById(new Admin(), this.getId());
			admin.setStatus(Constant.STATUS_APPROVED);
			try {
				modelHome.beginTransaction();
				modelHome.merge(admin);
				modelHome.commit();
			} catch (Exception e) {
				modelHome.rollback();
			}
		}else if (Constant.ACTION_CHANGE_PASSWORD.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			Admin admin = (Admin)modelHome.findById(new Admin(), this.getId());
			if(this.oldPassword!=null&this.oldPassword.equals(admin.getPassword())){

				if(this.newPassword!=null&this.newPassword.equals(this.confirmPassword)){
	
					try {
						admin.setPassword(this.newPassword);
						modelHome.beginTransaction();
						modelHome.merge(admin);
						modelHome.commit();
					} catch (Exception e) {
						modelHome.rollback();
						throw new ModelException(e);
					}	
				}else{
					throw new ModelException(action.getText(Constant.CODE_ERROR_NEW_PASSWORD));
				}	
			}else{
				throw new ModelException(action.getText(Constant.CODE_ERROR_PASSWORD));
			}	
		}else if (Constant.ACTION_UPDATE_ACCOUNT.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			Admin admin = (Admin)modelHome.findById(new Admin(), this.getId());
			try {
				admin.setFullName(this.getFullName());
				admin.setEmail(this.getEmail());
				admin.setMobile(this.getMobile());
				admin.setAddress(this.getAddress());
				admin.setPhone(this.getPhone());
				modelHome.beginTransaction();
				modelHome.merge(admin);
				modelHome.commit();
			} catch (Exception e) {
				modelHome.rollback();
				throw new ModelException(e);
			}	
		}
	}

	private String getActionType() {
		// TODO Auto-generated method stub
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean prepare(RuntechContext context) {
		ModelHome modelHome = new ModelHome();
		this.setUpdater(context.getUser().getId());
		this.setUpdateTime(new Date());
		this.setStatus(Constant.STATUS_ENABLED);
		this.setAdminRole((AdminRole) modelHome.findById(new AdminRole(), this.roleId));
		return true;
	}

	public void setRoleId(Integer roleIdValue){
		this.roleId = roleIdValue;
	}

	public Integer getRoleId() {
		return roleId;
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
		if(super.getId()!=null){
			return super.getId().toString();
		}else{
			return null;
		}
	}
	
	public Integer[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}
	
	public void clearId() {
		this.setId(null);
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}

	public static List getAllAdmins(){
		Admin admin = new Admin();
		ModelHome modelHome = new ModelHome();
		List list = modelHome.findByExample(admin);
		return list;	
	}
}
