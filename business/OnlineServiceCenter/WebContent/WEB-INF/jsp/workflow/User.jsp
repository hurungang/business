<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:bean id="area" name="com.runtech.onlineshop.form.AreaForm"></s:bean>

	<div class="input_container">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:hidden name="documentType" value="%{documentType}"></s:hidden>
	<s:hidden name="documentStatus" value="%{status}"></s:hidden>
	<s:select disabled="true" name="areaId" label="%{getText('text.customer.areaId')}" list="#area.allAreas" listKey="id" listValue="name"/>
	<s:textfield readonly="true" name="name" label="%{getText('text.customer.name')}" required="true"></s:textfield>
	<s:textfield readonly="true" name="fullName" label="%{getText('text.customer.fullName')}" required="true"></s:textfield>
	<s:textfield readonly="true" name="email" label="%{getText('text.customer.email')}" ></s:textfield>
	<s:if test="picturePath!=null">
		<a class="download align-right" target="_blank" href="<s:property value='picturePath'/>">&nbsp;</a>
	</s:if>
	<s:select disabled="true" name="userType" label="%{getText('text.customer.userType')}" list="@com.runtech.onlineshop.form.SystemCodeForm@getCodes('userType')" listKey="code" listValue="name"/>
	<s:select disabled="true" name="serviceAdminId" value="context.user.id" label="%{getText('text.customer.serviceAdmin')}" list="@com.runtech.onlineshop.form.AdminForm@getAllAdmins()" emptyOption="true" listKey="id" listValue="fullName"/>
	<s:textfield readonly="true" name="contact" label="%{getText('text.customer.contact')}" ></s:textfield>
	<s:textfield readonly="true" name="contactQQ" label="%{getText('text.customer.contactQQ')}" ></s:textfield>
	<s:textfield readonly="true" name="contactOther" label="%{getText('text.customer.contactOther')}" ></s:textfield>
	<s:textfield readonly="true" name="phone" label="%{getText('text.customer.phone')}" ></s:textfield>
	<s:textfield readonly="true" name="mobile" label="%{getText('text.customer.mobile')}" ></s:textfield>
	<s:textfield readonly="true" name="website" label="%{getText('text.customer.website')}" ></s:textfield>
	<s:textfield readonly="true" name="fax" label="%{getText('text.customer.fax')}" ></s:textfield>
	<s:select disabled="true" name="credentialType" label="%{getText('text.customer.credentialType')}" list="@com.runtech.onlineshop.form.SystemCodeForm@getCodes('credentialType')" listKey="code" listValue="name"/>
	<s:textfield readonly="true" name="credentialNumber" label="%{getText('text.customer.credentialNumber')}" ></s:textfield>
	<s:select disabled="true" name="importance" label="%{getText('text.customer.importance')}" list="@com.runtech.onlineshop.form.SystemCodeForm@getCodes('userImportance')" listKey="code" listValue="name"/>
	<s:textfield readonly="true" name="address" label="%{getText('text.customer.address')}" ></s:textfield>
	<s:textarea readonly="true" name="remark" label="%{getText('text.customer.remark')}" cols="45" rows="5"></s:textarea>

	</div>
