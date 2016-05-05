<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:bean id="model" name="com.runtech.onlineshop.form.AdminRoleForm"></s:bean>

<s:form>
	<tt:table value="modelList" action="Table" sortName="col1" clientSort="true" name="modelElement">
		<tt:column title="check">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.administrator.id')}" sortKey="id" sortable="true" >
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.administrator.name')}" sortKey="name" sortable="true" >
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.administrator.email')}" sortKey="email" sortable="true">
			<s:property value="email" />
		</tt:column>
		<tt:column title="%{getText('text.administrator.phone')}" sortKey="phone" sortable="true">
			<s:property value="phone" />
		</tt:column>
		<tt:column title="%{getText('text.administrator.mobile')}" sortKey="mobile" sortable="true">
			<s:property value="mobile" />
		</tt:column>
	</tt:table>
	<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.delete')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE}');"></s:submit>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="name" label="%{getText('text.administrator.name')}"></s:textfield>
	<s:textfield name="password" label="%{getText('text.administrator.password')}"></s:textfield>
	<s:textfield name="fullName" label="%{getText('text.administrator.fullName')}"></s:textfield>
	<s:select name="roleId" label="%{getText('text.administrator.roleId')}" list="#model.allRoles" listKey="id" listValue="name"/>
	<s:textfield name="email" label="%{getText('text.administrator.email')}"></s:textfield>
	<s:textfield name="phone" label="%{getText('text.administrator.phone')}"></s:textfield>
	<s:textfield name="mobile" label="%{getText('text.administrator.mobile')}"></s:textfield>
	<s:textarea name="address" label="%{getText('text.administrator.address')}"></s:textarea>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_SAVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_SAVE}" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>
