<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<style>
<!--
                
/************ Table ************/

table.listTable {
                width: 100%;
                border-collapse: collapse;
                }
                
table.listTable thead th {
                font-weight: bold;
                font-size: 15px;
                border-bottom: 1px solid #ddd;
                color: #404050;
                }
                #login-content table td,
table.listTable td,
table.listTable th,
#facebox table.listTable td,
#facebox table.listTable th {
                padding: 10px;
                line-height: 1.3em;
                }        
				
table.listTable tfoot td .bulk-actions {
                padding: 15px 0 5px 0;
                } 
				
table.listTable tfoot td .bulk-actions select {
                padding: 4px;
				border: 1px solid #ccc;
                }      
div.orderPage {
width:80%; margin:auto; border: #D0D0D0 solid 5px; page-break-after:always;
}
-->
</style>
			
	<s:iterator value="commodityOrders">
<div class="orderPage">
		<h1>亲爱的<s:property value="contact"/>,你好</h1>
		<p>希望您收到的这份礼物能够让你满意。有什么问题联系我。</p>
		<p>为了你方便查收快递，我把礼物的内容列出来了，你查看一下对不对。</p>
		<p>另外考虑到礼物的说明都是英文，我给您找了一些中文的使用说明和介绍，为了方便，你只需要用手机扫描下面的二维码就可以了，还可以看到我给您寄送的时候的照片哦。</p>
		<img src="<%=request.getContextPath()%>/protect/ajax/orderList.do?actionType=showQRCode&formId=<s:property value="id"/>">
	
	<div id="commodityOrderItemsContainer">
		<table class="listTable">
			<thead>
				<th><s:property value="%{getText('text.commodityOrderItem.commodity')}"/></th>
				<th><s:property value="%{getText('text.commodityOrderItem.commodityNumber')}"/></th>
				<th></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="commodityOrderItems">
				<tr><td>
					<a href="javascript:editOrderItem('<s:property value='id'/>','<s:property value='commodity.id'/>','<s:property value='commodityNumber'/>','<s:property value='price'/>','<s:property value='realPrice'/>','<s:property value='remark'/>')">
						<s:property value="commodity.name" />
					</a>
				</td><td>
					<s:property value="commodityNumber" />
				</td><td>
	<s:if test="status=='preparing'&&status=='pending'">
					<input class="button" type="button" onclick="deleteOrderItem(this);" value='<s:property value="%{getText('text.sumbit.delete')}"/>'/>
	</s:if>
				</td></tr>
				</s:iterator>
			</table>
	</div>
</div>
	</s:iterator>