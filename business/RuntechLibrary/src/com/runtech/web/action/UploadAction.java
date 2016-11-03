package com.runtech.web.action;

/** 
 * Struts2Test 
 * 使用Struts2上传文件 
 */
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts2.ServletActionContext;

import com.runtech.web.util.Constant;

public class UploadAction extends StrutsAction {

	/** 
			     *  
			     */
	private static final long serialVersionUID = -1896915260152387341L;
	
    private List<File> upload;  
    private List<String> uploadContentType;  
    private List<String> uploadFileName;  
    
	private String fullPath;

	private String error;

	private String actionType;


	public UploadAction() {
	}

	

	public List<File> getUpload() {
		return upload;
	}



	public void setUpload(List<File> upload) {
		this.upload = upload;
	}



	public List<String> getUploadContentType() {
		return uploadContentType;
	}



	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}



	public List<String> getUploadFileName() {
		return uploadFileName;
	}



	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}



	@Override
	public String execute() throws Exception {
		fullPath = "[";
		for (int i=0;i<this.upload.size();i++){
			ServletContext servletContext=ServletActionContext.getServletContext();
			try {
				String realPath = servletContext.getContext(getUploadContext()).getRealPath(getUploadFilePath());
			
				long currentTimeMillis = System.currentTimeMillis();
				String tempFileName = uploadFileName.get(i);
				String surfix = tempFileName.substring(tempFileName.lastIndexOf(".")+1);
				
				File newFile = new File(realPath+"/"+currentTimeMillis+"."+surfix);
				FileUtils.copyFile(upload.get(i), newFile);
				if(!fullPath.equals("[")){
					fullPath +=",";
				}
				fullPath += "\""+getUploadContext()+getUploadFilePath()+"/"+newFile.getName()+"\"";
			} catch (Exception e) {
				this.error = e.getMessage();
			}
		}
		fullPath += "]";
		if(Constant.ACTION_CKEDITOR_UPLOAD.equals(actionType)){
			return Constant.RESULT_CKEDITOR_SUCCESS;
		}else{
			return SUCCESS;
		}
	}


	public String getFullPath() {
		return fullPath;
	}

	public String getError() {
		return error;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
}
