package com.runtech.junit.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

public class VirtualServletOutputStream extends ServletOutputStream {

	private ByteArrayOutputStream buff = null;

	public VirtualServletOutputStream() {
		super();
		buff = new ByteArrayOutputStream();
	}

	@Override
	public void write(int b) throws IOException {
		buff.write(b);
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#close()
	 */
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		buff.close();
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#flush()
	 */
	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		buff.flush();
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(byte[])
	 */
	@Override
	public void write(byte[] b) throws IOException {
		// TODO Auto-generated method stub
		buff.write(b);
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(byte[], int, int)
	 */
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		buff.write(b, off, len);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new String(buff.toByteArray());
	}

}
