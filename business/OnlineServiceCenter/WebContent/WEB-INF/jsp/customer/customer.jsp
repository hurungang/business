<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:set name="pager" value="pager" scope="request"/>
<s:bean id="area" name="com.runtech.onlineshop.form.AreaForm"></s:bean>
<s:set name="searchResult" value="searchModel.getSearchResult(specialPager,context)"/>

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
	<div class="input_container">
			<s:textfield labelposition="left" name="searchModel.fullName" label="%{getText('text.customer.fullName')}"/>
			<s:textfield cssClass="text-input datepicker" labelposition="left" name="searchModel.lastMaintainTimeString" label="%{getText('text.customer.lastMaintainTimeBefore')}"/>
			<s:submit cssClass="button" value="%{getText('text.submit.search')}"/>
	</div>
	<div class="table_container">
	<runtech:page pager="${specialPager}" command="paging"></runtech:page>
	<tt:table value="searchResult" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.customer.id')}">
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.customer.fullName')}">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="fullName" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.customer.admin')}">
				<s:property value="admin.fullName" />
		</tt:column>
		<tt:column title="%{getText('text.customer.userType')}">
			<s:property value="@com.runtech.onlineshop.form.SystemCodeForm@getCode(userType,'userType').name" />
		</tt:column>
		<tt:column title="%{getText('text.customer.phone')}">
			<s:property value="phone" />
		</tt:column>
		<tt:column title="%{getText('text.customer.mobile')}">
			<s:property value="mobile" />
		</tt:column>
		<tt:column title="%{getText('text.customer.lastMaintainTime')}">
			<s:property value="lastMaintainTimeString" />
		</tt:column>
		<tt:column title="%{getText('text.customer.status')}">
			<s:property value="@com.runtech.onlineshop.form.SystemCodeForm@getCode(status,'status').name" />
		</tt:column>
		<tt:column >
			<a class="button" rel="modal" href="<%=request.getContextPath()%>/protect/ajax/customerContract.do?userId=<s:property value="id"/>"><s:property value="%{getText('text.customer.contract')}"/></a>
			<a class="button" rel="modal" href="<%=request.getContextPath()%>/protect/ajax/customerMaintain.do?userId=<s:property value="id"/>"><s:property value="%{getText('text.customer.maintain')}"/></a>
		</tt:column>
	</tt:table>
	</div>
	<div class="button_container">
	<s:submit theme="simple" cssClass="button" id="delete" value="%{getText('text.submit.delete')}" name="action" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_SPECIAL_DELETE}');" align="left"></s:submit>
	<input type="button" class="button" onclick="switchTab('<s:property value="pageCode"/>','form_tab');" value=<s:property value="%{getText('text.submit.add')}"/>>
	</div>
</s:form>

					</div>
					<div class="tab-content" id="<s:property value="pageCode"/>_form_tab">
<s:form enctype="multipart/form-data">
	<div class="input_container">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:select name="areaId" label="%{getText('text.customer.areaId')}" list="#area.allAreas" listKey="id" listValue="name"/>
	<s:textfield name="name" label="%{getText('text.customer.name')}" required="true"></s:textfield>
	<s:textfield name="fullName" label="%{getText('text.customer.fullName')}" required="true"></s:textfield>
	<s:textfield name="email" label="%{getText('text.customer.email')}" ></s:textfield>
	<s:if test="picturePath!=null">
		<a class="download align-right" target="_blank" href="<s:property value='picturePath'/>">&nbsp;</a>
	</s:if>
	<s:file name="picture" label="%{getText('text.customer.picture')}" ></s:file>
	<s:select name="userType" label="%{getText('text.customer.userType')}" list="@com.runtech.onlineshop.form.SystemCodeForm@getCodes('userType')" listKey="code" listValue="name"/>
	<s:select name="serviceAdminId" value="context.user.id" label="%{getText('text.customer.serviceAdmin')}" list="@com.runtech.onlineshop.form.AdminForm@getAllAdmins()" emptyOption="true" listKey="id" listValue="fullName"/>
	<s:textfield name="contact" label="%{getText('text.customer.contact')}" ></s:textfield>
	<s:textfield name="contactPosition" label="%{getText('text.customer.contactPosition')}" ></s:textfield>
	<s:textfield name="contactQQ" label="%{getText('text.customer.contactQQ')}" ></s:textfield>
	<s:textfield name="contactOther" label="%{getText('text.customer.contactOther')}" ></s:textfield>
	<s:textfield name="phone" label="%{getText('text.customer.phone')}" required="true"></s:textfield>
	<s:textfield name="mobile" label="%{getText('text.customer.mobile')}" required="true"></s:textfield>
	<s:textfield name="website" label="%{getText('text.customer.website')}" ></s:textfield>
	<s:textfield name="fax" label="%{getText('text.customer.fax')}" ></s:textfield>
	<s:select name="credentialType" label="%{getText('text.customer.credentialType')}" list="@com.runtech.onlineshop.form.SystemCodeForm@getCodes('credentialType')" listKey="code" listValue="name"/>
	<s:textfield name="credentialNumber" label="%{getText('text.customer.credentialNumber')}" ></s:textfield>
	<s:select name="importance" label="%{getText('text.customer.importance')}" list="@com.runtech.onlineshop.form.SystemCodeForm@getCodes('userImportance')" listKey="code" listValue="name"/>
	<s:textfield name="address" label="%{getText('text.customer.address')}" ></s:textfield>
	<s:textarea name="remark" label="%{getText('text.customer.remark')}" cols="45" rows="5"></s:textarea>
	</div>
	<div class="button_container">
			<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.save')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE}')" align="left"></s:submit>
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.cancel')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}')" align="left" ></s:submit>
	</div>
</s:form>

					</div>
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
