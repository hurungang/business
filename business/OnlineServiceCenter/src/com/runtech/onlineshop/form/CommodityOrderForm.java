package com.runtech.onlineshop.form;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Area;
import com.runtech.onlineshop.model.Commodity;
import com.runtech.onlineshop.model.CommodityOrder;
import com.runtech.onlineshop.model.CommodityOrderItem;
import com.runtech.onlineshop.model.CommodityPayment;
import com.runtech.onlineshop.model.CommodityPromotion;
import com.runtech.onlineshop.model.PointConsumeLog;
import com.runtech.onlineshop.model.PointLog;
import com.runtech.onlineshop.model.SystemCode;
import com.runtech.onlineshop.model.User;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.model.Cart;
import com.runtech.web.model.CartItem;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.runtime.RuntechRuntimeException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class CommodityOrderForm extends CommodityOrder implements ModelForm,Cart,com.runtech.web.model.Order {

	private Integer userId;
	private Integer areaId;
	private Integer[] ids;
	private CommodityOrderItemForm tempOrderItem = new CommodityOrderItemForm();
	private BigDecimal totalPrice;
	private BigDecimal totalRealPrice;
	private Integer totalPoint;
	private String startDate;
	private String endDate;
	private String userName;
	private String nextLocation;
	private Integer promotionId;
	private Map<Integer, CommodityOrderItemForm> commodityOrderItemFormMap = new HashMap<Integer, CommodityOrderItemForm>();
	private Integer commodityId;
	private Integer[] commodityIds;
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
		CommodityOrderItem commodityOrderItem = null;

		try {
			commodityOrder = (CommodityOrder)modelHome.findById(new CommodityOrder(), this.getId());
			commodityOrderItem = (CommodityOrderItem) this.tempOrderItem.getModel();
			modelHome.beginTransaction();
			PageAction action = (PageAction) context.getAction();
			if (Constant.ACTION_ADD_CHILD.equals(action.getActionType()))
			{
				Commodity tempCommodity = new Commodity();
				tempCommodity = (Commodity) modelHome.findById(tempCommodity, this.commodityId);
				if(tempCommodity!=null){
					tempOrderItem.setCommodity(tempCommodity);
					tempOrderItem.setCommodityNumber(1);
					tempOrderItem.setDiscount(new BigDecimal(1));
					if(context.getUser()!=null){
						context.getUser().getCart().add(tempOrderItem);
					}
				}
			}else if(Constant.ACTION_SAVE_ALL.equals(action.getActionType()))
			{
				if(context.getUser()!=null){
					Cart cart = context.getUser().getCart();
					cart.save(context);
				}
				
			}
			else if(Constant.ACTION_DELETE_CHILD.equals(action.getActionType())){
				if(context.getUser()!=null){
					Cart cart = context.getUser().getCart();

					if(this.commodityIds!=null){
						for (int i = 0; i < this.commodityIds.length; i++) {
							cart.remove(this.commodityIds[i]);
						}
					}
				}
			}else if(Constant.ACTION_CONFIRM_ORDER.equals(action.getActionType())){
				if(context.getUser()!=null){
					Cart cart = context.getUser().getCart();
					if(this.getId()!=null){
						CommodityOrder commodityOrderResult = (CommodityOrder) modelHome.findById(new CommodityOrder(), this.getId());
						this.copyFrom(commodityOrderResult);
						cart = this;
					}
					if(cart.isEmpty()){
						context.setError("购物车是空的");
					}
				
					cart.setStatus(Constant.STATUS_UNCONFIRMED);
					cart.save(context);
					com.runtech.web.model.Order saveOrder = cart.saveQuickOrder(context, modelHome);
					context.getUser().clearCart();
				}
			}
			else if (Constant.ACTION_SAVE_CHILD.equals(action.getActionType())||Constant.ACTION_FINISH.equals(action.getActionType())||Constant.ACTION_SAVE_ALL.equals(action.getActionType()))
			{
				this.setArea((Area)modelHome.findById(new Area(), this.areaId));
				if(commodityOrder==null){
					commodityOrder = (CommodityOrder) this.getModel();
					Date now = new Date();
					commodityOrder.setDealTime(now);
					commodityOrder.setDeliveryTime(now);
					commodityOrder.setUpdateTime(now);
					commodityOrder.setStatus(Constant.STATUS_MANUALED);
					commodityOrder = (CommodityOrder) modelHome.merge(commodityOrder);
				}else{
					commodityOrder.setArea(this.getArea());
					commodityOrder.setDeliveryAddress(this.getDeliveryAddress());
					commodityOrder.setPlanDeliveryTime(this.getPlanDeliveryTime());
					commodityOrder.setDeliveryman(this.getDeliveryman());
					commodityOrder.setOrderType(this.getOrderType());
					commodityOrder.setPayType(this.getPayType());
					commodityOrder.setFreight(this.getFreight());
					commodityOrder.setContact(this.getContact());
					commodityOrder.setContactPhone(this.getContactPhone());
					commodityOrder.setComment(this.getComment());
					commodityOrder.setMemo(this.getMemo());
				}					
				if(Constant.ACTION_SAVE_CHILD.equals(action.getActionType())){
					commodityOrderItem.setStatus(Constant.STATUS_PENDING);
					if(commodityOrderItem.getId()!=null){
						CommodityOrderItem oldCommodityOrderItem = (CommodityOrderItem) modelHome.findById(commodityOrderItem, commodityOrderItem.getId());
						commodityOrderItem.setStatus(oldCommodityOrderItem.getStatus());
						commodityOrderItem.setPoint(oldCommodityOrderItem.getPoint());
					}
					commodityOrderItem.setCommodityOrder(commodityOrder);
					BigDecimal discount = commodityOrderItem.getRealPrice().divide(commodityOrderItem.getPrice(),2,BigDecimal.ROUND_HALF_UP);
					commodityOrderItem.setDiscount(discount);
					if(commodityOrderItem!=null){
						commodityOrderItem = (CommodityOrderItem) modelHome.merge(commodityOrderItem);
					}
					commodityOrder.getCommodityOrderItems().add(commodityOrderItem);
					this.copyFrom(commodityOrder);
				}
				if(Constant.ACTION_FINISH.equals(action.getActionType()))
				{
					context.setResult(action.getResultSuccess());
				}else
				{
					context.setResult(action.getResultCustom());
					context.setNextLocation(this.nextLocation);
				}
			}
			else if(Constant.ACTION_DELETE_CHILD.equals(action.getActionType())){
				Integer[] deletingIds = this.tempOrderItem.getIds();
				for (Integer deletingId : deletingIds) {
					modelHome.deleteById(tempOrderItem.getModel(),deletingId);
				}
				this.copyFrom(commodityOrder);
			}
			else if(Constant.ACTION_CANCEL_CHILD.equals(action.getActionType())||Constant.ACTION_RESTORE_CHILD.equals(action.getActionType())){
				Integer[] updateIds = this.tempOrderItem.getIds();
				for (Integer updateId : updateIds) {
					CommodityOrderItem updateOrderItem = (CommodityOrderItem) modelHome.findById(commodityOrderItem, updateId);
					if(updateOrderItem!=null){
						if(Constant.ACTION_RESTORE_CHILD.equals(action.getActionType())){
							updateOrderItem.setStatus(Constant.STATUS_PENDING);
						}else{
							updateOrderItem.setStatus(Constant.STATUS_CANCELLED);
						}
						modelHome.merge(updateOrderItem);
					}
				}
				this.copyFrom(modelHome.findById(new CommodityOrder(), this.getId()));
				context.setResult(action.getResultCustom());
				context.setNextLocation(this.nextLocation);
			}
			//Order process for external agent
			else if(Constant.ACTION_AGENT_ORDER.equals(action.getActionType())){
				this.setArea((Area)modelHome.findById(new Area(), this.areaId));
				if(commodityOrder==null){
					commodityOrder = (CommodityOrder) this.getModel();
					Date now = new Date();
					commodityOrder.setDealTime(now);
					commodityOrder.setUpdateTime(now);
					commodityOrder.setStatus(Constant.STATUS_MANUALED);
					commodityOrder = (CommodityOrder) modelHome.merge(commodityOrder);
					commodityOrder.setUser((User) context.getUser());

				}else{
					commodityOrder.setArea(this.getArea());
					commodityOrder.setDeliveryAddress(this.getDeliveryAddress());
					commodityOrder.setOrderType(this.getOrderType());
					commodityOrder.setPayType(this.getPayType());
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
							tempOrderItem.setCommodityOrder(commodityOrder);
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
			else{

				for (Integer tempId : this.ids) {
					commodityOrder = (CommodityOrder)modelHome.findById(new CommodityOrder(), tempId);
					if (Constant.ACTION_PREPARE.equals(action.getActionType()))
					{
						commodityOrder.setStatus(Constant.STATUS_PREPARING);
					}
					else if (Constant.ACTION_DELIVER.equals(action.getActionType()))
					{
						commodityOrder.setStatus(Constant.STATUS_DELIVERING);
					}
					else if (Constant.ACTION_CANCEL_ORDER.equals(action.getActionType()))
					{
						for(CommodityOrderItem item : commodityOrder.getCommodityOrderItems()){
							CommodityPromotion commodityPromotion = item.getCommodityPromotion();
							if(commodityPromotion!=null){
								int newCurrentOrderCount = commodityPromotion.getCurrentOrderCount() - item.getCommodityNumber();
								if(newCurrentOrderCount<0){
									newCurrentOrderCount = 0;
								}
								commodityPromotion.setCurrentOrderCount(newCurrentOrderCount);
								modelHome.merge(commodityPromotion);
							}
						}
						Set<CommodityPayment> commodityPayments = commodityOrder.getCommodityPayments();
						for (CommodityPayment commodityPayment : commodityPayments) {
							if(Constant.PAY_BY_POINT.equals(commodityPayment.getPayType())){
								Set<PointConsumeLog> pointConsumeLogs = commodityPayment.getPointConsumeLogs();
								for (PointConsumeLog pointConsumeLog : pointConsumeLogs) {
									savePointLog(context,modelHome,commodityOrder,commodityOrder.getUser(),pointConsumeLog.getPoint(),Constant.CODE_POINT_TYPE_REFUND);
								}
							}
						}
						commodityOrder.setStatus(Constant.STATUS_CANCELLED);
					}
					else if (Constant.ACTION_PEND_ORDER.equals(action.getActionType()))
					{
						commodityOrder.setStatus(Constant.STATUS_PENDING);
					}
					else if (Constant.ACTION_SUCCEED_ORDER.equals(action.getActionType()))
					{
						commodityOrder.setStatus(Constant.STATUS_SUCCEED);
						//add point for user
						User pointUser = commodityOrder.getUser();
						if(pointUser!=null){
							CommodityOrderForm tempCommodityOrderForm = new CommodityOrderForm(commodityOrder);
							tempCommodityOrderForm.calcPrice();
//							BigDecimal point = new BigDecimal(tempCommodityOrderForm.getTotalPoint());
							BigDecimal point = tempCommodityOrderForm.getTotalRealPrice();
							savePointLog(context,modelHome,commodityOrder, pointUser, point, Constant.CODE_POINT_TYPE_ORDER);
							//validate if there is the first buy, then provide the point award to recommend user
							Long count = (Long) modelHome.executeQuery("select count(*) from CommodityOrder where user.id = "+pointUser.getId()+" and dealTime < '" + commodityOrder.getDealTime() + "'");
							if(count==0&&pointUser.getRecommendUser()!=null){
								PointLogForm pointLog = new PointLogForm();
//								Date now = new Date();
//								pointLog.setPointTime(now);
//								pointLog.setTypeCode(Constant.CODE_POINT_TYPE_PROMOTIVE);
//								pointLog.setUser(pointUser.getRecommendUser());
//								pointLog.setPoint(new BigDecimal(0));
//								pointLog.setCommodityOrder(null);
//								pointLog.setRemark("首次订单:"+commodityOrder.getContact()+"|"+commodityOrder.getContactPhone());
								RegulationForm registerRegulationHelper = RegulationForm.getRegulationHelperByCode(RuntechContext.REGULATION_CODE_REGISTER);
								registerRegulationHelper.applyRegulation(context, pointLog);
//								modelHome.save(pointLog.getModel());
//								pointUser.getRecommendUser().getPointLogs().add(pointLog);
								savePointLog(context, modelHome, null, pointUser.getRecommendUser(), pointLog.getPoint(), Constant.CODE_POINT_TYPE_REWARD);
							}
						}
					}
					else if (Constant.ACTION_REJECT_ORDER.equals(action.getActionType()))
					{
						commodityOrder.setStatus(Constant.STATUS_REJECTED);
					}
					else if (Constant.ACTION_RESTORE_ORDER.equals(action.getActionType()))
					{
						commodityOrder.setStatus(Constant.STATUS_CONFIRMED);
					}else{
						return;
					}
					commodityOrder = (CommodityOrder) modelHome.merge(commodityOrder);
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
	
	public void add(CartItem cartItem) throws ModelException {
		CommodityOrderItemForm commodityOrderItemForm = (CommodityOrderItemForm) cartItem;
		Commodity tempCommodity = commodityOrderItemForm.getCommodity();
		if(tempCommodity!=null){
			CommodityOrderItemForm oldOrderItem = this.commodityOrderItemFormMap.get(tempCommodity.getId());
			if(oldOrderItem!=null){
				oldOrderItem.setCommodityNumber(oldOrderItem.getCommodityNumber()+commodityOrderItemForm.getCommodityNumber());
			}else{
				oldOrderItem = commodityOrderItemForm;
			}
			oldOrderItem.calcPrice();
			this.commodityOrderItemFormMap.put(tempCommodity.getId(),oldOrderItem);
			this.calcPrice();
		}
	}

	private void savePointLog(RuntechContext context, ModelHome modelHome,
			CommodityOrder commodityOrder, User pointUser, BigDecimal point, String typeCode) 
					throws ParseErrorException, MethodInvocationException, ResourceNotFoundException, IOException, ModelException {
		if(pointUser!=null){
			point = point.setScale(0, BigDecimal.ROUND_FLOOR);
			PointLog pointLog = new PointLog();
			pointLog.setPoint(point);
			pointLog.setPointTime(new Date());
			pointLog.setTypeCode(typeCode);
			pointLog.setCommodityOrder(commodityOrder);
			pointLog.setUser(pointUser);
			modelHome.save(pointLog);
			BigDecimal userEmpiricValue = pointUser.getEmpiricValue();
			if(userEmpiricValue!=null){
				userEmpiricValue = userEmpiricValue.add(point);
			}else{
				userEmpiricValue = point;
			}
			pointUser.setEmpiricValue(userEmpiricValue);
			RegulationForm regulationForm = RegulationForm.getRegulationHelperByCode(RuntechContext.REGULATION_CODE_LEVEL);
			regulationForm.applyRegulation(context, pointUser);
			BigDecimal userPoint = pointUser.getPoint();
			if(userPoint!=null){
				userPoint = userPoint.add(point);
			}else{
				userPoint = point;
			}
			pointUser.setPoint(userPoint);
			modelHome.merge(pointUser);
			User recommendPointUser = pointUser.getRecommendUser();
			BigDecimal pointRate = new BigDecimal("0.1");
			point = point.multiply(pointRate);
			point = point.setScale(2, BigDecimal.ROUND_HALF_DOWN);
			if(point.compareTo(new BigDecimal(0))>0){
				savePointLog(context,modelHome, commodityOrder, recommendPointUser, point, Constant.CODE_POINT_TYPE_PROMOTIVE);
			}
		}
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
		this.setArea((Area)modelHome.findById(new Area(), this.areaId));
		this.setUser((User)modelHome.findById(new User(), this.userId));
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
		List<CommodityOrderForm> list = new ArrayList<CommodityOrderForm>();
		ModelHome modelHome = new ModelHome();
		String queryString = "select  distinct coi.commodityOrder from CommodityOrderItem coi right join coi.commodityOrder co where 1=1 ";
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
		if(context.getUser()!=null){
			queryString += " and co.user.id = "+context.getUser().getId();
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
		if(this.getPromotionId()!=null){
			queryString += " and coi.commodityPromotion.id ="+this.getPromotionId();
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

	public List<SystemCode> getOrderTypes() throws ModelException{
		return SystemCodeForm.getCodes(Constant.CODE_ORDER_TYPE);
	}

	public List<SystemCode> getPayTypes() throws ModelException{
		return SystemCodeForm.getCodes(Constant.CODE_PAY_TYPE);
	}
	
	public void setPlanDeliveryTimeString(String planDeliveryTime) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date date = formatter.parse(planDeliveryTime);
		this.setPlanDeliveryTime(date);
	}
	
	public String getPlanDeliveryTimeString(){
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		return formatter.format(this.getPlanDeliveryTime());
	}
	
	public CommodityOrderItemForm getTempOrderItem() {
		return tempOrderItem;
	}

	public void setTempOrderItem(CommodityOrderItemForm tempOrderItem) {
		this.tempOrderItem = tempOrderItem;
	}
	
	public void calcPrice() throws ModelException{
		totalPrice = new BigDecimal("0");
		totalRealPrice = new BigDecimal("0");
		totalPoint = new Integer("0");
		for (CartItem item: this.getItems()) {
			if(!Constant.STATUS_CANCELLED.equals(item.getStatus())){
				totalPrice = totalPrice.add(item.getPrice());
				totalRealPrice = totalRealPrice.add(item.getRealPrice());
				totalPoint = totalPoint + item.getPoint();
			}
		}
		totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		totalRealPrice = totalRealPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	public BigDecimal getTotalPrice() throws ModelException {
		if(this.totalPrice==null){
			this.calcPrice();
		}
		return totalPrice;
	}

	public BigDecimal getTotalRealPrice() throws ModelException {
		if(this.totalRealPrice==null){
			this.calcPrice();
		}
		return totalRealPrice;
	}

	public Integer getTotalPoint() throws ModelException {
		if(this.totalPoint==null){
			this.calcPrice();
		}
		return totalPoint;
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
	
	public BigDecimal getRestAmount() throws ModelException{
		BigDecimal restAmount = new BigDecimal("0");
		restAmount = this.getTotalRealPrice().subtract(this.getPaidAmount());
		return restAmount;
	}
	
	public BigDecimal getPaidAmount(){
		BigDecimal totalPaidAmount = new BigDecimal("0.0");
		for (CommodityPayment payment : this.getSuccessCommodityPayments()) {
			totalPaidAmount = totalPaidAmount.add(payment.getSum());
		}
		return totalPaidAmount;
	}
	
	public Set<CommodityPayment> getSuccessCommodityPayments() {
		// TODO Auto-generated method stub
		Set<CommodityPayment> allCommodityPayments = this.getCommodityPayments();
		Set<CommodityPayment> allSuccessCommodityPayments = new HashSet<CommodityPayment>();
		for (CommodityPayment commodityPayment : allCommodityPayments) {
			if(Constant.STATUS_LIST_PAYMENT_SUCCESS.contains(commodityPayment.getStatus())){
				allSuccessCommodityPayments.add(commodityPayment);
			}
		}
		return allSuccessCommodityPayments;
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}
	
	public String getSpecialValue(){
		StringBuffer sb = new StringBuffer();
		if(Constant.ORDER_TYPE_SELF_GROUP.equals(this.getOrderType())||Constant.ORDER_TYPE_WEEKLY_GROUP.equals(this.getOrderType())){
			for(CommodityOrderItem item : this.getCommodityOrderItems()){
				CommodityPromotion commodityPromotion = item.getCommodityPromotion();
				if(commodityPromotion !=null){
					
					Integer currentOrderCount = commodityPromotion.getCurrentOrderCount();
					Integer minOrderCount = commodityPromotion.getMinOrderCount();
					if(currentOrderCount==null)
						currentOrderCount = 0;
					if(minOrderCount==null)
						minOrderCount = 0;
					if(currentOrderCount>=minOrderCount) {
						String name = item.getCommodity().getShortName();
						Integer commodityNumber = item.getCommodityNumber();
						Date now = new Date();
						if(Constant.ORDER_TYPE_WEEKLY_GROUP.equals(this.getOrderType())&&now.after(commodityPromotion.getEndTime())){
							sb.append(name).append("预定结束已成团["+commodityNumber+"]\n");
						}else if(Constant.ORDER_TYPE_SELF_GROUP.equals(this.getOrderType())){
							sb.append(name).append("已成团["+commodityNumber+"]\n");
						}else{
							sb.append(name).append("预定未结束已成团["+commodityNumber+"]\n");
						}
					}
				}
			}
		}
		return sb.toString();
	}
	
	
	public List<CartItem> getItems(){
		List<CartItem> resultList = new ArrayList<CartItem>();
		if(this.commodityOrderItemFormMap.isEmpty()){
			for (CommodityOrderItem element : this.getCommodityOrderItems()) {
				try {
					resultList.add(new CommodityOrderItemForm(element));
				} catch (ModelException e) {
				}
			}
		}else{
			resultList = new ArrayList<CartItem>(this.commodityOrderItemFormMap.values());
		}
		return resultList;
	}

	
	public void setCommodityId(String commodityIds){
		String[] splitValues;
		if(commodityIds!=null){
			splitValues = commodityIds.split(Constant.SYMBOL_COMMA_SPLIT);
			if(splitValues.length==1){
				this.commodityId = Integer.parseInt(splitValues[0].trim());
			}
			this.commodityIds = new Integer[splitValues.length];
			for(int i = 0; i < splitValues.length; i++)
			{
				this.commodityIds[i] = Integer.parseInt(splitValues[i].trim());
			}
		}
	}
	
	private int getCommodityNumber(RuntechContext context, Integer commodityId) {
		String numberValue = context.getParameter("number_"
				+ commodityId);
		int commodityNumber = Integer.parseInt(numberValue);
		return commodityNumber;
	}
	
	public void remove(Integer commodityId) throws ModelException {
		this.commodityOrderItemFormMap.remove(commodityId);
		this.calcPrice();
	}
	
	public CartItem get(Object cartItemId) {
		return this.commodityOrderItemFormMap.get(cartItemId);
	}

	public void save(RuntechContext context) throws ModelException, RuntechRuntimeException {
		// TODO Auto-generated method stub
		List<CartItem> items = this.getItems();
		for (CartItem cartItem : items) {
			Commodity itemCommodity = (Commodity) cartItem.getCommodity();
			Integer commodityId = cartItem.getCommodityId();
			int commodityNumber = this.getCommodityNumber(context,commodityId);
			if(commodityNumber<itemCommodity.getMinimumBuy()){
				throw new RuntechRuntimeException(itemCommodity.getName()+"的购买数量不能小于"+itemCommodity.getMinimumBuy());
			}
			cartItem.setCommodityNumber(commodityNumber);
			cartItem.calcPrice();
		}
		this.calcPrice();
	}


	public boolean isEmpty() {
		return this.getItems().isEmpty();
	}

	public com.runtech.web.model.Order saveOrder(RuntechContext context,
			ModelHome modelHome) throws ModelException {
		Date currentTime = new Date();
		this.setDealTime(currentTime);
		this.setUpdateTime(currentTime);
		this.setDeliveryTime(Constant.DATE_NULL);
		this.setPlanDeliveryTime(Constant.DATE_NULL);
		
		
		List<CartItem> items = this.getItems();
		CommodityOrder cartModel = (CommodityOrder) this.getModel();
		CommodityOrder tempOrder = (CommodityOrder) modelHome.merge(cartModel);
		this.setOrderId(tempOrder.getId());
		for (CartItem cartItem : items) {
			CommodityOrderItemForm commodityOrderItemForm = (CommodityOrderItemForm) cartItem;
			CommodityOrderItem orderItem = (CommodityOrderItem) commodityOrderItemForm.getModel();
			orderItem.setCommodityOrder(tempOrder);
			CommodityOrderItem tempOrderItem = (CommodityOrderItem)modelHome.merge(orderItem);
			commodityOrderItemForm.setId(tempOrderItem.getId());
			tempOrder.getCommodityOrderItems().add(tempOrderItem);
		}
		return new CommodityOrderForm(tempOrder);
	}
	
	public com.runtech.web.model.Order saveQuickOrder(RuntechContext context,
			ModelHome modelHome) throws ModelException {
		User currentUser = (User) context.getUser();
		this.setUser(currentUser);
		this.setArea(currentUser.getArea());
		this.setDeliveryAddress(currentUser.getAddress());
		this.setContact(currentUser.getContact());
		this.setContactPhone(this.getContactPhone());
		return saveOrder(context, modelHome);
	}
	
	public void setOrderId(Integer id) {
		this.orderId = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setUser(com.runtech.web.model.User user) {
		// TODO Auto-generated method stub
		super.setUser((User) user);
	}
}
