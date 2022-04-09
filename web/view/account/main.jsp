<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../css/common/rectangle.css">
</head>
<body>
    <h1>
        <jsp:include page="../common/background_rectangles.jsp"/>
        Привет!
        Это страница аккаунта
        <c:out value="${15+15}"/>
        <form method="post" action="/pixel">
            <input type="submit" value="button" formaction="command=show_account">
        </form>
    </h1>
</body>
</html>
