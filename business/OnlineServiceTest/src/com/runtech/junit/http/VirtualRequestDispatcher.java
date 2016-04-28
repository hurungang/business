/*
 * Created on Nov 12, 2007
 * Created by Harry
 */
package com.runtech.junit.http;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class VirtualRequestDispatcher implements RequestDispatcher {

	private String url;

	/* (non-Javadoc)
	 * @see javax.servlet.RequestDispatcher#forward(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	public void forward(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.RequestDispatcher#include(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	public void include(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * set the url
	 * @param urlValue
	 */
	public void setUrl(String urlValue) {
		this.url = urlValue;
	}

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}
}
