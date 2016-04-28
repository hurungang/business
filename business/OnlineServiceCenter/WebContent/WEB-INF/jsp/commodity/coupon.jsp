<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:set name="pager" value="pager" scope="request"/>
<s:set name="searchResult" value="searchModel.getSearchResult(specialPager)"/>
<s:form>

	<s:component template="component.ftl">
		<s:param name="body">		
			<s:textfield name="searchModel.name" label="%{getText('text.coupon.name')}" size="30"/>	
			<s:textfield name="searchModel.number" label="%{getText('text.coupon.number')}" size="30"/>	
			<s:select name="searchModel.status" label="%{getText('text.coupon.status')}" list="@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_COUPON_STATUS)" listKey="code" listValue="name"/>
			<s:submit value="%{getText('text.submit.search')}"/>
		</s:param>
	</s:component>
	<runtech:page pager="${specialPager}" command="paging"></runtech:page>
	<tt:table value="searchResult" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.coupon.id')}">
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.coupon.name')}">
				<s:property value="name" />
		</tt:column>
		<tt:column title="%{getText('text.coupon.number')}">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="number" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.coupon.value')}">
				<s:property value="value" />
		</tt:column>
		<tt:column title="%{getText('text.coupon.startDate')}">
			<s:property value="startDateString" />
		</tt:column>
		<tt:column title="%{getText('text.coupon.expireDate')}">
			<s:property value="expireDateString" />
		</tt:column>
		<tt:column title="%{getText('text.coupon.status')}">
			<s:property value="status" />
		</tt:column>
	</tt:table>

	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit type="button" cssClass="button" id="create" name="action"  value="%{getText('text.submit.create')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_CREATE}');" align="left"></s:submit>
			<s:textarea name="remark" label="%{getText('text.coupon.remark')}" rows="5" cols="60"/>
			<s:submit type="button" cssClass="button" id="consume" name="action"  value="%{getText('text.submit.consume')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_CONSUME}');" align="left"></s:submit>
		</s:param>
	</s:component>
</s:form>

<s:form enctype="multipart/form-data">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="name" label="%{getText('text.coupon.name')}" size="60" readonly="true"></s:textfield>
	<s:textfield name="number" label="%{getText('text.coupon.number')}" size="60" readonly="true"></s:textfield>
	<s:textfield name="requireOrderSum" label="%{getText('text.coupon.requireOrderSum')}" size="60" readonly="true"></s:textfield>
	<s:textfield name="value" label="%{getText('text.coupon.value')}" size="60" readonly="true"></s:textfield>
	<s:textfield name="startDate" label="%{getText('text.coupon.startDate')}" readonly="true"></s:textfield>
	<s:textfield name="expireDate" label="%{getText('text.coupon.expireDate')}" readonly="true"></s:textfield>
	<s:textfield name="createDate" label="%{getText('text.coupon.createDate')}" readonly="true"></s:textfield>
	<s:textfield name="consumeDate" label="%{getText('text.coupon.consumeDate')}" readonly="true"></s:textfield>
	<s:textfield name="status" label="%{getText('text.coupon.status')}" readonly="true"></s:textfield>

	<s:textarea name="remark" label="%{getText('text.coupon.remark')}" cols="48" rows="2" readonly="true"></s:textarea>

</s:form>
