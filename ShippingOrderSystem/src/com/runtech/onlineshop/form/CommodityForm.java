package com.runtech.onlineshop.form;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.google.gson.JsonObject;
import com.runtech.onlineshop.model.Area;
import com.runtech.onlineshop.model.Commodity;
import com.runtech.onlineshop.model.CommodityCategory;
import com.runtech.onlineshop.model.CommodityPicture;
import com.runtech.onlineshop.model.CommodityProvider;
import com.runtech.onlineshop.model.Commodity;
import com.runtech.onlineshop.model.CommodityPicture;
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
	private CommodityPictureForm uploadPicture = new CommodityPictureForm();
	JsonObject ajaxResult = new JsonObject();
	
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
		Commodity original = (Commodity) modelHome.findById(new Commodity(), this.getId());
		return prepare(context, modelHome,original);
	}

	private boolean prepare(RuntechContext context, ModelHome modelHome, Commodity original) {
		String defaultPicturePath;
		if(original!=null){
			defaultPicturePath = original.getPicturePath();
			this.setStatus(original.getStatus());
			this.setCommodityPictures(original.getCommodityPictures());
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
				String filePath = context.saveImage(this.picture,fileName,true);
				this.setPicturePath(filePath);
			}
		}else{
			this.setPicturePath(defaultPicturePath);
		}
		if(this.areaId!=null){
			this.setArea((Area)modelHome.findById(new Area(), this.areaId));
		}
		this.setCommodityCategory((CommodityCategory)modelHome.findById(new CommodityCategory(), this.categoryId));
		this.setCommodityProvider((CommodityProvider)modelHome.findById(new CommodityProvider(), this.providerId));
		if(this.getDescription()!=null){
			this.setDescription(context.extractAllImages(this.getDescription()));
		}
		if(this.getSpecification()!=null){
			this.setSpecification(context.extractAllImages(this.getSpecification()));
		}
		if(this.getInstruction()!=null){
			this.setInstruction(context.extractAllImages(this.getInstruction()));
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
			boolean reload = true;
			modelHome.beginTransaction();
			PageAction action = (PageAction) context.getAction();
			Commodity original = (Commodity) modelHome.findById(new Commodity(), this.getId());
			if(Constant.ACTION_SAVE_ALL.equals(action.getActionType())){
				prepare(context, modelHome, original);
				Commodity commodity = (Commodity) this.getModel();
				commodity = (Commodity) modelHome.merge(commodity);
			}else if(Constant.ACTION_DUPLICATE_ALL.equals(action.getActionType())){
				this.copyFrom(original);
				this.clearId();
				Commodity newCommodity = (Commodity) this.getModel();
				newCommodity = (Commodity) modelHome.merge(newCommodity);
				Set<CommodityPicture> commodityPictures = original.getCommodityPictures();
				for (CommodityPicture commodityPicture : commodityPictures) {
					CommodityPictureForm commodityPictureForm = new CommodityPictureForm(commodityPicture);
					commodityPictureForm.setCommodity(newCommodity);
					commodityPictureForm.clearId();
					CommodityPicture tempCommodityPicture = (CommodityPicture) modelHome.merge(commodityPictureForm.getModel());
					newCommodity.getCommodityPictures().add(tempCommodityPicture);
				}
				this.copyFrom(newCommodity);
				this.setUpdateTime(new Date());
				context.setResult(action.getResultDefault());
				reload = false;
			}
			else if (Constant.ACTION_SPECIAL_DELETE.equals(action.getActionType()))
			{
				if(this.getIds() == null)
				{
					original.setStatus(Constant.STATUS_DELETED);
					modelHome.save(original);
				}
				else
				{
					for(Serializable id: this.getIds())
					{
						Commodity modelResult = (Commodity) modelHome.findById(new Commodity(), id);
						modelResult.setStatus(Constant.STATUS_DELETED);
						modelHome.save(modelResult);
					}
				}
			}
			else if(action.getActionType().equals(Constant.ACTION_SAVE_PICTURE)){
				if(this.uploadPicture!=null&&this.uploadPicture.getPicture()!=null){
					this.uploadPicture.savePicture(context);
					this.uploadPicture.setCommodity(original);
					modelHome.save(this.uploadPicture.getModel());
				}
			}
			else if(action.getActionType().equals(Constant.ACTION_DELETE_PICTURE)){
				String[] parameters = context.getParameters("pictureId");
				for (int i = 0; i < parameters.length; i++) {
					String string = parameters[i];
					Integer pictureId = Integer.parseInt(string);
					modelHome.deleteById(new CommodityPicture(), pictureId);
				}
			}
			
			modelHome.commit();
			if(reload){
				this.reload();
			}
			ajaxResult.addProperty(Constant.AJAX_SUCCESS, true);
			if(context.getResult()==null){
				context.setResult(action.getResultSuccess());
			}
		} catch (Exception e) {
			context.setError(e.getLocalizedMessage());
			modelHome.rollback();
			ajaxResult.addProperty(Constant.AJAX_SUCCESS, false);
			ajaxResult.addProperty(Constant.AJAX_ERROR_MESSAGE, e.toString());
		}
		if(context.getAjax()){
			context.setResult(Constant.RESULT_AJAX);
		}
	}
	
	private void reload() throws ModelException {
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		Commodity original = (Commodity) modelHome.findById(new Commodity(), this.getId());
		this.copyFrom(original);
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

	public Integer getCategoryId() {
		return this.getCommodityCategory().getId();
	}

	public Integer getAreaId() {
		return this.getArea().getId();
	}
	 
	public Integer getProviderId() {
		return this.getCommodityProvider().getId();
	}
	public CommodityPictureForm getUploadPicture() {
		return uploadPicture;
	}

	public void setUploadPicture(CommodityPictureForm uploadPicture) {
		this.uploadPicture = uploadPicture;
	}

	public JsonObject getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(JsonObject ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public static Commodity getComodityByExternalId(String cellValue) throws ModelException {

		ModelHome modelHome = new ModelHome();
		Commodity commodity = new Commodity(); 
		commodity.setExternalId(cellValue);
		return (Commodity) modelHome.findByKeyExample(commodity);
	}
}
