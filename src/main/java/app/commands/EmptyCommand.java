package app.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constant.ConstantCommand.EMPTY_COMMAND;

/**
 * Класс пустой команды
 */

public class EmptyCommand implements Command {

    /**
     * Единственный метод реализующий исполнение "пустой" команды,
     * т.е. случая, когда комнда либо пустая, либо нее пришла вовсе
     *
     * @param request
     * @param response
     * @throws IOException
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.sendRedirect(EMPTY_COMMAND);
    }
}