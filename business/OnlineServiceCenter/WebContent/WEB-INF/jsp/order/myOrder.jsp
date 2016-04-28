<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>

<s:bean id="user" name="com.runtech.onlineshop.form.UserForm"></s:bean>
<s:bean id="area" name="com.runtech.onlineshop.form.AreaForm"></s:bean>
<s:bean id="commodity" name="com.runtech.onlineshop.form.CommodityForm"></s:bean>
<s:bean id="commodityOrder" name="com.runtech.onlineshop.form.CommodityOrderForm"></s:bean>
<runtech:set name="statusValue" value="${param.status}"/>
<runtech:set name="statusValues" value="${paramValues.status}"/>
<runtech:set name="typeValues" value="${paramValues.type}"/>
<s:set name="orderList" value='searchModel.getUserOrderList(specialPager,statusValues,typeValues,context)'/>

<div class="content-box"><!-- Start Content Box -->
				
				<div class="content-box-header">
					
					<h3 style="cursor: s-resize; "><s:property value="%{currentMenu.title}"/></h3>
					
					<ul class="content-box-tabs">
						<li><a id="<s:property value="pageCode"/>_table_tab_link" href="#<s:property value="pageCode"/>_table_tab">Table</a></li>
						<li><a id="<s:property value="pageCode"/>_form_tab_link" href="#<s:property value="pageCode"/>_form_tab">Form</a></li>
					</ul>
					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					
					<div class="tab-content" id="<s:property value="pageCode"/>_table_tab">
<s:form name="orderForm" id="orderForm">
	<div class="input_container">
			<s:textfield theme="css_xhtml" cssClass="text-input medium-input" labelposition="left" name="searchModel.formId" label="%{getText('text.commodityOrder.id')}" size="20"/>
			<s:textfield theme="css_xhtml" cssClass="text-input medium-input" labelposition="left" name="searchModel.contact" label="%{getText('text.commodityOrder.contact')}" size="20"/>
			<s:textfield theme="css_xhtml" cssClass="text-input medium-input" labelposition="left" name="searchModel.contactPhone" label="%{getText('text.commodityOrder.contactPhone')}" size="20"/>
			<s:textfield theme="css_xhtml" id="datepicker" cssClass="text-input datepicker" labelposition="left" name="searchModel.startDate" label="%{getText('text.commodityOrder.startDate')}" size="20"/>
			<s:textfield theme="css_xhtml" cssClass="text-input datepicker" labelposition="left" name="searchModel.endDate" label="%{getText('text.commodityOrder.endDate')}" size="20"/>
			<s:submit theme="simple" cssClass="button" value="%{getText('text.submit.search')}"/>
	</div>
	
	<div class="table_container">
	<s:hidden name="formId"></s:hidden>
	<runtech:page pager="${specialPager}" command="paging"></runtech:page>
	<tt:table value="orderList" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>

		<tt:column title="%{getText('text.commodityOrder.id')}">
			<a href="?formId=<s:property value='id'/>">
				<s:property value="id" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.user')}">
			<s:property value="user.fullName" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.contact')}">
			<s:property value="contact" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.contactPhone')}">
			<s:property value="contactPhone" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.planDeliveryTime')}">
			<s:property value="%{getText('format.date',{planDeliveryTime})}" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.deliveryTime')}">
			<s:property value="%{getText('format.date',{deliveryTime})}" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.dealTime')}">
			<s:property value="%{getText('format.date',{dealTime})}" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.status')}">
			<s:property value="status" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.totalRealPrice')}">
			<s:property value="%{getText('format.money',{totalRealPrice})}" />
		</tt:column>
		<tt:column title="">
			
			<s:if test='status.equals("pending")||status.equals("unconfirmed")'>
				<s:submit  type="button" cssClass="button" name="actionButton" value="%{getText('text.submit.cancelOrder')}" align="left" onclick="return cancelOrder(%{id});"></s:submit>
			</s:if>
		</tt:column>
	</tt:table>
	</div>
