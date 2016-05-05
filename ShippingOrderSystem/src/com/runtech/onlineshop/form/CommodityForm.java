package com.runtech.onlineshop.form;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ActionContext;
import com.runtech.onlineshop.model.Commodity;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class CommodityForm extends Commodity implements ModelForm {

	private static final String DEFAULT_IMG_COMMODITY = "/images/default/commodity.jpg";
	private static final String ACTION_REFRESH_PREVIEW_PICTURE = "refreshPreviewPicture";
	private Integer areaId;
	private Integer categoryId;
	private Integer providerId;
	private Integer[] ids;
	private File picture;
	private String pictureFileName;
	private String keywords;
	
	public CommodityForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CommodityForm(Object commodity) throws ModelException {
		this.copyFrom(commodity);
	}
	
	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			Commodity commodity = (Commodity) model;
			PropertyUtils.copyProperties(this, commodity);
			this.setId(commodity.getId());
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
			Commodity commodity = new Commodity();
			PropertyUtils.copyProperties(commodity,this);
			return commodity;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context){
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		Commodity originalCommodity = (Commodity) modelHome.findById(new Commodity(), this.getId());
		return prepare(context, modelHome,originalCommodity);
	}

	private boolean prepare(RuntechContext context, ModelHome modelHome, Commodity originalCommodity) {
		String defaultPicturePath;
		if(originalCommodity!=null){
			defaultPicturePath = originalCommodity.getPicturePath();
			this.setStatus(originalCommodity.getStatus());
		}else{
			defaultPicturePath = DEFAULT_IMG_COMMODITY;
//			this.setStatus(Constant.PENDING_STATUS);
			this.setStatus(Constant.STATUS_APPROVED);
		}
		if(this.picture!=null){
			int lastIndexOf = this.pictureFileName.lastIndexOf(".");
			if(lastIndexOf>=0){
				String fileSuffix = this.pictureFileName.substring(lastIndexOf);
				String fileName = System.currentTimeMillis()+fileSuffix;
				
				try {
					savePreivewPicture(context, fileName,this.picture);
				} catch (Exception e) {
					context.setError("保存预览图失败");
					e.printStackTrace();
					return false;
				}
				String filePath = context.saveFile(this.picture,fileName,true);
				this.setPicturePath(filePath);
			}
		}else{
			this.setPicturePath(defaultPicturePath);
		}
		this.setUpdater(context.getUser().getId());
		this.setUpdateTime(new Date());
		return true;
	}

	private boolean savePreivewPicture(RuntechContext context, String fileName, File pictureFile)
			throws Exception {
		try {
			context.zoom(pictureFile, 438, 329, fileName + ".438_329.jpg",true);
			context.zoom(pictureFile, 338, 252, fileName + ".338_252.jpg",true);
			context.zoom(pictureFile, 213, 159, fileName + ".213_159.jpg",true);
			context.zoom(pictureFile, 60, 45, fileName + ".60_45.jpg",true);
			//		context.zoom(this.picture,640,480,fileName+".640_480.jpg");
			//		context.zoom(this.picture,320,240,fileName+".320_240.jpg");
			//		context.zoom(this.picture,160,120,fileName+".160_120.jpg");
			//		context.zoom(this.picture,80,60,fileName+".80_60.jpg");
			return true;
		} catch (Exception e) {
			return false;
		}
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

	public void setAreaId(Integer areaIdValue){
		this.areaId = areaIdValue;
	}

	public void setCategoryId(Integer categoryIdValue){
		this.categoryId = categoryIdValue;
	}

	public void setProviderId(Integer providerIdValue){
		this.providerId = providerIdValue;
	}


	public void clearId() {
		this.setId(null);
	}

	public Integer[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}
	
	public void setPictureFileName(String fileName){
		this.pictureFileName = fileName;
	}
	
	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		try {
			modelHome.beginTransaction();
			PageAction action = (PageAction) context.getAction();

			modelHome.commit();
			if(context.getResult()==null){
				context.setResult(action.getResultSuccess());
			}
		} catch (Exception e) {
			context.setError(e.getLocalizedMessage());
			modelHome.rollback();
		}
	}
	
	public static List<CommodityForm> getAllCommodity() throws ModelException
	{
		List<CommodityForm> list = new ArrayList<CommodityForm>();
		ModelHome modelHome = new ModelHome();
		Commodity commodity = new Commodity(); 
		List<Object> tempList = modelHome.findByExample(commodity);
		for (Object object : tempList) {
			CommodityForm commodityForm = new CommodityForm(object);
			list.add(commodityForm);
		}
		return list;
	}

	public List<CommodityForm> getSearchResult(Pager pager) throws ModelException
	{
		List<CommodityForm> list = new ArrayList<CommodityForm>();
		ModelHome modelHome = new ModelHome();
		Commodity commodity = new Commodity(); 
		String queryString = "from Commodity where status !='deleted' ";
		if(this.getName()!=null){
			queryString += " and name like \"%"+this.getName()+"%\"";
		}
		queryString += " order by updateTime desc";
		pager.setTotalSize(modelHome.getCount(queryString));
		List<Object> tempList = modelHome.executeQuery(queryString, pager.getStartNumber(), pager.getPageSize());
		for (Object object : tempList) {
			CommodityForm commodityForm = new CommodityForm(object);
			list.add(commodityForm);
		}
		return list;
	}
	
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}
}
