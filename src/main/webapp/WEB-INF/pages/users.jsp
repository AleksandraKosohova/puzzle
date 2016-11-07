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
        function getInfo(log, email, phone, role) {
           document.getElementById("login").value = log;
           document.getElementById("email").value = email;
           document.getElementById("phone").value = phone;
            if(role == "ROLE_USER"){
                document.getElementById("role").value = "user";
            }
            else {
                document.getElementById("role").value = "admin";
            }

        }

        function deleteUser(log) {
            if (log != ""){
                if (confirm("Удалить пользователя " + log+ "?" )){
                    var objects = [];
                    objects.push({
                        name: "log",
                        value: log
                    });
                    $.post("deleteUser/" + log, objects, function(data, status) {
                        location.href='formula';
                    });
                }
            }
            else {
                alert("Необходимо выбрать пользователя.")
            }

        }
    </script>

</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-lg-12" style = "border:3px #ffff00 solid; background: #FEFEF9; width: 100% ">
            <input type="button" value="<< Назад" onClick="location.href='formula'">
            <h2 class="text-center"/>Список пользователей</p>
        </div>
    </div>

    <div class="row-fluid">

        <div class="col-lg-6" style = "border:3px #9932cc solid; background: #FBF3FE; height: 80%;">
            <h4>Список пользователей</h4>
            <div style="height:100%; width:100%;overflow:auto; ">

                <table class="table table-striped table-hover" cellspacing="0"  width="100%">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Логин</th>
                        <th>Право доступа</th>
                        <th>email</th>
                        <th>Телефон</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:set var="number" value="${0}"/>
                        <c:forEach items="${users}" var="users">

                            <tr onClick="getInfo('${users.login}', '${users.email}', '${users.phone}', '${users.role}')">
                                <c:set var= "number" value="${number + 1}"/>
                                <td>${number}</td>
                                <td id = "log">${users.login}</td>
                                <td>${users.role}</td>
                                <td>${users.email}</td>
                                <td>${users.phone}</td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
        <div class="col-lg-6" style = "border:3px #00fa9a solid; height: 80%; background:#EEFEF1">
            <h4>Добавление нового пользователя</h4>
            <div style="height:90%; width:100%; overflow:auto;">
                <div class="col-lg-6 col-md-offset-3" style = "top:20px;">
                    <h5>
                        Право доступа:
                        <select name="role" id="role" size="1">
                            <option selected value="user">user</option>
                            <option value="admin">admin</option>
                        </select>
                    </h5>
                    <h5>Логин: <input type="text" size="40" name="login" id ="login"></h5>
                    <h5>Пароль: <input type="text" size="40" name="password" id ="password"></h5>
                    <h5>email: <input type="text" size="40" name="email" id ="email"></h5>
                    <h5>Телефон: <input type="text" size="40" name="phone" id ="phone"></h5>

                    <h5 class="text-center"/>

                    <button type="button" id="save" class="button" width="100px"> Добавить </button>

                    <c:set value="document.getElementById('login').value" var="log"/>

                    <input type="button" width="100px" value="Удалить" onclick = "deleteUser(document.getElementById('login').value)">
                </div>
            </div>


        </div>
    </div>
</div>

<script>
    $('#save').click(function(){
        var dataSet = $(".markClass");
        var objects = [];
        objects.push({
            name: "login",
            value: document.getElementById("login").value
        });
        objects.push({
            name: "password",
            value: document.getElementById("password").value
        });
        objects.push({
            name: "email",
            value: document.getElementById("email").value
        });
        objects.push({
            name: "phone",
            value: document.getElementById("phone").value
        });

        objects.push({
            name: "role",
            value: document.getElementById("role").value
        });

        $.post("users/save", objects, function(data, status) {
            window.location.reload();
        });
        alert("Сохранено.");
    });

</script>

</body>
</html>