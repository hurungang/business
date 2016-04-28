package com.runtech.web.runtime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.runtech.web.context.ContextFactory;
import com.runtech.web.util.Constant;

public class RuntechProperties {
	private static final int DEFAULT_SMS_DAILY_MAX_COUNT = 3;
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
	
	public static Properties getProperties(){
		return properties;
	}
	
	public static String getDomain() {
		return properties.getProperty("runtech.domain");
	}
	
	public static String getContextImpl() throws IOException {
		return properties.getProperty("runtech.runtime.context");
	}

	public static String getPageCacheFolderPath() {
		return properties.getProperty("runtech.runtime.pageCacheFolderPath");
	}

	public static String getWatermarkPath() {
		return properties.getProperty("runtech.runtime.watermarkPath");
	}
	
	public static String getAlipayGateway() {
		return properties.getProperty("runtech.pay.alipay.paygateway");
	}
	public static String getAlipayService() {
		return properties.getProperty("runtech.pay.alipay.service");
	}
	public static String getAlipaySignType() {
		return properties.getProperty("runtech.pay.alipay.signType");
	}
	public static String getAlipayInputCharset() {
		return properties.getProperty("runtech.pay.alipay.inputCharset");
	}
	public static String getAlipayPartnerId() {
		return properties.getProperty("runtech.pay.alipay.partnerId");
	}
	public static String getAlipayKey() {
		return properties.getProperty("runtech.pay.alipay.key");
	}
	public static String getAlipaySellerEmail() {
		return properties.getProperty("runtech.pay.alipay.sellerEmail");
	}

	public static String getShowOrderUrl() {
		return properties.getProperty("runtech.pay.alipay.showOrderUrl");
	}

	public static String getAlipayNotifyUrl() {
		return properties.getProperty("runtech.pay.alipay.notifyUrl");
	}

	public static String getAlipayReturnUrl() {
		return properties.getProperty("runtech.pay.alipay.returnUrl");
	}

	public static String getAlipayNotifyQueryUrl() {
		return properties.getProperty("runtech.pay.alipay.notifyQueryUrl");
	}

	public static String getDiscuzBBSUrl(){
		return properties.getProperty("runtech.bbs.discuz.url");
	}
	
	public static String getDiscuzBBSPassportUrl(){
		return properties.getProperty("runtech.bbs.discuz.passportUrl");
	}
	
	public static String getDiscuzBBSPrivateKey(){
		return properties.getProperty("runtech.bbs.discuz.privateKey");
	}

	public static String getDiscuzBBSAdminName() {
		return properties.getProperty("runtech.bbs.discuz.adminName");
	}

	public static String getDefaultPicturePath() {
		return properties.getProperty("runtech.runtime.defaultPicturePath");
	}
	

	public static String getMailFrom() {
		return properties.getProperty("runtech.mail.from");
	}

	public static String getWatermarkText() {
		return properties.getProperty("runtech.runtime.watermarkText");
	}

	public static String getNotificationMobile() {
		return properties.getProperty("runtech.mobile.notification");
	}

	public static int getSMSDailyMaxCount() {
		String maxCountString = properties.getProperty("runtech.sms.maxCount");
		try{
			return Integer.parseInt(maxCountString);
		}catch(NumberFormatException e){
			return DEFAULT_SMS_DAILY_MAX_COUNT;
		}
	}
}
