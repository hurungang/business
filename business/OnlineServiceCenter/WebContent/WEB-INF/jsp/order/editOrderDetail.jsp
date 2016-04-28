<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>

<s:bean id="user" name="com.runtech.onlineshop.form.UserForm"></s:bean>
<s:bean id="area" name="com.runtech.onlineshop.form.AreaForm"></s:bean>
<s:bean id="commodity" name="com.runtech.onlineshop.form.CommodityForm"></s:bean>
<s:bean id="commodityOrder" name="com.runtech.onlineshop.form.CommodityOrderForm"></s:bean>
<div class="content-box"><!-- Start Content Box -->
				
				<div class="content-box-header">
					
					<h3 style="cursor: s-resize; "><s:property value="%{currentMenu.title}"/></h3>
					
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab current">List</a></li> <!-- href must be unique and match the id of target div -->
					</ul>
					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					
					<div class="tab-content default-tab" id="tab1" style="display: block; "> <!-- This is the target div. id must match the href of this div's tab -->
						
						<s:actionerror/>
						<s:actionmessage/>
						
<s:form id="orderDetailForm" name="orderDetailForm">
	<div class="form_container">
		<input type="hidden" name="nextLocation" value="order/orderDetail.do?formId=<s:property value='formId'/>"/>
		<s:hidden name="formId"></s:hidden>
		<s:hidden name="pageCode"></s:hidden>
		<s:hidden name="nextPageCode" value="manualOrderItem"/>
		<s:select name="areaId" label="%{getText('text.commodity.areaId')}" list="#area.allDeliveryAreas" listKey="id" listValue="name"/>
		<s:textfield name="deliveryAddress" label="%{getText('text.commodityOrder.deliveryAddress')}" ></s:textfield>
		<s:textfield name="planDeliveryTimeString" label="%{getText('text.commodityOrder.planDeliveryTime')}" cssClass="text-input datepicker"/>
		<s:textfield name="deliveryman" label="%{getText('text.commodityOrder.deliveryman')}" ></s:textfield>
		<s:select name="orderType" label="%{getText('text.commodityOrder.orderType')}" list="orderTypes" listKey="code" listValue="name"></s:select>
		<s:select name="payType" label="%{getText('text.commodityOrder.payType')}" list="payTypes" listKey="code" listValue="name"></s:select>
		<s:textfield name="freight" label="%{getText('text.commodityOrder.freight')}" ></s:textfield>
		<s:textfield name="contact" label="%{getText('text.commodityOrder.contact')}" ></s:textfield>
		<s:textfield name="contactPhone" label="%{getText('text.commodityOrder.contactPhone')}" ></s:textfield>
		<s:textarea name="memo" label="%{getText('text.commodityOrder.memo')}" cols="48" rows="5"></s:textarea>
		<s:textarea name="comment" label="%{getText('text.commodityOrder.comment')}" cols="48" rows="5"></s:textarea>
	</div>
	<div class="table_container">
			<tt:table value="commodityOrderItems" name="modelChildElement">
				<tt:column title="%{getText('text.list.check')}">
					<input type="checkbox" name="tempOrderItem.formId" value="<s:property value='id'/>"/>
				</tt:column>
		
				<tt:column title="%{getText('text.commodityOrderItem.id')}">
					<s:property value="id" />
				</tt:column>
		
				<tt:column title="%{getText('text.commodityOrderItem.commodity')}">
					<a href="javascript:editOrderItem('<s:property value='id'/>','<s:property value='commodity.id'/>','<s:property value='commodityNumber'/>','<s:property value='price'/>','<s:property value='realPrice'/>','<s:property value='remark'/>')">
						<s:property value="commodity.name" />
					</a>
				</tt:column>
		
				<tt:column title="%{getText('text.commodityOrderItem.commodityNumber')}">
					<s:property value="commodityNumber" />
				</tt:column>
				<tt:column title="%{getText('text.commodityOrderItem.price')}">
					<s:property value="price" />
				</tt:column>
				<tt:column title="%{getText('text.commodityOrderItem.discount')}">
					<s:property value="discount" />
				</tt:column>
				<tt:column title="%{getText('text.commodityOrderItem.realPrice')}">
					<s:property value="realPrice" />
				</tt:column>
				<tt:column title="%{getText('text.commodityOrderItem.status')}">
					<s:property value="status" />
				</tt:column>
			</tt:table>
			<s:submit type="button" theme="simple"  cssClass="button" id="btnFinish" name="action"  value="%{getText('text.submit.saveAll')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_ALL}');" align="left"></s:submit>
			<s:submit type="button" theme="simple"  cssClass="button" id="itemCancelButton" name="action" value="%{getText('text.submit.cancelChild')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL_CHILD}');" align="left"></s:submit>
			<s:submit type="button" theme="simple"  cssClass="button" id="itemCancelButton" name="action" value="%{getText('text.submit.restoreChild')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_RESTORE_CHILD}');" align="left"></s:submit>
	</div>
	
	<div id="addItemDialog" style="display:none;">
		<a href="javascript:hideContainer('addItemDialog');">close</a>
			<s:hidden name="tempOrderItem.formId" id="editingFormItem.id" disabled="true"/>
			<s:select name="tempOrderItem.commodityId" label="%{getText('text.commodityOrderItem.commodity')}" list="#commodity.allCommodity" listKey="id" listValue="name"/>
			
			<s:textfield name="tempOrderItem.commodityNumber" label="%{getText('text.commodityOrderItem.commodityNumber')}" ></s:textfield>
			<s:textfield name="tempOrderItem.price" label="%{getText('text.commodityOrderItem.price')}" ></s:textfield>
			<s:textfield name="tempOrderItem.realPrice" label="%{getText('text.commodityOrderItem.realPrice')}" ></s:textfield>
			<s:textfield name="tempOrderItem.remark" label="%{getText('text.commodityOrderItem.remark')}" ></s:textfield>
			
			<s:submit type="button" cssClass="button" id="itemSaveButton" name="action" value="%{getText('text.submit.saveChild')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_CHILD}');" align="left"></s:submit>
			<s:reset  cssClass="button" id="itemCancelButton" name="action" value="%{getText('text.submit.reset')}" align="left" />
	</div>
</s:form>


					</div> <!-- End #tab1 -->
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
<script>
	function editOrderItem(formId,commodityId,commodityNumber,price,realPrice,remark){
		findObj("editingFormItem.id").value = formId;
		document.forms["orderDetailForm"].elements["tempOrderItem.commodityId"].value = commodityId;
		document.forms["orderDetailForm"].elements["tempOrderItem.commodityNumber"].value = commodityNumber;
		document.forms["orderDetailForm"].elements["tempOrderItem.price"].value = price;
		document.forms["orderDetailForm"].elements["tempOrderItem.realPrice"].value = realPrice;
		document.forms["orderDetailForm"].elements["tempOrderItem.remark"].value = remark;
		showContainer("addItemDialog");
		//manualOrderForm.elements["tempOrderItem.formId"].value = formId;
		
		//manualOrderForm.elements["tempOrderItem.commodityNumber"].value = commodityNumber;
	}
</script>