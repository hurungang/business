<span<#rt/>
<#if parameters.cssClass?exists>
 class="${parameters.cssClass?html}"<#rt/>
</#if>
<#if parameters.cssStyle?exists>
 style="${parameters.cssStyle?html}"<#rt/>
</#if>
><#rt/>
<#if parameters.sortable>
<div style="float: left">
  <#if parameters.clientSort?exists>
    <a href="#">${parameters.title}</a><#rt/>
  <#else>
    <a href="${parameters.url}">${parameters.title}</a><#rt/>
  </#if>
</div>
<div
 <#if parameters.showIndicator>
   <#if parameters.sortOrder == "ascending">
     class="selectedUp"<#rt/>
   <#else>
	 class="selectedDown"<#rt/>
   </#if>
   <#else>
   <#--Used to add the image using css when using client sort -->
   class="indicator"
 </#if>
/>
<#else>
 ${parameters.title}<#rt/>
</#if>
</span>