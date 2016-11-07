<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Puzzle</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script>
        function update() {
            localStorage.setItem('toUpdate', 'false');
        }
        function updateFormula(formulaName) {

            document.getElementById("update").submit();
            location.href = 'addFormula?update=1&formulaName=' + formulaName + '&saveFile=0';
        }

        function newPicture() {
            document.getElementById("file").onclick();
        }

        function reload() {
            location.href = 'addFormula?update=0&formulaName=""&saveFile=0';
        }


    </script>
</head>
<body onload="update()">

<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-lg-12" style = "border:3px #ffff00 solid; background: #FEFEF9; width: 100% ">
            <input type="button" value="<< Назад" onClick="location.href='formula'">
            <h2 class="text-center"/>Редактирование / добавление новой смеси</p>
        </div>
    </div>

    <div class="row-fluid">

        <div class="col-lg-6" style = "border:3px #9932cc solid; background: #FBF3FE; height: 80%;">
            <div style="height:100%; width:100%;overflow:auto; ">

                    <table class="table"  width="100%">
                        <thead>
                        <tr>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:set value="${formulaToUpdate}" var="formulaToUpdate"/>
                            <tr>
                                <form action= "addFormula?update=1"  method="post" id="update">
                                    <h4> Выберите смесь для редактирования
                                        <select size="1" name="form" onchange="updateFormula(value)">
                                        <option disabled>Выберите смесь</option>
                                         <option value=""></option>
                                        <c:forEach items="${formula}" var="formula">
                                            <option value="${formula.name}">${formula.name}</option>
                                        </c:forEach>


                                        </select></h4>
                                        <h4><input type="button" value="Очистить" onclick=reload()></h4>
                                </form>
                            </tr>
                            <tr>
                                <td>Изображение</td>
                                <td>
                                    <c:set var="fileName" value="${fileName}"/>
                                    <c:choose>
                                        <c:when test="${fileName eq '-'}">
                                            <form method="post" enctype="multipart/form-data" id="ffile" action="addFormula?update=0&formulaName=''&saveFile=1">
                                                <input type="file" id="file" name="file" onchange="this.form.submit ()"/>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            Выбранный файл: ${fileName}

                                        </c:otherwise>
                                    </c:choose>

                                </td>
                            </tr>
                            <tr>
                                <td>Название смеси </td>
                                <td><input type="text" size="50" name="fname" id ="fname" value="${formulaToUpdate.name}"></td>
                            </tr>
                            <tr>
                                <td>Информация о смеси</td>
                                <td><textarea name="info" id ="info" rows="5" cols="55" wrap="virtual" > ${formulaToUpdate.info}</textarea> </td>
                            </tr>



                        </tbody>
                    </table>

            </div>
        </div>
        <div class="col-lg-6" style = "border:3px #00fa9a solid; height: 80%; background:#EEFEF1">
            <div style="height:90%; width:100%; overflow:auto;">
                <table class="table table-striped table-hover" cellspacing="0" width="100% ">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Состав</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="number" value="${0}"/>

                    <c:forEach items="${components}" var="components">

                        <tr>
                            <c:set var= "number" value="${number + 1}"/>
                            <td>${number}</td>
                            <td>${components.name}</td>
                            <td>
                                <c:set value="false" var="isFind"/>
                                <c:forEach items="${formulaToUpdate.compositions}" var="comp">


                                    <c:if test="${comp.components.name eq components.name}">

                                        <input class="markClass" type="number" name="${components.id}" min = 0 step="0.01" value="${comp.value}">
                                        <input class="unitClass" type="text" size="5px" name="${components.defaultUnits}" value="${comp.units}">
                                        <c:set value="true" var="isFind"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${isFind eq 'false'}">
                                    <input class="markClass" type="number" name="${components.id}" min = 0 step="0.01">
                                    <input class="unitClass" type="text" size="5px" name="${components.defaultUnits}" value="${components.defaultUnits}">
                                    <c:set value="false" var="isFind"/>
                                </c:if>


                            </td>



                        </tr>

                    </c:forEach>



                    </tbody>
                </table>
            </div>

            <div class="row">
                <div class="col-lg-6 col-md-offset-3" style = "top:20px;">

                    <h5 class="text-center"/>

                    <button type="button" id="save" class="button" width="100px"> Сохранить </button>
                </div>

            </div>

        </div>
    </div>
</div>

<script>

    $('#save').click(function(){

        var dataSet = $(".markClass");
        var dataSetUnits = $(".unitClass");
        var objects = [];
        objects.push({
            name: "name",
            value: document.getElementById("fname").value
        });
        objects.push({
            name: "info",
            value: document.getElementById("info").value
        });
        objects.push({
            name: "file",
            value: "${fileName}"
        });
        for(var index = 0;  index < dataSet.length; ++index)
        {
            if (dataSet[index].value != "" && dataSet[index].value != undefined)
            {
                objects.push({
                    name: dataSet[index].name,
                    value: dataSet[index].value + "-" + dataSetUnits[index].value
                });
            }
        }
        $.post("addFormula/save", objects, function(data, status) {
            location.href='formula';
        });
    });
</script>

</body>
</html>
