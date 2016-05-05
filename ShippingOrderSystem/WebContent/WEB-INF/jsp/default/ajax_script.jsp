<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="runtech" uri="/runtech-tags" %>
<script>
	<runtech:set name="tabName" value="table_tab"/>
	<s:if test="formId!=null">
		<runtech:set name="tabName" value="form_tab"/>
	</s:if>		
			
			$('#facebox').find('.ajaxForm').ajaxForm({
		        delegation: true,
		        target: '#facebox_content'
		    });

			// Content box tabs:

			$('#facebox').find('.content-box .content-box-content div.tab-content').hide(); // Hide the content divs
			$('#facebox').find('ul.content-box-tabs li a.default-tab').addClass('current'); // Add the class "current" to the default tab
			$('#facebox').find('.content-box-content div.default-tab').show(); // Show the div with class "default-tab"

			$('#facebox').find('.content-box ul.content-box-tabs li a').click( // When a tab is clicked...
				function() { 
					$(this).parent().siblings().find("a").removeClass('current'); // Remove "current" class from all tabs
					$(this).addClass('current'); // Add class "current" to clicked tab
					var currentTab = $(this).attr('href'); // Set variable "currentTab" to the value of href of clicked tab
					$(currentTab).siblings().hide(); // Hide all content divs
					$(currentTab).show(); // Show the content div with the id equal to the id of clicked tab
					return false; 
				}
			);
			
		    // Alternating table rows:
			
			$('#facebox').find('tbody tr:even').addClass("alt-row"); // Add class "alt-row" to even table rows
	    	// Check all checkboxes when the one in a table head is checked:
			
			$('#facebox').find('.check-all').click(
				function(){
					$(this).parent().parent().parent().parent().find("input[type='checkbox']").attr('checked', $(this).is(':checked'));   
				}
			);
			
		    // Initialise Facebox Modal window:
			
			$('a[rel*=modal]').facebox(); // Applies modal window to any link with attribute rel="modal"
			$('.pager a').facebox(); // Applies modal window to any link with attribute rel="modal"

		    $(".datepicker").datepicker();
			$(".wysiwyg").wysiwyg(); // Applies WYSIWYG editor to any textarea with the class "wysiwyg"
			

			switchTab('<s:property value="pageCode"/>','<s:property value="tabName"/>');
			
			$(function() {
				$('input.uploadify').each(
						function(){
							var obj = $(this);
							var form = $(this).closest("form");
							var pathObj = form.find("input[name='"+$(this).attr("accesskey")+"']");
							$(this).uploadify({
								'formData'     : {
									'actionType' : 'upload',
								},
								'removeCompleted' : false,
								'buttonClass': 'button',
								'height':20,
								'buttonText': '<s:property value='%{getText("text.button.upload")}'/>',
								'fileObjName': 'fileObj',
								'multi': false,
								'swf'      : '<%=request.getContextPath()%>/js/uploadify.swf',
								'uploader' : '<%=request.getContextPath()%>/upload.do',
								'onUploadSuccess' : function(file, data, response) {
									var result = jQuery.parseJSON(data);
									if(result.error!=""){
							            alert(result.error);
									}else{
										if(pathObj.attr("fileId")){
											obj.uploadify('cancel',pathObj.attr("fileId"));
										}
										pathObj.val(result.filePath);
										pathObj.attr("fileId",file.id);
									}
						        }
							});
						});
			});

			$(function() {
				$('input[type="checkbox"].showSwitch').each(
					function(){
						var obj = $(this);
						var container = $("#"+obj.attr("container"));
						$(this).on("click",function(){
											if($(this).is(":checked")){
												container.show();
											}else{
												container.hide();
											}
										}
						);
					}
				);
			});
</script>