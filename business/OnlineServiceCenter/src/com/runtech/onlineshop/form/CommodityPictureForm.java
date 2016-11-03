package com.runtech.onlineshop.form;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Commodity;
import com.runtech.onlineshop.model.CommodityDetail;
import com.runtech.onlineshop.model.CommodityPicture;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class CommodityPictureForm extends CommodityPicture implements ModelForm {

	private Integer[] ids;
	private Integer commodityId;
	private String nextLocation;
	private File picture;
	private String pictureFileName;
	private String actionType;
	
	public CommodityPictureForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommodityPictureForm(CommodityPicture commodityPicture) throws ModelException {
		this.copyFrom(commodityPicture);
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		PageAction action = (PageAction) context.getAction();
		if(Constant.ACTION_SPECIAL_SAVE.equals(action.getActionType())){
			ModelHome modelHome = new ModelHome();
			try {
				this.prepare(context);
				if(this.picture!=null){
					String filePath = context.saveImage(this.picture,this.pictureFileName,true);
					this.setPath(filePath);
				}else{
					action.addActionError("no picture selected");
					return;
				}
				modelHome.beginTransaction();
				modelHome.merge(this.getModel());
				modelHome.commit();
				context.setResult(action.getResultCustom());
				context.setNextLocation(this.nextLocation);
			} catch (Exception e) {
				modelHome.rollback();
				action.addActionError(e.getMessage());
			}
		}
	}

	public String getNextLocation() {
		return nextLocation;
	}

	public void setNextLocation(String nextLocation) {
		this.nextLocation = nextLocation;
	}

	public void clearId() {
		this.setId(null);
	}

	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			CommodityPicture commodityPicture= (CommodityPicture) model;
			PropertyUtils.copyProperties(this, commodityPicture);
			this.setId(commodityPicture.getId());
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
			CommodityPicture commodityPicture = new CommodityPicture();
			PropertyUtils.copyProperties(commodityPicture,this);
			return commodityPicture;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		this.setCommodity((Commodity)modelHome.findById(new Commodity(), this.commodityId));
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

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}	
	
	public List getPictureList(){
		ModelHome modelHome = new ModelHome();
		StringBuffer queryString = new StringBuffer("from CommodityPicture");
		if(this.commodityId!=null){
			queryString.append(" where commodity.id="+this.commodityId);
		}
		return modelHome.executeQuery(queryString.toString(),null,null);
	}
	public Integer getPictureListCount(){
		ModelHome modelHome = new ModelHome();
		StringBuffer queryString = new StringBuffer("from CommodityPicture");
		if(this.commodityId!=null){
			queryString.append(" where commodity.id="+this.commodityId);
		}
		return modelHome.getCount(queryString.toString());
	}
	
	public void setPicture(File picture) {
		this.picture = picture;
	}
	
	public void setPictureFileName(String fileName){
		this.pictureFileName = fileName;
	}

	public void finish(RuntechContext context) {
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
