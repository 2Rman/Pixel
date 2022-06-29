<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="userId" value="${sessionScope.id}"/>
<c:set var="currentDateGlobal" value="${requestScope.refDate}"/>

<div id="commonMiddle" class="common_middle">
    <div class="upper_buttons">
        <a id = "left" class="left" onclick="changePeriod('previous','${userId}','${currentDateGlobal}')">
            <img class="arrow" src="../../image/Arrow.png" alt="Left">
        </a>
        <input name="command" type="button" class="btn_center" value="${requestScope.monthList[requestScope.periodData[1][1].date.monthValue-1]} ${requestScope.periodData[1][1].date.year}">
        <a id = "right" onclick="changePeriod('next', '${userId}', '${currentDateGlobal}')">
            <img class="arrow" src="../../image/Arrow.png" alt="Right">
        </a>
    </div>
</div>

