package app.util;

import app.dao.ExpenseDAO;
import app.dao.IncomeDAO;
import app.entity.DayEntity;
import app.entity.ExpenseEntity;
import app.entity.IncomeEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import static app.constant.ConstantAttribute.*;

/**
 * Утилитарный класс, предназначенный для сбора информации перед отправкой на клиентскую сторону
 */
@Data
public class RepresentationProcessor {

    IncomeDAO incomeDAO = new IncomeDAO();
    ExpenseDAO expenseDAO = new ExpenseDAO();
    AccountCalendarGenerator calendarGenerator = new AccountCalendarGenerator();
    TableFactory tableFactory = new TableFactory();
    TotalDataCollector totalData;

    LocalDate[][] table;
    DayEntity[][] periodData;

    /**
     * Метод, собирающий воедино всю информацию, для передачи на сторону клиента. Универсальна с точки зрения
     * необходимого периода для подсчета
     *
     * @param id аккаунта с которого сделан запрос
     * @param receivedDate принятая дата (исходная точка отсчета)
     * @param receivedPeriod период для подсчета (день, неделя, месяц, год, все время)
     */
    public void collect (String id, LocalDate receivedDate, String receivedPeriod) {

        LocalDate[] period;

        switch (receivedPeriod.toLowerCase()) {
            case DAY: {
                period =  PeriodDefiner.calculateDay(receivedDate);
                break;
            }
            case WEEK: {
                period = PeriodDefiner.calculateWeek(receivedDate);
                break;
            }
            case MONTH: {
                period = PeriodDefiner.calculateMonth(receivedDate);
                break;
            }
            case YEAR: {
                period = PeriodDefiner.calculateYear(receivedDate);
                break;
            }
            case INF: {
                period = PeriodDefiner.calculateInfinity();
                break;
            }
            default: {
                period = PeriodDefiner.calculateMonth(LocalDate.now());
            }
        }

        List<IncomeEntity> incomeEntities = incomeDAO.getPeriodIncome(id, period[0], period[1]);
        List<ExpenseEntity> expenseEntities = expenseDAO.getPeriodExpense(id, period[0], period[1]);

        table = calendarGenerator.getCalendarValues(receivedDate);
        periodData = tableFactory.buildUserMonth(table, incomeEntities, expenseEntities);

        totalData = new TotalDataCollector(incomeEntities, expenseEntities);
    }
}
