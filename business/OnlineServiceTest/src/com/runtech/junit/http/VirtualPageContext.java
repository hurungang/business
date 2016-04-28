package com.runtech.junit.http;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

public class VirtualPageContext extends PageContext {

	private VirtualJspWriter jspWriter = new VirtualJspWriter();

	@Override
	public void forward(String arg0) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Exception getException() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletRequest getRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletResponse getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handlePageException(Exception arg0) throws ServletException,
			IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlePageException(Throwable arg0) throws ServletException,
			IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void include(String arg0) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void include(String arg0, boolean arg1) throws ServletException,
			IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize(Servlet arg0, ServletRequest arg1,
			ServletResponse arg2, String arg3, boolean arg4, int arg5,
			boolean arg6) throws IOException, IllegalStateException,
			IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object findAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAttribute(String arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration getAttributeNamesInScope(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAttributesScope(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ExpressionEvaluator getExpressionEvaluator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VirtualJspWriter getOut() {
		return jspWriter;
	}

	@Override
	public VariableResolver getVariableResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAttribute(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAttribute(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAttribute(String arg0, Object arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
