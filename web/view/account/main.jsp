<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>
        <jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>
        Привет!
        Это страница аккаунта
        <form method="post" action="/pixel?command=show">
            <input type="submit" value="button">
        </form>
    </h1>
</body>
</html>
