<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/runtech-tags" prefix="runtech" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<runtech:layout id="html" template="/template/main_template.jsp" errorPage="/WEB-INF/jsp/default/error.jsp">
	<runtech:layoutBody name="$LEFT_NAVIGATION$" page="/WEB-INF/jsp/default/leftNavigation.jsp">
		Non Defined Content.
	</runtech:layoutBody>
	<runtech:layoutBody name="$BODY$" page="${param.$body}">
		Non Defined Content.
	</runtech:layoutBody>
</runtech:layout>