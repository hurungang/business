package com.runtech.web.runtime;


public class RuntechRuntimeException extends Exception {

	public RuntechRuntimeException(String string) {
		super(string);
	}

	public RuntechRuntimeException(Exception e) {
		super(e);
	}

}
