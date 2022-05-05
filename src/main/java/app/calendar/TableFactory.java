package app.calendar;

import app.entity.DayEntity;
import app.entity.NoteExpenseEntity;
import app.entity.NoteIncomeEntity;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * По сути главнейший механизм сборки таблицы-календаря.
 * Здесь осуществляется создание сущностей дней на основе полученных данных из БД
 * и сопоставление этих данных с датами, сгенерированной таблицы с датами.
 */
public class TableFactory {

    Logger logger = Logger.getLogger(TableFactory.class);

    /**
     * Создание новой таблицы, заполненной сущностями днями на основе переданных данных
     *
     * @param table    таблицы с датами
     * @param incomes  данные из БД о полученных доходах за период
     * @param expenses данные из БД о полученных расходах за период
     */
    public DayEntity[][] BuildUserMonth(LocalDate[][] table, List<NoteIncomeEntity> incomes, List<NoteExpenseEntity> expenses) {

        logger.info("Filling month-table with data from DB");

        DayEntity[][] monthData = new DayEntity[table.length][7];
        HashMap<LocalDate, DayEntity> dayMap = new HashMap<>();

        //TESTS
        int currentMonth = 3;
//        int currentMonth = LocalDate.now().getMonthValue();

        //Пробегаемся по таблице с датами, совмещаем даты с "событиями дня" из БД, создаем мапу дней
        for (LocalDate[] week : table) {
            for (LocalDate day : week) {
                //Цикл для заполнения дня данными по ДОХОДАМ------------------------------------------------------------
                for (NoteIncomeEntity income : incomes) {
                    if (day.isEqual(income.getDate())) {
                        if (!dayMap.containsKey(day)) {
                            dayMap.put(day, new DayEntity(day, income.getTypeNote(), income.getAmount(), income.getClient(), income.getCommentary()));
                        } else if (dayMap.containsKey(day)) {
                            DayEntity dayForSupplement = dayMap.get(day);

                            dayForSupplement.getServices().add(income.getTypeNote());
                            dayForSupplement.getIncomes().add(income.getAmount());
                            dayForSupplement.getClients().add(income.getClient());
                            dayForSupplement.getCommentaryIncomes().add(income.getCommentary());

                            dayMap.replace(day, dayForSupplement);
                        }
                    } else if (!dayMap.containsKey(day)) {
                        dayMap.put(day, new DayEntity(day));
                    }
                }
                //Цикл для заполнения дня данными по РАСХОДАМ-----------------------------------------------------------
                for (NoteExpenseEntity expense : expenses) {
                    if (day.isEqual(expense.getDate())) {
                        if (!dayMap.containsKey(day)) {
                            dayMap.put(day, new DayEntity(day, expense.getTypeNote(), expense.getAmount(), expense.getCommentary()));
                        } else if (dayMap.containsKey(day)) {
                            DayEntity dayForSupplement = dayMap.get(day);

                            dayForSupplement.getExpenseNames().add(expense.getTypeNote());
                            dayForSupplement.getExpenses().add(expense.getAmount());
                            dayForSupplement.getCommentaryExpenses().add(expense.getCommentary());

                            dayMap.replace(day, dayForSupplement);
                        }
                    } else if (!dayMap.containsKey(day)) {
                        dayMap.put(day, new DayEntity(day));
                    }
                }
            }
        }

        for (int i = 0; i < monthData.length; i++) {
            for (int j = 0; j < 7; j++) {
                monthData[i][j] = dayMap.get(table[i][j]);
            }
        }

        /*ДЛЯ ТЕСТА*/
        for (DayEntity[] monthDatum : monthData) {
            for (int j = 0; j < 7; j++) {
                System.out.println(monthDatum[j].getDate());
                System.out.println(monthDatum[j].getServices());
                System.out.println(monthDatum[j].getIncomes());
                System.out.println(monthDatum[j].getClients());
            }
        }
        return monthData;
    }
}

