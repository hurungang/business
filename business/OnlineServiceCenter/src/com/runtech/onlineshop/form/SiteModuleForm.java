package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.PageTemplate;
import com.runtech.onlineshop.model.SiteModule;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class SiteModuleForm extends SiteModule
		implements ModelForm{
	
	private Integer parentSiteModuleId;
	private Integer pageTemplateId;
	
	public SiteModuleForm() {
		super();
	}
	
	public SiteModuleForm(Object model) throws ModelException {
		this.copyFrom(model);
	}

	public void copyFrom(Object model) throws ModelException{
		try {
			SiteModule realModel = (SiteModule) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}
	
	public Object getModel() throws ModelException {
		try {
			SiteModule realModel = new SiteModule();
			PropertyUtils.copyProperties(realModel,this);
			return realModel;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context){
		ModelHome modelHome = new ModelHome();
		this.setSiteModule(getSiteModuleById(this.parentSiteModuleId));
		this.setPageTemplate((PageTemplate) modelHome.findById(new PageTemplate(), this.pageTemplateId));
		this.setUpdater(context.getUser().getId());
		this.setUpdateTime(new Date());
		return true;
	}
	
	public Integer getParentSiteModuleId() {
		if(this.getSiteModule()!=null){
			this.parentSiteModuleId = this.getSiteModule().getId();
		}
		return this.parentSiteModuleId;
	}

	public void setParentSiteModuleId(Integer parentSiteModuleIdValue) {
		this.parentSiteModuleId = parentSiteModuleIdValue;
	}

	public Integer getPageTemplateId() {
		if(this.getPageTemplate()!=null){
			this.pageTemplateId = this.getPageTemplate().getId();
		}
		return this.pageTemplateId;
	}

	public void setPageTemplateId(Integer templateId) {
		this.pageTemplateId = templateId;
	}

	public List<SiteModuleForm> getAllModules(String prefix) throws ModelException {
		List<SiteModuleForm> list = new ArrayList<SiteModuleForm>();
		SiteModuleForm root = getModuleFormByCode(Constant.CODE_SITE_MODULE_ROOT);
		if(root!=null){
			list.add(root);
			list.addAll(getAllModuleList(root,prefix));
		}
		return list;
	}

	private static List<SiteModuleForm> getAllModuleList(SiteModule root,String prefix) throws ModelException {
		List<SiteModuleForm> list = new ArrayList<SiteModuleForm>();
		Set<SiteModule> siteModules = root.getSiteModules();
		for (SiteModule siteModule : siteModules) {
			SiteModuleForm tempModule = new SiteModuleForm(siteModule);
			tempModule.setName(prefix+tempModule.getName());
			list.add(tempModule);
			list.addAll(getAllModuleList(siteModule,prefix+prefix));
		}
		return list;
	}
	
	public static List<SiteModuleForm> getAllModules(String rootCode, String prefix) throws ModelException {
		List<SiteModuleForm> list = new ArrayList<SiteModuleForm>();
		SiteModuleForm root = getModuleFormByCode(rootCode);
		if(root!=null){
			root.setName(prefix+root.getName());
			list.add(root);
			list.addAll(getAllModuleList(root, prefix+prefix));
		}
		return list;
	}

	public static SiteModuleForm getModuleFormByCode(String pageCodeValue) throws ModelException {
		if(pageCodeValue!=null){
			SiteModule siteModule = getModuleByCode(pageCodeValue);
			return new SiteModuleForm(siteModule);
		}
		else{
			return null;
		}
	}

	private static SiteModule getModuleByCode(String pageCodeValue)
			throws ModelException {
		ModelHome modelHome = new ModelHome();
		SiteModule siteModule = new SiteModule();
		siteModule.setCode(pageCodeValue);
		siteModule = (SiteModule)modelHome.findByKeyExample(siteModule);
		return siteModule;
	}
	
	private SiteModule getSiteModuleById(Integer id) {
		if(id!=null){
			ModelHome modelHome = new ModelHome();
			SiteModule siteModule = new SiteModule();
			siteModule = (SiteModule) modelHome.findById(siteModule, id);
			return siteModule;
		}
		else{
			return null;
		}
	}
	
	public Order getDefaultOrder() {
		return Order.desc("updateTime");
	}

	public void setFormId(String idValue) {
		if(idValue!=null){
			super.setId(Integer.parseInt(idValue));
		}
	}

	public String getFormId() {
		if(super.getId()!=null){
			return super.getId().toString();
		}else{
			return null;
		}
	}
	
	public void clearId() {
		this.setId(null);
	}

	public Serializable[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}

}
