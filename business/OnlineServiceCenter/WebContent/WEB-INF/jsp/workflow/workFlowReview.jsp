<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>

<s:set name="myReviewLogs" value="getMyReviewLogs(specialPager,context)"/>

<s:if test="documentType!=null">
<div class="content-box"><!-- Start Content Box -->
				<div class="content-box-header">
					
					<h3 style="cursor: s-resize; "><s:property value="%{currentMenu.title}"/></h3>
					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
	
<s:push value="getDocument(documentType,formId)">				
<s:form name="ajaxForm" cssClass="ajaxForm" enctype="multipart/form-data" action="ajax/contract" >
<s:include value="%{documentType}.jsp" ></s:include>
	
	<s:if test="getAuthorization(context).isSupervisable()">
	<div class="input_container">
		<s:textarea name="reviewComments" label="%{getText('text.workFlow.comments')}" cols="45" rows="5"></s:textarea>
	</div>
	<div class="button_container">
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.submit.approve')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_APPROVE}')" align="left"></s:submit>
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.submit.reject')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_REJECT}')" align="left"></s:submit>
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.submit.cancel')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}')" align="left" ></s:submit>
	</div>
	</s:if><s:elseif test="getAuthorization(context).isWritable()">
	<div class="input_container">
		<s:textarea name="reviewComments" label="%{getText('text.workFlow.comments')}" cols="45" rows="5"></s:textarea>
	</div>
	<div class="button_container">
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.save')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_ALL}')" align="left"></s:submit>
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.cancel')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}')" align="left" ></s:submit>
	</div>
	</s:elseif>
</s:form>
<div class="table_container">
	<tt:table value="flowLogs" name="modelElement">
		<tt:column title="%{getText('text.common.id')}">
				<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.submitter')}">
			<s:property value="adminBySubmitter.fullName" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.reviewer')}">
			<s:property value="adminByReviewer.fullName" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.reviewTime')}">
			<s:property value="%{getText('format.time',{reviewTime})}" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.previousStatus')}">
			<s:property value="@com.runtech.onlineshop.form.SystemCodeForm@getCode(previousStatus,'status').name" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.afterStatus')}">
			<s:property value="@com.runtech.onlineshop.form.SystemCodeForm@getCode(afterStatus,'status').name" />
		<tr><td colspan="6">
			<div id="reviewComments_<s:property value="id"/>" class="notification png_bg information"><div><span class="actionMessage"><s:property value="comments"/></span></div></div>
			</td>
		</tr>
		</tt:column>
	</tt:table>
	</s:push>
</div>					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
			
</s:if>
<script>
		$('#facebox').find('.close').click(
				function(){
					   location.reload();
				}
			);
</script>
