<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<script>
	<runtech:set name="tab_name" value="table_tab"/>
	<s:if test="formId!=null">
		<runtech:set name="tab_name" value="form_tab"/>
	</s:if>
			$('#<s:property value="pageCode"/>_<s:property value="tab_name"/>_link').addClass('default-tab');
			$('#<s:property value="pageCode"/>_<s:property value="tab_name"/>').addClass('default-tab');
</script>