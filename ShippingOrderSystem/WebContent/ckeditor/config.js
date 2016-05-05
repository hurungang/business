/*
Copyright (c) 2003-2009, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';   
	config.width = '812px'
	config.fullPage = false;
	config.filebrowserBrowseUrl = '/admin/ckeditor/editor/filemanager/browser/default/browser.html';
    config.filebrowserImageBrowseUrl = '/admin/ckeditor/editor/filemanager/browser/default/browser.html?Type=Image';
    config.filebrowserFlashBrowseUrl = '/admin/ckeditor/editor/filemanager/browser/default/browser.html?Type=Flash';
    config.filebrowserUploadUrl = '/admin/ckeditor/editor/filemanager/connectors/?Command=QuickUpload&Type=Files';
    config.filebrowserImageUploadUrl = '/admin/ckeditor/editor/filemanager/connectors/?Command=QuickUpload&Type=Image&CurrentFolder=/';
    config.filebrowserFlashUploadUrl = '/admin/ckeditor/editor/filemanager/connectors/?Command=QuickUpload&Type=Flash';
    config.filebrowserConnectorUrl = '/admin/ckeditor/editor/filemanager/connectors/';
    config.font_names = '宋体/宋体;隶书/隶书;黑体/黑体;幼圆/幼圆;' + config.font_names; 
};
