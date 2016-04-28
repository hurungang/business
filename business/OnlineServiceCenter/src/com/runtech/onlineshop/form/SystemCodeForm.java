package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.SystemCode;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class SystemCodeForm extends SystemCode implements ModelForm {

	private Integer parentId;
	private Integer[] ids;
	private String actionType;
	
	public SystemCodeForm()
	{
		super();
	}
	
	public SystemCodeForm(Object sysCode) throws ModelException {
		this.copyFrom(sysCode);
	}
	
	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			SystemCode realModel = (SystemCode) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Object getModel() throws ModelException {
		// TODO Auto-generated method stub
		try {
			SystemCode sysCode = new SystemCode();
			PropertyUtils.copyProperties(sysCode,this);
			return sysCode;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		this.setSystemCode(getSystemCodeById(this.parentId));
		this.setUpdater(context.getUser().getId());
		this.setUpdateTime(new Date());
		this.setStatus(Constant.STATUS_ENABLED);
		return true;
	}

	private SystemCode getSystemCodeById(Integer id) {
		if(id!=null){
			ModelHome modelHome = new ModelHome();
			SystemCode sysCode = new SystemCode();
			sysCode = (SystemCode) modelHome.findById(sysCode, id);
			return sysCode;
		}
		else{
			return null;
		}
	}

	public Integer getParentId() {
		return parentId;
	}
	
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public List<Object> getAllSysCodes() throws ModelException {
		SystemCode sysCode = new SystemCode();
		ModelHome modelHome = new ModelHome();
		List<Object> list = modelHome.findByExample(sysCode);
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
		if (Constant.ACTION_APPROVE.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			SystemCode systemCode = (SystemCode)modelHome.findById(new SystemCode(), this.getId());
			systemCode.setStatus(Constant.STATUS_APPROVED);
			try {
				modelHome.beginTransaction();
				modelHome.merge(systemCode);
				modelHome.commit();
			} catch (Exception e) {
				modelHome.rollback();
			}
		}			
	}


	public static List<SystemCode> getCodes(String category) throws ModelException{
		ModelHome modelHome = new ModelHome();
		SystemCode systemCodeExample = new SystemCode();
		systemCodeExample.setCategory(category);
		systemCodeExample.setStatus(Constant.STATUS_ENABLED);
		List systemCodes = modelHome.findByExample(systemCodeExample, "priority",true);
		return systemCodes;
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
	
	public static SystemCode getCode(String code,String category) throws ModelException{
		ModelHome modelHome = new ModelHome();
		SystemCode systemCodeExample = new SystemCode();
		systemCodeExample.setCode(code);
		systemCodeExample.setCategory(category);
		SystemCode systemCode = (SystemCode) modelHome.findByKeyExample(systemCodeExample);
		return systemCode;
	}
}
