package com.runtech.junit.http;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;

public class VirtualBodyContent extends BodyContent {

	private VirtualJspWriter jspWriter;

	public VirtualBodyContent(VirtualJspWriter jspWriterValue) {
		super(jspWriterValue);
		this.jspWriter = jspWriterValue;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Reader getReader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return jspWriter.getContent();
	}

	@Override
	public void writeOut(Writer arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearBuffer() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRemaining() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void newLine() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(boolean arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(char arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(int arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(long arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(float arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(double arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(char[] arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(String arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(Object arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void println() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void println(boolean arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void println(char arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void println(int arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void println(long arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void println(float arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void println(double arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void println(char[] arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void println(String arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void println(Object arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		// TODO Auto-generated method stub

	}

}
