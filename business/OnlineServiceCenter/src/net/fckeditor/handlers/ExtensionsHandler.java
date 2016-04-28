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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.fckeditor.tool.Utils;

/**
 * This handler manages the allowed and denied extensions for each resource
 * type. The extensions are preset by the properties managed by
 * {@link PropertiesLoader}.
 * <p>
 * <em>Hint</em>: It's recommend to use either allowed or denied extensions for
 * one file type. <strong>Never</strong> use both at the same time! That's why
 * denied extensions of a file type will be deleted if you set the allowed one
 * and vice versa.
 * </p>
 * 
 * @version $Id: ExtensionsHandler.java,v 1.1 2010/05/02 02:49:34 runtechcvs Exp $
 * @deprecated Class will be removed in FCKeditor.Java 2.6, functionality merged
 *             into {@link ResourceType}.
 * @see ResourceType
 */
@Deprecated
public class ExtensionsHandler {

	private static Map<ResourceType, Set<String>> extensionsAllowed = new HashMap<ResourceType, Set<String>>();
	private static Map<ResourceType, Set<String>> extensionsDenied = new HashMap<ResourceType, Set<String>>();

	static {
		// load defaults
		extensionsAllowed.put(ResourceType.FILE, Utils.getSet(PropertiesLoader
		    .getProperty("connector.resourceType.file.extensions.allowed")));
		extensionsDenied.put(ResourceType.FILE, Utils.getSet(PropertiesLoader
		    .getProperty("connector.resourceType.file.extensions.denied")));
		extensionsAllowed.put(ResourceType.MEDIA, Utils.getSet(PropertiesLoader
		    .getProperty("connector.resourceType.media.extensions.allowed")));
		extensionsDenied.put(ResourceType.MEDIA, Utils.getSet(PropertiesLoader
		    .getProperty("connector.resourceType.media.extensions.denied")));
		extensionsAllowed.put(ResourceType.IMAGE, Utils.getSet(PropertiesLoader
		    .getProperty("connector.resourceType.image.extensions.allowed")));
		extensionsDenied.put(ResourceType.IMAGE, Utils.getSet(PropertiesLoader
		    .getProperty("connector.resourceType.image.extensions.denied")));
		extensionsAllowed.put(ResourceType.FLASH, Utils.getSet(PropertiesLoader
		    .getProperty("connector.resourceType.flash.extensions.allowed")));
		extensionsDenied.put(ResourceType.FLASH, Utils.getSet(PropertiesLoader
		    .getProperty("connector.resourceType.flash.extensions.denied")));
	}

	/**
	 * Getter for the allowed extensions of a file type.
	 * 
	 * @param type
	 *          The file type.
	 * @return Set of allowed extensions or an empty set.
	 */
	public static Set<String> getExtensionsAllowed(final ResourceType type) {
		return extensionsAllowed.get(type);
	}

	/**
	 * Setter for the allowed extensions of a file type. The denied extensions
	 * will be cleared.<br />
	 * If <code>extensionsList</code> is <code>null</code>, allowed
	 * extensions are kept untouched.
	 * 
	 * @param type
	 *            The file type.
	 * @param extensionsList
	 *            Required format: <code>ext1&#124;ext2&#124;ext3</code>
	 */
	public static void setExtensionsAllowed(final ResourceType type, final String extensionsList) {
		if (extensionsList != null) {
			extensionsAllowed.put(type, Utils.getSet(extensionsList));
			extensionsDenied.get(type).clear();
		}
	}

	/**
	 * Getter for the denied extensions of a file type.
	 * 
	 * @param type
	 *            The file type.
	 * @return Set of denied extensions or an empty set.
	 */
	public static Set<String> getExtensionsDenied(final ResourceType type) {
		return extensionsDenied.get(type);
	}

	/**
	 * Setter for the denied extensions of a file type. The allowed extensions
	 * will be cleared.<br />
	 * If <code>extensionsList</code> is <code>null</code>, denied
	 * extensions are kept untouched.
	 * 
	 * @param type
	 *            The file type.
	 * @param extensionsList
	 *            Required format: <code>ext1&#124;ext2&#124;ext3</code>
	 */
	public static void setExtensionsDenied(final ResourceType type, final String extensionsList) {
		if (extensionsList != null) {
			extensionsDenied.put(type, Utils.getSet(extensionsList));
			extensionsAllowed.get(type).clear();
		}
	}

	/**
	 * Checks if an extension is allowed for a file type.
	 * 
	 * @param type
	 *            The resource type you want to check.
	 * @param extension
	 *            The extension you want to check.
	 * @return <code>true</code> is extension is allowed else
	 *         <code>false</code>. <em>Attention</em>: <code>false</code>
	 *         is always returned if 'type' or 'extensions' is <code>null</code>.
	 */
	public static boolean isAllowed(final ResourceType type, final String extension) {
		if (type == null || extension == null)
			return false;
		String ext = extension.toLowerCase();
		Set<String> allowed = extensionsAllowed.get(type);
		Set<String> denied = extensionsDenied.get(type);
		if (allowed.isEmpty())
			return !denied.contains(ext);
		if (denied.isEmpty())
			return allowed.contains(ext);
		return false;
	}
}
