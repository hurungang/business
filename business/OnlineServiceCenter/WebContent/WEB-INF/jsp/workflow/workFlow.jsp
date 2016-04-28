<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>

<s:set name="pager" value="pager" scope="request"/>
<runtech:set name="userId" value="${param.userId}"/>


<div class="content-box  column-left"><!-- Start Content Box -->
				<s:iterator value="user.workFlows" id="workFlow">
				<div class="content-box-header">
					<h3 style="cursor: s-resize; "><s:property value="workFlowName" /></h3>
				</div> <!-- End .content-box-header -->
				<div class="content-box-content">
				<s:form>
					<div class="table_container">
					<tt:table value="getPendingReviewForms(pager)" name="modelElement">
						<tt:column title="%{getText('text.workFlow.id')}">
							<s:property value="id" />
						</tt:column>
						<tt:column title="%{getText('text.workFlow.submitter')}">
							<s:property value="admin.fullName" />
						</tt:column>
						<tt:column title="%{getText('text.workFlow.updateTime')}">
							<s:property value="%{getText('format.time',{updateTime})}" />
						</tt:column>
						<tt:column >
							<a class="button" rel="modal" href="<%=request.getContextPath()%>/protect/ajax/workFlowReview.do?documentType=<s:property value="documentType"/>&formId=<s:property value="id"/>"><s:property value="%{getText('text.workFlow.review')}"/></a>
						</tt:column>
					</tt:table>
					</div>
				</s:form>
				</div>
				</s:iterator>
</div>
				
				
<div class="content-box  column-right"><!-- Start Content Box -->
				<div class="content-box-header">
					
					<h3 style="cursor: s-resize; "><s:property value="%{getText('text.workFlow.title')}"/></h3>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
				<s:form>
					<div class="table_container">
					<tt:table value="user.workFlows" name="modelElement">
						<tt:column title="%{getText('text.workFlow.workFlowName')}">
							<s:property value="workFlowName" />
						</tt:column>
						<tt:column title="%{getText('text.workFlow.pendingReviewCount')}">
							<s:property value="pendingReviewCount" />
						</tt:column>
					</tt:table>
					</div>
				</s:form>
				</div>
</div>