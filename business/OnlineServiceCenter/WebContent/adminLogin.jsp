<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/runtech-tags" prefix="runtech" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<runtech:layout id="html" template="/template/login_template.jsp" errorPage="/WEB-INF/jsp/default/error.jsp">
	<runtech:layoutBody name="$BODY$">
		<s:actionerror/>
		<s:form action="adminLoginAction">
			<s:textfield cssClass="text-input medium-input" id="userName" name="userName" label="%{getText('label.login.userName')}"/>
			<s:password cssClass="text-input medium-input" id="userPassword" name="userPassword" label="%{getText('label.login.userPassword')}"/>
			<s:submit cssClass="button" id="submit" name="submit" align="middle"/>
		</s:form>
	</runtech:layoutBody>
</runtech:layout>