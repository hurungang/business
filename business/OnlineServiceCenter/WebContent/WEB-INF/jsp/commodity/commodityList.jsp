<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>

<s:set name="pager" value="pager" scope="request"/>
<s:bean id="area" name="com.runtech.onlineshop.form.AreaForm"></s:bean>
<s:bean id="commodityCategory" name="com.runtech.onlineshop.form.CommodityCategoryForm"></s:bean>
<s:bean id="commodityProvider" name="com.runtech.onlineshop.form.CommodityProviderForm"></s:bean>
<s:bean id="commodity" name="com.runtech.onlineshop.form.CommodityForm"></s:bean>
<s:set name="commodityList" value="searchModel.getSearchResult(specialPager)"/>

<div class="content-box"><!-- Start Content Box -->
				
				<div class="content-box-header">
					
					<h3 style="cursor: s-resize; ">Content box</h3>
					
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab current">Table</a></li> <!-- href must be unique and match the id of target div -->
					</ul>
					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					
					<div class="tab-content default-tab" id="tab1" style="display: block; "> <!-- This is the target div. id must match the href of this div's tab -->
						
						<s:actionerror/>
						<s:actionmessage/>
						
<s:form>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:textfield theme="simple" cssClass="text-input" name="searchModel.name" label="%{getText('text.commodity.name')}" size="60"/><s:submit theme="simple" cssClass="button" value="%{getText('text.submit.search')}"/>
		</s:param>
	</s:component>
	<runtech:page pager="${specialPager}" command="paging"></runtech:page>
	<s:iterator value="commodityList">
		<div class="gmg left">
			<div class="gi"><img src="<s:property value="picturePath"/>">
			</div>
			<b class="gn"><s:property value="name" /></b>

			<div class="gp1">
				<span class="shop_price"> <b>¥<s:property value="price" />元</b></span> <span class="buy"><a href="<%=request.getContextPath()%>/protect/ajax/cart.do?commodityId=<s:property value="id"/>&actionType=<s:property value="%{@com.runtech.web.util.Constant@ACTION_ADD_CHILD}"/>" rel="modal" class="button"><s:property value="%{getText('text.button.order')}"/></a></span>
				<div class="clear"></div>
			</div>
		</div>
	</s:iterator>
		<div class="clear"></div>
</s:form>

					</div> <!-- End #tab1 -->
			 
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->