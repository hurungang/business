package com.runtech.web.helper;

import com.runtech.web.dispatcher.RuntechContext;
import com.runtech.web.runtime.ModelException;

public interface ModelHelper {
	boolean execute(RuntechContext context) throws ModelException;
}
