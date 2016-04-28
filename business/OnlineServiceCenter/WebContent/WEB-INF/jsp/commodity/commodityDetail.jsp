<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>
<s:set name="pager" value="pager" scope="request"/>
<s:bean id="commodity" name="com.runtech.onlineshop.form.CommodityForm"></s:bean>

<s:set name="detailListPager" value="getSpecialPager(detailListCount)"/>

<s:form >
	<s:hidden name="commodityId"></s:hidden>
	<runtech:page pager="${detailListPager}" command="paging"></runtech:page>
	<tt:table value="detailList" name="modelElement">
		<tt:column title="%{getText('text.list.check')}" >
			<input type="checkbox" name="formId" value="<s:property value='id'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.commodityDetail.id')}">
			<a href="commodity.do?formId=<s:property value="commodity.id"/>">
			<s:property value="id" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodityDetail.commodityId')}">
			<a href="commodity.do?formId=<s:property value="commodity.id"/>">
			<s:property value="commodity.name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodityDetail.detailCode')}">
			<a href="?commodityId=<s:property value="commodity.id"/>&formId=<s:property value='id'/>">
				<s:property value="detailCode" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodityDetail.introduction')}">
			<s:property value="introduction.substring(0,300)+'...'" />
		</tt:column>
	</tt:table>
	<s:hidden name="commodityId"></s:hidden>
	<input type="hidden" name="nextLocation" value="commodity/commodityDetail.do?commodityId=<s:property value='commodityId'/>"/>
	<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.delete')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE}');"></s:submit>
</s:form>

<s:form  enctype="multipart/form-data">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:hidden name="commodityId"></s:hidden>
	<input type="hidden" name="nextLocation" value="commodity/commodityDetail.do?commodityId=<s:property value='commodityId'/>"/>
	<s:select name="commodityId" label="%{getText('text.commodityPicture.commodityId')}" list="#commodity.allCommodity" listKey="id" listValue="name" disabled="true"/>
	<s:textfield name="detailCode" label="%{getText('text.commodityDetail.detailCode')}" size="60"></s:textfield>
	<s:textfield name="remark" label="%{getText('text.commodityDetail.remark')}" size="60"></s:textfield>
	<s:textarea cssClass="ckeditor" labelposition="top" name="introduction" label="%{getText('text.commodityDetail.introduction')}" cols="30" rows="40"></s:textarea>	
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit type="button" theme="simple" cssClass="button" id="save" name="action"  value="%{getText('text.submit.save')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SPECIAL_SAVE}');" align="left"></s:submit>
			<s:submit type="button" theme="simple" cssClass="button" id="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" name="action" value="%{@com.runtech.web.util.Constant@ACTION_CANCEL}" align="left" ></s:submit>
		</s:param>
	</s:component>
</s:form>