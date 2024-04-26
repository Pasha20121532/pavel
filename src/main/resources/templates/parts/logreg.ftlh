<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Имя пользователя:  </label>
            <div class="col-sm-4">
                <input type="text" name="username" class="form-control ${(username??)?string('is-invalid', '')}"
                       value="<#if user??>${user.getUsername()}</#if>" placeholder="Имя пользователя"/>
                <#if username??>
                    <div class="invalid-feedback">
                        ${username}
                    </div>
                </#if>

            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Пароль:  </label>
            <div class="col-sm-4">
                <input type="password" name="password" class="form-control ${(password??)?string('is-invalid', '')}"
                       placeholder="Пароль"/>
                <#if password??>
                    <div class="invalid-feedback">
                        ${password}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Подтверждение пароля:  </label>
                <div class="col-sm-4">
                    <input type="password" name="password2" class="form-control ${(password2??)?string('is-invalid', '')}"
                           placeholder="Пароль"/>
                    <#if password2??>
                        <div class="invalid-feedback">
                            ${password2}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Электронная почта: </label>
                <div class="col-sm-4">
                    <input type="email" name="mail" class="form-control ${(mail??)?string('is-invalid', '')}"
                           value="<#if user??>${user.getMail()}</#if>" placeholder="abc@def.com" />
                    <#if mail??>
                        <div class="invalid-feedback">
                            ${mail}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <#if !isRegisterForm>
            <a href="/registration">
                Добавить нового пользователя
            </a>
        </#if>
        <button class="btn btn-primary" type="submit">
            <#if isRegisterForm>Создать<#else>Авторизоваться </#if>
        </button>
    </form>
</#macro>
<#macro logout>
    <div>
        <form action="/logout" method="post">
            <button class="btn btn-primary" type="submit">
                Покинуть
            </button>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>
    </div>
</#macro>
