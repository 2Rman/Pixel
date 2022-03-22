package app.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constant.ConstantPage.REGISTRATION_PAGE;

public class RegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.sendRedirect(REGISTRATION_PAGE);
        
    }
}
