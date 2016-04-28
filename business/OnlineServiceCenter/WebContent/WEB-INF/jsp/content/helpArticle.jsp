<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:set name="pager" value="pager" scope="request"/>
<runtech:set name="typeCode" value="help"/>
<s:bean id="commodityCategory" name="com.runtech.onlineshop.form.CommodityCategoryForm"></s:bean>
<s:bean id="contentForm" name="com.runtech.onlineshop.form.ContentForm"></s:bean>
<s:set name="specialModelList" value="getListByType(specialPager,typeCode)"/>
<s:form>
	<runtech:page pager="${specialPager}" command="paging"></runtech:page>
	<tt:table value="specialModelList" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.content.id')}" sortKey="id" sortable="true" >
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.content.title')}">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="title" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.content.author')}">
				<s:property value="author" />
		</tt:column>
		<tt:column title="%{getText('text.content.status')}">
			<s:property value="status" />
		</tt:column>
	</tt:table>
	<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_DELETE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_DELETE}" align="left"></s:submit>
	<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_DUPLICATE}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_DUPLICATE}" align="left"></s:submit>
</s:form>
<s:form enctype="multipart/form-data">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:select name="commodityCategoryId" label="%{getText('text.commodity.categoryId')}" list='#commodityCategory.getAllCommodityCategory(@com.runtech.web.util.Constant@CODE_HELP_CATEGORY_ROOT," - ")' listKey="id" listValue="description"/>
	<s:select name="positionCode" label="%{getText('text.content.position')}" list='@com.runtech.onlineshop.form.SiteModuleForm@getAllModules(@com.runtech.web.util.Constant@CODE_HELP_CATEGORY_ROOT," - ")' listKey="code" listValue="name"/>
	<s:if test="%{typeCode==null}">
		<s:select name="typeCode" label="%{getText('text.content.type')}" list="@com.runtech.onlineshop.form.SystemCodeForm@getCodes('articleType')" listKey="code" listValue="name"/>
	</s:if>
	<s:else>
		<s:hidden name="typeCode" value="%{typeCode}"/>
	</s:else>
	<s:textfield name="title" label="%{getText('text.content.title')}" size="60"></s:textfield>
	<s:textfield name="author" label="%{getText('text.content.author')}" size="60"></s:textfield>
	<s:textfield name="priority" label="%{getText('text.content.priority')}" size="60"></s:textfield>
	<s:textfield name="source" label="%{getText('text.content.source')}" size="60"></s:textfield>
	<s:textarea name="remark" label="%{getText('text.content.remark')}" cols="48" rows="4"></s:textarea>
	<s:if test="picturePath!=null">
		<s:component template="component.ftl">
			<s:param name="body">
				<img src="<s:property value="picturePath"/>" width="200"/>
				<s:hidden name="picturePath"></s:hidden>
			</s:param>
		</s:component>
	</s:if>
	<s:file name="picture" label="%{getText('text.commodity.picture')}" size="60"></s:file>
	<s:textarea name="keywords" label="%{getText('text.commodity.keywords')}" cols="48" rows="2"></s:textarea>
	<s:textarea cssClass="ckeditor" labelposition="top" name="content" label="%{getText('text.content.content')}" cols="30" rows="40"></s:textarea>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.save')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_ALL}');"></s:submit>
			<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.cancel')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}');"></s:submit>
		</s:param>
	</s:component>
</s:form>
