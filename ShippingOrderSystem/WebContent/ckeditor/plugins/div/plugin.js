﻿/*
Copyright (c) 2003-2009, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

(function(){CKEDITOR.plugins.add('div',{requires:['editingblock','domiterator'],init:function(a){var b=a.lang.div;a.addCommand('creatediv',new CKEDITOR.dialogCommand('creatediv'));a.addCommand('editdiv',new CKEDITOR.dialogCommand('editdiv'));a.addCommand('removediv',{exec:function(c){var d=c.getSelection(),e=d&&d.getRanges(),f,g=d.createBookmarks(),h,i=[];function j(l){var m=new CKEDITOR.dom.elementPath(l),n=m.blockLimit,o=n.is('div')&&n;if(o&&!o.getAttribute('_cke_div_added')){i.push(o);o.setAttribute('_cke_div_added');}};for(var k=0;k<e.length;k++){f=e[k];if(f.collapsed)j(d.getStartElement());else{h=new CKEDITOR.dom.walker(f);h.evaluator=j;h.lastForward();}}for(var k=0;k<i.length;k++)i[k].remove(true);d.selectBookmarks(g);}});a.ui.addButton('CreateDiv',{label:b.toolbar,command:'creatediv'});if(a.addMenuItems){a.addMenuItems({editdiv:{label:b.edit,command:'editdiv',group:'div',order:1},removediv:{label:b.remove,command:'removediv',group:'div',order:5}});if(a.contextMenu)a.contextMenu.addListener(function(c,d){if(!c)return null;var e=new CKEDITOR.dom.elementPath(c),f=e.blockLimit;if(f&&f.getAscendant('div',true))return{editdiv:CKEDITOR.TRISTATE_OFF,removediv:CKEDITOR.TRISTATE_OFF};return null;});}CKEDITOR.dialog.add('creatediv',this.path+'dialogs/div.js');CKEDITOR.dialog.add('editdiv',this.path+'dialogs/div.js');}});})();
