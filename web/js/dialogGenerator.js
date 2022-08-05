function genPeriodPopup(userId, rDate) {

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
            changePeriod("current", userId, rDate, PERIOD_VAR[c]);
        }
        popup.append(periodBut);
    }
    mainTable.append(popup);
}

function showPeriodPopup() {
    document.querySelector('dialog').showModal();
}

/**
 * Добавление popup для внесения новой записи в БД
 *
 * @param id аккаунта
 * @param dateData дата дня, в котором должна быть создана новая запись
 */
function genAddIncomePopup(id, dateData) {

    let date = dateData.date;
    let mainTable = document.getElementById("mainTablePlace");
    let incomePopup = document.createElement("dialog");
    incomePopup.id = "incomePopup";
    incomePopup.classList.add("addPopup", "incomePopup");

    let form = document.createElement("form");
    form.id = "form";
    form.className = "formStyle";

    let popupNameFrame = document.createElement("h1");
    popupNameFrame.className = "popupNameFrame";
    popupNameFrame.innerText = "Добавление записи";
    form.append(popupNameFrame);

    //Добавление поля клиент
    let fieldNameCl = document.createElement("p");
    fieldNameCl.className = "fieldName";
    fieldNameCl.innerText = "Имя и фамилия клиента";
    form.append(fieldNameCl);

    let textPlace = document.createElement("div");
    textPlace.className = "textCombo";
    form.append(textPlace);

    let clientField = document.createElement("input");
    clientField.type = "text";
    clientField.id = "clientField"
    clientField.name = "clientField";
    clientField.autocomplete = "off";

    clientField.classList.add("textField");
    textPlace.append(clientField);

    //Добавление поля процедура
    let fieldNameT = document.createElement("p");
    fieldNameT.id = "fieldName";
    fieldNameT.className = "fieldName";
    fieldNameT.innerText = "Процедура";
    form.append(fieldNameT);

    let wrap = document.createElement("div");
    wrap.className = "selectWrap";
    form.append(wrap);

    //TODO выпадающий список исходя из наличия типов процедур мастера в БД
    let serviceTypeField = document.createElement("select");
    let opt1 = document.createElement("option");
    let opt2 = document.createElement("option");
    let opt3 = document.createElement("option");
    let opt4 = document.createElement("option");
    opt1.value = "1";
    opt2.value = "2";
    opt3.value = "3";
    opt4.value = "4";
    opt1.innerText = "Маникюр";
    opt2.innerText = "Педикюр";
    opt3.innerText = "Ремонт";
    opt4.innerText = "Комплекс";
    serviceTypeField.appendChild(opt1);
    serviceTypeField.appendChild(opt2);
    serviceTypeField.appendChild(opt3);
    serviceTypeField.appendChild(opt4);

    serviceTypeField.name = "serviceTypeField";
    serviceTypeField.classList.add("textField");

    //ОЧЕНЬ СЛОЖНАЯ ФУНКЦИЯ С FOR И ЗАПРОСОМ В БАЗУ

    wrap.append(serviceTypeField);

    //Добавление поля сумма
    let fieldNameAmt = document.createElement("p");
    fieldNameAmt.className = "fieldName";
    fieldNameAmt.innerText = "Сумма";
    form.append(fieldNameAmt);

    let amountField = document.createElement("input");
    amountField.id = "amountField";
    amountField.type = "text";
    amountField.autocomplete = "off";
    amountField.classList.add("textField");
    form.append(amountField);

    //Добавление комментария
    let fieldNameCom = document.createElement("p");
    fieldNameCom.className = "fieldName";
    fieldNameCom.innerText = "Комментарий к записи";
    form.append(fieldNameCom);

    let commentField = document.createElement("input");
    commentField.id = "commentField";
    commentField.type = "text";
    commentField.classList.add("textField", "comment");
    form.append(commentField);

    //Добавление кнопки
    let butWrap = document.createElement("div");
    butWrap.className = "buttonWrap";
    form.append(butWrap);

    let submitBut = document.createElement("button");
    submitBut.type = "button";
    submitBut.id = "submitButton";
    submitBut.name = "submitButton";
    submitBut.classList.add("submitButton");
    submitBut.innerText = "Добавить запись";
    butWrap.append(submitBut);

    //Обработка нажатия кнопки подтверждения (отправки формы)

    $(submitBut).click(function () {
        console.log(dateData.date)
        $("#incomePopup").hide();
        $.ajax({
            url: '/pixel',
            type: 'get',
            data: {
                contentType: "application/json; charset=utf-8; encodeURIComponent()",
                dataType: "json",
                command: 'add_note',
                userId: id,
                date: date,
                noteType: 'income',
                noteValue: $("#clientField").val(),
                noteDescription: $("#fieldName").val(),
                amount: $("#amountField").val(),
                commentary: $("#commentField").val()
            },
            success(data) {
                console.log("Sending done")
            }
        })
    })

    //---Добавление скрытого списка с результатом запроса на сервер
    let adviceList = document.createElement("div");
    adviceList.id = "adviceList";
    adviceList.className = "adviceList";
    textPlace.append(adviceList);

    incomePopup.append(form);

    mainTable.append(incomePopup);


    //функция по нажатию клавиши на клавиатуре
    //TODO вынести отдельной функцией для использования в кнопке добавления записи о расходах
    $("#clientField").keyup(function () {
        $.ajax({
            url: '/pixel',
            method: 'get',
            data: {
                contentType: "application/json; charset=utf-8; encodeURIComponent()",
                dataType: "json",
                command: "advice_request",
                //TODO каким-то боком сделать это переменной
                type: 'income',
                userId: id,
                mask: $("#clientField").val()
            },
            success(data) {
                //TODO делаем генерацию поля, в котором будут находиться варианты
                generateAdvices(data);
                $("#adviceList").show();
            }
        })
    });
}


function showAddPopup(id) {
    document.getElementById(id).showModal();
}

function generateAdvices(data) {
    closeAllLists();
    let parsedData = JSON.parse(data)
    for (let i = 0; i < data.length; i++) {
        if (!(parsedData[i] === undefined)) {
            let innerElem = document.createElement("div");
            innerElem.id = "adviceLine"+i;
            innerElem.className = "adviceLine";
            let innerText = document.createElement("p");
            innerText.className = "adviceText";
            innerText.innerHTML = parsedData[i];
            innerElem.append(innerText);
            console.log(innerText.textContent)
            $("#adviceList").append(innerElem);
            //Реакция на клик для каждого из элементов
            $("#adviceLine"+i).click(function () {
                $("#clientField").val(this.innerText);
                $("#adviceList").hide();
            })
        }
    }
}

function closeAllLists() {
    $("#adviceList>div").remove();
}

function setChosenAdvice() {

}

