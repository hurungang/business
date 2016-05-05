<#if parameters.clientSort?exists>
<script>
  dojo.require("dojo.widget.FilteringTable");
</script>
</#if>

<#if parameters.pagerValue?exists>
  <@tt.pager url="${parameters.url}" pagerCommand="${parameters.pagerCommand}" pagerValue="${parameters.pagerValue}" />
</#if>
<table class="listTable" cellspacing="1" cellpadding="0" 
<#if parameters.id?exists>
 id="${parameters.id?html}"<#rt/>
</#if>
<#if parameters.cssClass?exists>
 class="${parameters.cssClass?html}"<#rt/>
</#if>
<#if parameters.cssStyle?exists>
 style="${parameters.cssStyle?html}"<#rt/>
</#if>
<#if parameters.clientSort?exists>
  dojoType="dojo:filteringTable" headerUpClass="selectedDown" headerDownClass="selectedUp"<#rt/>
</#if>
>
<thead>
<tr>
<#list parameters.columns as column>
<th><#rt/>
  <@tt.sortableHeader url="${parameters.url}" name="${parameters.sortName?default('')}"
     sortOrder="${parameters.sortOrder?default('')}" sortKey="${column.sortKey?default('')}"
     sortable="${column.sortable?default('false')}"
     title="${column.title?default('')}"/><#rt/>
</th><#rt/>
</#list>
</tr><#rt/>
</thead>
<tbody>
