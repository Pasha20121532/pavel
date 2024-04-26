<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="mb-3">${username}</div>
    <#if message??>${message}</#if>

    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Пароль: </label>
            <div class="col-sm-4">
                <input type="password" name="password" class="form-control" placeholder="Пароль"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Электронная почта: </label>
            <div class="col-sm-4">
                <input type="email" name="email" class="form-control" placeholder="abc@acb.com" value="${email!''}"/>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary" type="submit">Сохранить</button>

    </form>
</@c.page>