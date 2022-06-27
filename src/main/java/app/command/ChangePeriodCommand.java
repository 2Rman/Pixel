package app.command;

import app.util.RepresentationProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static app.constant.ConstantPage.ACCOUNT_MONTH_TABLE;
import static app.constant.ConstantUtil.MONTH_LIST;

public class ChangePeriodCommand implements Command{

    Logger logger = Logger.getLogger(ChangePeriodCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("'change_period'-command executing");

        logger.info(request.getParameter("userId"));
        logger.info(request.getParameter("direction"));
        logger.info(request.getParameter("currentDate"));
        logger.info(request.getParameter("period"));

        String receivedId = request.getParameter("userId");
        String direction = request.getParameter("direction");
        LocalDate receivedDate = LocalDate.parse(request.getParameter("currentDate"));
        String receivedPeriod = request.getParameter("period");

        String jsonMonthList;
        String jsonPeriodData;
        String jsonTotalData;
        String[] commonData;
        String jsonCommonData;

        RepresentationProcessor representationProcessor = new RepresentationProcessor();

        representationProcessor.collect(receivedId, direction, receivedDate, receivedPeriod);

        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        jsonPeriodData = mapper.writeValueAsString(representationProcessor.getPeriodData());
        jsonTotalData = mapper.writeValueAsString(representationProcessor.getTotalData().toList());
        jsonMonthList = mapper.writeValueAsString(MONTH_LIST);

        commonData = new String[]{jsonMonthList, jsonPeriodData, jsonTotalData};
        jsonCommonData = mapper.writeValueAsString(commonData);

        logger.info(jsonMonthList);
        logger.info(jsonPeriodData);
        logger.info(jsonTotalData);

        response.getWriter().write(jsonCommonData);

        return jsonPeriodData;
    }
}
