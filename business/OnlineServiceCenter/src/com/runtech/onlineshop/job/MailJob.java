package com.runtech.onlineshop.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.runtech.onlineshop.model.UserMaintainLog;
import com.runtech.util.MailSender;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.runtime.RuntechProperties;
import com.runtech.web.util.Constant;

public class MailJob implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ModelHome modelHome = new ModelHome();
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		Calendar calendar = Calendar.getInstance();
		JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
		int opportunityAdvanceDays = 7;
		if(mergedJobDataMap!=null){
			try {
				opportunityAdvanceDays = Integer.parseInt((String) mergedJobDataMap.get("opportunityAdvanceDays"));
			} catch (NumberFormatException e) {
			}
		}
		String fromString = formatter.format(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, opportunityAdvanceDays);
		String toString = formatter.format(calendar.getTime());
		
		List<UserMaintainLog> logs = (List) modelHome.executeQuery("from UserMaintainLog where opportunityTime > '"+fromString+"' and opportunityTime <  '"+toString+"'",null,null);
		for (UserMaintainLog log : logs) {
			MailSender mailSender = new MailSender(
					RuntechProperties.getProperties());
			mailSender.setToRecipient(log.getAdmin().getEmail());
			mailSender.setSubject("Opportunity Reminder:"+log.getUser().getFullName());
			StringBuffer sb = new StringBuffer();
			sb.append(log.getOpportunity());
			sb.append("\r\n");
			sb.append(log.getOpportunityTime());
			sb.append("\r\n");
			sb.append(log.getOpportunityValue());
			sb.append("\r\n");
			mailSender.setBody(log.getOpportunity());
			mailSender.send();
		}
	}
}
