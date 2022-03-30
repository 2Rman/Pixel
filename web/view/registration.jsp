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
            <form id="register" class="register_form" action="/pixel">
                <div class="text_box">
                    Как к Вам обращаться?
                </div>
                <input class="input_box" name="username" type="text" placeholder="Ваше имя">
                <div class="text_box">
                    Ваш номер телефона
                </div>
                <input class="input_box" name="phone" type="text" placeholder="+375 29 123 45 67">
                <div class="text_box">
                    Задайте пароль
                </div>
                <input id="password" name="password" class="input_box" type="password">
                <div class="text_box">
                    Повторите пароль
                </div>
                <input id="password_repeat" class="input_box" type="password">
                <button type="submit" formmethod="post" class="btn btn_submit" name="command" value="register">Регистрация</button>
            </form>
        </div>
    </div>

</body>
</html>
