<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <title>Puzzle</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>




    <script type="text/javascript">
        function update() {
            if (localStorage.getItem('toUpdate') == 'false'){
                document.getElementById('fcheck').submit();
                localStorage.setItem('toUpdate', 'true');
            }
        }
        function getInfo(id) {
            location.href='info?id='+id;
        }

        function addFormulaOpen() {
            location.href='addFormula?update=0&formulaName=""&saveFile=0';
        }

    </script>
    <style>
        .button {
            width: 100%;}


    </style>
</head>
<body onload="update()">

<div class="container-fluid">
    <div class="row-fluid">

        <div class="col-lg-12" style = "border:3px #ffff00 solid; background: #FEFEF9; width: 100% ">
            <div class="btn-group text-left col-lg-12">

                <sec:authorize access="hasRole('ROLE_ADMIN')">

                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Меню<span class="caret"></span></button>

                    <ul class="dropdown-menu" role="menu">
                        <c:url value="/addFormula" var="addFormula" />
                        <li><a onclick="addFormulaOpen(); return false;">Редактирование/Добавление новой смеси</a></li>
                        <!--"${addFormula}"-->
                        <c:url value="/users" var="users" />
                        <li><a href="${users}">Пользователи</a></li>
                    </ul>
                </sec:authorize>
                <c:url value="/logout" var="logoutUrl" />
                <h5 class="text-right"/> <a href="about"> ? </a> <a href="${logoutUrl}">Выход</a>
            </div>



            <h3 class="text-center"/>Выбор смеси</p>

        </div>
    </div>

    <div class="row-fluid">

        <div class="col-lg-6" style = "border:3px #9932cc solid; background: #FBF3FE; height: 85%; ">
            <div style="height:90%; width:100%;overflow:auto; ">
                <table class="table table-striped table-hover" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th></th>
                        <th></th>
                        <th>Название смеси</th>
                        <th>Рекомендована как</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:set var="number" value="${0}"/>
                    <c:forEach items="${formula}" var="formula">
                        <c:set var="id" value="${formula.formula.id}"/>
                        <tr onClick="getInfo(${id})">
                            <c:set var= "number" value="${number + 1}"/>
                            <td>${number}</td>


                            <td><img src  = "pic/${formula.formula.pic}" class="img-rounded" alt="1" width="50" height="52">
                            <td>${formula.formula.name}</td>
                            <td>${formula.diagnosis.name}</td>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </div>


        </div>
        <div class="col-lg-6" style = "border:3px #00fa9a solid; height: 85%; background:#EEFEF1">
            <div style="height:80%; width:100%;">
                <h4 class="text-left"/> <b>Вид смеси</b>

                <form action="formula" id ="fcheck" method="post">
                    <c:forEach items="${diagnosis}" var="diagnosis">
                        <p>
                        <div class="checkbox" style = "left:15px">
                            <h4 label class="text-left" >

                                <input left= "30px" class="item" type="checkbox" name = "checkDiagnosis[]"
                                       value="${diagnosis.id}" id = "check_${diagnosis.id}" onchange = "this.form.submit()"/>
                                    ${diagnosis.name}
                            </h4 label>
                        </div>
                        </p>
                    </c:forEach>
                    <c:set value="${search}" var="search"/>
                    <br>
                    <h5><font color="blue"/> Поиск смеси по названию: <input type="text" id="search" name="search" value="${search}" onchange="this.form.submit()"></h5>
                    <script>
                        this.form.submit();
                    </script>
                </form>


            </div>
        </div>
    </div>
</div>


<script>

    var chboxes = document.getElementsByName("checkDiagnosis[]");
    for (var i=0; i < chboxes.length; i++){
        chboxes[i].onclick = function () {
            if (this.checked){
                localStorage.setItem(this.id, 'true');

            } else{
                localStorage.setItem(this.id, 'false')
            }
        }
    }
    for (var i=0; i < chboxes.length; i++){
        if (localStorage.getItem(chboxes[i].id) == 'true'){
            chboxes[i].setAttribute('checked','checked');
        }
    }

</script>
</body>
</html>
