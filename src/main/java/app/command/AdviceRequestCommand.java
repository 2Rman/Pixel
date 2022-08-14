package app.command;

import app.dao.ClientDAO;
import app.dao.ExpenseDAO;
import app.dao.ExpenseTypeDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static app.constant.ConstantUtil.EXPENSE;
import static app.constant.ConstantUtil.INCOME;

public class AdviceRequestCommand implements Command{

    Logger logger = Logger.getLogger(AdviceRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("AdviceCommand executing");

        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String mask = request.getParameter("mask");
        String jsonAdviceData;

        if (type.equals(INCOME)) {

            List<String> resultClients;

            ClientDAO clientDAO = new ClientDAO();

            resultClients = clientDAO.getLike(userId, mask);

            ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

            jsonAdviceData = mapper.writeValueAsString(resultClients);

            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonAdviceData);

            return jsonAdviceData;

        } else if (type.equals(EXPENSE)) {
            List<String> resultExpenses;

            ExpenseTypeDAO expenseTypeDAO = new ExpenseTypeDAO();

            resultExpenses = expenseTypeDAO.getLike(userId, mask);

            ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

            jsonAdviceData = mapper.writeValueAsString(resultExpenses);

            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonAdviceData);

            return jsonAdviceData;

        } else {
            return null;
        }
    }
}
