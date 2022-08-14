function generateDay(dayData, id) {

    console.log("dayGenerator is working")

    let dataIncomes = dayData[0]["incomeList"];
    let dataExpenses = dayData[0]["expenseList"];

    //TEST
    console.log(dayData[0].date);

    //TEST end

    let tablePlace = document.getElementById('mainTablePlace');

    let dayCommon = document.createElement("div");
    dayCommon.id = "middleTable";
    dayCommon.className = "day_common";
    tablePlace.prepend(dayCommon);

    let subTableIncome = document.createElement("div");
    subTableIncome.className = "sub-table";
    dayCommon.prepend(subTableIncome);

    let subTableNameInc = document.createElement("p");
    subTableNameInc.className = "sub-table_name";
    subTableNameInc.innerText = "Записи";
    subTableIncome.prepend(subTableNameInc);

    let dayIncomes = document.createElement("div");
    dayIncomes.id = "dayIncomes";
    dayIncomes.className = "column_place";
    subTableIncome.append(dayIncomes);

    let incomes = document.createElement("div");
    incomes.id = "incomes";
    incomes.classList.add("notes", "incomes");
    dayIncomes.append(incomes);

    let types = document.createElement("div");
    types.id = "types";
    types.className = "types";
    dayIncomes.append(types);

    let incomeAmount = document.createElement("div");
    incomeAmount.id = "incomeAmount";
    incomeAmount.className = "amount";
    dayIncomes.append(incomeAmount);

    dataIncomes.forEach((item) => {
        let noteInc = document.createElement("p");
        noteInc.className = "note";
        noteInc.innerText = item["client"]; //-переменная здесь
        incomes.append(noteInc);

        let descType = document.createElement("p");
        descType.className = "desc_type";
        descType.innerText = item["noteType"].substr(0,1).toUpperCase(); //-переменная--Берем первый символ строки, поднимаем в КАПС
        types.append(descType);

        let descAmountInc = document.createElement("p");
        descAmountInc.className = "desc_amount";
        descAmountInc.innerText = item["amount"];
        incomeAmount.append(descAmountInc);//-переменная здесь
    })

    let buttonPlaceInc = document.createElement("div");
    buttonPlaceInc.className = "button_place";
    subTableIncome.append(buttonPlaceInc);

    let greenButton = document.createElement("button");
    greenButton.classList.add("green", "button");
    greenButton.innerHTML = "Добавить запись";
    genAddIncomePopup(id, dayData[0].date);
    greenButton.onclick = function() {
        showAddPopup("incomePopup");
    }
    buttonPlaceInc.append(greenButton);
    // Конец первой таблицы

    // Начало второй таблицы
    let subTableExpense = document.createElement("div");
    subTableExpense.className = "sub-table";
    dayCommon.append(subTableExpense);

    let subTableNameExp = document.createElement("p");
    subTableNameExp.className = "sub-table_name";
    subTableNameExp.innerText = "Расходы";
    subTableExpense.prepend(subTableNameExp);

    let dayExpenses = document.createElement("div");
    dayExpenses.id = "dayExpenses";
    dayExpenses.className = "column_place";
    subTableExpense.append(dayExpenses);

    let expenses = document.createElement("div");
    expenses.id = "expenses";
    expenses.classList.add("notes", "expenses");
    dayExpenses.append(expenses);

    let expenseAmount = document.createElement("div");
    expenseAmount.id = "incomeAmount";
    expenseAmount.className = "amount";
    dayExpenses.append(expenseAmount);

    //-------------------------foreach
    dataExpenses.forEach((item) => {
        let noteExp = document.createElement("p");
        noteExp.className = "note";
        noteExp.innerText = item["noteType"]; //-----------переменная здесь
        expenses.append(noteExp);

        let descAmountExp = document.createElement("p");
        descAmountExp.className = "desc_amount";
        descAmountExp.innerText = item["amount"];
        expenseAmount.append(descAmountExp);//-------------переменная здесь
    })
    //-------------------------foreach конец

    let buttonPlaceExp = document.createElement("div");
    buttonPlaceExp.className = "button_place";
    subTableExpense.append(buttonPlaceExp);

    let redButton = document.createElement("button");
    redButton.classList.add("red", "button");
    redButton.innerHTML = "Добавить запись";
    genAddExpensePopup(id, dayData[0].date);
    redButton.onclick = function() {
        showAddPopup("expensePopup");
    }
    buttonPlaceExp.append(redButton);

}
