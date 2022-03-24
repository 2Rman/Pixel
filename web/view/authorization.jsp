<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Авторизация</title>
        <link rel="stylesheet" type="text/css" href="../css/index.css">
        <link rel="stylesheet" type="text/css" href="../css/common/header.css">
        <link rel="stylesheet" type="text/css" href="../css/common/page.css">
        <link rel="stylesheet" type="text/css" href="../css/authorization.css">
        <link rel="stylesheet" type="text/css" href="../css/manrope.css">
    </head>
    <body>
        <div class="header">
            <div class="logo">
                <img src="../image/Pixel.png" alt="PixeL">
            </div>
            <div class="form_container">
                <form class="register_form" method="post" action="/pixel?command=log_in">
                    <div class="text_top">
                        Уже есть аккаунт?
                    </div>
                    <div class="text_box">
                        <div>Просто введите</div>
                        <div>Ваш номер телефона и пароль!</div>
                    </div>
                    <div class="text_box">
                        Ваш номер телефона
                    </div>
                    <input class="input_box" type="text" placeholder="+375 29 123 45 67" autocomplete="off">
                    <div class="text_box">
                        Введите пароль
                    </div>
                    <input id="password" class="input_box" type="password" autocomplete="off">
                    <input type="submit" class="btn btn_submit" value="Войти">
                </form>
            </div>
        </div>
    </body>
</html>
