package app.command;

import app.dao.AccountDAO;
import app.entity.Account;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static app.constant.ConstantAttribute.*;
import static app.constant.ConstantPage.ACCOUNT_MAIN_PAGE;

/**
 * Класс реализующий команду регистрации
 */
public class RegisterCommand implements Command {

    Logger logger = Logger.getLogger(RegisterCommand.class);

    /**
     * Обработка команды регистрации. Включает создание сущности аккаунта, заполнение сущности данными,
     * полученными из запроса и передача сущности accountDAO для внесения данных в БД
     *
     * @param request  принятый запрос
     * @param response подготавливаемы ответ сервера
     * @return Возвращает главную страницу пользователя
     * @throws IOException исключение ввода-вывода
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("Registration procedure started");

        Account account = new Account();
        AccountDAO accountDAO = new AccountDAO();

        logger.info("Getting session from request-context");

        HttpSession session = request.getSession();

        account.setName(request.getParameter(USERNAME));
        account.setPhone(request.getParameter(PHONE_NUMBER));
        account.setPassword(request.getParameter(PASSWORD));

        accountDAO.create(account);
        account = accountDAO.getByPhoneNumber(request.getParameter(PHONE_NUMBER));

        session.setAttribute("id", account.getAccountId());

        logger.info("Session got id " + account.getAccountId());
        logger.info("Returning ACCOUNT_MAIN_PAGE");

        return ACCOUNT_MAIN_PAGE;
    }
}
