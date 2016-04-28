package com.runtech.web.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.JDBCException;

import com.opensymphony.xwork2.ModelDriven;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.FilterDispatcher;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.model.Authorization;
import com.runtech.web.model.Menu;
import com.runtech.web.model.Merchant;
import com.runtech.web.model.User;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class PageAction extends StrutsAction implements ModelDriven<Object>,SessionAware,ServletRequestAware{

	private static final int PAGE_INDEX_DEFAULT = 1;

	protected final Logger exceptionLogger=Logger.getLogger(PageAction.class);
	
	private String pageCode;
	private String catalogCode;
	private String pagePath;
	private String actionType = Constant.ACTION_LIST;
	private ModelForm modelForm;
	private ModelForm searchModelForm;
	private String pageModel;
	private Menu menu;
	private Menu catalogMenu;
	private String formId;
	private Menu currentMenu;
	private Map session;

	private Merchant merchant;


	private RuntechContext context;


	public PageAction() {
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		this.context.setAction(this);
		this.context.setMerchant(this.merchant);
		if(currentMenu==null){
			this.addActionError(getText(Constant.ERROR_AUTHORIZATION));
			return this.getResultError();
		}
		Authorization authorization = currentMenu.getAuthorization();
		/**
		 * Prepare id for model
		 */
		if(this.getFormId()!=null){
			if(this.modelForm==null){
				this.modelForm = (ModelForm) this.getModel();
				this.context.setModel(this.modelForm);
			}
			this.modelForm.setFormId(this.formId);
		}

		/*
		 * 
		 */
		if(!Constant.ACTION_DEFAULT.equals(this.actionType)){
			this.clearErrorsAndMessages();
		}
		if(Constant.ACTION_CANCEL.equals(this.actionType)){
			return this.getResultCancel();
		}
		/**
		 * List all model
		 */
		else if(Constant.ACTION_DEFAULT.equals(this.actionType)||Constant.ACTION_LIST.equals(this.actionType)||Constant.ACTION_DUPLICATE.equals(this.actionType)){
			if(!authorization.isReadable()){
				return this.getResultUnauthorized();
			}
			if(currentMenu!=null){
				if(this.getFormId()!=null){
					ModelHome modelHome = new ModelHome();
					Object modelResult = modelHome.findById(this.modelForm.getModel(), this.modelForm.getId());
					if(modelResult!=null){
						this.modelForm.copyFrom(modelResult);
						if(Constant.ACTION_DUPLICATE.equals(this.actionType)){
							this.modelForm.clearId();
						}
					}
				}else{
//					this.modelForm = null;
				}
				return this.getResultDefault();
			}else{
				return this.getResultError();
			}
		}
		/**
		 * Save current model
		 */
		else if (Constant.ACTION_SAVE.equals(this.actionType)){
			if(!authorization.isWritable()){
				return this.getResultUnauthorized();
			}
			ModelHome modelHome = new ModelHome();
			try {
				if(this.modelForm.prepare(context)){
					
					modelHome.beginTransaction();
					Object model = modelHome.merge(this.modelForm.getModel());
					this.modelForm.copyFrom(model);
					modelHome.commit();
					this.modelForm.finish(context);
					if(context.getResult()!=null){
						return context.getResult();
					}
					this.addActionMessage(this.getSuccessMessage());
					return this.getResultSuccess();
				}else{
					return this.getResultFailure();
				}
			} catch (JDBCException e) {
				modelHome.rollback();
				String message = e.getMessage();
				if(e.getSQLException()!=null){
					message = e.getSQLException().getMessage();
				this.addActionError(message);
				return this.getResultFailure();
			}
			} catch (Exception e) {
				modelHome.rollback();
				this.addActionError(e.getMessage());
				return this.getResultFailure();
			}
		}
		/**
		 * Delete current model
		 */
		else if(Constant.ACTION_DELETE.equals(this.actionType)){
			if(!authorization.isExecutable()){
				return this.getResultUnauthorized();
			}
			if(this.getFormId()!=null&&this.modelForm!=null){
				ModelHome modelHome = new ModelHome();
				try{					
					modelHome.beginTransaction();
					if(this.modelForm.getIds() == null)
					{
						Object modelResult = modelHome.findById(this.modelForm.getModel(), this.modelForm.getId());
						if(modelResult!=null){
							modelHome.delete(modelResult);
						}
					}
					else
					{
						for(Serializable id: this.modelForm.getIds())
						{
							Object modelResult = modelHome.findById(this.modelForm.getModel(), id);
							if(modelResult!=null){
								modelHome.delete(modelResult);
							}
						}
					}
					modelHome.commit();
					this.modelForm.finish(context);
					this.addActionMessage(this.getSuccessMessage());
					if(context.getResult()!=null){
						return context.getResult();
					}
					return this.getResultSuccess();
				}catch(Exception e){
					modelHome.rollback();
					this.addActionError(e.getMessage());
					return this.getResultFailure();
				}
			}
		}
		else
		{
			if(!authorization.isExecutable()){
				return this.getResultUnauthorized();
			}
			try{					
				this.modelForm.action(context);
				if(this.getActionErrors().size()==0){
					this.addActionMessage(this.getSuccessMessage());
				}
				if(this.context.getResult()!=null){
					if(this.context.getResult().equals(Constant.RESULT_NULL)){
						return null;
					}else{
						return this.context.getResult();
					}
				}else{
					return this.getResultSuccess();
				}
			}catch(Exception e){
				this.addActionError(e.getMessage());
				return this.getResultFailure();
			}
		}
		
		return this.getResultDefault();
	}

	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		if(this.modelForm==null){
			try {
				this.modelForm = newPageModelInstance();
			} catch (ModelException e) {
				LOG.error(e);
			}
		}
		return this.modelForm;
	}

	private ModelForm newPageModelInstance() throws ModelException {
		ModelForm tempModel = null;
		try {
			if(this.pageModel!=null){
				tempModel = (ModelForm) Class.forName(this.pageModel).newInstance();
			}
		} catch (Exception e) {
			this.exceptionLogger.debug(e);
		}
		return tempModel;
	}

	public List<ModelForm> getModelList() throws ModelException{
		Integer startNumber = pager.getStartNumber();
		Integer fetchSize = this.pager.getPageSize();
		List<ModelForm> modelList = new ArrayList<ModelForm>();
		ModelHome modelHome = new ModelHome();
		ModelForm newPageModelInstance = (ModelForm) this.newPageModelInstance();
		if(newPageModelInstance!=null){
			modelList = modelHome.listForm(newPageModelInstance,newPageModelInstance.getDefaultOrder(),startNumber,fetchSize);
		}
		return modelList;
	}

	public Integer getModelListCount(){
		Integer count = 0;
		List<Object> modelList = new ArrayList<Object>();
		ModelHome modelHome = new ModelHome();
		ModelForm newPageModelInstance = null;
		try {
			newPageModelInstance = this.newPageModelInstance();
			if(newPageModelInstance!=null){
				count = modelHome.getCount(newPageModelInstance.getModel());
			}
		} catch (ModelException e) {
			LOG.error(e);
		}
		return count;
	}
	
	public Menu getMenu() {
		return menu;
	}

	public void setSession(Map sessionValue) {
		this.session = sessionValue;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionTypeValue) {
		this.actionType = actionTypeValue;
	}
	
	public String getPageCode() {
		return pageCode;
	}
	public void setPageCode(String pageCode) throws ModelException {
		this.pageCode = pageCode;
		
		try {
			User user = this.context.getUser();
			this.menu = user.getMenu();
			currentMenu = this.menu.setActiveMenu(this.pageCode);
			if (currentMenu != null) {
				this.catalogMenu = findCatalogMenu(this.currentMenu);
				this.pagePath = currentMenu.getPage().trim();
				this.pageModel = currentMenu.getPageModelType().trim();
			}else{
				throw new ModelException(getText(Constant.ERROR_AUTHORIZATION));
			}
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	private Menu findCatalogMenu(Menu menuByCode) throws ModelException {
		if(menuByCode.getParentMenu()==null||(menuByCode.getParentMenu()!=null&&menuByCode.getParentMenu().getCode().equals(Constant.CODE_MODULE_ROOT))){
			return menuByCode;
		}else{
			return findCatalogMenu(menuByCode.getParentMenu());
		}
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public Menu getCatalogMenu() {
		return catalogMenu;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public Menu getCurrentMenu() {
		return currentMenu;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formIdValue) {
		if(formIdValue!=null&&formIdValue.trim().length()!=0){
			this.formId = formIdValue;
		}
	}

	public User getUser() {
		if(context!=null){
			return context.getUser();
		}else{
			return null;
		}
	}

	public void setUser(User user) {
		if(context!=null){
			context.setUser(user);
		}
	}
	
	public RuntechContext getContext() {
		return context;
	}


	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Pager getPager() {
		this.pager.setTotalSize(this.getModelListCount());
		return this.pager;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.context = (RuntechContext) request.getAttribute(FilterDispatcher.RUNTECH_CONTEXT);
	}

	public void setModelForm(ModelForm modelForm) {
		this.modelForm = modelForm;
	}
	
	public String getNextLocation(){
		return this.context.getNextLocation();
	}

	public ModelForm getSearchModel() throws ModelException {
		if(this.searchModelForm==null)
			searchModelForm = newPageModelInstance();
		return searchModelForm;
	}

	public void setSearchModel(ModelForm searchModelForm) {
		this.searchModelForm = searchModelForm;
	}

	@Override
	public void addActionError(String anErrorMessage) {
		// TODO Auto-generated method stub
		if(anErrorMessage!=null){
			super.addActionError(anErrorMessage);
		}
	}

	@Override
	public void addActionMessage(String aMessage) {
		if(aMessage!=null){
			super.addActionMessage(aMessage);
		}
	}
	
	
}
