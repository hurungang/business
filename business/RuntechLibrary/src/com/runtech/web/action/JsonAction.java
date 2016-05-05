package com.runtech.web.action;  
  
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ModelDriven;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.form.ModelJson;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;  
  
/** 
 * JSON测试 
 *  
 * @author Watson Xu 
 * @date 2012-8-4 下午06:21:01 
 */  
public class JsonAction extends StrutsAction implements ModelDriven<Object>,SessionAware,ServletRequestAware{  
      
	//Jason support
    private Map<String,Object> result; 

	private String objectId;
	private String objectType;
	private String actionType = Constant.ACTION_DEFAULT;
	private ModelJson modelJson;

	protected final Logger exceptionLogger=Logger.getLogger(JsonAction.class);

	private Order orderBy;
	
	public String json() {  

		// dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据  
        result = new HashMap<String, Object>();  
        try {

			if(this.modelJson==null){
				this.modelJson = (ModelJson) this.getModel();
				this.modelJson.setJsonId(this.objectId);
			}
        	if(Constant.ACTION_DEFAULT.equals(this.actionType)){
        		if(this.getObjectId()!=null){
				ModelHome modelHome = new ModelHome();
				Object modelResult = modelHome.findById(this.modelJson.getModel(), this.modelJson.getId());
				if(modelResult!=null){
					this.modelJson.copyFrom(modelResult);
					result.put("result", this.modelJson.toMap());
				}
			}
        		
        	}else if(Constant.ACTION_LIST.equals(this.actionType)){
        		result.put("result", this.getModelList());
        	}
	        // 放入一个是否操作成功的标识  
	        result.put("success", true);  
	        // 返回结果  
		} catch (Exception e) {
	        result.put("success", false);  
		}
        return SUCCESS;  
    }  
	
	public Map<String, Object> getResult() {  
		return result;  
	}  
	
	public Object getModel() {
		if(this.modelJson==null){
			try {
				this.modelJson = newPageModelInstance();
			} catch (ModelException e) {
				LOG.error(e.getMessage());
			}
		}
		return this.modelJson;
	}
	
	public List<Object> getModelList() throws ModelException{
		Integer startNumber = this.pager.getStartNumber();
		Integer fetchSize = this.pager.getPageSize();
		List<Object> modelList = new ArrayList<Object>();
		ModelHome modelHome = new ModelHome();
		Object newPageModelInstance = this.newPageModelInstance();
		if(newPageModelInstance!=null){
			modelList = modelHome.list(newPageModelInstance,orderBy,startNumber,fetchSize);
		}
		return modelList;
	}
	
	private ModelJson newPageModelInstance() throws ModelException {
		ModelJson tempModel = null;
		try {
			if(this.objectType!=null){
				tempModel = (ModelJson) Class.forName(this.objectType).newInstance();
			}
		} catch (Exception e) {
			this.exceptionLogger.debug(e);
		}
		return tempModel;
	}
	
	public String getObjectId() {
		return objectId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	
	public String getActionType() {
		return actionType;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Map<String, Object> getDataMap() {
		return result;
	}

	public Order getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Order orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

} 