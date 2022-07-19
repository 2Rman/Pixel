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
import static app.constant.ConstantUtil.*;

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
    DayEntity[] periodData;

    private List<IncomeEntity> incomeEntities;
    private List<ExpenseEntity> expenseEntities;

    /**
     * Метод, собирающий воедино всю информацию, для передачи на сторону клиента. Универсальна с точки зрения
     * необходимого периода для подсчета и "направления" листания (влево-вправо) - direction
     *
     * @param id аккаунта с которого сделан запрос
     * @param direction "направление листания", вычислить предыдущий, следующий или текущий период
     * @param receivedDate принятая дата (исходная точка отсчета)
     * @param receivedPeriod период для подсчета (день, неделя, месяц, год, все время)
     */
    public void collect (String id, String direction, LocalDate receivedDate, String receivedPeriod) {

        LocalDate[] period;

        switch (receivedPeriod.toLowerCase()) {
            case DAY: {
                period =  PeriodDefiner.calculateDay(receivedDate, direction);
                getDAOData(id, period[0], period[1]);
                periodData = tableFactory.buildUserDay(period[0], incomeEntities, expenseEntities);
                break;
            }
            case WEEK: {
                period = PeriodDefiner.calculateWeek(receivedDate, direction);
                break;
            }
            case MONTH: {
                period = PeriodDefiner.calculateMonth(receivedDate, direction);
                generateTable(direction, receivedDate);
                getDAOData(id, period[0], period[1]);
                periodData = tableFactory.buildUserMonth(table, incomeEntities, expenseEntities);
                break;
            }
            case YEAR: {
                period = PeriodDefiner.calculateYear(receivedDate, direction);
                break;
            }
            case INF: {
                period = PeriodDefiner.calculateInfinity();
                break;
            }
            default: {
                period = PeriodDefiner.calculateMonth(LocalDate.now(), direction);
            }
        }

        totalData = new TotalDataCollector(incomeEntities, expenseEntities);

    }

    private void generateTable(String direction, LocalDate receivedDate) {
        if (direction.equals(PREVIOUS)) {
            table = calendarGenerator.getCalendarValues(receivedDate.minusMonths(1));
        } else if (direction.equals(NEXT)) {
            table = calendarGenerator.getCalendarValues(receivedDate.plusMonths(1));
        } else if (direction.equals(CURRENT)){
            table = calendarGenerator.getCalendarValues(receivedDate);
        }
    }

    private void getDAOData(String id, LocalDate start, LocalDate end) {
        incomeEntities = incomeDAO.getPeriodIncome(id, start, end);
        expenseEntities = expenseDAO.getPeriodExpense(id, start, end);
    }
}
