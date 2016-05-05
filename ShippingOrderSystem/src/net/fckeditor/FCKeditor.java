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
package net.fckeditor;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.handlers.PropertiesLoader;
import net.fckeditor.tool.Compatibility;
import net.fckeditor.tool.Utils;
import net.fckeditor.tool.XHtmlTagTool;

/**
 * Java representation of the <a href="http://docs.fckeditor.net/FCKeditor_2.x/Developers_Guide/Server_Side_Integration#FCKeditor_Creator"
 * target="_blank">FCKeditor</a>. This representation reflects the editor in an
 * object-oriented way. It can be configured as any other JavaBean type class.
 * The final output of this class is HTML code.<br />
 * <strong>Note:</strong> It's your responsibility to supply reasonable and
 * valid values, only {@code request} and {@code instanceName} will be checked
 * for sanity.
 * 
 * @version $Id: FCKeditor.java,v 1.1 2010/05/02 02:50:23 runtechcvs Exp $
 */
public class FCKeditor {

	private FCKeditorConfig fckConfig = new FCKeditorConfig();
	private String instanceName;
	private HttpServletRequest request;

	// defaults
	private String value = Utils.EMPTY_STRING;
	private String toolbarSet = PropertiesLoader.getEditorToolbarSet();
	private String width = PropertiesLoader.getEditorWidth();
	private String height = PropertiesLoader.getEditorHeight();
	private String basePath = PropertiesLoader.getEditorBasePath();

	/**
	 * Class constructor with all basic parameters.
	 * 
	 * A constructors which handles basic FCKeditor initialization with a few
	 * parameters. If you omit basic parameters, default ones will be taken from
	 * the {@link PropertiesLoader properties file}.
	 * 
	 * @param request
	 *            current user request instance
	 * @param instanceName
	 *            the unique name of this editor
	 * @param width
	 *            the desired editor width (CSS-style value)
	 * @param height
	 *            the desired editor height (CSS-style value)
	 * @param toolbarSet
	 *            the desired toolbar set name
	 * @param value
	 *            the HTML markup of this editor. Markup will be properly
	 *            escaped.
	 * @param basePath
	 *            the base path of this editor, absolute to the context
	 * @throws IllegalArgumentException
	 *             if instanceName is empty or not a valid XHTML id
	 */
	public FCKeditor(final HttpServletRequest request,
			final String instanceName, final String width, final String height,
			final String toolbarSet, final String value, final String basePath) {

		this(request, instanceName);
		this.width = width;
		this.height = height;
		this.toolbarSet = toolbarSet;
		this.value = value;
		this.basePath = basePath;

	}

	/**
	 * Class constructor with a minimal set of parameters.
	 * 
	 * Omitted parameters will be set to default values.
	 * 
	 * @param request
	 *            current user request instance
	 * @param instanceName
	 *            the unique name of this editor
	 * @throws IllegalArgumentException
	 *             if instanceName is empty or not a valid HTML id
	 */
	public FCKeditor(final HttpServletRequest request, final String instanceName) {

		if (request == null)
			throw new NullPointerException("the request cannot be null");
		this.request = request;

		setInstanceName(instanceName);

	}

	/**
	 * Sets the unique name of this editor.
	 * 
	 * @param instanceName
	 *            the unique name of this editor
	 * @throws IllegalArgumentException
	 *             if instanceName is empty or not a valid XHTML id
	 */
	public void setInstanceName(final String instanceName) {
		if (Utils.isEmpty(instanceName))
			throw new IllegalArgumentException("instanceName cannot be empty");
		if (!instanceName.matches("\\p{Alpha}[\\p{Alnum}:_.-]*"))
			throw new IllegalArgumentException(
					"instanceName must be a valid XHTML id containing only \"\\p{Alpha}[\\p{Alnum}:_.-]*\"");
		this.instanceName = instanceName;
	}

	/**
	 * Sets the initial value to be edited as HTML markup.
	 * 
	 * @param value
	 *            the HTML markup of this editor. Markup will be properly
	 *            escaped.
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * Sets the base path of this editor. The base path reflects the location of
	 * the editor files absolute to the context root <i>not</i> the server root.
	 * 
	 * @param basePath
	 *            the base path of this editor, absolute to the context
	 */
	public void setBasePath(final String basePath) {
		this.basePath = basePath;
	}

	/**
	 * Sets the name of the toolbar set of this editor.
	 * 
	 * @param toolbarSet
	 *            the desired toolbar set name
	 */
	public void setToolbarSet(final String toolbarSet) {
		this.toolbarSet = toolbarSet;
	}

	/**
	 * Sets the width of this editor. This value can be any valid CSS width
	 * value.
	 * 
	 * @param width
	 *            the desired editor width (CSS-style value)
	 */
	public void setWidth(final String width) {
		this.width = width;
	}

	/**
	 * Sets the height of this editor. This value can be any valid CSS height
	 * value.
	 * 
	 * @param height
	 *            the desired editor height (CSS-style value)
	 */
	public void setHeight(final String height) {
		this.height = height;
	}

