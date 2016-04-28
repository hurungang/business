<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:set name="pager" value="pager" scope="request"/>
<s:bean id="commodity" name="com.runtech.onlineshop.form.CommodityForm"></s:bean>

<s:set name="pictureListPager" value="getSpecialPager(pictureListCount)"/>

<s:form>
	<runtech:page pager="${pictureListPager}" command="paging"></runtech:page>
	<tt:table value="pictureList">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='id'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.commodityPicture.id')}">
				<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.commodityPicture.category')}">
			<a href="?commodityId=<s:property value='commodityId'/>&formId=<s:property value='id'/>">
			<s:property value="category" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodityPicture.commodityId')}">
			<a href="commodity.do?formId=<s:property value="commodity.id"/>">
			<s:property value="commodity.name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodityPicture.path')}">
			<img src='<s:property value="path"/>' width="100" />
		</tt:column>
	</tt:table>
	<s:hidden name="commodityId"></s:hidden>
	<input type="hidden" name="nextLocation" value="commodity/commodityPicture.do?commodityId=<s:property value='commodityId'/>"/>
	<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.delete')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE}');"></s:submit>
</s:form>

<s:form enctype="multipart/form-data">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:hidden name="commodityId"></s:hidden>
	<input type="hidden" name="nextLocation" value="commodity/commodityPicture.do?commodityId=<s:property value='commodityId'/>"/>
	<s:select name="commodityId" label="%{getText('text.commodityPicture.commodityId')}" list="#commodity.allCommodity" listKey="id" listValue="name" disabled="true"/>
	<s:select name="category" label="%{getText('text.commodityPicture.category')}" list="%{@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_PICTURE_CATEGORY)}" listKey="code" listValue="name"/>
	<s:if test="path!=null">
	<s:component template="component.ftl">
		<s:param name="body">
			<img src="<s:property value="path"/>" width="200"/>
		</s:param>
	</s:component>
	</s:if>
	<s:file name="picture" label="%{getText('text.commodityPicture.path')}" size="60"></s:file>
	
	<s:textfield name="description" label="%{getText('text.commodityPicture.description')}" size="60"></s:textfield>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit type="button" theme="simple" cssClass="button" id="save" name="action"  value="%{getText('text.submit.save')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SPECIAL_SAVE}');" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>