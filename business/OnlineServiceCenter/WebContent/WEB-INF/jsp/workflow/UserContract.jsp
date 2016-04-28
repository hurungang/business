<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>

<s:push value="getDocument(documentType,formId)">

	<div class="input_container">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:hidden name="documentType" value="%{documentType}"></s:hidden>
	<s:hidden name="documentStatus" value="%{status}"></s:hidden>
	<input type="hidden" name="nextLocation" value="ajax/<s:property value='pageCode'/>.do?userId=<s:property value='user.id'/>"/>
	<s:select name="contractType" label="%{getText('text.contract.contractType')}" list="%{@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_CONTRACT_TYPE)}" listKey="code" listValue="name" disabled="true"/>
	<s:textfield name="signTime" value="%{getText('format.date',{signTime})}" label="%{getText('text.contract.signTime')}" cssClass="text-input" readonly="true"></s:textfield>
	<s:textfield name="startTime" value="%{getText('format.date',{startTime})}" label="%{getText('text.contract.startTime')}" cssClass="text-input" readonly="true"></s:textfield>
	<s:textfield name="endTime" value="%{getText('format.date',{endTime})}" label="%{getText('text.contract.endTime')}"  cssClass="text-input" readonly="true"></s:textfield>
	<s:select name="serviceAdminId" value="context.user.id" label="%{getText('text.contract.serviceAdmin')}" list="@com.runtech.onlineshop.form.AdminForm@getAllAdmins()" emptyOption="true" listKey="id" listValue="fullName" disabled="true"/>
	<s:if test="file!=null"><a class="download align-right" target="_blank" href="<%=request.getContextPath()%>/protect/ajax/<s:property value='pageCode'/>.do?actionType=<s:property value="%{@com.runtech.web.util.Constant@ACTION_DOWNLOAD}"/>&formId=<s:property value="formId"/>">&nbsp;</a></s:if>
	<s:hidden name="contractFilePath"/>
	</div>
	<div class="input_container">
	<s:textarea cssClass="wysiwyg" labelposition="top" name="contractContent" label="%{getText('text.content.content')}" cols="100" readonly="true"></s:textarea>

	</div>
</s:push>