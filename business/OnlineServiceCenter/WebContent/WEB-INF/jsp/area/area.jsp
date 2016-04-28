<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:set name="pager" value="pager" scope="request"/>

<s:form>
	<runtech:page pager="${pager}" command="paging"></runtech:page>
	<tt:table value="modelList" sortName="col1" clientSort="true" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.area.id')}" sortKey="id" sortable="true" >
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.area.name')}" sortKey="name" sortable="true">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.area.code')}" sortKey="code" sortable="true" >
			<s:property value="code" />
		</tt:column>
		<tt:column title="%{getText('text.area.category')}" sortKey="category" sortable="true" >
			<s:property value="category" />
		</tt:column>
		<tt:column title="%{getText('text.area.priority')}" sortKey="priority" sortable="true">
			<s:property value="priority" />
		</tt:column>
		<tt:column title="%{getText('text.regulation.status')}" sortKey="status" sortable="true">
			<s:property value="status" />
		</tt:column>
	</tt:table>
	<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.delete')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE}');"></s:submit>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="name" label="%{getText('text.area.name')}"></s:textfield>
	<s:textfield name="code" label="%{getText('text.area.code')}" size="60"></s:textfield>
	<s:select name="category" label="%{getText('text.area.category')}" emptyOption="true" list="@com.runtech.onlineshop.form.SystemCodeForm@getCodes('areaCategoryCode')" listKey="code" listValue="name"/>
	<s:textfield name="parentId" label="%{getText('text.area.parentId')}" size="60"></s:textfield>
	<s:textfield name="priority" label="%{getText('text.area.priority')}" size="60"></s:textfield>
	
	<s:select name="status" label="%{getText('text.regulation.status')}" list="#{'enabled':'enabled','disabled':'disabled'}"/>
	
	<s:textfield name="remark" label="%{getText('text.area.remark')}" size="60"></s:textfield>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_SAVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_SAVE}" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>
