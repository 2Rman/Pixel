package app.command;

import app.dao.ClientDAO;
import app.dao.IncomeDAO;
import app.dao.ServiceTypeDAO;
import app.entity.ClientEntity;
import app.entity.IncomeEntity;
import app.util.RepresentationProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static app.constant.ConstantAttribute.DAY;
import static app.constant.ConstantUtil.*;

public class AddNoteCommand implements Command{

    Logger logger = Logger.getLogger(AddNoteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("Adding new note");

        ClientDAO clientDAO = new ClientDAO();
        ServiceTypeDAO serviceTypeDAO = new ServiceTypeDAO();
        IncomeDAO incomeDAO = new IncomeDAO();
        IncomeEntity incomeEntity;
        ClientEntity clientEntity;
        String idClient;

        String userId = request.getParameter("userId");
        String date = request.getParameter("date");
        String noteType = request.getParameter("noteType");
        String noteValue = request.getParameter("noteValue");
        String noteDescription = request.getParameter("noteDescription");
        String amount = request.getParameter("amount");
        String commentary = request.getParameter("commentary");

        String jsonRefDate;
        String jsonPeriodData;
        String jsonTotalData;
        String[] commonData;
        String jsonCommonData;

        if (noteType.equals(INCOME)) {
            //DO ONE
            logger.info("INCOME adding");
            logger.info("we're adding: " + userId + " " + date + " " + noteValue + " " + noteDescription + " " + amount + " " + commentary);

            String[] splitInitials = castInitials(noteValue);

            if (!clientDAO.isClientInDatabase(splitInitials)) {

                logger.info("Client is missing in DB. ");

                clientEntity = new ClientEntity(splitInitials[0].toUpperCase(), splitInitials[1].toUpperCase());
                clientDAO.create(clientEntity);
            }

            idClient = clientDAO.getIdByName(splitInitials);
            String idServiceType = serviceTypeDAO.getIdByName(noteDescription);

            logger.info(LocalDate.parse(date));

            incomeEntity = new IncomeEntity(LocalDate.parse(date), idServiceType, Integer.parseInt(amount),
                    idClient, userId, commentary);

            incomeDAO.insertNote(incomeEntity);

        } else if (noteType.equals(EXPENSE)) {
            //DO TWO
            logger.info("EXPENSE adding");
        } else {
            // DO NOTHING
            logger.info("NOTHING adding");
        }
        //СБОР ОБНОВЛЕННЫХ ДАННЫХ
//        RepresentationProcessor representationProcessor = new RepresentationProcessor();
//
//        representationProcessor.collect(userId, CURRENT, LocalDate.parse(date), DAY);
//
//        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
//
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //Создание формата даты
//        mapper.setDateFormat(df);                                  //Назначение формата mapper
//
//        jsonRefDate = mapper.writeValueAsString(representationProcessor.getPeriodData()[0].getDate());
//        jsonPeriodData = mapper.writeValueAsString(representationProcessor.getPeriodData());
//        jsonTotalData = mapper.writeValueAsString(representationProcessor.getTotalData().toList());
//
//        commonData = new String[]{jsonRefDate, jsonPeriodData, jsonTotalData};
//        jsonCommonData = mapper.writeValueAsString(commonData);
//
//        logger.info(jsonRefDate);
//        logger.info(jsonPeriodData);
//        logger.info(jsonTotalData);
//
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(jsonCommonData);

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

