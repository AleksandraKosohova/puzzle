<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Puzzle</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <style>
        body {
            background: url(pic/background.jpg) no-repeat;
            background-size: 100%;
        }

        h1 {
            font-family: 'Times New Roman', Times, serif;
            font-size: 200%;
        }

    </style>

</head>
<body>

    <c:url value="/j_spring_security_check" var="loginUrl" />
    <div class="col-md-12 ">
        <h1> <p align="center"> <font color="5200cc"> Подбор лечебного детского питания </p></h1>

    </div>

    <div class="row-fluid">
        <div class="col-md-5" style="margin-left: 5%; margin-top: 5%; height: 85%">
            <form action="${loginUrl}" method="POST">

                <table width="400">
                    <tr>
                        <img src = "pic/logo.jpg">
                    </tr>
                    <tr>
                        <td align="right">Login: </td>
                        <td align="right"> <input type="text" name="j_login" /></td>
                        </tr>
                    <tr>
                        <td align="right">Passsword: </td>
                        <td align="right"><input type="password" name="j_password" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="right"><input type="submit"/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>

                        <td>
                            <c:if test="${param.error ne null}">
                            <h3>Wrong login or password!</h3>
                            </c:if>

                            <c:if test="${param.logout ne null}">
                                <h3>Выход выполнен</h3>
                            </c:if>
                        </td>
                        <td></td>
                    </tr>
                </table>


            </form>
        </div>
        <div class="col-md-5" style="margin-left: 5%; margin-top: 10%; height: 85%">

        </div>
    </div>
</body>
</html>
