/**
 * 电子邮件发送程序
 * @author:	freegoo@21cn.com
 * @time:	2006-5-18 15:41:36
 * @desc:	
 */

package com.runtech.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {

	private MimeMessage mimeMsg; // MIME邮件对象

	private Session session; // 邮件会话对象
	private Properties props; // 系统属性

	// private String userName = ""; //smtp认证用户名
	// private String password = ""; //smtp认证密码
	private String toRecipient = ""; // 收件人
	private String ccRecipient = ""; // 抄送
	private String subject = ""; // 主题
	private String body = ""; // 内容
	private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象

	/**
	 * 构造函数
	 */
	public MailSender() {
		props = System.getProperties();
		createMimeMessage();
	}

	/**
	 * 构造函数
	 */
	public MailSender(Properties properties) {
		props = properties;
		createMimeMessage();
	}

	/**
	 * 构造函数
	 * 
	 * @param smtpHostName
	 *            smtp服务器
	 */
	public MailSender(String smtpHostName) {
		// 获得系统属性对象
		props = System.getProperties();
		setSmtpHost(smtpHostName);
		createMimeMessage();
	}

	/**
	 * 设置发送邮件服务器
	 * 
	 * @param hostName
	 *            发送邮件服务器
	 */
	public void setSmtpHost(String hostName) {
		// 获得系统属性对象
		props.put("mail.smtp.host", hostName); // 设置SMTP主机
	}

	/**
	 * 创建MIME消息
	 * 
	 * @return 结果
	 */
	private boolean createMimeMessage() {
		try {
			PopupAuthenticator popupAuthenticator = new PopupAuthenticator(this.getUserName(),this.getPassword());
			session = Session.getDefaultInstance(props, popupAuthenticator); // 获得邮件会话对象
		} catch (Exception e) {
			System.err.println(this.getClass().getName()
					+ ".createMimeMessage():" + e);
			return false;
		}

		try {
			mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
			mp = new MimeMultipart();
			return true;
		} catch (Exception e) {
			System.err.println(this.getClass().getName()
					+ ".createMimeMessage():" + e);
			return false;
		}
	}

	/**
	 * 设置是否验证
	 * 
	 * @param needAuth
	 *            是否验证
	 */
	public void setNeedAuth(boolean needAuth) {
		if (props == null)
			props = System.getProperties();
		props.put("mail.smtp.auth", String.valueOf(needAuth));
	}

	/**
	 * 设置密码
	 * 
	 * @param password
	 *            密码
	 */
	public void setPassword(String password) {
		this.props.setProperty("mail.smtp.password", password);
	}
	
	public String getPassword() {
		return this.props.getProperty("mail.smtp.password");
	}
	/**
	 * 设置用户
	 * 
	 * @param username
	 *            用户
	 */
	public void setUserName(String username) {
		this.props.setProperty("mail.smtp.user", username);
	}
	
	public String getUserName() {
		return this.props.getProperty("mail.smtp.user");
	}
	/**
	 * 获得邮件主体
	 * 
	 * @return 邮件主体
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 设置邮件主体
	 * 
	 * @param body
	 *            邮件主体
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 获得抄送
	 * 
	 * @return 抄送
	 */
	public String getCcRecipient() {
		return ccRecipient;
	}

	/**
	 * 设置抄送
	 * 
	 * @param ccRecipient
	 *            抄送
	 */
	public void setCcRecipient(String ccRecipient) {
		this.ccRecipient = ccRecipient;
	}

	/**
	 * 获得收件人
	 * 
	 * @return 收件人
	 */
	public String getToRecipient() {
		return toRecipient;
	}

	/**
	 * 设置收件人
	 * 
	 * @param toRecipient
	 *            收件人
	 */
	public void setToRecipient(String toRecipient) {
		this.toRecipient = toRecipient;
	}

	/**
	 * 获得发件人
	 * 
	 * @return 发件人
	 */
	public String getFrom() {
		return (String) props.getProperty("mail.smtp.from");
	}

	/**
	 * 设置发件人
	 * 
	 * @param from
	 *            发件人
	 */
	public void setFrom(String from) {
		props.setProperty("mail.smtp.from", from);
	}

	/**
	 * 获得主题
	 * 
	 * @return 主题
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 设置主题
	 * 
	 * @param subject
	 *            主题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 获得内容类型
	 * 
	 * @return 内容类型
	 */
	public String getContentType() {
		return this.props.getProperty("mail.content.contentType");
	}

	/**
	 * 设置内容类型
	 * 
	 * @param contentType
	 *            内容类型
	 */
	public void setContentType(String contentType) {

		this.props.setProperty("mail.content.contentType", contentType);
	}

	/**
	 * 添加附件
	 * 
	 * @param filename
	 *            附件名称
	 * @return 结果
	 */
	public boolean addFileAffix(String filename) {
		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(fileds.getName());
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			System.err.println(this.getClass().getName() + ".addFileAffix():"
					+ e);
			return false;
		}
	}

	/**
	 * 发送
	 * 
	 * @return 发送结果
	 */
	public boolean send() {
		try {
			// 设置邮件包体
			BodyPart bp = new MimeBodyPart();
			bp.setContent(body,
					(String) props.getProperty("mail.content.contentType"));
			mp.addBodyPart(bp);

			// 设置收件人
			mimeMsg.setRecipients(Message.RecipientType.TO,
					(Address[]) InternetAddress.parse(toRecipient));
			// 设置抄送
			mimeMsg.setRecipients(Message.RecipientType.CC,
					(Address[]) InternetAddress.parse(ccRecipient));
			// 设置发件人
			mimeMsg.setFrom(new InternetAddress((String) props
					.getProperty("mail.smtp.from")));
			// 设置邮件主题
			mimeMsg.setSubject(subject);
			// 设置邮件内容
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();

			Transport transport = session.getTransport("smtp");
			if (props.getProperty("mail.smtp.port") != null) {
				transport.connect((String) props.getProperty("mail.smtp.host"),
						Integer.parseInt(props.getProperty("mail.smtp.port")),
						(String) props.getProperty("mail.smtp.user"),
						(String) props.getProperty("mail.smtp.password"));
			} else {
				transport.connect((String) props.getProperty("mail.smtp.host"),
						(String) props.getProperty("mail.smtp.user"),
						(String) props.getProperty("mail.smtp.password"));
			}
			transport.sendMessage(mimeMsg,
					(Address[]) InternetAddress.parse(toRecipient));
			// transport.send(mimeMsg);

			transport.close();
			return true;
		} catch (Exception e) {

			System.err.println(this.getClass().getName() + ".send():" + e);
			return false;
		}

	}

	class PopupAuthenticator extends Authenticator {

		String username, password;

		public PopupAuthenticator(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public PasswordAuthentication getPasswordAuthentication() {

			return new PasswordAuthentication(username, password);
		}
	}

	public static void main(String[] args) {

		String mailbody = "<meta http-equiv=Content-Type content=text/html; charset=gb2312>"
				+ "<div align=center><a href=http://www.csdn.net> csdn </a></div>";
		String contentType = "text/html;charset=GB2312";
		MailSender themail = new MailSender();
		themail.setSmtpHost("beingfruit.com");
		themail.setNeedAuth(true);
		themail.setContentType(contentType);
		themail.setSubject("标题");
		themail.setBody(mailbody);
		themail.setToRecipient("parmdesign@hotmail.com");
		themail.setFrom("POSTMASTER@beingfruit.com");
		// themail.addFileAffix("c:\\boot.ini");
		themail.setUserName("POSTMASTER");
		themail.setPassword("runtogether");

		System.out.println("发送邮件" + (themail.send() ? "成功" : "失败"));
	}
}
