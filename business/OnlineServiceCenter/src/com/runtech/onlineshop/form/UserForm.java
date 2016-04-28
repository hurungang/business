package com.runtech.onlineshop.form;

import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.runtime.parser.node.MathUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.Area;
import com.runtech.onlineshop.model.Commodity;
import com.runtech.onlineshop.model.User;
import com.runtech.onlineshop.security.AdminImpl;
import com.runtech.onlineshop.security.UserImpl;
import com.runtech.web.action.StrutsAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.FlowableForm;
import com.runtech.web.form.ModelForm;
import com.runtech.web.model.Authorization;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.runtime.SessionExpireException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class UserForm extends User implements ModelForm, FlowableForm {

	private static final String DEFAULT_IMG_USER = "/images/default/user.jpg";
	private Integer[] ids;
	private File picture;
	private String pictureFileName;
	private String actionType;
	private Integer areaId;
	private String newPassword;
	private String confirmPassword;
	private String oldPassword;
	private List logs;
	
	public UserForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserForm(Object model) throws ModelException
	{
		this.copyFrom(model);
	}
	
	public void copyFrom(Object model) throws ModelException {
		try {
			User realModel = (User) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Object getModel() throws ModelException {
		try {
			User pUser = new User();
			PropertyUtils.copyProperties(pUser,this);
			return pUser;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		String status = Constant.STATUS_DRAFT;
		String picPath = DEFAULT_IMG_USER;
		Date registerTime = new Date();
		Date lastMaintainTime = new Date();
		String password = RandomStringUtils.randomAlphanumeric(8);
		if(this.getId()!=null){
			User originalUser = (User) modelHome.findById(new User(), this.getId());
			registerTime = originalUser.getRegisterTime();
			lastMaintainTime = originalUser.getLastMaintainTime();
			picPath = originalUser.getPicturePath();
			password = originalUser.getPassword();
		}
		this.setPassword(password);
		this.setStatus(status);
		this.setRegisterTime(registerTime);
		this.setLastMaintainTime(lastMaintainTime);
		this.setUpdater((Admin) context.getUser());
		this.setUpdateTime(new Date());
		if(this.getMobile()!=null&&this.getMobile().equals("")){
			this.setMobile(null);
		}
		if(this.areaId!=null){
			this.setArea((Area)modelHome.findById(new Area(), this.areaId));
		}
		if(this.picture!=null){
			String filePath = context.saveFile(this.picture,this.pictureFileName,false);
			this.setPicturePath(filePath);
		}else{
			this.setPicturePath(picPath);
		}
		return true;
	}

	public Integer getAreaId() {
		if(this.getArea()!=null){
			return this.getArea().getId();
		}else{
			return null;
		}
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return Order.desc("id");
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
		return super.getId().toString();
	}
	
	public Integer[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}
	
	public void clearId() {
		this.setId(null);
	}

	public void setServiceAdminId(Integer serviceAdminId) {
		if(serviceAdminId!=null){
			ModelHome modelHome = new ModelHome();
			Admin admin = (Admin) modelHome.findById(new Admin(), serviceAdminId);
			this.setAdmin(admin);
		}
	}

	public Integer getServiceAdminId() {
		if(this.getAdmin()!=null){
			return this.getAdmin().getId();
		}else{
			return null;
		}
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		StrutsAction action = context.getAction();
		if (Constant.ACTION_APPROVE.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			User user = (User)modelHome.findById(new User(), this.getId());
			user.setStatus(Constant.STATUS_APPROVED);
			try {
				modelHome.beginTransaction();
				modelHome.merge(user);
				modelHome.commit();
			} catch (Exception e) {
				modelHome.rollback();
				throw new ModelException(e);
			}
		}else if (Constant.ACTION_SPECIAL_DELETE.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			User user = (User)modelHome.findById(new User(), this.getId());
			user.setStatus(Constant.STATUS_DELETED);
			try {
				modelHome.beginTransaction();
				modelHome.merge(user);
				modelHome.commit();
			} catch (Exception e) {
				modelHome.rollback();
				throw new ModelException(e);
			}
		}else if (Constant.ACTION_CHANGE_PASSWORD.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			User user = (User)modelHome.findById(new User(), this.getId());
			if(this.oldPassword!=null&this.oldPassword.equals(user.getPassword())){

				if(this.newPassword!=null&this.newPassword.equals(this.confirmPassword)){
	
					try {
						user.setPassword(this.newPassword);
						modelHome.beginTransaction();
						modelHome.merge(user);
						modelHome.commit();
					} catch (Exception e) {
						modelHome.rollback();
						throw new ModelException(e);
					}	
				}else{
					throw new ModelException(action.getText(Constant.CODE_ERROR_NEW_PASSWORD));
				}	
			}else{
				throw new ModelException(action.getText(Constant.CODE_ERROR_PASSWORD));
			}	
		}else if (Constant.ACTION_UPDATE_ACCOUNT.equals(this.getActionType())){

			ModelHome modelHome = new ModelHome();
			Integer userId = context.getUser().getId();
			if(userId!=null){
				User user = (User)modelHome.findById(new User(), userId);
				try {
					if(this.areaId!=null){
						this.setArea((Area)modelHome.findById(new Area(), this.areaId));
					}
					user.setAddress(this.getAddress());
					user.setAge(this.getAge());
					user.setArea(this.getArea());
					user.setContact(this.getContact());
					user.setContactPosition(this.getContactPosition());
					user.setCredentialNumber(this.getCredentialNumber());
					user.setCredentialType(this.getCredentialType());
					user.setEmail(this.getEmail());
					user.setFax(this.getFax());
					user.setFullName(this.getFullName());
					user.setMobile(this.getMobile());
					user.setPhone(this.getPhone());
					user.setPicturePath(this.getPicturePath());
					user.setRemark(this.getRemark());
					user.setSex(this.getSex());
					user.setWebsite(this.getWebsite());
					modelHome.beginTransaction();
					user = (User) modelHome.merge(user);
					UserImpl userImpl = new UserImpl(new UserForm(user));
					userImpl.init();
					context.setUser(userImpl);
					modelHome.commit();
				} catch (Exception e) {
					modelHome.rollback();
					throw new ModelException(e);
				}	
			}else{
				throw new SessionExpireException();
			}
		}
	}

	public List<UserForm> getAllUsers() throws ModelException
	{
		List<UserForm> list = new ArrayList<UserForm>();
		ModelHome modelHome = new ModelHome();
		User user = new User();
		List<Object> tempList = modelHome.findByExample(user);
		for (Object object : tempList) {
			UserForm userForm = new UserForm(object);
			list.add(userForm);
		}
		return list;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}
	private String getActionType() {
		// TODO Auto-generated method stub
		return this.actionType;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public List<UserForm> getSearchResult(Pager pager,RuntechContext context) throws ModelException
	{
		List<UserForm> list = new ArrayList<UserForm>();
		ModelHome modelHome = new ModelHome();
		String queryString = "from User where status !='deleted' ";
		if(this.getFullName()!=null&&!this.getFullName().trim().equals("")){
			queryString += " and fullName like \"%"+this.getFullName()+"%\"";
		}
		if(this.getLastMaintainTime()!=null){
			queryString += " and lastMaintainTime < \""+this.getLastMaintainTimeString()+"\"";
		}
		if(!context.getUser().getMenu().getActiveMenu().getAuthorization().isSupervisable()){
			queryString += " and admin.id = "+context.getUser().getId();
		}
		queryString += " order by updateTime desc";
		pager.setTotalSize(modelHome.getCount(queryString));
		List<Object> tempList = modelHome.executeQuery(queryString, pager.getStartNumber(), pager.getPageSize());
		for (Object object : tempList) {
			UserForm resultForm = new UserForm(object);
			list.add(resultForm);
		}
		return list;
	}
	
	public String getLastMaintainTimeString(){
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		return formatter.format(this.getLastMaintainTime());
	}

	public void setLastMaintainTimeString(String timeString) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date date = formatter.parse(timeString);
		this.setLastMaintainTime(date);

	}

	public com.runtech.web.model.User getFlowSubmitter() throws ModelException {
		return new AdminImpl(super.getAdmin());
	}
	
	public void setFlowLogs(List logs) {
		this.logs = logs;
	}

	public List getFlowLogs() {
		// TODO Auto-generated method stub
		return this.logs;
	}

	public String getDocumentType() {
		return "User";
	}

	public Authorization getAuthorization(RuntechContext context) {
		return WorkFlowForm.getAuthorization(this,context);
	}

}
