<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>

<s:bean id="user" name="com.runtech.onlineshop.form.UserForm"></s:bean>
<s:bean id="area" name="com.runtech.onlineshop.form.AreaForm"></s:bean>
<s:bean id="commodity" name="com.runtech.onlineshop.form.CommodityForm"></s:bean>
<s:bean id="commodityOrder" name="com.runtech.onlineshop.form.CommodityOrderForm"></s:bean>
<s:set name="preparingList" value='searchModel.getListByStatus(specialPager,@com.runtech.web.util.Constant@STATUS_PREPARING)'/>

<s:form>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:textfield theme="css_xhtml" labelposition="left" name="searchModel.userName" label="%{getText('text.commodityOrder.user')}" size="20"/>
			<s:textfield theme="css_xhtml" labelposition="left" name="searchModel.contact" label="%{getText('text.commodityOrder.contact')}" size="20"/>
			<s:textfield theme="css_xhtml" labelposition="left" name="searchModel.contactPhone" label="%{getText('text.commodityOrder.contactPhone')}" size="20"/>
			<s:textfield theme="css_xhtml" labelposition="left" name="searchModel.startDate" label="%{getText('text.commodityOrder.startDate')}" size="30"/>
			<s:textfield theme="css_xhtml" labelposition="left" name="searchModel.endDate" label="%{getText('text.commodityOrder.endDate')}" size="30"/>
			<s:submit theme="simple" value="%{getText('text.submit.search')}"/>
		</s:param>
	</s:component>
	<runtech:page pager="${specialPager}" command="paging"></runtech:page>
	<tt:table value="preparingList" clientSort="false" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>

		<tt:column title="%{getText('text.commodityOrder.id')}" sortKey="id" sortable="true">
			<a href="?formId=<s:property value='id'/>">
				<s:property value="id" />
			</a>
		</tt:column>

		<tt:column title="%{getText('text.commodityOrder.deliveryArea')}" sortKey="area.name" sortable="true" >
			<s:property value="area.name" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.user')}" sortKey="user" sortable="true" >
			<s:property value="user.name" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.contact')}" sortKey="contact" sortable="true" >
			<s:property value="contact" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.contactPhone')}" sortKey="contactPhone" sortable="true" >
			<s:property value="contactPhone" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.planDeliveryTime')}" sortKey="planDeliveryTime" sortable="true" >
			<s:property value="planDeliveryTime" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.dealTime')}" sortKey="dealTime" sortable="true" >
			<s:property value="dealTime" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.status')}" sortKey="status" sortable="true">
			<s:property value="status" />
		</tt:column>
	</tt:table>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" type="button" cssClass="button" id="btnDeliver" name="actionButton" value="%{getText('text.submit.deliverOrder')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELIVER}');"></s:submit>
			<s:submit theme="simple" type="button" cssClass="button" id="btnCancel" name="actionButton" value="%{getText('text.submit.cancelOrder')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL_ORDER}');"></s:submit>
		</s:param>
	</s:component>
</s:form>

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="user.name" label="%{getText('text.commodityOrder.user')}" size="60" disabled="true"></s:textfield>
	<s:select name="areaId" label="%{getText('text.commodity.areaId')}" list="#area.allDeliveryAreas" listKey="id" listValue="name" disabled="true"/>
	<s:textfield name="deliveryAddress" label="%{getText('text.commodityOrder.deliveryAddress')}" size="60" disabled="true"></s:textfield>
	<s:select name="orderType" label="%{getText('text.commodityOrder.orderType')}" list="orderTypes" listKey="id" listValue="name" disabled="true"></s:select>
	<s:select name="payType" label="%{getText('text.commodityOrder.payType')}" list="payTypes" listKey="id" listValue="name" disabled="true"></s:select>
	<s:textfield name="freight" label="%{getText('text.commodityOrder.freight')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="contact" label="%{getText('text.commodityOrder.contact')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="contactPhone" label="%{getText('text.commodityOrder.contactPhone')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="totalPrice" label="%{getText('text.commodityOrder.totalPrice')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="totalRealPrice" label="%{getText('text.commodityOrder.totalRealPrice')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="totalPoint" label="%{getText('text.commodityOrder.totalPoint')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="paidAmount" label="%{getText('text.commodityOrder.paidAmount')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="restAmount" label="%{getText('text.commodityOrder.restAmount')}" size="60" disabled="true"></s:textfield>
	<s:textarea name="memo" label="%{getText('text.commodityOrder.memo')}" cols ="48" disabled="true"></s:textarea>
	<s:textfield name="deliveryTime" label="%{getText('text.commodityOrder.deliveryTime')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="status" label="%{getText('text.commodityOrder.status')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="deliveryMan" label="%{getText('text.commodityOrder.deliveryman')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="deliveryStatus" label="%{getText('text.commodityOrder.deliveryStatus')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="postcode" label="%{getText('text.commodityOrder.postcode')}" size="60" disabled="true"></s:textfield>
	<s:textfield name="dealTime" label="%{getText('text.commodityOrder.dealTime')}" size="60" disabled="true"></s:textfield>
	

	<tt:table value="commodityOrderItems" sortName="col1" clientSort="true" name="modelElement">
		<tt:column title="%{getText('text.commodityOrderItem.id')}" sortKey="id" sortable="true">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="id" />
			</a>
		</tt:column>

		<tt:column title="%{getText('text.commodityOrderItem.commodity')}" sortKey="commodityId" sortable="true" >
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
	<tt:table title="%{getText('text.commodityOrder.payment')}"  value="successCommodityPayments" name="modelElement">
		<tt:column title="%{getText('text.commodityPayment.id')}">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="id" />
			</a>
		</tt:column>

		<tt:column title="%{getText('text.commodityPayment.payType')}">
			<s:property value="payType" />
		</tt:column>

		<tt:column title="%{getText('text.commodityPayment.sum')}" >
			<s:property value="sum" />
		</tt:column>
		<tt:column title="%{getText('text.commodityPayment.status')}">
			<s:property value="status" />
		</tt:column>
	</tt:table>
</s:form>
