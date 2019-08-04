<#assign
autentificated = Session.SPRING_SECURITY_CONTEXT??
>

<#if autentificated>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.isAdmin()
    />
<#else >
    <#assign
    name = "unknown"
    isAdmin = false
    />
</#if>