</s:form>

					</div>
					<div class="tab-content" id="<s:property value="pageCode"/>_form_tab">
					
<s:form id="manualOrderForm" name="manualOrderForm">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:hidden name="nextPageCode" value="manualOrderItem"/>
	<s:select name="areaId" label="%{getText('text.commodity.areaId')}" list="#area.allDeliveryAreas" listKey="id" listValue="name"/>
	<s:textfield name="deliveryAddress" label="%{getText('text.commodityOrder.deliveryAddress')}" size="60"></s:textfield>
	<s:textfield name="planDeliveryTimeString" label="%{getText('text.commodityOrder.planDeliveryTime')}" size="30" cssClass="datepicker"/>
	<s:select name="orderType" id="orderType" onchange="switchContainer();" label="%{getText('text.commodityOrder.orderType')}" list="orderTypes" listKey="code" listValue="name"></s:select>
	<s:select name="payType" label="%{getText('text.commodityOrder.payType')}" list="payTypes" listKey="code" listValue="name"></s:select>
	<s:textfield name="contact" label="%{getText('text.commodityOrder.contact')}" size="60"></s:textfield>
	<s:textfield name="contactPhone" label="%{getText('text.commodityOrder.contactPhone')}" size="60"></s:textfield>
	<s:textarea name="memo" label="%{getText('text.commodityOrder.memo')}" cols="48" rows="5"></s:textarea>

	<div id="quickOrderContainer" style="display:none">
	<s:textarea name="quickRequest" label="%{getText('text.commodityOrder.quickRequest')}" cols="48" rows="5"></s:textarea>
	<s:textarea disabled="true" name="quickResponse" label="%{getText('text.commodityOrder.quickResponse')}" cols="48" rows="5"></s:textarea>
	</div>
	
	<div id="commodityOrderItemsContainer">
	<s:component template="component.ftl">
		<s:param name="body">
			<tt:table id="newCommodityOrderItemsContainer" value="commodityOrderItems" sortName="id" clientSort="true" name="modelChildElement">
				<tt:column title="%{getText('text.commodityOrderItem.id')}">
					<input type="hidden" name="commodityId" value="<s:property value='commodity.id'/>"/><s:property value='id'/>
				</tt:column>
		
				<tt:column title="%{getText('text.commodityOrderItem.commodity')}" sortKey="commodity" sortable="true" >
					<a href="javascript:editOrderItem('<s:property value='id'/>','<s:property value='commodity.id'/>','<s:property value='commodityNumber'/>','<s:property value='price'/>','<s:property value='realPrice'/>','<s:property value='remark'/>')">
						<s:property value="commodity.name" />
					</a>
				</tt:column>
		
				<tt:column title="%{getText('text.commodityOrderItem.commodityNumber')}" sortKey="commodityNumber" sortable="true" >
					<input id='commodityNumber_<s:property value='commodity.id'/>' name='commodityNumber_<s:property value='commodity.id'/>' type='text' value='<s:property value="commodityNumber" />'/>
				</tt:column>
				<tt:column title="%{getText('text.commodityOrderItem.remark')}">
					<input id='remark_<s:property value='commodity.id'/>' name='remark_<s:property value='commodity.id'/>' type='text' value='<s:property value="remark" />'/>
				</tt:column>
				<tt:column>
					<input class="button" type="button" onclick="deleteOrderItem(this);" value='<s:property value="%{getText('text.sumbit.delete')}"/>'/>
				</tt:column>
			</tt:table>
			<a href="#addItemDialog" class="button" rel="modal"><s:property value="%{getText('text.submit.addChild')}" /></a>
		</s:param>
	</s:component>
	</div>
	
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit type="button" theme="simple" cssClass="button" id="btnFinish" name="action"  value="%{getText('text.submit.saveAll')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_AGENT_ORDER}');" align="left"></s:submit>
			</s:param>
	</s:component>

