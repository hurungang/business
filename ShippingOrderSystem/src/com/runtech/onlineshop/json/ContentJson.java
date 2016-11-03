package com.runtech.onlineshop.json;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Content;
import com.runtech.util.BeanUtil;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelJson;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class ContentJson extends Content implements ModelJson {

	private Integer[] ids;
	
	public ContentJson() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ContentJson(Object model) throws ModelException {
		this.copyFrom(model);
	}
	
	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			Content Content = (Content) model;
			PropertyUtils.copyProperties(this, Content);
			this.setId(Content.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return Order.desc("updateTime");
	}


	public Object getModel() throws ModelException {
		// TODO Auto-generated method stub
		try {
			Content model = new Content();
			PropertyUtils.copyProperties(model,this);
			return model;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Map toMap(){
		try {
			return BeanUtil.toMap(this.getModel());
		} catch (ModelException e) {
			return null;
		}
	}

	@Override
	public void setJsonId(String idValue) {
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

	@Override
	public List action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

}
