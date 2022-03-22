package app.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constant.ConstantPage.AUTHORIZATION_PAGE;

public class AuthorizationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.sendRedirect(AUTHORIZATION_PAGE);

    }
}
