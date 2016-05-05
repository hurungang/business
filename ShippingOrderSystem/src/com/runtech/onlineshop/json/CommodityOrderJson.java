package com.runtech.onlineshop.json;

import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.CommodityOrder;
import com.runtech.util.BeanUtil;
import com.runtech.web.form.ModelJson;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class CommodityOrderJson extends CommodityOrder implements ModelJson {

	private Integer[] ids;
	
	public CommodityOrderJson() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CommodityOrderJson(Object model) throws ModelException {
		this.copyFrom(model);
	}
	
	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			CommodityOrder commodityOrder = (CommodityOrder) model;
			PropertyUtils.copyProperties(this, commodityOrder);
			this.setId(commodityOrder.getId());
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
			CommodityOrder model = new CommodityOrder();
			PropertyUtils.copyProperties(model,this);
			return model;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Map toMap(){
		try {
			return BeanUtil.toMap(this.getModel(),"user");
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

}
