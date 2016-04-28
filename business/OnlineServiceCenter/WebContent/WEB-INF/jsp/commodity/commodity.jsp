<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>

<s:set name="pager" value="pager" scope="request"/>
<s:bean id="area" name="com.runtech.onlineshop.form.AreaForm"></s:bean>
<s:bean id="commodityCategory" name="com.runtech.onlineshop.form.CommodityCategoryForm"></s:bean>
<s:bean id="commodityProvider" name="com.runtech.onlineshop.form.CommodityProviderForm"></s:bean>
<s:set name="searchResult" value="searchModel.getSearchResult(specialPager)"/>

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
	<s:component template="component.ftl">
		<s:param name="body">
			<s:textfield theme="simple" cssClass="text-input" name="searchModel.name" label="%{getText('text.commodity.name')}" size="60"/><s:submit theme="simple" cssClass="button" value="%{getText('text.submit.search')}"/>
		</s:param>
	</s:component>
	<runtech:page pager="${specialPager}" command="paging"></runtech:page>
	<tt:table value="searchResult" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.commodity.id')}">
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.commodity.name')}">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodity.shortName')}">
			<s:property value="shortName" />
		</tt:column>
		<tt:column title="%{getText('text.commodity.categoryId')}">
			<s:property value="commodityCategory.commodityCategory.description" /> > <s:property value="commodityCategory.description" />
		</tt:column>
		<tt:column title="%{getText('text.commodity.standard')}">
			<s:property value="standard" />
		</tt:column>
		<tt:column title="%{getText('text.commodity.originalPrice')}">
			<s:property value="originalPrice" />
		</tt:column>
		<tt:column title="%{getText('text.commodity.price')}">
			<s:property value="price" />
		</tt:column>
		<tt:column title="%{getText('text.commodity.status')}">
			<s:property value="status" />
		</tt:column>
	</tt:table>

	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit type="button" theme="simple" cssClass="button" id="delete" name="action"  value="%{getText('text.submit.specialDelete')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SPECIAL_DELETE}');" align="left"></s:submit>
			<s:submit type="button" theme="simple" cssClass="button" id="duplicate" name="action"  value="%{getText('text.submit.duplicate')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DUPLICATE}');" align="left"></s:submit>
			<s:submit type="button" theme="simple" cssClass="button" id="duplicateAll" name="action"  value="%{getText('text.submit.duplicateAll')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DUPLICATE_ALL}');" align="left"></s:submit>
			<s:submit type="button" theme="simple" cssClass="button" id="duplicateAll" name="action"  value="%{getText('text.submit.refreshPreviewPicture')}" onclick="return submitForm(this.form,'refreshPreviewPicture');" align="left"></s:submit>
		</s:param>
	</s:component>
</s:form>

					</div>
					<div class="tab-content" id="<s:property value="pageCode"/>_form_tab">
					
<s:form enctype="multipart/form-data">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="name" label="%{getText('text.commodity.name')}" size="60"></s:textfield>
	<s:textfield name="shortName" label="%{getText('text.commodity.shortName')}" size="60"></s:textfield>

	<s:select name="areaId" label="%{getText('text.commodity.areaId')}" list="#area.allCommodityAreas" listKey="id" listValue="name"/>
	<s:select name="categoryId" label="%{getText('text.commodity.categoryId')}" list='#commodityCategory.getAllCommodityCategory(@com.runtech.web.util.Constant@CODE_COMMODITY_CATEGORY_ROOT," - ")' listKey="id" listValue="description"/>
	<s:select name="providerId" label="%{getText('text.commodity.providerId')}" list="#commodityProvider.allCommodityProvider" listKey="id" listValue="name"/>	
	

	<s:textfield name="summary" label="%{getText('text.commodity.summary')}" size="60"></s:textfield>
	<s:textfield name="standard" label="%{getText('text.commodity.standard')}" size="60"></s:textfield>
	<s:textfield name="priority" label="%{getText('text.commodity.priority')}" size="60"></s:textfield>
	<s:if test="picturePath!=null">
	<s:component template="component.ftl">
		<s:param name="body">
			<img src="<s:property value="picturePath"/>" width="200"/>
		</s:param>
	</s:component>
	</s:if>
	<s:file name="picture" label="%{getText('text.commodity.picture')}" size="60"></s:file>
	<s:select name="commendCode" label="%{getText('text.commodity.commendCode')}" emptyOption="true" list="@com.runtech.onlineshop.form.SystemCodeForm@getCodes('commendCode')" listKey="code" listValue="name"/>
	<s:textfield name="originalPrice" label="%{getText('text.commodity.originalPrice')}" size="60"></s:textfield>
	<s:textfield name="price" label="%{getText('text.commodity.price')}" size="60"></s:textfield>
	<s:textfield name="point" label="%{getText('text.commodity.point')}" size="60"></s:textfield>
	<s:select name="priceUnit" label="%{getText('text.commodity.priceUnit')}" list="%{@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_UNIT_TYPE)}" listKey="code" listValue="name"/>	
	<s:textfield name="priceQuantity" label="%{getText('text.commodity.priceQuantity')}" size="60"></s:textfield>
	<s:select name="unit" label="%{getText('text.commodity.unit')}" list="%{@com.runtech.onlineshop.form.SystemCodeForm@getCodes(@com.runtech.web.util.Constant@CODE_UNIT_TYPE)}" listKey="code" listValue="name"/>	
	<s:textfield name="quantity" label="%{getText('text.commodity.quantity')}" size="60"></s:textfield>
	<s:textfield name="minimumBuy" label="%{getText('text.commodity.minimumBuy')}" size="60"></s:textfield>
	<s:textarea name="keywords" label="%{getText('text.commodity.keywords')}" cols="48" rows="2"></s:textarea>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.save')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_ALL}');"></s:submit>
			<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.cancel')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}');"></s:submit>
			<s:if test="%{formId!=null}">
				<a class="button" href="commodityDetail.do?commodityId=<s:property value='id'/>"><s:property value="%{getText('text.button.detailManagement')}"/></a>
				<a class="button" href="commodityPicture.do?commodityId=<s:property value='id'/>"><s:property value="%{getText('text.button.pictureManagement')}"/></a>
				<s:submit type="button" theme="simple" cssClass="button" id="withdraw" name="action"  value="%{getText('text.submit.withdraw')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_WITHDRAW}');" align="left"></s:submit>
			</s:if>
		</s:param>
	</s:component>
</s:form>

					</div> <!-- End #tab2 -->        
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->