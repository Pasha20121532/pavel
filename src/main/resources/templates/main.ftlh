<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="row">
        <div class="col-sm-6">
            <div class="card">
                <div class="card-body">


                    <div class="form-row">
                        <div class="form-group ">
                            <form method="post" action="filter" class="form-inline">
                                <input type="text" name="filter" class="form-control" value="${filter?if_exists}" placeholder="Поиск по группе">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button class="btn btn-primary ml-2" type="submit">Найти</button>
                            </form>

                        </div>
                    </div>
                    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                        Добавить нового студента
                    </a>
                    <div class="collapse" id="collapseExample">
                        <div class="form-group mt-3">
                            <form method="post" enctype="multipart/form-data">
                                <div class="form-group ">
                                    <input type="text" class="form-control" name="text" placeholder="Введите ФИО"/>
                                </div>
                                <div class="form-group ">
                                    <input type="text" class="form-control" name="groupp" placeholder="Введите номер группы">
                                </div>
                                <div class="form-group ">
                                    <div class="custom-file">
                                        <input type="file" name="file" id="customfile">
                                    </div>
                                </div>

                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <div class="form-group">
                                    <button class="btn btn-primary" type="submit">Добавить</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <div class="col-sm-6">
            <div class="card">
                <div class="card-body">


                    <div class="card my-3" style="width: 18rem;"><h3>Список студентов</h3></div>
                    <div class="card-columns">
                        <#list students as student>
                            <div class="card" >

                                <b>${student.getGroup()}</b>
                                <span>${student.getName()}</span>
                                <strong>${student.getStudentuser()}</strong>
                                <#if student.getAvatarname()??>

                                    <img src="/img/${student.getAvatarname()}"  >
                                </#if>

                            </div>
                        <#else >
                            Список пуст
                        </#list>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>
