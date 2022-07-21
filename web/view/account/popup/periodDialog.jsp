<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
    <dialog id="popup" class="popup">
        <c:forEach var="period" items="${requestScope.periodItems}" varStatus="count">
            <button id="period_${count.index}" class="periodButton" onclick="document.querySelector('dialog').close()
                    changePeriod('current','${sessionScope.id}','${requestScope.refDate}','${requestScope.periodVar[count.index]}')">
                ${period}
            </button>
        </c:forEach>
    </dialog>
</html>

