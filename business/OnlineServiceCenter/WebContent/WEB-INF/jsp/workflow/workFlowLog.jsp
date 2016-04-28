<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:set name="myReviewLogs" value="getMyReviewLogs(specialPager,context)"/>

<div class="content-box"><!-- Start Content Box -->
				<div class="content-box-header">
					
					<h3 style="cursor: s-resize; "><s:property value="%{getText('text.workFlowReviewLog.title')}"/></h3>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">

<s:form>
	<div class="table_container">
	<tt:table value="myReviewLogs" name="modelElement">
		<tt:column title="%{getText('text.common.id')}">
				<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.submitter')}">
			<s:property value="adminBySubmitter.fullName" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.reviewer')}">
			<s:property value="adminByReviewer.fullName" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.documentType')}">
			<s:property value="@com.runtech.onlineshop.form.SystemCodeForm@getCode(documentType,'documentType').name" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.documentId')}">
			<a class="button" rel="modal" href="<%=request.getContextPath()%>/protect/ajax/workFlowReview.do?readOnly&documentType=<s:property value="documentType"/>&formId=<s:property value="documentId"/>"><s:property value="documentId" /></a>
		</tt:column>
		<tt:column title="%{getText('text.workFlow.reviewTime')}">
			<s:property value="%{getText('format.time',{reviewTime})}" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.previousStatus')}">
			<s:property value="@com.runtech.onlineshop.form.SystemCodeForm@getCode(previousStatus,'status').name" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.afterStatus')}">
			<a  class="button" rel="modal" href="#reviewComments_<s:property value="id"/>"><s:property value="@com.runtech.onlineshop.form.SystemCodeForm@getCode(afterStatus,'status').name" /></a>
			<div id="reviewComments_<s:property value="id"/>" style="display:none" class="notification png_bg information"><div><span class="actionMessage"><s:property value="comments"/></span></div></div>
		</tt:column>
	</tt:table>
	</div>
</s:form>
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->

			
<s:set name="mySubmitLogs" value="getMySubmitLogs(specialPager,context)"/>
<div class="content-box"><!-- Start Content Box -->
				<div class="content-box-header">
					
					<h3 style="cursor: s-resize; "><s:property value="%{getText('text.workFlowSubmitLog.title')}"/></h3>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">

<s:form>
	<div class="table_container">
	<tt:table value="mySubmitLogs" name="modelElement">
		<tt:column title="%{getText('text.common.id')}">
				<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.submitter')}">
			<s:property value="adminBySubmitter.fullName" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.reviewer')}">
			<s:property value="adminByReviewer.fullName" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.documentType')}">
			<s:property value="@com.runtech.onlineshop.form.SystemCodeForm@getCode(documentType,'documentType').name" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.documentId')}">
			<a class="button" rel="modal" href="<%=request.getContextPath()%>/protect/ajax/workFlowReview.do?readOnly&documentType=<s:property value="documentType"/>&formId=<s:property value="documentId"/>"><s:property value="documentId" /></a>
		</tt:column>
		<tt:column title="%{getText('text.workFlow.reviewTime')}">
			<s:property value="%{getText('format.time',{reviewTime})}" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.previousStatus')}">
			<s:property value="@com.runtech.onlineshop.form.SystemCodeForm@getCode(previousStatus,'status').name" />
		</tt:column>
		<tt:column title="%{getText('text.workFlow.afterStatus')}">
			<a  class="button" rel="modal" href="#reviewComments_<s:property value="id"/>"><s:property value="@com.runtech.onlineshop.form.SystemCodeForm@getCode(afterStatus,'status').name" /></a>
			<div id="reviewComments_<s:property value="id"/>" style="display:none" class="notification png_bg information"><div><span class="actionMessage"><s:property value="comments"/></span></div></div>
		</tt:column>
	</tt:table>
	</div>
</s:form>
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->