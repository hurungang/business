package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.Area;
import com.runtech.onlineshop.model.Commodity;
import com.runtech.onlineshop.model.CommodityCategory;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class CommodityCategoryForm extends CommodityCategory implements
		ModelForm {

	private Integer[] ids;
	private String actionType;
	
	public CommodityCategoryForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommodityCategoryForm(Object commodityCategory) throws ModelException {
		this.copyFrom(commodityCategory);
	}
	
	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			CommodityCategory commodityCategory = (CommodityCategory) model;
			PropertyUtils.copyProperties(this, commodityCategory);
			this.setId(commodityCategory.getId());
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
			CommodityCategory commodityCategory = new CommodityCategory();
			PropertyUtils.copyProperties(commodityCategory,this);
			return commodityCategory;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		this.setUpdater(context.getUser().getId());
		this.setUpdateTime(new Date());
		this.setStatus(Constant.STATUS_PENDING);
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

	public List<CommodityCategoryForm> getAllCommodityCategory(String rootCode, String prefix) throws ModelException
	{
		ModelHome modelHome = new ModelHome();
		CommodityCategory commodityCategory = new CommodityCategory(); 
		commodityCategory.setName(rootCode);
		commodityCategory.setStatus(Constant.STATUS_ENABLED);
		CommodityCategory rootCategory = (CommodityCategory) modelHome.findByKeyExample(commodityCategory);
		List<CommodityCategoryForm> list = getAllSubCategoryList(rootCategory,prefix);
		return list;
	}
	
	public static CommodityCategory getCategoryDescription(String code) throws ModelException{

		ModelHome modelHome = new ModelHome();
		CommodityCategory commodityCategory = new CommodityCategory(); 
		commodityCategory.setName(code);
		CommodityCategory category = (CommodityCategory) modelHome.findByKeyExample(commodityCategory);
		return category;
	}

	private List<CommodityCategoryForm> getAllSubCategoryList(
			CommodityCategory rootCategory, String prefix) throws ModelException {
		List<CommodityCategoryForm> list = new ArrayList<CommodityCategoryForm>();
		if(rootCategory!=null){
			CommodityCategoryForm commodityCategoryForm = new CommodityCategoryForm(rootCategory);
			commodityCategoryForm.setDescription(prefix+commodityCategoryForm.getDescription());
			list.add(commodityCategoryForm);

			ModelHome modelHome = new ModelHome();
			CommodityCategoryForm queryForm = new CommodityCategoryForm();
			queryForm.setParentId(rootCategory.getId());
			List<Object> subCommodityCategories = modelHome.findByExample(queryForm);
			
			for (Object element : subCommodityCategories) {
				CommodityCategory ca = (CommodityCategory) element;
				if(Constant.STATUS_ENABLED.equals(ca.getStatus())){
					list.addAll(getAllSubCategoryList(ca,prefix+prefix));
				}
			}
		}
		return list;
	}

	public Serializable[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}
	
	public void clearId() {
		this.setId(null);
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		if (Constant.ACTION_APPROVE.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			CommodityCategory commodityCategory = (CommodityCategory)modelHome.findById(new CommodityCategory(), this.getId());
			commodityCategory.setStatus(Constant.STATUS_ENABLED);
			try {
				modelHome.beginTransaction();
				modelHome.merge(commodityCategory);
				modelHome.commit();
			} catch (Exception e) {
				modelHome.rollback();
			}
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
