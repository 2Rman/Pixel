package app.util;

import app.entity.DayEntity;
import app.entity.ExpenseEntity;
import app.entity.IncomeEntity;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * По сути главнейший механизм сборки таблицы-календаря.
 * Здесь осуществляется создание сущностей дней на основе полученных данных из БД
 * и сопоставление этих данных с датами, сгенерированной таблицы с датами.
 * TODO составление "таблиц" день, неделя, год
 */
public class TableFactory {

    Logger logger = Logger.getLogger(TableFactory.class);

    /**
     * Создание новой "таблицы-месяца", заполненной сущностями днями на основе переданных данных
     *
     * @param table    таблицы с датами
     * @param incomes  данные из БД о полученных доходах за период
     * @param expenses данные из БД о расходах за период
     * @return двумерный массив сущностей DayEntity
     */
    public DayEntity[][] buildUserMonth(LocalDate[][] table, List<IncomeEntity> incomes, List<ExpenseEntity> expenses) {

        logger.info("Filling month-table with data from DB");

        DayEntity[][] monthData = new DayEntity[table.length][7];
        HashMap<LocalDate, DayEntity> dayMap = new HashMap<>();

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < 7; j++) {
                LocalDate generatedDay = table[i][j];
                for (IncomeEntity income : incomes) {
                    if (generatedDay.isEqual(income.getDate())) {
                        if (!dayMap.containsKey(generatedDay)) {
                            dayMap.put(generatedDay, new DayEntity(generatedDay, income));
                        } else if (dayMap.containsKey(generatedDay)) {
                            DayEntity tempDay = dayMap.get(generatedDay);
                            tempDay.getIncomeList().add(income);
                        }
                    }
                }
                for (ExpenseEntity expense : expenses) {
                    if (generatedDay.isEqual(expense.getDate())) {
                        if (!dayMap.containsKey(generatedDay)) {
                            dayMap.put(generatedDay, new DayEntity(generatedDay, expense));
                        } else if (dayMap.containsKey(generatedDay)) {
                            DayEntity tempDay = dayMap.get(generatedDay);
                            tempDay.getExpenseList().add(expense);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < monthData.length; i++) {
            for (int j = 0; j < 7; j++) {
                if (dayMap.containsKey(table[i][j])) {
                    monthData[i][j] = dayMap.get(table[i][j]);
                } else {
                    monthData[i][j] = new DayEntity(table[i][j]);
                }
            }
        }

        /*ДЛЯ ТЕСТА*/
        for (DayEntity[] monthDatum : monthData) {
            for (int j = 0; j < 7; j++) {
                System.out.println(monthDatum[j].getDate());
            }
        }
        /*КОНЕЦ ТЕСТА*/
        return monthData;
    }
}

