<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:bean id="module" name="com.runtech.onlineshop.form.AdminModuleForm"></s:bean>
<s:set name="pager" value="pager" scope="request"/>

<s:form>
	<runtech:page pager="${pager}" command="paging"></runtech:page>
	<tt:table value="modelList" sortName="col1" clientSort="true" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.module.id')}" sortKey="id" sortable="true" >
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.module.parentModule')}" sortKey="parent" sortable="true">
			<s:property value="AdminModule.name" />
		</tt:column>
		<tt:column title="%{getText('text.module.code')}" sortKey="code" sortable="true" >
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="code" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.module.name')}" sortKey="name" sortable="true">
			<s:property value="name" />
		</tt:column>
		<tt:column title="%{getText('text.module.page')}" sortKey="page" sortable="true">
			<s:property value="page" />
		</tt:column>
		<tt:column title="%{getText('text.module.modelType')}" sortKey="modelType" sortable="true">
			<s:property value="modelType" />
		</tt:column>
	</tt:table>
	<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.delete')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE}');"></s:submit>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:select name="parentSAdminModuleId" label="%{getText('text.module.parentModule')}" list="#module.allModules" listKey="id" listValue="name" emptyOption="true"/>
	<s:textfield name="code" label="%{getText('text.module.code')}"></s:textfield>
	<s:textfield name="name" label="%{getText('text.module.name')}"></s:textfield>
	<s:textfield name="page" label="%{getText('text.module.page')}" size="60"></s:textfield>
	<s:textfield name="modelType" label="%{getText('text.module.modelType')}" size="60"></s:textfield>
	<s:textfield name="priority" label="%{getText('text.module.priority')}" size="60"></s:textfield>
	<s:textfield name="title" label="%{getText('text.module.title')}" size="60"></s:textfield>
	<s:textarea name="subtitle" label="%{getText('text.module.subtitle')}" cols="48"></s:textarea>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_SAVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_SAVE}" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>
