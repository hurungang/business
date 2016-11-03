<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:set name="pager" value="pager" scope="request"/>
<runtech:set name="positionCode" value="${param.position}"/>
<runtech:set name="typeCode" value="${param.type}"/>
<s:set name="specialModelList" value="getListByPositionAndType(specialPager,positionCode,typeCode)"/>
<div class="content-box"><!-- Start Content Box -->
				
				<div class="content-box-header">
					
					<h3 style="cursor: s-resize; ">Content box</h3>
					
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
	<table class="listTable">
		<thead>
			<tr><th><s:property value="%{getText('text.list.check')}"/></th>
			<th><s:property value="%{getText('text.content.id')}"/></th>
			<th><s:property value="%{getText('text.content.title')}"/></th>
			<th><s:property value="%{getText('text.content.status')}"/></th></tr>
		</thead>
		<tbody>
		<s:iterator value="specialModelList">
			<tr><td><input type="checkbox" name="formId" value="<s:property value='id'/>"/></td>
				<td><s:property value='id'/></td>
				<td>			
					<a href="?formId=<s:property value='formId'/>">
					<s:property value="title" />
					</a>
				</td>
				<td><s:property value='status'/></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_DELETE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_DELETE}" align="left"></s:submit>
	<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_DUPLICATE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_DUPLICATE}" align="left"></s:submit>
</s:form>
					</div>
					<div class="tab-content" id="<s:property value="pageCode"/>_form_tab">
<s:form enctype="multipart/form-data">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="title" label="%{getText('text.content.title')}" size="60"></s:textfield>
	<s:textfield name="author" label="%{getText('text.content.author')}" size="60"></s:textfield>
	<s:textfield name="priority" label="%{getText('text.content.priority')}" size="60"></s:textfield>
	<s:textfield name="source" label="%{getText('text.content.source')}" size="60"></s:textfield>
	<s:textarea name="remark" label="%{getText('text.content.remark')}" cols="80" rows="4"></s:textarea>
	<s:if test="picturePath!=null">
		<s:component template="component.ftl">
			<s:param name="body">
				<img src="<s:property value="picturePath"/>" width="200"/>
				<s:hidden name="picturePath"></s:hidden>
			</s:param>
		</s:component>
	</s:if>
	<s:file name="picture" label="%{getText('text.commodity.picture')}" size="60"></s:file>
	<s:textarea cssClass="ckeditor" labelposition="top" name="content" label="%{getText('text.content.content')}" cols="30" rows="40"></s:textarea>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.save')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_ALL}');"></s:submit>
			<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.cancel')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}');"></s:submit>
		</s:param>
	</s:component>
</s:form>

					</div> <!-- End #tab2 -->        
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->