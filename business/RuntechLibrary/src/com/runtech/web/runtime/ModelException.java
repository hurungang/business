package com.runtech.web.runtime;

public class ModelException extends Exception {

	private String message;

	public ModelException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ModelException(String string) {
		this.fillInStackTrace();
		this.message = string;
	}

	public String getMessage() {
		if(this.message!=null){
			return this.message;
		}else{
			return super.getMessage();
		}
	}
	
}
