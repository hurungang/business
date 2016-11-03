package com.runtech.onlineshop.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Commodity;
import com.runtech.util.BeanUtil;
import com.runtech.web.action.JsonAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelJson;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class CommodityJson extends Commodity implements ModelJson {

	private Integer[] ids;
	
	public CommodityJson() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CommodityJson(Object commodity) throws ModelException {
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

		JsonAction action = (JsonAction) context.getAction();
		if(Constant.ACTION_LIST.equals(action.getActionType())){
			ModelHome modelHome = new ModelHome();
			Commodity queryExample = new Commodity();
			queryExample.setStatus(Constant.STATUS_APPROVED);
			List<Object> modelList = modelHome.findByExample(queryExample);
			List<Map> modelMapList = new ArrayList<Map>();
			for (Iterator iterator = modelList.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				Map<String, Object> map = BeanUtil.toMap(object);
				modelMapList.add(map);
			}
			return modelMapList;
		}
		return null;
	}

}
