<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="../css/index.css">
    <link rel="stylesheet" type="text/css" href="../css/common/header.css">
    <link rel="stylesheet" type="text/css" href="../css/common/page.css">
    <link rel="stylesheet" type="text/css" href="../css/register.css">
    <link rel="stylesheet" type="text/css" href="../css/manrope.css">
</head>
<body>
    <div class="header">
        <div class="logo">
            <img src="../image/Pixel.png" alt="PixeL">
        </div>
        <div class="form_container">
            <form class="register_form" method="post" action="register">
                <div class="text_box">
                    Как к Вам обращаться?
                </div>
                <input class="input_box" type="text" placeholder="Ваше имя" name="username" autocomplete="off" onfocus="null">
                <div class="text_box">
                    Ваш номер телефона
                </div>
                <input class="input_box" type="text" placeholder="+375 29 123 45 67" autocomplete="off">
                <div class="text_box">
                    Задайте пароль
                </div>
                <input id="password" class="input_box" type="password" autocomplete="off">
                <div class="text_box">
                    Повторите пароль
                </div>
                <input id="password_repeat" class="input_box" type="password" autocomplete="off">
                <input type="submit" class="btn btn_submit" value="Регистрация">
            </form>
        </div>
    </div>

</body>
</html>
