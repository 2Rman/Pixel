package app.command;

import app.calendar.AccountCalendarGenerator;
import app.calendar.TableFactory;
import app.dao.AccountDAO;
import app.dao.MonthDAO;
import app.entity.Account;
import app.entity.NoteExpenseEntity;
import app.entity.NoteIncomeEntity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static app.constant.ConstantAttribute.PHONE_NUMBER;
import static app.constant.ConstantPage.ACCOUNT_MAIN_PAGE;

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

        Account account = new AccountDAO().getByPhoneNumber(request.getParameter(PHONE_NUMBER));

        HttpSession session = request.getSession();

        session.setAttribute("id", account.getAccountId());
        //ВЫТЯГИВАЕМ ОБЪЕКТ ПО ID
//        session.setAttribute("phone_number", account.getPhone());
//        session.setAttribute("username", account.getName());
//        session.setAttribute("savings", account.getSavings());

        logger.info("User '" + account.getName() + "' successfully logged in");
        logger.info("Returning ACCOUNT_MAIN_PAGE");

        //Делается запрос в БД, запускается по сути механизм построения таблиц
        //TEST
        MonthDAO monthDAO = new MonthDAO();
        AccountCalendarGenerator generator = new AccountCalendarGenerator();
        TableFactory factory = new TableFactory();

        List<NoteIncomeEntity> notesInc = monthDAO.getMonthIncome(account.getAccountId(), LocalDate.of(2021,3, 1), LocalDate.of(2021, 3, 31));
        List<NoteExpenseEntity> notesExp = monthDAO.getMonthExpense(account.getAccountId(), LocalDate.of(2021,3, 1), LocalDate.of(2021, 3, 31));
        LocalDate[][] table = generator.getCalendarValues();
        factory.BuildUserMonth(table, notesInc, notesExp);
        //КОНЦОВКА ТЕСТА

        return ACCOUNT_MAIN_PAGE;
    }
}
