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
	<s:textfield name="maintainTimeString" readonly="true" label="%{getText('text.customerMaintain.maintainTime')}" cssClass="text-input datepicker"></s:textfield>
	<s:select name="serviceAdminId" value="context.user.id" label="%{getText('text.customerMaintain.serviceAdmin')}" list="@com.runtech.onlineshop.form.AdminForm@getAllAdmins()" listKey="id" listValue="fullName"/>
	<s:textarea name="summary" disabled="true" readonly="true" label="%{getText('text.customerMaintain.summary')}" cols="113"></s:textarea>
	</div>
	<div class="input_container">	
	<s:textarea cssClass="wysiwyg" labelposition="top" name="detail" label="%{getText('text.customerMaintain.detail')}"></s:textarea>
	</div>
	<div class="input_container">	
	<s:textfield name="opportunityValue" readonly="true" label="%{getText('text.customerMaintain.opportunityValue')}" ></s:textfield>
	<s:textfield name="opportunityPossibilityPercentage" readonly="true" label="%{getText('text.customerMaintain.opportunityPossibility')}" cssClass="text-input small-input"></s:textfield><span class="input-suffix">%</span>
	<s:textfield name="opportunityTimeString" readonly="true" label="%{getText('text.customerMaintain.opportunityTime')}" cssClass="text-input datepicker"></s:textfield>
	<s:select name="opportunityStatus" disabled="true" label="%{getText('text.customerMaintain.opportunityStatus')}" list="%{@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_OPPORTUNITY_STATUS)}" listKey="code" listValue="name"/>
	</div>
	<div class="input_container">	
	<s:textarea cssClass="wysiwyg disabled" labelposition="top" name="opportunity" label="%{getText('text.customerMaintain.opportunity')}"></s:textarea>
	</div>
</s:push>