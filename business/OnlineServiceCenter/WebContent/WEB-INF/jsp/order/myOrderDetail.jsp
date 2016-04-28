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
						
<s:set name="disabled" value="true"/>
<s:if test="status=='pending'">
	<s:set name="disabled" value="false"/>
</s:if>						
<s:form name="ajaxForm" cssClass="ajaxForm" action="ajax/orderDetail">
	<div class="input_container">
		<input type="hidden" name="nextLocation" value="ajax/orderDetail.do?formId=<s:property value='formId'/>"/>
		<s:hidden name="formId"></s:hidden>
		<s:hidden name="pageCode"></s:hidden>
		<s:hidden name="nextPageCode" value="manualOrderItem"/>
		<s:select name="areaId" label="%{getText('text.commodity.areaId')}" disabled="disabled" list="#area.allDeliveryAreas" listKey="id" listValue="name"/>
		<s:textfield name="deliveryAddress" disabled="disabled" label="%{getText('text.commodityOrder.deliveryAddress')}" ></s:textfield>
		<s:textfield name="planDeliveryTimeString" disabled="disabled" label="%{getText('text.commodityOrder.planDeliveryTime')}" cssClass="text-input datepicker"/>
		<s:textfield name="deliveryman" disabled="disabled" label="%{getText('text.commodityOrder.deliveryman')}" ></s:textfield>
		<s:select name="orderType" disabled="disabled" label="%{getText('text.commodityOrder.orderType')}" list="orderTypes" listKey="code" listValue="name"></s:select>
		<s:select name="payType" disabled="disabled" label="%{getText('text.commodityOrder.payType')}" list="payTypes" listKey="code" listValue="name"></s:select>
		<s:textfield name="freight" disabled="disabled" label="%{getText('text.commodityOrder.freight')}" ></s:textfield>
		<s:textfield name="contact" disabled="disabled" label="%{getText('text.commodityOrder.contact')}" ></s:textfield>
		<s:textfield name="contactPhone" disabled="disabled" label="%{getText('text.commodityOrder.contactPhone')}" ></s:textfield>
		<s:textarea name="memo" disabled="disabled" label="%{getText('text.commodityOrder.memo')}" cols="48" rows="5"></s:textarea>
		<s:textarea name="comment" disabled="disabled" label="%{getText('text.commodityOrder.comment')}" cols="48" rows="5"></s:textarea>
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
						<s:property value="commodity.name" />
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
			
			<s:if test='status.equals("pending")'>
			<s:submit type="button" theme="simple"  cssClass="button" id="itemCancelButton" name="action" value="%{getText('text.submit.cancelOrder')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL_ORDER}');" align="left"></s:submit>
			<s:submit type="button" theme="simple"  cssClass="button" id="btnFinish" name="action"  value="%{getText('text.submit.saveAll')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_ALL}');" align="left"></s:submit>
			</s:if>
	</div>
</s:form>


					</div> <!-- End #tab1 -->
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
