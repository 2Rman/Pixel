<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Pixel</title>
    <link rel="stylesheet" type="text/css" href="../../css/manrope.css">
    <link rel="stylesheet" type="text/css" href="../../css/common/rectangle.css">
    <link rel="stylesheet" type="text/css" href="../../css/common/page.css">
    <link rel="stylesheet" type="text/css" href="../../css/common/header.css">
    <link rel="stylesheet" type="text/css" href="../../css/middle/middle_container.css">
    <link rel="stylesheet" type="text/css" href="../../css/middle/cell.css">
</head>
<body>
    
    <jsp:useBean id="accountDAO" scope="session" class="app.dao.AccountDAO"/>

    <c:set var="account" value="accountDAO.getById()}"/>

    <jsp:include page="../common/background_rectangles.jsp"/>
    <jsp:include page="../common/account_header.jsp"/>
    <jsp:include page="../common/middle_buttons.jsp"/>

    <jsp:include page="../middle_table/cell.jsp"/>



</body>
</html>
