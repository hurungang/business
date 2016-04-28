package com.runtech.junit.http;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspWriter;

public class VirtualJspWriter extends JspWriter {

	private static final int BUFFER_SIZE = 1024;
	private static final boolean AUTO_FLUSH = true;
	StringWriter stringWriter;
	
	public VirtualJspWriter() {
		super(BUFFER_SIZE,AUTO_FLUSH);
		stringWriter =  new StringWriter(bufferSize);
	}

	protected VirtualJspWriter(int bufferSize, boolean autoFlush) {
		super(bufferSize,autoFlush);
		stringWriter =  new StringWriter(bufferSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void write(String str) throws IOException {
		// TODO Auto-generated method stub
		stringWriter.write(str);
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
	public void flush() throws IOException {
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
		this.stringWriter.write(arg0);
		this.flush();
	}

	@Override
	public void print(Object arg0) throws IOException {
		this.stringWriter.write(arg0.toString());
		this.flush();
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
		stringWriter.write(arg0);
	}

	@Override
	public void println(Object arg0) throws IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		// TODO Auto-generated method stub

	}
	
	public String getContent(){
		return stringWriter.getBuffer().toString();
	}
}
