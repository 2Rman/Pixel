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
    <link rel="stylesheet" type="text/css" href="../../css/bottom/info_table.css">
    <link rel="stylesheet" type="text/css" href="../../css/bottom/bottom_buttons.css">
</head>
<body>

    <jsp:include page="../common/background_rectangles.jsp"/>
    <jsp:include page="../common/account_header.jsp"/>
    <jsp:include page="../common/middle_buttons.jsp"/>
    <jsp:include page="../middle_table/month_table.jsp"/>

    <div class="info-table">
        <jsp:include page="../bottom_items/info_table.jsp"/>
    </div>

    <jsp:include page="../bottom_items/bottom_buttons.jsp"/>

</body>
</html>
