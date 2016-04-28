package com.runtech.web.dispatcher;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.runtech.util.HibernateUtil;
import com.runtech.util.ParameterCheckHelper;
import com.runtech.web.context.ContextFactory;
import com.runtech.web.runtime.RuntechRuntimeException;
import com.runtech.web.util.Constant;

public class FilterDispatcher extends
		org.apache.struts2.dispatcher.FilterDispatcher {

	public static final String RUNTECH_CONTEXT = "RUNTECH_CONTEXT";


	private Logger LOG = Logger.getLogger(FilterDispatcher.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		RuntechContext context = null;

        response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.setHeader("progma","no-cache"); 
		httpResponse.setHeader("Cache-Control","no-cache"); 
		httpResponse.setDateHeader("Expires",0); 
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession(true);
		Boolean checkScript = (Boolean) session.getAttribute(Constant.SESSION_CHECK_SCRIPT);
		if(checkScript==null){
			checkScript=true;
		}

		try{
			context = ContextFactory.createContext();
			
			if(checkScript){
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				StringBuffer requestURL = httpRequest.getRequestURL();
				String queryString = httpRequest.getQueryString();
				if(ParameterCheckHelper.isDangerousURL(requestURL.toString())||ParameterCheckHelper.isDangerousURL(queryString)){
						LOG.fatal("Dangerous script from"+request.getRemoteHost()+"("+request.getRemoteAddr()+":"+request.getRemotePort()+") detected:rpc");
	                	throw new ServletException("Dangerous script from"+request.getRemoteHost()+"("+request.getRemoteAddr()+":"+request.getRemotePort()+") detected:rpc");
				}
				//check risk script
		        Map<String, String[]> pMaps = request.getParameterMap();
		        Set<String> pKeySet = pMaps.keySet();
		        for (String key : pKeySet) {
		            String[] pValue = (String[]) pMaps.get(key);
		            for (String val : pValue) {
		                boolean isDangerous = ParameterCheckHelper.isDangerousValue(val);
		                if (isDangerous) {
		                	LOG.fatal("Dangerous script from"+request.getRemoteHost()+"("+request.getRemoteAddr()+":"+request.getRemotePort()+") detected:"+val);
		                	throw new ServletException("Dangerous script from"+request.getRemoteHost()+"("+request.getRemoteAddr()+":"+request.getRemotePort()+") detected:"+val);
		                }
		            }
		        }
			}
			if(context!=null){
				request.setAttribute(RUNTECH_CONTEXT, context);
				context.setRequest(httpServletRequest);
				context.setResponse((HttpServletResponse) response);
				context.setServletContext(this.getServletContext());
				context.setSession(session);
			}
			super.doFilter(request, response, chain);
		} catch (RuntechRuntimeException e) {
			throw new ServletException(e.fillInStackTrace());
		}finally{
			HibernateUtil.closeSession();
			if(context!=null){
				context.destory();
			}
		}
	}
	
}
