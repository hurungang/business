package com.runtech.onlineshop.form;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Regulation;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class RegulationForm extends Regulation implements ModelForm {

	private Integer ids[];
	private String actionType;
	
	public RegulationForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegulationForm(Object regulation) throws ModelException {
		this.copyFrom(regulation);
	}
	
	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			Regulation regulation = (Regulation) model;
			PropertyUtils.copyProperties(this, regulation);
			this.setId(regulation.getId());
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
			Regulation regulation = new Regulation();
			PropertyUtils.copyProperties(regulation,this);
			return regulation;
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

	public Integer[] getIds() {
		// TODO Auto-generated method stub
		return ids;
	}
	
	public void clearId() {
		this.setId(null);
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		if (Constant.ACTION_APPROVE.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			Regulation regulation = (Regulation)modelHome.findById(new Regulation(), this.getId());
			regulation.setStatus(Constant.STATUS_APPROVED);
			try {
				modelHome.beginTransaction();
				modelHome.merge(regulation);
				modelHome.commit();
			} catch (Exception e) {
				modelHome.rollback();
			}
		}			
	}
	
	public void applyRegulation(RuntechContext siteContext, Object helper) 
			throws ParseErrorException, MethodInvocationException, ResourceNotFoundException, IOException {
			String statements = getStatements();
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put(RuntechContext.KEYWORD_CONTEXT, siteContext);
			velocityContext.put(RuntechContext.KEYWORD_COMPONENT_RESULT, helper);
			Velocity.evaluate(velocityContext, new StringWriter(), helper.getClass().getName(), statements);
	}
	
	public static RegulationForm getRegulationHelperByCode(String code) throws ModelException{
		ModelHome modelHome = new ModelHome();
		Regulation regulationExample = new Regulation();
		regulationExample.setRegulationCode(code);
		Regulation regulation = (Regulation) modelHome.findByKeyExample(regulationExample);
		if(regulation!=null){
			return new RegulationForm(regulation);
		}else{
			return null;
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
