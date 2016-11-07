<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            localStorage.setItem('toUpdate', 'false');
        }
        function showModal(){
            document.getElementById("parent_popup").style.display = 'block';

        }
        function closeModal(){
            document.getElementById("parent_popup").style.display = 'none';
        }
        function onClickSave() {
            var objects = [];
            var radio = document.getElementsByName('checkPriorDiagnosis');
            for (var i = 0; i < radio.length; i++)
            {
                if  (radio[i].checked == true) {
                    objects.push({
                        name: "idDiagnosis",
                        value: radio[i].value
                    });
                    break;
                }
            }

            if (objects.length == 0) {
                objects.push({
                    name: "id",
                    value: radio[0].value
                });
            }
            objects.push({
                name: "idFormula",
                value: ${formula.id}
            })

            $.post("info/save", objects, function(data, status) {
                window.location.reload();
            });
        }
        function deleteFormula() {
            if (confirm("Удалить смесь?")){
                location.href='delete${formula.id}';
                //location.href='formula';
            }

        }



    </script>
    <style>
        #parent_popup {
            display:none;
            background: #000;
            height: 100%;
            opacity: 0.9;
            position: fixed;
            width: 100%;
            z-index: 100;
            top: 0;
            left: 0;
        }
        #popup {
            background-color: white;
            height: 60%;
            position: fixed;
            top: 20%;
            left: 40%;
            color: #000;
            width: 30%;

        }
    </style>
</head>
<body onload="update()">
<c:set var="priorDiagnosis" value="${priorDiagnosis}"/>
<div id="parent_popup">
    <div id="popup">
        <p class="text-right" style="cursor: pointer; color: #f00" onclick="closeModal()"><b>X</b></p>
        <br>
        <c:forEach items="${diagnosis}" var="diagnosis">
            <c:set var="diagnosisName" value="${diagnosis.diagnosis.name}"/>
            <c:set var="priorDiagnosisName" value="${priorDiagnosis.diagnosis.name}"/>
            <c:choose>
                <c:when test="${diagnosisName eq priorDiagnosisName}">
                    <h4 style="padding-left:20px;"><input type="radio" value="${diagnosis.diagnosis.id}"
                            name="checkPriorDiagnosis" id ="${diagnosis.diagnosis.name}" checked> ${diagnosis.diagnosis.name}</h4>
                </c:when>
                <c:otherwise>
                    <h4 style="padding-left:20px;"><input type="radio" value="${diagnosis.diagnosis.id}"
                            name="checkPriorDiagnosis" id ="${diagnosis.diagnosis.name}"> ${diagnosis.diagnosis.name}</h4>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <br>
        <h5 class="text-center"/>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <input type="button" value="Установить" onclick="onClickSave()">
        </sec:authorize>

    </div>
</div>



    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12" style = "border:3px #ffff00 solid; background: #FEFEF9; width: 100%">
                <input type="button" value="<< Назад" onClick="location.href='formula'">
                <h2 class="text-center" items="${formula}" var="formula"/>${formula.name}
            </div>
        </div>


        <div class="row-fluid">

            <div class="col-lg-6 table-responsive" style = "border:3px #9932cc solid;  background: #FBF3FE; height: 85%;">

                <div style="height:100%; width:100%;overflow:auto;">
                    <table class="table table-striped table-hover" cellspacing="0" width="100%">
                        <thead>
                        <tr>

                            <th>Cостав</th>
                            <th></th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${composition}" var="composition">
                            <tr>
                                <td>${composition.components.name}</td>
                                <td>${composition.value} ${composition.units}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
            <div class="col-lg-5" style = "border:3px #00fa9a solid; background:#EEFEF1; height: 85%;">

                    <div class="row" style="left: 10px;">
                        <div class="col-md-12">

                            <h4 class="text-center"/><img src = "pic/${formula.pic}" class="img-rounded" alt="1" width="250" height="200">
                            <h4 class="text-center"/><b>Может исользоваться как: </b>
                                <c:forEach items="${diagnosis}" var="diagnosis">
                                <h4>${diagnosis.diagnosis.name}</h4>
                                </c:forEach>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <br>
                                <input type="button" value="Установить приоритетет" onclick = showModal()>
                            </sec:authorize>
                            <br>
                            <h4 class="text-left"/><b style="color: blue">Приоритет: </b>


                            ${priorDiagnosis.diagnosis.name}

                            <br>
                            <h4 class="text-center"/> <b>Информация</b>
                            <p class="text-left"><br>${formula.info}</p>

                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <br>
                                <h4 class="text-left"></h4>
                                <input type="button" value="Удалить смесь" onclick = "deleteFormula()">
                            </sec:authorize>
                        </div>

                    </div>
            </div>
        </div>
    </div>

</body>
</html>
