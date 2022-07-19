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
 * и сопоставление этих данных с датами, сгенерированной таблицы-календаря.
 * TODO составление "таблиц" день, неделя, год
 */
public class TableFactory {

    Logger logger = Logger.getLogger(TableFactory.class);

    /**
     * Создание "таблицы-дня", заполненной сущностью дня на основе переданных данных.
     * Возвращает массив из одного дня, т.к. другие классы, использующие данные, полученные
     * с помощью данного метода ожидают одномерный массив
     *
     * @param date дата, за которую необходимо собрать данные
     * @param incomes список записей(доходы) за день (получено из БД)
     * @param expenses список затрат(расходы) за день (получено из БД)
     * @return возвращает массив дней, но по сути, массив, состоящий из одного дня
     */
    public DayEntity[] buildUserDay(LocalDate date, List<IncomeEntity> incomes, List<ExpenseEntity> expenses) {

        logger.info("Filling day with data from DB");

        DayEntity[] dayEntity = new DayEntity[1];

        if (!(incomes.isEmpty()) || !(expenses.isEmpty())) {
            for (IncomeEntity income : incomes) {
                if (date.equals(income.getDate())) {
                    if (dayEntity[0] == null) {
                        dayEntity[0] = new DayEntity(date, income);
                    } else if (dayEntity[0].getDate().equals(date)) {
                        dayEntity[0].getIncomeList().add(income);
                    }
                }
            }
            for (ExpenseEntity expense : expenses) {
                if (date.equals((expense.getDate()))) {
                    if (dayEntity[0] == null) {
                        dayEntity[0] = new DayEntity(date, expense);
                    } else if (dayEntity[0].getDate().equals(date)) {
                        dayEntity[0].getExpenseList().add(expense);
                    }
                }
            }
        } else {
            dayEntity[0] = new DayEntity(date);
        }
        return dayEntity;

    }

    /**
     * Создание новой "таблицы-месяца", заполненной сущностями днями на основе переданных данных
     *
     * @param table    таблицы с датами
     * @param incomes  данные из БД о полученных доходах за период
     * @param expenses данные из БД о расходах за период
     * @return двумерный массив сущностей DayEntity
     */
    public DayEntity[] buildUserMonth(LocalDate[][] table, List<IncomeEntity> incomes, List<ExpenseEntity> expenses) {

        logger.info("Filling month-table with data from DB");

        DayEntity[] monthData = new DayEntity[table.length * table[0].length];
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
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < 7; j++) {
                if (dayMap.containsKey(table[i][j])) {
                    monthData[i * table[0].length + j] = dayMap.get(table[i][j]);
                } else {
                    monthData[i * table[0].length + j] = new DayEntity(table[i][j]);
                }
            }
        }

        /*todo ДЛЯ ТЕСТА*/
        for (DayEntity monthDatum : monthData) {
            System.out.println(monthDatum.getDate());
        }
        /*КОНЕЦ ТЕСТА*/
        return monthData;
    }
}

