<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:set name="pager" value="pager" scope="request"/>
<s:bean id="commodityOrder" name="com.runtech.onlineshop.form.CommodityOrderForm"></s:bean>
<s:bean id="commodity" name="com.runtech.onlineshop.form.CommodityForm"></s:bean>

<s:form>
	<runtech:page pager="${pager}" command="paging"></runtech:page>
	<tt:table value="modelList" sortName="col1" clientSort="true" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.commodityOrderItem.id')}" sortKey="id" sortable="true">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="id" />
			</a>
		</tt:column>
		
		<tt:column title="%{getText('text.commodityOrderItem.orderId')}" sortKey="commodityOrder.id" sortable="true" >
			<s:property value="commodityOrder.id" />
		</tt:column>
		
		<tt:column title="%{getText('text.commodityOrderItem.commodityId')}" sortKey="commodityId" sortable="true">
			<s:property value="commodity.name" />
		</tt:column>
		
		<tt:column title="%{getText('text.commodityOrderItem.commodityNumber')}" sortKey="commodityNumber" sortable="true" >
			<s:property value="commodityNumber" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrderItem.price')}" sortKey="price" sortable="true">
			<s:property value="price" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrderItem.discount')}" sortKey="discount" sortable="true">
			<s:property value="discount" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrderItem.realPrice')}" sortKey="realPrice" sortable="true">
			<s:property value="realPrice" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrderItem.status')}" sortKey="status" sortable="true">
			<s:property value="status" />
		</tt:column>
	</tt:table>
	<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.delete')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE}');"></s:submit>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	
	<s:textfield name="orderId" label="%{getText('text.commodityOrderItem.orderId')}" readonly="true"/>
	<s:select name="commodityId" label="%{getText('text.commodityOrderItem.commodityId')}" list="#commodity.allCommodity" listKey="id" listValue="name"/>
	
	<s:textfield name="commodityNumber" label="%{getText('text.commodityOrderItem.commodityNumber')}" size="60"></s:textfield>
	<s:textfield name="price" label="%{getText('text.commodityOrderItem.price')}" size="60"></s:textfield>
	<s:textfield name="discount" label="%{getText('text.commodityOrderItem.discount')}" size="60"></s:textfield>
	<s:textfield name="realPrice" label="%{getText('text.commodityOrderItem.realPrice')}" size="60"></s:textfield>
	<s:textfield name="point" label="%{getText('text.commodityOrderItem.point')}" size="60"></s:textfield>
	<s:textfield name="rate" label="%{getText('text.commodityOrderItem.rate')}" size="60"></s:textfield>
	<s:textfield name="remark" label="%{getText('text.commodityOrderItem.remark')}" size="60"></s:textfield>
	<s:textarea name="comment" label="%{getText('text.commodityOrderItem.comment')}" cols="60"></s:textarea>
	
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_SAVE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_SAVE}" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>
