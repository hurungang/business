package com.runtech.onlineshop.form;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.criterion.Order;

import com.google.gson.JsonObject;
import com.runtech.onlineshop.model.Commodity;
import com.runtech.onlineshop.model.CommodityOrder;
import com.runtech.onlineshop.model.CommodityOrderItem;
import com.runtech.onlineshop.model.CommodityProvider;
import com.runtech.onlineshop.model.Consignment;
import com.runtech.onlineshop.model.ConsignmentPicture;
import com.runtech.web.action.PageAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class ConsignmentForm extends Consignment implements ModelForm {

	private Integer[] ids;
	private File orderFile;
	private ConsignmentPictureForm uploadPicture = new ConsignmentPictureForm();
	JsonObject ajaxResult = new JsonObject();
	private String companyId;
	private String deliveryCompanyId;
	private Object Object;
	
	public ConsignmentForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ConsignmentForm(Object consignment) throws ModelException {
		this.copyFrom(consignment);
	}
	
	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		try {
			Consignment consignment = (Consignment) model;
			PropertyUtils.copyProperties(this, consignment);
			this.setId(consignment.getId());
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

	public Object getModel() throws ModelException {
		// TODO Auto-generated method stub
		try {
			Consignment consignment = new Consignment();
			PropertyUtils.copyProperties(consignment,this);
			return consignment;
		} catch (Exception e) {
			throw new ModelException(e.fillInStackTrace());
		}
	}

	public boolean prepare(RuntechContext context){
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		Consignment originalConsignment = (Consignment) modelHome.findById(new Consignment(), this.getId());
		return prepare(context, modelHome,originalConsignment);
	}

	private boolean prepare(RuntechContext context, ModelHome modelHome, Consignment original) {
		if(original!=null){
			this.setStatus(original.getStatus());
			this.setConsignmentPictures(original.getConsignmentPictures());
		}
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

	public void clearId() {
		this.setId(null);
	}

	public Integer[] getIds() {
		// TODO Auto-generated method stub
		return this.ids;
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		try {
			boolean reload = true;
			modelHome.beginTransaction();
			PageAction action = (PageAction) context.getAction();
			Consignment original = (Consignment) modelHome.findById(new Consignment(), this.getId());
			if(action.getActionType().equals(Constant.ACTION_SAVE_ALL)){
				Consignment consignment = (Consignment) this.getModel();
				CommodityProvider company = (CommodityProvider) modelHome.findById(new CommodityProvider(), Integer.parseInt(this.companyId));
				consignment.setCompany(company);
				CommodityProvider deliveryCompany = (CommodityProvider) modelHome.findById(new CommodityProvider(), Integer.parseInt(this.deliveryCompanyId));
				consignment.setDeliveryCompany(deliveryCompany);
		
				if(original!=null){
					consignment.setCreateTime(original.getCreateTime());
					consignment.setStatus(original.getStatus());
					consignment.setDeliveryTime(original.getDeliveryTime());
				}else{
					consignment.setCreateTime(new Date());
					consignment.setStatus(Constant.STATUS_PREPARING);
				}
				consignment = (Consignment) modelHome.merge(consignment);
				
				if(this.getOrderFile()!=null){
					//delete all orders to reload
					CommodityOrder queryOrder = new CommodityOrder();
					queryOrder.setConsignment(consignment);
					List<Object> orderList = modelHome.findByExample(queryOrder);
					for (Iterator iterator = orderList.iterator(); iterator.hasNext();) {
						CommodityOrder object = (CommodityOrder) iterator.next();
						modelHome.delete(object);
					}
					//reload
					List<CommodityOrder> readBooksFromExcelFile = this.readBooksFromExcelFile(getOrderFile());
					for (Iterator iterator = readBooksFromExcelFile.iterator(); iterator.hasNext();) {
						CommodityOrder commodityOrder = (CommodityOrder) iterator.next();
						commodityOrder.setConsignment(consignment);
						commodityOrder.setDealTime(new Date());
						commodityOrder.setStatus(Constant.STATUS_PREPARING);
						modelHome.save(commodityOrder);
						Set<CommodityOrderItem> commodityOrderItems = commodityOrder.getCommodityOrderItems();
						for (Iterator iterator2 = commodityOrderItems.iterator(); iterator2.hasNext();) {
							CommodityOrderItem commodityOrderItem = (CommodityOrderItem) iterator2.next();
							commodityOrderItem.setCommodityOrder(commodityOrder);
							modelHome.save(commodityOrderItem);
						}
					}
				}
/*				if(original!=null){
					for (Iterator iterator = original.getConsignmentPictures().iterator(); iterator.hasNext();) {
						ConsignmentPicture picture = (ConsignmentPicture) iterator.next();
						modelHome.deleteById(picture, picture.getId());
					}
				}
				//save pictures
				String[] parameters = context.getParameters("uploadedPic");
				for (int i = 0; i < parameters.length; i++) {
					ConsignmentPicture newPicture = new ConsignmentPicture();
					newPicture.setConsignment(consignment);
					newPicture.setPicturePath(parameters[i]);
					
				}*/
			}
			else if(action.getActionType().equals(Constant.ACTION_SAVE_PICTURE)){
				if(this.uploadPicture!=null&&this.uploadPicture.getPicture()!=null){
					this.uploadPicture.savePicture(context);
					this.uploadPicture.setConsignment(original);
					modelHome.save(this.uploadPicture.getModel());
				}
			}
			else if(action.getActionType().equals(Constant.ACTION_DELETE_PICTURE)){
				String[] parameters = context.getParameters("pictureId");
				for (int i = 0; i < parameters.length; i++) {
					String string = parameters[i];
					Integer pictureId = Integer.parseInt(string);
					modelHome.deleteById(new ConsignmentPicture(), pictureId);
				}
			}
			else if (Constant.ACTION_SPECIAL_DELETE.equals(action.getActionType()))
			{
				if(this.getIds() == null)
				{
					original.setStatus(Constant.STATUS_DELETED);
					modelHome.save(original);
				}
				else
				{
					for(Serializable id: this.getIds())
					{
						Consignment modelResult = (Consignment) modelHome.findById(new Consignment(), id);
						modelResult.setStatus(Constant.STATUS_DELETED);
						modelHome.save(modelResult);
					}
				}
			}
			else if(action.getActionType().equals(Constant.ACTION_DELIVER)){
				if(original!=null){
					original.setStatus(Constant.STATUS_DELIVERING);
					original.setDeliveryTime(new Date());
					modelHome.save(original);
					Set<CommodityOrder> tempCommodityOrders = original.getCommodityOrders();
					for (Iterator iterator = tempCommodityOrders.iterator(); iterator.hasNext();) {
						CommodityOrder tempCommodityOrder = (CommodityOrder) iterator.next();
						tempCommodityOrder.setStatus(Constant.STATUS_DELIVERING);
						tempCommodityOrder.setDeliveryTime(original.getDeliveryTime());
						modelHome.save(tempCommodityOrder);
					}
				}
			}
			modelHome.commit();

			if(reload){
				this.reload();
			}
			ajaxResult.addProperty(Constant.AJAX_SUCCESS, true);
			if(context.getResult()==null){
				context.setResult(action.getResultSuccess());
			}
		} catch (Exception e) {
			context.setError(e.getLocalizedMessage());
			modelHome.rollback();
			ajaxResult.addProperty(Constant.AJAX_SUCCESS, false);
			ajaxResult.addProperty(Constant.AJAX_ERROR_MESSAGE, e.toString());
		}
		if(context.getAjax()){
			context.setResult(Constant.RESULT_AJAX);
		}
	}
	
	private void reload() throws ModelException {
		// TODO Auto-generated method stub
		ModelHome modelHome = new ModelHome();
		Consignment original = (Consignment) modelHome.findById(new Consignment(), this.getId());
		this.copyFrom(original);
	}

	public static List<ConsignmentForm> getAllConsignment() throws ModelException
	{
		List<ConsignmentForm> list = new ArrayList<ConsignmentForm>();
		ModelHome modelHome = new ModelHome();
		Consignment consignment = new Consignment(); 
		List<Object> tempList = modelHome.findByExample(consignment);
		for (Object object : tempList) {
			ConsignmentForm consignmentForm = new ConsignmentForm(object);
			list.add(consignmentForm);
		}
		return list;
	}

	public List<ConsignmentForm> getSearchResult(Pager pager) throws ModelException
	{
		List<ConsignmentForm> list = new ArrayList<ConsignmentForm>();
		ModelHome modelHome = new ModelHome();
		Consignment consignment = new Consignment(); 
		String queryString = "from Consignment where status !='deleted' ";
		if(this.getTitle()!=null){
			queryString += " and title like \"%"+this.getTitle()+"%\"";
		}
		queryString += " order by createTime desc";
		pager.setTotalSize(modelHome.getCount(queryString));
		List<Object> tempList = modelHome.executeQuery(queryString, pager.getStartNumber(), pager.getPageSize());
		for (Object object : tempList) {
			ConsignmentForm consignmentForm = new ConsignmentForm(object);
			list.add(consignmentForm);
		}
		return list;
	}
	
	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}
	

	public File getOrderFile() {
		return orderFile;
	}

	public void setOrderFile(File orderFile) {
		this.orderFile = orderFile;
	}
	
	private Object getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	    case Cell.CELL_TYPE_STRING:
	        return cell.getStringCellValue();
	 
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case Cell.CELL_TYPE_NUMERIC:
	        return cell.getNumericCellValue();
	    }
	 
	    return null;
	}
	
	private List<CommodityOrder> readBooksFromExcelFile(File file) throws IOException, ModelException, EncryptedDocumentException, InvalidFormatException {
	    List<CommodityOrder> listOrders = new ArrayList<CommodityOrder>();
	 
	    org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
	    org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
	 
	    //skip header
	    if(iterator.hasNext())
	    	iterator.next();
	    while (iterator.hasNext()) {
	        Row nextRow = iterator.next();
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        CommodityOrder aOrder = new CommodityOrder();
	 
	        
	        //prepare order item
	        Set<CommodityOrderItem> commodityOrderItems = new HashSet<CommodityOrderItem>();
	        CommodityOrderItem commodityOrderItem = new CommodityOrderItem();
	        while (cellIterator.hasNext()) {
	            Cell nextCell = cellIterator.next();
	            int columnIndex = nextCell.getColumnIndex();
                
	            switch (columnIndex) {
	            case 0:
	                aOrder.setExternalId((String) getCellValue(nextCell));
	                break;
	            case 1:
	                aOrder.setAgent((String) getCellValue(nextCell));
	                break;
	            case 2:
	            	Commodity comodityByExternalId = CommodityForm.getComodityByExternalId((String)getCellValue(nextCell));
	                commodityOrderItem.setCommodity(comodityByExternalId);
	                break;
	            case 6:
	                commodityOrderItem.setCommodityNumber(((Double) getCellValue(nextCell)).intValue());
	                break;
	            case 12:
	                aOrder.setContact(((String) getCellValue(nextCell)));
	                break;
	            case 13:
	                aOrder.setContactPhone(((Double) getCellValue(nextCell)).longValue()+"");
	                break;
	            case 14:
	                aOrder.setDeliveryAddress(((String) getCellValue(nextCell)));
	                break;
	            case 15:
	                aOrder.setDeliveryAddress(aOrder.getDeliveryAddress()+((String) getCellValue(nextCell)));
	                break;
	            case 17:
	                aOrder.setQuickRequest((String) getCellValue(nextCell));
	                break;
	            case 18:
	                aOrder.setQuickPropose((String) getCellValue(nextCell));
	                break;
	            case 20:
	                aOrder.setDeliveryTrackId((String) getCellValue(nextCell));
	                break;
	            }
	        }
	        commodityOrderItems.add(commodityOrderItem);
	        aOrder.setCommodityOrderItems(commodityOrderItems);
	        listOrders.add(aOrder);
	    }
	 
	    workbook.close();
	 
	    return listOrders;
	}

	public ConsignmentPicture getUploadPicture() {
		return uploadPicture;
	}

	public void setUploadPicture(ConsignmentPictureForm uploadPicture) {
		this.uploadPicture = uploadPicture;
	}

	public JsonObject getAjaxResult() {
		return ajaxResult;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getDeliveryCompanyId() {
		return deliveryCompanyId;
	}

	public void setDeliveryCompanyId(String deliveryCompanyId) {
		this.deliveryCompanyId = deliveryCompanyId;
	}
	
	
}
