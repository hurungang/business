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
<runtech:set name="userId" value="${param.userId}"/>
<s:set name="orderList" value='searchModel.getUserOrderList(specialPager,statusValues,typeValues)'/>

<div class="content-box"><!-- Start Content Box -->
				
				<div class="content-box-header">
					
					<h3 style="cursor: s-resize; ">Content box</h3>
					
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab current">Table</a></li> <!-- href must be unique and match the id of target div -->
					</ul>
					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					
					<div class="tab-content default-tab" id="tab1" style="display: block; "> <!-- This is the target div. id must match the href of this div's tab -->
						
						<s:actionerror/>
						<s:actionmessage/>
<s:form>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:textfield theme="css_xhtml" cssClass="text-input medium-input" labelposition="left" name="searchModel.formId" label="%{getText('text.commodityOrder.id')}" size="20"/>
			<s:textfield theme="css_xhtml" cssClass="text-input medium-input" labelposition="left" name="searchModel.userName" label="%{getText('text.commodityOrder.user')}" size="20"/>
			<s:textfield theme="css_xhtml" cssClass="text-input medium-input" labelposition="left" name="searchModel.contact" label="%{getText('text.commodityOrder.contact')}" size="20"/>
			<s:textfield theme="css_xhtml" cssClass="text-input medium-input" labelposition="left" name="searchModel.contactPhone" label="%{getText('text.commodityOrder.contactPhone')}" size="20"/>
			<s:textfield theme="css_xhtml" id="datepicker" cssClass="text-input datepicker" labelposition="left" name="searchModel.startDate" label="%{getText('text.commodityOrder.startDate')}" size="20"/>
			<s:textfield theme="css_xhtml" cssClass="text-input datepicker" labelposition="left" name="searchModel.endDate" label="%{getText('text.commodityOrder.endDate')}" size="20"/>
			<s:submit theme="simple" cssClass="button" value="%{getText('text.submit.search')}"/>
		</s:param>
	</s:component>
	<runtech:page pager="${specialPager}" command="paging"></runtech:page>
	<tt:table value="orderList" clientSort="false" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>

		<tt:column title="%{getText('text.commodityOrder.id')}" sortKey="id" sortable="true">
			<a href="orderDetail.do?formId=<s:property value='id'/>" target="_blank">
				<s:property value="id" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.user')}" sortKey="user" sortable="true" >
			<s:property value="user.fullName" />
		</tt:column>
		<tt:column title="%{getText('text.commodityOrder.organizationUser')}" sortKey="user" sortable="true" >
			<s:property value="user.id" /> - <s:property value="user.parentUser.fullName" />
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
		<tt:column title="%{getText('text.commodityOrder.remark')}">
			<s:property value="remark" />
		</tt:column>
	</tt:table>
	<s:component template="component.ftl">
		<s:param name="body">
				<input type="button" class="button" id="btnSelect" name="btnSelect" value="Select/Deselect All" align="left" onclick="selectAll(this,'formId');"/>
			<s:if test='statusValue.equals("manualed")||statusValue.equals("paid")||statusValue.equals("confirmed")'>
				<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.prepareOrder')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_PREPARE}');"></s:submit>
			</s:if>
			<s:elseif test='statusValue.equals("preparing")'>
				<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.deliverOrder')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELIVER}');"></s:submit>
			</s:elseif>
			<s:elseif test='statusValue.equals("delivering")'>
				<s:submit theme="simple" type="button" cssClass="button" id="btnSuccess" name="actionButton" value="%{getText('text.submit.succeedOrder')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SUCCEED_ORDER}');"></s:submit>
				<s:submit theme="simple" type="button" cssClass="button" id="btnRejected" name="actionButton" value="%{getText('text.submit.rejectOrder')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_REJECT_ORDER}');"></s:submit>
			</s:elseif>
			<s:if test='statusValue.equals("cancelled")'>
				<s:submit theme="simple" type="button" cssClass="button" id="btnRestore" name="actionButton" value="%{getText('text.submit.restoreOrder')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_RESTORE_ORDER}');"></s:submit>
			</s:if>
			<s:else>
				<s:submit theme="simple" type="button" cssClass="button" id="btnCancel" name="actionButton" value="%{getText('text.submit.cancelOrder')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL_ORDER}');"></s:submit>
			</s:else>
		</s:param>
	</s:component>
</s:form>

					</div> <!-- End #tab1 -->
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->