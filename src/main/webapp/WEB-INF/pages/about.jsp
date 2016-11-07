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

</head>
<body>
<div class="container-fluid">

    <div class="row-fluid">

        <div class="col-lg-12" style = "border:3px #ffff00 solid; background: #FEFEF9; width: 100% ">

            <div class="btn-group text-left col-lg-12">
                <input type="button" value="<< Назад" onClick="location.href='formula'">

            </div>


            <h3 class="text-center"/>О программе</p>

        </div>
    </div>



    <div class="row-fluid">

        <div class="col-lg-6" style = "border:3px #9932cc solid; background: #FBF3FE; height: 85%;">
            <div class="col-md-5" style="margin-left: 5%; margin-top: 15%;">
                <img src = "pic/logo.jpg">
            </div>
        </div>
        <div class="col-lg-6" style = "border:3px #00fa9a solid;  height: 85%; background:#EEFEF1">
            <div class="col-md-8" style="margin-left: 10%; margin-top: 10%;">
                <p>Существует множество различных лечебных смесей.
                    В зависимости от состава, смесь можно отнести к определенной группе.
                    Кроме того, одна и та же смесь может относиться к нескольким группам.
                    При назначении ребенку лечебной смеси,
                    исходя из диагноза, врач определяет группы смесей, необходимые для лечения и подбирает
                    наиболее подходящую смесь.</p>
                <p>Данная программа работает в двух режимах:</p>
                - отображение списка смесей, относящихся ко всем выбранным видам и просмотр информации по выбранной смеси (для пользователей);
                <br>- добавление и редактирование данных, при котором вид смеси определяется автоматически, исходя из состава смеси (для администратора).
            </div>

        </div>
    </div>
</div>
</body>
</html>
