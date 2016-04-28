<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:set name="pager" value="pager" scope="request"/>
<s:bean id="area" name="com.runtech.onlineshop.form.AreaForm"></s:bean>
<s:set name="searchResult" value="searchModel.getSearchResult(specialPager,context)"/>

<div class="content-box"><!-- Start Content Box -->
				<div class="content-box-header">
					
					<h3 style="cursor: s-resize; "><s:property value="%{currentMenu.title}"/></h3>
					
					<ul class="content-box-tabs">
						<li><a id="<s:property value="pageCode"/>_table_tab_link" href="#<s:property value="pageCode"/>_table_tab">Table</a></li>
						<li><a id="<s:property value="pageCode"/>_form_tab_link" href="#<s:property value="pageCode"/>_form_tab">Form</a></li>
					</ul>
					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					
					<div class="tab-content" id="<s:property value="pageCode"/>_table_tab">

<s:form>
	<div class="input_container">
			<s:textfield labelposition="left" name="searchModel.name" label="%{getText('text.commodityProvider.name')}"/>
			<s:textfield labelposition="left" name="searchModel.supplyScope" label="%{getText('text.commodityProvider.supplyScope')}"/>
			<s:submit cssClass="button" value="%{getText('text.submit.search')}"/>
	</div>
	<div class="table_container">
	<runtech:page pager="${specialPager}" command="paging"></runtech:page>
	<tt:table value="searchResult" sortName="col1" clientSort="true" name="modelElement">
		<tt:column title="%{getText('text.list.check')}">
			<input type="checkbox" name="formId" value="<s:property value='formId'/>"/>
		</tt:column>
		<tt:column title="%{getText('text.commodityProvider.id')}">
			<s:property value="id" />
		</tt:column>
		<tt:column title="%{getText('text.commodityProvider.name')}">
			<a href="?formId=<s:property value='formId'/>">
				<s:property value="name" />
			</a>
		</tt:column>
		<tt:column title="%{getText('text.commodityProvider.supplyScope')}">
			<s:property value="supplyScope" />
		</tt:column>
		<tt:column title="%{getText('text.commodityProvider.phone')}">
			<s:property value="phone" />
		</tt:column>
		<tt:column title="%{getText('text.commodityProvider.linkman')}">
			<s:property value="linkman" />
		</tt:column>
		<tt:column title="%{getText('text.commodityProvider.linkmanPhone')}">
			<s:property value="linkmanPhone" />
		</tt:column>
		<tt:column title="%{getText('text.commodityProvider.linkmanMobile')}">
			<s:property value="linkmanMobile" />
		</tt:column>
	</tt:table>
	</div>
	<div class="button_container">
	<s:submit theme="simple" cssClass="button" id="delete" value="%{getText('text.submit.delete')}" name="action" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_SPECIAL_DELETE}');" align="left"></s:submit>
	<input type="button" class="button" onclick="switchTab('<s:property value="pageCode"/>','form_tab');" value=<s:property value="%{getText('text.submit.add')}"/>>
	</div>
</s:form>

					</div>
					<div class="tab-content" id="<s:property value="pageCode"/>_form_tab">
<s:form enctype="multipart/form-data">
	<div class="input_container">
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:textfield name="name" required="true" label="%{getText('text.commodityProvider.name')}" ></s:textfield>
	<s:textfield name="phone" required="true" label="%{getText('text.commodityProvider.phone')}" ></s:textfield>
	<s:textfield name="linkman" label="%{getText('text.commodityProvider.linkman')}" ></s:textfield>
	<s:textfield name="linkmanPhone" label="%{getText('text.commodityProvider.linkmanPhone')}" ></s:textfield>
	<s:textfield name="linkmanMobile" label="%{getText('text.commodityProvider.linkmanMobile')}" ></s:textfield>
	<s:textfield name="address" label="%{getText('text.commodityProvider.address')}" ></s:textfield>
	<s:textfield name="webSite" label="%{getText('text.commodityProvider.webSite')}" ></s:textfield>
	<s:textfield name="qq" label="%{getText('text.commodityProvider.qq')}" ></s:textfield>
	<s:textfield name="email" label="%{getText('text.commodityProvider.email')}" ></s:textfield>
	<s:file name="contract" label="%{getText('text.commodityProvider.contractFile')}" cssClass="text-input"></s:file>
	<s:if test="contractFile!=null"><a class="download align-right" target="_blank" href="<%=request.getContextPath()%>/protect/ajax/<s:property value='pageCode'/>.do?actionType=<s:property value="%{@com.runtech.web.util.Constant@ACTION_DOWNLOAD}"/>&formId=<s:property value="formId"/>">&nbsp;</a></s:if>
	<s:textarea name="supplyScope" required="true" label="%{getText('text.commodityProvider.supplyScope')}"  cols="30" rows="5"></s:textarea>
	<s:textarea name="remark" label="%{getText('text.commodityProvider.remark')}"  cols="30" rows="5"></s:textarea>
	</div>
	<div class="input_container">
	<s:textarea cssClass="wysiwyg" name="description" label="%{getText('text.commodityProvider.description')}" ></s:textarea>
	</div>
	<div class="input_container">
	<s:textarea cssClass="wysiwyg" name="contractContent" label="%{getText('text.commodityProvider.contractContent')}" ></s:textarea>
	</div>
	<div class="button_container">
			<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.save')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_ALL}')" align="left"></s:submit>
		<s:submit theme="simple" cssClass="button" name="action" value="%{getText('text.button.cancel')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_CANCEL}')" align="left" ></s:submit>
	</div>
</s:form>

					</div>
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
