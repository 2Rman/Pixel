package app.dao;

import app.connection.ConnectionPool;
import app.entity.Entity;
import app.entity.NoteExpenseEntity;
import app.entity.NoteIncomeEntity;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static app.constant.ConstantQuery.GET_MONTH_EXPENSE;
import static app.constant.ConstantQuery.GET_MONTH_INCOME;

/**
 * Класс для манипуляций с БД в рамках месяца
 */
public class MonthDAO implements AbstractDAO {

    Logger logger = Logger.getLogger(MonthDAO.class);

    /**
     * Метод для получения данных дохода месяца
     *
     * @param id        аккаунта из которого осуществляется запрос
     * @param dateStart начальная дата в календаре на странице
     * @param dateEnd   конечная дата в календаре на странице
     * @return Список записей доходов в рамках заданных дат
     */
    public List<NoteIncomeEntity> getMonthIncome(String id, LocalDate dateStart, LocalDate dateEnd) {

        logger.info("Getting current month income data");

        List<NoteIncomeEntity> notes = new ArrayList<>();

        Date dStart = Date.valueOf(dateStart.toString());
        Date dEnd = Date.valueOf(dateEnd.toString());

        Connection connection = ConnectionPool.getInstance().getConnection();

        logger.info("Connection got");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_MONTH_INCOME);

            preparedStatement.setString(1, id);
            preparedStatement.setDate(2, dStart);
            preparedStatement.setDate(3, dEnd);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Date date = resultSet.getDate(1);
                String typeNote = resultSet.getString(2);
                int amount = resultSet.getInt(3);
                String name = resultSet.getString(4) + " " + resultSet.getString(5);
                String commentary = resultSet.getString(6);
                notes.add(new NoteIncomeEntity(date, typeNote, amount, name, commentary));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    /**
     * Метод для получения данных расхода месяца
     *
     * @param id        аккаунта из которого осуществляется запрос
     * @param dateStart начальная дата в календаре на странице
     * @param dateEnd   конечная дата в календаре на странице
     * @return Список записей расходов в рамках заданных дат
     */
    public List<NoteExpenseEntity> getMonthExpense(String id, LocalDate dateStart, LocalDate dateEnd) {

        logger.info("Getting current month expense data");

        List<NoteExpenseEntity> notes = new ArrayList<>();

        Date dStart = Date.valueOf(dateStart.toString());
        Date dEnd = Date.valueOf(dateEnd.toString());

        Connection connection = ConnectionPool.getInstance().getConnection();

        logger.info("Connection got");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_MONTH_EXPENSE);

            preparedStatement.setString(1, id);
            preparedStatement.setDate(2, dStart);
            preparedStatement.setDate(3, dEnd);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Date date = resultSet.getDate(1);
                String typeNote = resultSet.getString(2);
                int amount = resultSet.getInt(3);
                String comment = resultSet.getString(4);
                notes.add(new NoteExpenseEntity(date, typeNote, amount, comment));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    @Override
    public List getALL() {
        return null;
    }

    @Override
    public Entity getById(Object id) {
        return null;
    }

    @Override
    public int create(Entity entity) {
        return 0;
    }

    @Override
    public boolean update(Entity entity) {
        return false;
    }

    @Override
    public boolean delete(Entity entity) {
        return false;
    }
}
