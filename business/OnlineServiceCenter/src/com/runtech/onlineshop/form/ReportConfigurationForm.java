package com.runtech.onlineshop.form;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.CommodityOrder;
import com.runtech.onlineshop.model.CommodityOrderItem;
import com.runtech.onlineshop.model.ReportConfiguration;
import com.runtech.onlineshop.model.SiteModule;
import com.runtech.onlineshop.model.User;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class ReportConfigurationForm extends ReportConfiguration implements ModelForm {

	private static final String KEYWORD_REPORT = Constant.ACTION_REPORT;
	private static final String KEYWORD_CONFIG = "config";
	private static final String KEYWORD_CONTEXT = "context";
	private String actionType;
	
	public ReportConfigurationForm() {
		super();
	}
	
	public ReportConfigurationForm(ReportConfiguration reportConfiguration) throws ModelException {
		this.copyFrom(reportConfiguration);
	}
	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		if(Constant.ACTION_REPORT.equals(this.getActionType())){
			if(this.getId()!=null){
				ModelHome modelHome = new ModelHome();
				
				ReportConfiguration reportConfiguration = (ReportConfiguration) modelHome.findById(new ReportConfiguration(),this.getId());
				ReportConfiguration reportConfigurationForm = new ReportConfigurationForm(reportConfiguration);
				
				StringWriter templateWriter = new StringWriter();
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put(KEYWORD_CONTEXT, context);
				velocityContext.put(KEYWORD_CONFIG, reportConfigurationForm);
				try {
					Velocity.evaluate(velocityContext, templateWriter, reportConfigurationForm.getName(), reportConfigurationForm.getQuery());
					String querySql = templateWriter.toString();
					List result = modelHome.executeQuery(querySql,null,null);
					velocityContext.put(KEYWORD_REPORT, result);
					Velocity.evaluate(velocityContext, context.getResponse().getWriter(), reportConfigurationForm.getName(), reportConfigurationForm.getTemplate());
				} catch (Exception e) {
					e.printStackTrace();
				}
				context.setResult(Constant.RESULT_NULL);
			}
		}
	}

	public void clearId() {
		// TODO Auto-generated method stub

	}
	private String getActionType() {
		// TODO Auto-generated method stub
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public void copyFrom(Object model) throws ModelException {
		try {
			ReportConfiguration realModel = (ReportConfiguration) model;
			PropertyUtils.copyProperties(this, realModel);
			this.setId(realModel.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}

	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFormId() {
		// TODO Auto-generated method stub
		return this.getId().toString();
	}


	public Serializable[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getModel() throws ModelException {
		try {
			ReportConfiguration realModel = new ReportConfiguration();
			PropertyUtils.copyProperties(realModel,this);
			return realModel;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		return true;
	}

	public void setFormId(String idValue) {
		if(idValue!=null){
			super.setId(Integer.parseInt(idValue));
		}
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}

	public List<CommodityOrderForm> consolidateOrder(List<CommodityOrderItem> orderItemList) throws ModelException{
		Map<String,CommodityOrderForm> orderMap = new HashMap<String,CommodityOrderForm>();
		for(CommodityOrderItem orderItem: orderItemList){
			CommodityOrder order = orderItem.getCommodityOrder();
			Integer id = order.getUser().getId();
			String contact = order.getContact();
			String contactPhone = order.getContactPhone();
			String key = id+contact.concat(contactPhone);
			CommodityOrderForm tempOrder = orderMap.get(key);
			if(tempOrder!=null){
				Set<CommodityOrderItem> commodityOrderItems = tempOrder.getCommodityOrderItems();
				if(commodityOrderItems!=null){
					commodityOrderItems.add(orderItem);
				}
			}else{
				tempOrder = new CommodityOrderForm();
				tempOrder.copyFrom(order);
				Set<CommodityOrderItem> commodityOrderItems = new HashSet<CommodityOrderItem>();
				commodityOrderItems.add(orderItem);
				tempOrder.setCommodityOrderItems(commodityOrderItems);
				orderMap.put(key,tempOrder);
			}
		}
		return new ArrayList<CommodityOrderForm>(orderMap.values());
	}

	public List<CommodityOrderForm> consolidateDeliveryOrder(List<CommodityOrderItem> orderItemList) throws ModelException{
		Map<String,CommodityOrderForm> orderMap = new HashMap<String,CommodityOrderForm>();
		for(CommodityOrderItem orderItem: orderItemList){
			CommodityOrder order = orderItem.getCommodityOrder();
			
			User deliveryToUser = order.getDeliveryToUser();
			if(deliveryToUser==null){
				deliveryToUser = order.getUser();
			}
			Integer id = deliveryToUser.getId();
			String contact = order.getContact();
			String contactPhone = order.getContactPhone();
			String key = id+contact.concat(contactPhone);
			CommodityOrderForm tempOrder = orderMap.get(key);
			if(tempOrder!=null){
				Set<CommodityOrderItem> commodityOrderItems = tempOrder.getCommodityOrderItems();
				if(commodityOrderItems!=null){
					commodityOrderItems.add(orderItem);
				}
			}else{
				tempOrder = new CommodityOrderForm();
				tempOrder.copyFrom(order);
				Set<CommodityOrderItem> commodityOrderItems = new HashSet<CommodityOrderItem>();
				commodityOrderItems.add(orderItem);
				tempOrder.setCommodityOrderItems(commodityOrderItems);
				orderMap.put(key,tempOrder);
			}
		}
		return new ArrayList<CommodityOrderForm>(orderMap.values());
	}

	public List<UserForm> consolidateUserOrder(List<CommodityOrder> orderList) throws ModelException{
		Map<Integer,UserForm> orderMap = new HashMap<Integer,UserForm>();
		for(CommodityOrder order: orderList){
			User user = order.getUser();
			if(user!=null){
				Integer key = user.getId();
				UserForm tempUser = orderMap.get(key);
				if(tempUser==null){
					tempUser = new UserForm();
					tempUser.copyFrom(user);
					Set<CommodityOrder> commodityOrders = new HashSet<CommodityOrder>();
					commodityOrders.add(order);
					tempUser.setCommodityOrders(commodityOrders);
					orderMap.put(key,tempUser);
				}else{
					Set<CommodityOrder> commodityOrders = tempUser.getCommodityOrders();
					if(commodityOrders!=null){
						commodityOrders.add(order);
					}
				}
			}
			
		}
		return new ArrayList<UserForm>(orderMap.values());
	}

	public List<UserForm> consolidateDeliveryUserOrder(List<CommodityOrder> orderList) throws ModelException{
		Map<Integer,UserForm> orderMap = new HashMap<Integer,UserForm>();
		for(CommodityOrder order: orderList){
			User user = order.getDeliveryToUser();
			if(user!=null){
				Integer key = user.getId();
				UserForm tempUser = orderMap.get(key);
				if(tempUser==null){
					tempUser = new UserForm();
					tempUser.copyFrom(user);
					Set<CommodityOrder> commodityOrders = new HashSet<CommodityOrder>();
					commodityOrders.add(order);
					tempUser.setDeliveryCommodityOrders(commodityOrders);
					orderMap.put(key,tempUser);
				}else{
					Set<CommodityOrder> commodityOrders = tempUser.getDeliveryCommodityOrders();
					if(commodityOrders!=null){
						commodityOrders.add(order);
					}
				}
			}
			
		}
		return new ArrayList<UserForm>(orderMap.values());
	}

	public List<UserForm> consolidateOrderByContact(List<CommodityOrder> orderList) throws ModelException{
		Map<String,UserForm> orderMap = new HashMap<String,UserForm>();
		for(CommodityOrder order: orderList){
			CommodityOrderForm orderForm = new CommodityOrderForm(order);
			User user = orderForm.getUser();
			if(user!=null){
				Integer id = user.getId();
				String contact = orderForm.getContact();
				String contactPhone = orderForm.getContactPhone();
				String key = id+contact.concat(contactPhone);
				UserForm tempUser = orderMap.get(key);
				if(tempUser==null){
					tempUser = new UserForm();
					tempUser.copyFrom(user);
					tempUser.setContact(contact);
					tempUser.setMobile(contactPhone);
					Set<CommodityOrder> commodityOrders = new HashSet<CommodityOrder>();
					commodityOrders.add(orderForm);
					tempUser.setCommodityOrders(commodityOrders);
					orderMap.put(key,tempUser);
				}else{
					Set<CommodityOrder> commodityOrders = tempUser.getCommodityOrders();
					if(commodityOrders!=null){
						commodityOrders.add(orderForm);
					}
				}
			}
			
		}
		return new ArrayList<UserForm>(orderMap.values());
	}
	
	public List<UserForm> consolidateUser(List<UserForm> userList) throws ModelException{
		Map<Integer,UserForm> userMap = new HashMap<Integer,UserForm>();
		for (UserForm user : userList) {
			User parentUser = user.getParentUser();
			Integer key;
			if(parentUser!=null){
				key = user.getId();
			}else{
				key = -1;
				parentUser = new User();
			}
			UserForm tempUser = userMap.get(key);
			if(tempUser==null){
				tempUser = new UserForm();
				tempUser.copyFrom(parentUser);
				Set<User> childUsers = new HashSet<User>();
				childUsers.add(user);
				tempUser.setSubUsers(childUsers);
				userMap.put(key,tempUser);
			}else{
				Set<User> childUsers = tempUser.getSubUsers();
				if(childUsers!=null){
					childUsers.add(user);
				}
			}
		}
		return new ArrayList<UserForm>(userMap.values());
	}
	
	public List query(String query, int start, int fetchSize){
		ModelHome modelHome = new ModelHome();
		Integer fetchSizeObject = fetchSize;
		if(fetchSize<0){
			fetchSizeObject = null;
		}
		List list = modelHome.executeQuery(query, start, fetchSizeObject);
		return list;
	}
}
