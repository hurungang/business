<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>

<runtech:set name="userId" value="${param.userId}"/>

<div class="content-box  column-left"><!-- Start Content Box -->
				<div class="content-box-header">
					
					<h3 style="cursor: s-resize; "><s:property value="%{getText('text.userLoginLog.title')}"/></h3>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">

<s:form>
	<div class="table_container">
	<tt:table value="user.loginLogs" name="modelElement">
		<tt:column title="%{getText('text.userLoginLog.id')}">
				<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.userLoginLog.loginFrom')}">
			<s:property value="loginFrom" />
		</tt:column>
		<tt:column title="%{getText('text.userLoginLog.loginMachine')}">
			<s:property value="loginMachine" />
		</tt:column>
		<tt:column title="%{getText('text.userLoginLog.loginTime')}">
				<s:property value="%{getText('format.time',{loginTime})}" />
		</tt:column>
		<tt:column title="%{getText('text.userLoginLog.loginResult')}">
			<div class="notification-small <s:if test="loginResult">success"><div><s:property value="%{getText('text.userLoginLog.loginResult.true')}"/></div></s:if><s:else>error"><div><s:property value="%{getText('text.userLoginLog.loginResult.false')}"/></div></s:else></div>
		</tt:column>
	</tt:table>
	</div>
</s:form>
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
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