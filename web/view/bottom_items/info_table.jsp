<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="tableValues" scope="page" value="${requestScope.totalData}"/>

<div id="infoTable" class="info-table">
    <c:forEach var="row" items="${tableValues}" varStatus="count" >
        <div class="info-row">
<%--            <p class="text-info-table">${row.key}</p>--%>
            <p class="text-info-table">
                <script>
                    document.write(TOTAL_TABLE_ITEMS[${count.index}]);
                </script>
            </p>
            <p class="value-info-table">${row}</p>
        </div>
    </c:forEach>
</div>

