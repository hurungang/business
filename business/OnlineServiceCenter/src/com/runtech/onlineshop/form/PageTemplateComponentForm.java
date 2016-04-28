package com.runtech.onlineshop.form;

import java.io.Serializable;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.AdminRoleModuleId;
import com.runtech.onlineshop.model.PageComponent;
import com.runtech.onlineshop.model.PageTemplateComponent;
import com.runtech.onlineshop.model.PageTemplateComponentId;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class PageTemplateComponentForm extends PageTemplateComponent implements
		ModelForm {

	private PageTemplateComponentId[] ids;
	private String actionType;
	
	public void copyFrom(Object model) throws ModelException {
		try {
			PageTemplateComponent realModel = (PageTemplateComponent) model;
			this.setId(realModel.getId());
			PropertyUtils.copyProperties(this, realModel);
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Object getModel() throws ModelException {
		try {
			PageTemplateComponent model = new PageTemplateComponent();
			PropertyUtils.copyProperties(model,this);
			return model;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Order getDefaultOrder() {
		return Order.desc("id");
	}

	public String getFormId() {
		PageTemplateComponentId id = super.getId();
		return id.getTemplateId()+Constant.SYMBOL_ID_SPLIT+id.getComponentId();
	}


	public boolean prepare(RuntechContext context) {
		if(this.getPageComponent()!=null&&this.getPageTemplate()!=null){
			PageTemplateComponentId id = new PageTemplateComponentId();
			id.setTemplateId(this.getPageTemplate().getId());
			id.setComponentId(this.getPageComponent().getId());
			this.setId(id);
		}
		this.setStatus(Constant.STATUS_DRAFT);
		return true;
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
					PageTemplateComponentId id = new PageTemplateComponentId();
					id.setTemplateId(Integer.parseInt(idArray[0]));
					id.setComponentId(Integer.parseInt(idArray[1]));
					super.setId(id);
				}
			}
			else
			{
				ids = new PageTemplateComponentId[splitValues.length];
				for(int i = 0; i < splitValues.length; i++)
				{
					idArray = splitValues[i].trim().split(Constant.SYMBOL_ID_SPLIT);
					if(idArray!=null&&idArray.length==2){
						PageTemplateComponentId id = new PageTemplateComponentId();
						id.setTemplateId(Integer.parseInt(idArray[0]));
						id.setComponentId(Integer.parseInt(idArray[1]));
						ids[i] = id;
					}
				}
			}
		}		
	}

	public PageTemplateComponentId[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}
	
	public void clearId() {
		this.setId(null);
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		if (Constant.ACTION_APPROVE.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			PageTemplateComponent pageTemplateComponent = (PageTemplateComponent)modelHome.findById(new PageTemplateComponent(), this.getId());
			pageTemplateComponent.setStatus(Constant.STATUS_APPROVED);
			try {
				modelHome.beginTransaction();
				modelHome.merge(pageTemplateComponent);
				modelHome.commit();
			} catch (Exception e) {
				modelHome.rollback();
			}
		}		
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}
	private String getActionType() {
		// TODO Auto-generated method stub
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

}
