package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.PageTemplate;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class PageTemplateForm extends PageTemplate implements ModelForm {

	private Integer[] ids;
	private String actionType;

	public void copyFrom(Object model) throws ModelException {
		try {
			PageTemplate realModel = (PageTemplate) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Object getModel() throws ModelException {
		try {
			PageTemplate model = new PageTemplate();
			PropertyUtils.copyProperties(model,this);
			return model;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}


	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return Order.desc("updateTime");
	}


	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		this.setUpdater(context.getUser().getId());
		this.setUpdateTime(new Date());
		this.setStatus(Constant.STATUS_DRAFT);
		return true;
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
	
	public List<Object> getAllPageTemplates(){
		PageTemplate pageTemplate = new PageTemplate();
		ModelHome modelHome = new ModelHome();
		List<Object> list = modelHome.findByExample(pageTemplate);
		return list;
	}
	
	public void clearId() {
		this.setId(null);
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		if (this.getActionType().equals(Constant.ACTION_APPROVE))
		{
			ModelHome modelHome = new ModelHome();
			PageTemplate pageTemplate = (PageTemplate)modelHome.findById(new PageTemplate(), this.getId());
			pageTemplate.setStatus(Constant.STATUS_APPROVED);
			try {
				modelHome.beginTransaction();
				modelHome.merge(pageTemplate);
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
