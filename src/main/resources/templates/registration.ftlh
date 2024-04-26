<#import "parts/common.ftlh" as c>
<#import "parts/logreg.ftlh" as l>

<@c.page>
    <div class="mb-3">
        Добавить нового пользователя
    </div>
    <#if message??>${message}</#if>
    <@l.login "/registration " true/>
</@c.page>