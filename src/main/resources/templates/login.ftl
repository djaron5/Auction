<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as login>

<@c.page>
    <h2>Login page</h2>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            User not found
        </div>
    </#if>
    <@login.login "/login" false/>
</@c.page>