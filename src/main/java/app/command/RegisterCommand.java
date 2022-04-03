package app.command;

import app.dao.AccountDAO;
import app.entity.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static app.constant.ConstantAttribute.*;
import static app.constant.ConstantPage.ACCOUNT_MAIN_PAGE;

public class RegisterCommand implements Command {

    /**
     * Обработка команды регистрации. Включает создание сущности аккаунта, заполнение сущности данными,
     * полученными из запроса и передача сущности accountDAO для внесения данных в БД
     *
     * @param request принятый запрос
     * @param response подготавливаемы ответ сервера
     * @return Возвращает главную страницу пользователя
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Account account = new Account();
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();

        account.setName(request.getParameter(USERNAME));
        account.setPhone(request.getParameter(PHONE));
        account.setPassword(request.getParameter(PASSWORD));

        accountDAO.create(account);
        account = accountDAO.getByNumber(Integer.parseInt(request.getParameter(PHONE)));

        session.setAttribute("id", account.getAccountId());

        return ACCOUNT_MAIN_PAGE;
    }
}
