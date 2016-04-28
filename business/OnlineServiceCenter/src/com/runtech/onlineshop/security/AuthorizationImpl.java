package com.runtech.onlineshop.security;

import com.runtech.web.model.Authorization;

public class AuthorizationImpl implements Authorization {

	private static final int ACCESS_SUPERVISE = 8;
	private static final int ACCESS_EXECUTE = 4;
	private static final int ACCESS_WRITABLE = 2;
	private static final int ACCESS_READABLE = 1;
	private boolean supervisable = false;
	private boolean executable = false;
	private boolean writable = false;
	private boolean readable = false;

	private Integer accessCode;
	
	public AuthorizationImpl(Integer accessCodeValue) {
		int tempAccessCode = accessCodeValue;
		if(tempAccessCode>=ACCESS_SUPERVISE){
			this.supervisable = true;
			tempAccessCode = tempAccessCode % ACCESS_SUPERVISE;
		}
		if(tempAccessCode>=ACCESS_EXECUTE){
			this.executable = true;
			tempAccessCode = tempAccessCode % ACCESS_EXECUTE;
		}
		if(tempAccessCode>=ACCESS_WRITABLE){
			this.writable = true;
			tempAccessCode = tempAccessCode % ACCESS_EXECUTE;
		}
		if(tempAccessCode>=ACCESS_READABLE){
			this.readable = true;
			tempAccessCode = tempAccessCode % ACCESS_EXECUTE;
		}
	}

	public AuthorizationImpl() {
	}

	public static Integer generateAccessCode(Authorization authorization){
		int tempAccessCode = 0;
		if(authorization.isReadable()){
			tempAccessCode += ACCESS_READABLE;
		}
		if(authorization.isWritable()){
			tempAccessCode += ACCESS_WRITABLE;
		}
		if(authorization.isExecutable()){
			tempAccessCode += ACCESS_EXECUTE;
		}
		if(authorization.isSupervisable()){
			tempAccessCode += ACCESS_SUPERVISE;
		}
		return tempAccessCode;
	}
	
	public Integer getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(Integer accessCode) {
		this.accessCode = accessCode;
	}
	
	public boolean isSupervisable() {
		return supervisable;
	}
	
	public boolean isExecutable() {
		return executable;
	}

	public boolean isWritable() {
		return writable;
	}

	public boolean isReadable() {
		return readable;
	}
	public void setSupervisable(boolean supervisable) {
		this.supervisable = supervisable;
	}

	public void setExecutable(boolean executable) {
		this.executable = executable;
	}

	public void setWritable(boolean writable) {
		this.writable = writable;
	}

	public void setReadable(boolean readable) {
		this.readable = readable;
	}
	
}
