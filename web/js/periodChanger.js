function changePeriod(direction, userId, currentDate) {
    $.ajax({
        url: '/pixel',
        method: 'get',
        data: {
            contentType: "application/json; charset=windows-1251; encodeURIComponent()",
            dataType: "json",
            command: "change_period",
            direction: direction,
            userId: userId,
            currentDate: currentDate,
            period:"MONTH"
        },
        success: function(data) {
            document.getElementById("commonMiddle").remove();
            document.getElementById("monthTable").remove();
            document.getElementById("infoTable").remove();
            let parsedData = JSON.parse(data);
            console.log(parsedData)
            generateUpperButtons(JSON.parse(parsedData[1]), userId, currentDate);
            generateTable(JSON.parse(parsedData[1]));
            generateTotalData(JSON.parse(parsedData[2]));
        },
        error: function (data, status) {
            alert('error ' + status);
        }
    })
}