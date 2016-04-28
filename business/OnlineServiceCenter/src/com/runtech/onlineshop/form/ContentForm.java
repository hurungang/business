package com.runtech.onlineshop.form;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.CommodityCategory;
import com.runtech.onlineshop.model.Content;
import com.runtech.onlineshop.model.LabelContent;
import com.runtech.onlineshop.model.LabelContentId;
import com.runtech.onlineshop.model.LabelWord;
import com.runtech.onlineshop.model.SiteModule;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class ContentForm extends Content implements ModelForm {

	private Integer commodityCategoryId;
	private Integer[] ids;
	private File picture;
	private String pictureFileName;
	private String pictureFileType;
	private String keywords;
	private Integer siteModuleId;
	
	public ContentForm() {
		super();
	}

	public ContentForm(Content contentExample) throws ModelException {
		this.copyFrom(contentExample);
	}

	public void copyFrom(Object model) throws ModelException {
		try {
			Content realModel = (Content) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return Order.desc("updateTime");
	}

	public Object getModel() throws ModelException {
		try {
			Content model = new Content();
			PropertyUtils.copyProperties(model,this);
			return model;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		this.setUpdater(context.getUser().getId());
		this.setUpdateTime(new Date());
		this.setStatus(Constant.STATUS_DRAFT);
		ModelHome modelHome = new ModelHome();
		CommodityCategory commodityCategory = (CommodityCategory) modelHome.findById(new CommodityCategory(), this.commodityCategoryId);
		this.setCommodityCategory(commodityCategory);
		SiteModule siteModule = (SiteModule) modelHome.findById(new SiteModule(), this.siteModuleId);
		this.setSiteModule(siteModule);
		if(this.picture!=null){
			String filePath = context.saveFile(this.picture,this.pictureFileName,false);
			this.setPicturePath(filePath);
		}
		return true;
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
		if(super.getId()!=null){
			return super.getId().toString();
		}else{
			return null;
		}
	}

	public Integer getCommodityCategoryId() {
		if(this.getCommodityCategory()!=null){
			return this.getCommodityCategory().getId();
		}else{
			return null;
		}
	}

	public void setCommodityCategoryId(Integer commodityCategoryId) {
		this.commodityCategoryId = commodityCategoryId;
	}

	public Integer getSiteModuleId() {
		if(this.getSiteModule()!=null){
			return this.getSiteModule().getId();
		}else{
			return null;
		}
	}

	public void setSiteModuleId(Integer siteModuleId) {
		this.siteModuleId = siteModuleId;
	}	
	public void clearId() {
		this.setId(null);
	}

	public Integer[] getIds() {
		return this.ids;
	}

	public void action(RuntechContext context) throws ModelException {
		ModelHome modelHome = new ModelHome();
		try {
			modelHome.beginTransaction();
			PageAction action = (PageAction) context.getAction();
			if (action.getActionType().equals(Constant.ACTION_APPROVE))
			{
				Content content = (Content)modelHome.findById(new Content(), this.getId());
				content.setStatus(Constant.STATUS_APPROVED);
				modelHome.merge(content);
			}else if(action.getActionType().equals(Constant.ACTION_SAVE_ALL)){
				Content originalContent = (Content) modelHome.findById(new Content(), this.getId());
				prepare(context);
				if(originalContent!=null){
					for (LabelContent labelContent : originalContent.getLabelContents()) {
						modelHome.delete(labelContent);
					}
				}
				Content content = (Content) this.getModel();
				content = (Content) modelHome.merge(content);
				if(this.keywords!=null){
					String[] strings = keywords.split(" ");
					for (String string : strings) {
						LabelWord labelWord = new LabelWord();
						labelWord.setLabelName(string);
						labelWord.setUpdateTime(new Date());
						labelWord = (LabelWord) modelHome.merge(labelWord);
						
						LabelContent labelContent = new LabelContent();
						labelContent.setLabelWord(labelWord);
						labelContent.setContent(content);
						labelContent.setId(new LabelContentId(string,content.getId()));
						labelContent = (LabelContent) modelHome.merge(labelContent);
						content.getLabelContents().add(labelContent);
					}
				}
			}
			modelHome.commit();
			context.setResult(action.getResultSuccess());
		} catch (Exception e) {
			context.setError(e.getLocalizedMessage());
			modelHome.rollback();
		}
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}
	
	public void setPictureFileType(String pictureFileType) {
		this.pictureFileType = pictureFileType;
	}
	
	public List<ModelForm> getListByPositionAndType(Pager pager,String position, String type) throws ModelException{
		ModelHome modelHome = new ModelHome();
		Content contentExample = new  Content();
		contentExample.setPositionCode(position);
		contentExample.setTypeCode(type);
		Integer count = modelHome.getCount(contentExample);
		
		
		pager.setTotalSize(count);
		Integer startNumber = pager.getStartNumber();
		Integer fetchSize = pager.getPageSize();
		
		List<ModelForm> list = modelHome.listForm(new ContentForm(contentExample), getDefaultOrder(), startNumber, fetchSize);
		return list;
	}
	
	public List<ModelForm> getListByType(Pager pager, String type) throws ModelException{
		ModelHome modelHome = new ModelHome();
		Content contentExample = new  Content();
		contentExample.setTypeCode(type);
		Integer count = modelHome.getCount(contentExample);
		
		
		pager.setTotalSize(count);
		Integer startNumber = pager.getStartNumber();
		Integer fetchSize = pager.getPageSize();
		
		List<ModelForm> list = modelHome.listForm(new ContentForm(contentExample), getDefaultOrder(), startNumber, fetchSize);
		return list;
	}
	
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	
	public String getKeywords(){
		StringBuffer buffer = new StringBuffer();
		for (LabelContent labelCommodity : this.getLabelContents()) {
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
