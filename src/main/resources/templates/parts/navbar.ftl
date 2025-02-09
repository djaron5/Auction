<#include "security.ftl">
<#import "login.ftl" as login>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand ml-5" href="">Auction</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Auctions</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">User list</a>
                </li>
            </#if>
        </ul>
        <#if autentificated>
            <div class="navbar-text mr-3"><a href="/user/profile">${name}</a></div>
            <div class="navbar-text mr-3"><@login.logout/></div>
        <#else >
            <div class="navbar-text mr-3"><@login.login_button/></div>
        </#if>
    </div>
</nav>