<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://jakarta.apache.org/taglibs/display" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<%@ taglib prefix="tt" uri="/tabletags" %>
<s:actionerror/>

<s:set name="pictureListPager" value="getSpecialPager(pictureListCount)"/>
<h3><s:property value="title" /></h3>
<s:form name="ajaxListForm" id="ajaxListForm" cssClass="ajaxForm" action="ajax/ajaxConsignmentPicture" >
	<table class="listTable">
		<thead>
			<tr><th><s:property value="%{getText('text.list.check')}"/></th>
			<th><s:property value="%{getText('text.picture.id')}"/></th>
			<th><s:property value="%{getText('text.picture.image')}"/></th>
			<th><s:property value="%{getText('text.picture.name')}"/></th>
			<th><s:property value="%{getText('text.picture.description')}"/></th>
			<th><s:property value="%{getText('text.picture.status')}"/></th></tr>
		</thead>
		<tbody>
		<s:iterator value="consignmentPictures">
			<tr><td><input type="checkbox" name="pictureId" value="<s:property value='id'/>"/></td>
				<td><s:property value='id'/></td>
				<td>			
					<a href="?formId=<s:property value='formId'/>">
					<img src="<s:property value="picturePath" />" height="50"/>
					</a>
				</td>
				<td><s:property value='pictureName'/></td>
				<td><s:property value='pictureDescription'/></td>
				<td><s:property value='status'/></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	<s:submit theme="simple" type="button" cssClass="button" id="btnDelete" name="actionButton" value="%{getText('text.submit.delete')}" align="left" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_DELETE_PICTURE}');"></s:submit>
	</s:form>
	<s:form name="ajaxUpdateForm" id="ajaxUpdateForm" cssClass="ajaxForm" enctype="multipart/form-data" action="ajax/ajaxConsignmentPicture" >
	<s:hidden name="formId"></s:hidden>
	<s:hidden name="pageCode"></s:hidden>
	<s:file name="uploadPicture.picture" label="%{getText('text.picture.path')}" size="60"></s:file>
	<s:textfield name="uploadPicture.pictureName" label="%{getText('text.picture.name')}" size="60"></s:textfield>
	<s:textfield name="uploadPicture.pictureDescription" label="%{getText('text.picture.description')}" size="60"></s:textfield>
	<s:component template="component.ftl">
		<s:param name="body">
			<s:submit type="button" theme="simple" cssClass="button" id="save" name="action"  value="%{getText('text.submit.save')}" onclick="setAction(this.form,'%{@com.runtech.web.util.Constant@ACTION_SAVE_PICTURE}');" align="left"></s:submit>
		</s:param>
	</s:component>
</s:form>

<script>
 $(document).ready(function() { 
        // bind and provide a simple callback function 
        $('#ajaxUpdateForm').ajaxForm(function(data) {
        	var result = $.parseJSON(data);
            if(result.success){
            	alert("Updated successfully!");
            	location.reload();
            }else{
            	alert("Update failed!"+result.errorMessage);
            }
        }); 
        // bind and provide a simple callback function 
        $('#ajaxListForm').ajaxForm(function(data) {
        	var result = $.parseJSON(data);
            if(result.success){
            	alert("Upda successfully!");
            	location.reload();
            }else{
            	alert("Updated failed!"+result.errorMessage);
            }
        }); 
    }); 
</script>