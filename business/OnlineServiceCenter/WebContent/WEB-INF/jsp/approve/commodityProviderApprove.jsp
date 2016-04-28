<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:set name="pager" value="pager" scope="request"/>

<s:form>
	<runtech:page pager="${pager}" command="paging"></runtech:page>
	<tt:table value="modelList" sortName="col1" clientSort="true" name="modelElement">
		<tt:column title="%{getText('text.commodityProvider.id')}" sortKey="id" sortable="true" >
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.commodityProvider.name')}" sortKey="name" sortable="true">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodityProvider.phone')}" sortKey="phone" sortable="true" >
			<s:property value="phone" />
		</tt:column>
		<tt:column title="%{getText('text.commodityProvider.linkman')}" sortKey="linkman" sortable="true" >
			<s:property value="linkman" />
		</tt:column>
		<tt:column title="%{getText('text.commodityProvider.linkmanPhone')}" sortKey="linkmanPhone" sortable="true" >
			<s:property value="linkmanPhone" />
		</tt:column>
		<tt:column title="%{getText('text.commodityProvider.linkmanMobile')}" sortKey="linkmanMobile" sortable="true">
			<s:property value="linkmanMobile" />
		</tt:column>
		<tt:column title="%{getText('text.commodityProvider.address')}" sortKey="address" sortable="true">
			<s:property value="address" />
		</tt:column>
	</tt:table>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="name" label="%{getText('text.commodityProvider.name')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="phone" label="%{getText('text.commodityProvider.phone')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="linkman" label="%{getText('text.commodityProvider.linkman')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="linkmanPhone" label="%{getText('text.commodityProvider.linkmanPhone')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="linkmanMobile" label="%{getText('text.commodityProvider.linkmanMobile')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="address" label="%{getText('text.commodityProvider.address')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="remark" label="%{getText('text.commodityProvider.remark')}" size="60" disabled="true"></s:textfield>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_APPROVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_APPROVE}" align="left"></s:submit>
		</s:param>
	</s:component>
</s:form>
