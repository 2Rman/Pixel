package app.servlet;

import app.command.CommandDefineProcessor;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.constant.ConstantCommand.COMMAND;

/**
 * Сервлет-контроллер. Реализует часть "Controller" MVC-модели
 */
public class ControllerServlet extends HttpServlet {

    Logger logger = Logger.getLogger(ControllerServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processorHandler(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processorHandler(request, response);
    }

    /**
     * Метод для обработки всех поступающих запросов
     */
    private void processorHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info(request.getParameter(COMMAND) + "-command got");

        request.setCharacterEncoding("UTF-8");

        String currentCommand = request.getParameter(COMMAND);

        CommandDefineProcessor commandDefineProcessor = new CommandDefineProcessor();

        if (currentCommand != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(commandDefineProcessor.defineCommand(currentCommand).execute(request, response));
            requestDispatcher.forward(request, response);

        } else {

            logger.info("Return start-page");

            response.sendRedirect("view/index.jsp");
        }
    }
}
