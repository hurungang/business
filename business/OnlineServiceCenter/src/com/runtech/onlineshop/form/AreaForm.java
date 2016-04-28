package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.Area;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class AreaForm extends Area implements ModelForm {

	private Integer[] ids;
	private String actionType;
	
	public AreaForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AreaForm(Object model) throws ModelException
	{
		this.copyFrom(model);
	}
	
	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			Area areaModel = (Area) model;
			PropertyUtils.copyProperties(this, areaModel);
			this.setId(areaModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return Order.desc("updateTime");
	}

	public String getFormId() {
		// TODO Auto-generated method stub
		if(super.getId()!=null){
			return super.getId().toString();
		}else{
			return null;
		}
	}

	public Object getModel() throws ModelException {
		// TODO Auto-generated method stub
		try {
			
			Area area = new Area();
			PropertyUtils.copyProperties(area,this);
			return area;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		this.setUpdater(context.getUser().getId());
		this.setUpdateTime(new Date());
		this.setStatus(Constant.STATUS_ENABLED);
		return true;
	}

	public void setFormId(String idValue) {
		// TODO Auto-generated method stub
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
	
	public List<AreaForm> getAllAreas() throws ModelException
	{
		List<AreaForm> list = new ArrayList<AreaForm>();
		ModelHome modelHome = new ModelHome();
		Area area = new Area();
		List<Object> tempList = modelHome.findByExample(area);
		for (Object object : tempList) {
			AreaForm areaForm = new AreaForm(object);
			list.add(areaForm);
		}
		return list;
	}
	
	public List<AreaForm> getAllCommodityAreas() throws ModelException
	{
		List<AreaForm> list = new ArrayList<AreaForm>();
		ModelHome modelHome = new ModelHome();
		Area area = new Area();
		area.setCategory(Constant.CODE_COMMODITY_AREA);
		area.setStatus(Constant.STATUS_ENABLED);
		List<Object> tempList = modelHome.findByExample(area);
		for (Object object : tempList) {
			AreaForm areaForm = new AreaForm(object);
			list.add(areaForm);
		}
		return list;
	}
	
	public List<AreaForm> getAllDeliveryAreas() throws ModelException
	{
		List<AreaForm> list = new ArrayList<AreaForm>();
		ModelHome modelHome = new ModelHome();
		Area area = new Area();
		area.setCategory(Constant.CODE_DELIVERY_AREA);
		area.setStatus(Constant.STATUS_ENABLED);
		List<Object> tempList = modelHome.findByExample(area);
		for (Object object : tempList) {
			AreaForm areaForm = new AreaForm(object);
			list.add(areaForm);
		}
		return list;
	}
	
	public void clearId() {
		this.setId(null);
	}

	public Integer[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		if (Constant.ACTION_APPROVE.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			Area area = (Area)modelHome.findById(new Area(), this.getId());
			area.setStatus(Constant.STATUS_APPROVED);
			try {
				modelHome.beginTransaction();
				modelHome.merge(area);
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
