<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:bean id="role" name="com.runtech.onlineshop.form.AdminRoleForm"></s:bean>
<s:bean id="module" name="com.runtech.onlineshop.form.AdminModuleForm"></s:bean>
<s:set name="pager" value="pager" scope="request"/>

<s:form>
	<runtech:page pager="${pager}" command="paging"></runtech:page>
	<tt:table value="modelList" clientSort="true" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.role.id')}" sortKey="roleId" sortable="true" >
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="AdminRole.name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.module.id')}" sortKey="moduleId" sortable="true">
			<s:property value="AdminModule.name" />
		</tt:column>
		<tt:column title="%{getText('text.authorization.readable')}" sortKey="readable" sortable="true" >
				<s:property value="authorization.readable" />
		</tt:column>
		<tt:column title="%{getText('text.authorization.writable')}" sortKey="writable" sortable="true" >
				<s:property value="authorization.writable" />
		</tt:column>
		<tt:column title="%{getText('text.authorization.executable')}" sortKey="executable" sortable="true" >
				<s:property value="authorization.executable" />
		</tt:column>
		<tt:column title="%{getText('text.authorization.supervisable')}" sortKey="supervisable" sortable="true" >
				<s:property value="authorization.supervisable" />
		</tt:column>
	</tt:table>
	<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.delete')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE}');"></s:submit>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:select name="adminRole.id" label="%{getText('text.role.id')}" list="#role.allRoles" listKey="id" listValue="name"/>
	<s:select name="adminModule.id" label="%{getText('text.module.id')}" list="#module.allModules" listKey="id" listValue="name"/>
	<s:checkbox name="authorization.readable" label="%{getText('text.authorization.readable')}"/>
	<s:checkbox name="authorization.writable" label="%{getText('text.authorization.writable')}"/>
	<s:checkbox name="authorization.executable" label="%{getText('text.authorization.executable')}"/>
	<s:checkbox name="authorization.supervisable" label="%{getText('text.authorization.supervisable')}"/>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_SAVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_SAVE}" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>
