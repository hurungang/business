<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/uploadify.css" type="text/css" media="screen">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.uploadify.min.js"></script>

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
	<runtech:page pager="${specialPager}" command="paging"></runtech:page>
	<tt:table value="searchResult" name="modelElement"  cssClass="listTable">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.contract.id')}">
			<a href="?formId=<s:property value='id'/>">
				<s:property value="id" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.contract.user')}">
			<s:property value="user.fullName" />
		</tt:column>
		<tt:column title="%{getText('text.contract.signTime')}">
			<s:property value="signTimeString" />
		</tt:column>
		<tt:column title="%{getText('text.contract.startTime')}">
				<s:property value="startTimeString" />
		</tt:column>
		<tt:column title="%{getText('text.contract.endTime')}">
			<s:property value="endTimeString" />
		</tt:column>
		<tt:column title="%{getText('text.contract.contractType')}">
			<s:property value="@com.runtech.onlineshop.form.SystemCodeForm@getCode(contractType,@com.runtech.web.util.Constant@CODE_CONTRACT_TYPE).name" />
		</tt:column>
		<tt:column title="%{getText('text.contract.contractFile')}">
			<s:if test="file!=null"><a class="download" target="_blank" href="<%=request.getContextPath()%>/protect/ajax/<s:property value='pageCode'/>.do?actionType=<s:property value="%{@com.runtech.web.util.Constant@ACTION_DOWNLOAD}"/>&formId=<s:property value="formId"/>"><s:property value="contractFile"/></a></s:if>
		</tt:column>
		<tt:column title="%{getText('text.contract.status')}">
			<s:property value="@com.runtech.onlineshop.form.SystemCodeForm@getCode(status,'status').name" />
		</tt:column>
	</tt:table>
	</div>
</s:form>

					</div>
					<div class="tab-content" id="<s:property value="pageCode"/>_form_tab">
<s:form enctype="multipart/form-data">
	<div class="input_container">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="user.fullName" label="%{getText('text.contract.user')}" disabled="true"></s:textfield>
	<s:select name="contractType" label="%{getText('text.contract.contractType')}" list="%{@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_CONTRACT_TYPE)}" listKey="code" listValue="name"/>
	<s:textfield name="signTimeString" label="%{getText('text.contract.signTime')}" cssClass="text-input datepicker"></s:textfield>
	<s:textfield name="startTimeString" label="%{getText('text.contract.startTime')}" cssClass="text-input datepicker"></s:textfield>
	<s:textfield name="endTimeString" label="%{getText('text.contract.endTime')}"  cssClass="text-input datepicker"></s:textfield>
	<s:select name="serviceAdminId" value="context.user.id" label="%{getText('text.contract.serviceAdmin')}" list="@com.runtech.onlineshop.form.AdminForm@getAllAdmins()" emptyOption="true" listKey="id" listValue="fullName"/>
	<s:file name="contract" label="%{getText('text.contract.contractFile')}" cssClass="uploadify" id="contract" accesskey="contractFilePath"></s:file>
	<s:if test="file!=null"><a class="download align-right" target="_blank" href="<%=request.getContextPath()%>/protect/ajax/<s:property value='pageCode'/>.do?actionType=<s:property value="%{@com.runtech.web.util.Constant@ACTION_DOWNLOAD}"/>&formId=<s:property value="formId"/>">&nbsp;</a></s:if>
	<s:hidden name="contractFilePath"/>
	</div>
	<div class="input_container">
	<s:textarea cssClass="wysiwyg" labelposition="top" name="contractContent" label="%{getText('text.content.content')}" cols="100"></s:textarea>

	</div>
	<div class="button_container">
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.save')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_ALL}')" align="left"></s:submit>
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.cancel')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}')" align="left" ></s:submit>
	</div>
</s:form>

					</div>
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
