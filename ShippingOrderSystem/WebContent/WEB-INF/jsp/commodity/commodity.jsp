<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
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
	<table class="listTable">
		<thead>
			<tr><th><s:property value="%{getText('text.list.check')}"/></th>
			<th><s:property value="%{getText('text.commodity.id')}"/></th>
			<th><s:property value="%{getText('text.commodity.name')}"/></th>
			<th><s:property value="%{getText('text.commodity.category.name')}"/></th>
			<th><s:property value="%{getText('text.commodity.standard')}"/></th>
			<th><s:property value="%{getText('text.commodity.status')}"/></th></tr>
		</thead>
		<tbody>
		<s:iterator value="searchResult">
			<tr><td><input type="checkbox" name="formId" value="<s:property value='id'/>"/></td>
				<td><s:property value='id'/></td>
				<td>			
					<a href="?formId=<s:property value='formId'/>">
					<s:property value="name" />
					</a>
				</td>
				<td><s:property value='commodityCategory.description'/></td>
				<td><s:property value='standard'/></td>
				<td><s:property value='status'/></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>

	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit type="button" theme="simple" cssClass="button" id="delete" name="action"  value="%{getText('text.submit.specialDelete')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SPECIAL_DELETE}');" align="left"></s:submit>
			<s:submit type="button" theme="simple" cssClass="button" id="duplicate" name="action"  value="%{getText('text.submit.duplicate')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DUPLICATE}');" align="left"></s:submit>
			<s:submit type="button" theme="simple" cssClass="button" id="duplicateAll" name="action"  value="%{getText('text.submit.duplicateAll')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_DUPLICATE_ALL}');" align="left"></s:submit>
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
	<s:select name="categoryId" label="%{getText('text.commodity.categoryId')}" list='#commodityCategory.getAllCommodityCategory(@com.runtech.web.util.Constant@CODE_OVERSEA_COMMODITY_CATEGORY_ROOT," - ")' listKey="id" listValue="description"/>
	<s:select name="providerId" label="%{getText('text.commodity.providerId')}" list="#commodityProvider.allCommodityProviders" listKey="id" listValue="name"/>	
	
	<s:textfield name="summary" label="%{getText('text.commodity.summary')}" size="60"></s:textfield>
	<s:textfield name="standard" label="%{getText('text.commodity.standard')}" size="60"></s:textfield>
	<s:textfield name="priority" label="%{getText('text.commodity.priority')}" size="60"></s:textfield>
	<s:if test="picturePath!=null">
	<s:component template="component.ftl">
		<s:param name="body">
			<img src="<s:property value="picturePath"/>" height="80"/>
		</s:param>
	</s:component>
	</s:if>
	<s:file name="picture" label="%{getText('text.commodity.picture')}" size="60"></s:file>
	
	
	<s:textfield name="originalPrice" label="%{getText('text.commodity.originalPrice')}" size="60"></s:textfield>
	<s:textfield name="price" label="%{getText('text.commodity.price')}" size="60"></s:textfield>

	<s:component template="component.ftl">
		<s:param name="body">	
		<div class="inbox-tabs-container">
		<div class="inbox-tabs-header">
			<ul class="inbox content-box-tabs">
				<li><a class="current" id="<s:property value="pageCode"/>_table_tab_link " href="#<s:property value="pageCode"/>_description_tab"><s:property value="%{getText('text.content.description')}"/></a></li>
				<li><a id="<s:property value="pageCode"/>_table_tab_link" href="#<s:property value="pageCode"/>_specification_tab"><s:property value="%{getText('text.content.specification')}"/></a></li>
				<li><a id="<s:property value="pageCode"/>_table_tab_link" href="#<s:property value="pageCode"/>_instruction_tab"><s:property value="%{getText('text.content.instruction')}"/></a></li>
				<div class="clear"></div>
			</ul>
		</div>
		<div class="inbox-tabs-header">
			<div class="tab-content default-tab" id="<s:property value="pageCode"/>_description_tab">
				<textarea class="ckeditor" id="description" name="description" cols="100" rows="40"><s:property value="description"/></textarea>
			</div>
			<div class="tab-content" id="<s:property value="pageCode"/>_specification_tab">
				<textarea class="ckeditor" id="description" name="specification" cols="100" rows="40"><s:property value="specification"/></textarea>
			</div>
			<div class="tab-content" id="<s:property value="pageCode"/>_instruction_tab">
				<textarea class="ckeditor" id="description" name="instruction" cols="100" rows="40"><s:property value="instruction"/></textarea>
			</div>
		</div>
		</div>
		</s:param>
	</s:component>
		
	<s:component template="component.ftl">
		<s:param name="body">	
			<div class="pictureContainer">
			<s:iterator value="commodityPictures">
				<span><img src="<s:property value="path"/>" height="50"/><b><s:property value="description"/></b></span>
			</s:iterator>
			</div>	
			<s:if test="%{formId!=null}">
				<a class="button" rel="modal" href="<%=request.getContextPath()%>/protect/ajax/ajaxCommodityPicture.do?formId=<s:property value="id"/>"><s:property value="%{getText('text.button.pictureManagement')}"/></a>
			</s:if>
		</s:param>
	</s:component>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.save')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_ALL}');"></s:submit>
			<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.cancel')}" align="left" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}');"></s:submit>

			<s:if test="%{formId!=null}">
				<s:submit type="button" theme="simple" cssClass="button" id="withdraw" name="action"  value="%{getText('text.submit.withdraw')}" onclick="return submitForm(this.form,'%{@com.runtech.web.util.Constant@ACTION_WITHDRAW}');" align="left"></s:submit>
			</s:if>
		</s:param>
	</s:component>
</s:form>

	    <script type="text/JavaScript"> 
	    var editor = CKEDITOR.replace('.ckeditor'); 
	    </script>
					</div> <!-- End #tab2 -->        
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->