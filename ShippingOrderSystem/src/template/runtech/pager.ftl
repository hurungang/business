<#assign nextPageUrl = parameters.url + parameters.pagerCommand + '=next' />
<#assign previousPageUrl = parameters.url + parameters.pagerCommand + '=previous' />
<span<#rt/>
<#if parameters.cssClass?exists>
 class="${parameters.cssClass?html}"<#rt/>
<#else>
 class="pagerText"
</#if>
<#if parameters.cssStyle?exists>
 style="${parameters.cssStyle?html}"<#rt/>
</#if>
><#rt/>
<#if parameters.hasPreviousPage>
<a href="${previousPageUrl}">&lt;</a>&nbsp;&nbsp;<#rt/>
<#else>
&lt;&nbsp;&nbsp;
</#if>
Page ${parameters.pageIndex} of ${parameters.totalPages}<#rt/>
<#if parameters.hasNextPage>
&nbsp;&nbsp;<a href="${nextPageUrl}">&gt;</a><#rt/>
<#else>
&nbsp;&nbsp;&gt;
</#if>
</span>