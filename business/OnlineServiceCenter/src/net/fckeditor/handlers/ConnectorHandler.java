/*
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2004-2009 Frederico Caldeira Knabben
 * 
 * == BEGIN LICENSE ==
 * 
 * Licensed under the terms of any of the following licenses at your
 * choice:
 * 
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 * 
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 * 
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 * 
 * == END LICENSE ==
 */
package net.fckeditor.handlers;

import net.fckeditor.connector.Connector;
import net.fckeditor.connector.Dispatcher;
import net.fckeditor.tool.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handler for Connector-related properties.<br />
 * Wraps to the {@link PropertiesLoader}.<br />
 * <strong>Attention</strong>: This class will be merged into {@link Dispatcher}
 * since it serves only one reason (Connector instantiation) which will be done
 * more reasonably in the dispatcher directly.
 * 
 * @version $Id: ConnectorHandler.java,v 1.1 2010/05/02 02:49:34 runtechcvs Exp $
 * @ddeprecated Class will be removed in FCKeditor.Java 2.6.
 */
public class ConnectorHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(ConnectorHandler.class);
	private static Connector connector = null;

	static {

		// try to instantiate the Connector object
		String className = PropertiesLoader.getConnectorImpl();
		if (Utils.isEmpty(className))
			logger.error("Empty Connector implementation class name provided");
		else {
			try {
				Class<?> clazz = Class.forName(className);
				connector = (Connector) clazz.newInstance();
				logger.info("Connector initialized to {}", className);
			} catch (Throwable e) {
				logger.error("Connector implementation {} could not be instantiated", className);
				throw new RuntimeException("Connector implementation " + className + " could not be instantiated", e); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Getter for <code>connector.userFilesPath</code> property
	 * 
	 * @return UserFilesPath
	 * @see PropertiesLoader#getUserFilesPath()
	 * @deprecated Method will be removed in FCKeditor.Java 2.6, use
	 *             {@link PropertiesLoader#getUserFilesPath()}.
	 */
	@Deprecated
	public static String getUserFilesPath() {
		return PropertiesLoader.getProperty("connector.userFilesPath");
	}

	/**
	 * Getter for <code>connector.userFilesAbsolutePath</code> property
	 * 
	 * @return UserFilesAbsolutePath
	 * @see PropertiesLoader#getUserFilesAbsolutePath()
	 * @deprecated Method will be removed in FCKeditor.Java 2.6, use
	 *             {@link PropertiesLoader#getUserFilesAbsolutePath()}.
	 */
	@Deprecated
	public static String getUserFilesAbsolutePath() {
		return PropertiesLoader.getProperty("connector.userFilesAbsolutePath");
	}

	/**
	 * Getter for <code>connector.forceSingleExtension</code> property
	 * 
	 * @return ForceSingleExtension
	 * @see PropertiesLoader#isForceSingleExtension()
	 * @deprecated Method will be removed in FCKeditor.Java 2.6, use
	 *             {@link PropertiesLoader#isForceSingleExtension()}.
	 */
	@Deprecated
	public static boolean isForceSingleExtension() {
		return Boolean.valueOf(PropertiesLoader
				.getProperty("connector.forceSingleExtension"));
	}

	/**
	 * Getter for <code>connector.secureImageUploads</code> property
	 * 
	 * @return SecureImageUploads
	 * @see PropertiesLoader#isSecureImageUploads()
	 * @deprecated Method will be removed in FCKeditor.Java 2.6, use
	 *             {@link PropertiesLoader#isSecureImageUploads()}.
	 */
	@Deprecated
	public static boolean isSecureImageUploads() {
		return Boolean.valueOf(PropertiesLoader
				.getProperty("connector.secureImageUploads"));
	}

	/**
	 * Getter for the implementation of {@link Connector}.
	 * 
	 * @return Implementation of {@link Connector}.
	 * @deprecated Method will be removed in FCKeditor.Java 2.6, functionality
	 *             will be merged into {@link Dispatcher}.
	 */
	public static Connector getConnector() {
		return connector;
	}
}
