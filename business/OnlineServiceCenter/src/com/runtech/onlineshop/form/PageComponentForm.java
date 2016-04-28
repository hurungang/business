package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Content;
import com.runtech.onlineshop.model.PageComponent;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class PageComponentForm extends PageComponent implements ModelForm {

	private Integer[] ids;
	private String actionType;
	
	public void copyFrom(Object model) throws ModelException {
		try {
			PageComponent realModel = (PageComponent) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Object getModel() throws ModelException {
		try {
			PageComponent model = new PageComponent();
			PropertyUtils.copyProperties(model,this);
			return model;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}


	public Order getDefaultOrder() {
		return Order.desc("updateTime");
	}

	public boolean prepare(RuntechContext context) {
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

	public Serializable[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}
	
	public List<Object> getAllPageComponents(){
		PageComponent pageTemplate = new PageComponent();
		ModelHome modelHome = new ModelHome();
		List<Object> list = modelHome.findByExample(pageTemplate);
		return list;
	}
	
	public void clearId() {
		this.setId(null);
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		if (Constant.ACTION_APPROVE.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			PageComponent pageComponent = (PageComponent)modelHome.findById(new PageComponent(), this.getId());
			pageComponent.setStatus(Constant.STATUS_APPROVED);
			try {
				modelHome.beginTransaction();
				modelHome.merge(pageComponent);
				modelHome.commit();
			} catch (Exception e) {
				modelHome.rollback();
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

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}

}
