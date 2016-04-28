<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:bean id="pageTemplateHelper" name="com.runtech.onlineshop.form.PageTemplateForm"></s:bean>
<s:bean id="pageComponentHelper" name="com.runtech.onlineshop.form.PageComponentForm"></s:bean>
<s:set name="pager" value="pager" scope="request"/>

<s:form>
	<runtech:page pager="${pager}" command="paging"></runtech:page>
	<tt:table value="modelList" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.templateReference.name')}" sortKey="referenceName" sortable="true" >
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="referenceName" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.template.name')}" sortKey="pageTemplateName" sortable="true" >
			<a href="template.do?formId=<s:property value='pageTemplate.id'/>">
				<s:property value="pageTemplate.name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.pageComponent.name')}" sortKey="pageComponentName" sortable="true">
			<a href="pageComponent.do?formId=<s:property value='pageComponent.id'/>">
				<s:property value="pageComponent.name" />
			</a>
		</tt:column>
	</tt:table>
	<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.delete')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE}');"></s:submit>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:select name="pageTemplate.id" label="%{getText('text.pageTemplate.id')}" list="#pageTemplateHelper.allPageTemplates" listKey="id" listValue="name"/>
	<s:select name="pageComponent.id" label="%{getText('text.pageComponent.id')}" list="#pageComponentHelper.allPageComponents" listKey="id" listValue="name"/>
	<s:textfield name="referenceName" label="%{getText('text.templateReference.name')}"/>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_SAVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_SAVE}" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>
