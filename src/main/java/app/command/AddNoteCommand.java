package app.command;

import app.dao.*;
import app.entity.ClientEntity;
import app.entity.ExpenseEntity;
import app.entity.ExpenseTypeEntity;
import app.entity.IncomeEntity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static app.constant.ConstantUtil.EXPENSE;
import static app.constant.ConstantUtil.INCOME;

public class AddNoteCommand implements Command {

    Logger logger = Logger.getLogger(AddNoteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("Adding new note");

        ClientEntity clientEntity;
        IncomeEntity incomeEntity;
        ExpenseEntity expenseEntity;
        IncomeDAO incomeDAO = new IncomeDAO();
        ExpenseDAO expenseDAO = new ExpenseDAO();
        ClientDAO clientDAO = new ClientDAO();
        ServiceTypeDAO serviceTypeDAO = new ServiceTypeDAO();
        ExpenseTypeDAO expenseTypeDAO = new ExpenseTypeDAO();

        String idClient;
        String idExpenseType;

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

            if (!clientDAO.isClientInDatabase(userId, splitInitials)) {

                logger.info("Client missed in DB");

                clientEntity = new ClientEntity(splitInitials[0].toUpperCase(), splitInitials[1].toUpperCase());
                clientDAO.create(clientEntity);
                idClient = clientEntity.getIdClient();
            } else {
                idClient = clientDAO.getIdByName(userId, splitInitials);
            }

            String idServiceType = serviceTypeDAO.getIdByName(userId, noteDescription);

            incomeEntity = new IncomeEntity(LocalDate.parse(date), idServiceType, amount,
                    idClient, userId, commentary);

            incomeDAO.insertNote(incomeEntity);

        } else if (noteType.equals(EXPENSE)) {
            //DO TWO
            logger.info("EXPENSE adding");
            logger.info("we're adding: " + userId + " " + date + " " + noteValue + " " + amount + " " + commentary);

            if (!expenseTypeDAO.isExpenseTypeInDatabase(userId, noteValue.trim())) {

                logger.info(noteValue + " is't in Database. Creating new expense-type");

                ExpenseTypeEntity expenseTypeEntity = new ExpenseTypeEntity(noteValue.trim());
                expenseTypeDAO.create(expenseTypeEntity);
                idExpenseType = expenseTypeEntity.getIdExpenseType();
            } else {
                idExpenseType = expenseTypeDAO.getIdByName(userId, noteValue);
            }

            expenseEntity = new ExpenseEntity(LocalDate.parse(date), idExpenseType, amount,
                    userId, commentary);

            expenseDAO.insertNote(expenseEntity);
        } else {
            // DO NOTHING
            logger.info("NOTHING adding");
        }

        return "Insert procedure done";
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

