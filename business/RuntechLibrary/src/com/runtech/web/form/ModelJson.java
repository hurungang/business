package com.runtech.web.form;

import java.io.Serializable;
import java.util.Map;

import com.runtech.web.runtime.ModelException;

public interface ModelJson{
	public Map toMap();

	public void setJsonId(String id);

	public void copyFrom(Object modelResult) throws ModelException;

	public Object getModel() throws ModelException;

	public Serializable getId();
}
