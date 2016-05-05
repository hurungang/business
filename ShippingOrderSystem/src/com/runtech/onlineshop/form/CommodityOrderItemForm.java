package com.runtech.onlineshop.form;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Commodity;
import com.runtech.onlineshop.model.CommodityOrder;
import com.runtech.onlineshop.model.CommodityOrderItem;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.model.CartItem;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class CommodityOrderItemForm extends CommodityOrderItem implements
		ModelForm {

	private Integer[] ids;
	private String actionType;
	private Integer commodityId;
	
	public CommodityOrderItemForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommodityOrderItemForm(Object model) throws ModelException {
		this.copyFrom(model);
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		if (Constant.ACTION_APPROVE.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			CommodityOrder commodityOrder = (CommodityOrder)modelHome.findById(new CommodityOrder(), this.getId());
			commodityOrder.setStatus(Constant.STATUS_APPROVED);
			try {
				modelHome.beginTransaction();
				modelHome.merge(commodityOrder);
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

	public void clearId() {
		// TODO Auto-generated method stub
		this.setId(null);
	}

	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			CommodityOrderItem commodityOrderItem = (CommodityOrderItem) model;
			PropertyUtils.copyProperties(this, commodityOrderItem);
			this.setId(commodityOrderItem.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFormId() {
		// TODO Auto-generated method stub
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

	public Object getModel() throws ModelException {
		// TODO Auto-generated method stub
		try {
			CommodityOrderItem commodityOrderItem = new CommodityOrderItem();
			PropertyUtils.copyProperties(commodityOrderItem,this);
			return commodityOrderItem;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		this.setStatus(Constant.STATUS_PENDING);
		return true;
	}

	public void setCommodityId(Integer commodityId){
		if(commodityId!=null){
			ModelHome modelHome = new ModelHome();
			Commodity commodity = (Commodity) modelHome.findById(new Commodity(), commodityId);
			this.setCommodity(commodity);
		}
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

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}

	public Integer getCommodityId() {
		if(this.getCommodity()==null){
			return null;
		}
		return this.getCommodity().getId();
	}
	
}
