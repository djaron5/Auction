<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${username}</h5>
    <#if errorMessage??>
        <div class="alert alert-danger" role="alert">
            ${errorMessage}
        </div>
        <#else>
        <#if successMessage??>
            <div class="alert alert-success" role="alert">
                ${successMessage}
            </div>
        </#if>
    </#if>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="false" aria-controls="collapseExample">
        Want to change password?
    </a>
    <div class="collapse <#if passwordError??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form action="/user/profile/changePassword" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"
                           name="oldPassword" placeholder="Enter old password"/>
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control ${(newPasswordError??)?string('is-invalid', '')}"
                           name="password2" placeholder="Enter new password"/>
                    <#if newPasswordError??>
                        <div class="invalid-feedback">
                            ${newPasswordError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control ${(newPasswordError??)?string('is-invalid', '')}"
                           name="password3" placeholder="Repeat new password"/>
                    <#if newPasswordError??>
                        <div class="invalid-feedback">
                            ${newPasswordError}
                        </div>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Change password</button>
                </div>
            </form>
        </div>
    </div>
</@c.page>