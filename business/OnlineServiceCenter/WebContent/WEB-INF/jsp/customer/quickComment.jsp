<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:set name="pager" value="pager" scope="request"/>
<runtech:set name="status" value="${param.status}"/>
<s:set name="specialModelList" value="getListByStatus(specialPager,status)"/>

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
	<runtech:page pager="${specialPager}" command="paging"></runtech:page>
	<tt:table value="specialModelList" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.quickComment.id')}" sortKey="id" sortable="true" >
			<a href="?formId=<s:property value='formId'/>">
			<s:property value="id" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.quickComment.clientName')}">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="clientName" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.quickComment.commentTime')}">
				<s:property value="commentTime" />
		</tt:column>
		<tt:column title="%{getText('text.quickComment.status')}">
			<s:property value="status" />
		</tt:column>
	</tt:table>
	<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_DELETE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_DELETE}" align="left"></s:submit>
</s:form>
					</div>
					<div class="tab-content" id="<s:property value="pageCode"/>_form_tab">
<s:form enctype="multipart/form-data">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<div class="input_container">
	<s:textfield name="clientName" label="%{getText('text.quickComment.clientName')}" size="60" readonly="true"></s:textfield>
	</div>	
	<div class="input_container">
	<s:textarea cssClass="wysiwyg" labelposition="top" name="commentContent" label="%{getText('text.quickComment.commentContent')}" readonly="true" cols="60" rows="10" ></s:textarea>
	</div>
	<div class="input_container">
	<s:textarea cssClass="wysiwyg" labelposition="top" name="replyContent" label="%{getText('text.quickComment.replyContent')}" cols="60" rows="10"></s:textarea>
	</div>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.save')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE}');"></s:submit>
			<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.cancel')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}');"></s:submit>
		</s:param>
	</s:component>
</s:form>
					</div>
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->