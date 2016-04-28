<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
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

<s:form>
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="name" label="%{getText('text.administrator.name')}" disabled="true" value="%{user.name}"></s:textfield>
	<s:textfield name="fullName" label="%{getText('text.administrator.fullName')}" value="%{user.fullName}"></s:textfield>
	<s:textfield name="email" label="%{getText('text.administrator.email')}" value="%{user.email}"></s:textfield>
	<s:textfield name="phone" label="%{getText('text.administrator.phone')}" value="%{user.phone}"></s:textfield>
	<s:textfield name="mobile" label="%{getText('text.administrator.mobile')}" value="%{user.mobile}"></s:textfield>
	<s:textarea name="address" label="%{getText('text.administrator.address')}" value="%{user.address}"></s:textarea>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_UPDATE_ACCOUNT}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_SAVE}" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>

					</div> <!-- End #tab1 -->
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
