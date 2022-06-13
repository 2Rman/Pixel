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

public class PreviousCommand implements Command{

    Logger logger = Logger.getLogger(PreviousCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("'previous'-command executing");

        logger.info(request.getParameter("userId"));
        logger.info(request.getParameter("currentDate"));
        logger.info(request.getParameter("period"));

        String receivedId = request.getParameter("userId");
        LocalDate receivedDate = LocalDate.parse(request.getParameter("currentDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd")).minusMonths(1);
        String receivedPeriod = request.getParameter("period");
        String jsonMonthList;
        String jsonPeriodData;
        String jsonTotalData;

        RepresentationProcessor representationProcessor = new RepresentationProcessor();

        representationProcessor.collect(receivedId, receivedDate, receivedPeriod);
        request.setAttribute("monthList", MONTH_LIST);

        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        jsonPeriodData = mapper.writeValueAsString(representationProcessor.getPeriodData());
        logger.info(jsonPeriodData);

//        response.getWriter().write("ТЛЕН == ГЛЕХ");

        request.setAttribute("periodData", representationProcessor.getPeriodData());

        request.setAttribute("totalData", representationProcessor.getTotalData().toMap());

        return ACCOUNT_MONTH_TABLE;
    }
}
