<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:set name="pager" value="pager" scope="request"/>
<s:bean id="area" name="com.runtech.onlineshop.form.AreaForm"></s:bean>
<s:set name="searchResult" value="searchModel.getSearchResult(specialPager)"/>
<s:form>

	<s:component template="component.ftl">
		<s:param name="body">		
			<s:select theme="css_xhtml" name="searchModel.typeCode"label="%{getText('text.commodityPromotion.typeCode')}" list="%{@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_PROMOTION_TYPE)}" listKey="code" listValue="name"/>
			<s:textfield theme="css_xhtml" name="searchModel.startTimeString" label="%{getText('text.commodityPromotion.startTime')}" cssClass="timepicker"/>	
			<s:textfield theme="css_xhtml" name="searchModel.endTimeString" label="%{getText('text.commodityPromotion.endTime')}" cssClass="timepicker"/>	
			<s:textfield theme="css_xhtml" name="searchModel.title" label="%{getText('text.commodityPromotion.title')}" size="60"/>	
			<s:submit theme="simple" value="%{getText('text.submit.search')}"/>
		</s:param>
	</s:component>
	<runtech:page pager="${specialPager}" command="paging"></runtech:page>
	<tt:table value="searchResult" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.commodityPromotion.id')}">
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.commodityPromotion.typeTitle')}">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="typeTitle" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodityPromotion.commodityId')}">
			<a href="commodity.do?formId=<s:property value='commodity.id'/>">
				<s:property value="commodity.name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodityPromotion.startTime')}">
			<s:property value="startTime" />
		</tt:column>
		<tt:column title="%{getText('text.commodityPromotion.endTime')}">
			<s:property value="endTime" />
		</tt:column>
		<tt:column title="%{getText('text.commodityPromotion.status')}">
			<s:property value="status" />
		</tt:column>
	</tt:table>

	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit type="button" theme="simple" cssClass="button" id="delete" name="action"  value="%{getText('text.submit.specialDelete')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SPECIAL_DELETE}');" align="left"></s:submit>
			<s:submit type="button" theme="simple" cssClass="button" id="duplicate" name="action"  value="%{getText('text.submit.duplicate')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DUPLICATE}');" align="left"></s:submit>
			<s:submit type="button" theme="simple" cssClass="button" id="duplicateAll" name="action"  value="%{getText('text.submit.duplicateAll')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DUPLICATE_ALL}');" align="left"></s:submit>
		</s:param>
	</s:component>
</s:form>

<s:form enctype="multipart/form-data">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:select name="commodityId" label="%{getText('text.commodityPromotion.commodityId')}" list="%{@com.runtech.onlineshop.form.CommodityForm@getAllCommodity()}" listKey="id" listValue="shortName"/>
	<s:select name="typeCode" label="%{getText('text.commodityPromotion.typeCode')}" list="%{@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_PROMOTION_TYPE)}" listKey="code" listValue="name"/>
	<s:select name="specialFlag" label="%{getText('text.commodityPromotion.specialFlag')}" list="%{@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_PROMOTION_SPECIAL_FLAG)}" listKey="code" listValue="name" emptyOption="true"/>
	<s:textfield name="typeTitle" label="%{getText('text.commodityPromotion.typeTitle')}" size="60"></s:textfield>
	<s:textfield name="title" label="%{getText('text.commodityPromotion.title')}" size="60"></s:textfield>
	<s:textfield name="price" label="%{getText('text.commodityPromotion.price')}" size="60"></s:textfield>
	<s:textfield name="minOrderCount" label="%{getText('text.commodityPromotion.minOrderCount')}" size="60"></s:textfield>
	<s:textfield name="currentOrderCount" label="%{getText('text.commodityPromotion.currentOrderCount')}" size="60"></s:textfield>
	<s:textfield name="maxOrderCount" label="%{getText('text.commodityPromotion.maxOrderCount')}" size="60"></s:textfield>
	<s:textfield name="maxIndividualOrderCount" label="%{getText('text.commodityPromotion.maxIndividualOrderCount')}" size="60"></s:textfield>
	<s:select name="userType" label="%{getText('text.customer.userType')}" list="@com.runtech.onlineshop.form.SystemCodeForm@getCodes('userType')" listKey="code" listValue="name"/>
	<s:textfield name="userLevel" label="%{getText('text.commodityPromotion.userLevel')}" size="60"></s:textfield>
	<s:textfield name="additionalCondition" label="%{getText('text.commodityPromotion.additionalCondition')}" size="60"></s:textfield>
	<s:textfield name="startTimeString" label="%{getText('text.commodityPromotion.startTime')}" cssClass="timepicker"></s:textfield>
	<s:textfield name="endTimeString" label="%{getText('text.commodityPromotion.endTime')}" cssClass="timepicker"></s:textfield>
	<s:textfield name="priority" label="%{getText('text.commodityPromotion.priority')}" size="60"></s:textfield>

	<s:select name="status" label="%{getText('text.commodityPromotion.status')}" list="%{@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_PROMOTION_STATUS)}" listKey="code" listValue="name"/>
	<s:textarea name="description" label="%{getText('text.commodityPromotion.description')}" cols="48" rows="2"></s:textarea>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" type="button" cssClass="button" id="btnSave" name="actionButton" value="%{getText('text.submit.save')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE}');"></s:submit>
			<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.cancel')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}');"></s:submit>
		</s:param>
	</s:component>
</s:form>
