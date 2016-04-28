<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:bean id="siteModuleHelper" name="com.runtech.onlineshop.form.SiteModuleForm"></s:bean>
<s:bean id="pageTemplateHelper" name="com.runtech.onlineshop.form.PageTemplateForm"></s:bean>
<s:set name="pager" value="pager" scope="request"/>

<s:form>
	<runtech:page pager="${pager}" command="paging"></runtech:page>
	<tt:table value="modelList" sortName="col1" clientSort="true" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.siteModule.id')}" sortKey="id" sortable="true" >
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.siteModule.parentModule')}" sortKey="parent" sortable="true">
			<s:property value="siteModule.name" />
		</tt:column>
		<tt:column title="%{getText('text.siteModule.code')}" sortKey="code" sortable="true" >
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="code" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.siteModule.name')}" sortKey="name" sortable="true">
			<s:property value="name" />
		</tt:column>
		<tt:column title="%{getText('text.siteModule.pageTemplate')}" sortKey="page" sortable="true">
			<s:property value="pageTemplate.name" />
		</tt:column>
		<tt:column title="%{getText('text.siteModule.type')}" sortKey="type" sortable="true">
			<s:property value="type" />
		</tt:column>
	</tt:table>
	<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.delete')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE}');"></s:submit>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:select name="parentSiteModuleId" label="%{getText('text.siteModule.parentModule')}"  list='@com.runtech.onlineshop.form.SiteModuleForm@getAllModules(@com.runtech.web.util.Constant@CODE_SITE_MODULE_ROOT," - ")' listKey="id" listValue="name" emptyOption="true"/>
	<s:select name="pageTemplateId" label="%{getText('text.siteModule.pageTemplate')}" list="#pageTemplateHelper.allPageTemplates" listKey="id" listValue="name"/>
	<s:textfield name="code" label="%{getText('text.siteModule.code')}"></s:textfield>
	<s:textfield name="name" label="%{getText('text.siteModule.name')}"></s:textfield>
	<s:textfield name="type" label="%{getText('text.siteModule.type')}" size="60"></s:textfield>
	<s:textfield name="url" label="%{getText('text.siteModule.url')}" size="60"></s:textfield>
	<s:textfield name="queryString" label="%{getText('text.siteModule.queryString')}" size="60"></s:textfield>
	<s:textfield name="title" label="%{getText('text.siteModule.title')}" size="60"></s:textfield>
	<s:textarea name="subtitle" label="%{getText('text.siteModule.subtitle')}" cols="48"></s:textarea>
	<s:textfield name="priority" label="%{getText('text.siteModule.priority')}" size="60"></s:textfield>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_SAVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_SAVE}" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>
