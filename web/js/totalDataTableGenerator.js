function generateTotalData(pData) {
    let dataPlace = document.getElementById("totalDataPlace");

    let infoTable = document.createElement("div");
    infoTable.id = "infoTable";
    infoTable.className = "info-table";
    dataPlace.prepend(infoTable);

    $.each(pData, function (index, data) {

        let infoRow = document.createElement("div");
        infoRow.className = "info-row";
        infoTable.append(infoRow);

        let textInfoTable = document.createElement("p");
        textInfoTable.className = "text-info-table";
        textInfoTable.innerText = TOTAL_TABLE_ITEMS[index];

        infoRow.append(textInfoTable);

        let valueInfoTable = document.createElement("p");
        valueInfoTable.className = "value-info-table";
        valueInfoTable.innerText = data;
        infoRow.append(valueInfoTable);
    })
}
