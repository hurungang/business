package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.PointLog;
import com.runtech.onlineshop.model.QuickComment;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;

public class PointLogForm extends PointLog implements ModelForm {

	public PointLogForm() {
		super();
	}

	public PointLogForm(PointLog pointLog) throws ModelException {
		this.copyFrom(pointLog);
	}
	public void copyFrom(Object model) throws ModelException {
		try {
			PointLogForm realModel = (PointLogForm) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub

	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub

	}

	public Object getModel() throws ModelException {
		try {
			PointLog realModel = new PointLog();
			PropertyUtils.copyProperties(realModel,this);
			return realModel;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public void setFormId(String idValue) {
		// TODO Auto-generated method stub

	}

	public String getFormId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public Serializable[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}


	public void clearId() {
		// TODO Auto-generated method stub

	}

	public void setPoint(String point){
		BigDecimal pointNumber = new BigDecimal(0);
		try {
			pointNumber = new BigDecimal(point);
		} catch (Exception e) {
		}
		this.setPoint(pointNumber);
	}
}
