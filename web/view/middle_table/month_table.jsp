<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="date" class="java.util.Date"/>

<c:set var="userId" value="${sessionScope.id}"/>
<c:set var="curdate" value="19"/>

<div id="middleTable" class="calendar-table">
    <c:forEach var="day" items="${requestScope.periodData}" varStatus="dayNum">
        <c:if test="${dayNum.index % 7 == 0}">
            <div class="calendar-row">
        </c:if>
                    <%--                ЗДЕСЬ В УСЛОВИИ (date.month-1) - т.к. месяцы в date читаются с 1--%>
                <c:if test="${day.date.monthValue == requestScope.periodData[7].date.monthValue}">
                    <div id="cell" class="cell"
                         onclick=changePeriod('current','${userId}','${day.date.toString()}','DAY')>
                        <div class="date-cell">
                            <p>
                                    ${day.date.dayOfMonth}
                            </p>
                        </div>
                        <div class="lines-cell">
                            <div class="green_lines">
                                <c:forEach var="line" items="${day.incomeList}">
                                    <div class="green_line"></div>
                                </c:forEach>
                            </div>
                            <div class="red_lines">
                                <c:forEach var="line" items="${day.expenseList}">
                                    <div class="red_line"></div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                </c:if>
                    <%--                ЗДЕСЬ В УСЛОВИИ (date.month-1) - т.к. месяцы в date читаются с 1--%>
                <c:if test="${day.date.monthValue != requestScope.periodData[7].date.monthValue}">
                    <div class="cell_grey">
                        <div class="date-cell_grey">
                            <p>
                                    ${day.date.dayOfMonth}
                            </p>
                        </div>
                    </div>
                </c:if>
        <c:if test="${(dayNum.index - 6) % 7 == 0}">
            </div>
        </c:if>
    </c:forEach>
</div>

