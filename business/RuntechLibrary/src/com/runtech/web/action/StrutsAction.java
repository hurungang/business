package com.runtech.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
import com.runtech.util.SMSSender;
import com.runtech.web.ui.Pager;

public class StrutsAction extends ActionSupport {

	private String resultLogin;
	private String resultSuccess;
	private String resultFailure;
	private String resultError;
	private String resultCancel;
	private String resultDefault;
	private String resultUnauthorized;
	private Integer pageSize;
	private String uploadImagePath;
	protected Pager pager;
	private String uploadContext;
	private String resultCustom;
	
	private SMSSender smsSender;
	private String successMessageKey;
	private String uploadFilePath;
	
	public SMSSender getSmsSender() {
		return smsSender;
	}

	@Inject("runtech.sms.sender")
	public void setSmsSender(SMSSender smsSender) {
		this.smsSender = smsSender;
	}

	public String getResultLogin() {
		return resultLogin;
	}

	@Inject("runtech.result.login")
	public void setResultLogin(String value) {
		resultLogin = value;
	}

	public String getResultSuccess() {
		return resultSuccess;
	}

	protected String getSuccessMessage() {
		// TODO Auto-generated method stub
		return getText(successMessageKey);
	}

	@Inject("runtech.messageKey.success")
	public void setSuccessMessageKey(String successMessageKey) {
		this.successMessageKey = successMessageKey;
	}

	@Inject("runtech.result.success")
	public void setResultSuccess(String resultSuccess) {
		this.resultSuccess = resultSuccess;
	}

	public String getResultFailure() {
		return resultFailure;
	}

	@Inject("runtech.result.failure")
	public void setResultFailure(String resultFailure) {
		this.resultFailure = resultFailure;
	}

	public String getResultError() {
		return resultError;
	}

	@Inject("runtech.result.error")
	public void setResultError(String resultError) {
		this.resultError = resultError;
	}
	
	public String getResultDefault() {
		return resultDefault;
	}

	@Inject("runtech.result.default")
	public void setResultDefault(String resultDefault) {
		this.resultDefault = resultDefault;
	}
	
	@Inject("runtech.result.cancel")
	public void setResultCancel(String resultCancel) {
		this.resultCancel = resultCancel;
	}

	protected String getResultCancel() {
		// TODO Auto-generated method stub
		return resultCancel;
	}
	
	@Inject("runtech.result.unauthorized")
	public void setResultUnauthorized(String resultUnauthorized) {
		this.resultUnauthorized = resultUnauthorized;
	}

	protected String getResultUnauthorized() {
		return resultUnauthorized;
	}
	
	
	public Integer getPageSize() {
		return pageSize;
	}

	@Inject("runtech.page.size")
	public void setPageSize(String pageSize) {
		this.pageSize = Integer.parseInt(pageSize);
		this.pager.setPageSize(this.pageSize);
	}

	public String getUploadImagePath() {
		return uploadImagePath;
	}

	@Inject("runtech.path.uploadImage")
	public void setUploadImagePath(String uploadImagePath) {
		this.uploadImagePath = uploadImagePath;
	}

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	@Inject("runtech.path.uploadFile")
	public void setUploadFilePath(String uploadImagePath) {
		this.uploadFilePath = uploadImagePath;
	}

	public void setPaging(String pageIndexValue) {
		Integer pageIndex=1;
		if(pageIndexValue!=null){
			pageIndex = Integer.parseInt(pageIndexValue);
		}
		this.pager.setPageIndex(pageIndex);
	}

	public Pager getPager() {
		return this.pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Pager getSpecialPager(Integer totalSize) {
		this.pager.setTotalSize(totalSize);
		return this.pager;
	}
	
	public Pager getSpecialPager() {
		return this.pager;
	}
	
	public StrutsAction() {
		super();
		this.pager = new Pager();
	}

	public String getUploadContext() {
		// TODO Auto-generated method stub
		return this.uploadContext;
	}
	
	@Inject("runtech.context.upload")
	public void setUploadContext(String uploadContext) {
		this.uploadContext = uploadContext;
	}

	@Inject("runtech.result.custom")
	public void setResultCustom(String resultCustom) {
		this.resultCustom = resultCustom;
	}

	public String getResultCustom() {
		// TODO Auto-generated method stub
		return this.resultCustom;
	}

}