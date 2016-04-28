package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;
import org.hibernate.exception.JDBCConnectionException;

import com.runtech.onlineshop.model.WorkFlowLog;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.FlowableForm;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.ui.Pager;

public class WorkFlowLogForm extends WorkFlowLog implements ModelForm {

	private String actionType;
	
	public WorkFlowLogForm() {
		super();
	}
	
	public WorkFlowLogForm(WorkFlowLog workFlowLog) throws ModelException {
		this.copyFrom(workFlowLog);
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
			WorkFlowLog realModel = (WorkFlowLog) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}

	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return Order.desc("reviewTime");
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
			WorkFlowLog realModel = new WorkFlowLog();
			PropertyUtils.copyProperties(realModel,this);
			return realModel;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public void setFormId(String idValue) {
		if(idValue!=null){
			super.setId(Integer.parseInt(idValue));
		}
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}

	public boolean prepare(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List getMyReviewLogs(Pager pager, RuntechContext context) throws ModelException {
		ModelHome modelHome = new ModelHome();
		try{
			String queryString = "from WorkFlowLog where adminByReviewer.id="+context.getUser().getId()+" order by reviewTime desc";
			List<Object> tempList = modelHome.executeQuery(queryString, pager.getStartNumber(), pager.getPageSize());
			return tempList;
		}catch (JDBCConnectionException e) {
			context.setError(e.getSQLException().getMessage());
			return null;
		}
	}
	public List getMySubmitLogs(Pager pager, RuntechContext context) throws ModelException {
		ModelHome modelHome = new ModelHome();
		try{
			String queryString = "from WorkFlowLog where adminBySubmitter.id="+context.getUser().getId()+" order by reviewTime desc";
			List<Object> tempList = modelHome.executeQuery(queryString, pager.getStartNumber(), pager.getPageSize());
			return tempList;
		}catch (JDBCConnectionException e) {
			context.setError(e.getSQLException().getMessage());
			return null;
		}
	}
	
	public static List getDocumentLogs(FlowableForm document) throws ModelException {
		ModelHome modelHome = new ModelHome();
		try{
			String queryString = "from WorkFlowLog where documentType='"+document.getDocumentType()+"' and documentId="+document.getFormId()+" order by reviewTime desc";
			List<Object> tempList = modelHome.executeQuery(queryString, 0, null);
			return tempList;
		}catch (JDBCConnectionException e) {
			return null;
		}
	}
}
