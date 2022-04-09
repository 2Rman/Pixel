package app.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constant.ConstantPage.REGISTRATION_PAGE;

/**
 * Класс реализующий команду регистрации, реализует интерфейс команды
 */

public class RegistrationCommand implements Command {

    Logger logger = Logger.getLogger(RegistrationCommand.class);

    /**
     * Единственный исполняемый метод перенаправляет пользователя на страницу авторизации
     *
     * @param request  полученный запрос
     * @param response подготавливаемы ответ
     * @throws IOException исключение ввода-вывода
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("Returning REGISTRATION_PAGE");

        return REGISTRATION_PAGE;
    }
}

