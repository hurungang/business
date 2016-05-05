<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<runtech:layout id="html" template="/template/template.jsp" errorPage="/WEB-INF/jsp/default/error.jsp">
	<runtech:layoutBody name="$BODY$">
		Welcome <s:property value="#session.user.name"/>!
	</runtech:layoutBody>
</runtech:layout>