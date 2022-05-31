
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="common_middle">
    <div class="upper_buttons">
        <input type="image" class="arrow left" src="../../image/Arrow.png" alt="Left">
        <input type="button" class="btn_center" value="${requestScope.monthList[requestScope.periodData[1][1].date.monthValue-1]} ${requestScope.periodData[1][1].date.year}">
        <input type="image" class="arrow" src="../../image/Arrow.png" alt="Right">
    </div>
</div>