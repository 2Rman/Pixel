package app.command;

import app.dao.ClientDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static app.constant.ConstantUtil.EXPENSE;
import static app.constant.ConstantUtil.INCOME;

public class AddNoteCommand implements Command{

    Logger logger = Logger.getLogger(AddNoteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("Adding new note");

        ClientDAO clientDAO = new ClientDAO();
        String userId = request.getParameter("userId");
        String date = request.getParameter("date");
        String noteType = request.getParameter("noteType");
        String noteValue = request.getParameter("noteValue");
        String noteDescription = request.getParameter("noteDescription");
        String amount = request.getParameter("amount");
        String commentary = request.getParameter("commentary");

        if (noteType.equals(INCOME)) {
            //DO ONE
            logger.info("INCOME adding");
            logger.info("we're adding: " + userId + " " + date + " " + noteValue + " " + noteDescription + " " + amount + " " + commentary);

            String[] splitInitials = castInitials(noteValue);

            if (clientDAO.isClientInDatabase(splitInitials)) {
//            String clientId = clientDAO.getIdByName(splitInitials);
            } else {

            }

        } else if (noteType.equals(EXPENSE)) {
            //DO TWO
            logger.info("EXPENSE adding");
        } else {
            // DO NOTHING
            logger.info("NOTHING adding");
        }
        return "done";
    }

    /**
     * Метод для определения имеющихся пробелов и разделения имени и фамилии (условно) на отдельные составляющие
     *
     * @param initials имя и фамилия клиента одной строкой
     * @return массив, содержащий имя и фамилию клиента в качестве элементов
     */
    private String[] castInitials(String initials) {

        initials = initials.trim();
        String[] result = new String[2];

        if (initials.contains(" ")) {
            String upperCase = initials.toUpperCase();
            result = upperCase.split(" ");
            result[0] = result[0].trim();
            result[1] = result[1].trim();
        } else {
            result[0] = initials;
            result[1] = " ";
        }
        return result;
    }
}

