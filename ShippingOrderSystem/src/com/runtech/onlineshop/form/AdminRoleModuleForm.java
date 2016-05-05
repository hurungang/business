package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.AdminModule;
import com.runtech.onlineshop.model.AdminRole;
import com.runtech.onlineshop.model.AdminRoleModule;
import com.runtech.onlineshop.model.AdminRoleModuleId;
import com.runtech.onlineshop.security.AuthorizationImpl;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class AdminRoleModuleForm extends AdminRoleModule implements ModelForm {

	private AuthorizationImpl authorization;
	private AdminRoleModuleId[] ids;
	
	public void copyFrom(Object model) throws ModelException {
		try {
			AdminRoleModule realModel = (AdminRoleModule) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
			if(this.getAccessCode()!=null){
				this.authorization = new AuthorizationImpl(this.getAccessCode());
			}else{
				this.authorization = new AuthorizationImpl();
			}
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return Order.desc("updateTime");
	}

	public Object getModel() throws ModelException {
		try {
			AdminRoleModule model = new AdminRoleModule();
			PropertyUtils.copyProperties(model,this);
			if(this.authorization!=null){
				model.setAccessCode(AuthorizationImpl.generateAccessCode(this.authorization));
			}
			return model;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		ModelHome modelHome = new ModelHome();
		
		if(this.getId()!=null){
			AdminRole role = (AdminRole) modelHome.findById(new AdminRole(), this.getId().getRoleId());
			AdminModule module = (AdminModule) modelHome.findById(new AdminModule(), this.getId().getModuleId());
			this.setAdminRole(role);
			this.setAdminModule(module);
		}
		
		if(this.getAdminModule()!=null&&this.getAdminRole()!=null){
			AdminRoleModuleId tempId=new AdminRoleModuleId(this.getAdminRole().getId(),this.getAdminModule().getId());
			this.setId(tempId);
		}
		this.setUpdater(context.getUser().getId());
		this.setUpdateTime(new Date());
		return true;
	}

	public AuthorizationImpl getAuthorization() {
		return this.authorization;
	}

	public void setAuthorization(AuthorizationImpl authorization) {
		this.authorization = authorization;
	}

	public void setFormId(String idValue) {
		String[] splitValues;
		String[] idArray;
		
		if(idValue!=null)
		{
			splitValues = idValue.split(Constant.SYMBOL_COMMA_SPLIT);
			if(splitValues.length == 1)
			{
				idArray = idValue.split(Constant.SYMBOL_ID_SPLIT);
				if(idArray!=null&&idArray.length==2){
					AdminRoleModuleId id = new AdminRoleModuleId();
					id.setRoleId(Integer.parseInt(idArray[0]));
					id.setModuleId(Integer.parseInt(idArray[1]));
					super.setId(id);
				}
			}
			else
			{
				ids = new AdminRoleModuleId[splitValues.length];
				for(int i = 0; i < splitValues.length; i++)
				{
					idArray = splitValues[i].trim().split(Constant.SYMBOL_ID_SPLIT);
					if(idArray!=null&&idArray.length==2){
						AdminRoleModuleId id = new AdminRoleModuleId();
						id.setRoleId(Integer.parseInt(idArray[0]));
						id.setModuleId(Integer.parseInt(idArray[1]));
						ids[i] = id;
					}
				}
			}
		}
	}

	public String getFormId() {
		AdminRoleModuleId id = super.getId();
		return id.getRoleId()+Constant.SYMBOL_ID_SPLIT+id.getModuleId();
	}
	
	public void clearId() {
		this.setId(null);
	}

	public AdminRoleModuleId[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}

}
