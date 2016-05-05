<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/runtech-tags" prefix="runtech" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set name="menu" value="menu" scope="request"/>
<div id="platform"><s:text name="text.platform.title"/></div>
<div id="poweredBy"><span id="version"><s:text name="text.platform.version"/></span><s:text name="text.platform.poweredBy"/></div>
<div id="welcome"><s:if test="#session.user.name!=null"><s:text name="text.platform.welcome">
	<s:param value="#session.user.fullName"></s:param>
	<s:param value="#session.user.adminRole.name"></s:param>
</s:text><a href="../../logout.do"><s:text name="text.platform.logout"/></a></s:if></div>
<div id="topNav">
	<runtech:menu root="${menu}" showRoot="false" name="menuItem" depthName="menuDepth" depth="1">
		<s:if test="#attr.menuItem.visible">
			<span id="menu_${menuItem.id}" class="menu_${menuDepth}_${menuItem.active}"><a href="../${menuItem.code}/${menuItem.code}.do">${menuItem.name}</a></span>
		</s:if>
		<s:else>
		</s:else>
	</runtech:menu>
</div>
