package com.runtech.web.model;

public interface Authorization {
	public boolean isExecutable() ;

	public boolean isWritable() ;

	public boolean isReadable() ;

	public boolean isSupervisable();
}
