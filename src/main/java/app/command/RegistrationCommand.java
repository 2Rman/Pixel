package app.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constant.ConstantPage.REGISTRATION_PAGE;

/**
 * Класс реализующий команду регистрации, реализует интерфейс команды
 */

public class RegistrationCommand implements Command {

    /**
     * Единственный исполняемый метод перенаправляет пользователя на страницу авторизации
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return REGISTRATION_PAGE;
    }
}