</s:form>

			<div id="addItemDialog" style="display: none;">
				<form>
					<s:hidden name="tempOrderItem.formId" id="editingFormItem.id" disabled="true"/>
					<s:select name="tempOrderItem.commodityId" id="tempOrderItem_commodityId" label="%{getText('text.commodityOrderItem.commodity')}" list="#commodity.allCommodity" listKey="id" listValue="name"/>
					
					<s:textfield name="tempOrderItem.commodityNumber" id="tempOrderItem_commodityNumber" label="%{getText('text.commodityOrderItem.commodityNumber')}" size="60"></s:textfield>
					<s:textfield name="tempOrderItem.remark" id="tempOrderItem_remark" label="%{getText('text.commodityOrderItem.remark')}" size="60"></s:textfield>
					
					<s:component template="component.ftl">
						<s:param name="body">
							<a class="button" href="javascript:addOrderItem('addItemDialog');" class="iconButton iconAdd"><s:property value="%{getText('text.submit.addChild')}"/></a>
							</s:param>
					</s:component>
				</form>
			</div>
					</div> <!-- End #tab1 -->
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
<script>
function addOrderItem(){
	var commodityId = $('#facebox').find('#tempOrderItem_commodityId').find("option:selected").val();
	var commodityNumberInput = $('#newCommodityOrderItemsContainer').find('#commodityNumber_'+commodityId);
	var remarkInput = $('#newCommodityOrderItemsContainer').find('#remark_'+commodityId);
	if(commodityNumberInput.length>0){
		commodityNumberInput.val(parseInt(commodityNumberInput.val())+parseInt($('#facebox').find('#tempOrderItem_commodityNumber').val()));
		remarkInput.val($('#facebox').find('#tempOrderItem_remark').val());
	}else{
			
		var innerContent = "<tr id='tr_"+commodityId+"'><td><input type='hidden' name='commodityId' value='"+commodityId+"'/></td>"+
		"<td>"+$('#facebox').find('#tempOrderItem_commodityId').find("option:selected").text()+"<input type='hidden' value='"+$('#facebox').find('#tempOrderItem_commodityId').find("option:selected").text()+"'/></td>"+
		"<td><input id='commodityNumber_"+commodityId+"' name='commodityNumber_"+commodityId+"' type='text' value='"+$('#facebox').find('#tempOrderItem_commodityNumber').val()+"'/></td>"+
		"<td><input id='remark_"+commodityId+"' name='remark_"+commodityId+"' type='text' value='"+$('#facebox').find('#tempOrderItem_remark').val()+"'/></td>"
		"<td><input class='button' type='button' onclick='deleteOrderItem(this);' value='<s:property value="%{getText('text.sumbit.delete')}"/>'/></td></tr>";
		
		if($('#newCommodityOrderItemsContainer').length==0){
			$('#commodityOrderItemsContainer ').append("<table id='newCommodityOrderItemsContainer' class='listTable'>"+
				"<thead><tr><th><s:property value="%{getText('text.commodityOrderItem.id')}"/></th>"+
				"<th><s:property value="%{getText('text.commodityOrderItem.commodity')}"/></th>"+
				"<th><s:property value="%{getText('text.commodityOrderItem.commodityNumber')}"/></th>"+
				"<th><s:property value="%{getText('text.commodityOrderItem.remark')}"/></th></thead></table>");
		}
		$('#newCommodityOrderItemsContainer').append(innerContent);
	}
}

	function deleteOrderItem(obj){
		$(obj).parents("tr").remove();	
	}
	function cancelOrder(orderId){
		document.orderForm.formId[0].value = orderId;
		setAction(document.orderForm,'<s:property value="%{@com.runtech.web.util.Constant@ACTION_CANCEL_ORDER}"/>');
		document.orderForm.submit();
	}
	function switchContainer(){
		var orderType = $("#manualOrderForm").find("#orderType").find("option:selected").val();
		if(orderType =='simpleOrder'){
			showContainer('quickOrderContainer');
			hideContainer('commodityOrderItemsContainer');
		}else{
			hideContainer('quickOrderContainer');
			showContainer('commodityOrderItemsContainer');
		}
	}
	switchContainer();
</script>
