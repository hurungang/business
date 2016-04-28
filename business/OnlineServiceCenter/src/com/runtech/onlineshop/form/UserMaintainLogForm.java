package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.User;
import com.runtech.onlineshop.model.UserMaintainLog;
import com.runtech.onlineshop.security.AdminImpl;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.FlowableForm;
import com.runtech.web.form.ModelForm;
import com.runtech.web.model.Authorization;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class UserMaintainLogForm extends UserMaintainLog implements ModelForm,FlowableForm {

	private Integer[] ids;
	private String nextLocation;
	private String userFullName;
	private List logs;

	public UserMaintainLogForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserMaintainLogForm(Object form) throws ModelException {
		this.copyFrom(form);
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub

	}

	public void clearId() {
		// TODO Auto-generated method stub

	}

	public void copyFrom(Object model) throws ModelException {
		try {
			UserMaintainLog realModel = (UserMaintainLog) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public void finish(RuntechContext context) throws ModelException {
			Admin admin = this.getAdmin();
			WorkFlowForm.sendWorkFlowMail(context, this);
	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFormId() {
		// TODO Auto-generated method stub
		return this.getId().toString();
	}

	public Serializable[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNextLocation() {
		return nextLocation;
	}

	public void setNextLocation(String nextLocation) {
		this.nextLocation = nextLocation;
	}

	public Object getModel() throws ModelException {
		try {
			UserMaintainLog model = new UserMaintainLog();
			PropertyUtils.copyProperties(model,this);
			return model;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		String status = Constant.STATUS_DRAFT;
		this.setStatus(status);
		if(this.getId()!=null){
			UserMaintainLog originalUserMaintainLog = (UserMaintainLog) modelHome.findById(new UserMaintainLog(), this.getId());
//			if(Constant.STATUS_APPROVED.equals(status)){
//				context.setErrorByKey("error.status.noUpdate");
//				return false;
//			}
			Date signTime = originalUserMaintainLog.getMaintainTime();
			if(signTime!=null){
				this.setMaintainTime(signTime);
			}
			if(originalUserMaintainLog.getUser()!=null){
				this.setUser(originalUserMaintainLog.getUser());
			}
		}
		if(this.getAdmin()==null){
			this.setAdmin((Admin)context.getUser());
		}
		this.setUpdater((Admin)context.getUser());
		Date now = new Date();
		this.setUpdateTime(now);
		if(this.getUser()!=null){
			this.getUser().setLastMaintainTime(now);
		}
		if(this.nextLocation!=null){
			context.setResult(context.getAction().getResultCustom());
			context.setNextLocation(this.nextLocation);
		}
		return true;
	}
	
	public void setServiceAdminId(Integer serviceAdminId) {
		if(serviceAdminId!=null){
			ModelHome modelHome = new ModelHome();
			Admin admin = (Admin) modelHome.findById(new Admin(), serviceAdminId);
			this.setAdmin(admin);
		}
	}

	public void setUserId(Integer userId) {
		if(userId!=null){
			ModelHome modelHome = new ModelHome();
			User user = (User) modelHome.findById(new User(), userId);
			this.setUser(user);
		}
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
	
	public List<UserMaintainLogForm> getSearchResult(Pager pager, Integer userId, RuntechContext context) throws ModelException
	{
		List<UserMaintainLogForm> list = new ArrayList<UserMaintainLogForm>();
		ModelHome modelHome = new ModelHome();
		String queryString = "from UserMaintainLog where 1=1 ";
		if(this.getUserFullName()!=null&&!this.getUserFullName().trim().equals("")){
			queryString += " and user.fullName like \"%"+this.getUserFullName()+"%\"";
		}
		if(userId!=null){
			queryString += " and user.id = "+userId;
		}
		if(!context.getUser().getMenu().getActiveMenu().getAuthorization().isSupervisable()){
			queryString += " and (admin.id = "+context.getUser().getId()+" or updater.id = "+context.getUser().getId()+" or user.admin.id = "+context.getUser().getId()+")";
		}
		queryString += " order by updateTime desc, maintainTime desc";
		pager.setTotalSize(modelHome.getCount(queryString));
		List<Object> tempList = modelHome.executeQuery(queryString, pager.getStartNumber(), pager.getPageSize());
		for (Object object : tempList) {
			UserMaintainLogForm commodityForm = new UserMaintainLogForm(object);
			list.add(commodityForm);
		}
		return list;
	}
	
	public void setMaintainTimeString(String timeString) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date date = formatter.parse(timeString);
		this.setMaintainTime(date);
	}
	
	
	public String getMaintainTimeString(){
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		return formatter.format(this.getMaintainTime());
	}
	
	public void setOpportunityTimeString(String timeString) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date date = formatter.parse(timeString);
		this.setOpportunityTime(date);
	}
	
	
	public String getOpportunityTimeString(){
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		return formatter.format(this.getMaintainTime());
	}
	public Integer getServiceAdminId(){
		if(this.getAdmin()!=null){
			return this.getAdmin().getId();
		}else{
			return null;
		}
	}
	private String getUserFullName() {
		// TODO Auto-generated method stub
		return this.userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	
	public BigDecimal getOpportunityPossibilityPercentage() {
		if(this.getOpportunityPossibility()!=null){
			return this.getOpportunityPossibility().multiply(new BigDecimal(100));
		}else{
			return new BigDecimal(0);
		}
	}

	public void setOpportunityPossibilityPercentage(
			BigDecimal opportunityPossiblityPercentage) {
		this.setOpportunityPossibility(opportunityPossiblityPercentage.divide(new BigDecimal(100)));
	}

	public com.runtech.web.model.User getFlowSubmitter() throws ModelException{
		return new AdminImpl(super.getAdmin());
	}
	
	public void setFlowLogs(List logs) {
		this.logs = logs;
	}

	public List getFlowLogs() {
		return this.logs;
	}

	public String getDocumentType() {
		return "UserMaintainLog";
	}
	
	public Authorization getAuthorization(RuntechContext context) {
		return WorkFlowForm.getAuthorization(this,context);
	}
}

