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
		<tt:column title="%{getText('text.content.id')}" sortKey="id" sortable="true" >
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.template.name')}" sortKey="title" sortable="true">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.template.language')}" sortKey="author" sortable="true" >
				<s:property value="language" />
		</tt:column>
		<tt:column title="%{getText('text.template.status')}" sortKey="status" sortable="true">
			<s:property value="status" />
		</tt:column>
	</tt:table>
	<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.delete')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE}');"></s:submit>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="name" label="%{getText('text.template.name')}"></s:textfield>
	<s:textfield name="language" label="%{getText('text.template.language')}"></s:textfield>
	<s:textarea labelposition="top" name="template" label="%{getText('text.template.language')}" cols="99" rows="40"></s:textarea>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_SAVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_SAVE}" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>

	<tt:table value="pageTemplateComponents" sortName="id" name="pageTemplateComponent">
		<tt:column title="%{getText('text.content.id')}" sortKey="id" sortable="true" >
			<s:property value="pageComponent.id" />
		</tt:column>
		<tt:column title="%{getText('text.pageComponent.name')}" sortKey="title" sortable="true">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="pageComponent.name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.pageComponent.code')}" sortKey="author" sortable="true" >
				<s:property value="pageComponent.code" />
		</tt:column>
		<tt:column title="%{getText('text.pageTemplateComponent.referenceName')}" sortKey="status" sortable="true">
			<s:property value="referenceName" />
		</tt:column>
	</tt:table>
</s:form>
