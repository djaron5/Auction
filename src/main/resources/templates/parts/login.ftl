<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label for="inputUsername" class="col-sm-2 col-form-label">Login</label>
            <div class="col-sm-8">
                <input type="text" class="form-control ${(usernameError??)?string('is-invalid', '')}" id="inputUsername"
                       name="username" placeholder="Login" value="<#if user??>${user.username}</#if>">
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-8">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" id="inputPassword" placeholder="Password">
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-8">
                    <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid', '')}"
                           id="inputEmail" placeholder="some@some.com" value="<#if user??>${user.email}</#if>">
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">Sign in</button>
            </div>
        </div>
        <#if !isRegisterForm>
            <label>Don't have account yet?</label>
            <a href="/registration">Register</a>
        </#if>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <button class="btn btn-primary" type="submit">Log out</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>