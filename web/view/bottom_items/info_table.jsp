<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="tableValues" scope="page" value="${requestScope.totalData}"/>
<div class="info-table">
    <c:forEach var="raw" items="${tableValues}" varStatus="count" >
        <div class="info-row">
            <p class="text-info-table">${raw.key}</p>
            <p class="value-info-table">${raw.value}</p>
        </div>
    </c:forEach>
</div>

