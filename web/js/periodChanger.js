function changePeriod(direction, userId, refDate, period) {

    console.log("changePeriod is working");

    $.ajax({
        url: '/pixel',
        method: 'get',
        data: {
            contentType: "application/json; charset=utf-8; encodeURIComponent()",
            dataType: "json",
            command: "change_period",
            direction: direction,
            userId: userId,
            currentDate: refDate,
            period: period
        },
        success: function(data) {
            document.getElementById("commonMiddle").remove();
            document.getElementById("middleTable").remove();
            document.getElementById("infoTable").remove();

            let parsedData = JSON.parse(data);
            let rDate = parseDate(parsedData[0]);

            document.getElementById("upperButtonsPlace").setAttribute("referenceDate", parseDate(parsedData[0]));
            generateUpperButtons(JSON.parse(parsedData[1]), userId, rDate, period);
            console.log("here is the point")
            defineMiddleContent(period, JSON.parse(parsedData[1]), userId);
            generateTotalData(JSON.parse(parsedData[2]));
        },
        error: function (data, status) {
            alert('error ' + status);
        }
    })
}

function parseDate(date) {

    let jDate = new Date(date);

    let year = jDate.getFullYear();
    let month = jDate.getMonth() + 1; //"+1" Потому что месяцы с 0!
    let day = jDate.getDate();

    if (month < 10) {
        month = '0' + month;
    }

    if (jDate.getDate() < 10) {
        day = '0' + day;
    }

    return year + '-' + month + '-' + day;
}

function defineMiddleContent(period, data, id) {
    switch (period.toLowerCase()) {
        case PERIOD_VAR[0]:
            generateDay(data);
            break;
        case PERIOD_VAR[1]:
            break;
        case PERIOD_VAR[2]:
            generateTable(data, id);
            break;
        case PERIOD_VAR[3]:
            break;
    }
}