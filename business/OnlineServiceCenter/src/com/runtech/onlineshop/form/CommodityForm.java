package com.runtech.onlineshop.form;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Area;
import com.runtech.onlineshop.model.Commodity;
import com.runtech.onlineshop.model.CommodityCategory;
import com.runtech.onlineshop.model.CommodityDetail;
import com.runtech.onlineshop.model.CommodityPicture;
import com.runtech.onlineshop.model.CommodityProvider;
import com.runtech.onlineshop.model.LabelCommodity;
import com.runtech.onlineshop.model.LabelCommodityId;
import com.runtech.onlineshop.model.LabelWord;
import com.runtech.util.PictureUtil;
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
				String filePath = context.saveImage(this.picture,fileName,true);
				this.setPicturePath(filePath);
			}
		}else{
			this.setPicturePath(defaultPicturePath);
		}
		this.setUpdater(context.getUser().getId());
		this.setUpdateTime(new Date());
		if(this.areaId!=null){
			this.setArea((Area)modelHome.findById(new Area(), this.areaId));
		}
		this.setCommodityCategory((CommodityCategory)modelHome.findById(new CommodityCategory(), this.categoryId));
		this.setCommodityProvider((CommodityProvider)modelHome.findById(new CommodityProvider(), this.providerId));
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

	public Integer getAreaId() {
		if(this.getArea()!=null){
			return this.getArea().getId();
		}else{
			return null;
		}
	}
	
	public void setCategoryId(Integer categoryIdValue){
		this.categoryId = categoryIdValue;
	}

	public Integer getCategoryId() {
		return this.getCommodityCategory().getId();
	}
	
	public void setProviderId(Integer providerIdValue){
		this.providerId = providerIdValue;
	}

	public Integer getProviderId() {
		return this.getCommodityProvider().getId();
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
			if (Constant.ACTION_APPROVE.equals(action.getActionType()))
			{
				Commodity commodity = (Commodity)modelHome.findById(new Commodity(), this.getId());
				commodity.setStatus(Constant.STATUS_APPROVED);
				modelHome.merge(commodity);
			}else if (Constant.ACTION_SPECIAL_DELETE.equals(action.getActionType()))
			{
				Commodity commodity = (Commodity)modelHome.findById(new Commodity(), this.getId());
				commodity.setStatus(Constant.STATUS_DELETED);
				modelHome.merge(commodity);
			}else if (Constant.ACTION_WITHDRAW.equals(action.getActionType()))
			{
				Commodity commodity = (Commodity)modelHome.findById(new Commodity(), this.getId());
				commodity.setStatus(Constant.STATUS_WITHDRAWED);
				modelHome.merge(commodity);
			}else if(Constant.ACTION_SAVE_ALL.equals(action.getActionType())){
				Commodity originalCommodity = (Commodity) modelHome.findById(new Commodity(), this.getId());
				prepare(context, modelHome, originalCommodity);
				if(originalCommodity!=null){
					for (LabelCommodity labelCommodity : originalCommodity.getLabelCommodities()) {
						modelHome.delete(labelCommodity);
					}
				}
				Commodity commodity = (Commodity) this.getModel();
				commodity = (Commodity) modelHome.merge(commodity);
				if(this.keywords!=null){
					String[] strings = keywords.split(" ");
					for (String string : strings) {
						LabelWord labelWord = new LabelWord();
						labelWord.setLabelName(string);
						labelWord.setUpdateTime(new Date());
						labelWord = (LabelWord) modelHome.merge(labelWord);
						
						LabelCommodity labelCommodity = new LabelCommodity();
						labelCommodity.setLabelWord(labelWord);
						labelCommodity.setCommodity(commodity);
						labelCommodity.setId(new LabelCommodityId(string,commodity.getId()));
						labelCommodity = (LabelCommodity) modelHome.merge(labelCommodity);
						commodity.getLabelCommodities().add(labelCommodity);
					}
				}
			}else if(Constant.ACTION_DUPLICATE_ALL.equals(action.getActionType())){
				Commodity originalCommodity = (Commodity) modelHome.findById(new Commodity(), this.getId());
				this.copyFrom(originalCommodity);
				this.clearId();
				Commodity newCommodity = (Commodity) this.getModel();
				newCommodity = (Commodity) modelHome.merge(newCommodity);
				Set<CommodityPicture> commodityPictures = originalCommodity.getCommodityPictures();
				for (CommodityPicture commodityPicture : commodityPictures) {
					CommodityPictureForm commodityPictureForm = new CommodityPictureForm(commodityPicture);
					commodityPictureForm.setCommodity(newCommodity);
					commodityPictureForm.clearId();
					CommodityPicture tempCommodityPicture = (CommodityPicture) modelHome.merge(commodityPictureForm.getModel());
					newCommodity.getCommodityPictures().add(tempCommodityPicture);
				}
				Set<LabelCommodity> labelCommodities = originalCommodity.getLabelCommodities();
				for (LabelCommodity labelCommodity : labelCommodities) {
					LabelCommodity newLabelCommodity = new LabelCommodity();
					newLabelCommodity.setLabelWord(labelCommodity.getLabelWord());
					newLabelCommodity.setCommodity(newCommodity);
					newLabelCommodity.setId(new LabelCommodityId(labelCommodity.getLabelWord().getLabelName(),newCommodity.getId()));
					newLabelCommodity = (LabelCommodity) modelHome.merge(newLabelCommodity);
					newCommodity.getLabelCommodities().add(newLabelCommodity);
				}
				this.copyFrom(newCommodity);
				this.setUpdateTime(new Date());
				context.setResult(action.getResultDefault());
			}else if(ACTION_REFRESH_PREVIEW_PICTURE.equals(action.getActionType())){
				List<Commodity> commodityList = modelHome.executeQuery("from Commodity where status = '"+Constant.STATUS_APPROVED+"'",null,null);
				for (Commodity tempCommodity : commodityList) {
					String tempPicturePath = tempCommodity.getPicturePath();
					String[] split = tempPicturePath.split("\\/");
					
					String realPath = context.getServletContext().getContext(action.getUploadContext()).getRealPath(action.getUploadImagePath());
					String fileName = split[split.length-1];
					File pictureFile = new File(realPath+"/"+fileName);
					savePreivewPicture(context, fileName, pictureFile);
				}
			}
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
	
	public String getKeywords(){
		StringBuffer buffer = new StringBuffer();
		for (LabelCommodity labelCommodity : this.getLabelCommodities()) {
			String labelName = labelCommodity.getLabelWord().getLabelName();
			buffer.append(labelName);
			buffer.append(" ");
		}
		return buffer.toString();
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}
}
