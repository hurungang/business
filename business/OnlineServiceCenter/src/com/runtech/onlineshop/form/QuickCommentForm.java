package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.QuickComment;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class QuickCommentForm extends QuickComment implements ModelForm {

	private String actionType;
	
	public QuickCommentForm() {
		super();
	}
	
	public QuickCommentForm(QuickComment quickComment) throws ModelException {
		this.copyFrom(quickComment);
	}
	public void action(RuntechContext context) throws ModelException {
		
	}

	public void clearId() {
		// TODO Auto-generated method stub

	}
	private String getActionType() {
		// TODO Auto-generated method stub
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public void copyFrom(Object model) throws ModelException {
		try {
			QuickComment realModel = (QuickComment) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}

	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return Order.desc("commentTime");
	}

	public String getFormId() {
		// TODO Auto-generated method stub
		return this.getId().toString();
	}


	public Serializable[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getModel() throws ModelException {
		try {
			QuickComment realModel = new QuickComment();
			PropertyUtils.copyProperties(realModel,this);
			return realModel;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		if(this.getId()!=null){
			QuickComment quickComment = (QuickComment) modelHome.findById(new QuickComment(), this.getId());
			this.setCommentTime(quickComment.getCommentTime());
			this.setCommentContent(quickComment.getCommentContent());
			this.setClientName(quickComment.getClientName());
		}
		Date now = new Date();
		this.setReplyTime(now);
		this.setStatus(Constant.STATUS_READ);
		return true;
	}

	public void setFormId(String idValue) {
		if(idValue!=null){
			super.setId(Integer.parseInt(idValue));
		}
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}
	
	public List<ModelForm> getListByStatus(Pager pager, String status) throws ModelException{
		ModelHome modelHome = new ModelHome();
		QuickComment quickComment = new  QuickComment();
		quickComment.setStatus(status);
		Integer count = modelHome.getCount(quickComment);
		
		
		pager.setTotalSize(count);
		Integer startNumber = pager.getStartNumber();
		Integer fetchSize = pager.getPageSize();
		
		List<ModelForm> list = modelHome.listForm(new QuickCommentForm(quickComment), getDefaultOrder(), startNumber, fetchSize);
		return list;
	}
	
}
