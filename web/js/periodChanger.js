function changePeriod(direction, userId, refDate) {

    console.log("changePeriod is working");

    $.ajax({
        url: '/pixel',
        method: 'get',
        data: {
            contentType: "application/json; charset=windows-1251; encodeURIComponent()",
            dataType: "json",
            command: "change_period",
            direction: direction,
            userId: userId,
            currentDate: refDate,
            period:"MONTH"
        },
        success: function(data) {
            document.getElementById("commonMiddle").remove();
            document.getElementById("monthTable").remove();
            document.getElementById("infoTable").remove();

            let parsedData = JSON.parse(data);

            document.getElementById("upperButtonsPlace").setAttribute("referenceDate", dateParser(parsedData[0]));
            generateUpperButtons(JSON.parse(parsedData[1]), userId, refDate);
            generateTable(JSON.parse(parsedData[1]));
            generateTotalData(JSON.parse(parsedData[2]));
        },
        error: function (data, status) {
            alert('error ' + status);
        }
    })
}

function dateParser(date) {

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