function genPeriodPopup(userId, rDate) {

    let refDate = document.getElementById("upperButtonsPlace").getAttribute("referenceDate");

    let mainTable = document.getElementById("commonMiddle");
    let popup = document.createElement("dialog");
    popup.id = "popup";
    popup.className = "popup";

    for (let c = 0; c < 4; c++) {
        let periodBut = document.createElement("button");
        periodBut.id = "period_" + c;
        periodBut.className = "periodButton";
        periodBut.innerText = PERIOD[c];
        periodBut.onclick = function () {
            document.querySelector("dialog").close();
            console.log(refDate);
            changePeriod("current", userId, refDate, PERIOD_VAR[c]);
        }
        popup.append(periodBut);
    }
    mainTable.append(popup);
}