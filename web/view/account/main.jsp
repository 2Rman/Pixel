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
    <link rel="stylesheet" type="text/css" href="../../css/common/popup.css">
    <link rel="stylesheet" type="text/css" href="../../css/middle/middle_container.css">
    <link rel="stylesheet" type="text/css" href="../../css/middle/cell.css">
    <link rel="stylesheet" type="text/css" href="../../css/middle/day.css">
    <link rel="stylesheet" type="text/css" href="../../css/bottom/info_table.css">
    <link rel="stylesheet" type="text/css" href="../../css/bottom/bottom_buttons.css">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="../../js/constant.js"></script>

</head>
<body>

    <jsp:include page="../common/background_rectangles.jsp"/>
    <jsp:include page="../common/account_header.jsp"/>
    <div id="upperButtonsPlace">
        <jsp:include page="../common/middle_buttons.jsp"/>
    </div>
    <div id="mainTablePlace">
<%--        <jsp:include page="../middle_table/month_table.jsp"/>--%>
        <jsp:include page="../middle_table/month_table.jsp"/>
<%--    <jsp:include page="../middle_table/day.jsp"/>--%>
    </div>
    <div id="totalDataPlace">
        <jsp:include page="../bottom_items/info_table.jsp"/>
    </div>
    <jsp:include page="../bottom_items/bottom_buttons.jsp"/>

</body>
</html>

<script src="../../js/middleTableGenerator.js"></script>
<script src="../../js/upperButtonsGenerator.js"></script>
<script src="../../js/totalDataTableGenerator.js"></script>
<script src="../../js/periodChanger.js"></script>
<script src="../../js/middleDayGenerator.js"></script>
<script src="../../js/dialogGenerator.js"></script>


