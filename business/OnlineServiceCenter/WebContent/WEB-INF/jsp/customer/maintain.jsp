<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:set name="pager" value="pager" scope="request"/>
<runtech:set name="userId" value="${param.userId}"/>
<s:set name="searchResult" value="searchModel.getSearchResult(specialPager,userId,context)"/>

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
			<s:textfield labelposition="left" name="searchModel.user.fullName" label="%{getText('text.customer.fullName')}"/>
			<s:submit cssClass="button" value="%{getText('text.submit.search')}"/>
	</div>
	<div class="table_container">
	<runtech:page pager="${specialPager}" action="../ajax/customerMaintain.do" command="paging"></runtech:page>
	<tt:table value="searchResult" name="modelElement" cssClass="listTable">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.customerMaintain.id')}">
			<a href="?formId=<s:property value='id'/>">
				<s:property value="id" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.customerMaintain.user')}">
			<s:property value="user.fullName" />
		</tt:column>
		<tt:column title="%{getText('text.customerMaintain.maintainTime')}">
				<s:property value="maintainTimeString" />
		</tt:column>
		<tt:column title="%{getText('text.customerMaintain.summary')}">
			<s:property value="summary" />
		</tt:column>
		<tt:column title="%{getText('text.customerMaintain.status')}">
			<s:property value="@com.runtech.onlineshop.form.SystemCodeForm@getCode(status,'status').name" />
		</tt:column>
	</tt:table>
	</div>
</s:form>

					</div>
					<div class="tab-content" id="<s:property value="pageCode"/>_form_tab">
<s:form >
	<div class="input_container">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="user.fullName" label="%{getText('text.contract.user')}" disabled="true"></s:textfield>
	<s:textfield name="maintainTimeString" label="%{getText('text.customerMaintain.maintainTime')}" cssClass="text-input datepicker"></s:textfield>
	<s:select name="serviceAdminId" value="context.user.id" label="%{getText('text.customerMaintain.serviceAdmin')}" list="@com.runtech.onlineshop.form.AdminForm@getAllAdmins()" listKey="id" listValue="fullName"/>
	<s:textarea name="summary" label="%{getText('text.customerMaintain.summary')}" cols="113"></s:textarea>
	</div>
	<div class="input_container">	
	<s:textarea cssClass="wysiwyg" labelposition="top" name="detail" label="%{getText('text.customerMaintain.detail')}"></s:textarea>
	</div>
	<runtech:set name="oppContainerStyle" value="display"/>
	<s:if test="opportunityValue==null">
		<div class="input_container">	
			<input type="checkbox" id="oppSwitch" name="oppSwitch" container="opportunityContainer" class="showSwitch"/><s:property value="%{getText('text.customerMaintain.opportunityShowHide')}"/>
		</div>
		<runtech:set name="oppContainerStyle" value="display:none"/>
	</s:if>
	<div id="opportunityContainer" style="<s:property value="oppContainerStyle"/>">
		<div class="input_container">	
		<s:textfield name="opportunityValue" label="%{getText('text.customerMaintain.opportunityValue')}" ></s:textfield>
		<s:textfield name="opportunityPossibilityPercentage" label="%{getText('text.customerMaintain.opportunityPossibility')}" cssClass="text-input small-input"></s:textfield><span class="input-suffix">%</span>
		<s:textfield name="opportunityTimeString" label="%{getText('text.customerMaintain.opportunityTime')}" cssClass="text-input datepicker"></s:textfield>
		<s:select name="opportunityStatus" label="%{getText('text.customerMaintain.opportunityStatus')}" list="%{@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_OPPORTUNITY_STATUS)}" listKey="code" listValue="name"/>
		</div>
		<div class="input_container">	
		<s:textarea cssClass="wysiwyg" labelposition="top" name="opportunity" label="%{getText('text.customerMaintain.opportunity')}"></s:textarea>
		</div>
	</div>
	<div class="button_container">
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.save')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE}')" align="left"></s:submit>
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.cancel')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}')" align="left" ></s:submit>
	</div>
</s:form>

					</div>
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
