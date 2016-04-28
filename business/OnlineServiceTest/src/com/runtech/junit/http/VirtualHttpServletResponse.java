/*
 * Created on Nov 8, 2007
 * Created by Harry
 */
package com.runtech.junit.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;



public class VirtualHttpServletResponse implements HttpServletResponse{

    private String redirectUrl;
	private int error;
	public StringWriter out = new StringWriter();
	private VirtualServletOutputStream virtualServletOutputStream;
	private String characterEncoding;

	public VirtualHttpServletResponse() {
		super();
		virtualServletOutputStream = new VirtualServletOutputStream();
	}

	/* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#addCookie(javax.servlet.http.Cookie)
     */
    public void addCookie(Cookie arg0) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#containsHeader(java.lang.String)
     */
    public boolean containsHeader(String arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#encodeURL(java.lang.String)
     */
    public String encodeURL(String url) {
        // TODO Auto-generated method stub
        return encode(url);
    }

	private String encode(String url) {
		return URLEncoder.encode(url);
	}

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#encodeRedirectURL(java.lang.String)
     */
    public String encodeRedirectURL(String url) {
        // TODO Auto-generated method stub
        return encode(url);
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#encodeUrl(java.lang.String)
     */
    public String encodeUrl(String url) {
        // TODO Auto-generated method stub
        return encode(url);
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#encodeRedirectUrl(java.lang.String)
     */
    public String encodeRedirectUrl(String url) {
        // TODO Auto-generated method stub
        return encode(url);
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#sendError(int, java.lang.String)
     */
    public void sendError(int errorValue, String arg1) throws IOException {
        this.error = errorValue;
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#sendError(int)
     */
    public void sendError(int errorValue) throws IOException {
        // TODO Auto-generated method stub
        this.error = errorValue;
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#sendRedirect(java.lang.String)
     */
    public void sendRedirect(String redirectUrlValue) throws IOException {
        // TODO Auto-generated method stub
        this.redirectUrl = redirectUrlValue;
    }

	/**
	 * @return Returns the redirectUrl.
	 */
	public String getRedirectUrl() {
		return redirectUrl;
	}
	
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#setDateHeader(java.lang.String, long)
     */
    public void setDateHeader(String arg0, long arg1) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#addDateHeader(java.lang.String, long)
     */
    public void addDateHeader(String arg0, long arg1) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#setHeader(java.lang.String, java.lang.String)
     */
    public void setHeader(String arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#addHeader(java.lang.String, java.lang.String)
     */
    public void addHeader(String arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#setIntHeader(java.lang.String, int)
     */
    public void setIntHeader(String arg0, int arg1) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#addIntHeader(java.lang.String, int)
     */
    public void addIntHeader(String arg0, int arg1) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#setStatus(int)
     */
    public void setStatus(int arg0) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#setStatus(int, java.lang.String)
     */
    public void setStatus(int arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#getCharacterEncoding()
     */
    public String getCharacterEncoding() {
        // TODO Auto-generated method stub
        return this.characterEncoding;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#getContentType()
     */
    public String getContentType() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#getOutputStream()
     */
    public ServletOutputStream getOutputStream() throws IOException {
        return virtualServletOutputStream;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#getWriter()
     */
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(out);
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#setCharacterEncoding(java.lang.String)
     */
    public void setCharacterEncoding(String arg0) {
        // TODO Auto-generated method stub
        this.characterEncoding = arg0;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#setContentLength(int)
     */
    public void setContentLength(int arg0) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#setContentType(java.lang.String)
     */
    public void setContentType(String arg0) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#setBufferSize(int)
     */
    public void setBufferSize(int arg0) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#getBufferSize()
     */
    public int getBufferSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#flushBuffer()
     */
    public void flushBuffer() throws IOException {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#resetBuffer()
     */
    public void resetBuffer() {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#isCommitted()
     */
    public boolean isCommitted() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#reset()
     */
    public void reset() {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#setLocale(java.util.Locale)
     */
    public void setLocale(Locale arg0) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#getLocale()
     */
    public Locale getLocale() {
        // TODO Auto-generated method stub
        return null;
    }

	/**
	 * @return
	 */
	public int getError() {
		// TODO Auto-generated method stub
		return this.error;
	}
    
}