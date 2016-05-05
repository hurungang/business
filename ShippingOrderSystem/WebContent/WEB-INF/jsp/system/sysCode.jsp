<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:bean id="model" name="com.runtech.onlineshop.form.SystemCodeForm"></s:bean>

<s:form>
	<tt:table value="modelList" action="Table" sortName="col1" clientSort="true" name="modelElement">
		<tt:column title="check">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="id" sortKey="id" sortable="true" >
			<s:property value="id" />
		</tt:column>
		<tt:column title="code" sortKey="code" sortable="true">
			<s:property value="code" />
		</tt:column>
		<tt:column title="name" sortKey="name" sortable="true" >
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="name" />
			</a>
		</tt:column>
		<tt:column title="category" sortKey="category" sortable="true">
			<s:property value="category" />
		</tt:column>
		<tt:column title="priority" sortKey="priority" sortable="true">
			<s:property value="priority" />
		</tt:column>
	</tt:table>
	<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.delete')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE}');"></s:submit>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:textfield name="code" label="System Code"></s:textfield>
	<s:textfield name="name" label="System Code Name"></s:textfield>
	<s:textfield name="category" label="System Code Category"></s:textfield>
	<s:select name="parentId" label="System Code Parent" list="#model.allSysCodes" listKey="id" listValue="name" emptyOption="true"/>
	<s:textfield name="priority" label="System Code Priority"></s:textfield>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_SAVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_SAVE}" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>
