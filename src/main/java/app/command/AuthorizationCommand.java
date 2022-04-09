package app.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constant.ConstantPage.AUTHORIZATION_PAGE;

/**
 * Класс реализующий команду авторизации, реализует интерфейс команды
 */
public class AuthorizationCommand implements Command {

    Logger logger = Logger.getLogger(AuthorizationCommand.class);

    /**
     * Единственный исполняемый метод перенаправляет пользователя на страницу авторизации
     *
     * @param request  Полученный запрос
     * @param response Подготавливаемый ответ
     * @throws IOException исключение ввода-вывода
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("Returning Authorization page");

        return AUTHORIZATION_PAGE;
    }
}
