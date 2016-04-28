package com.runtech.onlineshop.form;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.AdminRole;
import com.runtech.onlineshop.model.User;
import com.runtech.onlineshop.model.UserContract;
import com.runtech.onlineshop.model.WorkFlow;
import com.runtech.onlineshop.security.AdminImpl;
import com.runtech.onlineshop.security.AuthorizationImpl;
import com.runtech.web.action.StrutsAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.FlowableForm;
import com.runtech.web.form.ModelForm;
import com.runtech.web.model.Authorization;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class UserContractForm extends UserContract implements ModelForm, FlowableForm {

	private Integer[] ids;
	private String nextLocation;
	private File contract;
	private String contractFileName;
	private String actionType;
	private String contractFilePath;
	private String userFullName;
	private List logs;


	public UserContractForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserContractForm(Object form) throws ModelException {
		this.copyFrom(form);
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		StrutsAction action = context.getAction();
		if (Constant.ACTION_SAVE_ALL.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			UserContract userContract = (UserContract)modelHome.findById(new UserContract(), this.getId());
			if(userContract==null){
				userContract = new UserContract();
				userContract.setUser(this.getUser());
			}
			userContract.setStatus(Constant.STATUS_DRAFT);
			try {
				userContract.setAdmin(this.getAdmin());
				userContract.setContractType(this.getContractType());
				userContract.setSignTime(this.getSignTime());
				userContract.setStartTime(this.getStartTime());
				userContract.setEndTime(this.getEndTime());
				userContract.setUpdateTime(new Date());
				userContract.setUpdater((Admin) context.getUser());
				userContract.setContractContent(this.getContractContent());
				if(this.contract!=null||(this.contractFilePath!=null&&!this.contractFilePath.trim().equals(""))){
				    FileInputStream fin;
				    File file = this.contract;
				    String fileName = this.contractFileName;
				    if(this.contractFilePath!=null&&!this.contractFilePath.trim().equals("")){
				    	file = context.getFile(this.contractFilePath);
				    	fileName = file.getName();
				    }
					userContract.setContractFile(fileName);
					try {
						fin = new FileInputStream(file);
						Blob blob = Hibernate.createBlob(fin);
						userContract.setFile(blob);
					} catch (FileNotFoundException e) {
						context.setError(e.getMessage());
					} catch (IOException e) {
						context.setError(e.getMessage());
					}
				}
				modelHome.beginTransaction();
				modelHome.saveOrUpdate(userContract);
				modelHome.commit();

				finish(context);
			} catch (Exception e) {
				modelHome.rollback();
				throw new ModelException(e);
			}
		}else if (Constant.ACTION_DOWNLOAD.equals(this.getActionType())){
			ModelHome modelHome = new ModelHome();
			UserContract userContract = (UserContract)modelHome.findById(new UserContract(), this.getId());
			if(userContract!=null){
				
				Blob contractFile = userContract.getFile();
				String contractFileName = userContract.getContractFile();
				if(contractFile!=null){
					context.outputBlob(contractFile, contractFileName);
				}
			}
		}
		if(this.nextLocation!=null){
			context.setResult(context.getAction().getResultCustom());
			context.setNextLocation(this.nextLocation);
		}
	}

	public void clearId() {
		// TODO Auto-generated method stub

	}

	public void copyFrom(Object model) throws ModelException {
		try {
			UserContract realModel = (UserContract) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public void finish(RuntechContext context) {
		if(this.nextLocation!=null){
			context.setResult(context.getAction().getResultCustom());
			context.setNextLocation(this.nextLocation);
		}
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
			UserContract model = new UserContract();
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
		if(this.getId()!=null){
			UserContract originalUserContract = (UserContract) modelHome.findById(new UserContract(), this.getId());
			status = originalUserContract.getStatus();
//			if(Constant.STATUS_APPROVED.equals(status)){
//				context.setErrorByKey("error.status.noUpdate");
//				return false;
//			}
			Date signTime = originalUserContract.getSignTime();
			if(signTime!=null){
				this.setSignTime(signTime);
			}
		}
		this.setStatus(status);

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
	
	public List<UserContractForm> getSearchResult(Pager pager, Integer userId, RuntechContext context) throws ModelException
	{
		List<UserContractForm> list = new ArrayList<UserContractForm>();
		ModelHome modelHome = new ModelHome();
		String queryString = "from UserContract where status !='deleted' ";
		if(this.getUserFullName()!=null&&!this.getUserFullName().trim().equals("")){
			queryString += " and user.fullName like \"%"+this.getUserFullName()+"%\"";
		}
		if(userId!=null){
			queryString += " and user.id = "+userId;
		}
		if(!context.getUser().getMenu().getActiveMenu().getAuthorization().isSupervisable()){
			queryString += " and ( admin.id = "+context.getUser().getId()+" or user.admin.id = "+context.getUser().getId()+")";
		}
		queryString += " order by updateTime desc, signTime desc";
		pager.setTotalSize(modelHome.getCount(queryString));
		List<Object> tempList = modelHome.executeQuery(queryString, pager.getStartNumber(), pager.getPageSize());
		for (Object object : tempList) {
			UserContractForm commodityForm = new UserContractForm(object);
			list.add(commodityForm);
		}
		return list;
	}
	
	private String getUserFullName() {
		// TODO Auto-generated method stub
		return this.userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public void setSignTimeString(String timeString) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date date = formatter.parse(timeString);
		this.setSignTime(date);
	}
	
	public void setStartTimeString(String timeString) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date date = formatter.parse(timeString);
		this.setStartTime(date);
	}	
	
	public void setEndTimeString(String timeString) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date date = formatter.parse(timeString);
		this.setEndTime(date);
	}	
	
	public String getSignTimeString(){
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		return formatter.format(this.getSignTime());
	}
	
	public String getStartTimeString(){
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		return formatter.format(this.getStartTime());
	}
	
	public String getEndTimeString(){
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		return formatter.format(this.getEndTime());
	}
	
	public Integer getServiceAdminId(){
		if(this.getAdmin()!=null){
			return this.getAdmin().getId();
		}else{
			return null;
		}
	}
	

	public void setContract(File file) {
		this.contract = file;
	}

	public void setContractFileName(String fileName) {
		this.contractFileName = fileName;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public void setContractFilePath(String contractFilePath) {
		this.contractFilePath = contractFilePath;
	}

	public com.runtech.web.model.User getFlowSubmitter() throws ModelException {
		return new AdminImpl(super.getAdmin());
	}

	public String getDocumentType() {
		return "UserContract";
	}

	public void setFlowLogs(List logs) {
		this.logs = logs;
	}

	public List getFlowLogs() {
		return this.logs;
	}

	public Authorization getAuthorization(RuntechContext context) {
		return WorkFlowForm.getAuthorization(this,context);
	}

}
