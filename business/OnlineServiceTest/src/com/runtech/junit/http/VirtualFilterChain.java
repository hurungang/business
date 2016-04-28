/*
 * Created on Nov 12, 2007
 * Created by Harry
 */
package com.runtech.junit.http;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class VirtualFilterChain implements FilterChain {

    
	private boolean doFilter;

	/* (non-Javadoc)
	 * @see javax.servlet.FilterChain#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	public void doFilter(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		this.doFilter = true;
	}

	/**
	 * @return Returns the doFilter.
	 */
	public boolean isDoFilter() {
		return doFilter;
	}
}
