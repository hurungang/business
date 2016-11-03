<%@ page contentType="text/html; charset=UTF-8" %><%@ taglib uri="/runtech-tags" prefix="runtech" %><%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript"> 

var result = {
"error":"<s:property value="error"/>",
"filePathArray":<s:property value="fullPath" escape="false"/>
}
if(result.error!="")
{
	window.parent.CKEDITOR.tools.callFunction(<s:property value="%{#parameters.CKEditorFuncNum}"/>,'',result.error); 
}else{
	window.parent.CKEDITOR.tools.callFunction(<s:property value="%{#parameters.CKEditorFuncNum}"/>,result.filePathArray[0],'');
}
</script> 
