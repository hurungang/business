package com.runtech.web.form;

import java.util.Date;
import java.util.List;

import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.model.Authorization;
import com.runtech.web.model.User;
import com.runtech.web.runtime.ModelException;


public interface FlowableForm{

	User getFlowSubmitter() throws ModelException;

	String getStatus();

	String getFormId();

	Date getUpdateTime();

	void copyFrom(Object object) throws ModelException;

	Integer getId();
	
	String getDocumentType();
	
	void setFlowLogs(List logs);
	
	List getFlowLogs();
	
	Authorization getAuthorization(RuntechContext context);
}
