
<!Doctype html>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/index.css">
    <link rel="stylesheet" type="text/css" href="../css/common/page.css">
    <link rel="stylesheet" type="text/css" href="../css/common/header.css">
    <link rel="stylesheet" type="text/css" href="../css/common/rectangle.css">
    <link rel="stylesheet" type="text/css" href="../css/bona_nova.css">
    <link rel="stylesheet" type="text/css" href="../css/manrope.css">

    <title>Pixel</title>
</head>
<body>
    <jsp:include page="common/background_rectangles.jsp"/>
    <div class="greetings_box">
        <div class="header_start">
            <div class="top_text">
                Добро пожаловать в PixeL!
            </div>
        </div>
    </div>

    <div class=button_box>
        <div class="logo_start">
            <img src="../image/Pixel.png">
        </div>
        <form method="post">
            <input type="submit" class="btn" id="btn_registration" formaction="/pixel?command=registration" value="Регистрация">
            <input type="submit" class="btn" id="btn_login" formaction="/pixel?command=authorization" value="Войти">
            <input type="submit" class="btn btn_about" id="btn_about" formaction="/pixel?command=about" value="Что такое PixeL">
        </form>
    </div>
</body>
</html>
