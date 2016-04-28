package com.runtech.onlineshop.form;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.CommodityCategory;
import com.runtech.onlineshop.model.CommodityProvider;
import com.runtech.onlineshop.model.CommodityProvider;
import com.runtech.onlineshop.model.CommodityProvider;
import com.runtech.onlineshop.model.UserContract;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class CommodityProviderForm extends CommodityProvider implements
		ModelForm {

	private Integer[] ids;
	private File contract;
	private String contractFileName;
	private String actionType;
	private String contractFilePath;
	
	public CommodityProviderForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommodityProviderForm(Object commodityProvider) throws ModelException {
		this.copyFrom(commodityProvider);
	}
	
	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			CommodityProvider commodityProvider = (CommodityProvider) model;
			PropertyUtils.copyProperties(this, commodityProvider);
			this.setId(commodityProvider.getId());
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
			CommodityProvider commodityProvider = new CommodityProvider();
			PropertyUtils.copyProperties(commodityProvider,this);
			return commodityProvider;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		this.setAdmin((Admin) context.getUser());
		this.setUpdateTime(new Date());
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

	public List<CommodityProviderForm> getAllCommodityProvider() throws ModelException
	{
		List<CommodityProviderForm> list = new ArrayList<CommodityProviderForm>();
		ModelHome modelHome = new ModelHome();
		CommodityProvider commodityProvider = new CommodityProvider(); 
		List<Object> tempList = modelHome.findByExample(commodityProvider);
		for (Object object : tempList) {
			CommodityProviderForm commodityProviderForm = new CommodityProviderForm(object);
			list.add(commodityProviderForm);
		}
		return list;
	}

	public Integer[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}
	
	public void clearId() {
		this.setId(null);
	}

	public void action(RuntechContext context) throws ModelException {
		if (Constant.ACTION_SAVE_ALL.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			CommodityProvider commodityProvider = (CommodityProvider)modelHome.findById(new CommodityProvider(), this.getId());
			if(commodityProvider==null){
				commodityProvider = new CommodityProvider();
			}
			commodityProvider.setStatus(Constant.STATUS_DRAFT);
			try {
				commodityProvider.setName(this.getName());
				commodityProvider.setSupplyScope(this.getSupplyScope());
				commodityProvider.setPhone(this.getPhone());
				commodityProvider.setLinkman(this.getLinkman());
				commodityProvider.setLinkmanPhone(this.getLinkmanPhone());
				commodityProvider.setLinkmanMobile(this.getLinkmanMobile());
				commodityProvider.setAddress(this.getAddress());
				commodityProvider.setWebSite(this.getWebSite());
				commodityProvider.setQq(this.getQq());
				commodityProvider.setEmail(this.getEmail());
				commodityProvider.setDescription(this.getDescription());
				commodityProvider.setUpdateTime(new Date());
				commodityProvider.setAdmin((Admin) context.getUser());
				commodityProvider.setContractContent(this.getContractContent());
				if(this.contract!=null||(this.contractFilePath!=null&&!this.contractFilePath.trim().equals(""))){
				    FileInputStream fin;
				    File file = this.contract;
				    String fileName = this.contractFileName;
				    if(this.contractFilePath!=null&&!this.contractFilePath.trim().equals("")){
				    	file = context.getFile(this.contractFilePath);
				    	fileName = file.getName();
				    }
				    commodityProvider.setContractFileName(fileName);
					try {
						fin = new FileInputStream(file);
						Blob blob = Hibernate.createBlob(fin);
						commodityProvider.setContractFile(blob);
					} catch (FileNotFoundException e) {
						context.setError(e.getMessage());
					} catch (IOException e) {
						context.setError(e.getMessage());
					}
				}
				modelHome.beginTransaction();
				modelHome.saveOrUpdate(commodityProvider);
				modelHome.commit();

				finish(context);
			} catch (Exception e) {
				modelHome.rollback();
				throw new ModelException(e);
			}
		}else if (Constant.ACTION_DOWNLOAD.equals(this.getActionType())){
			ModelHome modelHome = new ModelHome();
			CommodityProvider provider = (CommodityProvider)modelHome.findById(new CommodityProvider(), this.getId());
			if(provider!=null){
				
				Blob contractFile = provider.getContractFile();
				String contractFileName = provider.getContractFileName();
				if(contractFile!=null){
					context.outputBlob(contractFile, contractFileName);
				}
			}
		}else if (Constant.ACTION_SPECIAL_DELETE.equals(this.getActionType()))
		{
			updateStatus(Constant.STATUS_DELETED);
		}
	}

	private void updateStatus(String status) {
		ModelHome modelHome = new ModelHome();
		CommodityProvider commodityProvider = (CommodityProvider)modelHome.findById(new CommodityProvider(), this.getId());
		commodityProvider.setStatus(status);
		try {
			modelHome.beginTransaction();
			modelHome.merge(commodityProvider);
			modelHome.commit();
		} catch (Exception e) {
			modelHome.rollback();
		}
	}
	
	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
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
	
	public List<CommodityProviderForm> getSearchResult(Pager pager,RuntechContext context) throws ModelException
	{
		List<CommodityProviderForm> list = new ArrayList<CommodityProviderForm>();
		ModelHome modelHome = new ModelHome();
		String queryString = "from CommodityProvider where status !='deleted' ";
		if(this.getName()!=null&&!this.getName().trim().equals("")){
			queryString += " and name like \"%"+this.getName()+"%\"";
		}
		if(this.getSupplyScope()!=null&&!this.getSupplyScope().trim().equals("")){
			queryString += " and supplyScope like \"%"+this.getSupplyScope()+"%\"";
		}
		if(!context.getUser().getMenu().getActiveMenu().getAuthorization().isSupervisable()){
			queryString += " and admin.id = "+context.getUser().getId();
		}
		queryString += " order by updateTime desc";
		pager.setTotalSize(modelHome.getCount(queryString));
		List<Object> tempList = modelHome.executeQuery(queryString, pager.getStartNumber(), pager.getPageSize());
		for (Object object : tempList) {
			CommodityProviderForm resultForm = new CommodityProviderForm(object);
			list.add(resultForm);
		}
		return list;
	}
}
