<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:set name="pager" value="pager" scope="request"/>

<s:form>
	<runtech:page pager="${pager}" command="paging"></runtech:page>
	<tt:table value="modelList" sortName="col1" clientSort="true" name="modelElement">
		<tt:column title="%{getText('text.commodityCategory.id')}" sortKey="id" sortable="true" >
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.commodityCategory.name')}" sortKey="name" sortable="true">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodityCategory.description')}" sortKey="description" sortable="true" >
			<s:property value="description" />
		</tt:column>
		<tt:column title="%{getText('text.commodityCategory.status')}" sortKey="status" sortable="true" >
			<s:property value="status" />
		</tt:column>
	</tt:table>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="name" label="%{getText('text.commodityCategory.name')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="commodityCategory.name" label="%{getText('text.commodityCategory.parentId')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="description" label="%{getText('text.commodityCategory.description')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="remark" label="%{getText('text.commodityCategory.remark')}" size="60" disabled="true"></s:textfield>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_APPROVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_APPROVE}" align="left"></s:submit>
		</s:param>
	</s:component>
</s:form>