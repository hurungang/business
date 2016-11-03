<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>

<s:set name="pager" value="pager" scope="request"/>
<s:set name="searchResult" value="searchModel.getSearchResult(specialPager)"/>
<s:bean id="providerUtil" name="com.runtech.onlineshop.form.CommodityProviderForm"></s:bean>

<div class="content-box"><!-- Start Content Box -->
				
				<div class="content-box-header">
					
					<h3 style="cursor: s-resize; ">Content box</h3>
					
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
			<s:textfield theme="simple" cssClass="text-input" name="searchModel.consignmentTitle" label="%{getText('text.consignment.consignmentTitle')}" size="60"/><s:submit theme="simple" cssClass="button" value="%{getText('text.submit.search')}"/>
		</s:param>
	</s:component>
	<runtech:page pager="${specialPager}" command="paging"></runtech:page>
	<table class="listTable">
		<thead>
			<tr><th><s:property value="%{getText('text.list.check')}"/></th>
			<th><s:property value="%{getText('text.consignment.id')}"/></th>
			<th><s:property value="%{getText('text.consignment.title')}"/></th>
			<th><s:property value="%{getText('text.consignment.createTime')}"/></th>
			<th><s:property value="%{getText('text.consignment.deliveryTime')}"/></th>
			<th><s:property value="%{getText('text.consignment.company')}"/></th>
			<th><s:property value="%{getText('text.consignment.deliveryCompany')}"/></th>
			<th><s:property value="%{getText('text.consignment.status')}"/></th></tr>
		</thead>
		<tbody>
		<s:iterator value="searchResult">
			<tr><td><input type="checkbox" name="formId" value="<s:property value='id'/>"/></td>
				<td><s:property value='id'/></td>
				<td>			
					<a href="?formId=<s:property value='formId'/>">
					<s:property value="title" />
					</a>
				</td>
				<td><s:property value='commodityCategory.description'/></td>
				<td><s:property value='createTime'/></td>
				<td><s:property value='deliveryTime'/></td>
				<td><s:property value='company.name'/></td>
				<td><s:property value='deliveryCompany.name'/></td>
				<td><s:property value='status'/></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>

	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit type="button" theme="simple" cssClass="button" id="delete" name="action"  value="%{getText('text.submit.specialDelete')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SPECIAL_DELETE}');" align="left"></s:submit>
		</s:param>
	</s:component>
</s:form>

					</div>
					<div class="tab-content" id="<s:property value="pageCode"/>_form_tab">
					
<s:form enctype="multipart/form-data">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="title" label="%{getText('text.consignment.title')}" size="60"></s:textfield>
	<s:select name="companyId" label="%{getText('text.consignment.company')}" list="#providerUtil.allPartnerProviders" listKey="id" listValue="name"/>	
	<s:select name="deliveryCompanyId" label="%{getText('text.consignment.deliveryCompany')}" list="#providerUtil.allDeliveryProviders" listKey="id" listValue="name"/>	
	

	<s:textarea name="comment" label="%{getText('text.consignment.comment')}" cols="48" rows="5"></s:textarea>
	<s:file name="orderFile" label="%{getText('text.consignment.orderFile')}" size="60"></s:file>
	
	<s:component template="component.ftl">
		<s:param name="body">	
			<div class="pictureContainer">
			<s:iterator value="consignmentPictures">
				<span><img src="<s:property value="picturePath"/>" height="50"/><b><s:property value="pictureName"/></b></span>
			</s:iterator>
			</div>	
		</s:param>
	</s:component>
	<s:component template="component.ftl">
		<s:param name="body">
	<s:if test="status==null||status=='preparing'||status=='pending'">
		<s:if test="status!=null">
			<a class="button" rel="modal" href="<%=request.getContextPath()%>/protect/ajax/ajaxConsignmentPicture.do?formId=<s:property value="id"/>"><s:property value="%{getText('text.consignment.uploadPictures')}"/></a>
		</s:if>
			<s:submit theme="simple" type="button" cssClass="button" id="btnSave" name="actionButton" value="%{getText('text.submit.save')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_ALL}');"></s:submit>
		<s:if test="status=='preparing'">
			<s:submit theme="simple" type="button" cssClass="button" id="btnSave" name="actionButton" value="%{getText('text.submit.consign')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELIVER}');"></s:submit>
		</s:if>
			<a class="button" target="_blank" href="<%=request.getContextPath()%>/protect/ajax/consignmentOrdersPrint.do?formId=<s:property value="id"/>"><s:property value="%{getText('text.common.print')}"/></a>
	</s:if>
		</s:param>
	</s:component>
	
		<div class="table_container">
	<table class="listTable">
		<thead>
			<tr>
				<th><s:property value="%{getText('text.list.check')}"/>
				<th><s:property value="%{getText('text.commodityOrder.id')}" /></th>
				<th><s:property value="%{getText('text.commodityOrder.contact')}" /></th>
				<th><s:property value="%{getText('text.commodityOrder.contactPhone')}" /></th>
				<th><s:property value="%{getText('text.commodityOrder.dealTime')}" /></th>
				<th><s:property value="%{getText('text.commodityOrder.status')}"/></th>
				<th><s:property value="%{getText('text.commodityOrder.remark')}"/></th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="commodityOrders">
			<tr>
				<td>
					<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
				</td>
				<td>
					<a rel="modal" href="<%=request.getContextPath()%>/protect/ajax/ajaxOrder.do?formId=<s:property value='id'/>">
						<s:property value="id" />
					</a>
				</td><td>
					<s:property value="contact" />
				</td><td>
					<s:property value="contactPhone" />
				</td><td>
					<s:property value="dealTime" />
				</td><td>
					<s:property value="status" />
				</td><td>
					<s:property value="remark" />
				</td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	</div>
</s:form>

					</div> <!-- End #tab2 -->        
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
