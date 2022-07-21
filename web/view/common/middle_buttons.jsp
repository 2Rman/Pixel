<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../account/popup/periodDialog.jsp"/>

<c:set var="userId" value="${sessionScope.id}"/>
<c:set var="currentDateGlobal" value="${requestScope.refDate}"/>

<div id="commonMiddle" class="common_middle">
    <div class="upper_buttons">
        <a id="left" class="left" onclick="changePeriod('previous','${userId}','${currentDateGlobal}','MONTH')">
            <img class="arrow" src="../../image/Arrow.png" alt="Left">
        </a>
        <input name="command" type="button" class="btn_center" onclick=showPeriodPopup();
               value="${requestScope.monthList[requestScope.periodData[7].date.monthValue-1]} ${requestScope.periodData[7].date.year}">
        <a id="right" onclick="changePeriod('next', '${userId}', '${currentDateGlobal}','MONTH')">
            <img class="arrow" src="../../image/Arrow.png" alt="Right">
        </a>
    </div>
</div>

