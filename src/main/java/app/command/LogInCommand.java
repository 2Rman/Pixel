package app.command;

import app.entity.DayEntity;
import app.util.AccountCalendarGenerator;
import app.util.TotalDataCollector;
import app.util.RepresentationProcessor;
import app.util.TableFactory;
import app.dao.AccountDAO;
import app.dao.ExpenseDAO;
import app.dao.IncomeDAO;
import app.entity.AccountEntity;
import app.entity.ExpenseEntity;
import app.entity.IncomeEntity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static app.constant.ConstantAttribute.MONTH;
import static app.constant.ConstantAttribute.PHONE_NUMBER;
import static app.constant.ConstantPage.ACCOUNT_MAIN_PAGE;
import static app.constant.ConstantUtil.MONTH_LIST;

/**
 * Класс реализующий выполнение команды авторизации
 */
public class LogInCommand implements Command {

    Logger logger = Logger.getLogger(LogInCommand.class);

    /**
     * Обработка команды авторизации. Включает:
     * - поиск в БД существующей информации о пользователе по введенному номеру телефона
     * - создание экземпляра аккаунта и заполнение его информацией из БД
     * - инициализация сессии, добавление атрибутов (инфо об аккаунте)
     * <p>
     * TODO
     *  - процедура проверки правильности введенного пароля
     *  - возможно, вынесение инициализации сессии в отдельный метод
     *
     * @param request  полученный запрос
     * @param response подготавливаемый ответ
     * @return возвращает главную страницу пользователя
     * @throws IOException исключение ввода-вывода
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("Log_in procedure started");

        AccountEntity account = new AccountDAO().getByPhoneNumber(request.getParameter(PHONE_NUMBER));

        HttpSession session = request.getSession();

        session.setAttribute("id", account.getIdAccount());
        //ВЫТЯГИВАЕМ ОБЪЕКТ ПО ID
//        session.setAttribute("phone_number", account.getPhone());
        session.setAttribute("username", account.getName());
//        session.setAttribute("savings", account.getSavings());

        logger.info("User '" + account.getName() + "' successfully logged in");
        logger.info("Returning ACCOUNT_MAIN_PAGE");

        //Делается запрос в БД, запускается по сути процедура построения таблиц
        //TEST

        //ПРЕДПОЛОЖИМ, что это было получено со стороны клиента:
        LocalDate testDate = LocalDate.of(2021,3,8);
        String testPeriod = MONTH;
        //Получили, теперь считаем:
        RepresentationProcessor repProcessor = new RepresentationProcessor();

        repProcessor.collect(account.getIdAccount(), testDate, testPeriod);

        request.setAttribute("monthList", MONTH_LIST);
        request.setAttribute("periodData", repProcessor.getPeriodData());
        request.setAttribute("totalData", repProcessor.getTotalData().toMap());

        //КОНЦОВКА ТЕСТА
        return ACCOUNT_MAIN_PAGE;
    }
}
