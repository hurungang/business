package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.AdminModule;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class AdminModuleForm extends AdminModule
		implements ModelForm{
	
	private Integer parentSAdminModuleId;
	private Integer ids[];
	
	public AdminModuleForm() {
		super();
	}
	
	public AdminModuleForm(Object adminModule) throws ModelException {
		this.copyFrom(adminModule);
	}

	public void copyFrom(Object model) throws ModelException{
		try {
			AdminModule realModel = (AdminModule) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}
	
	public Object getModel() throws ModelException {
		try {
			AdminModule adminModule = new AdminModule();
			PropertyUtils.copyProperties(adminModule,this);
			return adminModule;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context){
		this.setAdminModule(getSAdminModuleById(this.parentSAdminModuleId));
		this.setUpdater(context.getUser().getId());
		this.setUpdateTime(new Date());
		return true;
	}
	
	public Integer getParentSAdminModuleId() {
		if(this.getAdminModule()!=null){
			this.parentSAdminModuleId = this.getAdminModule().getId();
		}
		return this.parentSAdminModuleId;
	}

	public void setParentSAdminModuleId(Integer parentSAdminModuleId) {
		this.parentSAdminModuleId = parentSAdminModuleId;
	}

	public List<AdminModuleForm> getAllModules() throws ModelException {
		List<AdminModuleForm> list = new ArrayList<AdminModuleForm>();
		AdminModuleForm root = getModuleFormByCode(Constant.CODE_MODULE_ROOT);
		list.add(root);
		list.addAll(getAllModuleList(root, "-"));
		return list;
	}

	private List<AdminModuleForm> getAllModuleList(AdminModule root, String prefix) throws ModelException {
		List<AdminModuleForm> list = new ArrayList<AdminModuleForm>();
		Set<AdminModule> adminModules = root.getAdminModules();
		
		
		
		for (AdminModule adminModule : adminModules) {
			adminModule.setName(prefix + adminModule.getName());
			list.add(new AdminModuleForm(adminModule));
			list.addAll(getAllModuleList(adminModule,"-" + prefix));
		}
		return list;
	}

	public static AdminModuleForm getModuleFormByCode(String pageCodeValue) throws ModelException {
		if(pageCodeValue!=null){
			AdminModule adminModule = getModuleByCode(pageCodeValue);
			return new AdminModuleForm(adminModule);
		}
		else{
			return null;
		}
	}

	private static AdminModule getModuleByCode(String pageCodeValue)
			throws ModelException {
		ModelHome modelHome = new ModelHome();
		AdminModule adminModule = new AdminModule();
		adminModule.setCode(pageCodeValue);
		adminModule = (AdminModule)modelHome.findByKeyExample(adminModule);
		return adminModule;
	}
	
	private AdminModule getSAdminModuleById(Integer id) {
		if(id!=null){
			ModelHome modelHome = new ModelHome();
			AdminModule adminModule = new AdminModule();
			adminModule = (AdminModule) modelHome.findById(adminModule, id);
			return adminModule;
		}
		else{
			return null;
		}
	}

	public Order getDefaultOrder() {
		return Order.desc("updateTime");
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
	
	public void clearId() {
		this.setId(null);
	}

	public Serializable[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}

	public void action(RuntechContext action) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}

}
