package app.dao;

import app.connection.ConnectionPool;
import app.entity.Entity;
import app.entity.ExpenseTypeEntity;
import app.entity.ServiceTypeEntity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static app.constant.ConstantQuery.*;

/**
 * Класс реализующий сущность типа затраты
 */
public class ExpenseTypeDAO implements AbstractDAO {

    Logger logger = Logger.getLogger(ExpenseTypeDAO.class);

    /**
     * Метод для получения всех видов затрат, определенных авторизованным аккаунтом
     *
     * @param id авторизованного аккаунта
     * @return список видов затрат данного аккаунта
     */
    @Override
    public List getAll(String id) {

        logger.info("Getting all expense-type data");

        List<ExpenseTypeEntity> expenseTypeEntities = new ArrayList<>();

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_ALL_EXPENSE_TYPES);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idExpenseType = resultSet.getString(1);
                String expenseName = resultSet.getString(2);
                expenseTypeEntities.add(new ExpenseTypeEntity(idExpenseType, expenseName));

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
        return expenseTypeEntities;
    }

    /**
     * Метод для получения строки конкретной затраты по id
     *
     * @param id затраты
     * @return заполненную сущность затраты
     */
    @Override
    public Entity getById(String id) {

        logger.info("Getting expense-type data by ID");

        ExpenseTypeEntity expenseTypeEntity = null;

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_SERVICE_TYPE_BY_ID);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idExpenseType = resultSet.getString(1);
                String expenseName = resultSet.getString(2);
                expenseTypeEntity = new ExpenseTypeEntity(idExpenseType, expenseName);

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
        return expenseTypeEntity;
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
