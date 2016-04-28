package com.runtech.onlineshop.form;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.FileUtils;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;

import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.User;
import com.runtech.onlineshop.model.UserContract;
import com.runtech.web.action.StrutsAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.ui.Pager;
import com.runtech.web.util.Constant;

public class UploadForm implements ModelForm {

	private String actionType;
	private File file;
	private String fileName;
	
	public void copyFrom(Object model) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void action(RuntechContext context) throws ModelException {
		// TODO Auto-generated method stub
		StrutsAction action = context.getAction();
		if (Constant.ACTION_UPLOAD.equals(this.getActionType())){
			if(this.file!=null){
				ServletContext servletContext = context.getServletContext();
				try {
					String realPath = servletContext.getContext(action.getUploadContext()).getRealPath(action.getUploadImagePath());
					File newFile = new File(realPath+"/"+fileName);
					FileUtils.copyFile(file, newFile);
					String fullPath = action.getUploadContext()+action.getUploadImagePath()+"/"+newFile.getName();
					context.setResult(fullPath);
				} catch (Exception e) {
					context.setResult(null);
				}
			}
		}
	}

	public boolean prepare(RuntechContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public void finish(RuntechContext context) {
		// TODO Auto-generated method stub
		
	}

	public Object getModel() throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFormId(String idValue) {
		// TODO Auto-generated method stub
		
	}

	public String getFormId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Order getDefaultOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public Serializable[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clearId() {
		// TODO Auto-generated method stub
		
	}


}
