package app.command;

import app.util.RepresentationProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static app.constant.ConstantAttribute.DAY;
import static app.constant.ConstantAttribute.MONTH;

public class ChangePeriodCommand implements Command{

    Logger logger = Logger.getLogger(ChangePeriodCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("'change_period'-command executing");

        logger.info(request.getParameter("userId"));
        logger.info(request.getParameter("direction"));
        logger.info(request.getParameter("currentDate"));
        logger.info(request.getParameter("period"));
        logger.info(request.getParameter("dataType"));

        String receivedId = request.getParameter("userId");
        String direction = request.getParameter("direction");
        LocalDate receivedDate = LocalDate.parse(request.getParameter("currentDate"));
        String receivedPeriod = request.getParameter("period");

        String jsonRefDate;
        String jsonPeriodData;
        String jsonTotalData;
        String[] commonData;
        String jsonCommonData;

        RepresentationProcessor representationProcessor = new RepresentationProcessor();

        representationProcessor.collect(receivedId, direction, receivedDate, receivedPeriod);

        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //Создание формата даты
        mapper.setDateFormat(df);                                  //Назначение формата mapper

        jsonPeriodData = mapper.writeValueAsString(representationProcessor.getPeriodData());
        jsonTotalData = mapper.writeValueAsString(representationProcessor.getTotalData().toList());

        //TODO вынести в отедльный метод
        switch (receivedPeriod.toLowerCase()) {
            case DAY:
                jsonRefDate = mapper.writeValueAsString(representationProcessor.getPeriodData()[0].getDate());
                break;
            case MONTH:
                jsonRefDate = mapper.writeValueAsString(representationProcessor.getPeriodData()[7].getDate()); // 7 - точно один из дней которые нужно отобразить
                break;
            default:
                jsonRefDate = mapper.writeValueAsString(representationProcessor.getPeriodData()[7].getDate()); // 7 - точно один из дней которые нужно отобразить
        }

        commonData = new String[]{jsonRefDate, jsonPeriodData, jsonTotalData};
        jsonCommonData = mapper.writeValueAsString(commonData);

        logger.info(jsonRefDate);
        logger.info(jsonPeriodData);
        logger.info(jsonTotalData);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonCommonData);

        return jsonPeriodData;
    }
}
