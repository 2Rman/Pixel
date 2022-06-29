function generateTable(pData) {

    let tablePlace = document.getElementById('mainTablePlace');

    let monthTable = document.createElement("div");
    monthTable.className = "calendar-table";
    monthTable.id = "monthTable";
    tablePlace.prepend(monthTable);

    for (let i = 0; i < pData.length; i++) {
        let week = document.createElement("div");
        week.className = "calendar-row";
        monthTable.append(week);

        for (let j = 0; j < pData[i].length; j++) {
            // if (pData[i][j]["date"][1] === pData[1][1]["date"][1]) {
            if (new Date(pData[i][j]["date"]).getMonth() === new Date(pData[1][1]["date"]).getMonth()) {
                let day = document.createElement("div");
                let dayData = document.createElement("div");
                let dayNum = document.createElement("p");

                day.className = "cell";
                day.onclick = function() {
                        alert('It\'s ' + new Date(pData[1][1]["date"]).getDate() + " day!")
                    };
                dayData.className = "date-cell"

                week.append(day);
                day.prepend(dayData);
                dayData.prepend(dayNum);
                // dayNum.innerText = pData[i][j]["date"][2];
                dayNum.innerText = new Date(pData[i][j]["date"]).getDate().toString();

                let linesCell = document.createElement("div");
                linesCell.className = "lines-cell"
                day.append(linesCell);

                let greenLines = document.createElement("div");
                greenLines.className = "green_lines";
                linesCell.append(greenLines);

                for (let inc = 0; inc < pData[i][j]["incomeList"].length; inc++) {
                    let greenLine = document.createElement("div");
                    greenLine.className = "green_line";
                    greenLines.append(greenLine);
                }

                let redLines = document.createElement("div");
                redLines.className = "red_lines";
                linesCell.append(redLines);

                for (let exp = 0; exp < pData[i][j]["expenseList"].length; exp++) {
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
                dayNum.innerText = new Date(pData[i][j]["date"]).getDate().toString();
            }
        }
    }
}