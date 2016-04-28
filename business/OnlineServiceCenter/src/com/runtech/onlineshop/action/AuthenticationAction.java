package com.runtech.onlineshop.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.runtech.onlineshop.form.AdminForm;
import com.runtech.onlineshop.form.AdminModuleForm;
import com.runtech.onlineshop.form.UserForm;
import com.runtech.onlineshop.model.Admin;
import com.runtech.onlineshop.model.AdminLoginLog;
import com.runtech.onlineshop.model.UserLoginLog;
import com.runtech.onlineshop.security.AdminImpl;
import com.runtech.onlineshop.security.MenuImpl;
import com.runtech.onlineshop.security.UserImpl;
import com.runtech.util.HibernateUtil;
import com.runtech.web.action.StrutsAction;
import com.runtech.web.dao.ModelHome;
import com.runtech.web.dispatcher.FilterDispatcher;
import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.model.Menu;
import com.runtech.web.model.User;
import com.runtech.web.runtime.ModelException;
import com.runtech.web.util.Constant;

public class AuthenticationAction extends StrutsAction implements ServletRequestAware,Interceptor,SessionAware,StrutsStatics{

	private static final String ERROR_LOGIN_FAILURE = "error.login.failure";
	private String userName;
	private String userPassword;
	private Map<String,Object> session;
	private HttpServletRequest servletRequest;
	private String requestURL;

	private RuntechContext context;
	
	/**
	 * Struts action implementation for node login
	 * @return a link to follow
	 */
	public String login() {
		UserForm userExample = new UserForm();
		userExample.setName(userName);
		ModelHome userHome = new ModelHome();
		User user;
		try {
			com.runtech.onlineshop.model.User userModel = (com.runtech.onlineshop.model.User) userHome.findByKeyExample(userExample.getModel());

			if(userModel!=null){
				if(!userModel.getPassword().equals(userPassword)){
					saveLoginLog(userHome, false,userModel);
					this.addActionError(getText(ERROR_LOGIN_FAILURE));
				}else{
					user = new UserImpl(new UserForm(userModel));
					user.init();
					context.setUser(user);
					this.requestURL = (String) session.get(Constant.REQUEST_URL);
					saveLoginLog(userHome, true,userModel);
					if(this.requestURL!=null){
						return this.getResultSuccess();
					}else{
						return this.getResultDefault();
					}
				}
			}else{
				this.addActionError(getText(ERROR_LOGIN_FAILURE));
			}
		} catch (ModelException e) {
			LOG.error(e);
			this.addActionError(getText(ERROR_LOGIN_FAILURE));
		}
		return this.getResultFailure();
	}


	private void saveLoginLog(ModelHome userHome, boolean result, com.runtech.onlineshop.model.User user) {
		UserLoginLog loginLog = new UserLoginLog();
		loginLog.setLoginFrom(getIpAddr(servletRequest));
		loginLog.setLoginMachine(servletRequest.getRemoteHost());
		loginLog.setLoginResult(result);
		loginLog.setLoginTime(new Date());
		loginLog.setUser(user);
		userHome.beginTransaction();
		userHome.save(loginLog);
		userHome.commit();
	}
	
	public String getIpAddr(HttpServletRequest request) {  
		String ip = request.getHeader("x-forwarded-for");  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("Proxy-Client-IP");  
		}  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("WL-Proxy-Client-IP");  
		}  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getRemoteAddr();  
		}  
		return ip;  
	} 
	/**
	 * Struts action implementation for node login
	 * @return a link to follow
	 */
	public String adminLogin() {
		
		AdminForm adminExample = new AdminForm();
		adminExample.setName(userName);
		ModelHome adminHome = new ModelHome();
		AdminForm admin;
		try {
			admin = new AdminForm(adminHome.findByKeyExample(adminExample.getModel()));
			if(admin!=null){
				if(!admin.getPassword().equals(userPassword)){
					saveAdminLoginLog(adminHome, false, admin);
					this.addActionError(getText(ERROR_LOGIN_FAILURE));
				}else{
					User user = new AdminImpl(admin);
					user.init();
					context.setUser(user);
					this.requestURL = (String) session.get(Constant.REQUEST_URL);
					saveAdminLoginLog(adminHome, true, admin);
					if(this.requestURL!=null){
						return this.getResultSuccess();
					}else{
						return this.getResultDefault();
					}
				}
			}else{
				this.addActionError(getText(ERROR_LOGIN_FAILURE));
			}
		} catch (ModelException e) {
			LOG.error(e);
			this.addActionError(getText(ERROR_LOGIN_FAILURE));
		}
		return this.getResultFailure();
	}
	/**
	 * Struts action implementation for node logout
	 * @return a link to follow
	 */

	private void saveAdminLoginLog(ModelHome userHome, boolean result, Admin admin) {
		AdminLoginLog loginLog = new AdminLoginLog();
		loginLog.setLoginFrom(servletRequest.getLocalAddr());
		loginLog.setLoginMachine(servletRequest.getLocalName());
		loginLog.setLoginResult(result);
		loginLog.setLoginTime(new Date());
		loginLog.setAdmin(admin);
		userHome.beginTransaction();
		userHome.save(loginLog);
		userHome.commit();
	}
	
	public String logout() {
		User user = context.getUser();
		context.setUser(null);
		session.clear();
		if(user instanceof AdminImpl){
			return this.getResultSuccess();
		}else{
			return this.getResultDefault();
		}
	}
	
	public String refreshWeb() {
		ModelHome modelHome = new ModelHome();
		modelHome.beginTransaction();
		modelHome.executeUpdate("update SiteModule set status='expired' where status='cacheEnabled'");
		modelHome.commit();
		return this.getResultSuccess();
	}
	/**
	 * Struts action implementation for node logout
	 * @return a link to follow
	 */
	public String clear() {
		HibernateUtil.getCurrentSession().flush();
		HibernateUtil.getCurrentSession().clear();
		return this.getResultSuccess();
	}

	public void setSession(Map sessionValue) {
		this.session = sessionValue;
	}
	
	public Map getSession() {
		return session;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRequestURL() {
		return requestURL;
	}
	
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	
	public String intercept(ActionInvocation invocation) throws Exception {
        final ActionContext context = invocation.getInvocationContext();  
        this.servletRequest = (HttpServletRequest) context.get(HTTP_REQUEST);
        this.setSession(context.getSession());
        User user = (User) session.get(Constant.SESSION_USER);   
        if (user == null) {
        	StringBuffer requestURL = this.servletRequest.getRequestURL();
        	session.put(Constant.REQUEST_URL,requestURL.toString());
        	return this.getResultLogin();   
        } else {   
            return invocation.invoke();   
        }   
	}

	public void destroy() {
		LOG.debug("AuthenticationAction.destroy");
	}
	

	public void init() {
		LOG.debug("AuthenticationAction.init");
	}
	
	public void setServletRequest(HttpServletRequest servletRequestValue) {
		this.servletRequest = servletRequestValue;
		this.context = (RuntechContext) servletRequest.getAttribute(FilterDispatcher.RUNTECH_CONTEXT);
	}

}
