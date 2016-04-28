package com.runtech.onlineshop.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Coupon;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.runtime.RuntechRuntimeException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class CouponForm extends Coupon implements
		ModelForm {

	private Integer[] ids;
	private String actionType;
	private int maxRecord;
	
	public CouponForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CouponForm(Object Coupon) throws ModelException {
		this.copyFrom(Coupon);
	}
	
	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			Coupon Coupon = (Coupon) model;
			PropertyUtils.copyProperties(this, Coupon);
			this.setId(Coupon.getId());
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return Order.asc("id");
	}

	public String getFormId() {
		// TODO Auto-generated method stub
		if(super.getId()!=null){
			return super.getId().toString();
		}else{
			return null;
		}
	}

	public Object getModel() throws ModelException {
		// TODO Auto-generated method stub
		try {
			Coupon Coupon = new Coupon();
			PropertyUtils.copyProperties(Coupon,this);
			return Coupon;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		return true;
	}


	public void setFormId(String idValue) {
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


	public Serializable[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}
	
	public void clearId() {
		this.setId(null);
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		Date now = new Date();
		if (Constant.ACTION_CREATE.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			Coupon coupon = (Coupon)modelHome.findById(new Coupon(), this.getId());
			if(coupon!=null&&Constant.STATUS_DRAFT.equalsIgnoreCase(coupon.getStatus())){
				coupon.setStatus(Constant.STATUS_ENABLED);
				coupon.setCreateTime(now);
				try {
					modelHome.beginTransaction();
					modelHome.merge(coupon);
					modelHome.commit();
				} catch (Exception e) {
					modelHome.rollback();
				}
			}else{
				context.setError("该兑换券目前状态不能启用！");
			}
		}
		if (Constant.ACTION_CONSUME.equals(this.getActionType()))
		{
			ModelHome modelHome = new ModelHome();
			Coupon coupon = (Coupon)modelHome.findById(new Coupon(), this.getId());
			if(coupon!=null&&Constant.STATUS_ENABLED.equalsIgnoreCase(coupon.getStatus())){
				coupon.setStatus(Constant.STATUS_USED);
				coupon.setComsumeTime(now);
				coupon.setRemark(this.getRemark());
				try {
					modelHome.beginTransaction();
					modelHome.merge(coupon);
					modelHome.commit();
				} catch (Exception e) {
					modelHome.rollback();
				}
			}else{
				context.setError("该兑换券目前状态不能兑换！");
			}
		}
	}
	
	public List<CouponForm> getSearchResult(Pager pager) throws ModelException
	{
		List<CouponForm> list = new ArrayList<CouponForm>();
		ModelHome modelHome = new ModelHome();
		Coupon Coupon = new Coupon(); 
		String queryString = "from Coupon where status !='deleted' ";
		if(this.getStatus()!=null){
			queryString += " and status = \""+this.getStatus()+"\"";
		}
		if(this.getName()!=null){
			queryString += " and name like \"%"+this.getName()+"%\"";
		}
		if(this.getNumber()!=null){
			queryString += " and number like  \"%"+this.getNumber()+"%\"";
		}
		queryString += " order by id asc";
		pager.setTotalSize(modelHome.getCount(queryString));
		List<Object> tempList = modelHome.executeQuery(queryString, pager.getStartNumber(), pager.getPageSize());
		for (Object object : tempList) {
			CouponForm CouponForm = new CouponForm(object);
			list.add(CouponForm);
		}
		return list;
	}
	
	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}
	private String getActionType() {
		// TODO Auto-generated method stub
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getExpireDateString() {
		// TODO Auto-generated method stub
		return Constant.TIME_PICKER_FORMAT.format(super.getExpireDate());
	}

	public String getStartDateString() {
		// TODO Auto-generated method stub
		return Constant.TIME_PICKER_FORMAT.format(super.getStartDate());
	}

	public boolean generate() {

		ModelHome modelHome = new ModelHome();
		try {
			modelHome.beginTransaction();
			Coupon coupon = (Coupon) this.getModel();
			for(int i=0; i<maxRecord; i++){
				SimpleDateFormat reverseformatter = new SimpleDateFormat("yyyyMMdd");
				String expireDateString = reverseformatter.format(this.getExpireDate());
				coupon.setNumber(this.getName()+" "+expireDateString+RandomStringUtils.random(6));
				modelHome.save(coupon);
			}
			modelHome.commit();
		} catch (ModelException e) {
			modelHome.rollback();
			return false;
		}
		return true;
	}


	public void setMaxRecord(int maxRecord) {
		this.maxRecord = maxRecord;
	}


}
