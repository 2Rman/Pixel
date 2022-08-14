/**
 * Добавление popup для внесения новой записи о расходе в БД
 *
 * @param id аккаунта
 * @param date дата дня, в котором должна быть создана новая запись
 */
function genAddExpensePopup(id, date) {

    let mainTable = document.getElementById("mainTablePlace");
    let expensePopup = document.createElement("dialog");
    expensePopup.id = "expensePopup";
    expensePopup.classList.add("addPopup", "expensePopup");

    let form = document.createElement("form");
    form.id = "form";
    form.className = "formStyle";

    let popupNameFrame = document.createElement("h1");
    popupNameFrame.className = "popupNameFrame";
    popupNameFrame.innerText = "Добавление записи";
    form.append(popupNameFrame);

    //Добавление поля ип расхода
    let fieldNameExpense = document.createElement("p");
    fieldNameExpense.className = "fieldName";
    fieldNameExpense.innerText = "На что потрачено";
    form.append(fieldNameExpense);

    let textPlace = document.createElement("div");
    textPlace.className = "textCombo";
    form.append(textPlace);

    let expenseField = document.createElement("input");
    expenseField.type = "text";
    expenseField.id = "expenseField"
    expenseField.name = "expenseField";
    expenseField.autocomplete = "off";

    expenseField.classList.add("textField");
    textPlace.append(expenseField);

    //Добавление поля сумма
    let fieldNameAmt = document.createElement("p");
    fieldNameAmt.className = "fieldName";
    fieldNameAmt.innerText = "Сумма";
    form.append(fieldNameAmt);

    let amountExpenseField = document.createElement("input");
    amountExpenseField.id = "amountExpenseField";
    amountExpenseField.type = "text";
    amountExpenseField.autocomplete = "off";
    amountExpenseField.classList.add("textField");
    form.append(amountExpenseField);

    //Добавление комментария
    let fieldNameCom = document.createElement("p");
    fieldNameCom.className = "fieldName";
    fieldNameCom.innerText = "Комментарий";
    form.append(fieldNameCom);

    let commentExpenseField = document.createElement("input");
    commentExpenseField.id = "commentExpenseField";
    commentExpenseField.type = "text";
    commentExpenseField.classList.add("textField", "comment");
    form.append(commentExpenseField);

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
        expensePopup.classList.add("backdrop");
        $.ajax({
            url: '/pixel',
            type: 'get',
            data: {
                contentType: "application/json; charset=utf-8; encodeURIComponent()",
                dataType: "json",
                command: 'add_note',
                userId: id,
                date: date,
                noteType: 'expense',
                noteValue: $("#expenseField").val(),
                amount: $("#amountExpenseField").val(),
                commentary: $("#commentExpenseField").val()
            },
            success(data) {
                console.log("Sending done")
                changePeriod("current", id, date, "day");
            }
        })
        $("#expensePopup").remove();
    })
    let adviceExpenseList = document.createElement("div");
    adviceExpenseList.id = "adviceExpenseList";
    adviceExpenseList.className = "adviceList";
    textPlace.append(adviceExpenseList);

    expensePopup.append(form);

    mainTable.append(expensePopup);

    $("#expenseField").keyup(function () {
        $.ajax({
            url: '/pixel',
            method: 'get',
            data: {
                contentType: "application/json; charset=utf-8; encodeURIComponent()",
                dataType: "json",
                command: "advice_request",
                //TODO каким-то боком сделать это переменной
                type: 'expense',
                userId: id,
                mask: $("#expenseField").val()
            },
            success(data) {
                generateExpenseAdvices(data);
                console.log(data);
                $("#adviceExpenseList").show();
            }
        })
    });
}

function generateExpenseAdvices(data) {
    closeAllLists();
    let parsedExpenseData = JSON.parse(data)
    for (let i = 0; i < data.length; i++) {
        if (!(parsedExpenseData[i] === undefined)) {
            let innerExpenseElem = document.createElement("div");
            innerExpenseElem.id = "adviceExpenseLine"+i;
            innerExpenseElem.className = "adviceLine";
            let innerExpenseText = document.createElement("p");
            innerExpenseText.className = "adviceText";
            innerExpenseText.innerHTML = parsedExpenseData[i];
            innerExpenseElem.append(innerExpenseText);
            $("#adviceExpenseList").append(innerExpenseElem);
            //Реакция на клик для каждого из элементов
            $("#adviceExpenseLine"+i).click(function () {
                $("#expenseField").val(this.innerText);
                $("#adviceExpenseList").hide();
            })
        }
    }
}