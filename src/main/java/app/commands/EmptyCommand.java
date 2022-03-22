package app.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constant.ConstantCommand.EMPTY_COMMAND;

public class EmptyCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.sendRedirect(EMPTY_COMMAND);
    }
}