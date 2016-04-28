<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:set name="pager" value="pager" scope="request"/>

<s:form>
	<runtech:page pager="${pager}" command="paging"></runtech:page>
	<tt:table value="modelList" clientSort="false" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.content.id')}">
				<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.reportConfiguration.name')}">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.reportConfiguration.updateTime')}">
				<s:property value="updateTime" />
		</tt:column>
		<tt:column title="%{getText('text.reportConfiguration.status')}">
			<s:property value="status" />
		</tt:column>
	</tt:table>
	<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_DELETE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_DELETE}" align="left"  onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DDELETE}');"></s:submit>
	<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_DUPLICATE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_DUPLICATE}" align="left"  onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DUPLICATE}');"></s:submit>
</s:form>

<s:form target="report">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:property value="form" escape="false"/>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" cssClass="report" id="%{@com.runtech.web.util.Constant@ACTION_REPORT}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_REPORT}" align="left"  onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_REPORT}');"></s:submit>
		</s:param>
	</s:component>
</s:form>
