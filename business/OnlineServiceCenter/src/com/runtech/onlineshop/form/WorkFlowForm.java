package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;
import org.hibernate.exception.JDBCConnectionException;

import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.AdminRole;
import com.runtech.onlineshop.model.WorkFlow;
import com.runtech.onlineshop.model.WorkFlowLog;
import com.runtech.onlineshop.security.AdminImpl;
import com.runtech.onlineshop.security.AuthorizationImpl;
import com.runtech.web.action.PageAction;
import com.runtech.web.action.StrutsAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.FlowableForm;
import com.runtech.web.form.ModelForm;
import com.runtech.web.model.Authorization;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class WorkFlowForm extends WorkFlow implements ModelForm {

	private String formId;
	private String actionType;
	private Object document;
	private String reviewComments;
	
	public WorkFlowForm() {
		super();
	}
	
	public WorkFlowForm(Object admin) throws ModelException {
		this.copyFrom(admin);
	}
	
	public void copyFrom(Object model) throws ModelException {
		try {
			WorkFlow realModel = (WorkFlow) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		StrutsAction action = context.getAction();
		WorkFlow workFlow =new WorkFlow();
		ModelHome modelHome = new ModelHome();
		modelHome.beginTransaction();
		try{
			workFlow.setDocumentStatus(this.getDocumentStatus());
			workFlow.setDocumentType(this.getDocumentType());
			AdminRole role = (AdminRole) modelHome.findById(new AdminRole(), context.getUser().getRole().getId());
			workFlow.setAdminRole(role);
			workFlow = (WorkFlow) modelHome.findByKeyExample(workFlow);
			if(workFlow==null){
				context.setErrorByKey("error.workflow.updateFailed");
				return;
			}
			FlowableForm form = null;
			String afterStatus = null;
			if (Constant.ACTION_APPROVE.equals(this.getActionType()))
			{
				int count = modelHome.executeUpdate("update "+this.getDocumentType()+" dt set dt.status= '"+workFlow.getReviewedStatus()+"' where dt.status = '"+this.getDocumentStatus()+"' and dt.id="+this.getFormId());
				afterStatus = workFlow.getReviewedStatus();
				if(count==0){
					context.setErrorByKey("error.workflow.updateFailed");
				}else{
					Object object = modelHome.executeQuery("from "+this.getDocumentType()+" where id="+this.getFormId());
					form = buildForm(object);
					sendWorkFlowMail(context, form);
					sendResultMail(context, form);
				}
			}else if (Constant.ACTION_REJECT.equals(this.getActionType()))
			{
				int count = modelHome.executeUpdate("update "+this.getDocumentType()+" dt set dt.status= '"+workFlow.getRejectedStatus()+"' where dt.status = '"+this.getDocumentStatus()+"' and dt.id="+this.getFormId());
				afterStatus = workFlow.getRejectedStatus();
				if(count==0){
					context.setErrorByKey("error.workflow.updateFailed");
				}else{
					Object object = modelHome.executeQuery("from "+this.getDocumentType()+" where id="+this.getFormId());
					form = buildForm(object);
					sendResultMail(context, form);
				}
			}
			//ADD LOG
			WorkFlowLog log = new WorkFlowLog();
			log.setAdminByReviewer((Admin) context.getUser());
			log.setAdminBySubmitter((Admin) form.getFlowSubmitter());
			log.setReviewTime(new Date());
			log.setPreviousStatus(workFlow.getDocumentStatus());
			log.setAfterStatus(afterStatus);
			log.setComments(this.getReviewComments());
			log.setDocumentType(this.getDocumentType());
			log.setDocumentId(form.getId());
			modelHome.save(log);
			modelHome.commit();
		}catch (JDBCConnectionException e) {
			modelHome.rollback();
			context.setError(e.getSQLException().getMessage());
		}
	}

	private String getReviewComments() {
		return this.reviewComments;
	}

	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}

	private FlowableForm buildForm(Object object) {
		try {
			Class<FlowableForm> formClass = (Class<FlowableForm>) Class.forName(this.getClass().getPackage().getName()+"."+object.getClass().getSimpleName()+"Form");
			if(formClass!=null){
				FlowableForm newInstance = formClass.newInstance();
				newInstance.copyFrom(object);
				List documentLogs = WorkFlowLogForm.getDocumentLogs(newInstance);
				newInstance.setFlowLogs(documentLogs);
				return newInstance;
			}else{
				return null;
			}
		} catch (ClassNotFoundException e) {
			return null;
		} catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		} catch (ModelException e) {
			return null;
		}
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
			WorkFlowForm model = new WorkFlowForm();
			PropertyUtils.copyProperties(model,this);
			return model;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Object getDocument() {
		return document;
	}

	public void setFormId(String idValue) {
		this.formId = idValue;
	}

	public String getFormId() {
		// TODO Auto-generated method stub
		return this.formId;
	}



	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
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

	public Integer getPendingReviewCount(){
		ModelHome modelHome = new ModelHome();
		String queryString = "from "+this.getDocumentType()+" where status = '"+this.getDocumentStatus()+"'";
		Integer count = (Integer) modelHome.getCount(queryString);
		return count;
	}

	public List getPendingReviewForms(Pager pager){
		ModelHome modelHome = new ModelHome();
		String queryString = "from "+this.getDocumentType()+" where status = '"+this.getDocumentStatus()+"'";
		return modelHome.executeQuery(queryString,pager.getStartNumber(),pager.getPageSize());
	}

	public boolean review(RuntechContext context, FlowableForm form){
		
		return false;
	}

	public static void sendWorkFlowMail(RuntechContext context, FlowableForm form) throws ModelException {
		PageAction action = (PageAction) context.getAction();

		//get submitter email
		AdminImpl admin = (AdminImpl) form.getFlowSubmitter();
		String ccEmail = admin.getEmail();
		
		StringBuffer emailsBuffer = new StringBuffer();
		
		//setup text params
		String documentType = form.getClass().getSuperclass().getSimpleName();
		String referenceId = documentType+"-"+form.getFormId();
		String[] subjectParams = new String[]{referenceId};
		String[] bodyParams = new String[]{admin.getFullName(),referenceId};
		
		//find workflow
		WorkFlow workFlowExample = new WorkFlow();
		workFlowExample.setDocumentType(documentType);
		workFlowExample.setDocumentStatus(form.getStatus());
		ModelHome modelHome = new ModelHome();
		List<Object> workFlowList = modelHome.findByExample(workFlowExample);
		
		for(Object workFlow:workFlowList){
			AdminRole adminRole = ((WorkFlow)workFlow).getAdminRole();
			if(adminRole!=null){
				Set<Admin> reviewAdmins = adminRole.getAdmins();
				for(Admin reviewAdmin: reviewAdmins){
					emailsBuffer = emailsBuffer.append(reviewAdmin.getEmail());
					emailsBuffer.append(",");
				}
				String emails = emailsBuffer.toString();
				if(admin!=null&&emails!=null){
					context.sendMail(emails, ccEmail, action.getText("text.workflow.mailSubject",subjectParams), action.getText("text.workflow.mailBody",bodyParams), null);
				}
			}
		}
	}

	public static void sendResultMail(RuntechContext context, FlowableForm form) throws ModelException {
		PageAction action = (PageAction) context.getAction();

		//get submitter email
		AdminImpl submitter = (AdminImpl) form.getFlowSubmitter();
		String email = submitter.getEmail();
		//

		AdminImpl reviewer = (AdminImpl) context.getUser();
		String ccEmail = reviewer.getEmail();
		
		
		//setup text params
		String documentType = form.getClass().getSuperclass().getSimpleName();
		String referenceId = documentType+"-"+form.getFormId();
		String[] subjectParams = new String[]{referenceId,form.getStatus(),};
		String[] bodyParams = new String[]{reviewer.getFullName(),form.getStatus(),referenceId,};
		
		//find workflow
		context.sendMail(email, ccEmail, action.getText("text.workflow.resultMailSubject",subjectParams), action.getText("text.workflow.resultMailBody",bodyParams), null);
	}
	public FlowableForm getDocument(String documentType,Serializable ID){
		ModelHome modelHome = new ModelHome();
		Object object = modelHome.executeQuery("from "+this.getDocumentType()+" where id="+this.getFormId());
		return buildForm(object); 
	}

	public static Authorization getAuthorization(FlowableForm form,
			RuntechContext context) {
		WorkFlow workFlow = new WorkFlow();
		AuthorizationImpl authorization = new AuthorizationImpl();
		Admin currentUser = (Admin) context.getUser();
		workFlow.setDocumentStatus(form.getStatus());
		workFlow.setDocumentType(form.getDocumentType());
		ModelHome modelHome = new ModelHome();
		AdminRole role;
		try {
			if(currentUser.getId().equals(form.getFlowSubmitter().getId())){
				authorization.setReadable(true);
				authorization.setWritable(true);
			}

			role = (AdminRole) modelHome.findById(new AdminRole(), context.getUser().getRole().getId());
			workFlow.setAdminRole(role);
			workFlow = (WorkFlow) modelHome.findByKeyExample(workFlow);
			
			if(workFlow!=null){
				authorization.setSupervisable(true);
			}
			
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return authorization;
	}

}
