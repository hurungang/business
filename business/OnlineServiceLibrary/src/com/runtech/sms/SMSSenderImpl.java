package com.runtech.sms;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.tempuri.LinkWS;
import org.tempuri.LinkWSSoap;


import com.runtech.util.SMSSender;
import com.runtech.web.context.ContextFactory;
import com.runtech.web.util.Constant;

public class SMSSenderImpl implements SMSSender {

	private static final String MOBILE_SPLIT_CHAR = ",";
	private static final String RUNTECH_SMS_PASSWORD = "runtech.sms.password";
	private static final String RUNTECH_SMS_CORP_ID = "runtech.sms.corpID";
	private LinkWSSoap sender;
	private String content;
	private List<String> mobileList;
	static Properties properties = new Properties();

	static {
		try {
			InputStream resourceAsStream = ContextFactory.class.getResourceAsStream(Constant.RUNTECH_PROPERTIES);
			if(resourceAsStream!=null){
				properties.load(resourceAsStream);
			}
		} catch (IOException e) {
		}
	}
	
	private Date time;
	private String cell;
	private String mobileListString;
	private String error;

	public String getError() {
		return error;
	}

	public SMSSenderImpl() {
		// TODO Auto-generated constructor stub
		try{
			this.sender = new LinkWS().getLinkWSSoap();
		}catch(Exception e){
			
		}
	}

	public String getMobileListString() {
		return mobileListString;
	}

	public boolean send() {
		if(this.mobileList!=null&&this.content!=null){
			return send(this.mobileList,this.content,this.time);
		}else if(this.mobileListString!=null&&this.content!=null){
			return send(this.mobileListString,this.content,this.time);
		}
		return false;
	}

	public boolean send(List<String> mobile, String content, Date time) {
		this.mobileList = mobile;
		String mobileListString = generateMobileString(mobile);
		return send(mobileListString, content, time);
	}

	public boolean send(String mobileListString, String content, Date time) {
		this.content = content;
		this.time = time;
		if (this.properties != null) {
			String corpID = this.properties.getProperty(RUNTECH_SMS_CORP_ID);
			String password = this.properties
					.getProperty(RUNTECH_SMS_PASSWORD);
			if (this.sender != null) {
				String timeString = "";
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				if(this.time != null){
					timeString = df.format(this.time);
				}
				int result = this.sender.batchSend(corpID, password, mobileListString, content, this.cell, timeString);
				if(result>0){
					return true;
				}else{
					this.error = String.valueOf(result);
					return false;
				}
			}
		}
		return false;
	}

	private String generateMobileString(List<String> mobile) {
		StringBuffer mobileString = new StringBuffer();
		for (Iterator iterator = mobile.iterator(); iterator.hasNext();) {
			String mobileNumber = (String) iterator.next();
			mobileString.append(mobileNumber);
			mobileString.append(MOBILE_SPLIT_CHAR);
		}
		String mobileListString = mobileString.toString();
		return mobileListString;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public void setContent(String content) {

		this.content = content;
	}

	public void setMobileList(List<String> mobile) {

		this.mobileList = mobile;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public List<String> getMobileList() {
		return mobileList;
	}

	public Properties getProperties() {
		return properties;
	}

	public Date getTime() {
		return time;
	}

	public void setMobileListString(String mobileListString) {
		this.mobileListString = mobileListString;
	}

}
