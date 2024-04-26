<#import "parts/common.ftlh" as c>
<#import "parts/logreg.ftlh" as l>

<@c.page>
    <div class="mb-3">
        Страница входа
    </div>
    <#if message??>${message}</#if>
    <@l.login "/login " false/>
</@c.page>



