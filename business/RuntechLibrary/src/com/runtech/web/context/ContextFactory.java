package com.runtech.web.context;

import java.io.IOException;

import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.runtime.RuntechProperties;
import com.runtech.web.runtime.RuntechRuntimeException;

public class ContextFactory {

	public static RuntechContext createContext() throws RuntechRuntimeException {
		
		try {
			String contextImpl = RuntechProperties.getContextImpl();
			if(contextImpl!=null){
				return (RuntechContext) Class.forName(contextImpl).newInstance();
			}else{
				return null;
			}
		} catch (IOException e) {
			throw new RuntechRuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntechRuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntechRuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntechRuntimeException(e);
		}
	}

}
