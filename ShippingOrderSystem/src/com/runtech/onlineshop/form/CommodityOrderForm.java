package com.runtech.onlineshop.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ActionContext;
import com.runtech.onlineshop.model.Area;
import com.runtech.onlineshop.model.Commodity;
import com.runtech.onlineshop.model.CommodityOrder;
import com.runtech.onlineshop.model.CommodityOrderItem;
import com.runtech.onlineshop.model.User;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.runtime.RuntechRuntimeException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class CommodityOrderForm extends CommodityOrder implements ModelForm{

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Integer areaId;
	private Integer[] ids;
	private String startDate;
	private String endDate;
	private String userName;
	private String nextLocation;
	private Integer promotionId;
	private Integer orderId;


	public CommodityOrderForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CommodityOrderForm(Object model) throws ModelException
	{
		this.copyFrom(model);
	}
	
	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		CommodityOrder commodityOrder = null;

		try {
			commodityOrder = (CommodityOrder)modelHome.findById(new CommodityOrder(), this.getId());
			modelHome.beginTransaction();
			PageAction action = (PageAction) context.getAction();
			//Order process for external agent
			if(Constant.ACTION_AGENT_ORDER.equals(action.getActionType())){
				this.setAreaId(this.areaId);
				if(commodityOrder==null){
					commodityOrder = (CommodityOrder) this.getModel();
					Date now = new Date();
					commodityOrder.setDealTime(now);
					commodityOrder.setUpdateTime(now);
					commodityOrder.setStatus(Constant.STATUS_MANUALED);
					commodityOrder = (CommodityOrder) modelHome.merge(commodityOrder);
					commodityOrder.setUser((User) context.getUser());

				}else{
					commodityOrder.setDeliveryAddress(this.getDeliveryAddress());
					commodityOrder.setContact(this.getContact());
					commodityOrder.setContactPhone(this.getContactPhone());
					commodityOrder.setQuickRequest(this.getQuickRequest());
					commodityOrder.setMemo(this.getMemo());
				}	
				//save order items
				String[] parameters = context.getParameters("commodityId");
				//remove deleted items
				Set<CommodityOrderItem> commodityOrderItems = commodityOrder.getCommodityOrderItems();
				Object[] commodityOrderItemArray = commodityOrderItems.toArray();
				int tempListSize = commodityOrderItems.size();
				for (int i = 0; i < tempListSize; i++) {
					CommodityOrderItem currentOrderItem = (CommodityOrderItem) commodityOrderItemArray[i];
					Integer tempCommodityId = currentOrderItem.getCommodity().getId();
					if(!ArrayUtils.contains(parameters,tempCommodityId.toString())){
						modelHome.deleteById(currentOrderItem, currentOrderItem.getId());
					}
				}
				if(parameters!=null){
					//save all other items
					for (String commodityId : parameters) {
						CommodityOrderItem tempOrderItem = new CommodityOrderItem();
						Commodity tempCommodity = new Commodity();
						tempCommodity = (Commodity) modelHome.findById(tempCommodity, Integer.parseInt(commodityId));
						if(Constant.STATUS_DELETED.equalsIgnoreCase(tempCommodity.getStatus())){
							throw new RuntechRuntimeException(tempCommodity.getName()+" - 该商品已下架,订单无法修改，请删除该商品后重试");
						}else{
							tempOrderItem.setCommodity(tempCommodity);
							List<Object> findByExample = modelHome.findByExample(tempOrderItem);
							if(!findByExample.isEmpty()){
								tempOrderItem = (CommodityOrderItem) findByExample.get(0);
							}else{
								commodityOrderItems.add(tempOrderItem);
							}
							String paraCommodityNumber = context.getParameter("commodityNumber_"+commodityId);
							tempOrderItem.setCommodityNumber(Integer.parseInt(paraCommodityNumber));
							tempOrderItem.setRemark(context.getParameter("remark_"+commodityId));
							tempOrderItem = (CommodityOrderItem) modelHome.merge(tempOrderItem);
						}
					}
				}
			}
			modelHome.commit();
			this.clearId();
		} catch (Exception e) {
			context.getAction().addActionError(e.toString());
			modelHome.rollback();
			this.copyFrom(commodityOrder);
		}
		
	}

	public String getNextLocation() {
		return nextLocation;
	}

	public void setNextLocation(String nextLocation) {
		this.nextLocation = nextLocation;
	}
	
	
	public void clearId() {
		// TODO Auto-generated method stub
		this.setId(null);
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

	public String getFormId() {
		// TODO Auto-generated method stub
		if(super.getId()!=null){
			return super.getId().toString();
		}else{
			return null;
		}
	}

	public Integer[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}

	public Object getModel() throws ModelException {
		// TODO Auto-generated method stub
		try {
			CommodityOrder commodityOrder = new CommodityOrder();
			PropertyUtils.copyProperties(commodityOrder,this);
			return commodityOrder;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		this.setStatus(Constant.STATUS_PENDING);
		this.setDealTime(new Date());
		this.setDeliveryTime(new Date(0));
		this.setUpdateTime(new Date());
		return true;
	}

	public void setFormId(String idValue) {
		// TODO Auto-generated method stub
		String splitValues[];
		if(idValue!=null){
			splitValues = idValue.split(Constant.SYMBOL_COMMA_SPLIT);
			if(splitValues.length == 1)
			{
				ids = new Integer[]{Integer.parseInt(idValue)};
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
	public Integer getListCountByStatus(String status) throws ModelException
	{
		CommodityOrder commodityOrder = new CommodityOrder();
		commodityOrder.setStatus(status);
		ModelHome modelHome = new ModelHome();
		Integer count = modelHome.getCount(commodityOrder);
		return count;
	}
	public List<CommodityOrderForm> getUserOrderList(Pager pager, String[] status,String[] type, RuntechContext context) throws ModelException
	{
		return getOrderList(pager,status,type,context.getUser().getId());
	}
	
	public List<CommodityOrderForm> getOrderList(Pager pager, String[] status,String[] type, Integer userId) throws ModelException
	{
		List<CommodityOrderForm> list = new ArrayList<CommodityOrderForm>();
		ModelHome modelHome = new ModelHome();
		String queryString = "select co from CommodityOrder co where 1=1 ";
		if(status!=null){
			queryString += "and co.status in(";
			int i=0;
			for (; i < status.length-1; i++) {
				queryString += "'"+status[i]+"',";
			}
			queryString += "'"+status[i]+"')";
		}
		if(type!=null){
			queryString += "and co.orderType in(";
			int i=0;
			for (; i < type.length-1; i++) {
				queryString += "'"+type[i]+"',";
			}
			queryString += "'"+type[i]+"')";
		}
		if(this.getFormId()!=null&&!this.getFormId().trim().equals("")){
			queryString += " and co.id like \"%"+this.getFormId()+"%\"";
		}
		if(userId!=null){
			queryString += " and co.user.id = "+userId.toString();
		}
		if(this.getContact()!=null&&!this.getContact().trim().equals("")){
			queryString += " and co.contact like \"%"+this.getContact()+"%\"";
		}
		if(this.getContactPhone()!=null&&!this.getContactPhone().trim().equals("")){
			queryString += " and co.contactPhone like \"%"+this.getContactPhone()+"%\"";
		}
		if(this.getStartDate()!=null&&!this.getStartDate().trim().equals("")){
			queryString += " and co.dealTime >= \""+this.getStartDate()+"\"";
		}
		if(this.getEndDate()!=null&&!this.getEndDate().trim().equals("")){
			queryString += " and co.dealTime <= \""+this.getEndDate()+"\"";
		}
		queryString += " order by co.updateTime desc";
		pager.setTotalSize(modelHome.getCount(queryString));
		List tempList = modelHome.executeQuery(queryString, pager.getStartNumber(), pager.getPageSize());
		for (Object object : tempList) {
			CommodityOrderForm commodityOrderForm = new CommodityOrderForm(object);
			list.add(commodityOrderForm);
		}
		return list;
	}
	
	public Integer getPromotionId() {
		return this.promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

		
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public List<CommodityOrderItemForm> getCommodityOrderItemForms() throws ModelException{
		List<CommodityOrderItemForm> list = new ArrayList<CommodityOrderItemForm>();
		Set<CommodityOrderItem> tempCommodityOrderItems = this.getCommodityOrderItems();
		for (CommodityOrderItem commodityOrderItem : tempCommodityOrderItems) {
			CommodityOrderItemForm commodityOrderItemForm = new CommodityOrderItemForm(commodityOrderItem);
			list.add(commodityOrderItemForm);
		}
		return list;
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}

	public void setOrderId(Integer id) {
		this.orderId = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

}
