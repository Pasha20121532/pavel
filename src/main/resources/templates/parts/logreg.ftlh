<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Имя пользователя: </label>
            <div class="col-sm-4">
                <input type="text" name="username" class="form-control" placeholder="Имя прользователя" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Пароль: </label>
            <div class="col-sm-4">
                <input type="password" name="password" class="form-control" placeholder="Пароль" />
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Электронная почта </label>
                <div class="col-sm-4">
                    <input type="email" name="email" class="form-control" placeholder="abc@abc.com" />
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if !isRegisterForm>
            <a href="/registration">
                Добавить нового пользователя
            </a>
        </#if>
        <button class="bnt btn-primary" type="submit">
            <#if isRegisterForm>Создать<#else>Авторизироваться</#if>
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

<!--
< #macro login path>
    <form action="$ {path}" method="post">
        <div><label> Имя пользователя: <input type="text" name="username"/> </label></div>
        <div><label> Пароль: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="$ {_csrf.token}">
        <div><input type="submit" value="Принять"/></div>
    </form>
< /#macro>

-->

