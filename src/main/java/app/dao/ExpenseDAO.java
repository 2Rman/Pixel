package app.dao;

import app.connection.ConnectionPool;
import app.entity.Entity;
import app.entity.ExpenseEntity;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static app.constant.ConstantQuery.*;

public class ExpenseDAO implements AbstractDAO {

    Logger logger = Logger.getLogger(ExpenseDAO.class);

    /**
     * Метод для вызова записей расхода за один день
     *
     * @param id   аккаунта из которого осуществляется запрос
     * @param date дата для которой посылается запрос
     * @return список записей в заданную дату
     */
    public List getDailyIncome(String id, LocalDate date) {

        logger.info("Getting current month expense data");

        List<ExpenseEntity> expenseEntities = new ArrayList<>();

        Date start = Date.valueOf(date.toString());

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_DAILY_EXPENSE);

            preparedStatement.setString(1, id);
            preparedStatement.setDate(2, start);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Date expenseDate = resultSet.getDate(1);
                String expenseName = resultSet.getString(2);
                int amount = resultSet.getInt(3);
                String commentary = resultSet.getString(4);
                new ExpenseEntity(expenseDate, expenseName, amount, commentary);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return expenseEntities;
    }

    /**
     * Метод для получения расходов за промежуток времени
     *
     * @param id        аккаунта авторизованного пользователя
     * @param dateStart начальная дата
     * @param dateEnd   конечная дата
     * @return список сущностей записей расхода
     */
    public List<ExpenseEntity> getPeriodExpense(String id, LocalDate dateStart, LocalDate dateEnd) {

        logger.info("Getting expense data between " + dateStart + " and " + dateEnd);

        List<ExpenseEntity> expenseEntities = new ArrayList<>();

        Date dStart = Date.valueOf(dateStart.toString());
        Date dEnd = Date.valueOf(dateEnd.toString());

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_PERIOD_EXPENSE);

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
                expenseEntities.add(new ExpenseEntity(date, typeNote, amount, name, commentary));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return expenseEntities;
    }

    /**
     * Метод получения всех записей о расходов из БД по id-account
     *
     * @param id текущего авторизованного аккаунта
     * @return список сущностей записей о расходах из БД
     */
    @Override
    public List<ExpenseEntity> getAll(String id) {

        logger.info("Getting all expense data");

        List<ExpenseEntity> expenseEntities = new ArrayList<>();

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_ALL_EXPENSE);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Date expenseDate = resultSet.getDate(1);
                String expenseName = resultSet.getString(2);
                int amount = resultSet.getInt(3);
                String commentary = resultSet.getString(4);
                expenseEntities.add(new ExpenseEntity(expenseDate, expenseName, amount, commentary));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return expenseEntities;
    }

    /**
     * Метод для получения конкретной записи из БД о доходе в виде id-шников
     *
     * @param id конкретной записи в БД о доходе
     * @return сущность записи дохода, заполненную id-ключами других таблиц
     */
    @Override
    public Entity getById(String id) {

        logger.info("Getting expense-note data by id");

        ExpenseEntity expenseEntity = null;

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_EXPENSE_BY_ID);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idExpense = resultSet.getString(1);
                Date expenseDate = resultSet.getDate(2);
                String idExpenseType = resultSet.getString(3);
                int amount = resultSet.getInt(4);
                String idAccount = resultSet.getString(5);
                String commentary = resultSet.getString(6);
                expenseEntity = new ExpenseEntity(idExpense, expenseDate, idExpenseType, amount, idAccount, commentary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return expenseEntity;
    }

    /**
     * Метод получения данных записи о расходе по ее id в виде текстовой информации
     *
     * @param id записи о прибыли
     * @return заполненную сущность записи в читабельном виде
     */
    public Entity getDataById(String id) {

        logger.info("Getting data-expense data by id");

        ExpenseEntity expenseEntity = null;

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_DATA_EXPENSE_BY_ID);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Date expenseDate = resultSet.getDate(1);
                String expenseName = resultSet.getString(2);
                int amount = resultSet.getInt(3);
                String commentary = resultSet.getString(4);
                expenseEntity = new ExpenseEntity(expenseDate, expenseName, amount, commentary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return expenseEntity;
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
