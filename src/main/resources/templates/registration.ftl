<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as login>

<@c.page>
<h2>Registration page</h2>
${message!}
<@login.login "/registration" true/>
</@c.page>