function generateTable(pData, id) {

    console.log("generateTable is working")

    let tablePlace = document.getElementById('mainTablePlace');

    let middleTable = document.createElement("div");
    middleTable.className = "calendar-table";
    middleTable.id = "middleTable";
    tablePlace.prepend(middleTable);

    for (let i = 0; i < (pData.length / 7); i++) {

        let week = document.createElement("div");
        week.className = "calendar-row";
        middleTable.append(week);

        for (let j = 0; j < 7; j++) {
            if (new Date(pData[i * 7 + j]["date"]).getMonth() === new Date(pData[7]["date"]).getMonth()) {
                let day = document.createElement("div");
                let dayData = document.createElement("div");
                let dayNum = document.createElement("p");

                day.className = "cell";
                day.onclick = function () {
                    changePeriod("current", id,pData[i * 7 + j]["date"], "DAY");
                };
                dayData.className = "date-cell"

                week.append(day);
                day.prepend(dayData);
                dayData.prepend(dayNum);
                dayNum.innerText = new Date(pData[i * 7 + j]["date"]).getDate().toString();

                let linesCell = document.createElement("div");
                linesCell.className = "lines-cell"
                day.append(linesCell);

                let greenLines = document.createElement("div");
                greenLines.className = "green_lines";
                linesCell.append(greenLines);

                for (let inc = 0; inc < pData[i * 7 + j]["incomeList"].length; inc++) {
                    let greenLine = document.createElement("div");
                    greenLine.className = "green_line";
                    greenLines.append(greenLine);
                }

                let redLines = document.createElement("div");
                redLines.className = "red_lines";
                linesCell.append(redLines);

                for (let exp = 0; exp < pData[i * 7 + j]["expenseList"].length; exp++) {
                    let redLine = document.createElement("div");
                    redLine.className = "red_line";
                    redLines.append(redLine);
                }
            } else {
                let day = document.createElement("div");
                let dayData = document.createElement("div");
                let dayNum = document.createElement("p");
                day.className = "cell_grey";
                dayData.className = "date-cell_grey";

                week.append(day);
                day.prepend(dayData);
                dayData.prepend(dayNum);
                dayNum.innerText = new Date(pData[i * 7 + j]["date"]).getDate().toString();
            }
        }
    }
}