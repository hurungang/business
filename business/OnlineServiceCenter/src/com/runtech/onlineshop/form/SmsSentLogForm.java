package com.runtech.onlineshop.form;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.QuickComment;
import com.runtech.onlineshop.model.SmsSentLog;
import com.runtech.onlineshop.model.SmsSentLog;
import com.runtech.util.SMSSender;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class SmsSentLogForm extends SmsSentLog implements ModelForm {

	private Integer[] ids;
	
	public SmsSentLogForm() {
		super();
	}

	public SmsSentLogForm(SmsSentLog smsSentLog) throws ModelException {
		this.copyFrom(smsSentLog);
	}
	
	public void action(RuntechContext context) throws ModelException {

		ModelHome modelHome = new ModelHome();
		try {
			Date now = new Date();
			modelHome.beginTransaction();
			PageAction action = (PageAction) context.getAction();
			SMSSender smsSender = action.getSmsSender();
			if (action.getActionType().equals(Constant.ACTION_SEND)){
				if(smsSender!=null&&smsSender.send(this.getRecipients(),this.getContent(),now)){
					this.setStatus(Constant.STATUS_SUCCEED);
				}else{
					this.setStatus(Constant.STATUS_FAILED);
					this.setRemark(smsSender.getError());
				}
				this.setSendTime(now);
				modelHome.save(this.getModel());
			}
			modelHome.commit();
			context.setResult(action.getResultSuccess());
		} catch (Exception e) {
			context.setError(e.getLocalizedMessage());
			modelHome.rollback();
		}
		
	}

	public void clearId() {
		this.setId(null);

	}

	public void copyFrom(Object model) throws ModelException {
		try {
			SmsSentLog realModel = (SmsSentLog) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub

	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return null;
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
		return this.ids;
	}

	public Object getModel() throws ModelException {
		try {
			SmsSentLog model = new SmsSentLog();
			PropertyUtils.copyProperties(model,this);
			return model;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public List<ModelForm> getListByStatus(Pager pager, String status) throws ModelException{
		ModelHome modelHome = new ModelHome();
		SmsSentLog smsSentLog = new  SmsSentLog();
		smsSentLog.setStatus(status);
		Integer count = modelHome.getCount(smsSentLog);
		
		
		pager.setTotalSize(count);
		Integer startNumber = pager.getStartNumber();
		Integer fetchSize = pager.getPageSize();
		
		List<ModelForm> list = modelHome.listForm(new SmsSentLogForm(smsSentLog), getDefaultOrder(), startNumber, fetchSize);
		return list;
	}
}
