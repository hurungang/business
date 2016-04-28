<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/runtech-tags" prefix="runtech" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<runtech:layout id="html" template="/template/login_template.jsp" errorPage="/WEB-INF/jsp/default/error.jsp">
	<runtech:layoutBody name="$BODY$">
		<s:actionerror/>
		<s:form action="loginAction">
			<s:textfield cssClass="text-input medium-input" id="userName" name="userName" value="freegoo" label="%{getText('label.login.userName')}"/>
			<s:password cssClass="text-input medium-input" id="userPassword" name="userPassword" value="810603" label="%{getText('label.login.userPassword')}"/>
			<div style="float: right"><s:submit cssClass="button" theme="simple" id="submit" name="submit" align="right"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="button" href="adminLogin.do"/><s:property value="%{getText('label.login.adminLogin')}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		</s:form>
	</runtech:layoutBody>
</runtech:layout>