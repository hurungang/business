<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>

<s:form name="ajaxForm" cssClass="ajaxForm" action="ajax/cart" >
<table class="listTable" width="100%" cellpadding="4" cellspacing="1">
	<thead>
		<tr>
			<th width="5%"></th>
			<th width="53%"><s:property value="%{getText('text.commodity.name')}"/></th>
			<th width="7%">单价</th>
			<th width="7%">数量</th>
			<th width="7%">总价</th>
			<th width="7%">折扣</th>
			<th width="7%">实付</th>
			<th width="7%">积分</th>
		</tr>
	</thead>
	<tbody>
		<s:if test="context.user.cart.items.isEmpty()">
			<tr><td colspan="8">购物车当前是空的</td></tr>
		</s:if>
		<s:else>
		<s:iterator id="item" value="context.user.cart.items">
		<tr>
			<td ><input type="checkbox" name="commodityId"
				value="<s:property value="commodity.id"/>" />
				<input type="hidden" id="price_<s:property value="commodity.id"/>" name="price_<s:property value="commodity.id"/>" value="<s:property value="commodity.price"/>"/>
				<input type="hidden" id="discount_<s:property value="commodity.id"/>" name="discount_<s:property value="commodity.id"/>" value="<s:property value="discount"/>"/>
				<input type="hidden" id="point_<s:property value="commodity.id"/>"  name="point_<s:property value="commodity.id"/>" value="<s:property value="point"/>"/></td>
			<td>
			<ul class="orderItem">
				<li><a href="$!{context.servletPath}/generalV2/commodityDetailV2.do?id=<s:property value="commodity.id"/>"><img src="<s:property value="commodity.picturePath"/>"
					width="30" /></a></li>
				<li>
					<dl>
						<dt><a href="$!{context.servletPath}/generalV2/commodityDetailV2.do?id=<s:property value="commodity.id"/>"><s:property value="commodity.name"/></a></dt>
					</dl>
				</li>
			</ul>
			</td>
			<td class="price"><s:property value="commodity.price"/></td>
			<td><input type="text" size="1" name="number_<s:property value="commodity.id"/>" id="number_<s:property value="commodity.id"/>" value="<s:property value="commodityNumber"/>" onChange="calcCart();"/></td>
			<td class="price"><span id="priceSpan_<s:property value="commodity.id"/>"><s:property value="price"/></span></td>
			<td><s:property value="discountPercent"/></td>
			<td class="realPrice"><span id="realPriceSpan_<s:property value="commodity.id"/>"><s:property value="realPrice"/></span></td>
			<td class="point"><span id="pointSpan_<s:property value="commodity.id"/>"><s:property value="point"/></td>
		</tr>
		</s:iterator>
		<tr class="bottom">
			<td colspan="3">
			<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.save')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_ALL}')" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.delete')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE_CHILD}')" align="left"></s:submit>
			<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.confirmOrder')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_CONFIRM_ORDER}')" align="left"></s:submit>
			<td>总计</td>
			<td><span id="spanTotalPrice"><s:property value="context.user.cart.totalPrice"/></span></td>
			<td>&nbsp;</td>
			<td><span id="spanTotalRealPrice"><s:property value="context.user.cart.totalRealPrice"/></span></td>
			<td><span id="spanTotalPoint"><s:property value="context.user.cart.totalPoint"/></span></td>
		</tr>
		</s:else>
	</tbody>
</table>
</s:form>