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
	<s:password name="oldPassword" label="%{getText('text.customer.oldPassword')}" value=""></s:password>
	<s:password name="newPassword" label="%{getText('text.customer.newPassword')}" value=""></s:password>
	<s:password name="confirmPassword" label="%{getText('text.customer.confirmPassword')}" value=""></s:password>
	</div>
	<div class="button_container">
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.changePassword')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_CHANGE_PASSWORD}')" align="left"></s:submit>
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.cancel')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}')" align="left" ></s:submit>
	</div>
</s:form>

					</div> <!-- End #tab1 -->
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->