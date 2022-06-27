function generateUpperButtons(pDataTable, userId, currentDate) {
    let buttonsPlace = document.getElementById("upperButtonsPlace")

    let commonMiddle = document.createElement("div");
    commonMiddle.id = "commonMiddle";
    commonMiddle.className = "common_middle";
    buttonsPlace.prepend(commonMiddle);

    let upperButtons = document.createElement("div");
    upperButtons.className = "upper_buttons";
    commonMiddle.prepend(upperButtons);

    //-------------ГЕНЕРАЦИЯ КНОПКИ "ВЛЕВО"----------------------------
        let left = document.createElement("a");
        left.id = "left";
        left.className = "left";
        // left.onclick = changePeriod(ssd);
        left.onclick = function () {
            $.ajax({
                url: '/pixel',
                method: 'get',
                data: {
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    command: "change_period",
                    direction: "previous",
                    userId: userId,
                    currentDate: currentDate,
                    period:"MONTH"
                },
                success: function(data) {
                    document.getElementById("commonMiddle").remove();
                    document.getElementById("monthTable").remove();
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
        upperButtons.append(left);

            let img = document.createElement("img");
            img.className = "arrow";
            img.src = "../image/Arrow.png";
            left.prepend(img);


    //-------------ГЕНЕРАЦИЯ ЦЕНТРАЛЬНОЙ КНОПКИ----------------------------
        let centerButton = document.createElement("input");
        centerButton.className = "btn_center";
        centerButton.type = "button";
        centerButton.value = MONTH_NAMES[pDataTable[1][1]["date"][1] - 1] + " " + pDataTable[1][1]["date"][0];  //Отнимаем 1 потому что отсчет с нуля, а месяцы передаются в своих нормальных цифрах
        upperButtons.append(centerButton);

    //-------------ГЕНЕРАЦИЯ КНОПКИ "ВПРАВО"----------------------------
        let right = document.createElement("a");
        right.id = "right";
        right.onclick = function () {
            $.ajax({
                url: '/pixel',
                method: 'get',
                data: {
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    command: "change_period",
                    direction: "next",
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
        };
        upperButtons.append(right);

            let imgR = document.createElement("img");
            imgR.className = "arrow";
            imgR.src = "../image/Arrow.png";
            right.append(imgR);
}
