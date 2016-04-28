package com.runtech.onlineshop.model;
// Generated 2015-7-23 13:12:47 by Hibernate Tools 3.4.0.CR1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CommodityOrder generated by hbm2java
 */
public class CommodityOrder  implements java.io.Serializable {


     private Integer id;
     private User userByUserId;
     private User userByDeliveryToUserId;
     private Area area;
     private Date dealTime;
     private String payType;
     private String orderType;
     private String quickRequest;
     private String quickPropose;
     private BigDecimal freight;
     private String deliveryAddress;
     private Date planDeliveryTime;
     private String memo;
     private Date deliveryTime;
     private String deliveryman;
     private String deliveryStatus;
     private String postcode;
     private String contact;
     private String contactPhone;
     private Date updateTime;
     private String status;
     private String comment;
     private String remark;
     private Set<CommodityPayment> commodityPayments = new HashSet<CommodityPayment>(0);
     private Set<CommodityOrderItem> commodityOrderItems = new HashSet<CommodityOrderItem>(0);
     private Set<BillCommodityOrder> billCommodityOrders = new HashSet<BillCommodityOrder>(0);
     private Set<PointLog> pointLogs = new HashSet<PointLog>(0);

    public CommodityOrder() {
    }

	
    public CommodityOrder(Date dealTime) {
        this.dealTime = dealTime;
    }
    public CommodityOrder(User userByUserId, User userByDeliveryToUserId, Area area, Date dealTime, String payType, String orderType, String quickRequest, String quickPropose, BigDecimal freight, String deliveryAddress, Date planDeliveryTime, String memo, Date deliveryTime, String deliveryman, String deliveryStatus, String postcode, String contact, String contactPhone, Date updateTime, String status, String comment, String remark, Set<CommodityPayment> commodityPayments, Set<CommodityOrderItem> commodityOrderItems, Set<BillCommodityOrder> billCommodityOrders, Set<PointLog> pointLogs) {
       this.userByUserId = userByUserId;
       this.userByDeliveryToUserId = userByDeliveryToUserId;
       this.area = area;
       this.dealTime = dealTime;
       this.payType = payType;
       this.orderType = orderType;
       this.quickRequest = quickRequest;
       this.quickPropose = quickPropose;
       this.freight = freight;
       this.deliveryAddress = deliveryAddress;
       this.planDeliveryTime = planDeliveryTime;
       this.memo = memo;
       this.deliveryTime = deliveryTime;
       this.deliveryman = deliveryman;
       this.deliveryStatus = deliveryStatus;
       this.postcode = postcode;
       this.contact = contact;
       this.contactPhone = contactPhone;
       this.updateTime = updateTime;
       this.status = status;
       this.comment = comment;
       this.remark = remark;
       this.commodityPayments = commodityPayments;
       this.commodityOrderItems = commodityOrderItems;
       this.billCommodityOrders = billCommodityOrders;
       this.pointLogs = pointLogs;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public User getUserByUserId() {
        return this.userByUserId;
    }
    
    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
    public User getUserByDeliveryToUserId() {
        return this.userByDeliveryToUserId;
    }
    
    public void setUserByDeliveryToUserId(User userByDeliveryToUserId) {
        this.userByDeliveryToUserId = userByDeliveryToUserId;
    }
    public Area getArea() {
        return this.area;
    }
    
    public void setArea(Area area) {
        this.area = area;
    }
    public Date getDealTime() {
        return this.dealTime;
    }
    
    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }
    public String getPayType() {
        return this.payType;
    }
    
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getQuickRequest() {
        return this.quickRequest;
    }
    
    public void setQuickRequest(String quickRequest) {
        this.quickRequest = quickRequest;
    }
    public String getQuickPropose() {
        return this.quickPropose;
    }
    
    public void setQuickPropose(String quickPropose) {
        this.quickPropose = quickPropose;
    }
    public BigDecimal getFreight() {
        return this.freight;
    }
    
    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }
    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }
    
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    public Date getPlanDeliveryTime() {
        return this.planDeliveryTime;
    }
    
    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }
    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public Date getDeliveryTime() {
        return this.deliveryTime;
    }
    
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    public String getDeliveryman() {
        return this.deliveryman;
    }
    
    public void setDeliveryman(String deliveryman) {
        this.deliveryman = deliveryman;
    }
    public String getDeliveryStatus() {
        return this.deliveryStatus;
    }
    
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    public String getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getContact() {
        return this.contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getContactPhone() {
        return this.contactPhone;
    }
    
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Set<CommodityPayment> getCommodityPayments() {
        return this.commodityPayments;
    }
    
    public void setCommodityPayments(Set<CommodityPayment> commodityPayments) {
        this.commodityPayments = commodityPayments;
    }
    public Set<CommodityOrderItem> getCommodityOrderItems() {
        return this.commodityOrderItems;
    }
    
    public void setCommodityOrderItems(Set<CommodityOrderItem> commodityOrderItems) {
        this.commodityOrderItems = commodityOrderItems;
    }
    public Set<BillCommodityOrder> getBillCommodityOrders() {
        return this.billCommodityOrders;
    }
    
    public void setBillCommodityOrders(Set<BillCommodityOrder> billCommodityOrders) {
        this.billCommodityOrders = billCommodityOrders;
    }
    public Set<PointLog> getPointLogs() {
        return this.pointLogs;
    }
    
    public void setPointLogs(Set<PointLog> pointLogs) {
        this.pointLogs = pointLogs;
    }




}