	/**
	 * Gets the advanced configuration map. Each configuration element has to be
	 * set individually in this map.<br />
	 * The editor provides already a system-wide configuration through the
	 * <code>config.js</code> file. By adding elements to this map you can
	 * override the configuration for each editor instance.
	 * 
	 * @deprecated Method will be removed in FCKeditor.Java 2.6, use
	 *             {@link FCKeditor#getConfig(String)}.
	 * @see #getConfig(String)
	 * @return configuration configuration map for this editor instance
	 */
	@Deprecated
	public FCKeditorConfig getConfig() {
		return fckConfig;
	}

	/**
	 * Returns a configuration option. See {@link FCKeditorConfig} for more
	 * details.
	 * 
	 * @param name
	 *            the name of the parameter (case-sensitive)
	 * @return the value represented by this parameter, else null
	 */
	public String getConfig(String name) {
		return fckConfig.get(name);
	}

	/**
	 * Sets a configuration option. See {@link FCKeditorConfig} for more
	 * details.
	 * 
	 * @param name
	 *            the name of the config option (case-sensitive)
	 * @param value
	 *            the value of the config option. Null values will be ignored.
	 */
	public void setConfig(String name, String value) {
		if (value != null)
			fckConfig.put(name, value);
	}

	/**
	 * Sets the advanced configuration maps. <strong>Note:</strong> previously
	 * 
	 * @deprecated Method will be removed in FCKeditor.Java 2.6, use
	 *             {@link #setConfig(String, String)}.
	 * @see #setConfig(String, String)
	 * @param config
	 *            configuration collection
	 */
	@Deprecated
	public void setConfig(FCKeditorConfig config) {
		this.fckConfig = config;
	}

	/**
	 * Escapes base XML entities as specified <a
	 * href="http://en.wikipedia.org/wiki/Xml#Entity_references">here</a>.
	 * 
	 * @param str
	 *            string to escape, empty strings will be ignored
	 * @return escaped string
	 */
	private String escapeXml(String str) {

		if (Utils.isEmpty(str))
			return str;

		StringBuffer sb = new StringBuffer();

		int len = str.length();
		char c;

		for (int i = 0; i < len; i++) {

			c = str.charAt(i);
			switch (c) {
			case '&':
				sb.append("&amp;");
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			// XML actually defines &apos; as entity for the apostrophe but we
			// user rather the numerical reference to avoid XHTML 1.0 validation
			// problems
			case '\'':
				sb.append("&#39;");
				break;
			default:
				sb.append(c);
				break;
			}
		}

		return sb.toString();
	}

	/**
	 * Creates the HTML representation of this editor instance. First of all,
	 * this method determines whether the request browser is supported.
	 * According to the result an appropriate HTML representation is assembled
	 * and returned.
	 * 
	 * @return HTML representation of this editor instance
	 */
	@Override
	public String toString() {
		StringBuffer strEditor = new StringBuffer();

		strEditor.append("<div>");
		String encodedValue = escapeXml(value);

		if (Compatibility.isCompatibleBrowser(request)) {
			strEditor.append(createInputForVariable(instanceName, instanceName,
					encodedValue));

			// create config HTML
			String configStr = fckConfig.getUrlParams();
			if (Utils.isNotEmpty(configStr))
				strEditor.append(createInputForVariable(null, instanceName
						.concat("___Config"), configStr));

			// create IFrame
			StringBuffer editorLink = new StringBuffer(request.getContextPath());
			editorLink.append(basePath);
			editorLink.append("/editor/fckeditor.html?InstanceName=").append(
					instanceName);
			if (Utils.isNotEmpty(toolbarSet))
				editorLink.append("&amp;Toolbar=").append(toolbarSet);

			XHtmlTagTool iframeTag = new XHtmlTagTool("iframe",
					XHtmlTagTool.SPACE);
			iframeTag.addAttribute("id", instanceName.concat("___Frame"));
			iframeTag.addAttribute("src", editorLink.toString());
			iframeTag.addAttribute("width", width);
			iframeTag.addAttribute("height", height);
			iframeTag.addAttribute("frameborder", "0");
			iframeTag.addAttribute("scrolling", "no");
			strEditor.append(iframeTag);

		} else {
			XHtmlTagTool textareaTag = new XHtmlTagTool("textarea",
					encodedValue);
			textareaTag.addAttribute("name", instanceName);
			textareaTag.addAttribute("rows", "4");
			textareaTag.addAttribute("cols", "40");
			textareaTag.addAttribute("wrap", "virtual");
			textareaTag.addAttribute("style", "width: ".concat(width).concat(
					"; height: ").concat(height));
		}
		strEditor.append("</div>");
		return strEditor.toString();
	}

	/**
	 * Creates the HTML representation of this editor instance.
	 * 
	 * @see #toString()
	 * @return HTML representation of this editor instance
	 */
	public String createHtml() {
		return toString();
	}

	/**
	 * Creates a hidden input element for the given attributes.
	 * 
	 * @param name
	 *            name attribute of the input tag
	 * @param id
	 *            id attribute of the input tag
	 * @param value
	 *            value attribute of the input tag
	 * @return the produced XHTML tag
	 */
	private String createInputForVariable(final String name, final String id,
			final String value) {
		XHtmlTagTool tag = new XHtmlTagTool("input");
		if (Utils.isNotEmpty(id))
			tag.addAttribute("id", id);
		if (Utils.isNotEmpty(name))
			tag.addAttribute("name", name);
		tag.addAttribute("value", value);
		tag.addAttribute("type", "hidden");
		return tag.toString();
	}
}