<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:set name="pager" value="pager" scope="request"/>

<s:form>
	<runtech:page pager="${pager}" command="paging"></runtech:page>
	<tt:table value="modelList" sortName="id" clientSort="true" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.content.id')}" sortKey="id" sortable="true" >
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.templateComponent.code')}" sortKey="title" sortable="true">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="code" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.templateComponent.name')}" sortKey="author" sortable="true" >
				<s:property value="name" />
		</tt:column>
		<tt:column title="%{getText('text.templateComponent.type')}" sortKey="status" sortable="true">
			<s:property value="type" />
		</tt:column>
	</tt:table>
	<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_DELETE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_DELETE}" align="left"></s:submit>
	<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_DUPLICATE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_DUPLICATE}" align="left"></s:submit>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="code" label="%{getText('text.templateComponent.code')}"/>
	<s:textfield name="name" label="%{getText('text.templateComponent.name')}"/>
	<s:textfield name="type" label="%{getText('text.templateComponent.type')}"/>
	<s:textarea name="query" label="%{getText('text.templateComponent.query')}" cols="60" rows="6"/>
	<s:textfield name="start" label="%{getText('text.templateComponent.start')}"/>
	<s:textfield name="fetchSize" label="%{getText('text.templateComponent.fetchSize')}"/>
	<s:textarea labelposition="top" name="body" label="%{getText('text.templateComponent.body')}" cols="99" rows="30"></s:textarea>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_SAVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_SAVE}" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>
