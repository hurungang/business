package com.runtech.onlineshop.json;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.CommodityProvider;
import com.runtech.util.BeanUtil;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelJson;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class CommodityProviderJson extends CommodityProvider implements ModelJson {

	private Integer[] ids;
	
	public CommodityProviderJson() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CommodityProviderJson(Object model) throws ModelException {
		this.copyFrom(model);
	}
	
	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			CommodityProvider CommodityProvider = (CommodityProvider) model;
			PropertyUtils.copyProperties(this, CommodityProvider);
			this.setId(CommodityProvider.getId());
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
			CommodityProvider model = new CommodityProvider();
			PropertyUtils.copyProperties(model,this);
			return model;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Map toMap(){
		try {
			return BeanUtil.toMap(this.getModel(),"admin");
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
