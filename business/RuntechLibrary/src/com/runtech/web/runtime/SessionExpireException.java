package com.runtech.web.runtime;

import com.runtech.web.util.Constant;

public class SessionExpireException extends ModelException {

	public SessionExpireException(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}

	public SessionExpireException() {
		super(Constant.ERROR_SESSION_EXPIRE);
		// TODO Auto-generated constructor stub
	}

}
