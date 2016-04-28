package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Commodity;
import com.runtech.onlineshop.model.CommodityPromotion;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class CommodityPromotionForm extends CommodityPromotion implements
		ModelForm {

	private Integer[] ids;
	private String actionType;
	private Integer commodityId;
	
	public CommodityPromotionForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommodityPromotionForm(Object commodityPromotion) throws ModelException {
		this.copyFrom(commodityPromotion);
	}
	
	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			CommodityPromotion commodityPromotion = (CommodityPromotion) model;
			PropertyUtils.copyProperties(this, commodityPromotion);
			this.setId(commodityPromotion.getId());
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
			CommodityPromotion commodityPromotion = new CommodityPromotion();
			PropertyUtils.copyProperties(commodityPromotion,this);
			return commodityPromotion;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		this.setStatus(Constant.STATUS_ENABLED);
		this.setCommodity((Commodity)modelHome.findById(new Commodity(), this.commodityId));
		return true;
	}

	public Integer getCommodityId() {
		return this.getCommodity().getId();
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
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


	public Serializable[] getIds() {
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
			updateStatus(Constant.STATUS_APPROVED);
		}
		else if (Constant.ACTION_SPECIAL_DELETE.equals(this.getActionType()))
		{
			updateStatus(Constant.STATUS_DELETED);
		}
	}

	private void updateStatus(String status) {
		ModelHome modelHome = new ModelHome();
		CommodityPromotion commodityPromotion = (CommodityPromotion)modelHome.findById(new CommodityPromotion(), this.getId());
		commodityPromotion.setStatus(status);
		try {
			modelHome.beginTransaction();
			modelHome.merge(commodityPromotion);
			modelHome.commit();
		} catch (Exception e) {
			modelHome.rollback();
		}
	}
	
	public List<CommodityPromotionForm> getSearchResult(Pager pager) throws ModelException
	{
		List<CommodityPromotionForm> list = new ArrayList<CommodityPromotionForm>();
		ModelHome modelHome = new ModelHome();
		CommodityPromotion commodityPromotion = new CommodityPromotion(); 
		String queryString = "from CommodityPromotion where status !='deleted' ";
		if(this.getTitle()!=null){
			queryString += " and title like \"%"+this.getTitle()+"%\"";
		}
		if(this.getTypeCode()!=null){
			queryString += " and typeCode = \""+this.getTypeCode()+"\"";
		}
		if(this.getStartTime()!=null){
			queryString += " and startTime < \""+this.getStartTimeString()+"\"";
		}
		if(this.getEndTime()!=null){
			queryString += " and endTime > \""+this.getEndTimeString()+"\"";
		}
		queryString += " order by startTime desc";
		pager.setTotalSize(modelHome.getCount(queryString));
		List<Object> tempList = modelHome.executeQuery(queryString, pager.getStartNumber(), pager.getPageSize());
		for (Object object : tempList) {
			CommodityPromotionForm commodityPromotionForm = new CommodityPromotionForm(object);
			list.add(commodityPromotionForm);
		}
		return list;
	}
	public static List getPromotionListByType(String[] typeCodes){
		ModelHome modelHome = new ModelHome();
		String queryString = "from CommodityPromotion where status!='"+Constant.STATUS_DELETED+"' ";
		if(typeCodes!=null){
			queryString += "and typeCode in(";
			int i=0;
			for (; i < typeCodes.length-1; i++) {
				queryString += "'"+typeCodes[i]+"',";
			}
			queryString += "'"+typeCodes[i]+"')";
		}
		queryString += " order by endTime desc, startTime desc";
		return modelHome.executeQuery(queryString,null,null);
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

	public String getEndTimeString() {
		// TODO Auto-generated method stub
		return Constant.TIME_PICKER_FORMAT.format(super.getEndTime());
	}

	public String getStartTimeString() {
		// TODO Auto-generated method stub
		return Constant.TIME_PICKER_FORMAT.format(super.getStartTime());
	}

	public void setEndTimeString(String endTimeString) {
		try {
			this.setEndTime(Constant.TIME_PICKER_FORMAT.parse(endTimeString));
		} catch (ParseException e) {
		}
	}
	public void setStartTimeString(String startTimeString) {
		try {
			this.setStartTime(Constant.TIME_PICKER_FORMAT.parse(startTimeString));
		} catch (ParseException e) {
		}
	}
}
