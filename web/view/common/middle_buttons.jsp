<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--TODO по всей видимости в currentDate надо изначально передавать дату с сервера-->
<c:set var="currentDate" value="${requestScope.periodData[1][1].date}"/>

<div class="common_middle">

    <div class="upper_buttons">
        <a id = "left" class="left" onclick="previous()">
            <img class="arrow" src="../../image/Arrow.png" alt="Left">
        </a>
        <input name="command" type="button" class="btn_center" value="${requestScope.monthList[requestScope.periodData[1][1].date.monthValue-1]} ${requestScope.periodData[1][1].date.year}">
        <a href="${pageContext.request.contextPath}/pixel?command=next">
            <img class="arrow" src="../../image/Arrow.png" alt="Right">
        </a>
        <script>
            function previous() {
                $.ajax({
                    url: '/pixel',
                    method: 'get',
                    // dataType: "Json",
                    data: {
                        command: "previous",
                        //command: "changePeriod",
                        userId:"${sessionScope.id}",
                        currentDate:"${currentDate}",
                        period:"MONTH"
                    },
                    success: function(data) {
                        console.log("${requestScope.periodData}");
                        document.getElementById("monthTable").remove();
                        console.log(data);
                        $("#mainTablePlace").append(data);
                        console.log(data.responseText);
                    },
                    error: function (data, status) {
                        alert('error ' + status);
                    }
                })
            }
        </script>
    </div>
</div>