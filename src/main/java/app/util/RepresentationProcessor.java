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
    DayEntity[][] periodData;

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
                break;
            }
            case WEEK: {
                period = PeriodDefiner.calculateWeek(receivedDate, direction);
                break;
            }
            case MONTH: {
                period = PeriodDefiner.calculateMonth(receivedDate, direction);
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

        List<IncomeEntity> incomeEntities = incomeDAO.getPeriodIncome(id, period[0], period[1]);
        List<ExpenseEntity> expenseEntities = expenseDAO.getPeriodExpense(id, period[0], period[1]);

        //TODO поменять на нормальное представление (а что это значит... уже хз)
        if (direction.equals(PREVIOUS)) {
            table = calendarGenerator.getCalendarValues(receivedDate.minusMonths(1));
        } else if (direction.equals(NEXT)) {
            table = calendarGenerator.getCalendarValues(receivedDate.plusMonths(1));
        } else if (direction.equals(CURRENT)){
            table = calendarGenerator.getCalendarValues(receivedDate);
        }

        periodData = tableFactory.buildUserMonth(table, incomeEntities, expenseEntities);

        totalData = new TotalDataCollector(incomeEntities, expenseEntities);

    }
}
