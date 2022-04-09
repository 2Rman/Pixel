package app.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constant.ConstantCommand.EMPTY_COMMAND;

/**
 * Класс пустой команды
 */
public class EmptyCommand implements Command {

    Logger logger = Logger.getLogger(EmptyCommand.class);

    /**
     * Единственный метод реализующий исполнение "пустой" команды,
     * т.е. случая, когда комнда либо пустая, либо нее пришла вовсе
     *
     * @param request  полученный запрос
     * @param response подготавливаемый ответ
     * @throws IOException исключение ввода-вывода
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("Returning empty command");

        return EMPTY_COMMAND;
    }
}