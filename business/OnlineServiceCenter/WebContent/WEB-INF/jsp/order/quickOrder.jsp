<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:bean id="quickOrderHelper" name="com.runtech.onlineshop.form.quickOrderForm"></s:bean>
<s:bean id="pageTemplateHelper" name="com.runtech.onlineshop.form.PageTemplateForm"></s:bean>
<s:set name="pager" value="pager" scope="request"/>

<s:form>
	<runtech:page pager="${pager}" command="paging"></runtech:page>
	<tt:table value="modelList" sortName="col1" clientSort="true" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.quickOrder.id')}" sortKey="id" sortable="true" >
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.quickOrder.clientName')}" sortKey="clientName" sortable="true">
			<s:property value="clientName" />
		</tt:column>
		<tt:column title="%{getText('text.quickOrder.contact')}" sortKey="contact" sortable="true" >
				<s:property value="contact" />
		</tt:column>
		<tt:column title="%{getText('text.quickOrder.contactPhone')}" sortKey="contactPhone" sortable="true">
			<s:property value="contactPhone" />
		</tt:column>
		<tt:column title="%{getText('text.quickOrder.dealTime')}" sortKey="dealTime" sortable="true">
			<s:property value="dealTime" />
		</tt:column>
		<tt:column title="%{getText('text.quickOrder.type')}" sortKey="type" sortable="true">
			<s:property value="type" />
		</tt:column>
	</tt:table>
	<s:submit cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left"></s:submit>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:select name="parentquickOrderId" label="%{getText('text.quickOrder.parentModule')}"  list='@com.runtech.onlineshop.form.quickOrderForm@getAllModules(@com.runtech.web.util.Constant@CODE_SITE_MODULE_ROOT," - ")' listKey="id" listValue="name" emptyOption="true"/>
	<s:select name="pageTemplateId" label="%{getText('text.quickOrder.pageTemplate')}" list="#pageTemplateHelper.allPageTemplates" listKey="id" listValue="name"/>
	<s:textfield name="code" label="%{getText('text.quickOrder.code')}"></s:textfield>
	<s:textfield name="name" label="%{getText('text.quickOrder.name')}"></s:textfield>
	<s:textfield name="type" label="%{getText('text.quickOrder.type')}" size="60"></s:textfield>
	<s:textfield name="url" label="%{getText('text.quickOrder.url')}" size="60"></s:textfield>
	<s:textfield name="queryString" label="%{getText('text.quickOrder.queryString')}" size="60"></s:textfield>
	<s:textfield name="title" label="%{getText('text.quickOrder.title')}" size="60"></s:textfield>
	<s:textarea name="subtitle" label="%{getText('text.quickOrder.subtitle')}" cols="48"></s:textarea>
	<s:textfield name="priority" label="%{getText('text.quickOrder.priority')}" size="60"></s:textfield>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_SAVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_SAVE}" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>
