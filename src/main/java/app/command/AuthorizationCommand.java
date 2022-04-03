package app.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constant.ConstantPage.AUTHORIZATION_PAGE;

/**
 * Класс реализующий команду авторизации, реализует интерфейс команды
 */

public class AuthorizationCommand implements Command {

    /**
     * Единственный исполняемый метод перенаправляет пользователя на страницу авторизации
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return AUTHORIZATION_PAGE;
    }
}