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
						<li><a href="#tab1" class="default-tab current">Form</a></li> <!-- href must be unique and match the id of target div -->
					</ul>
					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					
					<div class="tab-content default-tab" id="tab1" style="display: block; "> <!-- This is the target div. id must match the href of this div's tab -->

<s:form enctype="multipart/form-data">
	<div class="input_container">
	<s:hidden name="formId" value="%{user.id}"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:select name="areaId" label="%{getText('text.customer.areaId')}" list="#area.allAreas" value="%{user.areaId}" listKey="id" listValue="name"/>
	<s:textfield name="name" label="%{getText('text.customer.name')}" value="%{user.name}" readonly="true"></s:textfield>
	<s:textfield name="fullName" label="%{getText('text.customer.fullName')}" value="%{user.fullName}"></s:textfield>
	<s:textfield name="email" label="%{getText('text.customer.email')}" value="%{user.email}"></s:textfield>
	<s:textfield name="phone" label="%{getText('text.customer.phone')}" value="%{user.phone}"></s:textfield>
	<s:textfield name="mobile" label="%{getText('text.customer.mobile')}" value="%{user.mobile}"></s:textfield>
	<s:textfield name="contact" label="%{getText('text.customer.contact')}" value="%{user.contact}"></s:textfield>
	<s:textfield name="contactPosition" label="%{getText('text.customer.contactPosition')}" value="%{user.contactPosition}"></s:textfield>
	<s:textfield name="website" label="%{getText('text.customer.website')}" value="%{user.website}"></s:textfield>
	<s:textfield name="fax" label="%{getText('text.customer.fax')}" value="%{user.fax}"></s:textfield>
	<s:textfield name="age" label="%{getText('text.customer.age')}" value="%{user.age}"></s:textfield>
	<s:select name="sex" label="%{getText('text.customer.sex')}" list="%{@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_SEX)}" listKey="code" listValue="name"/>
	<s:select name="credentialType" label="%{getText('text.customer.credentialType')}" list="%{@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_CREDENTIAL_TYPE)}" listKey="code" listValue="name"/>
	<s:textfield name="credentialNumber" label="%{getText('text.customer.credentialNumber')}" value="%{user.credentialNumber}"></s:textfield>
	<s:textfield name="address" label="%{getText('text.customer.address')}" value="%{user.address}"></s:textfield>
	<s:textarea name="remark" label="%{getText('text.customer.remark')}" cols="45" rows="5" value="%{user.remark}"></s:textarea>
	</div>
	<div class="button_container">
			<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.save')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_UPDATE_ACCOUNT}')" align="left"></s:submit>
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.cancel')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}')" align="left" ></s:submit>
	</div>
</s:form>

					</div> <!-- End #tab1 -->
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->