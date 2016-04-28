package com.runtech.onlineshop.form;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.CommodityBase;
import com.runtech.onlineshop.model.CommodityDetail;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class CommodityDetailForm extends CommodityDetail implements ModelForm {

	private Integer[] ids;
	private Integer commodityBaseId;
	private String nextLocation;
	private String actionType;
	
	public CommodityDetailForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CommodityDetailForm(CommodityDetail commodityDetail) throws ModelException {
		this.copyFrom(commodityDetail);
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		modelHome.beginTransaction();
		PageAction action = (PageAction) context.getAction();
		try {
			if(action.getActionType().equals(Constant.ACTION_SPECIAL_SAVE)){
				this.prepare(context);
				modelHome.merge(this.getModel());
				modelHome.commit();
				context.setResult(action.getResultCustom());
				context.setNextLocation(this.nextLocation);
			}
		} catch (Exception e) {
			modelHome.rollback();
			action.addActionError(e.getMessage());
		}
	}

	public void clearId() {
		// TODO Auto-generated method stub
		this.setId(null);
	}

	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			CommodityDetail commodityDetail= (CommodityDetail) model;
			PropertyUtils.copyProperties(this, commodityDetail);
			this.setId(commodityDetail.getId());
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
			CommodityDetail commodityDetail = new CommodityDetail();
			PropertyUtils.copyProperties(commodityDetail,this);
			return commodityDetail;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		CommodityBase commodityBase = (CommodityBase) modelHome.findById(new CommodityBase(), this.getCommodityBaseId());
		this.setCommodityBase(commodityBase);
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

	public Integer getCommodityBaseId() {
		if(this.commodityBaseId!=null){
			return this.commodityBaseId;
		}else if(this.getCommodityBase()!=null){
			return this.getCommodityBase().getId();
		}else{
			return null;
		}
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityBaseId = commodityId;
	}
	
	public List getDetailList(){
		ModelHome modelHome = new ModelHome();
		StringBuffer queryString = new StringBuffer("from CommodityDetail");
		if(this.commodityBaseId!=null){
			queryString.append(" where commodityBase.id="+this.commodityBaseId);
		}
		return modelHome.executeQuery(queryString.toString(),null,null);
	}
	public Integer getDetailListCount(){
		ModelHome modelHome = new ModelHome();
		StringBuffer queryString = new StringBuffer("from CommodityDetail");
		if(this.commodityBaseId!=null){
			queryString.append(" where commodityBase.id="+this.commodityBaseId);
		}
		return modelHome.getCount(queryString.toString());
	}

	public String getNextLocation() {
		return nextLocation;
	}

	public void setNextLocation(String nextLocation) {
		this.nextLocation = nextLocation;
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		PageAction action = (PageAction)context.getAction();
		if(action.getActionType().equals(Constant.ACTION_DELETE)){
			context.setResult(context.getAction().getResultCustom());
			context.setNextLocation(this.nextLocation);
		}
	}
	private String getActionType() {
		// TODO Auto-generated method stub
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

}
