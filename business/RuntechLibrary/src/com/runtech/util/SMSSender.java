package com.runtech.util;

import java.util.Date;
import java.util.List;
import java.util.Properties;

public interface SMSSender {

	public void setProperties(Properties properties);
	public void setMobileList(List<String> mobileList);
	public void setMobileListString(String mobileListString);
	public void setContent(String content);
	public void setTime(Date time);
	public boolean send();
	public boolean send(List<String> mobileList, String content,Date time);
	public boolean send(String mobileListString, String content,Date time);
	public String getError();
}
