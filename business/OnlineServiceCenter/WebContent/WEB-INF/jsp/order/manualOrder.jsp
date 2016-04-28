<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:bean id="user" name="com.runtech.onlineshop.form.UserForm"></s:bean>
<s:bean id="area" name="com.runtech.onlineshop.form.AreaForm"></s:bean>
<s:bean id="commodity" name="com.runtech.onlineshop.form.CommodityForm"></s:bean>
<s:bean id="commodityOrder" name="com.runtech.onlineshop.form.CommodityOrderForm"></s:bean>
<s:set name="pendingListCount" value='getListCountByStatus("pending")'/>
<s:set name="pendingPager" value="getSpecialPager(#pendingListCount)"/>
<s:set name="pendingList" value='searchModel.getListByStatus(#pendingPager,@com.runtech.web.util.Constant@STATUS_MANUALED)'/>

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
	<runtech:page pager="${pendingPager}" command="paging"></runtech:page>
	<tt:table value="pendingList" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>

		<tt:column title="%{getText('text.commodityOrder.id')}">
			<a href="?formId=<s:property value='id'/>">
				<s:property value="id" />
			</a>
		</tt:column>

		<tt:column title="%{getText('text.commodityOrder.deliveryArea')}">
			<s:property value="area.name" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.contact')}" >
			<s:property value="contact" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.contactPhone')}">
			<s:property value="contactPhone" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.planDeliveryTime')}">
			<s:property value="planDeliveryTime" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.dealTime')}">
			<s:property value="dealTime" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.status')}">
			<s:property value="status" />
		</tt:column>
	</tt:table>
	<s:submit type="button" cssClass="button" name="action" value="%{getText('text.submit.cancel')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL_ORDER}');" align="left"></s:submit>
</s:form>

<s:form id="manualOrderForm" name="manualOrderForm">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:hidden name="nextPageCode" value="manualOrderItem"/>
	<s:select name="areaId" label="%{getText('text.commodity.areaId')}" list="#area.allDeliveryAreas" listKey="id" listValue="name"/>
	<s:textfield name="deliveryAddress" label="%{getText('text.commodityOrder.deliveryAddress')}" size="60"></s:textfield>
	<s:textfield name="planDeliveryTimeString" label="%{getText('text.commodityOrder.planDeliveryTime')}" size="30" cssClass="datepicker"/>
	<s:textfield name="deliveryman" label="%{getText('text.commodityOrder.deliveryman')}" size="60"></s:textfield>
	<s:select name="orderType" label="%{getText('text.commodityOrder.orderType')}" list="orderTypes" listKey="id" listValue="name"></s:select>
	<s:select name="payType" label="%{getText('text.commodityOrder.payType')}" list="payTypes" listKey="id" listValue="name"></s:select>
	<s:textfield name="freight" label="%{getText('text.commodityOrder.freight')}" size="60"></s:textfield>
	<s:textfield name="contact" label="%{getText('text.commodityOrder.contact')}" size="60"></s:textfield>
	<s:textfield name="contactPhone" label="%{getText('text.commodityOrder.contactPhone')}" size="60"></s:textfield>
	<s:textarea name="memo" label="%{getText('text.commodityOrder.memo')}" cols="48" rows="5"></s:textarea>
	<s:textarea name="comment" label="%{getText('text.commodityOrder.comment')}" cols="48" rows="5"></s:textarea>
	
	<s:component template="component.ftl">
		<s:param name="body">
			<tt:table value="commodityOrderItems" sortName="id" clientSort="true" name="modelChildElement">
				<tt:column title="%{getText('text.list.check')}">
					<input type="checkbox" name="tempOrderItem.formId" value="<s:property value='id'/>"/>
				</tt:column>
		
				<tt:column title="%{getText('text.commodityOrderItem.id')}" sortKey="id" sortable="true">
					<s:property value="id" />
				</tt:column>
		
				<tt:column title="%{getText('text.commodityOrderItem.commodity')}" sortKey="commodity" sortable="true" >
					<a href="javascript:editOrderItem('<s:property value='id'/>','<s:property value='commodity.id'/>','<s:property value='commodityNumber'/>','<s:property value='price'/>','<s:property value='realPrice'/>','<s:property value='remark'/>')">
						<s:property value="commodity.name" />
					</a>
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
			</tt:table>
		</s:param>
	</s:component>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit type="button" theme="simple" cssClass="button" id="btnFinish" name="action"  value="%{getText('text.submit.finish')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_FINISH}');" align="left"></s:submit>
			<s:submit type="button" theme="simple" cssClass="button" id="itemDeleteButton" name="action" value="%{getText('text.submit.deleteChild')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE_CHILD}');" align="left"></s:submit>
			<a class="button" rel="modal" href="javascript:showContainer('addItemDialog');" class="iconButton iconAdd"><s:property value="%{getText('text.submit.addChild')}"/></a>
		</s:param>
	</s:component>

	<s:component template="component.ftl">
		<s:param name="body">
			<div id="addItemDialog" style="display:none;">
				<table class="wwFormTable">
				<caption><a href="javascript:hideContainer('addItemDialog');">close</a></caption>
					<s:hidden name="tempOrderItem.formId" id="editingFormItem.id" disabled="true"/>
					<s:select name="tempOrderItem.commodityId" label="%{getText('text.commodityOrderItem.commodity')}" list="#commodity.allCommodity" listKey="id" listValue="name"/>
					
					<s:textfield name="tempOrderItem.commodityNumber" label="%{getText('text.commodityOrderItem.commodityNumber')}" size="60"></s:textfield>
					<s:textfield name="tempOrderItem.price" label="%{getText('text.commodityOrderItem.price')}" size="60"></s:textfield>
					<s:textfield name="tempOrderItem.realPrice" label="%{getText('text.commodityOrderItem.realPrice')}" size="60"></s:textfield>
					<s:textfield name="tempOrderItem.remark" label="%{getText('text.commodityOrderItem.remark')}" size="60"></s:textfield>
					
					<s:component template="component.ftl">
						<s:param name="body">
							<s:submit type="button" theme="simple" cssClass="button" id="itemSaveButton" name="action" value="%{getText('text.submit.saveChild')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_CHILD}');" align="left"></s:submit>
							<s:reset  theme="simple" cssClass="button" id="itemCancelButton" name="action" value="%{getText('text.submit.reset')}" align="left" />
						</s:param>
					</s:component>
				</table>
			</div>
		</s:param>
	</s:component>
</s:form>

					</div>
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
<script>
	function editOrderItem(formId,commodityId,commodityNumber,price,realPrice,remark){
		findObj("editingFormItem.id").value = formId;
		document.forms["manualOrderForm"].elements["tempOrderItem.commodityId"].value = commodityId;
		document.forms["manualOrderForm"].elements["tempOrderItem.commodityNumber"].value = commodityNumber;
		document.forms["manualOrderForm"].elements["tempOrderItem.price"].value = price;
		document.forms["manualOrderForm"].elements["tempOrderItem.realPrice"].value = realPrice;
		document.forms["manualOrderForm"].elements["tempOrderItem.remark"].value = remark;
		showContainer("addItemDialog");
		//manualOrderForm.elements["tempOrderItem.formId"].value = formId;
		
		//manualOrderForm.elements["tempOrderItem.commodityNumber"].value = commodityNumber;
	}
</script>