<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:set name="pager" value="pager" scope="request"/>
<s:bean id="area" name="com.runtech.onlineshop.form.AreaForm"></s:bean>
<s:bean id="commodityCategory" name="com.runtech.onlineshop.form.CommodityCategoryForm"></s:bean>
<s:bean id="commodityProvider" name="com.runtech.onlineshop.form.CommodityProviderForm"></s:bean>

<s:form>
	<runtech:page pager="${pager}" command="paging"></runtech:page>
	<tt:table value="modelList" sortName="col1" clientSort="true" name="modelElement">
		<tt:column title="%{getText('text.commodity.id')}" sortKey="id" sortable="true" >
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.commodity.name')}" sortKey="name" sortable="true">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodity.shortName')}" sortKey="shortName" sortable="true" >
			<s:property value="shortName" />
		</tt:column>
		<tt:column title="%{getText('text.commodity.categoryId')}" sortKey="categoryId" sortable="true" >
			<s:property value="commodityCategory.name" />
		</tt:column>
		<tt:column title="%{getText('text.commodity.priority')}" sortKey="priority" sortable="true" >
			<s:property value="priority" />
		</tt:column>
		<tt:column title="%{getText('text.commodity.originalPrice')}" sortKey="originalPrice" sortable="true">
			<s:property value="originalPrice" />
		</tt:column>
		<tt:column title="%{getText('text.commodity.price')}" sortKey="price" sortable="true">
			<s:property value="price" />
		</tt:column>
		<tt:column title="%{getText('text.commodity.status')}" sortKey="status" sortable="true">
			<s:property value="status" />
		</tt:column>
	</tt:table>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="name" label="%{getText('text.commodity.name')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="shortName" label="%{getText('text.commodity.shortName')}" size="60" disabled="true"></s:textfield>

	<s:textfield name="area.name" label="%{getText('text.commodity.areaId')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="commodityCategory.name" label="%{getText('text.commodity.categoryId')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="commodityProvider.name" label="%{getText('text.commodity.providerId')}" size="60" disabled="true"></s:textfield>
	
	<s:textfield name="summary" label="%{getText('text.commodity.summary')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="standard" label="%{getText('text.commodity.standard')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="priority" label="%{getText('text.commodity.priority')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="commendCode" label="%{getText('text.commodity.commendCode')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="originalPrice" label="%{getText('text.commodity.originalPrice')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="price" label="%{getText('text.commodity.price')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="remark" label="%{getText('text.commodity.remark')}" size="60" disabled="true"></s:textfield>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_APPROVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_APPROVE}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>
