package app.dao;

import app.connection.ConnectionPool;
import app.entity.Entity;
import app.entity.ExpenseTypeEntity;
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

    /**
     * Метод для получения типов расходов по "маске" для подсказок во время набора на стороне клиента
     *
     * @param id id-шник пользователя, у которого проверяем наличие вводимых типов расходов
     * @param mask маска по которой производим поиск типов расходов в БД
     * @return список найденных расходов по маске
     */
    public List<String> getLike(String id, String mask) {

        logger.info("Getting expenses like " + mask);

        List<String> expensesLikeMask = new ArrayList<>();

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_EXPENSES_LIKE);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, mask + '%');

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String expenseName = resultSet.getString(1);
                expensesLikeMask.add(expenseName);
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
        return expensesLikeMask;
    }

    /**
     * Метод для проверки наличия конкретного названия расхода в БД
     *
     * @param expenseType название расхода
     * @return ответ присутствует или отсутствует такое название расхода в БД
     */
    public boolean isExpenseTypeInDatabase(String userId, String expenseType) {

        logger.info("Checking expense type with " + expenseType);

        boolean result = false;
        String expenseTypeId = null;

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_ID_EXPENSE_TYPE_BY_NAME);

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, expenseType);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                expenseTypeId = resultSet.getString(1);
            }

            if (expenseTypeId != null) {
                result = true;
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

        logger.info(result);

        return result;
    }

    /**
     * Метод добавляет новый тип расходов в БД
     *
     * @param expenseTypeEntity заполненная сущность "Тип расходов"
     * @return успешно ли выполнена операция
     */
    public int create(ExpenseTypeEntity expenseTypeEntity) {
        //TODO разобраться почему не оверрайдится метод

        logger.info("Creating new expense type");

        int result = 0;
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {

            preparedStatement = connection.prepareStatement(CREATE_EXPENSE_TYPE);

            preparedStatement.setString(1, expenseTypeEntity.getIdExpenseType());
            preparedStatement.setString(2, expenseTypeEntity.getExpenseTypeName());

            result = preparedStatement.executeUpdate();

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
        return result;
    }

    public String getIdByName(String userId, String expenseTypeName) {

        String expenseTypeId = null;

        logger.info("Searching id-expense type with " + expenseTypeName);

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        logger.info("Connection got");

        try {
            preparedStatement = connection.prepareStatement(GET_ID_EXPENSE_TYPE_BY_NAME);

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, expenseTypeName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                expenseTypeId = resultSet.getString(1);
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
        return expenseTypeId;
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
