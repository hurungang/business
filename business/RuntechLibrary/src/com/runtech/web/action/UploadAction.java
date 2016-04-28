package com.runtech.web.action;

/** 
 * Struts2Test 
 * 使用Struts2上传文件 
 */
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends StrutsAction {

	/** 
			     *  
			     */
	private static final long serialVersionUID = -1896915260152387341L;
	
    private List<File> fileObj;  
    private List<String> fileObjContentType;  
    private List<String> fileObjFileName;  
    
	private String fullPath;

	private String error;


	public UploadAction() {
	}

	

	public List<File> getFileObj() {
		return fileObj;
	}



	public void setFileObj(List<File> fileObj) {
		this.fileObj = fileObj;
	}



	public List<String> getFileObjContentType() {
		return fileObjContentType;
	}



	public void setFileObjContentType(List<String> fileObjContentType) {
		this.fileObjContentType = fileObjContentType;
	}



	public List<String> getFileObjFileName() {
		return fileObjFileName;
	}



	public void setFileObjFileName(List<String> fileObjFileName) {
		this.fileObjFileName = fileObjFileName;
	}



	@Override
	public String execute() throws Exception {
		for (int i=0;i<this.fileObj.size();i++){
			ServletContext servletContext=ServletActionContext.getServletContext();
			try {
				String realPath = servletContext.getContext(getUploadContext()).getRealPath(getUploadFilePath());
				File newFile = new File(realPath+"/"+fileObjFileName.get(i));
				FileUtils.copyFile(fileObj.get(i), newFile);
				fullPath = getUploadFilePath()+"/"+newFile.getName();
			} catch (Exception e) {
				this.error = e.getMessage();
			}
		}
		return SUCCESS;
	}


	public String getFullPath() {
		return fullPath;
	}

	public String getError() {
		return error;
	}

}
