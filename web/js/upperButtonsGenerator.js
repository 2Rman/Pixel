function generateUpperButtons(pDataTable, userId, refDate, period) {

    console.log("generateUpperButtons is working")

    let buttonsPlace = document.getElementById("upperButtonsPlace")
    let rDate = document.getElementById("upperButtonsPlace").getAttribute("referenceDate");

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
    left.onclick = function () {
        changePeriod("previous", userId, rDate, period);
    }
    upperButtons.append(left);

    let img = document.createElement("img");
    img.className = "arrow";
    img.src = "../image/Arrow.png";
    left.prepend(img);


    //-------------ГЕНЕРАЦИЯ ЦЕНТРАЛЬНОЙ КНОПКИ----------------------------
    let centerButton = document.createElement("input");
    centerButton.id = "centerButton";
    centerButton.className = "btn_center";
    centerButton.type = "button";
    centerButton.value = defineText(rDate, period);
    genPeriodPopup(userId, rDate);
    centerButton.onclick = function () {
        document.querySelector("dialog").showModal();
    }
    upperButtons.append(centerButton);

    //-------------ГЕНЕРАЦИЯ КНОПКИ "ВПРАВО"----------------------------
    let right = document.createElement("a");
    right.id = "right";
    right.onclick = function () {
        changePeriod("next", userId, rDate, period)
    };
    upperButtons.append(right);

    let imgR = document.createElement("img");
    imgR.className = "arrow";
    imgR.src = "../image/Arrow.png";
    right.append(imgR);
}

function defineText(rDate, period) {
    switch (period.toLowerCase()) {
        case PERIOD_VAR[0]: {
            return new Date(rDate).getDate() + " " + MONTH_NAMES[new Date(rDate).getMonth()] + " " + new Date(rDate).getFullYear();
        }
        case PERIOD_VAR[1]: {
            return MONTH_NAMES[new Date(rDate).getMonth()] + " " + new Date(rDate).getFullYear();
        }
        case PERIOD_VAR[2]: {
            return MONTH_NAMES[new Date(rDate).getMonth()] + " " + new Date(rDate).getFullYear();
        }
        case PERIOD_VAR[3]: {
            return new Date(rDate).getFullYear();
        }
    }
}
